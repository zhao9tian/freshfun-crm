<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增专题</title>
<style>
	tr{
	 COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold;
	}
</style>

</head>
<%@ include file="../head.jsp" %>
<body>
	<fieldset>
		<legend><font style="color:red">操作</font></legend>
		<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
	</fieldset>
	<fieldset>
		<legend><font style="color:red">操作</font></legend>
	<form action="saveSpecialTheme.do" method="post" >
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr>
				<td><label>主题描述</label></td>
				<td colspan="3">
					<input type="text" name="themeDes" size="30px">
				</td>
			</tr>
			<tr>
				<td><label>主题图片</label></td>
				<td><input type="file" name="themeImg1"></td>
				<td><label>主题图片描述</label></td>
				<td>
					<textarea rows="3" cols="100" name="ThemeContent"></textarea>
			</tr>
			<tr>
				<td><label>主题详情图片</label></td>
				<td><input type="file" name="themeInfoImg1"></td>
				<td><label>主题详情图片描述</label></td>
				<td>
					<textarea rows="3" cols="100" name="themeInfoContent"></textarea>
				</td>
			</tr>
		</table>
		
		<input type="submit" value="提交">
	</form>
	</fieldset>
</body>
</html>