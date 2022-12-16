<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Action Item</h2>
					<a href="./listExecutedContract"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>

				</div>
				</div>
				<div class="col-md-2">
						<a href="./addActionItem?exec_id=<%=request.getParameter("exec_id")%>"><button
											type="button" class="btn btn-primary" style="margin :2px">Add Action Item
						</button></a>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	<form role="form" class="form-horizontal" method="post">
			
			<div class="f_form_content">
				<h2>Search</h2>

				<div class="col-md-12">
				
				<input type="hidden" id="role" value="${sessionScope.sess_user_role}">
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Frequency</label>
							<div class="col-sm-8">
								<select class="form-control" id="exec_frequency" >
									<option value="0">--Select--</option>
									<option value="Weekly">Weekly</option>
									<option value="Monthly">Monthly</option>
									<option value="Quarterly">Quarterly</option>
									<option value="Half yearly">Half Yearly</option>
									<option value="Yearly">Yearly</option>
								</select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Action Item due date- from:</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="exec_from_due_date" name="exec_from_due_date" readonly type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">To:</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="exec_to_due_date" name="exec_to_due_date" readonly type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 litigation_buttons">

					<center>
						<a href="javascript:searchAction(<%=request.getParameter("exec_id")%>)"><input type="button" id="btnSearch" value="Search" class="btn btn-myPrimary" /></a>
						<div id="noFound"></div>
					</center>

				</div>
				<div id="loader" style="display: none;">
					<center>
						<img src="./images/Loader/WaitLoader.gif" width="80px"
							height="85px" />
					</center>
				</div>
			</div>
			
		</form>
			
		
				<div class="table_data1">
				<div class="col-md-12">
					<div style="margin-bottom:10px;"><a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[7]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		 			</div>
		  		</div>
		<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div>
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Sr. No</th>
                <th>Action Item</th>
                <th>Responsible Person</th>
                <th>Frequency</th>
                <th>Due Date</th>
                <th>First alert prior day/s</th>
                <th>Second alert prior day/s</th>
                <th>Action</th>
				
            </tr>
        </thead>
       
        <tbody id="searchResult">
        
        <c:forEach items="${ActionItemsList}" var="actionItem" varStatus="loop"> 
        			<tr>
						<td><a href="./actionItemHistory?action_id=${actionItem.exec_action_id}" style="color: blue;">${loop.index+1}</a></td>
						<td>${actionItem.exec_action_item}</td>
						<td>${actionItem.exec_responsible_user_name}</td>
						<td>${actionItem.exec_frequency}</td>
						<td>${actionItem.exec_due_date}</td>
						<td>${actionItem.exec_first_alert_prior_days}</td>
						<td>${actionItem.exec_second_alert_prior_days}</td>
						<td>
						<c:choose>
						<c:when	test="${sessionScope.sess_user_role == 1 || actionItem.exec_added_by == sessionScope.sess_user_id}">
						<a href="./editActionItem?exec_id=${actionItem.exec_action_id}">
						    <button type="button" name='submit' value="submit" class="btn btn-primary" style="margin-bottom: 4px;">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
							</button></a>
							</c:when>
							<c:otherwise>
							No Access
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


<script src="appJs/ExecutedContracts/validateActionItem.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});
</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
