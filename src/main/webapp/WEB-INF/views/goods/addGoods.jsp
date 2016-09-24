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
<title>新增商品</title>
<script type="text/javascript">
//	$(function(){
//		if(!$("#gs_11").is(':checked')){
//			$("#gs_pic").hide();
//		}
//		$("#gs_11").click(function(){
//			$("#gs_pic").toggle();
//		});
//	});
</script>
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
<fieldset>
		 	<legend><font style="color: red">操作</font></legend>
		 	<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
		 	</fieldset>
	<form action="saveGoods.do" id="commentForm" class="cmxform"
		 method="post" enctype="multipart/form-data">
		 <fieldset>
		 	<legend><font style="color: red">商品信息</font></legend>
			<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<tr>
					<td width="10%" >商品名称</td>
					<td width="20%"><input id="goodsName" type="text" name="goodsName" required/><span id="errorMessage"></span></td>
					<td width="15%">所属商户</td>
					<td width="15%"><input type="text" name="storeId" readonly="readonly"/></td><!-- value="(暂不填:默认80808080)"  -->
					<td width="15%">代理商户(id)</td>
					<td width="15%"><input type="text" name="merchantProxyId" readonly="readonly"/></td><!-- value="(暂不填:默认80808081)"  -->
				</tr>
				<tr>
					<td>商品单价(售价)</td>
					<td><input type="text" name="shopPriceString" required/></td>
					<td>商品标价</td>
					<td><input type="text" name="marketPriceString" required/></td><td></td><td></td>
				</tr>
				<tr>
					<td>商品重量</td>
					<td><input type="number" name="goodsWeight" /></td>
					<td>商品库存量</td>
					<td colspan="3"><input type="number" name="goodsStorage" /></td>
				</tr>
				<tr>
					<td>商品类型</td>
					<td colspan="5">
						<c:forEach items="${goodsTypes}" var="gts">
							<label>
								<c:if test="${gts.id ==11 }">
					 				<input id="gs_11" type="checkbox"  name="goodsTypes" value="${gts.id }" required />
					 				${gts.goodsType } 
								</c:if>
								<c:if test="${gts.id !=11 }">
					 				<input type="checkbox"  name="goodsTypes" value="${gts.id }" required />
					 				${gts.goodsType } 
								</c:if>
				 			</label>
			 			</c:forEach>
				 		<label for="goodsTypes" class="error"></label>
			 		</td>
				</tr>
				<tr>
					<td>是否库存警报</td>
					<td>
						<input type="radio" name="warnning" value="1" checked="checked" />是
					 	<input type="radio" name="warnning" value="0"/>否
					</td>
					<td>是否新品</td>
					<td>
						<input type="radio" name="isNew" value="1" />是
						<input type="radio" name="isNew" value="0" checked="checked"/>否
					</td>
					<td></td>
					<td>
					</td>
				</tr>
				<tr>
					<td>是否热销</td>
					<td>
						<input type="radio" name="isHot" value="1"/>是
						<input type="radio" name="isHot" value="0" checked="checked"/>否
					</td>
					<td>是否折扣</td>
					<td>
						<input type="radio" name="isPromote" value="1" checked="checked"/>是
						<input type="radio" name="isPromote" value="0"/>否
					</td><td></td><td></td>
				</tr>
				<tr>
					<td>商品描述</td>
					<td colspan="5">
						<textarea rows="2" cols="100" name="goodsDes" required></textarea>
					</td>
				</tr>
				<tr>
					<td>首页图片</td>
					<td colspan="5">
						<input type="file" name="indexFile" required>
					</td>
				</tr>
				<%--<tr id="gs_pic" style="display: none">--%>
					<%--<td>精选图片</td>--%>
					<%--<td colspan="5">--%>
						<%--<input type="file" name="goodsSelect">--%>
					<%--</td>--%>
				<%--</tr>--%>
			</table>
		</fieldset>
		<fieldset>
			<legend>
				<font style="color: red">商品详细信息</font>	
			</legend>
			<table rules="all" border="1" style="width:100%">
				<!-- <tr>
					<td width="12%">送货数量/次</td>
					<td width="20%"><input type="number" name="goodsUnit" /></td>
					<td width="15%">商品品牌</td>
					<td width="15%"><input type="text" name="goodsBrand" /></td>
					<td width="15%">商品规格</td>
					<td width="15%"><input type="text" name="goodsSize" /></td>
				</tr>
				<tr>
					<td>商品产地</td>
					<td><input type="text" name="goodsPlace" /></td>
					<td>储存方法</td>
					<td><input type="text" name="storageMethod" /></td>
					<td>快递信息</td>
					<td><input type="text" name="goodsDelivery" /></td>
				</tr>
				<tr>
					<td>服务信息</td>
					<td><input type="text" name="goodsService" /></td>
					<td>生产日期</td>
					<td>
					<input class="Wdate" type="text" onClick="WdatePicker()" name="pickBirthdate" >
					</td>
					<td>保质期</td>
					<td>
						<input class="Wdate" type="text" onClick="WdatePicker()" name="pickOutdate" > 
					</td>
				</tr>  -->
				<tr>
					<td rowspan="4">商品详情描述</td>
					<td rowspan="1" colspan="5">
						<tr>
							<td width="50">Title</td>
							<td colspan="4"><input name="title" style="width:70%" required/></td>
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
					<td><label>规格图片:(必填)</label></td>
					<td colspan="5">
						<input type="file" name="standardImg" required><font style="color:red">没有图片,商品详情无法显示</font>
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