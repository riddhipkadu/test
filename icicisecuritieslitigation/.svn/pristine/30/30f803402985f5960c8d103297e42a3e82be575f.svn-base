
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form class="form-horizontal" role="form" commandName="editExternalCounsel"
					action="./updateExternalCounsel" onsubmit="return validateForm();"  method="POST">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">External Counsels</h2>
					<a href="./listExternalCounsel"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
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
								<sf:input path="exte_coun_id"/>
								
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Country :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="exte_coun_country_id" items="${allCoutries }" id="exte_coun_country_id" name="exte_coun_country_id">
								    <option value="0">--Select--</option>
								    
							   </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">State :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="exte_coun_state_id" items="${allState}" id="exte_coun_state_id" name="exte_coun_state_id">
								    <option value="0">--Select--</option>
								    
							   </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">City :</label>
							<div class="col-sm-7">
								<sf:select class="form-control"  path="exte_coun_city" items="${allCities}" id="exte_coun_city" name="exte_coun_city">
								    <option value="0">--Select--</option>
								
							   </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div>
						
					   <div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Law firm :</label>
							<div class="col-sm-7">
							<sf:select class="validate[required]" items="${allLawFirm}" path="exte_coun_law_firm" id="exte_coun_law_firm" name="exte_coun_law_firm">
							   </sf:select>
							   <i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Counsel Name :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="exte_coun_name" id="exte_coun_name" name="exte_coun_name" onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Contact no :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="exte_coun_mobile_no" id="exte_coun_mobile_no" name="exte_coun_mobile_no"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Email Id:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="exte_coun_email_id" id="exte_coun_email_id" name="exte_coun_email_id"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Address:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="exte_coun_address" id="exte_coun_address" name="exte_coun_address"></sf:textarea>
							</div>
						</div>
						
						
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Area of Expertise :</label>
							<div class="col-sm-7">
								<sf:select class="validate[required]" items="${allAreaOfExpertise}" path="exte_coun_area_of_expertise" id="exte_coun_area_of_expertise"	name="exte_coun_area_of_expertise">
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

<script>
$(document).ready(function(){

	$("#exte_coun_law_firm").multiselect({
		buttonWidth: '300px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
	      var values = [];
	      $('#exte_coun_law_firm option').each(function() {
	          if ($(this).val() !== option.val()) {
	              values.push($(this).val());
	          }
	      });

	      $('#exte_coun_law_firm').multiselect('deselect', values);
	  }
	});

	$("#exte_coun_area_of_expertise").multiselect({
		buttonWidth: '300px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 130,
	    onChange: function(option, checked) {
	      var values = [];
	      $('#exte_coun_area_of_expertise option').each(function() {
	          if ($(this).val() !== option.val()) {
	              values.push($(this).val());
	          }
	      });

	      $('#exte_coun_area_of_expertise').multiselect('deselect', values);
	  }
	});
	
});

</script>

<script src="appJs/ExternalCounsel/external_counsel.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>