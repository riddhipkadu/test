<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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

<!-- Fail Modal -->
			<div class="modal fade" id="actionFail" role="dialog">
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
							<p>This Law Firm Field is used in External Counsel. Can't be deleted </p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>


	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Law Firms List</h2>
				    <a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> 
				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addLawFirm" class="btn btn-primary">Create New</a>
				</center>
			</div>
		</div>
		<div style="clear: both"></div>
		

		<div style="clear: both"></div>

		<div class="table_data1">
		<div class="col-md-12" style="margin-bottom:10px;"><a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[5]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div>
			<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
		 	</div> 
				<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
					<thead>
						<tr style="background: #a72f14; color: #fff;">
							<th>Sr.No.</th>
							<th>Law Firm Name</th>
							<th>Contact Person</th>
							<th>Contact No</th>
							<th>Email Id</th>
                            <th>Action</th>
						</tr>
					</thead>

					<tbody id="searchResult">
					<c:forEach items="${listLawFirm}" var="lawFirm" varStatus="loop">
					   <tr id="row_${lawFirm.lawf_id}">
								<td><a href="#">${loop.index+1}</a></td>
								<td>${lawFirm.lawf_name}</td>
								<td>${lawFirm.lawf_contact_person}</td>
								<td>${lawFirm.lawf_mobile_no}</td>
								<td>${lawFirm.lawf_email_id}</td>
								<td class="edit"><a href="editLawFirm?lawf_id=${lawFirm.lawf_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
								</button></a>
								
								<button type="button" value="${lawFirm.lawf_id}"
											 name='delete_law_firm' 
											class="btn btn-danger">
											<i class='fa fa-trash'></i>&nbsp;Delete
										</button></td>
					  </tr>
					 </c:forEach>  
					</tbody>
				</table>
			</div>
		</div>
	</div>

<script src="appJs/LawFirm/law_firm.js"></script>



<script type="text/javascript">
$("#document").click(function(){
	var htmlOutputForm = "<img src='images/Certi.jpg' width='100%' height='500%' style='overflow:auto'/><br/>";
	bootbox.dialog({
		title : "Document",
		message : htmlOutputForm
	});
});
$("#document1").click(function(){
	var htmlOutputForm = "<img src='images/Certi.jpg' width='100%' height='500%' style='overflow:auto'/><br/>";
	bootbox.dialog({
		title : "Document",
		message : htmlOutputForm
	});
});

$(document).ready(function(){
	
	 document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
	
});
</script>

 

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
