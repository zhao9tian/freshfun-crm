<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="java.util.*" %>
<%@ page import="com.quxin.freshfun.model.goods.GoodsPOJO"  %>
<%@ page import="com.quxin.freshfun.model.activity.ActivityPOJO"  %>
<%@ page import="com.quxin.freshfun.utils.DateUtils"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
ActivityPOJO gt = (ActivityPOJO)request.getAttribute("gt");
System.out.println(gt.getId());
String curPage = (String)request.getAttribute("curPage");
String countPage = (String)request.getAttribute("countPage");
List<GoodsPOJO> goodsList = (List<GoodsPOJO>)request.getAttribute("list");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDrag.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>活动商品展示</title>
<style>
	a{
		text-decoration:none;
	}
</style>
</head>
<%@ include file="../head.jsp" %>
<body>
<div style="text-align: center;margin-top:50px;color:orange;">
	<h1><%=gt.getActivityDes() %>商品</h1>
</div>
	<a href="javascript:void(0)" onclick="javascript:history.go(-1)" >&lt;&lt;返回</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="../goodsList.do" >去为活动添加商品</a>
<form action="getGoods.do" method="post" id="myForm">
</form>
<br/>
<br/>
<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
	<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
		<td>编号</td>
		<td>商品图片</td>
		<td>商品名称</td>
		<td>商品描述</td>
		<td>创建时间</td>
		<td>更新时间</td>
		<td>操作</td>
	</tr>
		<% if(goodsList!=null&&goodsList.size()>0){
		for(GoodsPOJO goods : goodsList){ %>
	<tr bgcolor='#F4FAFF' style="text-align: center;">
		<td><%=goods.getId() %></td>
		<td><img alt="mm商品图片" style="hight:50px;width:80px;" src="${IMAGEIP}<%=goods.getGoodsImg()%>"></td>
		<td><%=goods.getGoodsName() %></td>
		<td><%=goods.getGoodsDes() %></td>
		<td><%=goods.getGmtCreate()!=null?DateUtils.longToString(goods.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"):"" %></td>
		<td><%=goods.getGmtModified()!=null?DateUtils.longToString(goods.getGmtModified(), "yyyy-MM-dd HH:mm:ss"):"" %></td>
		<td><a href="javascript:void(0)" onclick="removeGoods(<%=goods.getId()%>,<%=gt.getId() %>)">移除商品</a></td>
	</tr>
	<%} }else{%>
		<tr bgcolor='#F4FAFF' style="text-align: center;">
		<td colspan="7">没有符合要求的商品</td>
	</tr>
	<%}%>
</table>
<br/>
<div style="text-align: center;">
<% if(goodsList!=null&&goodsList.size()>0){%>
	<%if(Integer.parseInt(curPage)!=1){%><a href="javascript:void(0)" onclick="goPage(1)">首页</a><%} %>&nbsp;
	<%if(Integer.parseInt(curPage)-1>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)-1 %>)">上一页</a><%} %>&nbsp;
	第<%=curPage %>页&nbsp;/&nbsp;共<%=countPage %>页
	<%if(Integer.parseInt(countPage)-Integer.parseInt(curPage)>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)+1 %>)">下一页</a><%} %>&nbsp;
	<%if(Integer.parseInt(curPage)!=Integer.parseInt(countPage)){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(countPage) %>)">尾页</a><%} %>
</div><%} %>
</body>
<script type="text/javascript">
	
	function goPage(res){
		var countPage = <%=countPage%>;
		var gtId = <%=gt.getId()%>;
		document.getElementById("myForm").action="activityGoods.do?curPage="+res+"&countPage="+countPage+"&gtid="+gtId;
		document.getElementById("myForm").submit();
	}

	function removeGoods(goodsId,gtId){
		
		Dialog.confirm("提示：确定从新人购中移除该商品吗？",function(){
			$.ajax({
				url:'removeActivityGoods.do',
				data:{goodsId:goodsId,gtId:gtId},
				dataType:'text',
				success:function(data){
					if(data!='')
						Dialog.alert(data,function(){location.reload();});
				}
			});
		});
	}
	
	function setFlashSale(id){
		var diag = new Dialog();
		diag.Drag=true;
		diag.Width = 800;
		diag.Height = 500;
		diag.AutoClose=180;
		diag.ShowCloseButton=false;
		diag.MessageTitle = "活动信息编辑页面";
		diag.Message = "请编辑所选商品的限时购信息<br/><p style=\"color:red\">时间格式24时制，例：“2016-01-30 22:40:20” 、 “2016-01-01 01:10:05”<br/>本宝宝只校验时间格式，不校验时间有效性。输入时间信息时用英文输入法，规范使用。</p>";
		diag.URL = "itemShow.do?id="+id;
		diag.show();
	}
</script>


</html>