<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8	">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDrag.js"></script>
<title>新人购首页</title>
<style>
	a{
		text-decoration: none;
	}
</style>
</head>
<%@ include file="../head.jsp" %>
<body>
<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;" >
		<tr  style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
			<th>id</th>
			<th>活动名称</th>
			<th>首页图片</th>
			<th>首页文字</th>
			<th>详情页图片</th>
			<th>详情页文字</th>
			<th>录入时间</th>
			<th>修改时间</th>
			<th>当前状态</th>
			<th>操作</th>
		</tr>
		
		<c:forEach items="${activityList}" var="g" >
				<tr  bgcolor='#F4FAFF' style="text-align: center;">
					<td>${g.id }</td>
					<td>${g.activityDes }</td>
					<td><img style="width:100px;height:100px;" src="${IMAGEIP}${g.activityImg }"/></td>
					<td>${g.activityContent}</td>
					<td><img style="width:100px;height:100px;" src="${IMAGEIP}${g.activityInfoImg }"/></td>
					<td>${g.activityInfoContent}</td>
					<td>${g.gmtCreateView }</td>
					<td>${g.gmtModifiedView }</td>
					<td>
						<c:if test="${g.isDeleted ==1}">禁用</c:if>
						<c:if test="${g.isDeleted ==0}">启用</c:if>
					</td>
					<td><a href="javascript:void(0)" onclick="delActivity(${g.id},${g.isDeleted})"><c:if test="${g.isDeleted ==1}">启用</c:if><c:if test="${g.isDeleted ==0}">禁用</c:if></a>
					&nbsp;<a href="javascript:void(0)" onclick="modifyActivity(${g.id})">修改</a>&nbsp;<a href="javascript:void(0)" onclick="showGoods(${g.id})">查看活动商品</a> </td>
					</tr>
		
		</c:forEach>
		</table>
		
		<a href="javascript:void(0)" onclick="addActivity()">新增活动</a>
		
		<form action="" method="post" id="myform"></form>
		
		<script>
		function modifyActivity(id){
			document.getElementById("myform").action="toUpdate.do?id="+id;
			document.getElementById("myform").submit();
		}
		function addActivity(){
			document.getElementById("myform").action="toUpdate.do";
			document.getElementById("myform").submit();
		}
		
		
		function showGoods(id){
			document.getElementById("myform").action="activityGoods.do?id="+id;
			document.getElementById("myform").submit();
		}
		
		
		
		function delActivity(aId,isdelete){
			var str= "提示：您确认要启用该活动吗？ ";
			if (isdelete==0) 
				str = "提示：您确认要禁用该活动吗？";
				Dialog.confirm(str,function(){
					$.ajax({
						url:'openOrStopActivity.do',
						data:{aId:aId,isdelete:isdelete},
						dataType:'text',
						success:function(data){
							if(data!='')
								Dialog.alert(data,function(){location.reload();});
						}
					});
				});
			}
		
		</script>
		

</body>
</html>