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
				<h2 style="color: #032BEC; font-size: 24px; float: left;">
					Update</h2>
				<a href="./listExecutedContract"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>

		<!-- Tab Menu -->

		<div class="task_tabs">

			<ul id="myTab" class="nav nav-tabs mynav-tabs">
				<li class="active"><a href="#details" data-toggle="tab">Details</a></li>
				<!-- <li><a href="#history" data-toggle="tab">History</a></li> -->
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
								<span>${executedContractDetails.exec_contract_entity_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Unit/Vertical :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_unit_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Function/Location /Department :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_function_name }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Contract Type :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_type_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Description :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_description }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Person responsible name:</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_resposible_person_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Parties  :</label>
							</div>
							<div class="col-md-10">
								<c:forEach items="${executedContractDetails.exec_parties}" var="parties" varStatus="loop"> 
							       ${loop.index+1}) ${parties.cont_party_name}
							     </c:forEach>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Contact person:</label>
							</div>
							<div class="col-md-2">
								<span>${executedContractDetails.exec_contract_contact_person }</span>
							</div>
							<div class="col-md-1">
								<label>Email:</label>
							</div>
							<div class="col-md-3">
								<span>${executedContractDetails.exec_contract_cont_person_email }</span>
							</div>
							<div class="col-md-2">
								<label>Contact number:</label>
							</div>
							<div class="col-md-2">
								<span>${executedContractDetails.exec_contract_cont_person_number }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Contract date :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_date }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Start date:</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_start_date_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>End date :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_end_date_name }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Surviving Obligations:</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_surviving_obl }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Performance related obligations & payment  :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_payment }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Notice period(in months) :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_notice_period }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Insurance  :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_insurance }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Damages :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_damages }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Jurisdiction/ governing law :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_jurisdiction }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Seat of arbitration :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_arbitration }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Criticality :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_criticality }</span>
							</div>
						</div>

					</div>
				</div>

				<%-- <div class="tab-pane fade" id="history">
					<div class="col-md-12" style="margin-top: 20px;">

						<div class="col-md-12" style="text-align: right;">

							<a href="./addContractHistory?cont_id=${contractDetails.cont_id }"><button
									type="button" onclick="" name='submit' value="submit"
									class="btn btn-primary">

									<i class='fa fa-pencil-square-o'></i>&nbsp;Update
								</button></a>

						</div>
 						<div id="col-md-12">
						<table id="example" class="table table-striped table-bordered"
							width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #0B6EC3; color: #fff;">

									<th>Sr.No</th>
									<th>Comments</th>
									
									<th>Status</th>
									<th>Date</th>
									<th>Reviewer</th>
									<th>Document</th>
									<!-- <th>Edit</th> -->

								</tr>
							</thead>

							<tbody>
							<c:forEach items="${contractHistory}" var="contracthst" varStatus="contracthstLoop">
							<tr>
							<td>${contracthstLoop.index+1 }</td>
							<td>${contracthst.chst_comments }</td>
							<td>${contracthst.chst_status }</td>
							<td>${contracthst.chst_created_at }</td>
							<td>${contracthst.chst_reviewer }</td>
							<td>
							<c:choose>
							 <c:when test="${fn:length(contracthst.hst_doc)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:forEach items="${contracthst.hst_doc}" var="doc"><a href="./downloadPreExecutedHistoryDocument?chst_doc_id=${doc.chst_doc_id }">
							   ${doc.chst_doc_original_file_name}</a> <br>
							</c:forEach>
							</c:otherwise>
							</c:choose>

                              </td>
							<td class="edit"><center>
											<a href="#"><button type="button" onclick=""
													name='submit' value="submit" class="btn btn-primary">

													<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
												</button></a>
										</center></td>
							</tr>
							
							</c:forEach>

								
							</tbody>
						</table>
						</div>
					</div>
				</div> --%>

				<div class="tab-pane fade" id="documents">
					<div class="col-md-12" style="margin-top: 20px;">
						<h4><span class="label label-primary">Executed Contract Documents</span></h4>
					</div>
					<div class="col-md-12" style="margin-top: 20px;">
					     
                          <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #0B6EC3; color: #fff;">
								    <th>Sr.No</th>
									<th>Document Name</th>
								    <th>Delete</th> 
								</tr>
							</thead>

							<tbody>
							
							<c:forEach items="${executedContractDocuments}" var="document" varStatus="doc">
							<tr id="row_${document.exec_doc_id}">
							
							<td>${doc.index+1 }</td>
							<td><a href="./downloadExecutedContractDoc?exec_doc_id=${document.exec_doc_id}">${document.exec_original_file_name}</a></td>
							<td><button type="button" name="delete_executed_contract_doc" value="${document.exec_doc_id}" class="btn btn-danger">
							<i class='fa fa-trash'></i>&nbsp;Delete	</button>
							</td>
							
							</tr>
							</c:forEach>
								
							</tbody>
						</table>

					</div>
					<%-- <div class="col-md-12" style="margin-top: 20px;">
						<h4><span class="label label-primary">Contract Documents</span></h4>
					</div>
					<div class="col-md-12" style="margin-top: 20px;">
                          <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #0B6EC3; color: #fff;">
								    <th>Sr.No</th>
									<th>Document Name</th>
									<!-- <th>Delete</th> -->
								</tr>
							</thead>

							<tbody>
							<c:forEach items="${contractDocuments}" var="document" varStatus="doc1">
							<c:choose>
							<c:when test="${document.cdoc_document_type=='Contract'}">
							<c:set var="contract_docs" value="${contract_docs+1}"/>
							<tr>
							<td>${contract_docs}</td>
							<td><a href="./downloadPreExecutedDocument?cdoc_id=${document.cdoc_id }">${document.cdoc_original_file_name}</td>
							<td class="edit">
											<a href="${document.cdoc_id}"><button type="button" onclick=""
													name='submit' value="submit" class="btn btn-danger">

													<i class='fa fa-trash'></i>&nbsp;Delete
												</button></a>
										</td>
							</tr>
							</c:when>
							</c:choose>
							</c:forEach>
								
							</tbody>
						</table>
					</div> --%>
				</div>

			</div>
		</div>
	</div>
</div>
<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>