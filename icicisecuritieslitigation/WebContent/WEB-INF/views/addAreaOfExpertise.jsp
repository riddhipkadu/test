<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Area Of Expertise</h2>
					<a href="./listAreaOfExpertise"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addAreaOfExpertise" autocomplete="off" cssClass="form-horizontal" onsubmit="return validateForm();"
				action="./saveAreaOfExpertise" >
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
				
			<div class="f_form_content">
			<h2>Create Area Of Expertise</h2>
			
				<div class="col-md-12">
				
						<div class="form-group">
							  <label class="control-label col-sm-4" for="sel1">Area Of Expertise:</label>
								<div class="col-sm-5">
							  <sf:input path="area_expe_name" cssClass="form-control" id='area_expe_name' onkeypress="return blockSpecialChar(event);"/><i class="asterisk_input"></i>
							  
								<sf:errors path="area_expe_name" cssClass="errorBlock"></sf:errors>	 
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
				
					<center><input type="submit" value="Add" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listLocations' " >Back</button> -->
					</center>
				</div>
				</div>
				</sf:form>	
			
			<div style="clear:both"></div>
			
</div>

<script src="appJs/AreaOfExpertise/area_of_expertise.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	  
    $('#area_expe_name').click(function(){
        $('#area_expe_name').popover('destroy');
    });

}); 
 
 var area_Of_Expertise_status;
 $("#area_expe_name").keyup(function(){ 
	  var area_expe_name = $("#area_expe_name").val();
	  var area_expe_id = 0;

		if(area_expe_name !='' && area_expe_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["area_expe_id"] = area_expe_id;
				items["area_expe_name"] = area_expe_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isAreaExpeNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						//alert(result+" resulatt");
						if(result==0||result=='0'){
							area_Of_Expertise_status = 0;
							$('#area_expe_name').popover('destroy');
						}
						else{
							area_Of_Expertise_status = 1;
							$("#area_expe_name").attr("data-placement", "top");
							$("#area_expe_name").attr("data-content", "Area Of Expertise already exists..!!");
							$('#area_expe_name').popover('show');
							return false;
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
 });	
 
 $('#addAreaOfExpertise').on('keyup keypress', function(e) {
	  var keyCode = e.keyCode || e.which;
	  if (keyCode === 13) { 
	    e.preventDefault();
	    return false;
	  }
	});
 
      
    	  
    	  
</script>

     

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
