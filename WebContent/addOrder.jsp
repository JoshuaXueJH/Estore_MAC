<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>订单</h1><hr>
	<form action="${pageContext.request.contextPath }/AddOrderServlet">
		<table style="width:100%">
			<tr>
				<td width="50%">收货信息:<textarea rows="3" cols="80" name=receiverinfo></textarea></td>
				<td>支付方式:<input type="radio" checked="checked">在线支付</td>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
		<hr>
	</form>
<!-- -----------------------------------------------以上为提交的订单信息，以下为订单内容展示------------------------------------------------------------------ -->
	<table style="width: 100%;text-align: center">
		<tr>
			<th>名称</th>
			<th>单价</th>
			<th>种类</th>
			<th>数量</th>
			<th>库存状态</th>
			<th>合计</th>
		</tr>
		<c:set var="bill" value="0"></c:set>
		<c:forEach items="${sessionScope.cartMap }" var="items">
			<tr>
				<td>${items.key.name }</td>
				<td>${items.key.price }</td>
				<td>${items.key.category }</td>
				<td>${items.value }</td>
				<td><c:if test="${items.value>items.key.pnum }"><font color="red">缺货</font> </c:if> <c:if
						test="${items.value <=items.key.pnum }"><font color="blue">有货</font> </c:if></td>
				<td>${items.key.price*items.value }</td>
				<c:set var="bill" value="${bill+items.key.price*items.value }"></c:set>
			</tr>
		</c:forEach>
	</table>
</body>
</html>