<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
<div class="page_container">

<!-- Dialogue box -->
	<div class="modal fade" id="submitModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header" style="background-color: #097a09;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">
							 <span class="glyphicon glyphicon-ok"></span>  &nbsp;Success
						</h4>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" onClick="location.href='./listQuery'" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
	</div>
<!-- End of Dialogue box -->


<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#032BEC;font-size:24px;float:left;">Reply To Query</h2>
					<!-- <a href="./listQuery"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	
	<sf:form autocomplete="off" role="form" cssClass="form-horizontal" commandName="replyToQuery" action="./saveReplyQuery"  method="POST" enctype="multipart/form-data" >
	
			<div class="f_form_content">
			<h2>Reply</h2>
			
				<div class="col-md-10">
				<div class="col-md-12" style="display:none">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">ID :</label>
							<div class="col-sm-6">
								<sf:input path="query_quer_id" />
							</div>
						</div>
					</div>
				
				<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Status :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="query_hst_status" name="query_hst_status" id="query_hst_status">
								      <option value="0">--Select--</option>
			   				          <option value="Pending">Pending</option>
			   				          <option value="In Progress">In Progress</option>
			   				          <option value="Awaiting Feedback">Awaiting Feedback</option>
			   				          <option value="Awaiting Approval">Awaiting Approval</option>
			   				          <option value="Sent for Review">Sent for Review</option>
			   				          <option value="Closed/Executed">Closed/Executed</option>
			   				          <option value="Query Cancelled">Query Cancelled</option>
			   				          <option value="Query Suspended">Query Suspended</option>
			   				          <option value="Save As Draft">Save As Draft</option>
			   				          <option value="Others">Others</option>
							     </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row" id="others_div_id">
							<label for="sfctitle" class="control-label col-sm-3"></label>
							<div class="col-sm-6">
								<sf:input path="query_hst_others" cssClass="form-control"  />
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Type :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="query_hst_type" name="query_hst_type" id="query_hst_type">
								      <option value="NA">--Select--</option>
			   				          <option value="Replied">Replied</option>
			   				          <option value="Received">Received</option>
			   				          
							     </sf:select>
							   
							</div>
						</div>
					</div>
						
											
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Received/Replied On :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="replied_date_div" data-date-format="MM">
									<sf:input id="query_hst_replied_date" path="query_hst_replied_date" class="form-control" type="text" 
										   name="query_hst_replied_date" readonly="true"/><span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar" ></i></span>
								</div>
								
							</div>
						</div>
					</div>
				
				<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Entity :</label>
							<div class="col-sm-6">
								<select name="quer_entity_id" id="quer_entity_id">
								     <option value="0">--SELECT--</option>
								     <c:forEach items="${allOrganizations}" var="organization">
								     <option value="${organization['key']}">${organization['value']}</option>
								     </c:forEach>
								</select>
							</div>
						</div>
					</div> --%>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Action to be performed :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="query_hst_action_tobe_performed" >
								      <option value="0">--Select--</option>
			   				          <option value="Drafting/Preparing Memo">Drafting/Preparing Memo</option>
			   				          <option value="Reviewing">Reviewing</option>
			   				          <option value="Negotations">Negotations</option>
			   				          <option value="Teleconference">Teleconference</option>
			   				          <option value="Videoconference">Videoconference</option>
			   				          <option value="Meeting">Meeting</option>
			   				          <option value="Co-ordination">Co-ordination</option>
			   				          <option value="Execution">Execution</option>
			   				          <option value="Others">Others</option>
							     </sf:select><i class="asterisk_input"></i>
							   
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row" id="others_action_performed_div">
							<label for="sfctitle" class="control-label col-sm-3"></label>
							<div class="col-sm-6">
								<sf:input path="query_hst_action_performed_others" cssClass="form-control"  />
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Action to be performed by :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="query_hst_action_tobe_performed_by" >
								      <option value="0">--Select--</option>
			   				          <option value="Internal Customer">Internal Customer</option>
			   				          <option value="External Customer">External Customer</option>
			   				          <option value="Both">Both</option>
			   				          <option value="Others">Others</option>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row" id="others_action_performed_by_div">
							<label for="sfctitle" class="control-label col-sm-3"></label>
							<div class="col-sm-6">
								<sf:input path="query_hst_action_performed_by_others" cssClass="form-control"  />
							     <i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Action assigned to :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="query_hst_action_assigned_to" >
									  <option value="0">--Select--</option>
									  <c:forEach items="${allUser}" var="users">
										<option value="${users.user_id}">${users.user_first_name}
											${users.user_last_name}</option>
									  </c:forEach>
							     </sf:select><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<%-- <div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Action Performed By :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="query_hst_replied_by" name="query_hst_replied_by" id="query_hst_replied_by">
									  <option value="0">--Select--</option>
									  <c:forEach items="${allUser}" var="users">
										<option value="${users.user_id}">${users.user_first_name}
											${users.user_last_name}</option>
									  </c:forEach>
							     </sf:select>	<i class="asterisk_input"></i>
							</div>
						</div>
					</div> --%>
										<!-- <div class="col-md-12"> -->
<!-- 						<div class="form-group row"> -->
<!-- 							<label for="sfctitle" class="control-label col-sm-3">To :</label> -->
<!-- 							<div class="col-sm-6"> -->
<!-- 								<input id="sfctitle" class="form-control" type="text" name="sfctitle" readonly="readonly" value="Legal Department"/> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Comments :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" rows="4" cols="6" path="query_hst_comments" placeholder="Enter Comments"></sf:textarea><i class="asterisk_input"></i>
							</div>
						</div>
					</div>
									  
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Document :</label>
							<div class="col-sm-6">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="query_doc" ><br />
								</div>
							</div>
							
						</div>
					</div>
				
				</div>
			
				<div class="col-md-12 litigation_buttons">
				<br>
				<center><button type="submit" name="Save" value="Save" class="btn btn-myPrimary">Submit</button>
				<button type="submit" name="Draft" value="Draft" class="btn btn-myDefault" >Save As Draft</button>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listDepartments' " >Back</button> -->
					</center>
				</div>
				</div>
		</sf:form>		
			
			<div style="clear:both"></div>
			
</div>
</div>

<script>
$(document).ready(function(){
	
	$(document).on("click", ":submit", function(e){
	    buttonClicked = $(this).val();
	    if(buttonClicked=="Save"){
	    	if(validateHstForm()!=false){
	    		$("form").submit();
	    	}else{
	    		e.preventDefault();
	    	}
	    }
	    if(buttonClicked=="Draft"){
	    	if(validateHstDraft()!=false){
	    		$("form").submit();
	        }else{
	    		e.preventDefault();
	    	}
	    }
	    //e.preventDefault();
		}); 
});

</script>
<script src="appJs/Query/query_validation.js"></script>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
