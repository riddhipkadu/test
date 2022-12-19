<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<script src="appJs/RequestValidate/contractRequestvalidate.js"></script>

<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->
			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Edit Contract Request</h2>
					<a href="./listContractRequest"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
<sf:form autocomplete="off" role="form" class="form-horizontal" commandName="editContractRequest" enctype="multipart/form-data" action="./updateContractRequest" method="POST">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			
			<div class="f_form_content">
			<h2>Generate Contract Request</h2>
						
						<div class="col-md-4" style="display:none">
							<div class="form-group" >
								<label class="control-label col-sm-4" for="sel1">request id: </label>
								<div class="col-sm-8">
									<sf:input path="req_contract_id" id="req_contract_id" />
								</div>
							</div>
						</div>
					<div class="col-md-12">
			        <div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Entity/Company Name :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="req_contract_entity_id" id="req_contract_entity_id">
									   <c:forEach items="${allOrganizations}" var="organization">
								          <sf:option value="${organization.key}">${organization.value}</sf:option>
								       </c:forEach> 
							     </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Unit/Vertical :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="req_contract_unit_id" id="req_contract_unit_id">
									   <option value="0">--Select--</option>
									   <c:forEach items="${allLocations}" var="loca">
								          <sf:option value="${loca.key}">${loca.value}</sf:option>
								       </c:forEach>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Function/Location/Department :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="req_contract_function_id" id="req_contract_function_id">
									   <option value="0">--Select--</option>
								        <c:forEach items="${allDepartments}" var="dept">
								          <sf:option value="${dept.key}">${dept.value}</sf:option>
								       </c:forEach>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div> 
				
					<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-4 control-label">SFC Name :</label>
							<div class="col-sm-8">
								<sf:input path="sfco_name" class="form-control" type="text" name="sfco_name" onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Contract Type:</label>
							<div class="col-sm-8">
								<sf:select id="req_contract_type" path="req_contract_type" class="form-control">
										<c:forEach items="${allContractType}" var="contra_type">
									    <sf:option value="${contra_type.key}">${contra_type.value}</sf:option>
									</c:forEach>
								</sf:select>
							</div>
						</div>
					</div>
					
					
					
					</div>
					<div class="col-md-12">
					<!-- <div class="col-md-4">
					<label for="sfctitle" class="control-label col-sm-6">Type of Contract :</label>
					</div>
					<div class="col-md-4">
					<input type="radio" checked="checked" value="Actual Contract" name="contract_type" id="actual_contract"> Actual Contract<br>
					</div>
					<div class="col-md-4">
					<input type="radio" value="Template" name="contract_type" id="template_contract"> Template<br>
					</div> -->
					
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Type of Contract :</label>
							<div class="col-sm-8">
								<select id="contract_type" class="form-control">
										<option value="actual_contract">Actual Contract</option>
										<option value="template_contract">Template</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Expected Date :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="datepicker3" data-date-format="MM">
									<sf:input class="form-control" type="text" id="req_contract_expected_date" 
										   path="req_contract_expected_date" readonly="true" placeholder="Enter Expected date"/>
										   <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12" id="actual_cont_div">
					<div class="col-md-12">
					
					<!-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Contract name :</label>
							<div class="col-sm-8">
								<input id="req_contract_name" name="req_contract_name" class="form-control" placeholder="Enter contract name" />
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>  -->
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Number of Parties :</label>
							<div class="col-sm-8">
								<sf:select id="req_party_no" path="req_party_no" class="form-control">
										<!-- <option value="0">--Select--</option> -->
										<sf:option value="1">1</sf:option>
										<sf:option value="2">2</sf:option>
										<sf:option value="3">3</sf:option>
										<sf:option value="4">4</sf:option>
										<sf:option value="5">5</sf:option>
										<sf:option value="6">6</sf:option>
										<sf:option value="7">7</sf:option>
										<sf:option value="8">8</sf:option>
										<sf:option value="9">9</sf:option>
										<sf:option value="10">10</sf:option>
								</sf:select>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12" id="div_addParties">
                       <!-- Dont delete  - added by Harshad 
							<div id="" class="form-group" style="display:none;">
								<label class="col-sm-3 control-label">Party :</label>
								<div class="col-sm-6">
									<input type="text" class="form-control"
										name="additional_parties">
								</div>
								
							</div>
					   End Dont delete -->
					<c:set var="count" scope="page" value="0"></c:set>
					<c:forEach items="${editContractRequest.cont_parties}" varStatus="loop"
						var="party">
						<c:set var="count" value="${count+1}"></c:set>
						<div class="col-md-6">
							<div id="party_div_${count}" class="form-group">
								<label class="col-sm-4 control-label">Party :</label>
								<div class="col-sm-7">
									<sf:input type="hidden" class="form-control"
										path="cont_parties[${loop.index}].cont_party_id"
										name="additional_parties_id" />
									<sf:input type="text" class="form-control"
										path="cont_parties[${loop.index}].cont_party_name"
										name="additional_parties" onkeypress="return blockWhiteSpaces(event);" required="true"/><i class='asterisk_input'></i>
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
					<div class="col-md-12" id="error_msg">
				
				</div>
				<!-- <div class="col-md-12">
					<div class="form-group row">
							<label for="sfctitle" class="col-sm-3 control-label"></label>
							<div class="col-md-6 litigation_buttons" id="add_party_div">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addPartiesInput();">Add Parties</button>
							</div>
					</div>
					<input type="hidden" id="total_box" value="0">
				</div> -->

					<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctype" class="col-sm-4 control-label">SFC Type :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_type_id" name="sfco_type_id" id="sfco_type_id">
									<!-- <option value="0">--Select--</option> -->
								<c:forEach items="${allSfcType}" var="sfcType">
								<option value="${sfcType.sfco_type_id}">${sfcType.sfco_type_name}</option>
								</c:forEach>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Start Date :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="from_date_div" data-date-format="MM">
									<sf:input class="form-control" type="text"  id ="req_contract_start_date"
										   path="req_contract_start_date" readonly="true" placeholder="Enter Start date"/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">End Date :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="to_date_div" data-date-format="MM">
									<sf:input class="form-control" type="text" id="req_contract_end_date" 
										   path="req_contract_end_date" readonly="true" placeholder="Enter End date"/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
					
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Email Address : </label>
							<div class="col-sm-8">
								<sf:input id="req_contract_email_id" path="req_contract_email_id" class="form-control" placeholder="Enter Email Id"></sf:input>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Criticality : </label>
							<div class="col-sm-8">
								<sf:select path="req_contract_criticality"
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
							<label class="control-label col-sm-4" for="sel1"> Jurisdiction: </label>
							<div class="col-sm-8">
								<sf:input id="req_contract_jurisdiction" path="req_contract_jurisdiction" class="form-control" placeholder="Enter Jurisdiction"></sf:input>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Total value of Contract: </label>
							<div class="col-sm-8">
								<sf:input id="req_contract_total_value" path="req_contract_total_value" class="form-control" placeholder="Enter Total value of Contract"></sf:input>
							</div>
						</div>
					</div>
				  </div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Purpose of Agreement:</label>
							<div class="col-sm-8">
								<sf:textarea class="form-control" id="req_contract_desc" rows="2" cols="29" 
									path="req_contract_desc" placeholder="Enter Purpose of Agreement"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Major Clauses :</label>
							<div class="col-sm-8" id="radio_major_clause">
							<div class="col-sm-4">
							<input type="radio" value="Yes" name="major_clause_result" id="major_yes"> Yes<br>
							</div>
							<div class="col-sm-4">
							<input type="radio" checked="checked" value="No" name="major_clause_result" id="major_no"> No<br>
							</div>
							</div>
							<div class="col-sm-8" id="major_clause" style="display: none;">
								<sf:textarea class="form-control" id="req_contract_major_clause" rows="2" cols="29" 
									path="req_contract_major_clause" placeholder="Enter Major Clause"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Any Surviving Clause:</label>
							<div class="col-sm-8" id="radio_surviving_clause">
							<div class="col-sm-4">
							<input type="radio" value="Yes" name="surviving_clause_result" id="surviving_yes"> Yes<br>
							</div>
							<div class="col-sm-4">
							<input type="radio" checked="checked" value="No" name="surviving_clause_result" id="surviving_no"> No<br>
							</div>
							</div>
							<div class="col-sm-8" id="surviving_clause" style="display: none;">
								<sf:textarea class="form-control" id="req_contract_surviving_clause" rows="2" cols="29" 
									path="req_contract_surviving_clause" placeholder="Enter Surviving Clause"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Performance Related Obligation/Payment :</label>
							<div class="col-sm-8" id="radio_payment">
							<div class="col-sm-4">
							<input type="radio" value="Yes" name="payment_result" id="payment_yes"> Yes<br>
							</div>
							<div class="col-sm-4">
							<input type="radio" checked="checked" value="No" name="payment_result" id="payment_no"> No<br>
							</div>
							</div>
							<div class="col-sm-8" id="payment_div" style="display: none;">
							<sf:input id="req_contract_perform_rel_payment" path="req_contract_perform_rel_payment" class="form-control" placeholder="Enter Payment" />
							     <i class="asterisk_input"></i>
								
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Insurance:</label>
							<div class="col-sm-8" id="radio_insurance">
							<div class="col-sm-4">
							<input type="radio" value="Yes" name="insurance_result" id="insurance_yes"> Yes<br>
							</div>
							<div class="col-sm-4">
							<input type="radio" checked="checked" value="No" name="insurance_result" id="insurance_no"> No<br>
							</div>
							</div>
							<div class="col-sm-8" id="insurance_div" style="display: none;">
								<sf:textarea class="form-control" id="req_contract_insurance" rows="2" cols="29" 
									path="req_contract_insurance" placeholder="Enter Insurance"></sf:textarea>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Notice period (In months) :</label>
							<div class="col-sm-8" id="radio_notice_period">
							<div class="col-sm-4">
							<input type="radio" value="Yes" name="notice_period_result" id="notice_yes"> Yes<br>
							</div>
							<div class="col-sm-4">
							<input type="radio" checked="checked" value="No" name="notice_period_result" id="notice_no"> No<br>
							</div>
							</div>
							<div class="col-sm-8" id="notice_period_div" style="display: none;">
							<sf:input id="req_contract_notice_period" path="req_contract_notice_period" class="form-control" onkeypress="return isNumber(event)" placeholder="Enter Notice Period"/>
							     
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-4 control-label">Any Damage :</label>
							<div class="col-sm-8" id="radio_damages">
							<div class="col-sm-4">
							<input type="radio" value="Yes" name="damages_result" id="damages_yes"> Yes<br>
							</div>
							<div class="col-sm-4">
							<input type="radio" checked="checked" value="No" name="damages_result" id="damages_no"> No<br>
							</div>
							</div>
							<div class="col-sm-8" id="damages_div" style="display: none;">
								<sf:textarea class="form-control" id="req_contract_damage" rows="2" cols="29" 
									path="req_contract_damage" placeholder="Enter Damages"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Term Sheet Document:</label>
							<div class="col-sm-8">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="term_sheet_document" ><br />
								</div>
							</div>
						</div>
					</div>
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
										onclick="addContractFileInput();"> Add Contract Document</button>
								</div>
								
							</div>
					</div>
                </div>
				
				<div class="col-md-12 litigation_buttons">
				<br>
					<center>
					<button type="submit" name="Save" value="Save" class="btn btn-success">Create</button>
					<button type="submit" name="Draft" value="Draft" class="btn btn-primary">Save As Draft</button> 
					</center>
				</div>
				</div>
				</sf:form>		
			
			<div style="clear:both"></div>
</div>
</div>

<script>

 $(document).ready(function(){
	 //alert("Party is: "+'${party}');
	 var party = '${party}';
	 var party_no = '${party_no}';
	 
	 if(party == "[]"){
		for(var i= 1; i < party_no; i++){
			addPartiesInput();
		} 
	 }
	//addPartiesInput();
	
	if('${major_claus}' != 'No'){
		$('#major_clause').show();	
		$("#major_yes").prop("checked", true);
	}else{
		$('#major_clause').hide();
	}

	if('${insurance}' != 'No'){
		$('#insurance_div').show();	
		$("#insurance_yes").prop("checked", true);
	}else{
		$('#insurance_div').hide();
	}
	
	if('${surviving_claus}' != 'No' ){
		$('#surviving_clause').show();	
		$("#surviving_yes").prop("checked", true);
	}else{
		$('#surviving_clause').hide();
	}

	if('${payment}' != 'No'){
		$('#payment_div').show();	
		$("#payment_yes").prop("checked", true);
	}else{
		$('#payment_div').hide();
	}
	
	if('${notice_period}' != 'No'){
		$('#notice_period_div').show();	
		$("#notice_yes").prop("checked", true);
		//alert('${notice_period}');
	}else{
		$('#notice_period_div').hide();
	}

	if('${damages}' != 'No'){
		$('#damages_div').show();	
		$("#damages_yes").prop("checked", true);
	}else{
		$('#damages_div').hide();
	}
	
	
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
 
 $('#contract_type').on('click', function() {
	 //alert("onclick");
	 var e = document.getElementById("contract_type");
	 var strUser = e.options[e.selectedIndex].text;
	 
	 if(strUser == 'Template'){
		 document.getElementById("actual_cont_div").style.display = 'none';
	 } else{
		 document.getElementById("actual_cont_div").style.display = 'block';
	 }
 });
 
 $('#req_party_no').on('change', numberParty);
 
 $('input[name="major_clause_result"]').on('click', function() {
	 if($(this).val() == 'Yes'){
		  $('#major_clause').show();	
	 } else {
		  $('#major_clause').hide();
	 }
 });
 
 $('input[name="surviving_clause_result"]').on('click', function() {
	 if($(this).val() == 'Yes'){
		  $('#surviving_clause').show();	
	 } else {
		  $('#surviving_clause').hide();
	 }
 });
 
 $('input[name="payment_result"]').on('click', function() {
	 if($(this).val() == 'Yes'){
		  $('#payment_div').show();	
	 } else {
		  $('#payment_div').hide();
	 }	 
 });
 
 $('input[name="damages_result"]').on('click', function() {
	 if($(this).val() == 'Yes'){
		  $('#damages_div').show();	
	 } else {
		  $('#damages_div').hide();
	 }	 
 });
 
 $('input[name="insurance_result"]').on('click', function() {
	 if($(this).val() == 'Yes'){
		  $('#insurance_div').show();	
	 } else {
		  $('#insurance_div').hide();
	 }	 
 });
 
 $('input[name="notice_period_result"]').on('click', function() {
	 if($(this).val() == 'Yes'){
		  $('#notice_period_div').show();	
	 } else {
		  $('#notice_period_div').hide();
	 }	 
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
				"<div class='col-md-6'> <div class='form-group row' id='party_"+total_box+"'>"
				+"<label class='col-sm-4 control-label'>Party :</label>"
				+"<div class='col-sm-7'><input type='text' name='additional_parties' class='form-control' onkeypress='return blockWhiteSpaces(event);' placeholder='Enter Party Name'/><i class='asterisk_input'></i>"
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

function numberParty() {
	 //addPartiesInput(); 
	var count = document.getElementById("req_party_no");
	var selectedNum = count.options[count.selectedIndex].value;
	//alert("party is: "+sel);
 var numSelected = Number(selectedNum);
 appendControls(numSelected);
}

function appendControls(num) {
	var party_count = 1;
	var total_box = $("#total_box").val();
 $('#div_addParties').empty();
 for (var i=1; i<=num; i++) {
	 $('#div_addParties')
		.append(
				"<div class='col-md-6'> <div class='form-group row' id='party_"+total_box+"'>"
				+"<label class='col-sm-4 control-label'>Party :</label>"
				+"<div class='col-sm-7'><input type='text' name='additional_parties' class='form-control' onkeypress='return blockWhiteSpaces(event);' placeholder='Enter Party Name'/><i class='asterisk_input'></i>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteParty("+total_box+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div></div>");
	 	party_count++;
		total_box++;
 } 
}

var termFileCount = 1;
function addContractFileInput(){
	$("#fileContiner_Contract").append('<div class="col-md-6" id="termFile'+termFileCount+'">'
			+'<div class="form-group">'
	        +'<label class="col-sm-4 control-label">document:</label>'
	        +'<div class="col-sm-2">'
	        +'<input type="file" name="contract_document" class="file-loading" />'
	        +'</div>'
	        +'<div class="col-sm-4" style="text-align: right; cursor: pointer;">'
	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteTermRow('+termFileCount+');" style="color: red;" title="Delete"></i>'
	        +'</div>'
	        +'</div>'
	        +'</div>'
			);
	termFileCount++;
}
function deleteTermRow(filecount) {
	$("#termFile" + filecount).remove();
}

</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
