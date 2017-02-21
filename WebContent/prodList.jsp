<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>prodList</title>
</head>
<body>
	<h1>ESTORE</h1>
	<hr>
	<table width="100%">
		<c:forEach items="${requestScope.prodList }" var="prod">
			<tr>
				<td width="40%"><img src="${pageContext.request.contextPath }/${prod.imgurl}"></td>
				<td width="40%">品名：${prod.name }<br> 价格：${prod.price }<br>
					种类：${prod.category }<br> 库存：${prod.pnum }<br>
				</td>
				<td width="20%">
					<c:if test="${prod.pnum>0 }"><font color="blue">有货</font></c:if>
					<c:if test="${prod.pnum<=0 }"><font color="red">缺货</font></c:if>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>