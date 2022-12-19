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
							<p>This Area Of Expertise Field is used in External Counsel. Can't be deleted </p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>


<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->

<div class="col-md-12">
<div class="col-md-9">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Standard Form Contract Type List</h2>
					<a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>

				</div>
</div>
				<div class="col-md-2">
				<center><a href="./addStandardFormContractType" class="btn btn-primary">Add Standard Form Contract Type</a></center>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	
			
			
				<div class="table_data1">
				<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[2,3]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div>
		<div class="container">
		<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
		</div> 
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#0B6EC3;color:#fff;">
                <th>Id </th>
                <th>Standard Form Contract Type Name</th>
                
                <th>Edit</th>
               <!-- <th>Approve / Disapprove</th> -->
                <th>Delete</th>
				
				
            </tr>
        </thead>
       
        <tbody>
          <c:forEach items="${listStandardFormContractType}" var="standard" varStatus="loop">
					<tr id="row_${standard.sfco_type_id}">
						<td>${loop.index+1}</td>
						<td>${standard.sfco_type_name}</td>
						<td class="edit"><a href="editStandardFormContractType?sfco_type_id=${standard.sfco_type_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
						</button></a></td>
						
						<td class="delete"><button type="button" value="${standard.sfco_type_id}"
											 name='delete_sfco_type' 
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
</div>

<script>
$(document).ready(function(){
	
	 document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});
</script>

<script src="appJs/StandardFormContract/sfc_type.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
