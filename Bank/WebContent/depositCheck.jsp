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
			url:"findAccountServlet?flag="+Math.random(),
			data:{"name":$('#username').val()},
			dataType:"json",
			success:function(data){
				if(data){
					if(data[0].accountID == "null"){
						$('#submit').attr('disabled', true);
					} else{
						for(var i = 0;i < data.length;i++){
							if(data[i].accountStatus == "true"){
								$('#account').append("<option value='"+data[i].accountNumber+"'>"+data[i].accountNumber+"</option>");
							}
						}
					}
				}
			}
		});
		$('#amount').blur(function(){
			if(isNaN($('#amount').val())){
				alert("Amount must be a number.");
				$('#amount').val("");
				$('#amount').focus();
				return false;
			} else{
				if($('#amount').val() < 0){
					alert("Deposit amount must greater than 0.");
					$('#amount').val("");
					$('#amount').focus();
					return false;
				}
			}
		});
		$('#depositForm').submit(function(){
			if($('#amount').val() == ""){
				alert("Please enter the deposit amount.");
				return false;
			} else{
				if($('#image').val() == ""){
					alert("Please select the check image.");
					return false;
				} else{
					if(confirm("Are you sure you want to make this deposit?")){
						return true;
					} else{
						return false;
					}
				}
			}
		});
	});
</script>
</head>
<body>
	<input type="hidden" id="username" value=<%=session.getAttribute("username") %> />
	<h2>Make deposit</h2>
	<form id="depositForm" action="DepositCheckServlet">
		<p>Deposit to:<select id="account" name="account"></select></p>
		<p>Amount:<input type="text" id="amount" name="amount" /></p>
		<p>Image: <input type="file" id="image" name="image" accept="image/*" /></p>
		<input type="submit" id="submit" value="Deposit"/>
	</form>
</body>
</html>