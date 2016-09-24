package com.quxin.freshfun.service.refund;

import com.quxin.freshfun.model.refund.RefundPOJO;

public interface RefundService {
	public RefundPOJO findRefundByDetailId(String detailId);
}
