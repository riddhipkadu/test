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
					<h2 style="color:#032BEC;font-size:24px;float:left;">List Reason of Rejection</h2>

				</div>
				</div>
				<%-- <div class="col-md-2">
				<center><a href="./addQuery" class="btn btn-primary">Raised Query</a></center>
				</div> --%>
			</div>
		
	<div style="clear:both"></div>

		<div class="table_data1">
		<!-- <div class="col-md-12" style="margin-bottom:10px;"> <a href="#" class="list-group-item col-md-2 btn btn-success"
										onClick="$('#example').tableExport({type:'excel',escape:'false',ignoreColumn:[8]});"><img
										src='images/reportIcons/xls.png' width="22" />Export XLS</a>
		  </div> -->
		
		<div class="container">
			<div class="col-md-12">
		  		<p><b>Total Records : </b><label id="count"></label></p>
			</div>
			<table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
        <thead>
            <tr style="background:#0B6EC3;color:#fff;">
                <th>Query Id</th>
                <th>SPOC name</th>
                <th>Reason by SPOC</th>
                <th>Admin name</th>
                <th>Reason By Admin</th>
            </tr>
        </thead>
       
        <tbody id="searchResult">
               <tr>
	                <td>1</td>
	                <td>${reasonList.req_reject_spoc_name}</td>
	                <td>${reasonList.req_reject_spoc_comments}</td>
	                <td>${reasonList.req_reject_admin_name}</td>
	                <td>${reasonList.req_reject_admin_comments}</td>
               </tr>
        </tbody>
    </table>
	</div>
</div>
</div>
</div>
<script>
$(document).ready(function(){
	
	document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
});

</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>