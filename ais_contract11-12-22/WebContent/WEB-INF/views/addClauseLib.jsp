<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="page_cont_padd">
<div class="page_container">
<!--heading text-->

			<div class="col-md-12">
				<div class="header_button">
					<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
					<h2 style="color:#054eff;font-size:24px;float:left;">Clause Library</h2>
					<a href="./listClauseLib"><img src="images/DashboardIcons/backold.png" class="backButton" width="100px;"></a>
				</div>
			</div>
	<div style="clear:both"></div>
	<!--first form-->
	<sf:form commandName="addClauseLib" autocomplete="off" cssClass="form-horizontal" onsubmit="return validateForm();"
				action="./saveClauseLib" >
				<sf:errors path="*" cssClass="errorBlock"></sf:errors>
				
			<div class="f_form_content">
			<h2>Clause Library</h2>
			
				


					<div class="col-md-12">

						<div class="form-group">
							<label class="control-label col-sm-3" for="sel1">Clause
								Name</label>
						<div class="col-sm-4">
							 <sf:select class="form-control" path="clause_Id"
								items="${allClause}" id="clause_Id" name="clause_Id">
								<sf:option value="0">--Select--</sf:option>

							</sf:select>
							<i class="asterisk_input"></i>

						</div>
					</div>

						<div class="col-md-12">
							<div class="col-md-1"></div>
							<b>NOTE : Please type opposite party as OPPOSITE_PARTY and
								effective date as EFFECTIVE_DATE where ever you wish to add the
								same.</b>
							<div class="col-md-1"></div>
							<br> <br>
						</div>

						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-3" for="sel1">
									Paragraph: </label>
								<div class="col-sm-7">
									<sf:textarea rows="10" cols="60" path="lib_paragraph"
										id="lib_paragraph" name="lib_paragraph"
										placeholder="Enter Information"></sf:textarea>
								</div>
							</div>
						</div>


						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-3" for="sel1">Owner
									Name:</label>
								<div class="col-sm-4">
								<sf:select class="form-control" items="${allUser}"
									path="lib_owner_Id" id="lib_owner_Id">

								</sf:select>
								<i class="asterisk_input"></i>
							</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label col-sm-3" for="sel1">Review
									date:</label>
								<div class="col-sm-3">
									<div class="input-group date" id="lib_date_div"
										data-date-format="MM">
										<sf:input path="lib_review_date" cssClass="form-control"
											readonly="true" placeholder="Enter Review Date" />
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
						</div>






					</div>

			
				
				
				<div class="col-md-12 litigation_buttons">
				<br>
				
					<center><input type="submit" value="Add" class="btn btn-myPrimary"/>
					<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listClauseLib' " >Back</button> -->
					</center>
				</div>
				</div>
				</sf:form>	
			
			<div style="clear:both"></div>
			
</div>
</div>
<script src="appJs/ClauseLib/clauselib.js"></script>

<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>

