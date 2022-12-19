<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<!-- <script>

	$(document).ready(function(){
		$('#file').click(function(){
			$('#file').popover('destroy');
		});
		
	});
	function validateForm() {
		var fileData = $('#file').val();
		if (fileData == null || fileData == "") {

			$("#file").attr("data-placement", "top");
			$("#file").attr("data-content", "First Select File");
			$('#file').popover('show');

			return false;
		} else {
			$('#file').popover('destroy');
		}
	}
	
	function submitForm() {

		$('#submitModal .modal-body').html("<p><strong>Query Updated successfully!</strong></p>");
		$("#submitModal").modal('show');
	}
</script>
 -->

<div class="page_cont_padd">
<div class="page_container">

<!-- Fail Modal -->
			<div class="modal fade" id="dialogBox" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #e26d1c;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								<span class="glyphicon glyphicon-remove-sign"></span> &nbsp;Error
							</h4>
						</div>
						<div class="modal-body">
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
<!-- Fail Modal END -->

<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Edit Query</h2>
					<a href="./listQuery"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	
	<sf:form autocomplete="off" commandName="editQuery" cssClass="form-horizontal" enctype="multipart/form-data" action="./updateQuery" method="POST">
			
			<div class="f_form_content">
			<h2>Update Query</h2>
			
				<div class="col-md-12">
				<div class="col-md-6">
				<div class="col-md-12" style="display:none">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">ID :</label>
							<div class="col-sm-6">
								<sf:input path="quer_id" />
							</div>
						</div>
					</div>
				<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Entity/Company Name :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allOrganizations}" path="quer_entity_id" name="quer_entity_id" id="quer_entity_id">
								       <option value="0">--Select--</option>
									   
							     </sf:select>
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Unit/Vertical :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="quer_unit_id" name="quer_unit_id" id="quer_unit_id">
									   <c:forEach items="${allLocations}" var="loca">
								          <sf:option value="${loca.key}">${loca.value}</sf:option>
								       </c:forEach>
							     </sf:select>
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Function/Location/Department :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allDepartments }" path="quer_function_id" name="quer_function_id" id="quer_function_id">
									  <c:forEach items="${allDepartments}" var="dept">
								          <sf:option value="${dept.key}">${dept.value}</sf:option>
								       </c:forEach> 
							     </sf:select>
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">From :</label>
							<div class="col-sm-7">
								<sf:input path="quer_from_id" cssClass="form-control" placeholder="Enter From Name" />
							     <i class="asterisk_input"></i>
							</div>
							
						</div>
					</div>
<!-- 					<div class="col-md-12"> -->
<!-- 						<div class="form-group row"> -->
<!-- 							<label for="sfctitle" class="control-label col-sm-5">To :</label> -->
<!-- 							<div class="col-sm-6"> -->
<!-- 								<input id="sfctitle" class="form-control" type="text" name="sfctitle" readonly="readonly" value="Legal Department"/> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Query :</label>
							<div class="col-sm-7">
								<sf:textarea class="form-control" rows="4" cols="6" path="quer_query" id="quer_query" name="quer_query" placeholder="Enter Query"></sf:textarea>
							<i class="asterisk_input"></i>
							</div>
							
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Assigned To :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="quer_assigned_to" name="quer_assigned_to" id="quer_assigned_to">
								<sf:option value="0">--Select--</sf:option>
								<c:forEach items="${user_legal_department}"	var="user_legal_department">
									<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
								</c:forEach>	  
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
							
						</div>
					</div>
					 <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Query Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="datepicker3" data-date-format="MM">
									<sf:input id="quer_query_date" path="quer_query_date" class="form-control" type="text" 
										   name="quer_query_date" readonly="true"/> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					  <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Expected Reply Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="reply_date_div" data-date-format="MM">
									<sf:input id="quer_reply_date" class="form-control" type="text" readonly="true"  
										   name="quer_reply_date" path="quer_reply_date"/> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>  
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Remainder Required On :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="reminder_date_div" data-date-format="MM">
									<sf:input id="quer_reminder_date" class="form-control" type="text" readonly="true" path="quer_reminder_date"
										 name="quer_reminder_date" value="" /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>  
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Criticality :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="quer_criticality" id="quer_criticality">
									   	<sf:option value="0">--Select--</sf:option>
										<sf:option value="High">High</sf:option>
										<sf:option value="Medium">Medium</sf:option>
										<sf:option value="Low">Low</sf:option>
							     </sf:select>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Turnaround Time :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="turnarond_date_div" data-date-format="MM">
									<sf:input id="quer_turnaround_time_name" class="form-control" type="text" readonly="true"
										 path="quer_turnaround_time_name" value="" /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document :</label>
							<div class="col-sm-7">
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
						<div id="doc_attached_${document.quer_doc_id}" class = "col-sm-7">
							
						<div class="col-sm-3" style="text-align: right; cursor: pointer;">
						<a href="./downloadQueryDocument?quer_doc_id=${document.quer_doc_id}">
							${document.quer_doc_original_file_name}</a>
	                     <i class="glyphicon glyphicon-remove-circle" onclick="deleteQueryDoc(${document.quer_doc_id});" style="color: red; margin-right: -200px; margin-top: -19px;" title="Delete"></i>
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
				<%-- <div class="col-md-6">
				   <div class="col-md-12">
						<table id="example" class="table table-striped table-bordered" width="100%">
						  <thead>
						  <tr style="background:#0B6EC3;color:#fff;">
						   <th>Sr.No</th>
						   <th>Document Name</th>
						   <th>Action</th>
						  </tr> 
						  </thead>
						  <tbody id="tbody">
						   <c:forEach items="${queryDocuments}" var="doc" varStatus="loop">
						   <tr id="row_${doc.quer_doc_id}">
						     <td>${loop.index+1}</td>
						     <td>${doc.quer_doc_original_file_name }</td>
						     <td><button type="button" value="${doc.quer_doc_id}"
							name='delete_query_document' class="btn btn-danger">
							     <i class='fa fa-trash'></i>&nbsp;Delete	</button></td>
						   </tr>
						   </c:forEach>
						  </tbody>
						</table>
					</div>
				</div> --%>
				
				<div class="col-md-12 litigation_buttons">
				<br>
				
					<center>
					<button type="submit" value="Update" name="Update" class="btn btn-myPrimary">Update</button>
					<c:if test="${query_status =='Draft'}">
			        <button type="submit" name="Draft" value="Draft"class="btn btn-myDefault">Update As Draft</button>
					</c:if>
					</center>
				</div>
				</div>
				
				</div>
		</sf:form>		
			
			<div style="clear:both"></div>
			
</div>
</div>
<script>

$(document).ready(function(){
	$(document).on("click", ":submit", function(e){
	    buttonClicked = $(this).val();
	    if(buttonClicked=="Update"){
	    	if(validateForm()!=false){
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
