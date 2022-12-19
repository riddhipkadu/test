$(document).ready(function(){
		
});

var party_count = 1;
var total_box = $("#total_box").val();
function addPartiesInput(){
	//var a = $('input[name*="additional_parties"]').filter(function() {return !!this.value;}).length;
	//console.log("Total lenght "+ total_box);
	if(total_box !=10)
	{
		$('#div_addParties')
		.append(
				"<div class='col-md-6'> <div class='form-group' id='party_"+total_box+"'>"
				+"<label class='col-sm-4 control-label'>Party :</label>"
				+"<div class='col-sm-7'><input type='text' name='additional_parties' class='form-control' placeholder='Enter Party name' onkeypress='return blockWhiteSpaces(event);'/><i class='asterisk_input'></i>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteParty("+total_box+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div></div>");
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
//validation function for parameters
function validateForm() {
	        
	var cont_orga_id 				= $('#cont_orga_id').val();
    var cont_loca_id 				= $('#cont_loca_id').val();
    var cont_dept_id 				= $('#cont_dept_id').val();
    var cont_agreement_name 		= $('#cont_agreement_name').val();
    var cont_requested_date 		= $('#cont_requested_date').val();
    var cont_requested_by_user_id 	= $('#cont_requested_by_user_id').val();
    var cont_start_date 			= $('#cont_start_date').val();
    var cont_end_date 				= $('#cont_end_date').val();
    var cont_payment 				= $('#cont_payment').val();
    var cont_expected_date 			= $('#cont_expected_date').val();
    var cont_responsible_user_id 	= $('#cont_responsible_user_id').val();
	var cont_targetted_date 		= $("#cont_targetted_date").val();
	var cont_reminder_date 			= $("#cont_reminder_date").val();
	var contract_type       		= $("#cont_type_of_contract option:selected").length;
	//alert("Total "+contract_type)
    if (cont_orga_id == 0 ) {
   	 
 		$( "#cont_orga_id" ).attr( "data-placement", "top" );
 		$( "#cont_orga_id" ).attr( "data-content", "Please select Entity Name." );
 		$('#cont_orga_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_orga_id').popover('destroy');
    }
	
	if (cont_loca_id == 0) {
      	 
 		$( "#cont_loca_id" ).attr( "data-placement", "top" );
 		$( "#cont_loca_id" ).attr( "data-content", "Please select Unit Name." );
 		$('#cont_loca_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_loca_id').popover('destroy');
    }
	
	if (cont_dept_id == 0) {
   	 
 		$( "#cont_dept_id" ).attr( "data-placement", "top" );
 		$( "#cont_dept_id" ).attr( "data-content", "Please select Function Name." );
 		$('#cont_dept_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_dept_id').popover('destroy');
    }
	
	if (cont_agreement_name == 0) {
      	 
 		$( "#cont_agreement_name" ).attr( "data-placement", "top" );
 		$( "#cont_agreement_name" ).attr( "data-content", "Please enter agreement name." );
 		$('#cont_agreement_name').popover('show');
        return false;
    }
	 else
     {
     	$('#cont_agreement_name').popover('destroy');
    }
	
	if (cont_requested_date == 0) {
     	 
 		$( "#cont_requested_date" ).attr( "data-placement", "top" );
 		$( "#cont_requested_date" ).attr( "data-content", "Please enter requested date ." );
 		$('#cont_requested_date').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_requested_date').popover('destroy');
    }
	
	if (cont_requested_by_user_id == 0) {
	   	 
 		$( "#cont_requested_by_user_id" ).attr( "data-placement", "top" );
 		$( "#cont_requested_by_user_id" ).attr( "data-content", "Please select requested by person name." );
 		$('#cont_requested_by_user_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_requested_by_user_id').popover('destroy');
    }
	
	if (cont_start_date == 0) {
	   	 
 		$( "#cont_start_date" ).attr( "data-placement", "top" );
 		$( "#cont_start_date" ).attr( "data-content", "Please select start date." );
 		$('#cont_start_date').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_start_date').popover('destroy');
    }
	
	if (cont_end_date == 0) {
	   	 
 		$( "#cont_end_date" ).attr( "data-placement", "top" );
 		$( "#cont_end_date" ).attr( "data-content", "Please select end date." );
 		$('#cont_end_date').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_end_date').popover('destroy');
    }
	
	var status = "true";
	//alert(party_added);
	var values  =  $('input[name="additional_parties"]').map(function(){
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
		
		if(contract_type == 0) {
			$( "#cont_type_of_contract" ).attr( "data-placement", "top" );
	 		$( "#cont_type_of_contract" ).attr( "data-content", "Please select document type." );
	 		$('#cont_type_of_contract').popover('show');

	        return false;
	    }
		 else
	     {
	     	$('#cont_type_of_contract').popover('destroy');
	    } 
	
	if (cont_payment == 0) {
   	 
 		$( "#cont_payment" ).attr( "data-placement", "top" );
 		$( "#cont_payment" ).attr( "data-content", "Please Enter payment." );
 		$('#cont_payment').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_payment').popover('destroy');
    }
	if (cont_expected_date == 0) {
	   	 
 		$( "#cont_expected_date" ).attr( "data-placement", "top" );
 		$( "#cont_expected_date" ).attr( "data-content", "Please enter expected date." );
 		$('#cont_expected_date').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_expected_date').popover('destroy');
    }
	
	if (cont_responsible_user_id == 0) {
   	 
 		$( "#cont_responsible_user_id" ).attr( "data-placement", "top" );
 		$( "#cont_responsible_user_id" ).attr( "data-content", "Please select responsible person." );
 		$('#cont_responsible_user_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_responsible_user_id').popover('destroy');
    }
	if (cont_targetted_date == 0) {
	   	 
 		$( "#cont_targetted_date" ).attr( "data-placement", "top" );
 		$( "#cont_targetted_date" ).attr( "data-content", "Please enter targeted date." );
 		$('#cont_targetted_date').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_targetted_date').popover('destroy');
    }
	
	if (cont_reminder_date == 0) {
	   	 
 		$( "#cont_reminder_date" ).attr( "data-placement", "top" );
 		$( "#cont_reminder_date" ).attr( "data-content", "Please enter reminder date." );
 		$('#cont_reminder_date').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_reminder_date').popover('destroy');
    }
}

function validateDraft(){

	var cont_orga_id = $('#cont_orga_id').val();
    var cont_loca_id = $('#cont_loca_id').val();
    var cont_dept_id = $('#cont_dept_id').val();
    var cont_agreement_name = $('#cont_agreement_name').val();
    var cont_requested_date = $('#cont_requested_date').val();
    var cont_requested_by_user_id = $('#cont_requested_by_user_id').val();
    var cont_purpose = $('#cont_purpose').val();
    var cont_expected_date = $('#cont_expected_date').val();
    var cont_responsible_user_id = $('#cont_responsible_user_id').val();
	var cont_targetted_date = $("#cont_targetted_date").val();
	
    if (cont_orga_id == 0 ) {
   	 
 		$( "#cont_orga_id" ).attr( "data-placement", "top" );
 		$( "#cont_orga_id" ).attr( "data-content", "Please select Entity Name." );
 		$('#cont_orga_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_orga_id').popover('destroy');
    }
	
	if (cont_loca_id == 0) {
      	 
 		$( "#cont_loca_id" ).attr( "data-placement", "top" );
 		$( "#cont_loca_id" ).attr( "data-content", "Please select Unit Name." );
 		$('#cont_loca_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_loca_id').popover('destroy');
    }
	
	if (cont_dept_id == 0) {
   	 
 		$( "#cont_dept_id" ).attr( "data-placement", "top" );
 		$( "#cont_dept_id" ).attr( "data-content", "Please select Function Name." );
 		$('#cont_dept_id').popover('show');

        return false;
    }
	 else
     {
     	$('#cont_dept_id').popover('destroy');
    }
	
	if (cont_agreement_name == 0) {
      	 
 		$( "#cont_agreement_name" ).attr( "data-placement", "top" );
 		$( "#cont_agreement_name" ).attr( "data-content", "Please enter agreement name." );
 		$('#cont_agreement_name').popover('show');
        return false;
    }
	 else
     {
     	$('#cont_agreement_name').popover('destroy');
    }
	
	var status = "true";
	//alert(party_added);
	var values = $('input[name="additional_parties"]').map(function(){
		if (this.value.length == null || this.value.length == "0" || this.value.length == 0){
			$('input[name="additional_parties"]').attr( "data-placement", "top" );
			$('input[name="additional_parties"]').attr( "data-content", "Please enter the party." );
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
		
}