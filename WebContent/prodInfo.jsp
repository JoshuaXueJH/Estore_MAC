<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prod Information</title>
</head>
<body>
	<h1>商品信息--${prod.name }</h1>
	<hr>
	<table style="text-align: center;">
		<tr>
			<td width="60%"><img alt="pic" src="${pageContext.request.contextPath }/${prod.imgurl}"></td>
			<td width="40%">
			商品名称：${prod.name }<br>
			商品种类：${prod.category }<br>
			商品库存：${prod.pnum }<br>
			商品价格：${prod.price }<br>
			商品描述：${prod.description }<br>
			<a href="${pageContext.request.contextPath }/AddToCartServlet?id=${prod.id}"><img alt="pic" src="./img/buy.bmp"></a>
			</td>
		</tr>
	</table>
</body>
</html>