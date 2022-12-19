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
					<h2 style="color:#032BEC;font-size:24px;float:left;">Completion Task</h2>
					<a href="./actionItemHistory?action_id=${action_id }"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> 
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addTaskCompletion" autocomplete="off" action="./updateTaskCompletionAction" method="POST" enctype="multipart/form-data" onsubmit="return validateForm();" cssClass="form-horizontal">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Completion</h2>
			
				<div class="col-md-10">
				
				<div class="col-md-12">
							<div class="form-group row" style="display:none;">
								<label for="sfctitle" class="col-sm-3 control-label">Contract Id :</label>
								<div class="col-sm-6">
									<sf:input path="atrn_id" class="form-control" type="text" /><i class="asterisk_input"></i>
								</div>
							</div>
						<%-- <div class="form-group row" style="display:none;">
							<label for="sfctitle" class="col-sm-3 control-label">Contract Id :</label>
							<div class="col-sm-6">
								<sf:input path="exec_contract_id" class="form-control" type="text" /><i class="asterisk_input"></i>
							</div>
						</div> --%>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Completion Date :</label>
							<div class="col-sm-6">
								<div id="datepicker1" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="atrn_completed_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Remark :</label>
							<div class="col-sm-6">
									<sf:input class="form-control"  path="atrn_remark" />
							</div>
						</div>
					</div> --%>
					<div class="col-md-12">
					<div class="form-group row" >
								<label for="sfctitle" class="col-sm-3 control-label">Remark :</label>
								<div class="col-sm-6">
									<sf:textarea path="atrn_remark" class="form-control" type="text"></sf:textarea>
									<i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					
					<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Remark :</label>
							<div class="col-sm-6">
						<sf:textarea class="form-control" path="atrn_remark" placeholder="Remarks"></sf:textarea>
									<i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					
					</div>
				
				<div class="col-md-12 litigation_buttons">
				<br>
				
					<center>
					<input type="submit" value="Update" class="btn btn-myPrimary"/>
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
