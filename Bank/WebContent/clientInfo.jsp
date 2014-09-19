<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script>
	$(document).ready(function(){	
		var name = $('#name').val();
		$('#submit').hide();
		$('#cancel').hide();
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
		$('#edit').click(function(){
			$('#submit').show();
			$('#cancel').show();
			$('#edit').hide();
			$('#fname').attr("disabled", false);
			$('#mname').attr("disabled", false);
			$('#lname').attr("disabled", false);
			$('#dob').attr("disabled", false);
			$('#tel').attr("disabled", false);
			$('#add1').attr("disabled", false);
			$('#add2').attr("disabled", false);
			$('#zip').attr("disabled", false);
			$('#email').attr("disabled", false);
			$('#password').attr("disabled", false);
			$('#dob').datepicker({
			      changeMonth: true,
			      changeYear: true,
			      dateFormat: 'yy-mm-dd'
			 });
		});
		$('#cancel').click(function(){
			$('#submit').hide();
			$('#cancel').hide();
			$('#edit').show();
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
			$('#fname').attr("disabled", true);
			$('#mname').attr("disabled", true);
			$('#lname').attr("disabled", true);
			$('#dob').attr("disabled", true);
			$('#tel').attr("disabled", true);
			$('#add1').attr("disabled", true);
			$('#add2').attr("disabled", true);
			$('#zip').attr("disabled", true);
			$('#email').attr("disabled", true);
			$('#password').attr("disabled", true);
		});
	});
</script>
</head>
<body>
	<h2>My Profile</h2>
	<form id="ClientInfo" action="ClientInfoServlet" >
		<p><input type="hidden" id="name" value=<%=session.getAttribute("username") %> /></p>
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
		<p>Password: <input type="text" id="password" name="password" disabled="disabled" /></p>
		<p>
			<input type="button" id="edit" value="Edit"/>
			<input type="submit" id="submit" value="Confirm"/>
			<input type="button" id="cancel" value="Cancel" />
		</p>
	</form>
</body>
</html>