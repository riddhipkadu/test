$(document).ready(function(){
	var party_added = "false"; //default false if party added then value become a true
	
	$("#cont_orga_id").on('change', getAllLocationForOrganizationCont);
	$("#cont_loca_id").on('change', getAllDepartmentsByLocationCont);
	
	$("#cont_type_of_contract").multiselect({
		buttonWidth: '310px',
        enableFiltering: true,
        filterBehavior: 'text',
        enableCaseInsensitiveFiltering: true,
        maxHeight: 200,
	});
	
	$("#from_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
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
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#from_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
	
		$("#from_date_div1").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#to_date_div1').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
		});

			$("#to_date_div1").datepicker({
				 
			 	autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
				todayHighlight:"true",
				showOnFocus:"true",
				defaultViewDate:"today"

			}).datepicker().on('changeDate', function(e){
				
				$('#from_date_div1').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
			});
		

});

function getAllLocationForOrganizationCont() { 
	var user_orga_id = $("#cont_orga_id").val();
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
				$("#cont_loca_id").html(data);	
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	} else {
		$('#cont_loca_id').html('<option value="0">--Select--</option>');
		$('#cont_dept_id').html('<option value="0">--Select--</option>');
	}
}

function getAllDepartmentsByLocationCont() {
	var user_orga_id = $("#cont_orga_id").val();
	var user_loca_id = $("#cont_loca_id").val();
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
				$("#cont_dept_id").html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	}
	else {
		$('#cont_dept_id').html('<option value="0">--Select--</option>');
	}

}
$("#cont_dept_id").change(function(){
	
	var user_orga_id = $("#cont_orga_id").val();
	var user_loca_id = $("#cont_loca_id").val();
	var user_dept_id = $("#cont_dept_id").val();
	
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
						$("#cont_responsible_user_id").html(data);
						//$("#cont_requested_by_user_id").html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
	}
	else {
		$('#cont_responsible_user_id').html('<option value="0">--Select--</option>');
		//$('#cont_requested_by_user_id').html('<option value="0">--Select--</option>');
	}
});

var termFileCount = 1;
function addTermFileInput(){
	$("#fileContiner_TermSheet").append('<div class="col-md-6" id="termFile'+termFileCount+'">'
			+'<div class="form-group">'
	        +'<label class="col-sm-4 control-label">Term Sheet:</label>'
	        +'<div class="col-sm-2">'
	        +'<input type="file" name="term_sheet_document" class="file-loading" />'
	        +'</div>'
	        +'<div class="col-sm-4" style="text-align: right; cursor: pointer;">'
	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteTermRow('+termFileCount+');" style="color: red;" title="Delete"></i>'
	        +'</div>'
	        +'</div>'
	        +'</div>'
			);
	termFileCount++;
}
var contractFileCount = 1;
function addContractFileInput(){
	$("#fileContiner_Contract").append('<div class="col-md-6" id="contractFile'+contractFileCount+'" >'
			+'<div class="form-group">'
	        +'<label class="col-sm-4 control-label">Contract:</label>'
	        +'<div class="col-sm-2">'
	        +'<input type="file" name="contract_document" class="file-loading" />'
	        +'</div>'
	        +'<div class="col-sm-4" style="text-align: right; cursor: pointer;">'
	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteContractRow('+contractFileCount+');" style="color: red;" title="Delete"></i>'
	        +'</div>'
	        +'</div>'
	        +'</div>'
			);
	contractFileCount++;
}
function deleteTermRow(filecount) {
	$("#termFile" + filecount).remove();
}
function deleteContractRow(filecount) {
	$("#contractFile" + filecount).remove();
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

function blockWhiteSpaces( e ) {    
	if (e.which === 32 &&  e.target.selectionStart === 0) {
	      return false;
	    }  
}





