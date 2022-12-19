<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<div class="container">
			<!-- Header -->
			<div class="row">
				<div class="col-md-12">
					<div class="header_button">
						<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
						<h2 style="color: #032BEC; font-size: 24px; float: left;">Generate
							Legal Notice Request</h2>
						<a href="./listNoticeRequest"><img
							src="images/DashboardIcons/backold.png" class="backButton"
							width="100px;"></a>
					</div>
				</div>
			</div>
			<!-- End Header -->
			<sf:form class="form-horizontal" commandName="addNoticeRequest" action="./saveNoticeRequest" enctype="multipart/form-data" method="POST">

				<!-- Second Panel -->
					<div class="f_form_content">
					<h2>Add Legal Notice Request</h2>
					
					 <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Entity/Company Name :</label>
							<div class="col-sm-8">
								<select class="form-control" name="req_noti_entity_id" id="req_noti_entity_id">
									   <c:forEach items="${allOrganizations}" var="organization">
								          <option value="${organization.key}">${organization.value}</option>
								       </c:forEach> 
							     </select><i class="asterisk_input"></i>
							   
							</div>
						</div>
					</div>
					</div>
					
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Unit/Vertical :</label>
							<div class="col-sm-8">
								<select class="form-control" name="req_noti_unit_id" id="req_noti_unit_id">
									   <option value="0">--Select--</option>
									    
							     </select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-4">Function/Location/Department:</label>
							<div class="col-sm-8">
								<select class="form-control" name="req_noti_function_id" id="req_noti_function_id">
									   <option value="0">--Select--</option>
									   
							     </select><i class="asterisk_input"></i>
							</div>
						</div>
					</div> 	
					</div>				
					
					<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Category:
								</label>
								<div class="col-sm-8">
									<select id="req_noti_category" name="req_noti_category" class="form-control">
									<option value="0">--Select--</option>
										<c:forEach items="${notigation_type_list}"
											var="notigation_type">
											<option value="${notigation_type.noti_type_id}">${notigation_type.noti_type_name}</option>
										</c:forEach>
									</select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>--%>
					
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Notice Type:</label>
								 <div class="col-sm-8">
									<select id="req_noti_type_by_against" name="req_noti_type_by_against" class="form-control">
										<option value="0">--Select--</option>
										<option value="By">Notice Given</option>
										<!-- By -->
										<option value="Against">Notice Received</option>
										<!-- Against -->
									</select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div> 
					
					
					<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						 <div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Counsel Law Firm :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="liti_counsel_law_firm" id="liti_counsel_law_firm">
								   <!-- <option value="0">--Select--</option> -->
								   <c:forEach items="${allLawFirm}" var="lawf">
								   <option value="${lawf.key}">${lawf.value}</option>
								   </c:forEach>
							   </sf:select ><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div> --%>
					
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4">Date (Notice/ Court order) :</label>
								<div class="col-sm-8">
									<div class="input-group date" id="datepicker1"
										data-date-format="MM">
										<input id="req_noti_date" name="req_noti_date" class="form-control" readonly/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>

									</div>
								</div>
							</div>
						</div>
					</div>
					
					<!-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Case
									Reference No :</label>
								<div class="col-sm-8">
									<input id="req_liti_case_ref_no" name="req_liti_case_ref_no" class="form-control"
										placeholder="CASE REF. No" /><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div> -->
					
				<!-- End Second panel -->
					
				<!-- Third Panel -->
				 	<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">
									Opposite Party(s) : </label>
								<div class="col-sm-8">
									<input id="req_noti_oppo_party" name="req_noti_oppo_party" class="form-control"
										placeholder="Party Name" /><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>
					
				<!-- End Third panel -->
				<!-- Fourth Panel -->
				
					<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Forum :
								</label>
								<div class="col-sm-8">
									<sf:select path="liti_forum" cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<sf:option value="District_Forum">District Forum</sf:option>
									</sf:select><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div> --%>

					<%-- <div class="col-md-12" style="margin-top: 10px;">

						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Amount
									Involved (INR): </label>
								<div class="col-sm-8">
									<sf:input path="liti_amount_involved" cssClass="form-control"
										placeholder="Enter Amount" /><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div> --%>

					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Brief
									Description About Notice : </label>
								<div class="col-sm-8">
									<textarea id="req_noti_des" name="req_noti_des" rows="4" cols="59" ></textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
					
					<div class="col-md-8">
					
						<div class="form-group ">
							<label for="sfctitle" class="control-label col-sm-4">Document :</label>
							<div class="col-sm-8">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="noti_document" ><br />
								</div>
							</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12 litigation_buttons" style="margin-top: 10px;">
						<center>
							<button type="submit" value="Save" name="Save" class="btn btn-myPrimary">Generate Request</button>
						  	 <button type="submit" name="Draft" value="Draft"class="btn btn-myDefault">Save As Draft</button> 

						</center>
					</div>
				</div>
				<!-- Fourth panel End-->
			</sf:form>
		</div>
	</div>
</div>

<script src="appJs/RequestValidate/notiRequestValidate.js"></script>
<script>
$(document).ready(function(){
	
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

