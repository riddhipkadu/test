<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #054eff; font-size: 24px; float: left;">Designation</h2>
				<a href="./listDesignations"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form commandName="designation" onsubmit="return validateForm();" autocomplete="off"
			action="./saveDesignation" cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
						
			<div class="f_form_content">
				<h2>Create Designation</h2>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Parent
								Designation:</label>
							<div class="col-sm-8">
								<sf:select path="desi_parent_id" items="${designation_list}"
									cssClass="form-control" >
									
									</sf:select>

							</div>
						</div>
					</div>


					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Designation:</label>
							<div class="col-sm-8">
								<sf:input path="desi_name" cssClass="form-control"
									id='desi_name' /><i class="asterisk_input"></i>
								<sf:errors path="desi_name" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>






				</div>


				<div class="col-md-12 litigation_buttons">
					<br>

					<center>
						<input type="submit" value="Add" class="btn btn-myPrimary" />
						<!-- <button type="button" name="back" id="back"
							class="btn btn-myDefault"
							onclick="window.location.href ='./listDesignations' ">Back</button> -->
					</center>

				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>



<script type="text/javascript">

$(document).ready(function(){
	 
	 
    $('#desi_name').click(function(){
        $('#desi_name').popover('destroy');
    });


}); 

var desi_status;
$("#desi_name").keyup(function(){ 
	//$('#desi_name').val($.trim($('#desi_name').val()));
	  var desi_name = $("#desi_name").val();
	  var desi_id = 0;

		if(desi_name !='' && desi_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["desi_id"] = desi_id;
				items["desi_name"] = desi_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isDesiNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						if(result==0){
							desi_status = 0;
							$('#desi_name').popover('destroy');
						}else{
							desi_status = 1;
							$("#desi_name").attr("data-placement", "top");
							$("#desi_name").attr("data-content", "Designation already exists..!!");
							$('#desi_name').popover('show');
							return false;
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
});

$('#designation').on('keyup keypress', function(e) {
	  var keyCode = e.keyCode || e.which;
	  if (keyCode === 13) { 
	    e.preventDefault();
	    return false;
	  }
	});

      function validateForm() {
            
             var oname = $('#desi_name').val();
             
			
             
             if (oname == null || oname == "") {
            	 
            	 $( "#desi_name" ).attr( "data-placement", "top" );
                 $( "#desi_name" ).attr( "data-content", "Designation name required" );
                 $('#desi_name').popover('show');
				
                    return false;
                }else if(desi_status==1){
		        	   $( "#desi_name" ).attr( "data-placement", "top" );
			            $( "#desi_name" ).attr( "data-content", "Designation already exists..!!" );
			            $('#desi_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#desi_name').popover('destroy');
                }
             if (!(isNaN(oname))) {
            	// $("#oerr").innerHTML="this is invalid name ";
                 $( "#desi_name" ).attr( "data-placement", "top" );
                 $( "#desi_name" ).attr( "data-content", "Designation should be text" );
                 $('#desi_name').popover('show');
                 return false;
             }
             else
             {
             	$('#desi_name').popover('destroy');
             }
            
             
             if (!(oname.length>2)) {
                	// $("#oerr").innerHTML="this is invalid name ";
                     $( "#desi_name" ).attr( "data-placement", "top" );
                     $( "#desi_name" ).attr( "data-content", "Designation should be valid length" );
                     $('#desi_name').popover('show');
                     return false;
                 }
                 else
                 {
                 	$('#desi_name').popover('destroy');
                 }
         } 
    	  
       </script>
       <script>
$(document).ready(function(){
noPrecedingSpace ("desi_name");
});
</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
