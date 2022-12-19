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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Internal Litigation Code List</h2>
					<a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>

				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addInternalLitigation" class="btn btn-primary">Create new</a></center>
				</div>
		</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
			
		<div class="table_data1">
				<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[3,4]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		 		</div>
				
		<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div>
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
				<th>Id</th>
				<th>Internal Litigation code</th>                
                <th>Code Description</th>
                <!-- <th>Currency rate</th> -->
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>
          <c:forEach items="${listInternalLitigation}" var="internal" varStatus="loop">
					<tr id="row_${internal.internal_liti_id}">
						<td>${loop.index+1 }</td>
						<td>${internal.internal_liti_code}</td>
						<td>${internal.internal_liti_desc}</td>
						<%-- <td>${currency.curr_rate}</td> --%>
						<td class="edit"><a href="editInternalLitigation?inter_id=${internal.internal_liti_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
						</button></a></td>
						<td class="delete"><button type="button" value="${internal.internal_liti_id}" name='delete_inter_liti' class="btn btn-danger">
						<i class='fa fa-trash'></i>&nbsp;Delete
						</button></td>
					</tr>
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

<script src="appJs/InternalLitigation/internalLitigation.js"></script> 

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
