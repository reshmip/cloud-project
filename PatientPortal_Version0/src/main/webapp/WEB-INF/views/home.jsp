<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Login Form</title>
<style>
	body{
    	background: #000000 url("resources/images/home1.jpg") no-repeat fixed center;
    	background-size: 1600px 900px;
	}
	.login{
		margin-top: 80px;
		margin-bottom:100px;
		padding-top: 30px;
		display: block;
		background-color: rgba(0,0,0,0.6);
		color: white;
		width: 500px;
		height:500px;
	}
	.header{
		margin-left: 60px;
		margin-bottom: 40px;
		font-weight: bold;
		font-size: 32px;
		color: white;
		font-family: sans-serif;
	}
	.form{
		margin-left: 60px;
		conlor: 20px;
	}
	input[type=text], select {
    width: 75%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 75%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    font-size: 14px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

</style>
</head>
<body>
	<div class="login">
		<div class="header" >Log In</div>
		<form:form name="submitForm" method="POST" action="${pageContext.request.contextPath}/loginUser.htm">
		
		<div class="form">
			<div class="label">User Name</div>
			<input type="text" name="userName" />
			<br/>
			<div class="label">Password</div>
			<input type="text" name="password" />
			<br/>
			<br/>
			<input type="submit" value="Submit"/>
		</div>
		
		</form:form>
	</div>
	
</body>
</html>