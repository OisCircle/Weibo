<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
<script type="text/javascript">
function validate(){
	var username=document.getElementsByName("username")[0];
	var password=document.getElementsByName("password")[0];
	var repassword=document.getElementsByName("password2")[0];
	if(username.value==0){
		alert("username blank!");
		return false;
	}
	if(password.value==0){
		alert("password blank!");
		return false;
	}
	if(repassword.value==0){
		alert("repassword blank!");
		return false;
	}
	if(password.value!=repassword.value){
		alert("passwords are different!");
		return false;
	}
	return true;
	
}

</script>
</head>
<body>
	<!-- 这里要注意action的url -->
	<form onsubmit="validate();" action="/weibo/reg" >
	username:<input type="text" name="username"><br>
	password:<input type="password" name="password"><br>
	password confirm:<input type="password"	name="password2"><br>
	age:<input type="text" name="age"><br>
	gender:male<input type="radio" name="gender" value="male">female<input type="radio" name="gender" value="female">
	<input type="hidden" value="reg" name="method">
	<input type="submit" value="submit">
	</form>
</body>
</html>