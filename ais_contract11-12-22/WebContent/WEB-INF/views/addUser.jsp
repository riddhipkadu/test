<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#054eff;font-size:24px;float:left;">Add User Information</h2>
					<a href="./listUsers"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form class="form-horizontal" commandName="user" autocomplete="off" role="form" onsubmit="return validateForm();"  id="addUserForm" action="./saveUser" method="post">
		
			<div class="f_form_content">
			<h2>Add User</h2>
			
				<div class="col-md-12">
					
					<div class="col-md-6">
						<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">First Name:</label>
							  <div class="col-sm-9">
							  <input  name="user_first_name" id="user_first_name"  class="form-control" placeholder="Enter First Name"/> <i class="asterisk_input"></i>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Last Name:</label>
							  <div class="col-sm-9">
							  <input  name="user_last_name" id="user_last_name" class="form-control" placeholder="Enter Last Name"/> <i class="asterisk_input"></i>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Mobile No.:</label>
							  <div class="col-sm-9">
							  <input name="user_mobile" id ="user_mobile" maxlength="12" class="form-control" placeholder="Enter Mobile Number"/>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Mail ID:</label>
							  <div class="col-sm-9">
 							<input  class="form-control" id="user_email" name="user_email"	placeholder="Enter email-id"/>	<i class="asterisk_input"></i>		  </div>
							</div>
							
							
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">User Name:</label>
							  <div class="col-sm-9">
							  <input name="user_username" id ="user_username" class="form-control" placeholder="Enter User Name"/> <i class="asterisk_input"></i>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">User Password:</label>
							  <div class="col-sm-9">
							  <input type="password"  name="user_userpassword" id ="user_userpassword" class="form-control" placeholder="Enter Password"/> <i class="asterisk_input"></i>
							  </div>
							</div>
							
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Confirm Password:</label>
							  <div class="col-sm-9">
							  <input type="password"  name="confirm_password" id ="confirm_password" class="form-control" placeholder="Enter Confirm Password"/> <i class="asterisk_input"></i>
							  </div>
							</div>
							
							</div>
							<div class="col-md-6">
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Entity:</label>
							  <div class="col-sm-9">
							  <select class="form-control" name="user_organization_id" id="user_organization_id"> 
							  	<option value="0">--Select--</option>
								<c:forEach items="${allOrganizations}" var="organization">
								<option value="${organization.orga_id}">${organization.orga_name}</option>
								</c:forEach>
							  </select><i class="asterisk_input"></i>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Location:</label>
							  <div class="col-sm-9">
							  	<select class="form-control" name="user_location_id"
								id="user_location_id">
								<option>--Select--</option>
								</select><i class="asterisk_input"></i>
							 <!--  	<select class="form-control" name="user_location_id" id="user_location_id"></select> -->
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Department:</label>
							  <div class="col-sm-9">
							  <select class="form-control" name="user_department_id" id="user_department_id">
							  <option>--Select--</option>
								</select><i class="asterisk_input"></i>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Reporting Manager:</label>
							  <div class="col-sm-9">
							  <select class="form-control" name="user_report_to" id="user_report_to">
							  <option value="0">--Select--</option>
							<%--   <c:forEach items="${allUser}" var="users">
									<option value="${users.user_id}">${users.user_first_name}
										${users.user_last_name}</option>
							  </c:forEach>  --%>
								</select>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Employee ID:</label>
							  <div class="col-sm-9">
								<input type="text" placeholder="Enter Employee ID" name="user_employee_id" id="user_employee_id" class="form-control"/>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Designation:</label>
							  <div class="col-sm-9">
							  <select class="form-control" name="user_designation_id" id="user_designation_id">
							  <option value="0">--Select--</option>
							<c:forEach items="${allDesignations}" var="designation">
								<option value="${designation.desi_id}">${designation.desi_name}</option>
							</c:forEach>
							</select>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Role:</label>
							  <div class="col-sm-9">
							   <select class="form-control" name="user_role_id" id="user_role_id">
							      <option value=0>--Select--</option>
							      <option value="1">Admin User</option>
							      <option value="2">System User</option>
							      <option value="7">Entity Head</option>
							     <!--  <option value="3">POC User</option>
							      <option value="4">Contract SPOC</option>
							      <option value="6">Litigation SPOC</option>  -->
							   </select><i class="asterisk_input"></i>
							  </div>
							</div>
							<div class="form-group">
							  <label class="control-label col-sm-3" for="sel1">Address:</label>
							  <div class="col-sm-9">
 							<textarea class="form-control"
 							 rows="4" cols="6" id="user_address" name="user_address"
							placeholder="Enter Address"></textarea>
							 </div>
							</div>
							
					</div>
							
				<div class="col-md-12 litigation_buttons">
				<br>
					<center><button type="submit"  class="btn btn-myPrimary">Add</button>
					<%-- <button type="button" class="btn btn-myDefault" onclick="window.location.href ='./listUsers' " >Back</button></center> --%>
				</div>
				
				</div>
				</div>
				</sf:form>		
			
			<div style="clear:both"></div>
			
</div>
</div>




<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script type="text/javascript">

	function getAllLocationForOrganization() {
		
	var user_orga_id = $("select#user_organization_id option:selected").attr('value');

		
		if (user_orga_id > 0) {
			items = {};
			items['tmap_orga_id'] = user_orga_id;

			var jsonString = JSON.stringify(items);

			
					$.ajax({
						type : "post",
						url : "./getAllLocationsForOrganization",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(locationlist) {
							//console.log(locationlist);

							var locationjson = $.parseJSON(locationlist);
							//console.log(locationjson[3]);
							$('#user_location_id').empty();
							$('#user_loca_row').removeAttr('hidden');
							$('#user_loca_row').show();
							$('#user_location_id').append(
									'<option value = 0>--Select--</option>');
							console.log("Length of keys:"
									+ Object.keys(locationjson).length);
							for (var i = 1; i <= Object.keys(locationjson).length; i++) {
								for (i in locationjson) {
									$('#user_location_id').append(
											'<option value = '+i +'>'
													+ locationjson[i]
													+ '</option>');
									console.log("This is from for loop"
											+ locationjson[i]);
								}

							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#user_location_id').html('<option value="0">--Select--</option>');
			$('#user_department_id').html('<option value="0">--Select--</option>');
			$('#user_loca_row').hide();
		}
	}

	function getAllDepartmentsByLocation() {
		var user_orga_id = $("select#user_organization_id option:selected").attr('value');
		var user_loca_id = $("select#user_location_id option:selected").attr('value');
		if (user_loca_id > 0) {
			items = {};
			items["tmap_orga_id"] = user_orga_id;
			items["tmap_loca_id"] = user_loca_id;

			var jsonString = JSON.stringify(items);

			
					$.ajax({
						type : "post",
						url : "./getAllDepartmentsForOrgAndLoc",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(departmentlist) {
							//console.log(locationlist);

							var departmentjson = $.parseJSON(departmentlist);
							//console.log(locationjson[3]);
							$('#user_department_id').empty();
							$('#user_dept_row').removeAttr('hidden');
							$('#user_dept_row').show();
							$('#user_department_id').append(
									'<option value = 0>--Select--</option>');
							console.log("Length of keys:"
									+ Object.keys(departmentjson).length);
							for (var i = 1; i <= Object.keys(departmentjson).length; i++) {
								for (i in departmentjson) {
									$('#user_department_id').append(
											'<option value = '+i +'>'
													+ departmentjson[i]
													+ '</option>');
									console
											.log("This is from for loop for departments"
													+ departmentjson[i]);
								}

							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#user_department_id').html('<option value="0">--Select--</option>');
		}
		
	}

	$("#user_department_id").change(function(){
		
		var user_orga_id = $("#user_organization_id").val();
		var user_loca_id = $("#user_location_id").val();
		var user_dept_id = $("#user_department_id").val();
		if (user_dept_id > 0 && user_loca_id > 0 && user_dept_id > 0) {
			
			var data = {};
			data['tmap_orga_id'] = user_orga_id;
			data['tmap_loca_id'] = user_loca_id;
			data['tmap_dept_id'] = user_dept_id;
			var jsonString = JSON.stringify(data);
			
					$.ajax({
						type : "post",
						url : "./getAllReportingUsersByOrganizationLocationDepartment",
						contentType : "application/json",
						dataType : "json",
						data : jsonString,
						cache : false,
						success : function(userList) {
							var data ="";
							data +="<option value= 0>--Select--</option>";
							
							$.each(userList,function(key,value){
								//console.log("In loop");
								data += "<option value="+value['user_id']+">"+value['user_name']+"</option>";
							});
							$("#user_report_to").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#user_report_to').html('<option value="0">--Select--</option>');
		}
	});
	
	
	$('#addUserForm').on('keyup keypress', function(e) {
		  var keyCode = e.keyCode || e.which;
		  if (keyCode === 13) { 
		    e.preventDefault();
		    return false;
		  }
		});
	 
	
	  function validateForm() {
		 /*  $('#user_first_name').val($.trim($('#user_first_name').val()));
		  $('#user_last_name').val($.trim($('#user_last_name').val()));
		  $('#user_mobile').val($.trim($('#user_mobile').val()));
		  $('#user_email').val($.trim($('#user_email').val())); */
        var fname = $('#user_first_name').val();
        var lname= $('#user_last_name').val();
        var contact=$('#user_mobile').val();
        var mail=$('#user_email').val();
        var oname = $('#user_organization_id').val();
        
        
        //// Validation for First name
        if (fname == 0 || fname=="") {
       	 
       	 $( "#user_first_name" ).attr( "data-placement", "top" );
            $( "#user_first_name" ).attr( "data-content", "First Name required" );
            $( "#user_first_name" ).focus();
            $('#user_first_name').popover('show');
				
               return false;
           }
           else
           {
           	$('#user_first_name').popover('destroy');
           }
        if (!(isNaN(fname))) {
        	
             $( "#user_first_name" ).attr( "data-placement", "top" );
             $( "#user_first_name" ).attr( "data-content", "First Name should be text" );
             $( "#user_first_name" ).focus();
             $('#user_first_name').popover('show');
             return false;
         }
         else
         {
         	$('#user_first_name').popover('destroy');
         }
        
        if (!(fname.length>2)) {
        	// $("#oerr").innerHTML="this is invalid name ";
             $( "#user_first_name" ).attr( "data-placement", "top" );
             $( "#user_first_name" ).attr( "data-content", "First Name should be valid length" );
             $( "#user_first_name" ).focus();
             $('#user_first_name').popover('show');
             return false;
         }
         else
         {
         	$('#user_first_name').popover('destroy');
         }
        
        
        ///////////// validate last name /////////////
        
         if (lname == 0 || lname=="") {
       	
       	 $( "#user_last_name" ).attr( "data-placement", "top" );
            $( "#user_last_name" ).attr( "data-content", "Last Name required" );
            $( "#user_last_name" ).focus();
            $('#user_last_name').popover('show');
				
               return false;
           }
           else
           {
           	$('#user_last_name').popover('destroy');
           }
        if (!(isNaN(lname))) {
        	// $("#oerr").innerHTML="this is invalid name ";
             $( "#user_last_name" ).attr( "data-placement", "top" );
             $( "#user_last_name" ).attr( "data-content", "Last Name should be text" );
             $( "#user_last_name" ).focus();
             $('#user_last_name').popover('show');
             return false;
         }
         else
         {
         	$('#user_last_name').popover('destroy');
         }
        
        if (!(lname.length>2)) {
        	// $("#oerr").innerHTML="this is invalid name ";
             $( "#user_last_name" ).attr( "data-placement", "top" );
             $( "#user_last_name" ).attr( "data-content", "Last Name should have valid length" );
             $( "#user_last_name" ).focus();
             $('#user_last_name').popover('show');
             return false;
         }
         else
         {
         	$('#user_last_name').popover('destroy');
         }
        
       ////////////// validate mobile no  user_phone_number/////////
        
       /*  if (contact == 0 || contact=="") {
       	 
       	 $( "#user_mobile" ).attr( "data-placement", "top" );
            $( "#user_mobile" ).attr( "data-content", "Mobile No required" );
            $('#user_mobile').popover('show');
				
               return false;
           }
           else
           {
           	$('#user_mobile').popover('destroy');
           }
       
       
       
        if (isNaN(contact)) {
        	
        	 $( "#user_mobile" ).attr( "data-placement", "top" );
             $( "#user_mobile" ).attr( "data-content", "Mobile No should be numeric" );
             $('#user_mobile').popover('show');
             return false;
         }
         else
         {
         	$('#user_mobile').popover('destroy');
         }
        
        if (!(contact.length>9)) {
        	// $("#oerr").innerHTML="this is invalid name ";
             $( "#user_mobile" ).attr( "data-placement", "top" );
             $( "#user_mobile" ).attr( "data-content", "Mobile should have valid length" );
             $('#user_mobile').popover('show');
             return false;
         }
         else
         {
         	$('#user_mobile').popover('destroy');
         }
        
        */
        //////////////// Mail Validation //////////////////
        if (mail === null || mail === "") {
            $("#user_email").attr("data-placement", "top");
            $("#user_email").attr("data-content", "Email must be filled out");
            $( "#user_email" ).focus();
            $('#user_email').popover('show');

            return false;
        }else if(email_id_status ==1){
			$("#user_email").attr("data-placement", "top");
			$("#user_email").attr("data-content", "Email Id already exists");
			 $( "#user_email" ).focus();
			$('#user_email').popover('show');
			return false; 
        } else
        {
            $('#user_email').popover('destroy');
        }
        /// validate email format
        var atpos = mail.indexOf("@");
        var dotpos = mail.lastIndexOf(".");
        if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= mail.length)
        {
            $("#user_email").attr("data-placement", "top");
            $("#user_email").attr("data-content", "Not a valid e-mail address");
            $( "#user_email" ).focus();
            $('#user_email').popover('show');
            // alert("Not a valid e-mail address");
            return false;
        } else
        {
            $('#user_email').popover('destroy');
        } 
        
   //// validate username  ////
		  if ($('#user_username').val() == 0  || $('#user_username').val()  =="") {
		       	 
		       	 $( "#user_username" ).attr( "data-placement", "top" );
		            $( "#user_username" ).attr( "data-content", "Username required" );
		            $( "#user_username" ).focus();
		            $('#user_username').popover('show');
						
		               return false;
		           }else if(user_name_status==1){
		        	   $( "#user_username" ).attr( "data-placement", "top" );
			            $( "#user_username" ).attr( "data-content", "Username already exists" );
			            $( "#user_username" ).focus();
			            $('#user_username').popover('show');
			            return false;
		           }
		           else
		           {
		           	$('#user_username').popover('destroy');
		           }
		        if (!(isNaN($('#user_username').val() ))) {
		        	
		             $( "#user_username" ).attr( "data-placement", "top" );
		             $( "#user_username" ).attr( "data-content", "Username should be text" );
		             $( "#user_username" ).focus();
		             $('#user_username').popover('show');
		             return false;
		         }
		         else
		         {
		         	$('#user_username').popover('destroy');
		         }
		        
		        if (!($('#user_username').val().length>2)) {
		        	// $("#oerr").innerHTML="this is invalid name ";
		             $( "#user_username" ).attr( "data-placement", "top" );
		             $( "#user_username" ).attr( "data-content", "Username should be valid length" );
		             $( "#user_username" ).focus();
		             $('#user_username').popover('show');
		             return false;
		         }
		         else
		         {
		         	$('#user_first_name').popover('destroy');
		         }
		/////////////Confirm Password ///////// 
			if ($('#user_userpassword').val() == 0  || $('#user_userpassword').val()  =="") {
		       	 
		       	 $( "#user_userpassword" ).attr( "data-placement", "top" );
		            $( "#user_userpassword" ).attr( "data-content", "Password is required and at least 6 characters long" );
		            $( "#user_userpassword" ).focus();
		            $('#user_userpassword').popover('show');
						
		               return false;
		           }
		           else
		           {
		           	$('#user_userpassword').popover('destroy');
		           }
				
					if (($('#user_userpassword').val()).match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$/))
				  	{
				        
				    } else {
				    	$( "#user_userpassword" ).attr( "data-placement", "top" );
			             $( "#user_userpassword" ).attr( "data-content", "password should contain at least one lowercase, one uppercase and one digit" );
			             $( "#user_userpassword" ).focus();
			             $('#user_userpassword').popover('show');
			             return false;
				    }
					
		       

        
        // validate organization drop down
        
          if (oname == 0) {
            	 
            	 $( "#user_organization_id" ).attr( "data-placement", "top" );
                 $( "#user_organization_id" ).attr( "data-content", " Select Entity " );
                 $( "#user_organization_id" ).focus();
                 $('#user_organization_id').popover('show');
				
                    return false;
                }
              
             else
             {
             	$('#user_organization_id').popover('destroy');
             }
        
        /// validate unit dropdown
          if ($( "#user_location_id" ).val() == 0) {
         	 
         		$( "#user_location_id" ).attr( "data-placement", "top" );
             	$( "#user_location_id" ).attr( "data-content", " Select Unit" );
             	 $( "#user_location_id" ).focus();
              	$('#user_location_id').popover('show');
				
                 return false;
             }
           
          else
          {
          	$('#user_location_id').popover('destroy');
          }
          
        
	// Validate Function dropdown
		if ($("#user_department_id").val() == 0) {

			$("#user_department_id").attr("data-placement", "top");
			$("#user_department_id").attr("data-content", " Select Function ");
		    $( "#user_department_id" ).focus();
			$('#user_department_id').popover('show');

			return false;
		}

		else {
			$('#user_department_id').popover('destroy');
		}

	//Validation for Reporting manager id
/* 	if ($("#user_report_to").val() == 0) {

			$("#user_report_to").attr("data-placement", "top");
			$("#user_report_to").attr("data-content", " Select Reporting Manager ");
			$('#user_report_to').popover('show');

			return false;
		}

		else {
			$('#user_report_to').popover('destroy');
		} */

	
		// Validation for employee id
/* 		if ($('#user_employee_id').val() == 0 || $('#user_employee_id').val() == "") {

			$("#user_employee_id").attr("data-placement", "top");
			$("#user_employee_id").attr("data-content", "Employee id required");
			$('#user_employee_id').popover('show');

			return false;
		} else {
			$('#user_employee_id').popover('destroy');
		}
 */		 /*  if (!(isNaN(fname))) {
		 	
		      $( "#user_employee_id" ).attr( "data-placement", "top" );
		      $( "#user_employee_id" ).attr( "data-content", "First Name should be text" );
		      $('#user_employee_id').popover('show');
		      return false;
		  }
		  else
		  {
		  	$('#user_employee_id').popover('destroy');
		  }  */

	/* 	if (!(($('#user_employee_id').val()).length > 2)) {
			// $("#oerr").innerHTML="this is invalid name ";
			$("#user_employee_id").attr("data-placement", "top");
			$("#user_employee_id").attr("data-content",
					"Employee id should be valid length");
			$('#user_employee_id').popover('show');
			return false;
		}else if(emp_status ==1){
			$("#user_employee_id").attr("data-placement", "top");
			$("#user_employee_id").attr("data-content", "Employee Id already exists");
			$('#user_employee_id').popover('show');
			return false;
		}else{
			$('#user_employee_id').popover('destroy');
		}
	 */	  
		  
		  
		  /// validate Designation
	/* 	  if ( $( "#user_designation_id" ).val() == 0) {
         	 
         	 $( "#user_designation_id" ).attr( "data-placement", "top" );
              $( "#user_designation_id" ).attr( "data-content", " Select Designation " );
              $('#user_designation_id').popover('show');
				
                 return false;
             }
           
          else
          {
          	$('#user_designation_id').popover('destroy');
          }
 */		  
		  /// validate role
		  
		  if ( $( "#user_role_id" ).val() == 0) {
            	 
            	 $( "#user_role_id" ).attr( "data-placement", "top" );
                 $( "#user_role_id" ).attr( "data-content", " Select Role " );
                 $( "#user_role_id" ).focus();
                 $('#user_role_id').popover('show');
				
                    return false;
                }
              
             else
             {
             	$('#user_role_id').popover('destroy');
             }
		//  validate addresss textarea..
	        
	        
	     /*    if ( $('#user_address').val() == 0 || $('#user_address').val()=="") {
	        	 
	        	 $( "#user_address" ).attr( "data-placement", "top" );
	             $( "#user_address" ).attr( "data-content", "Address required" );
	             $('#user_address').popover('show');
	 				
	                return false;
	            }
	            else
	            {
	            	$('#user_address').popover('destroy');
	            }
	          if (!(isNaN($('#user_address').val()))) {
	         	
	              $( "#user_address" ).attr( "data-placement", "top" );
	              $( "#user_address" ).attr( "data-content", "Address should be text" );
	              $('#user_address').popover('show');
	              return false;
	          }
	          else
	          {
	          	$('#user_address').popover('destroy');
	          } 
	         
	         if (!(($('#user_address').val()).length>2)) {
	         	// $("#oerr").innerHTML="this is invalid name ";
	              $( "#user_address" ).attr( "data-placement", "top" );
	              $( "#user_address" ).attr( "data-content", "Address should be valid length" );
	              $('#user_address').popover('show');
	              return false;
	          }
	          else
	          {
	          	$('#user_address').popover('destroy');
	          }
	 */
	}

	$(document).ready(function() {

		
		$("#user_organization_id").on('change', getAllLocationForOrganization);
		$("#user_location_id").on('change', getAllDepartmentsByLocation);

		// validation destroy popups
		$('#user_userpassword').click(function() {
			$('#user_userpassword').popover('destroy');
		});
		
		$('#confirm_password').click(function() {
			$('#confirm_password').popover('destroy');
		});
		
		$('#user_username').click(function() {
			$('#user_username').popover('destroy');
		});
		
		$('#user_first_name').click(function() {
			$('#user_first_name').popover('destroy');
		});
		
		$('#user_last_name').click(function() {
			$('#user_last_name').popover('destroy');
		});

		$('#user_email').click(function() {
			$('#user_email').popover('destroy');
		});

		$('#user_mobile').click(function() {
			$('#user_mobile').popover('destroy');
		});

		$('#user_organization_id').click(function() {
			$('#user_organization_id').popover('destroy');
		});

		$('#user_location_id').click(function() {
			$('#user_location_id').popover('destroy');
		});

		$('#user_department_id').click(function() {
			$('#user_department_id').popover('destroy');
		});

		$('#user_employee_id').click(function() {
			$('#user_employee_id').popover('destroy');
		});
		$('#user_address').click(function() {
			$('#user_address').popover('destroy');
		});
		
		$('#user_role_id').click(function() {
			$('#user_role_id').popover('destroy');
		});
		/* $('#user_designation_id').click(function() {
			$('#user_designation_id').popover('destroy');
		});
		 */
		
		 $( "#confirm_password" ).keyup(function(){ 
			 
			 if ($('#user_userpassword').val()!=$('#confirm_password').val())
    	{
        	 $( "#confirm_password" ).attr( "data-placement", "top" );
             $( "#confirm_password" ).attr( "data-content", "Password and Confirm Password should be same" );
             $('#confirm_password').popover('show');
             return false;
    	}
    else
    	{
    		$('#confirm_password').popover('destroy');
    	}

		});
	});
	var emp_status;
	  $("#user_employee_id").keyup(function(){ 
		  //$('#user_employee_id').val($.trim($('#user_employee_id').val()));
		  var emp_id = $("#user_employee_id").val();
		  var user_id = 0;
	 
			if(emp_id !='' && emp_id!=null ){ // && !typeof emp_id === 'undefined'
				
				    items = {};
					items["user_id"] = user_id;
					items["emp_id"] = emp_id;

					var jsonString = JSON.stringify(items);
					$.ajax({
						type : "post",
						url : "./checkExistEmployeeId",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(res) {
							if(res==0){
								emp_status = 0;
								$('#user_employee_id').popover('destroy');
							}else{
								emp_status = 1;
								$("#user_employee_id").attr("data-placement", "top");
								$("#user_employee_id").attr("data-content", "Employee Id already exists");
								$('#user_employee_id').popover('show');
							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
			}
	  });
	  
	  var email_id_status;
	  $("#user_email").keyup(function(){ 
		  //$('#user_email').val($.trim($('#user_email').val()));
		  var email_id = $("#user_email").val();
		  var user_id = 0;
	 
			if(email_id !='' && email_id!=null ){ // && !typeof emp_id === 'undefined'
				
				    items = {};
					items["user_id"] = user_id;
					items["email_id"] = email_id;

					var jsonString = JSON.stringify(items);
					$.ajax({
						type : "post",
						url : "./isEmailIdExist",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(result) {
							if(result==0){
								email_id_status = 0;
								$('#user_email').popover('destroy');
							}else{
								email_id_status = 1;
								$("#user_email").attr("data-placement", "top");
								$("#user_email").attr("data-content", "Email Id already exists");
								$('#user_email').popover('show');
							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
			}
	  });
	  
	  var user_name_status;
	  $("#user_username").keyup(function(){ 
		 // $('#user_username').val($.trim($('#user_username').val()));
		  var user_name = $("#user_username").val();
		  var user_id = 0;
	 
			if(user_name !='' && user_name!=null ){ // && !typeof emp_id === 'undefined'
				
				    items = {};
					items["user_id"] = user_id;
					items["user_name"] = user_name;

					var jsonString = JSON.stringify(items);
					$.ajax({
						type : "post",
						url : "./isUserNameExist",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(result) {
							if(result==0){
								user_name_status = 0;
								$('#user_username').popover('destroy');
							}else{
								user_name_status = 1;
								$("#user_username").attr("data-placement", "top");
								$("#user_username").attr("data-content", "Username already exists");
								$('#user_username').popover('show');
							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
			}
	  });
</script>