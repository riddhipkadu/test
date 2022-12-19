<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Unit/Location</h2>
					<a href="./listLocations"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="location" autocomplete="off" cssClass="form-horizontal" onsubmit="return validateForm();"
				action="./saveLocation" >
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Create Unit</h2>
				<div class="col-md-12">
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Unit/Location:</label>
								<div class="col-sm-5">
							  <sf:input path="loca_name" cssClass="form-control" id='loca_name' /><i class="asterisk_input"></i>
								<sf:errors path="loca_name" cssClass="errorBlock"></sf:errors>	 
								</div>
						</div>
						 <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Unit/Location Short name:</label>
								<div class="col-sm-5">
							  <sf:input path="loca_short_name" cssClass="form-control" id='loca_short_name'/><i class="asterisk_input"></i>
								<sf:errors path="loca_short_name" cssClass="errorBlock"></sf:errors>	 
								</div>
						</div>
							 
							<%-- <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Unit:</label>
							  <div class="col-sm-5">
							   <sf:input path="loca_name" cssClass="form-control" id='loca_name' />
								<sf:errors path="loca_name" cssClass="errorBlock"></sf:errors>	  </div>
							</div> --%>
	
				</div>
				<div class="col-md-12 litigation_buttons">
				<br>
					<center><input type="submit" value="Add" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listLocations' " >Back</button> -->
					</center>
				</div>
				</div>
				</sf:form>	
			
			<div style="clear:both"></div>
		</div>

			
<script type="text/javascript">

 $(document).ready(function(){
	  
    $('#loca_name').click(function(){
        $('#loca_name').popover('destroy');
    });

}); 
 
 var loca_status;
 $("#loca_name").keyup(function(){ 
	  var loca_name = $("#loca_name").val();
	  var loca_id = 0;

		if(loca_name !='' && loca_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["loca_id"] = loca_id;
				items["loca_name"] = loca_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isLocaNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						//alert(result+" resulatt");
						if(result==0||result=='0'){
							loca_status = 0;
							$('#loca_name').popover('destroy');
						}
						else{
							loca_status = 1;
							$("#loca_name").attr("data-placement", "top");
							$("#loca_name").attr("data-content", "Unit already exists..!!");
							$('#loca_name').popover('show');
							return false;
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
 });	
 var loca_short_status;
 $("#loca_short_name").keyup(function(){ 
 	  var loca_short_name = $("#loca_short_name").val();
 	  var loca_id = 0;

 		if(loca_short_name !='' && loca_short_name!=null ){ // && !typeof emp_id === 'undefined'
 			
 			    items = {};
 				items["loca_id"] = loca_id;
 				items["loca_short_name"] = loca_short_name;

 				var jsonString = JSON.stringify(items);
 				$.ajax({
 					type : "post",
 					url : "./islocaShortNameExist",
 					contentType : "application/json",
 					dataType : "html",
 					data : jsonString,
 					cache : false,
 					success : function(result) {
 						if(result==0){
 							loca_short_status = 0;
 							$('#loca_short_name').popover('destroy');
 						}else{
 							loca_short_status = 1;
 							$("#loca_short_name").attr("data-placement", "top");
 							$("#loca_short_name").attr("data-content", "Unit short name is already exists..!!");
 							$('#loca_short_name').popover('show');
 						}
 					},
 					error : function(xhr, ajaxOptions, thrownError) {
 						$('#errors').html("There is error:" + thrownError);
 					}
 				});
 		}
 });
 
 $('#location').on('keyup keypress', function(e) {
	  var keyCode = e.keyCode || e.which;
	  if (keyCode === 13) { 
	    e.preventDefault();
	    return false;
	  }
	});
 
      function validateForm() { 
            
             var oname = $('#loca_name').val();
             var short_name = $('#loca_short_name').val();
			
             
             if (oname == null || oname == "") {
            	 
            	 $( "#loca_name" ).attr( "data-placement", "top" );
                 $( "#loca_name" ).attr( "data-content", "Unit name required" );
                 $('#loca_name').popover('show');
				
                    return false;
                }
             if(loca_status==1){
		        	   $( "#loca_name" ).attr( "data-placement", "top" );
			            $( "#loca_name" ).attr( "data-content", "Unit already exists..!!" );
			            $('#loca_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#loca_name').popover('destroy');
                }
             if (!(isNaN(oname))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#loca_name" ).attr( "data-placement", "top" );
                 $( "#loca_name" ).attr( "data-content", "Unit should be text" );
                 $('#loca_name').popover('show');
                 return false;
             }
             else
             {
             	$('#loca_name').popover('destroy');
             }
             
             if (!(oname.length>2)) {
              	// $("#oerr").innerHTML="this is invalid name ";
                   $( "#loca_name" ).attr( "data-placement", "top" );
                   $( "#loca_name" ).attr( "data-content", "Unit should be valid length" );
                   $('#loca_name').popover('show');
                   return false;
               }
               else
               {
               	$('#loca_name').popover('destroy');
               }
				if (short_name == null || short_name == "") {
            	 
            	 $( "#loca_short_name" ).attr( "data-placement", "top" );
                 $( "#loca_short_name" ).attr( "data-content", "Unit short name required" );
                 $('#loca_short_name').popover('show');
				
                    return false;
                }else if(loca_short_status==1){
		        	   $( "#loca_short_name" ).attr( "data-placement", "top" );
			            $( "#loca_short_name" ).attr( "data-content", "Unit short name already exists..!!" );
			            $('#loca_short_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#loca_short_name').popover('destroy');
                }
             if (!(isNaN(short_name))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#loca_short_name" ).attr( "data-placement", "top" );
                 $( "#loca_short_name" ).attr( "data-content", "Unit short name should be text" );
                 $('#loca_short_name').popover('show');
                 return false;
             }
             else
             {
             	$('#loca_short_name').popover('destroy');
             }
             
             if (!(short_name.length == 3)) {
             	// $("#oerr").innerHTML="this is invalid name ";
                  $( "#loca_short_name" ).attr( "data-placement", "top" );
                  $( "#loca_short_name" ).attr( "data-content", "Unit short name should be maximum of 3 letters" );
                  $('#loca_short_name').popover('show');
                  return false;
              }
              else {
              	$('#loca_short_name').popover('destroy');
              }
         } 
    	  
</script>
     

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
