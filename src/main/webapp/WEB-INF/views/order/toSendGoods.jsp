<%@page import="com.quxin.freshfun.model.refund.RefundPOJO"%>
<%@page import="com.quxin.freshfun.utils.DateUtils"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>退款首页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<body>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td>订单编号</td>
				<td><input type="text" id="orderDetailId"/></td>
				<td><input type="button" onclick="check()" value="检测订单"/></td>
				<td>快递单号</td>
				<td><input type="text" id="orderWuliuNo"/></td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td rowspan="6">快递公司</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="hxlwl"/>华夏龙</td>
				<td><input type="radio" name="company" value="yuantong"/>圆通速递</td>
				<td><input type="radio" name="company" value="ds"/>D速物流</td>
				<td><input type="radio" name="company" value="guotong"/>国通快递</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="zjs"/>宅急送</td>
				<td><input type="radio" value="minhang" name="company"/>民航快递</td>
				<td><input type="radio" value="minbang" name="company"/>民邦快递</td>
				<td><input type="radio" name="company" value="yunda"/>韵达快递</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="jingdong"/>京东速递</td>
				<td><input type="radio" name="company" value="shentong"/>申通快递</td>
				<td><input type="radio" name="company" value="debang"/>德邦物流</td>
				<td><input type="radio" name="company" value="tiantian"/>天天快递</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" value="shunfeng" name="company"/>顺丰速运</td>
				<td><input type="radio" value="shengfeng" name="company"/>盛丰物流</td>
				<td><input type="radio" value="shenghui" name="company"/>盛辉物流</td>
				<td><input type="radio" name="company" value="zhongyou"/>中邮物流</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="zhongtong"/>中通快递</td>
				<td><input type="radio" name="company" value="zhongtie"/>中铁快运</td>
				<td><input type="radio" name="company" value="axd"/>安信达快递</td>
				<td><input type="radio" name="company" value="cszx"/>城市之星物流</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td></td>
				<td><input type="radio" name="company" value="bySelf"/>自定义</td>
				<td colspan="2"><input type="text" id="wirteCode" value=""/></td>
				<td><span id="ts" style="color:red">选择自定义快递公司时请联系开发人员</span></td>
			</tr>
		</table>
		
</body>
<script type="text/javascript">
	function upDate(){
		var orderDetailId = document.getElementById("orderDetailId").value;
		if(typeof(orderDetailId)=="undefined"||orderDetailId==""){
			alert("请输入订单编号");
			return;
		}
		var orderWuliuNo = document.getElementById("orderWuliuNo").value;
		if(typeof(orderWuliuNo)=="undefined"||orderWuliuNo==""){
			alert("请输入快递单号");
			return;
		}
		var company = document.getElementsByName("company");
		var companyCode = "";
		for(var i = 0 ; i<company.length;i++){
			if(company[i].checked){
				companyCode=company[i].value;
			}
			if("bySelf"==companyCode){
				var wirteCode = document.getElementById("wirteCode").value;
				if(typeof(wirteCode)=="undefined"||wirteCode==""){
					alert("请输入快递公司编码");
					return;
				}
				companyCode = wirteCode;
			}
		}
		$.ajax({
			url:'checkOrderDetailId.do',
			type:"post",
			data:{orderDetailId : orderDetailId,orderWuliuNo:orderWuliuNo,companyCode:companyCode},
			dataType:'text',
			success:function(data){
				if(data!='')
					Dialog.alert(data);
			}
		});
	}
	
	function check(){
		var orderDetailId = document.getElementById("orderDetailId").value;
		if(typeof(orderDetailId)=="undefined"||orderDetailId==""){
			alert("请输入订单编号");
			return;
		}
		$.ajax({
			url:'checkOrderDetailId.do',
			type:"post",
			data:{orderDetailId : orderDetailId},
			dataType:'text',
			success:function(data){
				if(data!='')
					Dialog.alert(data);
			}
		});
	}
</script>
</html>