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


	<div class="page_container">
		<!--heading text-->
		<sf:form class="form-horizontal" role="form" commandName="externalcounsel"
					action="./searchExternalCounsel" onsubmit="return validateForm();"  method="POST">
							<div class="col-md-12">
			<div class="col-md-10">
				<div class="header_button">
				
					<h2 style="color:#a72f14; font-size: 24px; float: left;">Advocate List</h2>
				    <a href="./masters"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> 
				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addAdvocate" class="btn btn-primary">Create New</a>
				</center>
			</div>
		</div>
		<div style="clear: both"></div>
		
		<div class="f_form_content">
				<h2>Search</h2>
				
				<div class="col-md-12">
				
				<%-- 
				<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Country :</label>
							<div class="col-sm-8">
								<select class="form-control" id="search_advo_country_id">
								      <option value="0">--Select--</option>
								      <c:forEach items="${allJoinCountries}" var="country">
								      	<option value="${country[0]}">${country[1]}</option>
								      </c:forEach> 
							   </select> <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">State :</label>
							<div class="col-sm-8">
								<select class="form-control" id="search_advo_state_id">
								      <option value="0">--Select--</option>
							   </select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">City :</label>
							<div class="col-sm-8">
								<select class="form-control" id="search_advo_city_id">
								      <option value="0">--Select--</option>
							   </select>
							</div>
						</div>
					</div>
					 --%>
					
					<div class="col-md-4">
						<div class="form-group">
							<label for="user_orga_id" class="control-label col-sm-4"
								for="sel1">Law Firm :</label>
							<div class="col-sm-8">
								<select class="form-control"  id="advo_law_firm">
								   <c:forEach items="${allLawFirm}" var="lawFirm">
								      <option value="${lawFirm['key']}">${lawFirm['value']}</option>
								   </c:forEach>
							   </select> 

							</div>
						</div>
					</div>
					

					<div class="col-md-4">
						<div class="form-group">
							<label for="user_dept_id" class="control-label col-sm-4" for="sel1">Advocate Name :</label>
							<div class="col-sm-8">
								<select class="form-control" id="search_advocate_id">
   							        <option value="0">--Select--</option>
							   </select>
							</div>
						</div>
					</div>
					  
                    <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Area of Expertise :</label>
							<div class="col-sm-8">
								<select class="form-control" id="advo_area_of_expertise" name="advo_area_of_expertise">
								   <c:forEach items="${allAreaOfExpertise}" var="areaOfExpertise">
								      <option value="${areaOfExpertise['key']}">${areaOfExpertise['value']}</option>
								   </c:forEach>
							   </select>
							</div>
						</div>
					</div>

				</div>
				<div class="col-md-12 litigation_buttons">
					<center>
						<button type="button" name='search_advocate' class="btn btn-myPrimary" id="search_advocate">Search</button>
					</center>
				</div>
				<div id="loader" style="display: none;">
					<center>
						<img src="./images/Loader/WaitLoader.gif" width="80px"
							height="85px" />
					</center>
				</div>
			</div>
			</sf:form>

		<div style="clear: both"></div>

		<div class="table_data1">
		<div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[6]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div>
			<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div> 
				<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
					<thead>
						<tr style="background:#a72f14; color: #fff;">
							<th>Sr.No.</th>
							<th>Name</th>
							<th>Law Firm</th>
							<th>Contact No</th>
							<th>Email Id</th>
							<!-- <th>City</th> -->
							<th>Area of Expertise</th>
                            <th>Action</th>
						</tr>
					</thead>

					<tbody id="searchResult">
					<c:forEach items="${listAdvocate}" var="advocate" varStatus="loop">
					   <tr id="row_${advocate.advo_id}">
								<td><a href="#">${loop.index+1}</a></td>
								<td>${advocate.advo_name}</td>
								<td>${advocate.advo_law_firm_name}</td>
								<td>${advocate.advo_mobile_no}</td>
								<td>${advocate.advo_email_id}</td>
								<%-- <td>${advocate.advo_city}</td> --%>
								<td>${advocate.advo_area_of_expertise_name}</td>
								<td><a href="editAdvocate?advo_id=${advocate.advo_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
								</button></a>
								<button type="button" value="${advocate.advo_id}"
											 name='delete_advocate' id='delete_advocate' 
											class="btn btn-danger">
											<i class='fa fa-trash'></i>&nbsp;Delete
										</button>
								</td>
					  </tr>
					 </c:forEach>  
					</tbody>
				</table>
			</div>
		</div>
	</div>

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

<script src="appJs/Advocate/advocate.js"></script> 
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
