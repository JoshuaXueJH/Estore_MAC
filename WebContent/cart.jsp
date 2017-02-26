<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
<script type="text/javascript">
	function changeNum(id,obj,oldNum){
		if(!/^[1-9]\d*$/.test(obj.value)){
			alert("购买数量必须是正整数");
			obj.value=oldNum;
			return;
		}
		window.location.href="ChangeCartNumServlet?id="+id+"&buyNum="+obj.value;
	}
	function continueToShop(){
		window.location.href="ProdListServlet";
	}
	function emptyCart(){
		window.location.href="EmptyCartServlet";
	}
</script>
</head>
<body>
	<h1>购物车</h1>
	<hr>
	<c:if test="${empty sessionScope.cartMap }">
		<a href="${pageContext.request.contextPath }/ProdListServlet">购物车空空如也，去添加点商品吧</a>
	</c:if>
	<c:if test="${!empty sessionScope.cartMap }">
		<div align="right">
			<button onclick="continueToShop()">继续购物</button>
			<button onclick="emptyCart()">清空购物车</button>
		</div><br>
		<table style="width: 100%;text-align: center">
		<tr>
			<th>图片</th>
			<th>名称</th>
			<th>单价</th>
			<th>种类</th>
			<th>数量</th>
			<th>库存状态</th>
			<th>合计</th>
			<th>操作</th>
		</tr>
		<c:set var="bill" value="0"></c:set>
		<c:forEach items="${sessionScope.cartMap }" var="items">
			<tr>
				<td><img alt="pic" src="${pageContext.request.contextPath }/${items.key.imgurls}"></td>
				<td>${items.key.name }</td>
				<td>${items.key.price }</td>
				<td>${items.key.category }</td>
				<td><input type="text" value="${items.value }" style="width:20px;" onchange="changeNum('${items.key.id }',this,'${items.value }')"></td>
				<td><c:if test="${items.value>items.key.pnum }"><font color="red">缺货</font> </c:if> <c:if
						test="${items.value <=items.key.pnum }"><font color="blue">有货</font> </c:if></td>
				<td>${items.key.price*items.value }</td>
				<c:set var="bill" value="${bill+items.key.price*items.value }"></c:set>
				<td><a href="${pageContext.request.contextPath }/DelCartItemServlet?id=${items.key.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div align="right">
			总价：<font color="red" size="7">${bill }</font>
			<a href="${pageContext.request.contextPath }/addOrder.jsp"><img alt="pic" src="img/gotoorder.bmp"></a>
	</div>
	</c:if>
</body>
</html>