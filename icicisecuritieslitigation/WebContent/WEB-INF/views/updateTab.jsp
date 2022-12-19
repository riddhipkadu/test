<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<h2 style="color: #a72f14; font-size: 24px; float: left;">
					Update Contract Status</h2>
				<a href="./listPreExecutionSummary"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>

		<!-- Tab Menu -->

		<div class="task_tabs">

			<ul id="myTab" class="nav nav-tabs mynav-tabs">
				<li class="active"><a href="#details" data-toggle="tab">Details</a></li>
				<li><a href="#history" data-toggle="tab">History</a></li>
				<li><a href="#statusForNegotiation" data-toggle="tab">Status of Document</a></li>
                <li><a href="#documents" data-toggle="tab">Documents</a></li>
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

						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Entity/Company Name :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.orga_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Unit/Vertical  :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.loca_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Function/Location
								/Department :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.dept_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Name of Document :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_agreement_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Document Type:</label>
							</div>
							<div class="col-md-10">
							
							<c:choose>
							 <c:when test="${fn:length(contractDetails.cont_type_list_name)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:set var="cont_type" scope="page" value="0"/> 
						    		<c:forEach items="${contractDetails.cont_type_list_name}" var="cont" >
						         <c:set var="cont_type" value="${cont_type+1}"/>
						         ${cont_type}) ${cont.cont_type_name}<br>
						     		</c:forEach>
							</c:otherwise>
							</c:choose>
							</div>
						</div>
						
						<%-- <div class="col-md-12 light">
							<div class="col-md-2">
								<label>Requested On :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_requested_date }</span>
							</div>
						</div> --%>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Expected date of closure :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_expected_date }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Start date :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_start_date_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>End date :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_end_date_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Party:</label>
							</div>
							<div class="col-md-10">
							
							<c:choose>
							 <c:when test="${fn:length(contractDetails.cont_parties)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:forEach items="${contractDetails.cont_parties}" var="party" varStatus="loop">
							  <span> ${loop.index+1}) ${party.cont_party_name }</span>
							</c:forEach>
							</c:otherwise>
							</c:choose>
							</div>
						</div>
						<%-- <div class="col-md-12 light">
							<div class="col-md-2">
								<label>Party Two:</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_party_two }</span>
							</div>
						</div> --%>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Requested By:</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.user_requested_by_fullname }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Assigned to :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.user_responsible_fullname }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Targeted date :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_targetted_date }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Criticality :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_criticality}</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Nature & Scope :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_nature }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Term :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_term}</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Payment :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_payment }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Surviving clauses :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_surviving_clause}</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Any other major clauses :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_major_clause }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Any Damages :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_damages}</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Any Instructions :</label>
							</div>
							<div class="col-md-10">
								<span>${contractDetails.cont_instructions }</span>
							</div>
						</div>

					</div>
				</div>

				<div class="tab-pane fade" id="history">
					<div class="col-md-12" style="margin-top: 20px;">
					<a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[7,8]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
						
						<div class="col-md-12" style="text-align: right; margin: 10px">
							<c:choose>

								<c:when
									test="${contractDetails.cont_status !='Draft' && contractDetails.cont_status != 'Closed/Executed' || sessionScope.sess_user_role==1 }">

									<a
										href="./addContractHistory?cont_id=${contractDetails.cont_id }"><button
											type="button" onclick="" name='submit' value="submit"
											class="btn btn-primary">

											<i class='fa fa-pencil-square-o'></i>&nbsp;Update
										</button></a>
								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>
						</div>
						<div id="col-md-12">
						<table id="example" class="table table-striped table-bordered"
							width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #a72f14; color: #fff;">

									<th>Sr.No</th>
									<th>Action assigned to</th>
									<th>Status</th>
									<th>Received date</th> 
									<th>Action to be performed</th>
									<!-- <th>Action to be performed by</th> -->
									<th>Comments</th>
									<th>Date</th>
									<th>Document</th>
									<c:choose>
										<c:when test="${contractDetails.cont_status != 'Closed/Executed' || sessionScope.sess_user_role==1}">
									<th>Action</th>
										</c:when>
									</c:choose>
								</tr>
							</thead>

							<tbody id="historyResult">
							<c:forEach items="${contractHistory}" var="contracthst" varStatus="contracthstLoop">
							  <!-- Start If draft and added by currently login user -->
										<c:if
											test="${contracthst.chst_status =='Save As Draft' && sessionScope.sess_user_id == contracthst.chst_added_by}">
											<tr id="row_${contracthst.chst_id}">
												<td>${contracthstLoop.index+1 }</td>
												<td><c:choose>
														<c:when test="${contracthst.chst_assigned_to =='Others'}">
								${contracthst.chst_assign_others}
								</c:when>
														<c:otherwise>
									${contracthst.chst_assigned_to }
								</c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${contracthst.chst_status =='Others' }">
								${contracthst.chst_status_others}
								</c:when>
														<c:otherwise>
									${contracthst.chst_status}
								</c:otherwise>
													</c:choose></td>

												<td><c:choose>
														<c:when test="${contracthst.chst_received_date =='NA' }">
								NA
								</c:when>
														<c:otherwise>
								${contracthst.chst_received_date}
								</c:otherwise>
													</c:choose></td>

												<td><c:choose>
														<c:when
															test="${contracthst.chst_action_performed=='Others' }">
								${contracthst.chst_action_performed_others}
								</c:when>
														<c:otherwise>
								${contracthst.chst_action_performed }
								</c:otherwise>
													</c:choose></td>

												<%-- <td><c:choose>
														<c:when test="${contracthst.chst_performed_by=='Others' }">
								${contracthst.chst_performed_by_others}
								</c:when>
														<c:otherwise>
								${contracthst.chst_performed_by }
								</c:otherwise>
													</c:choose></td> --%>
												<td>${contracthst.chst_comments }</td>
												<td>${contracthst.chst_created_at }</td>

												<td><c:choose>
														<c:when test="${fn:length(contracthst.hst_doc)==0}">
							  									 NA
														</c:when>
														<c:otherwise>
															<c:forEach items="${contracthst.hst_doc}" var="doc">
																<a
																	href="./downloadPreExecutedHistoryDocument?chst_doc_id=${doc.chst_doc_id }">
																	${doc.chst_doc_original_file_name}</a>
																<br>
															</c:forEach>
														</c:otherwise>
													</c:choose>
												</td>
												<c:choose>
													<c:when
														test="${contractDetails.cont_status != 'Closed/Executed' || sessionScope.sess_user_role==1}">
														<td class="edit"><a
															href="./editContractHistory?chst_id=${contracthst.chst_id }">
																<button type="button" onclick="" name='submit'
																	value="submit" class="btn btn-primary"
																	style="margin-bottom: 4px;">

																	<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
																</button>
														</a><br />
															<button type="button" value="${contracthst.chst_id}"
																name='delete_pre_contract_history'
																class="btn btn-danger" style="margin-bottom: 4px;">
																<i class='fa fa-trash'></i>&nbsp;Delete
															</button></td>
													</c:when>
												</c:choose>
											</tr>
										</c:if>
										<!-- END If draft and added by currently login user -->

										<c:if test="${contracthst.chst_status !='Save As Draft'}">
											<tr id="row_${contracthst.chst_id}">
												<td>${contracthstLoop.index+1 }</td>
												<td><c:choose>
														<c:when test="${contracthst.chst_assigned_to =='Others'}">
								${contracthst.chst_assign_others}
								</c:when>
														<c:otherwise>
									${contracthst.chst_assigned_to }
								</c:otherwise>
													</c:choose></td>
												<td><c:choose>
														<c:when test="${contracthst.chst_status =='Others' }">
								${contracthst.chst_status_others}
								</c:when>
														<c:otherwise>
									${contracthst.chst_status}
								</c:otherwise>
													</c:choose></td>

												<td><c:choose>
														<c:when test="${contracthst.chst_received_date =='NA' }">
								NA
								</c:when>
														<c:otherwise>
								${contracthst.chst_received_date}
								</c:otherwise>
													</c:choose></td>

												<td><c:choose>
														<c:when
															test="${contracthst.chst_action_performed=='Others' }">
								${contracthst.chst_action_performed_others}
								</c:when>
														<c:otherwise>
								${contracthst.chst_action_performed }
								</c:otherwise>
													</c:choose></td>

												<%-- <td><c:choose>
														<c:when test="${contracthst.chst_performed_by=='Others' }">
								${contracthst.chst_performed_by_others}
								</c:when>
														<c:otherwise>
								${contracthst.chst_performed_by }
								</c:otherwise>
													</c:choose></td> --%>
												<td>${contracthst.chst_comments }</td>
												<td>${contracthst.chst_created_at }</td>

												<td><c:choose>
														<c:when test="${fn:length(contracthst.hst_doc)==0}">
							   NA
							</c:when>
														<c:otherwise>
															<c:forEach items="${contracthst.hst_doc}" var="doc">
																<a
																	href="./downloadPreExecutedHistoryDocument?chst_doc_id=${doc.chst_doc_id }">
																	${doc.chst_doc_original_file_name}</a>
																<br>
															</c:forEach>
														</c:otherwise>
													</c:choose></td>
												<c:choose>
													<c:when
														test="${contractDetails.cont_status != 'Closed/Executed' || sessionScope.sess_user_role==1}">
														<td class="edit"><a
															href="./editContractHistory?chst_id=${contracthst.chst_id }">
																<button type="button" onclick="" name='submit'
																	value="submit" class="btn btn-primary"
																	style="margin-bottom: 4px;">

																	<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
																</button>
														</a><br />
															<button type="button" value="${contracthst.chst_id}"
																name='delete_pre_contract_history'
																class="btn btn-danger" style="margin-bottom: 4px;">
																<i class='fa fa-trash'></i>&nbsp;Delete
															</button></td>
													</c:when>
												</c:choose>
											</tr>
										</c:if>
									</c:forEach>
								
							</tbody>
						</table>
						</div>
					</div>
				</div>
				
		<div class="tab-pane fade" id="statusForNegotiation">
		<div class="col-md-12" style="margin-top: 20px;">
		<div id="col-md-12">
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background: #a72f14;color:#fff;">
                <th>Sr. No</th>
                <th>Action Item</th>
                <th>Status Date</th>
                <th>Status added by</th>
                <th>Document</th>
                <th>Status</th>
                <!-- <th>Action</th> -->
            </tr>
        </thead>
        <tbody id="searchResult">
        <c:forEach items="${allStatus}" var="allStatus" varStatus="loop"> 
        			<tr>
						<td>${loop.index+1}</td>
						<td>${allStatus.poc_action_item}</td>
						<td>${allStatus.poc_status_date_name}</td>
						<td>${allStatus.poc_status_added_by_name}</td>
						<td><c:choose>
						<c:when test="${fn:length(allStatus.pocDoc)==0}">
							  	 NA
						</c:when>
						<c:otherwise>
						<c:forEach items="${allStatus.pocDoc}" var="doc">
						<a href="./downloadPOCDocument?doc_id=${doc.poc_doc_id }">${doc.poc_doc_original_file_name}</a>
							<br>
						</c:forEach>
						</c:otherwise>
						</c:choose>
						</td>
						<c:choose>
						<c:when test="${role_id == 2 || role_id == 3 || role_id == 4}">
						<td>${allStatus.poc_status}</td>
						</c:when>
						<c:when test="${(role_id == 1 || role_id == 5) && allStatus.poc_status =='SentToPOCforNegotiation'}">
						<td id="appDis${allStatus.poc_status_id}"><button type="button" style="margin:2px"
											onclick="getReason(${allStatus.poc_status_id},'2')" name='submit'
											value="submit" class="btn btn-danger">
											<i class='fa fa-thumbs-down'></i>&nbsp;Reject</button>
								<button type="button" style="margin:2px"
								onclick="getAccept(${allStatus.poc_status_id},'1')" name='submit'
								value="submit" class="btn btn-success">
								<i class='fa fa-thumbs-up'></i>&nbsp;Accept</button>
						</td>
						</c:when>
						<c:otherwise>
						<td>${allStatus.poc_status}</td>
						</c:otherwise>
						</c:choose>								
					</tr>
        </c:forEach>
        </tbody>
    </table>
				</div>
				</div>
	</div>
		
				<div class="tab-pane fade" id="documents">
					<div class="col-md-12" style="margin-top: 20px;">
						<h4><span class="label label-primary">Term Sheet Documents</span></h4>
					</div>
					<div class="col-md-12" style="margin-top: 20px;">
					     
                          <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #a72f14; color: #fff;">
								    <th>Sr.No</th>
									<th>Document Name</th>
									<th>Delete</th> 
								</tr>
							</thead>

							<tbody>
							<c:forEach items="${contractDocuments}" var="document" varStatus="doc">
							<c:choose>
							<c:when test="${document.cdoc_document_type=='Term'}">
							<tr id="row_${document.cdoc_id }">
							<td>${doc.index+1 }</td>
							<td><a href="./downloadPreExecutedDocument?cdoc_id=${document.cdoc_id }">${document.cdoc_original_file_name}</a></td>
							<td><button type="button" name='delete_contract_doc' value="${document.cdoc_id }" class="btn btn-danger">
								<i class='fa fa-trash'></i>&nbsp;Delete	</button>
							</td>
 							</tr>
							</c:when>
							</c:choose>
							</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-md-12" style="margin-top: 20px;">
						<h4><span class="label label-primary">Contract Documents</span></h4>
					</div>
					<div class="col-md-12" style="margin-top: 20px;">
                          <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #a72f14; color: #fff;">
								    <th>Sr.No</th>
									<th>Document Name</th>
									<th>Delete</th> 
								</tr>
							</thead>

							<tbody>
							<c:forEach items="${contractDocuments}" var="document" varStatus="doc1">
							<c:choose>
							<c:when test="${document.cdoc_document_type=='Contract'}">
							<c:set var="contract_docs" value="${contract_docs+1}"/>
							<tr id="row_${document.cdoc_id }">
							<td>${contract_docs}</td>
							<td><a href="./downloadPreExecutedDocument?cdoc_id=${document.cdoc_id }">${document.cdoc_original_file_name}</a></td>
							 <td><button type="button" name='delete_contract_doc' value="${document.cdoc_id }" class="btn btn-danger">
								<i class='fa fa-trash'></i>&nbsp;Delete	</button>
							</td> 
							</tr>
							</c:when>
							</c:choose>
							</c:forEach>
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="appJs/PreExecutionContracts/contractHistoryValidate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});

function getReason(req_id, status){
	
	var form = "<table class='table table-striped table-bordered'><tr><td><label>Reason for rejection :</label></td><td><textarea class='form-control' name='reason_disapp' id='reason_disapp' placeholder='Please enter reason for rejection'></textarea></td></tr></table>";
	bootbox.confirm(form, function (value) {
		if(value != false){
		var reason = $('#reason_disapp').val();
		if(reason != null){
			items = {};
			items["req_id"] = req_id;
			items["req_status"] = status;
			items["reason"] = reason;
			var jsonString = JSON.stringify(items);
			
			$.ajax({
				type : "post",
				url : "./approveDisapproveStatusSentNegotiation",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(response) {
					if(response==1){
						var data = '';
							data += "Rejected";
						$("#appDis"+req_id).html(data);
						//window.location = "./updateTab?cont_id="+${id};
					}else{
						$('#errors').html("Somthing went wrong please try again..!");
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		}
    });
}
 
function getAccept(req_id,status){
	//alert("Hii");
	var reason = "";
	items = {};
	items["req_id"] = req_id;
	items["req_status"] = status;
	items["reason"] = reason;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./approveDisapproveStatusSentNegotiation",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
					data += "Accepted";
				$("#appDis"+req_id).html(data);
			} else{
				$('#errors').html("Something went wrong please try again..!");
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
 }
</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
