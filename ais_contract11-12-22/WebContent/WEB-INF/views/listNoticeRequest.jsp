<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Notice Calender</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Notice Request List</h2>
					<!-- <a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
</div>
				<div class="col-md-2">
					<center><a href="./addNoticeRequest" class="btn btn-primary">Generate Request</a></center>
				</div>
</div>
	<div style="clear:both"></div>
	<!--first form-->	
	<form role="form" class="form-horizontal" method="post" onsubmit="return validateForm();">
	<div class="f_form_content">
				<h2>Search</h2>
				<div class="col-md-12">
				<input type="hidden" id="role" value="${sessionScope.sess_user_role}">
				<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Entity :</label>
							<div class="col-sm-8">
								<select class="form-control" name="req_noti_entity_id" id="req_noti_entity_id">
									   <c:forEach items="${allOrganizations}" var="organization">
								          <option value="${organization.key}">${organization.value}</option>
								       </c:forEach>
							     </select><i class="asterisk_input"></i>
							</div>
						</div>
				</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Unit :</label>
							<div class="col-sm-8">
								<select class="form-control" id="req_noti_unit_id" name="req_noti_unit_id">
									  <option value="0">--Select--</option>
									   
							     </select>
							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_orga_id" class="control-label col-sm-4"
								for="sel1">Function:</label>
							<div class="col-sm-8">
								<select class="form-control" id="req_noti_function_id" name="req_noti_function_id">
									  <option value="0">--Select--</option>
							     </select>

							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Notice date from:</label>
							<div class="col-sm-8">
								<div class="input-group date" id="from_date_div" data-date-format="MM">
									<input id="from_date" class="form-control" type="text" readonly
										name="from_date"/> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">To :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="to_date_div" data-date-format="MM">
									<input id="to_date" class="form-control" type="text" readonly
										name="to_date" value="" /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
	
			</div>
				<div class="col-md-12 litigation_buttons">

					<center>
						<input type="button" id="btnSearch" value="Search" class="btn btn-myPrimary" />
						<div id="noFound"> </div>
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
					<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[8]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  			</div>

		<div class="container">
		<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
		</div> 
		
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#0B6EC3;color:#fff;">
                <th>Sr.No.</th>
                <th>Date (Notice/ Court order)</th>
                <th>Type</th>
                <th>Party(s)</th>
                <th>Description</th>
                <th>Document</th>
                <!-- <th>Edit</th> -->
                <th>Requested by</th>
                <th>Accept/Reject</th>
                <th>Action</th> 
            </tr>
        </thead>
       
        <tbody id="searchResult">
          <c:forEach items="${listNoticeRequest}" var="req" varStatus="loop">
          <c:if test="${req.req_noti_approval_status !='4'}">
					<tr>
						<td>${loop.index+1 }</td>
						<td>${req.req_noti_date_name}</td>
						<td>${req.req_noti_type_by_against}</td>
						<td>${req.req_noti_oppo_party }</td>
						<td>${req.req_noti_des }</td>
						<td><c:set var="doc" scope="page" value="0"/> 
						    <c:forEach items="${req.req_doc_list}" var="docs" >
						         <c:set var="doc" value="${doc+1}"/>
						         ${doc})<a href="./downloadRequestDocument?doc_id=${docs.req_doc_id }">
						          ${docs.req_doc_original_file_name}</a><br>
						     
						    </c:forEach> 
						</td>
						<td>${req.req_added_by_name} - ${req.req_entity_name}, ${req.req_unit_name}, ${req.req_function_name }</td>
						<c:choose>
							<c:when test="${(user_role_id == 1 || user_role_id == 5) && req.req_noti_approval_status=='0'}">
											<td id="appDis${req.req_noti_id}"><button type="button" style="margin:2px"
											onclick="getReason(${req.req_noti_id},'3')" name='submit'
											value="submit" class="btn btn-danger">
											<i class='fa fa-thumbs-down'></i>&nbsp;Reject</button>
											<a href="./addNoticeByAccept?id=${req.req_noti_id}">
								     		<button type="button" name='submit' value="submit" style="margin:2px" class="btn btn-success">
											<i class='fa fa-thumbs-up'></i>&nbsp;Accept</button></a>
											</td>
							</c:when>
							
							<c:when test="${(user_role_id == 4 || user_role_id == 6) && req.req_noti_approval_status=='0' }">
								
											<td id="appDis${req.req_noti_id}"><button type="button" style="margin:2px"
											onclick="getReason(${req.req_noti_id},'1')" name='submit'
											value="submit" class="btn btn-danger">
											<i class='fa fa-thumbs-down'></i>&nbsp;Reject</button>
											
											<a href="./addNoticeByAccept?id=${req.req_noti_id}">
											<button type="button" onclick="" name='submit' style="margin:2px"
											value="submit" class="btn btn-success">
											<i class='fa fa-thumbs-up'></i>&nbsp;Accept</button></a>
											</td>
							</c:when>
							
							<c:when test="${(user_role_id == 1 || user_role_id == 5) && req.req_noti_approval_status=='1'}">
								
											<td id="appDis${req.req_noti_id}">
											<label>Request Rejected by SPOC</label><br/>
											<button type="button" style="margin:2px"
											onclick="getReason(${req.req_noti_id},'3')" name='submit'
											value="submit" class="btn btn-danger">
											<i class='fa fa-thumbs-down'></i>&nbsp;Reject</button>
											<a href="./addNoticeByAccept?id=${req.req_noti_id}">
											<button type="button" onclick="" name='submit' style="margin:2px" value="submit" class="btn btn-success">
											<i class='fa fa-thumbs-up'></i>&nbsp;Accept</button></a>
											<a href="./listRejectionReason?relatedTo=notice&id=${req.req_noti_id}">
						    				<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">
											&nbsp;Reason
											</button></a></td>
							</c:when>
							
							<c:when test="${(req.req_noti_approval_status=='0' || req.req_noti_approval_status=='1') && user_role_id == 3 }">
								<td>Pending</td>							
							</c:when>
							
							<c:when test="${req.req_noti_approval_status == '3'}">
								 <td>Rejected</br>
								 <a href="./listRejectionReason?relatedTo=notice&id=${req.req_noti_id}">
						    				<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">
											&nbsp;Reason
											</button></a></td>
							</c:when>
							
							<c:when test="${(user_role_id == 4 || user_role_id == 6) && req.req_noti_approval_status == '1' }">
								 <td>Rejected by SPOC<br/>
								 <a href="./listRejectionReason?relatedTo=notice&id=${req.req_noti_id}">
						    				<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">
											&nbsp;Reason
											</button></a></td>
							</c:when>
							
							<c:otherwise>
                                 <td>Accepted</td>
							</c:otherwise>
						</c:choose>
						<td><input type="button" id="raisequery" onclick="raiseQuery(${req.req_noti_id},'notice')" value="Raise Query" class="btn btn-success">
						    <br>
						    <a href="./listRaisedQuery?relatedTo=notice&id=${req.req_noti_id}" style="margin:2px" class="btn btn-primary">List Queries</a>
						</td>
					</tr>
					</c:if>
					<c:if test="${req.req_noti_approval_status =='4' && req.req_added_by == sessionScope.sess_user_id}">
					<tr>
						<td>${loop.index+1 }</td>
						<td>${req.req_noti_date_name}</td>
						<td>${req.req_noti_type_by_against}</td>
						<td>${req.req_noti_oppo_party }</td>
						<td>${req.req_noti_des }</td>
						<td><c:set var="doc" scope="page" value="0"/> 
						    <c:forEach items="${req.req_doc_list}" var="docs" >
						         <c:set var="doc" value="${doc+1}"/>
						         ${doc})<a href="./downloadRequestDocument?doc_id=${docs.req_doc_id }">
						          ${docs.req_doc_original_file_name}</a><br>
						     
						    </c:forEach> </td>
						<td>${req.req_added_by_name} - ${req.req_entity_name}, ${req.req_unit_name}, ${req.req_function_name }</td>
						
						<td><label>Save for Later</label><br/>
					   <a href="./editNoticeRequest?id=${req.req_noti_id}">
											<button type="button" onclick="" name='submit' style="margin:2px"
											value="submit" class="btn btn-success">
											<i class='fa fa-thumbs-up'></i>&nbsp;Edit</button></a>
					   </td> 
					
					</tr>
					</c:if>
					
		  </c:forEach> 
        </tbody>
    </table>
		</div>

</div>
</div>
</div>

<script src="appJs/RequestValidate/notiRequestValidate.js"></script>
<script>
$(document).ready(function(){
	
	 document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});

</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
