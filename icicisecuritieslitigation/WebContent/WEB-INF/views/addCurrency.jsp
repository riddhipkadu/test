<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Currency</h2>
					<a href="./listCurrency"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addCurrency" autocomplete="off" cssClass="form-horizontal"  
				action="./saveCurrency" onsubmit="return validateForm();">
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			
			<div class="f_form_content">
			<h2>Create </h2>
			
				<div class="col-md-12">
				
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Currency code :</label>
								<div class="col-sm-5">
							  <sf:input path="curr_code" cssClass="form-control" id='curr_code' style="text-transform: uppercase" onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
							  
								<sf:errors path="curr_code" cssClass="errorBlock"></sf:errors>	 
								</div>
						</div>
						  <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Currency name :</label>
								<div class="col-sm-5">
							  <sf:input path="curr_name" cssClass="form-control" id='curr_name' /><i class="asterisk_input"></i>
							  
								<sf:errors path="curr_name" cssClass="errorBlock"></sf:errors>	 
								</div>
						</div>
						<%-- <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Currency rate :</label>
								<div class="col-sm-5">
							  <sf:input path="curr_rate" cssClass="form-control" id='curr_rate' /><i class="asterisk_input"></i>
							  
								<sf:errors path="curr_rate" cssClass="errorBlock"></sf:errors>	 
								</div>
						</div> --%>
							 
				</div>
				<div class="col-md-12 litigation_buttons">
				<br>
					<center><input type="submit" value="Add" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listLocations' " >Back</button> -->
					</center>
				</div>
				</div>
				</sf:form>	
			
			<div style="clear:both"></div>
			
</div>

<script src="appJs/Currency/currency.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>