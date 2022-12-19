<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Entity
						List</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addOrganization" class="btn btn-primary">Add Entity</a>
				</center>
			</div>
		</div>


		<div style="clear: both"></div>
		<!--first form-->

		<%-- <%
			if (session.getAttribute("smsg") != null) {
		%>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div id="fadeOutFun" class="alert alert-success">Data saved
					successfully.</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
		<%
			}
		%> --%>

		<div class="table_data1">
			<div class="container">
				<table id="example" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr style="background: #a72f14; color: #fff;">

							<th>Entity Name</th>
							<th>Entity Short Name</th>
							<th>Parent</th>
							<th>Edit</th>
							<!-- <th>Approve / Disapprove</th>
                <th>Enable / Disable</th> -->


						</tr>
					</thead>

					<tbody>
						<c:forEach items="${allOrganizations}" var="organization">
							<tr>
								<td>${organization[1]}</td>
								<td>${organization[5]}</td>
								<td>${organization[2]}</td>
								<td class="edit"><a
									href="editOrganization?orga_id=${organization[0]}"><button
											type="button" onclick="" name='submit' value="submit"
											class="btn btn-primary">
											<i class="fa fa-pencil-square-o"></i>&nbsp;Edit
										</button></a></td>
								<%--  <c:choose>
							<c:when test="${organization[3]=='1'}">
								<td class="disapprove" id="appDis${organization[0]}"><button type="button"
											onclick="approveDisapprove(${organization[0]},'0')" name='submit'
											value="submit" class="btn btn-primary">
											<i class="fa fa-thumbs-down"></i>&nbsp;Disapprove</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="approve" id="appDis${organization[0]}"><button type="button"
								           name='submit' onclick="approveDisapprove(${organization[0]},'1')" value="submit" class="btn btn-primary">
											<i class="fa fa-thumbs-up"></i>&nbsp;Approve</button>
							</td>
							</c:otherwise>
						</c:choose>
						 
						<c:choose>
							<c:when test="${organization[4]=='1'}">
								<td class="view" id="enaDis${organization[0]}"><button type="button"
											 name='submit'
											value="submit" onclick="enableDisable(${organization[0]},0)"  class="btn btn-danger">
											<i class="fa fa-times"></i>&nbsp;Disable</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="view" id="enaDis${organization[0]}"><button type="button"
								           name='submit' value="submit" onclick="enableDisable(${organization[0]},1)" class="btn btn-success">
											<i class='fa fa-eye'></i>&nbsp;Enable</button>
							</td>
							</c:otherwise>
						</c:choose> --%>
							</tr>
						</c:forEach>

					</tbody>
				</table>



			</div>





		</div>
	</div>




<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script>
	/* function approveDisapprove(orga_id,status){
	
	 items = {};
	 items["orga_id"] = orga_id;
	 items["orga_status"] = status;
	 var jsonString = JSON.stringify(items);
	
	 $.ajax({
	 type : "post",
	 url : "./approveDisapprove",
	 contentType : "application/json",
	 dataType : "html",
	 data : jsonString,
	 cache : false,
	 success : function(response) {
	 if(response==1){
	 var data = '';
	 if(status=='0'){
	 $("#appDis"+orga_id).addClass("approve").removeClass('disapprove');
	 data += "<button type='button' onclick='approveDisapprove("+orga_id+",1)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>";
	 }else{
	 $("#appDis"+orga_id).addClass("disapprove").removeClass('approve');
	 data += "<button type='button' onclick='approveDisapprove("+orga_id+",0)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button>";
	 }
	 $("#appDis"+orga_id).html(data);
	 }else{
	 $('#errors').html("Somthing went wrong please try again..!");
	 }

	 },
	 error : function(xhr, ajaxOptions, thrownError) {
	 $('#errors').html("There is error:" + thrownError);
	 }
	 });
	
	 }

	 function enableDisable(orga_id,status){
	
	 items = {};
	 items["orga_id"] = orga_id;
	 items["orga_status"] = status;
	 var jsonString = JSON.stringify(items);
	
	 $.ajax({
	 type : "post",
	 url : "./enableDisable",
	 contentType : "application/json",
	 dataType : "html",
	 data : jsonString,
	 cache : false,
	 success : function(response) {
	 if(response==1){
	 var data = '';
	 if(status=='0'){
	 //$("#appDis"+orga_id).addClass("disapprove").removeClass('approve');
	 data += "<button type='button' onclick='enableDisable("+orga_id+",1)' name='submit' class='btn btn-success'><i class='fa fa-eye'></i>&nbsp;Enable</button>";
	 }else{
	 //$("#appDis"+orga_id).addClass("approve").removeClass('disapprove');
	 data += "<button type='button' onclick='enableDisable("+orga_id+",0)' name='submit' class='btn btn-danger'><i class='fa fa-times'></i>&nbsp;Disable</button>";
	 }
	 $("#enaDis"+orga_id).html(data);
	 }else{
	 $('#errors').html("Somthing went wrong please try again..!");
	 }

	 },
	 error : function(xhr, ajaxOptions, thrownError) {
	 $('#errors').html("There is error:" + thrownError);
	 }
	 });
	
	 } */
</script>