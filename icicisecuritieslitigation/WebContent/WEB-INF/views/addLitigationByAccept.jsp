<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<div class="container">
			<!-- Header -->
			<div class="row">
				<div class="col-md-12">
					<div class="header_button">
						<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
						<h2 style="color: #a72f14; font-size: 24px; float: left;">Add
							Litigation</h2>
						<a href="./listLitigation"><img
							src="images/DashboardIcons/backold.png" class="backButton"
							width="100px;"></a>
					</div>
				</div>
			</div>
			<!-- End Header -->
			<sf:form class="form-horizontal" commandName="litiRequest"  action="./saveLititgation" method="POST">
				<!-- First Panel -->
				       <div class="col-md-4" style="display:none">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">
									: </label>
								<div class="col-sm-8">
									<sf:input path="liti_id" />
								</div>
							</div>
						</div>
						<div class="col-md-4" style="display:none">
							<div class="form-group" >
								<label class="control-label col-sm-4" for="sel1">request id: </label>
								<div class="col-sm-8">
								<input name="liti_req_id" id="liti_req_id" value="${id}"/>
								</div>
							</div>
						</div>
						<div class="col-md-4" style="display:none">
							<div class="form-group" >
								<label class="control-label col-sm-4" for="sel1">request type: </label>
								<div class="col-sm-8">
								<input name="liti_req_type" id="liti_req_type" value="${type}"/>
								</div>
							</div>
						</div>

				<div class="f_form_content">
					<h2>Organization Details</h2>
					<div class="col-md-12">
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Entity/Company Name: </label>
								<div class="col-sm-8">
								  <sf:select path="liti_orga_id" items="${allOrganizations}"
										cssClass="form-control">
									</sf:select> <i class="asterisk_input"></i> 
									
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Unit/Vertical:	</label>
								<div class="col-sm-8">
								  <sf:select class="form-control" items="${allLocations}" name="liti_loca_id" path="liti_loca_id" id="liti_loca_id">
								   <option value="0">--Select--</option>
							     </sf:select><i class="asterisk_input"></i>
							     
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Function/Location /Department:</label>
								<div class="col-sm-8">
								<sf:select class="form-control" items="${allFunctions}" name="liti_dept_id" path="liti_dept_id" id="liti_dept_id">
								   <option value="0">--Select--</option>
							     </sf:select><i class="asterisk_input"></i>
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
									<sf:select  path="liti_type_id" cssClass="form-control">
										<sf:option value="0" selected="selected">--Select--</sf:option>
									 <c:forEach items="${litigation_type_list}"
											var="litigation_type">
											<option value="${litigation_type.liti_type_id}">${litigation_type.liti_type_name}</option>
										</c:forEach>
									</sf:select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Internal Litigation Code: </label>
								<div class="col-sm-8">
									<sf:select path="liti_internal_liti_code" cssClass="form-control">
										<option value="0" selected="selected">--Select--</option>
										 <c:forEach items="${liti_code}" var="liti_code">
											<option value="${liti_code.internal_liti_id}">${liti_code.internal_liti_code}</option>
										</c:forEach> 
									</sf:select><!-- <i class="asterisk_input"></i> -->
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Litigation Reference number: </label>
								<div class="col-sm-8">
									<sf:select path="liti_previous_liti_ref_no" cssClass="form-control">
										
									</sf:select><!-- <i class="asterisk_input"></i> -->
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
										<option value="0">--Select--</option>
										<option value="By">By</option>
										<!-- By -->
										<option value="Against">Against</option>
										<!-- Against -->
									</sf:select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">
									By Party(s) : </label>
								<div class="col-sm-8">
									<sf:input path="liti_party_by" name="liti_party_by" class="form-control" value="${party_by }"
										placeholder="By Party" /><i class="asterisk_input"></i>
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
									<sf:input path="liti_party_against" name="liti_party_against" class="form-control" value="${party_against }"
										placeholder="Against Party" /><i class="asterisk_input"></i>
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
									<sf:select path="liti_company_acting_as"
										cssClass="form-control">
										<option value="0">--Select--</option>
									</sf:select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>
					
					<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						 <div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Counsel Law Firm :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="liti_counsel_law_firm" id="liti_counsel_law_firm">
								   <!-- <option value="0">--Select--</option> -->
								   <c:forEach items="${allLawFirm}" var="lawf">
								   <option value="${lawf.key}">${lawf.value}</option>
								   </c:forEach>
							   </sf:select ><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div> --%>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">External Counsel: </label>
								<div class="col-sm-8">
									<sf:select path="liti_external_counsel_id"
										cssClass="form-control">
										<option value="0" selected="selected">--Select--</option>
										 <c:forEach items="${external_counsel_list}"
											var="external_counsel">
											<option value="${external_counsel.exte_coun_id}">${external_counsel.exte_coun_name}</option>
										</c:forEach> 
									</sf:select><!-- <i class="asterisk_input"></i> -->
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
						 <div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Advocate Law Firm :</label>
							<div class="col-sm-8">
								<sf:select class="form-control" path="liti_advocate_law_firm" id="liti_advocate_law_firm">
								   <c:forEach items="${allLawFirm}" var="advocate">
											<option value="${advocate.key}">${advocate.value}</option>
										</c:forEach>
							   	</sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Advocate: </label>
								<div class="col-sm-8">
									<sf:select path="liti_advocate_id"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										
									</sf:select><i class="asterisk_input"></i>
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
										<option value="0">--Select--</option>
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
								<label class="control-label col-sm-4" for="sel1">Assigned
									To : </label>
								<div class="col-sm-8">
									<sf:select path="liti_assigned_to"
										cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<c:forEach items="${user_legal_department}"
											var="user_legal_department">
											<sf:option value="${user_legal_department.user_id}">${user_legal_department.user_first_name}
												${user_legal_department.user_last_name}</sf:option>
										</c:forEach>
									</sf:select>
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
										<sf:input path="liti_case_filing_date" cssClass="form-control" disabled="true" value="${notice_date}" readonly="true"/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>

									</div><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Internal Contact Person: </label>
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
								<label class="control-label col-sm-4" for="sel1">Criticality : </label>
								<div class="col-sm-8">
									<sf:select path="liti_criticality"
										cssClass="form-control">
										<option value="0">--Select--</option>
										<option value="High">High</option>
										<option value="Medium">Medium</option>
										<option value="Low">Low</option>
									</sf:select><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12" id="multi_email_id" style="margin-top: 10px; display:none">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Email Id: </label>
								<div class="col-sm-8">
									<sf:textarea path="liti_mail_id_cc" rows="2" cols="57" cssClass="form-control"
										placeholder="Enter Email id" ></sf:textarea>
								</div><p style="text-align: right;">(Please enter Email id separated by comma.)</p>
							</div>
						</div>
					</div>
				</div>
				<!-- End Second panel -->
				<div class="f_form_content">
					<h2>Escalation Mail Id Details</h2>
					
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Internal Resources: </label>
								<div class="col-sm-8">
									<input id="esc_internal_resource" name="esc_internal_resource" class="form-control" 
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
										placeholder="Enter Email id" onkeypress="return blockSpace(event);"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Appearing counsel:</label>
								<div class="col-sm-8">
									<input id="esc_appear_counsel" name="esc_appear_counsel" 
										class="form-control" placeholder="Enter Email id" onkeypress="return blockSpace(event);"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Others:</label>
								<div class="col-sm-8">
									<input id="esc_others" name="esc_others"
										class="form-control" placeholder="Enter Email id" onkeypress="return blockSpace(event);"/>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
					<div class="form-group">
					<p style="text-align: left;">(Note: Please enter atmost six email id separated by comma for above three fields except law firm.)</p>
					</div>
					</div>
				</div>

				<!-- Third Panel -->
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
									</sf:select><i class="asterisk_input"></i>
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
										placeholder="Opposite Party" value="${oppo_party}" readonly="true"/>
								</div>
							</div>
						</div>
					</div> --%>
					<div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Opposite party address : </label>
								<div class="col-sm-8">
									<sf:textarea path="liti_oppo_party_address" placeholder="Opposite party address" cssClass="form-control" rows="2" cols="57" />
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
								<label class="control-label col-sm-4" for="sel1">Advocate name:</label>
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
								<label class="control-label col-sm-4" for="sel1">Advocate contact number:</label>
								<div class="col-sm-8">
									<sf:input path="liti_oppo_advo_contact"
										cssClass="form-control" placeholder="Contact number" />

								</div>
							</div>
						</div>
					</div>

				</div>
				<!-- End Third panel -->
				<!-- Fourth Panel -->
				<div class="f_form_content">
					<h2>Litigation Details</h2>
					<%-- <div class="col-md-12" style="margin-top: 10px;">
						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Forum :
								</label>
								<div class="col-sm-8">
									<sf:select path="liti_forum" cssClass="form-control">
										<sf:option value="0">--Select--</sf:option>
										<sf:option value="District_Forum">District Forum</sf:option>
									</sf:select><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div> --%>
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
								<label class="control-label col-sm-4" for="sel1">Jurisdiction name:</label>
								<div class="col-sm-8">
									<sf:input path="liti_jurisdiction_name"
										cssClass="form-control" placeholder="Jurisdiction Name" /><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12" style="margin-top: 10px;">

						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Court/Tribunal :
								</label>
								<div class="col-sm-8">
									<sf:select path="liti_court" class="form-control">
										<option value="0" selected="selected">--Select--</option>
										
										<c:forEach items="${AllCourt }" var="court">
										<sf:option value="${court.court_id}">${court.court_name}</sf:option>
										</c:forEach>
										<%-- <sf:option value="Supreme_Court">Supreme Court</sf:option>
										<sf:option value="High_Court">High Court</sf:option>
										<sf:option value="Session_Court">Session Court</sf:option> --%>

									</sf:select><i class="asterisk_input"></i>
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
									<sf:textarea path="liti_relevant_law" rows="2" cols="57" cssClass="form-control"
										placeholder="Enter Law" ></sf:textarea><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div>

					<%-- <div class="col-md-12" style="margin-top: 10px;">

						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Amount
									Involved (INR): </label>
								<div class="col-sm-8">
									<sf:input path="liti_amount_involved" cssClass="form-control"
										placeholder="Enter Amount" /><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
					</div> --%>

					<div class="col-md-12" style="margin-top: 10px;">

						<div class="col-md-8">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Brief
									Description about the Case : </label>
								<div class="col-sm-8">
									<sf:textarea path="liti_brief_description" placeholder="Brief Description" cssClass="form-control" rows="2" cols="57" readonly="true"></sf:textarea>
								</div>
							</div>
						</div>
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
											<sf:input path="liti_amount_involved" cssClass="form-control" 
												placeholder="Enter Amount" />
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Currency: </label>
										<div class="col-sm-6">
											<sf:select path="liti_involved_amt_currency" class="form-control">
												<option value="NA" selected="selected">--Select--</option>
												<c:forEach items="${allCurrency }" var="curr">
													<option value="${curr.curr_code }">${curr.curr_code }</option>
												</c:forEach>
											</sf:select><i class="asterisk_input"></i>
											
										</div>
									</div>
								</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4">Date :</label>
								<div class="col-sm-6">
									<div class="input-group date" id="liti_amount_div"
										data-date-format="MM">
										<input class="form-control" id="liti_conversion_date" readonly/><span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>

									</div><i class="asterisk_input"></i>
								</div>
							</div>
						</div>
							</div>
							<div class="col-md-12" style="margin-top: 10px;">
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1"> Converted Currency: </label>
										<div class="col-sm-6">
											<sf:select path="liti_converted_amt_currency" class="form-control">
												<option value="NA" selected="selected">--Select--</option>
												<c:forEach items="${allCurrency }" var="curr">
													<option value="${curr.curr_code }">${curr.curr_code }</option>
												</c:forEach>
											</sf:select>
											<i class="asterisk_input"></i>

										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Conversion Rate: </label>
										<div class="col-sm-6">
											<sf:input path="liti_conversion_rate" cssClass="form-control" size="10" maxlength="10" 
												placeholder="Enter Amount" />
										</div>
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label class="control-label col-sm-4" for="sel1">Converted Amount: </label>
										<div class="col-sm-6">
											<sf:input path="liti_converted_amt" cssClass="form-control" readonly="true"
												placeholder="Enter Amount" />
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
					<div class="col-md-12" style="margin-top: 10px;">
						<center>
							<button type="submit" value="Save" name="Save" class="btn btn-success">Create</button>
						    	<!--  <button type="submit" name="Draft" value="Draft" class="btn btn-primary">Save As Draft</button> -->
						</center>
					</div>
				</div>
				<!-- Fourth panel End-->
			</sf:form>
		</div>
	</div>
</div>
<script>

$(document).ready(function(){
	//alert("type and id is:"+'${type} ${id}');
	if('${type}' == 'noti'){
		$("#liti_orga_id").prop("disabled",false);
		$("#liti_loca_id").prop("disabled",false);
		$("#liti_dept_id").prop("disabled",false);
		$("#liti_case_filing_date").prop("disabled",false);
		$("#liti_brief_description").prop("readonly",false);

	}else{
		document.getElementById("liti_brief_description").innerHTML = '${description}';
	}
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
			
				data += "<option value="+value['client_id']+">"+value['client_id']+"</option>";
			});
			$("#liti_previous_liti_ref_no").html(data);

		},
		error: function(){
			alert("error in ajax form submission");
		}
	});
	
	/*$("#lega_noti_involved_amt_currency").change(function(){
		var base = $(this).val();
		var currency_date = $("#currency_date").val();
		alert(currency_date);
	}); */
 $(document).on("click", ":submit", function(e){
	 
	 
    buttonClicked = $(this).val();
    if(buttonClicked=="Save"){
    	if(validateForm()!=false){
    		$("#liti_orga_id").prop("disabled",false);
    		$("#liti_loca_id").prop("disabled",false);
    		$("#liti_dept_id").prop("disabled",false);
    		$("#liti_case_filing_date").prop("disabled",false);
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
	
 $("#liti_type_id").multiselect({
		buttonWidth: '435px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
             var values = [];
             $('#liti_type_id option').each(function() {
                 if ($(this).val() !== option.val()) {
                     values.push($(this).val());
                 }
             });

             $('#liti_type_id').multiselect('deselect', values);
         }
	});
 
 
	$("#liti_external_counsel_id").multiselect({
		buttonWidth: '435px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
          var values = [];
          $('#liti_external_counsel_id option').each(function() {
              if ($(this).val() !== option.val()) {
                  values.push($(this).val());
              }
          });

          $('#liti_external_counsel_id').multiselect('deselect', values);
      }
	});
 
	$("#liti_advocate_law_firm").multiselect({
		buttonWidth: '435px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
          var values = [];
          $('#liti_advocate_law_firm option').each(function() {
              if ($(this).val() !== option.val()) {
                  values.push($(this).val());
              }
          });

          $('#liti_advocate_law_firm').multiselect('deselect', values);
      }
	});
	
 	 $("#liti_court").multiselect({
 		buttonWidth: '435px',
 	    enableFiltering: true,
 	    filterBehavior: 'text',
 	    enableCaseInsensitiveFiltering: true,
 	    maxHeight : 200,
 	    onChange: function(option, checked) {
        var values = [];
        $('#liti_court option').each(function() {
            if ($(this).val() !== option.val()) {
                values.push($(this).val());
            }
        });

        $('#liti_court').multiselect('deselect', values);
    }
 	});
 
	$("#liti_involved_amt_currency").multiselect({
		buttonWidth: '130px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
             var values = [];
             $('#liti_involved_amt_currency option').each(function() {
                 if ($(this).val() !== option.val()) {
                     values.push($(this).val());
                 }
             });

             $('#liti_involved_amt_currency').multiselect('deselect', values);
         }
     
	});

	$("#liti_converted_amt_currency").multiselect({
		buttonWidth: '130px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
             var values = [];
             $('#liti_converted_amt_currency option').each(function() {
                 if ($(this).val() !== option.val()) {
                     values.push($(this).val());
                 }
             });

             $('#liti_converted_amt_currency').multiselect('deselect', values);
         }
	});
});


</script>
<script src="appJs/Litigation/litigation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

