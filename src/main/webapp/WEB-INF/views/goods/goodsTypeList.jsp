<%@page import="com.quxin.freshfun.model.goods.GoodsTypePOJO"%>
<%@page import="com.quxin.freshfun.utils.DateUtils"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style>
		a{
			text-decoration:none;
		}
	</style>
<% 
	//获取Bannerlist
	List<GoodsTypePOJO> typeList = (List<GoodsTypePOJO>)request.getAttribute("typeList");
	String curPage = (String)request.getAttribute("curPage");
	String countPage = (String)request.getAttribute("countPage");
%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>分类首页</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<%@ include file="../head.jsp" %>
<body>
	<fieldset>
		<legend><font style="color:red">分类操作</font></legend>
		<form action="" id="myForm" method="post">
		</form>
		<input type="button" onclick="toShowTypeInfo()" value="新增分类信息"/>&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="<<返回">
	</fieldset>
	<fieldset>
		<legend>商品分类列表信息</legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<th>分类名称</th>
				<%--<th>分类ICON图片</th>--%>
				<th>分类静态图片</th>
				<th>分类详情描述</th>
				<th>分类创建时间</th>
				<th>分类修改时间</th>
				<th>是否启用</th>
				<th>相关操作</th>
			</tr>
			<%if(typeList!=null&&typeList.size()>0){
				for(GoodsTypePOJO type : typeList){
				%>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td><%=type.getGoodsType() %></td>
					<%--<td><img style="width:100px;height:100px;" src="${IMAGEIP}<%=type.getGoodsTypeImg()%>"/></td>--%>
					<td><img style="width:100px;height:100px;" src="${IMAGEIP}<%=type.getGoodsInfoImg()%>"/></td>
					<td><%=type.getGoodsInfoDes() %></td>
					<td><%=DateUtils.longToString(type.getGmtCreate(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><%=DateUtils.longToString(type.getGmtModified(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><%=type.getIsDeleted()==0?"已启用":"已禁用" %></td>
					<td><a href="toShowTypeInfo.do?gtid=<%=type.getId() %>">编辑分类信息</a>&nbsp;<a href="javascript:void(0)" onclick="openOrStop(<%=type.getId() %>,<%=type.getIsDeleted()%>)"><%=type.getIsDeleted()==0?"禁用":"启用" %></a>&nbsp;<a href="findTypeGoods.do?gtid=<%=type.getId()%>">查看商品</a></td>
				</tr>
				<%	
				}
			}else{ %>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td colspan="7">没有找到您想要的数据哦，快去新增一条吧</td>
				</tr>
			<%} %>
		</table>
	</fieldset>
	<div style="text-align: center;">
	<% if(typeList!=null){%>
		<%if(Integer.parseInt(curPage)!=1){%><a href="javascript:void(0)" onclick="goPage(1)">首页</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)-1>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)-1 %>)">上一页</a><%} %>&nbsp;
		第<%=curPage %>页&nbsp;/&nbsp;共<%=countPage %>页
		<%if(Integer.parseInt(countPage)-Integer.parseInt(curPage)>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)+1 %>)">下一页</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)!=Integer.parseInt(countPage)){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(countPage) %>)">尾页</a><%} %>
	<%} %>
	</div>
	<script>
		function goPage(res){
			document.getElementById("myForm").action="getGoodsTypeList.do?curPage="+res;
			document.getElementById("myForm").submit();
		}
		
		function toShowTypeInfo(){
			document.getElementById("myForm").action="toShowTypeInfo.do";
			document.getElementById("myForm").submit();
		}
	
		function openOrStop(gtId,isdelete){
			var str = "提示：您确认要启用该分类吗？";
			if(isdelete==0)
				str = "提示：您确认要禁用该分类吗？";
			Dialog.confirm(str,function(){
				$.ajax({
					url:'openOrStopGoodsType.do',
					data:{gtId:gtId,isdelete:isdelete},
					dataType:'text',
					success:function(data){
						if(data!='')
							Dialog.alert(data,function(){location.reload();});
					}
				});
			});
		}
	</script>
</body>
</html>