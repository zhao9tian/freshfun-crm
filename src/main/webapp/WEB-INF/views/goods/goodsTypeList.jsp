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
	//��ȡBannerlist
	List<GoodsTypePOJO> typeList = (List<GoodsTypePOJO>)request.getAttribute("typeList");
	String curPage = (String)request.getAttribute("curPage");
	String countPage = (String)request.getAttribute("countPage");
%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>������ҳ</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<%@ include file="../head.jsp" %>
<body>
	<fieldset>
		<legend><font style="color:red">�������</font></legend>
		<form action="" id="myForm" method="post">
		</form>
		<input type="button" onclick="toShowTypeInfo()" value="����������Ϣ"/>&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="<<����">
	</fieldset>
	<fieldset>
		<legend>��Ʒ�����б���Ϣ</legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<th>��������</th>
				<%--<th>����ICONͼƬ</th>--%>
				<th>���ྲ̬ͼƬ</th>
				<th>������������</th>
				<th>���ഴ��ʱ��</th>
				<th>�����޸�ʱ��</th>
				<th>�Ƿ�����</th>
				<th>��ز���</th>
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
					<td><%=type.getIsDeleted()==0?"������":"�ѽ���" %></td>
					<td><a href="toShowTypeInfo.do?gtid=<%=type.getId() %>">�༭������Ϣ</a>&nbsp;<a href="javascript:void(0)" onclick="openOrStop(<%=type.getId() %>,<%=type.getIsDeleted()%>)"><%=type.getIsDeleted()==0?"����":"����" %></a>&nbsp;<a href="findTypeGoods.do?gtid=<%=type.getId()%>">�鿴��Ʒ</a></td>
				</tr>
				<%	
				}
			}else{ %>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td colspan="7">û���ҵ�����Ҫ������Ŷ����ȥ����һ����</td>
				</tr>
			<%} %>
		</table>
	</fieldset>
	<div style="text-align: center;">
	<% if(typeList!=null){%>
		<%if(Integer.parseInt(curPage)!=1){%><a href="javascript:void(0)" onclick="goPage(1)">��ҳ</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)-1>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)-1 %>)">��һҳ</a><%} %>&nbsp;
		��<%=curPage %>ҳ&nbsp;/&nbsp;��<%=countPage %>ҳ
		<%if(Integer.parseInt(countPage)-Integer.parseInt(curPage)>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)+1 %>)">��һҳ</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)!=Integer.parseInt(countPage)){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(countPage) %>)">βҳ</a><%} %>
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
			var str = "��ʾ����ȷ��Ҫ���ø÷�����";
			if(isdelete==0)
				str = "��ʾ����ȷ��Ҫ���ø÷�����";
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