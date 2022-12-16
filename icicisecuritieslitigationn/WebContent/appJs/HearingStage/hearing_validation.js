
$(document).ready(function(){

	$('#ttrn_stage_id').click(function(){
        $('#ttrn_stage_id').popover('destroy');
    });

	$("#hearing_stage_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	
	$("#hearing_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	//set to query date as today
	$("#first_alert_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	$("#second_alert_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	$("#third_alert_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	$("#due_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	
//******************************* code for next hearing date < 1st alert date ********************************************	
$("#first_alert_div").datepicker({
	 
 	autoclose : true,
	format : "dd-mm-yyyy",
	viewMode : "auto",
	minViewMode : "auto",
	endDate:"now",
	todayHighlight:"true",
	showOnFocus:"true",
	//defaultViewDate:"today"

}).datepicker().on('changeDate', function(e){

$('#hearing_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
});
	
	$("#hearing_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true"
		//defaultViewDate:"today"

	}).datepicker().on('changeDate', function(e){
		$('#first_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
	});
	
//************************************ code for action due date < hearing date **********************************************	
	$("#due_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		//defaultViewDate:"today"


	}).datepicker().on('changeDate', function(e){

	$('#hearing_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});
	$('#hearing_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', $('#ttrn_stage_due_date').val()).focus();
	
		$("#hearing_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		}).datepicker().on('changeDate', function(e){
			
			$('#due_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		
		$('#due_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', $('#ttrn_next_hearing_date').val()).focus();
//******************************************** code for 3rd alert > 2nd alert ****************************************************

	$("#second_alert_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		//defaultViewDate:"today"


	}).datepicker().on('changeDate', function(e){

	$('#third_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	});
	$('#third_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', $('#ttrn_second_alert').val()).focus();

		$("#third_alert_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"da"

		}).datepicker().on('changeDate', function(e){
			
			$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', $('#ttrn_third_alert').val()).focus();
		
//************************************** code for 3rd alert date > 2nd alert date > 1st alert date ************************************
		$("#first_alert_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
		});
		$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', $('#ttrn_first_alert').val()).focus();
		
			$("#second_alert_div").datepicker({
				 
			 	autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
				endDate:"now",
				todayHighlight:"true",
				showOnFocus:"true",
				//defaultViewDate:"today"

			}).datepicker().on('changeDate', function(e){
				
				$('#first_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
			});
			$('#first_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', $('#ttrn_second_alert').val()).focus();
			
			 $("#third_alert_div").datepicker({
				 
				 	autoclose : true,
					format : "dd-mm-yyyy",
					viewMode : "auto",
					minViewMode : "auto",
					endDate:"now",
					todayHighlight:"true",
					showOnFocus:"true",
					//defaultViewDate:"today"

				}).datepicker().on('changeDate', function(e){
					
					$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
				});
			 $('#second_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate',  $('#ttrn_third_alert').val()).focus();
			 
			 $("#hearing_date_div").datepicker({
				 
				 	autoclose : true,
					format : "dd-mm-yyyy",
					viewMode : "auto",
					minViewMode : "auto",
					endDate:"now",
					todayHighlight:"true",
					showOnFocus:"true",
					//defaultViewDate:"today"

				}).datepicker().on('changeDate', function(e){
					
					$('#third_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
				});
			 $('#third_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate',  $('#ttrn_next_hearing_date').val()).focus();
			 
			 $("#hearing_date_div").datepicker({
				 
				 	autoclose : true,
					format : "dd-mm-yyyy",
					viewMode : "auto",
					minViewMode : "auto",
					endDate:"now",
					todayHighlight:"true",
					showOnFocus:"true",
					//defaultViewDate:"today"

				}).datepicker().on('changeDate', function(e){
					
					$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
				});
				$('#second_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate',  $('#ttrn_next_hearing_date').val()).focus();
			 
			 $('#hearing_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', $('#ttrn_first_alert').val()).focus();
			 $('#first_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', $('#ttrn_next_hearing_date').val()).focus();
			 
});

$("#ttrn_counsel_law_firm_id").change(function(){ 
	var law_firm_id = $("#ttrn_counsel_law_firm_id").val();
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
			    $("#ttrn_counsel_id").html(data);
			}
	    });//End ajax
	}//End If
	
});

$(document).on("click","button[name='logsDetails']",function(){
	var hearing_stage_id = $(this).val();
	if(hearing_stage_id > 0) {
	var data = {};
	
	data["hearing_stage_id"]    = hearing_stage_id;

	var jsonString = JSON.stringify(data);
	
	$.ajax({
		type : "POST",
		url  : "./getHearingStageLogs",
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
				//data += "<td>"+value['stages']+"</td>";
				data += "<td>"+value['hearing_date']+" </td>";
				data += "<td>"+value['resp_person']+" </td>";
				data += "<td>"+value['counsel_name']+" </td>";
				data += "<td> 1st: "+value['first_alert']+" </br> 2nd: "+value['second_alert']+" </br> 3rd: "+value['third_alert']+" </td>";
				data += "<td>"+value['stage_desc']+" </td>";
				data += "<td>"+value['stage_log_status']+" </td>";
				data += "</tr>"
			i++;	
			});
		   }else{
			   data += "<tr><td colspan=8 style='text-align:center'>No result found.</td></tr>";
			   
		   }
			$("#loader").hide();
			$("#log_model").modal('show');
			$("#logResult").html(data);
		}
	});	
  }
	
});

$(document).on("click","button[name='delete_hearing_stage']",function(){
	
	var hear_stage_id = $(this).val();
	items = {};
	items["hear_stage_id"] = hear_stage_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(hear_stage_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteHearingStage",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span> &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Hearing Stage deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+hear_stage_id).remove();
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

function deleteHearingStageDoc(hear_doc_id){
	var doc_id = hear_doc_id;
	items = {};
	items["doc_id"] = doc_id;
	var jsonString = JSON.stringify(items);
	
		if(doc_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteHearingStageDoc",
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
   
function validateForm() {
		        
		var ttrn_stage_id = $("#ttrn_stage_id").val();
        var ttrn_next_hearing_date = $('#ttrn_next_hearing_date').val();
        var ttrn_first_alert = $('#ttrn_first_alert').val();
        var ttrn_additional_mail_id = $('#ttrn_additional_mail_id').val();
        var ttrn_stage_description = $('#ttrn_stage_description').val();
        var ttrn_responsible_person = $('#ttrn_responsible_person').val();
        var ttrn_stage_due_date = $('#ttrn_stage_due_date').val();
        var ttrn_counsel_id = $('#ttrn_counsel_id').val();
        var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
       
        if(ttrn_additional_mail_id !=''){
        	 var x = ttrn_additional_mail_id;
               var emails = x.split(",");
              
               if(emails.length <= 3){
            	   	for(var i=0; i<emails.length; i++){
            	   		if(reg.test(emails[i]) == false){ 
            	   			$( "#ttrn_additional_mail_id" ).attr( "data-placement", "top" );
            	   			$( "#ttrn_additional_mail_id" ).attr( "data-content", "Please enter valid Email id." );
            	   			$('#ttrn_additional_mail_id').popover('show');
            	   			return false;
            	   			}else{
            	   			$('#ttrn_additional_mail_id').popover('destroy');
            	   		}
            	   	}
               }else{
            	$( "#ttrn_additional_mail_id" ).attr( "data-placement", "top" );
           		$( "#ttrn_additional_mail_id" ).attr( "data-content", "Please enter three Email ids." );
           		$('#ttrn_additional_mail_id').popover('show');
           		return false;
			}
               
        }
  	 
        
        if (ttrn_stage_id == 0 ) {
       	 
     		$( "#ttrn_stage_id" ).attr( "data-placement", "top" );
     		$( "#ttrn_stage_id" ).attr( "data-content", "Please select Stage name." );
     		$('#ttrn_stage_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_stage_id').popover('destroy');
        }
    	
    	if (ttrn_next_hearing_date == 0) {
          	 
     		$( "#ttrn_next_hearing_date" ).attr( "data-placement", "top" );
     		$( "#ttrn_next_hearing_date" ).attr( "data-content", "Please select next hearing date." );
     		$('#ttrn_next_hearing_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_next_hearing_date').popover('destroy');
        }
    	
    	/*if (ttrn_first_alert == 0) {
       	 
     		$( "#ttrn_first_alert" ).attr( "data-placement", "top" );
     		$( "#ttrn_first_alert" ).attr( "data-content", "Please select first alert ." );
     		$('#ttrn_first_alert').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_first_alert').popover('destroy');
        }*/
    	
    	/*if (ttrn_additional_mail_id == 0) {
          	 
     		$( "#ttrn_additional_mail_id" ).attr( "data-placement", "top" );
     		$( "#ttrn_additional_mail_id" ).attr( "data-content", "Please enter additional mail id." );
     		$('#ttrn_additional_mail_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_additional_mail_id').popover('destroy');
        }
    	 
    	if (reg.test(ttrn_additional_mail_id) == false) 
    	    {
    	    	$( "#ttrn_additional_mail_id" ).attr( "data-placement", "top" );
    	 		$( "#ttrn_additional_mail_id" ).attr( "data-content", "Please enter valid additional mail id." );
    	 		$('#ttrn_additional_mail_id').popover('show');     
    	 		return false;
    	    }

    	 else
         {
         	$('#ttrn_additional_mail_id').popover('destroy');
         }
*/          
    	
    	if (ttrn_stage_description == 0) {
         	 
     		$( "#ttrn_stage_description" ).attr( "data-placement", "top" );
     		$( "#ttrn_stage_description" ).attr( "data-content", "Please enter stage description." );
     		$('#ttrn_stage_description').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_stage_description').popover('destroy');
        }
    	
    	if (ttrn_responsible_person == 0) {
        	 
     		$( "#ttrn_responsible_person" ).attr( "data-placement", "top" );
     		$( "#ttrn_responsible_person" ).attr( "data-content", "Please select responsible person." );
     		$('#ttrn_responsible_person').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_responsible_person').popover('destroy');
        }
    	/*if (ttrn_counsel_id == 0) {
          	 
     		$( "#ttrn_counsel_id" ).attr( "data-placement", "top" );
     		$( "#ttrn_counsel_id" ).attr( "data-content", "Please select Counsel name." );
     		$('#ttrn_counsel_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#ttrn_counsel_id').popover('destroy');
        }*/
    
    	if (ttrn_stage_due_date == 0) {
       	 
     		$( "#ttrn_stage_due_date" ).attr( "data-placement", "top" );
     		$( "#ttrn_stage_due_date" ).attr( "data-content", "Please enter stage due Date." );
     		$('#ttrn_stage_due_date').popover('show');
	
            return false;
        }else {
         	$('#ttrn_stage_due_date').popover('destroy');
        }
    		
    	//return false;
    	
 }

function validateDraft() {
    
	var ttrn_stage_id = $("#ttrn_stage_id").val();
    var ttrn_next_hearing_date = $('#ttrn_next_hearing_date').val();
    var ttrn_first_alert = $('#ttrn_first_alert').val();
    var ttrn_additional_mail_id = $('#ttrn_additional_mail_id').val();
    var ttrn_stage_description = $('#ttrn_stage_description').val();
    var ttrn_responsible_person = $('#ttrn_responsible_person').val();
    var ttrn_stage_due_date = $('#ttrn_stage_due_date').val();
    var ttrn_counsel_id = $('#ttrn_counsel_id').val();
    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   
    if(ttrn_additional_mail_id !=''){
    	 var x = ttrn_additional_mail_id;
           var emails = x.split(",");
          
           if(emails.length <= 3){
        	   	for(var i=0; i<emails.length; i++){
        	   		if(reg.test(emails[i]) == false){ 
        	   			$( "#ttrn_additional_mail_id" ).attr( "data-placement", "top" );
        	   			$( "#ttrn_additional_mail_id" ).attr( "data-content", "Please enter valid Email id." );
        	   			$('#ttrn_additional_mail_id').popover('show');
        	   			return false;
        	   			}else{
        	   			$('#ttrn_additional_mail_id').popover('destroy');
        	   		}
        	   	}
           }else{
        	$( "#ttrn_additional_mail_id" ).attr( "data-placement", "top" );
       		$( "#ttrn_additional_mail_id" ).attr( "data-content", "Please enter three Email ids." );
       		$('#ttrn_additional_mail_id').popover('show');
       		return false;
		}
           
    }
	 
    
    if (ttrn_stage_id == 0 ) {
   	 
 		$( "#ttrn_stage_id" ).attr( "data-placement", "top" );
 		$( "#ttrn_stage_id" ).attr( "data-content", "Please select Stage name." );
 		$('#ttrn_stage_id').popover('show');

        return false;
    }
	 else
     {
     	$('#ttrn_stage_id').popover('destroy');
    }
	
	/*if (ttrn_next_hearing_date == 0) {
      	 
 		$( "#ttrn_next_hearing_date" ).attr( "data-placement", "top" );
 		$( "#ttrn_next_hearing_date" ).attr( "data-content", "Please select next hearing date." );
 		$('#ttrn_next_hearing_date').popover('show');

        return false;
    }
	 else
     {
     	$('#ttrn_next_hearing_date').popover('destroy');
    }*/
	
	/*if (ttrn_first_alert == 0) {
   	 
 		$( "#ttrn_first_alert" ).attr( "data-placement", "top" );
 		$( "#ttrn_first_alert" ).attr( "data-content", "Please select first alert ." );
 		$('#ttrn_first_alert').popover('show');

        return false;
    }
	 else
     {
     	$('#ttrn_first_alert').popover('destroy');
    }
	
	
	if (ttrn_stage_description == 0) {
     	 
 		$( "#ttrn_stage_description" ).attr( "data-placement", "top" );
 		$( "#ttrn_stage_description" ).attr( "data-content", "Please enter stage description." );
 		$('#ttrn_stage_description').popover('show');

        return false;
    }
	 else
     {
     	$('#ttrn_stage_description').popover('destroy');
    }
	
	if (ttrn_responsible_person == 0) {
    	 
 		$( "#ttrn_responsible_person" ).attr( "data-placement", "top" );
 		$( "#ttrn_responsible_person" ).attr( "data-content", "Please select responsible person." );
 		$('#ttrn_responsible_person').popover('show');

        return false;
    }
	 else
     {
     	$('#ttrn_responsible_person').popover('destroy');
    }

	if (ttrn_stage_due_date == 0) {
   	 
 		$( "#ttrn_stage_due_date" ).attr( "data-placement", "top" );
 		$( "#ttrn_stage_due_date" ).attr( "data-content", "Please enter stage due Date." );
 		$('#ttrn_stage_due_date').popover('show');

        return false;
    }
	 else
     {
     	$('#ttrn_stage_due_date').popover('destroy');
    } */
		
	//return false;
	
}

var filecount = 1;
function addFileInput() {
   // if(filecount <=5){
	$('#filesContainer')
	.append(
			"<div class='form-group' id='file"+filecount+"'>"
			+"<label class='col-sm-5 control-label'>Upload Document :</label>"
			+"<div class='col-sm-3'><input type='file' name='stage_document' class='file-loading'/>"
			+"</div>"
			+"<div class='col-sm-4' style='text-align: right;'>"
			+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteRow("+filecount+");' style='color: red;cursor:pointer;' title='Delete'></i>"
			+"</div></div>");
	filecount++;
    //}
}

function deleteRow(filecount) {
	$("#file" + filecount).remove();
}
