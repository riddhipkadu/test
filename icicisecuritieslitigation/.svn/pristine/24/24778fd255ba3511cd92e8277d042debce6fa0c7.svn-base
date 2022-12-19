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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Unit/Location List</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addLocation" class="btn btn-primary">Add Unit</a></center>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
		<div class="table_data1">
		<div class="container">
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Unit/Location Name</th>
                <th>Unit/Location Short Name</th>
                <th>Edit</th>
                <!-- <th>Approve / Disapprove</th>
                <th>Enable / Disable</th> -->
            </tr>
        </thead>
       
        <tbody>
           <c:forEach items="${allLocations}" var="location">
					<tr>
						<td>${location.loca_name}</td>
						<td>${location.loca_short_name}</td>
						<td class="edit"><a href="editLocation?loca_id=${location.loca_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
						</button></a></td>
						<%-- <c:choose>
							<c:when test="${location.loca_approval_status=='1'}">
								<td class="disapprove" id="appDis${location.loca_id}"><button type="button"
											onclick="approveDisapprove(${location.loca_id},'0')" name='submit'
											value="submit" class="btn btn-primary">
											<i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="approve" id="appDis${location.loca_id}"><button type="button"
								           name='submit' onclick="approveDisapprove(${location.loca_id},'1')" value="submit" class="btn btn-primary">
											<i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>
							</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${location.loca_enable_status=='1'}">
								<td class="view" id="enaDis${location.loca_id}"><button type="button"
											 name='submit'
											value="submit" onclick="enableDisable(${location.loca_id},0)"  class="btn btn-danger">
											<i class='fa fa-times'></i>&nbsp;Disable</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="view" id="enaDis${location.loca_id}"><button type="button"
								           name='submit' value="submit" onclick="enableDisable(${location.loca_id},1)" class="btn btn-success">
											<i class='fa fa-eye'></i>&nbsp;Enable</button>
							</td>
							</c:otherwise>
						</c:choose>
 --%>						
					</tr>
				</c:forEach>
           
        </tbody>
    </table>

				
				
			</div>
			
			
			
			
	
</div>
</div>




<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script >
/* function approveDisapprove(loca_id,status){
	
	items = {};
	items["loca_id"] = loca_id;
	items["loca_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./approveDisapproveLoc",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					$("#appDis"+loca_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='approveDisapprove("+loca_id+",1)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>";
				}else{
					$("#appDis"+loca_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='approveDisapprove("+loca_id+",0)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button>";
				}
				$("#appDis"+loca_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
}

function enableDisable(loca_id,status){
	
	items = {};
	items["loca_id"] = loca_id;
	items["loca_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./enableDisableLoc",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					//$("#appDis"+orga_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='enableDisable("+loca_id+",1)' name='submit' class='btn btn-success'><i class='fa fa-eye'></i>&nbsp;Enable</button>";
				}else{
					//$("#appDis"+orga_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='enableDisable("+loca_id+",0)' name='submit' class='btn btn-danger'><i class='fa fa-times'></i>&nbsp;Disable</button>";
				}
				$("#enaDis"+loca_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
}
 */
 </script>