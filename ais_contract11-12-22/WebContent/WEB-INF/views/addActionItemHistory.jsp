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
					<h2 style="color: #032BEC; font-size: 24px; float: left;">Add Action Item History</h2>
					<!-- <a href="./listLegalNotice"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Add</h2>
		<sf:form class="form-horizontal" role="form" enctype="multipart/form-data"  commandName="addActionItemHistory" action="./saveContractHistory" method="post">
				<div class="col-md-12">

						<%-- <div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1">Contract ID :</label>
								<div class="col-sm-7">
									<sf:input class="form-control" path="chst_contract_id"/>
								</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Status :</label>
							<div class="col-sm-7">
								<select class="form-control" id="chst_status">
								      <option value="NA">--Select--</option>
									  <option value="Pending">Pending</option>
			   				          <option value="In Progress">In Progress</option>
			   				          <option value="Awaiting Feedback">Awaiting Feedback</option>
			   				          <option value="Awaiting Approval">Awaiting Approval</option>
			   				          <option value="Sent for Review">Sent for Review</option>
			   				          <option value="Closed/Executed">Closed/Executed</option>
			   				          <option value="Contract Cancelled">Cancelled</option>
			   				          <option value="Contract Suspended">Suspended</option>
			   				          <option value="Others">Others</option>
									
							    </select><i class="asterisk_input"></i>
								
							</div>
						</div>
						
						<div class="form-group" id="chst_status_others_div">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
								<input id="chst_status_others" class="form-control"
										 /><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="replied_date_div" data-date-format="MM">
									<input id="chst_received_date" name="chst_received_date" class="form-control" type="text" 
										   name="chst_received_date" readonly/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Comments :</label>
							<div class="col-sm-7">
								<textarea rows="2" cols="41" id="chst_comments"  placeholder="Enter Comments"></textarea>
							<i class="asterisk_input"></i>
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
/* var filecount = 1;
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
 */
</script>

<!-- <script src="appJs/PreExecutionContracts/contractHistoryValidate.js"></script> -->
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
