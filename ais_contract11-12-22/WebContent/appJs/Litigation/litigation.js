
$(document).ready(function(){
	
	$("#liti_orga_id").on('change', getAllLocationForOrganizationLiti);
	$("#liti_loca_id").on('change', getAllDepartmentsByLocationLiti);
	$("#liti_against_by_id").on('change', getCompanyActingAs);
	$("#liti_company_acting_as").on('change', getOppositePartyActingAs);
	$(".addLitigationDocuments").on('click', addFileInputLitigation);
	$(".addLitigationDocumentReference").on('click', addFileInputReference);
	
	getOppositePartyActingAs();
	
	
	$("#liti_amount_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
	
	 $("#liti_amount_div").datepicker({
		 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"

		}).datepicker({ autoclose: true}).datepicker('setEndDate', new Date()).focus();
	    
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
		
		$("#from_hear_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker().on('changeDate', function(e){

		$('#to_hear_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
		});

			$("#to_hear_date_div").datepicker({
				 
			 	autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
				endDate:"now",
				todayHighlight:"true",
				showOnFocus:"true",
				defaultViewDate:"today"

			}).datepicker().on('changeDate', function(e){
				
				$('#from_hear_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
			});

	$("#searchLitigation").click(function(){
		
		var orga_id = $("#liti_orga_id").val();
		var loca_id = $("#liti_loca_id").val();
		var dept_id = $("#liti_dept_id").val();
		var liti_by_against = $("#liti_by_against").val();
		var liti_category = $("#liti_category").val();
		var liti_status = $("#liti_status").val();
		var liti_case_from = $("input[name=liti_case_date_from]").val();
		var liti_case_to = $("input[name=liti_case_date_to]").val();
		var liti_hearing_date_from = $("input[name=liti_hearing_date_from]").val();
		var liti_hearing_date_to = $("input[name=liti_hearing_date_to]").val();
		var liti_handle_by = $("#liti_handle_by").val();
		var liti_criticality = $("#liti_criticality").val(); 
		var liti_internal_liti_code = $("#liti_internal_liti_code").val();
		//var user_role               = $("#role").val();
		if(orga_id > 0) {
			
			$("#searchLitigation").hide();
			$("#loader").show();
		var data = {};
		data["orga_id"]         = orga_id;
		data["loca_id"]         = loca_id;
		data["dept_id"]         = dept_id;
		data["liti_by_against"] = liti_by_against;
		data["liti_category"]   = liti_category;
		data["liti_status"]     = liti_status;
		data["liti_case_from"]  = liti_case_from;
		data["liti_case_to"]    = liti_case_to;
		data["liti_hearing_date_from"]  = liti_hearing_date_from;
		data["liti_hearing_date_to"]    = liti_hearing_date_to;
		data["liti_handle_by"]    		= liti_handle_by;
		data["liti_criticality"]  		= liti_criticality;
		data["liti_internal_liti_code"] = liti_internal_liti_code;
		
		var jsonString = JSON.stringify(data);
		
		$.ajax({
			type : "POST",
			url  : "./searchLitigation",
			data : jsonString,
			contentType : "application/json",
			dataType : "json",
			success : function(result){
				var data = "";
				document.getElementById('count').innerHTML = result.length;	
				if(result.length != 0){
					
				$.each(result,function(key,value){
					data += "<tr id ='row_"+value['liti_id']+"'>";
					data += "<td>"+value['liti_client_id']+"</td>";
					data += "<td>"+value['liti_by_againts']+" Company</td>";
					data += "<td>"+value['liti_case_ref_no']+"</td>";
					
					data += "<td>"+value['liti_next_hearing_date']+"</td>";
					data += "<td>"+value['liti_case_title']+"</td>";
					data += "<td>"+value['liti_status']+"</td>";
					data +=" <td class='edit'>";
					if(value['liti_user_role']==1 || value['liti_user_id']==value['liti_added_by']){
					 data += "<a href='editLitigation?liti_id="+value['liti_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin-bottom :4px'><i class='fa fa-pencil-square-o'></i>&nbsp;Edit</button></a><br/>";
					 data += "<button type='button' value="+value['liti_id']+" name='delete_litigation' class='btn btn-danger' style='margin-bottom: 4px;'><i class='fa fa-trash'></i>&nbsp;Delete</button>";
					}else{
						data +=" No Access.";	
					}
					data += "<br/><a href='litigationDetails?liti_id="+value['liti_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin :2px'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
					data +=" </td>";
					//data += "<td class='delete'><button type='button' value="+value['liti_id']+" name='delete_litigation' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td>";
				    data += "</tr>";
					
				});
			   }else{
				   data += "<tr><td colspan=7 style='text-align:center'>No result found.</td></tr>";
			   }
				$("#searchLitigation").show();
				$("#loader").hide();
				$("#searchResult").html(data);
			}
		});	
	  }
		else{
			$("#liti_orga_id").attr("data-placement", "top");
			$("#liti_orga_id").attr("data-content", "Choose Entity Field..!!");
			$('#liti_orga_id').popover('show');
			return false;
		}
		
	});
	/*--------------------------------Code to post form Data using ajax-------------------------------------------------*/
	$("form#addLitigationDocumentForm").submit(function (event) {

		//disable the default form submission
		event.preventDefault();
		//grab all form data  
		var formData = new FormData($(this)[0]);
		// formData.append( 'file', $( '#file' )[0].files[0] );

		$.ajax({
			url: "./addLitigationDocument",
			type: 'POST',
			data: formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success: function (data) {


				getAllLitigationDocuments(data);

			},
			error: function(){
				alert("error in ajax form submission");
			}
		});

		return false;
	});
	/*----------------------------Code to post form Data using ajax ends here-------------------------------------------*/

	/*----------------------------------Code for delete document--------------------------------------------------------*/

	$(document).on("click","button[name='delete_litigation_document']",function(){
		var ldoc_id = $(this).val();
		//console.log("liti_id "+liti_id);
		items = {};
		items["ldoc_id"] = ldoc_id;
		var jsonString = JSON.stringify(items);

		if(ldoc_id>0){
			bootbox.confirm("Are you sure you want to delete?", function(result) { 
				if(result==true){
					$.ajax({
						type : "post",
						url : "./deleteLitigationDocument",
						contentType : "text/html",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(deleteCount) {
							if(deleteCount=="Success"){ 
								$("#dialogBox .modal-header").css("background-color", "#097a09");
								$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
								$('#dialogBox .modal-body').html("<p><strong>Litigation Document deleted successfully!</strong></p>");
								$("#dialogBox").modal('show');
								//window.location.reload(true);
								//getAllLitigationDocuments();
								$('table#example tr#litiDocRow_'+ldoc_id).remove();
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
	/*------------------------------Code for delete document ends here--------------------------------------------------*/

});


$("#liti_criticality").on('change', function(){
	if($(this).val() == 'High'){
		$("#multi_email_id").show();
	}else{
		$("#multi_email_id").hide();
	}
});

function getAllLocationForOrganizationLiti() { 
	var user_orga_id = $("#liti_orga_id").val();
	if (user_orga_id > 0) {

		$.ajax({
			type : "post",
			url : "./getLocationByOrgaUserId",
			//contentType : "application/json",
			dataType : "json",
			data : {orga_id :user_orga_id },
			cache : false,
			success : function(locationjson) {
				data ="";
				data +='<option value = 0>--Select--</option>';

				$.each(locationjson,function(key,value){
					data += "<option value="+value['loca_id']+">"+value['loca_name']+"</option>";
				});
				$("#liti_loca_id").html(data);	
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	} else {
		$('#liti_loca_id').html('<option value="0">--Select--</option>');
		$('#liti_dept_id').html('<option value="0">--Select--</option>');
	}
}

function getAllDepartmentsByLocationLiti() {
	var user_orga_id = $("#liti_orga_id").val();
	var user_loca_id = $("#liti_loca_id").val();
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
				$("#liti_dept_id").html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	}
	else {
		$('#liti_dept_id').html('<option value="0">--Select--</option>');
	}

}

$("#liti_dept_id").change(function(){
	
	var user_orga_id = $("#liti_orga_id").val();
	var user_loca_id = $("#liti_loca_id").val();
	var user_dept_id = $("#liti_dept_id").val();
	
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
						$("#liti_internally_handled_by").html(data);
						$("#liti_assigned_to").html(data);
					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
	}
	else {
		$('#liti_internally_handled_by').html('<option value="0">--Select--</option>');
		$('#liti_assigned_to').html('<option value="0">--Select--</option>');
	}
});


$(document).on("click","button[name='delete_litigation']",function(){
	
	var liti_id = $(this).val();
	items = {};
	items["liti_id"] = liti_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(liti_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLitigation",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Litigation deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#LitigationList tr#row_'+liti_id).remove();
				    				document.getElementById('count').innerHTML = ($('#LitigationList >tbody >tr').length);	
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


function getCompanyActingAs(){


	if($("#liti_against_by_id").val() == "By"){


		$('#liti_company_acting_as').empty();
		$('#liti_company_acting_as').append('<option value = 0> --Select-- </option>'+
				'<option value = Plaintiff> Plaintiff </option>' +
				'<option value = Applicant> Applicant </option>' +
				'<option value = Complainant> Complainant </option>' +
				'<option value = Appellant> Appellant </option>' +
		'<option value = Petitioner> Petitioner </option>');
	}
	else{
		if($("#liti_against_by_id").val() == "Against"){
			$('#liti_company_acting_as').empty();
			$('#liti_company_acting_as').append('<option value = 0> --Select-- </option>'+
					'<option value = Defendant> Defendant </option>' +
					'<option value = Opponent> Opponent </option>' +
					'<option value = Opposite_Party> Opposite Party </option>' +
			'<option value = Repondent> Repondent </option>');
		}
		else{
			if($("#liti_against_by_id").val() == 0){
				$('#liti_company_acting_as').empty();
				$('#liti_company_acting_as').append('<option value = 0> --Select-- </option>');
				$('#liti_opp_party_acting_as').empty();
				$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>');
			}
		}
	}
}

function getOppositePartyActingAs(){
	if($("#liti_against_by_id").val() == "By"){
		if($("#liti_company_acting_as").val() == 0){
			$('#liti_opp_party_acting_as').empty();
			$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>');
		}
		if($("#liti_company_acting_as").val() == "Plaintiff"){
			$('#liti_opp_party_acting_as').empty();
			$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
			'<option value = "Defendant" selected> Defendant </option>');
		}
		if($("#liti_company_acting_as").val() == "Applicant"){
			$('#liti_opp_party_acting_as').empty();
			$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
			'<option value = "Opponent" selected> Opponent </option>');
		}
		if($("#liti_company_acting_as").val() == "Complainant"){
			$('#liti_opp_party_acting_as').empty();
			$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
			'<option value = "Opposite_Party" selected> Opposite Party </option>');
		}
		if($("#liti_company_acting_as").val() == "Appellant" || $("#liti_company_acting_as").val() == "Petitioner"){
			$('#liti_opp_party_acting_as').empty();
			$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
			'<option value = "Repondent" selected> Repondent </option>');
		}
	}
	else{
		if($("#liti_against_by_id").val() == "Against"){
			if($("#liti_company_acting_as").val() == 0){
				$('#liti_opp_party_acting_as').empty();
				$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>');
			}
			if($("#liti_company_acting_as").val() == "Defendant"){
				$('#liti_opp_party_acting_as').empty();
				$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
				'<option value = "Plaintiff" selected> Plaintiff </option>');
			}
			if($("#liti_company_acting_as").val() == "Opponent"){
				$('#liti_opp_party_acting_as').empty();
				$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
				'<option value = "Applicant" selected> Applicant </option>');
			}
			if($("#liti_company_acting_as").val() == "Opposite_Party"){
				$('#liti_opp_party_acting_as').empty();
				$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
				'<option value = "Complainant" selected> Complainant </option>');
			}
			if($("#liti_company_acting_as").val() == "Repondent"){
				$('#liti_opp_party_acting_as').empty();
				$('#liti_opp_party_acting_as').append('<option value = 0> --Select-- </option>'+
						'<option value = "Appelant" > Appelant </option>' +
				'<option value = "Petitioner" > Petitioner </option>');
			}
		}

	}

}

var filecount = 1;
function addFileInputLitigation(i){
	$('#fileAddingForm'+i)
	.append(
			"<div id='div"+filecount+"' class='col-md-12'>"
			+ "<div class='form-group row'>"
			+ "<label class='col-md-3 form-control-label'>Select Document</label>"
			+ "<div class='col-md-4'>"
			+ "<input type='file' required='required' id='litigationDocument' name='litigationDocument' class='file-loading' />"
			+ "</div> <a class='col-md-1' href='#' onclick='deleteRowLitiRelated("+filecount+ ");'><i class='glyphicon glyphicon-remove-circle uploadClose'></i></a></div></div>"
			+ "<div class='col-md-2'></div><div  style='padding:5px;margin:5px; text-align:center;' class='col-md-12 litigation_buttons' id='divButton"+filecount+"'>"
			+ "<input type='submit' class='btn btn-myPrimary' value='Submit' /></div>");
	filecount++;
	$("#addLitigationDocuments").hide();
}
function deleteRowLitiRelated(filecount) {
	$("#div" + filecount).remove();
	$("#divButton" + filecount).remove();
	$("#addLitigationDocuments").show();

}

function addFileInputReference(){
	
	$('#fileAddingReferenceForm')
	.append(
			"<div id='div"+filecount+"' class='col-md-12'>"
			+ "<div class='form-group row'>"
			+ "<label class='col-md-3 form-control-label'>Select Document</label>"
			+ "<div class='col-md-4'>"
			+ "<input  type='file' required='required' id='litigationDocument' name='litigationDocument' class='file-loading' />"
			+ "</div> <a class='col-md-1' href='#' onclick='deleteRowRef("+filecount+");'><i class='glyphicon glyphicon-remove-circle uploadClose' ></i></a></div></div>"
			+ "<div class='col-md-2'></div><div  style='padding:5px;margin:5px; text-align:center;' class='col-md-12 litigation_buttons' id='divButton"+filecount+"'>"
			+ "<input type='submit' class='btn btn-myPrimary' value='Submit' /></div>");
	filecount++;
	$("#addLitigationDocumentReference").hide();
}

function deleteRowRef(filecount) { 
	$("#div" + filecount).remove();
	$("#divButton" + filecount).remove();
	$("#addLitigationDocumentReference").show();
}

function getAllLitigationDocuments(data){
	items = {};
	items['ldoc_liti_id'] = data;

	var jsonString = JSON.stringify(items);


	$.ajax({
		type : "post",
		url : "./getAllDocumentForAjax",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(documentsList) {
			var htmlOutputLitigationRelated1 = '';
			var htmlOutputLitigationRelated2 = '';
			var htmlOutputLitigationRelated3 = '';
			var htmlOutputLitigationRelated4 = '';
			var htmlOutputLitigationRelated5 = '';
			var htmlOutputReferenceRelated = '';
			if(documentsList != ""){
				var documentJson = $.parseJSON(documentsList);
				//console.log("This is length of document json:"+documentJson.length);
				//console.log(documentJson[2]);
				var k=1;
				var j=1; var l=1; var m =1; var n=1; var p=1;
				for(var i=0 ; i < documentJson.length ; i++){
					var innerObj = documentJson[i];

					if(innerObj["ldoc_document_type"] == "LitigationRelated"){
						htmlOutputLitigationRelated1 += "<tr id='litiDocRow_"+innerObj["ldoc_id"]+"'><td>"+ j +"</td>";
						htmlOutputLitigationRelated1 += "<td><a href='./downloadLitigationDocument?ldoc_id="+innerObj["ldoc_id"]+"'>"+ innerObj["ldoc_original_file_name"] +"</a></td>";
						htmlOutputLitigationRelated1 += "<td class='delete'><button type='button' value='"+ innerObj["ldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
						j++;
					}
					
					else if(innerObj["ldoc_document_type"] == "LitigationReference"){
							htmlOutputReferenceRelated += "<tr id='litiDocRow_"+innerObj["ldoc_id"]+"'><td>"+ k +"</td>";
							htmlOutputReferenceRelated += "<td><a href='./downloadLitigationDocument?ldoc_id="+innerObj["ldoc_id"]+"'>"+ innerObj["ldoc_original_file_name"] +"</a></td>";
							htmlOutputReferenceRelated += "<td class='delete'><button type='button' value='"+ innerObj["ldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
							k++;
						}
					else if(innerObj["ldoc_document_type"] == "LitigationActs"){
						htmlOutputLitigationRelated2 += "<tr id='litiDocRow_"+innerObj["ldoc_id"]+"'><td>"+ l +"</td>";
						htmlOutputLitigationRelated2 += "<td><a href='./downloadLitigationDocument?ldoc_id="+innerObj["ldoc_id"]+"'>"+ innerObj["ldoc_original_file_name"] +"</a></td>";
						htmlOutputLitigationRelated2 += "<td class='delete'><button type='button' value='"+ innerObj["ldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
						l++;
					}
					
					else if(innerObj["ldoc_document_type"] == "LitigationStatuatory"){
						htmlOutputLitigationRelated3 += "<tr id='litiDocRow_"+innerObj["ldoc_id"]+"'><td>"+ m +"</td>";
						htmlOutputLitigationRelated3 += "<td><a href='./downloadLitigationDocument?ldoc_id="+innerObj["ldoc_id"]+"'>"+ innerObj["ldoc_original_file_name"] +"</a></td>";
						htmlOutputLitigationRelated3 += "<td class='delete'><button type='button' value='"+ innerObj["ldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
						m++;
					}
					
					else if(innerObj["ldoc_document_type"] == "LitigationMiscellaneous"){
						htmlOutputLitigationRelated4 += "<tr id='litiDocRow_"+innerObj["ldoc_id"]+"'><td>"+ n +"</td>";
						htmlOutputLitigationRelated4 += "<td><a href='./downloadLitigationDocument?ldoc_id="+innerObj["ldoc_id"]+"'>"+ innerObj["ldoc_original_file_name"] +"</a></td>";
						htmlOutputLitigationRelated4 += "<td class='delete'><button type='button' value='"+ innerObj["ldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
						n++;
					}
					
					else if(innerObj["ldoc_document_type"] == "LitigationCaseLaw"){
						htmlOutputLitigationRelated5 += "<tr id='litiDocRow_"+innerObj["ldoc_id"]+"'><td>"+ p +"</td>";
						htmlOutputLitigationRelated5 += "<td><a href='./downloadLitigationDocument?ldoc_id="+innerObj["ldoc_id"]+"'>"+ innerObj["ldoc_original_file_name"] +"</a></td>";
						htmlOutputLitigationRelated5 += "<td class='delete'><button type='button' value='"+ innerObj["ldoc_id"] +"' name='delete_litigation_document' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
						p++;
					}
				}
			}else {
				htmlOutput += "<tr><td colspan=3 style='text-align:center'>No Result Found</td></tr>";
			}

			$("#liti_docs_tbody1").html(htmlOutputLitigationRelated1);
			$("#liti_docs_tbody2").html(htmlOutputLitigationRelated2);
			$("#liti_docs_tbody3").html(htmlOutputLitigationRelated3);
			$("#liti_docs_tbody4").html(htmlOutputLitigationRelated4);
			$("#liti_docs_tbody5").html(htmlOutputLitigationRelated5);
			$("#liti_docs_tbody_reference").html(htmlOutputReferenceRelated);
			$('#fileAddingForm1').html("");
			$('#fileAddingForm2').html("");
			$('#fileAddingForm3').html("");
			$('#fileAddingForm4').html("");
			$('#fileAddingForm5').html("");
			$(".addLitigationDocument").show();
			$('#fileAddingReferenceForm').html("");
			$("#addLitigationDocumentReference").show();
			filecount =1;

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
}
/*******************************Ajax call for counsel name on change of Law firm ****************************/

$("#liti_counsel_law_firm").change(function(){ 
	var law_firm_id = $("#liti_counsel_law_firm").val();
	if(law_firm_id>0){
		$.ajax({
			type : "POST",
			url  : "./getCounselByLawFirmId",
			data : {law_firm_id:law_firm_id},
			success : function(res){
				var data = "";
				var result = jQuery.parseJSON(res);
				data += "<option value='0'>--Select--</option>";
				if(result.length !=0){
				$.each(result,function(key,value){ 
					data +="<option value="+value['counsel_id']+">"+value['counsel_name']+"</option>";
				   });
				
				}
			    $("#liti_external_counsel_id").html(data);
			}
	    });//End ajax
	}//End If
	
});

//Ajax call for advocate name on change of Law firm 
$("#liti_advocate_law_firm").change(function(){ 
	var law_firm_id = $("#liti_advocate_law_firm").val();
	if(law_firm_id>0){
		$.ajax({
			type : "POST",
			url  : "./getAdvocateByLawFirmId",
			data : {law_firm_id:law_firm_id},
			success : function(res){
				var data = "";
				var result = jQuery.parseJSON(res);
				data += "<option value='0'>--Select--</option>";
				if(result.length !=0){
				$.each(result,function(key,value){ 
					data +="<option value="+value['advocate_id']+">"+value['advocate_name']+"</option>";
				   });
				
				}
			    $("#liti_advocate_id").html(data);
			}
	    });//End ajax
	}//End If
	
});

/****************************code for online converted currency rate starts here ****************************************************/

$("#liti_involved_amt_currency").on("change", getConversionRate);
$("#liti_converted_amt_currency").on("change", getConversionRate);
$("#liti_conversion_date").on("change", getConversionRate);

function getConversionRate(){
	
	var amount_involved = $('#liti_amount_involved').val();
	var converted_amt_currency = $("#liti_converted_amt_currency").val();
	var involved_amt_currency = $('#liti_involved_amt_currency').val();
	var amount_date = $('#liti_conversion_date').val();
	var conversion_rate = $('#liti_conversion_rate').val();
	
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
								  $("#liti_conversion_rate").val(rate);
								  //var conversion_rate = 
								  $("#liti_converted_amt").val(rate*amount_involved);
								  if (rate == 0) {
							         	 
							      		$( "#liti_conversion_rate" ).attr( "data-placement", "bottom" );
							      		$( "#liti_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#liti_conversion_rate').popover('show');
							    	
							             return false;
							        }else{
							          	$('#liti_conversion_rate').popover('destroy');
							        }
					 }
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
	}
		
}
/****************************code for online converted currency rate ends here ****************************************************/

$("#liti_conversion_rate").change(function(){
	var liti_amount_involved = $('#liti_amount_involved').val();
	var liti_conversion_rate = $("#liti_conversion_rate").val();
	if(liti_conversion_rate !=''){
	var liti_conversion_rate = $('#liti_conversion_rate').val();
	$("#liti_converted_amt").val(liti_conversion_rate*liti_amount_involved);
	}else{
		$("#liti_conversion_rate").val(0.0);
	}
});

$("#liti_amount_involved").keyup(function(){
	var liti_conversion_rate = $("#liti_conversion_rate").val();
	var liti_amount_involved = $('#liti_amount_involved').val();
	$("#liti_converted_amt").val(liti_conversion_rate*liti_amount_involved);
});

function validateForm() { 
    
	var liti_orga_id = $('#liti_orga_id').val();
    var liti_loca_id = $('#liti_loca_id').val();
    var liti_dept_id = $('#liti_dept_id').val();
    var liti_type_id = $('#liti_type_id').val();
    var liti_against_by_id 	= $('#liti_against_by_id').val();
    var liti_party_by 		= $('#liti_party_by').val();
    var liti_party_against 	= $('#liti_party_against').val();
    var liti_company_acting_as = $('#liti_company_acting_as').val();
    var liti_counsel_law_firm = $('#liti_counsel_law_firm').val();
    var liti_external_counsel_id = $('#liti_external_counsel_id').val();
    var liti_advocate_law_firm = $('#liti_advocate_law_firm').val();
    var liti_advocate_id = $('#liti_advocate_id').val();
    var liti_internally_handled_by = $('#liti_internally_handled_by').val();
    var liti_case_filing_date = $('#liti_case_filing_date').val();
    var liti_opp_party_acting_as = $('#liti_opp_party_acting_as').val();
    //var liti_opposite_party = $('#liti_opposite_party').val();
    var liti_opposite_party_advocate = $('#liti_opposite_party_advocate').val();
   // var liti_forum = $('#liti_forum').val();
    var liti_jurisdiction_name = $('#liti_jurisdiction_name').val();
    var liti_court = $('#liti_court').val();
    var liti_relevant_law = $('#liti_relevant_law').val();
    var liti_amount_involved = $('#liti_amount_involved').val();
    //var liti_brief_description = $('#liti_brief_description').val();
    var liti_amount_involved = $('#liti_amount_involved').val();
    var liti_involved_amt_currency = $('#liti_involved_amt_currency').val();
    var liti_conversion_rate = $('#liti_conversion_rate').val();
    var liti_converted_amt_currency = $('#liti_converted_amt_currency').val();
    var liti_criticality = $('#liti_criticality').val();
    var liti_mail_id_cc = jQuery.trim($('#liti_mail_id_cc').val());
    var esc_internal_resource = jQuery.trim($('#esc_internal_resource').val());
    var esc_law_firm = jQuery.trim($('#esc_law_firm').val());
    var esc_appear_counsel = jQuery.trim($('#esc_appear_counsel').val());
    var esc_others = jQuery.trim($('#esc_others').val());
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    
   if(liti_criticality == 'High' ){
          
           if(liti_mail_id_cc !="" || liti_mail_id_cc != null){
        	   var x = liti_mail_id_cc;
               var emails = x.split(",");
               
        	   	for(var i=0; i<emails.length; i++){
        	   		if(reg.test(emails[i]) == false){
        	   			
        	   			$( "#liti_mail_id_cc" ).attr( "data-placement", "top" );
        	   			$( "#liti_mail_id_cc" ).attr( "data-content", "Please enter valid Email id." );
        	   			$('#liti_mail_id_cc').popover('show');
        	   			return false;
        	   			
        	   		}else{
        	   			$('#liti_mail_id_cc').popover('destroy');
        	   		}
        	   	}
           }else{
        	   $('#liti_mail_id_cc').popover('destroy');
		}
           
    }else{
 	   $('#liti_mail_id_cc').popover('destroy');
	}
    if (liti_orga_id == 0 ) {
      	 
  		$( "#liti_orga_id" ).attr( "data-placement", "top" );
  		$( "#liti_orga_id" ).attr( "data-content", "Please select entity." );
  		$('#liti_orga_id').popover('show');
	
         return false;
     } 
    else {
      	$('#liti_orga_id').popover('destroy');
      }

    if (liti_loca_id == 0 ) {
     	 
  		$( "#liti_loca_id" ).attr( "data-placement", "top" );
  		$( "#liti_loca_id" ).attr( "data-content", "Please select unit." );
  		$('#liti_loca_id').popover('show');
	
         return false;
     } else{
      	$('#liti_loca_id').popover('destroy');
      }
    	if (liti_dept_id == 0 ) {
     	 
  		$( "#liti_dept_id" ).attr( "data-placement", "top" );
  		$( "#liti_dept_id" ).attr( "data-content", "Please select function." );
  		$('#liti_dept_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_dept_id').popover('destroy');
      }
    if (liti_type_id == 0 ) {
     	 
  		$( "#liti_type_id" ).attr( "data-placement", "top" );
  		$( "#liti_type_id" ).attr( "data-content", "Please select litigation type." );
  		$('#liti_type_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_type_id').popover('destroy');
      }
    if (liti_against_by_id == 0 ) {
     	 
  		$( "#liti_against_by_id" ).attr( "data-placement", "top" );
  		$( "#liti_against_by_id" ).attr( "data-content", "Please select against or by." );
  		$('#liti_against_by_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_against_by_id').popover('destroy');
      }
    
    if (liti_party_by == null || liti_party_by =='' ) {
    	 
  		$( "#liti_party_by" ).attr( "data-placement", "top" );
  		$( "#liti_party_by" ).attr( "data-content", "Please enter by party." );
  		$('#liti_party_by').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_party_by').popover('destroy');
      }
    
    if (liti_party_against == null || liti_party_against ==''  ) {
    	 
  		$( "#liti_party_against" ).attr( "data-placement", "top" );
  		$( "#liti_party_against" ).attr( "data-content", "Please enter party against." );
  		$('#liti_party_against').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_party_against').popover('destroy');
      }
    
    
    if (liti_company_acting_as == 0 ) {
     	 
  		$( "#liti_company_acting_as" ).attr( "data-placement", "top" );
  		$( "#liti_company_acting_as" ).attr( "data-content", "Please select company acting as." );
  		$('#liti_company_acting_as').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_company_acting_as').popover('destroy');
      }
    if (liti_counsel_law_firm == 0 ) {
    	 
  		$( "#liti_counsel_law_firm" ).attr( "data-placement", "top" );
  		$( "#liti_counsel_law_firm" ).attr( "data-content", "Please select counsel law firm ." );
  		$('#liti_counsel_law_firm').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_counsel_law_firm').popover('destroy');
      }
    
    
    /*if (liti_external_counsel_id == 0 ) {
     	 
  		$( "#liti_external_counsel_id" ).attr( "data-placement", "top" );
  		$( "#liti_external_counsel_id" ).attr( "data-content", "Please select external counsel." );
  		$('#liti_external_counsel_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_external_counsel_id').popover('destroy');
      }*/
    if (liti_advocate_law_firm == 0 ) {
    	 
  		$( "#liti_advocate_law_firm" ).attr( "data-placement", "top" );
  		$( "#liti_advocate_law_firm" ).attr( "data-content", "Please select advocate law firm." );
  		$('#liti_advocate_law_firm').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_advocate_law_firm').popover('destroy');
      }
    if (liti_advocate_id == 0 ) {
    	 
  		$( "#liti_advocate_id" ).attr( "data-placement", "top" );
  		$( "#liti_advocate_id" ).attr( "data-content", "Please select advocate name." );
  		$('#liti_advocate_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_advocate_id').popover('destroy');
      }
    
    
   /* if (liti_internally_handled_by == 0 ) {
     	 
  		$( "#liti_internally_handled_by" ).attr( "data-placement", "top" );
  		$( "#liti_internally_handled_by" ).attr( "data-content", "Please select matter handled by." );
  		$('#liti_internally_handled_by').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_internally_handled_by').popover('destroy');
      }*/
    if (liti_case_filing_date == 0 ) {
     	 
  		$( "#liti_case_filing_date" ).attr( "data-placement", "top" );
  		$( "#liti_case_filing_date" ).attr( "data-content", "Please select limitation date." );
  		$('#liti_case_filing_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_case_filing_date').popover('destroy');
      }
    if (liti_criticality == 0 ) {
    	 
  		$( "#liti_criticality" ).attr( "data-placement", "top" );
  		$( "#liti_criticality" ).attr( "data-content", "Please select criticality level." );
  		$('#liti_criticality').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_criticality').popover('destroy');
      }
    
    if(esc_internal_resource !=''){
 	   var x1 = esc_internal_resource;
        var emails1 = x1.split(",");
        if(emails1.length <= 6){
 	   	for(var i=0; i<emails1.length; i++){
 	   		if(reg.test(emails1[i]) == false){
 	   			
 	   			$( "#esc_internal_resource" ).attr( "data-placement", "top" );
 	   			$( "#esc_internal_resource" ).attr( "data-content", "Please enter valid Email id." );
 	   			$('#esc_internal_resource').popover('show');
 	   			return false;
 	   			
 	   		}else{
 	   			$('#esc_internal_resource').popover('destroy');
 	   		}
 	   	}
        }else{
        	$( "#esc_internal_resource" ).attr( "data-placement", "top" );
       		$( "#esc_internal_resource" ).attr( "data-content", "Please enter six Email ids." );
       		$('#esc_internal_resource').popover('show');
       		return false;
		}
    }
    
    if(esc_law_firm !=''){
 	    //var y = esc_law_firm;
        //var emails2 = y.split(",");
        //if(esc_law_firm.length == 1){
 	   	//for(var i=0; i<emails2.length; i++){
 	   		if(reg.test(esc_law_firm) == false){
 	   			
 	   			$( "#esc_law_firm" ).attr( "data-placement", "top" );
 	   			$( "#esc_law_firm" ).attr( "data-content", "Please enter one valid Email id." );
 	   			$('#esc_law_firm').popover('show');
 	   			return false;
 	   		}else{
 	   			$('#esc_law_firm').popover('destroy');
 	   		}
       /* }else{
        	$( "#esc_law_firm" ).attr( "data-placement", "top" );
       		$( "#esc_law_firm" ).attr( "data-content", "Please enter only one email id." );
       		$('#esc_law_firm').popover('show');
       		return false;
		}*/
    }
    
    if(esc_appear_counsel !=''){
 	   var z = esc_appear_counsel;
        var emails3 = z.split(",");
        if(emails3.length <= 6){
 	   	for(var i=0; i<emails3.length; i++){
 	   		if(reg.test(emails3[i]) == false){
 	   			
 	   			$( "#esc_appear_counsel" ).attr( "data-placement", "top" );
 	   			$( "#esc_appear_counsel" ).attr( "data-content", "Please enter valid Email id." );
 	   			$('#esc_appear_counsel').popover('show');
 	   			return false;
 	   			
 	   		}else{
 	   			$('#esc_appear_counsel').popover('destroy');
 	   		}
 	   	}
        }else{
        	$( "#esc_appear_counsel" ).attr( "data-placement", "top" );
       		$( "#esc_appear_counsel" ).attr( "data-content", "Please enter six Email ids." );
       		$('#esc_appear_counsel').popover('show');
       		return false;
		}
    }
    
    if(esc_others !=''){
 	   var x4 = esc_others;
        var emails4 = x4.split(",");
        if(emails4.length <= 6){
 	   	for(var i=0; i<emails4.length; i++){
 	   		if(reg.test(emails4[i]) == false){
 	   			
 	   			$( "#esc_others" ).attr( "data-placement", "top" );
 	   			$( "#esc_others" ).attr( "data-content", "Please enter valid Email id." );
 	   			$('#esc_others').popover('show');
 	   			return false;
 	   			
 	   		}else{
 	   			$('#esc_others').popover('destroy');
 	   		}
 	   	}
	}else{
		$( "#esc_others" ).attr( "data-placement", "top" );
		$( "#esc_others" ).attr( "data-content", "Please enter six Email ids." );
		$('#esc_others').popover('show');
		return false;
	}
    }
	
    if (liti_opp_party_acting_as == 0 ) {
     	 
  		$( "#liti_opp_party_acting_as" ).attr( "data-placement", "top" );
  		$( "#liti_opp_party_acting_as" ).attr( "data-content", "Please select opposite party acting as." );
  		$('#liti_opp_party_acting_as').popover('show');
	
         return false;
     } else {
      	$('#liti_opp_party_acting_as').popover('destroy');
      }
    /*if (liti_opposite_party == null || liti_opposite_party == "" ) {
     	 
  		$( "#liti_opposite_party" ).attr( "data-placement", "top" );
  		$( "#liti_opposite_party" ).attr( "data-content", "Please enter opposite party." );
  		$('#liti_opposite_party').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_opposite_party').popover('destroy');
      }*/
   /* if (liti_opposite_party_advocate == null || liti_opposite_party_advocate == "" ) {
     	 
  		$( "#liti_opposite_party_advocate" ).attr( "data-placement", "top" );
  		$( "#liti_opposite_party_advocate" ).attr( "data-content", "Please enter opposite party advocate." );
  		$('#liti_opposite_party_advocate').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_opposite_party_advocate').popover('destroy');
      }*/
   
    if (liti_jurisdiction_name == null || liti_jurisdiction_name == "") {
     	 
  		$( "#liti_jurisdiction_name" ).attr( "data-placement", "top" );
  		$( "#liti_jurisdiction_name" ).attr( "data-content", "Please enter Jurisdiction." );
  		$('#liti_jurisdiction_name').popover('show');
	
         return false;
     } else {
      	$('#liti_jurisdiction_name').popover('destroy');
      }
    
    if (liti_court == 0 ) {
     	 
  		$( "#liti_court" ).attr( "data-placement", "top" );
  		$( "#liti_court" ).attr( "data-content", "Please select litigation court.");
  		$('#liti_court').popover('show');
	
         return false;
     } else {
      	$('#liti_court').popover('destroy');
      }
    if (liti_relevant_law == null || liti_relevant_law == "" ) {
     	 
  		$( "#liti_relevant_law" ).attr( "data-placement", "top" );
  		$( "#liti_relevant_law" ).attr( "data-content", "Please enter the relevant law." );
  		$('#liti_relevant_law').popover('show');
	
         return false;
     } else  {
      	$('#liti_relevant_law').popover('destroy');
      }
    
    /*if (liti_brief_description == null || liti_brief_description=="" ) {
    	 
  		$( "#liti_brief_description" ).attr( "data-placement", "top" );
  		$( "#liti_brief_description" ).attr( "data-content", "Please enter brief description." );
  		$('#liti_brief_description').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_brief_description').popover('destroy');
      }*/
    
    if (liti_amount_involved == null || liti_amount_involved == "") {
     	 
  		$( "#liti_amount_involved" ).attr( "data-placement", "top" );
  		$( "#liti_amount_involved" ).attr( "data-content", "Please enter the amount involved." );
  		$('#liti_amount_involved').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_amount_involved').popover('destroy');
      }
    if (isNaN(liti_amount_involved)) {
      	
    	 $("#liti_amount_involved" ).attr( "data-placement", "top" );
         $("#liti_amount_involved" ).attr( "data-content", "Please enter only numbers." );
         $('#liti_amount_involved').popover('show');
         return false;
     }   
    else
    {
    	$('#liti_amount_involved').popover('destroy');
    }
    
   
    if (liti_amount_involved == null || liti_amount_involved == "" ) {
    	 
  		$( "#liti_amount_involved" ).attr( "data-placement", "top" );
  		$( "#liti_amount_involved" ).attr( "data-content", "Please enter amount." );
  		$('#liti_amount_involved').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_amount_involved').popover('destroy');
      }
    
    if (isNaN(liti_amount_involved)) {
   	 
  		$( "#liti_amount_involved" ).attr( "data-placement", "top" );
  		$( "#liti_amount_involved" ).attr( "data-content", "Please enter numeric amount." );
  		$('#liti_amount_involved').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_amount_involved').popover('destroy');
      }
    if (liti_involved_amt_currency == "NA" ) {
   	 
  		$( "#liti_involved_amt_currency" ).attr( "data-placement", "top" );
  		$( "#liti_involved_amt_currency" ).attr( "data-content", "Please select currency." );
  		$('#liti_involved_amt_currency').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_involved_amt_currency').popover('destroy');
      }
    if (isNaN(liti_conversion_rate)) {
      	 
  		$( "#liti_conversion_rate" ).attr( "data-placement", "top" );
  		$( "#liti_conversion_rate" ).attr( "data-content", "Please enter numeric rate." );
  		$('#liti_conversion_rate').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_conversion_rate').popover('destroy');
      }
    if(liti_conversion_rate > 0 ){
    if (liti_converted_amt_currency == "NA") {
      	 
  		$( "#liti_converted_amt_currency" ).attr( "data-placement", "top" );
  		$( "#liti_converted_amt_currency" ).attr( "data-content", "Please select converted currency." );
  		$('#liti_converted_amt_currency').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_converted_amt_currency').popover('destroy');
      }
    }
    else
    {
    	$('#liti_converted_amt_currency').popover('destroy');
    }
}
function validateDraft() { 
    
	var liti_orga_id = $('#liti_orga_id').val();
    var liti_loca_id = $('#liti_loca_id').val();
    var liti_dept_id = $('#liti_dept_id').val();
    var liti_type_id = $('#liti_type_id').val();
    var liti_against_by_id = $('#liti_against_by_id').val();
    var liti_company_acting_as = $('#liti_company_acting_as').val();
    var liti_counsel_law_firm = $('#liti_counsel_law_firm').val();
    var liti_external_counsel_id = $('#liti_external_counsel_id').val();
    var liti_advocate_law_firm = $('#liti_advocate_law_firm').val();
    var liti_advocate_id = $('#liti_advocate_id').val();
    var liti_internally_handled_by = $('#liti_internally_handled_by').val();
    var liti_case_filing_date = $('#liti_case_filing_date').val();
    var liti_opp_party_acting_as = $('#liti_opp_party_acting_as').val();
    var liti_opposite_party = $('#liti_opposite_party').val();
    var liti_opposite_party_advocate = $('#liti_opposite_party_advocate').val();
    var liti_forum = $('#liti_forum').val();
    var liti_case_reference_no = $('#liti_case_reference_no').val();
    var liti_court = $('#liti_court').val();
    var liti_relevant_law = $('#liti_relevant_law').val();
    var liti_amount_involved = $('#liti_amount_involved').val();
    var liti_brief_description = $('#liti_brief_description').val();
    var liti_amount_involved = $('#liti_amount_involved').val();
    var liti_involved_amt_currency = $('#liti_involved_amt_currency').val();
    var liti_conversion_rate = $('#liti_conversion_rate').val();
    var liti_converted_amt_currency = $('#liti_converted_amt_currency').val();

    if (liti_orga_id == 0 ) {
      	 
  		$( "#liti_orga_id" ).attr( "data-placement", "top" );
  		$( "#liti_orga_id" ).attr( "data-content", "Please select entity." );
  		$('#liti_orga_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_orga_id').popover('destroy');
      }

    if (liti_loca_id == 0 ) {
     	 
  		$( "#liti_loca_id" ).attr( "data-placement", "top" );
  		$( "#liti_loca_id" ).attr( "data-content", "Please select unit." );
  		$('#liti_loca_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_loca_id').popover('destroy');
      }
    if (liti_dept_id == 0 ) {
     	 
  		$( "#liti_dept_id" ).attr( "data-placement", "top" );
  		$( "#liti_dept_id" ).attr( "data-content", "Please select function." );
  		$('#liti_dept_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_dept_id').popover('destroy');
      }
    
    if (liti_against_by_id == 0 ) {
    	 
  		$( "#liti_against_by_id" ).attr( "data-placement", "top" );
  		$( "#liti_against_by_id" ).attr( "data-content", "Please select against or by." );
  		$('#liti_against_by_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#liti_against_by_id').popover('destroy');
      }
}

function validateCompletion(){
	
	var liti_litigation_result 		= $("#liti_litigation_result").val();
	var liti_disposal_date 			= $("#liti_disposal_date").val();
	var liti_synopsis_of_order 		= $("#liti_synopsis_of_order").val();
	var liti_completion_court_id	= $("#liti_completion_court_id").val();
	var liti_last_date_for_filling_appeal = $("#liti_last_date_for_filling_appeal").val();
	var liti_comments 				= $("#liti_comments").val();
	
	
	if (liti_litigation_result == 0 ) {
   	 
  		$( "#liti_litigation_result" ).attr( "data-placement", "top" );
  		$( "#liti_litigation_result" ).attr( "data-content", "Please select result." );
  		$('#liti_litigation_result').popover('show');
	
         return false;
     } else {
      	$('#liti_litigation_result').popover('destroy');
      }
	
	if (liti_disposal_date == 0 ) {
	   	 
  		$( "#liti_disposal_date" ).attr( "data-placement", "top" );
  		$( "#liti_disposal_date" ).attr( "data-content", "Please select Disposal date." );
  		$('#liti_disposal_date').popover('show');
	
         return false;
     } else {
      	$('#liti_disposal_date').popover('destroy');
      }
	if(liti_litigation_result != 'Against'){
	if (liti_synopsis_of_order == 0 ) {
	   	 
  		$( "#liti_synopsis_of_order" ).attr( "data-placement", "top" );
  		$( "#liti_synopsis_of_order" ).attr( "data-content", "Please enter Synopsis of order." );
  		$('#liti_synopsis_of_order').popover('show');
	
         return false;
     } else {
       	$('#liti_synopsis_of_order').popover('destroy');
       }
 	} else {
      	$('#liti_synopsis_of_order').popover('destroy');
      }
	
	if(liti_litigation_result != 'Against'){
	if (liti_completion_court_id == 0 ||  liti_completion_court_id == "NA") {
	   	 
  		$( "#liti_completion_court_id" ).attr( "data-placement", "top" );
  		$( "#liti_completion_court_id" ).attr( "data-content", "Please select court." );
  		$('#liti_completion_court_id').popover('show');
	
         return false;
     } else {
      	$('#liti_completion_court_id').popover('destroy');
      }
	} else {
		$('#liti_completion_court_id').val("0");
      	$('#liti_completion_court_id').popover('destroy');
    }
	
	if(liti_litigation_result != 'Against'){
	if (liti_last_date_for_filling_appeal == 0 ) {
	   	 
  		$( "#liti_last_date_for_filling_appeal" ).attr( "data-placement", "top" );
  		$( "#liti_last_date_for_filling_appeal" ).attr( "data-content", "Please select Last Date for Fillig Appeal." );
  		$('#liti_last_date_for_filling_appeal').popover('show');
	
         return false;
     } else {
      	$('#liti_last_date_for_filling_appeal').popover('destroy');
      }
	} else {
		if(document.getElementById('file_yes').checked && liti_last_date_for_filling_appeal == 0){
			$( "#liti_last_date_for_filling_appeal" ).attr( "data-placement", "top" );
	  		$( "#liti_last_date_for_filling_appeal" ).attr( "data-content", "Please select Last Date for Filling Appeal." );
	  		$('#liti_last_date_for_filling_appeal').popover('show');
		
	         return false;
	     } else {
	      	$('#liti_last_date_for_filling_appeal').popover('destroy');
	      }
		}
	
	if (liti_comments == 0 ) {
	   	 
  		$( "#liti_comments" ).attr( "data-placement", "top" );
  		$( "#liti_comments" ).attr( "data-content", "Please enter comments." );
  		$('#liti_comments').popover('show');
	
         return false;
     } else {
      	$('#liti_comments').popover('destroy');
      }
	
}

function blockSpace(e){
    var k;
    document.all ? k = e.keyCode : k = e.which;
    //console.log("Key code "+k)
    return k != 32;
    }

