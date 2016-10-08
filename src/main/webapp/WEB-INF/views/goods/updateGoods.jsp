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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/goods/editGoods.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>update Goods</title>
<style>
.error{
	color:red;
}
tr{
  COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold;
  } 
</style>
<script type="text/javascript">
function deleteImg(o){
	o.parentNode.parentNode.removeChild(o.parentNode);
}
</script>
</head>
<jsp:include page="../head.jsp" />
<body>
<fieldset>
		 	<legend><font style="color: red">操作</font></legend>
		 	<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
		 	</fieldset>
	<form action="updateGoods.do" method="post" id="commentForm" enctype="multipart/form-data">
		<input id="id" type="hidden" name="id" value="${goods.id }">
		<fieldset>
		 	<legend><font style="color: red">商品信息</font></legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr>
					<td width="120px" >商品名称：</td>
					<td><input id="goodsName" type="text" name="goodsName" value="${goods.goodsName }" required/><span id="errorMessage"></span></td>
					<td>所属商户(id)：</td>
					<td><input type="text" name="storeId" value="${goods.storeId }" readonly="readonly" /></td>
					<td>代理商户(id)：</td> 
					<td><input type="text" name="merchantProxyId"  value="${goods.merchantProxyId }" readonly="readonly" /></td>
				</tr>
				<tr>
					<td>商品单价：</td>
					<td><input type="text" name="shopPriceString" value="${goods.shopPriceString}" required/></td>
					<td>商品标价：</td>
					<td><input type="text" name="marketPriceString" value="${goods.marketPriceString }" required/></td><td></td><td></td>
				<tr>
					<td>商品重量(g)：</td>
					<td><input type="number" name="goodsWeight" value="${goods.goodsWeight }" /></td>
					<td>商品库存量：</td>
					<td><input type="number" name="goodsStorage" value="${goods.goodsStorage }" /></td><td></td><td></td>
				</tr>
				<tr>
					<td>商品类型：</td>
					<td colspan="5">
						<c:forEach items="${goodsTypes}" var="gts">
							<label>
							 	<input type="checkbox"  name="goodsTypes"  value="${gts.id }"  required
							 		<c:forEach items="${ goods.goodsTypeIds}" var="gType">
									 	<c:if test="${gType ==gts.id}">checked="true"</c:if> 
							 		</c:forEach>
							 	/>${gts.goodsType } 
						 	</label>
					 	</c:forEach>
					 	<label class="error" for="goodsTypes"></label>
			 		</td>
				</tr>
				<tr>
					<td>是否库存警报：</td>
					<td >
						<input type="radio" name="warnning" value="1"  <c:if test="${ goods.warnning  =='1' }">checked="true"</c:if> />是
				 		<input type="radio" name="warnning" value="0" <c:if test="${ goods.warnning  =='0' }">checked="true"</c:if> />否
					</td>
					<td>是否新品：</td>
					<td >
						<input type="radio" name="isNew" value="1" <c:if test="${ goods.isNew  =='1' }">checked="true"</c:if> />是
						<input type="radio" name="isNew" value="0" <c:if test="${ goods.isNew  =='0' }">checked="true"</c:if>/>否
					</td>
					<td></td>
					<td >
					</td>
				</tr>
				<tr>
					<td>是否热销：</td>
					<td >
						<input type="radio" name="isHot" value="1" <c:if test="${ goods.isHot  =='1' }">checked="true"</c:if>/>是
						<input type="radio" name="isHot" value="0" <c:if test="${ goods.isHot  =='0' }">checked="true"</c:if>/>否
					</td>
					<td>是否折扣：</td>
					<td colspan="3">
						<input type="radio" name="isPromote" value="1" <c:if test="${ goods.isPromote  =='1' }">checked="true"</c:if>/>是
						<input type="radio" name="isPromote" value="0" <c:if test="${ goods.isPromote  =='0' }">checked="true"</c:if>/>否
					</td>
				</tr>
				<tr>
					<td><label>添加时间</label></td>
					<td><input type="text" name="gmtCreateStr" value="${gmtCreateStr }" readonly="readonly"></td>
					<td><label>修改时间</label></td>
					<td><input type="text" name="gmtModifiedStr" value="${gmtModifiedStr }" readonly="readonly"></td><td></td><td></td>
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
						<input type="file" name="indexFile"/>
					</td>
				</tr>
		</table>
		</fieldset>
		<fieldset>
			<legend>
				<font >商品详细信息</font>
			</legend>
		<input type="hidden" name="goodsId" value="${gm.goodsId }" required>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr>
				<td rowspan="4">商品详情描述:</td>
						<td width="50">Title</td>
						<td colspan="4"><input name="title" style="width:80%" value="${title }" required/></td>
			</tr>
			<tr>
			<td>TitleDes</td>
						<td colspan="4"><textarea name="titleDes" rows="2" cols="110" required>${titleDes }</textarea></td>
			<tr>
			<tr>
				<td>悦选小编说</td>
						<td colspan="4"><textarea name="editer" rows="2" cols="110" required>${editer }</textarea></td>
			</tr>
			<tr>
				<td><label>规格图片</label></td>
				<td colspan="5">
					<img src="${standardImgPath }" width="80">
					<input type="hidden" name="standardImgPath" value="${gm.standardImgPath }">
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
					<div id="newUpload1"><input type="file" name="carouselImg" /></div> 
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
					<div id="newUpload2"><input type="file" name="detailImg" /></div> 
					<input type="button" id="btn_add2" value="add" ><br>
				</td>
			</tr>
		</table>
		<input type="submit" value="提交"/> 
		</fieldset>
	</form> 
</body>
</html>