<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<div class="container">
			<!-- Header -->
			<div class="row">
				<div class="col-md-12">
					<div class="header_button">
						<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
						<h2 style="color: #a72f14; font-size: 24px; float: left;">
							Query Details</h2>
						<a href="./listQuery"><img
							src="images/DashboardIcons/backold.png" class="backButton"
							width="100px;"></a>
					</div>
				</div>
			</div>
			<!-- End Header -->

			<!-- Body -->
			<div class="task_tabs">
				<ul id="myTab" class="nav nav-tabs mynav-tabs">
					
					<li class="active"><a href="#QueryDetails" data-toggle="tab" >Query Details </a></li>
					<li><a href="#QueryHistory" data-toggle="tab" id="queryHistory">Query History</a></li>
					<!-- <li><a href="#Document" data-toggle="tab">Documents</a></li>
					<li><a href="#Completion" data-toggle="tab">Completion</a></li>
					<li><a href="" data-toggle="tab">Counsel Fees</a></li> -->
				</ul>

				<div id="myTabContent" class=" container tab-content">
			
					 <div class="tab-pane fade active in" id="QueryDetails"
						style="padding: 10px;">
						<table id="example" class="table table-striped table-bordered"
							style="width: 1000px;" cellspacing="0">
							 <tr>
								<td colspan="2"
									style="text-align: center; background-color: grey; color: white; font-size: 23px;"><strong>Query Details</strong>
									<!-- <div style="float: right;">
										<input type="button" name="export" class="btn btn-danger"
											value="Export In To Word">
									</div> --></td>
							</tr>
							
							<tr style="display: none">
								<td style="width: 200px;"><strong>Query Id :</strong></td>
								<td>${query_details_by_id.quer_id }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Entity/Company Name :</strong></td>
								<td>${query_details_by_id.quer_entity }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Unit/Vertical :</strong></td>
								<td>${query_details_by_id.quer_unit }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Function/Location/Department:</strong></td>
								<td>${query_details_by_id.quer_function }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>From :</strong></td>
								<td>${query_details_by_id.quer_from_name }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Query :</strong></td>
								<td>${query_details_by_id.quer_query }</td>
							</tr><tr>
								<td style="width: 200px;"><strong>Assigned to :</strong></td>
								<td>${query_details_by_id.quer_assigned_to_name }</td>
							</tr><tr>
								<td style="width: 200px;"><strong>Query Date :</strong></td>
								<td>${query_details_by_id.quer_query_date }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Expected Reply Date :</strong></td>
								<td>${query_details_by_id.quer_reply_date }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Reminder Date :</strong></td>
								<td>${query_details_by_id.quer_reminder_date }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Criticality :</strong></td>
								<td>${query_details_by_id.quer_criticality }</td>
							</tr>
							<tr>
								<td style="width: 200px;"><strong>Turnaround time :</strong></td>
								<td>${query_details_by_id.quer_turnaround_time_name }</td>
							</tr>
							<%-- <tr>
								<td style="width: 200px;"><strong>Document :</strong></td>
								<td>${query_details_by_id.quer_doc }</td>
							</tr> --%>
						</table>
					</div>
					 
					<div class="tab-pane fade" id="QueryHistory">
					 <div class="col-md-12" style="margin-top: 20px;">
					 <div class="col-md-12" style="margin-bottom: 10px;">
					 <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example1').tableExport({type:'excel',escape:'false',ignoreColumn:[7,8]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
					</div>
						<c:if test="${query_details_by_id.quer_status != 'Draft'}">
						<div class="col-md-12" style="text-align: right;">

							<a href="./replyToQuery?query_id=${query_details_by_id.quer_id }"><button
									type="button" onclick="" name='submit' value="submit"
									class="btn btn-primary" style="margin :8px">

									<i class='fa fa-pencil-square-o'></i>&nbsp;Update
								</button></a>
						</div>
						</c:if>
 						<div id="col-md-12">
						<table id="example1" class="table table-striped table-bordered"
							width="100%" cellspacing="0">
							
							<thead>
								<tr style="background: #a72f14; color: #fff;">

									<th>Sr.No</th>
									<!-- <th>Action performed by</th> -->
									<th>Action assigned to</th>
									<th>Status</th>
									<th>Received/Replied on date</th>
									<th>Action to be performed</th>
									<th>Action to be performed by</th>
									<th>Comments</th>
									<th>Document</th>
								    <th>Edit</th>

								</tr>
							</thead>

							<tbody>
							<c:forEach items="${queryHistory}" var="queryhst" varStatus="queryhstLoop">
							<c:if test="${queryhst.query_hst_status =='Save As Draft' && sessionScope.sess_user_id == queryhst.query_hst_added_by }">
							<tr id="row_${queryhst.query_hst_id}">
							<td>${queryhstLoop.index+1 }</td>
							<%-- <td>${queryhst.query_hst_replied_by}</td> --%>
							<td>${queryhst.query_hst_action_assigned_to }</td>
							<td>
								<c:choose>
								<c:when test="${queryhst.query_hst_status=='Others' }">
								${queryhst.query_hst_others}
								</c:when>
								<c:otherwise>
								${queryhst.query_hst_status}
								</c:otherwise>
								</c:choose>
							</td>
							<td>${queryhst.query_hst_type} : ${queryhst.query_hst_replied_date }</td>
							<td>
								<c:choose>
								<c:when test="${queryhst.query_hst_action_tobe_performed=='Others' }">
								${queryhst.query_hst_action_performed_others}
								</c:when>
								<c:otherwise>
								${queryhst.query_hst_action_tobe_performed }
								</c:otherwise>
								</c:choose>
							</td>
							
							<td>
								<c:choose>
								<c:when test="${queryhst.query_hst_action_tobe_performed_by=='Others' }">
								${queryhst.query_hst_action_performed_by_others}
								</c:when>
								<c:otherwise>
								${queryhst.query_hst_action_tobe_performed_by }
								</c:otherwise>
								</c:choose>
							</td>
							
							<td>${queryhst.query_hst_comments }</td>
							
							<td>
							<c:choose>
							 <c:when test="${fn:length(queryhst.hst_doc)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:forEach items="${queryhst.hst_doc}" var="doc">
							<a href="./downloadQueryDocument?quer_doc_id= ${doc.quer_doc_id }">
							   ${doc.quer_doc_original_file_name}</a>
							  </c:forEach>
							</c:otherwise>
							</c:choose> 
                             </td>
							
						    <td><a href="editQueryHistory?query_hst_id=${queryhst.query_hst_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary btn-block" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Edit</button></a>
											<button
											type="button" name="deleteQueryHst" value="${queryhst.query_hst_id}"
											class="btn btn-danger btn-block" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Delete</button>
							</td>
							</tr>
							</c:if>
							
							<c:if test="${queryhst.query_hst_status !='Save As Draft'}">
							<tr id="row_${queryhst.query_hst_id}">
							<td>${queryhstLoop.index+1 }</td>
							<%-- <td>${queryhst.query_hst_replied_by}</td> --%>
							<td>${queryhst.query_hst_action_assigned_to }</td>
							<td>
								<c:choose>
								<c:when test="${queryhst.query_hst_status=='Others' }">
								${queryhst.query_hst_others}
								</c:when>
								<c:otherwise>
								${queryhst.query_hst_status}
								</c:otherwise>
								</c:choose>
							</td>
							<td>${queryhst.query_hst_type} :${queryhst.query_hst_replied_date }</td>
							<td>
								<c:choose>
								<c:when test="${queryhst.query_hst_action_tobe_performed=='Others' }">
								${queryhst.query_hst_action_performed_others}
								</c:when>
								<c:otherwise>
								${queryhst.query_hst_action_tobe_performed }
								</c:otherwise>
								</c:choose>
							</td>
							
							<td>
								<c:choose>
								<c:when test="${queryhst.query_hst_action_tobe_performed_by=='Others' }">
								${queryhst.query_hst_action_performed_by_others}
								</c:when>
								<c:otherwise>
								${queryhst.query_hst_action_tobe_performed_by }
								</c:otherwise>
								</c:choose>
							</td>
							
							<td>${queryhst.query_hst_comments }</td>
							
							<td>
							<c:choose>
							 <c:when test="${fn:length(queryhst.hst_doc)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:forEach items="${queryhst.hst_doc}" var="doc">
							<a href="./downloadQueryDocument?quer_doc_id= ${doc.quer_doc_id }">
							   ${doc.quer_doc_original_file_name}</a>
							  </c:forEach>
							</c:otherwise>
							</c:choose> 
                             </td>
							
						    <td><a href="editQueryHistory?query_hst_id=${queryhst.query_hst_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary btn-block" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Edit</button></a>
								<button	type="button" name="deleteQueryHst" value="${queryhst.query_hst_id}"
											class="btn btn-danger btn-block" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Delete</button>			
							</td>
							</tr>
							</c:if>
							
							</c:forEach>
							</tbody>
						</table>
						</div>
				</div>
					</div>
					<div class="modal fade" id="log_model_query" role="dialog" >
				<div class="modal-dialog" style="width: 80%;">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #13a430;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								Logs
							</h4>
						</div>
						<div class="modal-body">
		<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#0B6EC3;color:#fff;">
                
                					<th>Sr.No</th>
									<!-- <th>Action performed by</th> -->
									<th>Action assigned to</th>
									<th>Status</th>
									<th>Replied on date</th>
									<th>Action to be performed</th>
									<th>Action to be performed by</th>
									<th>Comments</th>
              					    <th>Log Status</th>
            </tr>
        </thead>
       
        <tbody id="logResultQuery">
        </tbody>
        
        </table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
					
				</div>
			</div>
		</div>
	</div>
</div>

<script src="appJs/Query/query_validation.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
