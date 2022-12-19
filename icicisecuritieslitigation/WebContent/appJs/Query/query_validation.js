
$(document).ready(function(){
	
			$("#others_div_id").hide();
			var query_hst_status = $("#query_hst_status").val();
			if (query_hst_status == 'Others') {
				$("#others_div_id").show();
			} else {
				$("#others_div_id").val("NA");
				$("#others_div_id").hide();
			}
			
			$("#others_action_performed_div").hide();
			var query_hst_action_tobe_performed = $(
					"#query_hst_action_tobe_performed").val();
			if (query_hst_action_tobe_performed == 'Others') {
				$("#others_action_performed_div").show();
			} else {
				$("#others_action_performed_div").val("NA");
				$("#others_action_performed_div").hide();
			}

			$("#others_action_performed_by_div").hide();
			var query_hst_action_tobe_performed_by = $(
					"#query_hst_action_tobe_performed_by").val();
			if (query_hst_action_tobe_performed_by == 'Others') {
				$("#others_action_performed_by_div").show();
			} else {
				$("#others_action_performed_by_div").val("NA");
				$("#others_action_performed_by_div").hide();
			}
		
	$("#query_hst_status").change(function(){ 
		var query_hst_status = $("#query_hst_status").val();
		if(query_hst_status == 'Others'){
			$("#others_div_id").show();
		}else{
			$("#others_div_id").val("NA");
			$("#others_div_id").hide();
		}
	});
	$("#query_hst_action_tobe_performed").change(function(){ 
		var query_hst_action_tobe_performed = $("#query_hst_action_tobe_performed").val();
		if(query_hst_action_tobe_performed == 'Others'){
			$("#others_action_performed_div").show();
		}else{
			$("#others_action_performed_div").val("NA");
			$("#others_action_performed_div").hide();
		}
	});

	$("#query_hst_action_tobe_performed_by").change(function(){ 
		var query_hst_action_tobe_performed_by = $("#query_hst_action_tobe_performed_by").val();
		if(query_hst_action_tobe_performed_by == 'Others'){
			$("#others_action_performed_by_div").show();
		}else{
			$("#others_action_performed_by_div").val("NA");
			$("#others_action_performed_by_div").hide();
		}
	});
	
	$("#quer_entity_id").on('change', getAllLocationForOrganization);
	$("#quer_unit_id").on('change', getAllDepartmentsByLocation);
	$("#quer_function_id").on('change', getAllUserByOrgaLocaFun); 

	//set from query date as today	
	$("#from_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	//set to query date as today
	$("#to_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	//set query date as today
	$("#quer_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
	$("#replied_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		endDate: new Date()
	}).datepicker();
	$("#reminder_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	$("#reply_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	
	// Start Date Picker for Report 
	
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
	
	//End Date Picker 
	
	//******************** code for reminder date < reply date ********************************************	
	$("#reminder_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",

	}).datepicker().on('changeDate', function(e){

	$('#reply_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});
	$('#reply_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', $('#quer_reminder_date').val()).focus();

		$("#reply_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#reminder_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		$('#reminder_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', $('#quer_reply_date').val()).focus();
		
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


$(document).on("click","button[name='delete_query']",function(){
	
	var quer_id = $(this).val();
	items = {};
	items["quer_id"] = quer_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(quer_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteQuery",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Query deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+quer_id).remove();
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

$(document).on("click","button[name='deleteQueryHst']",function(){
	
	var query_hst_id = $(this).val();
	items = {};
	items["query_hst_id"] = query_hst_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(query_hst_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteQueryHistory",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Query History deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example1 tr#row_'+query_hst_id).remove();
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

//delete the query document attached
$(document).on("click","button[name='delete_query_document']",function(){
	var quer_doc_id = $(this).val();

	//console.log("liti_id "+liti_id);
	items = {};
	items["quer_doc_id"] = quer_doc_id;
	var jsonString = JSON.stringify(items);

	if(quer_doc_id > 0){
		bootbox.confirm("Are you sure you want to delete?", function(result) { 
			if(result==true){
				$.ajax({
					type : "post",
					url : "./deleteQueryDocument",
					contentType : "text/html",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(deleteCount) {
						if(deleteCount=="Success"){ 
							$("#dialogBox .modal-header").css("background-color", "#097a09");
							$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
							$('#dialogBox .modal-body').html("<p><strong>Query Document deleted successfully!</strong></p>");
							$("#dialogBox").modal('show');
							//window.location.reload(true);
							//getAllLitigationDocuments();
							$('table#example tr#row_'+quer_doc_id).remove();
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

function deleteQueryDoc(quer_doc_id){
	var quer_doc_id = quer_doc_id;
	items = {};
	items["quer_doc_id"] = quer_doc_id;
	var jsonString = JSON.stringify(items);
	
		if(quer_doc_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteQueryDocument",
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
				    				$("#doc_attached_" + quer_doc_id).remove();
				    			}
				    			
				    		}
					 });
				 }
			 });
		}
}

$("#btnSearch").click(function(){
	
	
	//console.log("Called ");
	var quer_id = $("#quer_id").val();
	var quer_entity_id = $("#quer_entity_id").val();
	var quer_unit_id = $("#quer_unit_id").val();
	var quer_function_id = $("#quer_function_id").val();
	var quer_assigned_to = $("#quer_search_assigned_to").val();
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
	var user_role               = $("#role").val();
		if(quer_entity_id > 0 ){
			
			$("#btnSearch").hide();
			$("#loader").show();
			
			items = {};
			items["quer_entity_id"]          = quer_entity_id;
			items["quer_unit_id"]            = quer_unit_id;
			items["quer_function_id"] 		 = quer_function_id;
			items["quer_assigned_to"] 		 = quer_assigned_to;
			items["from_date"] 		 		 = from_date;
			items["to_date"] 		 		 = to_date;

			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchQuery",
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
						
						var quer_id = value['quer_id'];
						data += "<tr id ='row_"+quer_id+"'>";
						data +="<td>"+i+"</td>";
						data +="<td>"+value['entity_name']+" </td>";
						data +="<td>"+value['unit_name']+" </td>";
						data +="<td>"+value['dept_name']+" </td>";
						data +="<td>"+value['quer_query']+" </td>";
						data +="<td>"+value['quer_assigned_to_name']+" </td>";
						data +="<td>"+value['quer_reply_date']+" </td>";
						data +="<td>"+value['query_status']+ "</td>";
						data +="<td>"
						if(value['user_role'] ==1 || value['added_by']==value['user_id'] ){
						data += '<a href="editQuery?query_id='+quer_id+'">';
						data += '<button type="button" onclick="" name="submit" value="submit" class="btn btn-primary btn-block" style="margin-bottom :2px">';
						data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Edit ';
						data += '</button></a>';
						data += '<button type="button" name="delete_query" value="'+quer_id+'" class="btn btn-danger btn-block" style="margin-bottom: 2px;">';
						data += '<i class="fa fa-trash"></i>&nbsp;Delete </button>';
						
						}else{
						data +=""
						}
						data += "<a href='queryDetails?query_id="+quer_id+"'><button type='button' name='submit' value='submit' class='btn btn-primary btn-block' style='margin :2px'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
						data +="</td>";
						data +="</tr>";
						i++;
						
					});				
					}else{
						data += "<tr><td colspan=9 style='text-align:center'>No result found..!!</td></tr> " ;
					}
					$("#searchResult").html(data); 
					//$("label[for='totaltask']").html($('#example >tbody >tr').length);	
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#quer_entity_id").attr("data-placement", "top");
			$("#quer_entity_id").attr("data-content", "Choose Entity Field..!!");
			$('#quer_entity_id').popover('show');
			return false;
		}
});

$(document).on("click","button[name='statusQueryHstLogs']",function(){
	var query_hst_id = $(this).val();
	if(query_hst_id > 0) {
	var data = {};
	
	data["query_hst_id"]    = query_hst_id;

	var jsonString = JSON.stringify(data);
	
	$.ajax({
		type : "POST",
		url  : "./getQueryHistoryLogs",
		data : jsonString,
		contentType : "application/json",
		dataType : "json",
		success : function(result){
			var data = "";
			if(result.length != 0){
			var i = 1;
			$.each(result,function(key,value){
				data += "<tr>";
				data += "<td>"+i+" </td>";
				/*data += "<td>"+value['query_hst_replied_by']+"</td>";*/
				data += "<td>"+value['query_hst_action_assigned_to']+" </td>";
				if(value['query_hst_status'] == 'Others'){
				data += "<td>"+value['query_hst_others']+" </td>";	
				}else{
				data += "<td>"+value['query_hst_status']+" </td>";
				}
				data += "<td>"+value['query_hst_replied_date']+" </td>";
				if(value['query_hst_action_tobe_performed'] == 'Others'){
				data += "<td>"+value['query_hst_action_performed_others']+" </td>";
				}else{
				data += "<td>"+value['query_hst_action_tobe_performed']+" </td>";
				}
				if(value['query_hst_action_tobe_performed_by']=='Others'){
					data += "<td>"+value['query_hst_action_performed_by_others']+" </td>";
				}else{
				data += "<td>"+value['query_hst_action_tobe_performed_by']+" </td>";
				}
				data += "<td>"+value['query_hst_comments']+" </td>";
				data += "<td>"+value['query_hst_log_status']+" </td>";
				data += "</tr>"
			i++;	
			});
		   }else{
			   data += "<tr><td colspan=11 style='text-align:center'>No result found.</td></tr>";
			   
		   }
			$("#loader").hide();
			$("#log_model_query").modal('show');
			$("#logResultQuery").html(data);
		}
	});	
  }
	
});


function getAllLocationForOrganization() { 
	var user_orga_id = $("#quer_entity_id").val();
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
							$("#quer_unit_id").html(data);	
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#quer_unit_id').html('<option value="0">--Select--</option>');
			$('#quer_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		//alert("In function");
		var user_orga_id = $("#quer_entity_id").val();
		var user_loca_id = $("#quer_unit_id").val();
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
							$("#quer_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#quer_function_id').html('<option value="0">--Select--</option>');
		}
		
	}
	
	function getAllUserByOrgaLocaFun() {
		//alert("In function");
		var user_orga_id = $("#quer_entity_id").val();
		var user_loca_id = $("#quer_unit_id").val();
		var user_dept_id = $("#quer_function_id").val();
		
		if (user_dept_id > 0 && user_loca_id > 0 && user_dept_id > 0) {
			
					$.ajax({
						type : "post",
						url : "./getAllUserByFunction",
						//contentType : "application/json",
						dataType : "json",
						data : {dept_id : user_dept_id, loca_id : user_loca_id, orga_id: user_orga_id},
						cache : false,
						success : function(userList) {
							var data ="";
							data +="<option value= 0>--Select--</option>";
							
							$.each(userList,function(key,value){
								//console.log("In loop");
								data += "<option value="+value['user_id']+">"+value['user_name']+"</option>";
							});
							$("#quer_search_assigned_to").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#quer_search_assigned_to').html('<option value="0">--Select--</option>');
		}
		
	}
	
	$("#quer_function_id").change(function(){
		
		var user_orga_id = $("#quer_entity_id").val();
		var user_loca_id = $("#quer_unit_id").val();
		var user_dept_id = $("#quer_function_id").val();
		
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
							$("#quer_assigned_to").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#quer_assigned_to').html('<option value="0">--Select--</option>');
		}
	});
	
	
// validation function for parameters
	function validateForm() {
		        
		var quer_entity_id = $("#quer_entity_id").val();
        var quer_unit_id = $('#quer_unit_id').val();
        var quer_function_id = $('#quer_function_id').val();
        var quer_from_id = $('#quer_from_id').val();
        var quer_query = $('#quer_query').val();
        var quer_assigned_to = $('#quer_assigned_to').val();
        var quer_query_date = $('#quer_query_date').val();
        var quer_reply_date = $('#quer_reply_date').val();
        var quer_reminder_date = $('#quer_reminder_date').val();
        
        if (quer_entity_id == 0 ) {
       	 
     		$( "#quer_entity_id" ).attr( "data-placement", "top" );
     		$( "#quer_entity_id" ).attr( "data-content", "Please select entity name." );
     		$('#quer_entity_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#user_orga_id').popover('destroy');
        }
    	
    	if (quer_unit_id == 0) {
          	 
     		$( "#quer_unit_id" ).attr( "data-placement", "top" );
     		$( "#quer_unit_id" ).attr( "data-content", "Please select unit name." );
     		$('#quer_unit_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_unit_id').popover('destroy');
        }
    	
    	if (quer_function_id == 0) {
       	 
     		$( "#quer_function_id" ).attr( "data-placement", "top" );
     		$( "#quer_function_id" ).attr( "data-content", "Please select function name." );
     		$('#quer_function_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_function_id').popover('destroy');
        }
    	
    	if (quer_from_id == 0) {
          	 
     		$( "#quer_from_id" ).attr( "data-placement", "top" );
     		$( "#quer_from_id" ).attr( "data-content", "Please enter person Name." );
     		$('#quer_from_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_from_id').popover('destroy');
        }
    	
    	if (quer_query == 0) {
         	 
     		$( "#quer_query" ).attr( "data-placement", "top" );
     		$( "#quer_query" ).attr( "data-content", "Please enter query." );
     		$('#quer_query').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_query').popover('destroy');
        }
    	
    	if (quer_assigned_to == 0) {
        	 
     		$( "#quer_assigned_to" ).attr( "data-placement", "top" );
     		$( "#quer_assigned_to" ).attr( "data-content", "Please enter person name." );
     		$('#quer_assigned_to').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_assigned_to').popover('destroy');
        }
    	
    	if (quer_query_date == 0) {
       	 
     		$( "#quer_query_date" ).attr( "data-placement", "top" );
     		$( "#quer_query_date" ).attr( "data-content", "Please enter query date." );
     		$('#quer_query_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_query_date').popover('destroy');
        }
    	
    	
    	if (quer_reply_date == 0) {
       	 
     		$( "#quer_reply_date" ).attr( "data-placement", "top" );
     		$( "#quer_reply_date" ).attr( "data-content", "Please enter reply date." );
     		$('#quer_reply_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_reply_date').popover('destroy');
        }
    	if (quer_reminder_date == 0) {
          	 
     		$( "#quer_reminder_date" ).attr( "data-placement", "top" );
     		$( "#quer_reminder_date" ).attr( "data-content", "Please enter reminder date." );
     		$('#quer_reminder_date').popover('show');
            return false;
        }
    	 else
         {
         	$('#quer_reminder_date').popover('destroy');
        }
    	
    	
    	
    	
    	/*if(Date.parse(quer_reminder_date) >= Date.parse(quer_reply_date)) {
          	 
     		$( "#quer_reminder_date" ).attr( "data-placement", "top" );
     		$( "#quer_reminder_date" ).attr( "data-content", "Please enter date before the reply date." );
     		$('#quer_reminder_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_reminder_date').popover('destroy');
        }*/
   }
	
	
	function validateHstForm(){
		
			var query_hst_status = $('#query_hst_status').val();
	        var query_hst_others = $('#query_hst_others').val();
	        var query_hst_replied_date = $('#query_hst_replied_date').val();
	        var query_hst_action_tobe_performed = $('#query_hst_action_tobe_performed').val();
	        var query_hst_action_performed_others = $('#query_hst_action_performed_others').val();
	        var query_hst_action_tobe_performed_by = $('#query_hst_action_tobe_performed_by').val();
	        var query_hst_action_performed_by_others = $('#query_hst_action_performed_by_others').val();
	        var query_hst_action_assigned_to = $('#query_hst_action_assigned_to').val();
	        //var query_hst_replied_by = $('#query_hst_replied_by').val();
	        var query_hst_comments = $('#query_hst_comments').val();

    	if (query_hst_status == 0) {
         	 
     		$( "#query_hst_status" ).attr( "data-placement", "top" );
     		$( "#query_hst_status" ).attr( "data-content", "Please select status." );
     		$('#query_hst_status').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_status').popover('destroy');
        }
    	if (query_hst_status == "Save As Draft") {
        	 
     		$( "#query_hst_status" ).attr( "data-placement", "top" );
     		$( "#query_hst_status" ).attr( "data-content", "Please select status other than 'Save As Draft'." );
     		$('#query_hst_status').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_status').popover('destroy');
        }
    	if(query_hst_status == 'Others'){
    	if (query_hst_others == 0) {
       	 
     		$( "#query_hst_others" ).attr( "data-placement", "top" );
     		$( "#query_hst_others" ).attr( "data-content", "Please enter others." );
     		$('#query_hst_others').popover('show');
	
            return false;
        }
    	}
    	 else
         {
         	$('#query_hst_others').popover('destroy');
        }
    	/*if (query_hst_replied_date == 0) {
          	 
     		$( "#query_hst_replied_date" ).attr( "data-placement", "top" );
     		$( "#query_hst_replied_date" ).attr( "data-content", "Please Enter Replied Date." );
     		$('#query_hst_replied_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_replied_date').popover('destroy');
        }*/
    	
    	if (query_hst_action_tobe_performed == 0) {
         	 
     		$( "#query_hst_action_tobe_performed" ).attr( "data-placement", "top" );
     		$( "#query_hst_action_tobe_performed" ).attr( "data-content", "Please enter action to be performed." );
     		$('#query_hst_action_tobe_performed').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_action_tobe_performed').popover('destroy');
        }
    	if(query_hst_action_tobe_performed == 'Others'){
    	if (query_hst_action_performed_others == 0) {
         	 
     		$( "#query_hst_action_performed_others" ).attr( "data-placement", "top" );
     		$( "#query_hst_action_performed_others" ).attr( "data-content", "Please enter others." );
     		$('#query_hst_action_performed_others').popover('show');
	
            return false;
        }
    	}
    	 else
         {
         	$('#query_hst_action_performed_others').popover('destroy');
        }
    	if (query_hst_action_tobe_performed_by == 0) {
         	 
     		$( "#query_hst_action_tobe_performed_by" ).attr( "data-placement", "top" );
     		$( "#query_hst_action_tobe_performed_by" ).attr( "data-content", "Please enter action to be performed by." );
     		$('#query_hst_action_tobe_performed_by').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_action_tobe_performed_by').popover('destroy');
        }
    	if(query_hst_action_tobe_performed_by == 'Others'){
    	if (query_hst_action_performed_by_others == 0) {
         	 
     		$( "#query_hst_action_performed_by_others" ).attr( "data-placement", "top" );
     		$( "#query_hst_action_performed_by_others" ).attr( "data-content", "Please enter others." );
     		$('#query_hst_action_performed_by_others').popover('show');
	
            return false;
        }
    	}
    	 else
         {
         	$('#query_hst_action_performed_by_others').popover('destroy');
        }
    	
    	if (query_hst_action_assigned_to == 0) {
        	 
     		$( "#query_hst_action_assigned_to" ).attr( "data-placement", "top" );
     		$( "#query_hst_action_assigned_to" ).attr( "data-content", "Please enter action assigned to." );
     		$('#query_hst_action_assigned_to').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_action_assigned_to').popover('destroy');
        }
    	/*if (query_hst_replied_by == 0) {
        	 
     		$( "#query_hst_replied_by" ).attr( "data-placement", "top" );
     		$( "#query_hst_replied_by" ).attr( "data-content", "Please enter action performed by." );
     		$('#query_hst_replied_by').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_replied_by').popover('destroy');
        }*/
    	if (query_hst_comments == 0) {
        	 
     		$( "#query_hst_comments" ).attr( "data-placement", "top" );
     		$( "#query_hst_comments" ).attr( "data-content", "Please enter comments." );
     		$('#query_hst_comments').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#query_hst_comments').popover('destroy');
        }
	}

	function validateDraft() {
        
		var quer_entity_id = $("#quer_entity_id").val();
        var quer_unit_id = $('#quer_unit_id').val();
        var quer_function_id = $('#quer_function_id').val();
        
        
        if (quer_entity_id == 0 ) {
       	 
     		$( "#quer_entity_id" ).attr( "data-placement", "top" );
     		$( "#quer_entity_id" ).attr( "data-content", "Please select entity name." );
     		$('#quer_entity_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#user_orga_id').popover('destroy');
        }
    	
    	if (quer_unit_id == 0) {
          	 
     		$( "#quer_unit_id" ).attr( "data-placement", "top" );
     		$( "#quer_unit_id" ).attr( "data-content", "Please select unit name." );
     		$('#quer_unit_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_unit_id').popover('destroy');
        }
    	
    	if (quer_function_id == 0) {
       	 
     		$( "#quer_function_id" ).attr( "data-placement", "top" );
     		$( "#quer_function_id" ).attr( "data-content", "Please select function name." );
     		$('#quer_function_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#quer_function_id').popover('destroy');
        }
    	
   }

function validateHstDraft(){
	var query_hst_status = $('#query_hst_status').val();
    var query_hst_others = $('#query_hst_others').val();
    var query_hst_replied_date = $('#query_hst_replied_date').val();
    var query_hst_action_tobe_performed = $('#query_hst_action_tobe_performed').val();
    var query_hst_action_performed_others = $('#query_hst_action_performed_others').val();
    var query_hst_action_tobe_performed_by = $('#query_hst_action_tobe_performed_by').val();
    var query_hst_action_performed_by_others = $('#query_hst_action_performed_by_others').val();
    var query_hst_action_assigned_to = $('#query_hst_action_assigned_to').val();
	
    if (query_hst_status == 0) {
   	 
 		$( "#query_hst_status" ).attr( "data-placement", "top" );
 		$( "#query_hst_status" ).attr( "data-content", "Please select status." );
 		$('#query_hst_status').popover('show');

        return false;
    }
	 else
     {
     	$('#query_hst_status').popover('destroy');
    }
	
	if (query_hst_status != 'Save As Draft') {
   	 
 		$( "#query_hst_status" ).attr( "data-placement", "top" );
 		$( "#query_hst_status" ).attr( "data-content", "Please select 'Save As Draft' status." );
 		$('#query_hst_status').popover('show');

        return false;
    }
	 else
     {
     	$('#query_hst_status').popover('destroy');
    }
	
	if (query_hst_action_tobe_performed == 0) {
    	 
 		$( "#query_hst_action_tobe_performed" ).attr( "data-placement", "top" );
 		$( "#query_hst_action_tobe_performed" ).attr( "data-content", "Please select action to be performed." );
 		$('#query_hst_action_tobe_performed').popover('show');

        return false;
    }
	 else
     {
     	$('#query_hst_action_tobe_performed').popover('destroy');
    }
	if(query_hst_action_tobe_performed == 'Others'){
	if (query_hst_action_performed_others == 0) {
     	 
 		$( "#query_hst_action_performed_others" ).attr( "data-placement", "top" );
 		$( "#query_hst_action_performed_others" ).attr( "data-content", "Please enter others." );
 		$('#query_hst_action_performed_others').popover('show');

        return false;
    }
	}	 else
     {
     	$('#query_hst_action_performed_others').popover('destroy');
    }
}