<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->

<div class="col-md-12">
<div class="col-md-9">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Calender</button>-->
					<h2 style="color:#a72f14;font-size:24px;float:left;">Paid Fees</h2>
					<%-- <a href="./litigationDetails?liti_id=${liti_id }"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> --%>
				</div>
				</div>
				<div class="col-md-3">
				<a href="./payCounselFees?counsel_fees_id=${counsel_fees_id}" class="btn btn-primary">Pay fees</a>
				</div>
			</div>
			
		
	<div style="clear:both"></div>
	<!--first form-->
	
			
			
				<div class="table_data1">
		<div class="container">
		

			
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
                
               <tr style="background:#a72f14; color: #fff;">
					<td>SN</td>
					<td>Invoice No</td>
					<td>Invoice Date</td>
					<td>Comments</td>
					<td>Paid fees</td>
					<td>Document</td>
					<td>Action</td> 
					
				</tr>
            
        </thead>
       
        <tbody>
          <c:forEach items="${listPaidFees}" var="fees" varStatus="loop">
					<tr>
						<td>${loop.index+1}</td>
						<td>${fees.cpaid_invoice_no}</td>
						<td>${fees.cpaid_invoice_date}</td>
						<td>${fees.lcou_comments}</td>
						<td>${fees.cpaid_fees_amount} ${fees.cpaid_currency_code}</td>
						<td>
							<c:choose>
							 <c:when test="${fn:length(fees.fees_doc)==0}">
							   NA
							</c:when>
							<c:otherwise>
							   <c:forEach items="${fees.fees_doc}" var="doc">
							<a href="./downloadLitigationFeesDocument?fdoc_id=${doc.fdoc_id} ">
							   ${doc.fdoc_original_file_name}</a><br/>
							  </c:forEach>
							</c:otherwise>
							</c:choose> 
                         </td>
						<td class="edit">
						<c:if test="${fees.lcoun_fees_added_by==sessionScope.sess_user_id || sessionScope.sess_user_role==1}">
						    <a href="editCounselPaidFees?cpaid_id=${fees.cpaid_id}"><button type="button" onclick=""
							class="btn btn-primary"><i class="fa fa-pencil-square-o"></i>
							&nbsp;Edit
						</button></a>
						</c:if>
						
						</td>
						
						</tr>
				</c:forEach>
           
        </tbody>
    </table>

				
				
			</div>
			
			
			
			
	
</div>
</div>
</div>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
