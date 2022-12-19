<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
<div class="page_container">

<!-- Dialogue box -->
	<div class="modal fade" id="submitModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="background-color: #097a09;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							 <span class="glyphicon glyphicon-ok"></span>  &nbsp;Success
						</h4>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" onClick="location.href='./listQuery'" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
	</div>
<!-- End of Dialogue box -->


<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Add Query</h2>
					<a href="./listQuery"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	
	<sf:form autocomplete="off" cssClass="form-horizontal" commandName="addQueryByAccept" enctype="multipart/form-data" action="./saveQuery" method="POST">
				
			<div class="f_form_content">
			<h2>Add Query</h2>
			
				<div class="col-md-10">
				<div class="col-md-4" style="display:none">
							<div class="form-group" >
								<label class="control-label col-sm-4" for="sel1">request id: </label>
								<div class="col-sm-8">
									<input name="quer_req_id" id="quer_req_id" value="${id}"/>
								</div>
							</div>
						</div>
				
				<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Entity/Company Name :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="quer_entity_id" name="quer_entity_id" id="quer_entity_id" disabled="true">
									   <c:forEach items="${allOrganizations}" var="organization">
								          <sf:option value="${organization.key}">${organization.value}</sf:option>
								       </c:forEach> 
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				
				<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Entity :</label>
							<div class="col-sm-6">
								<select name="quer_entity_id" id="quer_entity_id">
								     <option value="0">--SELECT--</option>
								     <c:forEach items="${allOrganizations}" var="organization">
								     <option value="${organization['key']}">${organization['value']}</option>
								     </c:forEach>
								</select>
							</div>
						</div>
					</div> --%>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Unit/Vertical :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="quer_unit_id" id="quer_unit_id" disabled="true">
									   <c:forEach items="${allLocations}" var="loca">
								          <sf:option value="${loca.key}">${loca.value}</sf:option>
								       </c:forEach>  
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Function/Location/Department :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="quer_function_id" id="quer_function_id" disabled="true">
									   <c:forEach items="${allDepartments}" var="dept">
								          <sf:option value="${dept.key}">${dept.value}</sf:option>
								       </c:forEach> 
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">From :</label>
							<div class="col-sm-6">
								<sf:input path="quer_from_id" cssClass="form-control" placeholder="Enter From Name" />
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="form-group row"> -->
<!-- 							<label for="sfctitle" class="control-label col-sm-3">To :</label> -->
<!-- 							<div class="col-sm-6"> -->
<!-- 								<input id="sfctitle" class="form-control" type="text" name="sfctitle" readonly="readonly" value="Legal Department"/> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Query :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" rows="4" cols="6" path="quer_query" id="quer_query" name="quer_query" placeholder="Enter Query" readonly="true"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Assigned To :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="quer_assigned_to" name="quer_assigned_to" id="quer_assigned_to">
									   <option value="0">--Select--</option>
									   <c:forEach items="${user_legal_department}"
											var="user_legal_department">
											<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
										</c:forEach>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					  <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Query Date :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="datepicker1" data-date-format="MM">
									<sf:input id="quer_query_date" path="quer_query_date" class="form-control" type="text" value="${quer_date}" 
										   name="quer_query_date" disabled="true"/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					  <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Expected Reply Date :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="reply_date_div" data-date-format="MM">
									<sf:input id="quer_reply_date" path="quer_reply_date" class="form-control" type="text" readonly="true" 
										   name="quer_reply_date" /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>  
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Reminder Required On :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="reminder_date_div" data-date-format="MM">
									<sf:input id="quer_reminder_date" path="quer_reminder_date" class="form-control" type="text" readonly="true"
										 name="quer_reminder_date" value="" /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>  
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Document :</label>
							<div class="col-sm-6">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="query_doc" ><br />
								</div>
							</div>
							
						</div>
					</div>
					
					<c:choose>
					<c:when test="${fn:length(allquery_doc) != 0}">
					<div class="col-md-12">
						<div class="form-group row">
						<label for="sfctitle" class="control-label col-sm-5">Document Attached :</label>
						<c:forEach items="${allquery_doc}" var="document" varStatus="doc">
						<div id="doc_attached_${document.req_doc_id}" class = "col-sm-7">
							
						<div class="col-sm-3" style="text-align: right; cursor: pointer;">
						<a href="./downloadRequestDocument?doc_id=${document.req_doc_id}">
							${document.req_doc_original_file_name}</a>
	                     <%-- <i class="glyphicon glyphicon-remove-circle" onclick="deleteQueryDoc(${document.quer_doc_id});" style="color: red; margin-right: -200px; margin-top: -19px;" title="Delete"></i> --%>
	       				</div>
						</div>
						</c:forEach>
						 </div>
					</div>
					</c:when>
						<c:otherwise>
						
						</c:otherwise>
						</c:choose>
				
				</div>
			
				<div class="col-md-12 litigation_buttons">
				<br>
				<center>
					<button type="submit" value="Save" name="Save" class="btn btn-myPrimary">Create</button>
						    	<!--  <button type="submit" name="Draft" value="Draft"class="btn btn-myDefault">Save As Draft</button> -->
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listDepartments' " >Back</button> -->
					</center>
				</div>
				</div>
		</sf:form>		
			
			<div style="clear:both"></div>
			
</div>
</div>

<script>
$(document).ready(function(){
	document.getElementById("quer_query").innerHTML = '${query}';
	
 $(document).on("click", ":submit", function(e){
    buttonClicked = $(this).val();
    if(buttonClicked=="Save"){
    	if(validateForm()!=false){
    		$("#quer_entity_id").prop("disabled",false);
    		$("#quer_unit_id").prop("disabled",false);
    		$("#quer_function_id").prop("disabled",false);
    		$("#quer_query_date").prop("disabled",false);
    		$("form").submit();
    	}else{
    		e.preventDefault();
    	}
    }
    if(buttonClicked=="Draft"){
    	if(validateDraft()!=false){
    		$("form").submit();
        }else{
    		e.preventDefault();
    	}
    }
    //e.preventDefault();
	}); 
});
</script>
<script src="appJs/Query/query_validation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
