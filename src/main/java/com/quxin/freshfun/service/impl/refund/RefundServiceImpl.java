package com.quxin.freshfun.service.impl.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.RefundMapper;
import com.quxin.freshfun.model.refund.RefundPOJO;
import com.quxin.freshfun.service.refund.RefundService;
@Service
public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundMapper refundMapper;
	
	@Override
	public RefundPOJO findRefundByDetailId(String detailId) {
		// TODO Auto-generated method stub
		return refundMapper.findRefundByDetailId(detailId);
	}

}
