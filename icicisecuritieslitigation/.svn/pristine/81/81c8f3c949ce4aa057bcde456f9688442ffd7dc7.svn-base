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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Litigation Type</h2>
					<a href="./listLitigationType"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	
	<sf:form commandName="edit_liti_type" autocomplete="off" cssClass="form-horizontal" method="post" 
				action="./updateLitigation_type"  onsubmit="return validateForm();">
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
				
				<div class="f_form_content">
			<h2>Update Litigation Type</h2>
			
				<div class="col-md-12">
					<div class="col-md-6" style="display : none;">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Litigation Id:</label>
							<div class="col-sm-8">
								<sf:input type="hidden" path="liti_type_id" cssClass="form-control" />
								<sf:errors path="liti_type_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
				
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Litigation type:</label>
							<div class="col-sm-5">
								<sf:input path="liti_type_name" cssClass="form-control" onkeypress="return blockSpecialChar(event);" /><i class="asterisk_input"></i>
								<sf:errors path="liti_type_name" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					<div class="col-md-12 litigation_buttons">
				<br>
				<center>
					<input type="submit" value="Update"  class="btn btn-myPrimary"/>
				</center>
				
				</div>
			</div>
			</div>
		</sf:form>	
			
			<div style="clear:both"></div>
	</div>
</div>	

<script src="appJs/Litigation/liti_type_validation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>