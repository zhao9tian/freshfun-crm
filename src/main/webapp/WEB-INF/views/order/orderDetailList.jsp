<%@page import="com.quxin.freshfun.model.orders.OrderDetailsPOJO"%>
<%@page import="com.quxin.freshfun.utils.DateUtils"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	//获取专题list
	List<OrderDetailsPOJO> detailList = (List<OrderDetailsPOJO>)request.getAttribute("detailList");
	String curPage = (String)request.getAttribute("curPage");
	String countPage = (String)request.getAttribute("countPage");
%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>主题首页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<%@ include file="../head.jsp" %>
<body>
	<fieldset>
		<legend><font style="color:red">操作</font></legend>
		<form action="" id="myForm">
		</form>
		<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
	</fieldset>
	<fieldset>
		<legend>处理退款订单</legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<th>订单编号</th>
				<th>用户名称</th>
				<th>商品图片</th>
				<th>商品名称</th>
				<th>商品标价</th>
				<th>商品售价</th>
				<th>商品数量</th>
				<th>订单价格</th>
				<th>订单创建时间</th>
				<th>相关操作</th>
			</tr>
			<%if(detailList!=null&&detailList.size()>0){
				for(OrderDetailsPOJO detail : detailList){
				%>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td><%=detail.getOrderDetailsId() %></td>
					<td><%=detail.getUser()!=null?detail.getUser().getUserName():"" %></td>
					<%if(detail.getIsLimit()==0){ %>
						<td><img style="width:100px;height:100px;" src="${IMAGEIP}<%=detail.getGoods().getGoodsImg() %>"/></td>
						<td><%=detail.getGoods().getGoodsName() %></td>
						<td><%=detail.getGoods().getMarketPrice()/100 %>元</td>
						<td><%=detail.getGoods().getShopPrice()/100 %>元</td>
					<%}else{ %>
						<td><img style="width:100px;height:100px;" src="${IMAGEIP}<%=detail.getGoodsLimit().getGoodsImg() %>"/></td>
						<td><%=detail.getGoodsLimit().getGoodsName() %></td>
						<td><%=detail.getGoodsLimit().getMarketPrice()/100 %>元</td>
						<td><%=detail.getGoodsLimit().getShopPrice()/100 %>元</td>
					<%} %>
					<td><%=detail.getCount() %></td>
					<td><%=detail.getActualPrice()/100 %>元</td>
					<td><%=DateUtils.longToString(detail.getCreateDate(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><a href="javascript:void(0)" onclick="toShowReason('<%=detail.getOrderDetailsId() %>')">去处理</a></td>
				</tr>
				<%	
				}
			} %>
		</table>
		<div style="text-align: center;">
	<% if(detailList!=null && detailList.size()>0){%>
		<%if(Integer.parseInt(curPage)!=1){%><a href="javascript:void(0)" onclick="goPage(1)">首页</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)-1>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)-1 %>)">上一页</a><%} %>&nbsp;
		第<%=curPage %>页&nbsp;/&nbsp;共<%=countPage %>页
		<%if(Integer.parseInt(countPage)-Integer.parseInt(curPage)>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)+1 %>)">下一页</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)!=Integer.parseInt(countPage)){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(countPage) %>)">尾页</a><%} %>
	<%} %>
	</div>
	</fieldset>
	
	<script>
	
	function goPage(res){
		document.getElementById("myForm").action="orderList.do?curPage="+res;
		document.getElementById("myForm").submit();
	}
	
		function toShowReason(orderDetailsId){
			var diag = new Dialog();
			diag.Width = 400;
			diag.Height = 200;
			diag.AutoClose=10;
			diag.ShowCloseButton=false;
			diag.Drag=true;
			diag.MessageTitle = "处理退款订单";
			diag.URL = "toShowReason.do?orderDetailsId="+orderDetailsId;
			diag.OKEvent = function(){
				Dialog.confirm('警告：请您在完成用户退款后做此操作？',function(){
					var orderDetailId =  diag.innerFrame.contentWindow.document.getElementsByName('orderDetailId')[0].value;
					
					$.ajax({
						url:'retOrder.do',
						type:"post",
						data:{orderDetailId : orderDetailId,isOk:2},
						dataType:'text',
						success:function(data){
							if(data!='')
								Dialog.alert(data,function(){location.reload();});
						}
					});
					diag.close();
				});
			};
			diag.CancelEvent=function(){
					Dialog.confirm('警告：您选择了拒绝用户的退款申请？',function(){
					var orderDetailId =  diag.innerFrame.contentWindow.document.getElementsByName('orderDetailId')[0].value;
					$.ajax({
						url:'retOrder.do',
						type:"post",
						data:{orderDetailId : orderDetailId,isOk:3},
						dataType:'text',
						success:function(data){
							if(data!='')
								Dialog.alert(data,function(){location.reload();});
						}
					});
					diag.close();
				});
			};
			diag.show();
			diag.okButton.value="同意退款";
			diag.cancelButton.value="拒绝退款";
		}
	</script>
</body>
</html>