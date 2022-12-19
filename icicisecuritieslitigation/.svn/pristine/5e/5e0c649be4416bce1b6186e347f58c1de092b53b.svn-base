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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Designation List</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addDesignation" class="btn btn-primary">Add Designation</a></center>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	
			
			
				<div class="table_data1">
		<div class="container">
		

			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Designation Name</th>
                
                <th>Edit</th>
                <!-- <th>Approve / Disapprove</th>
                <th>Enable / Disable</th> -->
				
				
            </tr>
        </thead>
       
        <tbody>
          
				<c:forEach items="${allDesignations}" var="designation">
					<tr>
						<td>${designation.desi_name}</td>
						<td class="edit"><a href="editDesignation?desi_id=${designation.desi_id}"><button type="button" onclick=""
							name='submit' value="submit" class="btn btn-primary">
							<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
						</button></a></td>
						<%-- <c:choose>
							<c:when test="${designation.desi_approval_status=='1'}">
								<td class="disapprove" id="appDis${designation.desi_id}"><button type="button"
											onclick="approveDisapprove(${designation.desi_id},'0')" name='submit'
											value="submit" class="btn btn-primary">
											<i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="approve" id="appDis${designation.desi_id}"><button type="button"
								           name='submit' onclick="approveDisapprove(${designation.desi_id},'1')" value="submit" class="btn btn-primary">
											<i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>
							</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${designation.desi_enable_status=='1'}">
								<td class="view" id="enaDis${designation.desi_id}"><button type="button"
											 name='submit'
											value="submit" onclick="enableDisable(${designation.desi_id},0)"  class="btn btn-danger">
											<i class='fa fa-times'></i>&nbsp;Disable</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="view" id="enaDis${designation.desi_id}"><button type="button"
								           name='submit' value="submit" onclick="enableDisable(${designation.desi_id},1)" class="btn btn-success">
											<i class='fa fa-eye'></i>&nbsp;Enable</button>
							</td>
							</c:otherwise>
						</c:choose>
 --%>					</tr>
				</c:forEach>
           
        </tbody>
    </table>

				
				
			</div>
			
			
			
			
	
</div>
</div>




<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script >
/* function approveDisapprove(desi_id,status){
	
	items = {};
	items["desi_id"] = desi_id;
	items["desi_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./approveDisapproveDesi",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					$("#appDis"+desi_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='approveDisapprove("+desi_id+",1)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>";
				}else{
					$("#appDis"+desi_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='approveDisapprove("+desi_id+",0)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button>";
				}
				$("#appDis"+desi_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
}

function enableDisable(desi_id,status){
	
	items = {};
	items["desi_id"] = desi_id;
	items["desi_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./enableDisableDesi",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					//$("#appDis"+orga_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='enableDisable("+desi_id+",1)' name='submit' class='btn btn-success'><i class='fa fa-eye'></i>&nbsp;Enable</button>";
				}else{
					//$("#appDis"+orga_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='enableDisable("+desi_id+",0)' name='submit' class='btn btn-danger'><i class='fa fa-times'></i>&nbsp;Disable</button>";
				}
				$("#enaDis"+desi_id).html(data);
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