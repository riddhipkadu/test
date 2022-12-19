<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
					History</h2>
				<a href="./listActionItem?exec_id=${exec_id}"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>

		<!-- Tab Menu -->

		<div class="task_tabs">

			<ul id="myTab" class="nav nav-tabs mynav-tabs">
				<li class="active"><a href="#history" data-toggle="tab">Task History</a></li>
			</ul>

			<div id="myTabContent" class="tab-content">

				<div class="tab-pane fade active in" id="history">
					<div class="col-md-12" style="margin-top: 20px;">

						<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div>
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Sr. No</th>
                <th>Responsible Person</th>
                <th>Frequency</th>
                <th>Due Date</th>
                <th>First alert prior day/s</th>
                <th>Second alert prior day/s</th>
                <th>Status</th>
                <th>Completed date</th>
                <th>Completed by</th>
				<th>Activity</th>
            </tr>
        </thead>
       
        <tbody id="searchResult">
        
        <c:forEach items="${taskHistory}" var="taskHistory" varStatus="loop"> 
        			<tr>
						<td>${loop.index+1}</td>
						<td>${taskHistory.exec_responsible_user_name}</td>
						<td>${taskHistory.exec_frequency}</td>
						<td>${taskHistory.exec_due_date}</td>
						<td>${taskHistory.exec_first_alert_prior_days}</td>
						<td>${taskHistory.exec_second_alert_prior_days}</td>
						<td>${taskHistory.atrn_status}</td>
						<td>${taskHistory.atrn_completed_date}</td>
						<td>${taskHistory.atrn_completed_by}</td>
						<td>
						<c:choose>
						<c:when test="${taskHistory.atrn_status !='Completed'}">
						<a href="./addTaskCompletion?atrn_id=${taskHistory.atrn_id}">
						    <button type="button" name='submit' value="submit" class="btn btn-primary" style="margin-bottom: 4px;">Complete Task
							</button></a>
						</c:when>
						<c:otherwise>
						<a href="./addTaskCompletion?atrn_id=${taskHistory.atrn_id}">
						    <button type="button" name='submit' value="submit" class="btn btn-primary" style="margin-bottom: 4px;">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Update Complete Task
							</button></a>
						</c:otherwise>
						</c:choose>
						
						</td>
						
					</tr>
        </c:forEach>
        </tbody>
    </table>
			</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>
<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>