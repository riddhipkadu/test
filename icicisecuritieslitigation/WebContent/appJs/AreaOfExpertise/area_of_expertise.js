/*
 * Author: Tejashri Zurunge
 * Date: 24/08/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#area_expe_name').click(function(){
        $('#area_expe_name').popover('destroy');
    });

}); 

$("button[name='delete_area_expe']").click(function(){
	
	var area_expe_id = $(this).val();
	console.log("area_expe_id "+area_expe_id);
	items = {};
	items["area_expe_id"] = area_expe_id;
	var jsonString = JSON.stringify(items);
	
		if(area_expe_id>0){
				 $.ajax({
						type : "post",
						url : "./checkAreaOfExpertiseDependancy",
						contentType : "application/json",
						dataType : "html",
						data : jsonString,
						cache : false,
						success : function(response) {
							if(response>0){
								$("#actionFail").modal('show');
							
							}else{
								bootbox.confirm("Are you sure you want to delete?", function(result) { 
									 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteAreaOfExpertise",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Area Of Expertise deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+area_expe_id).remove();
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

var area_Of_Expertise_status;
$("#area_expe_name").keyup(function(){ 
	  var area_expe_name = jQuery.trim($("#area_expe_name").val());
	  var area_expe_id = 0;

		if(area_expe_name !='' && area_expe_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["area_expe_id"] = area_expe_id;
				items["area_expe_name"] = area_expe_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isAreaExpeNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			area_Of_Expertise_status = 0;
			$('#area_expe_name').popover('destroy');
		}
		else{
			area_Of_Expertise_status = 1;
			$("#area_expe_name").attr("data-placement", "top");
			$("#area_expe_name").attr("data-content", "Area of Expertise is already exists..!!");
			$('#area_expe_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addAreaOfExpertise').on('keyup keypress', function(e) {
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

//Get All Area Of Expertise JSON  
$.ajax({
		type : "post",
		url : "./getAllAreaOfExpertiseJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#area_expe_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				       //$( "#area_expe_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#area_expe_name" ).val( ui.item.label );
				       area_Of_Expertise_status = 1;
				       $( "#area_expe_name" ).attr( "data-placement", "top" );
	             		$( "#area_expe_name" ).attr( "data-content", "Area of Expertise is already exist..!." );
	             		$('#area_expe_name').popover('show');
				       return false;
				    } 
				 });
		}
	});

function validateForm() { 
            
             var area_expe_name = $('#area_expe_name').val();
             
             	if (area_expe_name == null || area_expe_name == "") {
            	 
             		$( "#area_expe_name" ).attr( "data-placement", "top" );
             		$( "#area_expe_name" ).attr( "data-content", "Please enter Area of Expertise." );
             		$('#area_expe_name').popover('show');
				
                    return false;
                }
             	if(area_Of_Expertise_status==1){
		        	 $( "#area_expe_name" ).attr( "data-placement", "top" );
			         $( "#area_expe_name" ).attr( "data-content", " Area of Expertise is already exists..!!" );
			         $('#area_expe_name').popover('show');
			         
			         return false;
		         }
                
             	else
                {
                	$('#area_expe_name').popover('destroy');
                }
             
             	if (!(isNaN(area_expe_name))) {
                 $( "#area_expe_name" ).attr( "data-placement", "top" );
                 $( "#area_expe_name" ).attr( "data-content", "Please enter only text." );
                 $('#area_expe_name').popover('show');
                 return false;
             }
             else
             {
             	$('#area_expe_name').popover('destroy');
             }
             
             if (!(area_expe_name.length>2)) {
                   $( "#area_expe_name" ).attr( "data-placement", "top" );
                   $( "#area_expe_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#area_expe_name').popover('show');
                   return false;
               }
               else
               {
               	$('#area_expe_name').popover('destroy');
              }
             //if(area_expe_name)
		}