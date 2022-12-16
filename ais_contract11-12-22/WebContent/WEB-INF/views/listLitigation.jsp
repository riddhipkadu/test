<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
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
					<h2 style="color:#032BEC;font-size:24px;float:left;">Litigation Summary</h2>
					<!-- <a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addLitigation" class="btn btn-primary">Add Litigation</a></center>
				</div>
</div>
		
	<div style="clear:both"></div>
<div class="col-md-12">
    <!-- First Panel -->
				<form role="form" class="form-horizontal"  method="post" >
				
				<div class="f_form_content">
				
					<h2>Search</h2>
					<div class="col-md-12">
					<input type="hidden" id="role" value="${sessionScope.sess_user_role}">
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_orga_id" class="control-label col-sm-4"
								for="sel1">Entity/Company Name :</label>
							<div class="col-sm-8">
								<select class="form-control" id="liti_orga_id">
								<!-- <option value="0">--Select--</option> -->
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
								<select class="form-control" id="liti_loca_id"
									name="lega_noti_unit_id">
									<option value="0">--Select--</option>
									
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="form-group">
							<label for="user_dept_id" class="control-label col-sm-4" for="sel1">Function/Location /Department: </label>
							<div class="col-sm-8">
								<select class="form-control" id="liti_dept_id"
									name="lega_noti_function_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
                   </div>
                   <div class="col-md-12">
					 <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">By/Against Company:</label>
							<div class="col-sm-8">
								<select class="form-control" name="liti_by_against" id="liti_by_against">
								    <option value="NA">--Select--</option>
									<option value="By">By Company</option>
									<option value="Against">Against Company</option>

								</select>
							</div>
						</div>
					</div> 
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Category:</label>
							<div class="col-sm-8">
								<select class="form-control" name="liti_category" id="liti_category">
								   <option value="0">--Select--</option>
										<c:forEach items="${litigation_type_list}"
											var="litigation_type">
											<option value="${litigation_type.liti_type_id}">${litigation_type.liti_type_name}</option>
										</c:forEach>
									</select>
							</div>
						</div>
					</div> 
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Responsible Person:</label>
							<div class="col-sm-8">
								<select class="form-control" id="liti_handle_by" >
									 <option value="0">--Select--</option>
									 <c:forEach items="${UserList}" var="user">
									 <option value="${user[0]}">${user[1]} </option>
									 </c:forEach>
								</select>
							</div>
						</div>
					</div> 
					</div>
					<div class="col-md-12">
                    <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Case Filling Date - From:</label>
							<div class="col-sm-8">
								<div id="from_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="from_date" name="liti_case_date_from" readonly="true" type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">To:</label>
							<div class="col-sm-8">
								<div id="to_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="to_date" name="liti_case_date_to" readonly="true" type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Case Status:</label>
							<div class="col-sm-8">
								<select class="form-control" id="liti_status" >
									 <option value="NA">--Select--</option>
									 <option value="Pending">Pending</option>
									 <option value="Completed">Completed</option>
								</select>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12">
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Hearing Date - From:</label>
							<div class="col-sm-8">
								<div id="from_hear_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="from_date" name="liti_hearing_date_from" readonly="true" type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">To:</label>
							<div class="col-sm-8">
								<div id="to_hear_date_div" class="input-group date" data-date-format="MM">
									<input class="form-control" id="to_date" name="liti_hearing_date_to" readonly="true" type="text"> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</div>
					
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Criticality : </label>
								<div class="col-sm-8">
									<select id="liti_criticality"
										class="form-control">
										<option value="NA">--Select--</option>
										<option value="High">High</option>
										<option value="Medium">Medium</option>
										<option value="Low">Low</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Internal Litigation Code: </label>
								<div class="col-sm-8">
									<select name="liti_internal_liti_code" id="liti_internal_liti_code" class="form-control">
										<option value="0">--Select--</option>
										 <c:forEach items="${liti_code}" var="liti_code">
											<option value="${liti_code.internal_liti_id}">${liti_code.internal_liti_code}</option>
										</c:forEach> 
									</select><!-- <i class="asterisk_input"></i> -->
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12 litigation_buttons">
					<br>
					<center>
						<button type="button" id="searchLitigation" class="btn btn-myPrimary">Search</button>
						
						<!-- <button type="button" name="back" id="back"
							class="btn btn-myDefault"
							onclick="window.location.href ='./dashboard' ">Back</button> -->
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
				<!-- End First panel -->
</div>	
	
	<!--first form-->
			
				<div class="table_data1">
				<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" id="exportXls" class="list-group-item col-md-2 btn btn-success"
										><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div>
		<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div>
			<table id="LitigationList" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#0B6EC3;color:#fff;">
                
                <th>Litigation Id</th>
                <th>Company (By/Against)</th>
                <th class="demo visi">Category</th>
                <th>Case Reference No</th>
                <th class="demo visi">Case Filling Date</th>
                <th>Next Hearing Date</th>
                <th>Case Title</th>
                <th class="demo visi">Jurisdiction name</th>
                <th class="demo visi">Company External counsel</th>
                <th class="demo visi">Matter Handled by</th>
                <th class="demo visi">Internal contact person</th>
                <th class="demo visi">By Party</th>
                <th class="demo visi">Against Party</th>  
                <th class="demo visi">Opposite Party address</th>
                <th class="demo visi">Opposite Party Advocate Law Firm</th>
                <th class="demo visi">Opposite Party Advocate name</th>
                <th class="demo visi">Advocate contact number</th>
                <th class="demo visi">Court/Tribunal</th>
                <th class="demo visi">Amount Involved</th>
                <th class="demo visi">Relevant Law</th>
                <th class="demo visi">Brief Description</th>
                <th>Status</th>
                <th>Action</th>
                <!--<c:choose>
			      <c:when test="${sessionScope.sess_user_role==1}">
                <th>Edit</th>
                </c:when>
                </c:choose>
                 <th>Category</th>
                <th>Case Filling Date</th>
                <th>Company External counsel</th>
                <th>Matter Handled by</th>
                <th>Opposite Party</th>
                <th>Court/Tribunal</th>
                <th>Amount Involved</th>
                <th>Relevant Law</th>
                <th>Brief Description</th>
                 -->
                <!-- <th>Delete</th> -->
				
				
            </tr>
        </thead>
       
        <tbody id="searchResult">
           <c:forEach items="${list_litigation}" var="litigation">
					<tr id="row_${litigation.liti_id}">
						<td>${litigation.liti_client_liti_id}</td>
						<td>${litigation.liti_against_by_id} Company</td>
						<td class="demo visi">${litigation.liti_type_name}</td>
						<td>${litigation.liti_case_reference_no}</td>
						<td class="demo visi">${litigation.liti_case_filing_date}</td>
						<td>${litigation.liti_next_hearing_date}</td>
						<td>${litigation.liti_case_title}</td>
						<td class="demo visi">${litigation.liti_jurisdiction_name}</td>
						<td class="demo visi">${litigation.liti_external_counsel}</td>
						<td class="demo visi">${litigation.liti_internally_handled_name}</td>
						<td class="demo visi">${litigation.liti_intenal_person}</td>
						<td class="demo visi">${litigation.liti_party_by}</td>
						<td class="demo visi">${litigation.liti_party_against}</td>
						<td class="demo visi">${litigation.liti_oppo_party_address}</td>
						<td class="demo visi">${litigation.liti_oppo_advo_law_firm}</td>
						<td class="demo visi">${litigation.liti_opposite_party_advocate}</td>
						<td class="demo visi">${litigation.liti_oppo_advo_contact}</td>
						<td class="demo visi">${litigation.liti_court}</td>
						<td class="demo visi">${litigation.liti_amount_involved}</td>
						<td class="demo visi">${litigation.liti_relevant_law}</td>
						<td class="demo visi">${litigation.liti_brief_description}</td>
						<td>${litigation.liti_status}</td>
						<td class="edit">
								<c:choose>
									<c:when test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_id == litigation.liti_added_by}">
										<a href="editLitigation?liti_id=${litigation.liti_id}"><button
													type="button" onclick="" name='submit' value="submit"
													class="btn btn-primary" style="margin-bottom :4px">
													<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
												</button></a><br/>
										<button type="button" value="${litigation.liti_id}" name='delete_litigation' 
												class="btn btn-danger" style="margin-bottom: 4px;">
												<i class='fa fa-trash'></i>&nbsp;Delete
										</button><br/>
									</c:when>
									<c:otherwise>
									 
									</c:otherwise>
								</c:choose>
								<a href="litigationDetails?liti_id=${litigation.liti_id}"><button
											type="button" onclick="" name="submit" value="submit"
											class="btn btn-primary" style="margin :2px">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Update
								</button></a>
						</td>		
						<%-- <td>${litigation.liti_type_name}</td>
						<td>${litigation.liti_case_filing_date}</td>
						<td>${litigation.exte_coun_name}</td>
						<td>${litigation.liti_internally_handled_name}</td>
						<td>${litigation.liti_opposite_party}</td>
						<td>${litigation.liti_court}</td>
						<td>${litigation.liti_amount_involved}</td>
						<td>${litigation.liti_relevant_law}</td>
						<td>${litigation.liti_brief_description}</td> --%>
								
								<%-- <td class="delete"><button type="button" value="${litigation.liti_type_id}"
											 name='delete_litigation_type' 
											class="btn btn-danger">
											<i class='fa fa-trash'></i>&nbsp;Delete
										</button></td> --%>
										
					</tr>
				</c:forEach>
           
        </tbody>
    </table>
			</div>
</div>
</div>
</div>
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
							<p>This Litigation Field is used in External Counsel. Can't be deleted </p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
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
	
	document.getElementById('count').innerHTML = ($('#LitigationList >tbody >tr').length);	
});
 $("#exportXls").click(function(){
	 $("th").removeClass( "visi" );
	 $("td").removeClass( "visi" );
	 
	 //alert();
	 $('#LitigationList').tableExport({type:'excel',escape:'false',ignoreColumn:[21]});
	 $(".demo").addClass( "visi" );
	 $(".demo").addClass( "visi" );
	 
 })
</script>	
<script src="appJs/Litigation/litigation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>