<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/lib/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/goods/goods.js"></script>
<style>
.error{
	color:red;
}
</style>
</head>
<%@ include file="../head.jsp" %>
<body>
		<fieldset>
		<legend><font style="color:red">Goods操作</font></legend>
		<form action="toAddGoods.do" id="addGoods"></form>
		<input value="新增商品" type="button" onclick="addGoods()"/>
				<input value="添加选中商品到分类" type="button" onclick="addGoodsTo(1)"/>
				<input value="添加选中商品到专题" type="button" onclick="addGoodsTo(3)"/>
				<input value="添加选中商品到专场" type="button" onclick="addGoodsTo(2)"/>
				<input value="推送选中商品到B端" type="button" onclick="pushToB()"/>
		</fieldset>
		<fieldset>
			<legend><font>条件查询</font></legend>
			<form id="queryForm" action="goodsList.do" method="post">
				<label>商品名称</label>
				<input type="text" name="goodsName" value="${goodsName }"/>
				<label>所属商户</label>
				<input type="digits" name="storeId" value="${storeId }"/>
				<label>录入时间</label>
				<input class="Wdate" type="text" onClick="WdatePicker()" name="gmtCreate" value="${gmtCreate }"/>
				<input type="hidden" name="page" value="1" />
				<input type="button" id="query" value="query" />
			<input type="hidden" name="returnValue"/>
			<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr  style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
					<th>选择</th>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>商品标价</th>
					<th>所属商户（id）</th>
					<th>录入时间</th>
					<th>修改时间</th>
					<th>库存是否警报</th>
					<th>是否上架</th>
					<th>是否新品</th>
					<th>是否热销</th>
					<th>是否折扣</th>
					<th>信息修改</th>
					<th>商品下架</th>
					<th>相关操作</th>
				</tr>
					<c:forEach items="${goodsList }" var="g" >
						<tr bgcolor='#F4FAFF' style="text-align: center;">
							<td><input type="checkbox" name="goodsId" value="${g.id }"/></td>
							<td>${g.goodsName }</td>
							<td>${g.shopPriceString }</td>
							<td>${g.marketPriceString }</td>
							<td>${g.storeId }</td>
							<td>${g.gmtCreateView }</td>
							<td>${g.gmtModifiedView }</td>
							<td>
								<c:if test="${g.warnning ==1}">是</c:if>
								<c:if test="${g.warnning ==0}">否</c:if>
							</td>
							<td>
								<c:if test="${g.isOnSale ==1}">是</c:if>
								<c:if test="${g.isOnSale ==0}">否</c:if>
							</td>
							<td>
								<c:if test="${g.isNew ==1}">是</c:if>
								<c:if test="${g.isNew ==0}">否</c:if>
							</td>
							<td>
								<c:if test="${g.isHot ==1}">是</c:if>
								<c:if test="${g.isHot ==0}">否</c:if>
							</td>
							<td>
								<c:if test="${g.isPromote ==1}">是</c:if>
								<c:if test="${g.isPromote ==0}">否</c:if>
							</td>
							<td><a href="toUpdateGoods.do?id=${g.id}">编辑</a></td>
							<td><a href="javascript:void(0)" onclick="onsale(${g.id},${g.isOnSale})"><c:if test="${g.isOnSale ==1}">下架商品</c:if><c:if test="${g.isOnSale ==0}">上架商品</c:if></a></td>
							<td><a href="javascript:void(0)" onclick="addToMall(${g.id})">加入Banner</a>&nbsp;<a href="javascript:void(0)" onclick="addToType(${g.id})">加入分类</a>
							&nbsp;<a href="javascript:void(0)" onclick="addToTheme(${g.id})">加入专题</a>&nbsp;<a href="javascript:void(0)" onclick="addToActivity(${g.id})">加入活动</a></td>
						</tr>
					</c:forEach>
			</table>
			<br>
			<div style="text-align: center">
			<a onClick='gotoPage("1")' href="javascript:void(0)">首页</a>
			<a onClick='gotoPage(${page-1})' href="javascript:void(0)">上一页</a>
				第${page }页/共${totalPage }页
			<a onClick='gotoPage(${page+1})' href="javascript:void(0)">下一页</a>
			<a onclick='gotoPage(${totalPage})' href="javascript:void(0)" >尾页</a>
			页面大小:<input style="width:20px" type="text" name="pageSize" value="${pageSize }"/>
			共有${totalRecords }条数据
			<a onclick='gotoPage($("#toPage").val())' href="javascript:void(0)">跳转到</a>
			<input style="width:20px" id="toPage" value="${ page}">页</div>
			</form>
		</fieldset>
</body>
<script>
	function pushToB(){
		var goodsInput = document.getElementsByName("goodsId");
		var goods;
		for(var i = 0 ; i < goodsInput.length ; i++){
			if(goodsInput[i].checked){
				if(typeof(goods)=="undefined"){
					goods = goodsInput[i].value;
				}else{
					goods = goods + "," + goodsInput[i].value;
				}
			}
		}
		if(typeof(goods)=="undefined"){
			Dialog.alert("请选择商品");
			return;
		}
		$.ajax({
			url:'pushGoodsToB.do?ids='+goods,
			dataType:'json',
			success:function(data){
				Dialog.alert(data.status.msg,function(){location.reload();});
			}
		});
	}
	function onsale(goodsId,isOnSale){
		//updateIsOnSale.do?id='+goodsId+'&isOnSale='+inOnSale
		if(isOnSale==1){
			$.ajax({
				url:'findProxyByGoodsId.do?goodsId='+goodsId,
				dataType:'json',
				success:function(data){
					Dialog.confirm(data.status.msg,function(){
						$.ajax({
							url:'updateIsOnSale.do?id='+goodsId+'&isOnSale='+isOnSale,
							dataType:'json',
							success:function(data){
								Dialog.alert(data.status.msg,function(){location.reload();});
							}
						});
					});
				}
			});
		}else{
			Dialog.alert("确认要上架吗？",function(){
				$.ajax({
					url:'updateIsOnSale.do?id='+goodsId+'&isOnSale='+isOnSale,
					dataType:'json',
					success:function(data){
						Dialog.alert(data.status.msg,function(){location.reload();});
					}
				});
			});

		}
	}

	function addGoods(){document.getElementById('addGoods').submit();}
	
	function addGoodsTo(type){
		var goodsInput = document.getElementsByName("goodsId");
		var goods;
		for(var i = 0 ; i < goodsInput.length ; i++){
			if(goodsInput[i].checked){
				if(typeof(goods)=="undefined"){
					goods = goodsInput[i].value;
				}else{
					goods = goods + "," + goodsInput[i].value;
				}
			}
		}
		if(typeof(goods)=="undefined"){
			Dialog.alert("请选择商品");
			return;
		}
		if(type==1){
			var diag = new Dialog();
			diag.Title = "请选择想要加入分类（单选）";//弹窗标题
			diag.URL = "goodstype/chooseType.do?way=1";
			diag.OKEvent = function(){
				var returns =  diag.innerFrame.contentWindow.document.getElementsByName('goodsType');
				var theReturns;
				for(var i=0;i< returns.length;i++){
					if(returns[i].checked){
						if(typeof(theReturns)=="undefined")
							theReturns = returns[i].value;
					}
				}
				if(typeof(theReturns)=="undefined"){
					alert("请选择想要加入的分类项");
					return;
				}
				$.ajax({
					url:'goodstype/addTypeGoodsOne.do',
					type:"post",
					data:{goodsType : theReturns , goodsId : goods},
					dataType:'text',
					success:function(data){
						if(data!='')
							Dialog.alert(data,function(){location.reload();});
					}
				});
				diag.close();
			};
			diag.show();
		}else if(type==2){
			var diag = new Dialog();
			diag.Title = "请选择想要加入专场（单选）";//弹窗标题
			diag.URL = "specialMall/chooseSpecialMall.do?way=1";
			diag.OKEvent = function(){
				var returns =  diag.innerFrame.contentWindow.document.getElementsByName('specialMall');
				var theReturns;
				for(var i=0;i< returns.length;i++){
					if(returns[i].checked){
						if(typeof(theReturns)=="undefined"){
							theReturns = returns[i].value;
						}
					}
				}
				if(typeof(theReturns)=="undefined"){
					alert("请选择想要加入的专场项");
					return;
				}
				$.ajax({
					url:'specialMall/addSpecialMallGoodsOne.do',
					data:{goodsId:goods,specialMall:theReturns},
					dataType:'text',
					success:function(data){
						if(data!='')
							Dialog.alert(data,function(){location.reload();});
					}
				});
				diag.close();
			};
			diag.show();
		}else if(type==3){
			var diag = new Dialog();
			diag.Title = "请选择想要加入专题（单选）";//弹窗标题
			diag.URL = "chooseSpecial.do?way=1";
			diag.OKEvent = function(){
				var returns =  diag.innerFrame.contentWindow.document.getElementsByName('special');
				var theReturns;
				for(var i=0;i< returns.length;i++){
					if(returns[i].checked){
						if(typeof(theReturns)=="undefined")
							theReturns = returns[i].value;
					}
				}
				if(typeof(theReturns)=="undefined"){
					alert("请选择想要加入的专题项");
					return;
				}
				$.ajax({
					url:'addSpecialGoodsOne.do',
					data:{goodsId:goods,specialId:theReturns},
					dataType:'text',
					success:function(data){
						if(data!='')
							Dialog.alert(data,function(){location.reload();});
					}
				});
				diag.close();
			};
			diag.show();
		}
	}

	function addToMall(goodsId){
		var diag = new Dialog();
		diag.Title = "请选择想要加入专场（多选）";//弹窗标题
		diag.URL = "specialMall/chooseSpecialMall.do";
		diag.OKEvent = function(){
			var returns =  diag.innerFrame.contentWindow.document.getElementsByName('specialMall');
			var theReturns;
			for(var i=0;i< returns.length;i++){
				if(returns[i].checked){
					if(typeof(theReturns)=="undefined"){
						theReturns = returns[i].value;
					}
					else{
						theReturns = theReturns + "," + returns[i].value;
					}
				}
			}
			if(typeof(theReturns)=="undefined"){
				alert("请选择想要加入的专场项");
				return;
			}
			$.ajax({
				url:'specialMall/addSpecialMallGoods.do',
				data:{goodsId:goodsId,theReturns:theReturns},
				dataType:'text',
				success:function(data){
					if(data!='')
						Dialog.alert(data,function(){location.reload();});
				}
			});
			diag.close();
		};
		diag.show();
	}
	
	function addToType(goodsId){
		var diag = new Dialog();
		diag.Title = "请选择想要加入分类（多选）";//弹窗标题
		diag.URL = "goodstype/chooseType.do";
		diag.OKEvent = function(){
			var returns =  diag.innerFrame.contentWindow.document.getElementsByName('goodsType');
			var theReturns;
			for(var i=0;i< returns.length;i++){
				if(returns[i].checked){
					if(typeof(theReturns)=="undefined")
						theReturns = returns[i].value;
					else
						theReturns =theReturns+ "," + returns[i].value;
				}
			}
			if(typeof(theReturns)=="undefined"){
				alert("请选择想要加入的分类项");
				return;
			}
			$.ajax({
				url:'goodstype/addTypeGoods.do',
				type:"post",
				data:{theReturns : theReturns , goodsId : goodsId},
				dataType:'text',
				success:function(data){
					if(data!='')
						Dialog.alert(data,function(){location.reload();});
				}
			});
			diag.close();
		};
		diag.show();
	}
	function addToTheme(goodsId){
		var diag = new Dialog();
		diag.Title = "请选择想要加入专题（多选）";//弹窗标题
		diag.URL = "chooseSpecial.do";
		diag.OKEvent = function(){
			var returns =  diag.innerFrame.contentWindow.document.getElementsByName('special');
			var theReturns;
			for(var i=0;i< returns.length;i++){
				if(returns[i].checked){
					if(typeof(theReturns)=="undefined")
						theReturns = returns[i].value;
					else
						theReturns = theReturns + "," + returns[i].value;
				}
			}
			if(typeof(theReturns)=="undefined"){
				alert("请选择想要加入的专题项");
				return;
			}
			$.ajax({
				url:'addSpecialGoods.do',
				data:{goodsId:goodsId,theReturns:theReturns},
				dataType:'text',
				success:function(data){
					if(data!='')
						Dialog.alert(data,function(){location.reload();});
				}
			});
			diag.close();
		};
		diag.show();
	}
	function addToActivity(goodsId){
		var diag = new Dialog();
		diag.Title = "请选择想要加入活动（单选）";//弹窗标题
		diag.URL = "activity/chooseActivity.do";
		diag.OKEvent = function(){
			var returns =  diag.innerFrame.contentWindow.document.getElementsByName('activitys');
			var discount =  diag.innerFrame.contentWindow.document.getElementsByName('discount');
			var discountPrice =  diag.innerFrame.contentWindow.document.getElementsByName('discountPrice');
			var theReturns;
			for(var i=0;i< returns.length;i++){
				if(returns[i].checked){
					if(typeof(theReturns)=="undefined")
						theReturns = returns[i].value;
				}
			}
			if(typeof(theReturns)=="undefined"){
				alert("请选择想要加入的活动项");
				return;
			}
			$.ajax({
				url:'goodstype/addActivityGoods.do',
				data:{goodsId:goodsId,theReturns:theReturns,discount:discount,discountPrice:discountPrice},
				dataType:'text',
				success:function(data){
					if(data!='')
						Dialog.alert(data,function(){location.reload();});
				}
			});
			diag.close();
		};
		diag.show();
	}
</script>
</html>