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
					<h2 style="color:#032BEC;font-size:24px;float:left;">List Raised Queries</h2>

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
                <th>Query</th>
                <th>Query raised at</th>
                <th>Query Raised By</th>
                <th>Query reply</th>
                <th>Query replied at</th>
                <th>Query reply By</th>
                <th>Action</th>
            </tr>
        </thead>
       
        <tbody id="searchResult">
            <c:forEach items="${queryList}" var="query" varStatus="loop">
               <tr>
	                <td>${loop.index+1}</td>
	                <td>${query[1]}</td>
	                <td>${query[8]}</td>
	                <td>${query[2]} ${query[3]}</td>
	                <td>${query[4]}</td>
	                <td>${query[9]}</td>
	                <td>${query[5]} ${query[6]}</td>
	                <td> 
	                <%-- <c:if test="${empty query[4]}"> <input type="button" onclick="raiseQueryReply(${query[0]})" class="btn btn-success" value="Reply Query"/></c:if> --%>
	                <c:choose>
  						<c:when test="${empty query[4]}">
  						   <input type="button" onclick="raiseQueryReply(${query[0]})" class="btn btn-success" value="Reply Query"/>
  						</c:when>
  						<c:otherwise>
                          Reply sent
  						</c:otherwise>
					</c:choose>
	                </td>
               </tr>
            </c:forEach>
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

function raiseQueryReply(id){
	
	var form = "<table class='table table-striped table-bordered'><tr><td><label>Query Reply:</label></td><td><textarea class='form-control' name='queryreply' id='queryreply'></textarea></td></tr></table>";
	bootbox.confirm(form, function (value) {
		//alert("Value is :"+value);
		if(value != false){
		var queryreply = $('#queryreply').val();
		  //alert("reason is the :"+$('#reason_disapp').val()); 
		if(queryreply != null && queryreply!='' ){
			
			items = {};
			items["queryreply"] = queryreply;
			items["query_id"] = id;
			var jsonString = JSON.stringify(items);
			
			$.ajax({
				type : "post",
				url : "./raiseQueryReply",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(response) {
					 if(response.responseMessage=="success"){
						 bootbox.alert("Query reply submitted successfully.");
						 setTimeout(function(){ 
							 window.location.reload();
						 
						 }, 2000);
						 
					}else{
						 bootbox.alert("Somthing went wrong please try again..!");
					} 

				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
			
		}else{
			bootbox.alert("Please enter query reply details.");
		}
		}
		
    });
}

</script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>