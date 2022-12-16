<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<head>
<style>
.popover .fade .right .in {
	top: 2.984px;
}
</style>
</head>

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #054eff; font-size: 24px; float: left;">New
					Executed Contract</h2>
				<a href="./listExecutedContract"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form commandName="addExecutedContract" autocomplete="off"
			action="./saveExecutedContract" onsubmit="return validateForm();"
			method="POST" enctype="multipart/form-data"
			cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			<input type="hidden" name="actionType" id="actionType" />
			<div class="f_form_content">
				<h2>Add Contract</h2>

				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="sel1">Entity/Company
								Name :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" items="${allOrganizations}"
									path="exec_contract_entity_id" id="exec_contract_entity_id">

								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="sel1">Location :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="exec_contract_unit_id">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="dept_id">Department:</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="exec_contract_function_id">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctype_cont_type" class="col-sm-4 control-label">Type
								of Contract :</label>

							<div class="col-sm-8">
								<a href="#" data-toggle="popover" data-placement="top"
									data-content="Please Select Contract Type"> <sf:select
										class="form-control" path="exec_contract_type_id"
										id="exec_contract_type_id">
										<option value="0">--Select--</option>
										<c:forEach items="${allContractType}" var="cont_type">
											<sf:option value="${cont_type.key }">${cont_type.value }</sf:option>
										</c:forEach>
									</sf:select></a><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">

					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Division
								:</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_division" cssClass="form-control">
									<sf:option value="NA">--Select--</sf:option>
									<sf:option value="Animal_Feed">Animal Feed</sf:option>
									<sf:option value="Aqua_Feed">Aqua Feed</sf:option>
									<sf:option value="OMM">OMM</sf:option>
									<sf:option value="Oil_palm">Oil palm</sf:option>
									<sf:option value="Crop_Protection_Business">Crop Protection Business</sf:option>
									<sf:option value="RGC">RGC</sf:option>
									<sf:option value="Yummiez">Yummiez </sf:option>
									<sf:option value="Live Operation">Live Operation</sf:option>
									<sf:option value="Seeds">Seeds</sf:option>
								</sf:select>
							</div>
						</div>
					</div> --%>
					
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Start
								Date :</label>
							<div class="col-sm-8">
								<div id="execution_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" readonly="true"
										path="exec_contract_execution_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Expiry
								Date:</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" readonly="true"
										path="exec_contract_end_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Effective
								Date :</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" readonly="true"
										path="exec_contract_start_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div> --%>
					
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">First Reminder
								Date :</label>
							<div class="col-sm-8">
								<div id="remind_alert_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="exec_remind_date"
										id="exec_remind_date" name="exec_remind_date" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">
								Second Reminder Date :</label>
							<div class="col-sm-8">
								<div id="second_remind_alert_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="exec_remind_date_second"
										id="exec_remind_date_second" name="exec_remind_date_second"
										readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Third
								Reminder Date :</label>
							<div class="col-sm-8">
								<div id="third_remind_alert_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="exec_remind_date_third"
										id="exec_remind_date_third" name="exec_remind_date_third"
										readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div> --%>

					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Renewal
								Date:</label>
							<div class="col-sm-8">
								<div id="renewal_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" readonly="true"
										path="exec_contract_renewal_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
 --%>
				</div>


				<div class="col-md-12" id="div_addParties">

					<!--  <input type="hidden" id="total_box" value="0"> -->
				</div>
				<div class="col-md-12">
					<div class="form-group row">
						<label for="sfctitle" class="col-sm-4 control-label"></label>
						<div class="col-md-8 litigation_buttons" id="add_party_div">
							<button type="button" class="btn btnTaskCompDoc"
								onclick="addPartiesInput();">Add Parties</button>
						</div>
					</div>
				</div>
				
			<%-- 	<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Royalty Submission :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_royalty_number"
									name="exec_royalty_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Options :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_option_number"
									name="exec_option_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Territory :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_territory_number"
									name="exec_territory_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Crop :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_crop_number"
									name="exec_crop_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Technology :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_technology_number"
									name="exec_technology_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Report :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_report_number"
									name="exec_report_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Milestone :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_milestone_number"
									name="exec_milestone_number" placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">
								Assignability :</label>
							<div class="col-sm-8">
								<sf:select path="exec__assignability"
									class="form-control">
									<option value="0">--Select--</option>
									<sf:option value="Yes">Yes</sf:option>
									<sf:option value="No">No</sf:option>
								</sf:select>
								
							</div>
						</div>
					</div>
					
					</div> --%>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Term
								Period of the Agreement (In months) :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_notice_period"
									class="form-control" type="text" />
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Lock-in-period
								:</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_lockin_period"
									class="form-control" type="text" />
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Purpose
								of the Agreement :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_description"
									name="exec_contract_description"
									placeholder="Enter description"></sf:textarea>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Payment
								clause:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_payment"
									name="exec_contract_payment" placeholder="Enter payment clause"></sf:textarea>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>




				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Termination
								Clause:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_termination_clause"
									name="exec_contract_termination_clause"
									placeholder="Enter termination clause"></sf:textarea>
							<!-- 	<i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Indemnity
								Clause :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_damages"
									name="exec_contract_damages"
									placeholder="Enter Indemnity Clause"></sf:textarea>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>

				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Consideration
								involved:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_consider_involve"
									name="exec_contract_consider_involve"
									placeholder="Enter Consideration involved"></sf:textarea>
							<!-- 	<i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Signatories
								to the Agreement :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_signatories_assign"
									name="exec_contract_signatories_assign"
									placeholder="Enter Sign agreement"></sf:textarea>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Jurisdiction/
								governing law :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_jurisdiction"
									name="exec_contract_jurisdiction"
									placeholder="Enter description"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Force
								Majeure</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_force_majeure"
									name="exec_force_majeure" placeholder="Enter force majeure"></sf:textarea>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">

					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">
								Renewal Status:</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_renewal_status"
									class="form-control">
									<option value="0">--Select--</option>
									<option value="Renewed">Renewed</option>
									<option value="Not Renewed">Not Renewed</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
				</div>
				<div class="col-md-12">
					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Any
								connected Amendment/Addendum Letter/renewal letter/ Agreement:</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_ammendment" class="form-control">
									<option value="NA">--Select--</option>
									<option value="Yes">Yes</option>
									<option value="No">No</option>
								</sf:select>
							</div>
						</div>
					</div>
 --%>

				</div>

				<div class="col-md-12">
					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Surviving Obligation :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_surviving_obl"
									name="exec_contract_surviving_obl" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>


					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Seat
								of arbitration :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_arbitration"
									name="exec_contract_arbitration"
									placeholder="Enter seat of arbitration"></sf:textarea>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Insurance
								:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_insurance"
									name="exec_contract_insurance" placeholder="Enter insurance"></sf:textarea>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Opposite
								Party Contact Person :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_executed_contact_name"
									class="form-control" type="text" />
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Contract
								Date :</label>
							<div class="col-sm-8">
								<div id="datepicker1" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true"
										path="exec_contract_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Opposite
								Party Email id :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_cont_person_email"
									class="form-control" type="text" />
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Opposite
								Party Contact number :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_cont_person_number"
									class="form-control" type="text" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Responsible
								Person :</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_resposible_person"
									class="form-control">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>


					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">
								Status of the Agreement:</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_agree_status"
									class="form-control">
									<option value="0">--Select--</option>
									<sf:option value="Contract Continue ">Contract Continue</sf:option>
									<sf:option value="Contract Closed">Contract Terminated</sf:option>
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				
				<!-- ----------------------------------------------START------------------------------------------------- -->
				
				
				
				
				
				
				
				<!-------------------------------------------------STOP-------------------------------------------------- -->
				
				
				<div class="col-md-12" id="filesContainer"></div>

				<!-- <div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Upload File :</label>
							<div class="col-sm-6">
								<input type="file" name="executed_documents"
									class="file-loading"/>
									
							</div>
						</div>
					</div> -->
				<div class="col-md-12">
					<div class="form-group row">
						<label for="inputPassword3" class="col-sm-3 control-label"></label>
						<div class="col-md-6 litigation_buttons">
							<button type="button" class="btn btnTaskCompDoc"
								onclick="addFileInput();">Document</button>
						</div>
					</div>
				</div>

				<div class="col-md-12 litigation_buttons">
					<br>

					<center>
						<button type="submit" value="Save" name="Save"
							class="btn btn-success">Create</button>
						<button type="submit" name="Draft" value="Draft"
							class="btn btn-primary">Save As Draft</button>
					</center>
				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>


<script>
	$(document).ready(function() {
		addPartiesInput();
		addPartiesInput();
		/* function validateDraft(); */

		$(document).on("click", ":submit", function(e) {
			buttonClicked = $(this).val();

			if (buttonClicked == "Save") {
				if (validateForm() != false) {
					$("#actionType").val("Save");
					$("#Save").text("Saving..");
					$("#Save").attr("disabled", "disabled");
					$("#Draft").attr("disabled", "disabled");
					$("form").submit();
					e.preventDefault();
				} else {
					e.preventDefault();
				}
			}
			if (buttonClicked == "Draft") {
				if (validateDraft() != false) {
					$("#actionType").val("Draft");
					$("#Draft").text("Saving..");
					$("#Draft").attr("disabled", "disabled");
					$("#Save").attr("disabled", "disabled");
					$("form").submit();
					e.preventDefault();
				} else {
					e.preventDefault();
				}
			}
			//e.preventDefault();
		});
	});
</script>

<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<script src="appJs/ExecutedContracts/addExecutedContractValidate.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
