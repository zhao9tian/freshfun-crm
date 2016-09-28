<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
    String goodsId = (String)request.getAttribute("goodsId");
%>
    <style type="text/css">
        img{
            width: 200px;
            hight:170px;
        }
        tr{
            background-color:#F4FAFF;
        }
    </style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<script>
    var goodsId = <%=goodsId%>;
    $.ajax({
        url:'findGoodsByGoodsId.do?goodsId='+goodsId,
        dataType:'json',
        success:function(data){
            console.log(data);
            $("#indexImg").html('<img alt="首页图片" src="'+data.result.indexPicture+'" id="indexImg"/>');
            $("#standardImg").html('<img alt="商品规格图片" src="'+data.result.standardImgPath+'" id="standardImg"/>');
            var detailImg = '';
            $.each(data.result.detailImgPaths,function(n,obj){
                detailImg += '<img alt="商品规格图片" src="'+obj.imgView+'"/>&nbsp;&nbsp;';
            });
            $("#detailImgs").html(detailImg);
            var carouselImg = '';
            $.each(data.result.carouselImgPaths,function(n,obj){
                carouselImg += '<img alt="商品规格图片" src="'+obj.imgView+'"/>&nbsp;&nbsp;';
            });
            $("#carouselImgs").html(carouselImg);

            $("#goodsName").val(data.result.title);
            $("#goodsDes").val(data.result.titleDes);
            $("#editor").html(data.result.editer);
            $("#shopPrice").val(data.result.shopPrice);
            $("#marketPrice").val(data.result.marketPrice);
            $("#proxyId").val(data.result.proxyId);
            $("#createDate").val(data.result.createDate);
            $("#modifyDate").val(data.result.modifyDate);
            var types = '';
            if(data.result.goodsTypeIds.length>0){
                $.each(data.result.goodsTypes,function(n,obj){
                    $.each(data.result.goodsTypeIds,function(n1,obj1){
                        console.log(obj.id+":"+obj1);
                        if(obj.id==obj1){
                            types+=obj.goodsType+" .  ";
                        }
                    });
                });
            }
            $("#goodsType").html(types);
        }
    });
</script>
</head>

<body>
<fieldset>
    <legend><span style="color: #ff6654">操作</span></legend>
    <input type="button" onclick="javascript:history.go(-1)" value="<<返回列表页">
</fieldset>
    <table border="0" cellpadding="3" cellspacing="1" width="100%" align="center" style="background-color: #b9d8f3;">
        <tr>
            <td>商品名称</td>
            <td><input id="goodsName" readonly= "true " type="text"/></td>
            <td>商品描述</td>
            <td><input id="goodsDes" readonly= "true " type="text"/></td>
        </tr>
        <tr>
            <td>悦选小编说</td>
            <td colspan="3"><textarea rows="5" cols="100" id="editor"></textarea></td>
        </tr>
        <tr>
            <td>商品售价</td>
            <td><input id="shopPrice" readonly= "true " type="text"/></td>
            <td>商品标价</td>
            <td><input id="marketPrice" readonly= "true " type="text"/></td>
        </tr>
        <tr>
            <td>商品分类</td>
            <td id="goodsType"></td>
            <td>代理商户id</td>
            <td><input id="proxyId" readonly= "true " type="text"/></td>
        </tr>

        <tr>
            <td>创建时间</td>
            <td><input id="createDate" readonly= "true " type="text"/></td>
            <td>更新时间</td>
            <td><input id="modifyDate" readonly= "true " type="text"/></td>
        </tr>
        <tr>
            <td>首页图片</td>
            <td id="indexImg"></td>
            <td>规格图片</td>
            <td id="standardImg"></td>
        </tr>
        <tr>
            <td>轮播图片</td>
            <td colspan="3" id="detailImgs"></td>
        </tr>
        <tr>
            <td>详情图片</td>
            <td colspan="3" id="carouselImgs"></td>
        </tr>
    </table>
</body>

</html>