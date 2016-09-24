<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.quxin.freshfun.model.activity.ActivityPOJO"%>
<%@page import="com.quxin.freshfun.model.goods.GoodsPOJO"%>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	List<ActivityPOJO> activityList = (List<ActivityPOJO>)request.getAttribute("activityList");
	GoodsPOJO goods = (GoodsPOJO)request.getAttribute("goods");
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择专题</title>
</head>
<body>
	<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
		<% if(activityList!=null&&activityList.size()>0){ 
			for(ActivityPOJO activity : activityList){%>
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td><input type="radio" value="<%=activity.getId() %>" name="activitys"/></td>
				<td><%=activity.getActivityDes() %></td>
			</tr>
		<%} }%>
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td>活动折扣（1-10）</td>
				<td><input type="text" onchange="changeDiscount(<%=goods!=null?goods.getMarketPrice():"" %>)" name="discount"/>折</td>
			</tr>
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td>活动价格</td>
				<td><input type="text" readonly="readonly" name="disPrice"/>元<input type="hidden" readonly="readonly" name="discountPrice"/></td>
			</tr>
	</table>
	<script type="text/javascript">
		function changeDiscount(price){
			document.getElementsByName("disPrice")[0].value=price*document.getElementsByName("discount")[0].value/1000;
			document.getElementsByName("discountPrice")[0].value=price*document.getElementsByName("discount")[0].value/10;
		}
	</script>
</body>
</html>