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
	<div class="container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<h2 style="color: #054eff; font-size: 24px; float: left;">
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



			<div id="myTabContent" class="container tab-content">

				<div class="tab-pane fade active in" id="details">
				
					<div class="col-md-12" style="margin-top: 10px; ">


						<a href="#" id="exportPdf"
							class="list-group-item col-md-2 btn btn-success"
							style="width: 200px; margin-right: 8px; margin-bottom: 10px; margin-top: 10px;">
							<img src='images/reportIcons/pdf.png' width="25" /> Contract Synopsis
						</a>
						<button onclick="Export2Doc('WDoc');"
							style="width: 165px; margin-right: 8px; height: 45px; margin-top: 10px;">Export
							As .doc</button>
						
						<table align="right" id="example" class="table table-striped table-bordered"
							width="400%" cellspacing="0" border="1" >
							
							<tr>
								<th style="background: #0B6EC3; color: #fff;">Contract Synopsis</th>
								<th style="background: #0B6EC3; color: #fff;">${executedContractDetails.exec_contract_entity_name}</th>
							</tr>
							<%-- <tr>
								<td style="width: 500px;"><strong>Entity/Company Name</strong></td>
								<td>${executedContractDetails.exec_contract_entity_name}</td>
							</tr> --%>

							<tr>
								<td style="width: 500px;"><strong>Unit</strong></td>
								<td>${executedContractDetails.exec_contract_unit_name}</td>
							</tr>

							<tr>
								<td style="width: 500px;"><strong>Department</strong></td>
								<td>${executedContractDetails.exec_contract_function_name}</td>
							</tr>

							<tr>
								<td style="width: 500px;"><strong>Type of Contract</strong></td>
								<td>${executedContractDetails.exec_contract_type_name }</td>
							</tr>
							<tr>
								<td style="width: 500px;"><strong>Purpose of the Agreement</strong></td>
								<td>${executedContractDetails.exec_contract_description }</td>
							</tr>
							<tr>
								<td style="width: 500px;"><strong>Person responsible name</strong></td>
								<td>${executedContractDetails.exec_contract_resposible_person_name }</td>
							</tr>
							<tr>
								<td style="width: 500px;"><strong>Parties </strong></td>
								<td><c:forEach items="${executedContractDetails.exec_parties}" var="parties" varStatus="loop"> 
							       ${loop.index+1}) ${parties.cont_party_name}
							     </c:forEach></td>
							</tr>
							
							<%-- <tr>
								<td style="width: 200px;"><strong>Royalty Submission :</strong></td>
								<td>${executedContractDetails.exec_royalty_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Options :</strong></td>
								<td>${executedContractDetails.exec_option_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Territory :</strong></td>
								<td>${executedContractDetails.exec_territory_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Crop :</strong></td>
								<td>${executedContractDetails.exec_crop_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Technology :</strong></td>
								<td>${executedContractDetails.exec_technology_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Report :</strong></td>
								<td>${executedContractDetails.exec_report_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Milestone :</strong></td>
								<td>${executedContractDetails.exec_milestone_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Assignability :</strong></td>
								<td>${executedContractDetails.exec__assignability}</td>
							</tr> --%>

							 <tr>
								<td style="width: 500px;"><strong>Opposite Party Contact Person</strong></td>

								<td>${executedContractDetails.exec_contract_executed_contact_name}</td>
							</tr>
							<tr>
								<td style="width: 500px;"><strong>Opposite Party Email id</strong></td>
								<td>${executedContractDetails.exec_contract_cont_person_email}</td>
							</tr>

							<tr>
								<td style="width: 500px;"><strong>Contract No. </strong></td>
								<td>${executedContractDetails.exec_contract_cont_person_number }</td>
							</tr>

							<tr>
								<td style="width: 500px;"><strong>Contract Date</strong></td>

								<td>${executedContractDetails.exec_contract_date}</td>
							</tr>
							<%-- <tr>
								<td style="width: 500px;"><strong>Effective date</strong></td>
								<td>${executedContractDetails.exec_contract_start_date }</td>
							</tr> --%>

							<tr>
								<td style="width: 500px;"><strong>Expiry date</strong></td>
								<td>${executedContractDetails.exec_contract_end_date }</td>
							</tr>

							<tr>
								<td style="width: 500px;"><strong>Payment clause</strong></td>
								<td>${executedContractDetails.exec_contract_payment }</td>
							</tr>


							<tr>
								<td style="width: 200px;"><strong>Term Period of the Agreement (In months)</strong></td>
								<td>${executedContractDetails.exec_contract_notice_period}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Insurance</strong></td>
								<td>${executedContractDetails.exec_contract_insurance}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Indemnity Clause</strong></td>
								<td>${executedContractDetails.exec_contract_damages}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Seat of arbitration</strong></td>
								<td>${executedContractDetails.exec_contract_arbitration}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Lock-in-period</strong></td>
								<td>${executedContractDetails.exec_contract_lockin_period}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Termination Clause</strong></td>
								<td>${executedContractDetails.exec_contract_termination_clause}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Consideration involved</strong></td>
								<td>${executedContractDetails.exec_contract_consider_involve}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Signatories to the Agreement</strong></td>
								<td>${executedContractDetails.exec_contract_signatories_assign}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Execution date</strong></td>
								<td>${executedContractDetails.exec_contract_execution_date}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Renewal date</strong></td>
								<td>${executedContractDetails.exec_contract_renewal_date}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Any connected Amendment/Addendum Letter/renewal letter/ Agreement</strong></td>
								<td>${executedContractDetails.exec_contract_ammendment}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Renewal Status</strong></td>
								<td>${executedContractDetails.exec_contract_renewal_status}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Agreement Status</strong></td>
								<td>${executedContractDetails.exec_contract_agree_status}</td>
							</tr>
							
							
			
							</table>
							
							
						
						<!-- <div class="col-md-12">
							<div class="header_button">
								<h2 style="color: #032BEC; font-size: 24px; float: left;">Details</h2>
							</div>
						</div> -->
						<%-- <%-- <div style="clear: both"></div>

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
								<label>Type of Agreement :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_type_name }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Purpose of the Agreement :</label>
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
								<label>Parties :</label>
							</div>
							<div class="col-md-10">
								<c:forEach items="${executedContractDetails.exec_parties}" var="parties" varStatus="loop"> 
							       ${loop.index+1}) ${parties.cont_party_name}
							     </c:forEach>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Opposite Party Contact Person :</label>
							</div>
							<div class="col-md-2">
								<span>${executedContractDetails.exec_contract_contact_person }</span>
							</div>
							<div class="col-md-1">
								<label>Opposite Party Email id :</label>
							</div>
							<div class="col-md-3">
								<span>${executedContractDetails.exec_contract_cont_person_email }</span>
							</div>
							<div class="col-md-2">
								<label>Contract Date :</label>
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
								<label>Effective date :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_start_date }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Expiry date :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_end_date }</span>
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
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Payment clause :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_payment }</span>
							</div>
						</div>
						
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Term Period of the Agreement (In months) :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_notice_period }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Insurance  :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_insurance }</span>
							</div>
						</div>
						
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Indemnity Clause :</label>
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
								<label>Lock-in-period  :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_lockin_period }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Termination Clause :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_termination_clause }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Consideration involved :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_consider_involve }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Signatories to the Agreement :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_signatories_assign }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Execution date :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_execution_date }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Renewal date :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_renewal_date }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Any connected Amendment/Addendum Letter/renewal letter/ Agreement :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_ammendment }</span>
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Renewal Status :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_renewal_status }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Agreement Status: :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_agree_status }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Criticality :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_contract_criticality }</span>
							</div>
						</div> --%>
						<div id="WDoc" style="display: none;">
							<table id="example1" class="table table-striped table-bordered"
								width="100%" cellspacing="0" border="1">
								<tr>
								<th style="background: #0B6EC3; color: #fff;">Contract Synopsis </th>
								<th style="background: #0B6EC3; color: #fff;">${executedContractDetails.exec_contract_entity_name}</th>
							</tr>
							<%-- <tr>
								<td style="width: 200px;"><strong>Entity/Company Name</strong></td>
								
								<td>${executedContractDetails.exec_contract_entity_name}</td>
							</tr> --%>

							<tr>
								<td style="width: 200px;"><strong>Location</strong></td>
								<td>${executedContractDetails.exec_contract_unit_name}</td>
							</tr>

							<tr>
								<td style="width: 200px;"><strong>Department</strong></td>
								<td>${executedContractDetails.exec_contract_function_name}</td>
							</tr>

							<tr>
								<td style="width: 200px;"><strong>Type of Contract.</strong></td>
								<td>${executedContractDetails.exec_contract_type_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Purpose of the Agreement</strong></td>
								<td>${executedContractDetails.exec_contract_description }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Person responsible name</strong></td>
								<td>${executedContractDetails.exec_contract_resposible_person_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Parties </strong></td>
								<td><c:forEach items="${executedContractDetails.exec_parties}" var="parties" varStatus="loop"> 
							       ${loop.index+1}) ${parties.cont_party_name}
							     </c:forEach></td>
							</tr>
							
							<%-- <tr>
								<td style="width: 200px;"><strong>Royalty Submission :</strong></td>
								<td>${executedContractDetails.exec_royalty_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Options :</strong></td>
								<td>${executedContractDetails.exec_option_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Territory :</strong></td>
								<td>${executedContractDetails.exec_territory_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Crop :</strong></td>
								<td>${executedContractDetails.exec_crop_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Technology :</strong></td>
								<td>${executedContractDetails.exec_technology_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Report :</strong></td>
								<td>${executedContractDetails.exec_report_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Milestone :</strong></td>
								<td>${executedContractDetails.exec_milestone_number}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Assignability :</strong></td>
								<td>${executedContractDetails.exec__assignability}</td>
							</tr> --%>

							 <tr>
								<td style="width: 200px;"><strong>Opposite Party Contact Person</strong></td>

								<td>${executedContractDetails.exec_contract_executed_contact_name}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Opposite Party Email id</strong></td>
								<td> INR ${executedContractDetails.exec_contract_cont_person_email}</td>
							</tr>

							<tr>
								<td style="width: 200px;"><strong>Contract No. </strong></td>
								<td>${executedContractDetails.exec_contract_cont_person_number }</td>
							</tr>

							<tr>
								<td style="width: 200px;"><strong>Contract Date</strong></td>

								<td>${executedContractDetails.exec_contract_date}</td>
							</tr>
							<%-- <tr>
								<td style="width: 200px;"><strong>Effective date</strong></td>
								<td>${executedContractDetails.exec_contract_start_date }</td>
							</tr> --%>

							<tr>
								<td style="width: 200px;"><strong>Expiry date</strong></td>
								<td>${executedContractDetails.exec_contract_end_date }</td>
							</tr>

							<tr>
								<td style="width: 200px;"><strong>Payment clause</strong></td>
								<td>${executedContractDetails.exec_contract_payment }</td>
							</tr>


							<tr>
								<td style="width: 200px;"><strong>Term Period of the Agreement (In months)</strong></td>
								<td>${executedContractDetails.exec_contract_notice_period}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Insurance</strong></td>
								<td>${executedContractDetails.exec_contract_insurance}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Indemnity Clause</strong></td>
								<td>${executedContractDetails.exec_contract_damages}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Seat of arbitration</strong></td>
								<td>${executedContractDetails.exec_contract_arbitration}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Lock-in-period</strong></td>
								<td>${executedContractDetails.exec_contract_lockin_period}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Termination Clause</strong></td>
								<td>${executedContractDetails.exec_contract_termination_clause}</td>
							</tr>
							
							<tr>
								<td style="width: 200px;"><strong>Consideration involved</strong></td>
								<td>${executedContractDetails.exec_contract_consider_involve}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Signatories to the Agreement</strong></td>
								<td>${executedContractDetails.exec_contract_signatories_assign}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Execution date</strong></td>
								<td>${executedContractDetails.exec_contract_execution_date}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Renewal date</strong></td>
								<td>${executedContractDetails.exec_contract_renewal_date}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Any connected Amendment/Addendum Letter/renewal letter/ Agreement</strong></td>
								<td>${executedContractDetails.exec_contract_ammendment}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Renewal Status</strong></td>
								<td>${executedContractDetails.exec_contract_renewal_status}</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Agreement Status</strong></td>
								<td>${executedContractDetails.exec_contract_agree_status}</td>
							</tr>
			                 <tr>
								<td style="width: 200px;"><strong>Force Majeure</strong></td>
								<td>${executedContractDetails.exec_force_majeure}</td>
							</tr>
							
							
							
							
							<%-- <!-- ----------------------------------Start-------------------------------------------------------- -->

						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Royalty Submission :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_royalty_number }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Options :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_option_number }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Territory :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_territory_number }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Crop :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_crop_number }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Technology :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_technology_number }</span>
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Report :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_report_number }</span>
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Milestone :</label>
							</div>
							<div class="col-md-10">
								<span>${executedContractDetails.exec_milestone_number }</span>
							</div>
						</div>
						
<!-- ----------------------------------Stop----------------------------------------------------------- --> --%>
							</table>
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
</div>

<script type="text/javascript">

/* function demoFromHTML() {
	var pdf = new jsPDF('p', 'pt', 'letter');

	// source can be HTML-formatted string, or a reference
	// to an actual DOM element from which the text will be scraped.
	source = $('#demo')[0];

	// we support special element handlers. Register them with jQuery-style 
	// ID selector for either ID or node name. ("#iAmID", "div", "span" etc.)
	// There is no support for any other type of selectors 
	// (class, of compound) at this time.
	specialElementHandlers = {
		// element with id of "bypass" - jQuery style selector
		'#bypassme' : function(element, renderer) {
			// true = "handled elsewhere, bypass text extraction"
			return true
		}
	};
	margins = {
		top : 130,
		bottom : 70,
		left : 40,
		width : 500
	};
	// all coords and widths are in jsPDF instance's declared units
	// 'inches' in this case
	pdf.fromHTML(source, // HTML string or DOM elem ref.
	margins.left, // x coord
	margins.top, { // y coord
		'width' : margins.width, // max width of content on PDF
		'elementHandlers' : specialElementHandlers
	},

	function(dispose) {
		// dispose: object with X, Y of the last line add to the PDF 
		//          this allow the insertion of new lines after html
		pdf.save('Debit_Notice.pdf');
	},  margins);
} */


$("#exportPdf").click(function(){
	//alert("hi:" + document.getElementById("example1"));
	var doc1 = new jsPDF('p', 'pt', 'a4');
	var res1 = doc1.autoTableHtmlToJson(document.getElementById("example1"));
	doc1.autoTable(res1.columns, res1.data, {
	    startY: 35,
	    styles: {
	      overflow: 'linebreak',
	      fontSize: 9,
	    },
	    columnStyles: {
	      1: {columnWidth: 'auto'}
	    }
	  });		
	doc1.setProperties({
	        title: 'Contract Details',
	        subject: 'Details',
	        author: 'Lexcare',
	        keywords: 'lexcare Litigation tool',
	        creator: 'Litigation Management Tool'
	        	
	    });

	doc1.cellInitialize(); 
	doc1.save('ContractSynopsis.pdf');	 
	//doc1.output('dataurlnewwindow');	

	});

 function Export2Doc(element,filename =''){
    var preHtml = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word' xmlns='http://www.w3.org/TR/REC-html40'><head><meta charset='utf-8'><title>Export HTML To Doc</title></head><body>";
    var postHtml = "</body></html>";
    var html = preHtml+document.getElementById(element).innerHTML+postHtml;

    var blob = new Blob(['\ufeff', html], {
        type: 'application/msword'
    });
    
    // Specify link url
    var url = 'data:application/vnd.ms-word;charset=utf-8,' + encodeURIComponent(html);
    
    // Specify file name
    filename = filename?filename+'.doc':'ContractSynopsis.doc';
    
    // Create download link element
    var downloadLink = document.createElement("a");

    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob ){
        navigator.msSaveOrOpenBlob(blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = url;
        
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
    
    document.body.removeChild(downloadLink);
} 

</script>
<script type="text/javascript">
$(document).ready(function(){
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});

var filecount = 1;
function addFileInputLitigation(i){
	$('#fileAddingForm'+i)
	.append(
			"<div id='div"+filecount+"' class='col-md-12'>"
			+ "<div class='form-group row'>"
			+ "<label class='col-md-3 form-control-label'>Select Document</label>"
			+ "<div class='col-md-4'>"
			+ "<input type='file' required='required' id='executed_contract_documents' name='executed_contract_documents' class='file-loading' />"
			+ "</div> <a class='col-md-1' href='#' onclick='deleteRowLitiRelated("+filecount+ ");'><i class='glyphicon glyphicon-remove-circle uploadClose'></i></a></div>"
			/* + "<div class='form-group row'>"
			+ "<label class='col-md-3 form-control-label'>Comments</label>"
			+ "	+ "<div class='col-md-4'><input id='doc_comments' name='doc_comments' class='form-control' placeholder='Enter Comments'/></div></div> */
			+ "</div>"
			+ "<div class='col-md-2'></div>"
			+ "<div  style='padding:5px;margin:5px; text-align:center;' class='col-md-12 litigation_buttons' id='divButton"+filecount+"'>"
			+ "<input type='submit' class='btn btn-myPrimary' value='Submit' /></div>");
	filecount++;
	$("#addLitigationDocuments").hide();
}
function deleteRowLitiRelated(filecount) {
	$("#div" + filecount).remove();
	$("#divButton" + filecount).remove();
	$("#addLitigationDocuments").show();

}

$("form#addLitigationDocumentForm").submit(function (event) {

	//disable the default form submission
	event.preventDefault();
	//grab all form data  
	var formData = new FormData($(this)[0]);
	// formData.append( 'file', $( '#file' )[0].files[0] );

	$.ajax({
		url: "./addExecContractDocument",
		type: 'POST',
		data: formData,
		async: false,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {


			getAllExecutedContractDocumentForAjax(data);

		},
		error: function(){
			alert("error in ajax form submission");
		}
	});

	return false;
});



function getAllExecutedContractDocumentForAjax(data){
	items = {};
	items['exec_contract_id'] = data;
	var jsonString = JSON.stringify(items);
//alert("hiiiii");
	$.ajax({
		type : "post",
		url : "./getAllExecutedContractDocumentForAjax",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(documentsList) {
			var htmlOutputReferenceRelated = '';
			if(documentsList != ""){
				var documentJson = $.parseJSON(documentsList);
				//console.log("This is length of document json:"+documentJson.length);
				//console.log(documentJson[2]);
				var k=1;
				for(var i=0 ; i < documentJson.length ; i++){
					var innerObj = documentJson[i];

					// if(innerObj["ldoc_document_type"] == "LitigationReference"){
							htmlOutputReferenceRelated += "<tr id='row_"+innerObj["eldoc_id"]+"'><td>"+ k +"</td>";
							htmlOutputReferenceRelated += "<td><a href='./downloadExecutedContractDoc?exec_doc_id="+innerObj["eldoc_id"]+"'>"+ innerObj["eldoc_original_file_name"] +"</a></td>";
							//htmlOutputReferenceRelated += "<td>"+innerObj["eldoc_document_type"] +"</td>";
							htmlOutputReferenceRelated += "<td class='delete'><button type='button' value='"+ innerObj["eldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
							k++;
						//}
				}
			}else {
				htmlOutput += "<tr><td colspan=3 style='text-align:center'>No Result Found</td></tr>";
			}

			
			$("#executed_id").html(htmlOutputReferenceRelated);
			$('#fileAddingForm1').html("");
			$(".addLitigationDocument").show();
			$('#fileAddingReferenceForm').html("");
			$("#addLitigationDocumentReference").show();
			filecount =1;

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
}

</script>

<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>