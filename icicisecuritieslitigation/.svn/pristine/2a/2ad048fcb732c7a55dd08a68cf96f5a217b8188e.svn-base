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
		<sf:form class="form-horizontal" role="form"
			commandName="editLegalNotice" enctype="multipart/form-data"
			action="./updateLegalNotice" method="post">

			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Update
						Legal Notice</h2>
					<a href="./listLegalNotice"><img
						src="images/DashboardIcons/backold.png" class="backButton"
						width="100px;"></a>
				</div>
			</div>

			<div style="clear: both"></div>

			<div class="f_form_content">
				<h2>Update</h2>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="form-group" style="display: none">
							<label class="control-label col-sm-5" for="sel1">ID :</label>
							<div class="col-sm-7">
								<sf:input class="form-control" path="lega_noti_id" />
							</div>
						</div>
						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Entity :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="lega_noti_entity_id"
									items="${allOrganization}" name="lega_noti_entity_id"
									id="lega_noti_entity_id">

								</sf:select>
								<i class="asterisk_input"></i>

							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Entity/Company
								Name: </label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_entity_id" cssClass="form-control">
									<sf:option value="2">ICICI Securities</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Unit/Location
								:</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allLocations}"
									name="lega_noti_unit_id" path="lega_noti_unit_id"
									id="lega_noti_unit_id">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Unit/Location:
							</label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_unit_id" cssClass="form-control">
									<sf:option value="1">Mumbai</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Function/Department
								:</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allDepartments}"
									name="lega_noti_function_id" path="lega_noti_function_id"
									id="lega_noti_function_id">
									<option value="0">--Select--</option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div> --%>

						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Function/
								Department:</label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_function_id" cssClass="form-control">
									<sf:option value="1">Legal</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">By/Against
								Company :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" name="lega_noti_by_against"
									path="lega_noti_by_against" id="lega_noti_by_against">
									<sf:option value="NA">--Select--</sf:option>
									<sf:option value="By">By Company</sf:option>
									<sf:option value="Against">Against Company</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">
								Opposite Party :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"
									path="lega_noti_opposite_party" id="lega_noti_opposite_party"
									name="lega_noti_opposite_party" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Category
								:</label>
							<div class="col-sm-7">
								<sf:select class="form-control" path="lega_noti_category_id"
									id="lega_noti_category_id">
									<option value="0" selected="selected">--Select--</option>
									<c:forEach items="${allLegalCategory }" var="cat">
										<sf:option value="${cat.lega_noti_category_id }">${cat.lega_noti_category_name }</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Notice/Ref.
								No :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"
									path="lega_noti_reference_no" id="lega_noti_reference_no"
									name="lega_noti_reference_no" />
							</div>
						</div>

						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Primary
								Responsible Person:</label>
							<div class="col-sm-7">
								<sf:select class="form-control" name="lega_noti_assigned_to_id"
									path="lega_noti_assigned_to_id" id="lega_noti_assigned_to_id">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${user_legal_department}"
										var="user_legal_department">
										<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Primary
								Responsible Person: </label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_assigned_to_id" items="${allUsers}"
									cssClass="form-control">
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Secondary
								Responsible Person :</label>
							<div class="col-sm-7">
								<sf:select class="form-control"
									name="lega_noti_secondary_responsible_person"
									path="lega_noti_secondary_responsible_person"
									id="lega_noti_secondary_responsible_person">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${users_legals_departments}"
										var="users_legals_departments">
										<sf:option value="${users_legals_departments.user_id}">${users_legals_departments.user_first_name}
												${users_legals_departments.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Secondary
								Responsible Person: </label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_secondary_responsible_person"
									items="${allUsers}" cssClass="form-control">
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Third
								Responsible Person :</label>
							<div class="col-sm-7">
								<sf:select class="form-control"
									name="lega_noti_third_responsible_person"
									path="lega_noti_third_responsible_person"
									id="lega_noti_third_responsible_person">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${user_third_legal_department}"
										var="user_third_legal_department">
										<sf:option value="${user_third_legal_department.user_id}">${user_third_legal_department.user_first_name}
												${user_third_legal_department.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Third
								Responsible Person: </label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_third_responsible_person"
									items="${allUsers}" cssClass="form-control">
								</sf:select>
								<!-- 	<i class="asterisk_input"></i> -->

							</div>
						</div>
						<%-- 	<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Other
								Responsible Person :</label>
							<div class="col-sm-7">
								<sf:select class="form-control"
									name="lega_noti_other_responsible_person"
									path="lega_noti_other_responsible_person"
									id="lega_noti_other_responsible_person">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${user_other_legal_department}"
										var="user_other_legal_department">
										<sf:option value="${user_other_legal_department.user_id}">${user_other_legal_department.user_first_name}
												${user_other_legal_department.user_last_name}</sf:option>
									</c:forEach>
								</sf:select>
							</div>
						</div> --%>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Fourth
								Responsible Person: </label>
							<div class="col-sm-7">
								<sf:select path="lega_noti_other_responsible_person"
									items="${allUsers}" cssClass="form-control">
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->

							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label col-sm-5">Notice Dated :</label>
							<div class="col-sm-7">
								<div id="noti_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" path="lega_noti_dated"
										id="lega_noti_dated" name="lega_noti_dated" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Sent/Received On :</label>
							<div class="col-sm-7">
								<div id="recei_date_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control" path="lega_noti_recived_on"
										id="lega_noti_recived_on" name="lega_noti_recived_on"
										readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Notice Reply
								Deadline :</label>
							<div class="col-sm-7">
								<div id="reply_dead_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control"
										path="lega_noti_reply_deadline" id="lega_noti_reply_deadline"
										name="lega_noti_reply_deadline" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Notice reminder
								date :</label>
							<div class="col-sm-7">
								<div id="noti_remain_div" class="input-group date"
									data-date-format="MM">
									<sf:input cssClass="form-control"
										path="lega_noti_reminder_date" id="lega_noti_reminder_date"
										name="lega_noti_reminder_date" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">External Counsel :</label>
							<div class="col-sm-7">
								<sf:select class="form-control" items="${allExternalCounsel}"
									path="lega_noti_external_counsel_id"
									id="lega_noti_external_counsel_id">
									<option value="0" selected="selected">--Select--</option>

								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Internal
								contact person :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"
									path="lega_noti_intern_cont_person"
									id="lega_noti_intern_cont_person"
									name="lega_noti_intern_cont_person" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Addressed
								To :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"
									path="lega_noti_addressed_to" id="lega_noti_addressed_to"
									name="lega_noti_addressed_to" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Opposite
								Party Advocate :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"
									path="lega_noti_opposite_party_advocate"
									id="lega_noti_opposite_party_advocate"
									name="lega_noti_opposite_party_advocate" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="sel1">Relevant
								Law :</label>
							<div class="col-sm-7">
								<sf:input type="text" class="form-control"
									path="lega_noti_relevant_law" id="lega_noti_relevant_law"
									name="lega_noti_relevant_law" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Brief
								Facts/Comments :</label>
							<div class="col-sm-7">
								<sf:textarea rows="2" cols="42" path="lega_noti_comments"
									id="lega_noti_comments" name="lega_noti_comments"
									placeholder="Enter Comments"></sf:textarea>
							</div>
						</div>
						<div class="col-md-12">
							<div id="fileContiner_Legal"></div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-4 control-label"></label>
									<div class="col-sm-6 litigation_buttons">
										<button type="button" class="btn btnTaskCompDoc"
											onclick="addLegalFileInput();">Legal Notice Document</button>
									</div>

								</div>
							</div>
						</div>
						<!-- <div class="form-group row">
							<label class="col-sm-5 control-label">Document:</label>
							<div class="col-sm-7">
								<input id="input-folder-1" type="file" class="file-loading" id="scau_document" name="scau_document">
							</div>
						</div> -->
					</div>

					<div class="col-md-12">
						<div class="f_form_content">
							<h2>Amount Details</h2>
							<div class="col-md-12" style="margin-top: 10px;">
								<%-- <div class="col-md-8">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Amount
											Involved: </label>
										<div class="col-sm-3">
											<sf:input path="liti_amount_involved" cssClass="form-control"
												placeholder="Enter Amount" />
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div> --%>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Amount
											Involved: </label>
										<div class="col-sm-6">
											<sf:input path="lega_noti_amount_involved"
												cssClass="form-control" placeholder="Enter Amount" />
											<!-- <i class="asterisk_input"></i> -->

										</div>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Interest:
										</label>
										<div class="col-sm-6">
											<sf:input path="lega_noti_interest" cssClass="form-control"
												placeholder="Enter Interest" />
											<!-- <i class="asterisk_input"></i> -->

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Total
											Amount: </label>
										<div class="col-sm-6">
											<sf:input path="lega_noti_total_amount"
												cssClass="form-control" placeholder="Enter Total Amount"
												readonly="true" />
											<!-- <i class="asterisk_input"></i> -->

										</div>
									</div>
								</div>


								<%-- 	<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Currency: </label>
										<div class="col-sm-6">
											<sf:select path="lega_noti_involved_amt_currency" class="validate[required]">
												<option value="NA" selected="selected">--Select--</option>
												<c:forEach items="${allCurrency }" var="curr">
													<sf:option value="${curr.curr_code }">${curr.curr_code }</sf:option>
												</c:forEach>
											</sf:select><!-- <i class="asterisk_input"></i> -->
											
										</div>
									</div>
								</div>
								<div class="col-md-4">
								<div class="form-group">
									<label  class="col-sm-4 control-label">Date :</label>
									<div class="col-sm-6">
										<div id="noti_amount_div" class="input-group date"
										data-date-format="MM">
									<input class="form-control" id="lega_noti_amount_date" name="lega_noti_amount_date" readonly="true"/>
									<span class="input-group-addon">
									<i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								</div>
								</div> --%>

								<div class="col-md-12">
									<div class="col-md-8">
										<div class="form-group">
											<label class="col-sm-2 control-label">Prayer Details
												:</label>
											<div class="col-sm-6">
												<sf:textarea rows="2" cols="42"
													path="lega_noti_prayer_details"
													id="lega_noti_prayer_details"
													name="lega_noti_prayer_details"
													placeholder="Enter prayer details"></sf:textarea>
											</div>
										</div>
									</div>
								</div>
							</div>

							<%-- <div class="col-md-12" style="margin-top: 10px;">
							<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Converted Currency: </label>
										<div class="col-sm-6">
											<sf:select path="lega_noti_converted_amt_currency" class="form-control">
												<option value="NA" selected="selected">--Select--</option>
												<c:forEach items="${allCurrency }" var="curr">
													<sf:option value="${curr.curr_code }">${curr.curr_code }</sf:option>
												</c:forEach>
											</sf:select>
											<!-- <i class="asterisk_input"></i> -->

										</div>
									</div>
								</div>
							
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Conversion Rate: </label>
										<div class="col-sm-6">
											<sf:input path="lega_noti_conversion_rate" cssClass="form-control" size="10" maxlength="10" 
												placeholder="Enter Amount" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Converted Amount: </label>
										<div class="col-sm-6">
											<sf:input path="lega_noti_converted_amt" cssClass="form-control" readonly="true"
												placeholder="Enter Amount" />
										</div>
									</div>
								</div>
							</div>
							 --%>
						</div>
					</div>

				</div>

				<div class="col-md-12 litigation_buttons">
					<br>

					<center>
						<button type="submit" value="Update" name="Update"
							class="btn btn-myPrimary">Update</button>
						<c:if test="${NoticeStatus=='Draft'}">
							<button type="submit" name="Draft" value="Draft"
								class="btn btn-myDefault">Update As Draft</button>
						</c:if>
					</center>

				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>
</div>

<script>
	$(document)
			.ready(
					function() {
						$(document).on("click", ":submit", function(e) {
							buttonClicked = $(this).val();
							if (buttonClicked == "Update") {
								if (validateForm() != false) {
									$("form").submit();
								} else {
									e.preventDefault();
								}
							}
							if (buttonClicked == "Draft") {
								if (validateDraft() != false) {
									$("form").submit();
								} else {
									e.preventDefault();
								}
							}
							//e.preventDefault();
						});

						$("#lega_noti_category_id")
								.multiselect(
										{
											buttonWidth : '300px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#lega_noti_category_id option')
														.each(
																function() {
																	if ($(this)
																			.val() !== option
																			.val()) {
																		values
																				.push($(
																						this)
																						.val());
																	}
																});

												$('#lega_noti_category_id')
														.multiselect(
																'deselect',
																values);
											}

										});

						$("#lega_noti_external_counsel_id")
								.multiselect(
										{
											buttonWidth : '300px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#lega_noti_external_counsel_id option')
														.each(
																function() {
																	if ($(this)
																			.val() !== option
																			.val()) {
																		values
																				.push($(
																						this)
																						.val());
																	}
																});

												$(
														'#lega_noti_external_counsel_id')
														.multiselect(
																'deselect',
																values);
											}

										});

						$("#lega_noti_involved_amt_currency")
								.multiselect(
										{
											buttonWidth : '137px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#lega_noti_involved_amt_currency option')
														.each(
																function() {
																	if ($(this)
																			.val() !== option
																			.val()) {
																		values
																				.push($(
																						this)
																						.val());
																	}
																});

												$(
														'#lega_noti_involved_amt_currency')
														.multiselect(
																'deselect',
																values);
											}

										});

						$("#lega_noti_converted_amt_currency")
								.multiselect(
										{
											buttonWidth : '137px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#lega_noti_converted_amt_currency option')
														.each(
																function() {
																	if ($(this)
																			.val() !== option
																			.val()) {
																		values
																				.push($(
																						this)
																						.val());
																	}
																});

												$(
														'#lega_noti_converted_amt_currency')
														.multiselect(
																'deselect',
																values);
											}

										});

					});
</script>
<script src="appJs/LegalNotice/LegalNotice.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>