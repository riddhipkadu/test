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
		<sf:form class="form-horizontal" role="form" enctype="multipart/form-data" commandName="addHearingStageOnHearing"
					action="./saveHearingStageOnHearing" method="post">
			<div id="hearing_details">
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #032BEC; font-size: 24px; float: left;">Add Hearing Stage</h2>
					
					<%-- <a href="./litigationDetails?liti_id=<%=request.getParameter("liti_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> --%> 
				</div>
			</div>
			
			<div style="clear: both"></div>
			<div class="f_form_content" >
				<h2>Add</h2>
					
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1">Litigation ID :</label>
								<div class="col-sm-7">
									<sf:input class="form-control" path="ttrn_litigation_id" value='<%=request.getParameter("liti_id")%>'/>
								</div>
						</div>
						
						<div class="form-group" style="display:none">
								<label class="control-label col-sm-5" for="sel1">Hearing ID :</label>
								<div class="col-sm-7">
									<input class="form-control" name="hearing_id" value='<%=request.getParameter("hear_id")%>'/>
								</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Date:</label>
							<div class="col-sm-7">
								<div id="hearing_stage_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="ttrn_hearing_stage_date" id="ttrn_hearing_stage_date" name="ttrn_hearing_stage_date" readonly="true"/><span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5">Next Hearing Date :</label>
							<div class="col-sm-7">
								<div id="hearing_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="ttrn_next_hearing_date" id="ttrn_next_hearing_date" name="ttrn_next_hearing_date" readonly="true"/><span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label  class="control-label col-sm-5 ">Stage Description :</label>
							<div class="col-sm-7">
								<sf:textarea rows="2" cols="43" path="ttrn_stage_description" id="ttrn_stage_description" name="ttrn_stage_description"  placeholder="Enter Comments"></sf:textarea>
							</div>
							<i class="asterisk_input"></i>
						</div>
						
						
					</div> 
					
					<div class="col-md-6">
						
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Action Item :</label>
							<div class="col-sm-7">
								<sf:textarea rows="2" cols="35" class="form-control" path="ttrn_action_item" id="ttrn_action_item" name="ttrn_action_item" placeholder="Enter action item"></sf:textarea>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-5">Action item due Date :</label>
							<div class="col-sm-7">
								<div id="due_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="ttrn_stage_due_date" id="ttrn_stage_due_date" name="ttrn_stage_due_date" readonly="true"/> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
						
						<div id="filesContainer">						
						<!-- <div class="form-group">
							<label class="col-sm-5 control-label">Upload Document :</label>
							<div class="col-sm-3">
								<input type="file" name="stage_document" class="file-loading"/>  
							</div>
							<div class="col-sm-4 " style="text-align: right;">
							  <i class='glyphicon glyphicon-remove-circle' onclick='deleteRow(0);' style="color: red;" title="Delete"></i>
							</div>
							
						</div> -->
                        </div>
						<div class="form-group">
							<label class="control-label col-sm-5"></label>
							<div class="col-sm-7">
								<div class="col-md-6 litigation_buttons">
									<button type="button" class="btn btnTaskCompDoc"
										onclick="addFileInput();">Document</button>
								</div>
							</div>
						</div>

					</div> 
				</div>
				<div class="col-md-12 litigation_buttons">
					<br>
						<center><button type="submit" value="Save" name="Save" class="btn btn-success">Create</button>
						    <!--<button type="submit" name="Draft" value="Draft"class="btn btn-primary">Save As Draft</button> -->
						</center>
				</div>
			</div>
			
			</div>
			<div class="form-group" id="status_updated">
			<br/><br/><br/>
			<h2 style="color: #032BEC; font-size: 24px;">Hearing Stage Status have been updated successfully in the system !!</h2>
			
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>

<script>
var added_counsel = [];
var optionsData = [];
$("#add_counsel").click(function(){
	var lawfirm_id   = $("#ttrn_counsel_law_firm_id").val();
	var lawfirm_name = $("#ttrn_counsel_law_firm_id option:selected").text();
	var extc_id      = $("#ttrn_counsel_id").val();
	var extc_name    = $("#ttrn_counsel_id option:selected").text();
	if(lawfirm_id!=0 && extc_id!=0){
		//console.log("In array "+jQuery.inArray(extc_id,added_counsel));
		//var label = ""+lawfirm_name+"-"+extc_name+"";
		var label = ""+extc_name+"-"+lawfirm_name+"";
		var value = lawfirm_id+"_"+extc_id;
		if(jQuery.inArray(value,added_counsel)==-1){
			added_counsel.push(value);
			//$("#ttrn_counsel_list").append("<option value="+value+" selected>"+label+"</option>");
			$("#txt_div").append('<div class="col-sm-10" id="div_'+value+'" ><input type="hidden" name="counsel_list" value='+value+'> </div>');
			$("#ext_div").append('<div class="col-sm-12" id="coun_'+value+'" style="margin-bottom:5px;"><label class="control-label col-sm-5" for="sel1">Added counsel :</label><div class="col-sm-6" >'
					 +'<input type="text" readonly value="'+label+'" class="form-control" >'
					 +'</div>'				
			        +'<div class="col-sm-1" style="text-align: right; cursor: pointer;">'
			        +'<i class="glyphicon glyphicon-remove-circle"	id='+value+' style="color: red;" title="Delete"></i>'
			        +'</div>'
			        +'</div></div>'
					);
	 }
	}
	
});

//Delete counsel
$(document).on("click",".glyphicon",function(){
	//alert(this.id);
	var removeItem = this.id;
	//console.log(removeItem);
	$("#coun_"+removeItem).remove();
	$("#div_"+removeItem).remove();
	added_counsel = jQuery.grep(added_counsel, function(value) {
		  return value != removeItem;
		});
});

$(document).on("click", ":submit", function(e){
    buttonClicked = $(this).val();
    if(buttonClicked=="Save"){
    	if(validateForm()!=false){
    		$("#ttrn_third_alert").prop("disabled",false);
    		
    		$("form").submit();
    	}else{
    		e.preventDefault();
    	}
    }
    if(buttonClicked=="Draft"){
    	if(validateDraft()!=false){
    		$("#ttrn_third_alert").prop("disabled",false);
    		$("form").submit();
        }else{
    		e.preventDefault();
    	}
    }
    //e.preventDefault();
});

$(document).ready(function(){
	
	if(${hearing_status == 0}){
		$("#hearing_details").show();
		$("#status_updated").hide();
	}else{
		$("#status_updated").show();
		$("#hearing_details").hide();
	}
	
	$("#ttrn_stage_id").multiselect({
		buttonWidth: '300px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#ttrn_stage_id option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#ttrn_stage_id').multiselect('deselect', values);
            }
	});

	$("#ttrn_counsel_law_firm_id").multiselect({
		buttonWidth: '300px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#ttrn_counsel_law_firm_id option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#ttrn_counsel_law_firm_id').multiselect('deselect', values);
            }
	});

});

</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script src="appJs/HearingStage/hearing_validation.js"></script>

