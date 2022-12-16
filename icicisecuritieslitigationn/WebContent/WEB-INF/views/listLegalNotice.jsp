<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<!-- Fail Modal -->
<div class="modal fade" id="dialogBox" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header" style="background-color: #e26d1c;">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">
					<span class="glyphicon glyphicon-remove-sign"></span> &nbsp;Error
				</h4>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>
<!-- Fail Modal END -->


<div class="page_container">
	<!--heading text-->

	<div class="col-md-12">
		<div class="col-md-10">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Legal
					Notices</h2>
				<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
			</div>
		</div>
		<div class="col-md-1">
			<button class="btn btn-primary" type="button" data-toggle="collapse"
				data-target="#collapseExample" aria-controls="collapseExample">
				Filter</button>
		</div>
		<div class="col-md-1">
			<center>
				<a href="./addLegalNotice" class="btn btn-primary">Create New</a>
			</center>
		</div>
	</div>
	<div style="clear: both"></div>

	<div class="collapse" id="collapseExample">
		<div class="card">
			<!--first form-->
			<sf:form role="form" class="form-horizontal" method="post">
				<div class="f_form_content">
					<h2>Search</h2>

					<div class="col-md-12">
						<input type="hidden" id="role"
							value="${sessionScope.sess_user_role}">

						<div class="col-md-4">
							<div class="form-group">
								<label for="user_orga_id" class="control-label col-sm-4"
									for="sel1">Entity :</label>
								<div class="col-sm-8">
									<select class="form-control" id="lega_noti_entity_id"
										name="lega_noti_entity_id">
										<c:forEach items="${allOrganizations}" var="organization">
											<option value="${organization.key}">${organization.value}</option>
										</c:forEach>
									</select><i class="asterisk_input"></i>

								</div>
							</div>
						</div>
						<!-- 	<div class="col-md-4">
					<div class="form-group">
						<label for="user_loca_id" class="control-label col-sm-4"
							for="sel1">Unit/Vertical :</label>
						<div class="col-sm-8">
							<select class="form-control" id="lega_noti_unit_id"
								name="lega_noti_unit_id">
								<option value="0">--Select--</option>

							</select>
						</div>
					</div>
				</div> -->

						<!-- <div class="col-md-4">
					<div class="form-group">
						<label for="user_dept_id" class="control-label col-sm-4"
							for="sel1">Function/ Location:</label>
						<div class="col-sm-8">
							<select class="form-control" id="lega_noti_function_id"
								name="lega_noti_function_id">
								<option value="0">--Select--</option>
							</select>
						</div>
					</div>
				</div> -->

						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">By/Against
									Company:</label>
								<div class="col-sm-8">
									<select class="form-control" name="lega_noti_by_against"
										id="lega_noti_by_against">
										<option value="NA">--Select--</option>
										<option value="By">By Company</option>
										<option value="Against">Against Company</option>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Category:</label>
								<div class="col-sm-8">
									<select class="form-control" name="lega_noti_category_id"
										id="lega_noti_category_id">
										<option value="0" selected="selected">--Select--</option>
										<c:forEach items="${allLegalCategory }" var="cat">
											<option value="${cat.lega_noti_category_id }">${cat.lega_noti_category_name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">Primary
										Responsible Person:</label>
									<div class="col-sm-8">
										<select class="form-control" name="lega_noti_assigned_id"
											id="lega_noti_assigned_id">
											<c:forEach items="${allUser}" var="users">
												<option value="${users.key}">${users.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">Secondary
										Responsible Person:</label>
									<div class="col-sm-8">
										<select class="form-control"
											name="lega_noti_secondary_responsible_person"
											id="lega_noti_secondary_responsible_person">
											<c:forEach items="${allUser}" var="users">
												<option value="${users.key}">${users.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">Third
										Responsible Person:</label>
									<div class="col-sm-8">
										<select class="form-control"
											name="lega_noti_third_responsible_person"
											id="lega_noti_third_responsible_person">
											<c:forEach items="${allUser}" var="users">
												<option value="${users.key}">${users.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">Legal
										notice - From:</label>
									<div class="col-sm-8">
										<div id="from_date_div" class="input-group date"
											data-date-format="MM">
											<input class="form-control" id="lega_noti_from_date"
												name="lega_noti_from_date" readonly type="text"><span
												class="input-group-addon"><i
												class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="control-label col-sm-4" for="sel1">To:</label>
									<div class="col-sm-8">
										<div id="to_date_div" class="input-group date"
											data-date-format="MM">
											<input class="form-control" id="lega_noti_to_date"
												name="lega_noti_to_date" readonly type="text"> <span
												class="input-group-addon"><i
												class="glyphicon glyphicon-calendar"></i></span>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12 litigation_buttons">
							<center>
								<input type="button" id="btnSearch" value="Search"
									class="btn btn-myPrimary" />
								<div id="noFound"></div>
							</center>

						</div>
						<div id="loader" style="display: none;">
							<center>
								<img src="./images/Loader/WaitLoader.gif" width="80px"
									height="85px" />
							</center>
						</div>
					</div>
			</sf:form>
		</div>
	</div>

	<div style="clear: both"></div>

	<div class="table_data1">
		<div class="col-md-12" style="margin-bottom: 10px;">
			<a href="#" class="list-group-item col-md-2 btn btn-success"
				onClick="$('#NoticeList').tableExport({type:'excel',escape:'false',ignoreColumn:[10]});"><img
				src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		</div>
		<div class="container">
			<div class="col-md-12">
				<p>
					<b>Total Records : </b><label id="count"></label>
				</p>
			</div>
			<table id="NoticeList" class="table table-striped table-bordered"
				width="100%" cellspacing="0">
				<thead>
					<tr style="background: #a72f14; color: #fff;">
						<th>Sr.No.</th>
						<th>Entity</th>
						<!-- <th>Unit/Vertical</th> -->
						<!-- <th>Function/Location</th> -->
						<th>Category</th>
						<th>Notice Date</th>
						<th>Sent/Received Date</th>
						<th>Deadline Date</th>
						<!-- <th>Comments</th> -->
						<!-- <th>Action Taken</th> -->
						<!-- <th>Next Action Item</th> -->
						<th>Responsible Person</th>
						<th>Second Responsible Person</th>
						<th>Third Responsible Person</th>
						<th>Reminder Date</th>
						<th>Status</th>
						<th>Action</th>
						<%-- <c:choose>
			      				<c:when test="${sessionScope.sess_user_role==1 || }">
							<th>Action</th>
							</c:when></c:choose> --%>
					</tr>
				</thead>

				<tbody id="searchResult">
					<c:forEach items="${listLegalNotice}" var="notice" varStatus="loop">
						<tr id="row_${notice.lega_noti_id}">
							<td>${loop.index+1}</td>
							<td>${notice.lega_noti_entity_name}</td>
							<%-- <td>${notice.lega_noti_unit_name}</td> --%>
							<%-- 	<td>${notice.lega_noti_function_name}</td> --%>
							<td>${notice.lega_noti_category_name}</td>
							<td>${notice.lega_noti_dated}</td>
							<td>${notice.lega_noti_recived_on}</td>
							<td>${notice.lega_noti_reply_deadline}</td>
							<td>${notice.lega_noti_assigned_to_name}</td>
							<td>${notice.lega_noti_secondary_responsible_person_name}</td>
							<td>${notice.lega_noti_third_responsible_person_name}</td>
							<td>${notice.lega_noti_reminder_date}</td>
							<td>${notice.lega_noti_status}<br /> <c:choose>
									<c:when test="${notice.lega_noti_status != 'Converted'}">
										<a
											href="./addLitigationByAccept?id=${notice.lega_noti_id}&type=noti">
											<button type="button" name='submit' value="submit"
												style="margin: 2px" class="btn btn-success">
												&nbsp;Convert To Litigation</button>
										</a>
									</c:when>
								</c:choose>
							</td>
							<!-- <td><a href="#" id="document">Document</a></td> -->
							<td><c:choose>
									<c:when
										test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_role==5 || notice.lega_noti_added_by == sessionScope.sess_user_id}">
										<a href="editLegalNotice?lega_noti_id=${notice.lega_noti_id}"><button
												type="button" name='submit' value="submit"
												class="btn btn-primary btn-block" style="margin-bottom: 2px">
												<i class='fa fa-pencil-square-o'></i>&nbsp;Edit
											</button></a>
										<button type="button" value="${notice.lega_noti_id}"
											name='delete_legal_notice' class="btn btn-danger btn-block"
											style="margin-bottom: 2px;">
											<i class='fa fa-trash'></i>&nbsp;Delete
										</button>
									</c:when>
									<c:otherwise>
								 		  No Access.
								 		</c:otherwise>
								</c:choose> <a href="legalNoticeUpdate?lega_noti_id=${notice.lega_noti_id}"><button
										type="button" onclick="" name="submit" value="submit"
										class="btn btn-primary btn-block" style="margin: 2px">
										<i class="fa fa-pencil-square-o"></i>&nbsp;Update
									</button></a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>

		</div>

	</div>
</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#lega_noti_category_id")
								.multiselect(
										{
											buttonWidth : '225px',
											enableFiltering : true,
											filterBehavior : 'text',
											enableCaseInsensitiveFiltering : true,
											onChange : function(option, checked) {
												var values = [];
												$(
														'#lega_noti_category_id option')
														.each(
																function() {
																	if ($(this)
																			.val() !== option
																			.val()) {
																		values
																				.push($(
																						this)
																						.val());
																	}
																});

												$('#lega_noti_category_id')
														.multiselect(
																'deselect',
																values);
											}

										});

						document.getElementById('count').innerHTML = ($('#NoticeList >tbody >tr').length);
					});

	$("#document")
			.click(
					function() {
						var htmlOutputForm = "<img src='images/Certi.jpg' width='100%' height='500%' style='overflow:auto'/><br/>";
						bootbox.dialog({
							title : "Document",
							message : htmlOutputForm
						});
					});
	$("#document1")
			.click(
					function() {
						var htmlOutputForm = "<img src='images/Certi.jpg' width='100%' height='500%' style='overflow:auto'/><br/>";
						bootbox.dialog({
							title : "Document",
							message : htmlOutputForm
						});

					});
</script>

<script src="appJs/LegalNotice/LegalNotice.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
