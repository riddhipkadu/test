<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="col-md-10">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color: #a72f14; font-size: 24px; float: left;">Organogram
						List</h2>
					<a href="./listEntitiesMapping"><img
						src="images/DashboardIcons/backold.png" class="backButton"
						width="100px;"></a>

				</div>
			</div>
		</div>


		<div style="clear: both"></div>
		<!--first form-->



		<div class="table_data1">
			<div class="container">



				<table id="example" class="table table-striped table-bordered"
					width="100%" cellspacing="0">
					<thead>
						<tr style="background: #a72f14; color: #fff;">

							<th>Entity</th>
							<th>Unit</th>
							<th>Function</th>
							<th>List of Executors</th>
							<th>List of Evaluators</th>
							<th>List of Function Head</th>

						</tr>
					</thead>

					<tbody>
						<c:forEach items="${mappingList}" var="result">
							<tr>
								<td>${result.entityName}</td>
								<td>${result.unitName}</td>
								<td>${result.functionName}</td>
								<td><c:forEach items="${result.executorList}" var="exelist">
										<a href="editUser?user_id=${exelist[0]}&user_name=${exelist[1]}${exelist[2]}&userRole=${exelist[3]}"> ${exelist[1]}
											${exelist[2]}</a>
										<br />
									</c:forEach></td>
								<td><c:forEach items="${result.evaluatorList}"
										var="evalist">
										<a href="editUser?user_id=${evalist[0]}&user_name=${evalist[1]}${evalist[2]}&userRole=${evalist[3]}"> ${evalist[1]}
											${evalist[2]}</a>
										<br />
									</c:forEach></td>
								<td><c:forEach items="${result.functionHeadList}"
										var="funlist">
										<a href="editUser?user_id=${funlist[0]}&user_name=${funlist[1]}${funlist[2]}&userRole=${funlist[3]}"> ${funlist[1]}
											${funlist[2]}</a>
										<br />
									</c:forEach></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>
	</div>
</div>




<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
