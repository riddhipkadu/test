$(document).ready(function(){
	$("#clause_Lib_Id").multiselect({
		buttonWidth: '351px',
        enableFiltering: true,
        filterBehavior: 'text',
        enableCaseInsensitiveFiltering: true,
        maxHeight: 250
	
	});
	
	
	
	
$("#lib_date_div").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			//endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"


		}).datepicker().on('changeDate', function(e){

		$('#lib_date_div').datepicker({ autoclose: true}).datepicker('', e.date).focus();
		
		});
		
		
		
$(document).on("click","button[name='delete_clauselib']",function(){
	
	var clause_Lib_Id = $(this).val();
	items = {};
	items["clause_Lib_Id"] = clause_Lib_Id;
	//alert("Clause Library ID is: "+clause_Lib_Id);
	var jsonString = JSON.stringify(items);
	
		if(clause_Lib_Id > 0){
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result == true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteClauseLib",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Executed Contract deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+clause_Lib_Id).remove();
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
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


function validateForm() { 
			 var clause_Id = $('#clause_Id').val();
			 var lib_owner_Id = $('#lib_owner_Id').val();
			
    
 //Validation for Clause Library Name      
             if (clause_Id == 0) {
             	 
            		$( "#clause_Id" ).attr( "data-placement", "top" );
            		$( "#clause_Id" ).attr( "data-content", "Please select Clause Name." );
            		$('#clause_Id').popover('show');
     			
                   return false;
               }
         
              else
              {
              	$('#clause_Id').popover('destroy');
             }
            	if (lib_owner_Id == 0) {
              	 
            		$( "#lib_owner_Id" ).attr( "data-placement", "top" );
            		$( "#lib_owner_Id" ).attr( "data-content", "Please select state." );
            		$('#lib_owner_Id').popover('show');
     			
                   return false;
               }
         
              else
              {
              	$('#lib_owner_Id').popover('destroy');
             }
            	
       

       	
}


});

