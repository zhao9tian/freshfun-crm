<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.quxin.freshfun.model.specialmall.SpecialMallPOJO"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	List<SpecialMallPOJO> smList = (List<SpecialMallPOJO>)request.getAttribute("smList");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择专题</title>
</head>
<body>
	<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<% if(smList!=null&&smList.size()>0){ 
			for(SpecialMallPOJO sm : smList){%>
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td><input type="checkbox" value="<%=sm.getId() %>" name="specialMall"/></td>
				<td><%=sm.getMallDes() %></td>
			</tr>
		<%} }%>
	</table>
</body>
</html>