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
<title>编辑限时购商品</title>
<style>
.error{
	color:red;
}
</style>
<script type="text/javascript">
function deleteImg(o){
	o.parentNode.parentNode.removeChild(o.parentNode);
}
</script>
</head><%@ include file="../head.jsp" %>
<body>
	<form action="updateGoodsLimit.do" method="post" id="commentForm" enctype="multipart/form-data">
		<input id="id" type="hidden" name="id" value="${goods.id }">
		<fieldset>
		 	<legend><font style="color: red">商品信息</font></legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr >
					<td width="120px" >商品名称：</td>
					<td><input id="goodsName" type="text" name="goodsName" value="${goods.goodsName }" required/><span id="errorMessage"></span></td>
					<td>所属商户(id)：</td>
					<td><input type="digits" name="storeId" value="${goods.storeId }" required/></td>
					<td>代理商户(id)：</td> 
					<td><input type="number" name="merchantProxyId"  value="${goods.merchantProxyId }" required /></td>
				</tr>
				<tr>
					<td>商品单价(售价)：</td>
					<td><input id="shopPrice" type="text" name="shopPriceString" value="${goods.shopPriceString }" required/></td>
					<td>折扣(1-10)：</td>
					<td><input id="discount" type="text" range="1,10" /></td>
					<td>商品标价：</td>
					<td><input id="marketPrice" type="text" name="marketPriceString" value="${goods.marketPriceString }" readonly="readonly" required/></td>
				</tr>
				<tr>
					<td>商品库存量：</td>
					<td><input type="number" name="goodsStorage" value="${goods.goodsStorage }" required/></td>
					<td>限时开始时间：</td>
					<td><input class="Wdate" type="text" onClick="WdatePicker()" value="${startTimePick}" name="startTimePick" required/></td>
					<td>限时结束时间：</td>
					<td><input class="Wdate" type="text" onClick="WdatePicker()" value="${endTimePick}" name="endTimePick" required/></td>
				</tr>
				<tr>
					<td>是否上架：</td>
					<td >
						<input type="radio" name="isOnSale" value="1" <c:if test="${ goods.isOnSale  =='1' }">checked="true"</c:if>/>是
						<input type="radio" name="isOnSale" value="0" <c:if test="${ goods.isOnSale  =='0' }">checked="true"</c:if>/>否
					</td>
				</tr>
				<tr>
					<td><label>添加时间</label></td>
					<td><input type="text" name="gmtCreateStr" value="${gmtCreateStr }" readonly="readonly"></td>
					<td><label>修改时间</label></td>
					<td><input type="text" name="gmtModifiedStr" value="${gmtModifiedStr }" readonly="readonly"></td>
				</tr>
				<tr>
					<td>商品描述：</td>
					<td colspan="5">
						<textarea rows="2" cols="100" name="goodsDes" required>${goods.goodsDes }</textarea>
					</td>
				</tr>
				<tr>
					<td>首页图片：</td>
					<td colspan="5">
						<img src="${indexPicture }" width="80">
						<input type="hidden" name="goodsImg" value="${goods.goodsImg }">
						<br><font style="color:red">若选择新图片则默认覆盖原来的图片</font><br>
						<input type="file" name="indexFile"/>
					</td>
				</tr>
		</table>
		</fieldset>
		<fieldset>
			<legend>
				<font style="color: red">商品详细信息</font>
			</legend>
		<input type="hidden" name="goodsId" value="${gm.goodsId }" required>
		<table rules="all" border="1" style="width:100%">
			<tr>
				<td width="120px">送货数量/次:</td>
				<td><input type="number" name="goodsUnit" value="${gm.goodsUnit }" required/></td>
				<td>商品品牌:</td>
				<td><input type="text" name="goodsBrand" value="${gm.goodsBrand }" required/></td>
				<td>商品规格:</td>
				<td><input type="text" name="goodsSize" value="${gm.goodsSize }" required/></td>
			</tr>
			<tr>
				<td>商品产地:</td>
				<td><input type="text" name="goodsPlace" value="${gm.goodsPlace }" required/></td>
				<td>储存方法:</td>
				<td><input type="text" name="storageMethod" value="${gm.storageMethod }" required/></td>
				<td>快递信息:</td>
				<td><input type="text" name="goodsDelivery" value="${gm.goodsDelivery }" required/></td>
			</tr>
			<tr>
				<td>服务信息:</td>
				<td><input type="text" name="goodsService" value="${gm.goodsService }" required/></td>
				<td>生产日期:</td>
				<td>
				<input class="Wdate" type="text" onClick="WdatePicker()"  name="pickBirthdate" value="${pickBirthdate }" required>
				</td>
				<td>保质期:</td>
				<td>
					<input class="Wdate" type="text" onClick="WdatePicker()" name="pickOutdate" value="${pickOutdate }" required> 
				</td>
			</tr>
			<tr>
				<td rowspan="4">商品详情描述:</td>
				<td rowspan="1" colspan="5">
					<tr>
						<td width="50">Title</td>
						<td colspan="4"><input name="title" style="width:90%" value="${title }" required/></td>
					</tr>
					<tr>
						<td>TitleDes</td>
						<td colspan="4"><textarea name="titleDes" rows="2" cols="110" required>${titleDes }</textarea></td>
					</tr>
					<tr>
						<td>悦选小编说</td>
						<td colspan="4"><textarea name="editer" rows="2" cols="110" required>${editer }</textarea></td>
					</tr>
				</td>
			</tr>
			<tr>
				<td><label>规格图片</label></td>
				<td colspan="5">
					<img src="${standardImgPath }" width="80">
					<input type="hidden" name="standardImgPath" value="${gm.standardImgPath }">
					<br><font style="color:red">若选择新图片则默认覆盖原来的图片</font><br>
					<input type="file" name="standardImg">
				</td>
			</tr>
			<tr>
				<td>轮播图片</td>
				<td colspan="5">
					<c:forEach items="${carouselImgPaths}" var="imgs" >
						 <span>
						 <img src="${imgs.imgView}" width="80">
						 <input type="button" onclick="deleteImg(this)" value="delete"/>
						 <input type="hidden"  name="carousel_file" value="${imgs.imgPath }"/>
						 </span> 
					</c:forEach>
					<br>
					<div id="newUpload1"><input type="file" name="carouselImg"></div> 
					<input type="button" id="btn_add1" value="add" ><br>
				</td>
			</tr>
			<tr>
				<td>详情图片</td>
				<td colspan="5">
					<c:forEach items="${detailImgPaths}" var="imgs" >
						 <span>
						 <img src="${imgs.imgView}" width="80">
						 <input type="button" onclick="deleteImg(this)" value="delete"/>
						 <input type="hidden"  name="detail_file" value="${imgs.imgPath }"/>
						 </span> 
					</c:forEach>
					<br>
					<div id="newUpload2"><input type="file" name="detailImg"></div> 
					<input type="button" id="btn_add2" value="add" />
				</td>
			</tr>
		</table>
		</fieldset>
		<input type="submit" value="提交"/> 
	</form> 
</body>
</html>