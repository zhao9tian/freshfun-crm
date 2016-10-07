package com.quxin.freshfun.service.refund;

import com.quxin.freshfun.model.refund.RefundPOJO;

import java.util.List;

public interface RefundService {
	/**
	 * 根据分页信息查询退款信息
	 * @param curPage  当前页
	 * @param pageSize  页面数据条数
	 * @return  退款信息集合
	 */
	public List<RefundPOJO> queryRefundWithPage(Integer curPage, Integer pageSize);
}
