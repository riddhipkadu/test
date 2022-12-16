<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">

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
					<h2 style="color: #032BEC; font-size: 24px; float: left;">Contracts
						for Execution</h2>
					<!-- <a href="./ShowSubTaskMappingPanel"><img
						src="images/DashboardIcons/backold.png" class="backButton"
						width="100px;"></a> -->

				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addContractForExecution" class="btn btn-primary">Initiate
						New Contract</a>
				</center>
			</div>
		</div>


		<div style="clear: both"></div>

		<sf:form role="form" class="form-horizontal"  method="post">
				<div class="f_form_content">
					<h2>Search</h2>
					<div class="col-md-12">
					<input type="hidden" id="role" value="${sessionScope.sess_user_role}">
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_orga_id" class="control-label col-sm-4"
								for="sel1">Entity/Company Name :</label>
							<div class="col-sm-8">
								<select class="form-control" id="pre_orga_id">
									<c:forEach items="${allOrganizations}" var="orga">
								<option value="${orga.key}">${orga.value}</option>
								</c:forEach>
								</select><i class="asterisk_input"></i>

							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Unit/Vertical :</label>
							<div class="col-sm-8">
								<select class="form-control" id="pre_loca_id"
									name="pre_loca_id">
									<option value="0">--Select--</option>
									
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="form-group">
							<label for="user_dept_id" class="control-label col-sm-4" for="sel1">Function/Location /Department :</label>
							<div class="col-sm-8">
								<select class="form-control" id="pre_dept_id"
									name="pre_dept_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
                   </div>
                   <div class="col-md-12">
					 <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Document Type:</label>
							<div class="col-sm-8">
								<select class="form-control" id="pre_contract_type" class="form-control">
								    <option value="0" selected="selected">--Select--</option>
									 <c:forEach items="${contract_types }" var="contra">
									    <option value="${contra[0]}">${contra[1]}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div> 
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Case Status:</label>
							<div class="col-sm-8">
								<select class="form-control" id="pre_status">
								      <option value="NA">--Select--</option>
									  <option value="Pending">Pending</option>
			   				          <option value="In Progress">In Progress</option>
			   				          <option value="Awaiting Feedback">Awaiting Feedback</option>
			   				          <option value="Awaiting Approval">Awaiting Approval</option>
			   				          <option value="Sent for Review">Sent for Review</option>
			   				          <option value="Contract Cancelled">Contract Cancelled</option>
			   				          <option value="Contract Suspended">Contract Suspended</option>
			   				          <option value="Others">Others</option>
									<!-- <option value="Closed">Closed</option>
									<option value="Pending">Pending</option>
									<option value="Sent_For_Review">Sent For Review</option> -->	
									
							    </select>
							</div>
						</div>
					</div> 
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Criticality :</label>
							<div class="col-sm-8">
								<select class="form-control" id=cont_criticality>
								      <option value="NA">--Select--</option>
									  <option value="High">High</option>
			   				          <option value="Medium">Medium</option>
			   				          <option value="Low">Low</option>
							    </select>
							</div>
						</div>
					</div> 
					
					</div>
					<div class="col-md-12">
                    <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Targeted Date - From:</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="datepicker1" name="pre_tergeted_date_from" readonly type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">To:</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="datepicker2" name="pre_tergeted_date_to" readonly type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Assigned to :</label>
							<div class="col-sm-8">
								<select id="pre_responsible_user_id" name="pre_responsible_user_id" 
										class="form-control">
										<option value="0">--Select--</option>
										
									</select>
							</div>
						</div>
					</div>
					
					</div>
					
					<div class="col-md-12 litigation_buttons">
					<br>
					<center>
						<button type="button" id="searchContractForExecution" class="btn btn-myPrimary">Search</button>
						
						<!-- <button type="button" name="back" id="back"
							class="btn btn-myDefault"
							onclick="window.location.href ='./dashboard' ">Back</button> -->
						<div id="noFound"> </div>
					</center>
				</div>
				<div id="loader" style="display: none;">
					<center>
							<img src="./images/Loader/WaitLoader.gif" width="80px" height="85px" />
					</center>
				</div>
				</div>
             </sf:form>

		<div style="clear: both"></div>

		<div class="table_data1">
		  <div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#ContractList').tableExport({type:'excel',escape:'false',ignoreColumn:[12]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div>
			<div class="container" style="overflow-x: scroll;">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div>
				<table id="ContractList" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr style="background: #0B6EC3; color: #fff;">

							<th>Contract Id</th>
							<th>Entity Name</th>
							<th>Unit Name</th>
							<th>Function Name</th>
							<th>Name of agreement</th>
							<th>Requested by</th>
							<!-- <th>Requested on</th> -->
							<th>Expected date of closure</th>
							<th>Party</th>
							<!-- <th>Party Two</th> -->
							<th>Assigned to</th>
							<th>Targeted date</th>
							<th>Status</th>
							<th>Action</th>
							<%-- <c:choose>
								<c:when test="${sessionScope.sess_user_role==1}">
									<th>Edit</th>
								</c:when>
							</c:choose> 
							<th>Update</th> --%>

						</tr>
					</thead>

					<tbody id="searchResult">


						<c:forEach items="${allContractDetails}" var="contract" varStatus="contractLoop">
						<tr id="row_${contract.cont_id}">
						<td>${contractLoop.index+1 }</td>
						<td>${contract.orga_name }</td>
						<td>${contract.loca_name }</td>
						<td>${contract.dept_name }</td>
						<td>${contract.cont_agreement_name }</td>
						<td>${contract.user_requested_by_fullname }</td>
						<%-- <td>${contract.cont_requested_date }</td> --%>
						<td>${contract.cont_expected_date }</td>
						<td>
						 <c:set var="list" scope="page" value="0"> </c:set>
						   <c:forEach items="${contract.cont_parties }" var="type">
						     <c:set var="list" value="${list+1}"></c:set>
									${list}) ${type.cont_party_name}<br>									    
						    </c:forEach>
						</td>
					<%-- 	<td>${contract.cont_party_two }</td> --%>
						<td>${contract.user_responsible_fullname }</td>
						<td>${contract.cont_targetted_date }</td>
						<td>${contract.cont_status }</td>
								<%-- <c:choose>
									<c:when test="${sessionScope.sess_user_role==1}">
										<td class="edit"><a
											href="./editContract?cont_id=${contract.cont_id }"><button
													type="button" onclick="" name="submit" value="submit"
													class="btn btn-primary">
													<i class="fa fa-pencil-square-o"></i>&nbsp;Edit
												</button></a></td>
									</c:when>
								</c:choose> --%>
								<td class="edit"> 
								<c:choose>
									<c:when test="${contract.cont_status != 'Closed/Executed' && contract.cont_added_by == sessionScope.sess_user_id || sessionScope.sess_user_role==1}">    
									    <a href="./editContract?cont_id=${contract.cont_id }"><button
												type="button" onclick="" name="submit" value="submit"
												class="btn btn-primary btn-block" style="margin-bottom: 4px;">
												<i class="fa fa-pencil-square-o"></i>&nbsp;Edit
											</button></a> 
										<button type="button" value="${contract.cont_id}"
											 name='delete_pre_contract' 
											class="btn btn-danger btn-block" style="margin-bottom: 4px;">
											<i class='fa fa-trash'></i>&nbsp;Delete
										</button>
									</c:when>
										<c:otherwise>
										 
										</c:otherwise>
								</c:choose>
									<a	href="./updateTab?cont_id=${contract.cont_id }"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary btn-block">

											<i class="fa fa-pencil-square-o"></i>&nbsp;Update
										</button></a>
								</td>
						</tr>
						
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#pre_contract_type").multiselect({
		buttonWidth: '220px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    onChange: function(option, checked) {
                var values = [];
                $('#pre_contract_type option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#pre_contract_type').multiselect('deselect', values);
            }
        
	});
	
	document.getElementById('count').innerHTML = ($('#ContractList >tbody >tr').length);
	
});


$("#pre_dept_id").change(function(){
	
	var user_orga_id = $("#pre_orga_id").val();
	var user_loca_id = $("#pre_loca_id").val();
	var user_dept_id = $("#pre_dept_id").val();
	
	if (user_dept_id > 0 && user_loca_id > 0 && user_dept_id > 0) {
		
		var data = {};
		data['tmap_orga_id'] = user_orga_id;
		data['tmap_loca_id'] = user_loca_id;
		data['tmap_dept_id'] = user_dept_id;
		var jsonString = JSON.stringify(data);
		
				$.ajax({
					type : "post",
					url : "./getAllUsersByOrganizationLocationDepartment",
					contentType : "application/json",
					dataType : "json",
					data : jsonString,
					cache : false,
					success : function(userList) {
						var data ="";
						data +="<option value= 0>--Select--</option>";
						
						$.each(userList,function(key,value){
							//console.log("In loop");
							data += "<option value="+value['user_id']+">"+value['user_name']+"</option>";
						});
						$("#pre_responsible_user_id").html(data);
						//$("#cont_requested_by_user_id").html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
	}
	else {
		$('#pre_responsible_user_id').html('<option value="0">--Select--</option>');
		//$('#cont_requested_by_user_id').html('<option value="0">--Select--</option>');
	}
});
</script>
<script src="js/bootstrap-multiselect.js"></script>
<script src="appJs/PreExecutionContracts/pre_executionContracts.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
