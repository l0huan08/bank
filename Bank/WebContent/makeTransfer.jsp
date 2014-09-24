<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/website.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#select').hide();
		$('#cancel').hide();
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
			if($.trim($('#recipient').val()) == "" && $('#select').val() == null){
				alert("Please enter the account number of the recipient.");
				$('#recipient').focus();
				return false;
			} else{
				if($('#select').val() != null){
					$('#recipient').val($('#select').val());
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
			}
		});
		$('#between').click(function(){
			$('#select').show();
			$('#recipient').hide();
			$('#between').hide();
			$('#cancel').show();
			$.ajax({
				type:"post",
				url:"findAccountServlet?flag="+Math.random(),
				data:{"name":$('#username').val()},
				dataType:"json",
				success:function(data){
					$('#select').empty();
					for(var i = 0;i < data.length;i++){
						if(data[i].accountStatus == "true" && data[i].accountNumber != $('#id').val()){
							$('#select').append("<option value='"+data[i].accountNumber+"'>"+data[i].accountNumber+"</option>");
						}
					}
					if($('#select option').length == 0){
						$("input[type='submit']").attr('disabled', true);
					}
				}
			});
		});
		$('#cancel').click(function(){
			$('#select').hide();
			$('#recipient').show();
			$('#between').show();
			$('#cancel').hide();
			$("input[type='submit']").attr('disabled', false);
		});
	});
</script>
</head>
<body>
	<h2>Transfer money</h2>
	<form id="TransferForm" action="MakeTransferServlet" method="post">
		<input type="hidden" id="id" name="id" value=<%=request.getParameter("id") %> />
		<input type="hidden" id="username" value=<%=session.getAttribute("username") %> />
		<p>Recipient:<input type="text" id="recipient" name="recipient" /><p>
		<select id="select"></select></p>
		<p>Amount:<input type="text" id="amount" name="amount" /></p>
		<p>Description:</p>
		<p><textarea rows="5" cols="35" id="description" name="description" ></textarea></p>
		<p><input type="submit" value="Transfer" />
			<input type="button" id="between" value="Transfer between your accounts" />
			<input type="button" id="cancel" value="Cancel" />
		</p>
	</form>
</body>
</html>