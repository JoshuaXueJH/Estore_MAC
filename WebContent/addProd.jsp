<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product</title>
<script type="text/javascript">
	function checkData(){
		var price = document.getElementsByName("price")[0].value;
		if(isNaN(price)){
			alert("单价必须是数字");
			document.getElementsByName("price")[0].value="";
			return false;
		}else if(price<=0){
			alert("单价不能小于等于0.0");
			document.getElementsByName("price")[0].value="";
			return false;
		}else{
			return true;
		}
	}
</script>
</head>
<body>
	<h1>添加商品</h1>
	<hr>
	<form action="${pageContext.request.contextPath }/AddprodServlet" method="post" enctype="multipart/form-data" onsubmit="return checkData()">
		<table>
			<tr>
				<td>名称</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>单价</td>
				<td><input type="text" name="price"></td>
			</tr>
			<tr>
				<td>数量</td>
				<td><input type="text" name="pnum"></td>
			</tr>
			<tr>
				<td>种类</td>
				<td><select name="category">
						<option value="电子数码">电子数码
						<option value="图书杂志">图书杂志
						<option value="床上用品">床上用品
						<option value="日用百货">日用百货
						<option value="大型家电">大型家电
				</select></td>
			</tr>
			<tr>
				<td>图片</td>
				<td><input type="file" name="file"></td>
			</tr>
			<tr>
				<td>描述</td>
				<td><textarea name="description" rows="" cols=""></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="提交"></td>
			</tr>
		</table>
	</form>
</body>
</html>