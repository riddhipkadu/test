<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



<div class="page_container">
<!--heading text-->

<div class="col-md-12">
<div class="col-md-9">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">User</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
				</div>
				<div class="col-md-3">
				<a href="./setAccessLevel" class="btn btn-primary">Set access level</a>
				&nbsp;
				<a href="./addUser" class="btn btn-primary">Add User</a>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	
			
			
				<div class="table_data1">
		<div class="container">
		

			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            	<tr style="background:#a72f14;color:#fff;">
                
					<td>First Name</td>
					<td>Last Name</td>
					<td>User Name</td>
					<td>Edit</td>
					<!-- <td>Approve/Disapprove</td> -->
					<td>Enable/Disable</td>
					
				</tr>
        </thead>
       
        <tbody>
          <c:forEach items="${allUsers}" var="user">
					<tr>
						<td>${user.user_first_name}</td>
						<td>${user.user_last_name}</td>
						<td>${user.user_username}</td>
						<td class="edit"><a href="editUser?user_id=${user.user_id}&user_name=${user.user_first_name} ${user.user_last_name} &userRole=${user.user_role_id}"><button type="button" onclick=""
							class="btn btn-primary"><i class="fa fa-pencil-square-o"></i>
							&nbsp;Edit
						</button></a></td>
						
						<%-- <c:choose>
							<c:when test="${user.user_approval_status=='1'}">
								<td class="disapprove" id="appDis${user.user_id}"><button type="button"
											onclick="approveDisapprove(${user.user_id},'0')" name='submit'
											value="submit" class="btn btn-primary">
											<i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="approve" id="appDis${user.user_id}"><button type="button"
								           name='submit' onclick="approveDisapprove(${user.user_id},'1')" value="submit" class="btn btn-primary">
											<i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>
							</td>
							</c:otherwise>
						</c:choose> --%>
						<c:choose>
							<c:when test="${user.user_enable_status=='1'}">
								<td class="view" id="enaDis${user.user_id}"><button type="button"
											 name='submit'
											value="submit" onclick="enableDisable(${user.user_id},0)"  class="btn btn-danger">
											<i class='fa fa-times'></i>&nbsp;Disable</button></td>
							</c:when>
							<c:otherwise>
                                 <td class="view" id="enaDis${user.user_id}"><button type="button"
								           name='submit' value="submit" onclick="enableDisable(${user.user_id},1)" class="btn btn-success">
											<i class='fa fa-eye'></i>&nbsp;Enable</button>
							</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
           
        </tbody>
    </table>

				
				
			</div>
			
			
			
			
	
</div>
</div>



<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script >
/* function approveDisapprove(user_id,status){
	
	items = {};
	items["user_id"] = user_id;
	items["user_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./approveDisapproveUser",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					$("#appDis"+user_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='approveDisapprove("+user_id+",1)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-up'></i>&nbsp;Approve</button>";
				}else{
					$("#appDis"+user_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='approveDisapprove("+user_id+",0)' name='submit' class='btn btn-primary'><i class='fa fa-thumbs-down'></i>&nbsp;Disapprove</button>";
				}
				$("#appDis"+user_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
}*/

function enableDisable(user_id,status){
	
	items = {};
	items["user_id"] = user_id;
	items["user_status"] = status;
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./enableDisableUser",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			if(response==1){
				var data = '';
				if(status=='0'){
					//$("#appDis"+orga_id).addClass("disapprove").removeClass('approve');
					data += "<button type='button' onclick='enableDisable("+user_id+",1)' name='submit' class='btn btn-success'><i class='fa fa-eye'></i>&nbsp;Enable</button>";
				}else{
					//$("#appDis"+orga_id).addClass("approve").removeClass('disapprove');
					data += "<button type='button' onclick='enableDisable("+user_id+",0)' name='submit' class='btn btn-danger'><i class='fa fa-times'></i>&nbsp;Disable</button>";
				}
				$("#enaDis"+user_id).html(data);
			}else{
				$('#errors').html("Somthing went wrong please try again..!");
			}

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
	
} 
</script>