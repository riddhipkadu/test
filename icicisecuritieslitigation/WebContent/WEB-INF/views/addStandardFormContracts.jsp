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
					<h2 style="color:#032BEC;font-size:24px;float:left;">Standard Form Contract</h2>
					<a href="./listStandardFormContracts"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
<sf:form autocomplete="off" cssClass="form-horizontal" commandName="addStandardFormContracts" enctype="multipart/form-data" onsubmit="return validateForm();" action="./saveStandardFormContracts" method="POST">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			
			<div class="f_form_content">
			<h2>Add Standard Form Contract</h2>
			
				<div class="col-md-10">
				
				<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Entity/Company Name :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_entity_id" name="sfco_entity_id" id="sfco_entity_id">
									   <c:forEach items="${allOrganizations}" var="organization">
								          <option value="${organization.key}">${organization.value}</option>
								       </c:forEach> 
							     </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Unit/Vertical :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_unit_id" id="sfco_unit_id">
									   <option value="0">--Select--</option>
									    
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Function/Location/Department :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_function_id" id="sfco_function_id">
									   <option value="0">--Select--</option>
									   
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					<div class="col-md-12">
					<div class="form-group row">
					<label for="sfctitle" class="control-label col-sm-3">Contract Type :</label>
					<div class="col-md-9">
					<div class="col-md-4">
					<input type="radio" checked="checked" value="Template Clauses" name="sfco_contract_type" id="template_clauses">Template Clauses
					</div>
					<div class="col-md-4">
					<input type="radio" value="Template Contract" name="sfco_contract_type" id="template_contract"> Template Contract<br>
					</div>
					</div>
					<!-- <div class="col-md-3">
					
					</div> -->
					</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-3 control-label">SFC Name :</label>
							<div class="col-sm-6">
								<sf:input path="sfco_name" class="form-control" type="text" name="sfco_name" onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctype" class="col-sm-3 control-label">SFC Type :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_type_id" name="sfco_type_id" id="sfco_type_id" required="true">
								<option value="0">--Select--</option>
								<c:forEach items="${allSfcType}" var="sfcType">
								<option value="${sfcType.sfco_type_id}">${sfcType.sfco_type_name}</option>
								</c:forEach>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Abbreviation :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" id="sfco_abbreviation" path="sfco_abbreviation"
									name="sfco_abbreviation" placeholder="Abbreviation"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
						<!-- <div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Upload Standard Form Contract :</label>
							<div class="col-sm-6">
								<input type="file" 
									id="sfco_doc" name="sfco_doc"
									class="file-loading" />
							</div>
						</div>
					</div> 	 -->
					
					<div class="col-md-12">
					<div id="fileContiner_Contract"></div>
				</div>
				<div class="col-md-12">
                   <div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addContractFileInput();"> Add Standard Form Contract Document</button>
								</div>
								
							</div>
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
</div>

<script src="appJs/StandardFormContract/sfc_validation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
