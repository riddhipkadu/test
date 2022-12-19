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

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Sarfaesi Act</h2>
					
				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addSarfaesiAct" class="btn btn-primary"> Add Case Under Sarfaesi Act</a>
				</center>
			</div>
		</div>
		<div style="clear: both"></div>
		
		
		<!--first form-->
		<form class="form-horizontal" role="form" method="POST">
		<div class="col-md-12">
			
		</div>
		<div style="clear: both"></div>
		
		<div class="f_form_content">
				<h2>Search</h2>
				
				<div class="col-md-12">
				<input type="hidden" id="role" value="${sessionScope.sess_user_role}">
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_orga_id" class="control-label col-sm-4"
								for="sel1">Entity/Company Name :</label>
							<div class="col-sm-8">
								<select class="form-control" id="sarf_orga_id">
								<!-- <option value="0">--Select--</option> -->
									<c:forEach items="${allOrganizations}" var="orga">
								<option value="${orga.key}">${orga.value}</option>
								</c:forEach>
								</select><i class="asterisk_input"></i>

							</div>
						</div>
					</div>
					
					<!-- <div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Unit/Vertical :</label>
							<div class="col-sm-8">
								<select class="form-control" id="sarf_loca_id"
									name="sarf_loca_id">
									<option value="0">--Select--</option>
									
								</select>
							</div>
						</div>
					</div> -->
					
					 <div class="col-md-4">
						<div class="form-group">
							<label for="user_loca_id" class="control-label col-sm-4"
								for="sel1">Unit:</label>
							<div class="col-sm-8">
								<select class="form-control" id="sarf_loca_id"
									name="sarf_loca_id">
									<option value="0">--Select--</option>
									
								</select>
							</div>
						</div>
					</div> 


					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Security Type :</label>
							<div class="col-sm-8">
								<select class="form-control" name="sarf_security_type"  id="sarf_security_type">
								    <option value="NA">--Select--</option>
									<option value="Land & Building">Land & Building</option>
									<option value="Motor Vehicle">Motor Vehicle</option>
									<option value="Shares or Stock">Shares or Stock</option>
									<option value="Book Debts">Book Debts</option>
							     </select><!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
						</div>
					</div>	
					
				<div class="col-md-12">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> Loan Number :</label>
							<div class="col-sm-8">
								<select name="sarf_loan_no" id="sarf_loan_no" class="form-control">
										<option value="0" selected="selected">--Select--</option>
										 <c:forEach items="${sarf_loan_no}" var="loanno">
										 <c:choose>
										 <c:when test="${loanno !=''}">
											<option value="${loanno}">${loanno}</option>
										</c:when>
										</c:choose>
										</c:forEach> 
									</select>
							</div>
						</div>
					</div>
					
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Borrower Name :</label>
							<div class="col-sm-8">
								<select name="sarf_borrower" id="sarf_borrower" class="form-control">
										<option value="0" selected="selected">--Select--</option>
										 <c:forEach items="${sarf_borrower}" var="borrwname">
										 <c:choose>
										 <c:when test="${borrwname !=''}">
											<option value="${borrwname}">${borrwname}</option>
										</c:when>
										</c:choose>
										</c:forEach> 
									</select>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Type of Security Interest :</label>
							<div class="col-sm-8">
								<select class="form-control" name="sarf_security_Interest"  id="sarf_security_Interest">
								    <option value="NA">--Select--</option>
									<option value="Pledge">Pledge</option>
									<option value="Hypothecation">Hypothecation</option>
									<option value="Charge">Charge</option>
									<option value="Lease">Lease</option>
							     </select><!-- <i class="asterisk_input"></i> -->
							</div>
						</div>
						</div>
					</div>	
					
				<div class="col-md-12"> 
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1"> From:</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="SarfAct_from_date" name="SarfAct_from_date" readonly type="text">
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">To:</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="SarfAct_to_date" name="SarfAct_to_date" readonly type="text"> 
									<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					
					
				</div>
				<div class="col-md-12 litigation_buttons">
					<center>
						<input type="button" id="btnSearch" value="Search" class="btn btn-myPrimary" />
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
		<div style="clear: both"></div>

		<div class="table_data1">
	
			<div class="container">
			<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" id=exportXls class="list-group-item col-md-2 btn btn-success"
										><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  		</div>
		  	
			 <div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
		  			
			</div>  
					<table id="SarfActList" class="table table-striped table-bordered" width="100%" cellspacing="0">
							<thead>
								<tr style="background: #a72f14; color: #fff;">
									     <th>Sr.No.</th>
										<th>Loan Number</th>
										<th>Borrower Name</th>
										<th>Security Type </th>
										<th>Type of Security Interest</th>
										<th>NPA Date</th>
										<th>Action</th>
								</tr>
							</thead>
							
                        
					<tbody id="searchResult">
		 <c:forEach items="${listSarfaesiAct}" var="sarf" varStatus="loop">
					   <tr id="row_${sarf.sarf_act_id}">
								<td>${loop.index+1}</td>
								<td>${sarf.sarf_loan_no}</td>
								<td>${sarf.sarf_borrower}</td>
								<td>${sarf.sarf_security_type}</td> 
								<td>${sarf.sarf_security_Interest}</td>
								<td>${sarf.sarf_npa_dates}</td>
								<td class="edit">
								
								<a href="editSarfaesiAct?sarf_act_id=${sarf.sarf_act_id}">
								<button type="button" value="${sarf.sarf_act_id}" name='edit_sarfAct' 
												class="btn btn-warning btn-block" style="margin-bottom: 4px;">
												<i class=''></i>&nbsp;Edit
										</button></a> 
								 <c:choose>
									<c:when test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_id == sarfaesiActDetails.sarf_added_by}"> 
										
										<button type="button" value="${sarf.sarf_act_id}" name='delete_sarfAct' 
												class="btn btn-danger btn-block" style="margin-bottom: 4px;">
												<i class='fa fa-trash'></i>&nbsp;Delete
										</button>
									 </c:when>
								</c:choose> 
								
										
										
							     <a href="sarfaesiActDetails?sarf_act_id=${sarf.sarf_act_id}"><button type="button"
								name='submit' value="submit" class="btn btn-primary btn-block" style="margin :2px">
								 Update
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
<script>
$(document).ready(function(){
	
	$("#sarf_loan_no").multiselect({
		buttonWidth: '226px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    onChange: function(option, checked) {
                var values = [];
                $('#sarf_loan_no option').each(function() {
                    if ($(this).val() !== option.val()) {
                        values.push($(this).val());
                    }
                });

                $('#sarf_loan_no').multiselect('deselect', values);
            }
	});
	
	 $("#sarf_borrower").multiselect({
			buttonWidth: '226px',
		    enableFiltering: true,
		    filterBehavior: 'text',
		    enableCaseInsensitiveFiltering: true,
		    onChange: function(option, checked) {
	                var values = [];
	                $('#sarf_borrower option').each(function() {
	                    if ($(this).val() !== option.val()) {
	                        values.push($(this).val());
	                    }
	                });

	                $('#sarf_borrower').multiselect('deselect', values);
	            }
		});
	
	 document.getElementById('count').innerHTML = ($('#SarfActList >tbody >tr').length);
   });
$("#exportXls").click(function(){
	/*  $("th").removeClass( "visi" );
	 $("td").removeClass( "visi" ); */
	 
	 //alert();
	 $('#SarfActList').tableExport({type:'excel',escape:'false',ignoreColumn:[21]});
	/*  $(".demo").addClass( "visi" );
	 $(".demo").addClass( "visi" ); */
	 
})
	 </script>
<script src="appJs/SarfaesiAct/sarfaesiAct.js"></script>	
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
