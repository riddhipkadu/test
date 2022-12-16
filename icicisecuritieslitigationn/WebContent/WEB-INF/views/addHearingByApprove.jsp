<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<!-- Fail Modal -->
			<div class="modal fade" id="dialogBox" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #e26d1c;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								<span class="glyphicon glyphicon-remove-sign"></span> &nbsp;Error
							</h4>
						</div>
						<div class="modal-body">
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
<!-- Fail Modal END -->


<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form class="form-horizontal" role="form" enctype="multipart/form-data" commandName="addHearingByApprove"
					action="./updateHearingStage" method="post">
			
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Add Hearing Stage</h2>
					 <%-- <a href="./litigationDetails?liti_id=<%=request.getParameter("liti_id")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> --%> 
				</div>
			</div>
			
			<div style="clear: both"></div>
			
			<div class="f_form_content">
				<h2>Add</h2>

				<div class="col-md-12">


					<div class="col-md-6">
						<div class="form-group" style="display: none">
							<label class="control-label col-sm-5" for="sel1"> ID :</label>
							<div class="col-sm-7">
								<sf:input class="form-control" path="ttrn_hearing_stage" />
							</div>
						</div>
						<div class="form-group" style="display: none">
							<label class="control-label col-sm-5" for="sel1">Litigation
								ID :</label>
							<div class="col-sm-7">
								<sf:input class="form-control" path="ttrn_litigation_id" />
							</div>
						</div>
						<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Stage :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="ttrn_stage_id" name="ttrn_stage_id" id="ttrn_stage_id">
								     <option value="0" selected="selected">--Select--</option>
									 <c:forEach items="${listStages}" var="stage">
									   <sf:option value="${stage.stage_id}">${stage.stage_name}</sf:option>
									 </c:forEach>
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div> --%>
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
							<label class="col-sm-5 control-label">First Alert :</label>
							<div class="col-sm-7">
								<div id="first_alert_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="ttrn_first_alert" id="ttrn_first_alert" name="ttrn_first_alert"  readonly="true" /> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div><i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-5 control-label">Second Alert :</label>
							<div class="col-sm-7">
								<div id="second_alert_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="ttrn_second_alert" id="ttrn_second_alert" name="ttrn_second_alert" readonly="true" /> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-5 control-label">Third Alert :</label>
							<div class="col-sm-7">
								<div id="third_alert_div" class="input-group date"
									data-date-format="MM">
									<sf:input class="form-control" path="ttrn_third_alert" id="ttrn_third_alert" name="ttrn_third_alert" value="${threeDayBefore}" readonly="true" /> <span
										class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Additional Email Id :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control" path="ttrn_additional_mail_id_1" id="ttrn_additional_mail_id" name="ttrn_additional_mail_id" />
							</div><p style="text-align: right;">(Please enter atmost three Email id seperated by comma.)</p>
						</div>
					</div> 
					
					<div class="col-md-6">
					
					<div class="form-group">
							<label  class="col-sm-5 control-label">Stage Description :</label>
							<div class="col-sm-6">
								<sf:textarea rows="2" cols="35" path="ttrn_stage_description" id="ttrn_stage_description" name="ttrn_stage_description"  placeholder="Enter Comments"></sf:textarea>
							</div>
							<i class="asterisk_input"></i>
					</div>
					<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Action Item :</label>
							<div class="col-sm-7">
								<sf:textarea rows="2" cols="35" class="form-control" path="ttrn_action_item" id="ttrn_action_item" name="ttrn_action_item"></sf:textarea>
							</div>
					</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Responsible
								Person :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" name="ttrn_responsible_person"
									path="ttrn_responsible_person" id="ttrn_responsible_person">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${user_list}" var="user_legal_department">
										<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
											${user_legal_department.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Counsel Law Firm :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="ttrn_counsel_law_firm_id" name="ttrn_law_firm_id">
								   <!-- <option value="0">--Select--</option> -->
								   <c:forEach items="${allLawFirm}" var="lawf">
								   <sf:option value="${lawf.key}">${lawf.value}</sf:option>
								   </c:forEach>
							   </sf:select >
							</div>
						</div>
						
						<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Counsel
								Name :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" name="ttrn_counsel_id"
									path="ttrn_counsel_id" id="ttrn_counsel_id">
									<sf:option value="0">--Select--</sf:option>
									 <c:forEach items="${external_counsel_list}"
										var="external_counsel">
										<sf:option value="${external_counsel.exte_coun_id}">${external_counsel.exte_coun_name}</sf:option>
									</c:forEach> 
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">External counsel :</label>
							<div class="col-sm-5">
								<sf:select class="form-control" name="ttrn_counsel_id"
									path="ttrn_counsel_id" id="ttrn_counsel_id">
									<option value="0">--Select--</option>
								</sf:select>
								
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary btn-sm" id="add_counsel">Add</button>
							 </div>
						</div>
						<div  id="ext_div">
							
						</div>
						<!-- Add hidden text field -->
						<div class="form-group" style="display:none;" id="txt_div">
							
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
						<c:choose>
							<c:when test="${fn:length(hearing_stage_doc) != 0}">
								<div class="form-group">
									<label class="control-label col-sm-5">Document Attached
										:</label>
									<div class="col-sm-4">
										<c:forEach items="${hearing_stage_doc}" var="document"
											varStatus="doc">
											 <div id="doc_attached_${document.hear_doc_id}" class="col-sm-3">

												<div class="col-sm-3"
													style="text-align: right; cursor: pointer;">
													<a
														href="downloadHearingDocument?hearing_doc_id=${document.hear_doc_id}">
														${document.hear_stage_original_file_name}</a> <i
														class="glyphicon glyphicon-remove-circle"
														onclick="deleteHearingStageDoc(${document.hear_doc_id});"
														style="color: red; margin-right: -250px; margin-top: -19px;"
														title="Delete"></i>
												</div>
											</div><br/> 
										</c:forEach>
									</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div> 
				</div>
				<div class="col-md-12 litigation_buttons">
					<br>
						<center><button type="submit" value="Update" name="Update" class="btn btn-myPrimary">Add</button>
						    	 <%-- <c:if test="${status=='Draft'}">
						    	 <button type="submit" name="Draft" value="Draft"class="btn btn-myDefault">Update As Draft</button>
						    	 </c:if> --%>
						</center>
				</div>
			</div>
		</sf:form>
		<div style="clear: both"></div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	
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


var added_counsel = [];
var optionsData = [];
var counsel_list = jQuery.parseJSON('${counsel_list}');
$.each(counsel_list,function(key,value){
	//alert(value['hsco_law_firm_name']);
	var lawfirm_id   = value['hsco_law_firm_id'];
	var lawfirm_name = value['hsco_law_firm_name'];
	var extc_id      = value['hsco_counsel_id'];
	var extc_name    = value['hsco_counsel_name'];
	var hsco_id      = value['hsco_id'];
	if(lawfirm_id!=0 && extc_id!=0){
		var label = ""+extc_name+"-"+lawfirm_name+"";
		var value = lawfirm_id+"_"+extc_id;
		if(jQuery.inArray(value,added_counsel)==-1){
			added_counsel.push(value);
			$("#txt_div").append('<div class="col-sm-10" id="div_'+hsco_id+'" ><input type="hidden" name="counsel_list_edit" value='+value+'> </div>');
			$("#ext_div").append('<div class="col-sm-12" id="coun_'+hsco_id+'" style="margin-bottom:5px;"><label class="control-label col-sm-5" for="sel1">Added counsel :</label><div class="col-sm-6" >'
					 +'<input type="text" readonly value="'+label+'" class="form-control" >'
					 +'</div>'				
			        +'<div class="col-sm-1" style="text-align: right; cursor: pointer;">'
			        +'<i class="glyphicon glyphicon-remove-circle" onclick="delete_coun('+hsco_id+');" style="color: red;" title="Delete"></i>'
			        +'</div>'
			        +'</div></div>'
					);
	 }
	}
}); 

$("#ttrn_next_hearing_date").on("change", function(e){
	//alert();
	 var date2 = $('#hearing_date_div').datepicker('getDate'); 
	  date2.setDate(date2.getDate()-3); 
	  $('#third_alert_div').datepicker('setDate', date2);
});

$("#add_counsel").click(function(){
	var lawfirm_id   = $("#ttrn_counsel_law_firm_id").val();
	var lawfirm_name = $("#ttrn_counsel_law_firm_id option:selected").text();
	var extc_id      = $("#ttrn_counsel_id").val();
	var extc_name    = $("#ttrn_counsel_id option:selected").text();
	if(lawfirm_id!=0 && extc_id!=0){
		var label = ""+extc_name+"-"+lawfirm_name+"";
		var value = lawfirm_id+"_"+extc_id;
		if(jQuery.inArray(value,added_counsel)==-1){
			added_counsel.push(value);
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

function delete_coun(id){
	var hsco_id = id;
	items = {};
	items["hsco_id"] = hsco_id;
	var jsonString = JSON.stringify(items);
	
		 if(hsco_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteHearingCounselById",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(result) {
				    			 if(result == 1){ 
				    				
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Counsel deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$("#coun_"+hsco_id).remove();
				    				
				    			} 
				    		}
					 });
				 }
			 });
		} 
}

$(document).on("click", ":submit", function(e){
    buttonClicked = $(this).val();
    if(buttonClicked=="Update"){
    	if(validateForm()!=false){
    		$("form").submit();
    	}else{
    		e.preventDefault();
    	}
    }
    if(buttonClicked=="Draft"){
    	if(validateDraft()!=false){
    		$("form").submit();
        }else{
    		e.preventDefault();
    	}
    }
    //e.preventDefault();
});


/* $(document).ready(function(){

$('#third_alert_div').datepicker('setDate', date2);
}); */
</script>
<script src="appJs/HearingStage/hearing_validation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>