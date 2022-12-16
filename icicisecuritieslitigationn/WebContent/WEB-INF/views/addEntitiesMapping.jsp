<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<script type="text/javascript">

$(document).ready(function() {
	
	$('#enti_orga_id').click(function(){
		 $('#enti_orga_id').popover('destroy');
  });
	
	$('#enti_loca_id').click(function(){
		 $('#enti_loca_id').popover('destroy');
	});
	
	$('#enti_dept_id').click(function(){
		 $('#enti_dept_id').popover('destroy');
	});
	
	$("#enti_orga_id").on('change', function(){
		getDept();
		$('#enti_loca_id').val('0');
		$('#searchResult').html("");
	});
	
	$("#enti_loca_id").on('change', function(){
		getDept();
		getMappingList();
		
	});
	
	
});
	

/* Start Validate Form fumction  */
function validateForm() {
    
    var oname = $('#enti_orga_id').val();
    
    //Validate Organization dropdown
    if (oname == 0) {
   	 
   	 $( "#enti_orga_id" ).attr( "data-placement", "top" );
        $( "#enti_orga_id" ).attr( "data-content", "Select Entity first" );
        $('#enti_orga_id').popover('show');
			
           return false;
       }
       else
       {
       	$('#enti_orga_id').popover('destroy');
       }
    
    
    //Validate Location dropdown
    if ($( "#enti_loca_id" ).val() == 0) {
    	 
 		$( "#enti_loca_id" ).attr( "data-placement", "top" );
     	$( "#enti_loca_id" ).attr( "data-content", " Select Unit" );
      	$('#enti_loca_id').popover('show');
         return false;
     }
   
  else
  {
	   
  	$('#enti_loca_id').popover('destroy');
  }
    
    //alert($( "#enti_dept_id" ).val());
    //Validate department dropdown
    if ($( "#enti_dept_id" ).val() == null) {
    	 
 		$( "#enti_dept_id" ).attr( "data-placement", "top" );
     	$( "#enti_dept_id" ).attr( "data-content", " Select Function" );
      	$('#enti_dept_id').popover('show');
			
         return false;
     }
   
  else
  {
  	$('#enti_dept_id').popover('destroy');
  }
    
    
 }	
 
 
/* End Validate Form fumction  */

	
/* Start get MappedDeptList function  */
function getMappingList() {
	var enti_orga_id = $("select#enti_orga_id option:selected").attr(
			'value');
	var enti_loca_id = $("select#enti_loca_id option:selected").attr(
			'value');
	
	var mappedList;
	
	items = {};
	items['enti_orga_id'] = enti_orga_id;
	items['enti_loca_id'] = enti_loca_id;
	var alldepts = '${allDepartmentsJSON}';
	var alldeptsjson = $.parseJSON(alldepts);
	var jsonString = JSON.stringify(items);
	if(enti_loca_id!=0)
		{ 
		$.ajax({
			type : "post",
			url : "./getMappedDeptList",
			contentType : "application/json",
			dataType : "html",
			data : jsonString,
			cache : false,
			success : function(result) {
				var data = "";
				mappedList = $.parseJSON(result);
				console.log($('#enti_orga_id option:selected').text());
				console.log($('#enti_loca_id option:selected').text());
				
				mappedDepartments = $.parseJSON(result);
				
				$.each(mappedList, function(key, value){
					 data += "<tr>";
					 data += "<td><strong>"+ $('#enti_orga_id option:selected').text() + "</strong></td>";
					 data += "<td><strong>"+ $('#enti_loca_id option:selected').text() + "</strong></td>";
					 data += "<td><strong>"+ value +"</strong></td>";
					 data += "</tr>";
				   // $('#searchResult').html("<tr><td><strong>"+$('#enti_orga_id option:selected').text()+"</strong></td><td><strong>"+$('#enti_loca_id option:selected').text()+"</strong></td><td><strong>"+value+"</strong></td>	</tr>");
					
				});
				
				 $("#searchResult").html(data);
				
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
				$('#enti_dept_id').empty();
				for (var j = 1; j <= Object.keys(alldeptsjson).length; j++){
					$('#enti_dept_id').append('<option  value=' + j + '>' + alldeptsjson[j] + '</option>');
				}
			}
		});
		
		}else
			{
			 $('#searchResult').html("");
			}
	
}
/* End get MappedDeptList function  */


/* Start get Mapped Departments function  */

function getDept() {
		var enti_orga_id = $("select#enti_orga_id option:selected").attr(
				'value');
		var enti_loca_id = $("select#enti_loca_id option:selected").attr(
				'value');
		var alldepts;
		var mappedDepartments;
		items = {};
			
		items['enti_orga_id'] = enti_orga_id;
		items['enti_loca_id'] = enti_loca_id;
		var alldepts = '${allDepartmentsJSON}';
		var alldeptsjson = $.parseJSON(alldepts);
		var jsonString = JSON.stringify(items);
		$.ajax({
			type : "post",
			url : "./getMappedDepartments",
			contentType : "application/json",
			dataType : "html",
			data : jsonString,
			cache : false,
			success : function(deptlist) {
				$('#enti_dept_id').empty();
				$('#errors').html('');
				mappedDepartments = $.parseJSON(deptlist);
				for (var j = 1; j <= Object.keys(alldeptsjson).length; j++) {
					if ($.inArray(j, mappedDepartments) != -1) {
						console.log("In array:" + j);
						/* $('#enti_dept_id').append(
								'<option  value=' + j + ' selected="selected">'
										+ alldeptsjson[j] + '</option>'); */
					} else {
						$('#enti_dept_id').append(
								'<option  value=' + j + '>' + alldeptsjson[j]
										+ '</option>');
					}
				}
				for (var i = 0; i < mappedDepartments.length; i++) {
					console.log(mappedDepartments[i]);
				}

			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
				$('#enti_dept_id').empty();
				for (var j = 1; j <= Object.keys(alldeptsjson).length; j++){
					$('#enti_dept_id').append('<option  value=' + j + '>' + alldeptsjson[j] + '</option>');
				}
			}
		});
		
	}
	
/* End get Mapped Departments function  */

</script>


	<div class="page_container">
		<!--heading text-->

		<div class="col-md-12">
			<div class="header_button">
				<!--<button type="button" class="btn btn-primary" id="wdt_re">Litigation Summary</button>-->
				<h2 style="color: #a72f14; font-size: 24px; float: left;">Entity
					Mapping</h2>
				<a href="./listEntitiesMapping"><img
					src="images/DashboardIcons/backold.png" class="backButton"
					width="100px;"></a>
			</div>
		</div>
		<div style="clear: both"></div>
		<!--first form-->
		<sf:form commandName="entitiesMapping" cssClass="form-horizontal"
			onsubmit="return validateForm();" action="./saveEntitiesMapping">
			<sf:errors path="*" cssClass="errorBlock"></sf:errors>

	

			<div class="f_form_content">
				<h2>Add Mapping</h2>

				<div class="col-md-12">


					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Entity:</label>
							<div class="col-sm-7">


								<sf:select path="enti_orga_id" items="${allOrganizations}"
									cssClass="form-control">
								</sf:select>
								<i class="asterisk_input"></i>
								<sf:errors path="enti_orga_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Unit:</label>
							<div class="col-sm-7">
								<sf:select path="enti_loca_id" items="${allLocations}"
									cssClass="form-control">
								</sf:select>
								<i class="asterisk_input"></i>
								<sf:errors path="enti_loca_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="form-group">
							<label class="control-label col-sm-4" for="sel1">Function:</label>
							<div class="col-sm-7">
								<sf:select path="enti_dept_id" items="${allDepartments}"
									cssClass="form-control">
								</sf:select>
								<sf:errors path="enti_dept_id" cssClass="errorBlock"></sf:errors>
							</div>
						</div>
					</div>





				</div>


				<div class="col-md-12 litigation_buttons">
					<br>

					<center>
						<input type="submit" id="addBtn" value="Add"
							class="btn btn-myPrimary" />
						<!-- <button type="button" name="back" id="back" class="btn btn-myDefault" onclick="window.location.href ='./listEntitiesMapping' " >Back</button> -->
						</button>
					</center>
				</div>
			</div>
		</sf:form>

		<div style="clear: both"></div>

		<div class="f_form_content">
			<h2> Mapped Entity List</h2>

			<div class="table_data1">
				<div class="container">



					<table id="example" class="table table-striped table-bordered"
						width="100%" cellspacing="0">
						<thead>
							<tr style="background: #a72f14; color: #fff;">

								<th>Entity</th>
								<th>Unit</th>
								<th>Function</th>
							</tr>
						</thead>

						<tbody id = searchResult>
							
						</tbody>
					</table>
				</div>

			</div>

		</div>

	</div>


<div id="errors"></div>
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
