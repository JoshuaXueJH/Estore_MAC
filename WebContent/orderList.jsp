<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>订单列表</h1>
	<hr>
	<c:forEach items="${requestScope.orderlist }" var="orders">
		<div>
			订单号：${orders.id }<br>
			用户名：${orders.username }<br>
			金额：${orders.money }<br>
			收货信息：${orders.receiverinfo }<br> 
			支付状态：
			<c:if test="${orders.paystate==1 }">
				<font color="blue">已支付</font>
			</c:if>
			<font color="red">未支付
				<a href="${pageContext.request.contextPath }/pay.jsp?id=${orders.id}&money=${orders.money}">支付</a> &nbsp
				<a href="${pageContext.request.contextPath }/DelOrderServlet?id=${orders.id}">删除订单</a>
			</font><br> 
			订单时间：${orders.ordertime }
			<table style="width:100%">
				<tr>
					<th>名称</th>
					<th>种类</th>
					<th>数量</th>
					<th>单价</th>
					<th>总价</th>
				</tr>
				<c:forEach items="${orders.prodMap }" var="prod">
					<tr>
						<td align="center">${prod.key.name }</td>
						<td align="center">${prod.key.category }</td>
						<td align="center">${prod.value }</td>
						<td align="center">${prod.key.price }</td>
						<td align="center"s>${prod.key.price*prod.value }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<hr>
	</c:forEach>
</body>
</html>