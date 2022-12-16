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
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
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
					<h2 style="color: #054eff; font-size: 24px; float: left;">Executed
						Contracts</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addExecutedContract" class="btn btn-primary">Add New
						Executed Contract</a>
				</center>
			</div>
		</div>


		<div style="clear: both"></div>
		<!--first form-->
		<form role="form" class="form-horizontal" method="post">

			<div class="f_form_content">
				<h2>Search</h2>

				<div class="col-md-12">

					<input type="hidden" id="role"
						value="${sessionScope.sess_user_role}">
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Entity/Company Name:</label>
							<div class="col-sm-8">
								<select class="form-control" id="exec_contract_entity_id">
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
								for="sel1">Unit/Vertical :</label>
							<div class="col-sm-8">
								<select class="form-control" id="exec_contract_unit_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Function/Department:</label>
							<div class="col-sm-8">
								<select class="form-control" id="exec_contract_function_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_orga_id" class="control-label col-sm-4"
								for="sel1">Type/Name of Contract:</label>
							<div class="col-sm-8">
								<select class="form-control" id="exec_contract_type_id">
									<option value="0">--Select--</option>
									<c:forEach items="${allContractType}" var="contract">
										<option value="${contract.key}">${contract.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<!-- <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Division :</label>
							<div class="col-sm-8">
								<select id="exec_contract_division" Class="form-control">
									<option value="NA">--Select--</option>
									<option value="Animal_Feed">Animal Feed</option>
									<option value="Aqua_Feed">Aqua Feed</option>
									<option value="OMM">OMM</option>
									<option value="Oil_palm">Oil palm</option>
									<option value="Crop_Protection_Business">Crop Protection Business</option>
									<option value="RGC">RGC</option>
									<option value="Yummiez">Yummiez </option>
									<option value="Live Operation">Live Operation</option>
								</select>
							</div>
						</div>
					</div> -->
				</div>
				<div class="col-md-12 litigation_buttons">
					<center>
						<input type="button" id="btnSearch" value="Search"
							class="btn btn-myPrimary" />
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
				<!-- <div class="col-md-8" style="margin-bottom: 10px;">
					<a href="#" class="list-group-item col-md-2 btn btn-success" id="exportXls"
						onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[]});"><img
						src='images/reportIcons/xls.png' width="22" />Export XLS</a>

				</div> -->
				 <div class="col-md-12" style="margin-bottom:10px;"> <a href="#" id="exportXls" class="list-group-item col-md-2 btn btn-success">
				 <img src='images/reportIcons/xls.png' width="22" />Export XLS</a>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search ...." style="margin-left: 30px ; width:250px; height:40px;" title="Type in a name">
				  </div>
				

			<div class="container">
				<div class="col-md-12">
					<p>
						<b>Total Records : </b><label id="count"></label>
					</p>
				</div>
				<table id="example" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr id="row" style="background: #054eff; color: #fff;">

							<th>Sr. No</th>
							<th>Entity/Company Name</th>
							<th>Unit/Vertical</th>
							<th>Function/Location/ Department</th>
							<th>Type/Name of Contract</th>
							<th>Party</th>
							<th>Division</th>
							<th class="demo visi">Execution date</th>
							<th class="demo visi">Effective date</th>
							<th>Expiry Date</th>
							<th class="demo visi">Renewal date</th>
							<th class="demo visi">Term Period of the Agreement (In months)</th>
							<th class="demo visi">Lock-in-period</th>
							
							<th class="demo visi">Purpose of the Agreement </th>
							<th class="demo visi">Payment clause</th>
							<th class="demo visi">Termination Clause</th>
							<th class="demo visi">Indemnity Clause</th>
							<th class="demo visi">Consideration involved</th>
							<th class="demo visi">Signatories to the Agreement</th>
							<th class="demo visi">Status of the Agreement</th>
							<th class="demo visi">Renewal Status</th>
							
							<th class="demo visi">Any connected Amendment/Addendum Letter/renewal letter/ Agreement</th>
							<!-- <th class="demo visi">Responsible Person</th> -->
							<th class="demo visi">Seat of arbitration</th>
							<th class="demo visi">Insurance</th>
							<th>Status</th>
							<!--<th>Contract/Document Name</th> -->
							<!-- <th>File Size</th> -->
							<th>Action</th>

						</tr>
					</thead>

					<tbody id="searchResult">

						<c:forEach items="${ContractList}" var="contract" varStatus="loop">
							<tr id="row_${contract.exec_contract_id}">
								<td class="edit">${loop.index+1}</td>
								<td>${contract.exec_contract_entity_name}</td>
								<td>${contract.exec_contract_unit_name}</td>
								<td>${contract.exec_contract_function_name}</td>
								<td>
									<%-- <c:choose>
						    <c:when test="${contract.exec_contract_executed_type_status==1}"> --%>
									${contract.exec_contract_type_name} <%-- </c:when>
						
							<c:otherwise>
								<c:set var="cont_type" scope="page" value="0"/> 
						    		<c:forEach items="${contract.exec_cont_type_list_name}" var="cont" >
						         <c:set var="cont_type" value="${cont_type+1}"/>
						         ${cont_type}) ${cont.cont_type_name}<br>
						     		</c:forEach>
							</c:otherwise>
						</c:choose> --%>
								</td>
								
								<td><c:set var="count" scope="page" value="0" /> <c:forEach
										items="${contract.exec_parties}" var="parties">
										<c:set var="count" value="${count+1}" />
						         ${count}) ${parties.cont_party_name}<br>
									</c:forEach></td>
									<td>${contract.exec_contract_division}</td>
									<td class="demo visi">${contract.exec_contract_execution_date}</td>
									<td class="demo visi">${contract.exec_contract_start_date_name}</td>
									<td>${contract.exec_contract_end_date_name}</td>
									<td class="demo visi">${contract.exec_contract_renewal_date}</td>
									<td class="demo visi">${contract.exec_contract_notice_period}</td>
									<td class="demo visi">${contract.exec_contract_lockin_period}</td>
									
									<td class="demo visi">${contract.exec_contract_description}</td>
									<td class="demo visi">${contract.exec_contract_payment}</td>
									<td class="demo visi">${contract.exec_contract_termination_clause}</td>
									<td class="demo visi">${contract.exec_contract_damages}</td>
									<td class="demo visi">${contract.exec_contract_consider_involve}</td>
									<td class="demo visi">${contract.exec_contract_signatories_assign}</td>
									<td class="demo visi">${contract.exec_contract_agree_status}</td>
									<td class="demo visi">${contract.exec_contract_renewal_status}</td>
									
									<td class="demo visi">${contract.exec_contract_ammendment}</td>
									<%-- <td class="demo visi">${contract.exec_contract_resposible_person}</td> --%>
									<td class="demo visi">${contract.exec_contract_arbitration}</td>
									<td class="demo visi">${contract.exec_contract_insurance}</td>
									
									 
									<td>${contract.exec_contract_agree_status}</td> 
								<td class="edit"><c:choose>
								
								
										<c:when
											test="${contract.exec_contract_executed_type_status == 1 && (sessionScope.sess_user_role==1 || contract.exec_contract_added_by == sessionScope.sess_user_id)}">
								<%-- <c:if test="${contract.exec_contract_agree_status=='Draft'}"> --%>
											
											
											<%-- </c:if>  --%>
											<a href="./editExecutedContract?exec_id=${contract.exec_contract_id}" target='_blank'>
												<button type="button" name='submit' value="submit"
													class="btn btn-primary" style="margin-bottom: 4px;">
													<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
												</button>
											</a>
											<button type="button" value="${contract.exec_contract_id}"
												name='delete_executed_contract' class="btn btn-danger"
												style="margin-bottom: 4px;">
												<i class='fa fa-trash'></i>&nbsp;Delete
											</button>
											<br />
										</c:when> 
										<c:otherwise>

										</c:otherwise>
									</c:choose> <%-- <c:choose>
						<c:when test="${contract.exec_contract_executed_type_status==1}"> --%>
				
									<a
									href="executedContractUpdates?exec_id=${contract.exec_contract_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary" style="margin: 2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Contract Details
										</button></a> 
										
								<%-- 		<a
									href="./listActionItem?exec_id=${contract.exec_contract_id}"><button
											type="button" class="btn btn-primary" style="margin: 2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;List Action Item
										</button></a>  --%>
										<a
									href="./listTermRenContract?exec_id=${contract.exec_contract_id}"><button
											type="button" class="btn btn-success" style="margin: 2px">
											&nbsp;Termination/Renewal</button></a>
										<a
									href="./listAmendmentContract?exec_id=${contract.exec_contract_id}"><button
											type="button" class="btn btn-success" style="margin: 2px">
											&nbsp;Contract Amendment</button></a> <%-- </c:when>
						<c:otherwise> 
						 <a href="./updateTab?cont_id=${contract.exec_contract_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Update
										</button></a>
						</c:otherwise>
						</c:choose>--%></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<style>
.visi{
display :none; 
/* visibility:hidden */
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);	
});
 $("#exportXls").click(function(){
	 $("th").removeClass( "visi" );
	 $("td").removeClass( "visi" );
	 
	 //alert();
	 $('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[23]});
	 $(".demo").addClass( "visi" );
	 $(".demo").addClass( "visi" );
	 
 })

</script>
<script type="text/javascript">
/* 	function exportXLs() {
		$('#example1').show();
		$('#example1').tableExport({
			type : 'excel',
			escape : 'false'
		});
		$('#example1').hide();
	} */

	/* function uploadXLs() {
	 $('#example1').show();
	 $('#example1').tableExport({type:'excel',escape:'false'});
	 $('#example1').hide();
	 } */

	function getAllLocationForOrganization() {
		var tmap_orga_id = $("#orga_id").val();

		if (tmap_orga_id > 0) {
			items = {};
			items['tmap_orga_id'] = tmap_orga_id;

			var jsonString = JSON.stringify(items);

			$.ajax({
				type : "post",
				url : "./getAllLocationByUserOrganization",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(locationlist) {

					var locationjson = $.parseJSON(locationlist);
					$('#loca_id').empty();
					$('#loca_id').append(
							'<option value = 0>--Select--</option>');
					for (var i = 0; i <= locationjson.length - 1; i++) {
						var innerobj = locationjson[i];
						$('#loca_id').append(
								'<option value = '+innerobj["loca_id"] +'>'
										+ innerobj["loca_name"] + '</option>');
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		} else {
			$('#loca_id').html("<option value=0>--Select--</option>");
			$('#dept_id').html("<option value=0>--Select--</option>");
		}

	}

	function getAllDepartmentsByLocation() {
		var tmap_orga_id = $("#orga_id").val();
		var tmap_loca_id = $("#loca_id").val();
		if (tmap_loca_id > 0) {
			items = {};
			items["tmap_orga_id"] = tmap_orga_id;
			items["tmap_loca_id"] = tmap_loca_id;

			var jsonString = JSON.stringify(items);

			$.ajax({
				type : "post",
				url : "./getAllDeparmentsByUserOrganizationLocation",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(departmentlist) {
					var departmentjson = $.parseJSON(departmentlist);
					$('#dept_id').empty();
					$('#dept_id').append(
							'<option value = 0>--Select--</option>');

					for (var i = 0; i <= departmentjson.length - 1; i++) {
						var innerobj = departmentjson[i];
						$('#dept_id').append(
								'<option value = '+innerobj["dept_id"] +'>'
										+ innerobj["dept_name"] + '</option>');
					}

				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		} else {
			$('#dept_id').html("<option value=0>--Select--</option>");
		}
	}

	$(document)
			.ready(
					function() {
						$("#orga_id").on('change',
								getAllLocationForOrganization);
						$("#loca_id").on('change', getAllDepartmentsByLocation);

						$("#exec_contract_type_id").multiselect({
							buttonWidth : '224px',
							enableFiltering : true,
							filterBehavior : 'text',
							enableCaseInsensitiveFiltering : true,
							maxHeight : 250
						});
						document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);

					});
	
	
	/* $(document).ready(function(){
		
		document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);	
	});
	  $("#example").click(function(){
		 $("th").removeClass( "visi" );
		 $("td").removeClass( "visi" );
		 
		 //alert();
		 $('#LitigationList').tableExport({type:'excel',escape:'false',ignoreColumn:[]});
		 $(".demo").addClass( "visi" );
		 $(".demo").addClass( "visi" );
		 
	 })  */
	 
	 
</script>
<script>

function myFunction() {

	  // Declare variables 
	  var input = document.getElementById("myInput");
	  var filter = input.value.toUpperCase();
	  var table = document.getElementById("example");
	  var trs = table.tBodies[0].getElementsByTagName("tr");

	  // Loop through first tbody's rows
	  for (var i = 0; i < trs.length; i++) {

	    // define the row's cells
	    var tds = trs[i].getElementsByTagName("td");

	    // hide the row
	    trs[i].style.display = "none";

	    // loop through row cells
	    for (var i2 = 0; i2 < tds.length; i2++) {

	      // if there's a match
	      if (tds[i2].innerHTML.toUpperCase().indexOf(filter) > -1) {

	        // show the row
	        trs[i].style.display = "";

	        // skip to the next row
	        continue;

	      }
	    }
	  }

	}
</script>
<script src="appJs/ExecutedContracts/executed_contracts.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
