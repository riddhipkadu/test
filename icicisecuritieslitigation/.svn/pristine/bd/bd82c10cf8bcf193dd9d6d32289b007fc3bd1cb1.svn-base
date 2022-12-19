<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Set
					Access Level</h2>
				<a href="./listUsers"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<form class="form-horizontal" role="form">
			<div class="f_form_content">
				<h2>Set Access</h2>

				<div class="col-md-12">
					<div class="col-md-6">

						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">User
								Name:</label><i class="asterisk_input"></i>
							<div class="col-sm-9">
								<select class="form-control" name="user_user_id"
									id="user_user_id">
									<option value="0">Select User</option>
									<c:forEach items="${allUsers}" var="user">
										<option value="${user[0]}">${user[12]} ${user[13]} -
										 <c:choose>
										       <c:when test="${user[18]==1}">Admin User </c:when>
										       <c:when test="${user[18]==2}">Normal User </c:when>
										       <c:otherwise>
  										 
  										 </c:otherwise>
  										</c:choose>
  										
										</option>
									</c:forEach>

								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Entity:<i class="asterisk_input"></i></label>
							<div class="col-sm-9">
								<select class="form-control" name="user_organization_id"
									id="user_orga_id">
									<option value="0">--Select--</option>
									<c:forEach items="${allOrganizations}" var="organization">
										<option value="${organization.orga_id}">${organization.orga_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Unit:</label>
							<div class="col-sm-9">
								<select class="form-control" name="user_location_id"
									id="user_loca_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Function:</label>
							<div class="col-sm-9">
								<select class="form-control" name="user_department_id"
									id="user_dept_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>

						<div class="col-md-12 litigation_buttons">
							<br>

							<center>
								<button type="button" onclick="addAccessLevel()"
									class="btn btn-myPrimary">Add Access level</button>
									<!-- <button type="button" class="btn btn-myDefault"
								onclick="window.location.href ='./listUsers' ">Back</button> -->
							</center>

						</div>
					</div>


					<div class="col-md-6 litigation_buttons">
						<div style="float: right">
							<button type="button" id="set_access" class="btn btn-myPrimary">Save
								Access</button>
						</div>
						<div id="total_count"></div>
						<table border="1" id="id_tblrow"
							class="table table-striped table-bordered"
							style="float: left; width: 100%; clear: both;margin-top: 10px;">
							<thead>
								<tr>
									<td>Entity</td>
									<td>Unit</td>
									<td>Function</td>
									<td>Action</td>
								</tr>

							</thead>

						</table>
					</div>





				</div>



			</div>
		</form>

		<div style="clear: both"></div>

	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script>
	$(document).ready(function() {

		$("#user_orga_id").on('change', getAllLocationForOrganization);
		$("#user_loca_id").on('change', getAllDepartmentsByLocation);
	});
	function getAllLocationForOrganization() {
		var user_orga_id = $("select#user_orga_id option:selected").attr(
				'value');

		$('#user_dept_id').html('<option value="0">--Select--</option>');

		if (user_orga_id > 0) {
			items = {};
			items['tmap_orga_id'] = user_orga_id;

			var jsonString = JSON.stringify(items);

			$.ajax({
						type : "post",
						url : "./getAllLocationsForOrganization",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(locationlist) {
							var data = "";
							var locationjson = $.parseJSON(locationlist);

							data += "<option value = 0>--Select--</option>";
							for (var i = 1; i <= Object.keys(locationjson).length; i++) {
								for (i in locationjson) {
									data += "<option value ="+ i +">"
											+ locationjson[i] + "</option>";
								}
							}
							$('#user_loca_id').html(data);
							
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#user_loca_id').html('<option value="0">--Select--</option>');
			$('#user_dept_id').html('<option value="0">--Select--</option>');
		}

	}

	function getAllDepartmentsByLocation() {
		var user_orga_id = $("select#user_orga_id option:selected").attr(
				'value');
		var user_loca_id = $("select#user_loca_id option:selected").attr(
				'value');

		if (user_loca_id > 0) {
			items = {};
			items["tmap_orga_id"] = user_orga_id;
			items["tmap_loca_id"] = user_loca_id;

			var jsonString = JSON.stringify(items);

			$
					.ajax({
						type : "post",
						url : "./getAllDepartmentsForOrgAndLoc",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(departmentlist) {
							var data = "";
							var departmentjson = $.parseJSON(departmentlist);

							data += "<option value = 0>--Select--</option>";
							for (var i = 1; i <= Object.keys(departmentjson).length; i++) {
								for (i in departmentjson) {
									data += "<option value ="+ i +">"
											+ departmentjson[i] + "</option>";
								}

							}
							$('#user_dept_id').html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#user_dept_id').html('<option value="0">--Select--</option>');
		}
	}

	var accessLevel = [];
	var jsonStringAccessLevel;
	var cnt = 0;
	function addAccessLevel() {

		if (validateAccess() == true) {
			var orgcnt = 0;
			var loccnt = 0;
			var deptcnt = 0;
			var deptallcnt = 0;

			var user_orga_id = $("select#user_orga_id option:selected").attr(
					'value');
			var user_loca_id = $("select#user_loca_id option:selected").attr(
					'value');
			var user_dept_id = $("select#user_dept_id option:selected").attr(
					'value');
			var flag = 1;

			var accessLevelItem = {};
			accessLevelItem["user_orga_id"] = user_orga_id;

			accessLevelItem["user_loca_id"] = user_loca_id;
			accessLevelItem["user_dept_id"] = user_dept_id;

			var cnts = accessLevel.length;
			if (cnts != 0) {

				$
						.each(
								accessLevel,
								function(k, v) {

									if (v["user_orga_id"] == accessLevelItem["user_orga_id"]) {
										orgcnt = orgcnt + 1;
										if (v["user_loca_id"] == accessLevelItem["user_loca_id"]
												|| v["user_loca_id"] == 0) {
											loccnt = loccnt + 1;
											if (v["user_dept_id"] == accessLevelItem["user_dept_id"]
													|| v["user_dept_id"] == 0) {
												deptcnt = deptcnt + 1;
											}
											if (v["user_dept_id"] == 0) {
												deptallcnt = deptallcnt + 1;
											}

										}

									}

								});

				if (orgcnt >= 1 && accessLevelItem["user_loca_id"] == 0) {
					flag = 0;
				}

				if (loccnt >= 1 && accessLevelItem["user_dept_id"] == 0) {

					flag = 0;
				}
				if (deptallcnt > 0) {
					flag = 0;
				}

				$
						.each(
								accessLevel,
								function(k, v) {

									if (accessLevelItem["user_orga_id"] == v["user_orga_id"]
											&& accessLevelItem["user_loca_id"] == v["user_loca_id"]
											&& accessLevelItem["user_dept_id"] == v["user_dept_id"]) {
										//	console.log(v);
										flag = 0;
									}

								});

				if (flag) {
					addAccessLevels();
					//accessLevel.push(accessLevelItem);
					flag = 1;

				}

			} else {
				//first time push element in array..
				addAccessLevels();
				//accessLevel.push(accessLevelItem);
			}

		}

	}
	function validateAccess() {
		var oname = $('#user_orga_id').val();
		if (oname == 0) {

			$("#user_orga_id").attr("data-placement", "top");
			$("#user_orga_id").attr("data-content", " Select Entity ");
			$('#user_orga_id').popover('show');

			return false;
		}

		else {
			$('#user_orga_id').popover('destroy');
		}

		return true;

	}
	function addAccessLevels() {
		var user_orga_id = $("#user_orga_id").val();
		var user_loca_id = $("#user_loca_id").val();
		var user_dept_id = $("#user_dept_id").val();

		var orga_id
		var orga_name;
		var loca_name;
		var dept_name;
		if (user_orga_id != 0 || user_loca_id != 0) {
			items = {};
			items["user_orga_id"] = user_orga_id;
			items["user_loca_id"] = user_loca_id;
			items["user_dept_id"] = user_dept_id;
			var jsonString = JSON.stringify(items);

			$.ajax({
						type : "post",
						url : "./getAccessList",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(Accesslist) {
							var data = "";
							var Accessjson = $.parseJSON(Accesslist);
							var table = document.getElementById("id_tblrow");
							$.each(
											Accessjson,
											function(key, value) {
												//display the key and value pair
												var accessLevelItem = {};
												accessLevelItem["user_orga_id"] = value['org_id'];
												accessLevelItem["user_loca_id"] = value['loc_id'];
												accessLevelItem["user_dept_id"] = value['dept_id'];
												orga_id = value['org_id'];
												orga_name = value['orga_name'];
												loca_name = value['loca_name'];
												dept_name = value['dept_name'];

												var org = value['org_id'];
												var loc = value['loc_id'];
												var dept = value['dept_id'];
												var row_id = org+""+loc+""+dept;
												
											    var row = table.insertRow(1);
											    row.id    = "row"+row_id;
											    var cell1 = row.insertCell(0);
											    var cell2 = row.insertCell(1);
											    var cell3 = row.insertCell(2);
											    var cell4 = row.insertCell(3);
											    
											    cell1.innerHTML = orga_name;
											    cell2.innerHTML = loca_name;
											    cell3.innerHTML = dept_name;
											    
											   // var cell4 			= row.insertCell(1);
												var element1 		= document.createElement("button");
												element1.type       = "button";
												element1.className  = "btn btn-sm btn-danger";
												element1.name 		= "button";
												element1.innerHTML  = "<i class='fa fa-trash' title='Remove'></i> &nbsp;Remove";
												element1.id 		= "rtd"+row_id;
												element1.onclick    = function() { DeleteAccess(org,loc,dept); };
												cell4.appendChild(element1); 
												accessLevel.push(accessLevelItem);
											});

							jsonStringAccessLevel = JSON.stringify(accessLevel);
							//console.log(jsonStringAccessLevel);
							$("#total_count")
									.html(
											"<p style='color:green;'><i style='color:orange;' class='fa fa-exclamation-triangle'></i></i> You Have Added <strong style='color:orange;'>"
													+ accessLevel.length
													+ "</strong> Mapping<p>");
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
	}
	function DeleteAccess(org,loc,dept){
		  $.each(accessLevel, function(key, val) {
			     if(val.user_orga_id == org && val.user_loca_id == loc && val.user_dept_id == dept) 
			       {
				      accessLevel.splice(key, 1);
				      $('#row'+org+""+loc+""+dept).remove();
			       } 
			});
		  jsonStringAccessLevel = JSON.stringify(accessLevel);
		  $("#total_count")
			.html(
					"<p style='color:green;'><i style='color:orange;' class='fa fa-exclamation-triangle'></i></i> You Have Added <strong style='color:orange;'>"
							+ accessLevel.length
							+ "</strong> Mapping<p>");
	 }
	$("#set_access").click(function(){ 
		$(this).hide();
		var access_lenght = accessLevel.length;
		var storeData = [];
		var user_orga_id = $("#user_orga_id").val();
		var user_user_id = $("#user_user_id").val();
		var user_user_emp_id = $("#user_user_emp_id").val();
		itemsUserInfo = {};
		itemsAccessLevel = {};
		itemsUserInfo["user_user_id"] = user_user_id;
		//itemsUserInfo['user_user_emp_id'] = user_user_emp_id;
		itemsAccessLevel["user_access_level_data"] = jsonStringAccessLevel;
		storeData.push(itemsUserInfo);
		storeData.push(itemsAccessLevel);
		var jsonStringStoreData = JSON.stringify(storeData);
		if(user_orga_id !=0 && user_user_id !=0 && access_lenght != 0){
			
			$.ajax({
				type : "POST",
				url : "./setAccessToUser",
				contentType : "application/json",
				dataType : "html",
				data : jsonStringStoreData,
				cache : false,
				success : function(res) { 
					if(res=="success"){
						window.location.href="./listUsers";
					}
					
				}
			});
		}
	});
</script>