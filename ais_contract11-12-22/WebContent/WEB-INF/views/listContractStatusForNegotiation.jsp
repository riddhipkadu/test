
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
<!--heading text-->

<div class="col-md-12">
				<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Contract status sent for negotiation</h2>
				</div>
				</div>
				<c:choose>
				<c:when test="${allStatus != '[]'}">
				<div class="col-md-2">
				<a href="./addContractStatusForNegotiation?id=<%=request.getParameter("id")%>"><button
					type="button" class="btn btn-primary" style="margin :2px">Add Status
						</button></a>
				</div>
				</c:when>
				</c:choose>
				
</div>
		
	<div style="clear:both"></div>
	<!--first form-->
	
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
            <tr style="background:#0B6EC3;color:#fff;">
                <th>Sr. No</th>
                <th>Action Item</th>
                <th>Status Date</th>
                <th>Status added by</th>
                <th>Document</th>
                <th>Status</th>
                <!-- <th>Action</th> -->
            </tr>
        </thead>
        <tbody id="searchResult">
        <c:forEach items="${allStatus}" var="allStatus" varStatus="loop"> 
        			<tr>
						<td>${loop.index+1}</td>
						<td>${allStatus.poc_action_item}</td>
						<td>${allStatus.poc_status_date_name}</td>
						<td>${allStatus.poc_status_added_by_name}</td>
						<td><c:choose>
						<c:when test="${fn:length(allStatus.pocDoc)==0}">
							  	 NA
						</c:when>
						<c:otherwise>
						<c:forEach items="${allStatus.pocDoc}" var="doc">
						<a href="./downloadPOCDocument?doc_id=${doc.poc_doc_id }">
							${doc.poc_doc_original_file_name}</a>
							<br>
						</c:forEach>
						</c:otherwise>
						</c:choose>
						</td>
						<c:choose>
						<%-- <c:when test="${role_id == 2 || role_id == 3 || role_id == 4}">
						<td>${allStatus.poc_status}</td>
						</c:when> --%>
						<c:when test="${(role_id == 1 || role_id == 5) && allStatus.poc_status =='SentToPOCforNegotiation'}">
						<td id="appDis${allStatus.poc_status_id}"><button type="button" style="margin:2px"
											onclick="getReason(${allStatus.poc_status_id},'2')" name='submit'
											value="submit" class="btn btn-danger">
											<i class='fa fa-thumbs-down'></i>&nbsp;Reject</button>
								<button type="button" style="margin:2px"
								onclick="getAccept(${allStatus.poc_status_id},'1')" name='submit'
								value="submit" class="btn btn-success">
								<i class='fa fa-thumbs-up'></i>&nbsp;Accept</button>
						</td>
						</c:when>
						<c:otherwise>
						<td>${allStatus.poc_status}</td>
						</c:otherwise>
						</c:choose>								
					</tr>
        </c:forEach>
        </tbody>
    </table>
			</div>
</div>
</div>
</div>
<!-- <script src="appJs/ExecutedContracts/validateActionItem.js"></script>  -->
<script type="text/javascript">
$(document).ready(function(){
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});

function getReason(req_id, status){
	
	var form = "<table class='table table-striped table-bordered'><tr><td><label>Reason for rejection :</label></td><td><textarea class='form-control' name='reason_disapp' id='reason_disapp' placeholder='Please enter reason for rejection'></textarea></td></tr></table>";
	bootbox.confirm(form, function (value) {
		//alert("Value is :"+value);
		if(value != false){
		var reason = $('#reason_disapp').val();
		  //alert("reason is the :"+$('#reason_disapp').val()); 
		if(reason != null){
			items = {};
			items["req_id"] = req_id;
			items["req_status"] = status;
			items["reason"] = reason;
			var jsonString = JSON.stringify(items);
			
			$.ajax({
				type : "post",
				url : "./approveDisapproveStatusSentNegotiation",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(response) {
					if(response==1){
						var data = '';
							data += "Reject by Admin";
						$("#appDis"+req_id).html(data);
						window.location = "./listContractStatusForNegotiation?id="+${id};
					}else{
						$('#errors').html("Somthing went wrong please try again..!");
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		}
    });
}

function getAccept(req_id,status){
	
	var reason = "";
	items = {};
	items["req_id"] = req_id;
	items["req_status"] = status;
	items["reason"] = reason;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./approveDisapproveStatusSentNegotiation",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
					data += "Accepted";
				$("#appDis"+req_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}
		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
 }
</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
