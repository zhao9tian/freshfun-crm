<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>
<head>
	<title>FreshFun后台管理界面</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
	<style>
		a{
			text-decoration:none;
		}
	</style>
</head>
<body>
<div style="text-align: center">
<h2>FreshFun&nbsp;后台管理入口</h2>
<br><br><br>
<a href="goodsList.do">商品管理</a>
<br><br>
<a href="goodstype/getGoodsTypeList.do">分类管理</a>
<br><br>
<a href="specialThemeIndex.do">专题管理</a>
<br><br>
<a href="specialMall/getSMList.do">Banner 专场</a>
<br><br>
<a href="cashManagement.html">提现管理</a>
<br><br>
<a href="backOrder.html">订单管理</a>
<br><br>
<a href="javascript:void(0)" onclick="sendGoods()">快递单号录入</a>
</div>

</body>
<script>
	function sendGoods(){
		var diag = new Dialog();
		diag.Width = 900;
		diag.Height = 300;
		diag.Title = "返回值到调用页面";
		diag.URL = "order/toSendGoods.do";
		diag.OKEvent = function(){
				var orderDetailId = diag.innerFrame.contentWindow.document.getElementById("orderDetailId").value;
				if(typeof(orderDetailId)=="undefined"||orderDetailId==""){
					alert("请输入订单编号");
					return;
				}
				var orderWuliuNo = diag.innerFrame.contentWindow.document.getElementById("orderWuliuNo").value;
				if(typeof(orderWuliuNo)=="undefined"||orderWuliuNo==""){
					alert("请输入快递单号");
					return;
				}
				var company = diag.innerFrame.contentWindow.document.getElementsByName("company");
				var companyCode = "";
				for(var i = 0 ; i<company.length;i++){
					if(company[i].checked){
						companyCode=company[i].value;
					}
					if("bySelf"==companyCode){
						var wirteCode = diag.innerFrame.contentWindow.document.getElementById("wirteCode").value;
						if(typeof(wirteCode)=="undefined"||wirteCode==""){
							alert("请输入快递公司编码");
							return;
						}
						companyCode = wirteCode;
					}
				}
				$.ajax({
					url:'order/updateOrderDetail.do',
					type:"post",
					data:{orderDetailId : orderDetailId,orderWuliuNo:orderWuliuNo,companyCode:companyCode},
					dataType:'text',
					success:function(data){
						if(data!='')
							Dialog.alert(data);
					}
				});
			diag.close();
		};
		diag.show();
	}
</script>
</html>
