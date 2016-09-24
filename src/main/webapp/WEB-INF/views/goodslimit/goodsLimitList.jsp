<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/lib/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/goods/goodsLimit.js"></script>
<style>
.error{
	color:red;
}
</style>
</head><%@ include file="../head.jsp" %>
<body>
		<fieldset>
			<legend><font>条件查询</font></legend>
			<form id="queryForm" action="goodsLimitList.do" method="post">
				<label>商品名称</label>
				<input type="text" name="goodsName" value="${goodsName }"/>
				<label>所属商户</label>
				<input type="digits" name="storeId" value="${storeId }"/>
				<label>录入时间</label>
				<input class="Wdate" type="text" onClick="WdatePicker()" name="gmtCreate" value="${gmtCreate }"/>
				<input type="hidden" name="page" value="1" />
				<input type="button" id="query" value="query" />
			
			<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr  style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
					<th>商品名称</th>
					<th>商品原价</th>
					<th>商品售价</th>
					<th>所属商户(id)</th>
					<th>商品库存量</th>
					<th>商品描述</th> 
					<th>是否上架</th>
					<th>信息修改</th>
					<th>商品下架</th>
				</tr>
					<c:forEach items="${goodsLimitList }" var="g" >
						<tr bgcolor='#F4FAFF' style="text-align: center;">
							<td>${g.goodsName }</td>
							<td>${g.shopPrice }</td>
							<td>${g.marketPrice }</td>
							<td>${g.storeId }</td>
							<td>${g.goodsStorage }</td>
							<td>${g.goodsDes }</td>
							<td>
								<c:if test="${g.isOnSale ==1}">是</c:if>
								<c:if test="${g.isOnSale ==0}">否</c:if>
							</td>
							<td><a href="toEditGoodsLimit.do?id=${g.id}">编辑</a></td>
							<td><a href="deleteGoodsLimit.do?id=${g.id}">商品下架</a></td>
						</tr>
					</c:forEach>
			</table>
			<a onClick='gotoPage("1")' href="javascript:void(0)">首页</a>
			<a onClick='gotoPage(${page-1})' href="javascript:void(0)">上一页</a>
				第${page }页/共${totalPage }页
			<a onClick='gotoPage(${page+1})' href="javascript:void(0)">下一页</a>
			<a onclick='gotoPage(${totalPage})' href="javascript:void(0)" >尾页</a>
			页面大小:<input style="width:20px" type="text" name="pageSize" value="${pageSize }"/>
			共有${totalRecords }条数据
			<a onclick='gotoPage($("#toPage").val())' href="javascript:void(0)">跳转到</a>
			<input style="width:20px" id="toPage" value="${ page}">页
			</form>
			<a href="toAddGoodsLimit.do">新增商品</a>
		</fieldset>
</body>
</html>