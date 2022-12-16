<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">


<div class="page_cont_padd">
	<div class="page_container">
		<div class="col-md-12">
			<div class="col-md-10">
				<div class="header_button">

					<h2 style="color: #054eff; font-size: 24px; float: left;">Amendment
						Contract List</h2>
					<a href="listExecutedContract"><img
						src="images/DashboardIcons/backold.png" class="backButton"
						width="100px;"></a>
				</div>
			</div>

			
				
					<div class="col-md-2">
						<center>
							<a
								href="./addAmendmentContract?exec_id=<%=request.getParameter("exec_id")%>"><button
									type="button" class="btn btn-primary" style="margin: 2px">
									Create New</button></a>
						</center>

					</div>
				
			
		</div>



		<div class="table_data1">
			<div class="col-md-12" style="margin-bottom: 10px;">
				<a href="#" class="list-group-item col-md-2 btn btn-success"
					onClick="$('#AmendmentList').tableExport({type:'excel',escape:'false',ignoreColumn:[8]});"><img
					src='images/reportIcons/xls.png' width="22" />Export XLS</a>

			</div>
			<div class="container" style="overflow-x: scroll;">
				<div class="col-md-12">
					<p>
						<b>Total Records : </b><label id="count"></label>
					</p>
				</div>
				<table id="AmendmentList" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr style="background: #054eff; color: #fff;">

							<th>CR.No</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Person Responsible</th>
							<th>Party</th>
							<th>clauses</th>
							<th>Term</th>
							<th>Status</th>
							<th>Document</th>
							<th>Action</th>
						</tr>
					</thead>

					<tbody id="searchResult">


						<c:forEach items="${allAmendmentContract}" var="amendmentcontract"
							varStatus="amendmentLoop">
							<tr id="row_${amendmentcontract.amend_contract_id}">
								<td>${amendmentLoop.index+1 }</td>
								<td>${amendmentcontract.ref_amend_contract_start_date }</td>
								<td>${amendmentcontract.ref_amend_contract_end_date }</td>
								<td>${amendmentcontract.ref_amend_Fresponsible_person }</td>
								<td><c:set var="list" scope="page" value="0">
									</c:set> <c:forEach items="${amendmentcontract.ref_cont_parties }"
										var="type">
										<c:set var="list" value="${list+1}"></c:set>
									${list}) ${type.cont_party_name}<br>
									</c:forEach></td>
								<td>${amendmentcontract.amend_contract_clauses }</td>
								<td>${amendmentcontract.amend_contract_term }</td>


								<td>${amendmentcontract.amend_contract_status }</td>

								<td><c:choose>
										<c:when test="${fn:length(amendmentcontract.amend_doc)==0}">
							  									 NA
														</c:when>
										<c:otherwise>
											<c:forEach items="${amendmentcontract.amend_doc}" var="doc">
												<a
													href="./downloadExecutedContractDoc?exec_doc_id=${doc.exec_doc_id}">${doc.exec_original_file_name}</a>
												<br>
											</c:forEach>
										</c:otherwise>
									</c:choose></td>

								<td>
											<a
												href="./editAmendmentContract?amend_contract_id=${amendmentcontract.amend_contract_id}"><button
													type="button" onclick="" name='submit' value="submit"
													class="btn btn-primary">
													<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
												</button></a>
												<c:choose>
										<c:when test="${sessionScope.sess_user_role==1}">
											<button type="button"
												value="${amendmentcontract.amend_contract_id}"
												name='delete_Amendment_Contract'
												id='delete_Amendment_Contract' class="btn btn-danger">
												<i class='fa fa-trash'></i>&nbsp;Delete
											</button>
										</c:when>
										<c:otherwise>
									No Access
										</c:otherwise>
									</c:choose></td>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script>
	//Delete Amendment Contract
	$(document)
			.ready(
					function() {

						$(document)
								.on(
										"click",
										"button[name='delete_Amendment_Contract']",
										function() {

											var amend_contract_id = $(this)
													.val();
											items = {};
											items["amend_contract_id"] = amend_contract_id;
											// alert("contract id is: "+amend_contract_id);
											var jsonString = JSON
													.stringify(items);

											if (amend_contract_id > 0) {
												bootbox
														.confirm(
																"Are you sure you want to delete?",
																function(result) {
																	if (result == true) {
																		$
																				.ajax({
																					type : "post",
																					url : "./deleteAmendmentContract",
																					contentType : "text/html",
																					dataType : "html",
																					data : jsonString,
																					cache : false,
																					success : function(
																							deleteCount) {
																						if (deleteCount == 1) {
																							$(
																									"#dialogBox .modal-header")
																									.css(
																											"background-color",
																											"#097a09");
																							$(
																									'#dialogBox .modal-title')
																									.html(
																											"<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
																							$(
																									'#dialogBox .modal-body')
																									.html(
																											"<p><strong> Amendment Contract deleted successfully!</strong></p>");
																							$(
																									"#dialogBox")
																									.modal(
																											'show');
																							$(
																									'table#AmendmentList tr#row_'
																											+ amend_contract_id)
																									.remove();
																							document
																									.getElementById('count').innerHTML = ($('#ContractList >tbody >tr').length);
																							//window.location.reload(true);
																						} else {
																							$(
																									'#dialogBox .modal-body')
																									.html(
																											"<p><strong>Please try again!</strong></p>");
																							$(
																									"#dialogBox")
																									.modal(
																											'show');
																						}

																					},

																					error : function(
																							xhr,
																							ajaxOptions,
																							thrownError) {
																						$(
																								'#errors')
																								.html(
																										"There is error:"
																												+ thrownError);
																					}
																				});
																	}
																});
											}
										});

					});
</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>