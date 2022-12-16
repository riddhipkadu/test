<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
<!-- Fail Modal END -->


	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<h2 style="color: #a72f14; font-size: 24px; float: left;">
					Update</h2>
				<a href="./listLegalNotice"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>

		<!-- Tab Menu -->

		<div class="task_tabs">

			<ul id="myTab" class="nav nav-tabs mynav-tabs">
				<li class="active"><a href="#details" data-toggle="tab">Details</a></li>
				<li><a href="#documents" data-toggle="tab">Documents</a></li>
				<li><a href="#status" data-toggle="tab">Status</a></li>
			</ul>



			<div id="myTabContent" class="tab-content">

				<div class="tab-pane fade active in" id="details">
					<div class="col-md-12" style="margin-top: 20px;">

						<!-- <div class="col-md-12">
							<div class="header_button">
								<h2 style="color: #032BEC; font-size: 24px; float: left;">Details</h2>
							</div>
						</div> -->
						<div style="clear: both"></div>
						<c:choose>
							<c:when
								test="${legalNoticeDetails.lega_noti_status == 'Converted' }">
								<div class="col-md-12 light">
									<div class="col-md-2">
										<label>Litigation Reference Number :</label>
									</div>
									<div class="col-md-10">
										<span><a href="litigationDetails?liti_id=${liti_id }"
											style="color: blue;">${legalNoticeDetails.lega_noti_liti_ref_id }</a></span>
									</div>
								</div>
							</c:when>
						</c:choose>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Entity Name :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_entity_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Unit/Vertical Name :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_unit_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Function/Location Name :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_function_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>By/Against Company :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_by_against } Company</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Opposite Party :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_opposite_party }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Category:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_category_name }</span>
							</div>
						</div>
					
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Notice/Ref. No:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_reference_no }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Address to :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_addressed_to }</span>
							</div>
						</div>



						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Primary Responsible Person to:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_assigned_to_name }</span>
							</div>
						</div>

						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>secondary Responsible Person to:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_secondary_responsible_person_name}</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Third Responsible Person to:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_third_responsible_person_name}</span>
							</div>
						</div>

						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Fourth Responsible Person to:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_other_responsible_person_name}</span>
							</div>
						</div>



						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Internal contact person:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_intern_cont_person }</span>
							</div>
						</div>

						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Notice dated :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_dated }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Sent/Received on:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_recived_on }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Notice reply deadline :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_reply_deadline }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Notice reminder date:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_reminder_date }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>External Counsel :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_external_counsel_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Opposite party advocate :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_opposite_party_advocate }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Relevant law :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_relevant_law }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Brief facts/comments:</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_comments }</span>
							</div>
						</div>
						
							<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Amount involved :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_amount_involved }
									${legalNoticeDetails.lega_noti_involved_amt_currency }</span>
							</div>
						</div>

						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Interest :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_interest }</span>
							</div>
						</div>

						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Total Amount :</label>
							</div>
							<div class="col-md-10">
								<span>${legalNoticeDetails.lega_noti_total_amount }</span>
							</div>
						</div>


					</div>
				</div>

				<div class="tab-pane fade" id="documents">
					<div class="col-md-12" style="margin-top: 20px;">
						<!-- <h4><span class="label label-primary">Legal Notice Documents</span></h4> -->
					</div>
					<div class="col-md-12" style="margin-top: 20px;">

						<table id="example" class="table table-striped table-bordered"
							width="100%" cellspacing="0">

							<thead>
								<tr style="background: #a72f14; color: #fff;">
									<th>Sr.No</th>
									<th>Document Name</th>
									<th>Delete</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach items="${legalNoticeDocuments}" var="document"
									varStatus="doc">
									<tr id="row_${document.lega_doc_id}">

										<td>${doc.index+1}</td>
										<td><a
											href="./downloadLegalNoticeDoc?lega_doc_id=${document.lega_doc_id}">${document.lega_doc_original_file_name}</a></td>
										<td><button type="button" value="${document.lega_doc_id}"
												name='delete_legal_notice_status_doc' class="btn btn-danger"
												style="margin-bottom: 4px;">
												<i class='fa fa-trash'></i>&nbsp;Delete
											</button></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>

					</div>
				</div>

				<div class="tab-pane fade" id="status">
					<c:if test="${legalNoticeDetails.lega_noti_status!='Draft' }">
						<div class="col-md-12" style="margin-top: 20px;">
							<div>
								<a
									href="legalNoticeStatus?lega_noti_id=${legalNoticeDetails.lega_noti_id}"
									class="btn btn-primary">Add Status</a>
							</div>
						</div>
					</c:if>
					<div class="col-md-12" style="margin-top: 20px;">

						<table id="example" class="table table-striped table-bordered"
							width="100%" cellspacing="0">

							<thead>
								<tr style="background: #a72f14; color: #fff;">
									<th>Sr.No</th>
									<th>Received Date</th>
									<th>Action Taken</th>
									<th>Next Action Item</th>
									<th>Next Action Due Date</th>
									<th>Responsible Person</th>
									<th>Reminder Date</th>
									<th>Status</th>
									<th>Documents</th>
									<th>Action</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach items="${legalNoticeStatus}" var="status"
									varStatus="loop">
									<tr id="row_${status.lega_status_id}">
										<td>${loop.index+1 }</td>
										<td>${status.lega_received_date }</td>
										<td>${status.lega_action_taken }</td>
										<td>${status.lega_next_action_item  }</td>
										<td>${status.lega_action_item_due_date}</td>
										<td>${status.lega_person_responsible}</td>
										<td>${status.lega_reminder_date}</td>
										<td><c:choose>
												<c:when test="${status.lega_notice_status =='NA'}">
										Active
										</c:when>
												<c:otherwise>
										${status.lega_notice_status}
										</c:otherwise>
											</c:choose></td>
										<td><c:forEach items="${status.lega_documents }"
												var="status_doc">
												<a
													href="./downloadLegalNoticeDoc?lega_doc_id=${status_doc.lega_doc_id}">
													${status_doc.lega_doc_original_file_name } </a>
												<br>
											</c:forEach></td>
										<td><c:choose>
												<c:when
													test="${sessionScope.sess_user_role==1 || status.lega_status_added_by == sessionScope.sess_user_id}">
													<a
														href="editLegalNoticeStatus?lega_noti_status_id=${status.lega_status_id}">
														<button type="button" name='submit' value="submit"
															class="btn btn-primary btn-block"
															style="margin-bottom: 4px">
															<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
														</button>
													</a>
													<button type="button" value="${status.lega_status_id}"
														name='delete_legal_notice_status'
														class="btn btn-danger btn-block"
														style="margin-bottom: 4px;">
														<i class='fa fa-trash'></i>&nbsp;Delete
													</button>
												</c:when>
												<c:otherwise>
														No Access
											    </c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</div>


<script src="appJs/LegalNotice/LegalNoticeStatus.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>