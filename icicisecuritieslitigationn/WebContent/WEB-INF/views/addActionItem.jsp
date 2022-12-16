<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">New Action Item</h2>
					 <a href="./listActionItem?exec_id=<%=request.getParameter("exec_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addActionItem" autocomplete="off" action="./saveActionItem" method="POST" onsubmit="return validateForm();" enctype="multipart/form-data" cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Add Action Item</h2>
			
				<div class="col-md-10">
				
				<div class="col-md-12">
						<div class="form-group row" style="display:none;">
							<label for="sfctitle" class="col-sm-3 control-label">Contract Id :</label>
							<div class="col-sm-6">
								<sf:input class="form-control" path="exec_contract_id" value='<%=request.getParameter("exec_id")%>' type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Action Item :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" path="exec_action_item"
									 placeholder="Enter action"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="col-sm-3 control-label">Responsible Person :</label>
							<div class="col-sm-6">
								<sf:select path="exec_responsible_user_id" class="form-control" type="text" >
								<sf:option value="0">--Select--</sf:option>
								 <c:forEach items="${user_list}"
											var="user_list">
											<sf:option value="${user_list.user_id}">${user_list.user_first_name} ${user_list.user_last_name}</sf:option>
										</c:forEach>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label class="control-label col-sm-3" for="dept_id">Frequency:</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="exec_frequency">
									<option value="0">--Select--</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Quarterly">Quarterly</option>
									<option value="Half yearly">Half Yearly</option>
									<option value="Yearly">Yearly</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Action Due Date :</label>
							<div class="col-sm-6">
								<div id="datepicker1" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="exec_due_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">First Alert Prior day/s :</label>
							<div class="col-sm-6">
									<sf:input type="text" class="form-control"  path="exec_first_alert_prior_days" onkeypress="return blockSpecialChar(event);"/><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Second Alert Prior day/s :</label>
							<div class="col-sm-6">
									<sf:input type="text" class="form-control" path="exec_second_alert_prior_days" onkeypress="return blockSpecialChar(event);"/>
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

<script src="appJs/ExecutedContracts/validateActionItem.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	
});
</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
