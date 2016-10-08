<%@page import="com.quxin.freshfun.model.goods.GoodsTypePOJO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDrag.js"></script>
<style>
	tr{
	 COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold;
	}
	img{
		width:150px;
		height:150px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%GoodsTypePOJO gt = (GoodsTypePOJO)request.getAttribute("gt"); %>
<title>分类编辑页</title>
</head>
<jsp:include page="../head.jsp" />
<body>
	<fieldset>
		 	<legend><font style="color: red">操作</font></legend>
		 	<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
		 	</fieldset>
		 	<fieldset>
		 	<legend><font style="color: orange">分类信息编辑页</font></legend>
	<form action="saveGoodsType.do" enctype="multipart/form-data" method="post" ><input type="hidden" name="id" value="<%=gt!=null&&gt.getId()!=null?gt.getId():""%>">
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr>
				<td><label>分类名称</label></td>
				<td>
					<textarea cols="80" rows="6" name="goodsType"><%=gt!=null&&gt.getGoodsType()!=null?gt.getGoodsType():""%></textarea>
				</td>
				<td><label>分类详情描述</label></td>
				<td>
					<textarea cols="80" rows="6" name="goodsInfoDes"><%=gt!=null&&gt.getGoodsInfoDes()!=null?gt.getGoodsInfoDes():""%></textarea>
				</td>
			</tr>
			<tr>
				<%--<td><label>分类ICON图片</label></td>
				<td><%if(gt!=null&&gt.getGoodsTypeImg()!=null){%><img name="mallImg" alt="mallImg" src="${IMAGEIP}<%=gt.getGoodsTypeImg()%>"><%} %>
					<input type="file" name="mallImg1">
				</td>--%>
				<td><label>分类详情静态图片</label></td>
				<td><%if(gt!=null&&gt.getGoodsInfoImg()!=null){%><img name="mallImg" alt="mallImg" src="${IMAGEIP}<%=gt.getGoodsInfoImg()%>"><%} %>
					<input type="file" name="mallImg2">
				</td>
				<td></td><td></td>
			</tr>
		</table>
		<div style="text-align: center;margin-top:20px;"><input type="button" onclick="doSubmit()" value="提交">&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回"></div>
	</form>
	</fieldset>
</body>
<script>
	function doSubmit(){
		var id = document.getElementsByName("id")[0].value;
		var goodsType = document.getElementsByName("goodsType")[0].value;
		if(goodsType==""){
			alert("请输入分类名称");
			return ;
		}
		var goodsInfoDes = document.getElementsByName("goodsInfoDes")[0].value;
		if(goodsInfoDes==""){
			alert("请输入分类描述");
			return ;
		}
		var mallImg2 = document.getElementsByName("mallImg2")[0].value;
		if(id==""&&mallImg2==""){
			alert("请选择分类详情静态图片");
			return ;
		}
		document.forms[0].submit();
	}
</script>
</html>