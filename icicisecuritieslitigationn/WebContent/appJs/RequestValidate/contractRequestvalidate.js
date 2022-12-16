$(document).ready(function(){
	
	$("#req_contract_entity_id").on('change', getAllLocationForOrganization);
	$("#req_contract_unit_id").on('change', getAllDepartmentsByLocation);
	
	$("#from_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		//defaultViewDate:"today"

	}).datepicker().on('changeDate', function(e){

	$('#to_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});

		$("#to_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#from_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
});


function getAllLocationForOrganization() { 
	var user_orga_id = $("#req_contract_entity_id").val();
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
							$("#req_contract_unit_id").html(data);	
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#req_contract_unit_id').html('<option value="0">--Select--</option>');
			$('#req_contract_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		//alert("In function");
		var user_orga_id = $("#req_contract_entity_id").val();
		var user_loca_id = $("#req_contract_unit_id").val();
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
							$("#req_contract_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#req_contract_function_id').html('<option value="0">--Select--</option>');
		}
	}
	
	function getReason(req_id, status){
		
		var form = "<table class='table table-striped table-bordered'><tr><td><label>Reason for rejection :</label></td><td><textarea class='form-control' name='reason_disapp' id='reason_disapp'></textarea></td></tr></table>";
		bootbox.confirm(form, function (value) {
			//alert("Value is :"+value);
			if(value != false){
			var reason = $('#reason_disapp').val();
			  //alert("reason is the :"+$('#reason_disapp').val()); 
			if(reason != null){
				var req_type = "Contract Request";
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
		
		var req_type = "Contract Request";
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
					 window.location = "./addContractByAccept?id="+req_id;
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
		
		var req_contract_entity_id 	= $("#req_contract_entity_id").val();
		var req_contract_unit_id 	= $("#req_contract_unit_id").val();
		var req_contract_function_id= $("#req_contract_function_id").val();
		var from_date 				= $("#from_date").val();
		var to_date 				= $("#to_date").val();
			
		if(req_contract_entity_id > 0 ){
			$("#btnSearch").hide();
			$("#loader").show();
			
				items = {};
				items["req_contract_entity_id"]          = req_contract_entity_id;
				items["req_contract_unit_id"]            = req_contract_unit_id;
				items["req_contract_function_id"] 		 = req_contract_function_id;
				items["from_date"] 		 		 		 = from_date;
				items["to_date"] 		 		 		 = to_date;

				var jsonString = JSON.stringify(items);
	 
				$.ajax({
					type : "post",
					url : "./searchRequestContract",
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
							var contract_id = value['contract_id'];
							if(value['approval_status'] != 4){
								
							data += "<tr id ='row_"+value['contract_id']+"'>";
							data +="<td>"+i+"</td>";
							data +="<td>"+value['contract_type_name']+" </td>";
							//data +="<td>"+value['req_contract_date']+" </td>";
							data +="<td>";
							if(value['cont_parties'].length != 0){
								var j = 1;
								$.each(value['cont_parties'],function(key,value1){
									data += j+")"+value1['party_name'];
									data += "<br/>";
									j++;
								});
							}
							data +="</td>";
							data +="<td>"+value['contract_desc']+" </td>";
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
							data +=" <td>"+value['created_at']+"</td>"
							if( (value['role_id']== 1 || value['role_id']== 5) && value['approval_status'] ==0 ){
							data +="<td id='"+value['contract_id']+"'>"
							data += '<button type="button" onclick="getReason('+contract_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
							data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
							data += '<a href="./addContractByAccept?id='+value['contract_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
							data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a></td>';
							
							}else if( (value['role_id']== 4 || value['role_id']== 6) && value['approval_status'] ==0 ){
								data +="<td id='"+value['contract_id']+"'>"
								data += '<button type="button" onclick="getReason('+contract_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
								data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
								data += '<a href="./addContractByAccept?id='+value['contract_id']+'"><button type="button" onclick="getAccept('+value['contract_id']+',"2")" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a></td>';
							
							}
							else if( (value['role_id']== 1 || value['role_id']== 5) && value['approval_status'] ==1 ){
								data +="<td id='"+value['contract_id']+"'>"
								data +="<label>Request Rejected by SPOC</label><br/>";
								data += '<button type="button" onclick="getReason('+contract_id+',1)" name="submit" value="submit" class="btn btn-danger" style="margin:4px">';
								data += '<i class="fa fa-thumbs-down"></i>&nbsp;Reject</button>';
								data += '<a href="./addContractByAccept?id='+value['contract_id']+'"><button type="button" onclick="getAccept('+value['contract_id']+',"2")" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;Accept</button></a>';
								data += '<a href="./listRejectionReason?relatedTo=contract&id='+value['contract_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'
							}else if((value['approval_status']== 1 || value['approval_status']== 0) && value['role_id'] ==3 ){								
								
								data +="<td>Pending</td>"
							
							}else if(value['approval_status'] == 3){
								data +="<td>Rejected"
									data += '<a href="./listRejectionReason?relatedTo=contract&id='+value['contract_id']+'">'
									data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
									data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
									data += '</button></a></td>'
							
							}else if((value['role_id']== 4 || value['role_id']== 6) && value['approval_status'] == 1  ){
							data +="<td>Rejected by SPOC"
								data += '<a href="./listRejectionReason?relatedTo=contract&id='+value['contract_id']+'">'
								data += '<button type="button" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 4px;">'
								data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Reason'
								data += '</button></a></td>'
							}
							else if(value['approval_status'] == 2 && value['role_id']== 3){
									data +='<td>Accepted<br/><a href="./listContractStatusForNegotiation?id='+value['contract_id']+'"><button type="button" style="margin:2px" class="btn btn-primary">Contract Status</button></a></td>'	
							}else{
								data +="<td>Accepted</td>"
							}
							data +='<td><input type="button" style="margin:2px;" id="raisequery" onclick="raiseQuery('+value['contract_id']+',"contract")" value="Raise Query" class="btn btn-success">'
							data +='<br><a href="./listRaisedQuery?relatedTo=contract&id='+value['contract_id']+'"><button type="button" style="margin:2px" class="btn btn-primary">List Queries</button></a></td>'
														
							data +="</tr>";
							i++;
							}
							
							if((value['added_by'] == value['sess_user_id']) && value['approval_status'] == 4){
								
								data += "<tr id ='row_"+value['contract_id']+"'>";
								data +="<td>"+i+"</td>";
								data +="<td>"+value['contract_type_name']+" </td>";
								//data +="<td>"+value['req_contract_date']+" </td>";
								data +="<td>";
								data += "1)"+value['orga_name']+"<br/>";
								if(value['cont_parties'].length != 0){
									var j = 2;
									$.each(value['cont_parties'],function(key,value1){
										data += j+")"+value1['party_name'];
										data += "<br/>";
										j++;
									});
								}
								data +="</td>";
								data +="<td>"+value['contract_desc']+" </td>";
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
								data +=" <td>"+value['created_at']+"</td>"
								data +="<td id='"+value['contract_id']+"'>"
								data +="<label>Save for Later</label><br/>";
								data += '<a href="./editContractRequest?id='+value['contract_id']+'"><button type="button" onclick="" name="submit" value="submit" class="btn btn-success">';
								data += '<i class="fa fa-thumbs-up"></i>&nbsp;edit</button></a></td>';
							
								data +="</tr>";
								i++;
							}
						});				
						}else{
							data += "<tr><td colspan=9 style='text-align:center'>No result found..!!</td></tr> " ;
						}
						$("#searchResult").html(data); 
						
					},error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
			}
			else{
				$("#req_contract_entity_id").attr("data-placement", "top");
				$("#req_contract_entity_id").attr("data-content", "Choose Entity Field..!!");
				$('#req_contract_entity_id').popover('show');
				return false;
			}
	});
	
/*	function getReason(req_id, status){
		
		var form = "<table class='table table-striped table-bordered'><tr><td><label>Reason for disapproval :</label></td><td><textarea class='form-control' name='reason_disapp' id='reason_disapp'></textarea></td></tr></table>";
		bootbox.confirm(form, function (value) {
			var reason = $('#reason_disapp').val();
			  //alert("reason is the :"+$('#reason_disapp').val()); 
			if(reason != null){
				
				var req_type = "Contract Request";
				
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
							if(status=='0'){
								data += "Disapproved";
							}
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
			
	    });
	}*/

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
	function validateForm(){
		
		var req_contract_entity_id 				= $('#req_contract_entity_id').val();
	    var req_contract_unit_id 				= $('#req_contract_unit_id').val();
	    var req_contract_function_id 			= $('#req_contract_function_id').val();
	    /*var req_contract_type 					= $('#req_contract_type').val();
	    var req_contract_date 					= $('#req_contract_date').val();
	    var req_contract_start_date 			= $('#req_contract_start_date').val();
	    var req_contract_end_date 				= $('#req_contract_end_date').val();*/
	    var req_contract_email_id 				= $('#req_contract_email_id').val();
	    var req_contract_desc 					= $('#req_contract_desc').val();
	    var req_contract_major_clause 			= $('#req_contract_major_clause').val();
	    var req_contract_surviving_clause 		= $('#req_contract_surviving_clause').val();
	    var req_contract_perform_rel_payment 	= $('#req_contract_perform_rel_payment').val();
	    var req_contract_damage 				= $('#req_contract_damage').val();
	    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	    
	    if (req_contract_entity_id == 0) {
	    	//alert(req_contract_entity_id);

	    	$( "#req_contract_entity_id" ).attr( "data-placement", "top" );
	 		$( "#req_contract_entity_id" ).attr( "data-content", "Please select entity name.");
	 		$('#req_contract_entity_id').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_entity_id').popover('destroy');
	    }
	    
	    if (req_contract_unit_id == 0) {
	 		
	    	$( "#req_contract_unit_id" ).attr( "data-placement", "top" );
	 		$( "#req_contract_unit_id" ).attr( "data-content", "Please select unit name.");
	 		$('#req_contract_unit_id').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_unit_id').popover('destroy');
	    }
		
	    if (req_contract_function_id == 0) {
	 		
	    	$( "#req_contract_function_id" ).attr( "data-placement", "top" );
	 		$( "#req_contract_function_id" ).attr( "data-content", "Please select function name.");
	 		$('#req_contract_function_id').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_function_id').popover('destroy');
	    }
	    
	    /*if (req_contract_type == 0) {
	 		
	    	$( "#req_contract_type" ).attr( "data-placement", "top" );
	 		$( "#req_contract_type" ).attr( "data-content", "Please select contract type.");
	 		$('#req_contract_type').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_type').popover('destroy');
	    }
	    
	    if (req_contract_date == 0) {
	 		
	    	$( "#req_contract_date" ).attr( "data-placement", "top" );
	 		$( "#req_contract_date" ).attr( "data-content", "Please select contract date.");
	 		$('#req_contract_date').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_date').popover('destroy');
	    }
	    
		var status = "true";
		//alert(party_added);
		var values  =  $('input[name="additional_parties"]').map(function(){
			if (this.value.length == null || this.value.length == "0" || this.value.length == 0){
				$('input[name="additional_parties"]').attr( "data-placement", "top" );
				$('input[name="additional_parties"]').attr( "data-content", "Please enter the party name." );
				$('input[name="additional_parties"]').popover('show');
			    //return false;
				status = "false";
			}else{
				$(this).popover('destroy');
			}
			});
			if(status =="false"){
				//console.log("In fasle");
				return false;
				
			}else{
				$(this).popover('destroy');
			}
			
			if (req_contract_start_date == 0 ) {
		 		
		    	$( "#req_contract_start_date" ).attr( "data-placement", "top" );
		 		$( "#req_contract_start_date" ).attr( "data-content", "Please select start date.");
		 		$('#req_contract_start_date').popover('show');
		       
		 		return false;
		   
		    }else{
		     	$('#req_contract_start_date').popover('destroy');
		    }
			
			if (req_contract_end_date == 0) {
		 		
		    	$( "#req_contract_end_date" ).attr( "data-placement", "top" );
		 		$( "#req_contract_end_date" ).attr( "data-content", "Please enter end date.");
		 		$('#req_contract_end_date').popover('show');
		       
		 		return false;
		   
		    }else{
		     	$('#req_contract_end_date').popover('destroy');
		    }*/
	    	if(req_contract_email_id != 0){
	   		if(reg.test(req_contract_email_id) == false){
	   			
	   			$( "#req_contract_email_id" ).attr( "data-placement", "top" );
	   			$( "#req_contract_email_id" ).attr( "data-content", "Please enter valid Email id." );
	   			$('#req_contract_email_id').popover('show');
	   			return false;
	   			
	   		}else{
	   			$('#req_contract_email_id').popover('destroy');
	   		}
	    	}else{
	   			$('#req_contract_email_id').popover('destroy');
	   		}
			if (req_contract_desc == 0) {
	 		
	    	$( "#req_contract_desc" ).attr( "data-placement", "top" );
	 		$( "#req_contract_desc" ).attr( "data-content", "Please enter Purpose of agreement.");
	 		$('#req_contract_desc').popover('show');
	       
	 		return false;
	   
			}else{
	     	$('#req_contract_desc').popover('destroy');
			}
			if (document.getElementById('major_yes').checked){
			if (req_contract_major_clause == 0) {
		 		
		    	$( "#req_contract_major_clause" ).attr( "data-placement", "top" );
		 		$( "#req_contract_major_clause" ).attr( "data-content", "Please enter major clauses.");
		 		$('#req_contract_major_clause').popover('show');
		       
		 		return false;
		   
		    }else{
		     	$('#req_contract_major_clause').popover('destroy');
		    }
			} else{
		     	$('#req_contract_major_clause').popover('destroy');
		    }
			if (document.getElementById('surviving_yes').checked){
			if (req_contract_surviving_clause == 0) {
		 		
		    	$( "#req_contract_surviving_clause" ).attr( "data-placement", "top" );
		 		$( "#req_contract_surviving_clause" ).attr( "data-content", "Please enter surviving clauses.");
		 		$('#req_contract_surviving_clause').popover('show');
		       
		 		return false;
		   
		    }else{
		     	$('#req_contract_surviving_clause').popover('destroy');
		    }
			}else{
		     	$('#req_contract_surviving_clause').popover('destroy');
		    }
			
			if (document.getElementById('payment_yes').checked){
			if (req_contract_perform_rel_payment == 0) {
		 		
		    	$( "#req_contract_perform_rel_payment" ).attr( "data-placement", "top" );
		 		$( "#req_contract_perform_rel_payment" ).attr( "data-content", "Please enter payment.");
		 		$('#req_contract_perform_rel_payment').popover('show');
		       
		 		return false;
		   
		    }else{
		     	$('#req_contract_perform_rel_payment').popover('destroy');
		    }
			}else{
				$('#req_contract_perform_rel_payment').popover('destroy');
			}	
			
			if(document.getElementById('damages_yes').checked){
			if (req_contract_damage == 0) {
		 		
		    	$( "#req_contract_damage" ).attr( "data-placement", "top" );
		 		$( "#req_contract_damage" ).attr( "data-content", "Please enter damages.");
		 		$('#req_contract_damage').popover('show');
		       
		 		return false;
		   
		    }else{
		     	$('#req_contract_damage').popover('destroy');
		    }
			}else{
		     	$('#req_contract_damage').popover('destroy');
		    }
	}
	
	function validateDraft(){
		
		var req_contract_entity_id 				= $('#req_contract_entity_id').val();
	    var req_contract_unit_id 				= $('#req_contract_unit_id').val();
	    var req_contract_function_id 			= $('#req_contract_function_id').val();
	   
	    if (req_contract_entity_id == 0) {

	    	$( "#req_contract_entity_id" ).attr( "data-placement", "top" );
	 		$( "#req_contract_entity_id" ).attr( "data-content", "Please select entity name.");
	 		$('#req_contract_entity_id').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_entity_id').popover('destroy');
	    }
	    
	    if (req_contract_unit_id == 0) {
	 		
	    	$( "#req_contract_unit_id" ).attr( "data-placement", "top" );
	 		$( "#req_contract_unit_id" ).attr( "data-content", "Please select unit name.");
	 		$('#req_contract_unit_id').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_unit_id').popover('destroy');
	    }
		
	    if (req_contract_function_id == 0) {
	 		
	    	$( "#req_contract_function_id" ).attr( "data-placement", "top" );
	 		$( "#req_contract_function_id" ).attr( "data-content", "Please select function name.");
	 		$('#req_contract_function_id').popover('show');
	       
	 		return false;
	   
	    }else{
	     	$('#req_contract_function_id').popover('destroy');
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
			 //alert("reason is the :"+admin_cc); 
			if(query != null && query!='' ){
				var query_type = "contract";
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
			}else{
				bootbox.alert("Please enter query details.");
			}
			}
			
	    });
	 
}
	