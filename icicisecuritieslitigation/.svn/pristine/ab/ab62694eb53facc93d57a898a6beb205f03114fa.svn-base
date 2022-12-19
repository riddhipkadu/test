<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>




	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Entity</h2>
				<a href="./listOrganizations"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>


		<%-- <%
			if (session.getAttribute("smsg") != null) {
		%>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div id="fadeOutFun" class="alert alert-danger">
					<strong>Wrong!</strong> Something went wrong.
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
		<%
			}
		%> --%>

		<!--first form-->
		<sf:form commandName="organization" autocomplete="off"
			cssClass="form-horizontal" onsubmit="return validateForm();"
			action="./saveOrganization">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>


			<div class="f_form_content">
				<h2>Create Entity</h2>

				<!-- <div class="col-md-12"> -->
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-sm-3" for="sel1">Parent
							Entity:</label>
						<div class="col-sm-7">
							<sf:select path="orga_parent_id" cssClass="form-control">
								<option value="0">--Select--</option>
								<c:forEach items="${organization_list}" var="organization">
									<option value="${organization.orga_id}">${organization.orga_name}</option>
								</c:forEach>
							</sf:select>

						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-sm-3" for="sel1">Entity:</label>
						<div class="col-sm-7">
							<sf:input path="orga_name" id='orga_name' name="orga_name"
								cssClass="form-control" />
							<i class="asterisk_input"></i>
							<sf:errors path="orga_name" cssClass="errorBlock"></sf:errors>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="form-group">
						<label class="control-label col-sm-3" for="sel1">Short
							name for entity:</label>
						<div class="col-sm-7">
							<sf:input path="orga_short_name" id='orga_short_name'
								name="orga_short_name" cssClass="form-control" />
							<i class="asterisk_input"></i>
							<sf:errors path="orga_short_name" cssClass="errorBlock"></sf:errors>
						</div>
					</div>
				</div>
				<!-- </div> -->
				<div class="col-md-12 litigation_buttons">
					<br>
					<center>
						<input type="submit" onclick="myFunction()" value="Add"
							class="btn btn-myPrimary" />
					</center>

				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

	</div>

<script type="text/javascript">
	myFunction()
	{
		alert("dfsdf");
		bootbox
				.alert({
					message : '<label style="color:orange">Department name already Exist.</label>'
				});
	}
	$(document).ready(function() {

		$('#orga_name').click(function() {
			$('#orga_name').popover('destroy');
		});

		$('#orga_parent_id').click(function() {
			$('#orga_parent_id').popover('destroy');
		});

	});

	var orga_status;
	$("#orga_name").keyup(
			function() {
				var orga_name = $("#orga_name").val();
				var orga_id = 0;

				if (orga_name != '' && orga_name != null) { // && !typeof emp_id === 'undefined'

					items = {};
					items["orga_id"] = orga_id;
					items["orga_name"] = orga_name;

					var jsonString = JSON.stringify(items);
					$.ajax({
						type : "post",
						url : "./isOrgaNameExist",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(result) {
							if (result == 0) {
								orga_status = 0;
								$('#orga_name').popover('destroy');
							} else {
								orga_status = 1;
								$("#orga_name").attr("data-placement", "top");
								$("#orga_name").attr("data-content",
										"Entity already exists..!!");
								$('#orga_name').popover('show');
							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
				}
			});

	var orga_short_status;
	$("#orga_short_name")
			.onkeyup(
					function() {
						var orga_short_name = $("#orga_short_name").val();
						var orga_id = 0;

						if (orga_short_name != '' && orga_short_name != null) { // && !typeof emp_id === 'undefined'

							items = {};
							items["orga_id"] = orga_id;
							items["orga_short_name"] = orga_short_name;

							var jsonString = JSON.stringify(items);
							$
									.ajax({
										type : "post",
										url : "./isOrgaShortNameExist",
										contentType : "application/json",
										dataType : "html",
										data : jsonString,
										cache : false,
										success : function(result) {
											if (result == 0) {
												orga_short_status = 0;
												$('#orga_short_name').popover(
														'destroy');
											} else {
												orga_short_status = 1;
												$("#orga_short_name")
														.attr("data-placement",
																"top");
												$("#orga_short_name")
														.attr("data-content",
																"Entity short name is already exists..!!");
												$('#orga_short_name').popover(
														'show');
											}
										},
										error : function(xhr, ajaxOptions,
												thrownError) {
											$('#errors').html(
													"There is error:"
															+ thrownError);
										}
									});
						}
					});

	$('#organization').on('keyup keypress', function(e) {
		var keyCode = e.keyCode || e.which;
		if (keyCode === 13) {
			e.preventDefault();
			return false;
		}
	});

	function validateForm() {
		var oname = $('#orga_name').val();
		var short_name = $('#orga_short_name').val();

		if ($('#orga_parent_id').val() == 0 || $('#orga_parent_id').val() == "") {

			$("#orga_parent_id").attr("data-placement", "top");
			$("#orga_parent_id").attr("data-content",
					"Parent Entity name required");
			$('#orga_parent_id').popover('show');

			return false;
		} else {
			$('#orga_parent_id').popover('destroy');
		}

		if (oname == null || oname == "") {

			$("#orga_name").attr("data-placement", "top");
			$("#orga_name").attr("data-content", "Entity name required");
			$('#orga_name').popover('show');

			return false;
		} else if (orga_status == 1) {
			$("#orga_name").attr("data-placement", "top");
			$("#orga_name").attr("data-content", "Entity already exists..!!");
			$('#orga_name').popover('show');
			return false;
		} else {
			$('#orga_name').popover('destroy');
		}
		if (!(isNaN(oname))) {
			// $("#oerr").innerHTML="this is invalid name ";
			$("#orga_name").attr("data-placement", "top");
			$("#orga_name").attr("data-content", "Entity should be text");
			$('#orga_name').popover('show');
			return false;
		} else {
			$('#orga_name').popover('destroy');
		}

		if (!(oname.length > 2)) {
			// $("#oerr").innerHTML="this is invalid name ";
			$("#orga_name").attr("data-placement", "top");
			$("#orga_name").attr("data-content",
					"Entity should be valid length");
			$('#orga_name').popover('show');
			return false;
		} else {
			$('#orga_name').popover('destroy');
		}

		if (short_name == null || short_name == "") {

			$("#orga_short_name").attr("data-placement", "top");
			$("#orga_short_name").attr("data-content",
					"Entity short name required");
			$('#orga_short_name').popover('show');

			return false;
		} else if (orga_short_status == 1) {
			$("#orga_short_name").attr("data-placement", "top");
			$("#orga_short_name").attr("data-content",
					"Entity short name already exists..!!");
			$('#orga_short_name').popover('show');
			return false;
		} else {
			$('#orga_short_name').popover('destroy');
		}
		if (!(isNaN(short_name))) {
			// $("#oerr").innerHTML="this is invalid name ";
			$("#orga_short_name").attr("data-placement", "top");
			$("#orga_short_name").attr("data-content",
					"Entity short name should be text");
			$('#orga_short_name').popover('show');
			return false;
		} else {
			$('#orga_short_name').popover('destroy');
		}

		if (!(short_name.length == 3)) {
			// $("#oerr").innerHTML="this is invalid name ";
			$("#orga_short_name").attr("data-placement", "top");
			$("#orga_short_name").attr("data-content",
					"Entity short name should be maximum of 3 letters");
			$('#orga_short_name').popover('show');
			return false;
		} else {
			$('#orga_short_name').popover('destroy');
		}
	}

	function noPrecedingSpace(eID) {

		var elmt = document.getElementById(eID);

		elmt.addEventListener("keydown", function(event) {
			var strg = elmt.value;
			var lastChar = strg.charAt(strg.length - 2);

			if ((lastChar == " ") || (lastChar == "&nbsp;") || (strg == "")) {
				if (event.which === 32) {
					event.preventDefault();
				}
			}
			;
		});

	};
	noPrecedingSpace("orga_name");
</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
