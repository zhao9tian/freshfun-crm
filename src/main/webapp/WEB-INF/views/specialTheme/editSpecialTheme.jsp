<%@page import="com.quxin.freshfun.model.specialtheme.SpecialThemePOJO"%>
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
<%SpecialThemePOJO st = (SpecialThemePOJO)request.getAttribute("st"); %>
<title>专题编辑页</title>
</head>
<%@ include file="../head.jsp" %>
<body>
<fieldset>
		 	<legend><font style="color: red">操作</font></legend>
		 	<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
		 	</fieldset>
		 	<fieldset>
		 	<legend><font style="color: orange">专题信息编辑页</font></legend>
	<form action="saveSpecialTheme.do" enctype="multipart/form-data" method="post" >
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr>
				<td><label>专题描述</label></td>
				<td colspan="3">
					<textarea cols="150" rows="6" name="themeDes"><%=st!=null&&st.getThemeDes()!=null?st.getThemeDes():""%></textarea>
					<input type="hidden" name="id" value="<%=st!=null&&st.getId()!=null?st.getId():""%>">
				</td>
			</tr>
			<tr>
				<td><label>专题图片</label></td>
				<td><%if(st!=null&&st.getThemeImg()!=null){%><img name="themeImg" alt="themeImg" src="${IMAGEIP}<%=st.getThemeImg()%>"><%} %>
					<input type="file" name="themeImg1">
				</td>
				<td><label>专题图片描述</label></td>
				<td>
					<textarea rows="6" cols="60" name="ThemeContent"><%=st!=null&&st.getThemeContent()!=null?st.getThemeContent():""%></textarea>
			</tr>
			<tr>
				<td><label>专题详情图片</label></td>
				<td><%if(st!=null&&st.getThemeInfoImg()!=null){%><img name="themeInfoImg" alt="专题详情图片" src="${IMAGEIP}<%=st.getThemeInfoImg()%>"/><%} %>
					<input type="file" name="themeInfoImg1">
				</td>
				<td><label>专题详情图片描述</label></td>
				<td>
					<textarea rows=3 cols="60" name="themeInfoContent"><%=st!=null&&st.getThemeInfoContent()!=null?st.getThemeInfoContent():""%></textarea>
				</td>
			</tr>
		</table>
		<div style="text-align: center;margin-top:20px;"><input type="button" onclick="doSubmit()" value="提交"></div>
	</form>
	</fieldset>
</body>
<script>
	function doSubmit(){
		var themeDes = document.getElementsByName("themeDes")[0].value;
		if(themeDes==""){
			alert("请输入专题描述");
			return ;
		}
		var ThemeContent = document.getElementsByName("ThemeContent")[0].value;
		if(ThemeContent==""){
			alert("请输入专题描述");
			return ;
		}
		var themeInfoContent = document.getElementsByName("themeInfoContent")[0].value;
		if(themeInfoContent==""){
			alert("请输入专题详情描述");
			return ;
		}
		document.forms[0].submit();
	}
</script>
</html>