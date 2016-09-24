package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.orders.OrdersPOJO;

public interface OrdersMapper {
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
	
	
	/**
	 * 根据商品ID查询商品销量
	 * @param goodsId
	 * @return
	 */
	Integer findSalesByGoodsId(Integer goodsId);
	
}
