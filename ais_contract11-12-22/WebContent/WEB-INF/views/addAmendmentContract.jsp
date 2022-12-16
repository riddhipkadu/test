<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->


		<div style="clear: both"></div>

		<sf:form Class="form-horizontal" role="form"
			commandName="addamendment" action="./saveAmendmentContract" 
			method="POST"  enctype="multipart/form-data">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #054eff; font-size: 24px; float: left;">Add Amendment Contract</h2>
					<a
						href="./listAmendmentContract?exec_id=<%=request.getParameter("exec_id")%>"><img
						src="images/DashboardIcons/backold.png" class="backButton"
						width="100px;"></a>
				</div>
			</div>

			<div style="clear: both"></div>


			<div class="f_form_content">
				<h2>Create</h2>

				<div class="col-md-12">


					<div class="col-md-6">
						<div class="form-group" style="display: none;">
							<label for="sfctitle" class="control-label col-sm-5">contract
								id :</label>
							<div class="col-sm-7">
								<input id="amend_exec_contract_id" name="amend_exec_contract_id"
									value="<%=request.getParameter("exec_id")%>" />
							</div>
						</div>

						<div class="form-group">
							<label for="sfctitle" class="control-label col-sm-5">Start
								Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="from_date_div"
									data-date-format="MM">
									<sf:input id="amend_contract_start_date"
										path="amend_contract_start_date" Class="form-control"
										type="text" name="amend_contract_start_date" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>

							</div>
						</div>


						<div class="form-group">
							<label for="sfctitle" class="control-label col-sm-5">Person
								Responsible :</label>
							<div class="col-sm-7">
								<sf:select path="amend_responsible_person" Class="form-control">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${user_legal_department}"
										var="user_legal_department">
										<option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
											${user_legal_department.user_last_name}</option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>


						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Term
								:</label>
							<div class="col-sm-7">
								<sf:textarea class="form-control" rows="4" cols="4"
									path="amend_contract_term" id="amend_contract_term"
									name="amend_contract_term"></sf:textarea>

							</div>
						</div>
					</div>


					<div class="col-md-6">

						<div class="form-group">
							<label for="sfctitle" class="control-label col-sm-5">End
								Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="to_date_div"
									data-date-format="MM">
									<sf:input id="amend_contract_start_date"
										path="amend_contract_end_date" Class="form-control"
										type="text" name="amend_contract_end_date" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>


							</div>
						</div>
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">clauses
								:</label>
							<div class="col-sm-7">
								<sf:textarea class="form-control" rows="4" cols="4"
									path="amend_contract_clauses" id="amend_contract_clauses"
									name="amend_contract_clauses"></sf:textarea>

							</div>
						</div>

						<div class="col-md-12" id="filesContainer"></div>
						
						<div class="col-md-12">
							<div class="form-group row">
								<label for="inputPassword3" class="col-sm-3 control-label"></label>
								<div class="col-md-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addFileInput();">Document</button>
								</div>
							</div>
						</div>


					</div>
					<div class="col-md-12" id="div_addParties"></div>
					<div class="col-md-12" id="error_msg"></div>
					<div class="form-group row">
						<label for="sfctitle" class="col-sm-3 control-label"></label>
						<div class="col-md-7 litigation_buttons" id="add_party_div">
							<button type="button" class="btn btnTaskCompDoc"
								onclick="addPartiesInput();">Add Parties</button>
						</div>
					</div>
					<input type="hidden" id="total_box" value="0">
					<div class="col-md-12 litigation_buttons">
						<br>
						<center>
							<button type="submit" value="Save" name="Save" id="Save" class="btn btn-success">Create</button>

						</center>

					</div>
				</div>

			</div>
		</sf:form>
	</div>
</div>



<script>
	$(document).ready(function() {
		addPartiesInput();
		$(document).on("click", ":submit", function(e) {
			buttonClicked = $(this).val();
			if (buttonClicked == "Save") {
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
			todayHighlight : "true",
			showOnFocus : "true",
			defaultViewDate : "today"

		}).datepicker().on('changeDate', function(e) {

			$('#to_date_div').datepicker({
				autoclose : true
			}).datepicker('setStartDate', e.date).focus();
		});

		$("#to_date_div").datepicker({

			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight : "true",
			showOnFocus : "true",
			defaultViewDate : "today"

		}).datepicker().on('changeDate', function(e) {

			$('#from_date_div').datepicker({
				autoclose : true
			}).datepicker('setEndDate', e.date).focus();
		});
	});
	var party_count = 1;
	var total_box = $("#total_box").val();
	function addPartiesInput() {
		//var a = $('input[name*="additional_parties"]').filter(function() {return !!this.value;}).length;
		//console.log("Total lenght "+ total_box);
		if (total_box != 10) {
			$('#div_addParties')
					.append(
							"<div class='col-md-6'> <div class='form-group' id='party_"+total_box+"'>"
									+ "<label class='col-sm-5 control-label'>Party :</label>"
									+ "<div class='col-sm-6'><input type='text' name='additional_parties' class='form-control' placeholder='Enter Party name' onkeypress='return blockWhiteSpaces(event);'/><i class='asterisk_input'></i>"
									+ "</div>"
									+ "<div class='col-sm-1' style='text-align: right;'>"
									+ "<i class='glyphicon glyphicon-remove-circle' onclick='deleteParty("
									+ total_box
									+ ");' style='color: red;cursor:pointer;' title='Delete'></i>"
									+ "</div></div></div>");
			party_count++;
			total_box++;
		} else {
			$("#add_party_div").hide();
		}
	}
	//addPartiesInput(); //Add default party on page load
	function deleteParty(party_count) {
		$("#party_" + party_count).remove();
		total_box--;
		$("#total_box").val(total_box);
		if (total_box <= 10) {
			$("#add_party_div").show();
		}
	}

	function validateForm() {

		var amend_contract_start_date = $("#amend_contract_start_date").val();
		if (amend_contract_start_date == 0) {

			$("#amend_contract_start_date").attr("data-placement", "top");
			$("#amend_contract_start_date").attr("data-content",
					"Please enter Start date.");
			$('#amend_contract_start_date').popover('show');

			return false;
		} else {
			$('#amend_contract_start_date').popover('destroy');
		}

		var amend_responsible_person = $("#amend_responsible_person").val();
		if (amend_responsible_person == 0) {

			$("#amend_responsible_person").attr("data-placement", "top");
			$("#amend_responsible_person").attr("data-content",
					"Please enter Responsible Person .");
			$('#amend_responsible_person').popover('show');

			return false;
		} else {
			$('#amend_responsible_person').popover('destroy');
		}
		var status = "true";
		//alert(party_added);
		var values  =  $('input[name="additional_parties"]').map(function(){
			if (this.value.length == null || this.value.length == "0" || this.value.length == 0){
				$('input[name="additional_parties"]').attr( "data-placement", "top" );
				$('input[name="additional_parties"]').attr( "data-content", "Please enter the party name." );
				$('input[name="additional_parties"]').popover('show');
			    //return false;
				status = "false";
			}else{
				$(this).popover('destroy');
			}
			});
			if(status =="false"){
				//console.log("In fasle");
				return false;
				
			}else{
				$(this).popover('destroy');
			}
		return true;
	}
	
</script>


<script src="appJs/ExecutedContracts/amendmentContract.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
