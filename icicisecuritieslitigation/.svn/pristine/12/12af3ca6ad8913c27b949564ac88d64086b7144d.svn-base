$(document).ready(function(){
	$("#pre_orga_id").on('change', getAllLocationForOrganization);
	$("#pre_loca_id").on('change', getAllDepartmentsByLocation);

});	

	$("#searchContractForExecution").click(function(){
		var pre_orga_id 			= $("#pre_orga_id").val();
		var pre_loca_id 			= $("#pre_loca_id").val();
		var pre_dept_id 			= $("#pre_dept_id").val();
		var pre_contract_type       = $("#pre_contract_type").val();
		var pre_status              = $("#pre_status").val();
		var cont_criticality 		= $("#cont_criticality").val();
		var pre_responsible_user_id = $("#pre_responsible_user_id").val();
		var pre_tergeted_date_from  = $("input[name=pre_tergeted_date_from]").val();
		var pre_tergeted_date_to    = $("input[name=pre_tergeted_date_to]").val();
		
		var user_role               = $("#role").val(); 
		
		if(pre_contract_type[0] > 0){
			pre_contract_type = pre_contract_type[0];
		}else{
			pre_contract_type = 0;
		}
		
		if(pre_orga_id > 0) {
			
			$("#searchContractForExecution").hide();
			$("#loader").show();
			
			var data = {};
			data['pre_orga_id'] 		   = pre_orga_id;
			data['pre_loca_id'] 		   = pre_loca_id;
			data['pre_dept_id'] 		   = pre_dept_id;
			data['pre_contract_type'] 	   = pre_contract_type;
			data['pre_status']             = pre_status;
			data['cont_criticality']       = cont_criticality;
			data['pre_tergeted_date_from'] = pre_tergeted_date_from;
			data['pre_tergeted_date_to']   = pre_tergeted_date_to;
			data['pre_responsible_user_id']= pre_responsible_user_id;
			
			var jsonString = JSON.stringify(data);
			$.ajax({
				type : "POST",
				url  : "./searchContractForExecution",
				data : jsonString,
				contentType : "application/json",
				dataType : "json",
				success : function(result){
					$("#searchContractForExecution").show();
					$("#loader").hide();
                    var i = 1;
					var data = "";
					document.getElementById('count').innerHTML = result.length;
					if(result.length !=0){
					$.each(result,function(key,value){
						data += "<tr id='row_"+value['contract_id']+"'>";
						data += "<td >"+i+"</td>";
						data += "<td >"+value['orga_name']+"</td>";
						data += "<td >"+value['loca_name']+"</td>";
						data += "<td >"+value['dept_name']+"</td>";
						data += "<td>"+value['contract_name']+"</td>";
						data += "<td>"+value['requested_name']+"</td>";
						//data += "<td>"+value['requested_date']+"</td>";
						data += "<td>"+value['expected_date']+"</td>";
						data += "<td>";
						var c = 1;
						$.each(value['parties'],function(key,value){
							data += c+")"+value['party_name']+"<br>";
							c++;
						});
						data += "</td>"
						data += "<td>"+value['responsible_person']+"</td>";
						data += "<td>"+value['targeted_date']+"</td>";
						data += "<td>"+value['contract_status']+"</td>";
						data += "<td class='edit'>";
						if(value['contract_status'] !='Closed/Executed' && (value['user_id'] == value['contract_added_by'] || user_role==1)){
						    data += "<a href='editContract?cont_id="+value['contract_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary btn-block' style='margin-bottom: 4px;'><i class='fa fa-pencil-square-o'></i>&nbsp;Edit</button></a>";
						    data += "<button type='button' value="+value['contract_id']+" name='delete_pre_contract' class='btn btn-danger btn-block' style='margin-bottom: 4px;'><i class='fa fa-trash'></i>&nbsp;Delete</button>";
						}else{
							//data += "No Access.";	
						}
						
						data += "<a href='updateTab?cont_id="+value['contract_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary btn-block'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
						data += "</td>";
						data += "</tr>";
						i++;
					});
				   }else{
					   data += "<tr><td colspan=14 style='text-align:center'>No result found.</td></tr>";
				   }
					$("#searchResult").html(data);
				}
			});
		}else{
			$( "#pre_orga_id" ).attr( "data-placement", "top" );
	 		$( "#pre_orga_id" ).attr( "data-content", "Please select Entity Name." );
	 		$('#pre_orga_id').popover('show');
	        return false;
		}
		
	});


function getAllLocationForOrganization() { 
	var user_orga_id = $("#pre_orga_id").val();
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
				$("#pre_loca_id").html(data);	
			},
				error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	} else {
		$('#pre_loca_id').html('<option value="0">--Select--</option>');
		$('#pre_dept_id').html('<option value="0">--Select--</option>');
	}
}

function getAllDepartmentsByLocation() {
	var user_orga_id = $("#pre_orga_id").val();
	var user_loca_id = $("#pre_loca_id").val();
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
				$("#pre_dept_id").html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	}
	else {
		$('#pre_dept_id').html('<option value="0">--Select--</option>');
	}

}

$(document).on("click","button[name='delete_pre_contract']",function(){
	
	var cont_id = $(this).val();
	items = {};
	items["cont_id"] = cont_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(cont_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deletePreExecutedContract",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Contract deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#ContractList tr#row_'+cont_id).remove();
				    				document.getElementById('count').innerHTML = ($('#ContractList >tbody >tr').length);
				        			//window.location.reload(true);
				    			}
				    			else{
				    				$('#dialogBox .modal-body').html("<p><strong>Please try again!</strong></p>");
			    				    $("#dialogBox").modal('show');
				    			}
				    			 	
				    		},
					 
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
								});
				 }
			});
	}
});
