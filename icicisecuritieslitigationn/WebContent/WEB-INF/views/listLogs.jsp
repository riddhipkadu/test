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
					<h2 style="color:#a72f14;font-size:24px;float:left;">List Logs</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
				
			</div>
		
	<div style="clear:both"></div>
	<form role="form" class="form-horizontal" method="post" >
		
		
			<div class="f_form_content">
				<h2>Search</h2>

				<div class="col-md-12">
				<input type="hidden" id="role" value="${sessionScope.sess_user_role}">
				
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Log Related To :</label>
							<div class="col-sm-8">
								<select class="form-control" name="log_related_to" id="log_related_to">
									  <option value="NA">--Select--</option>
									  <option value="Litigation">Litigation</option>
									  <!-- <option value="Legal Notice">Legal Notice</option> -->
									  <option value="Query">Query</option>
									  <option value="Pre Execution contract">Pre-Execution Contract</option>
									  <option value="Executed contract">Executed Contract</option>
									  <option value="SFC">Standard Form Contract</option>
									  
								</select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Logs date from :</label>
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
		<!-- <div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[8]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div> -->
		
		<div class="container">
			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Sr.No.</th>
                <th>Log Related To</th>
                <th>Log related to Name</th>
                <th>Activity</th>
                <!-- <th>Sub Activity Id</th> -->
                <th>Sub Activity</th>
                <th>Responsible Person</th>
                <th>Activity Date</th>
                <th>Logs</th>
               <!--  <th>Update</th> -->
                <!-- <th>Reply</th> --> 
            </tr>
        </thead>
       
        <tbody id="searchResult">
           
        </tbody>
    </table>
			
			</div>
	
</div>

<div class="modal fade" id="log_model_activity_logs" role="dialog" >
				<div class="modal-dialog" style="width: 80%;">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #13a430;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								Logs
							</h4>
						</div>
						<div class="modal-body">
		<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                 <th>Sr.No.</th>
                <th>Log Related To</th>
                <th>Log related to Name</th>
                <th>Activity</th>
                <th>Sub Activity Id</th> 
                <th>Sub Activity</th>
                <th>Responsible Person</th>
                <th>Activity Date</th>
            </tr>
        </thead>
       
        <tbody id="logResultActivity">
        
        
        </tbody>
        
        </table>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

</div>
</div>
<script src="appJs/Logs/logs_validation.js"></script> 

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>