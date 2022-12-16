<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<h2 style="color:#a72f14;font-size:24px;float:left;">Litigation type</h2>
					<a href="./listLitigationType"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="add_litigation" autocomplete="off" cssClass="form-horizontal" onsubmit="return validateForm();"
				action="./saveLitigationType" >
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Create Litigation Type</h2>
			
				<div class="col-md-12">
				
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Litigation type:</label>
								<div class="col-sm-5">
							  <sf:input path="liti_type_name" cssClass="form-control" id='liti_type_name' onkeypress="return blockSpecialChar(event);"/><i class="asterisk_input"></i>
								</div>
						</div>
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


<script src="appJs/Litigation/liti_type_validation.js"></script>
     

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
