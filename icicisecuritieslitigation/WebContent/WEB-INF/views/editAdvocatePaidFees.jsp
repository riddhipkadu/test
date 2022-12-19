
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
		<sf:form class="form-horizontal" role="form" commandName="editPay_advocate" action="./updatePaidAdvocateFees"  method="POST" enctype="multipart/form-data" onsubmit="return validateForm();">
			
			<div class="col-md-10">
				<div class="header_button">
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Edit Pay Advocate Fees</h2>
					<%-- <a href="./litigationDetails?liti_id=<%=request.getParameter("liti_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> --%>
				</div>
			</div>
			
			<div style="clear: both"></div>
		
			
			<div class="f_form_content">
				<h2>Pay</h2>

				<div class="col-md-12">


					<div class="col-md-6">
						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">advocate_fees_id :</label>
							<div class="col-sm-7">
								<sf:input path="apaid_advocate_fees_id" cssClass="form-control"/>
							</div>
						</div>

						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">advocate_fees_id :</label>
							<div class="col-sm-7">
								<sf:input path="apaid_id" cssClass="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Invoice no :</label>
							<div class="col-sm-7">
								<sf:input path="apaid_invoice_no" cssClass="form-control"/><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Invoice amount :</label>
							<div class="col-sm-4">
								<sf:input path="apaid_invoice_amt" cssClass="form-control"/><i class="asterisk_input" maxlength="9"></i>
							</div>
							<div class="col-sm-3">
								<sf:select path="apaid_invoice_amt_currency" cssClass="form-control">
									<option value="NA" selected="selected">--Select--</option>
									<c:forEach items="${allCurrency }" var="curr">
										<sf:option value="${curr.curr_code }">${curr.curr_code }</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Invoice Date :</label>
							<div class="col-sm-7">
								<div id="datepicker1" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="apaid_invoice_date"  readonly="true"/><span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Comments:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="apaid_comments"></sf:textarea>
							</div>
						</div>
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document :</label>
							<div class="col-sm-7">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="advocate_paid_doc" ><br />
								</div>
							</div>
							
						</div>
						<c:choose>
					
					<c:when test="${fn:length(AdvocatePaidFees) != 0}">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document Attached :</label>
							<c:forEach items="${AdvocatePaidFees}" var="document" varStatus="doc">
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
										<label class="control-label col-sm-4" for="sel1">Paid Fees: </label>
										<div class="col-sm-6">
											 <sf:input path="apaid_fees_amount" cssClass="form-control" id="apaid_fees_amount" maxlength="9"
												placeholder="Enter Amount" /> 
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Currency: </label>
										<div class="col-sm-6">
											 <sf:select path="apaid_currency_code" class="form-control">
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
									<label class="control-label col-sm-4" for="sel1">Date :</label>
									<div class="col-sm-6">
									<div id="apaid_amt_date_div" class="input-group date"
									data-date-format="MM">
									<input class="form-control" id="apaid_amt_date" readonly="true"/><span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
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
											<sf:select path="apaid_converted_currency" class="form-control">
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
										<label class="control-label col-sm-4" for="sel1">Conversion Rate: </label>
										<div class="col-sm-6">
											<sf:input path="apaid_conversion_rate" cssClass="form-control" size="10" maxlength="10"
												placeholder="Enter Amount" /> 

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Converted Amount: </label>
										<div class="col-sm-6">
											 <sf:input path="apaid_converted_amt" cssClass="form-control" readonly="true"
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
<script>
$(document).ready(function(){
	
	$("#apaid_invoice_amt_currency").multiselect({
		buttonWidth: '115px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#apaid_invoice_amt_currency option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#apaid_invoice_amt_currency').multiselect('deselect', values);
            }
	});

	$("#apaid_currency_code").multiselect({
		buttonWidth: '141px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#apaid_currency_code option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#apaid_currency_code').multiselect('deselect', values);
            }
	});
	
	$("#apaid_converted_currency").multiselect({
		buttonWidth: '141px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#apaid_converted_currency option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });
                $('#apaid_converted_currency').multiselect('deselect', values);
            }
	});
});

</script>
<script src="appJs/Fees/advocate_paid_fees.js"></script> 
<script src="appJs/Fees/DeleteLitiDoc.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>