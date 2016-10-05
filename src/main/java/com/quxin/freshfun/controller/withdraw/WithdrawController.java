package com.quxin.freshfun.controller.withdraw;

import com.quxin.freshfun.model.flow.FlowParam;
import com.quxin.freshfun.model.withdraw.QueryContion;
import com.quxin.freshfun.model.withdraw.WithdrawOutput;
import com.quxin.freshfun.model.withdraw.WithdrawPOJO;
import com.quxin.freshfun.service.flow.FlowService;
import com.quxin.freshfun.service.withdraw.WithdrawService;
import com.quxin.freshfun.utils.DateUtils;
import com.quxin.freshfun.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提现controller
 * Created by qucheng on 2016/9/28.
 */
@Controller
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;
    @Autowired
    private FlowService flowService;

    /**
     * 根据查询条件分页查询
     *
     * @param qc
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/queryWithdrawList" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryWithdrawList(@RequestBody QueryContion qc) throws ParseException {
        //如果查询条件为空 就查询所有
        Map<String, Object> queryContion = new HashMap<String, Object>();
        if (qc.getState() == null || "0".equals(qc.getState())) {
            queryContion.put("state", null); //查询全部
        } else if("1".equals(qc.getState())){
            queryContion.put("state", 0);//查询待处理
        }else if("2".equals(qc.getState())){
            queryContion.put("state", 1);//查询已处理
        }else if("3".equals(qc.getState())){
            queryContion.put("state", 2); //查询驳回
        }
        //根据查询条件查询总记录数
        Integer countWithdraw = withdrawService.queryCountWithdraw(queryContion);
        Integer start = 0;
        Integer currentPage = 1;
        //计算总页数用于限制当前页大小
        Integer totalPage = countWithdraw % 10 == 0 ? countWithdraw / 10 : (countWithdraw / 10 + 1);
        if (qc.getCurrentPage() != null) {
            currentPage = Integer.parseInt(qc.getCurrentPage());
            if (currentPage < 1) {
                currentPage = 1;
            }
            if (currentPage > totalPage) {
                currentPage = totalPage;
            }
        }
        start = (currentPage - 1) * 10;
        queryContion.put("start", start);
        //分页查询
        List<WithdrawPOJO> withdrawPOJOs = withdrawService.queryWithDrawList(queryContion);
        List<WithdrawOutput> withdrawOutputs = new ArrayList<>();
        //遍历生成出参
        if (withdrawPOJOs != null && withdrawPOJOs.size() > 0) {
            for (WithdrawPOJO withDraw : withdrawPOJOs) {
                WithdrawOutput withdrawOutput = new WithdrawOutput();
                withdrawOutput.setId(withDraw.getId());
                withdrawOutput.setUserId(withDraw.getUserId());
                withdrawOutput.setWithDrawType(withDraw.getWithDrawType());
                withdrawOutput.setPaymentAccount(withDraw.getPaymentAccount());
                withdrawOutput.setWithDrawType(withDraw.getWithDrawType());
                if (withDraw.getState() == 0) {
                    withdrawOutput.setState("待处理");
                } else if (withDraw.getState() == 1) {
                    withdrawOutput.setState("已处理");
                } else if (withDraw.getState() == 2) {
                    withdrawOutput.setState("已驳回");
                }
                DecimalFormat df = new DecimalFormat("#0.00");
                withdrawOutput.setWithDrawPrice(df.format(((double) withDraw.getWithDrawPrice()) / 100));
                if (withDraw.getUpdateDate() != null && withDraw.getUpdateDate() != 0) {
                    withdrawOutput.setUpdateDate(DateUtils.longToString(withDraw.getUpdateDate(), "yyyy-MM-dd HH:mm:ss"));
                } else {
                    withdrawOutput.setUpdateDate("--");
                }
                withdrawOutput.setCreateDate(DateUtils.longToString(withDraw.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
                withdrawOutputs.add(withdrawOutput);
            }
        }
        Map<String, Object> reultMap = ResultUtil.success(withdrawOutputs);
        reultMap.put("totalPage", totalPage);
        return reultMap;
    }

    /**
     * 变更为已处理
     * @param operation
     * @param id
     * @return
     */
    @RequestMapping(value = "/toHandled" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> toHandled(String operation,String id) {
        Map<String, Object> reultMap = null;
        if (operation != null && "10".equals(operation)) {
            Integer record = withdrawService.modifiedStatus(id);
            if (record == null || record == 0) {
                reultMap = ResultUtil.fail(1004, "没有id为:" + id + "的数据");
            } else {
                //退款成功将流水记录到flow表里
                WithdrawPOJO withdrawPOJO = withdrawService.queryWithdrawPOJOById(id);
                FlowParam flowParam = new FlowParam();
                flowParam.setUserId(withdrawPOJO.getUserId());
                if(withdrawPOJO.getWithdrawSource() == 10){
                    flowParam.setFetcherFlow(-withdrawPOJO.getWithDrawPrice());
                }
                if(withdrawPOJO.getWithdrawSource() == 20){
                    flowParam.setAgentFlow(-withdrawPOJO.getWithDrawPrice());
                }
                flowParam.setCreated(System.currentTimeMillis()/1000);
                flowParam.setUpdated(System.currentTimeMillis()/1000);
                reultMap = ResultUtil.success(flowService.add(flowParam));
            }
        } else {
            reultMap = ResultUtil.fail(1004, "操作指定不正确");
        }
        return reultMap;
    }

    /**
     * 变更为驳回
     *
     * @param operation
     * @param rejectReason
     * @param id
     * @return
     */
    @RequestMapping(value = "/toReject" , method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> toReject(String operation, String rejectReason, String id) {
        Map<String, Object> reultMap = null;
        if (operation != null && "20".equals(operation)) {
            String str = "";
            if(rejectReason != null && !"".equals(rejectReason)){
                try {
                    str= new String(rejectReason.getBytes("iso-8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            Integer record = withdrawService.modifiedStatusAndReason(str, id);
            if (record == null || record == 0) {
                reultMap = ResultUtil.fail(1004, "没有id为:" + id + "的数据");
            } else {
                reultMap = ResultUtil.success(1);
            }
        } else {
            reultMap = ResultUtil.fail(1004, "操作指定不正确");
        }
        return reultMap;
    }

}
