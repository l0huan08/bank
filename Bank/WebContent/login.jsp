<!DOCTYPE html>
<html>

<head>

  <meta charset="UTF-8">

  <title>Login</title>
	
    <link rel="stylesheet" href="css/login.css" type="text/css" />
    <script src="js/prefixfree.min.js"></script>
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

  <div class="body"></div>
		<div class="grad"></div>
		<div class="header">
			<div>Welcome<span><br> to our Bank</span></div>
		</div>
		<br>
		<div class="login">
			<form id="LoginForm" action="LoginServlet" method="post">
				<input type="text" placeholder="username" id="username" name="username"><br>
				<input type="password" placeholder="password" id="password" name="password"><br>
				<input type="radio" name="status" value="client" checked="checked" />Client
				<input type="radio" name="status" value="admin" />Administrator<br>
				<input type="submit" value="Login">
			</form>
			<p>
			<a href="register.jsp">New Customer?</a>
		</div>
		

  <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

</body>

</html>