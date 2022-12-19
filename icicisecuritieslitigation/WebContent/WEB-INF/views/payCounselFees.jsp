
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form class="form-horizontal" role="form" commandName="pay_counsel" action="./savePaidCounselFees"  method="POST" onsubmit="return validateForm();" enctype="multipart/form-data">
			
			<div class="col-md-10">
				<div class="header_button">
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Pay Counsel Fees</h2>
					<%-- <a href="./litigationDetails?liti_id=<%=request.getParameter("liti_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> --%>
				</div>
			</div>
			
			<div style="clear: both"></div>
		
			
			<div class="f_form_content">
				<h2>Pay</h2>

				<div class="col-md-12">


					<div class="col-md-6">

						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">counsel_fees_id :</label>
							<div class="col-sm-7">
								<sf:input path="cpaid_counsel_fees_id" value="${counsel_fees_id}" cssClass="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Invoice no :</label>
							<div class="col-sm-7">
								<sf:input path="cpaid_invoice_no" cssClass="form-control"/><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Invoice amount :</label>
							<div class="col-sm-4">
								<sf:input path="cpaid_invoice_amt" cssClass="form-control" maxlength="9"/><i class="asterisk_input"></i>
							</div>
							<div class="col-sm-3">
								<sf:select path="cpaid_invoice_amt_currency" cssClass="form-control">
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
									<sf:input class="form-control" path="cpaid_invoice_date"  readonly="true"/><span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Comments:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="cpaid_comments"></sf:textarea>
							</div>
						</div>
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document :</label>
							<div class="col-sm-7">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="counsel_paid_doc" ><br />
								</div>
							</div>
						</div>
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
											 <sf:input path="cpaid_fees_amount" cssClass="form-control" id="cpaid_fees_amount" maxlength="9"
												placeholder="Enter Amount" /> 
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Currency: </label>
										<div class="col-sm-6">
											 <sf:select path="cpaid_currency_code" class="form-control">
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
									<div id="cpaid_amt_date_div" class="input-group date"
									data-date-format="MM">
									<input class="form-control" id="cpaid_amt_date" readonly/><span
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
											<sf:select path="cpaid_converted_currency" class="form-control">
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
											<sf:input path="cpaid_conversion_rate" cssClass="form-control" size="10" maxlength="10"
												placeholder="Enter Amount" /> 

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Converted Amount: </label>
										<div class="col-sm-6">
											 <sf:input path="cpaid_converted_amt" cssClass="form-control" readonly="true"
												placeholder="Enter Amount" /> 

										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>

				<div class="col-md-12 litigation_buttons">
					<button type="submit" class="btn btn-myPrimary" style="margin-left:340px;">Save</button>
					
					
				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>
<script>
$(document).ready(function(){
	
	$("#cpaid_invoice_amt_currency").multiselect({
		buttonWidth: '115px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#cpaid_invoice_amt_currency option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#cpaid_invoice_amt_currency').multiselect('deselect', values);
            }
	});

	$("#cpaid_currency_code").multiselect({
		buttonWidth: '141px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#cpaid_currency_code option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#cpaid_currency_code').multiselect('deselect', values);
            }
	});
	
	$("#cpaid_converted_currency").multiselect({
		buttonWidth: '141px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#cpaid_converted_currency option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });
                $('#cpaid_converted_currency').multiselect('deselect', values);
            }
	});
});
</script>
<script src="appJs/Fees/counsel_paid_fees.js"></script> 

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>