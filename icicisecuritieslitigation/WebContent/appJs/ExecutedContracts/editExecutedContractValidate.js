$(document).ready(function(){
	$("#exec_contract_type_id").multiselect({
		buttonWidth: '351px',
        enableFiltering: true,
        filterBehavior: 'text',
        enableCaseInsensitiveFiltering: true,
        maxHeight: 250
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
	
});

function validateForm() {
        
		var exec_contract_entity_id = $("#exec_contract_entity_id").val();
        var exec_contract_unit_id = $('#exec_contract_unit_id').val();
        var exec_contract_function_id = $('#exec_contract_function_id').val();
        var exec_contract_type_id = $('#exec_contract_type_id').val();
        var exec_contract_description = $('#exec_contract_description').val();
        var exec_contract_internal_contact_person = $('#exec_contract_internal_contact_person').val();
        var exec_contract_contact_person = $('#exec_contract_contact_person').val();
        var exec_contract_executed_with = $('#exec_contract_executed_with').val();
        var exec_contract_executed_contact_name = $('#exec_contract_executed_contact_name').val();
        var exec_contract_date = $('#exec_contract_date_date').val();
        
        if ($("#contractType").val() == 0) {

			$("#contractType").attr("data-placement", "top");
			$("#contractType").attr("data-content", " Select Contract Type ");
			$('#contractType').popover('show');

			return false;
		}

		else {
			$('#contractType').popover('destroy');
		}
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
     		$( "#exec_contract_type_id" ).attr( "data-content", "Please select Person Name." );
     		$('#exec_contract_type_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_type_id').popover('destroy');
        }
    	
    	if (exec_contract_description == 0) {
         	 
     		$( "#exec_contract_description" ).attr( "data-placement", "top" );
     		$( "#exec_contract_description" ).attr( "data-content", "Please enter description." );
     		$('#exec_contract_description').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_description').popover('destroy');
        }
    	
    	if (exec_contract_internal_contact_person == 0) {
        	 
     		$( "#exec_contract_internal_contact_person" ).attr( "data-placement", "top" );
     		$( "#exec_contract_internal_contact_person" ).attr( "data-content", "Please enter internal contact person." );
     		$('#exec_contract_internal_contact_person').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_internal_contact_person').popover('destroy');
        }
    	if (exec_contract_contact_person == 0) {
       	 
     		$( "#exec_contract_contact_person" ).attr( "data-placement", "top" );
     		$( "#exec_contract_contact_person" ).attr( "data-content", "Please enter contact person." );
     		$('#exec_contract_contact_person').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_contact_person').popover('destroy');
        }
    	
    	if (exec_contract_executed_with == 0) {
        	 
     		$( "#exec_contract_executed_with" ).attr( "data-placement", "top" );
     		$( "#exec_contract_executed_with" ).attr( "data-content", "Please enter Name." );
     		$('#exec_contract_executed_with').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_executed_with').popover('destroy');
        }
    	
    	if (exec_contract_executed_contact_name == 0) {
       	 
     		$( "#exec_contract_executed_contact_name" ).attr( "data-placement", "top" );
     		$( "#exec_contract_executed_contact_name" ).attr( "data-content", "Please enter contact name." );
     		$('#exec_contract_executed_contact_name').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_executed_contact_name').popover('destroy');
         }

    	if (exec_contract_date == 0) {
          	 
     		$( "#exec_contract_date_date" ).attr( "data-placement", "top" );
     		$( "#exec_contract_date_date" ).attr( "data-content", "Please enter date." );
     		$('#exec_contract_date_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_contract_date_date').popover('destroy');
        }
    	
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
}

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
				+"<div class='col-sm-7'><input type='text' name='additional_parties' class='form-control' onkeypress='return blockWhiteSpaces(event);' /><i class='asterisk_input'></i>"
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