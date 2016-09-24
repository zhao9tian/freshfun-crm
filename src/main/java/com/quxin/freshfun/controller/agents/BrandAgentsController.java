package com.quxin.freshfun.controller.agents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.goods.GoodsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quxin.freshfun.model.goods.GoodsPOJO;
import com.quxin.freshfun.model.orders.OrderDetailsPOJO;
import com.quxin.freshfun.service.goods.GoodsService;
import com.quxin.freshfun.service.order.OrderDetailsService;

@Controller
@RequestMapping("/brandAgent")
public class BrandAgentsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderDetailsService ordersService;

	/**
	 * 代理商品列表
	 * 
	 * @return
	 */
	@RequestMapping("/getBrandAgentGoods")
	public List<GoodsPOJO> getBrandAgentGoods() {
		List<GoodsPOJO> goodsList = goodsService.findMysqlList(null);
		for (GoodsPOJO goods : goodsList) {
			goods.setSales(goodsService.getLastSales(goods.getId()));
		}
		return goodsList;
	}

	/**
	 * 商品详情
	 * 
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/getGoodsDetailById")
	public GoodsDetail getGoodsDetailById(Integer goodsId) {
		GoodsDetail goods = goodsService.getGoodsByGoodsId(goodsId);
		return goods;
	}

	/**
	 * 我的品牌
	 * 
	 * @param merchant_proxy_id
	 */
	@RequestMapping("/toBrands")
	public List<GoodsPOJO> toMyBrand(Integer merchant_proxy_id) {
		Map<String, Object> doos = new HashMap<String, Object>();
		doos.put("mpId", merchant_proxy_id);
		List<GoodsPOJO> goods = goodsService.findProxyGoods(doos);
		for (GoodsPOJO goodsPOJO : goods) {
			Double salesMoney = goodsService.getAllSales(goodsPOJO.getId()) * Double.parseDouble(goodsPOJO.getShopPriceString());
			goodsPOJO.setSales(goodsService.getAllSales(goodsPOJO.getId()));
			goodsPOJO.setSalesMoney(salesMoney);
		}
		return goods;
	}

	/**
	 * 提取收益
	 * 
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/getIncome")
	public List<OrderDetailsPOJO> getIncome(Integer goodsId) {
		List<OrderDetailsPOJO> orderDetails = ordersService.findOrderByGoodsId(goodsId);
		System.out.println(orderDetails);
		return orderDetails;

	}
}
