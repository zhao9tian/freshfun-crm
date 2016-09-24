package com.quxin.freshfun.dao;

import com.quxin.freshfun.model.refund.RefundPOJO;

public interface RefundMapper {
	RefundPOJO findRefundByDetailId(String detailId);
}
