package com.quxin.freshfun.service.impl.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.OrdersMapper;
import com.quxin.freshfun.model.orders.OrdersPOJO;
import com.quxin.freshfun.service.order.OrdersService;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper mapper;

	@Override
	public List<OrdersPOJO> findAll(Map<String, Object> map) {
		List<OrdersPOJO> list = mapper.findAll(map);
		return list;
	}

	@Override
	public OrdersPOJO findByGoodsId(Integer goodsId) {
		OrdersPOJO orders =mapper.findByGoodsId(goodsId);
		return orders;
	}

}
