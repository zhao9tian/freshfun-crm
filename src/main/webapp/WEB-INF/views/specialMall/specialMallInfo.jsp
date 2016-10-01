<%@page import="com.quxin.freshfun.model.specialmall.SpecialMallPOJO"%>
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
<%SpecialMallPOJO sm = (SpecialMallPOJO)request.getAttribute("sm"); %>
<title>Banner编辑页</title>
</head>
<%@ include file="../head.jsp" %>
<body>
<fieldset>
		 	<legend><font style="color: red">操作</font></legend>
		 	<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
		 	</fieldset>
		 	<fieldset>
		 	<legend><font style="color: orange">Banner信息编辑页</font></legend>
	<form action="saveSpecialMall.do" enctype="multipart/form-data" method="post" ><input type="hidden" name="id" value="<%=sm!=null&&sm.getId()!=null?sm.getId():""%>">
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr>
				<td><label>Banner描述</label></td>
				<td colspan="3">
					<textarea cols="150" rows="6" name="mallDes"><%=sm!=null&&sm.getMallDes()!=null?sm.getMallDes():""%></textarea>
				</td>
			</tr>
			<tr>
				<td><label>Banner图片</label></td>
				<td><%if(sm!=null&&sm.getMallImg()!=null){%><img name="mallImg" alt="mallImg" src="${IMAGEIP}<%=sm.getMallImg()%>"><%} %>
					<input type="file" name="mallImg1">
				</td>
				<td><label>Banner图片描述</label></td>
				<td>
					<textarea rows="6" cols="60" name="mallContent"><%=sm!=null&&sm.getMallContent()!=null?sm.getMallContent():""%></textarea>
				</td>
			</tr>
			<tr>
				<td><label>Banner详情图片</label></td>
				<td><%if(sm!=null&&sm.getMallInfoImg()!=null){%><img name="mallInfoImg" alt="Banner详情图片" src="${IMAGEIP}<%=sm.getMallInfoImg()%>"/><%} %>
					<input type="file" name="mallInfoImg1">
				</td>
				<td><label>Banner详情图片描述</label></td>
				<td>
					<textarea rows=3 cols="60" name="mallInfoContent"><%=sm!=null&&sm.getMallInfoContent()!=null?sm.getMallInfoContent():""%></textarea>
				</td>
			</tr>
		</table>
		<div style="text-align: center;margin-top:20px;"><input type="button" onclick="doSubmit()" value="提交"></div>
	</form>
	</fieldset>
</body>
<script>
	function doSubmit(){
		var id = document.getElementsByName("id")[0].value;
		var mallDes = document.getElementsByName("mallDes")[0].value;
		if(mallDes==""){
			alert("请输入Banner描述");
			return ;
		}
		var mallImg1 = document.getElementsByName("mallImg1")[0].value;
		if(id==""&&mallImg1==""){
			alert("请选择Banner图片");
			return ;
		}
		var mallContent = document.getElementsByName("mallContent")[0].value;
		if(mallContent==""){
			alert("请输入Banner图片描述");
			return ;
		}
		var mallInfoImg1 = document.getElementsByName("mallInfoImg1")[0].value;
		if(id==""&&mallInfoImg1==""){
			alert("请选择Banner详情图片");
			return ;
		}
		var mallInfoContent = document.getElementsByName("mallInfoContent")[0].value;
		if(mallInfoContent==""){
			alert("请输入Banner详情描述");
			return ;
		}
		document.forms[0].submit();
	}
</script>
</html>