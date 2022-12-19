<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>




<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Entity Mapping</h2>
					<a href="./listEntitiesMapping"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	
	<sf:form cssClass="form-horizontal" commandName="entitiesMapping" onsubmit="return validateForm();" 
	action="./updateEntitiesMapping" method="post">
	<sf:errors path="*" cssClass="errorBlock"></sf:errors>
	
	
	<div class="f_form_content">
			<h2>Update Entity Mapping</h2>
			
			<div class="col-md-12">
				
						<div class="col-md-4" style="display: none;">
							<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Entity Id:</label>
								<div class="col-sm-7">
							<sf:input path="enti_id" cssClass="form-control input-lg" />
							<sf:errors path="enti_id" cssClass="errorBlock"></sf:errors>
								</div>
							</div>
						</div>
						  
						<div class="col-md-4">
							<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Entity:</label>
								<div class="col-sm-7">
							<sf:select path="enti_orga_id" items="${allOrganizations}" cssClass="form-control" /><i class="asterisk_input"></i>
							<sf:errors path="enti_orga_id" cssClass="errorBlock"></sf:errors>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							  <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Unit:</label>
							  <div class="col-sm-7">
							<sf:select path="enti_loca_id" items="${allLocations}" cssClass="form-control"/><i class="asterisk_input"></i>
							<sf:errors path="enti_loca_id" cssClass="errorBlock"></sf:errors>
							  </div>
							</div>
						</div>
						<div class="col-md-4">
							  <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Function:</label>
							  <div class="col-sm-7">
							<sf:select path="enti_dept_id" items="${allDepartments}" cssClass="form-control" />
							<sf:errors path="enti_dept_id" cssClass="errorBlock"></sf:errors>
							  </div>
							</div>
						</div>
			</div>
				
				
				<div class="col-md-12 litigation_buttons">
				<br>
				<div id="errors" style="color: red;"> </div>
					<center><input type="submit" value="Update" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listEntitiesMapping' " >Back</button> -->
				</button></center>
				</div>
				</div>
				</sf:form>	
			
			<div style="clear:both"></div>
			
</div>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script>
$(document).ready(function() {
	$("#enti_orga_id").on('change', check_exist_mapping);
	$("#enti_loca_id").on('change', check_exist_mapping);
	$("#enti_dept_id").on('change', check_exist_mapping);
	
	
	
});
var enti_mapp_exist;
function check_exist_mapping(){
	var enti_enti_id = $("#enti_id").val();
	var enti_orga_id = $("#enti_orga_id").val();
	var enti_loca_id = $("#enti_loca_id").val(); 
	var enti_dept_id = $("#enti_dept_id").val(); 
	items = {};
	items['enti_id'] = enti_enti_id;	
	items['orga_id'] = enti_orga_id;
	items['loca_id'] = enti_loca_id;
	items['dept_id'] = enti_dept_id;
	
	var jsonString = JSON.stringify(items);
	$.ajax({
		type : "post",
		url : "./isEntityExist",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(res) {
			if(res==0){
				enti_mapp_exist = 0;
				$('#errors').html("");
			}else{
				enti_mapp_exist = 1;
				$('#errors').html("Entity already mapped..!");
				//alertify.error("Entity already mapped..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
			
		}
	});
	
}
function validateForm() {
    
    var oname = $('#enti_orga_id').val();
    
    
    if (oname == 0) {
   	 
   	    $( "#enti_orga_id" ).attr( "data-placement", "top" );
        $( "#enti_orga_id" ).attr( "data-content", "Select Entity first" );
        $('#enti_orga_id').popover('show');
			
           return false;
       }else if(enti_mapp_exist==1){
    	   $('#errors').html("Entity already mapped..!");
           return false;
       }
       else
       {
       	$('#enti_orga_id').popover('destroy');
       }
    
  //Validate Location dropdown
    if ($( "#enti_loca_id" ).val() == 0) {
    	 
 		$( "#enti_loca_id" ).attr( "data-placement", "top" );
     	$( "#enti_loca_id" ).attr( "data-content", " Select Unit" );
      	$('#enti_loca_id').popover('show');
			
         return false;
     }
   
	  else
	  {
	  	$('#enti_loca_id').popover('destroy');
	  }
  
}
    
    
</script>