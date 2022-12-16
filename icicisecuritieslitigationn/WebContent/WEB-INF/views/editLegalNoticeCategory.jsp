<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Edit Legal Notice Category</h2>
					<a href="./listLegalNoticeCategory"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="editLegalNoticeCategory" autocomplete="off" cssClass="form-horizontal" onsubmit="return validateForm();" action="./updateLegalNoticeCategory" >
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
			
			<div class="f_form_content">
			<h2>Update Legal Notice Category</h2>
			
				<div class="col-md-12">
			
				<div class="col-md-6" style="display : none;">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Legal Notice Category Id:</label>
							<div class="col-sm-8">
								<sf:input path="lega_noti_category_id" cssClass="form-control" />
								<sf:errors path="lega_noti_category_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
				
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Legal Notice Category :</label>
								<div class="col-sm-5">
							  <sf:input path="lega_noti_category_name" cssClass="form-control" id='lega_noti_category_name' onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
							  
								<sf:errors path="lega_noti_category_name" cssClass="errorBlock"></sf:errors>	 
								</div>
						</div>
						  
							<%-- <div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Unit:</label>
							  <div class="col-sm-5">
							   <sf:input path="loca_name" cssClass="form-control" id='loca_name' />
								<sf:errors path="loca_name" cssClass="errorBlock"></sf:errors>	  </div>
							</div> --%>
				</div>
				
				<div class="col-md-12 litigation_buttons">
				<br>
				
					<center><input type="submit" value="Update" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listLocations' " >Back</button> -->
					</center>
				</div>
				</div>
				
</sf:form>			
			<div style="clear:both"></div>
			
</div>

<script src="appJs/LegalNotice/LegalNoticeCategory.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>