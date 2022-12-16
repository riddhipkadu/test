<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<div class="container">
			<!-- Header -->
			<div class="row">
				<div class="col-md-12">
					<div class="header_button">
						<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
						<h2 style="color: #032BEC; font-size: 24px; float: left;">
							Litigations Details</h2>
						<a href="./listLitigation"><img
							src="images/DashboardIcons/backold.png" class="backButton"
							width="100px;"></a>
					</div>
				</div>
			</div>
			<!-- End Header -->

			<!-- Body -->
			<div class="task_tabs">
				<ul id="myTab" class="nav nav-tabs mynav-tabs">
					<li class="active"><a href="#LitigationDetails"
						data-toggle="tab">Litigation Details</a></li>
					<li><a href="#hearingdates" data-toggle="tab" id="hearingStage"> Next
							Hearing & Stage </a></li>
					<li><a href="#Document" data-toggle="tab">Documents</a></li>
					<li><a href="#CounselFees" data-toggle="tab">Fees</a></li>
					<li><a href="#Completion" data-toggle="tab">Completion</a></li>
					
				</ul>

				<div id="myTabContent" class=" container tab-content">
					<div class="tab-pane fade active in" id="LitigationDetails"
						style="padding: 10px;">
						<table id="example" class="table table-striped table-bordered"
							style="width: 1000px;" cellspacing="0">
							<tr>
								<td colspan="2"
									style="text-align: center; background-color: grey; color: white; font-size: 23px;"><strong>Case
										Details</strong>
									<!-- <div style="float: right;">
										<input type="button" name="export" class="btn btn-danger"
											value="Export In To Word">
									</div> -->
									</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Litigation
										Id</strong></td>
								<td>${litigation_details_by_id.liti_client_liti_id }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>By/Against
										Company</strong></td>
								<td>${litigation_details_by_id.liti_against_by_id } Company</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Litigation Reference number</strong></td>
								<td>
								<c:choose>
								<c:when test="${litigation_details_by_id.liti_previous_liti_ref_no != '0'}">
								<a href="litigationDetails?liti_id=${previous_liti_client_id }" style="color: blue;">
								${litigation_details_by_id.liti_previous_liti_ref_no }</a> 
								</c:when>
								<c:otherwise>
								  NA
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Internal Litigation Code</strong></td>
								<td>${litigation_details_by_id.liti_internal_liti_code_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Category</strong></td>
								<td>${litigation_details_by_id.liti_type_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Case No.</strong></td>
								<td>${litigation_details_by_id.liti_case_reference_no }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Jurisdiction name </strong></td>
								<td>${litigation_details_by_id.liti_jurisdiction_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Case Filling
										Date</strong></td>
								<td>${litigation_details_by_id.liti_case_filing_date }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Company External
										Counsel</strong></td>
								<td>${litigation_details_by_id.exte_coun_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Matter Handled
										By</strong></td>
								<td>${litigation_details_by_id.liti_internally_handled_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Assigned to
										</strong></td>
								<td>${litigation_details_by_id.liti_assigned_name }</td>
							</tr> 
							<tr>
								<td style="width: 200px;"><strong>Internal Contact Person</strong></td>
								<td>${litigation_details_by_id.liti_intenal_person }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Criticality</strong></td>
								<td>${litigation_details_by_id.liti_criticality }</td>
							</tr>
							<%-- <tr>
								<td style="width: 200px;"><strong>Opposite Party</strong></td>
								<td>${litigation_details_by_id.liti_opposite_party }</td>
							</tr> --%>
							<tr>
								<td style="width: 200px;"><strong>By Party</strong></td>
								<td>${litigation_details_by_id.liti_party_by }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Against Party</strong></td>
								<td>${litigation_details_by_id.liti_party_against }</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Opposite Party address</strong></td>
								<td>${litigation_details_by_id.liti_oppo_party_address }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Opposite Party advocate </strong></td>
								<td>${litigation_details_by_id.liti_opposite_party_advocate }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Opposite Party advocate law firm</strong></td>
								<td>${litigation_details_by_id.liti_oppo_advo_law_firm }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Opposite Party advocate contact number</strong></td>
								<td>${litigation_details_by_id.liti_oppo_advo_contact }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Court/Tribunal</strong></td>
								<td>${litigation_details_by_id.liti_court }</td>
							</tr>
							<%-- <tr>
								<td style="width: 200px;"><strong>Forum</strong></td>
								<td>${litigation_details_by_id.liti_forum }</td>
							</tr> --%>
							<tr>
								<td style="width: 200px;"><strong>Amount Involved</strong></td>
								<td>${litigation_details_by_id.liti_amount_involved } ${litigation_details_by_id.liti_involved_amt_currency } </td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Relevant Law</strong></td>
								<td>${litigation_details_by_id.liti_relevant_law }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Brief
										Description</strong></td>
								<td>${litigation_details_by_id.liti_brief_description }</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane fade" id="hearingdates" style="padding: 10px;">
						<table id="example">
							<tr>
								<td></td>
								<td style="text-align: right;">
								<c:if test="${litigation_details_by_id.liti_status!='Completed' && litigation_details_by_id.liti_status!='Draft'}">
								   <a href="addHearingStage?liti_id=${litigation_details_by_id.liti_id }"><input class="btn btn-primary" type="button"
									name="next Hearing Date" id="getHearingStage"
									value="Next Hearing & Stage"></a> 
								</c:if>
									</td>
							</tr>
							<tr>
								<td style="text-align: left; font-size: 18px;"><strong>Hearing Summary</strong></td>
								<td></td>
							</tr>
							<tr>
								<td colspan="2">
									<table id="example" class="table table-striped table-bordered"
										style="width: 1000px;" cellspacing="0">
										<tr style="background-color: grey; color: white;">
											<th>Sr No.</th>
											<!-- <th>Stage</th> -->
											<th>date</th>
											<th>Responsible Person</th>
											<th>Counsel Name</th>
											<th>Alert Dates</th>
											<th>Stage Description</th>
											<th>Document</th>
											<th>Action</th>
										</tr>
										<tbody id="searchResult">
										
										</tbody>
									</table>
									<div id="loader_table" style="display: none;">
										<center>
												<img src="./images/Loader/graphLoader.gif" width="150px" height="130px" />
										</center>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane fade" id="Document" style="padding: 10px;">
					
					<ul id="myTab" class="nav nav-tabs mynav-tabs">
							<li class="active"><a href="#synopsisTab" data-toggle="tab">Synopsis</a></li>
							<li><a href="#actsTab" data-toggle="tab">Acts</a></li>
							<li><a href="#statutoryTab" data-toggle="tab">Statutory Provisions</a></li>
							<li><a href="#miscellaneousTab" data-toggle="tab">Miscellaneous</a></li>
							<li><a href="#caseLawTab" data-toggle="tab">Case Law</a></li>
					</ul>
					<div id="myTabContent1" class=" container tab-content">
					<div class="tab-pane fade active in" id="synopsisTab" style="padding: 10px;">
						 <div class="f_form_content">
							<h2>Synopsis Documents</h2>
							<div class="col-md-12">
								<div class="col-md-10">
									<div class="header_button">
										<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->

									</div>
								</div>
								<div class="col-md-2">
									<center>
										<input type="button" class="btn btn-primary addLitigationDocuments"
											value="Add Document" onclick="addFileInputLitigation(1);"/>
									</center>
								</div>
							</div>
							<div>
								<form class="form-horizontal" role="form"
									id="addLitigationDocumentForm" name="addLitigationDocumentForm"
									method="post" enctype="multipart/form-data">
									<input type="text" id="ldoc_liti_id" name="ldoc_liti_id"
										value="${litigation_details_by_id.liti_id }" hidden="">
									<input type="text" value="LitigationRelated"
										id="ldoc_document_type" name="ldoc_document_type" hidden="" />
									<div id="fileAddingForm1"></div>

								</form>
							</div>

							<div class="table_data1">
								<div class="col-md-12">
									<div class="table-responsive">
										<table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">
													<th>Sr No.</th>
													<th>Document Name</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody id="liti_docs_tbody1">
											<c:set var="liti_docs_loop_liti_var" value="0"/>
												<c:choose>
													<c:when test="${not empty litigation_details_by_id.liti_documents }">
													
														<c:forEach items="${litigation_details_by_id.liti_documents}" var="liti_docs_liti_related"
															varStatus="liti_docs_loop_liti_related">
															<c:choose>
																<c:when
																	test='${liti_docs_liti_related.ldoc_document_type == "LitigationRelated"}'>
																	<c:set var="liti_docs_loop_liti_var" value="${liti_docs_loop_liti_var+1}"/>
																	<tr id="litiDocRow_${liti_docs_liti_related.ldoc_id }">


																		<td>${liti_docs_loop_liti_var }</td>
																		<td><a href="./downloadLitigationDocument?ldoc_id=${liti_docs_liti_related.ldoc_id }">${liti_docs_liti_related.ldoc_original_file_name}</a></td>
																		<td class="delete"><button type="button"
																				value="${liti_docs_liti_related.ldoc_id}"
																				name='delete_litigation_document'
																				class="btn btn-danger">
																				<i class='fa fa-trash'></i>&nbsp;Delete
																			</button></td>
																	</tr>
																</c:when>
															</c:choose>

														</c:forEach>
													</c:when>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>

						<%-- <div class="f_form_content">
							<h2>Reference Library</h2>
							<div class="col-md-12">
								<div class="col-md-10">
									<div class="header_button">
										<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->

									</div>
								</div>
								<div class="col-md-2">
									<center>
										<input type="button" class="btn btn-primary addLitigationDocumentReference"
											value="Add Document" ></input>
									</center>
								</div>
							</div>
							<div>
								<form class="form-horizontal" role="form"
									id="addLitigationDocumentForm" name="addLitigationDocumentForm"
									method="post" enctype="multipart/form-data">
									<input type="text" id="ldoc_liti_id" name="ldoc_liti_id"
										value="${litigation_details_by_id.liti_id }" hidden="">
									<input type="text" value="LitigationReference"
										id="ldoc_document_type" name="ldoc_document_type" hidden="" />
									<div id="fileAddingReferenceForm"></div>
								</form>
							</div>
							<div class="table_data1">
								<div class="col-md-12">
									<div class="table-responsive">
										<table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">
													<th>Sr No.</th>
													<th>Document Name</th>
													<th>Action</th>
												</tr>
											</thead>

											<tbody id="liti_docs_tbody_reference">
											<c:set var="liti_docs_loop_refn_var" scope="session" value="0"/>
												<c:choose>
													<c:when test="${not empty litigation_details_by_id.liti_documents }">
													
														<c:forEach items="${litigation_details_by_id.liti_documents}" var="liti_docs"
															varStatus="liti_docs_loop">
															<c:choose>
																<c:when
																	test='${liti_docs.ldoc_document_type == "LitigationReference"}'>
																	<c:set var="liti_docs_loop_refn_var" value="${liti_docs_loop_refn_var+1}"/>
																	
																	<tr id="litiDocRow_${liti_docs.ldoc_id }">

																		<td>${liti_docs_loop_refn_var }</td>
																		<td><a href="./downloadLitigationDocument?ldoc_id=${liti_docs.ldoc_id }">${liti_docs.ldoc_original_file_name}</a></td>
																		<td class="delete"><button type="button"
																				value="${liti_docs.ldoc_id}"
																				name='delete_litigation_document'
																				class="btn btn-danger">
																				<i class='fa fa-trash'></i>&nbsp;Delete
																			</button></td>
																	</tr>
																</c:when>
															</c:choose>
														</c:forEach>
													</c:when>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div> --%>
					</div>
						 <div class="tab-pane fade" id="actsTab" style="padding: 10px;">
						 
						 <div class="f_form_content">
							<h2>Acts Documents</h2>
							<div class="col-md-12">
								<div class="col-md-10">
									<div class="header_button">
										<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->

									</div>
								</div>
								<div class="col-md-2">
									<input type="button" class="btn btn-primary addLitigationDocuments"
										value="Add Document" onclick="addFileInputLitigation(2);"/>
								</div>
							</div>
							<div>
								<form class="form-horizontal" role="form"
									id="addLitigationDocumentForm" name="addLitigationDocumentForm"
									method="post" enctype="multipart/form-data">
									<input type="text" id="ldoc_liti_id" name="ldoc_liti_id"
										value="${litigation_details_by_id.liti_id }" hidden="">
									<input type="text" value="LitigationActs"
										id="ldoc_document_type" name="ldoc_document_type" hidden="" />
									<div id="fileAddingForm2"></div>

								</form>
							</div>

							<div class="table_data1">
								<div class="col-md-12">
									<div class="table-responsive">
										<table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">
													<th>Sr No.</th>
													<th>Document Name</th>

													<th>Action</th>
												</tr>
											</thead>


											<tbody id="liti_docs_tbody2">
											<c:set var="liti_docs_loop_liti_var" value="0"/>
												<c:choose>
													<c:when test="${not empty litigation_details_by_id.liti_documents }">
													
														<c:forEach items="${litigation_details_by_id.liti_documents}" var="liti_docs_liti_related"
															varStatus="liti_docs_loop_liti_related">
															<c:choose>
																<c:when
																	test='${liti_docs_liti_related.ldoc_document_type == "LitigationActs"}'>
																	<c:set var="liti_docs_loop_liti_var" value="${liti_docs_loop_liti_var+1}"/>
																	<tr id="litiDocRow_${liti_docs_liti_related.ldoc_id }">


																		<td>${liti_docs_loop_liti_var }</td>
																		<td><a href="./downloadLitigationDocument?ldoc_id=${liti_docs_liti_related.ldoc_id }">${liti_docs_liti_related.ldoc_original_file_name}</a></td>
																		<td class="delete"><button type="button"
																				value="${liti_docs_liti_related.ldoc_id}"
																				name='delete_litigation_document'
																				class="btn btn-danger">
																				<i class='fa fa-trash'></i>&nbsp;Delete
																			</button></td>
																	</tr>
																</c:when>
															</c:choose>

														</c:forEach>
													</c:when>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>
						 </div>
						 
						 <div class="tab-pane fade" id="statutoryTab" style="padding: 10px;">
						 <div class="col-md-12">
						 <div class="f_form_content">
							<h2>Statutory provision Documents</h2>
							<div class="col-md-12">
								<div class="col-md-10">
									<div class="header_button">
										<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->

									</div>
								</div>
								<div class="col-md-2">
									<center>
										<input type="button" class="btn btn-primary addLitigationDocuments"
											value="Add Document" onclick="addFileInputLitigation(3);"/>
									</center>
								</div>
							</div>
							<div>
								<form class="form-horizontal" role="form"
									id="addLitigationDocumentForm" name="addLitigationDocumentForm"
									method="post" enctype="multipart/form-data">
									<input type="text" id="ldoc_liti_id" name="ldoc_liti_id"
										value="${litigation_details_by_id.liti_id }" hidden="">
									<input type="text" value="LitigationStatuatory"
										id="ldoc_document_type" name="ldoc_document_type" hidden="" />
									<div id="fileAddingForm3"></div>

								</form>
							</div>

							<div class="table_data1">
								<div class="col-md-12">
									<div class="table-responsive">
										<table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">
													<th>Sr No.</th>
													<th>Document Name</th>
													<th>Action</th>
												</tr>
											</thead>


											<tbody id="liti_docs_tbody3">
											<c:set var="liti_docs_loop_liti_var" value="0"/>
												<c:choose>
													<c:when test="${not empty litigation_details_by_id.liti_documents }">
													
														<c:forEach items="${litigation_details_by_id.liti_documents}" var="liti_docs_liti_related"
															varStatus="liti_docs_loop_liti_related">
															<c:choose>
																<c:when
																	test='${liti_docs_liti_related.ldoc_document_type == "LitigationStatuatory"}'>
																	<c:set var="liti_docs_loop_liti_var" value="${liti_docs_loop_liti_var+1}"/>
																	<tr id="litiDocRow_${liti_docs_liti_related.ldoc_id }">


																		<td>${liti_docs_loop_liti_var }</td>
																		<td><a href="./downloadLitigationDocument?ldoc_id=${liti_docs_liti_related.ldoc_id }">${liti_docs_liti_related.ldoc_original_file_name}</a></td>
																		<td class="delete"><button type="button"
																				value="${liti_docs_liti_related.ldoc_id}"
																				name='delete_litigation_document'
																				class="btn btn-danger">
																				<i class='fa fa-trash'></i>&nbsp;Delete
																			</button></td>
																	</tr>
																</c:when>
															</c:choose>

														</c:forEach>
													</c:when>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>

						</div>
						</div>
						 </div>						 
						 <div class="tab-pane fade" id="miscellaneousTab" style="padding: 10px;">
						 <div class="f_form_content">
							<h2>Miscellaneous Documents</h2>
							<div class="col-md-12">
								<div class="col-md-10">
									<div class="header_button">
										<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
									</div>
								</div>
								<div class="col-md-2">
									<center>
										<input type="button" class="btn btn-primary addLitigationDocuments"
											value="Add Document" onclick="addFileInputLitigation(4);"/>
									</center>
								</div>
							</div>
							<div>
								<form class="form-horizontal" role="form"
									id="addLitigationDocumentForm" name="addLitigationDocumentForm"
									method="post" enctype="multipart/form-data">
									<input type="text" id="ldoc_liti_id" name="ldoc_liti_id"
										value="${litigation_details_by_id.liti_id }" hidden="">
									<input type="text" value="LitigationMiscellaneous"
										id="ldoc_document_type" name="ldoc_document_type" hidden="" />
									<div id="fileAddingForm4"></div>

								</form>
							</div>
							<div class="table_data1">
								<div class="col-md-12">
									<div class="table-responsive">
										<table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">
													<th>Sr No.</th>
													<th>Document Name</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody id="liti_docs_tbody4">
											<c:set var="liti_docs_loop_liti_var" value="0"/>
												<c:choose>
													<c:when test="${not empty litigation_details_by_id.liti_documents }">
													
														<c:forEach items="${litigation_details_by_id.liti_documents}" var="liti_docs_liti_related"
															varStatus="liti_docs_loop_liti_related">
															<c:choose>
																<c:when
																	test='${liti_docs_liti_related.ldoc_document_type == "LitigationMiscellaneous"}'>
																	<c:set var="liti_docs_loop_liti_var" value="${liti_docs_loop_liti_var+1}"/>
																	<tr id="litiDocRow_${liti_docs_liti_related.ldoc_id }">

																		<td>${liti_docs_loop_liti_var }</td>
																		<td><a href="./downloadLitigationDocument?ldoc_id=${liti_docs_liti_related.ldoc_id }">${liti_docs_liti_related.ldoc_original_file_name}</a></td>
																		<td class="delete"><button type="button"
																				value="${liti_docs_liti_related.ldoc_id}"
																				name='delete_litigation_document'
																				class="btn btn-danger">
																				<i class='fa fa-trash'></i>&nbsp;Delete
																			</button></td>
																	</tr>
																</c:when>
															</c:choose>

														</c:forEach>
													</c:when>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						 
						 </div>
						 
						 <div class="tab-pane fade" id="caseLawTab" style="padding: 10px;">
						 <div class="f_form_content">
							<h2>Case law Documents</h2>
							<div class="col-md-12">
								<div class="col-md-10">
									<div class="header_button">
										<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->

									</div>
								</div>
								<div class="col-md-2">
									<center>
										<input type="button" class="btn btn-primary addLitigationDocuments"
											value="Add Document" onclick="addFileInputLitigation(5);"/>
									</center>
								</div>
							</div>
							<div>
								<form class="form-horizontal" role="form"
									id="addLitigationDocumentForm" name="addLitigationDocumentForm"
									method="post" enctype="multipart/form-data">
									<input type="text" id="ldoc_liti_id" name="ldoc_liti_id"
										value="${litigation_details_by_id.liti_id }" hidden="">
									<input type="text" value="LitigationCaseLaw"
										id="ldoc_document_type" name="ldoc_document_type" hidden="" />
									<div id="fileAddingForm5"></div>

								</form>
							</div>

							<div class="table_data1">
								<div class="col-md-12">

									<div class="table-responsive">
										<table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">
													<th>Sr No.</th>
													<th>Document Name</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody id="liti_docs_tbody5">
											<c:set var="liti_docs_loop_liti_var" value="0"/>
												<c:choose>
													<c:when test="${not empty litigation_details_by_id.liti_documents }">
													
														<c:forEach items="${litigation_details_by_id.liti_documents}" var="liti_docs_liti_related"
															varStatus="liti_docs_loop_liti_related">
															<c:choose>
																<c:when
																	test='${liti_docs_liti_related.ldoc_document_type == "LitigationCaseLaw"}'>
																	<c:set var="liti_docs_loop_liti_var" value="${liti_docs_loop_liti_var+1}"/>
																	<tr id="litiDocRow_${liti_docs_liti_related.ldoc_id }">


																		<td>${liti_docs_loop_liti_var }</td>
																		<td><a href="./downloadLitigationDocument?ldoc_id=${liti_docs_liti_related.ldoc_id }">${liti_docs_liti_related.ldoc_original_file_name}</a></td>
																		<td class="delete"><button type="button"
																				value="${liti_docs_liti_related.ldoc_id}"
																				name='delete_litigation_document'
																				class="btn btn-danger">
																				<i class='fa fa-trash'></i>&nbsp;Delete
																			</button></td>
																	</tr>
																</c:when>
															</c:choose>
														</c:forEach>
													</c:when>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						 </div>
					</div>
					</div>
					<div class="tab-pane fade" id="CounselFees" style="padding: 10px;">
					    <div class="col-md-12">
					    
					    </div>
						<ul id="myTab" class="nav nav-tabs mynav-tabs" style="width: 251px;">
							<li class="active"><a href="#CounselFeesTab" data-toggle="tab">Counsel Fees</a></li>
							<li><a href="#advocateFeesTab" data-toggle="tab"> Advocate Fees </a></li>
							
						</ul>
						<div id="myTabContent1" class=" container tab-content">
					        <div class="tab-pane fade active in" id="CounselFeesTab"  style="padding: 10px;">
								<table id="example">
									<tr>
										<td></td>
										<td style="text-align: right;"><c:if test="${litigation_details_by_id.liti_status!='Completed' && litigation_details_by_id.liti_status!='Draft'}">
													<a
														href="./addCounselFees?liti_id=${litigation_details_by_id.liti_id }"><input
														class="btn btn-primary" type="button"
														name="Add Counsel Fees" id="add_counsel_fees"
														value="Add Agreed Fees"></a>
												</c:if>
										</td>
									</tr>
									<tr>
										<td style="text-align: left; font-size: 18px;"><strong>Counsel
												Fees</strong></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2">
											<table id="example"
												class="table table-striped table-bordered"
												style="width: 1000px;" cellspacing="0">
												<tr style="background-color: grey; color: white;">
													<th>Sr No.</th>
													<th>Counsel Name</th>
													<th>Type of Fees</th>
													<th>Effective/Non-Effective</th>
													<th>Fees</th>
													<th>Comments</th>
													<th>Document</th>
													<th>Action</th>
												</tr>
												<tbody id="">
													<c:forEach items="${CounselFeesDetails}" var="fees"
														varStatus="loop">
														<tr>
															<td>${loop.index+1 }</td>
															<td>${fees.lcou_counsel_name}</td>
															<td>${fees.lcou_type_of_fees}</td>
															<td>${fees.lcou_effective_non_effective}</td>
															<td>${fees.lcou_counsel_fees_amount}</td>
															<td>${fees.lcou_comments}</td>
															<td>
																<c:choose>
							 										<c:when test="${fn:length(fees.fees_doc)==0}">
							   												NA
																	</c:when>
																	<c:otherwise>
							   											<c:forEach items="${fees.fees_doc}" var="doc">
																		  <a href="./downloadLitigationFeesDocument?fdoc_id=${doc.fdoc_id} "> ${doc.fdoc_original_file_name}</a>
							  											</c:forEach>
																	</c:otherwise>
																</c:choose> 
                         									</td>
															
															<td>
															<c:if test="${fees.lcoun_fees_added_by==sessionScope.sess_user_id || sessionScope.sess_user_role ==1}">
															<a href="./editCounselFees?lcou_id=${fees.lcou_id}"><button class="btn btn-primary" value="submit"
																	name="submit" type="button">
																	<i class="fa fa-pencil-square-o"></i>&nbsp;Edit
																</button></a>
																</c:if>
																<a href="./listPaidCounselFees?counsel_fees_id=${fees.lcou_id}"><button class="btn btn-primary" value="submit"
																	name="submit" type="button">
																	<i class="fa fa-pencil-square-o"></i>&nbsp;Paid Fees
																</button></a>
																</td>
														</tr>

													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
							</div>
					       <div class="tab-pane fade" id="advocateFeesTab" style="padding: 10px;">
								<table id="example">
									<tr>
										<td></td>
										<td style="text-align: right;"><c:if test="${litigation_details_by_id.liti_status!='Completed' && litigation_details_by_id.liti_status!='Draft'}">
													<a
														href="./addAdvocateFees?liti_id=${litigation_details_by_id.liti_id }"><input
														class="btn btn-primary" type="button"
														value="Add Agreed Fees"></a>
												</c:if>
										</td>
									</tr>
									<tr>
										<td style="text-align: left; font-size: 18px;"><strong>Advocate	Fees</strong></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2">
											<table id="example"
												class="table table-striped table-bordered"
												style="width: 1000px;" cellspacing="0">
												<tr style="background-color: grey; color: white;">
													<th>Sr No.</th>
													<th>Advocate Name</th>
													<th>Type of Fees</th>
													<th>Effective/Non-Effective</th>
													<th>Fees</th>
													<th>Comments</th>
													<th>Document</th>
													<th>Action</th>
												</tr>
												<tbody id="">
													<c:forEach items="${AdvocateFeesDetails}" var="fees"
														varStatus="loop">
														<tr>
															<td>${loop.index+1 }</td>
															<td>${fees.ladv_advocate_name}</td>
															<td>${fees.ladv_type_of_fees}</td>
															<td>${fees.ladv_effective_non_effective}</td>
															<td>${fees.ladv_advocate_fees_amount}</td>
															<td>${fees.ladv_comments}</td>
															<td>
																<c:choose>
							 										<c:when test="${fn:length(fees.fees_doc)==0}">
							   												NA
																	</c:when>
																	<c:otherwise>
							   											<c:forEach items="${fees.fees_doc}" var="doc">
																		  <a href="./downloadLitigationFeesDocument?fdoc_id=${doc.fdoc_id} "> ${doc.fdoc_original_file_name}</a>
							  											</c:forEach>
																	</c:otherwise>
																</c:choose> 
                         									</td>
                         									
															<td>
															<c:if test="${fees.ladv_added_by==sessionScope.sess_user_id || sessionScope.sess_user_role ==1}">
															<a href="./editAdvocateFees?ladv_id=${fees.ladv_id}"><button class="btn btn-primary" value="submit"
																	name="submit" type="button" style="margin : 2px">
																	<i class="fa fa-pencil-square-o"></i>&nbsp;Edit
																</button></a></c:if>
																<a href="./listPaidAdvocateFees?advocate_fees_id=${fees.ladv_id}"><button class="btn btn-primary" value="submit"
																	name="submit" type="button" style="margin : 2px">
																	<i class="fa fa-pencil-square-o"></i>&nbsp;Paid Fees
																</button></a>
																</td>
														</tr>

													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
					
					<div class="tab-pane fade" id="Completion" style="padding: 10px;">
						<div class="f_form_content">
						<c:choose>
						   <c:when test="${litigation_details_by_id.liti_status=='Completed'}">
						      <h2>Completion History</h2>
						      <table id="example" class="table table-striped table-bordered"
											width="100%" cellspacing="0">
											<thead>
												<tr style="background: #0B6EC3; color: #fff;">

													<th>Sr No.</th>
													<th>Litigation Result</th>
													<th>Disposal Date</th>
													<th>Synopsis of Order </th>
													<th>Court/Tribunal</th>
													<th>Last Date for Filling Appeal</th>
													<th>Documents</th>
													<!-- <th>Action</th> -->
												</tr>
											</thead>

											<tbody id="litigation_history">
											<tr>
													<td>1</td>
													<td>${litigation_details_by_id.liti_litigation_result}</td>
													<td>${litigation_details_by_id.liti_disposal_date}</td>
													<td>${litigation_details_by_id.liti_synopsis_of_order}</td>
													<td>${litigation_details_by_id.liti_completion_court_name}</td>
													<td>${litigation_details_by_id.liti_last_date_for_filling_appeal}</td>
													<td><c:forEach items="${litigation_details_by_id.liti_documents}" var="doc">
													   <c:choose>
													      <c:when test="${doc.ldoc_document_type =='LitigationCompletion'}">
													          <a href="./downloadLitigationDocument?ldoc_id=${doc.ldoc_id} ">${doc.ldoc_original_file_name}</a>
													      </c:when> 
													   </c:choose>     
													</c:forEach></td>
													<!-- <td><button class="btn btn-primary" value="submit" name="submit" type="button">
							                            <i class="fa fa-pencil-square-o"></i>&nbsp;Edit
								                        </button></td> -->
												</tr>

											</tbody>
										</table>
						   </c:when>
						   <c:otherwise>
						     <h2>Completion</h2>
							<sf:form commandName="litigation_completion" type="post" action="./saveLitiCompletion" enctype="multipart/form-data" onsubmit="return validateCompletion();">
							    <table>
                                      <sf:input path="liti_id" type="hidden" value="${litigation_details_by_id.liti_id }"/>
								<tr>
									<td style="text-align: right;padding: 10px;"><strong>Result : </strong></td>
									<td style="text-align: left;padding: 10px;">
									<sf:select class="form-control" path="liti_litigation_result">
											<option value="0">-- Select --</option>
											<option value="Against">Against</option>
											<option value="In Favour">In Favour</option>
											<option value="Partially Against">Partially Against</option>
											<option value="Partially In Favour">Partially In Favour</option>
											<option value="Settled">Settled</option>
											<option value="WithDrawn">WithDrawn</option>
									</sf:select></td>
								</tr>
								<tr>
									<td style="text-align: right; padding: 10px;"><strong>Disposal Date : </strong></td>
									<td style="text-align: left; padding: 10px;"><div id="datepicker1" class="input-group date" data-date-format="MM">
									<sf:input class="form-control" type="text" path="liti_disposal_date" id="liti_disposal_date" name="liti_disposal_date" readonly="true"/> 
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div></td>
								</tr>
								<tr id="against_tab" style="display: none;">
									<td style="text-align: right; padding: 10px;"><strong>Whether want to filing Appeal : </strong></td>
									<td style="text-align: left; padding: 5px;"><input type="radio" value="Yes" name="file_appeal_result" id="file_yes"> Yes 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" checked="checked" value="No" name="file_appeal_result" id="file_no"> No<br></td>
								</tr>
								<tr id="synopsis_div">
									<td style="text-align: right; padding: 10px;"><strong>Synopsis
											of the order : </strong></td>
									<td style="text-align: left; padding: 10px;"><sf:textarea class="form-control" path="liti_synopsis_of_order"></sf:textarea></td>
								</tr>
								<tr id="court_div">
									<td style="text-align: right;padding: 10px;"><strong>Court/Tribunal : </strong></td>
									<td style="text-align: left;padding: 10px;">
									<sf:select path="liti_completion_court_id" class="form-control">
												<option value="0" >--Select--</option>
												<c:forEach items="${allCourt}" var="court">
													<sf:option value="${court.court_id}">${court.court_name}</sf:option>
												</c:forEach>
									</sf:select></td>
								</tr>
								<tr id="last_date_div">
									<td style="text-align: right; padding: 10px;"><strong>Last
											Date for Fillig Appeal : </strong></td>
									<td style="text-align: left; padding: 10px;">
									<div id="datepicker2" class="input-group date" data-date-format="MM">
									<sf:input class="form-control" type="text" path="liti_last_date_for_filling_appeal" id="liti_last_date_for_filling_appeal" name="liti_last_date_for_filling_appeal" readonly="true"/> 
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div></td>
								</tr>
								<tr>
									<td style="text-align: right; padding: 10px;"><strong>Comments : </strong></td>
									<td style="text-align: left; padding: 10px;"><sf:textarea class="form-control" path="liti_comments"></sf:textarea></td>
								</tr>
								<tr>
									<td style="text-align: right; padding: 10px;"><strong>Upload
											File :</strong></td>
									<td style="text-align: left; padding: 10px;"><input type="file" name="liti_completion_doc" /></td>
								</tr>
								<tr>
									<td style="text-align: right; padding: 10px;"><input type="submit" class="btn btn-success" name="submit" value="Save"></td>
									<td style="text-align: left; padding: 10px;"></td>
								</tr>
							</table>
							
							</sf:form>
						   </c:otherwise>
						</c:choose>
							
						</div>
					</div>
									
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Body -->

<script type="text/javascript">

$(document).ready(function(){
	
	//alert("previous id: "+ '${litigation_details_by_id.liti_previous_liti_ref_no}');
	$("#liti_completion_court_id").multiselect({
		buttonWidth: '290px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 200,
	    onChange: function(option, checked) {
                var values = [];
                $('#liti_completion_court_id option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#liti_completion_court_id').multiselect('deselect', values);
            }
	});
});
 	
$('#liti_litigation_result').on('click', function() {
	 var e = document.getElementById("liti_litigation_result");
	 var strUser = e.options[e.selectedIndex].text;
	 //alert("onclick "+strUser);
	 if(strUser == 'Against'){
		 $("#synopsis_div").hide();
		 $("#court_div").hide();
		 $("#last_date_div").hide();
		 $("#against_tab").show();
		 
	 } else{
		 
		 $("#synopsis_div").show();
		 $("#court_div").show();
		 $("#last_date_div").show();
		 $("#against_tab").hide();
	 }
});
 	
 	$('input[name="file_appeal_result"]').on("click", function(){
 		if($(this).val() == 'Yes'){
 			
 			$("#last_date_div").show();
 			
 		} else{
 			 $("#last_date_div").hide();
 		}
 	});
 	
//Harshad Padole - get Hearing Stages info
var req_send = 0;
	$("#hearingStage").click(function() {
		if(req_send==0){
			var liti_id = "${litigation_details_by_id.liti_id}";
			$("#loader_table").show();
			$.ajax({
				type : "POST",
				url  : "./getHearingStagesById",
				data : {liti_id : liti_id},
				cacth : false,
				success : function(result){
					var res = jQuery.parseJSON(result);
					var data = "";
					//console.log(res.length);
					if(res.length !=0){
						var i = 1;
						$.each(res,function(key,value){
							data += "<tr id='row_"+value['hear_stage_id']+"'>";
							data += "<td>"+i+" </td>";
							//data += "<td>"+value['stages']+"</td>";
							data += "<td>"+value['hearing_date']+" </td>";
							data += "<td>"+value['resp_person']+" </td>";
							var count_coun = Object.keys(value['counsel_list']).length;
							var j = 1;
							if(count_coun!=0){
								var coun_td = "";
								$.each(value['counsel_list'],function(key,value){ 
									coun_td += j+") "+value['hsco_counsel_name']+"</br>";
									j++;
							       });
								data += "<td>"+coun_td+"</td>";
							}else{
								data += "<td> NA </td>";
							}
							//data += "<td>"+value['counsel_name']+" </td>";
							data += "<td> 1st: "+value['first_alert']+" </br> 2nd: "+value['second_alert']+" </br> 3rd: "+value['third_alert']+" </td>";
							data += "<td>"+value['stage_desc']+" </td>";
							var count = Object.keys(value['docList']).length
							if(count!=0){
								var doc_td = "";
								$.each(value['docList'],function(key,value){ 
									doc_td += "<a href='downloadHearingDocument?hearing_doc_id="+value['doc_id']+"'>"+value['doc_orig_name']+"</a> </br>";
							       });
								data += "<td>"+doc_td+"</td>";
							}else{
								data += "<td> NA </td>";
							}
							data +="<td>"; 
							if(value['hearing_status'] != 'Waiting for Approval' ){
							   if((value['added_by']==value['user_id'] || value['user_role'] == 1)){
								   data += "<a href='editHearingStage?hear_stage_id="+value['hear_stage_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin-bottom: 4px;'>" +
						             "<i class='fa fa-pencil-square-o'></i>&nbsp;Edit </button></a><br/>";
								   data += "<button type='button' name='delete_hearing_stage' value='"+value['hear_stage_id']+"' class='btn btn-danger' style='margin-bottom: 4px;'>" +
						             "<i class='fa fa-trash'></i>&nbsp;Delete </button>";
							   }else{
								   data +=" No Access.";
							   }
							}else{
								data += '<a href="./addHearingByApprove?id='+value['hear_stage_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Approve</button></a>';
							}
							data +="</td>";
							data += "</tr>";
							i++;
						});
						/* Change request status to 1 if request already send */
						req_send =1;
						
					}else{
						 data += "<tr><td style='text-align:center' colspan='9'> No result found.</td></tr>";
					}
					$("#loader_table").hide();
					$("#searchResult").html(data);
				}
			});
		}
	});
	
	$("#add_counsel_fees").click(function(){
		 $("#addCounselFees").modal('show');
	});
</script>


<script src="appJs/Litigation/litigation.js"></script>
<script src="appJs/HearingStage/hearing_validation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>