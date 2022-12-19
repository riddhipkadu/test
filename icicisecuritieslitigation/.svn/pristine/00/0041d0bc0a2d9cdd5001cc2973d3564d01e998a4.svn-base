
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



<div class="page_container">
	<!--heading text-->


	<div style="clear: both"></div>
	<!--first form-->
	<sf:form class="form-horizontal" role="form" commandName="editLawFirm"
		autocomplete="off" action="./updateLawFirm"
		onsubmit="return validateForm();" method="POST">
		<sf:errors path="*" cssClass="errorBlock"></sf:errors>
		<div class="col-md-10">

			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Law
					Firm</h2>
				<a href="./listLawFirm"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>

		<div style="clear: both"></div>

		<div class="f_form_content">
			<h2>Update</h2>

			<div class="col-md-12">


				<div class="col-md-6">
					<div class="form-group" style="display: none">
						<label class="control-label col-sm-5" for="sel1"> </label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="lawf_id"
								id="lawf_id" name="lawf_id" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="sel1"> Law Firm
							Name :</label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="lawf_name"
								id="lawf_name" name="lawf_name"
								onkeypress="return blockSpecialChar(event)" />
							<i class="asterisk_input"></i>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-5" for="sel1">Contact
							Person Name :</label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control"
								path="lawf_contact_person" id="lawf_contact_person"
								name="lawf_contact_person"
								onkeypress="return blockSpecialChar(event)" />
							<i class="asterisk_input"></i>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="sel1">Contact
							no :</label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="lawf_mobile_no"
								id="lawf_mobile_no" name="lawf_mobile_no" />
							<i class="asterisk_input"></i>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="sel1"> Email
							Id:</label>
						<div class="col-sm-7">
							<sf:input type="text" class="form-control" path="lawf_email_id"
								id="lawf_email_id" name="lawf_email_id" />

						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-5" for="sel1"> Address:</label>
						<div class="col-sm-7">
							<sf:textarea rows="4" cols="40" path="lawf_address"
								id="lawf_address" name="lawf_address"></sf:textarea>
						</div>
					</div>

				</div>

			</div>

			<div class="col-md-12 litigation_buttons">
				<button type="submit" class="btn btn-myPrimary"
					style="margin-left: 340px;">Update</button>

			</div>
		</div>
	</sf:form>

	<div style="clear: both"></div>
</div>


<script src="appJs/LawFirm/law_firm.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$('#lawf_name').click(function() {
			$('#lawf_name').popover('destroy');
		});

	});

	var lawf_status;
	$("#lawf_name").keyup(
			function() {
				var lawf_name = jQuery.trim($("#lawf_name").val());
				var lawf_id = 0;

				if (lawf_name != '' && lawf_name != null) { // && !typeof emp_id === 'undefined'

					items = {};
					items["lawf_id"] = lawf_id;
					items["lawf_name"] = lawf_name;

					var jsonString = JSON.stringify(items);
					$.ajax({
						type : "post",
						url : "./isLawFirmNameExist",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(result) {
							//alert(result+" resulatt");
							if (result == 0 || result == '0') {
								lawf_status = 0;
								$('#lawf_name').popover('destroy');
							} else {
								lawf_status = 1;
								$("#lawf_name").attr("data-placement", "top");
								$("#lawf_name").attr("data-content",
										"Law Firm Name already exists..!!");
								$('#lawf_name').popover('show');
								return false;
							}

						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
				}
			});

	$('#addLawFirm').on('keyup keypress', function(e) {
		var keyCode = e.keyCode || e.which;
		if (keyCode === 13) {
			e.preventDefault();
			return false;
		}

	});
</script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
