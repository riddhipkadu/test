<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
 <script src="jquery-3.1.1.min.js" type='text/javascript'></script>
<script src='bootstrap/js/bootstrap.min.js' type='text/javascript'></script>
		<script src='bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js' type='text/javascript'></script>
		

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		
		<sf:form class="form-horizontal" role="form" commandName="addSarfaesiAct" enctype="multipart/form-data"
					action="./saveSarfaesiAct" onsubmit="return validateForm();" method="post">		
			
			<div class="col-md-10">
				<div class="header_button">
					
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Sarfaesi Act</h2>
					<a href="listSarfaesiAct"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Create Sarfaesi Act </h2>
                 
                 <div class="col-md-12">
						<div class="f_form_content">
							<h2>Organization Details</h2>
							
							<div class="col-md-12" style="margin-top: 10px;">
								
						<div class="col-md-4">
									<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Entity Name: </label>
								<div class="col-sm-8">
									<sf:select path="sarf_orga_id" items="${allOrganizations}"
										cssClass="form-control">
									</sf:select> <i class="asterisk_input"></i> 

								</div>
							</div>
						</div>
							
								<div class="col-md-4">
									<div class="form-group">
								      <label class="control-label col-sm-4" for="sel1"> Unit:	</label>
								    <div class="col-sm-8">
									   <sf:select path="sarf_loca_id" id="sarf_loca_id" cssClass="form-control">
										<option value="0">--Select--</option> 
									  </sf:select> <i class="asterisk_input"></i> 
								    </div>
							       </div>
								</div>
								
						 <div class="col-md-4">
							<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Function:</label>
							   <div class="col-sm-8">
								  <sf:select class="form-control" items="${allFunctions}" name="sarf_dept_id" path="sarf_dept_id" id="sarf_dept_id">
								   <option value="0">--Select--</option>
							      </sf:select><i class="asterisk_input"></i>
							</div>
						 </div>
					 </div>
							</div>
						</div>
              <div class="col-md-12">
						<div class="f_form_content">
							<h2>Sarfaesi Act Details</h2>
				<div class="col-md-12">

               
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Loan Number :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"  id="sarf_loan_no" path="sarf_loan_no" placeholder="Enter Loan number"/><i class="asterisk_input"></i>
							</div>
						</div>
						
						   <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Contact no. :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"  id="sarf_contact_no" path="sarf_contact_no"/><i class="asterisk_input"></i>
							</div>
						</div>
						
					  	<div class="form-group row">
							<label for="inputPassword3" class="col-sm-5 control-label">Postal Address :</label>
							<div class="col-sm-7">
								<sf:textarea class="form-control" path="sarf_address" id="sarf_address"
									 placeholder="Enter Postsal Address"></sf:textarea>
							</div>
						</div>
					
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Security Type :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="sarf_security_type"  id="sarf_security_type">
								    <option value="NA">--Select--</option>
									<option value="Land & Building">Land & Building</option>
									<option value="Motor Vehicle">Motor Vehicle</option>
									<option value="Shares or Stock">Shares or Stock</option>
									<option value="Book Debts">Book Debts</option>
							     </sf:select> <i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Type of Security Interest :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="sarf_security_Interest"  id="sarf_security_Interest">
								    <option value="NA">--Select--</option>
									<option value="Pledge">Pledge</option>
									<option value="Hypothecation">Hypothecation</option>
									<option value="Charge">Charge</option>
									<option value="Lease">Lease</option>
							     </sf:select> <i class="asterisk_input"></i> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Security Location :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"  id="sarf_security_loc" path="sarf_security_loc"/><i class="asterisk_input"></i>
							</div>
						</div>
						
						 <div class="form-group">
								<label class="control-label col-sm-5">Case Filling Date:</label>
								<div class="col-sm-7">
								   <div id="sarf_filling_date_div" class="input-group date"
									    data-date-format="MM">
									    <sf:input Class="form-control" id="sarf_filling_date" path="sarf_filling_date" readonly="true"/> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								   </div> <i class="asterisk_input"></i>
								</div>
							</div> 
						
						
						 <div class="form-group">
							<label class="control-label col-sm-5">First Reminder:</label>
							<div class="col-sm-7">
								<div id="Remider_first_div" class="input-group date"
									data-date-format="MM">
									<sf:input Class="form-control" class="datepicker" id="sarf_first_reminder" path="sarf_first_reminder" name="sarf_first_reminder" readonly="true" onfocus="setDatePicker(this)" onkeypress="setDatePicker(this)" onmousedown="setDatePicker(this)"/>  <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div> 
						</div>
					
					</div>	
			 <div class="col-md-6">
			           <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Borrower Name:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"  id="sarf_borrower" path="sarf_borrower" placeholder="Enter Borrower Name"/><i class="asterisk_input"></i>
							</div>
						</div>
					<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Borrower Name :</label>
							<div class="col-sm-7">
								<select name="sarf_borrower" id="sarf_borrower" class="form-control">
										<option value="0" selected="selected">--Select--</option>
										 <c:forEach items="${sarf_borrower}" var="borrwname">
										 <c:choose>
										 <c:when test="${borrwname !=''}">
											<option value="${borrwname}">${borrwname}</option>
										</c:when>
										</c:choose>
										</c:forEach> 
									</select>
							</div>
						</div> --%>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Email Id :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"  id="sarf_email" path="sarf_email"/><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Executor :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="sarf_Executor_id" id="sarf_Executor_id">
								    <option value="0">--Select--</option>
									<c:forEach items="${userSarf}" var="executor">
									  <option value="${executor.user_id}">${executor.user_first_name}
										${executor.user_last_name}</option>
							       </c:forEach> 
							     </sf:select> <i class="asterisk_input"></i> 
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Asset Reconstruction Company:</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="sarf_arc_name" id="sarf_arc_name">
								   <c:forEach items="${allARCname}" var="ArcName">
											<option value="${ArcName.key}">${ArcName.value}</option>
										</c:forEach>
							   	</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
								<label class="control-label col-sm-5" for="sel1">ARC Manager : </label>
								<div class="col-sm-7">
									<sf:select class="form-control"  path="sarf_mgr_name" name="sarf_mgr_name" id="sarf_mgr_name">
								    <option value="0">--Select--</option>
							   </sf:select>
								</div> <i class="asterisk_input"></i> 
						</div>
						
							 <div class="form-group">
							<label class="control-label col-sm-5">NPA Date :</label>
							<div class="col-sm-7">
								<div id="npa_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" id="sarf_npa_date" path="sarf_npa_date" readonly="true"/> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i>
								</div></span><i class="asterisk_input"></i>
							</div>
						</div> 
						
					  <div class="form-group">
							<label class="control-label col-sm-5">Notice Issue Date:</label>
							<div class="col-sm-7">
								<div id="NotiIssue_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input Class="form-control" path="sarf_NotiIssue_date" id="sarf_NotiIssue_date" name="sarf_NotiIssue_date" readonly="true"/> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div> 
						 
						 <div class="form-group">
							<label class="control-label col-sm-5">Second Reminder:</label>
							<div class="col-sm-7">
								<div id="Reminder_second_div" class="input-group date"
									data-date-format="MM">
									<sf:input Class="form-control" id="sarf_second_reminder" path="sarf_second_reminder" readonly="true"/> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div> 
						
						
						
						
				<!-- <div class="col-md-12">
					<div id="fileContiner_sarfaesiDoc"></div>
				</div>
				 <div class="col-md-12">
                   <div class="col-md-6">
							<div class="form-group">
								<label class="col-sm-4 control-label"></label>
								<div class="col-sm-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addSarfActFileInput();">Upload Document</button>
								</div>
							</div>
						</div>
					</div> -->
                
                </div>
                </div></div>
						
					<div class="col-md-12">
						<div class="f_form_content">
							<h2>Amount Details</h2>
							<div class="col-md-12" style="margin-top: 10px;">
							
								<div class="col-md-3">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Total Loan Amount:
											</label>
										<div class="col-sm-6">
											<sf:input path="sarf_total_amount" id="sarf_total_amount" Class="form-control" 
												placeholder="Enter Total Amount" type="number" />
											 <i class="asterisk_input"></i> 

										</div>
									</div>
								</div>
								
								<div class="col-md-3">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Loan Repayment Amount:
											</label>
										<div class="col-sm-6">
											<sf:input path="sarf_loan_repay_amount" id="sarf_loan_repay_amount" Class="form-control" 
												placeholder="Enter Loan Repayment Amount" type="number" />
											 <i class="asterisk_input"></i> 

										</div>
									</div>
								</div>
								
								<div class="col-md-3">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Amount Paid:
											 </label>
										<div class="col-sm-6">
											<sf:input path="sarf_paid_amount" id="sarf_paid_amount" Class="form-control" 
												placeholder="Enter Paid Amount" type="number"/>
											 <!-- <i class="asterisk_input"></i>  -->

										</div>
									</div>
								</div>
								
								<div class="col-md-3">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Balance To Be Paid:
											 </label>
										<div class="col-sm-6">
											<sf:input path="sarf_balance_amount" id="sarf_balance_amount" Class="form-control" 
												placeholder="Enter Balance Amount" type="number"/>
											 <!-- <i class="asterisk_input"></i>  -->

										</div>
									</div>
								</div>
								
							</div>
								
						</div>
					</div>

				</div>
 

				<div class="col-md-12 litigation_buttons">
					<br>
						
						<center><button type="submit" value="Save" name="Save" onClick="alertName()" class="btn btn-myPrimary">Submit</button>
						    	 
						</center>
					
				</div>
			</div>
		</div>
	  		
		</sf:form>

		<!-- <div style="clear: both"></div> -->

	</div>
</div>

<script>
	$(document).ready(function(){
		
	    $("#sarf_arc_name").multiselect({
			buttonWidth: '270px',
		    enableFiltering: true,
		    filterBehavior: 'text',
		    enableCaseInsensitiveFiltering: true,
		    maxHeight : 200,
		    onChange: function(option, checked) {
	          var values = [];
	          $('#sarf_arc_name option').each(function() {
	              if ($(this).val() !== option.val()) {
	                  values.push($(this).val());
	              }
	          });

	          $('#sarf_arc_name').multiselect('deselect', values);
	      }
		});
	    

	   /*  $("#sarf_mgr_name").multiselect({
			buttonWidth: '270px',
		    enableFiltering: true,
		    filterBehavior: 'text',
		    enableCaseInsensitiveFiltering: true,
		    maxHeight : 250,
		    onChange: function(option, checked) {
	          var values = [];
	          $('#sarf_mgr_name option').each(function() {
	              if ($(this).val() !== option.val()) {
	                  values.push($(this).val());
	              }
	          });

	          $('#sarf_mgr_name').multiselect('deselect', values);
	      }
		}); */
		
	});

</script>

  <!--  <script>
		var x = "";
        $(document).ready(function(){
              
            $('#sarf_NotiIssue_date').datepicker({
                format: "dd-mm-yyyy",
            }).on('changeDate', function(selected){
				$('#enddterr').html("");
				x = selected.date.valueOf();
				$('#sarf_first_reminder').val('');
				$('#sarf_first_reminder').datepicker('remove');
				//Format: "dd-mm-yyyy",
			}).datepicker("setDate",new Date());
            
          
           
        });


	function setDatePicker(dtfield){
		var stdt = $('#sarf_NotiIssue_date').val();
			if(stdt != ""){
			
				var newDate = new Date(x);
				var newDate1 = new Date(x);
				newDate.setDate(newDate.getDate() + 60);
				$(dtfield).datepicker({
					Format: "dd-mm-yyyy",
					startDate: newDate1,
					endDate: newDate
				});
				
			}else{
				$('#enddterr').html("Please select the Start Date First..!");
				$('#sarf_NotiIssue_date').focus();
			}
			
	}
		
		
  </script>  -->
 <!--  <script>
  
  $("#NotiIssue_date_div").datepicker({
		 Calendar c = Calendar.getInstance(); 
      c.setTime(new Date()); 
      c.add(Calendar.DAYS, +60);
       Date d = c.getTime();
    String res = $("#Remider_first_div").format(d);
    
});
  </script> -->
	
	
<script src="appJs/SarfaesiAct/sarfaesiAct.js"></script>			
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
