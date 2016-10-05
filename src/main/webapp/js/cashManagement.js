$('.copy').click(function() {
	copyText($('#addressDetail').html())
})



//加载执行点击ALL
window.onload = function(){
	$.ajax({
		type:"post",
		url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
		dataType:'JSON',
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify({
			state:0,
			currentPage:1
		}),
		beforeSend: function() {
	        $("tbody").html("");
	    },
		success:function(DATA){
			console.log(DATA);
			var totalPage = DATA.totalPage;
			$(".tcdPageCode").createPage({
			    pageCount:totalPage,
			    current:1,
			    backFn:function(p){
			    	console.log(p);
			    	addOrder(p);
			    }
			});
			addOrder(1);
		},
		error:function(){
			console.log('请求失败');
		}
	});
}

//选项卡
		$('.tab').pignoseTab({
			animation: true,
			children: '.tab'
		});


		//点击all
		$('#all').click(function(){
			console.log('全部');
			$.ajax({
				type:"post",
				url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
				dataType:'JSON',
				contentType: 'application/json;charset=utf-8',
				data:JSON.stringify({
					state:0,
					currentPage:1
				}),
				beforeSend: function() {
			        $(".all-tbody").html("");
			    },
				success:function(DATA){
					console.log(DATA);
					var totalPage = DATA.totalPage;
					$(".tcdPageCode").createPage({
					    pageCount:totalPage,
					    current:1,
					    backFn:function(p){
					    	console.log(p);
					    	addOrder(p);
					    }
					});
					addOrder(1);
				},
				error:function(){
					console.log('请求失败');
				}
			});
		})

		//点击待处理
		$('#pending').click(function(){
			console.log('待处理');
			$.ajax({
				type:"post",
				url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
				dataType:'JSON',
				contentType: 'application/json;charset=utf-8',
				data:JSON.stringify({
					state:1,
					currentPage:1
				}),
				beforeSend: function() {
			        $("tbody").html("");
			    },
				success:function(DATA){
					console.log(DATA);
					var totalPage = DATA.totalPage;
					$(".tcdPageCode").createPage({
					    pageCount:totalPage,
					    current:1,
					    backFn:function(p){
					    	console.log(p);
					    	pending(p);
					    }
					});
					pending(1);
				},
				error:function(){
					console.log('请求失败');
				}
			});

		})
		//点击已处理
		$('#processed').click(function(){
			console.log('已处理');
			$.ajax({
				type:"post",
				url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
				dataType:'JSON',
				contentType: 'application/json;charset=utf-8',
				data:JSON.stringify({
					state:2,
					currentPage:1
				}),
				beforeSend: function() {
			        $("tbody").html("");
			    },
				success:function(DATA){
					console.log(DATA);
					var totalPage = DATA.totalPage;
					$(".tcdPageCode").createPage({
					    pageCount:totalPage,
					    current:1,
					    backFn:function(p){
					    	console.log(p);
					    	processed(p);
					    }
					});
					processed(1);
				},
				error:function(){
					console.log('请求失败');
				}
			});
		})
		//点击已驳回
		$('#rejected').click(function(){
			console.log('已驳回')
			$.ajax({
				type:"post",
				url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
				dataType:'JSON',
				contentType: 'application/json;charset=utf-8',
				data:JSON.stringify({
					state:3,
					currentPage:1
				}),
				beforeSend: function() {
			        $("tbody").html("");
			    },
				success:function(DATA){
					console.log(DATA);
					var totalPage = DATA.totalPage;
					$(".tcdPageCode").createPage({
					    pageCount:totalPage,
					    current:1,
					    backFn:function(p){
					    	console.log(p);
					    	rejected(p);
					    }
					});
					rejected(1);
				},
				error:function(){
					console.log('请求失败');
				}
			});
		})







//加载全部订单信息
function addOrder(p){
	$.ajax({
		type:"post",
		url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
		dataType:'JSON',
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify({
			state:0,
			currentPage:p
		}),
		beforeSend: function() {
	        $(".all-tbody").html("");
	    },
		success:function(DATA){
			console.log(DATA);
			var order = '';
			var stateOneArr = [];
			var stateTwoArr = [];
			$.each(DATA.data, function(n,obj) {
				if(obj.state == '待处理'){
					var stateOne = '变更为已处理';
					var stateTwo = '驳回提现';
				}else if(obj.state == '已处理'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}else if(obj.state == '已驳回'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}
				order += '<tr><th>'+obj.userId+'</th><th>'+obj.createDate+'</th><th>'+obj.updateDate+'</th><th>'+obj.withDrawPrice+'</th><th>'+obj.withDrawType+'</th><th>'+obj.paymentAccount+'</th><th>'+obj.state+'</th><th><h5 class="stateOne'+obj.id+'">'+stateOne+'</h5><h5 class="stateTwo'+obj.id+'">'+stateTwo+'</h5></th></tr>';
				stateOneArr.push('.stateOne'+obj.id);
				stateTwoArr.push('.stateTwo'+obj.id);
			});
			$(".all-tbody").html(order);
			$.each(stateOneArr, function(n,obj) {
				if($(stateOneArr[n]).html() == '变更为已处理'){
					$(stateOneArr[n]).click(function(){
						var id = stateOneArr[n].split('n')[1].split('e')[1];
						console.log(id);
						$.ajax({
							type:"get",
							url:"http://118.178.87.165:8080/freshfun-crm/withdraw/toHandled.do?operation=10&id="+id,
							dataType:'JSON',
							success:function(data){
								console.log(data);
//								window.location.reload();
								$('#all').click();
							},
							error:function(){
								console.log('请求失败')
							}
						});
					})
				}
			});
			$.each(stateTwoArr, function(n,obj) {
				if($(stateTwoArr[n]).html() == '驳回提现'){
					$(stateTwoArr[n]).click(function(){
						$('.remarkFrame').css('display','block');
						$('.btn-Y').click(function(){
							var rejectReason =$('textarea').val();
							var id = stateTwoArr[n].split('o')[1];
							console.log(id);
							$.ajax({
								type:"get",
								url:"http://118.178.87.165:8080/freshfun-crm/withdraw/toReject.do?operation=20&id="+id+"&rejectReason="+rejectReason,
								dataType:'JSON',
								success:function(data){
									console.log(data);
//									window.location.reload();
									$('#all').click();
									$('.remarkFrame').css('display','none');

								},
								error:function(){
									console.log('请求失败')
								}
							});
						})
						$('.btn-N').click(function(){
							$('.remarkFrame').css('display','none');
						})

					})
				}
			});
		},
		error:function(){
			console.log('请求失败');
		}
	});
}
//加载待处理订单
function pending(p){
	$.ajax({
		type:"post",
		url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
		dataType:'JSON',
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify({
			state:1,
			currentPage:p
		}),
		beforeSend: function() {
	        $(".pending-tbody").html("");
	    },
		success:function(DATA){
			console.log(DATA);
			var order = '';
			var stateOneArr = [];
			var stateTwoArr = [];
			$.each(DATA.data, function(n,obj) {
				if(obj.state == '待处理'){
					var stateOne = '变更为已处理';
					var stateTwo = '驳回提现';
				}else if(obj.state == '已处理'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}else if(obj.state == '已驳回'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}
				order += '<tr><th>'+obj.userId+'</th><th>'+obj.createDate+'</th><th>'+obj.updateDate+'</th><th>'+obj.withDrawPrice+'</th><th>'+obj.withDrawType+'</th><th>'+obj.paymentAccount+'</th><th>'+obj.state+'</th><th><h5 class="stateOne'+obj.id+'">'+stateOne+'</h5><h5 class="stateTwo'+obj.id+'">'+stateTwo+'</h5></th></tr>';
				stateOneArr.push('.stateOne'+obj.id);
				stateTwoArr.push('.stateTwo'+obj.id);
			});
			$(".pending-tbody").html(order);
			$.each(stateOneArr, function(n,obj) {
				if($(stateOneArr[n]).html() == '变更为已处理'){
					$(stateOneArr[n]).click(function(){
						var id = stateOneArr[n].split('n')[1].split('e')[1];
						console.log(id);
						$.ajax({
							type:"get",
							url:"http://118.178.87.165:8080/freshfun-crm/withdraw/toHandled.do?operation=10&id="+id,
							dataType:'JSON',
							success:function(data){
								console.log(data);
//								window.location.reload();
								$('#pending').click();
							},
							error:function(){
								console.log('请求失败')
							}
						});
					})
				}
			});
			$.each(stateTwoArr, function(n,obj) {
				if($(stateTwoArr[n]).html() == '驳回提现'){
					$(stateTwoArr[n]).click(function(){
						$('.remarkFrame').css('display','block');
						$('.btn-Y').click(function(){
							var rejectReason =$('textarea').val();
							var id = stateTwoArr[n].split('o')[1];
							console.log(id);
							$.ajax({
								type:"get",
								url:"http://118.178.87.165:8080/freshfun-crm/withdraw/toReject.do?operation=20&id="+id+"&rejectReason="+rejectReason,
								dataType:'JSON',
								success:function(data){
									console.log(data);
									$('#pending').click();
									$('.remarkFrame').css('display','none');

								},
								error:function(){
									console.log('请求失败')
								}
							});
						})
						$('.btn-N').click(function(){
							$('.remarkFrame').css('display','none');
						})

					})
				}
			});
		},
		error:function(){
			console.log('请求失败');
		}
	});
}
//加载已处理订单
function processed(p){
	$.ajax({
		type:"post",
		url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
		dataType:'JSON',
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify({
			state:2,
			currentPage:p
		}),
		beforeSend: function() {
	        $(".processed-tbody").html("");
	    },
		success:function(DATA){
			console.log(DATA);
			var order = '';
			$.each(DATA.data, function(n,obj) {
				if(obj.state == '待处理'){
					var stateOne = '变更为已处理';
					var stateTwo = '驳回提现';
				}else if(obj.state == '已处理'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}else if(obj.state == '已驳回'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}
				order += '<tr><th>'+obj.userId+'</th><th>'+obj.createDate+'</th><th>'+obj.updateDate+'</th><th>'+obj.withDrawPrice+'</th><th>'+obj.withDrawType+'</th><th>'+obj.paymentAccount+'</th><th>'+obj.state+'</th><th><h5>'+stateOne+'</h5><h5>'+stateTwo+'</h5></th></tr>';
			});
			$(".processed-tbody").html(order);

		},
		error:function(){
			console.log('请求失败');
		}
	});
}
//加载已驳回订单
function rejected(p){
	$.ajax({
		type:"post",
		url:"http://118.178.87.165:8080/freshfun-crm/withdraw/queryWithdrawList.do",
		dataType:'JSON',
		contentType: 'application/json;charset=utf-8',
		data:JSON.stringify({
			state:3,
			currentPage:p
		}),
		beforeSend: function() {
	        $(".rejected-tbody").html("");
	    },
		success:function(DATA){
			console.log(DATA);
			var order = '';
			$.each(DATA.data, function(n,obj) {
				if(obj.state == '待处理'){
					var stateOne = '变更为已处理';
					var stateTwo = '驳回提现';
				}else if(obj.state == '已处理'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}else if(obj.state == '已驳回'){
					var stateOne = ' - - ';
					var stateTwo = '';
				}
				order += '<tr><th>'+obj.userId+'</th><th>'+obj.createDate+'</th><th>'+obj.updateDate+'</th><th>'+obj.withDrawPrice+'</th><th>'+obj.withDrawType+'</th><th>'+obj.paymentAccount+'</th><th>'+obj.state+'</th><th><h5>'+stateOne+'</h5><h5>'+stateTwo+'</h5></th></tr>';
			});
			$(".rejected-tbody").html(order);
			
		},
		error:function(){
			console.log('请求失败');
		}
	});
}
