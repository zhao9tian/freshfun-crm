<%@page import="com.quxin.freshfun.model.specialtheme.SpecialThemePOJO"%>
<%@page import="com.quxin.freshfun.utils.DateUtils"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/yuexuan.ico">
<% 
	//��ȡר��list
	List<SpecialThemePOJO> stList = (List<SpecialThemePOJO>)request.getAttribute("stList");
	String curPage = (String)request.getAttribute("curPage");
	String countPage = (String)request.getAttribute("countPage");
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>������ҳ</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/zDialog.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zDrag.js"></script>
</head>
<jsp:include page="../head.jsp" />
<body>
	<fieldset>
		<legend><font style="color:red">����</font></legend>
		<form action="toAddSpecialTheme.do" id="myForm" method="post">
		</form>
		<input type="button" onclick="toAdd()" value="����ר��"/>
		<input type="button" onclick="javascript:history.go(-1)" value="<<����">
	</fieldset>
	<fieldset>
		<legend>ר���б���Ϣ</legend>
		<table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
			<tr style="text-align: center; COLOR: #0076C8; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<th>ר������</th>
				<th>ר��ͼƬ</th>
				<th>��ҳ�İ�</th>
				<th>ר�ⴴ��ʱ��</th>
				<th>ר���޸�ʱ��</th>
				<th>�Ƿ�����</th>
				<th>��ز���</th>
			</tr>
			<%if(stList!=null&&stList.size()>0){
				for(SpecialThemePOJO st : stList){
				%>
				<tr bgcolor='#F4FAFF' style="text-align: center;">
					<td><%=st.getThemeDes() %></td>
					<td><img style="width:100px;height:100px;" src="${IMAGEIP}<%=st.getThemeImg() %>"/></td>
					<td><%=st.getThemeContent() %></td>
					<td><%=DateUtils.longToString(st.getGmtCreate(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><%=DateUtils.longToString(st.getGmtModified(), "yyyy-MM-dd HH-mm-ss") %></td>
					<td><%=st.getIsDeleted()==0?"������":"�ѽ���" %></td>
					<td><a href="toEditSpecialTheme.do?stId=<%=st.getId() %>">�༭ר��</a>&nbsp;<a href="#" onclick="openOrStop(<%=st.getId() %>,<%=st.getIsDeleted()%>)"><%=st.getIsDeleted()==0?"����":"����" %></a>&nbsp;<a href="findSpecialGoods.do?stid=<%=st.getId()%>">�鿴��Ʒ</a></td>
				</tr>
				<%	
				}
			} %>
		</table>
		<div style="text-align: center;">
		<% if(stList!=null){%>
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
		document.getElementById("myForm").action="specialThemeIndex.do?curPage="+res;
		document.getElementById("myForm").submit();
	}
	
		function toAdd(){
			document.getElementById("myForm").submit();
		}
	
		function openOrStop(stid,isdelete){
			var str = "��ʾ����ȷ��Ҫ���ø�ר����";
			if(isdelete==0)
				str = "��ʾ����ȷ��Ҫ���ø�ר����";
			Dialog.confirm(str,function(){
				$.ajax({
					url:'openOrStopSpecialTheme.do',
					data:{stid:stid,isdelete:isdelete},
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