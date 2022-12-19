
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
		<sf:form class="form-horizontal" role="form" commandName="editArcManager"
					action="./updateArcManager" onsubmit=""  method="POST">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #032BEC; font-size: 24px; float: left;">ARC Manager</h2>
					<a href="./listArcManager"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
			
			<div style="clear: both"></div>
		
			
			<div class="f_form_content">
				<h2>Create</h2>

			<div class="col-md-12">

                  <div class="col-md-6">
                     
                     <div class="form-group" style="display:none">
							<label class="control-label col-sm-5" for="sel1">Id</label>
							<div class="col-sm-7">
								<sf:input path="mgr_arc_id"/>
								
							</div>
						</div>
                  
					   <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Asset Reconstruction Company:</label>
							<div class="col-sm-7">
							<sf:select class="form-control" items="${allArcMgr}" path="mgr_arc_name" id="mgr_arc_name" name="mgr_arc_name">
							   </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> ARC Manager :</label>
							<div class="col-sm-7">
							
								<sf:input type="text" class="form-control" path="mgr_name" id="mgr_name" name="mgr_name"/><i class="asterisk_input"></i>
							 <i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Contact no :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="mgr_arc_contact_no" id="mgr_arc_contact_no" name="mgr_arc_contact_no"/>
							 <i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Email Id:</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="mgr_arc_email_id" id="mgr_arc_email_id" name="mgr_arc_email_id"/>
							 <i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1"> Address:</label>
							<div class="col-sm-7">
								<sf:textarea rows="4" cols="40" path="mgr_arc_address" id="mgr_arc_address" name="mgr_arc_address"></sf:textarea>
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
</div>

<script>
$(document).ready(function(){
	
    $("#mgr_arc_name").multiselect({
		buttonWidth: '300px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
          var values = [];
          $('#mgr_arc_name option').each(function() {
              if ($(this).val() !== option.val()) {
                  values.push($(this).val());
              }
          });

          $('#mgr_arc_name').multiselect('deselect', values);
      }
	});
	
});

</script>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

