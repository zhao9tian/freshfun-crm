<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改活动 </title>
<script type= "text/javascript">
</script>
<style>
	tr{
	 COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold;
	}
</style>
</head>
<%@ include file="../head.jsp" %>
<body>
	<form action="updateActivity.do" id="commentForm" class="cmxform"
		 method="post" enctype="multipart/form-data">
		 <input type="hidden" name="id" value="${activity.id }"/>
		 <fieldset>
		 	<legend><font style="color: red">活动 信息</font></legend>
			<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr>
					<td>活动描述：</td>
					<td colspan="3"><textarea name="activityDes" rows="6" cols="197">${activity.activityDes }</textarea></td>
					</tr>
				    <tr>
						<td>首页图片：</td>
						<td><img src="${IMAGEIP}${activity.activityImg }" alt="首页图片" /><input type="file" name="indexFile1"></td>
						<td>首页文字描述：</td>
						<td><textarea name="activityContent" rows="6" cols="100">${activity.activityContent }</textarea></td>
				    </tr>
				    <tr>
					    <td>详情页图片：</td>
						<td><img src="${IMAGEIP}${activity.activityInfoImg }" alt="详情图片" /><input type="file" name="indexFile2"></td>
						<td>详情页文字描述：</td>
						<td><textarea name="activityInfoContent" rows="6" cols="100">${activity.activityInfoContent }</textarea></td>
					</tr><tr>
				</tr>
			</table>
		</fieldset>
		<div style="text-align: center;margin-top:20px;"><input type="submit" value="提交">&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="返回"></div>
	</form> 
</body>
</html>