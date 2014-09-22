<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Open new account</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/hot-sneaks/jquery-ui.css">
<link rel="stylesheet" href="css/website.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

</head>
<body>
 	<h2>Open new account</h2>
 	<p>Please select the account type:</p>
 	<form action="OpenAccountServlet" method="post">
 		<input type="radio" id="accountType" value="1" name="accountType" checked="checked" />Checking
 		<input type="radio" id="accountType" value="2" name="accountType" />Saving
 		<p><input type="submit" value="Open" /></p>
 	</form>
</body>
</html>