<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Entity</h2>
					<a href="./listOrganizations"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	
	<sf:form commandName="organization" autocomplete="off" method="post" 
				action="./updateOrganization" cssClass="form-horizontal" onsubmit="return validateForm()">
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>

			<div class="col-lg-12">
				<center>
					<h2 style="color: red;">&#42; Mandatory Fields</h2>
				</center>
			</div>

			<div class="f_form_content">
			<h2>Update Entity</h2>
			
				<div class="col-md-12">
					<div class="col-md-6" style="display : none;">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity:</label>
							<div class="col-sm-8">
								<sf:input path="orga_id" cssClass="form-control" />
								<sf:errors path="orga_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
				
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Parent Entity:</label>
							<div class="col-sm-8">
								<sf:select path="orga_parent_id" items="${organization_list}" cssClass="form-control" />
							</div>
						</div>
					</div>
						   
							  
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity:</label>
							<div class="col-sm-8">
								<sf:input path="orga_name" cssClass="form-control" /><i class="asterisk_input"></i>
								<sf:errors path="orga_name" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity short name:</label>
							<div class="col-sm-8">
								<sf:input path="orga_short_name" cssClass="form-control" /><i class="asterisk_input"></i>
								<sf:errors path="orga_short_name" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					
				</div>
				
				
				<div class="col-md-12 litigation_buttons">
				<br>
				<center>
					<input type="submit" value="Update"  class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listOrganizations' " >Back</button> -->
				</center>
				
				</div>
			</div>
		</sf:form>	
			
			<div style="clear:both"></div>
	</div>
	



<script type="text/javascript">

$(document).ready(function(){
	  
	   
    $('#orga_name').click(function(){
        $('#orga_name').popover('destroy');
    });


}); 

var orga_status;
$("#orga_name").keyup(function(){ 
	  var orga_name = $("#orga_name").val();
	  var orga_id = $("#orga_id").val();

		if(orga_name !='' && orga_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["orga_id"] = orga_id;
				items["orga_name"] = orga_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isOrgaNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						if(result==0){
							orga_status = 0;
							$('#orga_name').popover('destroy');
						}else{
							orga_status = 1;
							$("#orga_name").attr("data-placement", "top");
							$("#orga_name").attr("data-content", "Entity already exist..!!");
							$('#orga_name').popover('show');
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
});

var orga_short_status;
$("#orga_short_name").keyup(function(){ 
	  var orga_short_name = $("#orga_short_name").val();
	  var orga_id = 0;

		if(orga_short_name !='' && orga_short_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["orga_id"] = orga_id;
				items["orga_short_name"] = orga_short_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isOrgaShortNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						if(result==0){
							orga_short_status = 0;
							$('#orga_short_name').popover('destroy');
						}else{
							orga_short_status = 1;
							$("#orga_short_name").attr("data-placement", "top");
							$("#orga_short_name").attr("data-content", "Entity short name is already exists..!!");
							$('#orga_short_name').popover('show');
						}
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
});

$('#organization').on('keyup keypress', function(e) {
	  var keyCode = e.keyCode || e.which;
	  if (keyCode === 13) { 
	    e.preventDefault();
	    return false;
	  }
	});

      function validateForm() {
            
             var oname = $('#orga_name').val();
             var short_name = $('#orga_short_name').val();
             
             if (oname == null || oname == "") {
            	 
            	 $( "#orga_name" ).attr( "data-placement", "top" );
                 $( "#orga_name" ).attr( "data-content", "Entity name required" );
                 $('#orga_name').popover('show');
				
                    return false;
                }else if(orga_status==1){
		        	   $( "#orga_name" ).attr( "data-placement", "top" );
			            $( "#orga_name" ).attr( "data-content", "Entity already exist..!!" );
			            $('#orga_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#orga_name').popover('destroy');
                }
             if (!(isNaN(oname))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#orga_name" ).attr( "data-placement", "top" );
                 $( "#orga_name" ).attr( "data-content", "Entity should be text" );
                 $('#orga_name').popover('show');
                 return false;
             }
             else
             {
             	$('#orga_name').popover('destroy');
             }
             
             if (!(oname.length>2)) {
             	// $("#oerr").innerHTML="this is invalid name ";
                  $( "#orga_name" ).attr( "data-placement", "top" );
                  $( "#orga_name" ).attr( "data-content", "Entity should be valid length" );
                  $('#orga_name').popover('show');
                  return false;
              }
              else
              {
              	$('#orga_name').popover('destroy');
              }
             
if (short_name == null || short_name == "") {
            	 
            	 $( "#orga_short_name" ).attr( "data-placement", "top" );
                 $( "#orga_short_name" ).attr( "data-content", "Entity short name required" );
                 $('#orga_short_name').popover('show');
				
                    return false;
                }else if(orga_short_status==1){
		        	   $( "#orga_short_name" ).attr( "data-placement", "top" );
			            $( "#orga_short_name" ).attr( "data-content", "Entity short name already exists..!!" );
			            $('#orga_short_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#orga_short_name').popover('destroy');
                }
             if (!(isNaN(short_name))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#orga_short_name" ).attr( "data-placement", "top" );
                 $( "#orga_short_name" ).attr( "data-content", "Entity short name should be text" );
                 $('#orga_short_name').popover('show');
                 return false;
             }
             else
             {
             	$('#orga_short_name').popover('destroy');
             }
             
             if (!(short_name.length == 3)) {
             	// $("#oerr").innerHTML="this is invalid name ";
                  $( "#orga_short_name" ).attr( "data-placement", "top" );
                  $( "#orga_short_name" ).attr( "data-content", "Entity short name should be maximum of 3 letters" );
                  $('#orga_short_name').popover('show');
                  return false;
              }
              else {
              	$('#orga_short_name').popover('destroy');
              }
         } 
    

</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>