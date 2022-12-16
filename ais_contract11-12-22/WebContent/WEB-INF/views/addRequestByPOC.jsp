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
					<h2 style="color:#032BEC;font-size:24px;float:left;">Generate Request By POC</h2>
					<a href="./listRequestByPOC"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addRequestByPOC" autocomplete="off" cssClass="form-horizontal" onsubmit="return validateForm();"
				action="./saveRequestByPOC">
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
				
			<div class="f_form_content">
			<h2>Generate Request</h2>
			
				<div class="col-md-12">
				<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Entity :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="req_entity_id" items="${allOrganization}" >
								
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						</div>
						<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Unit/Vertical :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="req_unit_id" >
								   <option value="0">--Select--</option>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						</div>
						<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Function/Location  :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="req_function_id">
								   <option value="0">--Select--</option>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						</div>
						<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Request related to :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="req_related_to" >
								    <option value="NA">--Select--</option>
									<option value="Litigation">Litigation</option>
									<option value="Contract">Contract</option>
									<option value="Query">Query</option>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						</div>
						<div class="col-md-8">
						<div class="form-group">
							<label  class="col-sm-5 control-label">Brief Facts/Comments :</label>
							<div class="col-sm-7">
								<sf:textarea rows="5" cols="67" path="req_comments" placeholder="Enter Comments"></sf:textarea>
							</div>
						</div>
						</div>
						<div class="col-md-12">
						<div id="fileContiner_Legal"></div>
						</div>
						 <div class="col-md-12">
                   				<div class="col-md-6">
									<div class="form-group">
										<label class="col-sm-4 control-label"></label>
										<div class="col-sm-6 litigation_buttons">
										<button type="button" class="btn btnTaskCompDoc"
										onclick="addLegalFileInput();">Document</button>
										</div>
								
									</div>
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
</div>
<!-- <script src="appJs/AreaOfExpertise/area_of_expertise.js"></script> -->
<script type="text/javascript">


$(document).ready(function(){
	  
	$("#req_entity_id").on('change', getAllLocationForOrganization);
	$("#req_unit_id").on('change', getAllDepartmentsByLocation);

}); 
 
function getAllLocationForOrganization() { 
	
	var user_orga_id = $("#req_entity_id").val();
		if (user_orga_id > 0) {
			/*items = {};
			items['tmap_orga_id'] = user_orga_id;

			var jsonString = JSON.stringify(items);

*/			
					$.ajax({
						type : "post",
						url : "./getLocationByOrgaUserId",
						//contentType : "application/json",
						dataType : "json",
						data : {orga_id :user_orga_id },
						cache : false,
						success : function(locationlist) {
							//var locationjson = $.parseJSON(locationlist);
							data ="";
							data +='<option value = 0>--Select--</option>';

							$.each(locationlist,function(key,value){
								data += "<option value="+value['loca_id']+">"+value['loca_name']+"</option>";
							});
							$("#req_unit_id").html(data);	
							$('#req_function_id').html('<option value="0">--Select--</option>');
						},						
							error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#req_unit_id').html('<option value="0">--Select--</option>');
			$('#req_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		var user_orga_id = $("#req_entity_id").val();
		var user_loca_id = $("#req_unit_id").val();
		if (user_loca_id > 0) {
			/*items = {};
			items["tmap_orga_id"] = user_orga_id;
			items["tmap_loca_id"] = user_loca_id;

			var jsonString = JSON.stringify(items);
*/
			
					$.ajax({
						type : "post",
						url : "./getDepartmentsByLocaUserId",
						//contentType : "application/json",
						dataType : "json",
						data : {loca_id : user_loca_id, orga_id: user_orga_id},
						cache : false,
						success : function(departmentlist) {
							
							var data ="";
							data +="<option value= 0>--Select--</option>";
							
							$.each(departmentlist,function(key,value){
								//console.log("In loop");
								data += "<option value="+value['dept_id']+">"+value['dept_name']+"</option>";
							});
							$("#req_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#req_function_id').html('<option value="0">--Select--</option>');
		}
}
      
 var legalFileCount = 1;
 function addLegalFileInput(){
 	$("#fileContiner_Legal").append('<div class="col-md-12" id="legalFile'+legalFileCount+'" >'
 			+'<div class="form-group">'
 	        +'<label class="col-sm-4 control-label">Request document:</label>'
 	        +'<div class="col-sm-4">'
 	        +'<input type="file" name="legal_doc" class="file-loading" />'
 	        +'</div>'
 	        +'<div class="col-sm-3" style="text-align: right; cursor: pointer;">'
 	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteLegalRow('+legalFileCount+');" style="color: red;" title="Delete"></i>'
 	        +'</div>'
 	        +'</div>'
 	        +'</div>'
 			);
 	legalFileCount++;
 }
 function deleteLegalRow(filecount) {
 	$("#legalFile" + filecount).remove();
 }
  
    	  
</script>

     

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
