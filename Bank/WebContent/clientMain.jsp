<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
  <head>
  	
    <title>管理员主页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-store">
	<meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
  </head>
  

  <frameset rows="115,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="clientTop.jsp" name="topFrame" scrolling="No" id="topFrame" title="topFrame"/>
  	<frameset cols="200,*" frameborder="no" border="0" framespacing="0">
		<frame src="clientLeft.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame"/>
		<frame src="clientIndex.jsp" name="mainFrame" id="mainFrame" title="mainFrame"/>
  	</frameset>
  </frameset>
  <noframes><body>Your browser does not support frameset, please change your browser and try again.</body></noframes>
  
</html>
