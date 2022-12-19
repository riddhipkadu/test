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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Standard Form Contracts</h2>
					<a href="./listStandardFormContracts"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	
	<sf:form commandName="editStandardFormContracts" autocomplete="off" cssClass="form-horizontal" enctype="multipart/form-data" method="post" onsubmit="return validateForm();"
				action="./updateStandardFormContracts" >
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
				<div class="f_form_content">
			<h2>Update Standard Form Contracts</h2>
			
				<div class="col-md-10">
				
					<div class="col-md-12" style="display : none;">
						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Standard Form Contract Id:</label>
							<div class="col-sm-6">
								<sf:input path="sfco_id" cssClass="form-control" />
								<sf:errors path="sfco_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
				<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Entity/Company Name :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_entity_id" name="sfco_entity_id" id="sfco_entity_id">
									   <c:forEach items="${allOrganizations}" var="organization">
								          <sf:option value="${organization.key}">${organization.value}</sf:option>
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
									    <c:forEach items="${location}" var="loca">
								          <sf:option value="${loca.key}">${loca.value}</sf:option>
								       </c:forEach> 
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Function/Location/Department :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_function_id" id="sfco_function_id">
									   <c:forEach items="${department}" var="dept">
								          <sf:option value="${dept.key}">${dept.value}</sf:option>
								       </c:forEach> 
									   
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					<div class="col-md-12">
					<div class="form-group row">
					<label for="sfctitle" class="control-label col-sm-3">Contract Type :</label>
					<div class="col-md-9">
					<div class="col-md-4">
					<input type="radio" value="Template Clauses" name="sfco_contract_type" id="template_clauses">Template Clauses
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
						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">SFC Name:</label>
							<div class="col-sm-6">
								<sf:input path="sfco_name" cssClass="form-control" id='sfco_name' onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
								<sf:errors path="sfco_name" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">SFC Type :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sfco_type_id" >
 								<sf:option value="0">--Select--</sf:option>
								<c:forEach items="${allSfcType}" var="sfcType">
								<sf:option value="${sfcType.sfco_type_id}">${sfcType.sfco_type_name}</sf:option>
								</c:forEach>
							     </sf:select>
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Abbreviation :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" path="sfco_abbreviation" id="sfco_abbreviation" name="sfco_abbreviation" placeholder="Abbreviation"></sf:textarea>
							<i class="asterisk_input"></i>
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
					</div> -->
					
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
					
				<%-- 	<div class="col-md-6">
				   <div class="col-md-12">
						<table id="example" class="table table-striped table-bordered" width="100%">
						  <thead>
						  <tr style="background:#0B6EC3;color:#fff;">
						   <th>Sr.No</th>
						   <th>Document Name</th>
						   <th>Action</th>
						  </tr> 
						  </thead>
						  
						  <tbody id="tbody">
						   <c:forEach items="${sfcoDocuments}" var="doc" varStatus="loop">
						   <tr id="row_${doc.sfco_doc_id}">
						     <td>${loop.index+1}</td>
						     <td>${doc.sfco_doc_original_file_name }</td>
						     <td><button type="button" value="${doc.sfco_doc_id}"
							name='delete_sfco_document' class="btn btn-danger">
						     <i class='fa fa-trash'></i>&nbsp;Delete</button></td>
						   </tr>
						   </c:forEach>
						  </tbody>
						</table>
					</div>
				</div> --%> 
					
					<div class="col-md-12 litigation_buttons">
				<br>
				<center>
					<input type="submit" value="Update"  class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listLocations' " >Back</button> -->
				</center>
				
				</div>
			</div>
		</sf:form>	
			
			<div style="clear:both"></div>
	</div>
</div>	
<script src="appJs/StandardFormContract/sfc_validation.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	
	var cont = "${cont_type}";
	if(cont =='Template Contract'){
		 $('input[name="sfco_contract_type"][value="' + cont + '"]').prop('checked', true);
	}else{
		 $('input[name="sfco_contract_type"][value="' + cont + '"]').prop('checked', true);
	}
	
});

</script>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>