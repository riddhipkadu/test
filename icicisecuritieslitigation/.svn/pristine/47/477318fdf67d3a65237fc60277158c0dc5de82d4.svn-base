
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form class="form-horizontal" role="form" commandName="editAdvocate"  onsubmit="return validateForm();"
					action="./updateAdvocate" method="POST">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Advocate</h2>
					<a href="./listAdvocate"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Update</h2>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1"></label>
							<div class="col-sm-7">
								<sf:input path="advo_id"/>
							</div>
						</div>
						
						<%-- 
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Country :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="advo_country_id" items="${allCoutries}" id="advo_country_id" name="advo_country_id">
								    <sf:option value="0">--Select--</sf:option>
							   </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">State :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="advo_state_id" items="${allState}" id="advo_state_id" name="advo_state_id">
								    <sf:option value="0">--Select--</sf:option>
								    
							   </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">City :</label>
							<div class="col-sm-7">
								<sf:select class="form-control"  path="advo_city_id" items="${allCities}" id="advo_city_id" name="advo_city_id">
								    <sf:option value="0">--Select--</sf:option>
							   </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						 --%>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Law firm :</label>
							<div class="col-sm-7">
							<sf:select class="form-control" items="${allLawFirm}" path="advo_law_firm" id="advo_law_firm" name="advo_law_firm">
								   
							   </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Advocate Name :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="advo_name" id="advo_name" name="advo_name" onkeypress="return blockSpecialChar(event);"/><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Contact no :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="advo_mobile_no" id="advo_mobile_no" name="advo_mobile_no"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Email Id:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="advo_email_id" id="advo_email_id" name="advo_email_id"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Address:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="advo_address" id="advo_address" name="advo_address"></sf:textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Area of Expertise :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allAreaOfExpertise}" path="advo_area_of_expertise" id="advo_area_of_expertise"	name="advo_area_of_expertise">
								</sf:select>
								<i class="asterisk_input"></i>
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

<script src="appJs/Advocate/advocate.js"></script> 
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>