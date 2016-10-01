//package com.quxin.freshfun.controller.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//
//import com.quxin.freshfun.service.order.OrderDetailsService;
//
///**
// * 定时任务 发货后的七天之后默认收货
// * @author TuZl
// * @time 2016年9月20日上午11:26:01
// */
//@Controller
//public class ChangeStateTask {
//
//	@Autowired
//	private OrderDetailsService orderDetailsService;
//
//	@Scheduled(cron="0 0 1 * * ? ")   //每天1点钟触发
//	public void receivingStateTask(){
//		orderDetailsService.updateOrderStatus3();
//	}
//}
