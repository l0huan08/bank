<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		#welcome{
        	font:bold 20px Verdana, Times New Roman;
		}
	</style>
  </head>
  
  <body>
  	<div id="wrapper" align="right">
  		<div id="welcome">
  			Welcome, <%=session.getAttribute("username") %>
  		</div>
  		<div id="signoff">
  			<a href="LogoutServlet" target="_parent">Sign off</a>
  		</div>
    </div>
  </body>
</html>
