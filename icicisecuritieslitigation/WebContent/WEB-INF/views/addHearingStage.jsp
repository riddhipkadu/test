<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_container">
	<!--heading text-->

	<div style="clear: both"></div>
	<!--first form-->
	<sf:form class="form-horizontal" role="form"
		enctype="multipart/form-data" commandName="addHearingStage"
		action="./saveHearingStage" method="post">

		<div class="col-md-10">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Add
					Hearing Stage</h2>
				<a
					href="./litigationDetails?liti_id=<%=request.getParameter("liti_id")%>"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>

		<div style="clear: both"></div>
		<div class="f_form_content">
			<h2>Add</h2>

			<div class="col-md-12">
				<div class="col-md-6">
					<div class="form-group" style="display: none">
						<label class="control-label col-sm-5" for="sel1">Litigation
							ID :</label>
						<div class="col-sm-7">
							<sf:input class="form-control" path="ttrn_litigation_id"
								value='<%=request.getParameter("liti_id")%>' />
						</div>
					</div>
					<%-- <div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Stage :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="ttrn_stage_id" name="ttrn_stage_id" id="ttrn_stage_id">
								     <option value="0" selected="selected">--Select--</option>
									 <c:forEach items="${listStages}" var="stage">
									   <option value="${stage.stage_id}">${stage.stage_name}</option>
									 </c:forEach>
							    </sf:select><i class="asterisk_input"></i>
								
							</div>
						</div> --%>
					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1">Date:</label>
						<div class="col-sm-7">
							<div id="hearing_stage_date_div" class="input-group date"
								data-date-format="MM">
								<sf:input class="form-control" path="ttrn_hearing_stage_date"
									id="ttrn_hearing_stage_date" name="ttrn_hearing_stage_date"
									readonly="true" />
								<span class="input-group-addon"> <i
									class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4">Next Hearing Date :</label>
						<div class="col-sm-7">
							<div id="hearing_date_div" class="input-group date"
								data-date-format="MM">
								<sf:input class="form-control" path="ttrn_next_hearing_date"
									id="ttrn_next_hearing_date" name="ttrn_next_hearing_date"
									readonly="true" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-calendar"></i></span>
							</div>
							<i class="asterisk_input"></i>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">First Alert :</label>
						<div class="col-sm-7">
							<div id="first_alert_div" class="input-group date"
								data-date-format="MM">
								<sf:input class="form-control" path="ttrn_first_alert"
									id="ttrn_first_alert" name="ttrn_first_alert" readonly="true" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">Second Alert :</label>
						<div class="col-sm-7">
							<div id="second_alert_div" class="input-group date"
								data-date-format="MM">
								<sf:input class="form-control" path="ttrn_second_alert"
									id="ttrn_second_alert" name="ttrn_second_alert" readonly="true" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label">Third Alert :</label>
						<div class="col-sm-7">
							<div id="third_alert_div" class="input-group date"
								data-date-format="MM">
								<sf:input class="form-control" path="ttrn_third_alert"
									id="ttrn_third_alert" name="ttrn_third_alert" readonly="true"
									disabled="true" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1">Additional
							Email Id :</label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control"
								path="ttrn_additional_mail_id_1" id="ttrn_additional_mail_id"
								name="ttrn_additional_mail_id" />
						</div>
						<p style="text-align: center;">
							<br> <br>(Please enter atmost three Email id seperated
							by comma)
						</p>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4 ">Stage Description :</label>
						<div class="col-sm-7">
							<sf:textarea rows="4" cols="46" path="ttrn_stage_description"
								id="ttrn_stage_description" name="ttrn_stage_description"
								placeholder="Enter Comments"></sf:textarea>
							<i class="asterisk_input"></i>
						</div>
					</div>
				</div>

				<div class="col-md-6">

					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1">Action
							Item :</label>
						<div class="col-sm-7">
							<sf:textarea rows="3" cols="42" class="form-control"
								path="ttrn_action_item" id="ttrn_action_item"
								name="ttrn_action_item" placeholder="Enter action item"></sf:textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1"> Primary
							Responsible Person :</label>
						<div class="col-sm-7">
							<sf:select class="form-control" name="ttrn_responsible_person"
								path="ttrn_responsible_person" id="ttrn_responsible_person">
								<option value="0">--Select--</option>
								<c:forEach items="${user_list}" var="user_legal_department">
									<option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
										${user_legal_department.user_last_name}</option>
								</c:forEach>
							</sf:select>
							<i class="asterisk_input"></i>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1">
							Secondary Responsible Person:</label>
						<div class="col-sm-7">
							<sf:select class="form-control" name="ttrn_secondary_person"
								path="ttrn_secondary_person" id="ttrn_secondary_person">
								<option value="0">--Select--</option>
								<c:forEach items="${user_lists}" var="user_secondary_department">
									<option value="${user_secondary_department.user_id}">${user_secondary_department.user_first_name}
										${user_secondary_department.user_last_name}</option>
								</c:forEach>
							</sf:select>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1">Advocate/Law
							Firm :</label>
						<div class="col-sm-8">
							<sf:select class="form-control" path="ttrn_counsel_law_firm_id"
								name="ttrn_law_firm_id">
								<!-- <option value="0">--Select--</option> -->
								<c:forEach items="${allLawFirm}" var="lawf">
									<sf:option value="${lawf.key}">${lawf.value}</sf:option>
								</c:forEach>
							</sf:select>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="sel1">External
							counsel :</label>
						<div class="col-sm-6">
							<sf:select class="form-control" name="ttrn_counsel_id"
								path="ttrn_counsel_id" id="ttrn_counsel_id">
								<option value="0">--Select--</option>
							</sf:select>
							<!-- <i class="asterisk_input"></i> -->
						</div>
						<div class="col-sm-2">
							<button type="button" class="btn btn-primary btn-sm"
								id="add_counsel">Add</button>
						</div>
					</div>
					<div id="ext_div">
						<!-- <div class="col-sm-5">
								<select class="form-control" name="ttrn_counsel_list"
									id="ttrn_counsel_list" multiple="multiple">
									<option value="0">--Added Counsel--</option>
								</select> <i class="asterisk_input"></i>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary btn-sm"
									id="remove_counsel">Remove</button>
							</div> -->

					</div>
					<!-- Add hidden text field -->
					<div class="form-group" style="display: none;" id="txt_div">

					</div>

					<div class="form-group">
						<label class="control-label col-sm-4">Action item due Date
							:</label>
						<div class="col-sm-7">
							<div id="due_date_div" class="input-group date"
								data-date-format="MM">
								<sf:input class="form-control" path="ttrn_stage_due_date"
									id="ttrn_stage_due_date" name="ttrn_stage_due_date"
									readonly="true" />
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-calendar"></i></span>
							</div>
							<i class="asterisk_input"></i>
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

				<center>
					<button type="submit" value="Save" name="Save"
						class="btn btn-success">Create</button>
					<button type="submit" name="Draft" value="Draft"
						class="btn btn-primary">Save As Draft</button>
				</center>

			</div>
		</div>
	</sf:form>

	<div style="clear: both"></div>

</div>


<script>
	var added_counsel = [];
	var optionsData = [];
	$("#add_counsel")
			.click(
					function() {
						var lawfirm_id = $("#ttrn_counsel_law_firm_id").val();
						var lawfirm_name = $(
								"#ttrn_counsel_law_firm_id option:selected")
								.text();
						var extc_id = $("#ttrn_counsel_id").val();
						var extc_name = $("#ttrn_counsel_id option:selected")
								.text();
						if (lawfirm_id != 0 && extc_id != 0) {
							//console.log("In array "+jQuery.inArray(extc_id,added_counsel));
							//var label = ""+lawfirm_name+"-"+extc_name+"";
							var label = "" + extc_name + "-" + lawfirm_name
									+ "";
							var value = lawfirm_id + "_" + extc_id;
							if (jQuery.inArray(value, added_counsel) == -1) {
								added_counsel.push(value);
								//$("#ttrn_counsel_list").append("<option value="+value+" selected>"+label+"</option>");
								$("#txt_div")
										.append(
												'<div class="col-sm-10" id="div_'+value+'" ><input type="hidden" name="counsel_list" value='+value+'> </div>');
								$("#ext_div")
										.append(
												'<div class="col-sm-12" id="coun_'+value+'" style="margin-bottom:5px;"><label class="control-label col-sm-5" for="sel1">Added counsel :</label><div class="col-sm-6" >'
														+ '<input type="text" readonly value="'+label+'" class="form-control" >'
														+ '</div>'
														+ '<div class="col-sm-1" style="text-align: right; cursor: pointer;">'
														+ '<i class="glyphicon glyphicon-remove-circle"	id='+value+' style="color: red;" title="Delete"></i>'
														+ '</div>'
														+ '</div></div>');
								/*var a  ={"label": label,"value": value,selected: true};
								optionsData.push(a);
								added_counsel.push(value);
								console.log("Option "+optionsData);
								$("#ttrn_counsel_list").multiselect('dataprovider', optionsData);*/
							}
						}

					});
	/*$("#ttrn_counsel_list").multiselect({
	 buttonWidth: '310px',
	 enableFiltering: true,
	 filterBehavior: 'text',
	 enableCaseInsensitiveFiltering: true,
	 onChange: function(option, checked, select) {
	 alert('Changed option ' + $(option).val() + '.'+$(option).text());
	 //alert($('option[value="'+$(option).val()+'"]').remove());
	 //$('#ttrn_counsel_list option[value="'+$(option).val()+'"]').remove();
	 //$(this).find('option').not(':selected').remove();
	 // $("#ttrn_counsel_list").find('option[value="'+$(option).val()+'"]').remove();
	 }
	
	
	 });*/
	//Delete counsel
	$(document).on("click", ".glyphicon", function() {
		//alert(this.id);
		var removeItem = this.id;
		//console.log(removeItem);
		$("#coun_" + removeItem).remove();
		$("#div_" + removeItem).remove();
		added_counsel = jQuery.grep(added_counsel, function(value) {
			return value != removeItem;
		});
	});

	$(document).on("click", ":submit", function(e) {
		buttonClicked = $(this).val();
		if (buttonClicked == "Save") {
			if (validateForm() != false) {
				$("#ttrn_third_alert").prop("disabled", false);

				$("form").submit();
			} else {
				e.preventDefault();
			}
		}
		if (buttonClicked == "Draft") {
			if (validateDraft() != false) {
				$("#ttrn_third_alert").prop("disabled", false);
				$("form").submit();
			} else {
				e.preventDefault();
			}
		}
		//e.preventDefault();
	});

	/*$("#remove_counsel").click(function(){
	 var removeItem = $("#ttrn_counsel_list").val();
	 $('#ttrn_counsel_list option[value="'+removeItem+'"]').remove();
	 //$("#ttrn_counsel_list").multiselect('selectAll', true);
	 //$("#ttrn_counsel_list").multiselect("rebuild");
	 //$("#ttrn_counsel_list").multiselect('selectAll', true);
	
	 //console.log("value "+removeItem);
	 //console.log("Before delete array "+added_counsel);
	 //Delete from array
	 var index = added_counsel.indexOf(removeItem);
	 console.log("Index "+index);
	 if(index > -1)
	 added_counsel.splice(index, 1);
	
	 //console.log("after delete array "+added_counsel);
	 //console.log()
	 added_counsel = jQuery.grep(added_counsel, function(value) {
	 console.log("deleted value "+removeItem);
	 return value != removeItem;
	 });
	 $("#"+value).remove();
	 //$('#ttrn_counsel_list').multiselect('select', added_counsel);
	 console.log("after delete array "+added_counsel);
	 });*/

	$(document).ready(function() {

		/* $("#ttrn_next_hearing_date").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"


		}).datepicker(); */

		$("#ttrn_next_hearing_date").on("change", function(e) {
			//alert();
			var date2 = $('#hearing_date_div').datepicker('getDate');
			date2.setDate(date2.getDate() - 3);
			$('#third_alert_div').datepicker('setDate', date2);
		});

		$("#ttrn_stage_id").multiselect({
			buttonWidth : '300px',
			enableFiltering : true,
			filterBehavior : 'text',
			enableCaseInsensitiveFiltering : true,
			maxHeight : 200,
			onChange : function(option, checked) {
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
			buttonWidth : '300px',
			enableFiltering : true,
			filterBehavior : 'text',
			enableCaseInsensitiveFiltering : true,
			maxHeight : 200,
			onChange : function(option, checked) {
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

