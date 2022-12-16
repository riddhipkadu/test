



   $(document).on("click","button[name='delete_inter_liti']",function(){
	   
    	var $tr = $(this).closest('tr');
    	var internal_liti_id = $(this).val();
    	//console.log("exte_coun_id "+exte_coun_id);
    	items = {};
    	items["internal_liti_id"] = internal_liti_id;
    	var jsonString = JSON.stringify(items);
    	
    	/*if(advo_id > 0){
    			$.ajax({
					type : "post",
					url : "./checkDependancyAdvocate",
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
								if(value['liti_advo'] == 1 || value['fees_advo'] == 1){
					    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
					    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
								$('#dialogBox .modal-body').html("<p><strong>This Advocate is in use. Kindly make sure it is either unassigned or contact to administrator..!!</strong></p>");
		    				    $("#dialogBox").modal('show');	
		    				    flag =1;
							}else{
								flag = 0;
							}
							});
						}if(flag == 0){*/
							
							bootbox.confirm("Are you sure you want to delete?", function(result) { 
    				 if(result==true){
    					 $.ajax({
    				    		type : "post",
    				    		url : "./deleteInternalLitigation",
    				    		contentType : "text/html",
    				    		dataType : "html",
    				    		data : jsonString,
    				    		cache : false,
    				    		success : function(deleteCount) {
    				    			if(deleteCount==1){ 
    				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
    				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span> &nbsp;Success");
    				    				$('#dialogBox .modal-body').html("<p><strong>Internal litigation deleted successfully!</strong></p>");
    				    				$("#dialogBox").modal('show');
    				    				$('table#example tr#row_'+internal_liti_id).remove();
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
    	/*}
					}
    });
    		}*/
    
}); 
   
   
   var liti_status;
   $("#internal_liti_code").keyup(function(){
	   //alert("hi");
   	  var internal_liti_code = jQuery.trim($("#internal_liti_code").val());
   	  var internal_liti_id = 0;

   		if(internal_liti_code !='' && internal_liti_code!= null ){ // && !typeof emp_id === 'undefined'
   			
   			    items = {};
   				items["internal_liti_code"] = internal_liti_code;

   				var jsonString = JSON.stringify(items);
   				$.ajax({
   					type : "post",
   					url : "./isLitigationCodeExist",
   					contentType : "application/json",
   					dataType : "html",
   					data : jsonString,
   					cache : false,
   					success : function(result) {
   						//alert("result "+result);
   					if(result==0||result=='0'){
   						liti_status = 0;
   						$('#internal_liti_code').popover('destroy');
   					}
   					else{
   						liti_status = 1;
   						$("#internal_liti_code").attr("data-placement", "top");
   						$("#internal_liti_code").attr("data-content", "Litigation code is already exists..!!");
   						$('#internal_liti_code').popover('show');
   						return false;
   					}
   	},
   	error : function(xhr, ajaxOptions, thrownError) {
   		$('#errors').html("There is error:" + thrownError);
   	}
   });
   		}
   });
   
   
   function validateForm(){
	   
	   
	   var internal_liti_code = $("#internal_liti_code").val();
	   var internal_liti_desc = $("#internal_liti_desc").val();
	   
	   if(internal_liti_code == 0){
           	 
       		$( "#internal_liti_code" ).attr( "data-placement", "top" );
       		$( "#internal_liti_code" ).attr( "data-content", "Please enter code." );
       		$('#internal_liti_code').popover('show');
			
              return false;
          }
         else
         {
         	$('#internal_liti_code').popover('destroy');
         }
	   
	   if(liti_status == 1){
         	 
      		$( "#internal_liti_code" ).attr( "data-placement", "top" );
      		$( "#internal_liti_code" ).attr( "data-content", "Litigation code is already exists..!!" );
      		$('#internal_liti_code').popover('show');
			
             return false;
         }
        else
        {
        	$('#internal_liti_code').popover('destroy');
        }
	   
	   if(internal_liti_desc == 0){
         	 
      		$( "#internal_liti_desc" ).attr( "data-placement", "top" );
      		$( "#internal_liti_desc" ).attr( "data-content", "Please enter description." );
      		$('#internal_liti_desc').popover('show');
			
             return false;
         }
        else
        {
        	$('#internal_liti_desc').popover('destroy');
       }
	   
	   
   }
   
   