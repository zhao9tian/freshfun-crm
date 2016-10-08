<%@page import="com.quxin.freshfun.model.specialmall.SpecialMallPOJO"%>
<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="java.util.*" %>
<%@ page import="com.quxin.freshfun.model.goods.GoodsPOJO"  %>
<%@ page import="com.quxin.freshfun.model.goods.GoodsTypePOJO"  %>
<%@ page import="com.quxin.freshfun.utils.DateUtils"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
GoodsTypePOJO gt = (GoodsTypePOJO)request.getAttribute("gt");
String curPage = (String)request.getAttribute("curPage");
String countPage = (String)request.getAttribute("countPage");
List<GoodsPOJO> goodsList = (List<GoodsPOJO>)request.getAttribute("list");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDrag.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Banner��Ʒ����</title>
<style>
	a{
		text-decoration:none;
	}
</style>
</head>
<jsp:include page="../head.jsp" />
<body>
<fieldset>
		<legend><font style="color:red">����</font></legend>
		<input type="button" onclick="javascript:history.go(-1)" value="<<����">
	</fieldset>
	<fieldset>
		<legend><%=gt.getGoodsType() %>��Ʒ</legend>
<form action="getGoods.do" method="post" id="myForm">
</form>
<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
	<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
		<td>���</td>
		<td>��ƷͼƬ</td>
		<td>��Ʒ����</td>
		<td>��Ʒ����</td>
		<td>����ʱ��</td>
		<td>����ʱ��</td>
		<td>����</td>
	</tr>
	<% if(goodsList!=null&&goodsList.size()>0){
		for(GoodsPOJO goods : goodsList){ %>
	<tr bgcolor='#F4FAFF' style="text-align: center;">
		<td><%=goods.getId() %></td>
		<td><img alt="mm��ƷͼƬ" style="hight:50px;width:80px;" src="${IMAGEIP}<%=goods.getGoodsImg()%>"></td>
		<td><%=goods.getGoodsName() %></td>
		<td><%=goods.getGoodsDes() %></td>
		<td><%=goods.getGmtCreate()!=null?DateUtils.longToString(goods.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"):"" %></td>
		<td><%=goods.getGmtModified()!=null?DateUtils.longToString(goods.getGmtModified(), "yyyy-MM-dd HH:mm:ss"):"" %></td>
		<td><a href="javascript:void(0)" onclick="removeGoods(<%=goods.getId()%>,<%=gt.getId() %>)">�Ƴ���Ʒ</a></td>
	</tr>
	<%} }else{%>
		<tr bgcolor='#F4FAFF' style="text-align: center;">
		<td colspan="7">û�з���Ҫ�����Ʒ</td>
	</tr>
	<%}%>
</table>
<br/>
<div style="text-align: center;">
<% if(goodsList!=null&&goodsList.size()>0){%>
	<%if(Integer.parseInt(curPage)!=1){%><a href="javascript:void(0)" onclick="goPage(1)">��ҳ</a><%} %>&nbsp;
	<%if(Integer.parseInt(curPage)-1>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)-1 %>)">��һҳ</a><%} %>&nbsp;
	��<%=curPage %>ҳ&nbsp;/&nbsp;��<%=countPage %>ҳ
	<%if(Integer.parseInt(countPage)-Integer.parseInt(curPage)>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)+1 %>)">��һҳ</a><%} %>&nbsp;
	<%if(Integer.parseInt(curPage)!=Integer.parseInt(countPage)){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(countPage) %>)">βҳ</a><%} %>
</div><%} %>
</fieldset>
</body>
<script type="text/javascript">
	
	function goPage(res){
		var countPage = <%=countPage%>;
		var gtId = <%=gt.getId()%>;
		document.getElementById("myForm").action="findTypeGoods.do?curPage="+res+"&countPage="+countPage+"&gtid="+gtId;
		document.getElementById("myForm").submit();
	}

	function removeGoods(goodsId,gtId){
		
		Dialog.confirm("��ʾ��ȷ���ӷ������Ƴ�����Ʒ��",function(){
			$.ajax({
				url:'removeTypeGoods.do',
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
		diag.MessageTitle = "��ʱ����Ϣ�༭ҳ��";
		diag.Message = "��༭��ѡ��Ʒ����ʱ����Ϣ<br/><p style=\"color:red\">ʱ���ʽ24ʱ�ƣ�������2016-01-30 22:40:20�� �� ��2016-01-01 01:10:05��<br/>������ֻУ��ʱ���ʽ����У��ʱ����Ч�ԡ�����ʱ����Ϣʱ��Ӣ�����뷨���淶ʹ�á�</p>";
		diag.URL = "itemShow.do?id="+id;
		diag.show();
	}
</script>
</html>