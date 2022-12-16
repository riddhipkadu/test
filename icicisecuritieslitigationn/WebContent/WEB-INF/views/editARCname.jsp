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
		<sf:form class="form-horizontal" role="form" commandName="editARCname" autocomplete="off"
					action="./updateARCname" onsubmit=""  method="POST">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">ARC Name</h2>
					<a href="./listARCname"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Update</h2>

			<div class="col-md-12">
				 <div class="col-md-6">
				        <div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1"> </label>
								<div class="col-sm-7">
									<sf:input type="text" class="form-control" path="arc_id" id="arc_id" name="arc_id"/>
								</div>
						</div>
					 <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Asset Reconstruction Company:</label>
							<div class="col-sm-7">
								<sf:input type="text" cssClass="form-control" path="arc_name" id="arc_name" name="arc_name" />
							 <i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Contact Person :</label>
						  <div class="col-sm-7">
							  <sf:input type="text" cssClass="form-control" path="arc_contact_person" id="arc_contact_person" name="arc_contact_person" onkeypress="return blockSpecialChar(event)"/>
							  <i class="asterisk_input"></i>
						  </div>
						</div>
					
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Contact no :</label>
							<div class="col-sm-7">
								<sf:input type="text" cssClass="form-control" path="arc_contact_no" id="arc_contact_no" name="arc_contact_no"/>
								<i class="asterisk_input"></i> 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Email Id:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="arc_email_id" id="arc_email_id" name="arc_email_id"/>
							 <i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Address:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="arc_address" id="arc_address" name="arc_address"></sf:textarea>
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