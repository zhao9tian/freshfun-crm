<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/lib/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validation/dist/localization/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/goodslimit/editGoodsLimit.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增限时购商品</title>
<style>
.error{
	color:red;
}

 tr{
  COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold;
  } 
</style>
</head>
<%@ include file="../head.jsp" %>
<body>
	<form action="saveGoodsLimit.do" id="commentForm" class="cmxform"
		 method="post" enctype="multipart/form-data">
		 <fieldset>
		 	<legend><font style="color: red">商品信息</font></legend>
			<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr>
					<td width="130px" >商品名称：</td>
					<td><input id="goodsName" type="text" name="goodsName" required/><span id="errorMessage"></span></td>
					<td>所属商户(id)：</td>
					<td><input type="number"  name="storeId" required /></td>
					<td>代理商户(id)：</td>
					<td><input type="number" name="merchantProxyId" required /></td>
				</tr>
				<tr>
					<td>商品单价(售价)：</td>
					<td><input id="shopPrice" type="text" name="shopPriceString" required /></td>
					<td>折扣(1-10)：</td>
					<td><input id="discount" type="text" range="1,10"  required /></td>
					<td>商品标价：</td>
					<td><input id="marketPrice" type="text" name="marketPriceString" readonly="readonly" required/></td>
				</tr>
				<tr>
					<td>商品库存量：</td>
					<td><input type="number" name="goodsStorage" required/></td>
					<td>限时开始时间：</td>
					<td><input class="Wdate" type="text" onClick="WdatePicker()" name="startTimePick" required/></td>
					<td>限时结束时间：</td>
					<td><input class="Wdate" type="text" onClick="WdatePicker()" name="endTimePick" required/></td>
				</tr>
				<tr>
					<td>是否上架：</td>
					<td>
						<input type="radio" name="isOnSale" value="1" checked="checked"/>是
						<input type="radio" name="isOnSale" value="0"/>否
					</td>
					<td></td>
					<td></td><td></td><td></td>
				<tr>
					<td>商品描述：</td>
					<td colspan="5">
						<textarea rows="2" cols="100" name="goodsDes" required></textarea>
					</td>
				</tr>
				<tr>
					<td>首页图片：</td>
					<td colspan="5">
						<input type="file" name="indexFile" required>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>
				<font style="color: red">商品详细信息</font>	
			</legend>
			<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr>
					<td width="12%">送货数量/次</td>
					<td width="20%"><input type="number" name="goodsUnit" required/></td>
					<td width="15%">商品品牌</td>
					<td width="15%"><input type="text" name="goodsBrand" required/></td>
					<td width="15%">商品规格</td>
					<td width="15%"><input type="text" name="goodsSize" required/></td>
				</tr>
				<tr>
					<td>商品产地</td>
					<td><input type="text" name="goodsPlace" required/></td>
					<td>储存方法</td>
					<td><input type="text" name="storageMethod" required/></td>
					<td>快递信息</td>
					<td><input type="text" name="goodsDelivery" required/></td>
				</tr>
				<tr>
					<td>服务信息</td>
					<td><input type="text" name="goodsService" required/></td>
					<td>生产日期</td>
					<td>
					<input class="Wdate" type="text" onClick="WdatePicker()" name="pickBirthdate" required>
					</td>
					<td>保质期</td>
					<td>
						<input class="Wdate" type="text" onClick="WdatePicker()" name="pickOutdate" required> 
					</td>
				</tr> 
				<tr>
					<td rowspan="4">商品详情描述</td>
					<td rowspan="1" colspan="5">
						<tr>
							<td width="50">Title</td>
							<td colspan="4"><input name="title" style="width:90%" required/></td>
						</tr>
						<tr>
							<td>TitleDes</td>
							<td colspan="4"><textarea name="titleDes" rows="2" cols="110" required></textarea></td>
						</tr>
						<tr>
							<td>悦选小编说</td>
							<td colspan="4"><textarea name="editer" rows="2" cols="110" required></textarea></td>
						</tr>
					</td>
				</tr>
				<tr>
					<td><label>规格图片</label></td>
					<td colspan="5">
						<input type="file" name="standardImg" required>
					</td>
				</tr>
				<tr>
					<td>轮播图片</td>
					<td colspan="5">
						<div id="newUpload1"><input type="file" name="carouselImg" required></div> 
						<input type="button" id="btn_add1" value="add" />
					</td>
				</tr>
				<tr>
					<td>详情图片</td>
					<td colspan="5">
						<div id="newUpload2"><input type="file" name="detailImg" required></div> 
						<input type="button" id="btn_add2" value="add" />
					</td>
				</tr>
			</table>
			<input type="submit" value="提交" style="position: right"/> 
		</fieldset>
	</form> 
</body>
</html>