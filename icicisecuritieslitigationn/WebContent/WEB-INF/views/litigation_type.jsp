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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Litigation Type List</h2>
					<a href="./dashboard"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>

				</div>
				</div>
				<div class="col-md-2">
				<center><a href="./addLitigationType" class="btn btn-primary">Add Litigation Type</a></center>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	
			
			
				<div class="table_data1">
		<div class="container">
		

			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#a72f14;color:#fff;">
                
                <th>Litigation Name</th>
                
                <th>Edit</th>
                <th>Enable / Disable</th>
				
				
            </tr>
        </thead>
       
        <tbody>
           <c:forEach items="${list_litigation}" var="litigation">
					<tr>
						<td>${litigation.liti_name}</td>
					</tr>
				</c:forEach>
           
        </tbody>
    </table>

			</div>
	
</div>
</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>