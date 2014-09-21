<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script>
$(document).ready(function(){
	var id = $('#id').val();
	$('#another').hide();
	$('#export').hide();
	$('#start').datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: 'yy-mm-dd',
	      onClose: function( selectedDate ) {
	          $( "#end" ).datepicker( "option", "minDate", selectedDate );
	        }
	 });
	$('#end').datepicker({
	      changeMonth: true,
	      changeYear: true,
	      dateFormat: 'yy-mm-dd',
	      onClose: function( selectedDate ) {
	          $( "#start" ).datepicker( "option", "maxDate", selectedDate );
	        }
	 });
	$('#another').click(function(){
		$('#another').hide();
		$('#view').show();
		$('tbody').empty();
		$('#start').val("");
		$('#end').val("");
		$('#export').hide();
		$('#start').attr('disabled', false);
		$('#end').attr('disabled', false);
	});
	$('#view').click(function(){
		$('#another').show();
		$('#view').hide();
		$('#start').attr('disabled', true);
		$('#end').attr('disabled', true);
		if($('#start').length ==  0) {
			alert("Please select a start date.");
			$('#start').focus();
			return false;
		}
		if($('#end').length ==  0) {
			alert("Please select an end date.");
			$('#end').focus()
			return false;
		}
		$.ajax({
			type:"post",
			url:"ViewStatementServlet?flag="+Math.random(),
			data:{
				"id":id,
				"start":$('#start').val(),
				"end":$('#end').val()
			},
			dataType:"json",
			success:function(data){  
				if(data){
					if(data[0].amount == "null"){
						$('#statement').append("<tr><td colspan=3>No transaction found.</td></tr>");
						$('tbody tr').attr('align', 'center');
					} else{
						for(var i = 0;i < data.length;i++){
							$('#statement').append("<tr><td>"+data[i].amount+"</td><td>"+data[i].type+"</td><td>"+data[i].description+"</td></tr>");
							$('tbody tr').attr('align', 'center');
							if(data[i].type == "withdraw" || data[i].type == "transferout"){
								$('tbody tr td').attr('bgcolor', '#FF0000');
							} else{
								$('tbody tr td').attr('bgcolor', '#00FF00');
							}
							$('#export').show();
						}
					}
				} 
			}
		});
	});
});
</script>
</head>
<body>
	<input type="hidden" id="id" value=<%=request.getParameter("id") %> />
	<h3>Please select the period of your statement</h3><p>
	<form id="ViewStatement" action="ViewStatementServlet">
		From:<input type="text" id="start" name="start" /><p>
		To:<input type="text" id="end" name="end" /><p>
		<input type="button" id="view" value="View" />
		<input type="button" id="another" value="View another statement" />
	</form>
	<h2>View your statement</h2>
	<table id="statement" border="1">
		<thead>
			<tr align="center">
				<th>Amount</th>
				<th>Type</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<p><input type="button" id="export" value="Export to Excel" /></p>
</body>
</html>