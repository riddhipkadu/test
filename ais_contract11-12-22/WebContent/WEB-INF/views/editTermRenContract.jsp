<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->


		<div style="clear: both"></div>

		<sf:form Class="form-horizontal" role="form" commandName="editTermRenContract"
			action="./saveTermRenContract" method="POST" enctype="multipart/form-data">

			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #054eff; font-size: 24px; float: left;">Edit
						Termination/Renewal Contract</h2>
					<a
						href="./listTermRenContract?exec_id=<%=request.getParameter("exec_id")%>"><img
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
								<sf:input id="termren_contract_id"
									path="termren_contract_id"
									 />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Status :</label>
							<div class="col-sm-7">
								<sf:select path="termren_contract_status"
									cssClass="form-control">
									<sf:option value="NA">--Select--</sf:option>
									<sf:option value="Contract Terminated">Contract Terminated</sf:option>
									<sf:option value="Contract Closed">Contract Closed</sf:option>
									<sf:option value="Contract Renewal">Contract Renewal</sf:option>

								</sf:select>
							</div>
						</div>



						<div class="form-group">
							<label for="sfctitle" class="control-label col-sm-5">Person
								Responsible :</label>
							<div class="col-sm-7">
								<sf:select path="termren_responsible_person"
									Class="form-control">
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


						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Term
								:</label>
							<div class="col-sm-7">
								<sf:textarea class="form-control" rows="3" cols="4"
									path="termren_contract_term" id="termren_contract_term"
									name="termren_contract_term"></sf:textarea>

							</div>
						</div>
					</div>


					<div class="col-md-6">
						<div class="form-group">
							<label for="sfctitle" class="control-label col-sm-5">Start
								Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="from_date_div"
									data-date-format="MM">
									<sf:input id="termren_contract_start_date"
										path="termren_contract_start_date" Class="form-control"
										type="text" name="termren_contract_start_date" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>

							</div>
						</div>

						<div class="form-group">
							<label for="sfctitle" class="control-label col-sm-5">End
								Date :</label>
							<div class="col-sm-7">
								<div class="input-group date" id="to_date_div"
									data-date-format="MM">
									<sf:input id="termren_contract_start_date"
										path="termren_contract_end_date" Class="form-control"
										type="text" name="termren_contract_end_date" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>


							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-sm-5">clauses :</label>
							<div class="col-sm-7">
								<sf:textarea class="form-control" rows="3" cols="4"
									path="termren_contract_clauses" id="termren_contract_clauses"
									name="termren_contract_clauses"></sf:textarea>

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

					<div class="col-md-12 litigation_buttons">
						<br>
						<center>
							<button type="submit" value="Save" name="Save" id="Save"
								class="btn btn-success">Create</button>

						</center>

					</div>
				</div>

			</div>
		</sf:form>
	</div>
</div>



<script>
	$(document).ready(function() {
	
		$(document).on("click", ":submit", function(e) {
			buttonClicked = $(this).val();
			if (buttonClicked == "Save") {
				if (validateForm() != false) {
					$("form").submit();
				} else {
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
	
	var filecount = 1;
	function addFileInput() {
	   
		$('#filesContainer')
		.append(
				"<div class='form-group' id='file"+filecount+"'>"
				+"<label class='col-sm-5 control-label'>Upload Document :</label>"
				+"<div class='col-sm-6'><input type='file' name='termrenw_documents' class='file-loading'/>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteRow("+filecount+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div>");
		filecount++;
	   
	}

	function deleteRow(filecount) {
		$("#file" + filecount).remove();
	}

	function validateForm() {
		termren_contract_status

		var termren_contract_start_date = $("#termren_contract_start_date")
				.val();
		if (termren_contract_start_date == 0) {

			$("#termren_contract_start_date").attr("data-placement", "top");
			$("#termren_contract_start_date").attr("data-content",
					"Please enter Start date.");
			$('#termren_contract_start_date').popover('show');

			return false;
		} else {
			$('#termren_contract_start_date').popover('destroy');
		}

		var termren_responsible_person = $("#termren_responsible_person").val();
		if (termren_responsible_person == 0) {

			$("#termren_responsible_person").attr("data-placement", "top");
			$("#termren_responsible_person").attr("data-content",
					"Please enter Responsible Person .");
			$('#termren_responsible_person').popover('show');

			return false;
		} else {
			$('#termren_responsible_person').popover('destroy');
		}

		return true;
	}
</script>


<!-- <script src="appJs/ExecutedContracts/termrenmentContract.js"></script> -->
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
