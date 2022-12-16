/*
 * Author: Tejashri Zurunge
 * Date: 26/08/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#lawf_name').click(function(){
        $('#lawf_name').popover('destroy');
    });

}); 

$("button[name='delete_law_firm']").click(function(){
	
	var lawf_id = $(this).val();
	//alert("lawf_id "+lawf_id);
	items = {};
	items["lawf_id"] = lawf_id;
	var jsonString = JSON.stringify(items);
	
		if(lawf_id>0){
					 $.ajax({
							type : "post",
							url : "./checkLawFirmDependancy",
							contentType : "application/json",
							dataType : "html",
							data : jsonString,
							cache : false,
							success : function(response) {
								//alert("response length :"+response.length);
								var res = $.parseJSON(response);
								var flag = 0;
							if(res.length != 0){
								//alert("response body length :"+res.length);
								$.each(res, function(key,value){
								//alert("liti law firm : " + value['liti_law_firm'] +""+value['hear_law_firm']+""+value['exte_coun_law_firm']+""+value['liti_counsel_fee_lawFirm']+""+value['liti_advocate_fee_lawFirm']);
							    if(value['liti_law_firm'] == 1 || value['hear_law_firm'] == 1 || value['exte_coun_law_firm'] == 1 || value['liti_counsel_fee_lawFirm'] == 1 || value['liti_advocate_fee_lawFirm'] == 1){
							    		
							    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
							    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
										$('#dialogBox .modal-body').html("<p><strong>This Law Firm is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
				    				    $("#dialogBox").modal('show');	
				    				    flag = 1;
									}else{
										flag = 0;
									}
								});
							}
							
						   if(flag==0){

								bootbox.confirm("Are you sure you want to delete?", function(result) { 
							 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLawFirm",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Law Firm deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				        			//window.location.reload(true);
				    				$('table#example tr#row_'+lawf_id).remove();
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
				    				
				    			}
				    			else{
				    				$("#dialogBox .modal-header").css("background-color", "#e26d1c");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
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

var lawf_status;
$("#lawf_name").keyup(function(){ 
	  var lawf_name = jQuery.trim($("#lawf_name").val());
	  var lawf_id = 0;

		if(lawf_name !='' && lawf_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["lawf_id"] = lawf_id;
				items["lawf_name"] = lawf_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isLawFirmNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			lawf_status = 0;
			$('#lawf_name').popover('destroy');
		}
		else{
			lawf_status = 1;
			$("#lawf_name").attr("data-placement", "top");
			$("#lawf_name").attr("data-content", "Law Firm is already exists..!!");
			$('#lawf_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addLawFirm').on('keyup keypress', function(e) {
var keyCode = e.keyCode|| e.which;
if (keyCode === 13) { 
	e.preventDefault();
return false;
}

});


//to block the special characters and numeric values to enter
function blockSpecialChar(e){
	
    var k;
    document.all ? k = e.keyCode : k = e.which;
    //console.log("Key code "+k)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
    }

//Get All Litigation type 
$.ajax({
		type : "post",
		url : "./getAllLawFirmJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#lawf_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				      // $( "#liti_type_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#lawf_name" ).val( ui.item.label );
				       lawf_status = 1;
						$("#lawf_name").attr("data-placement", "top");
						$("#lawf_name").attr("data-content", "Law Firm name is already exists..!!");
						$('#lawf_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All Litigation type 

function validateForm() { 
            
             var lawf_name = $('#lawf_name').val();
             var lawf_contact_person = $('#lawf_contact_person').val();
             var lawf_mobile_no = $('#lawf_mobile_no').val();
             var mail=$('#lawf_email_id').val();
    
 //Validation for Law Firm Name      
             
             	if (lawf_name == null || lawf_name == "") {
            	 
             		$( "#lawf_name" ).attr( "data-placement", "top" );
             		$( "#lawf_name" ).attr( "data-content", "Please enter Law Firm Name." );
             		$('#lawf_name').popover('show');
			
                    return false;
                }
             	
             
             	if (!(isNaN(lawf_name))) {
                 $( "#lawf_name" ).attr( "data-placement", "top" );
                 $( "#lawf_name" ).attr( "data-content", "Please enter only text." );
                 $('#lawf_name').popover('show');
                 return false;
             }
             else
             {
             	$('#lawf_name').popover('destroy');
             }
             
             if (!(lawf_name.length>2)) {
                   $( "#lawf_name" ).attr( "data-placement", "top" );
                   $( "#lawf_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#lawf_name').popover('show');
                   return false;
               }
               else
               {
               	$('#lawf_name').popover('destroy');
              }
             if(lawf_status==1){
	        	 $( "#lawf_name" ).attr( "data-placement", "top" );
		         $( "#lawf_name" ).attr( "data-content", " Law Firm is already exists..!!" );
		         $('#lawf_name').popover('show');
		         
		         return false;
	         }
            
         	else
            {
            	$('#lawf_name').popover('destroy');
            }
            
  //Validation for Contact Person Name
             
             if (lawf_contact_person == null || lawf_contact_person == "") {
            	 
          		$( "#lawf_contact_person" ).attr( "data-placement", "top" );
          		$( "#lawf_contact_person" ).attr( "data-content", "Please enter Contact Person." );
          		$('#lawf_contact_person').popover('show');
				
                 return false;
             
          	   }
          
          	if (!(isNaN(lawf_contact_person))) {
              $( "#lawf_contact_person" ).attr( "data-placement", "top" );
              $( "#lawf_contact_person" ).attr( "data-content", "Please enter only text." );
              $('#lawf_contact_person').popover('show');
              return false;
          }
          else
          {
          	$('#lawf_contact_person').popover('destroy');
          }
          
          if (!(lawf_contact_person.length>2)) {
                $( "#lawf_contact_person" ).attr( "data-placement", "top" );
                $( "#lawf_contact_person" ).attr( "data-content", "Please enter at least three characters." );
                $('#lawf_contact_person').popover('show');
                return false;
            }
            else
            {
            	$('#lawf_contact_person').popover('destroy');
           }		
 
 // Validation for Contact Number
          
          if (lawf_mobile_no == null || lawf_mobile_no == "") {
         	 
        		$( "#lawf_mobile_no" ).attr( "data-placement", "top" );
        		$( "#lawf_mobile_no" ).attr( "data-content", "Please enter Contact Number." );
        		$('#lawf_mobile_no').popover('show');
				
               return false;
           
        	   }else{
        		   $('#lawf_mobile_no').popover('destroy');
        	   }
          
          if (isNaN(lawf_mobile_no)) {
          	
         	 $("#lawf_mobile_no" ).attr( "data-placement", "top" );
              $("#lawf_mobile_no" ).attr( "data-content", "Mobile No should be numeric" );
              $('#lawf_mobile_no').popover('show');
              return false;
          }   
         else
         {
         	$('#lawf_mobile_no').popover('destroy');
         }
        
          if (!(lawf_mobile_no.length==10)) {
              $( "#lawf_mobile_no" ).attr( "data-placement", "top" );
              $( "#lawf_mobile_no" ).attr( "data-content", "Please enter ten digits." );
              $('#lawf_mobile_no').popover('show');
              return false;
          }
          else
          {
          	$('#lawf_mobile_no').popover('destroy');
         }
          
          /*
          if (mail === null || mail === "") {
              $("#lawf_email_id").attr("data-placement", "top");
              $("#lawf_email_id").attr("data-content", "Email must be filled out");
              $('#lawf_email_id').popover('show');

              return false;
          }
          /// validate email format
*/         
          if (mail.length != 0 ) {
          var atpos = mail.indexOf("@");
          var dotpos = mail.lastIndexOf(".");
          if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= mail.length)
          {
              $("#lawf_email_id").attr("data-placement", "top");
              $("#lawf_email_id").attr("data-content", "Not a valid e-mail address");
              $('#lawf_email_id').popover('show');
              // alert("Not a valid e-mail address");
              return false;
          } else
          {
              $('#lawf_email_id').popover('destroy');
          } 
}else{
    $('#lawf_email_id').popover('destroy');
}

}     	
