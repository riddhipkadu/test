/*
 * Author: Tejashri Zurunge
 * Date: 18/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#sfco_type_name').click(function(){
        $('#sfco_type_name').popover('destroy');
    });

}); 

$("button[name='delete_sfco_type']").click(function(){
	
	var sfco_type_id = $(this).val();
	//console.log("area_expe_id "+area_expe_id);
	items = {};
	items["sfco_type_id"] = sfco_type_id;
	var jsonString = JSON.stringify(items);
	
		if(sfco_type_id>0){

			$.ajax({
					type : "post",
					url : "./checkDependancySFCType",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(res) {
						if(res == 1 ){
					    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
					    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
								$('#dialogBox .modal-body').html("<p><strong>This Standard Form Contract Type is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
		    				    $("#dialogBox").modal('show');	
							}else{
  		 							
						bootbox.confirm("Are you sure you want to delete?", function(result) { 
							 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteStandardFormContractType",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Standard Form Contract Type deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+sfco_type_id).remove();
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
					}					
});
		}
});

var sfco_type_status;
$("#sfco_type_name").keyup(function(){ 
	  var sfco_type_name = jQuery.trim($("#sfco_type_name").val());
	  var sfco_type_id = 0;

		if(sfco_type_name !='' && sfco_type_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["sfco_type_id"] = sfco_type_id;
				items["sfco_type_name"] = sfco_type_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isStandardFormContractTypeExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			sfco_type_status = 0;
			$('#sfco_type_name').popover('destroy');
		}
		else{
			sfco_type_status = 1;
			$("#sfco_type_name").attr("data-placement", "top");
			$("#sfco_type_name").attr("data-content", "Standard Form Contract Type already exists..!!");
			$('#sfco_type_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addStandardFormContractType').on('keyup keypress', function(e) {
var keyCode = e.keyCode || e.which;
if (keyCode === 13) { 
e.preventDefault();
return false;
}

});

//to block the special characters and numeric values to enter
function blockSpecialChar(e){
	
    var k;
    document.all ? k = e.keyCode : k = e.which;
    console.log("Key code "+k)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
    }

//Get All SFC type 
$.ajax({
		type : "post",
		url : "./getAllSFCType",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#sfco_type_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				          return false;
				    },
				    select: function( event, ui ) {
				        $( "#sfco_type_name" ).val( ui.item.label );
				        sfco_type_status = 1;
						$("#sfco_type_name").attr("data-placement", "top");
						$("#sfco_type_name").attr("data-content", "Standard Form Contract type is already exists..!!");
						$('#sfco_type_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All SFC type 


function validateForm() { 
            
             var sfco_type_name = $('#sfco_type_name').val();
             
             	if (sfco_type_name == null || sfco_type_name == "") {
            	 
             		$( "#sfco_type_name" ).attr( "data-placement", "top" );
             		$( "#sfco_type_name" ).attr( "data-content", "Please enter Standard Form Contract type." );
             		$('#sfco_type_name').popover('show');
				
                    return false;
                }
             	if(sfco_type_status==1){
		        	 $( "#sfco_type_name" ).attr( "data-placement", "top" );
			         $( "#sfco_type_name" ).attr( "data-content", " Standard Form Contract type already exists..!!" );
			         $('#sfco_type_name').popover('show');
			         
			         return false;
		         }
                
             	else
                {
                	$('#sfco_type_name').popover('destroy');
                }
             
             	if (!(isNaN(sfco_type_name))) {
                 $( "#sfco_type_name" ).attr( "data-placement", "top" );
                 $( "#sfco_type_name" ).attr( "data-content", "Please enter only text." );
                 $('#sfco_type_name').popover('show');
                 return false;
             }
             else
             {
             	$('#sfco_type_name').popover('destroy');
             }
             
             if (!(sfco_type_name.length>2)) {
                   $( "#sfco_type_name" ).attr( "data-placement", "top" );
                   $( "#sfco_type_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#sfco_type_name').popover('show');
                   return false;
               }
               else
               {
               	$('#sfco_type_name').popover('destroy');
              }
             //if(area_expe_name)
		}