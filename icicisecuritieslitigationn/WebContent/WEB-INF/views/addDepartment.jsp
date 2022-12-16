<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Function/Department</h2>
					<a href="./listDepartments"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="department" autocomplete="off" onsubmit="return validateForm();" action="./saveDepartment" cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			
			
			<div class="f_form_content">
			<h2>Create Function</h2>
				<div class="col-md-12">
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Function/Department:</label>
							  <div class="col-sm-5">
							  <sf:input path="dept_name" cssClass="form-control" id='dept_name' /><i class="asterisk_input"></i>
								<sf:errors path="dept_name" cssClass="errorBlock"></sf:errors>	  </div>
						</div>
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Function/Department Short Name:</label>
							  <div class="col-sm-5">
							  <sf:input path="dept_short_name" cssClass="form-control" id='dept_short_name' /><i class="asterisk_input"></i>
								<sf:errors path="dept_short_name" cssClass="errorBlock"></sf:errors>	  </div>
						</div>
				</div>
				
				<div class="col-md-12 litigation_buttons">
				<br>
				
					<center>
					<input type="submit" value="Add" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listDepartments' " >Back</button> -->
					</center>
				</div>
				</div>
				</sf:form>		
			
			<div style="clear:both"></div>
			
</div>


<script type="text/javascript">

$(document).ready(function(){
	 
    $('#dept_name').click(function(){
        $('#dept_name').popover('destroy');
    });

}); 

var dept_status;
$("#dept_name").keyup(function(){ 
	  var dept_name = $("#dept_name").val();
	  var dept_id = 0;

		if(dept_name !='' && dept_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["dept_id"] = dept_id;
				items["dept_name"] = dept_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isDeptNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						if(result==0){
							dept_status = 0;
							$('#dept_name').popover('destroy');
						}else{
							dept_status = 1;
							$("#dept_name").attr("data-placement", "top");
							$("#dept_name").attr("data-content", "Function already exists..!!");
							$('#dept_name').popover('show');
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
});

var dept_short_status;
$("#dept_short_name").keyup(function(){ 
	  var dept_short_name = $("#dept_short_name").val();
	  var dept_id = 0;

		if(dept_short_name !='' && dept_short_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["dept_id"] = dept_id;
				items["dept_short_name"] = dept_short_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isDeptShortNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						if(result==0){
							dept_short_status = 0;
							$('#dept_short_name').popover('destroy');
						}else{
							dept_short_status = 1;
							$("#dept_short_name").attr("data-placement", "top");
							$("#dept_short_name").attr("data-content", "Unit short name is already exists..!!");
							$('#dept_short_name').popover('show');
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
});

$('#department').on('keyup keypress', function(e) {
	  var keyCode = e.keyCode || e.which;
	  if (keyCode === 13) { 
	    e.preventDefault();
	    return false;
	  }
	});

      function validateForm() {
            
             var oname = $('#dept_name').val();
             var short_name = $('#dept_short_name').val();			
             
             if (oname == null || oname == "") {
            	 
            	 $( "#dept_name" ).attr( "data-placement", "top" );
                 $( "#dept_name" ).attr( "data-content", "Function name required" );
                 $('#dept_name').popover('show');
				
                    return false;
                }else if(dept_status==1){
		        	   $( "#dept_name" ).attr( "data-placement", "top" );
			            $( "#dept_name" ).attr( "data-content", "Function already exists..!!" );
			            $('#dept_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#dept_name').popover('destroy');
                }
             if (!(isNaN(oname))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#dept_name" ).attr( "data-placement", "top" );
                 $( "#dept_name" ).attr( "data-content", "Function should be text" );
                 $('#dept_name').popover('show');
                 return false;
             }
             else
             {
             	$('#dept_name').popover('destroy');
             }
            
             if (!(oname.length>1)) {
               	// $("#oerr").innerHTML="this is invalid name ";
                    $( "#dept_name" ).attr( "data-placement", "top" );
                    $( "#dept_name" ).attr( "data-content", "Function should be valid length" );
                    $('#dept_name').popover('show');
                    return false;
                }
                else
                {
                	$('#dept_name').popover('destroy');
                }
		if (short_name == null || short_name == "") {
            	 
            	 $( "#dept_short_name" ).attr( "data-placement", "top" );
                 $( "#dept_short_name" ).attr( "data-content", "Unit short name required" );
                 $('#dept_short_name').popover('show');
				
                    return false;
                }else if(dept_short_status==1){
		        	   $( "#dept_short_name" ).attr( "data-placement", "top" );
			            $( "#dept_short_name" ).attr( "data-content", "Unit short name already exists..!!" );
			            $('#dept_short_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#dept_short_name').popover('destroy');
                }
             if (!(isNaN(short_name))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#dept_short_name" ).attr( "data-placement", "top" );
                 $( "#dept_short_name" ).attr( "data-content", "Unit short name should be text" );
                 $('#dept_short_name').popover('show');
                 return false;
             }
             else
             {
             	$('#dept_short_name').popover('destroy');
             }
             
             if (!(short_name.length == 3)) {
             	// $("#oerr").innerHTML="this is invalid name ";
                  $( "#dept_short_name" ).attr( "data-placement", "top" );
                  $( "#dept_short_name" ).attr( "data-content", "Unit short name should be maximum of 3 letters" );
                  $('#dept_short_name').popover('show');
                  return false;
              }
              else {
              	$('#dept_short_name').popover('destroy');
              }
             
         } 
    	  
    	
    	  
    	  
       </script>
     

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
