package com.quxin.freshfun.controller.order;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quxin.freshfun.model.orders.OrderDetailsPOJO;
import com.quxin.freshfun.model.refund.RefundPOJO;
import com.quxin.freshfun.service.order.OrderDetailsService;
import com.quxin.freshfun.service.refund.RefundService;

/**
 * 订单
 * 
 * @author 付士豪
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderDetailsService orderService;

	@Autowired
	private RefundService refundService;

	private static String IMAGEIP;

	@Value("${rip}")
	public void setRip(String value) {
		IMAGEIP = value;
	}

	@RequestMapping("/orderList")
	public String orderList(HttpServletRequest request) {
		String curPage = request.getParameter("curPage");
		int pageSize = 5;
		int countPage = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		List<OrderDetailsPOJO> detailListPage = orderService.findOrder(map);
		if (detailListPage != null) {
			int count = detailListPage.size();
			countPage = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		}
		if (curPage == null) {
			curPage = "1";
		}
		if (Integer.parseInt(curPage) > countPage) {
			curPage = "" + countPage;
			if(countPage == 0){
				curPage = "1";
			}
		}
		int begin = (Integer.parseInt(curPage) - 1) * pageSize;
		map.put("pageSize", pageSize);
		map.put("begin", begin);
		List<OrderDetailsPOJO> detailList = orderService.findOrder(map);
		request.setAttribute("detailList", detailList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", ""+countPage);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return "/order/orderDetailList";
	}

	@RequestMapping("/toShowReason")
	public String toShowReason(HttpServletRequest request) {
		String orderDetailid = request.getParameter("orderDetailsId");
		RefundPOJO refund = refundService.findRefundByDetailId(orderDetailid);
		request.setAttribute("refund", refund);
		request.setAttribute("orderDetailid", orderDetailid);
		return "/order/retReason";
	}

	@RequestMapping("/toSendGoods")
	public String toSendGoods(HttpServletRequest request){
		return "/order/toSendGoods";
	}
	
	@RequestMapping("/checkOrderDetailId")
	public String checkOrderDetailId(HttpServletRequest request, HttpServletResponse response){
		String orderDetailid = request.getParameter("orderDetailId");
		String result="";
		List<OrderDetailsPOJO> list = orderService.findOrderByDetailId(orderDetailid);
		if(list==null||list.size()==0){
			result="您所要录入的订单不存在";
		}else{
			if((list.get(0).getOrderStatus()==0||list.get(0).getOrderStatus()==1)&&list.get(0).getPayStatus()==1){
				result="您所要录入的订单为待发货状态<br/><br/>请继续录入物流信息";
			}else{
				result="您所要录入的订单非待发货状态<br/><br/>请确认订单信息";
			}
		}
		response.setContentType("text/plain;charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/updateOrderDetail")
	public String updateOrderDetail(HttpServletRequest request, HttpServletResponse response){
		String orderDetailid = request.getParameter("orderDetailId");
		String orderWuliuNo = request.getParameter("orderWuliuNo");
		String companyCode = request.getParameter("companyCode");
		String result="";
		List<OrderDetailsPOJO> list = orderService.findOrderByDetailId(orderDetailid);
		if(list==null||list.size()==0){
			result="您所要录入的订单不存在";
		}else{
			if((list.get(0).getOrderStatus()==0||list.get(0).getOrderStatus()==1)&&list.get(0).getPayStatus()==1){
				result="您所要录入的订单为待发货状态<br/><br/>请继续录入物流信息";
				list.get(0).setDeliveryName(companyCode);
				list.get(0).setDeliveryNum(orderWuliuNo);
				list.get(0).setOrderStatus(2);
				list.get(0).setDeliveryTime(System.currentTimeMillis()/1000);;
				orderService.updateOrderDetail(list.get(0));
				result="物流信息录入成功";
			}else{
				result="您所要录入的订单非待发货状态<br/><br/>请继续确认订单信息";
			}
		}
		response.setContentType("text/plain;charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/retOrder")
	public String retOrder(HttpServletRequest request, HttpServletResponse response) {
		String orderDetailid = request.getParameter("orderDetailId");
		String isOk = request.getParameter("isOk");
		orderService.dealRetOrder(orderDetailid, Integer.parseInt(isOk));
		String result = "";
		if ("2".equals(isOk))
			result = "您已成功完成退款操作";
		else
			result = "您已成功拒绝退款操作";
		response.setContentType("text/plain;charset=UTF-8");
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
