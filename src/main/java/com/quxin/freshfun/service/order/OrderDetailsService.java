package com.quxin.freshfun.service.order;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.orders.OrderDetailsPOJO;

public interface OrderDetailsService {
	/**
	 * 根据商品ID查询销售记录
	 * @param goodsId
	 * @return
	 */
	List<OrderDetailsPOJO> findOrderByGoodsId(Integer goodsId);
	
	/**
	 * 根据orderDetailId查询销售记录
	 * @param orderDetailId
	 * @return
	 */
	List<OrderDetailsPOJO> findOrderByDetailId(String orderDetailId);
	
	/**
	 * 查询退货订单信息
	 * @param map
	 * @return
	 */
	List<OrderDetailsPOJO> findOrder(Map<String,Object> map);
	
	void dealRetOrder(String detailId,Integer isOk);
	
	void updateOrderDetail(OrderDetailsPOJO odp);
	
	/**
	 * 7天之后未收货的默认收货,修改收货状态为3,
	 * 填上七天后的时间为收货时间,
	 * 修改用户收益表确认收货
	 */
	void updateOrderStatus3();
}
