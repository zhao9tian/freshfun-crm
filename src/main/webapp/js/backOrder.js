//页码
var p=1;

var pageType=0;

var imgUrl = 'http://pic1.freshfun365.com';
//总的页码数
//全部总页码数
var allTotalCount;
//等待付款总页码数
var waitingPaymentTotalCount;

//等待发货总页码数
var waitingDeliveryTotalCount;



//加载执行点击ALL
window.onload = function() {
	$('#all').click(function() {
		console.log('全部');
		pageType=0;
		$("tbody").html("");
		//加载全部第一页
		$.ajax({
			type:"get",
			url:"https://www.freshfun365.com/FreshFun/selectBackstageOrdersCount.do",
		    success:function(DATA){
		    	console.log(JSON.parse(DATA));
			    var DATA = JSON.parse(DATA);
		    	if(DATA.status.code == '1001'){
			    	allTotalCount = DATA.data;
			    	$(".tcdPageCode").createPage({
						pageCount: allTotalCount,
						current: 1,
						backFn: function(p) {
							if(pageType==0){
								console.log("加载全部订单，页数："+p);
								addOrder(p);
							}
						}
					});
		    	}else if(DATA.status.code == '1004'){
		    		alert('请求失败');
		    	};
		    },
		    error:function(){
		    	alert('请求失败');
		    }
		});
		addOrder(1);

	});
	$('#all').click();
}



//选项卡
$('.tab').pignoseTab({
	animation: true,
	children: '.tab'
});

//点击等待付款
$('#waitingPayment').click(function() {
	console.log('等待付款');
	pageType=1;
	$("tbody").html("");
	//请求分页总数
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstagePendingPaymentOrderCount.do",
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	waitingPaymentTotalCount = DATA.data;
		    	console.log(waitingPaymentTotalCount);
		    	if(waitingPaymentTotalCount==0){
		    		alert('没有等待付款订单！');
		    	}else{
		    		waitingPaymentOrder(1);
		    		//点击等待付款，分页
					$(".tcdPageCode").createPage({
						pageCount: waitingPaymentTotalCount,
						current: 1,
						backFn: function(p) {
							if(pageType==1){
								console.log("加载待付款订单，页数："+p);
								waitingPaymentOrder(p);
							}
						}
					});
		    	}
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});

})
//点击等待发货
$('#waitingDelivery').click(function() {
	console.log('等待发货');
	pageType=2;
	$("tbody").html("");
	//请求分页总数
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageAwaitDeliverOrderCount.do",
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	waitingDeliveryTotalCount = DATA.data;
		    	console.log(waitingDeliveryTotalCount);
		    	if(waitingDeliveryTotalCount==0){
		    		alert('没有等待发货订单！');
		    	}else{
		    		waitingDeliveryOrder(1);
		    		//点击等待付款，分页
					$(".tcdPageCode").createPage({
						pageCount: waitingDeliveryTotalCount,
						current: 1,
						backFn: function(p) {
							if(pageType==2){
								console.log("加载待发货订单，页数："+p);
								waitingDeliveryOrder(p);
							}
						}
					});
		    	}
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});


})
//点击等待确认收货
$('#waitingConfirmDelivery').click(function() {
	console.log('待确认收货');
	pageType=3;
	$("tbody").html("");
	//请求分页总数
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageAwaitGoodsReceiptCount.do",
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	waitingConfirmDeliveryTotalCount = DATA.data;
		    	console.log(waitingConfirmDeliveryTotalCount);
		    	if(waitingConfirmDeliveryTotalCount==0){
		    		alert('没有等待确认收货订单！');
		    	}else{
		    		waitingConfirmDeliveryOrder(1);
		    		//点击等待付款，分页
					$(".tcdPageCode").createPage({
						pageCount: waitingConfirmDeliveryTotalCount,
						current: 1,
						backFn: function(p) {
							if(pageType==3){
								console.log("加载待收货订单，页数："+p);
								waitingConfirmDeliveryOrder(p);
							}
						}
					});
		    	}
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败1004');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});
})
//点击交易完成
$('#dealOver').click(function() {
	console.log('交易完成');
	pageType=4;
	$("tbody").html("");
	//请求分页总数
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectFinishOrderCount.do",
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	dealOverTotalCount = DATA.data;
		    	console.log(dealOverTotalCount);
		    	if(dealOverTotalCount==0){
		    		alert('没有交易完成订单！');
		    	}else{
		    		dealOverOrder(1);
		    		//点击等待付款，分页
					$(".tcdPageCode").createPage({
						pageCount: dealOverTotalCount,
						current: 1,
						backFn: function(p) {
							if(pageType==4){
								console.log("加载交易完成订单，页数："+p);
								dealOverOrder(p);
							}
						}
					});
		    	}
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败1004');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});

})
//点击交易关闭
$('#dealClose').click(function() {
	console.log('交易关闭');
	pageType=5;
	$("tbody").html("");
	//请求分页总数
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageOrderCloseCount.do",
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	dealCloseTotalCount = DATA.data;
		    	console.log(dealCloseTotalCount);
		    	if(dealCloseTotalCount==0){
		    		alert('没有交易关闭订单！');
		    	}else{
		    		dealCloseOrder(1);
		    		//点击等待付款，分页
					$(".tcdPageCode").createPage({
						pageCount: dealCloseTotalCount,
						current: 1,
						backFn: function(p) {
							if(pageType==5){
								console.log("加载交易关闭订单，页数："+p);
								dealCloseOrder(p);
							}
						}
					});
		    	}
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败1004');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});

})




//加载全部订单信息
function addOrder(p) {
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageOrders.do?currentPage="+p,
		beforeSend: function() {
	        $("tbody").html("");
	    },
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	var order = '';
		    	var orderIdArr = [];
		    	$.each(DATA.data, function(n,obj) {
		    		var createDate = $.myTime.UnixToDate(obj.createDate);
		    		var orderSource='';
					if(obj.payPlateform==0){
						orderSource = '自然流量';
					}else if(obj.payPlateform==1){
						orderSource = '捕手推广';
					}else if(obj.payPlateform==2){
						orderSource = 'B端推广';
					};
					var orderState;
					if(obj.orderStatus==10){
						orderState = '等待付款';
					}else if(obj.orderStatus==30){
						orderState = '等待发货';
					}else if(obj.orderStatus==50){
						orderState = '待确认收货';
					}else if(obj.orderStatus==70 && obj.commentStatus == 0){
						orderState = '待评价';
					}else if(obj.orderStatus==70 && obj.commentStatus == 1){
						orderState = '已评价';
					}else if(obj.orderStatus==40){
						orderState = '申请退款';
					}else if(obj.orderStatus==20){
						orderState = '已退款';
					}else if(obj.orderStatus==90){
						orderState = '评价（暂用）';
					}else if(obj.orderStatus==100){
						orderState = '交易完成';
					}

		    		order += '<tr><th colspan="9"><div class="orderMsg"><span>订单编号：<i>'+obj.orderId+'</i></span><span></span><span>成交时间：<i>'+createDate+'</i></span><span>买家：<i>'+obj.name+'</i></span></div></th></tr><tr><th><dl><dt><img src="'+imgUrl+obj.goods.goodsImg+'"/></dt><dd><h4>'+obj.goods.goodsName+'</h4><h5>'+obj.goods.goodsDes+'</h5><h6>规格：默认</h6></dd></dl></th><th>￥<i>'+obj.goods.marketMoney+'</i></th><th>'+obj.count+'</th><th></th><th></th><th>'+orderSource+'</th><th></th><th>'+orderState+'</th><th><h5 class="remark remark'+obj.orderId+'">备注</h5><h5 class="shipments"></h5></th></tr><tr><th colspan="9" class="addressMsg"><span>收货信息：<i>'+obj.name+'</i>，<i>'+obj.tel+'</i>，<i id="addressDetail">'+obj.city+obj.address+'</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="copy"></i></span></th></tr>';
		    		orderIdArr.push('.remark'+obj.orderId);
		    	});

				$('tbody').html(order);
				console.log(orderIdArr);
				//备注
				$.each(orderIdArr, function(n,obj) {
					$(orderIdArr[n]).click(function() {
						$('.remarkFrame').css('display','block');
						localStorage.setItem('orderId',orderIdArr[n]);
					});
				});
				//确认
				$('.btn-Y').click(function(){
					var orderId = localStorage.getItem('orderId');
//					alert(orderId);
					var orderId = orderId.split('k')[1];
					var remark = $('textarea').val();
					console.log(remark);
					alert('已备注');
					$.ajax({
						type:"get",
						url:"https://www.freshfun365.com/FreshFun/orderRemark.do?orderId="+orderId+'&remark='+remark,
						success:function(data){
							console.log(JSON.parse(data));
						}
					});
					$('.remarkFrame').css('display','none');
					$('textarea').val('');
				})
				//取消
				$('.btn-N').click(function() {
					$('.remarkFrame').css('display','none');
				});

	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});

}

//加载等待付款订单信息
function waitingPaymentOrder(p) {
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstagePendingPaymentOrder.do?currentPage="+p,
		beforeSend: function() {
	        $("tbody").html("");
	    },
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
//	    		if(DATA.data.length==0){
//	    			alert('付款订单已过期');
//	    			return;
//	    		}
		    	var order = '';
		    	var orderIdArr = [];
		    	$.each(DATA.data, function(n,obj) {
		    		var createDate = $.myTime.UnixToDate(obj.createDate);
		    		var orderSource='';
					if(obj.payPlateform==0){
						orderSource = '自然流量';
					}else if(obj.payPlateform==1){
						orderSource = '捕手推广';
					}else if(obj.payPlateform==2){
						orderSource = 'B端推广';
					};
		    		order += '<tr><th colspan="9"><div class="orderMsg"><span>订单编号：<i>'+obj.orderId+'</i></span><span></span><span>成交时间：<i>'+createDate+'</i></span><span>买家：<i>'+obj.name+'</i></span></div></th></tr><tr><th><dl><dt><img src="'+imgUrl+obj.goods.goodsImg+'"/></dt><dd><h4>'+obj.goods.goodsName+'</h4><h5>'+obj.goods.goodsDes+'</h5><h6>规格：默认</h6></dd></dl></th><th>￥<i>'+obj.goods.marketMoney+'</i></th><th>'+obj.count+'</th><th></th><th></th><th>'+orderSource+'</th><th></th><th>等待付款</th><th><h5 class="remark remark'+obj.orderId+'">备注</h5><h5 class="shipments"></h5></th></tr><tr><th colspan="9" class="addressMsg"><span>收货信息：<i>'+obj.name+'</i>，<i>'+obj.tel+'</i>，<i id="addressDetail">'+obj.city+obj.address+'</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="copy"></i></span></th></tr>';
		    		orderIdArr.push('.remark'+obj.orderId);
		    	});

				$('tbody').html(order);
				//备注
				$.each(orderIdArr, function(n,obj) {
					$(orderIdArr[n]).click(function() {
						$('.remarkFrame').css('display','block');
						localStorage.setItem('orderId',orderIdArr[n]);
					});
				});
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});
}

//加载等待发货订单信息
function waitingDeliveryOrder(p) {
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageAwaitDeliverOrder.do?currentPage="+p,
		beforeSend: function() {
	        $("tbody").html("");
	    },
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	var order = '';
		    	var orderIdArr = [];
		    	var fhOrderIdArr=[];
		    	$.each(DATA.data, function(n,obj) {
		    		var createDate = $.myTime.UnixToDate(obj.createDate);
		    		var orderSource='';
					if(obj.payPlateform==0){
						orderSource = '自然流量';
					}else if(obj.payPlateform==1){
						orderSource = '捕手推广';
					}else if(obj.payPlateform==2){
						orderSource = 'B端推广';
					};
		    		order += '<tr><th colspan="9"><div class="orderMsg"><span>订单编号：<i>'+obj.orderId+'</i></span><span></span><span>成交时间：<i>'+createDate+'</i></span><span>买家：<i>'+obj.name+'</i></span></div></th></tr><tr><th><dl><dt><img src="'+imgUrl+obj.goods.goodsImg+'"/></dt><dd><h4>'+obj.goods.goodsName+'</h4><h5>'+obj.goods.goodsDes+'</h5><h6>规格：默认</h6></dd></dl></th><th>￥<i>'+obj.goods.marketMoney+'</i></th><th>'+obj.count+'</th><th></th><th></th><th>'+orderSource+'</th><th></th><th>等待发货</th><th><h5 class="remark remark'+obj.orderId+'">备注</h5><h5 class="shipments shipments'+obj.orderId+'">发货</h5></th></tr><tr><th colspan="9" class="addressMsg"><span>收货信息：<i>'+obj.name+'</i>，<i>'+obj.tel+'</i>，<i id="addressDetail">'+obj.city+obj.address+'</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="copy"></i></span></th></tr>';
		    		orderIdArr.push('.remark'+obj.orderId);
		    		fhOrderIdArr.push('.shipments'+obj.orderId);
		    	});

				$('tbody').html(order);
				$.each(orderIdArr, function(n,obj) {
					$(orderIdArr[n]).click(function() {
						$('.remarkFrame').css('display','block');
						localStorage.setItem('orderId',orderIdArr[n]);
					});
				});


				$.each(fhOrderIdArr, function(n,obj) {
					$(fhOrderIdArr[n]).click(function() {
						$('.shipmentsFrame').css('display','block');
						localStorage.setItem('fhOrderId',fhOrderIdArr[n]);
					});
				});

				//确认
				$('.sf-btn-Y').click(function(){
					var orderId = localStorage.getItem('fhOrderId');
					var orderId = orderId.split('t')[1].split('s')[1];
//					alert(orderId);
					var deliveryName = $('.deliveryName').val();
					var deliveryNum = $('.deliveryNum').val();
					var deliveryRemark = $('.bz-txt').val();
					var goodsCost = $('.goodsCost').val();
					alert('已发货');
					$.ajax({
						type:"post",
						url:"https://www.freshfun365.com/FreshFun/selectFinishOrderCount.do",
						dataType: 'JSON',
                		contentType: 'application/json;charset=utf-8',
						data:JSON.stringify({
							deliveryNum:deliveryNum,
							orderId:orderId,
							deliveryName:deliveryName,
							deliveryRemark:deliveryRemark,
							actualMoney:goodsCost
						}),
						success:function(data){
							console.log(data);
						},
						error:function(){
							console.log('请求失败');
						}
					});
					$('.shipmentsFrame').css('display','none');
					$('.bz-txt').val('');
				})
				//取消
				$('.sf-btn-N').click(function() {
					$('.shipmentsFrame').css('display','none');
				});

	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});




}

//加载待确认收货订单信息
function waitingConfirmDeliveryOrder(p) {
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageAwaitGoodsReceipt.do?currentPage="+p,
		beforeSend: function() {
	        $("tbody").html("");
	    },
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	var order = '';
		    	var orderIdArr = [];
		    	$.each(DATA.data, function(n,obj) {
		    		var createDate = $.myTime.UnixToDate(obj.createDate);
		    		var orderSource='';
					if(obj.payPlateform==0){
						orderSource = '自然流量';
					}else if(obj.payPlateform==1){
						orderSource = '捕手推广';
					}else if(obj.payPlateform==2){
						orderSource = 'B端推广';
					};
		    		order += '<tr><th colspan="9"><div class="orderMsg"><span>订单编号：<i>'+obj.orderId+'</i></span><span></span><span>成交时间：<i>'+createDate+'</i></span><span>买家：<i>'+obj.name+'</i></span></div></th></tr><tr><th><dl><dt><img src="'+imgUrl+obj.goods.goodsImg+'"/></dt><dd><h4>'+obj.goods.goodsName+'</h4><h5>'+obj.goods.goodsDes+'</h5><h6>规格：默认</h6></dd></dl></th><th>￥<i>'+obj.goods.marketMoney+'</i></th><th>'+obj.count+'</th><th></th><th></th><th>'+orderSource+'</th><th></th><th>待确认收货</th><th><h5 class="remark remark'+obj.orderId+'">备注</h5><h5 class="shipments"></h5></th></tr><tr><th colspan="9" class="addressMsg"><span>收货信息：<i>'+obj.name+'</i>，<i>'+obj.tel+'</i>，<i id="addressDetail">'+obj.city+obj.address+'</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="copy"></i></span></th></tr>';
		    		orderIdArr.push('.remark'+obj.orderId);
		    	});

				$('tbody').html(order);
				//备注
				$.each(orderIdArr, function(n,obj) {
					$(orderIdArr[n]).click(function() {
						$('.remarkFrame').css('display','block');
						localStorage.setItem('orderId',orderIdArr[n]);
					});
				});
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});
}

//加载交易完成订单信息
function dealOverOrder(p) {
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectFinishOrder.do?currentPage="+p,
		beforeSend: function() {
	        $("tbody").html("");
	    },
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	var order = '';
		    	var orderIdArr = [];
		    	$.each(DATA.data, function(n,obj) {
		    		var createDate = $.myTime.UnixToDate(obj.createDate);
		    		var orderSource='';
					if(obj.payPlateform==0){
						orderSource = '自然流量';
					}else if(obj.payPlateform==1){
						orderSource = '捕手推广';
					}else if(obj.payPlateform==2){
						orderSource = 'B端推广';
					};
					var orderState = '';
					if(obj.orderStatus==40){
						orderState = '已申请售后';
					}

		    		order += '<tr><th colspan="9"><div class="orderMsg"><span>订单编号：<i>'+obj.orderId+'</i></span><span></span><span>成交时间：<i>'+createDate+'</i></span><span>买家：<i>'+obj.name+'</i></span></div></th></tr><tr><th><dl><dt><img src="'+imgUrl+obj.goods.goodsImg+'"/></dt><dd><h4>'+obj.goods.goodsName+'</h4><h5>'+obj.goods.goodsDes+'</h5><h6>规格：默认</h6></dd></dl></th><th>￥<i>'+obj.goods.marketMoney+'</i></th><th>'+obj.count+'</th><th>'+orderState+'</th><th></th><th>'+orderSource+'</th><th></th><th>交易完成</th><th><h5 class="remark remark'+obj.orderId+'">备注</h5><h5 class="shipments"></h5></th></tr><tr><th colspan="9" class="addressMsg"><span>收货信息：<i>'+obj.name+'</i>，<i>'+obj.tel+'</i>，<i id="addressDetail">'+obj.city+obj.address+'</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="copy"></i></span></th></tr>';
		    		orderIdArr.push('.remark'+obj.orderId);
		    	});

				$('tbody').html(order);
				//备注
				$.each(orderIdArr, function(n,obj) {
					$(orderIdArr[n]).click(function() {
						$('.remarkFrame').css('display','block');
						localStorage.setItem('orderId',orderIdArr[n]);
					});
				});

	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});
}

//加载交易关闭订单信息
function dealCloseOrder(p) {
	$.ajax({
		type:"get",
		url:"https://www.freshfun365.com/FreshFun/selectBackstageOrderClose.do?currentPage="+p,
		beforeSend: function() {
	        $("tbody").html("");
	    },
	    success:function(DATA){
	    	console.log(JSON.parse(DATA));
		    var DATA = JSON.parse(DATA);
	    	if(DATA.status.code == '1001'){
		    	var order = '';
		    	var orderIdArr = [];
		    	$.each(DATA.data, function(n,obj) {
		    		var createDate = $.myTime.UnixToDate(obj.createDate);
		    		var orderSource='';
					if(obj.payPlateform==0){
						orderSource = '自然流量';
					}else if(obj.payPlateform==1){
						orderSource = '捕手推广';
					}else if(obj.payPlateform==2){
						orderSource = 'B端推广';
					};
		    		order += '<tr><th colspan="9"><div class="orderMsg"><span>订单编号：<i>'+obj.orderId+'</i></span><span></span><span>成交时间：<i>'+createDate+'</i></span><span>买家：<i>'+obj.name+'</i></span></div></th></tr><tr><th><dl><dt><img src="'+imgUrl+obj.goods.goodsImg+'"/></dt><dd><h4>'+obj.goods.goodsName+'</h4><h5>'+obj.goods.goodsDes+'</h5><h6>规格：默认</h6></dd></dl></th><th>￥<i>'+obj.goods.marketMoney+'</i></th><th>'+obj.count+'</th><th></th><th></th><th>'+orderSource+'</th><th></th><th>交易关闭</th><th><h5 class="remark remark'+obj.orderId+'">备注</h5><h5 class="shipments"></h5></th></tr><tr><th colspan="9" class="addressMsg"><span>收货信息：<i>'+obj.name+'</i>，<i>'+obj.tel+'</i>，<i id="addressDetail">'+obj.city+obj.address+'</i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="copy"></i></span></th></tr>';
		    		orderIdArr.push('.remark'+obj.orderId);
		    	});
		    	
				$('tbody').html(order);
				//备注
				$.each(orderIdArr, function(n,obj) {
					$(orderIdArr[n]).click(function() {
						$('.remarkFrame').css('display','block');
						localStorage.setItem('orderId',orderIdArr[n]);
					});
				});
	    	}else if(DATA.status.code == '1004'){
	    		alert('请求失败');
	    	};
	    },
	    error:function(){
	    	alert('请求失败');
	    }
	});
}














//时间转换
(function($) {
    $.extend({
        myTime: {
            /**
             * 当前时间戳
             * @return <int>        unix时间戳(秒)  
             */
            CurTime: function(){
                return Date.parse(new Date())/1000;
            },
            /**              
             * 日期 转换为 Unix时间戳
             * @param <string> 2014-01-01 20:20:20  日期格式              
             * @return <int>        unix时间戳(秒)              
             */
            DateToUnix: function(string) {
                var f = string.split(' ', 2);
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                        )).getTime() / 1000;
            },
            /**              
             * 时间戳转换日期              
             * @param <int> unixTime    待时间戳(秒)              
             * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)              
             * @param <int>  timeZone   时区              
             */
            UnixToDate: function(unixTime, isFull, timeZone) {
                if (typeof (8) == 'number')
                {
                    unixTime = parseInt(unixTime) + parseInt(8) * 60 * 60;
                }
                var time = new Date(unixTime * 1000);
                    ymdhis = "";
                    getYear = time.getUTCFullYear();
                    getMonth = time.getUTCMonth() + 1;
                    getDay = time.getUTCDate();
                    getHour =time.getUTCHours();
                    getMinute = time.getUTCMinutes();
                    getSecond = time.getUTCSeconds();

                if (parseInt(getYear) < 10) {getYear = '0' + getYear;};
                if (parseInt(getMonth) < 10) {getMonth = '0' + getMonth;};
                if (parseInt(getDay) < 10) {getDay = '0' + getDay;};
                if (parseInt(getHour) < 10) {getHour = '0' + getHour;};
                if (parseInt(getMinute) < 10) {getMinute = '0' + getMinute;};
                if (parseInt(getSecond) < 10) {getSecond = '0' + getSecond;};
                ymdhis += getYear + "-";
                ymdhis += getMonth + "-";
                ymdhis += getDay;
                ymdhis += " " + getHour + ":";
                ymdhis += getMinute + ":";
                ymdhis += getSecond;
                return ymdhis;
            }
        }
    });
})(jQuery);