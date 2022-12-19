$(document).ready(function(){
	
	$("#due_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	})
	$("#reminder_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	})
	
	$("#lega_person_responsible_others_div").hide();
	var lega_person_responsible = $("#lega_person_responsible").val();
	if(lega_person_responsible == '-1'){
		$("#lega_person_responsible_others_div").show();
	}else{
		$("#lega_person_responsible_others").val("NA");
		$("#lega_person_responsible_others_div").hide();
	}
	
	$("#lega_person_responsible").change(function(){ 
		var lega_person_responsible = $("#lega_person_responsible").val();
		if(lega_person_responsible == '-1'){
			$("#lega_person_responsible_others_div").show();
		}else{
			$("#lega_person_responsible_others").val("NA");
			$("#lega_person_responsible_others_div").hide();
		}
	});
	
$("#reminder_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		//defaultViewDate:"today"

	}).datepicker().on('changeDate', function(e){

	$('#due_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});

		$("#due_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#reminder_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		
		$('#due_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', $('#lega_reminder_date').val()).focus();
		$('#reminder_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', $("#lega_action_item_due_date").val()).focus();
});


$(document).on("click","button[name='delete_legal_notice_status']",function(){
	
	var lega_status_id = $(this).val();
	items = {};
	items["lega_status_id"] = lega_status_id;
	var jsonString = JSON.stringify(items);
	
		if(lega_status_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLegalNoticeStatus",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Legal Notice Status deleted successfully..!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+lega_status_id).remove();
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

$(document).on("click","button[name='delete_legal_notice_status_doc']",function(){
	
	var doc_id = $(this).val();
	items = {};
	items["doc_id"] = doc_id;
	var jsonString = JSON.stringify(items);
	
		if(doc_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLegaNotiStatusDoc",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Legal Notice Document deleted successfully..!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+doc_id).remove();
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

$(document).on("click","button[name='statusLegalNotiLogs']",function(){
	var lega_noti_status_id = $(this).val();
	if(lega_noti_status_id > 0) {
	var data = {};
	
	data["lega_noti_status_id"]    = lega_noti_status_id;

	var jsonString = JSON.stringify(data);
	
	$.ajax({
		type : "POST",
		url  : "./getLegalNoticeStatusLogs",
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
				data += "<td>"+value['lega_action_taken']+"</td>";
				data += "<td>"+value['lega_next_action_item']+" </td>";
				data += "<td>"+value['lega_action_item_due_date']+" </td>";
				data += "<td>"+value['lega_person_responsible']+" </td>";
				data += "<td>"+value['lega_reminder_date']+" </td>";
				data += "<td>"+value['log_status']+" </td>";
				data += "</tr>"
			i++;	
			});
		   }else{
			   data += "<tr><td colspan=7 style='text-align:center'>No result found.</td></tr>";
			   
		   }
			$("#loader").hide();
			$("#log_model_legal_notice").modal('show');
			$("#logResultLegalNotice").html(data);
		}
	});	
  }
	
});


function deleteLegaNotiStatusDoc(lega_doc_id){
	var doc_id = lega_doc_id;
	items = {};
	items["doc_id"] = doc_id;
	var jsonString = JSON.stringify(items);
	
		if(doc_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLegaNotiStatusDoc",
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
				    				$("#doc_attached_" + doc_id).remove();
				    			}
				    			
				    		}
					 });
				 }
			 });
		}
}


function validateForm(){
		
		var lega_action_taken = $("#lega_action_taken").val();
        var lega_next_action_item = $('#lega_next_action_item').val();
        var lega_action_item_due_date = $('#lega_action_item_due_date').val();
        var lega_reminder_date = $('#lega_reminder_date').val();
        var lega_person_responsible = $('#lega_person_responsible').val();
        var lega_person_responsible_others = $('#lega_person_responsible_others').val();
		
		if (lega_action_taken == 0 ) {
	       	 
     		$( "#lega_action_taken" ).attr( "data-placement", "top" );
     		$( "#lega_action_taken" ).attr( "data-content", "Please enter action taken." );
     		$('#lega_action_taken').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_action_taken').popover('destroy');
        }
    	
    	if (lega_next_action_item == 0) {
          	 
     		$( "#lega_next_action_item" ).attr( "data-placement", "top" );
     		$( "#lega_next_action_item" ).attr( "data-content", "Please enter next action item." );
     		$('#lega_next_action_item').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_next_action_item').popover('destroy');
        }
    	
    	if (lega_action_item_due_date == 0 || lega_action_item_due_date == "" ) {
       	 
     		$( "#lega_action_item_due_date" ).attr( "data-placement", "top" );
     		$( "#lega_action_item_due_date" ).attr( "data-content", "Please select action item due date." );
     		$('#lega_action_item_due_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_action_item_due_date').popover('destroy');
        }	
    	
    	if (lega_reminder_date == 0 || lega_reminder_date == "") {
          	 
     		$( "#lega_reminder_date" ).attr( "data-placement", "top" );
     		$( "#lega_reminder_date" ).attr( "data-content", "Please select reminder date." );
     		$('#lega_reminder_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_reminder_date').popover('destroy');
        }	
		
    	if (lega_person_responsible == 0) {
          	 
     		$( "#lega_person_responsible" ).attr( "data-placement", "top" );
     		$( "#lega_person_responsible" ).attr( "data-content", "Please select responsible person name." );
     		$('#lega_person_responsible').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_person_responsible').popover('destroy');
        }	
    	if (lega_person_responsible == '-1') {
         	 if(lega_person_responsible_others == 0){
     		$( "#lega_person_responsible_others" ).attr( "data-placement", "top" );
     		$( "#lega_person_responsible_others" ).attr( "data-content", "Please select other name." );
     		$('#lega_person_responsible_others').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#lega_person_responsible_others').popover('destroy');
        }	
    	}else
         {
         	$('#lega_person_responsible_others').popover('destroy');
        }
}

function validateDraft(){
	
	var lega_action_taken = $("#lega_action_taken").val();

	if (lega_action_taken == 0 ) {
      	 
 		$( "#lega_action_taken" ).attr( "data-placement", "top" );
 		$( "#lega_action_taken" ).attr( "data-content", "Please enter action taken." );
 		$('#lega_action_taken').popover('show');

        return false;
    }
	 else
     {
     	$('#lega_action_taken').popover('destroy');
    }
}

