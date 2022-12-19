<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<!-- <script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script> -->

<script src="highchart/highcharts.js"></script>
<script src="highchart/exporting.js"></script>
<script src="highchart/export-data.js"></script>

<div class="page_cont_padd">
	<div class="page_container">
		<div style="clear: both"></div>
		<div class="row">
			<div class="col-md-6 light">
				<div class="f_form_content">
					<h2>Data</h2>
					<form name="dashboardForm"
						class="ng-pristine ng-valid-date ng-invalid ng-invalid-required">
						<div id="container"
							style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
					</form>
				</div>
			</div>
			<div class="col-md-6 light">
				<div class="f_form_content">
					<h2>Data</h2>
					<form name="dashboardForm"
						class="ng-pristine ng-valid-date ng-invalid ng-invalid-required">
						<div id="containers"
							style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
					</form>
				</div>
			</div>

		</div>
	</div>
</div>


<script src="appJs/ExecutedContracts/executed_contracts.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

<script>
	// Make monochrome colors
	var pieColors = (function() {
		var colors = [], base = Highcharts.getOptions().colors[0], i;

		for (i = 0; i < 10; i += 1) {
			// Start out with a darkened base color (negative brighten), and end
			// up with a much brighter color
			colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
		}
		return colors;
	}());

	// Build the chart
	Highcharts.chart('container', {
		chart : {
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false,
			type : 'pie'
		},
		title : {
			text : 'Company wise %'
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				colors : pieColors,
				dataLabels : {
					enabled : true,
					format : '<b>{point.name}</b><br>{point.percentage:.1f} %',
					distance : -50,
					filter : {
						property : 'percentage',
						operator : '>',
						value : 4
					}
				}
			}
		},
		series : [ {
			name : 'Share',
			data : [ {
				name : 'ABC',
				y : 29.41
			}, {
				name : 'XYZ',
				y : 11.84
			}, {
				name : 'PQR',
				y : 10.85
			}, {
				name : 'UVW',
				y : 4.67
			}, {
				name : 'POP',
				y : 4.18
			}, {
				name : 'Other',
				y : 7.05
			} ]
		} ]
	});
</script>

<script>
	Highcharts
			.chart(
					'containers',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Data'
						},
						xAxis : {
							type : 'category'
						},
						yAxis : {
							title : {
								text : 'Review of all Task status'
							}

						},
						legend : {
							enabled : false
						},
						plotOptions : {
							series : {
								borderWidth : 0,
								dataLabels : {
									enabled : true,
									format : '{point.y:.1f}%'
								}
							}
						},

						tooltip : {
							headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
							pointFormat : '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
						},

						series : [ {
							name : 'Brands',
							colorByPoint : true,
							data : [ {
								name : 'Pending',
								y : 56.33,
								drilldown : 'Pending'
							}, {
								name : 'Sent for review',
								y : 24.03,
								drilldown : 'Sent for review'
							}, {
								name : 'Finalised',
								y : 10.38,
								drilldown : 'Finalised'
							}, {
								name : 'Closed or Executed',
								y : 4.77,
								drilldown : 'Closed or Executed'
							}, {
								name : 'Other',
								y : 0.91,
								drilldown : 'Other'
							} ]
						} ],
						drilldown : {
							series : [
									{
										name : 'Pending',
										id : 'Pending',
										data : [ [ 'v11.0', 24.13 ],
												[ 'v8.0', 17.2 ],
												[ 'v9.0', 8.11 ],
												[ 'v10.0', 5.33 ],
												[ 'v6.0', 1.06 ],
												[ 'v7.0', 0.5 ] ]
									},
									{
										name : 'Sent for review',
										id : 'Sent for review',
										data : [ [ 'v40.0', 5 ],
												[ 'v41.0', 4.32 ],
												[ 'v42.0', 3.68 ],
												[ 'v39.0', 2.96 ],
												[ 'v36.0', 2.53 ],
												[ 'v43.0', 1.45 ],
												[ 'v31.0', 1.24 ],
												[ 'v35.0', 0.85 ],
												[ 'v38.0', 0.6 ],
												[ 'v32.0', 0.55 ],
												[ 'v37.0', 0.38 ],
												[ 'v33.0', 0.19 ],
												[ 'v34.0', 0.14 ],
												[ 'v30.0', 0.14 ] ]
									},
									{
										name : 'Finalised',
										id : 'Finalised',
										data : [ [ 'v35', 2.76 ],
												[ 'v36', 2.32 ],
												[ 'v37', 2.31 ],
												[ 'v34', 1.27 ],
												[ 'v38', 1.02 ],
												[ 'v31', 0.33 ],
												[ 'v33', 0.22 ],
												[ 'v32', 0.15 ] ]
									},
									{
										name : 'Closed Or Executed',
										id : 'Closed Or Executed',
										data : [ [ 'v8.0', 2.56 ],
												[ 'v7.1', 0.77 ],
												[ 'v5.1', 0.42 ],
												[ 'v5.0', 0.3 ],
												[ 'v6.1', 0.29 ],
												[ 'v7.0', 0.26 ],
												[ 'v6.2', 0.17 ] ]
									},
									{
										name : 'Others',
										id : 'Others',
										data : [ [ 'v12.x', 3.34 ],
												[ 'v28', 1.24 ],
												[ 'v27', 1.27 ],
												[ 'v29', 6.16 ] ]
									} ]
						}
					});
</script>