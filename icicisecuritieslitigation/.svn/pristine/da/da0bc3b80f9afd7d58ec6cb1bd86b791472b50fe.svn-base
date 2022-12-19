$(document).ready(function(){
	  
	$(document).on("click", ":submit", function(e){
	    buttonClicked = $(this).val();
	    if(buttonClicked=="Save"){
	    	if(validateForm()!=false){
	    		$("form").submit();
	    	}else{
	    		e.preventDefault();
	    	}
	    }
	}); 
	
	
	$("#sarf_action_NotiIssue_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		todayHighlight : "true",
		showOnFocus : "true",
		defaultViewDate : "today"
	});
	
	
	$("#action_due_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		todayHighlight : "true",
		showOnFocus : "true",
		defaultViewDate : "today"
	});
	
	$("#action_first_remind_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		todayHighlight : "true",
		showOnFocus : "true",
		defaultViewDate : "today"
	});
	
	/*$("#action_second_remind_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		todayHighlight : "true",
		showOnFocus : "true",
		defaultViewDate : "today"
	});*/
	
});


function validateForm() {
	
var sarf_action_status = $('#sarf_action_status').val();
   
   // var sarf_action_item = $('#sarf_action_item').val();
   // var sarf_nextaction_item = $('#sarf_nextaction_item').val();
    var sarf_action_NotiIssue_date = $('#sarf_action_NotiIssue_date').val();
    var sarf_action_due_date = $('#sarf_action_due_date').val();
   var action_first_remind_date = $('#action_first_remind_date').val();
    //var action_second_remind_date = $('#action_second_remind_date').val();
   
    
    
    if (sarf_action_status == 0)  {
     	 
  		$( "#sarf_action_status" ).attr( "data-placement", "top" );
  		$( "#sarf_action_status" ).attr( "data-content", "Please Select Action Status." );
  		$('#sarf_action_status').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_action_status').popover('destroy');
      }
    if (sarf_action_item == 0)  {
   	 
  		$( "#sarf_action_item" ).attr( "data-placement", "top" );
  		$( "#sarf_action_item" ).attr( "data-content", "Please Enter Action Item." );
  		$('#sarf_action_item').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_action_item').popover('destroy');
      }
    if (sarf_nextaction_item == 0)  {
      	 
  		$( "#sarf_nextaction_item" ).attr( "data-placement", "top" );
  		$( "#sarf_nextaction_item" ).attr( "data-content", "Please Enter Next Action Item." );
  		$('#sarf_nextaction_item').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_nextaction_item').popover('destroy');
      }
    /*if (sarf_action_NotiIssue_date == 0)  {
      	 
  		$( "#sarf_action_NotiIssue_date" ).attr( "data-placement", "top" );
  		$( "#sarf_action_NotiIssue_date" ).attr( "data-content", "Please Select Notice Issue date." );
  		$('#sarf_action_NotiIssue_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_action_NotiIssue_date').popover('destroy');
      }*/
    if (sarf_action_due_date == 0)  {
      	 
  		$( "#sarf_action_due_date" ).attr( "data-placement", "top" );
  		$( "#sarf_action_due_date" ).attr( "data-content", "Please Enter Action Due date." );
  		$('#sarf_action_due_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#sarf_action_due_date').popover('destroy');
      }
   if (action_first_remind_date == 0)  {
      	 
  		$( "#action_first_remind_date" ).attr( "data-placement", "top" );
  		$( "#action_first_remind_date" ).attr( "data-content", "Please Select First Reminder date." );
  		$('#action_first_remind_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#action_first_remind_date').popover('destroy');
      }
    /*if (action_second_remind_date == 0)  {
      	 
  		$( "#action_second_remind_date" ).attr( "data-placement", "top" );
  		$( "#action_second_remind_date" ).attr( "data-content", "Please Select Second Reminder date." );
  		$('#action_second_remind_date').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#action_second_remind_date').popover('destroy');
      }*/
    return true;
}