<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>LexCare :: Litigation Management Tool</title>
<meta charset="UTF-8">
<meta name="keywords" content="">
<meta name="discription" content="">
<meta name="author" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- IE Compatible  -->
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/animate.css">
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootbox.min.js"></script>
</head>
<body class="login_body">
	<div class="container">
	<!-- Incorrect Username -->
				<div class="modal fade" id="invalidUsername" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="background-color: #e26d1c;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">
									<i class="fa fa-exclamation-triangle"></i> &nbsp;Error
								</h4>
							</div>
							<div class="modal-body">
								<p>Incorrect Username...!</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>
				
				<!-- Incorrect Password -->
				<div class="modal fade" id="invalidPassword" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header" style="background-color: #e26d1c;">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">
									<i class="fa fa-exclamation-triangle"></i> &nbsp;Error
								</h4>
							</div>
							<div class="modal-body">
								<p>Incorrect Password...!</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>
		<div class="col-md-3"></div>
		<div class="col-md-5 animated flipInY" style="margin-left: 50px;">
			<div class="login">
				<center>
					<img src="images/asahiglass.jpg" height="100px" width="300px">
				</center>
				<div class="devider"></div>

				

				<sf:form action="./authenticateUser" method="post" onsubmit="return validateForm();"	class="form-horizontal" role="form">
					<div class="form-group">
						
						<div class="col-sm-9">
						<label class="control-label col-sm-3" for="email">Username:</label>
							<input type="text" class="form-control" id="user_username"
								name="user_username" placeholder="Enter Username" required>
						</div>
					</div>
					<div class="form-group">
						
						<div class="col-sm-9">
						<label class="control-label col-sm-3" for="pwd">Password:</label>
							<input type="password" class="form-control"
								name="user_userpassword" id="user_userpassword"
								placeholder="Enter password" required>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4">
							<div class="checkbox">
								<label><input type="checkbox" id="remember_me" name="remember_me"> Remember me</label>

							</div>
						</div>
						<div class="col-sm-offset-2 col-sm-4">
							<div class="checkbox">

								<a class="forgot_pass" id="forgot_pass"> Forgot Password</a>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-12">
							<center>
								<button type="submit" class="btn btn-default">Sign in</button>
							</center>
						</div>
					</div>

				</sf:form>
			</div>

		</div>

		<div style="clear: both"></div>
		<div class="copy_right animated slideInDown">
			All Rights Reserved - <a href="http://www.lexcareglobal.com"
				class="links">LexCare Global Consultants Pvt Ltd</a><br>
			<a href="#" class="links">Disclaimer</a>
		</div>
		<div class="logo_lexcare">
			<center>
				<img src="images/Newlogo-3.jpg" height="50" width="150">
			</center>
		</div>
		<div class="col-md-4"></div>
	</div>


	<script type="text/javascript">
	
		$(document)
				.ready(
						function() {
							   checkCookie(); //Onload Check cookies
							   
							if ('${loginErrorType}' == "InvalidUsername") {
								$("#invalidUsername").modal('show');
							}

							if ('${loginErrorType}' == "InvalidPassword") {
								$("#invalidPassword").modal('show');
							}

							if ("${ValidationFalied}" == "Failed") {

								bootbox
										.dialog({
											title : "<strong style='color:red;'>Validation Failed !</strong>",
											message : "Please make sure your username and password are correct."
										});
							}

							if ("${ValidationFalied}" == "Success") {
								bootbox
										.dialog({
											title : "<strong style='color:green;'>Reset Password</strong>",
											message : "Your password is reset <br/> Please check your inbox"
										});
							}

							if ("${ValidationFalied}" == "alreadyExist") {
								bootbox
										.dialog({
											title : "<strong style='color:Orange;'>Reset Password</strong>",
											message : "Your password has been already reset, Please check your inbox."
										});
							}
						});

		$("#forgot_pass").click(function () {

			var htmlOutputForm = '<style>#subTaskId{display:none;} table tr td {text-align: left;padding: 5px;}</style><div class="row">';
			htmlOutputForm += '<div class="col-md-12">';
			htmlOutputForm += '<form role="ChangePassword" action="./resetUserPassword" Method ="POST" ><table width="100%">';

			htmlOutputForm += '<tr><td><label class="control-label col-sm-12" for="sel1">Username :</label></td>';
			htmlOutputForm += '<td><div class="form-group"><div class="col-sm-12"><input type="text" class="form-control" id="user_reset_password_username" name="user_reset_password_username" placeholder="Enter your username" required></div></div></td></tr>';

			htmlOutputForm += '<tr><td><label class="control-label col-sm-12" for="sel1">Email Id :</label></td>';
			htmlOutputForm += '<td><div class="form-group"><div class="col-sm-12"><input type="text" class="form-control" id="user_reset_password_mail_id" name="user_reset_password_mail_id" placeholder="Enter your mail id" required></div></div></td></tr>';

			htmlOutputForm += '<tr><td></td><td><input class="btn btn-success" type="Submit" name="ResetPassword" value="Submit" /></td></tr>';
			htmlOutputForm += '</table></form></div>';

			bootbox.dialog({
				title : "<strong>Reset Password</strong>",
				message : htmlOutputForm
			});
		});
		
		function validateForm(){
			var user_username 		= $("#user_username").val();
			var user_userpassword 	= $("#user_userpassword").val();
			var remember_me         = document.getElementById("remember_me").checked;
			if (user_username == 0) {
	        	 
	     		$( "#user_username" ).attr( "data-placement", "top" );
	     		$( "#user_username" ).attr( "data-content", "Please enter Username.");
	     		$('#user_username').popover('show');
		
	            return false;
	        }
	    	 else
	         {
	         	$('#user_username').popover('destroy');
	        }
			if (user_userpassword == 0) {
	        	 
	     		$( "#user_userpassword" ).attr( "data-placement", "top" );
	     		$( "#user_userpassword" ).attr( "data-content", "Please enter Username.");
	     		$('#user_userpassword').popover('show');
		
	            return false;
	        }
	    	 else
	         {
	         	$('#user_userpassword').popover('destroy');
	        }
			if(remember_me==true){
				    var d = new Date();
				    d.setTime(d.getTime() + (30 * 24 * 60 * 60 * 1000));
				    var expires = "expires="+d.toUTCString();
				    document.cookie = "username="+user_username+"; path=/"; 
				    document.cookie = "password="+user_userpassword+"; path=/";
				    
				
			}else{
				document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
				document.cookie = "password=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
				
			}
			return true;
		}
		
		function checkCookie() {
		    var username = getCookie("username");
		    var password = getCookie("password");
		    if (username != "") {
		    	$("#user_username").val(username);
		    	$("#user_userpassword").val(password);
		    	document.getElementById("remember_me").checked = true;
		    } else {
		    	document.getElementById("remember_me").checked = false;
		        //alert("No Cookies");
		    }
		}
		
		function getCookie(cname) {
		    var name = cname + "=";
		    var decodedCookie = decodeURIComponent(document.cookie);
		    var ca = decodedCookie.split(';');
		    for(var i = 0; i < ca.length; i++) {
		        var c = ca[i];
		        while (c.charAt(0) == ' ') {
		            c = c.substring(1);
		        }
		        if (c.indexOf(name) == 0) {
		            return c.substring(name.length, c.length);
		        }
		    }
		    return "";
		}
	</script>
</body>
</html>