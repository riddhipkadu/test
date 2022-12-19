
/*
 * Author: Tejashri Zurunge
 * Date: 30/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#court_name').click(function(){
        $('#court_name').popover('destroy');
    });

});

$("button[name='delete_court']").click(function(){
	
	var court_id = $(this).val();
	items = {};
	items["court_id"] = court_id;
	var jsonString = JSON.stringify(items);
	
		if(court_id > 0){
			$.ajax({
				type : "post",
				url : "./checkDependancyCourt",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(response) {
							if(response == 1){
				    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
				    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
							$('#dialogBox .modal-body').html("<p><strong>This Court is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
	    				    $("#dialogBox").modal('show');	
						}else{
	
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteCourt",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Court deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+court_id).remove();
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
var court_status;
$("#court_name").keyup(function(){ 
	  var court_name = jQuery.trim($("#court_name").val());
	  var court_id = 0;

		if(court_name !='' && court_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["court_id"] = court_id;
				items["court_name"] = court_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isCourtExistOrNot",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			court_status = 0;
			$('#court_name').popover('destroy');
		}
		else{
			court_status = 1;
			$("#court_name").attr("data-placement", "top");
			$("#court_name").attr("data-content", "Court Name is already exists..!!");
			$('#court_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addCourt').on('keyup keypress', function(e) {
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

//Get All court_name type 
$.ajax({
		type : "post",
		url : "./getAllCourtJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#court_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				          return false;
				    },
				    select: function( event, ui ) {
				        $( "#court_name" ).val( ui.item.label );
				        court_status = 1;
						$("#court_name").attr("data-placement", "top");
						$("#court_name").attr("data-content", "Court is already exists..!!");
						$('#court_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All Court type

function validateForm() { 
            
             var court_name = $('#court_name').val();
             
             	if (court_name == null || court_name == "") {
            	 
             		$( "#court_name" ).attr( "data-placement", "top" );
             		$( "#court_name" ).attr( "data-content", "Please enter Court Name." );
             		$('#court_name').popover('show');
				
                    return false;
                }
             	if(court_status==1){
		        	 $( "#court_name" ).attr( "data-placement", "top" );
			         $( "#court_name" ).attr( "data-content", " Court Name is already exists..!!" );
			         $('#court_name').popover('show');
			         
			         return false;
		         }
               
            	else
               {
               	$('#court_name').popover('destroy');
               }
            
            	if (!(isNaN(court_name))) {
                $( "#court_name" ).attr( "data-placement", "top" );
                $( "#court_name" ).attr( "data-content", "Please enter only text." );
                $('#court_name').popover('show');
                return false;
            }
            else
            {
            	$('#court_name').popover('destroy');
            }
            
            if (!(court_name.length>2)) {
                  $( "#court_name" ).attr( "data-placement", "top" );
                  $( "#court_name" ).attr( "data-content", "Please enter at least three characters." );
                  $('#court_name').popover('show');
                  return false;
              }
              else
              {
              	$('#court_name').popover('destroy');
             }
        
}
     