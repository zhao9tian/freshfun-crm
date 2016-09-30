package com.quxin.freshfun.service.impl.withdraw;

import com.quxin.freshfun.dao.WithdrawMapper;
import com.quxin.freshfun.model.withdraw.WithdrawPOJO;
import com.quxin.freshfun.service.withdraw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qucheng on 2016/9/28.
 */
@Service("withdrawService")
public class WithdrawImpl implements WithdrawService{

    @Autowired
    private WithdrawMapper withdrawMapper;

    @Override
    public Integer queryCountWithdraw(Map<String , Object> map) {
        return withdrawMapper.selectCountWithdraw(map);
    }

    @Override
    public List<WithdrawPOJO> queryWithDrawList(Map<String , Object> qc) {
//        Map map = ResultUtil.bean2map(qc);
       return  withdrawMapper.selectWithdrawList(qc);
    }

    @Override
    public Integer modifiedStatus(String id) {
        Map<String , Object> map = new HashMap();
        map.put("id" , id);
        map.put("updateDate" , System.currentTimeMillis()/1000);
        return withdrawMapper.updateToHandled(map);
    }

    @Override
    public Integer modifiedStatusAndReason(String rejectReason , String id) {
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("handlerReason" ,rejectReason );
        map.put("id" , id);
        map.put("updateDate" , System.currentTimeMillis()/1000);
        return withdrawMapper.updateToReject(map);
    }

}
