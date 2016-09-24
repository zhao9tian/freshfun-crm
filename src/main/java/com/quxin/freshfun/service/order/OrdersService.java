package com.quxin.freshfun.service.order;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.orders.OrdersPOJO;

public interface OrdersService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	List<OrdersPOJO> findAll(Map<String, Object> map);
	
	/**
	 * 根据商品ID查询订单信息
	 * @param goodsId
	 * @return
	 */
	OrdersPOJO findByGoodsId(Integer goodsId);
	
}
