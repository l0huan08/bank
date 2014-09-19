<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script>
		$(function() {
			$( "#accordion" ).accordion({
				heightStyle: "content"
			});
		});
	</script>
  </head>
  
  <body>
 	<div id="accordion">
 		<h3>My Profile</h3>
 		<div>
 			<a href="resetPassword.jsp" target="mainFrame">Reset password</a><p>
 			<a href="updateUserInfo.jsp" target="mainFrame">View my profile</a>
 		</div>
 		<h3>My accounts</h3>
 		<div>
 			<a href="findUser.jsp" target="mainFrame">Open new account</a>
 		</div>
 		<h3>Deposit</h3>
 		<div>
 			<a href="depositCheck.jsp" target="mainFrame">Make deposit</a>
 		</div>
 	</div>
  </body>
</html>
