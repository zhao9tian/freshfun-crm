<%@page import="com.quxin.freshfun.model.specialmall.SpecialMallPOJO"%>
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
	List<SpecialMallPOJO> smList = (List<SpecialMallPOJO>)request.getAttribute("smList");
	String curPage = (String)request.getAttribute("curPage");
	String countPage = (String)request.getAttribute("countPage");
%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Banner��ҳ</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<jsp:include page="../head.jsp" />
<body>
	<fieldset>
		<legend><font style="color:red">Banner����</font></legend>
		<form action="" id="myForm" method="post">
		</form>
		<input type="button" onclick="toShowPage()" value="����Banner��Ϣ"/>&nbsp;<input type="button" onclick="javascript:history.go(-1)" value="<<����">
	</fieldset>
	<fieldset>
		<legend>Banner�б���Ϣ</legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<th>Banner����</th>
				<th>BannerͼƬ</th>
				<th>Banner��ҳ�İ�</th>
				<th>Banner����ʱ��</th>
				<th>Banner�޸�ʱ��</th>
				<th>�Ƿ�����</th>
				<th>��ز���</th>
			</tr>
			<%if(smList!=null&&smList.size()>0){
				for(SpecialMallPOJO sm : smList){
				%>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td><%=sm.getMallDes() %></td>
					<td><img style="width:100px;height:100px;" src="${IMAGEIP}<%=sm.getMallImg() %>"/></td>
					<td><%=sm.getMallContent() %></td>
					<td><%=DateUtils.longToString(sm.getGmtCreate(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><%=DateUtils.longToString(sm.getGmtModified(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><%=sm.getIsDeleted()==0?"������":"�ѽ���" %></td>
					<td><a href="toShowPage.do?smid=<%=sm.getId() %>">�༭BANNER</a>&nbsp;<a href="javascript:void(0)" onclick="openOrStop(<%=sm.getId() %>,<%=sm.getIsDeleted()%>)"><%=sm.getIsDeleted()==0?"����":"����" %></a>&nbsp;<a href="findSMGoods.do?smid=<%=sm.getId()%>">�鿴��Ʒ</a></td>
				</tr>
				<%	
				}
			}else{ %>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td colspan="7">û���ҵ�����Ҫ������Ŷ����ȥ����һ����</td>
				</tr>
			<%} %>
		</table>
		<div style="text-align: center;">
	<% if(smList!=null){%>
		<%if(Integer.parseInt(curPage)!=1){%><a href="javascript:void(0)" onclick="goPage(1)">��ҳ</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)-1>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)-1 %>)">��һҳ</a><%} %>&nbsp;
		��<%=curPage %>ҳ&nbsp;/&nbsp;��<%=countPage %>ҳ
		<%if(Integer.parseInt(countPage)-Integer.parseInt(curPage)>0){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(curPage)+1 %>)">��һҳ</a><%} %>&nbsp;
		<%if(Integer.parseInt(curPage)!=Integer.parseInt(countPage)){%><a href="javascript:void(0)" onclick="goPage(<%=Integer.parseInt(countPage) %>)">βҳ</a><%} %>
	<%} %>
	</div>
	</fieldset>
	
	<script>
		function goPage(res){
			document.getElementById("myForm").action="getSMList.do?curPage="+res;
			document.getElementById("myForm").submit();
		}
		
		function toShowPage(){
			document.getElementById("myForm").action="toShowPage.do";
			document.getElementById("myForm").submit();
		}
	
		function openOrStop(smid,isdelete){
			var str = "��ʾ����ȷ��Ҫ���ø�BANNER��";
			if(isdelete==0)
				str = "��ʾ����ȷ��Ҫ���ø�BANNER��";
			Dialog.confirm(str,function(){
				$.ajax({
					url:'openOrStopSpecialMall.do',
					data:{smid:smid,isdelete:isdelete},
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