$(document).ready(function(){

	$("#sarf_orga_id").on('change', getAllLocationForOrganizationLiti);
	$("#sarf_loca_id").on('change', getAllDepartmentsByLocationLiti);
	$(".addSarfActDocuments").on('click', addFileInputSarfAct);
	

	
	
	
	$("#from_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	//set to query date as today
	
	$("#to_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	
	
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
	
	$("#sarf_filling_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"
			 
	});


	$("#npa_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"
			 
	}).datepicker().on('changeDate', function(e){

		$('#NotiIssue_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
		});
	
	

	$("#NotiIssue_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		//defaultViewDate:"today"


	});
	
	$("#sarf_NotiIssue_date").on("change", function(e){
		//alert();
		 var date2 = $('#NotiIssue_date_div').datepicker('getDate'); 
		  date2.setDate(date2.getDate()+57); 
		  $('#Remider_first_div').datepicker('setDate', date2);
	});
	
	$("#sarf_NotiIssue_date").on("change", function(e){
		//alert();
		 var date2 = $('#NotiIssue_date_div').datepicker('getDate'); 
		  date2.setDate(date2.getDate()+60); 
		  $('#Reminder_second_div').datepicker('setDate', date2);
	});
	
	$("#Remider_first_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		//endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true"


	});
	
	
	 $("#Reminder_second_div").datepicker({
		 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			//defaultViewDate:"today"

		});
});
function getAllLocationForOrganizationLiti() { 
	var user_orga_id = $("#sarf_orga_id").val();
	//var orga_name = user_orga_id.options[user_orga_id.selectedIndex].text;
	if (user_orga_id > 0) {

		$.ajax({
			type : "post",
			url : "./getLocationByOrgaUserId",
			//contentType : "application/json",
			dataType : "json",
			data : {orga_id :user_orga_id },
			cache : false,
			success : function(locationjson) {
				var unit_name ="";
				data ="";
				data +='<option value = 0>--Select--</option>';

				$.each(locationjson,function(key,value){
					unit_name = value['loca_name'];
					data += "<option value="+value['loca_id']+">"+value['loca_name']+"</option>";
				});
				$("#sarf_loca_id").html(data);	
				//console.log("Hello "+value['loca_name']);
				//$("#liti_party_by").val(orga_name);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	} else {
		$('#sarf_loca_id').html('<option value="0">--Select--</option>');
		
	}
}

var sarfFileCount = 0;
function addSarfActFileInput(){
	//alert("!!!!!");
	$("#fileContiner_sarfaesiDoc").append('<div class="col-md-12" id="SarfActFile'+sarfFileCount+'" >'
			+'<div class="form-group">'
	        +'<label class="col-sm-4 control-label"> Document:</label>'
	        +'<div class="col-sm-4">'
	        +'<input type="file" name="sarfaesiAct_documents" class="file-loading" />'
	        +'</div>'
	        +'<div class="col-sm-3" style="text-align: right; cursor: pointer;">'
	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteSarfActRow('+sarfFileCount+');" style="color: red;" title="Delete"></i>'
	        +'</div>'
	        +'</div>'
	        +'</div>'
			);
	sarfFileCount++;
	}
function deleteSarfActRow(filecount) {
	$("#SarfActFile" + filecount).remove();
}

$("#btnSearch").click(function(){
	//alert(sarf_orga_id);
	var sarf_orga_id = $("#sarf_orga_id").val();
	//alert(sarf_orga_id);
	var sarf_loca_id = $("#sarf_loca_id").val();
	var sarf_security_type = $("#sarf_security_type").val();
	var sarf_loan_no = $("#sarf_loan_no").val();
	var sarf_borrower = $("#sarf_borrower").val();
	var sarf_security_Interest = $("#sarf_security_Interest").val();
	var SarfAct_from_date = $("#SarfAct_from_date").val();
	var SarfAct_to_date = $("#SarfAct_to_date").val();
	
	
	if(sarf_orga_id > 0) {
		
		$("#btnSearch").hide();
		$("#loader").show();
	var data = {};
	data["sarf_orga_id"] = sarf_orga_id;
	data["sarf_loca_id"]   = sarf_loca_id;
	data["sarf_security_type"]     = sarf_security_type;
	data["sarf_loan_no"]  = sarf_loan_no;
	data["sarf_borrower"] 			= sarf_borrower;
	data["sarf_security_Interest"]   = sarf_security_Interest;
	data["SarfAct_from_date"]  = SarfAct_from_date;
	data["SarfAct_to_date"]    = SarfAct_to_date;
	
	
	var jsonString = JSON.stringify(data);
	
	$.ajax({
		type : "POST",
		url  : "./searchSarfaesiAct",
		data : jsonString,
		contentType : "application/json",
		dataType : "json",
		success : function(result){
			var data = "";
			var i=1;
			document.getElementById('count').innerHTML = result.length;	
			if(result.length != 0){
				
			$.each(result,function(key,value){
			
				var sarf_act_id = value['sarf_act_id'];
				data += "<tr id ='row_"+sarf_act_id+"'>";
				data +='<td><center>'+i+"</center></a></td>";
				data += "<td>"+value['sarf_loan_no']+"</td>";
                data += "<td>"+value['sarf_borrower']+"</td>";
                data += "<td>"+value['sarf_security_type']+"</td>";
				data += "<td>"+value['sarf_security_Interest']+"</td>";
				data += "<td>"+value['sarf_npa_date']+"</td>";
			
				data += "<td>";
				data += "<a href='editSarfaesiAct?sarf_act_id="+value['sarf_act_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin-bottom :4px;'><i class='fa fa-pencil-square-o'></i>&nbsp;Edit</button></a><br/>";
				data += "<button type='button' value="+value['sarf_act_id']+" name='delete_sarfAct' class='btn btn-danger' style='margin-bottom: 4px;'><i class='fa fa-trash'></i>&nbsp;Delete</button>";
                data += "<br/><a href='sarfaesiActDetails?sarf_act_id="+value['sarf_act_id']+"'><button type='button' name='submit' value='submit' class='btn btn-primary' style='margin-bottom :4px;'><i class='fa fa-pencil-square-o'></i>&nbsp;Update</button></a>";
                data +=" </td>";
				
				
			    data += "</tr>";
			    i++;
				
			});
			   }else{
				   data += "<tr><td colspan=7 style='text-align:center'>No result found.</td></tr>";
			   }
				$("#btnSearch").show();
				$("#loader").hide();
				$("#searchResult").html(data);
			}
		});	
	}
	else{
		$("#sarf_orga_id").attr("data-placement", "top");
		$("#sarf_orga_id").attr("data-content", "Choose Entity Field..!!");
		$('#sarf_orga_id').popover('show');
		return false;
	}
	 
	});
$(document).on("click","button[name='delete_sarfAct']",function(){
	
	var sarf_act_id = $(this).val();
	
	items = {};
	items["sarf_act_id"] = sarf_act_id;
	
	var jsonString = JSON.stringify(items);
	
		if(sarf_act_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteSarfaesiAct",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Sarfaesi Act deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#SarfActList tr#row_'+sarf_act_id).remove();
				    				document.getElementById('count').innerHTML = ($('#SarfActList >tbody >tr').length);	
				        			/*window.location.reload(true);*/
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
$(document).on("click","button[name='delete_sarf_act_doc']",function(){
	
	var sarf_doc_id = $(this).val();
	//alert(sarf_doc_id);
	items = {};
	items["sarf_doc_id"] = sarf_doc_id;
	var jsonString = JSON.stringify(items);
	
		if(sarf_doc_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteSarfaesiActDocument",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount=="Success"){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Document deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+sarf_doc_id).remove();
				        			window.location.reload(true);
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);	
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
 
function validateForm() { 
    
	var sarf_orga_id = $('#sarf_orga_id').val();
    var sarf_loca_id = $('#sarf_loca_id').val();
    var sarf_dept_id = $('#sarf_dept_id').val();
    var sarf_loan_no = $('#sarf_loan_no').val();
    var sarf_borrower = $('#sarf_borrower').val();
    var sarf_contact_no = $('#sarf_contact_no').val();
    var sarf_email = $('#sarf_email').val();
    var sarf_Executor_id = $('#sarf_Executor_id').val();
    var sarf_security_type = $('#sarf_security_type').val();
   // alert(sarf_security_type);
    var sarf_security_Interest = $('#sarf_security_Interest').val();
    var sarf_asset_company_id = $('#sarf_asset_company_id').val();
    var sarf_security_loc = $('#sarf_security_loc').val();
    var sarf_npa_date = $('#sarf_npa_date').val();
    var sarf_filling_date = $('#sarf_filling_date').val();
    var sarf_NotiIssue_date = $('#sarf_NotiIssue_date').val();
    var sarf_first_reminder = $('#sarf_first_reminder').val();
   /* var sarf_NotiIssue_date = $('#sarf_NotiIssue_date').val();*/
    var sarf_total_amount = $('#sarf_total_amount').val();
    var sarf_loan_repay_amount = $('#sarf_loan_repay_amount').val();
    var sarf_paid_amount = $('#sarf_paid_amount').val();
    
    
    if (sarf_orga_id == 0 ) {
      	 
  		$( "#sarf_orga_id" ).attr( "data-placement", "top" );
  		$( "#sarf_orga_id" ).attr( "data-content", "Please select entity." );
  		$('#sarf_orga_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_orga_id').popover('destroy');
      }

    if (sarf_loca_id == 0 ) {
     	 
  		$( "#sarf_loca_id" ).attr( "data-placement", "top" );
  		$( "#sarf_loca_id" ).attr( "data-content", "Please select unit." );
  		$('#sarf_loca_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_loca_id').popover('destroy');
      }
    
    if (sarf_dept_id == 0 ) {
    	 
  		$( "#sarf_dept_id" ).attr( "data-placement", "top" );
  		$( "#sarf_dept_id" ).attr( "data-content", "Please select Function." );
  		$('#sarf_dept_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_dept_id').popover('destroy');
      }
    if (sarf_loan_no == 0)  {
    	 
  		$( "#sarf_loan_no" ).attr( "data-placement", "top" );
  		$( "#sarf_loan_no" ).attr( "data-content", "Please Enter Loan Numbar." );
  		$('#sarf_loan_no').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_loan_no').popover('destroy');
      }
   
    if(loan_no_status==1){
   	    $( "#sarf_loan_no" ).attr( "data-placement", "top" );
        $( "#sarf_loan_no" ).attr( "data-content", " Loan Number is already exists..!!" );
        $('#sarf_loan_no').popover('show');
        
        return false;
    }
  
	else
  {
  	$('#sarf_loan_no').popover('destroy');
  }
    
    if (sarf_borrower == 0)  {
   	 
  		$( "#sarf_borrower" ).attr( "data-placement", "top" );
  		$( "#sarf_borrower" ).attr( "data-content", "Please Enter Borrower Name." );
  		$('#sarf_borrower').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_borrower').popover('destroy');
      }
    if (sarf_contact_no == 0)  {
   	 
  		$( "#sarf_contact_no" ).attr( "data-placement", "top" );
  		$( "#sarf_contact_no" ).attr( "data-content", "Please Enter Contact Number." );
  		$('#sarf_contact_no').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_contact_no').popover('destroy');
      }
    
    if (sarf_contact_no.match( /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/))
  	{
        
    } else {
    	$( "#sarf_contact_no" ).attr( "data-placement", "top" );
         $( "#sarf_contact_no" ).attr( "data-content", "Phone number contain only 10 digit." );
         $('#sarf_contact_no').popover('show');
         return false;
    }
    
    if (sarf_email == 0)  {
    	 
  		$( "#sarf_email" ).attr( "data-placement", "top" );
  		$( "#sarf_email" ).attr( "data-content", "Please Enter Email." );
  		$('#sarf_email').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_email').popover('destroy');
      }
    
    if (sarf_email.match( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/))
  	{
        
    } else {
    	$( "#sarf_email" ).attr( "data-placement", "top" );
         $( "#sarf_email" ).attr( "data-content", "Not a valid e-mail address." );
         $('#sarf_email').popover('show');
         return false;
    }
    
    if (sarf_Executor_id == 0)  {
      	 
  		$( "#sarf_Executor_id" ).attr( "data-placement", "top" );
  		$( "#sarf_Executor_id" ).attr( "data-content", "Please select Executor ID ." );
  		$('#sarf_Executor_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_Executor_id').popover('destroy');
      }
    
    if (sarf_security_type == "NA")  {
     	 
  		$( "#sarf_security_type" ).attr( "data-placement", "top" );
  		$( "#sarf_security_type" ).attr( "data-content", "Please select Security Type." );
  		$('#sarf_security_type').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_security_type').popover('destroy');
      }
    if (sarf_security_Interest == "NA")  {
     	 
  		$( "#sarf_security_Interest" ).attr( "data-placement", "top" );
  		$( "#sarf_security_Interest" ).attr( "data-content", "Please select Security Interest." );
  		$('#sarf_security_Interest').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_security_Interest').popover('destroy');
      }
    
    if (sarf_asset_company_id == 0 ) {
     	 
  		$( "#sarf_asset_company_id" ).attr( "data-placement", "top" );
  		$( "#sarf_asset_company_id" ).attr( "data-content", "Please select Asset Reconstruction Company." );
  		$('#sarf_asset_company_id').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_asset_company_id').popover('destroy');
      }
    if (sarf_security_loc == 0)  {
     	 
  		$( "#sarf_security_loc" ).attr( "data-placement", "top" );
  		$( "#sarf_security_loc" ).attr( "data-content", "Please Enter Security Location." );
  		$('#sarf_security_loc').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_security_loc').popover('destroy');
      }

    if (sarf_npa_date == 0)  {
    	 
  		$( "#sarf_npa_date" ).attr( "data-placement", "top" );
  		$( "#sarf_npa_date" ).attr( "data-content", "Please Select NPA date." );
  		$('#sarf_npa_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_npa_date').popover('destroy');
      }
    if (sarf_filling_date == 0)  {
    	 
  		$( "#sarf_filling_date" ).attr( "data-placement", "top" );
  		$( "#sarf_filling_date" ).attr( "data-content", "Please Select Case Filling date." );
  		$('#sarf_filling_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_filling_date').popover('destroy');
      }
    
    if (sarf_NotiIssue_date == 0)  {
     	 
  		$( "#sarf_NotiIssue_date" ).attr( "data-placement", "top" );
  		$( "#sarf_NotiIssue_date" ).attr( "data-content", "Please Select Notice Issue date." );
  		$('#sarf_NotiIssue_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_NotiIssue_date').popover('destroy');
      }
    
    if (sarf_total_amount == 0)  {
    	 
  		$( "#sarf_total_amount" ).attr( "data-placement", "top" );
  		$( "#sarf_total_amount" ).attr( "data-content", "Please Enter Total Amount." );
  		$('#sarf_total_amount').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_total_amount').popover('destroy');
      }
    if (sarf_loan_repay_amount == 0)  {
   	 
  		$( "#sarf_loan_repay_amount" ).attr( "data-placement", "top" );
  		$( "#sarf_loan_repay_amount" ).attr( "data-content", "Please Enter Loan Repayment Amount." );
  		$('#sarf_loan_repay_amount').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_loan_repay_amount').popover('destroy');
      }
    
  /*  if(sarf_loan_repay_amount > sarf_total_amount)
    	{
    	    $( "#sarf_loan_repay_amount" ).attr( "data-placement", "top" );
  		    $( "#sarf_loan_repay_amount" ).attr( "data-content", "loan repayment amount is greater than total amount." );
  		    $('#sarf_loan_repay_amount').popover('show');
	
         return false;
    	}
    else
    {
    	$('#sarf_loan_repay_amount').popover('destroy');
    }*/
    if (sarf_paid_amount == 0)  {
   	 
  		$( "#sarf_paid_amount" ).attr( "data-placement", "top" );
  		$( "#sarf_paid_amount" ).attr( "data-content", "Please Enter Paid Amount." );
  		$('#sarf_paid_amount').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_paid_amount').popover('destroy');
      }
    
    
    return true;
}

$(document).on("click","button[name='delete_sarfActAction']",function(){
	
	var sarf_action_id = $(this).val();
	
	items = {};
	items["sarf_action_id"] = sarf_action_id;
	//alert(sarf_action_id);
	var jsonString = JSON.stringify(items);
	
		if(sarf_action_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteSarfaesiActAction",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Sarfaesi Act Action Item deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#listAction tr#row_'+sarf_action_id).remove();
				    				document.getElementById('count').innerHTML = ($('#listAction >tbody >tr').length);	
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

var filecount = 1;
function addFileInputSarfAct(){
	
	$('#fileAddingForm')
	.append(
			"<div id='div"+filecount+"' class='col-md-12'>"
			+ "<div class='col-md-3'><label  form-control-label'>Document Type : </label></div>"
			+ "<div class='col-md-4'> <input type='text' class='form-control' name='sarf_document_Type' id='sarf_document_Type' placeholder='Please enter Document Description'/></div><div  style='padding:3px;margin:3px; text-align:center;' class='col-md-12 litigation_buttons' id='divButton"+filecount+"'>"
			+ "<div class='form-group row'>"
			+ "<label class='col-md-4 form-control-label'>Select Document :</label>"
			+ "<div class='col-md-2'>"
			+ "<input type='file' required='required' id='SarfDocument' name='SarfDocument' class='file-loading' />"
			+ "</div> <a class='col-md-4' href='#' onclick='deleteRowSarfRelated("+filecount+ ");'><i class='glyphicon glyphicon-remove-circle uploadClose'></i></a></div>"
			
			+ "<input type='submit' id='submit' class='btn btn-myPrimary' value='Submit' /></div>");
	filecount++;
	$("#addSarfActDocuments").hide();
	
}
function deleteRowSarfRelated(filecount) {
	$("#div" + filecount).remove();
	$("#divButton" + filecount).remove();
	$("#addSarfActDocuments").show();

}

/*--------------------------------Code to post form Data using ajax-------------------------------------------------*/
$("form#addSarfActDocumentForm").submit(function (event) {
	$("#submit").hide();
	$("#loader").show();
	//disable the default form submission
	event.preventDefault();
	//grab all form data  
	var formData = new FormData($(this)[0]);
	// formData.append( 'file', $( '#file' )[0].files[0] );

	$.ajax({
		url: "./addSarfActDocuments",
		type: 'POST',
		data: formData,
		async: true,
		cache: false,
		contentType: false,
		processData: false,
		success: function (data) {


			getAllSarfActDocuments(data);
			$("#submit").show();
			$("#loader").hide();
		},
		error: function(){
			//alert("error in ajax form submission");
		}
	});

	return false;
});
/*----------------------------Code to post form Data using ajax ends here-------------------------------------------*/
function getAllSarfActDocuments(data){
	//alert("#######");
	items = {};
	items['doc_SarfAct_id'] = data;
    //alert(data)
	var jsonString = JSON.stringify(items);

$.ajax({
		type : "post",
		url : "./getAllSarfDocumentForAjax",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(documentsList) {
			var htmlOutputSarfAct = '';
		
			if(documentsList != ""){
				var documentJson = $.parseJSON(documentsList);
				var j=1; 
				for(var i=0 ; i < documentJson.length ; i++){
					var innerObj = documentJson[i];

						htmlOutputSarfAct += "<tr id='document"+innerObj["sarf_doc_id"]+"'><td>"+ j +"</td>";
						htmlOutputSarfAct += "<td><"+innerObj["sarf_document_Type"]+">"+ innerObj["sarf_document_Type"] +"</td>";
						htmlOutputSarfAct += "<td><a href='./downloadSarfActDoc?sarf_doc_id="+innerObj["sarf_doc_id"]+"'>"+ innerObj["sarf_original_file_name"] +"</a></td>";
						htmlOutputSarfAct += "<td class='delete'><button type='button' value='"+ innerObj["sarf_doc_id"] +"' name='delete_sarf_act_doc' class='btn btn-danger'><i class='fa fa-trash'></i>&nbsp;Delete</button></td></tr>";
						j++;
					
				}
			}else {
				htmlOutput += "<tr><td colspan=3 style='text-align:center'>No Result Found</td></tr>";
			}

			$("#sarf_docs_tbody").html(htmlOutputSarfAct);
			$('#fileAddingForm').html("");
			$(".addSarfActDocuments").show();
			filecount =1;

		},
		error : function(xhr, ajaxOptions, thrownError) {
			$('#errors').html("There is error:" + thrownError);
		}
	});
}

var loan_no_status;
$("#sarf_loan_no").keyup(function(){ 
	  var sarf_loan_no = jQuery.trim($("#sarf_loan_no").val());
	  //alert(sarf_loan_no);
	 
 if(sarf_loan_no !='' && sarf_loan_no!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["sarf_loan_no"] = sarf_loan_no;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isLoanNumberExistOrNot",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			loan_no_status = 0;
			$('#sarf_loan_no').popover('destroy');
		}
		else{
			loan_no_status = 1;
			$("#sarf_loan_no").attr("data-placement", "top");
			$("#sarf_loan_no").attr("data-content", "Loan Number is already exists..!!");
			$('#sarf_loan_no').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});
//method name:Pranjali Kawale 
//****get ARC manager BY ARC name Id******
$("#sarf_arc_name").change(function(){ 
	var arc_name_id = $("#sarf_arc_name").val();
	//alert(arc_name_id);
	if(arc_name_id > 0){
		$.ajax({
			type : "POST",
			url  : "./getArcManagerByARCnameId",
			data : {arc_name_id:arc_name_id},
			success : function(res){
				var data = "";
				var result = jQuery.parseJSON(res);
				data += "<option value='0'>--Select--</option>";
				if(result.length !=0){
				$.each(result,function(key,value){ 
					data +="<option value="+value['ArcMgr_id']+">"+value['ArcMgr_name']+"</option>";
				   });
				
				}
			    $("#sarf_mgr_name").html(data);
			}
	    });//End ajax
	}//End If
	
});

function getAllDepartmentsByLocationLiti() {
	var user_orga_id = $("#sarf_orga_id").val();
	var user_loca_id = $("#sarf_loca_id").val();
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
				$("#sarf_dept_id").html(data);
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
	}
	else {
		$('#sarf_dept_id').html('<option value="0">--Select--</option>');
	}

}

//method name:Pranjali Kawale 
//****Calculate the balance Amount******
$('#sarf_paid_amount').keyup(function(){
    var loanRepayment;
    var paidAmount;
    var totalAmt;
    loanRepayment = parseFloat($('#sarf_loan_repay_amount').val());
    paidAmount = parseFloat($('#sarf_paid_amount').val());
   /* totalAmt = parseFloat($('#sarf_total_amount').val());
    if(loanRepayment>totalAmt)
    	{
    	  //alert("Loan Repayment amount is greater than total amount");
    	}*/
var balance = loanRepayment - paidAmount;
$('#sarf_balance_amount').val(balance.toFixed());
});


