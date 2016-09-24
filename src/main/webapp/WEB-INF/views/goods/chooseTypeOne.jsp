<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.quxin.freshfun.model.goods.GoodsTypePOJO"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	List<GoodsTypePOJO> gtList = (List<GoodsTypePOJO>)request.getAttribute("gtList");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择专题</title>
</head>
<body>
	<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<% if(gtList!=null&&gtList.size()>0){ 
			for(GoodsTypePOJO gt : gtList){%>
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td><input type="radio" value="<%=gt.getId() %>" name="goodsType"/></td>
				<td><%=gt.getGoodsType() %></td>
			</tr>
		<%} }%>
	</table>
</body>
</html>