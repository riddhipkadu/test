<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Standard Form Contract Type</h2>
					<a href="./listStandardFormContractType"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	
	<sf:form commandName="editStandardFormContractType" autocomplete="off" cssClass="form-horizontal" method="post" 
				action="./updateStandardFormContractType"  onsubmit="return validateForm();">
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
				<div class="f_form_content">
			<h2>Update Standard Form Contract Type</h2>
			
				<div class="col-md-12">
					<div class="col-md-6" style="display : none;">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Standard Form Contract Type Id:</label>
							<div class="col-sm-8">
								<sf:input path="sfco_type_id" cssClass="form-control" />
								<sf:errors path="sfco_type_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
				
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Standard Form Contract Type:</label>
							<div class="col-sm-5">
								<sf:input path="sfco_type_name" cssClass="form-control" id='sfco_type_name' onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
								<sf:errors path="sfco_type_name" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					<div class="col-md-12 litigation_buttons">
				<br>
				<center>
					<input type="submit" value="Update"  class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listLocations' " >Back</button> -->
				</center>
				
				</div>
			</div>
			</div>
		</sf:form>	
			
			<div style="clear:both"></div>
	</div>
</div>	
<script src="appJs/StandardFormContract/sfc_type.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>