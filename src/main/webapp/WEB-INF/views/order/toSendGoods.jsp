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
<title>�˿���ҳ</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<body>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td>�������</td>
				<td><input type="text" id="orderDetailId"/></td>
				<td><input type="button" onclick="check()" value="��ⶩ��"/></td>
				<td>��ݵ���</td>
				<td><input type="text" id="orderWuliuNo"/></td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td rowspan="6">��ݹ�˾</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="hxlwl"/>������</td>
				<td><input type="radio" name="company" value="yuantong"/>Բͨ�ٵ�</td>
				<td><input type="radio" name="company" value="ds"/>D������</td>
				<td><input type="radio" name="company" value="guotong"/>��ͨ���</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="zjs"/>լ����</td>
				<td><input type="radio" value="minhang" name="company"/>�񺽿��</td>
				<td><input type="radio" value="minbang" name="company"/>�����</td>
				<td><input type="radio" name="company" value="yunda"/>�ϴ���</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="jingdong"/>�����ٵ�</td>
				<td><input type="radio" name="company" value="shentong"/>��ͨ���</td>
				<td><input type="radio" name="company" value="debang"/>�°�����</td>
				<td><input type="radio" name="company" value="tiantian"/>������</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" value="shunfeng" name="company"/>˳������</td>
				<td><input type="radio" value="shengfeng" name="company"/>ʢ������</td>
				<td><input type="radio" value="shenghui" name="company"/>ʢ������</td>
				<td><input type="radio" name="company" value="zhongyou"/>��������</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td><input type="radio" name="company" value="zhongtong"/>��ͨ���</td>
				<td><input type="radio" name="company" value="zhongtie"/>��������</td>
				<td><input type="radio" name="company" value="axd"/>���Ŵ���</td>
				<td><input type="radio" name="company" value="cszx"/>����֮������</td>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<td></td>
				<td><input type="radio" name="company" value="bySelf"/>�Զ���</td>
				<td colspan="2"><input type="text" id="wirteCode" value=""/></td>
				<td><span id="ts" style="color:red">ѡ���Զ����ݹ�˾ʱ����ϵ������Ա</span></td>
			</tr>
		</table>
		
</body>
<script type="text/javascript">
	function upDate(){
		var orderDetailId = document.getElementById("orderDetailId").value;
		if(typeof(orderDetailId)=="undefined"||orderDetailId==""){
			alert("�����붩�����");
			return;
		}
		var orderWuliuNo = document.getElementById("orderWuliuNo").value;
		if(typeof(orderWuliuNo)=="undefined"||orderWuliuNo==""){
			alert("�������ݵ���");
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
					alert("�������ݹ�˾����");
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
			alert("�����붩�����");
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