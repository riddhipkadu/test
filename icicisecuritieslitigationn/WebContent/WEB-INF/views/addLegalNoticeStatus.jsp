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
					<h2 style="color:#a72f14;font-size:24px;float:left;">Add Status</h2>
					<!-- <a href="./listQuery"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a> -->
				<a href="./legalNoticeUpdate?lega_noti_id=<%=request.getParameter("lega_noti_id")%>"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	
	<sf:form autocomplete="off" role="form" cssClass="form-horizontal" commandName="addLegalStatus" action="./saveLegalNoticeStatus"  method="POST" enctype="multipart/form-data" >
	
			<div class="f_form_content">
			<h2>Status</h2>
			
				<div class="col-md-10">
				<div class="col-md-12" style="display:none">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-5">ID :</label>
							<div class="col-sm-6">
								<sf:input path="lega_notice_id" />
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Received Date :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="datepicker2" data-date-format="MM">
									<sf:input path="lega_received_date" cssClass="form-control" readonly="true" 
										    /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Action Taken :</label>
							<div class="col-sm-6">
								<sf:textarea class="form-control" rows="4" cols="10"
									path="lega_action_taken" placeholder="Please enter action taken"></sf:textarea>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Next Action Item :</label>
							<div class="col-sm-6">
								<sf:textarea path="lega_next_action_item"  class="form-control" placeholder="Please enter next action item" cols="6" rows="4" />
								<i class="asterisk_input"></i>
							   
							</div>
						</div>
					</div>
											
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Action Item Due Date :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="due_date_div" data-date-format="MM">
									<sf:input path="lega_action_item_due_date" cssClass="form-control"  
										readonly="true"/> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Reminder Date :</label>
							<div class="col-sm-6">
								<div class="input-group date" id="reminder_date_div" data-date-format="MM">
									<sf:input path="lega_reminder_date" cssClass="form-control" readonly="true" 
										    /> <span class="input-group-addon"><i
										class="glyphicon glyphicon-calendar"></i></span>
								</div>
								<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Person Responsible :</label>
							<div class="col-sm-6">
								<sf:select class="form-control" path="lega_person_responsible" >
								<option value="0">--Select--</option>
									<c:forEach items="${allUser}" var="userList">
											<option value="${userList.user_id}">${userList.user_first_name}
												${userList.user_last_name}</option>
										</c:forEach>
								<option value="-1">Others</option>		
							     </sf:select>	<i class="asterisk_input"></i>
							</div>
						</div>
					</div>
					<div class="col-md-12">
					<div class="form-group row" id="lega_person_responsible_others_div">
							<label class="control-label col-sm-3" for="sel1"></label>
							<div class="col-sm-6">
								<sf:input path="lega_person_responsible_others" cssClass="form-control"
										 /><i class="asterisk_input"></i>
							</div>
						</div>
					</div>				  
					<div class="col-md-12">
						<div class="form-group row">
							<label for="sfctitle" class="control-label col-sm-3">Document :</label>
							<div class="col-sm-6">
								<div class="litigation_buttons">
									<input type="file" class="btn btn-myWarning" name="lega_status_doc" ><br />
									
								</div>
							</div>
							
						</div>
					</div> 
				
				</div>
			
				<div class="col-md-12 litigation_buttons">
				<br>
				<center><button type="submit" value="Save" name="Save" class="btn btn-myPrimary">Save Status</button>
						    	 <button type="submit" name="Draft" value="Draft"class="btn btn-myDefault">Save As Draft</button> 
						</center>
				</div>
				</div>
		</sf:form>		
			
			<div style="clear:both"></div>
			
</div>
</div>

<script src="appJs/LegalNotice/LegalNoticeStatus.js"></script>
<script>
$(document).ready(function(){
	
 $(document).on("click", ":submit", function(e){
    buttonClicked = $(this).val();
    if(buttonClicked=="Save"){
    	if(validateForm()!=false){
    		$("form").submit();
    	}else{
    		e.preventDefault();
    	}
    }
    if(buttonClicked=="Draft"){
    	if(validateDraft()!=false){
    		$("form").submit();
        }else{
    		e.preventDefault();
    	}
    }
    //e.preventDefault();
});
	
});

</script>


<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
