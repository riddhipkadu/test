/*
 * Author: Harshad Padole
 * Date: 09/08/2016
 * Updated By:
 * Updated Date:
 * 
 * */


$(document).ready(function(){
	  
    $('#liti_type_name').click(function(){
        $('#liti_type_name').popover('destroy');
    });
}); 

$("button[name='delete_litigation_type']").click(function(){
	
	var liti_id = $(this).val();
	items = {};
	items["liti_id"] = liti_id;
	var jsonString = JSON.stringify(items);
	
		if(liti_id > 0){
			
			 $.ajax({
					type : "post",
					url : "./checkDependancyLitigationType",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(res) {
						if(res == 1 ){
					    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
					    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
								$('#dialogBox .modal-body').html("<p><strong>This Litigation Type is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
		    				    $("#dialogBox").modal('show');	
							}else{
								bootbox.confirm("Are you sure you want to delete?", function(result) { 
					if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLitigationType",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Litigation Type deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				        			//window.location.reload(true);
				    				$('table#example tr#row_'+liti_id).remove();
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
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

 var liti_status;
 $("#liti_type_name").on('keyup click', function(){ 
	  var liti_name = $("#liti_type_name").val();
	  var liti_id = 0;

		if(liti_name !='' && liti_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["liti_id"] = liti_id;
				items["liti_name"] = liti_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isLitiNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
						//alert(result+" resulatt");
						if(result==0||result=='0'){
							liti_status = 0;
							$('#liti_type_name').popover('destroy');
						}
						else{
							liti_status = 1;
							$("#liti_type_name").attr("data-placement", "top");
							$("#liti_type_name").attr("data-content", "Litigation type already exists..!!");
							$('#liti_type_name').popover('show');
							return false;
						}

					},
					error : function(xhr, ajaxOptions, thrownError) {
						$('#errors').html("There is error:" + thrownError);
					}
				});
		}
 });	
 
 $('#add_litigation').on('keyup keypress', function(e) {
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
     //console.log("Key code "+k)
     return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
     }
 
 //Get All Litigation type 
 $.ajax({
		type : "post",
		url : "./getAllLitiJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#liti_type_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				      // $( "#liti_type_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#liti_type_name" ).val( ui.item.label );
				       liti_status = 1;
						$("#liti_type_name").attr("data-placement", "top");
						$("#liti_type_name").attr("data-content", "Litigation type already exists..!!");
						$('#liti_type_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
// ENd Get All Litigation type 

 function validateForm() { 
            
             var liti_name = $('#liti_type_name').val();
             
             if (liti_name == null || liti_name == "") {
            	 
            	 $( "#liti_type_name" ).attr( "data-placement", "top" );
                 $( "#liti_type_name" ).attr( "data-content", "Please enter litigation type." );
                 $('#liti_type_name').popover('show');
				
                    return false;
                }
              if(liti_status==1){
		        	   $( "#liti_type_name" ).attr( "data-placement", "top" );
			            $( "#liti_type_name" ).attr( "data-content", "Litigation type already exists..!!" );
			            $('#liti_type_name').popover('show');
			            return false;
		           }
                else
                {
                	$('#liti_type_name').popover('destroy');
                } 
             if (!(isNaN(liti_name))) {
                 $( "#liti_type_name" ).attr( "data-placement", "top" );
                 $( "#liti_type_name" ).attr( "data-content", "Please enter only text." );
                 $('#liti_type_name').popover('show');
                 return false;
             }
             else
             {
             	$('#liti_type_name').popover('destroy');
             }
             
             if (!(liti_name.length>2)) {
                   $( "#liti_type_name" ).attr( "data-placement", "top" );
                   $( "#liti_type_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#liti_type_name').popover('show');
                   return false;
               }
               else
               {
               	$('#liti_type_name').popover('destroy');
               }
             
             
            
         } 
    
     
    