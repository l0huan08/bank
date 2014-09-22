<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/website.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script>
	$(document).ready(function(){
		$.ajax({
			type:"post",
			url:"AdminClientInfoServlet?flag="+Math.random(),
			data:{"username":""},
			dataType:"json",
			success:function(data){
				$('table tbody').empty();
				if(data[0].clientID == ""){
					alert("User does not exists.");
					window.location.href="adminClientInfo.jsp";
				} else{
					for(var i = 0;i < data.length;i++){
						$('table tbody').append("<tr><td><input type='radio' name='id' value='"+data[i].userName+"'></td><td>"+data[i].clientID+"</td><td>"+data[i].userName+"</td><td>"+data[i].firstName+"</td><td>"+data[i].lastName+"</td></tr>");
					}
				}
			}
		});
		$('#search').click(function(){
			$('table tbody').empty();
			$.ajax({
				type:"post",
				url:"AdminClientInfoServlet?flag="+Math.random(),
				data:{"username":$('#username').val()},
				dataType:"json",
				success:function(data){
					if(data[0].clientID == ""){
						alert("User does not exists.");
						window.location.href="adminClientInfo.jsp";
					} else{
						for(var i = 0;i < data.length;i++){
							$('table tbody').append("<tr><td><input type='radio' name='id' value='"+data[i].userName+"'></td><td>"+data[i].clientID+"</td><td>"+data[i].userName+"</td><td>"+data[i].firstName+"</td><td>"+data[i].lastName+"</td></tr>");
						}
						$('#username').val("");
					}
				}
			});
		});
		$('table tbody tr:nth-child(1)').find("td").eq(0).children().attr('bgcolor','#FF0000');
		$('#view').click(function(){
			if($("input[name='id']:checked").size() == 0){
				alert("Please select a client.");
			} else{
				window.location.href="editClientInfo.jsp?id="+$("input[name='id']:checked").val();
			}
		});
	});
</script>
</head>
<body>
		Username or name:<input type="text" name="username" id="username" />
		<input type="submit" id="search" value="Search" />
	<h2>Clients' information</h2>
	<div id="display">
		<table border="1">
			<thead>
				<tr>
					<th></th>
					<th>Client ID</th>
					<th>User name</th>
					<th>First name</th>
					<th>Last name</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<p>
		<input type="button" id="view" value="View" />
	</div>
</body>
</html>