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
					<h2 style="color: #032BEC; font-size: 24px; float: left;">Add Contract status for document Negotiation</h2>
					<!-- <a href="./listLegalNotice"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Add</h2>
			<sf:form class="form-horizontal" role="form" enctype="multipart/form-data" commandName="addContractStatusForNegotiation" action="./saveContractStatusForNegotiation" method="post">
				<div class="col-md-12">

					<div class="col-md-8">
						 <div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1">Contract ID :</label>
								<div class="col-sm-7">
									<sf:input class="form-control" path="poc_contract_req_id" value="${id}"/>
								</div>
						</div> 
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Status :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="poc_status">
								      <option value="NA">--Select--</option>
			   				          <option value="Sent for Review">Sent for Review</option>
			   				          <option value="Can be Executed">Can be Executed</option>
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="replied_date_div" data-date-format="MM">
									<sf:input id="poc_status_date" path="poc_status_date" class="form-control" type="text" 
										   name="poc_status_date" readonly="true"/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Comments :</label>
							<div class="col-sm-7">
								<sf:textarea rows="2" cols="61" path="poc_action_item"  placeholder="Enter Comments"></sf:textarea>
							<i class="asterisk_input"></i>
							</div>
							
						</div>
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document :</label>
							<div class="col-sm-7">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="contract_doc" ><br />
								</div>
							</div>
							
						</div>
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
