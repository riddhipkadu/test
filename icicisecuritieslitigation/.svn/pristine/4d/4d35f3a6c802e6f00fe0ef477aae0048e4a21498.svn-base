
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form class="form-horizontal" role="form" commandName="addInternalLitigation"  onsubmit="return validateForm();"
					action="./saveInternalLitigation"  method="POST">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Internal Litigation</h2>
					<a href="./listInternalLitigation"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
			
			<div style="clear: both"></div>
		
			
			<div class="f_form_content">
				<h2>Create</h2>

				<div class="col-md-12">

					<div class="col-md-6">
					
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Internal litigation code :</label>
							<div class="col-sm-7">
								<input type="text" class="form-control" id="internal_liti_code" name="internal_liti_code" onkeypress="return blockSpecialChar(event);"/>
								<i class="asterisk_input"></i>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Code description:</label>
							<div class="col-sm-7">
								<textarea rows="4" cols="48" id="internal_liti_desc" name="internal_liti_desc"></textarea><i class="asterisk_input"></i>
							</div>
						</div>
						
					</div> 
				</div>
				
				<div class="col-md-12 litigation_buttons">
					<button type="submit" class="btn btn-myPrimary" style="margin-left:340px;">Create</button>
					
				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>


<script src="appJs/InternalLitigation/internalLitigation.js"></script> 

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
