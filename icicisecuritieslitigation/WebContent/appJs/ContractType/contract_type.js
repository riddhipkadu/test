/*
 * Author: Tejashri Zurunge
 * Date: 12/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#cont_type_name').click(function(){
        $('#cont_type_name').popover('destroy');
    });

});

$("button[name='delete_cont_type']").click(function(){
	
	var cont_type_id = $(this).val();
	items = {};
	items["cont_type_id"] = cont_type_id;
	var jsonString = JSON.stringify(items);
	
		if(cont_type_id > 0){

			$.ajax({
					type : "post",
					url : "./checkDependancyContractType",
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
								if(value['cont_type'] == 1 || value['exec_type'] == 1){
					    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
					    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
								$('#dialogBox .modal-body').html("<p><strong>This Contract Type is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
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
				    		url : "./deleteContractType",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Contract Type deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+cont_type_id).remove();
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
var cont_type_status;
$("#cont_type_name").keyup(function(){ 
	  var cont_type_name = jQuery.trim($("#cont_type_name").val());
	  var cont_type_id = 0;

		if(cont_type_name !='' && cont_type_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["cont_type_id"] = cont_type_id;
				items["cont_type_name"] = cont_type_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isContractTypeNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			cont_type_status = 0;
			$('#cont_type_name').popover('destroy');
		}
		else{
			cont_type_status = 1;
			$("#cont_type_name").attr("data-placement", "top");
			$("#cont_type_name").attr("data-content", "Contract Type Name is already exists..!!");
			$('#cont_type_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addContractType').on('keyup keypress', function(e) {
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
		url : "./getAllContractTypeJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#cont_type_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				      // $( "#liti_type_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#cont_type_name" ).val( ui.item.label );
				       cont_type_status = 1;
						$("#cont_type_name").attr("data-placement", "top");
						$("#cont_type_name").attr("data-content", "Contract type name is already exists..!!");
						$('#cont_type_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All Litigation type 

function validateForm() { 
            
             var cont_type_name = $('#cont_type_name').val();
             
             	if (cont_type_name == null || cont_type_name == "") {
            	 
             		$( "#cont_type_name" ).attr( "data-placement", "top" );
             		$( "#cont_type_name" ).attr( "data-content", "Please enter Stage Name." );
             		$('#cont_type_name').popover('show');
				
                    return false;
                }
             	if(cont_type_status==1){
		        	 $( "#cont_type_name" ).attr( "data-placement", "top" );
			         $( "#cont_type_name" ).attr( "data-content", " Contract Type Name is already exists..!!" );
			         $('#cont_type_name').popover('show');
			         
			         return false;
		         }
               
            	else
               {
               	$('#cont_type_name').popover('destroy');
               }
            
            	if (!(isNaN(cont_type_name))) {
                $( "#cont_type_name" ).attr( "data-placement", "top" );
                $( "#cont_type_name" ).attr( "data-content", "Please enter only text." );
                $('#cont_type_name').popover('show');
                return false;
            }
            else
            {
            	$('#cont_type_name').popover('destroy');
            }
            
            if (!(cont_type_name.length>2)) {
                  $( "#cont_type_name" ).attr( "data-placement", "top" );
                  $( "#cont_type_name" ).attr( "data-content", "Please enter at least three characters." );
                  $('#cont_type_name').popover('show');
                  return false;
              }
              else
              {
              	$('#cont_type_name').popover('destroy');
             }
        
}
     