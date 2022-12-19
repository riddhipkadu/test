$(document).ready(function(){

	$("#chst_status_others_div").hide();
	
	var chst_status = $("#chst_status").val();
	if(chst_status == 'Others'){
		$("#chst_status_others_div").show();
	}else{
		$("#chst_status_others").val("NA");
		$("#chst_status_others_div").hide();
	}
	
	$("#chst_action_performed_div").hide();
	var chst_action_performed = $("#chst_action_performed").val();
	if(chst_action_performed == 'Others'){
		$("#chst_action_performed_div").show();
	}else{
		$("#chst_action_performed_others").val("NA");
		$("#chst_action_performed_div").hide();
	}
	
	$("#chst_performed_by_others_div").hide();
	var chst_performed_by = $("#chst_performed_by").val();
	if(chst_performed_by == 'Others'){
		$("#chst_performed_by_others_div").show();
	}else{
		$("#chst_performed_by_others").val("NA");
		$("#chst_performed_by_others_div").hide();
	}

	$("#chst_assigned_others_div").hide();
	var chst_assigned_to = $("#chst_assigned_to").val();
	if(chst_assigned_to == '-1'){
		$("#chst_assigned_others_div").show();
	}else{
		$("#chst_assign_others").val("NA");
		$("#chst_assigned_others_div").hide();
	}
	
	$("#action_poc_user_div").hide();
	$("#chst_status").change(function(){ 
	var chst_status = $("#chst_status").val();
	if(chst_status == 'SentToPOCforNegotiation'){
		$("#action_poc_user_div").show();
		addFileInputMandatory();
	}else{
		//$("#chst_poc_user_id").val("0");
		$("#action_poc_user_div").hide();
	}
	});
	
	var chst_status = $("#chst_status").val();
	if(chst_status == 'SentToPOCforNegotiation'){
		$("#action_poc_user_div").show();
	}else{
		//$("#chst_poc_user_id").val("0");
		$("#action_poc_user_div").hide();
	}
	
	$("#chst_status").change(function(){ 
		var chst_status = $("#chst_status").val();
		if(chst_status == 'Others'){
			$("#chst_status_others_div").show();
		}else{
			$("#chst_status_others").val("NA");
			$("#chst_status_others_div").hide();
		}
	});
	
	$("#chst_action_performed").change(function(){ 
		var chst_action_performed = $("#chst_action_performed").val();
		if(chst_action_performed == 'Others'){
			$("#chst_action_performed_div").show();
		}else{
			$("#chst_action_performed_others").val("NA");
			$("#chst_action_performed_div").hide();
		}
	});
	
	$("#chst_performed_by").change(function(){ 
		var chst_performed_by = $("#chst_performed_by").val();
		if(chst_performed_by == 'Others'){
			$("#chst_performed_by_others_div").show();
		}else{
			$("#chst_performed_by_others").val("NA");
			$("#chst_performed_by_others_div").hide();
		}
	});
	
	$("#chst_assigned_to").change(function(){ 
		var chst_assigned_to = $("#chst_assigned_to").val();
		if(chst_assigned_to == '-1'){
			$("#chst_assigned_others_div").show();
		}else{
			$("#chst_assign_others").val("NA");
			$("#chst_assigned_others_div").hide();
		}
	});
	
	$("#replied_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		endDate: new Date()
	}).datepicker();
});

$(document).on("click","button[name='delete_pre_contract_history']",function(){
	
	var chst_id = $(this).val();
	items = {};
	items["chst_id"] = chst_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(chst_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deletePreExecutedContractHistory",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Contract history deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+chst_id).remove();
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

$(document).on("click","button[name='delete_contract_doc']",function(){
	
	var cdoc_id = $(this).val();
	items = {};
	items["cdoc_id"] = cdoc_id;
	//alert("contract id is: "+cont_id);
	var jsonString = JSON.stringify(items);
	
		if(cdoc_id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deletePreExecutedContractDocument",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Contract Document deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+cdoc_id).remove();
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


function deleteContractHistoryDoc(chst_doc_id){
	var doc_id = chst_doc_id;
	items = {};
	items["doc_id"] = doc_id;
	var jsonString = JSON.stringify(items);
	
		if(doc_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteContractHistoryDoc",
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
				    				$("#doc_attached_"+doc_id).remove();
				    			}
				    			
				    		}
					 });
				 }
			 });
		}
}

function validateForm(){
	
	var chst_status = $("#chst_status").val();
	var chst_status_others = $("#chst_status_others").val();
	var chst_action_performed = $("#chst_action_performed").val();
	var chst_action_performed_others = $("#chst_action_performed_others").val();
	/*var chst_performed_by = $("#chst_performed_by").val();
	var chst_performed_by_others = $("#chst_performed_by_others").val();*/
	var chst_assigned_to = $("#chst_assigned_to").val();
	var chst_assign_others = $("#chst_assign_others").val();
	//var chst_comments = $("#chst_comments").val();
	var chst_poc_user_id = $('#chst_poc_user_id').val();
	var poc_doc = document.getElementById('poc_doc');
	
	//******************************** validation for contract History ***************************************
	
	if (chst_status == "NA") {
	   	 
 		$( "#chst_status" ).attr( "data-placement", "top" );
 		$( "#chst_status" ).attr( "data-content", "Please select status." );
 		$('#chst_status').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_status').popover('destroy');
    }
	if (chst_status =="Save As Draft") {
	   	 
 		$( "#chst_status" ).attr( "data-placement", "top" );
 		$( "#chst_status" ).attr( "data-content", "Please select status other than 'Save As Draft'." );
 		$('#chst_status').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_status').popover('destroy');
    }
	if(chst_status =='Others'){
	if (chst_status_others == 0 || chst_status_others == "NA") {
	   	 
 		$( "#chst_status_others" ).attr( "data-placement", "top" );
 		$( "#chst_status_others" ).attr( "data-content", "Please enter others." );
 		$('#chst_status_others').popover('show');

        return false;
    }else
    {
     	$('#chst_status_others').popover('destroy');
    }
	}
	 else
     {
     	$('#chst_status_others').popover('destroy');
    }
	if (chst_action_performed == "NA") {
	   	 
 		$( "#chst_action_performed" ).attr( "data-placement", "top" );
 		$( "#chst_action_performed" ).attr( "data-content", "Please enter action to be performed." );
 		$('#chst_action_performed').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_action_performed').popover('destroy');
    }
	if(chst_action_performed =='Others'){
	if (chst_action_performed_others == "NA" || chst_action_performed_others == 0) {
	   	 
 		$( "#chst_action_performed_others" ).attr( "data-placement", "top" );
 		$( "#chst_action_performed_others" ).attr( "data-content", "Please enter others." );
 		$('#chst_action_performed_others').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_action_performed_others').popover('destroy');
    }
	}
	 else
     {
     	$('#chst_action_performed_others').popover('destroy');
    }
	/*if (chst_performed_by == 0) {
	   	 
 		$( "#chst_performed_by" ).attr( "data-placement", "top" );
 		$( "#chst_performed_by" ).attr( "data-content", "Please enter performed by." );
 		$('#chst_performed_by').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_performed_by').popover('destroy');
    }
	if(chst_performed_by == 'Others'){
	if (chst_performed_by_others == 0 || chst_performed_by_others == "NA") {
	   	 
 		$( "#chst_performed_by_others" ).attr( "data-placement", "top" );
 		$( "#chst_performed_by_others" ).attr( "data-content", "Please enter others." );
 		$('#chst_performed_by_others').popover('show');

        return false;
    } else{
     	$('#chst_performed_by_others').popover('destroy');
    }
	
	}
	 else
     {
     	$('#chst_performed_by_others').popover('destroy');
    }*/
	if (chst_assigned_to == 0) {
	   	 
 		$( "#chst_assigned_to" ).attr( "data-placement", "top" );
 		$( "#chst_assigned_to" ).attr( "data-content", "Please enter assigned to person." );
 		$('#chst_assigned_to').popover('show');

        return false;
    } else
     {
     	$('#chst_assigned_to').popover('destroy');
    }
	if (chst_assigned_to == '-1') {
		if (chst_assign_others == 0 ) {
		   	 
	 		$( "#chst_assign_others" ).attr( "data-placement", "top" );
	 		$( "#chst_assign_others" ).attr( "data-content", "Please enter others." );
	 		$('#chst_assign_others').popover('show');

	        return false;
	    } else {
	     	$('#chst_assign_others').popover('destroy');
	    }
 		}else {
 			$('#chst_assign_others').popover('destroy');
 		}
	
	/*if (chst_comments == 0) {
	   	 
 		$( "#chst_comments" ).attr( "data-placement", "top" );
 		$( "#chst_comments" ).attr( "data-content", "Please enter comments." );
 		$('#chst_comments').popover('show');

        return false;
    } else
     {
     	$('#chst_comments').popover('destroy');
    }*/
	
	if(chst_status =='SentToPOCforNegotiation'){
		if (chst_poc_user_id == 0 ) {
		   	 
	 		$("#chst_poc_user_id" ).attr( "data-placement", "top" );
	 		$("#chst_poc_user_id" ).attr( "data-content", "Please enter POC user.");
	 		$('#chst_poc_user_id').popover('show');

	        return false;
	    }else {
	     	$('#chst_poc_user_id').popover('destroy');
	    }
		    if(poc_doc.files.length === 0){

		    	$("#poc_doc" ).attr( "data-placement", "top" );
		 		$("#poc_doc" ).attr( "data-content", "Please upload document of negotiation");
		 		$('#poc_doc').popover('show');
		        
		        return false;
		    }else {
		     	$('#poc_doc').popover('destroy');
		    }
		
		} else {
	     	$('#chst_poc_user_id').popover('destroy');
	    }
}

function validateDraft(){
	
	var chst_status = $("#chst_status").val();
	var chst_status_others = $("#chst_status_others").val();
	var chst_action_performed = $("#chst_action_performed").val();
	var chst_action_performed_others = $("#chst_action_performed_others").val();
	var chst_performed_by = $("#chst_performed_by").val();
	var chst_performed_by_others = $("#chst_performed_by_others").val();
	var chst_assigned_to = $("#chst_assigned_to").val();
	var chst_assign_others = $("#chst_assign_others").val();
	var chst_comments = $("#chst_comments").val();

	//******************************** validation for contract History ***************************************
	
	if (chst_status == "NA" || chst_status !="Save As Draft") {
	   	 
 		$( "#chst_status" ).attr( "data-placement", "top" );
 		$( "#chst_status" ).attr( "data-content", "Please select 'Save As Draft'." );
 		$('#chst_status').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_status').popover('destroy');
    }
	
	
	if(chst_action_performed =='Others'){
	if (chst_action_performed_others == "NA" || chst_action_performed_others == 0) {
	   	 
 		$( "#chst_action_performed_others" ).attr( "data-placement", "top" );
 		$( "#chst_action_performed_others" ).attr( "data-content", "Please enter others." );
 		$('#chst_action_performed_others').popover('show');

        return false;
    }
	 else
     {
     	$('#chst_action_performed_others').popover('destroy');
    }
	}
	 else
     {
     	$('#chst_action_performed_others').popover('destroy');
    }
	
	/*if(chst_performed_by == 'Others'){
	if (chst_performed_by_others == 0 || chst_performed_by_others == "NA") {
	   	 
 		$( "#chst_performed_by_others" ).attr( "data-placement", "top" );
 		$( "#chst_performed_by_others" ).attr( "data-content", "Please enter others." );
 		$('#chst_performed_by_others').popover('show');

        return false;
    } else{
     	$('#chst_performed_by_others').popover('destroy');
    }
	
	}
	 else
     {
     	$('#chst_performed_by_others').popover('destroy');
    }*/
	
	if (chst_assigned_to == '-1') {
		if (chst_assign_others == 0 ) {
		   	 
	 		$( "#chst_assign_others" ).attr( "data-placement", "top" );
	 		$( "#chst_assign_others" ).attr( "data-content", "Please enter others." );
	 		$('#chst_assign_others').popover('show');

	        return false;
	    } else{
	     	$('#chst_assign_others').popover('destroy');
	    }
	   	 
 		}else
 		{
 			$('#chst_assign_others').popover('destroy');
 		}
	
}
