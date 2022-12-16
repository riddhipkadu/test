<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<style>
.form-group {
	padding: 10px;
}

.control-label {
	text-align: left;
}
</style>

<script type="text/javascript">
function validateForm()
{
		 var oname = $('#file').val();
	 if (oname == null || oname == "") {
    	 
    	 $( "#file" ).attr( "data-placement", "top" );
         $( "#file" ).attr( "data-content", "First Select File" );
         $('#file').popover('show');
		
            return false;
        }
	 else{
		 	$('#file').popover('destroy');
	 	}
}

$(document).ready(function(){
	
	 $('#file').click(function(){
	        $('#file').popover('destroy');
	    });
	 
	
	if ('${firstLogin}' == "1") {			
			window.open('./ChangePassword');
		}
	});
</script>
<div class="container">
	<div class="page_container">
		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Account</h2>
				<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
			</div>
		</div>
		<br>
		
		<c:forEach items="${userInfo}" var="user">
		<div class="row">
			<div class="col-md-12">
				<div class="f_form_content">
					<div class="col-md-7">
						<div class="col-md-12">
							<h2>Personal Info.</h2>
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1"> Full
									Name :</label>
								<div class="col-sm-8"><span>${user[0]} ${user[1]}</span></div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">
									Username :</label>
								<div class="col-sm-8"><span>${user[2]}</span></div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1"> Role :</label>
								<div class="col-sm-8"><span>${user[3]}</span></div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">
									Employee Id :</label>
								<div class="col-sm-8"><span>${user[4]}</span></div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1"> Mobile
									No :</label>
								<div class="col-sm-8"><span>${user[5]}</span></div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1"> E-Mail
									:</label>
								<div class="col-sm-8"><span>${user[6]}</span></div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">
									Change Password :</label>
								<div class="col-sm-8">
									<span><a href="./ChangePassword" style="text-decoration: none;"><input type="button" name="chngpwd" value="Change Password" /></a></span>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="f_form_content">
								<h2>Organisational Profile.</h2>
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">
										Designation :</label>
									<div class="col-sm-8"><span>${user[7]}</span></div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">
										Entity :</label>
									<div class="col-sm-8"><span>${user[8]}</span></div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">
										Unit :</label>
									<div class="col-sm-8"><span>${user[9]}</span></div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">
										Function :</label>
									<div class="col-sm-8"><span>${user[10]}</span></div>
								</div>
							</div>
						</div>
					</div>
						
						<div class="col-md-3" style="text-align: left;">
							<div class="f_form_content">
								<div class="col-md-12" >
									<h2>Profile Pic.</h2>
									<img width="200px" height="175px"
										src="./getProfilePic">
								</div>
							</div>
							<form role="uploadPic" method="POST" onsubmit="return validateForm()" Action="./uploadProfilePic" enctype="multipart/form-data">
							<div class="col-md-12">
								<input style="margin-left: -34px;" class="btn btn-danger" id="file" type="file" name="profile_pic" />
								<br/>
								<input style="margin-left: 80px;" type="submit" class="btn btn-warning" name="submit" value="Upload" />
							</div>
							</form>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
	</div>

</div>