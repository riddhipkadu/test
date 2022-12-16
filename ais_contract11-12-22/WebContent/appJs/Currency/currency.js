
/*
 * Author: Tejashri Zurunge
 * Date: 04/10/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#curr_code').click(function(){
        $('#curr_code').popover('destroy');
    });

});

$(function() {
    $('#curr_code').keyup(function() {
        this.value = this.value.toUpperCase();
    });
});

$("button[name='delete_currency']").click(function(){
	
	var curr_id = $(this).val();
	items = {};
	items["curr_id"] = curr_id;
	var jsonString = JSON.stringify(items);
	
		if(curr_id > 0){
			$.ajax({
				type : "post",
				url : "./checkDependancyCurrency",
				contentType : "application/json",
				dataType : "html",
				data : jsonString,
				cache : false,
				success : function(response) {
					var res = $.parseJSON(response);
					var flag = 0;
					if(res.length != 0){
						//alert("response body length :"+res.length);
						$.each(res, function(key,value){
							if(value['liti_curr'] == 1 || value['fees_advo'] == 1 || value['fees_coun'] == 1 || value['advo_paid'] == 1 || value['coun_paid'] == 1 || value['lega_noti'] == 1){
				    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
				    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
							$('#dialogBox .modal-body').html("<p><strong>This Currency is in use. Kindly make sure it is either unassigned or contact to administrator..!!</strong></p>");
	    				    $("#dialogBox").modal('show');	
	    				    flag =1;
						}else{
							flag = 0;
						}
						});
					}if(flag == 0){
						
     		   			bootbox.confirm("Are you sure you want to delete?", function(result) { 
						if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteCurrency",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Currency deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+curr_id).remove();
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
var curr_status;
$("#curr_code").keyup(function(){ 
	  var curr_code = jQuery.trim($("#curr_code").val());
	  var curr_id = 0;

		if(curr_code !='' && curr_code!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["curr_id"] = curr_id;
				items["curr_code"] = curr_code;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isCurrencyCodeExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			curr_status = 0;
			$('#curr_code').popover('destroy');
		}
		else{
			curr_status = 1;
			$("#curr_code").attr("data-placement", "top");
			$("#curr_code").attr("data-content", "Currency Code is already exists..!!");
			$('#curr_code').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addCurrency').on('keyup keypress', function(e) {
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

//Get All currency type 
$.ajax({
		type : "post",
		url : "./getAllCurrencyJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#curr_code" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				          return false;
				    },
				    select: function( event, ui ) {
				        $( "#curr_code" ).val( ui.item.label );
				        curr_status = 1;
						$("#curr_code").attr("data-placement", "top");
						$("#curr_code").attr("data-content", "Currency code is already exists..!!");
						$('#curr_code').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All currency type


function validateForm() { 
            
             var curr_code = $('#curr_code').val();
             var curr_name = $('#curr_name').val();
             
             	if (curr_code == null || curr_code == "") {
            	 
             		$( "#curr_code" ).attr( "data-placement", "top" );
             		$( "#curr_code" ).attr( "data-content", "Please enter Currency code." );
             		$('#curr_code').popover('show');
				
                    return false;
                }
             	if(curr_status==1){
		        	 $( "#curr_code" ).attr( "data-placement", "top" );
			         $( "#curr_code" ).attr( "data-content", " Currency code is already exists..!!" );
			         $('#curr_code').popover('show');
			         
			         return false;
		         }
               
            	else
               {
               	$('#curr_code').popover('destroy');
               }
            
            	if (!(isNaN(curr_code))) {
                $( "#curr_code" ).attr( "data-placement", "top" );
                $( "#curr_code" ).attr( "data-content", "Please enter only text." );
                $('#curr_code').popover('show');
                return false;
            }
            else
            {
            	$('#curr_code').popover('destroy');
            }
            
            if (!(curr_code.length==3)) {
                  $( "#curr_code" ).attr( "data-placement", "top" );
                  $( "#curr_code" ).attr( "data-content", "Please enter three characters." );
                  $('#curr_code').popover('show');
                  return false;
              }
              else
              {
              	$('#curr_code').popover('destroy');
             }
            if (curr_name == null || curr_name == "") {
           	 
         		$( "#curr_name" ).attr( "data-placement", "top" );
         		$( "#curr_name" ).attr( "data-content", "Please enter Currency name." );
         		$('#curr_name').popover('show');
			
                return false;
            }
}
     