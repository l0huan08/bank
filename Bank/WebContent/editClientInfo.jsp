<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	$(document).ready(function(){
		var name = $('#name').val();
		$('#active').hide();
		$('#frozen').hide();
		$.ajax({
			type:"post",
			url:"ClientInfo?flag="+Math.random(),
			data:{"username":name},
			dataType:"json",
			success:function(data){  
				if(data){
					$('#fname').val(data[0].firstName);
					$('#mname').val(data[0].middleName);
					$('#lname').val(data[0].lastName);
					$("input[name=gender][value="+data[0].gender+"]").attr("checked",true); 
					$('#dob').val(data[0].birthday);
					$('#tel').val(data[0].telephone);
					$('#add1').val(data[0].address1);
					$('#add2').val(data[0].address2);
					$('#zip').val(data[0].zip);
					$('#email').val(data[0].email);
					$('#username').val(data[0].userName);
					$('#password').val(data[0].password);
				} 
			}
		});
		$.ajax({
			type:"post",
			url:"findAccountsServlet?flag="+Math.random(),
			data:{"username":name},
			dataType:"json",
			success:function(data){  
				if(data){
					if(data[0].accountNumber == "null"){
						alert("No account found.");
					} else{
						for(var i = 0;i < data.length;i++){
							if(data[i].accountStatus == "Active"){
								$('#activeAccounts').append("<tr><td><input type='radio' name='id1' value='"+data[i].accountNumber+"'></td><td>"+data[i].accountNumber+"</td><td>"+data[i].accountType+"</td><td>"+data[i].balance+"</td><td>"+data[i].accountStatus+"</td></tr>");
							} else{
								$('#frozenAccounts').append("<tr><td><input type='radio' name='id2' value='"+data[i].accountNumber+"'></td><td>"+data[i].accountNumber+"</td><td>"+data[i].accountType+"</td><td>"+data[i].balance+"</td><td>"+data[i].accountStatus+"</td></tr>");
							}
						}
					}
				} 
				if($('#activeAccounts tbody tr').length != 0){
					$('#active').show();
				}
				if($('#frozenAccounts tbody tr').length != 0){
					$('#frozen').show();
				}
			}
		});
		$('#reset').click(function(){
			$.ajax({
				type:"post",
				url:"resetPasswordServlet?flag="+Math.random(),
				data:{"username":name},
				dataType:"json",
				success:function(data){  
					if(data[0].result == "success"){
						alert("Password has been reseted successfully.");
					} else{
						alert("Reset password failed.");
					}
				}
			});
		});
		$('#delete').click(function(){
			$.ajax({
				type:"post",
				url:"DeleteUserServlet?flag="+Math.random(),
				data:{"username":name},
				dataType:"json",
				success:function(data){  
					if(data[0].result == "success"){
						alert(name + " has been deleted.");
						window.location.href = "adminClientInfo.jsp";
					} else{
						alert(name + " cannot be deleted at this time. Please try again later.");
					}
				}
			});
		});
		$('#frozeAccount').click(function(){
			if($("input[name='id1']:checked").size() == 0){
				alert("Please select an account to froze.");
			} else{
				$.ajax({
					type:"post",
					url:"FrozeAccountServlet?flag="+Math.random(),
					data:{"accountNumber":$("input[name='id1']:checked").val()},
					dataType:"json",
					success:function(data){  
						if(data[0].result == "success"){
							alert($("input[name='id1']:checked").val() + " has been frozen.");
							window.location.href = "editClientInfo.jsp?id=" + name;
							
						} else{
							alert($("input[name='id1']:checked").val() + " cannot be frozen at this time. Please try again later.");
						}
					}
				});
			}
		});
		$('#activeAccount').click(function(){
			if($("input[name='id2']:checked").size() == 0){
				alert("Please select an account to active.");
			} else{
				$.ajax({
					type:"post",
					url:"activeAccountServlet?flag="+Math.random(),
					data:{"accountNumber":$("input[name='id2']:checked").val()},
					dataType:"json",
					success:function(data){  
						if(data[0].result == "success"){
							alert($("input[name='id2']:checked").val() + " has been actived.");
							window.location.href = "editClientInfo.jsp?id=" + name;
						} else{
							alert($("input[name='id2']:checked").val() + " cannot be actived at this time. Please try again later.");
						}
					}
				});
			}
		});
	});
</script>
</head>
<body>
	<input type="hidden" id="name" name="name" value=<%=request.getParameter("id") %> />
	<div id="personal_info">
		<h2>Personal information</h2>
		<p>First name: <input type="text" id="fname" name="fname" disabled="disabled" /></p>
		<p>Middle name: <input type="text" id="mname" name="mname" disabled="disabled" /></p>
		<p>Last name: <input type="text" id="lname" name="lname" disabled="disabled" /></p>
		<p>Gender:<input type="radio" id="gender" name="gender" value="M" />Male
		<input type="radio" id="gender" name="gender" value="F" />Female</p>
		<p>Date of birth: <input type="text" id="dob" name="dob" disabled="disabled" /></p>
		<p>Telephone: <input type="text" id="tel" name="tel" disabled="disabled" /></p>
		<p>Address 1: <input type="text" id="add1" name="add1" disabled="disabled" /></p>
		<p>Address 2: <input type="text" id="add2" name="add2" disabled="disabled" /></p>
		<p>Zip code: <input type="text" id="zip" name="zip" disabled="disabled" /></p>
		<p>Email: <input type="text" id="email" name="email" disabled="disabled" /></p>
		<p>User name: <input type="text" id="username" name="username" disabled="disabled" /></p>
		<p>Password: <input type="password" id="password" name="password" disabled="disabled" /></p>
		<p>
			<input type="button" value="Reset password" id="reset" />
			<input type="button" value="Delete" id="delete" />
		</p>
	</div>
	<p>
	<div id="active">
		<h2>Active accounts</h2>
		<table id="activeAccounts" border="1">
			<thead>
				<tr>
					<th></th>
					<th>Account number</th>
					<th>Account type</th>
					<th>Balance</th>
					<th>Account status</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<p>
		<input type="button" value="Frozen" id="frozeAccount" />
	</div>
	<div id="frozen">
		<h2>Frozen accounts</h2>
		<table id="frozenAccounts" border="1">
			<thead>
				<tr>
					<th></th>
					<th>Account number</th>
					<th>Account type</th>
					<th>Balance</th>
					<th>Account status</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<p>
		<input type="button" value="Active" id="activeAccount" />
	</div>
</body>
</html> 