<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<div class="page_cont_padd">
	<div class="page_container">
		<div class="container">
		
		<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Logs</h2>
					<!-- <a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->

				</div>
				</div>
		
		<!-- <div class="col-md-10">
		<div class="header_button">
			<h3>Compliance Manager</h3>
			<a href="./dashboard"><img style="margin-top: -12px; margin-left: 18px; padding-left: 0px; padding-right: 0px; margin-right: 0px;" src="images/DashboardIcons/backold.png" class="menu" width="100px;"></a>
		</div>	
		</div>	 -->	
			<div class="f_form_content" style="margin-top: 60px;">
				<div class="col-md-12">

				    <div class="col-md-2">
						<a href="./listLogs">
				<center><img src="images/master/history2.png" height="110px" width="120px"></center>
							<h4 style="text-align: center;">Logs</h4>
						</a>
					</div> 
 					<div class="col-md-2">
						<a href="./listTechSupport">
							<center>
								<img src="images/master/supportlog.png" height="110px" width="120px">
							</center>
							<h4 style="text-align: center;">Support log</h4>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
