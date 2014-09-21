<%@ page language="java" contentType="text/html charset=utf-8"
	pageEncoding="utf-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html charset=utf-8">
<title>Register Client</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="css/form-validation.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
<script src="//jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
<script
    src="//jqueryvalidation.org/files/dist/additional-methods.min.js">
</script>
<script>
	$().ready(	function() {

		$('#dob').datepicker({
            changeMonth : true,
            changeYear : true,
            dateFormat : 'yy-mm-dd'
        });
        
		$("#clientProfileForm").validate( {
							rules : {
								fname : "required",
								lname : "required",
								gender : {
									required : true,
									maxlength : 1
								},
								dob : {
									required : true,
									date : true
								},
								tel : {
									required : true,
									phoneUS : true
								},
								add1 : "required",
								zip : {
									required : true,
									digits : true,
									rangelength: [6,6]
								},
								email : {
									required : true,
									email : true
								},
                                username : "required",
								password : "required",
								agree : "required"
							},
		
							messages: {
								agree: "Please accept our policy"
							}
		
		});
		
	});
</script>

<style>
.clearfix {
	zoom: 1;
}

.clearfix:after {
	content: '.';
	display: block;
	visibility: none;
	height: 0;
	clear: both;
}

#rules {
	float: left;
	width: 100px;
	border: 2px solid #FF0000;
}

#rulesTitle {
	color: blue;
}

#clientProfile {
	float: left;
}
</style>


</head>
<body>
	<div id="welcome" align="left">
		<h1>Client Register</h1>
	</div>

	<div id="rules" class="clearfix">
		<div id="rulesTitle" align="center">
			<h2>Client Rules</h2>
		</div>

		<div id="rulesInfo" align="center">All the money you save here
			will be our banks.</div>
	</div>

	<div id="clientProfile">
		<form id="clientProfileForm" action="RegisterServlet" method="post">
		  <fieldset>
		    <legend>Profile</legend>
			<p>
				First name: <input type="text" id="fname" name="fname" />
			</p>
			<p>
				Middle name: <input type="text" id="mname" name="mname"/>
			</p>
			<p>
				Last name: <input type="text" id="lname" name="lname" />
			</p>
			<p>
				Gender:
				<input type="radio" id="gender" name="gender" value="M" />Male
				<input type="radio" id="gender" name="gender" value="F" />Female
				<label for="gender" class="error" style="display:none;">Please select your gender</label>
			</p>
			<p>
				Date of birth: <input type="text" id="dob" name="dob" />
			</p>
			<p>
				Telephone: <input type="text" id="tel" name="tel" />
			</p>
			<p>
				Address 1: <input type="text" id="add1" name="add1" />
			</p>
			<p>
				Address 2: <input type="text" id="add2" name="add2" />
			</p>
			<p>
				Zip code: <input type="text" id="zip" name="zip" />
			</p>
			<p>
				Email: <input type="text" id="email" name="email" />
			</p>
			<p>
				User name: <input type="text" id="username" name="username" />
			</p>
			<p>
				Password: <input type="text" id="password" name="password" />
			</p>
            <p>
            <input type="checkbox" id="agree" name="agree"/>Accept Client Rules
            </p>
			<p>
				<input class="submit" type="submit" id="submit" value="Register" /> 
				<input
					type="button" id="cancel" value="Cancel"
					onClick="window.location.href='login.jsp'" />
			</p>
			</fieldset>
		</form>

	</div>


</body>
</html>