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



<div class="page_container">
<!--heading text-->

<div class="col-md-12">
<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">List Queries</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addQuery" class="btn btn-primary">Add Query</a></center>
				</div>
			</div>
		
	<div style="clear:both"></div>

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
								<select class="form-control" name="quer_entity_id" id="quer_entity_id">
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
								<select class="form-control" id="quer_unit_id" name="quer_unit_id">
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
								<select class="form-control" id="quer_function_id" name="quer_function_id">
									  <option value="0">--Select--</option>
							     </select>

							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Assigned To :</label>
							<div class="col-sm-8">
								<select class="form-control" name="quer_assigned_to" id="quer_search_assigned_to">
									  <option value="0">--Select--</option>
									  <%-- <c:forEach items="${allUser}" var="users">
										<option value="${users.user_id}">${users.user_first_name}
											${users.user_last_name}</option>
									  </c:forEach> --%>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1"> Query date from :</label>
							<div class="col-sm-8">
								<div class="input-group date" id="from_date_div" data-date-format="MM">
									<input id="from_date" class="form-control" type="text" readonly
										name="from_date" value="" /> <span class="input-group-addon"><i
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
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Query Id</th>
                <th>Entity</th>
                <th>Unit</th>
                <th>Function</th>
                <th>Query</th>
                <th>Assigned To</th>
                <th>Expected Reply Date</th>
                <th>Status</th>
                <th>Action</th>
               <!--  <th>Update</th> -->
                <!-- <th>Reply</th> --> 
            </tr>
        </thead>
       
        <tbody id="searchResult">
        <c:forEach items="${listQuery}" var="query" varStatus="loop">
        <c:if test="${query.quer_status =='Draft' && query.quer_added_by == sessionScope.sess_user_id}">
               <tr id="row_${query.quer_id}">
						<td>${loop.index+1}</td>
						<td>${query.quer_entity}</td>
						<td>${query.quer_unit}</td>
						<td>${query.quer_function}</td>
						<td>${query.quer_query}</td>
						<td>${query.quer_assigned_to_name}</td>
						<td>${query.quer_reply_date}</td>
						<td>${query.quer_status}</td>
						<td>
						<c:choose>
								<c:when	test="${sessionScope.sess_user_role==1 || query.quer_added_by == sessionScope.sess_user_id}">
											<a href="./editQuery?query_id=${query.quer_id}">
												<button type="button" onclick="" name='submit'
													value="submit" class="btn btn-primary btn-block"
													style="margin: 2px">
													<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
												</button>
											</a>
											<button type="button" value="${query.quer_id}"
												name='delete_query' class="btn btn-danger btn-block"
												style="margin-bottom: 4px;">
												<i class='fa fa-trash'></i>&nbsp;Delete
											</button>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose> <a href="queryDetails?query_id=${query.quer_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary btn-block" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Update
										</button></a>
								</td>
				</tr>
				</c:if>
				
				<c:if test="${query.quer_status !='Draft' }">
               <tr id="row_${query.quer_id}">
						<td>${loop.index+1}</td>
						<td>${query.quer_entity}</td>
						<td>${query.quer_unit}</td>
						<td>${query.quer_function}</td>
						<td>${query.quer_query}</td>
						<td>${query.quer_assigned_to_name}</td>
						<td>${query.quer_reply_date}</td>
						<td>${query.quer_status}</td>
						<td>
						<c:choose>
										<c:when
											test="${sessionScope.sess_user_role==1 || query.quer_added_by == sessionScope.sess_user_id}">
											<a href="./editQuery?query_id=${query.quer_id}">
												<button type="button" onclick="" name='submit'
													value="submit" class="btn btn-primary btn-block"
													style="margin: 2px">
													<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
												</button>
											</a>
											<button type="button" value="${query.quer_id}"
												name='delete_query' class="btn btn-danger btn-block"
												style="margin-bottom: 4px;">
												<i class='fa fa-trash'></i>&nbsp;Delete
											</button>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose> <a href="queryDetails?query_id=${query.quer_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary btn-block" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Update
										</button></a>
								</td>
				</tr>
				</c:if>
				
        </c:forEach>
        </tbody>
    </table>
			
			</div>
	
</div>
</div>
<script>
$(document).ready(function(){
	
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});
</script>
<script src="appJs/Query/query_validation.js"></script> 

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>