<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #032BEC; font-size: 24px; float: left;">Edit
					Executed Contract</h2>
				<a href="./listExecutedContract"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form commandName="editExecutedContract" autocomplete="off"
			action="./updateExecutedContract" method="POST"
			enctype="multipart/form-data" onsubmit="return validateForm();"
			cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>

			<div class="f_form_content">
				<h2>Edit Contract</h2>

				<div class="col-md-12" style="display: none">
					<div class="form-group row">
						<label class="control-label col-sm-3" for="sel1">ID :</label>
						<div class="col-sm-6">
							<sf:input class="form-control" path="exec_contract_id"
								id="exec_contract_id" />
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-sm-6">
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
					<div class="col-sm-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="sel1">Unit/Vertical
								:</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="exec_contract_unit_id"
									items="${allLocations}">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-sm-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="dept_id">Function/Location
								/Department:</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="exec_contract_function_id"
									items="${allDepartments}">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctype" class="col-sm-4 control-label">Contract
								Type :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" items="${allContractType}"
									path="exec_contract_type_id" id="exec_contract_type_id">

								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Start
								Date :</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true"
										path="exec_contract_start_date_name" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">End
								Date :</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true"
										path="exec_contract_end_date_name" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Surviving
								Obligation :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_surviving_obl"
									name="exec_contract_surviving_obl"
									placeholder="Enter description"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Insurance
								:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_insurance"
									name="exec_contract_insurance" placeholder="Enter description"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Performance
								related obligation & payment:</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_payment" class="form-control"
									type="text" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Notice
								Period (In months) :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_notice_period"
									class="form-control" type="text" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>


				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Damages
								:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_damages"
									name="exec_contract_damages" placeholder="Enter description"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Jurisdiction/
								governing law :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_jurisdiction"
									name="exec_contract_jurisdiction"
									placeholder="Enter description"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Seat
								of arbitration :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_arbitration"
									name="exec_contract_arbitration"
									placeholder="Enter description"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Criticality
								:</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_criticality" class="form-control">
									<sf:option value="0">--Select--</sf:option>
									<sf:option value="Low">Low</sf:option>
									<sf:option value="Medium">Medium</sf:option>
									<sf:option value="High">High</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-sm-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Description
								:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control"
									path="exec_contract_description"
									name="exec_contract_description"
									placeholder="Enter description"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Responsible
								Person :</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_resposible_person"
									class="form-control">
									<option value="0">--Select--</option>
									<c:forEach items="${user_list}" var="user_list">
										<sf:option value="${user_list.user_id}">${user_list.user_first_name} ${user_list.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				
				<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-3 control-label">Executed With :</label>
							<div class="col-sm-6">
								<sf:input path="exec_contract_executed_with" class="form-control" type="text" /><i class="asterisk_input"></i>
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
					<c:forEach items="${editExecutedContract.exec_parties}"
						varStatus="loop" var="party">
						<c:set var="count" value="${count+1}"></c:set>
						<div class="col-md-6">
							<div id="party_div_${count}" class="form-group row">
								<label class="col-sm-4 control-label">Party :</label>
								<div class="col-sm-7">
									<sf:input type="hidden" class="form-control"
										path="exec_parties[${loop.index}].cont_party_id"
										name="additional_parties_id" />
									<sf:input type="text" class="form-control"
										path="exec_parties[${loop.index}].cont_party_name"
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

				<div class="col-md-12">
					<div class="col-sm-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Contact
								Person :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_contact_person"
									class="form-control" type="text" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Contract
								Date :</label>
							<div class="col-sm-8">
								<div id="datepicker1" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true"
										path="exec_contract_date_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Email
								id :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_cont_person_email"
									class="form-control" type="text" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Contact
								number :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_cont_person_number"
									class="form-control" type="text" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>

				</div>

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
						<label for="inputPassword3" class="col-sm-4 control-label"></label>
						<div class="col-md-8 litigation_buttons">
							<button type="button" class="btn btnTaskCompDoc"
								onclick="addFileInput();">Document</button>
						</div>
					</div>
				</div>







				<div class="col-md-12 litigation_buttons">
					<br>

					<center>
						<input type="submit" value="Update" class="btn btn-myPrimary" />
						<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listDepartments' " >Back</button> -->
					</center>
				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>

<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<script src="appJs/ExecutedContracts/editExecutedContractValidate.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
