<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">

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
		<div style="clear: both"></div>
		<!--first form-->
		
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #032BEC; font-size: 24px; float: left;">Edit Contract History</h2>
					<!-- <a href="./listLegalNotice"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Edit</h2>
		<sf:form class="form-horizontal" role="form" enctype="multipart/form-data"  commandName="editContractHistory"
					action="./updateContractHistory" method="post">
				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1"> ID :</label>
								<div class="col-sm-7">
									<sf:input class="form-control" path="chst_id"/>
								</div>
						</div>
						<div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1">Contract ID :</label>
								<div class="col-sm-7">
									<sf:input class="form-control" path="chst_contract_id"/>
								</div>
						</div>
						<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Status :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="chst_status">
								      <sf:option value="NA">--Select--</sf:option>
			   				          <sf:option value="In Progress">In Progress</sf:option>
			   				          <sf:option value="Sent for Review">Sent for Review</sf:option>
			   				          <sf:option value="Draft sent for negotiation">Draft sent for negotiation</sf:option>
			   				          <sf:option value="Finalized">Finalized</sf:option>
			   				          <sf:option value="Closed/Executed">Closed/Executed</sf:option>
			   				          <sf:option value="SentToPOCforNegotiation">Sent to POC for negotiation</sf:option>
			   				          <c:if test="${status=='Save As Draft'}">
			   				          <sf:option value="Save As Draft">Save As Draft</sf:option>
			   				          </c:if>
			   				          <sf:option value="Others">Others</sf:option>
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div> --%>
						
						<div class="form-group" id="chst_status_others_div">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
								<sf:input path="chst_status_others" cssClass="form-control"
										 /><i class="asterisk_input"></i>
								
							</div>
						</div>
						
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Received Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="replied_date_div" data-date-format="MM">
									<sf:input id="chst_received_date" path="chst_received_date" class="form-control" type="text" 
										   name="chst_received_date" readonly="true"/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Action to be performed :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="chst_action_performed">
								      <sf:option value="NA">--Select--</sf:option>
									  <sf:option value="Drafting/Preparing Memo">Drafting</sf:option>
			   				          <sf:option value="Reviewing">Reviewing</sf:option>
			   				          <sf:option value="Contract Negotations">Contract Negotations</sf:option>
			   				          <sf:option value="Teleconference">Teleconference</sf:option>
			   				          <sf:option value="Videoconference">Video conference</sf:option>
			   				          <sf:option value="Meeting">Meeting</sf:option>
			   				          <sf:option value="Co-ordination">Co-ordination</sf:option>
			   				          <sf:option value="Execution">Execution</sf:option>
			   				          <sf:option value="Others">Others</sf:option>
									
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						<div class="form-group" id="chst_action_performed_div">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
								<sf:input path="chst_action_performed_others" cssClass="form-control"
										 /><i class="asterisk_input"></i>
								
							</div>
						</div>
						
						<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Action to be performed by :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="chst_performed_by">
								     <sf:option value="0">--Select--</sf:option>
									 <sf:option value="Internal Customer">Internal Customer</sf:option>
			   				          <sf:option value="External Customer">External Customer</sf:option>
			   				          <sf:option value="Both">Both</sf:option>
			   				          <sf:option value="Others">Others</sf:option>
									
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						<div class="form-group" id="chst_performed_by_others_div">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
								<sf:input path="chst_performed_by_others" cssClass="form-control"
										 /><i class="asterisk_input"></i>
								
							</div>
						</div> --%>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Document Type :</label>
							<div class="col-sm-7">
								<select class="form-control" multiple="multiple" id="chst_contract_type" name="chst_contract_type_id">
								    <c:forEach items="${allContractType}" var="contra_type">
									   <c:set var="flag" scope="page" value="0"></c:set>
									     <c:forEach items="${editContractHistory.chst_contract_type}" var="demo" varStatus="loop_cont_type">
									       <c:choose>
									          <c:when test="${demo.cont_contract_type_id==contra_type.key}">
									            <option value="${demo.cont_contract_type_id}" selected="selected">${contra_type.value}</option>
									            <c:set var="flag" value="1"></c:set>
									          </c:when>
									       </c:choose>
									      </c:forEach> 
									      <c:choose>
									        <c:when test="${flag==0 }">
									            <option value="${contra_type.key}">${contra_type.value}</option>
									        </c:when>
									      </c:choose>
									</c:forEach> 
							    </select>
							</div>
						</div>
						</div>
					
					<div class="col-md-6">
					<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Action assigned to :</label>
							<div class="col-sm-7">
								<sf:select path="chst_assigned_to" cssClass="form-control">
									<sf:option value="0">--Select--</sf:option>
										<c:forEach items="${allUser}" var="userList">
											<sf:option value="${userList.user_id}">${userList.user_first_name}
												${userList.user_last_name}</sf:option>
										</c:forEach>
										<sf:option value="-1">Others</sf:option>
									</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group" id="chst_assigned_others_div">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
								<sf:input path="chst_assign_others" cssClass="form-control"
										 /><i class="asterisk_input"></i>
							</div>
						</div>
					
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Comments :</label>
							<div class="col-sm-7">
								<sf:textarea rows="2" cols="41" path="chst_comments"  placeholder="Enter Comments"></sf:textarea>
							<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
						
						<div id="action_poc_user_div">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">POC user :</label>
							<div class="col-sm-7">
									<sf:select path="chst_poc_user_id" cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<c:forEach items="${allPOCUser}" var="userList">
											<sf:option value="${userList.user_id}">${userList.user_first_name}
												${userList.user_last_name}</sf:option>
										</c:forEach>
									</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						<!-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
							<input class='messageCheckbox' type='checkbox' name='Yes' value='Yes' checked="checked"> Mark admin in approval<br>
							</div>
						</div> -->
						</div>
					
						<div id="filesContainer">
						
						</div>
						<div class="form-group">
						<label class="control-label col-sm-5"></label>
							<div class="col-sm-7">
								<div class="col-md-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addFileInput();">Document</button>
								</div>
							</div>
						</div>
						<c:choose>
							<c:when test="${fn:length(contract_history_doc) != 0}">
								<div class="form-group">
									<label class="control-label col-sm-5">Document Attached
										:</label>
									<div class="col-sm-7">
										<c:forEach items="${contract_history_doc}" var="document"
											varStatus="doc">
											 <div id="doc_attached_${document.chst_doc_id}" class="col-sm-3">

												<div class="col-sm-3"
													style="text-align: right; cursor: pointer;">
													<a href="./downloadPreExecutedHistoryDocument?chst_doc_id=${document.chst_doc_id}">
														${document.chst_doc_original_file_name}</a>
														<c:choose>
														<c:when test="${status != 'SentToPOCforNegotiation'}">
														<i class="glyphicon glyphicon-remove-circle"
														onclick="deleteContractHistoryDoc(${document.chst_doc_id});"
														style="color: red; margin-right: -250px; margin-top: -19px;"
														title="Delete"></i>
														</c:when>
														</c:choose>
												</div>
											</div><br/> 
										</c:forEach>
									</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div> 
				</div>
				<div class="col-md-12 litigation_buttons">
					<br>
						<center>
                         <button type="submit" value="Update" name="Update" class="btn btn-success">Update</button>
						 <%-- <c:if test="${status=='Save As Draft'}">
						 <button type="submit" name="Draft" value="Draft" class="btn btn-primary">Update As Draft</button>
						</c:if> --%>
						</center>
				</div>
				</sf:form>
			</div>
		<div style="clear: both"></div>
	</div>
</div>
<script src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
/* $(document).ready(function(){
	//alert("status :"+'${status}');
	if('${status}'== SentToPOCforNegotiation){
		$("#action_poc_user_div").show();
	}else{
		$("#action_poc_user_div").hide();
	}
});  */

var filecount = 1;
function addFileInput() {
   // if(filecount <=5){
	$('#filesContainer')
	.append(
			"<div class='form-group' id='file"+filecount+"'>"
			+"<label class='col-sm-5 control-label'>Upload Document :</label>"
			+"<div class='col-sm-3'><input type='file' name='contract_history_document' class='file-loading'/>"
			+"</div>"
			+"<div class='col-sm-4' style='text-align: right;'>"
			+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteRow("+filecount+");' style='color: red;cursor:pointer;' title='Delete'></i>"
			+"</div></div>");
	filecount++;
    //}
}

function addFileInputMandatory() {
	   // if(filecount <=5){
		$('#filesContainer')
		.append(
				"<div class='form-group' id='file"+filecount+"'>"
				+"<label class='col-sm-5 control-label'>Upload Document :</label>"
				+"<div class='col-sm-3'><input type='file' name='contract_history_document' required='required' class='file-loading'/>"
				+"</div>"
				+"</div>");
		filecount++;
	    //}
	}

function deleteRow(filecount) {
	$("#file" + filecount).remove();
}
$("#chst_contract_type").multiselect({
	buttonWidth: '310px',
    enableFiltering: true,
    filterBehavior: 'text',
    enableCaseInsensitiveFiltering: true,
    maxHeight: 150
});

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
</script>
<script src="appJs/PreExecutionContracts/contractHistoryValidate.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>