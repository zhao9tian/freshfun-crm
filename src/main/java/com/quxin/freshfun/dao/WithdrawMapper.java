package com.quxin.freshfun.dao;


import com.quxin.freshfun.model.withdraw.WithdrawPOJO;

import java.util.List;
import java.util.Map;

/**
 * Created by qucheng on 2016/9/28.
 */
public interface WithdrawMapper {

	Integer selectCountWithdraw(Map<String , Object> map);
	/**
	 * 根据查询条件查询所有提现信息
	 * @param queryContion
	 * @return
	 */
	List<WithdrawPOJO> selectWithdrawList(Map<String , Object> queryContion);

	/**
	 * 修改状态为已处理
	 * @param map
	 * @return
	 */
    Integer updateToHandled(Map<String , Object> map);

	/**
	 * 修改为驳回且新增原因
	 * @param map
	 * @return
	 */
	Integer updateToReject(Map<String , Object> map);
}
