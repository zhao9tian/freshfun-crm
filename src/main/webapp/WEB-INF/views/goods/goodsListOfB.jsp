<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>

</head>

<body>

			<table  border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
				<thead>
				<tr  style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
					<th>序号</th>
					<th>商品名称</th>
					<th>商品单价</th>
					<th>商品标价</th>
					<th>全民电商操作</th>
					<th>相关操作</th>
				</tr>
				</thead>
				<tbody id="goodsList">

				</tbody>
			</table>
			<br>
			<div style="text-align: center" id="pageInfo">
			</div>


</body>
<script>

	getGoodsList(1);
	function getGoodsList(curPage){
		$.ajax({
			url:'pullGoodsFromB.do?curPage='+curPage,
			dataType:'json',
			success:function(data){
				console.log(data);
				var htmlstr = '';
				$.each(data.result.data,function(n,obj){
					var ddDemo = '<tr bgcolor="#F4FAFF" style="text-align: center;">'+
							'<td>'+(n+1)+'</td>'+
							'<td><a href="javascript:void(0)" onclick="goodsInfo('+obj.goodsId+')">'+obj.goodsName+'</a></td>'+
							'<td>'+obj.shopPrice+'</td>'+
							'<td>'+obj.marketPrice+'</td>';
					if(obj.isOnAgent==1){
						ddDemo+='<td><a href="javascript:void(0)" onclick="setAgent('+obj.goodsId+',2)">上架</a></td>';
					}else{
						ddDemo+='<td><a href="javascript:void(0)" onclick="setAgent('+obj.goodsId+',1)">下架</a></td>';
					}
					if(obj.proxyId==null||obj.proxyId==''||obj.proxyId==80808081)
						ddDemo+='<td><a href="javascript:void(0)" onclick="setProxy('+obj.goodsId+')">绑定代理人</a></td>'+
								'</tr>';
					else
						ddDemo+='<td><a href="javascript:void(0)" onclick="seeProxy('+obj.proxyPhone+')">查看代理人</a></td>'+
								'</tr>';
					htmlstr+=ddDemo;
				});
				$("#goodsList").html(htmlstr);
				if(data.result.countPage>0){
					var pageTxt ='';
					if(data.result.currentPage>1){
						pageTxt+='<a onClick="getGoodsList(1)" href="javascript:void(0)">首页</a>'+
						'<a onClick="getGoodsList('+(data.result.currentPage-1)+')" href="javascript:void(0)">上一页</a>';
					}
					pageTxt+='第'+data.result.currentPage+'页/共'+data.result.countPage+'页';
					if(data.result.currentPage<data.result.countPage){
						pageTxt+='<a onClick="getGoodsList('+(data.result.currentPage+1)+')" href="javascript:void(0)">下一页</a>'+
								'<a onclick="getGoodsList('+data.result.countPage+')" href="javascript:void(0)" >尾页</a>';
					}
					$("#pageInfo").html(pageTxt)
				}

			}
		});
	}

	function seeProxy(phone){
		Dialog.alert("代理人手机号为："+phone);
	}

	/**
	 * B端商品上下架
	 * @param goodsId
	 * @param isOnAgent
	 */
	function setAgent(goodsId,isOnAgent){
		$.ajax({
			url:'goodsAgentStatus.do?goodsId='+goodsId+'&agent='+isOnAgent,
			dataType:'json',
			success:function(data){
				Dialog.alert(data.status.msg,function(){location.reload();});
			}
		});
	}
	function setProxy(goodsId){
		var diag = new Dialog();
		diag.Width = 320;
		diag.Height = 80;
		diag.Title = "请输入代理人绑定的手机号";//弹窗标题
		diag.URL = "goodsTOBindOfB.do";
		diag.OKEvent = function(){
			var phone =  diag.innerFrame.contentWindow.document.getElementById('phone').value;
			if(typeof(phone)=="undefined"||""==phone){
				alert("请输入代理人绑定的手机号");
				return;
			}
			$.ajax({
				url:'goodsBindProxy.do?goodsId='+goodsId+'&phone='+phone,
				dataType:'json',
				success:function(data){
					if(data.status.code==1){
						Dialog.alert(data.status.msg,function(){location.reload();});
					}else{
						Dialog.alert(data.status.msg);
					}

				}
			});
			diag.close();
		};
		diag.show();
		var doc=diag.innerFrame.contentWindow.document;
		doc.open();
		doc.write('<html><body>请输入手机号：<input id="phone" type="text"/></body></html>') ;
		doc.close();
	}
	function goodsInfo(goodsId){
		alert("商品id:"+goodsId);
		window.location.href="goodsInfoOfB.do?goodsId="+goodsId;
	}
</script>
</html>