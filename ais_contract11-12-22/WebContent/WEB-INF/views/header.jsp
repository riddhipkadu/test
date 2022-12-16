<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lexcare :: Contract Management Tool</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta name="keywords" content="">
<meta name="discription" content="">
<meta name="author" content="">

<script src="js/jquery-1.11.1.min.js"></script>

<!-- <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> -->

<script src="js/jquery-ui-1.10.4.min.js"></script>
<script src="autoComplete/jquery-ui.js"></script>
<link rel="stylesheet" href="autoComplete/jquery-ui.css">

<!-- Load Bootbox -->
<script src="js/bootstrap.min.js"></script>
<script src="js/bootbox.min.js"></script>

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- IE Compatible  -->
<!--[if IE]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script src="http://www.jqchart.com/js/excanvas.js"></script>
<![endif]-->

<!-- Custom Chart CSS -->
<!-- <link href="charts/jquery-ui-1.10.4.min.css" rel="stylesheet">
<link href="charts/jqRangeSlider.css" rel="stylesheet">
<link href="charts/jqChart.css" rel="stylesheet"> -->

<!-- Full Calendar CSS -->
<link href="css/plugins/fullcalendar/fullcalendar.min.css"
	rel="stylesheet" type="text/css" />
<link href="css/plugins/fullcalendar/fullcalendar.print.css"
	rel="stylesheet" type="text/css" media='print' />

<link href="css/datepicker_new.css" rel="stylesheet" type="text/css" />
<!-- js files -->


<!-- Reveal CSS-->
<link href="css/reveal.css" rel="stylesheet" type="text/css" />

<!-- Application JS Files -->

<link rel="stylesheet" href="css/bootstrap-multiselect.css">

<style type="text/css">
.ui-autocomplete {
	max-height: 250px;
	
	overflow-y: auto;
	/* prevent horizontal scrollbar */
	overflow-x: hidden;
	border: 5px solid #222;
	position: absolute;
}

nav.navbar.navbar-defaults.navbar-fixed-top {
	background-color: #fff;
}

/* .nav>li>a:hover, .nav>li>a:focus {
	text-decoration: underline;
	background-color: #fff;
} */
.nav>li>a:hover, .nav>li>a:focus {
	text-decoration: none;
	background-color: #fff;
}

.underline {
	
}

a:hover {
	color: #054eff;
	font-family: Arial;
	font-size: 14px;
	border-bottom: 3px solid white;
}

a {
	color: black;
	font-family: Arial Rounded MT;
	font-size: 13px;
}
 /* .row1 {
    margin-right: -150px;
    margin-left: -150PX	;
}  */
</style>
</head>
<body>

	<!--navbar-->
	<nav class="navbar navbar-defaults navbar-fixed-top"> <c:choose>
		<c:when test="${sessionScope.sess_user_role != 0}">
			<div class="container-fluid">
				<div class="navbar-header">
					<center>
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse" data-target="#navbar"
								aria-expanded="false" aria-controls="navbar">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>



				<img class="logo" src="images/asahiglass.jpg" height="40" width="125">
			</div>
		</div>
		<div id="navbar" class="navbar-collapse collapse"
			style="x-overflow: scoll; max-height: 700px;">
			<ul class="nav navbar-nav header-menu-add-gap">
			<!-- <li><a href="./contract_dashboard">Dashboard</a></li> -->
				<%-- <c:choose>

				<img class="logo" src="images/asahiglass.jpg" height="50" width="150">
			</div>
		</div>
		<div id="navbar" class="navbar-collapse collapse"
			style="x-overflow: scoll; max-height: 700px;">
			<ul class="nav navbar-nav header-menu-add-gap">
				<%-- <c:choose>

							<img class="logo" src="images/litilogo1.png" height="50"
								width="150">
						</div>
				</div>
				<div id="navbar" class="navbar-collapse collapse"
					style="x-overflow: scoll; max-height: 700px;">
					<ul class="nav navbar-nav header-menu-add-gap">
						<%-- <c:choose>

				
				<c:when test="${sessionScope.sess_user_role==3}">
				<li><a href="./listNoticeRequest"><img class="menu"
						src="images/DashboardIcons/support.png">Notice Request</a></li>
						
						<li><a href="./listLitigationRequest"><img class="menu"
						src="images/DashboardIcons/support.png">Litigation Request</a></li>
						
						<li><a href="./listContractRequest"><img class="menu"
						src="images/DashboardIcons/support.png">Contract Request</a></li>
						
						<li><a href="./listQueryRequest"><img class="menu"
						src="images/DashboardIcons/support.png">Query Request</a></li>
				
				</c:when>
				<c:otherwise>
				<c:choose>
				<c:when test="${sessionScope.sess_user_role != 2}">
				
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					type="button" data-toggle="dropdown"><img class="menu"
						src="images/DashboardIcons/update.png">Request from POC
						&nbsp;<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<!-- <li><a href="./listLegalNotice">Notices</a></li> -->
						<li><a href="./listNoticeRequest">Notice Request</a></li>
						<li><a href="./listLitigationRequest">Litigation Request</a></li>
						<li><a href="./listContractRequest">Contract Request</a></li>
						<li><a href="./listQueryRequest">Query Request</a></li>
					</ul>
				</li>
				
				</c:when>
				</c:choose> --%>
						<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
					type="button" data-toggle="dropdown"><img class="menu"
						src="images/DashboardIcons/update.png">Litigation Management
						&nbsp;<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="./listLegalNotice">Notices</a></li>
						<li><a href="./listLitigation">Litigation Summary</a></li>
						<li><a href="./showLitigationCalendar">Litigation
								Calendar</a></li>

					</ul>
				</li> -->
						<li><a href="./dashboard"><img class="menu"
								src="images/DashboardIcons/dashboard12.png" height="30" width="30">Dashboard</a></li>
					<!-- 	<li><a href="http://103.224.247.216:8080/SunPharmaContractManagementV1.0/#/login"><img class="menu"
								src="images/DashboardIcons/logo-1.png">Contract Creation</a></li> -->
						<!-- <li><a href="./contract_calendar"><img class="menu"
								src="images/DashboardIcons/dashboard1.png">Calendar</a></li> -->
								
								
								<li class="dropdown"><a href="#" class="dropdown-toggle"
							type="button" data-toggle="dropdown"><img class="menu"
								src="images/DashboardIcons/avs.png">Contract Creation
								&nbsp;<span class="caret"></span></a>

							<ul class="dropdown-menu">
								<li><a href="./listClauseLib">Clause Library</a></li>
								<!-- <li><a href="./listContractTemplate">Contract Template</a></li>
								<li><a href="./listGenerateContract">Generate Contract</a></li> -->
							</ul></li>
							
							
								
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							type="button" data-toggle="dropdown"><img class="menu"
								src="images/DashboardIcons/logo-1.png">Contract Management
								&nbsp;<span class="caret"></span></a>

							<ul class="dropdown-menu">
								<li><a href="./listPreExecutionSummary">Pre-Execution
										Summary</a></li>
								<li><a href="./listExecutedContract">Executed Contracts</a></li>
								<!-- <li><a href="./listStandardFormContracts">Standard Form Contracts</a></li> -->
							</ul></li>
							
							
							<!-- <li><a href="./listPreExecutionSummary"><img
								class="menu" src="images/DashboardIcons/preexecuted1.jpg" height="30" width="30"/>Pre-Execution
										Summary</a></li>
										
										<li><a href="./listExecutedContract"><img
								class="menu" src="images/DashboardIcons/executed1.jpg" height="30" width="30"/>Executed Contracts</a></li>
							
							 -->
						<li><a href="./listStandardFormContracts"><img
								class="menu" src="images/DashboardIcons/sfc1.png" height="25" width="25"/>Standard Form Contracts</a></li>
						<li><a href="./listQuery"><img class="menu"
								src="images/DashboardIcons/11.png" height="25" width="25">Query</a></li>
						<!-- <li><a href="./masters"><img class="menu"
						src="images/DashboardIcons/update.png">Masters</a></li> -->

						<%-- </c:otherwise>
				</c:choose> --%>

						<li><a href="./techSupport"><img class="menu"
								src="images/DashboardIcons/support.png" height="20" width="20">Support</a></li>

						<c:choose>

							<c:when
								test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_role==7}">
								<li><a href="#" id="admin_menu_icon" ><img
										class="menu_sub" id="app_icon"
										src="images/DashboardIcons/app.png" ></a></li>
							</c:when>

						</c:choose>

						<li><a href="#" id="profile_icon"><img
								class="menu add_circle" src="images/dash_board/user.png" height="15" width="15"/></a></li>
						<li><a href="#" id="profile_icon"><strong> <%=session.getAttribute("fullName")%>

							</strong></a></li>
						</center>
					</ul>
				</div>
			
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose> </nav>
	<c:choose>
		<c:when
			test="${sessionScope.sess_user_role==1 || sessionScope.sess_user_role==5 || sessionScope.sess_user_role==7}">
			<!--navbar end-->
			<!--app gallery open-->
			<!-- <div style="position: fixed; z-index: 999;">
				<div >
					<div class="container">
						<div class="row1">
							<div class="col-md-8"></div>
							<div class="col-md-12" id="slide">
								<img class="triangle" src="images/triangle.png">
								<div class="row1">
									<div class="col-md-12">
									<div class="col-md-1"></div>
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./listOrganizations"><img
													src="images/dash_board/headoffice.png" width="75px;"
													height="75px;" />
													<p style="margin-top: 5px;">Entity/Company Name</p></a>
											</div>
										</div>
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./listLocations"><img
													src="images/dash_board/location_new.png" width="75px;"
													height="75px;">
													<p style="margin-top: 5px;">Unit/Vertical</p></a>
											</div>
                                         </div>
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./listDepartments"><img
													src="images/dash_board/department.jpg" width= "75px;"
													height="75px;">
													<p style="margin-top: 5px;">Function
														/Department</p></a>
											</div>
										</div>
									</div>
								

								 <div class="row">
									<div class="col-md-12">
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./listDesignations"><img
													src="images/dash_board/Designation.png" width="75px;"
													height="75px;">
													<p style="margin-top: 5px;">Designations</p></a>
											</div>
										</div>
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;"
													href="./listEntitiesMapping"><img
													src="images/dash_board/UserEntityMapping.png" width="75px;"
													height="75px;">
													<p style="margin-top: 5px;">Entity Mapping</p></a>
											</div>
										</div>
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./listUsers"><img
													src="images/dash_board/AddUser.png" width="75px;"
													height="75px;">
													<p style="margin-top: 5px;">Users</p></a>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-12">
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./masters"><img
													src="images/master/master3.png" width="75px;"
													height="75px;">
													<p style="margin-top: 5px;">
														<strong>Masters</strong>
													</p></a>
											</div>
										</div>
										</div>
										<div class="col-md-1">
											<div class="icon1">
												<a style="text-decoration: none;" href="./logs"><img
													src="images/master/logs.png" width="75px;" height="75px;">
													<p style="margin-top: 5px;">
														<strong>Logs</strong>
													</p></a>
											</div>
										</div>
										<div class="col-md-4">
									<div class="icon3">
										<a style="text-decoration: none;"
											href="./listTechSupport"> <img
											src="images/dash_board/user_Activity_log.png" height="75px"
											width="75px">
											<p style="margin-top: 5px;">Support log</p>
										</a>
									</div>
								</div>
										 <div class="col-md-4">
									<div class="icon3">
										<a style="text-decoration: none;" href="./ShowActivityLog">
											<img src="images/dash_board/user_Activity_log.png"
											height="75px" width="75px">
											<p style="margin-top: 5px;">Activity Log</p>
										</a>
									</div>
								</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> -->
		
		
		<div style="position: fixed; z-index: 999;">
		<div class="app" style="position: absolute;">
			<div class="container">
				<div class="row">
					<div class="col-md-8"></div>
					<div class="col-md-4" id="slide">
						<img class="triangle" src="images/triangle.png">
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="icon1">
										<a style="text-decoration: none;" href="./listOrganizations"><img
											src="images/dash_board/headoffice.png" width="75px;"
											height="75px;" />
											<p style="margin-top: 5px;">Entity/Company Name</p></a>
									</div>
								</div>
								<div class="col-md-4">
									<div class="icon1">
										<a style="text-decoration: none;" href="./listLocations"><img
											src="images/dash_board/location_new.png" width="75px;"
											height="75px;">
											<p style="margin-top: 5px;">Unit/Location</p></a>
									</div>

								</div>
								<div class="col-md-4">
									<div class="icon1">
										<a style="text-decoration: none;" href="./listDepartments"><img
											src="images/dash_board/department.jpg" width="75px;"
											height="75px;">
											<p style="margin-top: 5px;">Function/
											Department</p></a>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="icon2">
										<a style="text-decoration: none;" href="./listDesignations"><img
											src="images/dash_board/Designation.png" width="75px;"
											height="75px;">
											<p style="margin-top: 5px;">Designations</p></a>
									</div>
								</div>
								<div class="col-md-4">
									<div class="icon2">
										<a style="text-decoration: none;" href="./listEntitiesMapping"><img
											src="images/dash_board/UserEntityMapping.png" width="75px;"
											height="75px;">
											<p style="margin-top: 5px;">Entity Mapping</p></a>
									</div>
								</div>
								<div class="col-md-4">
									<div class="icon2">
										<a style="text-decoration: none;" href="./listUsers"><img
											src="images/dash_board/AddUser.png" width="75px;"
											height="75px;">
											<p style="margin-top: 5px;">Users</p></a>
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-12">
								<div class="col-md-4">
									<div class="icon3">
										<a style="text-decoration: none;" href="./masters"><img
											src="images/master/master3.png" width="75px;" height="75px;">
											<p style="margin-top: 5px;">
												<strong>Masters</strong>
											</p></a>
									</div>
								</div>
								<div class="col-md-4">
									<div class="icon3">
										<a style="text-decoration: none;" href="./logs"><img
											src="images/master/logs.png" width="75px;" height="75px;">
											<p style="margin-top: 5px;">
												<strong>Logs</strong>
											</p></a>
									</div>
								</div>
								<!-- <div class="col-md-4">
									<div class="icon3">
										<a style="text-decoration: none;"
											href="./listTechSupport"> <img
											src="images/dash_board/user_Activity_log.png" height="75px"
											width="75px">
											<p style="margin-top: 5px;">Support log</p>
										</a>
									</div>
								</div> -->
								<!--  <div class="col-md-4">
									<div class="icon3">
										<a style="text-decoration: none;" href="./ShowActivityLog">
											<img src="images/dash_board/user_Activity_log.png"
											height="75px" width="75px">
											<p style="margin-top: 5px;">Activity Log</p>
										</a>
									</div>
								</div> --> 
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		
		</c:when>
	</c:choose>
	<!--app gallery end-->
	<!--profile start-->
	<c:choose>
		<c:when test="${sessionScope.sess_user_role != 0}">
			<div style="position: fixed; z-index: 999;">
				<div class="pro_info" style="position: absolute;">
					<div class="container">
						<div class="row1">
							<div class="col-md-10"></div>
							<div class="col-md-4" id="profile">
								<img class="triangle1" src="images/triangle.png">
								<div class="col-md-5">
									<img class="img-circle" height="100px" width="100px"
										src="images/dash_board/user.png">
								</div>
								<div class="col-md-7">
									<h4>
										<%=session.getAttribute("fullName")%>
									</h4>
									<div class="pro_info">
										<div class="col-md-7">
											<a href="./myAccount"><button type="button"
													class="btn btn-primary">My Account</button></a>
										</div>

										<div class="col-md-5">
											<a href="./userLogout" type="button" class="btn btn-success">Sign
												out</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:when>

	</c:choose>

	<!--profile end-->