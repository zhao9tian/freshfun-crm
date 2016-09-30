package com.quxin.freshfun.service.withdraw;

import com.quxin.freshfun.model.withdraw.WithdrawPOJO;

import java.util.List;
import java.util.Map;

/**
 * Created by qucheng on 2016/9/28.
 */
public interface WithdrawService {

    /**
     * 查询对应状态的总数
     * @param map
     * @return
     */
    Integer queryCountWithdraw(Map<String , Object> map);

    /**
     * 根据查询条件查出对应状态的信息
     * @param qc
     * @return
     */
    List<WithdrawPOJO> queryWithDrawList(Map<String , Object> qc);

    /**
     * 修改为已处理
     * @param id
     * @return
     */
    Integer modifiedStatus(String id);

    /**
     * 修改状态为驳回和原因
     * @param rejectReason
     * @param id
     * @return
     */
    Integer modifiedStatusAndReason(String rejectReason , String id);


}
