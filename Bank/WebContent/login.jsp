<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
   <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/hot-sneaks/jquery-ui.css">
   <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('#LoginForm').submit(function(){
			var username = $.trim($('#username').val());
			var password = $.trim($('#password').val());
			if(username == "") {
				alert("Please enter username.");
				$('#username').focus();
				return false;
			}
			if(password == "") {
				alert("Please enter password.");
				$('#password').focus()
				return false;
			}
		});
	});
</script>
</head>
<body>
Welcome to online bank!
<form id="LoginForm" action="LoginServlet" method="post">
	<p>Username: <input type="text" id="username" name="username"/></p>
	<p>Password: <input type="password" id="password" name="password"/></p>
	<p>Login as:</p>
	<input type="radio" name="status" value="client" checked="checked" />Client
	&nbsp;&nbsp;&nbsp;
	<input type="radio" name="status" value="admin" />Administrator
	<P><input type="submit" value="Login" /></P>
	<p><a href="register.jsp">New Customer?</a></p>
</form> 
</body>
</html>