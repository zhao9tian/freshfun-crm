package com.quxin.freshfun.service.impl.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.RefundMapper;
import com.quxin.freshfun.model.refund.RefundPOJO;
import com.quxin.freshfun.service.refund.RefundService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RefundServiceImpl implements RefundService {

	@Autowired
	private RefundMapper refundMapper;
	
	@Override
	public List<RefundPOJO> queryRefundWithPage(Integer curPage, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		Integer start = (curPage-1)*pageSize;
		map.put("start",start);
		map.put("pageSize",pageSize);
		List<RefundPOJO> list = refundMapper.selectRefundByPage(map);
		if(list!=null&&list.size()>0){
			return list;
		}
		return null;
	}

}
