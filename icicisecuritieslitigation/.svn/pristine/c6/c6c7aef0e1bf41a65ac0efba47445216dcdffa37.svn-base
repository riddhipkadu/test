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
					<h2 style="color:#032BEC;font-size:24px;float:left;">Standard Form Contracts</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addStandardFormContracts" class="btn btn-primary">Add New SFC</a></center>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	<!-- <form class="form-horizontal" role="form"> -->
			<%-- <div class="f_form_content">
				<h2>Search</h2>

				<div class="col-md-12">


					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">SFC Type :</label>
							<div class="col-sm-7">
								<select class="form-control" id="sfco_type_id">
									<option value="0">--Select--</option>
									<c:forEach items="${allSfcType}" var="sfcType">
								<option value="${sfcType.sfco_type_id}">${sfcType.sfco_type_name}</option>
								</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">SFC :</label>
							<div class="col-sm-7">
								<select class="form-control" id="tmap_loca_id">
									<option value="0">--Select--</option>
									<option value="0">HR</option>
									<option value="0">Admin</option>
									<option value="0">Information Technology</option>
								</select>
							</div>
						</div>
					</div>

				</div>

				<div class="col-md-12 litigation_buttons">
					<br>
					<center>
						<button type="button" id="task_calendar" class="btn btn-myPrimary">Search</button>
						
						<!-- <button type="button" name="back" id="back"
							class="btn btn-myDefault"
							onclick="window.location.href ='./dashboard' ">Back</button> -->
						<div id="noFound"> </div>
					</center>
				</div>
			</div> --%>
		<!-- </form> -->
			
			
				<div class="table_data1">
				
				<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[7,8]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div>
		<div class="container">
		
		<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
		</div>
			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#0B6EC3;color:#fff;">
                
                <th>Sr. No</th>
               <!--  <th>Entity</th>
                <th>Unit</th>
                <th>Function</th> -->
                <th>SFC Name</th>
                <th>SFC Contract type</th>
                <th>SFC Type</th>
                <th>Abbreviation</th>
                <th>Document</th>
                <!-- <th>File Size</th>
                <th>Version</th> -->
                <c:choose>
			    <c:when test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_role==5}">               
                <th>Action</th>
                </c:when>
                </c:choose>
            </tr>
        </thead>
       
        <tbody>
        <c:forEach items="${listStandardFormContracts}" var="sfco" varStatus="loop">
					<tr id="row_${sfco.sfco_id}">
						<td>${loop.index+1}</td>
						<%-- <td>${sfco.sfco_entity}</td>
						<td>${sfco.sfco_unit}</td>
						<td>${sfco.sfco_function}</td> --%>
						<td>${sfco.sfco_name}</td>
						<td>${sfco.sfco_contract_type}</td>
						<td>${sfco.sfco_type_name}</td>
						<td>${sfco.sfco_abbreviation} </td>
						<td>
						<c:choose>
							 <c:when test="${fn:length(sfco.sfc_doc_list)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:forEach items="${sfco.sfc_doc_list}" var="doc"><a href="./downloadSfcoDocument?sfco_doc_id=${doc.sfco_doc_id }">
							   ${doc.sfco_doc_original_file_name}</a> <br>
							   </c:forEach>
							</c:otherwise>
						</c:choose>
						</td>
						 <c:choose>
			      			<c:when test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_role==5}">
						<td><a href="./editStandardFormContracts?sfco_id=${sfco.sfco_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary" style="margin:2px">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
								</button></a>
							<button type="button" value="${sfco.sfco_id}"
											 name='delete_sfco' 
											class="btn btn-danger" style="margin:2px">
											<i class='fa fa-trash'></i>&nbsp;Delete
										</button></td></c:when></c:choose>
							
						<!--  <td class="edit"><a href="./replyToQuery"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Update
						</button></a></td> --> 
									
				</tr>
        </c:forEach>
        
          <!-- 
					<tr>
						<td>1</td>
						<td>Confidentiality Agreements</td>
						<td><a href="./downloadProofOfCompliance?udoc_id=1">NDA with Employees</a></td>
						<td>1.2 MB</td>
						<td>1.0</td>
						
						<td class="edit"><a href="editStandardFormContract"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Update
						</button></a></td>
						
									
					</tr>
					<tr>
						<td>2</td>
						<td>Confidentiality Agreements</td>
						<td><a href="./downloadProofOfCompliance?udoc_id=1">NDA with Vendors</a></td>
						<td>536 KB</td>
						<td>1.6</td>
						<td class="edit"><a href="editStandardFormContract"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Update
						</button></a></td>
						
									
					</tr>
					<tr>
						<td>3</td>
						<td>Confidentiality Agreements</td>
						<td><a href="./downloadProofOfCompliance?udoc_id=1">NDA with Customer</a></td>
						<td>879 KB</td>
						<td>2.1</td>
						<td class="edit"><a href="editStandardFormContract"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Update
						</button></a></td>
						
									
					</tr>
					<tr>
						<td>4</td>
						<td>Marketing/Business Development</td>
						<td><a href="./downloadProofOfCompliance?udoc_id=1">Distributor Agreements</a></td>
						<td>450 KB</td>
						<td>1.3</td>
						<td class="edit"><a href="editStandardFormContract"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Update
						</button></a></td>
					</tr>
				
 -->           
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
<script src="appJs/StandardFormContract/sfc_validation.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
