package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quxin.freshfun.model.orders.OrderDetailsPOJO;

public interface OrderDetailsMapper {
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
	List<OrderDetailsPOJO> findRetOrder(Map<String,Object> map);
	
	void dealRetOrder(@Param("detailId")String detailId,@Param("isOk")Integer isOk);
	
	void updateByPrimaryKeySelective(OrderDetailsPOJO odp);
	
	
	/**********************************定时任务*********************************************/
	/**
	 * 修改订单状态
	 */
	void updateOrderStatus3(Map<String, Object> map);
	
	/**
	 * 修改交易状态
	 * @param map
	 */
	void updateRevenueState(Map<String, Object> map);

	/**
	 * 查询出所有订单已过期的id
	 * @return
	 */
	List<String> queryOrderIds();
}
