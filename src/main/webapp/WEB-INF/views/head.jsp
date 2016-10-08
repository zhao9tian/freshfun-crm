<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8	">
<title>FreshFun'IndexPage</title>
<style>
a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div style="text-align: center">
		<table border="0" cellpadding="3" cellspacing="1" width="100%"
			align="center" style="background-color: #b9d8f3;">
			<tr
				style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td colspan="7"><h2>欢迎使用悦选后台管理系统</h2></td>
			</tr>
			<tr
				style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td><a href="${pageContext.request.contextPath}/goodsList.do">商品管理</a></td>
				<td><a
					href="${pageContext.request.contextPath}/goodstype/getGoodsTypeList.do">分类管理</a></td>
				<td><a
					href="${pageContext.request.contextPath}/specialThemeIndex.do">专题管理</a></td>
				<td><a
					href="${pageContext.request.contextPath}/specialMall/getSMList.do">专场管理</a></td>
				<td><a
						href="${pageContext.request.contextPath}/backOrder.html">订单管理</a></td>
				<td><a
						href="${pageContext.request.contextPath}/cashManagement.html">提现管理</a></td>
			</tr>
		</table>
	</div>
</body>
</html>
