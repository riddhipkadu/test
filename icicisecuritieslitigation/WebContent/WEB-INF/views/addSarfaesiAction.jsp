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
					<h2 style="color:#39c388;font-size:24px;float:left;">New Action Item</h2>
					 <a href="./sarfaesiActDetails?sarf_act_id=<%=request.getParameter("sarf_act_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addSarfaesiAction" autocomplete="off" action="./saveSarfaesiAction" method="POST"  enctype="multipart/form-data" cssClass="form-horizontal" onsubmit="return validateForm();">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Add Sarfaesi Act Action Item</h2>
			
				<div class="col-md-10">
				
				<div class="col-md-12">
						<div class="form-group row" style="display:none;">
							<label for="sfctitle" class="col-sm-3 control-label"> Id :</label>
							<div class="col-sm-6">
								<sf:input class="form-control" path="action_sarf_act_id" value='<%=request.getParameter("sarf_act_id")%>' type="text" /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label class="control-label col-sm-3" for="sarf_act_id">Status:</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="sarf_action_status">
									<option value="0">--Select--</option>
									<option value="Pending">Pending</option>
									<option value="Closed">Closed</option>
									<option value="Further Process">Further Process</option>
									<option value="waiting for Reply">A waiting for Reply</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Action Item :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" path="sarf_action_item"
									 placeholder="Enter Action"></sf:textarea>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Next Action Item :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" path="sarf_nextaction_item"
									 placeholder="Enter Next Action"></sf:textarea>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Notice Issue Date :</label>
							<div class="col-sm-6">
								<div id="sarf_action_NotiIssue_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="sarf_action_NotiIssue_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>

					<%-- <c:forEach var="entry" items="${sarf_action_NotiIssue_date}">
						Name:  ${entry} <br />
					</c:forEach> --%>

					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Action Due Date :</label>
							<div class="col-sm-6">
								<div id="action_due_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="sarf_action_due_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">First Reminder alert :</label>
							<div class="col-sm-6">
								<div id="action_first_remind_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="action_first_remind_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-3 control-label">Second Reminder alert :</label>
							<div class="col-sm-6">
								<div id="action_second_remind_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" readonly="true" path="action_second_remind_date" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
					
					
					
					</div>
				
				<div class="col-md-12 litigation_buttons">
				<br>
				
					<center>
					<input type="submit" value="Save" class="btn btn-myPrimary"/>
					
					</center>
				</div>
				</div>
				</sf:form>		
			
			<div style="clear:both"></div>
			
</div>
</div>


<script type="text/javascript">
$(document).ready(function(){

}); 
</script>







<script src="appJs/SarfaesiAct/sarfaesiactAction.js"></script>	



<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
