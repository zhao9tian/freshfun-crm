package com.quxin.freshfun.service.impl.order;

import com.quxin.freshfun.dao.GoodsMapper;
import com.quxin.freshfun.dao.OrderDetailsMapper;
import com.quxin.freshfun.model.goods.GoodsPOJO;
import com.quxin.freshfun.model.orders.OrderDetailsPOJO;
import com.quxin.freshfun.service.order.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	@Autowired
	private OrderDetailsMapper mapper;
	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public List<OrderDetailsPOJO> findOrderByGoodsId(Integer goodsId) {
		List<OrderDetailsPOJO> orderDetails = mapper.findOrderByGoodsId(goodsId);
		return orderDetails;
	}
	
	@Override
	public List<OrderDetailsPOJO> findOrder(Map<String,Object> map) {
		// TODO Auto-generated method stub
		List<OrderDetailsPOJO> orderDetailsList = mapper.findRetOrder(map);
		GoodsPOJO goods = null;
		if(orderDetailsList != null){
			for(OrderDetailsPOJO detail : orderDetailsList){
				if(detail.getIsLimit()==0){
					goods = goodsMapper.getGoodsById(detail.getGoodsId());
				}
				detail.setGoods(goods);
			}
		}
		return orderDetailsList;
	}

	@Override
	public void dealRetOrder(String detailId, Integer isOk) {
		// TODO Auto-generated method stub
		mapper.dealRetOrder(detailId, isOk);
	}

	@Override
	public List<OrderDetailsPOJO> findOrderByDetailId(String orderDetailId) {
		// TODO Auto-generated method stub
		List<OrderDetailsPOJO> orderDetails = mapper.findOrderByDetailId(orderDetailId);
		return orderDetails;
	}

	@Override
	public void updateOrderDetail(OrderDetailsPOJO odp) {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(odp);
	}

	@Override
	public void updateOrderStatus3() {
		//查询出已过期的order_id
		List<String> ids = mapper.queryOrderIds();
		if(ids != null && ids.size() >0){
			//1修改订单状态为已收货,并填上收货时间
			Map<String, Object> map = new  HashMap<String, Object>();
			map.put("ids", ids);
			mapper.updateOrderStatus3(map);
			//2修改in_state为已完成交易
			mapper.updateRevenueState(map);
		}
		
	}

}
