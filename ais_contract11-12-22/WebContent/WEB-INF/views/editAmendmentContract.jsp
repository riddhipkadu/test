<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #054eff; font-size: 24px; float: left;">Edit
					Amendment Contract</h2>
				<a href="./listAmendmentContract?exec_id=${exec_id}"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form commandName="editAmendmentContract"
			action="./updateAmendmentContract" enctype="multipart/form-data"
			cssClass="form-horizontal">
			<div class="f_form_content">
				<h2>Edit Amendment Contract</h2>

				<!-- <div class="col-md-12"> -->
				<div class="col-md-12">
					<div class="col-md-6" style="display: none;">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">id :</label>
							<div class="col-sm-8">
								<sf:input path="amend_contract_id" />

							</div>
						</div>
					</div>
					<div class="col-md-6" style="display: none;">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">id :</label>
							<div class="col-sm-8">
								<sf:input path="amend_exec_contract_id" />

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Start
								Date</label>
							<div class="col-sm-8">
								<div class="input-group date" id="from_date_div"
									data-date-format="MM">
									<sf:input path="amend_contract_start_date"
										cssClass="form-control" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">End Date
								:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="to_date_div"
									data-date-format="MM">
									<sf:input path="amend_contract_end_date"
										cssClass="form-control" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Term:</label>
							<div class="col-sm-8">
								<sf:textarea rows="4" cols="58" path="amend_contract_term"
									id="amend_contract_term" name="amend_contract_term"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">
								Clauses:</label>
							<div class="col-sm-8">
								<sf:textarea rows="4" cols="58" path="amend_contract_clauses"
									id="amend_contract_clauses" name="amend_contract_clauses"></sf:textarea>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Person
								Responsible :</label>
							<div class="col-sm-8">
								<sf:select path="amend_responsible_person"
									cssClass="form-control">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${user_legal_department}"
										var="user_legal_department">
										<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Person Responsible :</label>
							<div class="col-sm-8">
								<sf:select path="amend_responsible_person"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										 <sf:option value="${editAmendmentContract.amend_responsible_person}">${editAmendmentContract.ref_amend_Fresponsible_person}</sf:option> 
										<c:forEach items="${user_legal_department}"
											var="user_legal_department">
											<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
										</c:forEach>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					<div class="col-md-12" id="div_addParties">
						<!-- Dont delete - Harshad 
							<div id="" class="form-group" style="display:none;">
								<label class="col-sm-3 control-label">Party :</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="additional_parties">
								</div>
								
							</div>
					    End Dont delete -->
						<c:set var="count" scope="page" value="0"></c:set>
						<c:forEach items="${editAmendmentContract.ref_cont_parties}"
							varStatus="loop" var="party">
							<c:set var="count" value="${count+1}"></c:set>
							<div class="col-md-6">
								<div id="party_div_${party.cont_party_id}"
									class="form-group row">
									<label class="col-sm-4 control-label">Party :</label>
									<div class="col-sm-7">
										<sf:input type="hidden" class="form-control"
											path="ref_cont_parties[${loop.index}].cont_party_id"
											name="additional_parties_id" />
										<sf:input type="text" class="form-control"
											path="ref_cont_parties[${loop.index}].cont_party_name"
											name="additional_parties"
											onkeypress="return blockWhiteSpaces(event);" />
										<i class='asterisk_input'></i>
									</div>
									<div style="text-align: right;" class="col-sm-1">
										<i title="Delete" style="color: red; cursor: pointer;"
											onclick="deletePartyAjax(${party.cont_party_id});"
											class="glyphicon glyphicon-remove-circle"></i>
									</div>
								</div>
							</div>
						</c:forEach>
						<input type="hidden" id="total_box" value="${count}">
					</div>
					<c:choose>
						<c:when test="${count!=10}">
							<div class="col-md-12">
								<div class="form-group row">
									<label for="sfctitle" class="col-sm-4 control-label"></label>
									<div class="col-md-8 litigation_buttons" id="add_party_div">
										<button type="button" class="btn btnTaskCompDoc"
											onclick="addPartiesInput();">Add Parties</button>
									</div>
								</div>
							</div>
						</c:when>
					</c:choose>


					<!-- <div class="col-md-12">
					<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addContractFileInput();">Add Contract Document</button>
								</div>
								
							</div>
						</div>
                </div> -->
					
						<div class="col-md-6" id="filesContainer"></div>
					
					<div class="col-md-12">

						<div class="col-md-6">
							<div class="form-group row">
								<label for="inputPassword3" class="col-sm-3 control-label"></label>
								<div class="col-md-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addFileInput();">Document</button>
								</div>
							</div>
						</div>
					</div>
					<div style="clear: both"></div>

					<div class="col-md-12 litigation_buttons">
						<br>
						<center>
							<button type="submit" value="Update" name="Update"
								class="btn btn-success">Update</button>

						</center>
					</div>
				</div>
			</div>

		</sf:form>
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
		}); 
		
		$("#from_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#to_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
		});
		

		$("#to_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#from_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});		
});


var party_count = 1;
var total_box = $("#total_box").val();
function addPartiesInput(){
	//var a = $('input[name*="additional_parties"]').filter(function() {return !!this.value;}).length;
	//console.log("Total lenght "+ total_box);
	if(total_box !=10)
	{
		$('#div_addParties')
		.append(
				"<div class='col-md-6'> <div class='form-group' id='party_"+total_box+"'>"
				+"<label class='col-sm-4 control-label'>Party :</label>"
				+"<div class='col-sm-7'><input type='text' name='additional_parties' class='form-control' onkeypress='return blockWhiteSpaces(event);' required/><i class='asterisk_input'></i>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteParty("+total_box+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div></div>");
		party_count++;
		total_box++;
	}else{
		$("#add_party_div").hide();
	}
	
}
//addPartiesInput(); //Add default party on page load
function deleteParty(party_count) {
	$("#party_" + party_count).remove();
	total_box--;
	$("#total_box").val(total_box);
	if(total_box <=10)
	{ 
		$("#add_party_div").show();
	}
}
function deletePartyAjax(cont_party_id){
	var party_id = cont_party_id;
	items = {};
	items["party_id"] = party_id;
	var jsonString = JSON.stringify(items);
	
		if(party_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deletePreExecutedParty",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Party deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$("#party_div_" + party_id).remove();
				    				total_box--;
				    				$("#total_box").val(total_box);
				    				if(total_box <=10)
				    				{ 
				    					$("#add_party_div").show();
				    				}
				    			}
				    			
				    		}
					 });
				 }
			 });
		}
}

function blockWhiteSpaces( e ) {    
	if (e.which === 32 &&  e.target.selectionStart === 0) {
	      return false;
	    }  
}


function validateForm() {

	var amend_contract_start_date = $("#amend_contract_start_date").val();
	if (amend_contract_start_date == 0) {
	  	 
			$( "#amend_contract_start_date" ).attr( "data-placement", "top" );
			$( "#amend_contract_start_date" ).attr( "data-content", "Please enter Start date." );
			$('#amend_contract_start_date').popover('show');

	    return false;
	}
	 else
	 {
	 	$('#amend_contract_start_date').popover('destroy');
	}


		var amend_responsible_person = $("#amend_responsible_person").val();
		if (amend_responsible_person == 0) {
		  	 
				$( "#amend_responsible_person" ).attr( "data-placement", "top" );
				$( "#amend_responsible_person" ).attr( "data-content", "Please enter Responsible Person ." );
				$('#amend_responsible_person').popover('show');

		    return false;
		}
		 else
		 {
		 	$('#amend_responsible_person').popover('destroy');
		}
		return true;
	}
	
</script>

<script src="appJs/ExecutedContracts/amendmentContract.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
