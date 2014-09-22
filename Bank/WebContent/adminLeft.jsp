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
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/hot-sneaks/jquery-ui.css">
	<link rel="stylesheet" href="css/website.css">
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
	<script>
		$(function() {
			$( "#accordion" ).accordion({
				heightStyle: "content"
			});
			var name = $('#name').val();
			$.ajax({
				type:"post",
				url:"AccountInfo?flag="+Math.random(),
				data:{"username":name},
				dataType:"json",
				success:function(data){  
						if(data){
							for(var i = 0;i < data.length;i++){
								$('#account_list').append("<p><a id='account"+i+"'>"+data[i].accountName+"</a></p>");
								$("#account"+i+"").attr('href',"accountDetail.jsp?id="+data[i].accountName+"");
								$("#account"+i+"").attr('target',"mainFrame");
							}
						} 
				}
			});
		});
	</script>
  </head>
  
  <body>
 	<div id="accordion">
 		<h3>View clients' information</h3>
 		<div id="">
 			<a href="adminClientInfo.jsp" target="mainFrame">View clients' information</a>
 		</div>
 	</div>
  </body>
</html>
