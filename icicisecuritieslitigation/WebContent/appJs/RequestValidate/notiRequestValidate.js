$(document).ready(function(){
	
	$("#req_noti_entity_id").on('change', getAllLocationForOrganization);
	$("#req_noti_unit_id").on('change', getAllDepartmentsByLocation);
	
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
});

function getAllLocationForOrganization() { 
	var user_orga_id = $("#req_noti_entity_id").val();
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
							$("#req_noti_unit_id").html(data);	
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#req_noti_unit_id').html('<option value="0">--Select--</option>');
			$('#req_noti_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		//alert("In function");
		var user_orga_id = $("#req_noti_entity_id").val();
		var user_loca_id = $("#req_noti_unit_id").val();
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
							$("#req_noti_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#req_noti_function_id').html('<option value="0">--Select--</option>');
		}
	}
	
$("#btnSearch").click(function(){
		//alert(hi);
		var req_noti_entity_id 		= $("#req_noti_entity_id").val();
		var req_noti_unit_id 		= $("#req_noti_unit_id").val();
		var req_noti_function_id 	= $("#req_noti_function_id").val();
		var from_date 				= $("#from_date").val();
		var to_date 				= $("#to_date").val();
		if(req_noti_entity_id > 0 ){
		
			$("#btnSearch").hide();
			$("#loader").show();
			
				items = {};
				items["noti_entity_id"]          = req_noti_entity_id;
				items["noti_unit_id"]            = req_noti_unit_id;
				items["noti_function_id"] 		 = req_noti_function_id;
				items["from_date"] 		 		 = from_date;
				items["to_date"] 		 		 = to_date;
				var jsonString = JSON.stringify(items);
	 
				$.ajax({
					type : "post",
					url : "./searchRequestNotice",
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
							//list of all litigation request
							var req_noti_id = value['req_noti_id'];
							if(value['approval_status'] != 4){
							
							data += "<tr id ='row_"+value['req_noti_id']+"'>";
							data +="<td>"+i+"</td>";
							data +="<td>"+value['req_noti_date']+" </td>";
							data +="<td>"+value['noti_by_against']+" </td>";
							data +="<td>"+value['req_oppo_party']+"</td>";
							data +="<td>"+value['req_desc']+" </td>";
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
							
							if( (value['role_id']== 1 || value['role_id']== 5) && value['approval_status'] ==0 ){
							data +="<td id='"+value['req_noti_id']+"'>"
							data += '<button type="button" onclick="getReason('+req_noti_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
							data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
							data += '<a href="./addNoticeByAccept?id='+req_noti_id+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
							data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a></td>';
							
							}else if((value['role_id']== 4 || value['role_id']== 6) && value['approval_status'] ==0 ){
								data +="<td id='"+value['req_noti_id']+"'>"
								data += '<button type="button" onclick="getReason('+req_noti_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
								data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
								data += '<a href="./addNoticeByAccept?id='+value['req_noti_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a></td>';
								
								}
							else if((value['role_id']== 1 || value['role_id']== 5) && value['approval_status'] ==1){
								
								data +="<td id='"+value['req_noti_id']+"'>"
								data +="<label>Request Rejected by SPOC</label><br/>";
								data += '<button type="button" onclick="getReason('+req_noti_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
								data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
								data += '<a href="./addNoticeByAccept?id='+value['req_noti_id']+'"><button type="button" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a>';
								data += '<a href="./listRejectionReason?relatedTo=notice&id='+value['req_noti_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'
							}
							else if( (value['approval_status']== 1 || value['approval_status']== 0) && value['role_id'] ==3 ){								
								
								data +="<td>Pending</td>"
							
							}else if(value['approval_status'] == 3){
								data += '<td>Rejected ' 
								data += '<a href="./listRejectionReason?relatedTo=notice&id='+value['req_noti_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'	
							
							}else if((value['role_id']== 4 || value['role_id']== 6) && value['approval_status'] == 1 ){
								data +="<td>Rejected by SPOC"	
								data += '<a href="./listRejectionReason?relatedTo=notice&id='+value['req_noti_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'
							}else{
								data +="<td>Accepted</td>"
							}
							
							data +='<td><input type="button" style="margin:2px;" id="raisequery" onclick="raiseQuery('+value['req_noti_id']+',"notice")" value="Raise Query" class="btn btn-success">'
							data +='<br><a href="./listRaisedQuery?relatedTo=notice&id='+value['req_noti_id']+'" style="margin:2px" class="btn btn-primary">List Queries</a></td>'
							
							data +="</tr>";
							i++;
							
							}
							
							//list of all drafted request
							if(value['approval_status'] == 4 && (value['added_by'] == value['sess_user_id'])){
								
								data += "<tr id ='row_"+value['req_noti_id']+"'>";
								data +="<td>"+i+"</td>";
								data +="<td>"+value['req_noti_date']+" </td>";
								data +="<td>"+value['noti_by_against']+" </td>";
								data +="<td>"+value['req_oppo_party']+"</td>";
								data +="<td>"+value['req_desc']+" </td>";
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
								
								data +="<td id='"+value['req_noti_id']+"'>"
								data +="<label>Save for Later</label><br/>";
								data += '<a href="./editLitiRequest?id='+value['req_noti_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;edit</button></a></td>';
							
								data +="</tr>";
								i++;
							}
						});				
						}else{
							data += "<tr><td colspan=8 style='text-align:center'>No result found..!!</td></tr>" ;
						}
						$("#searchResult").html(data); 
						
					},error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
			}
			else{
				$("#req_noti_entity_id").attr("data-placement", "top");
				$("#req_noti_entity_id").attr("data-content", "Choose Entity Field..!!");
				$('#req_noti_entity_id').popover('show');
				return false;
			}
	});
	
	function getReason(req_id, status){
		//alert("getreason");
		var form = "<table class='table table-striped table-bordered'><tr><td><label>Reason for rejection :</label></td><td><textarea class='form-control' name='reason_disapp' id='reason_disapp'></textarea></td></tr></table>";
		bootbox.confirm(form, function (value) {
			if(value != false){
			var reason = $('#reason_disapp').val();
			if(reason != null){
				
				var req_type = "Notice Request";
				
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
		
		var req_type = "Notice Request";
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
					//var data = '';
						//data += "Accepted";
					//$("#appDis"+req_id).html(data);
					 window.location = "./addLitigationByAccept?id="+req_id;
				}else{
					$('#errors').html("Somthing went wrong please try again..!");
				}

			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	}

function validateForm(){
	
	var req_noti_entity_id 			= $('#req_noti_entity_id').val();
    var req_noti_unit_id 			= $('#req_noti_unit_id').val();
    var req_noti_function_id 		= $('#req_noti_function_id').val();
    var req_noti_type_by_against 	= $('#req_noti_type_by_against').val();
    var req_noti_date 				= $('#req_noti_date').val();
    var req_noti_oppo_party 		= $('#req_noti_oppo_party').val();
   
    if (req_noti_entity_id == 0) {
 		
    	$( "#req_noti_entity_id" ).attr( "data-placement", "top" );
 		$( "#req_noti_entity_id" ).attr( "data-content", "Please select entity name.");
 		$('#req_noti_entity_id').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_entity_id').popover('destroy');
    }
    
    if (req_noti_unit_id == 0) {
 		
    	$( "#req_noti_unit_id" ).attr( "data-placement", "top" );
 		$( "#req_noti_unit_id" ).attr( "data-content", "Please select unit name.");
 		$('#req_noti_unit_id').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_unit_id').popover('destroy');
    }
	
    if (req_noti_function_id == 0) {
 		
    	$( "#req_noti_function_id" ).attr( "data-placement", "top" );
 		$( "#req_noti_function_id" ).attr( "data-content", "Please select function name.");
 		$('#req_noti_function_id').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_function_id').popover('destroy');
    }
    
    if (req_noti_type_by_against == 0) {
 		
    	$( "#req_noti_type_by_against" ).attr( "data-placement", "top" );
 		$( "#req_noti_type_by_against" ).attr( "data-content", "Please select Notice type.");
 		$('#req_noti_type_by_against').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_type_by_against').popover('destroy');
    }
    
    if(req_noti_type_by_against == 'Against'){
    if (req_noti_date == 0) {
 		
    	$( "#req_noti_date" ).attr( "data-placement", "top" );
 		$( "#req_noti_date" ).attr( "data-content", "Please enter notice date.");
 		$('#req_noti_date').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_date').popover('destroy');
    }
    }else{
     	$('#req_noti_date').popover('destroy');
    }
    
    if (req_noti_oppo_party == 0) {
 		
    	$( "#req_noti_oppo_party" ).attr( "data-placement", "top" );
 		$( "#req_noti_oppo_party" ).attr( "data-content", "Please Enter Opposite Party.");
 		$('#req_noti_oppo_party').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_oppo_party').popover('destroy');
    }
}

function validateDraft(){
	
	var req_noti_entity_id 			= $('#req_noti_entity_id').val();
    var req_noti_unit_id 			= $('#req_noti_unit_id').val();
    var req_noti_function_id 		= $('#req_noti_function_id').val();
   
    if (req_noti_entity_id == 0) {
 		
    	$( "#req_noti_entity_id" ).attr( "data-placement", "top" );
 		$( "#req_noti_entity_id" ).attr( "data-content", "Please select entity name.");
 		$('#req_noti_entity_id').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_entity_id').popover('destroy');
    }
    
    if (req_noti_unit_id == 0) {
 		
    	$( "#req_noti_unit_id" ).attr( "data-placement", "top" );
 		$( "#req_noti_unit_id" ).attr( "data-content", "Please select unit name.");
 		$('#req_noti_unit_id').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_unit_id').popover('destroy');
    }
	
    if (req_noti_function_id == 0) {
 		
    	$( "#req_noti_function_id" ).attr( "data-placement", "top" );
 		$( "#req_noti_function_id" ).attr( "data-content", "Please select function name.");
 		$('#req_noti_function_id').popover('show');
       
 		return false;
   
    }else{
     	$('#req_noti_function_id').popover('destroy');
    }
}

function raiseQuery(id,relatedTo){
	
	var form = "<table class='table table-striped table-bordered'><tr><td><label>Query :</label></td><td><textarea class='form-control' name='query' id='query'></textarea></td></tr>" +
			   "<tr><td></td><td><input class='messageCheckbox' type='checkbox' name='Yes' id='Yes' value='Yes'> Mark admin in CC <br></td></tr></table>";
	bootbox.confirm(form, function (value) {
		//alert("Value is :"+value);
		if(value != false){
		var query = $('#query').val();
		if($('.messageCheckbox:checked').val() != null){
			var admin_cc = $('.messageCheckbox:checked').val();
			} else {
			var admin_cc = "No";
			}
		 //alert("admin cc is :"+admin_cc); 
		if(query != null && query!='' ){
			items = {};
			items["query"] = query;
			items["admin_cc"] = admin_cc;
			items["query_related_to"] = relatedTo;
			items["query_related_id"] = id;
			var jsonString = JSON.stringify(items);
			
			$.ajax({
				type : "post",
				url : "./raiseQuery",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(response) {
					 if(response.responseMessage=="success"){
						 bootbox.alert("Query Successfully raised.");
					}else{
						 bootbox.alert("Somthing went wrong please try again..!");
					} 
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		} else {
			bootbox.alert("Please enter query details.");
		}
		}
    });
}
