$(document).ready(function(){
	$("#exec_contract_type_id").multiselect({
		buttonWidth: '351px',
        enableFiltering: true,
        filterBehavior: 'text',
        enableCaseInsensitiveFiltering: true,
        maxHeight: 250
	
	});
	
	/* $("#exec_contract_type_id").multiselect({multiple: false, selectedList: 1});
	   $('.ui-multiselect').click(function(){

	      $('.ui-multiselect-checkboxes').animate({
	         scrollTop: $(".ui-multiselect-checkboxes .ui-state-active").offset().top
	      }, 2000);
	   });    
*/
	
	/* $('.ui-multiselect').click(function(){

	      $('.ui-multiselect-checkboxes').animate({
	         scrollTop: $(".ui-multiselect-checkboxes .ui-state-active").offset().top
	      }, 2000);
	   }); */ 
	
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
	$('#remind_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	$('#second_remind_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
	$('#third_remind_alert_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
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
			$('#remind_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
			$('#second_remind_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
			$('#third_remind_alert_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
		});
		
	
		$("#execution_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#execution_date_div').datepicker({ autoclose: true}).datepicker('', e.date).focus();
		});
		
		$("#renewal_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#renewal_date_div').datepicker({ autoclose: true}).datepicker('', e.date).focus();
		
		});
		
		
		$("#remind_alert_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker()

		$("#to_date_div").on("change", function(e){

				 var date2 = $('#to_date_div').datepicker('getDate'); 
				  date2.setDate(date2.getDate()-30); 
				  $('#remind_alert_div').datepicker('setDate', date2);
			
		
});
		
		$("#second_remind_alert_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker()

		$("#to_date_div").on("change", function(e){

				 var date2 = $('#to_date_div').datepicker('getDate'); 
				  date2.setDate(date2.getDate()-15); 
				  $('#second_remind_alert_div').datepicker('setDate', date2);
			
		
});
		
		$("#third_remind_alert_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker()

		$("#to_date_div").on("change", function(e){

				 var date2 = $('#to_date_div').datepicker('getDate'); 
				  date2.setDate(date2.getDate()-5); 
				  $('#third_remind_alert_div').datepicker('setDate', date2);
			
		
});
});
var party_count = 1;
var total_box = $("#total_box").val();
function addPartiesInput(){
	//var a = $('input[name*="additional_parties"]').filter(function() {return !!this.value;}).length;
	console.log("Total lenght "+ total_box);
	if(total_box !=10)
	{
		$('#div_addParties')
		.append(
				"<div class='col-md-6'><div class='form-group row' id='party_"+total_box+"'>"
				+"<label class='col-sm-4 control-label'>Party :</label>"
				+"<div class='col-sm-7'><input type='text' name='additional_parties' class='form-control' onkeypress='return blockWhiteSpaces(event);'/>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteParty("+total_box+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div>");
		party_count++;
		total_box++;
	}else{
		$("#add_party_div").hide();
	}
	
}
//addPartiesInput(); //Add default party on page load
function deleteParty(party_count) {
	$("#party_" + party_count).remove();
	total_box--;
	$("#total_box").val(total_box);
	if(total_box <=10)
	{ 
		$("#add_party_div").show();
	}
}
function validateForm() {
    
	 var exec_contract_entity_id = $("#exec_contract_entity_id").val();
	 var exec_contract_unit_id = $('#exec_contract_unit_id').val();
	 var exec_contract_function_id = $('#exec_contract_function_id').val();
	 var exec_contract_type_id = $('#exec_contract_type_id').val();
	 var exec_contract_start_date = $('#exec_contract_start_date').val();
	 var exec_contract_end_date = $('#exec_contract_end_date').val();
	 var exec_contract_execution_date = $('#exec_contract_execution_date').val();
	 var exec_remind_date = $('#exec_remind_date').val(); 
	// var exec_contract_renewal_date = $('#exec_contract_renewal_date').val(); 
	 var exec_remind_date_second = $('#exec_remind_date_second').val(); 
	// var exec_remind_date_third = $('#exec_remind_date_third').val();        
	 var exec_contract_insurance = $('#exec_contract_insurance').val(); 
	// var exec_contract_arbitration = $('#exec_contract_arbitration').val();
	 var exec_contract_notice_period = $('#exec_contract_notice_period').val(); 
	 var exec_contract_termination_clause = $('#exec_contract_termination_clause').val();
	 var exec_contract_payment = $('#exec_contract_payment').val();
	 var exec_contract_consider_involve = $('#exec_contract_consider_involve').val();
	 var exec_contract_signatories_assign = $('#exec_contract_signatories_assign').val();
	 var exec_contract_damages = $('#exec_contract_damages').val();
	 var exec_contract_description = $('#exec_contract_description').val();
	 var exec_contract_agree_status = $('#exec_contract_agree_status').val();
	// var exec_contract_renewal_status = $('#exec_contract_renewal_status').val();
	 var exec_contract_resposible_person = $('#exec_contract_resposible_person').val();
	/* var exec_contract_executed_contact_name = $('#exec_contract_executed_contact_name').val();*/
	 var exec_contract_date = $('#exec_contract_date').val();
	/* var exec_contract_cont_person_email = $('#exec_contract_cont_person_email').val();*/
	 var exec_force_majeure = $('#exec_force_majeure').val();
	 
	if (exec_contract_entity_id == 0 ) {
      	 
 		$( "#exec_contract_entity_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_entity_id" ).attr( "data-content", "Please select Entity Name." );
 		$('#exec_contract_entity_id').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_entity_id').popover('destroy');
    }
	
	
	if (exec_contract_unit_id == 0) {
     	 
 		$( "#exec_contract_unit_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_unit_id" ).attr( "data-content", "Please select Unit Name." );
 		$('#exec_contract_unit_id').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_unit_id').popover('destroy');
    }
	
	if (exec_contract_function_id == 0) {
      	 
 		$( "#exec_contract_function_id" ).attr( "data-placement", "right" );
 		$( "#exec_contract_function_id" ).attr( "data-content", "Please select Function Name." );
 		$('#exec_contract_function_id').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_function_id').popover('destroy');
    }
	
	if (exec_contract_type_id == 0) {
     	 
 		$( "#exec_contract_type_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_type_id" ).attr( "data-content", "Please Select Contract Type." );
 		//$('#exec_contract_type_id').popover('show');
 		$('[data-toggle="popover"]').popover('show'); 
 		$("#exec_contract_type_id").focus();
        return false;
    }
	 else
     {
     	$('#exec_contract_type_id').popover('destroy');
    }
	
	/*if (exec_contract_start_date == 0) {
 	   	 
 		$( "#exec_contract_start_date" ).attr( "data-placement", "top" );
 		$( "#exec_contract_start_date" ).attr( "data-content", "Please select start date." );
 		$('#exec_contract_start_date').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_start_date').popover('destroy');
    }*/
	
	/*if (exec_contract_end_date == 0) {
 	   	 
 		$( "#exec_contract_end_date" ).attr( "data-placement", "top" );
 		$( "#exec_contract_end_date" ).attr( "data-content", "Please select end date." );
 		$('#exec_contract_end_date').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_end_date').popover('destroy');
    }*/
	/*if (exec_contract_execution_date == 0) {
	   	 
 		$( "#exec_contract_execution_date" ).attr( "data-placement", "top" );
 		$( "#exec_contract_execution_date" ).attr( "data-content", "Please select Execution date." );
 		$('#exec_contract_execution_date').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_execution_date').popover('destroy');
    }*/
	
	if (exec_remind_date == 0) {
	   	 
 		$( "#exec_remind_date" ).attr( "data-placement", "top" );
 		$( "#exec_remind_date" ).attr( "data-content", "Please Select first Reminder Date." );
 		$('#exec_remind_date').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_remind_date').popover('destroy');
    }
	if (exec_remind_date_second == 0) {
	   	 
 		$( "#exec_remind_date_second" ).attr( "data-placement", "top" );
 		$( "#exec_remind_date_second" ).attr( "data-content", "Please Select Second Reminder Date." );
 		$('#exec_remind_date_second').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_remind_date_second').popover('destroy');
    }
	/*if (exec_remind_date_third == 0) {
	   	 
 		$( "#exec_remind_date_third" ).attr( "data-placement", "top" );
 		$( "#exec_remind_date_third" ).attr( "data-content", "Please Select Third Reminder Date." );
 		$('#exec_remind_date_third').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_remind_date_third').popover('destroy');
    }*/
	/*if (exec_contract_renewal_date == 0) {
	   	 
 		$( "#exec_contract_renewal_date" ).attr( "data-placement", "top" );
 		$( "#exec_contract_renewal_date" ).attr( "data-content", "Please select renewal date." );
 		$('#exec_contract_renewal_date').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_renewal_date').popover('destroy');
    }
	*/
	 var status = "true";
	 	//alert(party_added);
	 	var values = $('input[name="additional_parties"]').map(function(){
	 		if (this.value.length == null || this.value.length == "0" || this.value.length == 0){
	 			$('input[name="additional_parties"]').attr( "data-placement", "top" );
	 			$('input[name="additional_parties"]').attr( "data-content", "Please enter the party name." );
	 			$('input[name="additional_parties"]').popover('show');
	 		    //return false;
	 			status = "false";
	 		}else{
	 			$(this).popover('destroy');
	 		}
	 		});
	 		if(status =="false"){
	 			//console.log("In fasle");
	 			return false;
	 			
	 		}else{
	 			$(this).popover('destroy');
	 		}
	 	
	 	var count = 0;
			do{
				if(! $('#party_div_'+(count+1)+'').is(':hidden')) { 
				var party_name = $('input[name="exec_parties['+count+'].cont_party_name"]').val();
					if(party_name != undefined){
						if(party_name.length == 0 ){
				$('input[name="exec_parties['+count+'].cont_party_name"]').attr( "data-placement", "top" );
				$('input[name="exec_parties['+count+'].cont_party_name"]').attr( "data-content", "Please enter the party name." );
				$('input[name="exec_parties['+count+'].cont_party_name"]').popover('show');
				
			    return false;
						}else{
							$(this).popover('destroy');
						}
					}
				}
				count++;
			}while( count < 10);
	/*if (exec_contract_insurance == 0) {
	   	 
 		$( "#exec_contract_insurance" ).attr( "data-placement", "top" );
 		$( "#exec_contract_insurance" ).attr( "data-content", "Please select Insurance ." );
 		$('#exec_contract_insurance').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_insurance').popover('destroy');
    }*/
	
	/*if (exec_contract_arbitration == 0) {
 	   	 
 		$( "#exec_contract_arbitration" ).attr( "data-placement", "top" );
 		$( "#exec_contract_arbitration" ).attr( "data-content", "Please select Seat of arbitration." );
 		$('#exec_contract_arbitration').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_arbitration').popover('destroy');
    }*/
	/*if (exec_contract_notice_period == 0) {
	   	 
 		$( "#exec_contract_notice_period" ).attr( "data-placement", "top" );
 		$( "#exec_contract_notice_period" ).attr( "data-content", "Please enter Notice Period (In months) ." );
 		$('#exec_contract_notice_period').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_notice_period').popover('destroy');
    }*/
	/*if (exec_contract_termination_clause == 0) {
	   	 
  		$( "#exec_contract_termination_clause" ).attr( "data-placement", "top" );
  		$( "#exec_contract_termination_clause" ).attr( "data-content", "Please enter Termination Clause." );
  		$('#exec_contract_termination_clause').popover('show');

         return false;
     }
 	 else
      {
      	$('#exec_contract_termination_clause').popover('destroy');
     }*/

	/*if (exec_contract_payment == 0) {
	   	 
  		$( "#exec_contract_payment" ).attr( "data-placement", "top" );
  		$( "#exec_contract_payment" ).attr( "data-content", "Please enter Payment Clause." );
  		$('#exec_contract_payment').popover('show');

         return false;
     }
 	 else
      {
      	$('#exec_contract_payment').popover('destroy');
     }*/

	/* if (exec_contract_consider_involve == 0) {
	   	 
  		$( "#exec_contract_consider_involve" ).attr( "data-placement", "top" );
  		$( "#exec_contract_consider_involve" ).attr( "data-content", "Please enter Consideration Involved." );
  		$('#exec_contract_consider_involve').popover('show');

         return false;
     }
 	 else
      {
      	$('#exec_contract_consider_involve').popover('destroy');
     }*/
	/* if (exec_contract_signatories_assign == 0) {
 	   	 
 		$( "#exec_contract_signatories_assign" ).attr( "data-placement", "top" );
 		$( "#exec_contract_signatories_assign" ).attr( "data-content", "Please enter Signotories Assign." );
 		$('#exec_contract_signatories_assign').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_signatories_assign').popover('destroy');
    }*/
	/* if (exec_contract_damages == 0) {
 	   	 
	 		$( "#exec_contract_damages" ).attr( "data-placement", "top" );
	 		$( "#exec_contract_damages" ).attr( "data-content", "Please enter Indemnity Clause." );
	 		$('#exec_contract_damages').popover('show');

	        return false;
	    }
		 else
	     {
	     	$('#exec_contract_damages').popover('destroy');
	    }*/
	 
	/* if (exec_contract_description == 0) {
     	 
  		$( "#exec_contract_description" ).attr( "data-placement", "top" );
  		$( "#exec_contract_description" ).attr( "data-content", "Please enter Purpose of the Agreement." );
  		$('#exec_contract_description').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#exec_contract_description').popover('destroy');
     }*/
		
	/* if (exec_contract_renewal_status == 0) {
 	   	 
  		$( "#exec_contract_renewal_status" ).attr( "data-placement", "top" );
  		$( "#exec_contract_renewal_status" ).attr( "data-content", "Please select renewal Status." );
  		$('#exec_contract_renewal_status').popover('show');

         return false;
     }
 	 else
      {
      	$('#exec_contract_renewal_status').popover('destroy');
     }
*/	 
	
	 
	
	 
	 
	/* if (exec_contract_executed_contact_name == 0) {
       	 
  		$( "#exec_contract_executed_contact_name" ).attr( "data-placement", "top" );
  		$( "#exec_contract_executed_contact_name" ).attr( "data-content", "Please enter opposite party's contact name." );
  		$('#exec_contract_executed_contact_name').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#exec_contract_executed_contact_name').popover('destroy');
      }*/
	/* if (exec_force_majeure == 0) {
 	   	 
	 		$( "#exec_force_majeure" ).attr( "data-placement", "top" );
	 		$( "#exec_force_majeure" ).attr( "data-content", "Please enter Force Majeure." );
	 		$('#exec_force_majeure').popover('show');

	        return false;
	    }
		 else
	     {
	     	$('#exec_force_majeure').popover('destroy');
	    }*/
	/* if (exec_contract_date == 0) {
     	 
  		$( "#exec_contract_date" ).attr( "data-placement", "top" );
  		$( "#exec_contract_date" ).attr( "data-content", "Please enter Contract date." );
  		$('#exec_contract_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#exec_contract_date').popover('destroy');
     }*/
	 
	 if (exec_contract_resposible_person == 0) {
       	 
	  		$( "#exec_contract_resposible_person" ).attr( "data-placement", "top" );
	  		$( "#exec_contract_resposible_person" ).attr( "data-content", "Please select Responsible Person." );
	  		$('#exec_contract_resposible_person').popover('show');
	  		$("#exec_contract_resposible_person").focus();
	         return false;
	     } 
	 	else {
	      	$('#exec_contract_resposible_person').popover('destroy');
	     }
	/* if (exec_contract_agree_status == 0) {
 	   	 
	 		$( "#exec_contract_agree_status" ).attr( "data-placement", "top" );
	 		$( "#exec_contract_agree_status" ).attr( "data-content", "Please select Agreement Status." );
	 		$('#exec_contract_agree_status').popover('show');

	        return false;
	    }
		 else
	     {
	     	$('#exec_contract_agree_status').popover('destroy');
	    }*/

	/* if (exec_contract_cont_person_email === null || exec_contract_cont_person_email === "") {
         $("#exec_contract_cont_person_email").attr("data-placement", "top");
         $("#exec_contract_cont_person_email").attr("data-content", "Email must be filled out");
         $( "#exec_contract_cont_person_email" ).focus();
         $('#exec_contract_cont_person_email').popover('show');

         return false;
     }
	 else
     {
         $('#exec_contract_cont_person_email').popover('destroy');
     }*/
	/// validate email format
     /*var atpos = exec_contract_cont_person_email.indexOf("@");
     var dotpos = exec_contract_cont_person_email.lastIndexOf(".");
     if(exec_contract_cont_person_email != null){
     if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= exec_contract_cont_person_email.length)
     {
         $("#exec_contract_cont_person_email").attr("data-placement", "top");
         $("#exec_contract_cont_person_email").attr("data-content", "Not a valid e-mail address");
         $( "#exec_contract_cont_person_email" ).focus();
         $('#exec_contract_cont_person_email').popover('show');
         // alert("Not a valid e-mail address");
         return false;
     } else
     {
         $('#exec_contract_cont_person_email').popover('destroy');
     } 
     }*/
	 
	 return true;
}
	
	
	
	
	
	
	
	
	
	
	
	





function validateDraft(){

	var exec_contract_entity_id = $('#exec_contract_entity_id').val();
    var exec_contract_unit_id = $('#exec_contract_unit_id').val();
    var exec_contract_function_id = $('#exec_contract_function_id').val();
    var exec_contract_type_id = $('#exec_contract_type_id').val();
  //  var cont_requested_date = $('#cont_requested_date').val();
  //  var cont_requested_by_user_id = $('#cont_requested_by_user_id').val();
  //  var cont_purpose = $('#cont_purpose').val();
   // var cont_expected_date = $('#cont_expected_date').val();
   // var cont_responsible_user_id = $('#cont_responsible_user_id').val();
	//var cont_targetted_date = $("#cont_targetted_date").val();
	
    if (exec_contract_entity_id == 0 ) {
   	 
 		$( "#exec_contract_entity_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_entity_id" ).attr( "data-content", "Please select Entity Name." );
 		$('#exec_contract_entity_id').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_entity_id').popover('destroy');
    }
	
	if (exec_contract_unit_id == 0) {
      	 
 		$( "#exec_contract_unit_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_unit_id" ).attr( "data-content", "Please select Unit Name." );
 		$('#exec_contract_unit_id').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_unit_id').popover('destroy');
    }
	
	if (exec_contract_function_id == 0) {
   	 
 		$( "#exec_contract_function_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_function_id" ).attr( "data-content", "Please select Function Name." );
 		$('#exec_contract_function_id').popover('show');

        return false;
    }
	 else
     {
     	$('#exec_contract_function_id').popover('destroy');
    }
	
	if (exec_contract_type_id == 0) {
      	 
 		$( "#exec_contract_type_id" ).attr( "data-placement", "top" );
 		$( "#exec_contract_type_id" ).attr( "data-content", "Please enter agreement name." );
 		$('#exec_contract_type_id').popover('show');
        return false;
    }
	 else
     {
     	$('#exec_contract_type_id').popover('destroy');
    }
	
	return true;
}