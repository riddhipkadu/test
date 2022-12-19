
/*
 * Author: Tejashri Zurunge
 * Date: 12/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#stage_name').click(function(){
        $('#stage_name').popover('destroy');
    });

});

$("button[name='delete_stages']").click(function(){
	
	var stage_id = $(this).val();
	//console.log("stage_id "+stage_id);
	items = {};
	items["stage_id"] = stage_id;
	var jsonString = JSON.stringify(items);
	
		if(stage_id > 0){

			 $.ajax({
					type : "post",
					url : "./checkDependancyStages",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(res) {
						if(res == 1 ){
					    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
					    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
								$('#dialogBox .modal-body').html("<p><strong>This Stage is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
		    				    $("#dialogBox").modal('show');	
							}else{
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteStages",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Stage deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+stage_id).remove();
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


var stage_status;
$("#stage_name").keyup(function(){ 
	  var stage_name = jQuery.trim($("#stage_name").val());
	  var stage_id = 0;

		if(stage_name !='' && stage_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["stage_id"] = stage_id;
				items["stage_name"] = stage_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isStagesNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			stage_status = 0;
			$('#stage_name').popover('destroy');
		}
		else{
			stage_status = 1;
			$("#stage_name").attr("data-placement", "top");
			$("#stage_name").attr("data-content", "Stages Name is already exists..!!");
			$('#stage_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addStages').on('keyup keypress', function(e) {
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
   // console.log("Key code "+k)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
    }

//Get All Stages type 
$.ajax({
		type : "post",
		url : "./getAllStagesJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#stage_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				      // $( "#liti_type_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#stage_name" ).val( ui.item.label );
				       stage_status = 1;
						$("#stage_name").attr("data-placement", "top");
						$("#stage_name").attr("data-content", "Stage name is already exists..!!");
						$('#stage_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All Litigation type 


function validateForm() { 
            
             var stage_name = $('#stage_name').val();
             	if (stage_name == null || stage_name == "") {
            	 
             		$( "#stage_name" ).attr( "data-placement", "top" );
             		$( "#stage_name" ).attr( "data-content", "Please enter Stage Name." );
             		$('#stage_name').popover('show');
				
                    return false;
                }
             	if(stage_status==1){
		        	 $( "#stage_name" ).attr( "data-placement", "top");
			         $( "#stage_name" ).attr( "data-content", " Stage Name is already exists..!!" );
			         $('#stage_name').popover('show');
			         
			         return false;
		         }
            	else
               {
               	$('#stage_name').popover('destroy');
               }
            
            	if (!(isNaN(stage_name))) {
                $( "#stage_name" ).attr( "data-placement", "top" );
                $( "#stage_name" ).attr( "data-content", "Please enter only text." );
                $('#stage_name').popover('show');
                return false;
            }
            else
            {
            	$('#stage_name').popover('destroy');
            }
            
            if (!(stage_name.length>2)) {
                  $( "#stage_name" ).attr( "data-placement", "top" );
                  $( "#stage_name" ).attr( "data-content", "Please enter at least three characters." );
                  $('#stage_name').popover('show');
                  return false;
              }
              else
              {
              	$('#stage_name').popover('destroy');
             }
        
}
     