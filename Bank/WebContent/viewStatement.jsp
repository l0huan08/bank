<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/hot-sneaks/jquery-ui.css">
<link rel="stylesheet" href="css/website.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
<script src='//www.kunalbabre.com/projects/table2CSV.js'></script>
<script src="js/exportcsv.js"></script>

<script>



$(document).ready(function(){
	var id = $('#id').val();
	$('#another').hide();
	$('#export').hide();
	$('#show').hide();
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
		$('#show').hide();
		$('#start').attr('disabled', false);
		$('#end').attr('disabled', false);
	});
	$('#view').click(function(){
		$('h2').text("Your statement from " + $('#start').val() + " to " +$('#end').val());
		$('#another').show();
		$('#show').show();
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
						$('#statement').append("<tr><td colspan=4>No transaction found.</td></tr>");
						$('table tbody tr').attr('align', 'center');
					} else{
						for(var i = 0;i < data.length;i++){
							$('#statement').append("<tr align='center'><td>"+data[i].time+"</td><td>"+data[i].amount+"</td><td id="+i+">"+data[i].type+"</td><td>"+data[i].description+"</td></tr>");
							$('#export').show();
							if($("#"+i+"").text() == "withdraw" || $("#"+i+"").text() == "transferout"){
								$("#"+i+"").parent().attr('bgcolor','#FF4545');
							} else{
								$("#"+i+"").parent().attr('bgcolor','#33CC33');
							}
						}
					}
				} 
			}
		});
	});
	
	$('#export').click( function() {
		exportCsv('#statement');
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
	<div id="show">
		<h2></h2>
		<table id="statement" border="1">
			<thead>
				<tr align="center">
					<th>Time</th>
					<th>Amount</th>
					<th>Type</th>
					<th>Description</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
		<p><input type="button" id="export" value="Export to Excel" /></p>
	</div>
</body>
</html>