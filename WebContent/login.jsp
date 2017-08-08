<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Weibo Login</title>
</head>
<body>
	<form action="/weibo/login" >
		username:<input type="text" name="username"><br>
		password:<input type="password" name="password"><br>
		<input type="hidden" name="method" value="login">
		<input type="submit" value="Log In"><br>
	</form>
	<form action="/weibo/registration.jsp">
		<input type="submit" value="Sign In">
	</form>
</body>
</html>