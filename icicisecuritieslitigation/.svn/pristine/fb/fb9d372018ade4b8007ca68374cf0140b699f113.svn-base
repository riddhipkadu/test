$(document).ready(function(){
	
	$("#req_query_entity_id").on('change', getAllLocationForOrganization);
	$("#req_query_unit_id").on('change', getAllDepartmentsByLocation);
	
	$("#quer_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
	
	$("#from_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"


	}).datepicker().on('changeDate', function(e){

	$('#to_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});

		$("#to_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#from_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		
		$("#quer_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"

		}).datepicker().on('click', function(e){
			
			$('#quer_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', "now").focus();
			$('#quer_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', "now").focus();
		});
		
		var tomorrow = new Date();
	    tomorrow.setDate(new Date().getDate() + 1);
		$("#turnarond_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			//todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		}).datepicker().on('click', function(e){
			
			$('#turnarond_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', tomorrow).focus();
		});
});


function getAllLocationForOrganization() { 
	var user_orga_id = $("#req_query_entity_id").val();
		if (user_orga_id > 0) {
			
					$.ajax({
						type : "post",
						url : "./getLocationByOrgaUserId",
						//contentType : "application/json",
						dataType : "json",
						data : {orga_id :user_orga_id },
						cache : false,
						success : function(locationlist) {
							data ="";
							data +='<option value = 0>--Select--</option>';

							$.each(locationlist,function(key,value){
								data += "<option value="+value['loca_id']+">"+value['loca_name']+"</option>";
							});
							$("#req_query_unit_id").html(data);	
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#req_query_unit_id').html('<option value="0">--Select--</option>');
			$('#req_query_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		//alert("In function");
		var user_orga_id = $("#req_query_entity_id").val();
		var user_loca_id = $("#req_query_unit_id").val();
		if (user_loca_id > 0) {
			
					$.ajax({
						type : "post",
						url : "./getDepartmentsByLocaUserId",
						//contentType : "application/json",
						dataType : "json",
						data : {loca_id : user_loca_id, orga_id: user_orga_id},
						cache : false,
						success : function(departmentlist) {
							var data ="";
							data +="<option value= 0>--Select--</option>";
							
							$.each(departmentlist,function(key,value){
								//console.log("In loop");
								data += "<option value="+value['dept_id']+">"+value['dept_name']+"</option>";
							});
							$("#req_query_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#req_query_function_id').html('<option value="0">--Select--</option>');
		}
		
	}
	
	function getReason(req_id, status){
		
		var form = "<table class='table table-striped table-bordered'><tr><td><label>Reason for rejection :</label></td><td><textarea class='form-control' name='reason_disapp' id='reason_disapp'></textarea></td></tr></table>";
		
		bootbox.confirm(form, function (value) {
			if(value != false){
			var reason = $('#reason_disapp').val();
			if(reason != null){
				
				var req_type = "Query Request";
				
				items = {};
				items["req_id"] = req_id;
				items["req_status"] = status;
				items["req_type"] = req_type;
				items["reason"] = reason;
				var jsonString = JSON.stringify(items);
				
				$.ajax({
					type : "post",
					url : "./approveDisapproveRequest",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(response) {
						if(response==1){
							var data = '';
								data += "Rejected";
							$("#appDis"+req_id).html(data);
						}else{
							$('#errors').html("Somthing went wrong please try again..!");
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
				
			}
			}
			
	    });
	}
	
	function getAccept(req_id,status){
		
		var req_type = "Query Request";
		var reason = "";
		items = {};
		items["req_id"] = req_id;
		items["req_status"] = status;
		items["req_type"] = req_type;
		items["reason"] = reason;
		var jsonString = JSON.stringify(items);
		
		$.ajax({
			type : "post",
			url : "./approveDisapproveRequest",
			contentType : "application/json",
			dataType : "html",
			data : jsonString,
			cache : false,
			success : function(response) {
				if(response==1){
					var data = '';
						data += "Accepted";
					$("#appDis"+req_id).html(data);
					 window.location = "./addQueryByAccept?id="+req_id;
				}else{
					$('#errors').html("Somthing went wrong please try again..!");
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
		
	}
	
	$("#btnSearch").click(function(){
		
		var req_query_entity_id 	= $("#req_query_entity_id").val();
		var req_query_unit_id 		= $("#req_query_unit_id").val();
		var req_query_function_id 	= $("#req_query_function_id").val();
		var from_date 				= $("#from_date").val();
		var to_date 				= $("#to_date").val();
			
		if(req_query_entity_id > 0 ){
			$("#btnSearch").hide();
			$("#loader").show();
			
				items = {};
				items["quer_entity_id"]          = req_query_entity_id;
				items["quer_unit_id"]            = req_query_unit_id;
				items["quer_function_id"] 		 = req_query_function_id;
				items["from_date"] 		 		 = from_date;
				items["to_date"] 		 		 = to_date;

				var jsonString = JSON.stringify(items);
	 
				$.ajax({
					type : "post",
					url : "./searchRequestQuery",
					contentType : "application/json",
					dataType : "json",
					data : jsonString,
					cache : false,
					success : function(result) { 
						$("#btnSearch").show();
						$("#loader").hide();

						var data = "";
						var i=1;
						document.getElementById('count').innerHTML = result.length;
						 if(result.length != 0){ 
						$.each(result,function(key,value){
							var req_query_id = value['req_query_id'];
							if(value['approval_status'] != 4){
								
							data += "<tr id ='row_"+value['req_query_id']+"'>";
							data +="<td>"+i+"</td>";
							data +="<td>"+value['req_query']+" </td>";
							data +="<td>"+value['req_query_date']+" </td>";
							data +="<td>";
							if(value['doc_list'].length != 0){
								var j = 1;
								$.each(value['doc_list'],function(key,value1){
									data += j+") <a href='./downloadRequestDocument?doc_id="+value1['doc_id']+"'>"+value1['doc_name']+"</a>";
									data += "<br/>";
									j++;
								});
							}
							data +="</td>";
							data +="<td>"+value['req_added_byName']+"-"+value['orga_name']+", "+value['loca_name']+", "+value['dept_name']+" </td>";
							data +="<td>"+value['req_query_criticality']+" </td>";
							data +="<td>"+value['req_query_turnaround_time_name']+" </td>";
							
							if( (value['role_id']== 1 || value['role_id']== 5) && value['approval_status'] ==0 ){
							data +="<td id='"+value['req_query_id']+"'>"
							data += '<button type="button" onclick="getReason('+req_query_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
							data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
							data += '<a href="./addQueryByAccept?id='+value['req_query_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
							data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a></td>';
							
							} else	if( (value['role_id']== 4 || value['role_id']== 6) && value['approval_status'] ==0 ){
								data +="<td id='"+value['req_query_id']+"'>"
								data += '<button type="button" onclick="getReason('+req_query_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
								data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
								data += '<a href="./addQueryByAccept?id='+value['req_query_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a></td>';
								
								}
							else if( (value['role_id']== 1 || value['role_id']== 5) && value['approval_status'] ==1  ){
								data +="<td id='"+value['req_query_id']+"'>"
								data +="<label>Request Rejected by SPOC</label><br/>";
								data += '<button type="button" onclick="getReason('+req_query_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
								data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
								data += '<a href="./addQueryByAccept?id='+value['req_query_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a>';
								data += '<a href="./listRejectionReason?relatedTo=query&id='+value['req_query_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'
							}		
							
							else if( (value['approval_status']== 1 || value['approval_status']== 0) && value['role_id'] ==3 ){								
							
								data +="<td>Pending</td>"
							
							}else if(value['approval_status'] == 3){
								data +="<td>Rejected"
									data += '<a href="./listRejectionReason?relatedTo=query&id='+value['req_query_id']+'">'
									data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
									data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
									data += '</button></a></td>'
							
							}else if((value['role_id']== 4 || value['role_id']== 6) && value['approval_status'] == 1  ){
							data +="<td>Rejected by SPOC"	
								data += '<a href="./listRejectionReason?relatedTo=query&id='+value['req_query_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'
							
							}else{
								data +="<td>Accepted</td>"
							}
							data +="</tr>";
							i++;
							}
							//list of all drafts
							if(value['approval_status'] == 4 && (value['added_by'] == value['sess_user_id'])){
								
								data += "<tr id ='row_"+value['req_query_id']+"'>";
								data +="<td>"+i+"</td>";
								data +="<td>"+value['req_query']+" </td>";
								data +="<td>"+value['req_query_date']+" </td>";
								data +="<td>";
								if(value['doc_list'].length != 0){
									var j = 1;
									$.each(value['doc_list'],function(key,value1){
										data += j+") <a href='./downloadRequestDocument?doc_id="+value1['doc_id']+"'>"+value1['doc_name']+"</a>";
										data += "<br/>";
										j++;
									});
								}
								data +="</td>";
								data +="<td>"+value['req_added_byName']+"-"+value['orga_name']+", "+value['loca_name']+", "+value['dept_name']+" </td>";
								data +="<td>"+value['req_query_criticality']+" </td>";
								data +="<td>"+value['req_query_turnaround_time_name']+" </td>";
								data +="<td id='"+value['req_query_id']+"'>"
								data +="<label>Save for Later</label><br/>";
								data += '<a href="./editQueryRequest?id='+value['req_query_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Edit</button></a></td>';

								data +="</tr>";
								i++;
								
							}
							
						});				
						}else{
							data += "<tr><td colspan=8 style='text-align:center'>No result found..!!</td></tr> " ;
						}
						$("#searchResult").html(data); 
						
					},error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
			}
			else{
				$("#req_query_entity_id").attr("data-placement", "top");
				$("#req_query_entity_id").attr("data-content", "Choose Entity Field..!!");
				$('#req_query_entity_id').popover('show');
				return false;
			}
	});

	function validateForm(){
		
		var req_query_entity_id	= $('#req_query_entity_id').val();
		var req_query_unit_id	= $('#req_query_unit_id').val();
		var req_query_function_id = $('#req_query_function_id').val();
		//var req_query_from		= $('#req_query_from').val();
		var req_query			= $('#req_query').val();
		var req_query_date		= $('#req_query_date').val();
		
		if (req_query_entity_id == 0) {
	       	 
     		$( "#req_query_entity_id" ).attr( "data-placement", "top" );
     		$( "#req_query_entity_id" ).attr( "data-content", "Please select entity name." );
     		$('#req_query_entity_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#req_query_entity_id').popover('destroy');
        }
		
		if (req_query_unit_id == 0) {
	       	 
     		$( "#req_query_unit_id" ).attr( "data-placement", "top" );
     		$( "#req_query_unit_id" ).attr( "data-content", "Please select unit name." );
     		$('#req_query_unit_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#req_query_unit_id').popover('destroy');
        }
		
		if (req_query_function_id == 0) {
	       	 
     		$( "#req_query_function_id" ).attr( "data-placement", "top" );
     		$( "#req_query_function_id" ).attr( "data-content", "Please select function name." );
     		$('#req_query_function_id').popover('show');
	
            return false;
        } else
         {
         	$('#req_query_function_id').popover('destroy');
        }
		
		/*if (req_query_from == 0) {
	       	 
     		$( "#req_query_from" ).attr( "data-placement", "top" );
     		$( "#req_query_from" ).attr( "data-content", "Please enter query from." );
     		$('#req_query_from').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#req_query_from').popover('destroy');
        }*/
		
		if (req_query == 0) {
	       	 
     		$( "#req_query" ).attr( "data-placement", "top" );
     		$( "#req_query" ).attr( "data-content", "Please enter query." );
     		$('#req_query').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#req_query').popover('destroy');
        }
		
		if (req_query_date == 0) {
	       	 
     		$( "#req_query_date" ).attr( "data-placement", "top" );
     		$( "#req_query_date" ).attr( "data-content", "Please enter query date." );
     		$('#req_query_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#req_query_date').popover('destroy');
        }
		
		
	}
	
function validateDraft(){
		
		var req_query_entity_id	= $('#req_query_entity_id').val();
		var req_query_unit_id	= $('#req_query_unit_id').val();
		var req_query_function_id = $('#req_query_function_id').val();

		if (req_query_entity_id == 0) {
	       	 
     		$( "#req_query_entity_id" ).attr( "data-placement", "top" );
     		$( "#req_query_entity_id" ).attr( "data-content", "Please select entity name." );
     		$('#req_query_entity_id').popover('show');
	
            return false;
        } else  {
         	$('#req_query_entity_id').popover('destroy');
        }
		
		if (req_query_unit_id == 0) {
	       	 
     		$( "#req_query_unit_id" ).attr( "data-placement", "top" );
     		$( "#req_query_unit_id" ).attr( "data-content", "Please select unit name." );
     		$('#req_query_unit_id').popover('show');
	
            return false;
        } else {
         	$('#req_query_unit_id').popover('destroy');
        }
		
		if (req_query_function_id == 0) {
	       	 
     		$( "#req_query_function_id" ).attr( "data-placement", "top" );
     		$( "#req_query_function_id" ).attr( "data-content", "Please select function name." );
     		$('#req_query_function_id').popover('show');
	
            return false;
        } else  {
         	$('#req_query_function_id').popover('destroy');
        }
		
		
}
