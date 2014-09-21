<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script>
	$(document).ready(function(){
		var id = $('#id').val();
		$.ajax({
			type:"post",
			url:"AccountDetailServlet?flag="+Math.random(),
			data:{"id":id},
			dataType:"json",
			success:function(data){  
					if(data){
						$('#accountNumber').val(data[0].accountNumber);
						$('#accountType').val(data[0].accountType);
						if(data[0].balance == ".00"){
							$('#balance').val("0.00");
						} else{
							$('#balance').val(data[0].balance);
						}
						$('#accountStatus').val(data[0].accountStatus);
					} 
			}
		});
		$('#view').click(function(){
			window.location.href = "viewStatement.jsp?id="+id; 
		});
		$('#transfer').click(function(){
			window.location.href = "makeTransfer.jsp?id="+id; 
		});
	});
</script>
</head>
<body>
	<input type="hidden" id="id" value=<%=request.getParameter("id") %> />
	<h2>Account Details</h2>
	<p>
		Account Number: <input type="text" id="accountNumber" disabled="disabled" />
	</p>
	<p>
		Account Type: <input type="text" id="accountType" disabled="disabled" />
	</p>
	<p>
		Balance: <input type="text" id="balance" disabled="disabled" />
	</p>
	<p>
		Account Status: <input type="text" id="accountStatus" disabled="disabled" />
	</p>
	<p>
		<input type="button" id="transfer" value="Make transfer" />
		<input type="button" id="view" value="View Statement"/>
	</p>
</body>
</html>