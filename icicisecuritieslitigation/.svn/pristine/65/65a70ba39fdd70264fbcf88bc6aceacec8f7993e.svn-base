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
					<h2 style="color:#a72f14; font-size: 24px; float: left;">Entity
						Mapping List</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
			</div>
			<div class="col-md-2">
				<center>
					<a href="./addEntitiesMapping" class="btn btn-primary">Add Entity Mapping</a>
				</center>
			</div>
			<%-- <div class="col-md-4">
				<center>
					<a href="./listAllMapping"
						class="btn btn-primary">Mapping List</a>
				</center>
			</div> --%>
		</div>


		<div style="clear: both"></div>
		<!--first form-->
		<div class="table_data1">
			<div class="container">
				<table id="example" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr style="background: #a72f14; color: #fff;">

							<th>Entity</th>
							<th>Unit</th>
							<th>Function</th>
							<th>Edit</th>
							<!-- <td>Approve/Disapprove</td>
							<td>Enable/Disable</td> -->
							<th>Delete</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach items="${entitiesMapping}" var="entityMapping">
							<tr>
								<td>${entityMapping[1]}</td>
								<td>${entityMapping[2]}</td>
								<td>${entityMapping[3]}</td>
								<td class="edit"><a href="editEntitiesMapping?enti_id=${entityMapping[0]}"><button type="button"
											onclick="" name='submit' value="submit"
											class="btn btn-primary">
											<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
										</button></a></td>

								<%-- <c:choose>
									<c:when test="${entityMapping[4]=='1'}">
										<td class="disapprove" id="appDis${entityMapping[0]}"><button
												type="button"
												onclick="approveDisapprove(${entityMapping[0]},'0')"
												name='submit' value="submit" class="btn btn-primary">
												<i class='fa fa-thumbs-down'></i>&nbsp;Disapprove
											</button></td>
									</c:when>
									<c:otherwise>
										<td class="approve" id="appDis${entityMapping[0]}"><button
												type="button" name='submit'
												onclick="approveDisapprove(${entityMapping[0]},'1')"
												value="submit" class="btn btn-primary">
												<i class='fa fa-thumbs-up'></i>&nbsp;Approve
											</button></td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${entityMapping[5]=='1'}">
										<td class="view" id="enaDis${entityMapping[0]}"><button
												type="button" name='submit' value="submit"
												onclick="enableDisable(${entityMapping[0]},0)"
												class="btn btn-danger">
												<i class='fa fa-times'></i>&nbsp;Disable
											</button></td>
									</c:when>
									<c:otherwise>
										<td class="view" id="enaDis${entityMapping[0]}"><button
												type="button" name='submit' value="submit"
												onclick="enableDisable(${entityMapping[0]},1)"
												class="btn btn-success">
												<i class='fa fa-eye'></i>&nbsp;Enable
											</button></td>
									</c:otherwise>
								</c:choose> --%>
								<td class="delete"><button type="button"
											onclick="deleteEntityMapping(${entityMapping[0]})" name='submit' value="submit"
											class="btn btn-danger">
											<i class='fa fa-pencil-square-o'></i>&nbsp;Delete
										</button></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script>
/* function approveDisapprove(enti_id,status){
	
	items = {};
	items["enti_id"] = enti_id;
	items["enti_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./approveDisapproveEntity",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					$("#appDis"+enti_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='approveDisapprove("+enti_id+",1)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>";
				}else{
					$("#appDis"+enti_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='approveDisapprove("+enti_id+",0)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button>";
				}
				$("#appDis"+enti_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
}

function enableDisable(enti_id,status){
	
	items = {};
	items["enti_id"] = enti_id;
	items["enti_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./enableDisableEntity",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					//$("#appDis"+orga_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='enableDisable("+enti_id+",1)' name='submit' class='btn btn-success'><i class='fa fa-eye'></i>&nbsp;Enable</button>";
				}else{
					//$("#appDis"+orga_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='enableDisable("+enti_id+",0)' name='submit' class='btn btn-danger'><i class='fa fa-times'></i>&nbsp;Disable</button>";
				}
				$("#enaDis"+enti_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
} */

function deleteEntityMapping(enti_id){
	items = {};
	items["enti_id"] = enti_id;
	var jsonString = JSON.stringify(items);
	var enti_id = enti_id;
	var arr = [];
	console.log(enti_id);
	$.ajax({
		type : "post",
		url : "./deleteEntityMapping",
		contentType : "text/html",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(allCounts) {
			//console.log(allCounts);
			count = $.parseJSON(allCounts);
			 $.each(count, function (index, value) {
			        //console.log(index + ":" + value);
			        arr.push(value);
			 });
			// console.log("one:" + arr[0] +" two: " +arr[1] + " three: " + arr[2]);
			        if(arr[0] != 0 || arr[1] !=0 ||arr[2] !=0){
			        	bootbox.alert("<strong>Can't Delete! </strong>This entity mapping is assigned to user");
			        }else{
			        	//bootbox.alert("Entity Mapping is being deleted");
			        	//alert("inner" + enti_id);
			        	bootbox.confirm("Are you sure you want to delete this Entity Mapping?", function(result) {
			        				
			        				if(result)
			        				{
			        					$.ajax({
			    			        		type : "post",
			    			        		url : "./deleteActualEntityMapping",
			    			        		contentType : "text/html",
			    			        		dataType : "html",
			    			        		data : jsonString,
			    			        		cache : false,
			    			        		success : function(deleteCount) {
			    			        			
			    			        			
			    			        			if(deleteCount){
				    			        			window.location.reload(true);

			    			        			}
			    			        			
			    			        		}
			    			        	
			    			        	});
			        				  
			        				}
			        				
			        				
			        				}); 
			        	
			        }
			
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
}

</script>