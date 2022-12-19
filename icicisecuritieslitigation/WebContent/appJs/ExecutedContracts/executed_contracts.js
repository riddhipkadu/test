$(document).ready(function(){
	
	$("#exec_contract_entity_id").on('change', getAllLocationForOrganization);
	$("#exec_contract_unit_id").on('change', getAllDepartmentsByLocation);
});

$(document).on("click","button[name='delete_executed_contract']",function(){
	
	var exec_contract_id = $(this).val();
	items = {};
	items["exec_contract_id"] = exec_contract_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(exec_contract_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result == true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteExecutedContract",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Executed Contract deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+exec_contract_id).remove();
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
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

$(document).on("click","button[name='delete_executed_contract']",function(){
	
	var exec_contract_id = $(this).val();
	items = {};
	items["exec_contract_id"] = exec_contract_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(exec_contract_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result == true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteExecutedContract",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Executed Contract deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+exec_contract_id).remove();
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
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

$(document).on("click","button[name='delete_executed_contract_doc']",function(){
	
	var exec_doc_id = $(this).val();
	items = {};
	items["exec_doc_id"] = exec_doc_id;
	var jsonString = JSON.stringify(items);
	
		if(exec_doc_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteExecutedContractDocument",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Document deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+exec_doc_id).remove();
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

$("#btnSearch").click(function(){
	//console.log("Called ");
	
	var exec_contract_entity_id = $("#exec_contract_entity_id").val();
    var exec_contract_unit_id = $('#exec_contract_unit_id').val();
    var exec_contract_function_id = $('#exec_contract_function_id').val();
	var exec_contract_id		 	= $("#exec_contract_id").val();
	var exec_contract_type_id 	 	= $("#exec_contract_type_id").val();
	//var exec_contract_executed_with = $("#exec_contract_executed_with").val();
	var user_role          	        = $("#role").val();

		if(exec_contract_entity_id > 0 ){
			$("#btnSearch").hide();
			$("#loader").show();
			
			items = {};
			items["exec_contract_entity_id"]          		= exec_contract_entity_id;
			items["exec_contract_unit_id"]          		= exec_contract_unit_id;
			items["exec_contract_function_id"]          	= exec_contract_function_id;
			items["exec_contract_type_id"]          		= exec_contract_type_id;
			//items["exec_contract_executed_with"]          = exec_contract_executed_with;
			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchExecutedContract",
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
						//console.log("ajax Call");
						var exec_contract_id = value['exec_contract_id'];
						data += "<tr id='row_"+exec_contract_id+"'>";
						data +="<td>"+i+"</td>";	
						data +="<td>"+value['exec_entity_id']+" </td>";
						data +="<td>"+value['exec_unit_id']+" </td>";
						data +="<td>"+value['exec_function_id']+" </td>";
						
						if(value['exec_contract_type_status'] == 1){
						data +="<td>"+value['exec_contract_type_name']+" </td>";
						}else{
							data +="<td>"
							var k =1;
							$.each(value['contract_type'],function(key,value){
							data += k+") "+ value['contract_type_name']+"</br>";
									k++;
								});
							data +="</td>";
						}
						data +="<td>"
					    var j =1;
						$.each(value['parties'],function(key,value){
							data += j+") "+ value['party_name']+"</br>";
							j++;
						});
							
						data +="</td>";
						data +="<td>";
						if((value['user_role']==1 || value['exec_contract_added_by'] == value['user_id']) && value['exec_contract_type_status'] == 1){
						data += '<a href="editExecutedContract?exec_id='+exec_contract_id+'">';
						data += '<button type="button" onclick="" name="submit" value="submit" class="btn btn-primary" style="margin-bottom: 2px;">';
						data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Edit ';
						data += '</button></a><br/>';
						data += '<button type="button" name="delete_executed_contract" value="'+exec_contract_id+'" class="btn btn-danger" style="margin-bottom: 2px;">';
						data += '<i class="fa fa-trash"></i>&nbsp;Delete </button><br/>';
						}else{
						
						}
						if(value['exec_contract_type_status'] == 1){
						data += "<a href='executedContractUpdates?exec_id="+exec_contract_id+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin :2px'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
						}else{
						data += "<a href='updateTab?cont_id="+exec_contract_id+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin :2px'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
						}
						data +="</td>";
						data +="</tr>";
						i++;
						
					}); //console.log("ajax Call");				
					}else{
						data += "<tr><td colspan=7 style='text-align:center'>No result found..!!</td></tr> " ;
					}
					$("#searchResult").html(data); 
					//$("label[for='totaltask']").html($('#example >tbody >tr').length);	
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#exec_contract_entity_id").attr("data-placement", "top");
			$("#exec_contract_entity_id").attr("data-content", "Choose Entity field..!!");
			$('#exec_contract_entity_id').popover('show');
			return false;
		}
});



function getAllLocationForOrganization() {  
	var user_orga_id = $("#exec_contract_entity_id").val();
		if (user_orga_id > 0) {
			/*items = {};
			items['tmap_orga_id'] = user_orga_id;

			var jsonString = JSON.stringify(items);
*/			
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
							$("#exec_contract_unit_id").html(data);	
							$('#exec_contract_function_id').html('<option value="0">--Select--</option>');
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#exec_contract_unit_id').html('<option value="0">--Select--</option>');
			$('#exec_contract_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		var user_orga_id = $("#exec_contract_entity_id").val();
		var user_loca_id = $("#exec_contract_unit_id").val();
		if (user_loca_id > 0) {
			/*items = {};
			items["tmap_orga_id"] = user_orga_id;
			items["tmap_loca_id"] = user_loca_id;

			var jsonString = JSON.stringify(items);*/
			
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
							$("#exec_contract_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#exec_contract_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function deletePartyAjax(cont_party_id){
		var party_id = cont_party_id;
		items = {};
		items["party_id"] = party_id;
		var jsonString = JSON.stringify(items);
		
			if(party_id > 0){
				 bootbox.confirm("Are you sure you want to delete?", function(result) { 
					 if(result==true){
						 $.ajax({
					    		type : "post",
					    		url : "./deletePreExecutedParty",
					    		contentType : "text/html",
					    		dataType : "html",
					    		data : jsonString,
					    		cache : false,
					    		success : function(deleteCount) {
					    			if(deleteCount==1){ 
					    				$("#dialogBox .modal-header").css("background-color", "#097a09");
					    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
					    				$('#dialogBox .modal-body').html("<p><strong>Party deleted successfully!</strong></p>");
					    				$("#dialogBox").modal('show');
					    				$("#party_div_" + party_id).remove();
					    				total_box--;
					    				$("#total_box").val(total_box);
					    				if(total_box <=10)
					    				{ 
					    					$("#add_party_div").show();
					    				}
					    			}
					    			
					    		}
						 });
					 }
				 });
			}
					    			
					    			
					    			
	}

	
	//to block the special characters and numeric values to enter
	function blockSpecialChar(e){
		
	    var k;
	    document.all ? k = e.keyCode : k = e.which;
	   // console.log("Key code "+k)
	    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
	    }
	
	function blockWhiteSpaces( e ) {    
		if (e.which === 32 &&  e.target.selectionStart === 0) {
		      return false;
		    }  
	}

	
	var filecount = 1;
	function addFileInput() {
	   // if(filecount <=5){
		$('#filesContainer')
		.append(
				"<div class='form-group' id='file"+filecount+"'>"
				+"<label class='col-sm-3 control-label'>Upload Document :</label>"
				+"<div class='col-sm-4'><input type='file' name='executed_documents' class='file-loading'/>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteRow("+filecount+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div>");
		filecount++;
	    //}
	}

	function deleteRow(filecount) {
		$("#file" + filecount).remove();
	}
	
	$("#exec_contract_function_id").change(function(){
		
		var user_orga_id = $("#exec_contract_entity_id").val();
		var user_loca_id = $("#exec_contract_unit_id").val();
		var user_dept_id = $("#exec_contract_function_id").val();
		
		if (user_dept_id > 0 && user_loca_id > 0 && user_dept_id > 0) {
			
			var data = {};
			data['tmap_orga_id'] = user_orga_id;
			data['tmap_loca_id'] = user_loca_id;
			data['tmap_dept_id'] = user_dept_id;
			var jsonString = JSON.stringify(data);
			
					$.ajax({
						type : "post",
						url : "./getAllUsersByOrganizationLocationDepartment",
						contentType : "application/json",
						dataType : "json",
						data : jsonString,
						cache : false,
						success : function(userList) {
							var data ="";
							data +="<option value= 0>--Select--</option>";
							
							$.each(userList,function(key,value){
								//console.log("In loop");
								data += "<option value="+value['user_id']+">"+value['user_name']+"</option>";
							});
							$("#exec_contract_resposible_person").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#exec_contract_resposible_person').html('<option value="0">--Select--</option>');
		}
	});
