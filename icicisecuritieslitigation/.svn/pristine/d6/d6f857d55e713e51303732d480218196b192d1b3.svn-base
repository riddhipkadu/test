
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<!-- Fail Modal -->
			<div class="modal fade" id="dialogBox" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #e26d1c;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								<span class="glyphicon glyphicon-remove-sign"></span> &nbsp;Error
							</h4>
						</div>
						<div class="modal-body">
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
<!-- Fail Modal END -->

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form class="form-horizontal" role="form" commandName="editCounselFees" onsubmit="return validateForm();" action="./updateCounselFees"  method="POST" enctype="multipart/form-data">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Counsels Fees</h2>
					<a href="./litigationDetails?liti_id=${liti_id }"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> 
				</div>
			</div>
			
			<div style="clear: both"></div>
		
			
			<div class="f_form_content">
				<h2>Edit</h2>

				<div class="col-md-12">


					<div class="col-md-6">

						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">Id
								:</label>
							<div class="col-sm-7">
								<sf:input path="lcou_id" />
							</div>
						</div>
						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">liti Id
								:</label>
							<div class="col-sm-7">
								<sf:input path="lcou_liti_id" />
							</div>
						</div>
						<%-- <div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1"> Counsel id:</label>
							<div class="col-sm-7">
								<sf:input type="hidden" class="form-control" path="lcou_counsel_id" />
							</div>
						</div>
						<div class="form-group" >
							<label class="control-label col-sm-5" for="sel1"> Counsel Name:</label>
							<div class="col-sm-7" style="margin-top: 12px; font-size: 17px;">
							     <label class="label label-default"> ${litigationDetails.exte_coun_name}</label>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Law Firm :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allLawFirm}" path="lcou_law_firm_id">
								   
							   </sf:select ><i class="asterisk_input"></i>
							
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Counsel Name :</label>
							<div class="col-sm-7">
							<sf:select class="form-control"  path="lcou_counsel_id" id="lcou_counsel_id">
								   <option value="0">--Select--</option>
								   <c:forEach items="${external_counsel_list }" var="coun">
										<sf:option value="${coun.exte_coun_id }">${coun.exte_coun_name }</sf:option>								   
								   </c:forEach>
							 </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					   <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Type Of Fees:</label>
							<div class="col-sm-7">
							<sf:select class="form-control"  path="lcou_type_of_fees" >
								   <sf:option value="NA">--Select--</sf:option>
								   <sf:option value="lump sum">lump sum</sf:option>
								   <sf:option value="appearance">appearance</sf:option>
								   <sf:option value="Hourly">Hourly</sf:option>
							 </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
						<div class="form-group" id="effe_non_effe">
							<label class="control-label col-sm-5" for="sel1"> Effective/Non-Effective:</label>
							<div class="col-sm-7">
							<sf:select class="form-control"  path="lcou_effective_non_effective" >
								   <sf:option value="NA">--Select--</sf:option>
								   <sf:option value="Effective">Effective</sf:option>
								   <sf:option value="Non-Effective">Non-Effective</sf:option>
							 </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Fees:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="lcou_counsel_fees_amount" /><i class="asterisk_input"></i>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Comments:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="lcou_comments"></sf:textarea>
							</div>
						</div>
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document :</label>
							<div class="col-sm-7">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="counsel_agreed_doc" ><br />
								</div>
							</div>
							
						</div>
						<c:choose>
					
					<c:when test="${fn:length(CounselFeesDoc) != 0}">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document Attached :</label>
							<c:forEach items="${CounselFeesDoc}" var="document" varStatus="doc">
							<div id="doc_attached_${document.fdoc_id}" class = "col-sm-7">
							
							<div class="col-sm-3" style="text-align: right; cursor: pointer;">
						<a href="./downloadLitigationFeesDocument?fdoc_id=${document.fdoc_id}">
							${document.fdoc_original_file_name}</a>
	                     <i class="glyphicon glyphicon-remove-circle" onclick="deleteLitiFeesDoc(${document.fdoc_id});" style="color: red; margin-right: -200px; margin-top: -19px;" title="Delete"></i>
	       					 </div>
							</div>
							</c:forEach>
						 </div>
					</c:when>
						<c:otherwise>
						
						</c:otherwise>
						</c:choose>
						
						
					</div> 
					
				</div>
				<div class="col-md-12">
						<div class="f_form_content">
							<h2>Fees Details</h2>
							<div class="col-md-12" style="margin-top: 10px;">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Fees: </label>
										<div class="col-sm-6">
											 <sf:input path="lcou_counsel_fees_amount" cssClass="form-control" maxlength="9"
												placeholder="Enter Amount" /> 
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Currency: </label>
										<div class="col-sm-6">
											 <sf:select path="lcou_currency" class="form-control">
												<option value="NA" selected="selected">--Select--</option>
												<c:forEach items="${allCurrency }" var="curr">
													<sf:option value="${curr.curr_code }">${curr.curr_code }</sf:option>
												</c:forEach>

											</sf:select> 
											<i class="asterisk_input"></i>
										</div>
									</div>
								</div>
								<div class="col-md-4">
								<div class="form-group">
									<label  class="col-sm-4 control-label">Date :</label>
									<div class="col-sm-6">
										<div id="noti_amount_div" class="input-group date"
										data-date-format="MM">
									<input class="form-control" id="lcou_amount_date" name="lcou_amount_date" readonly="true"/>
									<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								</div>
								</div>
							</div>
							<div class="col-md-12" style="margin-top: 10px;">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Converted Currency: </label>
										<div class="col-sm-6">
											<sf:select path="lcou_converted_currency" class="form-control">
												<option value="NA">--Select--</option>
												<c:forEach items="${allCurrency }" var="curr">
													<sf:option value="${curr.curr_code }">${curr.curr_code }</sf:option>
												</c:forEach>
											</sf:select> 
											<i class="asterisk_input"></i>
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Conversion Rate: </label>
										<div class="col-sm-6">
											<sf:input path="lcou_conversion_rate" cssClass="form-control" size="10" maxlength="10"
												placeholder="Enter Amount" /> 

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Converted Amount: </label>
										<div class="col-sm-6">
											 <sf:input path="lcou_converted_amt" cssClass="form-control" readonly="true"
												placeholder="Enter Amount" /> 

										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>

				<div class="col-md-12 litigation_buttons">
					<button type="submit" class="btn btn-myPrimary" style="margin-left:340px;">Update</button>
				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	 //setTimeout(function(){ 
		 var lcou_conversion_rate = $("#lcou_conversion_rate").val();
		 var lcou_counsel_fees_amount = $("#lcou_counsel_fees_amount").val();
		   $("#lcou_converted_amt").val(lcou_counsel_fees_amount*lcou_conversion_rate);
		 //}, 1000);
	
		   $("#lcou_law_firm_id").multiselect({
				buttonWidth: '300px',
			    enableFiltering: true,
			    filterBehavior: 'text',
			    enableCaseInsensitiveFiltering: true,
			    maxHeight : 200,
			    onChange: function(option, checked) {
		                var values = [];
		                $('#lcou_law_firm_id option').each(function() {
		                    if ($(this).val() !== option.val()) {
		                        values.push($(this).val());
		                    }
		                });

		                $('#lcou_law_firm_id').multiselect('deselect', values);
		            }
			});
			
			$("#lcou_currency").multiselect({
				buttonWidth: '137px',
			    enableFiltering: true,
			    filterBehavior: 'text',
			    enableCaseInsensitiveFiltering: true,
			    maxHeight : 200,
			    onChange: function(option, checked) {
		                var values = [];
		                $('#lcou_currency option').each(function() {
		                    if ($(this).val() !== option.val()) {
		                        values.push($(this).val());
		                    }
		                });
		                $('#lcou_currency').multiselect('deselect', values);
		            }
			});

			$("#lcou_converted_currency").multiselect({
				buttonWidth: '137px',
			    enableFiltering: true,
			    filterBehavior: 'text',
			    enableCaseInsensitiveFiltering: true,
			    maxHeight : 200,
			    onChange: function(option, checked) {
		                var values = [];
		                $('#lcou_converted_currency option').each(function() {
		                    if ($(this).val() !== option.val()) {
		                        values.push($(this).val());
		                    }
		                });

		                $('#lcou_converted_currency').multiselect('deselect', values);
		            }
		        
			});
	 
});
</script>
<script src="appJs/Fees/counselFees.js"></script>
<script src="appJs/Fees/DeleteLitiDoc.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>