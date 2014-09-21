<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#amount').blur(function(){
			if(isNaN($('#amount').val())){
				alert("Amount must be a number.");
				$('#amount').val("");
				$('#amount').focus();
				return false;
			} else{
				if($('#amount').val() < 0){
					alert("Transfer amount must greater than 0.");
					$('#amount').val("");
					$('#amount').focus();
					return false;
				}
			}
		});
		$('#TransferForm').submit(function(){
			if($.trim($('#recipient').val()) == ""){
				alert("Please enter the account number of the recipient.");
				$('#recipient').focus();
				return false;
			} else{
				if($.trim($('#amount').val()) == ""){
					alert("Please enter the transfer amount.");
					$('#amount').focus();
					return false;
				} else{
					if(confirm("Are you sure you want to make this transfer?")){
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
	<h2>Transfer from account number:<%=request.getParameter("id") %></h2>
	<form id="TransferForm" action="MakeTransferServlet" method="post">
		<input type="hidden" id="id" name="id" value=<%=request.getParameter("id") %> />
		<p>Recipient:<input type="text" id="recipient" name="recipient" /></p>
		<p>Amount:<input type="text" id="amount" name="amount" /></p>
		<p>Description:</p>
		<p><textarea rows="5" cols="35" id="description" name="description" ></textarea></p>
		<p><input type="submit" value="Transfer" /></p>
	</form>
</body>
</html>