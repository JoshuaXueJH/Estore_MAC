<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<h1>ESTORE</h1>
	<hr>
	<c:if test="${sessionScope.user==null }">
		欢迎光临， 您目前的身份是游客！ 
		<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
		<a href="${pageContext.request.contextPath }/regist.jsp">注册</a>
	</c:if>
	<c:if test="${sessionScope.user!=null }">
		<a href="${pageContext.request.contextPath }/addProd.jsp">添加商品</a>
		<a href="${pageContext.request.contextPath }/ProdListServlet">商品列表</a>
		<a href="${pageContext.request.contextPath }/cart.jsp">购物车</a>
		<a href="${pageContext.request.contextPath }/OrderListServlet">订单查询</a>
		<a href="${pageContext.request.contextPath }/">销售榜单下载</a>
		<a href="${pageContext.request.contextPath }/LogoutServlet">注销</a>
	</c:if>
</body>
</html>