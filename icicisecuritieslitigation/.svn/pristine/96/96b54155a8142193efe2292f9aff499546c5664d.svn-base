$(document).ready(function(){
	
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

/*function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}*/

function blockSpecialChar(e){
	
    var k;
    document.all ? k = e.keyCode : k = e.which;
   // console.log("Key code "+k)
    return (k == 8 || k == 46 || (k >= 48 && k <= 57) && k > 32);
    }


function searchAction(id){
	var exec_id = id;
	var exec_frequency = $("#exec_frequency").val();
	var exec_from_due_date = $("#exec_from_due_date").val();
	var exec_to_due_date = $("#exec_to_due_date").val();

		if(exec_frequency != 0 ){
			$("#btnSearch").hide();
			$("#loader").show();
			
			items = {};
			items["exec_id"]         	  		  = exec_id;
			items["exec_frequency"]         	  = exec_frequency;
			items["exec_from_due_date"] 		  = exec_from_due_date;
			items["exec_to_due_date"] 		  	  = exec_to_due_date;

			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchActionItem",
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
						
						var exec_action_id = value['exec_action_id'];
						data += "<tr id ='row_"+exec_action_id+"'>";
						data +='<td><a href="./actionItemHistory?action_id='+exec_action_id+'"><center>'+i+"</center></a></td>";
						data +="<td>"+value['exec_action_item']+" </td>";
						data +="<td>"+value['exec_responsible_user']+" </td>";
						data +="<td>"+value['exec_frequency']+" </td>";
						data +="<td>"+value['exec_due_date']+" </td>";
						data +="<td>"+value['exec_first_alert_prior_days']+ "</td>";
						data +="<td>"+value['exec_second_alert_prior_days']+ "</td>";
						data +="<td>";
						if(value['user_role'] == 1 || value['added_by'] == value['user_id'] ){
						data += '<a href="editActionItem?exec_id='+exec_action_id+'">';
						data += '<button type="button" onclick="" name="submit" value="submit" class="btn btn-primary btn-block" style="margin-bottom: 4px;">';
						data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Edit ';
						data += '</button></a>';
						}else{
						data += 'No Access';	
						}
						data +="</td></tr>";
						i++;
					});				
					}else{
						data += "<tr><td colspan=8 style='text-align:center'>No result found..!!</td></tr> " ;
					}
					$("#searchResult").html(data); 
					//$("label[for='totaltask']").html($('#example >tbody >tr').length);	
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#exec_frequency").attr("data-placement", "top");
			$("#exec_frequency").attr("data-content", "Choose Frequency Field..!!");
			$('#exec_frequency').popover('show');
			return false;
		}
}

function validateForm() {
        
		var exec_action_item 			= $("#exec_action_item").val();
        var exec_responsible_user_id 	= $('#exec_responsible_user_id').val();
        var exec_frequency 				= $('#exec_frequency').val();
        var exec_due_date 				= $('#exec_due_date').val();
        var exec_first_alert_prior_days = $('#exec_first_alert_prior_days').val();
        var exec_second_alert_prior_days= $('#exec_second_alert_prior_days').val();
        var atrn_completed_date 		= $('#atrn_completed_date').val();
        var atrn_remark 				= $('#atrn_remark').val();
        
        if (exec_action_item == 0) {

			$("#exec_action_item").attr("data-placement", "top");
			$("#exec_action_item").attr("data-content", "Please enter action item ");
			$('#exec_action_item').popover('show');

			return false;
		}

		else {
			$('#exec_action_item').popover('destroy');
		}
        if (exec_responsible_user_id == 0 ) {
       	 
     		$( "#exec_responsible_user_id" ).attr( "data-placement", "top" );
     		$( "#exec_responsible_user_id" ).attr( "data-content", "Please select responsible person name." );
     		$('#exec_responsible_user_id').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_responsible_user_id').popover('destroy');
        }
    	
    	if (exec_frequency == 0) {
          	 
     		$( "#exec_frequency" ).attr( "data-placement", "top" );
     		$( "#exec_frequency" ).attr( "data-content", "Please select frequency." );
     		$('#exec_frequency').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_frequency').popover('destroy');
        }
    	
    	if (exec_due_date == 0) {
       	 
     		$( "#exec_due_date" ).attr( "data-placement", "top" );
     		$( "#exec_due_date" ).attr( "data-content", "Please select due date." );
     		$('#exec_due_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_due_date').popover('destroy');
        }
    	
    	if (exec_first_alert_prior_days == 0) {
          	 
     		$( "#exec_first_alert_prior_days" ).attr( "data-placement", "top" );
     		$( "#exec_first_alert_prior_days" ).attr( "data-content", "Please enter first alert prior day/s." );
     		$('#exec_first_alert_prior_days').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_first_alert_prior_days').popover('destroy');
        }
    	
    	if(exec_first_alert_prior_days < exec_second_alert_prior_days){
    		
    		$( "#exec_second_alert_prior_days" ).attr( "data-placement", "top" );
     		$( "#exec_second_alert_prior_days" ).attr( "data-content", "please enter no. of days which is less than first alert prior day/s" );
     		$('#exec_second_alert_prior_days').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#exec_second_alert_prior_days').popover('destroy');
        }
    	
    	if (atrn_completed_date == 0) {
         	 
     		$( "#atrn_completed_date" ).attr( "data-placement", "top" );
     		$( "#atrn_completed_date" ).attr( "data-content", "Please select completed date" );
     		$('#atrn_completed_date').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#atrn_completed_date').popover('destroy');
        }
    	
    	if (atrn_remark == 0) {
         	 
     		$( "#atrn_remark" ).attr( "data-placement", "top" );
     		$( "#atrn_remark" ).attr( "data-content", "Please enter remark " );
     		$('#atrn_remark').popover('show');
	
            return false;
        }
    	 else
         {
         	$('#atrn_remark').popover('destroy');
        }
    	
}