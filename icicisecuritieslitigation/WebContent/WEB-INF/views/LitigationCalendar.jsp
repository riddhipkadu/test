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
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Litigation
					Calendar</h2>
				<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<form class="form-horizontal" role="form">
			<div class="f_form_content">
				<h2>Search</h2>

				<div class="col-md-12">


					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity :</label>
							<div class="col-sm-7">
								<select class="form-control" id="orga_id">
									<c:forEach items="${allOrganizations}" var="orga">
									 <option value="${orga.key}">${orga.value}</option>
									</c:forEach>
								</select>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Unit :</label>
							<div class="col-sm-7">
								<select class="form-control" id="loca_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Function :</label>
							<div class="col-sm-7">
								<select class="form-control" id="dept_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>
                  </div>
				<!--	<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Executor :</label>
							<div class="col-sm-7">
								<select class="form-control" id="tmap_pr_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Evaluator :</label>
							<div class="col-sm-7">
								<select class="form-control" id="tmap_rw_id">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div>

					 <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Category :</label>
							<div class="col-sm-7">
								<select class="form-control" id="sel1">
									<option>All</option>
									<option>Electronics</option>

								</select>
							</div>
						</div>
					</div> 
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Frequency :</label>
							<div class="col-sm-7">
								<select class="form-control" id="frequency">
									<option value="0">--Select--</option>
								</select>
							</div>
						</div>
					</div> -->
					<!-- <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Risk :</label>
							<div class="col-sm-7">
								<select class="form-control" id="sel1">
									<option>All</option>
									<option>Electronics</option>

								</select>
							</div>
						</div>
					</div> -->
       
			<%--  <div class="col-md-12" style="margin-top: 10px;">
					<div class="col-md-8">
						<div class="form-group">
								<label class="control-label col-sm-4" for="sel1">Primary Responsible Person: </label>
								<div class="col-sm-8">
									<select name="liti_assigned_to" items="${AllUsers}"
										class="form-control">
									</select>
								</div>
							</div>
					</div>
			</div>   --%>
				  <div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Primary Responsible Person:</label>
							<div class="col-sm-8">
								<select class="form-control" id="liti_assigned_to" items="${AllUsers}" >
									 <option value="0">--Select--</option>
									 <c:forEach items="${AllUsers}" var="user">
									 <option value="${user[0]}">${user[1]} </option>
									 </c:forEach>
									
								</select>
							</div>
						</div>
					</div>   


				<div class="col-md-12 litigation_buttons">
					<br>
					<center>
						<button type="button" id="liti_calendar" class="btn btn-myPrimary">Search</button>
						
						<!-- <button type="button" name="back" id="back"
							class="btn btn-myDefault"
							onclick="window.location.href ='./dashboard' ">Back</button> -->
						<div id="noFound"> </div>
					<div class="alert alert-info col-md-4" id="info" style="display: block; margin-left: 359px; margin-top: 10px;">
						<strong>Info!</strong> No result found.
					</div>
					<div id="loader_table" style="display: none;">
						<center><img src="./images/Loader/WaitLoader.gif" width="80px" height="85px"/></center>
					</div>
					</center>
					
				</div>
			</div>
		</form>

		

		<div style="clear: both"></div>
		<div class="box box-primary">
			<div class="box-body">
				<div id="calendar"></div>
			</div>
		</div>

	</div>



<script type="text/javascript">


$(document).ready(function() {
	
	$('#calendar').fullCalendar({
		header : {
			left : 'prev,next today',
			center : 'title',
			right : 'month,agendaWeek,agendaDay'
		},
		buttonText : {
			today : 'today',
			month : 'month',
			week : 'week',
			day : 'day'
		},
		//Random default events
		/*  events : eventsAdd,
		eventRender: function(event, element) {
	        element.attr('title', event.tooltip);
	    }, */ 
		editable : false,
		droppable : false
	});
	
	var allLitigation = '${Liti_details}';
	var data = $.parseJSON(allLitigation);
	var eventsAdd = new Array();
	$.each(data,function(key,value){
		var HearingDate = new Date(value["liti_next_hearing_date"].replace(/ /g,'T'));
		event = new Object();
		event.title = "Litigation Id[" + value["liti_client_id"] + "]";
		event.start = HearingDate; 
		event.allDay = true;
		event.url = './litigationDetails?liti_id='+ value["liti_id"];
		
		eventsAdd.push(event);
	
	});
	$('#calendar').fullCalendar('addEventSource',eventsAdd);
	
	
 });

</script>
<script src="appJs/Calendar/calendar.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
