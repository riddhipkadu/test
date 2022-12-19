<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="modal fade" id="dialogBox" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="background-color: #e26d1c;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">
								<span class="glyphicon glyphicon-remove-sign"></span> &nbsp;Error
							</h4>
						</div>
						<div class="modal-body">
							
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
	</div>
<div class="page_cont_padd">
	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				
				<a href="listSarfaesiAct"><img
					src="images/DashboardIcons/backold.png" style="float: left;"class="backButton"
					width="100px;"></a>
			</div>
			<h2 style="color: #a72f14; font-size: 24px; float: left;">
					Details</h2>
		</div>
		<div style="clear: both"></div>

		<!-- Tab Menu -->

		<div class="task_tabs">

			<ul id="myTab" class="nav nav-tabs mynav-tabs">
				<li class="active"><a href="#sarfActdetails" data-toggle="tab">SarfaesiAct Details</a></li> 
                <li><a href="#documents" data-toggle="tab">Document Details</a></li> 
                <li><a href="#action" data-toggle="tab">Action</a></li> 
			</ul>
			<div id="myTabContent" class="tab-content">

				<div class="tab-pane fade active in" id="sarfActdetails">
					
					<div class="col-md-12" style="margin-top: 20px;" >
						
						<div style="clear: both"></div>
						
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Loan Number:</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_loan_no }</span> 
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Borrower Name:</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_borrower}</span> 
							</div>
						</div>
						
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Contact no.:</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_contact_no }</span> 
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Email Id:</label>
							</div>
							<div class="col-md-10">
							 <span>${sarfaesiActDetails.sarf_email}</span> 
							</div>
						</div>
						
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Security Type :</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_security_type }</span> 
							</div>
						</div>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Type of Security Interest:</label>
							</div>
							<div class="col-md-10">
							    <span>${sarfaesiActDetails.sarf_security_Interest }</span> 
							</div>
						</div>
						
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Executor:</label>
							</div>
							<div class="col-md-10">
								<span>${sarfaesiActDetails.sarf_Executor_name }</span> 
							</div>
						</div>
						<%-- <div class="col-md-12 light">
							<div class="col-md-2">
								<label>ARC Manager :</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_mangr_name }</span> 
							</div>
						</div> --%>
						
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Security Location:</label>
							</div>
							<div class="col-md-10">
								<span>${sarfaesiActDetails.sarf_security_loc }</span> 
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>NPA Date :</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_npa_dates }</span> 
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>Case Filling Date:</label>
							</div>
							<div class="col-md-10">
							    <span>${sarfaesiActDetails.sarf_filling_dates }</span> 
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Notice Issue Date:</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_NotiIssue_dates }</span> 
							</div>
						</div>
						<div class="col-md-12 light">
							<div class="col-md-2">
								<label>First Reminder:</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_first_reminders }</span> 
							</div>
						</div>
						<div class="col-md-12 dark">
							<div class="col-md-2">
								<label>Second Reminder:</label>
							</div>
							<div class="col-md-10">
								 <span>${sarfaesiActDetails.sarf_second_reminders }</span> 
							</div>
						</div>
						
						
						</div>
					</div>
						 <table id="example" class="table table-striped table-bordered" width="100%" cellspacing="0">
							
             <tbody id="searchResult">
		 <c:forEach items="${listSarfaesiAct}" var="sarf" varStatus="loop">
					   <tr id="row_${sarf.sarf_act_id}">
								<td>${loop.index+1}</td>
								<td>${sarf.sarf_loan_no}</td>
								<td>${sarf.sarf_borrower}</td>
								<td>${sarf.sarf_security_type}</td> 
								<td>${sarf.sarf_security_Interest}</td>
								<td>${sarf.sarf_npa_dates}</td>
								<td class="edit">
						 </c:forEach>   
					</tbody> 
				</table> 
				
				 <div class="tab-pane fade" id="documents"
						style="padding: 10px;">
						<div class="col-md-12"></div>
						<ul id="myTab" class="nav nav-tabs mynav-tabs"
							style="width: 350px;">
							<li class="active"><a href="#documentTab" data-toggle="tab">
									Document Detail</a></li>
						</ul>
						
						<div id="myTabContent1" class=" container tab-content">
                       <div class="tab-pane fade active in" id="synopsisTab"
								style="padding: 10px;">
								<div class="f_form_content">
									<h2>Document Detail</h2>
									<div class="col-md-12">
										<div class="col-md-10">
											<div class="header_button">

											</div>
										</div>
										<div class="col-md-2">
											<center>
												<input type="button"
													class="btn btn-primary addSarfActDocuments"
													value="Add Document" onclick="addFileInputSarfAct;" />
											</center>
										</div>
				<div id="loader" style="display: none;">
					<center>
						<img src="./images/Loader/WaitLoader.gif" width="80px"
							height="85px" />
					</center>
				</div>
									</div>
									<div>
										<form class="form-horizontal" role="form"
											id="addSarfActDocumentForm"
											name="addSarfActDocumentForm" method="post"
											enctype="multipart/form-data">
											 <input type="text" id="doc_SarfAct_id" name="doc_SarfAct_id"
												value="${sarfaesiActDetails.sarf_act_id }" hidden=""> 
											<div id="fileAddingForm"></div>

										</form>
									</div>

									<div class="table_data1">
										<div class="col-md-12">
											<div class="table-responsive">
												<table id="example"
													class="table table-striped table-bordered" width="100%"
													cellspacing="0">
													<thead>
														<tr style="background: #0B6EC3; color: #fff;">
															<th>Sr No.</th>
															<th>Document Type</th>
															<th>Document Name</th>
															<th>Action</th>
														</tr>
													</thead>
										  <tbody id="sarf_docs_tbody">
							                <c:forEach items="${SarfDocument}" var="document" varStatus="doc">
							                  <tr id="row_${document.sarf_doc_id}">
						                       <td>${doc.index+1 }</td>
						                         <td>${document.sarf_document_Type}</td>
							                   <td><a href="./downloadSarfActDoc?sarf_doc_id=${document.sarf_doc_id}">${document.sarf_original_file_name}</a></td>
							                   <td><button type="button" name="delete_sarf_act_doc" value="${document.sarf_doc_id}" class="btn btn-danger">
							                   <i class='fa fa-trash'></i>&nbsp;Delete	</button>
							                  </td>
							               </tr>
						                </c:forEach>
							          </tbody>
												</table>
											</div>
										</div>
									</div>
                                 </div>
							  </div>
                            </div>
                           </div> 
           
           
                 
                 <div class="tab-pane fade" id="action" style="padding: 10px;">
								<table id="example">
									<tr>
										<td></td>
										<td style="text-align: right;">
													<a
														href="./addSarfaesiAction?sarf_act_id=<%=request.getParameter("sarf_act_id")%>"><input
														class="btn btn-primary" type="button"
														value="Add Action"></a>
												
										</td>
			                           </tr>
			                           
			                           <tr>
										<td style="text-align: left; font-size: 18px;"><strong>Action Summary</strong></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="2">
											<table id="listAction"
												class="table table-striped table-bordered"
												style="width: 1000px;" cellspacing="0">
												<tr style="background-color: #a72f14; color: white;">
													<th>Sr No.</th>
											        <th>Status</th>
											        <th>Action Item </th>
											        <th>Next Action Item</th>
											        <th>Notice Issue Date</th>
											        <th>Action Due Date</th>
											        <th>First Reminder alert</th>
											       <!--  <th>Second Reminder alert</th> -->
											        <th>Action</th>
												</tr>
												<tbody id="">
													<c:forEach items="${sarfaesiActActionItem}" var="item"
														varStatus="loop">
														<tr id="row_${item.sarf_action_id}">
														    <td>${loop.index+1 }</td> 
															<td>${item.sarf_action_status}</td>
															<td>${item.sarf_action_item}</td>
															<td>${item.sarf_nextaction_item}</td>
															<td>${item.sarf_action_NotiIssue_dates}</td>
															<td>${item.sarf_action_due_dates}</td>
															<td>${item.action_first_remind_dates}</td>
															<%-- <td>${item.action_second_remind_dates}</td> --%>
                         									
															<td>
															
															<a href="./editSarfaesiActAction?sarf_action_id=${item.sarf_action_id}"><button class="btn btn-primary btn-block" value="submit"
																	name="submit" type="button" style="margin-bottom: 2px;">
																	<i class="fa fa-pencil-square-o"></i>&nbsp;Edit
																</button></a>
																<button type="button" value="${item.sarf_action_id}" name='delete_sarfActAction' 
												                       class="btn btn-danger btn-block" style="margin-bottom: 2px;">
												                       <i class='fa fa-trash'></i>&nbsp;Delete
										                        </button>
																</td>
														</tr>

													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
							</div>
							
							
									<div id="loader_table" style="display: none;">
										<center>
												<img src="./images/Loader/graphLoader.gif" width="150px" height="130px" />
										</center>
									</div>
								</td>
							</tr> 
						</table>
					</div>
					
					</div>
				</div>	
      </div>
   </div>
     
	<script src="appJs/SarfaesiAct/sarfaesiAct.js"></script>	
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>			