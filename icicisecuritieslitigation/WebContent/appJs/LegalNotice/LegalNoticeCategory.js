
/*
 * Author: Tejashri Zurunge
 * Date: 29/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#lega_noti_category_name').click(function(){
        $('#lega_noti_category_name').popover('destroy');
    });

});

$("button[name='delete_lega_noti_category']").click(function(){
	
	var lega_noti_category_id = $(this).val();
	items = {};
	items["lega_noti_category_id"] = lega_noti_category_id;
	var jsonString = JSON.stringify(items);
	
		if(lega_noti_category_id > 0){
			
			$.ajax({
				type : "post",
				url : "./checkDependancyLegalCategory",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(res) {
					if(res == 1 ){
				    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
				    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
							$('#dialogBox .modal-body').html("<p><strong>This Legal Category is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
	    				    $("#dialogBox").modal('show');	
					
					}else{
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLegalCategory",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Legal notice category deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+lega_noti_category_id).remove();
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
var lega_noti_category_status;
$("#lega_noti_category_name").keyup(function(){ 
	  var lega_noti_category_name = jQuery.trim($("#lega_noti_category_name").val());
	  var lega_noti_category_id = 0;

		if(lega_noti_category_name != '' && lega_noti_category_name != null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["lega_noti_category_id"] = lega_noti_category_id;
				items["lega_noti_category_name"] = lega_noti_category_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isLegalNoticeCategoryExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			lega_noti_category_status = 0;
			$('#lega_noti_category_name').popover('destroy');
		}
		else{
			lega_noti_category_status = 1;
			$("#lega_noti_category_name").attr("data-placement", "top");
			$("#lega_noti_category_name").attr("data-content", "Legal notice category name is already exists..!!");
			$('#lega_noti_category_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addLegalNoticeCatagory').on('keyup keypress', function(e) {
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


//Get All Litigation type 
$.ajax({
		type : "post",
		url : "./getAllLegalCategory",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#lega_noti_category_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				       //$( "#lega_noti_category_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#lega_noti_category_name" ).val( ui.item.label );
				       lega_noti_category_status = 1;
						$("#lega_noti_category_name").attr("data-placement", "top");
						$("#lega_noti_category_name").attr("data-content", "Legal notice category name is already exists..!!");
						$('#lega_noti_category_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All Litigation type 


function validateForm() { 
            
             var lega_noti_category_name = $('#lega_noti_category_name').val();
             
             	if (lega_noti_category_name == null || lega_noti_category_name == "") {
            	 
             		$( "#lega_noti_category_name" ).attr( "data-placement", "top" );
             		$( "#lega_noti_category_name" ).attr( "data-content", "Please enter Legal notice category Name." );
             		$('#lega_noti_category_name').popover('show');
				
                    return false;
                }
             	if(lega_noti_category_status == 1){
		        	 $( "#lega_noti_category_name" ).attr( "data-placement", "top" );
			         $( "#lega_noti_category_name" ).attr( "data-content", " Legal notice category Name is already exists..!!" );
			         $('#lega_noti_category_name').popover('show');
			         
			         return false;
		         }
               
            	else
               {
               	$('#lega_noti_category_name').popover('destroy');
               }
            
            	if (!(isNaN(lega_noti_category_name))) {
                $( "#lega_noti_category_name" ).attr( "data-placement", "top" );
                $( "#lega_noti_category_name" ).attr( "data-content", "Please enter only text." );
                $('#lega_noti_category_name').popover('show');
                return false;
            }
            else
            {
            	$('#lega_noti_category_name').popover('destroy');
            }
            
            if (!(lega_noti_category_name.length>2)) {
                  $( "#lega_noti_category_name" ).attr( "data-placement", "top" );
                  $( "#lega_noti_category_name" ).attr( "data-content", "Please enter at least three characters." );
                  $('#lega_noti_category_name').popover('show');
                  return false;
              }
              else
              {
              	$('#lega_noti_category_name').popover('destroy');
             }
        
}
     