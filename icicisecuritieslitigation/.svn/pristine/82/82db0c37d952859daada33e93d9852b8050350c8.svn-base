
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
		<sf:form class="form-horizontal" role="form" commandName="addAdvocateFees" onsubmit="return validateForm();" action="./saveAdvocateFees"  method="POST" enctype="multipart/form-data">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Advocate Fees</h2>
					<a href="./litigationDetails?liti_id=<%=request.getParameter("liti_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
			
			<div style="clear: both"></div>
		
			
			<div class="f_form_content">
				<h2>Add</h2>

				<div class="col-md-12">

					<div class="col-md-6">

						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">Id
								:</label>
							<div class="col-sm-7">
								<sf:input path="ladv_litigation_id" value='<%=request.getParameter("liti_id")%>'/>

							</div>
						</div>
						<%--<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1"> Advocate id:</label>
							<div class="col-sm-7">
								<sf:input type="hidden" class="form-control" path="ladv_advocate_id" value="${litigationDetails.liti_advocate_id}" />
							</div>
						</div>
						 <div class="form-group" >
							<label class="control-label col-sm-5" for="sel1"> Advocate Name:</label>
							<div class="col-sm-7" style="margin-top: 12px; font-size: 17px;">
							     <label class="label label-default"> ${litigationDetails.liti_advocate_name}</label>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Law Firm
								:</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allLawFirm}"
									path="ladv_advocate_law_firm_id">

								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">
								Advocate Name :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="ladv_advocate_id">
									<sf:option value="0">--Select--</sf:option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Type Of Fees:</label>
							<div class="col-sm-7">
							<sf:select class="form-control"  path="ladv_type_of_fees" >
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
							<sf:select class="form-control"  path="ladv_effective_non_effective" >
								   <sf:option value="NA">--Select--</sf:option>
								   <sf:option value="Effective">Effective</sf:option>
								   <sf:option value="Non-Effective">Non-Effective</sf:option>
							 </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Fees:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="ladv_advocate_fees_amount" /><i class="asterisk_input"></i>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Comments:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="ladv_comments"></sf:textarea>
							</div>
						</div>
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">Document :</label>
							<div class="col-sm-7">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="advocate_agreed_doc" ><br />
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
										<label class="control-label col-sm-4" for="sel1"> Fees: </label>
										<div class="col-sm-6">
											 <sf:input path="ladv_advocate_fees_amount" cssClass="form-control" maxlength="9"
												placeholder="Enter Amount" /> 
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Currency: </label>
										<div class="col-sm-6">
											 <sf:select path="ladv_currency" class="form-control">
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
										<div id="ladv_amount_div" class="input-group date"
										data-date-format="MM">
									<input class="form-control" id="ladv_amount_date" name="ladv_amount_date" readonly/>
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
											<sf:select path="ladv_converted_currency" class="form-control">
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
											<sf:input path="ladv_conversion_rate" cssClass="form-control" size="10" maxlength="10"
												placeholder="Enter Amount" /> 

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Converted Amount: </label>
										<div class="col-sm-6">
											 <sf:input path="ladv_converted_amt" cssClass="form-control" readonly="true"
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
	
	$("#ladv_advocate_law_firm_id").multiselect({
		buttonWidth: '300px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#ladv_advocate_law_firm_id option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#ladv_advocate_law_firm_id').multiselect('deselect', values);
            }
	});
	
	$("#ladv_currency").multiselect({
		buttonWidth: '137px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#ladv_currency option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#ladv_currency').multiselect('deselect', values);
            }
        
	});
	
	$("#ladv_converted_currency").multiselect({
		buttonWidth: '137px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#ladv_converted_currency option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#ladv_converted_currency').multiselect('deselect', values);
            }
        
	});
	
});

</script>
<script src="appJs/Fees/advocateFees.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>