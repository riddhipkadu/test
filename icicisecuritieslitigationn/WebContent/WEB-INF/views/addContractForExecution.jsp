<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->
        
		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #032BEC; font-size: 24px; float: left;">Initiate
					New Contract</h2>
				<a href="./listPreExecutionSummary"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form commandName="newContract" enctype="multipart/form-data" action="./saveContractForExecution" cssClass="form-horizontal">

			<div class="f_form_content">
				<h2>New Contract</h2>

				<!-- <div class="col-md-12"> -->
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity/Company Name :</label>
							<div class="col-sm-8">
								<sf:select path="cont_orga_id" items="${allOrganizations}"
										cssClass="form-control">
									</sf:select><i class="asterisk_input"></i>

							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Unit/Vertical :</label>
							<div class="col-sm-8">
								<sf:select path="cont_loca_id" cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
									</sf:select><i class="asterisk_input"></i>

							</div>
						</div>
					</div>

				</div>

				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Function/Location /Department:</label>
							<div class="col-sm-8">
								<sf:select path="cont_dept_id" cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
									</sf:select><i class="asterisk_input"></i>

							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Document Name :</label>
							<div class="col-sm-8">
								<sf:input path="cont_agreement_name" cssClass="form-control"
										placeholder="Enter Agreement Name" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Requested
								Date:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="datepicker1"
										data-date-format="MM">
										<sf:input path="cont_requested_date" cssClass="form-control" readonly="true" placeholder="Enter Requested Date"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
								</div>
									<i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Requested
								By :</label>
							<div class="col-sm-8">
									<sf:select path="cont_requested_by_user_id"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<c:forEach items="${user_legal_department}" var="user_legal_department">
										<option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
										${user_legal_department.user_last_name}</option>
							  			</c:forEach>
									</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12">
				<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Start
								Date:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="from_date_div"
										data-date-format="MM">
										<sf:input path="cont_start_date" cssClass="form-control" readonly="true" placeholder="Enter Start Date"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">End
								Date:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="to_date_div"
										data-date-format="MM">
										<sf:input path="cont_end_date" cssClass="form-control" readonly="true" placeholder="Enter End Date"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" id="div_addParties">

					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Party 1
								:</label>
							<div class="col-sm-8">
								<sf:input path="cont_party_one" cssClass="form-control"
										placeholder="Enter Party One" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Party 2
								:</label>
							<div class="col-sm-8">
								<sf:input path="cont_party_two" cssClass="form-control"
								placeholder="Enter Party Two" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					 
				</div>
				<div class="col-md-12" id="error_msg">
				
				</div>
				<div class="col-md-12">
					<div class="form-group row">
							<label for="sfctitle" class="col-sm-3 control-label"></label>
							<div class="col-md-6 litigation_buttons" id="add_party_div">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addPartiesInput();">Add Parties</button>
							</div>
					</div>
					<input type="hidden" id="total_box" value="0">
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Type of Document :</label>
							<div class="col-sm-8">
								<select class="form-control" multiple="multiple" id="cont_type_of_contract" name="cont_type_of_contract" required >
									<c:forEach items="${allContractType}" var="contra_type">
									      <option value="${contra_type.key}">${contra_type.value}</option>
									</c:forEach>
								</select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<%-- <div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Purpose
								:</label>
							<div class="col-sm-8">
								<sf:input path="cont_purpose" cssClass="form-control"
										placeholder="Enter Purpose" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Payment:</label>
							<div class="col-sm-8">
								<sf:input path="cont_payment" cssClass="form-control" placeholder="Enter Payment" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Expected
								Date:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="datepicker2"
										data-date-format="MM">
										<sf:input path="cont_expected_date" cssClass="form-control" readonly="true" placeholder="Enter Expected Date"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Person Responsible :</label>
							<div class="col-sm-8">
								<sf:select path="cont_responsible_user_id"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										
									</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>


				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Targeted
								Date:</label>
							<div class="col-sm-8">
									<div class="input-group date" id="to_date_div1"
										data-date-format="MM">
										<sf:input path="cont_targetted_date" cssClass="form-control" readonly="true" placeholder="Enter Targeted Date"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
									</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Reminder 
								Date:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="from_date_div1"
										data-date-format="MM">
										<sf:input path="cont_reminder_date" cssClass="form-control" readonly="true" placeholder="Enter Reminder Date"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Criticality :</label>
							<div class="col-sm-8">
								<sf:select path="cont_criticality"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<sf:option value="High">High</sf:option>
										<sf:option value="Medium">Medium</sf:option>
										<sf:option value="Low">Low</sf:option>
										
									</sf:select>
							</div>
						</div>
					</div>
					
					
				</div>
				
				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Nature & Scope:</label>
							<div class="col-sm-8">
								<sf:textarea rows="3" cols="56" path="cont_nature" id="cont_nature" name="cont_nature" placeholder="Enter Nature and Scope"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Term:</label>
							<div class="col-sm-8">
								<sf:textarea rows="3" cols="56" path="cont_term" id="cont_term" name="cont_term" placeholder="Enter Terms"></sf:textarea>
							</div>
						</div>
					</div>
					
					
				</div>
				
				<div class="col-md-12">

					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Surviving Clauses:</label>
							<div class="col-sm-8">
								<sf:textarea rows="3" cols="56" path="cont_surviving_clause" id="cont_surviving_clause" name="cont_surviving_clause" placeholder="Enter Surviving clauses"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Any other major clauses:</label>
							<div class="col-sm-8">
								<sf:textarea rows="3" cols="56" path="cont_major_clause" id="cont_major_clause" name="cont_major_clause" placeholder="Enter Major clauses"></sf:textarea>
							</div>
						</div>
					</div>
					
				</div>
				<div class="col-md-12">

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Any Damages :</label>
							<div class="col-sm-8">
								<sf:textarea rows="3" cols="56" path="cont_damages" id="cont_damages" name="cont_damages" placeholder="Enter Damages"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Any Instructions :</label>
							<div class="col-sm-8">
								<sf:textarea rows="3" cols="56" path="cont_instructions" placeholder="Enter Instructions"></sf:textarea>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div id="fileContiner_TermSheet"></div>
				</div>
				<div class="col-md-12">
					<div id="fileContiner_Contract"></div>
				</div>
				<div class="col-md-12">
                   <div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addTermFileInput();"> Add Term Sheet Document</button>
								</div>
								
							</div>
					</div>
					
                </div>
                <div class="col-md-12">
					<div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addContractFileInput();">Add Contract Document</button>
								</div>
								
							</div>
						</div>
                </div>
				<div style="clear: both"></div>
				
				<div class="col-md-12 litigation_buttons">
					<br>
					<center>
						<button type="submit" value="Save" name="Save" class="btn btn-success">Create</button>
						<button type="submit" name="Draft" value="Draft" class="btn btn-primary">Save As Draft</button>
					</center>

				</div>
			</div>
		</sf:form>

	</div>
</div>
<script src="appJs/PreExecutionContracts/contract.js"></script>
<script src="appJs/PreExecutionContracts/addContractValidate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	addPartiesInput();
	addPartiesInput();
	
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

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
