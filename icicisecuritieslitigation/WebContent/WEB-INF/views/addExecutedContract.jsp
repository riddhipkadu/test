<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->
			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">New Executed Contract</h2>
					<a href="./listExecutedContract"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addExecutedContract" autocomplete="off" action="./saveExecutedContract" method="POST" enctype="multipart/form-data" onsubmit="return validateForm();" cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Add Contract</h2>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="sel1">Entity/Company Name :</label>
							<div class="col-sm-8">
								<sf:select  class="form-control" items="${allOrganizations}" path="exec_contract_entity_id" id="exec_contract_entity_id">
								
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="sel1">Unit/Vertical :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="exec_contract_unit_id">
									<option value="0">--Select--</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label class="control-label col-sm-4" for="dept_id">Function/Location /Department:</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="exec_contract_function_id">
									<option value="0">--Select--</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctype" class="col-sm-4 control-label">Contract Type :</label>
							<div class="col-sm-8">
								<sf:select class="form-control"  path="exec_contract_type_id" id="exec_contract_type_id">
									<option value="0">--Select--</option>
									<c:forEach items="${allContractType}" var="cont_type">
									<sf:option value="${cont_type.key }">${cont_type.value }</sf:option>
									</c:forEach>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Start Date :</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="exec_contract_start_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">End Date :</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="exec_contract_end_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Surviving Obligation :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_surviving_obl"
									name="exec_contract_surviving_obl" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Insurance :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_insurance"
									name="exec_contract_insurance" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Performance related obligation & payment:</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_payment" class="form-control" type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Notice Period (In months) :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_notice_period" class="form-control" type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Damages :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_damages"
									name="exec_contract_damages" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Jurisdiction/ governing law :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_jurisdiction"
									name="exec_contract_jurisdiction" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Seat of arbitration :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_arbitration"
									name="exec_contract_arbitration" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Criticality :</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_criticality" class="form-control" >
								   <option value="0">--Select--</option>
								   <option value="Low">Low</option>
								   <option value="Medium">Medium</option>
								   <option value="High">High</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Description :</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" path="exec_contract_description"
									name="exec_contract_description" placeholder="Enter description"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Responsible Person :</label>
							<div class="col-sm-8">
								<sf:select path="exec_contract_resposible_person" class="form-control" >
								   <option value="0">--Select--</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
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
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Contact Person :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_executed_contact_name" class="form-control" type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Contract Date :</label>
							<div class="col-sm-8">
								<div id="datepicker1" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="exec_contract_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Email id :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_cont_person_email" class="form-control" type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">Contact number :</label>
							<div class="col-sm-8">
								<sf:input path="exec_contract_cont_person_number" class="form-control" type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				
				</div>
					<div class="col-md-12" id="filesContainer">
					
					</div>
					
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
					<input type="submit" value="Add" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listDepartments' " >Back</button> -->
					</center>
				</div>
				</div>
				</sf:form>		
			
			<div style="clear:both"></div>
			
</div>


<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<script src="appJs/ExecutedContracts/addExecutedContractValidate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	addPartiesInput();
	addPartiesInput();
	
});
</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
