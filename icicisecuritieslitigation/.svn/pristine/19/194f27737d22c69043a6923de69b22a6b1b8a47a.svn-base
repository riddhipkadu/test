
$(document).ready(function(){
	$("#lega_noti_entity_id").on('change', getAllLocationForOrganization);
	$("#lega_noti_unit_id").on('change', getAllDepartmentsByLocation);
	$("#lega_noti_entity_id").on('change', getUserByLegalNoticeId);
	//$("#lega_noti_unit_id").on('change', getUserByLegalNoticeId);
	//$("#lega_noti_function_id").on('change', getUserByLegalNoticeId);

		/*$("#others_div_id").hide();
	$("#query_hst_status").change(function() {
		var query_hst_status = $("#query_hst_status").val();
		if (query_hst_status == 'Others') {
			$("#others_div_id").show();
		} else {
			$("#others_div_id").val("NA");
			$("#others_div_id").hide();
		}
	});*/
	
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
	$("#noti_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	//set to query date as today
	$("#recei_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	$("#reply_dead_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	$("#noti_remain_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	
	$("#noti_amount_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
	
	$("#noti_amount_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"

	}).datepicker({ autoclose: true}).datepicker('setEndDate', new Date()).focus();
	
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
	
	//******************** code for notice date < received on date ********************************************	
	
	$("#noti_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		//defaultViewDate:"today"


	}).datepicker().on('changeDate', function(e){

	$('#recei_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});

		$("#recei_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#noti_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		
	//************************************ code for notice date < deadline date **********************************************	
		$("#noti_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#reply_dead_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
		});

			$("#reply_dead_div").datepicker({
				 
			 	autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
				endDate:"now",
				todayHighlight:"true",
				showOnFocus:"true",
				//defaultViewDate:"today"

			}).datepicker().on('changeDate', function(e){
				
				$('#noti_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
			});
			
	//************************************** code for deadline date > reminder date > notice date ************************************
			$("#noti_date_div").datepicker({
				 
			 	autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
				endDate:"now",
				todayHighlight:"true",
				showOnFocus:"true",
				//defaultViewDate:"today"


			}).datepicker().on('changeDate', function(e){

			$('#noti_remain_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
			});

				$("#noti_remain_div").datepicker({
					 
				 	autoclose : true,
					format : "dd-mm-yyyy",
					viewMode : "auto",
					minViewMode : "auto",
					endDate:"now",
					todayHighlight:"true",
					showOnFocus:"true",
					//defaultViewDate:"today"
					
				}).datepicker().on('changeDate', function(e){
					
					$('#noti_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
				});
							 
				 $("#reply_dead_div").datepicker({
					 
					 	autoclose : true,
						format : "dd-mm-yyyy",
						viewMode : "auto",
						minViewMode : "auto",
						endDate:"now",
						todayHighlight:"true",
						showOnFocus:"true",
						//defaultViewDate:"today"

					}).datepicker().on('changeDate', function(e){
						
						$('#noti_remain_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
					});
				// $('#noti_remain_div').datepicker({ autoclose: true}).datepicker('setEndDate', $("#lega_noti_reply_deadline").val()).focus();
				// $('#noti_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', $("#lega_noti_reminder_date").val()).focus();
				 $('#noti_remain_div').datepicker({ autoclose: true}).datepicker('setStartDate', $("#lega_noti_dated").val()).focus();
				 //$('#noti_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', $("#lega_noti_reply_deadline").val()).focus();
				// $('#reply_dead_div').datepicker({ autoclose: true}).datepicker('setStartDate', $("#lega_noti_dated").val()).focus();
				 //$('#recei_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', $("#lega_noti_dated").val()).focus();
				 $('#noti_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', $("#lega_noti_recived_on").val()).focus();
				 
});

/****************************code for online converted currency rate starts here ****************************************************/

$("#lega_noti_involved_amt_currency").on("change", getConversionRate);
$("#lega_noti_converted_amt_currency").on("change", getConversionRate);
$("#lega_noti_amount_date").on("change", getConversionRate);

function getConversionRate(){
	
	var amount_involved = $('#lega_noti_amount_involved').val();
	var converted_amt_currency = $("#lega_noti_converted_amt_currency").val();
	var involved_amt_currency = $('#lega_noti_involved_amt_currency').val();
	var amount_date = $('#lega_noti_amount_date').val();
	var conversion_rate = $('#lega_noti_conversion_rate').val();
	
	if(involved_amt_currency != "NA" && converted_amt_currency != "NA" ){
			items = {};
			items["converted_amt_currency"]           = converted_amt_currency;
			items["involved_amt_currency"]            = involved_amt_currency;
			items["amount_date"] 		 			  = amount_date;
			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./getConversionRate",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(result) {
					 if(result.length != 0){ 
							 var rate = result;
							 //alert("£1 =" + rate)
								  $("#lega_noti_conversion_rate").val(rate);
								  $("#lega_noti_converted_amt").val(rate*amount_involved);
								  
								  if (rate == 0) {
							         	 
							      		$( "#lega_noti_conversion_rate" ).attr( "data-placement", "bottom" );
							      		$( "#lega_noti_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#lega_noti_conversion_rate').popover('show');
							    	
							             return false;
							         }
							     	 else
							          {
							          	$('#lega_noti_conversion_rate').popover('destroy');
							          }
					 }
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
	}
		
}
/****************************code for online converted interest calculation starts here ****************************************************/

function getConversionRate(){
	
	var amount_involved = $('#lega_noti_amount_involved').val();
	var interest = $("#lega_noti_interest").val();
	var liti_total_amount = $('#lega_noti_total_amount').val();
	
			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./getConversionRate",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(result) {
					 if(result.length != 0){ 
							 var rate = result;
							
								  $("#lega_noti_interest").val(rate);
								  
								  $("#lega_noti_total_amount").val(((rate /100) * amount_involved) + parseInt(amount_involved));
								  if (rate == 0) {
							         	 
							      		$( "#lega_noti_interest" ).attr( "data-placement", "bottom" );
							      		$( "#lega_noti_interest" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#lega_noti_interest').popover('show');
							    	
							             return false;
							        }else{
							          	$('#lega_noti_interest').popover('destroy');
							        }
					 }
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
	
}
$("#lega_noti_interest").change(function(){
	var liti_amount_involved = $('#lega_noti_amount_involved').val();
	var liti_interest = $("#lega_noti_interest").val();
	if(liti_interest !=''){
	var liti_interest = $('#lega_noti_interest').val();
	$("#lega_noti_total_amount").val(((liti_interest/100)*liti_amount_involved)+parseInt(liti_amount_involved));
	}else{
		$("#lega_noti_interest").val(0.0);
	}
});

$("#lega_noti_amount_involved").keyup(function(){
	var liti_interest = $("#lega_noti_interest").val();
	var liti_amount_involved = $('#lega_noti_amount_involved').val();
	$("#lega_noti_total_amount").val(((liti_interest/100)*liti_amount_involved)+parseInt(liti_amount_involved));
});


/****************************code for online converted currency rate ends here ****************************************************/

$("#lega_noti_conversion_rate").on("change", function(){
	var lega_noti_amount_involved = $('#lega_noti_amount_involved').val();
	var lega_noti_conversion_rate = $("#lega_noti_conversion_rate").val();
	if(lega_noti_conversion_rate !=''){
	var lega_noti_conversion_rate = $('#lega_noti_conversion_rate').val();
	$("#lega_noti_converted_amt").val(lega_noti_conversion_rate*lega_noti_amount_involved);
	}else{
		$("#lega_noti_conversion_rate").val(0.0);
	}
});

$("#lega_noti_amount_involved").keyup(function(){
	var lega_noti_conversion_rate = $("#lega_noti_conversion_rate").val();
	var lega_noti_amount_involved = $('#lega_noti_amount_involved').val();
	$("#lega_noti_converted_amt").val(lega_noti_conversion_rate*lega_noti_amount_involved);
});

	
	
$("#btnSearch").click(function(){
	$("#btnSearch").hide();
	$("#loader").show();
	//console.log("Called ");
	var lega_noti_id = $("#lega_noti_id").val();
	var lega_noti_entity_id = $("#lega_noti_entity_id").val();
	//var lega_noti_unit_id = $("#lega_noti_unit_id").val();
	//var lega_noti_function_id = $("#lega_noti_function_id").val();
	var lega_noti_by_against = $("#lega_noti_by_against").val();
	var lega_noti_category_id = $("#lega_noti_category_id").val();
	var lega_noti_assigned_id = $("#lega_noti_assigned_id").val();
	var lega_noti_secondary_responsible_person = $("#lega_noti_secondary_responsible_person").val();
	var lega_noti_third_responsible_person = $("#lega_noti_third_responsible_person").val();
	var lega_noti_from_date = $("#lega_noti_from_date").val();
	var lega_noti_to_date = $("#lega_noti_to_date").val();
	
	//var user_role               = $("#role").val();

		if(lega_noti_entity_id > 0 ){
			items = {};
			items["lega_noti_entity_id"]             = lega_noti_entity_id;
			//items["lega_noti_unit_id"]               = lega_noti_unit_id;
			//items["lega_noti_function_id"] 		     = lega_noti_function_id;
			items["lega_noti_by_against"] 		     = lega_noti_by_against;
			items["lega_noti_category_id"] 		     = lega_noti_category_id;
			items["lega_noti_assigned_id"] 		     = lega_noti_assigned_id;
			items["lega_noti_secondary_responsible_person"]  = lega_noti_secondary_responsible_person;
			items["lega_noti_third_responsible_person"]  = lega_noti_third_responsible_person;
			items["lega_noti_from_date"] 		     = lega_noti_from_date;
			items["lega_noti_to_date"] 		    	  = lega_noti_to_date;
			
			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchLegalNotice",
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
						
						var lega_noti_id = value['lega_noti_id'];
						data += "<tr id ='row_"+lega_noti_id+"'>";
						data +='<td><center>'+i+"</center></a></td>";
						data +="<td>"+value['lega_noti_entity_name']+" </td>";
						//data +="<td>"+value['lega_noti_unit_name']+" </td>";
						//data +="<td>"+value['lega_noti_function_name']+" </td>";
						data +="<td>"+value['lega_noti_category_name']+" </td>";
						data +="<td>"+value['lega_noti_dated']+ "</td>";
						data +="<td>"+value['lega_noti_recived_on']+ "</td>";
						data +="<td>"+value['lega_noti_reply_deadline']+ "</td>";
						data +="<td>"+value['lega_noti_assigned_to_name']+ "</td>";
						data +="<td>"+value['lega_noti_secondary_responsible_person_name']+ "</td>";
						data +="<td>"+value['lega_noti_third_responsible_person_name']+ "</td>";
						data +="<td>"+value['lega_noti_reminder_date']+ "</td>";
						data +='<td>'+value['lega_noti_status']+ '<br/>';
						
						if(value['lega_noti_status'] != 'Converted'){
						data +='<a href="./addLitigationByAccept?id='+lega_noti_id+'&type=noti">'
						data += '<button type="button" name="submit" value="submit" style="margin:2px" class="btn btn-success">'
						data += '&nbsp;Convert To Litigation</button></a> '
						}
						data +='</td>';
						data += "<td>";
						if(value['lega_user_role']==1 || value['lega_user_id']==value['lega_added_by']){
							
						data += '<a href="editLegalNotice?lega_noti_id='+lega_noti_id+'">';
						data += '<button type="button" onclick="" name="submit" value="submit" class="btn btn-primary btn-block" style="margin-bottom: 4px;">';
						data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Edit ';
						data += '</button></a>';
						data += '<button type="button" name="delete_legal_notice" value="'+lega_noti_id+'" class="btn btn-danger btn-block" style="margin-bottom: 4px;">';
						data += '<i class="fa fa-trash"></i>&nbsp;Delete </button>';
						
						 }else{
							 data +=" No Access.";
						 } 
						data += "<a href='legalNoticeUpdate?lega_noti_id="+lega_noti_id+"'><button type='button' name='submit' value='submit' class='btn btn-primary btn-block' style='margin :2px'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
						data +="</td></tr>";
						i++;
						
					});				
					}else{
						data += "<tr><td colspan=11 style='text-align:center'>No result found..!!</td></tr> " ;
					}
					$("#searchResult").html(data); 
					//$("label[for='totaltask']").html($('#example >tbody >tr').length);	
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#lega_noti_entity_id").attr("data-placement", "top");
			$("#lega_noti_entity_id").attr("data-content", "Choose Entity Field..!!");
			$('#lega_noti_entity_id').popover('show');
			return false;
		}
});

$(document).on("click","button[name='delete_legal_notice']",function(){
	
	var lega_noti_id = $(this).val();
	items = {};
	items["lega_noti_id"] = lega_noti_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(lega_noti_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLegalNotice",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Legal Notice deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#NoticeList tr#row_'+lega_noti_id).remove();
				    				document.getElementById('count').innerHTML = ($('#NoticeList >tbody >tr').length);	
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

function getAllLocationForOrganization() { 
		
	var user_orga_id = $("#lega_noti_entity_id").val();
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
							//var locationjson = $.parseJSON(locationlist);
							data ="";
							data +='<option value = 0>--Select--</option>';

							$.each(locationlist,function(key,value){
								data += "<option value="+value['loca_id']+">"+value['loca_name']+"</option>";
							});
							$("#lega_noti_unit_id").html(data);	
							$('#lega_noti_function_id').html('<option value="0">--Select--</option>');
						},						
							error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		} else {
			$('#lega_noti_unit_id').html('<option value="0">--Select--</option>');
			$('#lega_noti_function_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocation() {
		var user_orga_id = $("#lega_noti_entity_id").val();
		var user_loca_id = $("#lega_noti_unit_id").val();
		if (user_loca_id > 0) {
			/*items = {};
			items["tmap_orga_id"] = user_orga_id;
			items["tmap_loca_id"] = user_loca_id;

			var jsonString = JSON.stringify(items);
*/
			
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
							$("#lega_noti_function_id").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#lega_noti_function_id').html('<option value="0">--Select--</option>');
		}
}
$("#lega_noti_function_id").change(function(){
		
		var user_orga_id = $("#lega_noti_entity_id").val();
		var user_loca_id = $("#lega_noti_unit_id").val();
		var user_dept_id = $("#lega_noti_function_id").val();
		
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
							$("#lega_noti_assigned_to_id").html(data);
							$("#lega_noti_secondary_responsible_person").html(data);
							$("#lega_noti_third_responsible_person").html(data);
							$("#lega_noti_other_responsible_person").html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
							$('#errors').html("There is error:" + thrownError);
						}
					});
		}
		else {
			$('#lega_noti_assigned_to_id').html('<option value="0">--Select--</option>');
			$('#lega_noti_secondary_responsible_person').html('<option value="0">--Select--</option>');
			$('#lega_noti_third_responsible_person').html('<option value="0">--Select--</option>');
			$('#lega_noti_other_responsible_person').html('<option value="0">--Select--</option>');
		}
	});

function getUserByLegalNoticeId(){
	
	var user_orga_id = $("#lega_noti_entity_id").val();
	var user_loca_id = $("#lega_noti_unit_id").val();
	var user_dept_id = $("#lega_noti_function_id").val();
	if (user_orga_id > 0 || user_loca_id > 0 || user_dept_id > 0) {
		
		var data = {};
		data['orga_id'] = user_orga_id;
		data['loca_id'] = user_loca_id;
		data['dept_id'] = user_dept_id;
		var jsonString = JSON.stringify(data);
		
				$.ajax({
					type : "post",
					url : "./getUserByLegalNoticeId",
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
						$("#lega_noti_assigned_id").html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
	}
	else {
		$('#lega_noti_assigned_id').html('<option value="0">--Select--</option>');
	}
}


var legalFileCount = 1;
function addLegalFileInput(){
	$("#fileContiner_Legal").append('<div class="col-md-12" id="legalFile'+legalFileCount+'" >'
			+'<div class="form-group">'
	        +'<label class="col-sm-4 control-label">Legal Notice:</label>'
	        +'<div class="col-sm-4">'
	        +'<input type="file" name="legal_doc" class="file-loading" />'
	        +'</div>'
	        +'<div class="col-sm-3" style="text-align: right; cursor: pointer;">'
	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteLegalRow('+legalFileCount+');" style="color: red;" title="Delete"></i>'
	        +'</div>'
	        +'</div>'
	        +'</div>'
			);
	legalFileCount++;
}
function deleteLegalRow(filecount) {
	$("#legalFile" + filecount).remove();
}
	
	function validateForm(){
		
		var lega_noti_entity_id = $("#lega_noti_entity_id").val();
        var lega_noti_unit_id = $('#lega_noti_unit_id').val();
        var lega_noti_function_id = $('#lega_noti_function_id').val();
        var lega_noti_category_id = $('#lega_noti_category_id').val();
        var lega_noti_by_against = $('#lega_noti_by_against').val();
        var lega_noti_assigned_to_id = $('#lega_noti_assigned_to_id').val();
        
        
        //var lega_noti_intern_cont_person = $('#lega_noti_intern_cont_person').val();
        //var lega_noti_dated = $('#lega_noti_dated').val();
       // var lega_noti_recived_on = $('#lega_noti_recived_on').val();
      //  var lega_noti_reply_deadline = $('#lega_noti_reply_deadline').val();
       // var lega_noti_reminder_date = $('#lega_noti_reminder_date').val();
        var lega_noti_external_counsel_id = $('#lega_noti_external_counsel_id').val();
        
        var lega_noti_amount_involved = $('#lega_noti_amount_involved').val();
        var lega_noti_involved_amt_currency = $('#lega_noti_involved_amt_currency').val();
        var lega_noti_conversion_rate = $('#lega_noti_conversion_rate').val();
        var lega_noti_converted_amt_currency = $('#lega_noti_converted_amt_currency').val();

        //var lega_noti_external_counsel_id = $('#lega_noti_external_counsel_id').val();
		
		if (lega_noti_entity_id == 0 ) {
	       	 
     		$( "#lega_noti_entity_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_entity_id" ).attr( "data-content", "Please select Entity Name." );
     		$('#lega_noti_entity_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_entity_id').popover('destroy');
        }
    	
    	if (lega_noti_unit_id == 0) {
          	 
     		$( "#lega_noti_unit_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_unit_id" ).attr( "data-content", "Please select Unit Name." );
     		$('#lega_noti_unit_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_unit_id').popover('destroy');
        }
    	
    	if (lega_noti_function_id == 0) {
       	 
     		$( "#lega_noti_function_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_function_id" ).attr( "data-content", "Please select Function Name." );
     		$('#lega_noti_function_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_function_id').popover('destroy');
        }	
		
    	if (lega_noti_by_against == null || lega_noti_by_against == "NA") {
          	 
     		$( "#lega_noti_by_against" ).attr( "data-placement", "top" );
     		$( "#lega_noti_by_against" ).attr( "data-content", "Please select By or Against Company Name." );
     		$('#lega_noti_by_against').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_by_against').popover('destroy');
        }	
    	if (lega_noti_category_id == 0) {
          	 
     		$( "#lega_noti_category_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_category_id" ).attr( "data-content", "Please select Legal Catagory Name." );
     		$('#lega_noti_category_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_category_id').popover('destroy');
        }	
    	if (lega_noti_assigned_to_id == 0) {
          	 
     		$( "#lega_noti_assigned_to_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_assigned_to_id" ).attr( "data-content", "Please select notice assigned to Name." );
     		$('#lega_noti_assigned_to_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_assigned_to_id').popover('destroy');
        }
    /*	
    	if (lega_noti_intern_cont_person == 0) {
         	 
     		$( "#lega_noti_intern_cont_person" ).attr( "data-placement", "top" );
     		$( "#lega_noti_intern_cont_person" ).attr( "data-content", "Please enter person name." );
     		$('#lega_noti_intern_cont_person').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_intern_cont_person').popover('destroy');
         	
        }*/
    	
    	if(lega_noti_by_against =="Against"){
    	
/*    	if (lega_noti_dated == 0 || lega_noti_dated == "") {
         	 
     		$( "#lega_noti_dated" ).attr( "data-placement", "top" );
     		$( "#lega_noti_dated" ).attr( "data-content", "Please select notice date." );
     		$('#lega_noti_dated').popover('show');
	
            return false;
        }else
         {
         	$('#lega_noti_dated').popover('destroy');
        }
    	}
    	 else
         {
         	$('#lega_noti_dated').popover('destroy');
        }*/
    	/*if(lega_noti_by_against =="Against"){
    	if (lega_noti_recived_on == 0 || lega_noti_recived_on == "") {
         	 
     		$( "#lega_noti_recived_on" ).attr( "data-placement", "top" );
     		$( "#lega_noti_recived_on" ).attr( "data-content", "Please select notice sent/received date." );
     		$('#lega_noti_recived_on').popover('show');
	
            return false;
        }else
         {
         	$('#lega_noti_recived_on').popover('destroy');
        }
    	}
    	 else
         {
         	$('#lega_noti_recived_on').popover('destroy');
        }*/
   /* 	if(lega_noti_by_against =="Against"){
    	if (lega_noti_reply_deadline == 0 || lega_noti_reply_deadline == "") {
         	 
     		$( "#lega_noti_reply_deadline" ).attr( "data-placement", "top" );
     		$( "#lega_noti_reply_deadline" ).attr( "data-content", "Please select notice deadline date." );
     		$('#lega_noti_reply_deadline').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_reply_deadline').popover('destroy');
        }
    	}
    	 else
         {
         	$('#lega_noti_reply_deadline').popover('destroy');
        }*/
    	
   /* 	if(lega_noti_by_against =="Against"){
    	if (lega_noti_reminder_date == 0 || lega_noti_reminder_date == "") {
        	 
     		$( "#lega_noti_reminder_date" ).attr( "data-placement", "top" );
     		$( "#lega_noti_reminder_date" ).attr( "data-content", "Please select notice reminder date." );
     		$('#lega_noti_reminder_date').popover('show');
	
            return false;
        }
    	else
        {
        	$('#lega_noti_reminder_date').popover('destroy');
       }
    	}
    	 else
         {
         	$('#lega_noti_reminder_date').popover('destroy');
        }
    	*/
    	/*if (lega_noti_external_counsel_id == 0) {
          	 
     		$( "#lega_noti_external_counsel_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_external_counsel_id" ).attr( "data-content", "Please select External Counsel Name." );
     		$('#lega_noti_external_counsel_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_external_counsel_id').popover('destroy');
        }*/	
    	/*if (lega_noti_amount_involved == 0.0 ) {
       	 
      		$( "#lega_noti_amount_involved" ).attr( "data-placement", "top" );
      		$( "#lega_noti_amount_involved" ).attr( "data-content", "Please enter amount." );
      		$('#lega_noti_amount_involved').popover('show');
    	
             return false;
         }
     	 else
          {
          	$('#lega_noti_amount_involved').popover('destroy');
          }*/
        
        if (isNaN(lega_noti_amount_involved)) {
       	 
      		$( "#lega_noti_amount_involved" ).attr( "data-placement", "top" );
      		$( "#lega_noti_amount_involved" ).attr( "data-content", "Please enter numeric amount." );
      		$('#lega_noti_amount_involved').popover('show');
    	
             return false;
         }
     	 else
          {
          	$('#lega_noti_amount_involved').popover('destroy');
          }
       /* if (lega_noti_involved_amt_currency == "NA" ) {
       	 
      		$( "#lega_noti_involved_amt_currency" ).attr( "data-placement", "top" );
      		$( "#lega_noti_involved_amt_currency" ).attr( "data-content", "Please select currency." );
      		$('#lega_noti_involved_amt_currency').popover('show');
    	
             return false;
         }
     	 else
          {
          	$('#lega_noti_involved_amt_currency').popover('destroy');
          }*/
   /*     if (isNaN(lega_noti_conversion_rate)) {
          	 
      		$( "#lega_noti_conversion_rate" ).attr( "data-placement", "top" );
      		$( "#lega_noti_conversion_rate" ).attr( "data-content", "Please enter numeric rate." );
      		$('#lega_noti_conversion_rate').popover('show');
    	
             return false;
         }
     	 else
          {
          	$('#lega_noti_conversion_rate').popover('destroy');
          }
        
        if (lega_noti_conversion_rate == 0) {
         	 
      		$( "#lega_noti_conversion_rate" ).attr( "data-placement", "top" );
      		$( "#lega_noti_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
      		$('#lega_noti_conversion_rate').popover('show');
    	
             return false;
         }
     	 else
          {
          	$('#lega_noti_conversion_rate').popover('destroy');
          }
        
        if(lega_noti_conversion_rate > 0 ){
        if (lega_noti_converted_amt_currency == "NA") {
          	 
      		$( "#lega_noti_converted_amt_currency" ).attr( "data-placement", "top" );
      		$( "#lega_noti_converted_amt_currency" ).attr( "data-content", "Please select converted currency." );
      		$('#lega_noti_converted_amt_currency').popover('show');
    	
             return false;
         }
     	 else
          {
          	$('#lega_noti_converted_amt_currency').popover('destroy');
          }
        }
        else
        {
        	$('#lega_noti_converted_amt_currency').popover('destroy');
        }
	}*/
	
function validateDraft(){
		
		var lega_noti_entity_id = $("#lega_noti_entity_id").val();
        var lega_noti_unit_id = $('#lega_noti_unit_id').val();
        var lega_noti_function_id = $('#lega_noti_function_id').val();
        var lega_noti_category_id = $('#lega_noti_category_id').val();
        var lega_noti_by_against = $('#lega_noti_by_against').val();
        var lega_noti_assigned_to_id = $('#lega_noti_assigned_to_id').val();
        var lega_noti_secondary_responsible_person = $('#lega_noti_secondary_responsible_person').val();
        var lega_noti_third_responsible_person = $('#lega_noti_third_responsible_person').val();
        var lega_noti_other_responsible_person = $('#lega_noti_other_responsible_person').val();
        
        //var lega_noti_intern_cont_person = $('#lega_noti_intern_cont_person').val();
        var lega_noti_dated = $('#lega_noti_dated').val();
        var lega_noti_recived_on = $('#lega_noti_recived_on').val();
       // var lega_noti_reply_deadline = $('#lega_noti_reply_deadline').val();
       // var lega_noti_reminder_date = $('#lega_noti_reminder_date').val();
        var lega_noti_external_counsel_id = $('#lega_noti_external_counsel_id').val();
        
        var lega_noti_amount_involved = $('#lega_noti_amount_involved').val();
        var lega_noti_interest = $('#lega_noti_interest').val();
        var lega_noti_total_amount = $('#lega_noti_total_amount').val();
        
        var lega_noti_involved_amt_currency = $('#lega_noti_involved_amt_currency').val();
        var lega_noti_conversion_rate = $('#lega_noti_conversion_rate').val();
        var lega_noti_converted_amt_currency = $('#lega_noti_converted_amt_currency').val();

        //var lega_noti_external_counsel_id = $('#lega_noti_external_counsel_id').val();
		
		if (lega_noti_entity_id == 0 ) {
	       	 
     		$( "#lega_noti_entity_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_entity_id" ).attr( "data-content", "Please select Entity Name." );
     		$('#lega_noti_entity_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_entity_id').popover('destroy');
        }
    	
    	if (lega_noti_unit_id == 0) {
          	 
     		$( "#lega_noti_unit_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_unit_id" ).attr( "data-content", "Please select Unit Name." );
     		$('#lega_noti_unit_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_unit_id').popover('destroy');
        }
    	
    	if (lega_noti_function_id == 0) {
       	 
     		$( "#lega_noti_function_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_function_id" ).attr( "data-content", "Please select Function Name." );
     		$('#lega_noti_function_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_function_id').popover('destroy');
        }	
    	
    	if (lega_noti_category_id == 0) {
          	 
     		$( "#lega_noti_category_id" ).attr( "data-placement", "top" );
     		$( "#lega_noti_category_id" ).attr( "data-content", "Please select Legal Catagory Name." );
     		$('#lega_noti_category_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_category_id').popover('destroy');
        }	
    	
    	
    	
    	
    	/*if(lega_noti_by_against =="Against"){
    	
    	if (lega_noti_dated == 0 || lega_noti_dated == "") {
         	 
     		$( "#lega_noti_dated" ).attr( "data-placement", "top" );
     		$( "#lega_noti_dated" ).attr( "data-content", "Please select notice date." );
     		$('#lega_noti_dated').popover('show');
	
            return false;
        }else
         {
         	$('#lega_noti_dated').popover('destroy');
        }
    	
    	if (lega_noti_recived_on == 0 || lega_noti_recived_on == "") {
         	 
     		$( "#lega_noti_recived_on" ).attr( "data-placement", "top" );
     		$( "#lega_noti_recived_on" ).attr( "data-content", "Please select notice sent/received date." );
     		$('#lega_noti_recived_on').popover('show');
	
            return false;
        }else
         {
         	$('#lega_noti_recived_on').popover('destroy');
        }
    	
    	if (lega_noti_reply_deadline == 0 || lega_noti_reply_deadline == "") {
         	 
     		$( "#lega_noti_reply_deadline" ).attr( "data-placement", "top" );
     		$( "#lega_noti_reply_deadline" ).attr( "data-content", "Please select notice deadline date." );
     		$('#lega_noti_reply_deadline').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_noti_reply_deadline').popover('destroy');
        }
    	
    	if (lega_noti_reminder_date == 0 || lega_noti_reminder_date == "") {
        	 
     		$( "#lega_noti_reminder_date" ).attr( "data-placement", "top" );
     		$( "#lega_noti_reminder_date" ).attr( "data-content", "Please select notice reminder date." );
     		$('#lega_noti_reminder_date').popover('show');
	
            return false;
        }
    	else
        {
        	$('#lega_noti_reminder_date').popover('destroy');
       }
    	
    	}*/
}
	}
	}
	