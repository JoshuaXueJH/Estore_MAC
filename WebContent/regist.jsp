<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>regsit</title>
<script type="text/javascript">
	
	function changeImg(img) {
		img.src = img.src + "?time" + new Date().getTime();
	}
	
	function checkForm() {
		var checkResult = true;
		checkResult = checkNull("username", "用户名不能为空") && checkResult;
		checkResult = checkNull("password", "密码不能为空") && checkResult;
		checkResult = checkNull("password2", "确认密码不能为空") && checkResult;
		checkResult = checkNull("nickname", "用户昵称不能为空") && checkResult;
		checkResult = checkNull("email", "电子邮件不能为空") && checkResult;
		checkResult = checkNull("valistr", "验证码不能为空") && checkResult;
		
		//两次密码一致性校验
		var pwd=document.getElementsByName("password")[0].value;
		var pwd2=document.getElementsByName("password2")[0].value;
		if(pwd!=pwd2){
			document.getElementById("password2_msg").innerHTML="<font color='red'>两次密码不一致</font>";
			checkResult=false;
		}
		
		//邮件格式校验
		var email = document.getElementsByName("email")[0].value;

		if (email != null && email != "" && !/^\w+@\w+(.\w)+$/.test(email)) {
			document.getElementById("email_msg").innerHTML = "<font color='red'>邮箱格式不正确</font>";
			checkResult = false;
		}

		return checkResult;
	}

	function checkNull(name, msg) {
		document.getElementById(name + "_msg").innerHTML = "";
		var value = document.getElementsByName(name)[0].value;
		if (value == null || value == "") {
			document.getElementById(name + "_msg").innerHTML = "<font color='red'>"
					+ msg + "</font>";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<h1>用户注册</h1>
	<hr>
	<form action="${pageContext.request.contextPath }/RegistServlet" method="post" onsubmit="return checkForm()">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username" value="${param.username }"></td>
				<td id="username_msg"></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type="password" name="password"></td>
				<td id="password_msg"></td>
			</tr>
			<tr>
				<td>确认密码:</td>
				<td><input type="password" name="password2"></td>
				<td id="password2_msg"></td>
			</tr>
			<tr>
				<td>用户昵称:</td>
				<td><input type="text" name="nickname" value="${param.nickname }"></td>
				<td id="nickname_msg"></td>
			</tr>
			<tr>
				<td>电子邮箱:</td>
				<td><input type="text" name="email" value="${param.email }"></td>
				<td id="email_msg"></td>
			</tr>
			<tr>
				<td>验证码:</td>
				<td><input type="text" name="valistr"></td>
				<td id="valistr_msg"><font color="red">${msg}</font></td>
			</tr>
			<tr>
				<td><input type="submit" value="注册"></td>
				<td><img alt="我是验证码" src="${pageContext.request.contextPath }/ValiImage" onclick="changeImg(this)" style="cursor: pointer"></td>
			</tr>
		</table>
	</form>
</body>
</html>