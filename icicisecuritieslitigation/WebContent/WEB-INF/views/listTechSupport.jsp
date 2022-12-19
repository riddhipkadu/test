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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Tech Support List</h2>
					<!-- <a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
				<%-- <div class="col-md-2">
				<center><a href="./addLegalNoticeCatagory" class="btn btn-primary">Add Legal Notice Category</a></center>
				</div> --%>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	
			
			
				<div class="table_data1">
		<div class="container">
		

			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                <th>Sr. No</th>
                <th>Full Name</th>
                <th>Mobile</th>
                <th>Email Address</th>
				<th>Query</th>
				<th>Created At</th>
				
            </tr>
        </thead>
       
        <tbody>
          <c:forEach items="${listTechSupport}" var="tech" varStatus="loop">
					<tr id="row_${tech.supp_id}">
						<td>${loop.index+1}</td>
						<td>${tech.supp_user_name}</td>
						<td>${tech.supp_user_mobile}</td>
						<td>${tech.supp_user_email}</td>
						<td>${tech.supp_user_message}</td>
						<td>${tech.supp_created_at}</td>
					</tr>
				</c:forEach>
      
        </tbody>
    </table>
		</div>

</div>
</div>
</div>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
