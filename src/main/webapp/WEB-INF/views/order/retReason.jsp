<%@page import="com.quxin.freshfun.model.refund.RefundPOJO"%>
<%@page import="com.quxin.freshfun.utils.DateUtils"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	//��ȡר��list
	RefundPOJO refund = (RefundPOJO)request.getAttribute("refund");
	String orderDetailid = (String)request.getAttribute("orderDetailid");
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�˿���ҳ</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<body>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<%if(refund!=null){ %>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<th>��������</th>
				<th><%=refund!=null?refund.getServiceType():"" %></th>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<th>�˿�ԭ��</th>
				<th><%=refund!=null?refund.getReturnReason():"" %></th>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<th>�˿���</th>
				<th>��<span style="color:red"><%=refund!=null?refund.getReturnMoney()/100:"" %></span>&nbsp;Ԫ</th>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<th>�˿�˵��</th>
				<th><%=refund!=null?refund.getReturnDes():"" %></th>
			</tr>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<th>�˿���</th>
				<th><%=refund!=null?DateUtils.longToString(refund.getGmtCreate(), "yyyy-MM-dd HH-mm-ss"):"" %></th>
			</tr>
			<%}else{ %>
			<tr bgcolor='#F4FAFF' style="text-align: center;">
				<th colspan="2">�������Ϊ:<span style="color:red"><%=refund.getOrderDetailsId() %></span><br><br>�˿�ԭ����Ϣû��¼��������ݶ�ʧ<br><br>����Ȼ���Դ���ö������˿�����</th>
			</tr>
			<%} %>
		</table>
			<input type="hidden" name="orderDetailId" value="<%=refund.getOrderDetailsId() %>"/>
		
</body>
</html>