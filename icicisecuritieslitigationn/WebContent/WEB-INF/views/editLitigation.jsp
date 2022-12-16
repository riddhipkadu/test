<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_container">
	<div class="container">
		<!-- Header -->
		<div class="row">
			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Edit Litigations</h2>
					<a href="./listLitigation"><img
						src="images/DashboardIcons/backold.png" class="backButton"
						width="100px;"></a>
				</div>
			</div>
		</div>
		<!-- End Header -->
		<sf:form class="form-horizontal" commandName="editLitigation"
			action="./updateLitigation" method="post">
			<!-- First Panel -->

			<div class="f_form_content">
				<h2>Organization Details</h2>
				<div class="col-md-12">
					<div class="col-md-4" style="display: none">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> : </label>
							<div class="col-sm-8">
								<sf:input path="liti_id" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity/Company
								Name: </label>
							<div class="col-sm-8">
								<sf:select path="liti_orga_id" cssClass="form-control">
									<sf:option value="2">ICICI Securities</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Unit/Location:
							</label>
							<div class="col-sm-8">
								<sf:select path="liti_loca_id" cssClass="form-control">
									<sf:option value="1">Mumbai</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Function/
								Department:</label>
							<div class="col-sm-8">
								<sf:select path="liti_dept_id" cssClass="form-control">
									<sf:option value="1">Legal</sf:option>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- End First panel -->

			<!-- Second Panel -->
			<div class="f_form_content">
				<h2>Internal Details</h2>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Category:
							</label>
							<div class="col-sm-8">
								<sf:select path="liti_type_id" cssClass="form-control">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${litigation_type_list}"
										var="litigation_type">
										<sf:option value="${litigation_type.liti_type_id}">${litigation_type.liti_type_name}</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Match
								Id/ Employee Code: </label>
							<div class="col-sm-8">
								<sf:select path="liti_internal_liti_code"
									cssClass="form-control">
									<option value="0" selected="selected">--Select--</option>
									<c:forEach items="${liti_code}" var="liti_code">
										<sf:option value="${liti_code.internal_liti_id}">${liti_code.internal_liti_code}</sf:option>
									</c:forEach>
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Litigation
								Reference number: </label>
							<div class="col-sm-8">
								<sf:select path="liti_previous_liti_ref_no"
									cssClass="form-control">
									<sf:option value="0">--Select--</sf:option>
									<%-- <c:forEach items="${previous_liti_no}" var="previous_liti_no">
									<sf:option value="${previous_liti_no}">${previous_liti_no}</sf:option>
									</c:forEach> --%>
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Company
								(Against/By):</label>
							<div class="col-sm-8">
								<sf:select path="liti_against_by_id" cssClass="form-control">
									<sf:option value="0">--Select--</sf:option>
									<sf:option value="By">By</sf:option>
									<!-- By -->
									<sf:option value="Against">Against</sf:option>
									<!-- Against -->
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> By
								Party(s) : </label>
							<div class="col-sm-8">
								<sf:input path="liti_party_by" name="liti_party_by"
									class="form-control" placeholder="By Party" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Against
								Party(s): </label>
							<div class="col-sm-8">
								<sf:input path="liti_party_against" name="liti_party_against"
									class="form-control" placeholder="Against Party" />
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8" id="by">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Company
								Acting As: </label>
							<div class="col-sm-8">
								<sf:select path="liti_company_acting_as" cssClass="form-control">
									<c:forEach items="${acting_as}" var="acting_as">
										<sf:option value="${acting_as.value}">${acting_as.value}</sf:option>
									</c:forEach>
								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>
				<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						 <div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Counsel Law Firm :</label>
							<div class="col-sm-8">
									<sf:select class="form-control" path="liti_counsel_law_firm">
										<!-- <option value="0">--Select--</option> -->
										<c:forEach items="${allLawFirm}" var="lawf">
											<sf:option value="${lawf.key}">${lawf.value}</sf:option>
										</c:forEach>
									</sf:select><i class="asterisk_input"></i>
								</div>
						</div>
					</div>
					</div>
 --%>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">External
								Counsel: </label>
							<div class="col-sm-8">
								<sf:select path="liti_external_counsel_id"
									cssClass="form-control">
									<option value="0" selected="selected">--Select--</option>
									<c:forEach items="${external_counsel_list}"
										var="external_counsel">
										<sf:option value="${external_counsel.exte_coun_id}">${external_counsel.exte_coun_name}</sf:option>
									</c:forEach>
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Advocate
								Law Firm :</label>
							<div class="col-sm-8">
								<sf:select class="validate[required]"
									path="liti_advocate_law_firm" id="liti_advocate_law_firm">
									<c:forEach items="${allLawFirm}" var="advocate">
										<sf:option value="${advocate.key}">${advocate.value}</sf:option>
									</c:forEach>
								</sf:select>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Advocate:
							</label>
							<div class="col-sm-8">
								<sf:select path="liti_advocate_id" cssClass="form-control">
									<sf:option value="0">--Select--</sf:option>
									<c:forEach items="${listAdvocate}" var="advo">
										<sf:option value="${advo.advo_id }">${advo.advo_name }</sf:option>
									</c:forEach>

								</sf:select>
							</div>
						</div>
					</div>
				</div>


				<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Matter
									Handled By : </label>
								<div class="col-sm-8">
									<sf:select path="liti_internally_handled_by"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<c:forEach items="${user_legal_department}"
											var="user_legal_department">
											<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
										</c:forEach>
									</sf:select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div> --%>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Primary
								Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_assigned_to" items="${allUsers}"
									cssClass="form-control">
								</sf:select>
								<i class="asterisk_input"></i>

							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Secondary
								Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_secondary_responsible" items="${allUsers}"
									cssClass="form-control">
								</sf:select>
								<!-- 		<i class="asterisk_input"></i> -->

							</div>
						</div>
					</div>
				</div>
				<%-- <div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Secondary
								Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_secondary_responsible"
									cssClass="form-control">
									<option value="0">--Select--</option>
								</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div> --%>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Third
								Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_third_responsible_person"
									items="${allUsers}" cssClass="form-control">
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->

							</div>
						</div>
					</div>
				</div>
				<%--   <div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Third
								Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_third_responsible_person"
									cssClass="form-control">
									<option value="0">--Select--</option>
								</sf:select>
							</div>
						</div>
					</div>
				</div> --%>
				<%-- <div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Fourth Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_fourth_responsible_person"
									cssClass="form-control">
									<option value="0">--Select--</option>
								</sf:select>
							</div>
						</div>
					</div>
				</div> --%>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Fourth
								Responsible Person: </label>
							<div class="col-sm-8">
								<sf:select path="liti_fourth_responsible_person"
									items="${allUsers}" cssClass="form-control">
								</sf:select>
								<!-- 	<i class="asterisk_input"></i> -->

							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4">Case Filling Date :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="datepicker1"
									data-date-format="MM">
									<sf:input path="liti_case_filing_date" cssClass="form-control"
										readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>

								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Internal
								Contact Person: </label>
							<div class="col-sm-8">
								<sf:input path="liti_intenal_person" cssClass="form-control"
									placeholder="Internal person name" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Risk
								Analysis : </label>
							<div class="col-sm-8">
								<sf:select path="liti_criticality" cssClass="form-control">
									<option value="0">--Select--</option>
									<sf:option value="Remote"> Remote</sf:option>
									<sf:option value="Possible">Possible</sf:option>
									<sf:option value="Probable">Probable</sf:option>
								</sf:select>
							<!-- 	<i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Payable or Receivable: </label>
							<div class="col-sm-8">
								<sf:select path="liti_pay" cssClass="form-control">
									<sf:option value="NA">--Select--</sf:option>
									<sf:option value="Payable">Payable</sf:option>
									<sf:option value="Receivable">Receivable</sf:option>
									
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Part of ACM Note: </label>
							<div class="col-sm-8">
								<sf:select path="liti_part_acm" cssClass="form-control">
									<sf:option value="NA">--Select--</sf:option>
									<sf:option value="Yes">Yes</sf:option>
									<sf:option value="No">No</sf:option>
									
								</sf:select>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4">Date For MIS Purpose :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="datepicker12"
									data-date-format="MM">
									<sf:input path="liti_acm_note_date" cssClass="form-control"
										readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="col-md-12" id="multi_email_id"
					style="margin-top: 10px; display: none">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Email
								Id: </label>
							<div class="col-sm-8">
								<sf:textarea path="liti_mail_id_cc" rows="2" cols="57"
									cssClass="form-control" placeholder="Enter Email id"></sf:textarea>
							</div>
							<p style="text-align: right;">(Please enter Email id
								separated by comma.)</p>
						</div>
					</div>
				</div>
			</div>
			<!-- End Second panel -->
			<div class="f_form_content">
				<h2>Escalation Mail Id Details</h2>

				<%-- 
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Internal Resources: </label>
								<div class="col-sm-8">
									<input id="esc_internal_resource" name="esc_internal_resource" class="form-control" value="${internal_resource }"
										placeholder="Enter Email id" onkeypress="return blockSpace(event);"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Law firm: </label>
								<div class="col-sm-8">
									<input id="esc_law_firm" name="esc_law_firm" class="form-control"
									value="${law_firm }" placeholder="Enter Email id" onkeypress="return blockSpace(event);"/>
								</div>
							</div>
						</div>
					</div>
					
					 --%>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Appearing
								counsel:</label>
							<div class="col-sm-8">
								<input id="esc_appear_counsel" name="esc_appear_counsel"
									value="${counsel_appear }" class="form-control"
									placeholder="Enter Email id"
									onkeypress="return blockSpace(event);" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Others:</label>
							<div class="col-sm-8">
								<input id="esc_others" name="esc_others" value="${others }"
									class="form-control" placeholder="Enter Email id"
									onkeypress="return blockSpace(event);" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="form-group">
						<p style="text-align: center;">(Note: Please enter atmost six
							email id separated by comma for each of above four fields.)</p>
					</div>
				</div>
			</div>

			<!-- Third Panel -->
			<%-- <div class="f_form_content">
					<h2>Opposite Party Details</h2>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8" id="OppositePartyAgainst">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Opposite
									Party Acting As:</label>
								<div class="col-sm-8">
									<sf:select path="liti_opp_party_acting_as"
										cssClass="form-control">
										<option value="0">--Select--</option>
									</sf:select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Opposite
									Party(s): </label>
								<div class="col-sm-8">
									<sf:input path="liti_opposite_party" cssClass="form-control"
										placeholder="Opposite Party" /><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Opposite
									Party(Law Firm/Advocate):</label>
								<div class="col-sm-8">
									<sf:input path="liti_opposite_party_advocate"
										cssClass="form-control" placeholder="Firm/Adv." /><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div>

				</div> --%>
			<div class="f_form_content">
				<h2>Opposite Party Details</h2>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8" id="OppositePartyAgainst">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Opposite
								Party Acting As:</label>
							<div class="col-sm-8">
								<sf:select path="liti_opp_party_acting_as"
									cssClass="form-control">
									<option value="0">--Select--</option>
								</sf:select>
							</div>
						</div>
					</div>
				</div>

				<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Opposite
									Party(s): </label>
								<div class="col-sm-8">
									<sf:input path="liti_opposite_party" cssClass="form-control"
										placeholder="Opposite Party"/>
								</div>
							</div>
						</div>
					</div> --%>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Opposite
								party address : </label>
							<div class="col-sm-8">
								<sf:textarea path="liti_oppo_party_address"
									placeholder="Opposite party address" cssClass="form-control"
									rows="2" cols="57" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Opposite
								party advocate law firm: </label>
							<div class="col-sm-8">
								<sf:input path="liti_oppo_advo_law_firm" cssClass="form-control"
									placeholder="Opposite Party Law Firm" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Advocate
								name:</label>
							<div class="col-sm-8">
								<sf:input path="liti_opposite_party_advocate"
									cssClass="form-control" placeholder="Advocate name" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Advocate
								contact number:</label>
							<div class="col-sm-8">
								<sf:input path="liti_oppo_advo_contact" cssClass="form-control"
									placeholder="Contact number" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- End Third panel -->
			<!-- Fourth Panel -->
			<div class="f_form_content">
				<h2>Litigation Details</h2>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Case
								Reference No :</label>
							<div class="col-sm-8">
								<sf:input path="liti_case_reference_no" cssClass="form-control"
									placeholder="CASE REF. No" />
							</div>
						</div>
					</div>

				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Jurisdiction
								name/City:</label>
							<div class="col-sm-8">
								<sf:input path="liti_jurisdiction_name" cssClass="form-control"
									placeholder="Jurisdiction/City" />
								<!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">

					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Court/Tribunal
								: </label>
							<div class="col-sm-8">
								<sf:select path="liti_court" class="form-control">
									<option value="0" selected="selected">--Select--</option>

									<c:forEach items="${AllCourt }" var="court">
										<sf:option value="${court.court_id}">${court.court_name}</sf:option>
									</c:forEach>
									<%-- <sf:option value="Supreme_Court">Supreme Court</sf:option>
										<sf:option value="High_Court">High Court</sf:option>
										<sf:option value="Session_Court">Session Court</sf:option> --%>

								</sf:select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>


				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Relevant
								Law: </label>
							<div class="col-sm-8">
								<sf:textarea path="liti_relevant_law" cssClass="form-control"
									rows="4" cols="60" placeholder="Enter Law"></sf:textarea>
								<!-- 	<i class="asterisk_input"></i> -->

							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Brief
								Description about the Case : </label>
							<div class="col-sm-8">
								<sf:textarea path="liti_brief_description"
									cssClass="form-control" placeholder="Brief Description"
									rows="4" cols="60" />

							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Nature
								of Complaint : </label>
							<div class="col-sm-8">
								<sf:textarea path="liti_nature_of_complaint"
									cssClass="form-control" placeholder="Nature of Complaint"
									rows="4" cols="60" />

							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Status
								of Case:</label>
							<div class="col-sm-8">
								<sf:input path="liti_status_of_case" cssClass="form-control"
									placeholder="Status of Case" />
							</div>
						</div>
					</div>
				</div>


				<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4">Date of Receipt of
								Notice :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="datepicker12"
									data-date-format="MM">
									<sf:input path="liti_receipt_notice_date"
										cssClass="form-control" readonly="true" />
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>

								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div>

				<!-- 		<div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
							<label class="control-label col-sm-4">Current Date :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="liti_amount_div"
									data-date-format="MM">
									<input class="form-control" id="liti_conversion_date"
										readonly="true" /><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
				</div> -->

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
										<sf:input path="liti_amount_involved" cssClass="form-control"
											placeholder="Enter Amount" />
										<i class="asterisk_input"></i>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">Interest
										: </label>
									<div class="col-sm-6">
										<sf:input path="liti_interest" cssClass="form-control"
											placeholder="Enter Interest" />
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">Total
										Amount: </label>
									<div class="col-sm-6">
										<sf:input path="liti_total_amount" cssClass="form-control"
											placeholder="" readonly="true" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-8">
								<div class="form-group">
									<label class="control-label col-sm-2" for="sel1">
										Prayer Details : </label>
									<div class="col-sm-6">
										<sf:textarea path="liti_prayer_details"
											placeholder="Prayer Details" cssClass="form-control" rows="2"
											cols="57" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="margin-top: 10px;"></div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 10px;">
					<center>
						<button type="submit" value="Update" name="Update"
							class="btn btn-success">Update</button>
						<c:if test="${status=='Draft'}">
							<button type="submit" name="Draft" value="Draft"
								class="btn btn-primary">Update As Draft</button>
						</c:if>
					</center>
				</div>
			</div>

			<!-- Fourth panel End-->
		</sf:form>

	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//alert("${previous_liti_no}");
						$
								.ajax({
									url : "./getAllClientId",
									type : 'GET',
									contentType : "application/json",
									dataType : "json",
									cache : false,
									success : function(result) {

										var data = "";
										data += "<option value= 0>--Select--</option>";

										$
												.each(
														result,
														function(key, value) {
															//console.log("In loop");
															data += "<option value="+value['client_id']+">"
																	+ value['client_id']
																	+ "</option>";
														});
										$("#liti_previous_liti_ref_no").html(
												data);
										$("#liti_previous_liti_ref_no").val(
												'${previous_liti_no}');

									},
									error : function() {
										alert("error in ajax form submission");
									}
								});

						var criti = "${criticality}";
						if (criti == 'High') {
							$("#multi_email_id").show();
						} else {
							$("#multi_email_id").hide();
						}

						//setTimeout(function(){ 
						var liti_conversion_rate = $("#liti_conversion_rate")
								.val();
						var liti_amount_involved = $("#liti_amount_involved")
								.val();
						$("#liti_converted_amt").val(
								liti_amount_involved * liti_conversion_rate);
						//}, 1000);

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

						$("#liti_type_id")
								.multiselect(
										{
											buttonWidth : '435px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$('#liti_type_id option')
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

												$('#liti_type_id').multiselect(
														'deselect', values);
											}
										});

						$("#liti_external_counsel_id")
								.multiselect(
										{
											buttonWidth : '435px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#liti_external_counsel_id option')
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

												$('#liti_external_counsel_id')
														.multiselect(
																'deselect',
																values);
											}
										});

						$("#liti_advocate_law_firm")
								.multiselect(
										{
											buttonWidth : '435px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#liti_advocate_law_firm option')
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

												$('#liti_advocate_law_firm')
														.multiselect(
																'deselect',
																values);
											}
										});

						$("#liti_court")
								.multiselect(
										{
											buttonWidth : '435px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$('#liti_court option')
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

												$('#liti_court').multiselect(
														'deselect', values);
											}
										});

						$("#liti_involved_amt_currency")
								.multiselect(
										{
											buttonWidth : '130px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#liti_involved_amt_currency option')
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

												$('#liti_involved_amt_currency')
														.multiselect(
																'deselect',
																values);
											}

										});

						$("#liti_converted_amt_currency")
								.multiselect(
										{
											buttonWidth : '130px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											maxHeight : 200,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#liti_converted_amt_currency option')
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
														'#liti_converted_amt_currency')
														.multiselect(
																'deselect',
																values);
											}

										});

					});

	/* $("#liti_previous_liti_ref_no").click(function(){
	
	 $.ajax({
			url: "./getAllClientId",
			type: 'GET',
			contentType : "application/json",
			dataType : "json",
			cache : false,
			success: function (result) {
				
				var data ="";
				data +="<option value= 0>--Select--</option>";
				
				$.each(result,function(key,value){
					//console.log("In loop");
					data += "<option value="+value['client_id']+">"+value['client_id']+"</option>";
				});
				$("#liti_previous_liti_ref_no").html(data);

			},
			error: function(){
				alert("error in ajax form submission");
			}
		}); 
	});  */
</script>
<script src="appJs/Litigation/litigation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

