	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">

		<!--heading text-->
		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Change
					Password</h2>
				<a href="./myAccount"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<form role="change Password" method="POST" autocomplete="off" onsubmit="return validateForm()"
			Action="./ChangeNewPassword">
			<div class="f_form_content">
				<h2>Search Assigned Compliance</h2>
				<div class="row">
					<div class="form-group">
						<div class="col-md-6">
							<label>Old Password:</label> <input id="oldPassword" 
								class="form-control" type="password" name="oldPassword" />
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="form-group">					
						<div class="col-md-6">	
						<hr/>				
						<p><em>Note : New Password should be minimun 6 characters. (atleast one Uppercase,one lowercase letter and one digit )</em></p>	
							<label>New Password:</label> <input id="newPassword" 
								class="form-control" type="password" name="newPassword" />
								
						</div>
					</div>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-md-6">
							<label>Confirm Password:</label> <input id="confPassword" 
								class="form-control" type="password" name="confPassword" />
						</div>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="form-group">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<input class="btn btn-success" type="submit" name="save"
								value="change Password" /> &nbsp;<input class="btn btn-danger"
								type="reset" name="reset" value="Reset Password" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>

	<script type="text/javascript">
	
	/// Start Validation Function..
	function validateForm()
	{
		var opwd=$("#oldPassword").val();
		var cpwd=$("#confPassword").val();
		var npwd=$('#newPassword').val();
		
		//Remove white spaces
		if(opwd.length > $("#oldPassword").val().length){
			$( "#oldPassword" ).attr( "data-placement", "top" );
            $( "#oldPassword" ).attr( "data-content", "Warning! Old password should not be contain white/blank spaces");
            $('#oldPassword').popover('show');
        	$('#oldPassword').val($.trim(opwd));
            return false;
		}
		 
		if(npwd.length > $("#newPassword").val().length){
			$( "#newPassword" ).attr( "data-placement", "top" );
            $( "#newPassword" ).attr( "data-content", "Warning! New password should not be contain white/blank spaces");
            $('#newPassword').popover('show');
        	$('#newPassword').val($.trim(npwd));
            return false;
		}
		
		if(cpwd.length > $("#confPassword").val().length){
			$( "#confPassword" ).attr( "data-placement", "top" );
            $( "#confPassword" ).attr( "data-content", "Warning! Confirm password should not be contain white/blank spaces");
            $('#confPassword').popover('show');
            $("#confPassword").val($.trim(cpwd));
            return false;
		}
		
		if(opwd=="" || opwd==null)
			{

	       		$( "#oldPassword" ).attr( "data-placement", "top" );
	            $( "#oldPassword" ).attr( "data-content", "Old Password Required.. " );
	            $('#oldPassword').popover('show');
					
	               return false;
	           }
	           else
	           {
	        	   $.ajax({
	   				type : "POST",
	   				url : "./getOriginalPassword",
	   				contentType : "application/json",
	   				dataType : "html",
	   				cache : false,
	   				success : function(res) {
	   					var originalOldPass;
	   					originalOldPass = res;
	   					if (opwd != originalOldPass) {
	   						
	   						 $("#oldPassword" ).attr( "data-placement", "top" );
	   				         $("#oldPassword" ).attr( "data-content", "Old Password is not Correct..!" );
	   				         $('#oldPassword').popover('show');
	   						 $("#oldPassword").val("");
	   						 $("#oldPassword").focus();
	   						$('#newPassword').popover('destroy');
	   						 return false;
	   					}else{
	   						$("#newPassword").focus();
	   						$('#oldPassword').popover('destroy');
	   						
	   					}
	   				}
	   			});
	           	
	           }
		
				/////////////Confirm Password ///////// 
		if (npwd == "") {
	       	 
	       	 $( "#newPassword" ).attr( "data-placement", "top" );
	         $( "#newPassword" ).attr( "data-content", "Password is required and atleast 6 characters long" );
	         $('#newPassword').popover('show');
					
	               return false;
	           }
	           else
	           {
	        	   if (opwd == npwd) {
						 $( "#newPassword" ).attr( "data-placement", "top" );
				         $( "#newPassword" ).attr( "data-content", "Old and New Passworld sholud not be same..!" );
				         $('#newPassword').popover('show');
				         return false;
						$("#newPassword").focus();
					}else
						{
						 $('#newPassword').popover('destroy');
						}
	           	
	           }
			
				if (($('#newPassword').val()).match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$/))
			  	{
			        
			    } else {
			    	$( "#newPassword" ).attr( "data-placement", "top" );
		             $( "#newPassword" ).attr( "data-content", "password should contain at least one lowercase, one uppercase and one digit and atleast 6 characters long." );
		             $('#newPassword').popover('show');
		             return false;
			    }
				
				
				if(cpwd=="" || cpwd==null)
				{

		       	 $( "#confPassword" ).attr( "data-placement", "top" );
		            $( "#confPassword" ).attr( "data-content", "Confirm Password Required..!" );
		            $('#confPassword').popover('show');
						
		               return false;
		           }
		           else
		           {
		           	$('#confPassword').popover('destroy');
		           }
				if(npwd!==cpwd)
					{
					 	$( "#confPassword" ).attr( "data-placement", "top" );
			            $( "#confPassword" ).attr( "data-content", "Confirm and New Password sholud be same..!" );
			            $('#confPassword').popover('show');
			            return false;
					
					}
				
					
	       //return true;
	}
	/// End of Validation Function 
	
		$(document).ready(function() {
			
			//$("#confPassword").focusout(checkConfAndNewpassword);

			/// Start - validation popover destroy on click 
			$('#oldPassword').click(function() {
				$('#oldPassword').popover('destroy');
			});
			$('#newPassword').click(function() {
				$('#newPassword').popover('destroy');
			});
			$('#confPassword').click(function() {
				$('#confPassword').popover('destroy');
			});
			///End - validation popover destroy
			
			var result = "${result}";
			if(result !=""){
			if (result == 1) {
				bootbox.dialog({
					message : "Password successfully Changed!",
					title : "Success",
					buttons : {
						success : {
							label : "OK!",
							className : "btn-success"
						}
					}
				});
			} else if (result == 0 ) {
				bootbox.dialog({
					message : "Password not Changed!",
					title : "Wrong",
					buttons : {
						danger : {
							label : "OK!",
							className : "btn-success"
						}
					}
				});
			 }
			}
		});
		

		
	</script>
	
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
	</div>