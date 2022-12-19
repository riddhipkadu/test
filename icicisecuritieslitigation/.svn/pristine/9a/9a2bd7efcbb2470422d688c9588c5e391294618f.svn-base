
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<script src="appJs/Dashboard/dashboard.js"></script>
<script lang="javascript" type="text/javascript">

$(document).ready(function () {		
	getPieChart();
	
	/* $('#exportStatistics').on('click',ExportStatistics);
	$('#orgList3').on('change',function(){
		$("#locList2").val(0);
	});
	//Get All Filters
	$.ajax({
		type : "GET",
		url : "./getFilters",
		contentType : "application/json",
		dataType : "html",
		cache : false,
		success : function(filtersData) {
			var filterObject = jQuery.parseJSON(filtersData);

			jQuery.each(filterObject, function(i, item) {
				if (i === "loc") {
					jQuery.each(item, function(j, location) {
						$("#locList1").append(
								"<option value='"+j+"'>" + location
										+ "</option>");
						$("#locList2").append(
								"<option value='"+j+"'>" + location
										+ "</option>");
					});
				}

				if (i === "org") {
					jQuery.each(item, function(k, organization) {
						$("#orgList1").append(
								"<option value='"+k+"'>"
										+ organization
										+ "</option>");
						$("#orgList2").append(
								"<option value='"+k+"'>"
										+ organization
										+ "</option>");
						$("#orgList3").append(
								"<option value='"+k+"'>"
										+ organization
										+ "</option>");
					});
				}
				
				if(i === "typeOfTask"){
					jQuery.each(item, function(l, typeOfTask) {
						
						$("#typeOfTask").append(
								"<option value='"+l+"'>"
										+ typeOfTask
										+ "</option>");
					});
				}
			});
		}
	}); */
	
	//Load Graph By Impacts
	 $("#GraphByImpact").on('change',graphByImpacts);
	
	
	
    $('#jqChart1').bind('tooltipFormat', function (e, data) {
        var percentage = data.series.getPercentage(data.value);
        percentage = data.chart.stringFormat(percentage, '%.2f%%');
        return '<b>' + data.dataItem[0] + '</b><br />' +
                data.value + ' (' + percentage + ')';
    });
	
	$('#entityLevel').bind(
			'tooltipFormat',
			function(e, data) {
				return '<center><b>'+ data.x +'</b><br /><b>' + data.series.title + '</b><br />'
						+'<Strong>' + data.value +'</strong></center>';
	});
	
	 // Click Evevent & Dril-Down For Pie Chart
	   $('#jqChart1').bind('dataPointMouseUp', function (e, data) {
		   
		   		getDetailDataLitigationByStatus(data.dataItem[0]);
		
	   });
	 
	   $('#ProductBarChart').bind('dataPointMouseUp', function (e, data) {
	   		
	   		var caseCategory = encodeURIComponent(data.x);
	   	//	alert(" case "+caseCategory+" data "+data.x);
	   		var LitigationStatus = data.series.title;
	   		//window.open('./activityForProducts?data='+jsonString);
	   		getAllLitigationByCaseCategory(data.x, LitigationStatus);
	   		
	   	   });
	
	
	/* // Click Evevent & Dril-Down For Product Chart
	   $('#ProductBarChart').bind('dataPointMouseUp', function (e, data) {
		   window.open("./activityForProducts?productName="+data.x+"&riskId="+data.series.title+"&impact="+$("#GraphByImpact").val(),"_blank");
	   }); */
}); 


	function graphByImpacts() {
		var impact = $("#GraphByImpact").val();
		if (impact != "0") {
			getpieChartByImpact();
			getEntityChartByImpact();
			getLocationChartByImpact();
			getDepartmentChartByImpact();
			getCaseCategoryGraph();
		} else {
			getPieChart();
			getEntityChart();
			getLocationLevelgraph();
			getDepartmentLevelgraph();
			getCaseCategoryGraph();
		}
	}
</script>

<div class="modal fade" id="dialogBox" role="dialog">
				<div class="modal-dialog" style="width :1000px;">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #e26d1c;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								<span class="glyphicon glyphicon-remove-sign"></span> &nbsp;Error
							</h4>
						</div>
						<div class="modal-body">
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>


	<div class="page_container">
		<div class="container">
			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Dashboard</h2>
				</div>
			</div>
			<div style="clear: both"></div>
			 <!-- <div class="f_form_content">
				<h2>Time Period</h2>
				<form action="./getDashboardByDates" method="POST">
					<div class="col-md-12 light">
						<div class="col-md-12">
							<div class="col-md-3">
								<label>From :</label>
								<div id="from_currentDateCalender" class="input-group date"
									data-date-format="MM">
									<input id="from_date" class="form-control" type="text" readonly
										name="from_date"  /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-3">
								<label>To :</label>
								<div id="tillcurrentdate" class="input-group date"
									data-date-format="MM">
									<input id="to_date" class="form-control" type="text" readonly
										name="to_date" /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-3">
								<input style="margin-top: 20px;" type="submit"
									class="btn btn-primary" name="serach" value="Search Status" />
									<input style="margin-top: 20px;" type="submit" 
									class="btn btn-primary" name="search"/>
							</div>
							<div class="col-md-3">
								<label>Search By Type :</label> <select id="GraphByType"
									class="form-control" name="liti_type">
									<option value="0">-- Select --</option>
									<option value="NA">NA</option>
									<option value="By">By Company</option>
									<option value="Against">Against Company</option>
									<option value="Moderate">Moderate</option>
									<option value="Minor">Minor</option>
									<option value="Low">Low</option>
								</select>
							</div>
						</div>
					</div>
				</form>
			</div> -->
			<!-- Main content -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<!-- content -->
					<div class="row">
						<div class="row">
							<div class="col-md-12">
								<div class="box box-primary">
									<div class="f_form_content">
										<h2>Overall Litigation Status</h2>
										<div id="pieLoader">
											<img src="./images/Loader/graphLoader.gif" width="150px"
												height="150px" />
										</div>
										<center>
											<div id="overAllPieChart">
												<div class="box-body">
													<div style="height: 250px;" id="jqChart1"></div>
												</div>
											</div>
										</center>
									</div>
									</center>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<div class="box box-primary" id="deptLoaderGraph">
									<div class="f_form_content">
										<h2>Case Category Wise Status</h2>
										<center>
										<div id="productsLoader">
											<img src="./images/Loader/graphLoader.gif" width="150px"
												height="150px" />
										</div>
										</center>
										<div id="productsGraph">
											<%-- <div class="row" style="padding-bottom: 10px;">
												<div class="col-md-12">
													<div class="col-md-3">
														<label>Case Category :</label> 
														<select class="form-control" 
															id="productList" onchange="getCaseCategoryGraph();">
															<option value="0">-- Select --</option>
															<c:forEach items="${allLitiType }" var="liti_type">
															<option value="${liti_type.liti_type_id }">${liti_type.liti_type_name }</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div> --%>
											<div class="box-body">
												<div style="height: 350px;" id="ProductBarChart"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div> 
					</div>
					<!-- /.content -->
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
			function addDays(theDate, days) {
				return new Date(theDate.getTime() + days * 24 * 60 * 60 * 1000);
			}

			$(document).ready(function() {								
			
			//Assign Dates to textbox	
				var from_date = "";
				var to_date = "";
				
				<%SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
			if (session.getAttribute("dashboardFromDate") != null) {
				String stringDate_from = session.getAttribute("dashboardFromDate").toString();
				String stringDate_to = session.getAttribute("dashboardToDate").toString();

				Date from_date = sdf1.parse(stringDate_from);
				Date to_date = sdf1.parse(stringDate_to);%>
					from_date = "<%=sdf2.format(from_date)%>";
					to_date = "<%=sdf2.format(to_date)%>";
					
			    	 <%} else {%>
			    	 	from_date = "<%=sdf2.format(new Date())%>";
						to_date = "<%=sdf2.format(new Date())%>";
				<%}%>
		$('#from_date').val(from_date);
							$('#to_date').val(to_date);
							//END Assign Dates to textbox	

							// Destroy Validation popovers on click event
							$('#orgList1').click(function() {
								$('#orgList1').popover('destroy');
							});

							$('#orgList2').click(function() {
								$('#orgList2').popover('destroy');
							});

							$('#orgList3').click(function() {
								$('#orgList3').popover('destroy');
							});
							// end of validation popovers
							getPieChart();
							getEntityChart();
							getLocationLevelgraph();
							getDepartmentLevelgraph();
							getCaseCategoryGraph();
							getProductFilters();
						});
	</script>
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

