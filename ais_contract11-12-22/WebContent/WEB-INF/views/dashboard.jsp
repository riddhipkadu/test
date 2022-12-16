
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page
	import="java.util.Date,java.text.SimpleDateFormat,java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<script src="appJs/Dashboard/dashboard.js"></script>
<script lang="javascript" type="text/javascript">


$(document).ready(function () {	
	
	getPieChart();
	
	getPieChart1();
	
	//Load Graph By Impacts
	 $("#GraphByImpact").on('change',graphByImpacts);
	
	
	
    $('#jqChart1').bind('tooltipFormat', function (e, data) {
        var percentage = data.series.getPercentage(data.value);
        percentage = data.chart.stringFormat(percentage, '%.2f%%');
        return '<b>' + data.dataItem[0] + '</b><br />' +
                data.value + ' (' + percentage + ')';
    });
	
	
	
	 //Load Graph entity By Impacts - Akash jadhav
	 $("#GraphByImpact").on('change',graphByImpacts);
	
	
	
   $('#jqChart2').bind('tooltipFormat', function (e, data) {
       var percentage = data.series.getPercentage(data.value);
       percentage = data.chart.stringFormat(percentage, '%.2f%%');
       return '<b>' + data.dataItem[0] + '</b><br />' +
               data.value + ' (' + percentage + ')';
   });
	
	
	 // Click Evevent & Dril-Down For Pie Chart
	   $('#jqChart1').bind('dataPointMouseUp', function (e, data) {
		   
		   		getDetailDataPreContractByStatus(data.dataItem[0]);
		
	   });
	 
	// Click Evevent & Dril-Down For Entity Pie Chart - Akash Jadhav
	
	   $('#jqChart2').bind('dataPointMouseUp', function (e, data) {
		   
		   getDetailsDataEntity(data.dataItem[0]);
		
	   });
	 
	
	 
	   $('#ProductBarChart').bind('dataPointMouseUp', function (e, data) {
	   		
	   		var caseCategory = encodeURIComponent(data.x);
	   	//	alert(" case "+caseCategory+" data "+data.x);
	   		var PreContractStatus = data.series.title;
	   		//window.open('./activityForProducts?data='+jsonString);
	   		getAllPreContractByCaseCategory(data.x, PreContractStatus);
	   		
	   	   });
	
	
	
}); 



	function graphByImpacts() {
		var impact = $("#GraphByImpact").val();
		if (impact != "0") {
			getpieChartByImpact();
			getCaseCategoryGraph();
		} else {
			getPieChart1();
			getPieChart();
			getCaseCategoryGraph();
		}
	}
</script>

<div class="modal fade" id="dialogBox" role="dialog">
	<div class="modal-dialog" style="width: 1000px;">

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

<div class="page_cont_padd">
	<div class="page_container">
		<div class="container">
			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color: #054eff; font-size: 24px; float: left;">Dashboard</h2>
				</div>
			</div>
			<div style="clear: both"></div>

			<!-- Main content -->
			<div class="box box-primary">
				<div class="box-header with-border">
					<!-- content -->
					<div class="row">
						<div class="row">
							<div class="col-md-12">

								<div class="box box-primary">
									<div class="f_form_content">
										<h2>Overall PreContract Status Graph</h2>
										<div id="pieLoader">
											<img src="./images/Loader/graphLoader.gif" width="150px"
												height="150px" />
										</div>
											<div class="row">
												<div class="col-sm-4">
													<div id="chartContainer"
														style="height: 275px; width: 100%;"></div>
												</div>
												<center>
													<div id="overAllPieChart">
														<div class="box-body">
															<div style="height: 250px;" id="jqChart1"></div>
														</div>
													</div>
												</center>
											</div>



									</div>
								</div>
								
								
								
								
								

							<%-- 	<div class="box box-primary">
									<div class="f_form_content">
										<h2>Overall Entity Wise Graph</h2>
										<div id="pieLoader">
											<img src="./images/Loader/graphLoader.gif" width="150px"
												height="150px" />
										</div>
										
											<div class="row">
												<div class="col-sm-4">


													<div id="chartContainer"
														style="height: 275px; width: 100%;"></div>
												</div>
												<center>
													<div id="overAllPieChart1">
														<div class="box-body">
															<div style="height: 250px;" id="jqChart2"></div>
														</div>
													</div>
												</center>
											</div>

									


									</div>
								</div>
 --%>





								<div class="row">
									<div class="col-md-12">
										<div class="box box-primary" id="deptLoaderGraph">
											<div class="f_form_content">
												<h2>Contract Type Wise Status</h2>

												<div id="productsLoader">
													<img src="./images/Loader/graphLoader.gif" width="150px"
														height="150px" />
												</div>



												<div id="productsGraph">

													<div class="box-body">
														<div style="height: 350px;" id="ProductBarChart"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
		</div>

		
		<script type="text/javascript"
			src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>