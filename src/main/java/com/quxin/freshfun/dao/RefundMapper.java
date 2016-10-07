package com.quxin.freshfun.dao;

import com.quxin.freshfun.model.refund.RefundPOJO;

import java.util.List;
import java.util.Map;

public interface RefundMapper {
	/**
	 * 根据分页信息查询退款信息
	 * @param map  分页参数
	 * @return  退款信息集合
	 */
	List<RefundPOJO> selectRefundByPage(Map<String,Object> map);
}
