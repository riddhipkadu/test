<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div style="clear: both"></div>
		<!--first form-->
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #032BEC; font-size: 24px; float: left;">Add Contract History</h2>
					<!-- <a href="./listLegalNotice"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Add</h2>
<sf:form class="form-horizontal" role="form" enctype="multipart/form-data"  commandName="contractHistory" action="./saveContractHistory" method="post">
				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1">Contract ID :</label>
								<div class="col-sm-7">
									<sf:input class="form-control" path="chst_contract_id"/>
								</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Status :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="chst_status">
								      <option value="NA">--Select--</option>
			   				          <!-- <option value="In Progress">In Progress</option> -->
			   				          <option value="Sent for Review">Sent for Review</option>
			   				          <!-- <option value="Draft sent for negotiation">Draft sent for negotiation</option> -->
			   				          <option value="Finalized">Finalized</option>
			   				          <option value="Closed/Executed">Closed/Executed</option>
			   				          <option value="SentToPOCforNegotiation">Sent to POC for negotiation</option>
			   				          <option value="Others">Others</option>
			   				          <!-- <option value="Save As Draft">Save As Draft</option> -->
									
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						
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
								     <option value="NA">--Select--</option>
									 <option value="Drafting/Preparing Memo">Drafting</option>
			   				          <option value="Reviewing">Reviewing</option>
			   				          <option value="Contract Negotations">Contract Negotations</option>
			   				          <option value="Teleconference">Teleconference</option>
			   				          <option value="Videoconference">Videoconference</option>
			   				          <option value="Meeting">Meeting</option>
			   				          <option value="Co-ordination">Co-ordination</option>
			   				          <option value="Execution">Execution</option>
			   				          <option value="Others">Others</option>
									
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
								     <option value="0">--Select--</option>
									 <option value="Internal Customer">Internal Customer</option>
			   				          <option value="External Customer">External Customer</option>
			   				          <option value="Both">Both</option>
			   				          <option value="Others">Others</option>
									
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
								<select class="form-control" multiple="multiple" id="chst_contract_type" name="chst_contract_type">
								     <c:forEach items="${allContractType}" var="contra_type">
									      <option value="${contra_type.key}">${contra_type.value}</option>
									</c:forEach>
							    </select>
							</div>
						</div>
						
						</div>
					
					<div class="col-md-6">
					
					<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Action assigned to :</label>
							<div class="col-sm-7">
							<sf:select path="chst_assigned_to"
										cssClass="form-control">
										<option value="0">--Select--</option>
										<c:forEach items="${allUser}"
											var="userList">
											<sf:option value="${userList.user_id}">${userList.user_first_name}
												${userList.user_last_name}</sf:option>
										</c:forEach>
										<option value="-1">Others</option>
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
							</div>
						</div>
						<div id="action_poc_user_div">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">POC user :</label>
							<div class="col-sm-7">
									<sf:select path="chst_poc_user_id"
										cssClass="form-control">
										<option value="0">--Select--</option>
										<c:forEach items="${allPOCUser}" var="userList">
											<sf:option value="${userList.user_id}">${userList.user_first_name}
												${userList.user_last_name}</sf:option>
										</c:forEach>
									</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group" style="display:none;">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
							<input class='messageCheckbox' type='checkbox' name='Yes' value='Yes' checked="checked"> Mark admin in approval<br>
							</div>
						</div>
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
						<!-- <div class="form-group">
							<label class="col-sm-5 control-label">Upload Document :</label>
							<div class="col-sm-7">
								<input type="file" name="contract_history_document" class="file-loading"/>  <a href='#' onclick='deleteRow(0);'><i class='glyphicon glyphicon-remove-circle'></i></a>
							
							</div>
						</div> -->
						
					</div> 
				</div>
				<div class="col-md-12 litigation_buttons">
					<br>
						<center>
						<button type="submit" name="Save" value="Save" class="btn btn-success">Create</button>
						<!-- <button type="submit" name="Draft" value="Draft" class="btn btn-primary">Save As Draft</button> -->
						</center>
				</div>
				</sf:form>
			</div>
		

		<div style="clear: both"></div>

	</div>
</div>
<script src="js/bootstrap-multiselect.js"></script>
<script type="text/javascript">
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
				+"<div class='col-sm-3'><input type='file' id='poc_doc'  name='contract_history_document' class='file-loading'/>"
				+"</div>"
				+"<div class='col-sm-4' style='text-align: right;'>"
				//+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteRow("+filecount+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div>");
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
$(document).ready(function(){
	$(document).on("click", ":submit", function(e){
	    buttonClicked = $(this).val();
	    if(buttonClicked=="Save"){
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

<script src="appJs/PreExecutionContracts/contractHistoryValidate.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
