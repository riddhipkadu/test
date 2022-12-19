<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<script> $(document).ready(function(){
	var username = "<%=request.getParameter("user_name")%>";
	var role = <%=request.getParameter("userRole")%>
	if(role ==1){
		$("#userName").val(username+" - "+"Admin User");
	}
	if(role ==2){
		$("#userName").val(username+" - "+"System User");
	}
	 if(role ==3){
		$("#userName").val(username+" - "+"POC User");
	}
	if(role ==4){
		$("#userName").val(username+" - "+"SPOC");
	}
	/*	if(role ==5){
		$("#userName").val(username+" - "+"Entity Head");
	}*/
});</script>
<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Edit
					Access Level</h2>
				<a href="./editUser?user_id=<%=request.getParameter("user_id")%>&user_name=<%=request.getParameter("user_name")%>"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
			</div>
			<div class="form-group">
				
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
								Name:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" name="userName"  id="userName"  readonly/>
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Entity:<i class="asterisk_input"></i></label>
							<div class="col-sm-9">
								<select class="form-control" name="user_organization_id"
									id="user_orga_id">
									<option value="0">Select</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Unit:</label>
							<div class="col-sm-9">
								<select class="form-control" name="user_location_id"
									id="user_loca_id">
									<option value="0">Select</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Function:</label>
							<div class="col-sm-9">
								<select class="form-control" name="user_department_id"
									id="user_dept_id">
									<option value="0">Select</option>
								</select>
							</div>
						</div>

						<div class="col-md-12 litigation_buttons">
							<br>

							<center>
								<button type="button" onclick="addAccessLevel()"
									class="btn btn-myPrimary">Add Access level</button>
								<%-- <a class="btn btn-myDefault" href="./editUser?user_id=<%=request.getParameter("user_id")%>&user_name=<%=request.getParameter("user_name")%>">Back</a> --%>
							</center>


						</div>
					</div>


					<div class="col-md-6 litigation_buttons">
						<div style="float: right">
						<div id="errors"></div>
							<button type="button" id="set_access" class="btn btn-myPrimary">Update
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
							<p>Some task assigned to user as Evaluator or Executor for this department please change compliance owner first!</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<script>
	$(document).ready(function() {
		$("#user_orga_id").on('change',  getLocaForUser);
		$("#user_loca_id").on('change', getDeptForUser);
		getMappedData();
		getOrgaForUser();
		 
		 
		});
	function getOrgaForUser(){ 
		 var userId = "<%=request.getParameter("user_id")%>";
			items = {};
			items["user_id"] = userId;
			var jsonString = JSON.stringify(items);
			var umap;
			var orga_name;
			var loca_name;
			var dept_name;
			$.ajax({
				type : "post",
				url : "./getOrgaforUserAccess",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(Orgalist) {
					var data = "";
					var Orgajson = $.parseJSON(Orgalist);
					data += "<option value = 0>--Select--</option>";
					$.each(Orgajson, function(key, value) {
						//display the key and value pair
						data += "<option value ="+ value['orga_id'] +">"+ value['orga_name'] + "</option>";
					});
                  $("#user_orga_id").html(data);
                  $("#user_loca_id").html("<option value = 0>--Select--</option>");
                  $("#user_dept_id").html("<option value = 0>--Select--</option>");
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
	 }
	 function getLocaForUser(){
		 var userId = "<%=request.getParameter("user_id")%>";
		 var orga_id = $("#user_orga_id").val();
		 if(orga_id !=0){
			items = {};
			items["user_id"] = userId;
			items["orga_id"] = orga_id;
			var jsonString = JSON.stringify(items);
			var umap;
			var orga_name;
			var loca_name;
			var dept_name;
			$.ajax({
				type : "post",
				url : "./getLocaforUserAccess",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(Localist) {
					var data = "";
					var Locajson = $.parseJSON(Localist);
					data += "<option value = 0>--Select--</option>";
					$.each(Locajson, function(key, value) {
						//display the key and value pair
						data += "<option value ="+ value['loca_id'] +">"+ value['loca_name'] + "</option>";
					});
               $("#user_loca_id").html(data);
               $("#user_dept_id").html("<option value = 0>--Select--</option>");
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		 }else{
			  $("#user_loca_id").html("<option value = 0>--Select--</option>");
              $("#user_dept_id").html("<option value = 0>--Select--</option>");
		 }
	 }
	 function getDeptForUser(){
		 var userId = "<%=request.getParameter("user_id")%>";
		 var orga_id = $("#user_orga_id").val();
		 var loca_id = $("#user_loca_id").val();
		 if(orga_id !=0 && loca_id !=0){
			items = {};
			items["user_id"] = userId;
			items["orga_id"] = orga_id;
			items["loca_id"] = loca_id;
			var jsonString = JSON.stringify(items);
			var umap;
			var orga_name;
			var loca_name;
			var dept_name;
			$.ajax({
				type : "post",
				url : "./getDeptforUserAccess",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(Localist) {
					var data = "";
					var Locajson = $.parseJSON(Localist);
					data += "<option value = 0>--Select--</option>";
					$.each(Locajson, function(key, value) {
						//display the key and value pair
						data += "<option value ="+ value['dept_id'] +">"+ value['dept_name'] + "</option>";
					});
               $("#user_dept_id").html(data);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		 }else{
              $("#user_dept_id").html("<option value = 0>--Select--</option>");
		 }
	 }
	function getMappedData(){ 
	 var userId = "<%=request.getParameter("user_id")%>";
			items = {};
			items["user_id"] = userId;
			var jsonString = JSON.stringify(items);
			var umap;
			var orga_name;
			var loca_name;
			var dept_name;
			$.ajax({
				type : "post",
				url : "./getUserMappedAccess",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(Accesslist) {
					var Accessjson = $.parseJSON(Accesslist);
					var table = document.getElementById("id_tblrow");
					
					$.each(Accessjson, function(key, value) {
						//display the key and value pair
						umap_id = value['umap_id'];
						orga_name = value['orga_name'];
						loca_name = value['loca_name'];
						dept_name = value['dept_name'];
                        var fun = umap_id;
                     
					    var row = table.insertRow(1);
					    row.id    = "row"+fun;
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
						element1.id 		= "rtd"+fun;
						element1.onclick    = function() { DisableUmapAccess(fun,0); };
						cell4.appendChild(element1);
					  
					});

				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
	function DisableUmapAccess(umap_id,status){
		items = {};
		items["umap_id"] = umap_id;
		items["umap_status"] = status;
		var jsonString = JSON.stringify(items);
		
		$.ajax({
			type : "post",
			url : "./checkExistTaskForUser",
			contentType : "application/json",
			dataType : "html",
			data : jsonString,
			cache : false,
			success : function(response) {
				if(response>0){
					$("#actionFail").modal('show');
				}else{
					var r = confirm("Are you sure you want remove access!");
					if (r == true) {
						$.ajax({
							type : "post",
							url : "./disableUmapAccess",
							contentType : "application/json",
							dataType : "html",
							data : jsonString,
							cache : false,
							success : function(response) {
								if(response==1){
									getOrgaForUser();
									$('#row'+umap_id).remove();
									
								}else{
									$('#errors').html("Somthing went wrong please try again..!");
								}
		
							},
							error : function(xhr, ajaxOptions, thrownError) {
								$('#errors').html("There is error:" + thrownError);
							}
						}); 
					} 
					    
				}
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
		
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
		var user_id = "<%=request.getParameter("user_id")%>";
		
		var orga_id
		var orga_name;
		var loca_name;
		var dept_name;
		if (user_orga_id != 0 || user_loca_id != 0) {
			items = {};
			items["user_id"] = user_id;
			items["orga_id"] = user_orga_id;
			items["loca_id"] = user_loca_id;
			items["dept_id"] = user_dept_id;
			var jsonString = JSON.stringify(items);

			$
					.ajax({
						type : "post",
						url : "./getRemainUserAccess",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(Accesslist) {
							var data = "";
							var Accessjson = $.parseJSON(Accesslist);
							var table = document.getElementById("id_tblrow");
							$.each(Accessjson,function(key, value) {
												//display the key and value pair
												var accessLevelItem = {};
												accessLevelItem["user_orga_id"] = value['orga_id'];
												accessLevelItem["user_loca_id"] = value['loca_id'];
												accessLevelItem["user_dept_id"] = value['dept_id'];
												orga_id = value['org_id'];
												orga_name = value['orga_name'];
												loca_name = value['loca_name'];
												dept_name = value['dept_name'];
												
												var org = value['orga_id'];
												var loc = value['loca_id'];
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
	$("#set_access").click(function() {
		$(this).hide();
		var access_lenght = accessLevel.length;
		var storeData = [];
		var user_orga_id = $("#user_orga_id").val();
		var user_user_id = "<%=request.getParameter("user_id")%>";
		itemsUserInfo = {};
		itemsAccessLevel = {};
		itemsUserInfo["user_user_id"] = user_user_id;
		itemsAccessLevel["user_access_level_data"] = jsonStringAccessLevel;
		storeData.push(itemsUserInfo);
		storeData.push(itemsAccessLevel);
		var jsonStringStoreData = JSON.stringify(storeData);
		if (user_orga_id != 0 && user_user_id != 0 && access_lenght!=0) {

			$.ajax({
				type : "POST",
				url : "./setAccessToUser",
				contentType : "application/json",
				dataType : "html",
				data : jsonStringStoreData,
				cache : false,
				success : function(res) {
					if (res == "success") {
						//getOrgaForUser();
						window.location.href="./listUsers";
					}else{
						$('#errors').html("Plase try again..!!");
					}

				}
			});
		}
	});
</script>