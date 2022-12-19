/*
 * Author: Tejashri Zurunge
 * Date: 29/08/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	$("#exte_coun_country_id").on('change', getStateByCountry);
	$("#exte_coun_state_id").on('change', getCityByState);
	  
    $('#exte_coun_name').click(function(){
        $('#exte_coun_name').popover('destroy');
    });
    

});

$(document).on("click","button[name='delete_exte_coun']",function(){
    	var $tr = $(this).closest('tr');
    	var exte_coun_id = $(this).val();
    	//alert("exte_coun_id "+exte_coun_id);
    	items = {};
    	items["exte_coun_id"] = exte_coun_id;
    	var jsonString = JSON.stringify(items);
    	
    		if(exte_coun_id>0){
    			
					 $.ajax({
							type : "post",
							url : "./checkDependencyExternalCounsel",
							contentType : "application/json",
							dataType : "html",
							data : jsonString,
							cache : false,
							success : function(response) {
								var res = $.parseJSON(response);
								var flag = 0;
							if(res.length != 0){
								$.each(res, function(key,value){
								//alert("liti law firm : " + value['liti_counsel'] +""+value['hear_counsel']+""+value['lega_counsel']+""+value['fees_counsel']);
							    if(value['liti_counsel'] == 1 || value['hear_counsel'] == 1 || value['lega_counsel'] == 1 || value['fees_counsel'] == 1 ){
							    		
							    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
							    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
										$('#dialogBox .modal-body').html("<p><strong>This External Counsel is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
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
    				    		url : "./deleteExternalCounsel",
    				    		contentType : "text/html",
    				    		dataType : "html",
    				    		data : jsonString,
    				    		cache : false,
    				    		success : function(deleteCount) {
    				    			if(deleteCount==1){ 
    				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
    				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
    				    				$('#dialogBox .modal-body').html("<p><strong>External Counsel deleted successfully!</strong></p>");
    				    				$("#dialogBox").modal('show');
    				    				//$('table#example tr#row_'+exte_coun_id).remove();
    				    				$tr.remove();
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
function getStateByCountry(){
	if($(this).val()>0){
		$.ajax({
			type : "POST",
			url  : "./getStateByCountryId",
			data : {country_id : $(this).val()},
			dataType : "json",
			success : function(res){
				var data = "<option value=0>--Select--</option>";
				
				$.each(res,function(key,value){
					data += "<option value="+value['state_id']+">"+value['state_name']+"</option>";
				});
				$("#exte_coun_state_id").html(data);
			}
		});
	}
}
function getCityByState(){
	if($(this).val()>0){
		$.ajax({
			type : "POST",
			url  : "./getCityByStateId",
			data : {state_id : $(this).val()},
			dataType : "json",
			success : function(res){
				var data = "<option value=0>--Select--</option>";
				
				$.each(res,function(key,value){
					data += "<option value="+value['city_id']+">"+value['city_name']+"</option>";
				});
				$("#exte_coun_city").html(data);
			}
		});
	}
}

$("#exte_coun_law_firm").change(function(){ 
	var law_firm_id = $("#exte_coun_law_firm").val();
	if(law_firm_id > 0){
		$.ajax({
			type : "POST",
			url  : "./getCounselByLawFirmId",
			data : {law_firm_id:law_firm_id},
			success : function(res){
				var data = "";
				var result = jQuery.parseJSON(res);
				data += "<option value='0'>--Select--</option>";
				if(result.length !=0){
				$.each(result,function(key,value){ 
					data +="<option value="+value['counsel_id']+">"+value['counsel_name']+"</option>";
				   });
				
				}
			    $("#exte_coun_name").html(data);
			}
	    });//End ajax
	}//End If
	
});

$("#search_exte_coun_country_id").change(function(){
	if($(this).val()>0){
		$.ajax({
			type : "POST",
			url  : "./getJoinedCounselStateByCountryId",
			data : {exte_coun_country_id : $(this).val()},
			dataType : "json",
			success : function(res){
				var data = "<option value=0>--Select--</option>";
				
				$.each(res,function(key,value){
					data += "<option value="+value['state_id']+">"+value['state_name']+"</option>";
				});
				$("#search_exte_coun_state_id").html(data);
			}
		});
	}
});
$("#search_exte_coun_state_id").change(function(){
	if($(this).val()>0){
		$.ajax({
			type : "POST",
			url  : "./getJoinedCounselCityByStateId",
			data : {exte_coun_state_id : $(this).val()},
			dataType : "json",
			success : function(res){
				var data = "<option value=0>--Select--</option>";
				
				$.each(res,function(key,value){
					data += "<option value="+value['city_id']+">"+value['city_name']+"</option>";
				});
				$("#search_exte_coun_city").html(data);
			}
		});
	}
});

$("#search_exte_coun").click(function(){
	
	var exte_coun_country_id = $("#search_exte_coun_country_id").val();
	var exte_coun_state_id = $("#search_exte_coun_state_id").val();
	var exte_coun_city = $("#search_exte_coun_city").val();
	var exte_coun_law_firm = $("#exte_coun_law_firm").val();
	var exte_coun_name = $("#exte_coun_name").val();
	var exte_coun_area_of_expertise = $("#exte_coun_area_of_expertise").val();
	
		if(exte_coun_country_id > 0 ){
			$("#search_exte_coun").hide();
			$("#loader").show();
			items = {};
			items["exte_coun_country_id"]          = exte_coun_country_id;
			items["exte_coun_state_id"]          = exte_coun_state_id;
			items["exte_coun_city"]              = exte_coun_city;
			items["exte_coun_law_firm"]          = exte_coun_law_firm;
			items["exte_coun_name"]          = exte_coun_name;
			items["exte_coun_area_of_expertise"] = exte_coun_area_of_expertise;

			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchExternalCounsel",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(result) { 
					$("#search_exte_coun").show();
					$("#loader").hide();
					var data = "";
					var i=1;
					 document.getElementById('count').innerHTML = result.length;
					 if(result.length != 0){ 
						
					$.each(result,function(key,value){
						
						var exte_coun_id = value['exte_coun_id'];
						data += "<tr>";
						data +="<td><center><a href='#'>"+i+"</center></a></td>";
						data +="<td>"+value['exte_coun_name']+" </td>";
						data +="<td>"+value['lawf_name']+" </td>";
						data +="<td>"+value['exte_coun_mobile_no']+" </td>";
						data +="<td>"+value['exte_coun_email_id']+" </td>";
						data +="<td>"+value['area_expe_name']+ "</td>";
						data += '<td><a href="editExternalCounsel?exte_coun_id='+exte_coun_id+'">';
						data += '<button type="button" onclick="" name="submit" value="submit" class="btn btn-primary">';
						data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Edit ';
						data += '</button></a>&nbsp';
						data += '<button type="button" value='+exte_coun_id+' name="delete_exte_coun" class="btn btn-danger">';
						data +=	'<i class="fa fa-trash"></i>&nbsp;Delete </button>';
						data +="</td></tr>";
						i++;
						
					});	
					}else{
						data += "<tr ><td colspan=7 style='text-align:center'>No result found..!!</td></tr> " ;
						//document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
					}
					$("#searchResult").html(data); 
					//$("label[for='totaltask']").html($('#example >tbody >tr').length);	
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#search_exte_coun_country_id").attr("data-placement", "top");
			$("#search_exte_coun_country_id").attr("data-content", "please select country..!!");
			$('#search_exte_coun_country_id').popover('show');
			return false;
		}
});


var exte_coun_status;
$("#exte_coun_name").keyup(function(){ 
	  var exte_coun_name = jQuery.trim($("#exte_coun_name").val());
	  var exte_coun_id = 0;

		if(exte_coun_name !='' && exte_coun_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["exte_coun_id"] 	= exte_coun_id;
				items["exte_coun_name"] = exte_coun_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isExternalCounselNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			exte_coun_status = 0;
			$('#exte_coun_name').popover('destroy');
		}
		else{
			exte_coun_status = 1;
			$("#exte_coun_name").attr("data-placement", "top");
			$("#exte_coun_name").attr("data-content", "External Counsel already exists..!!");
			$('#exte_coun_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addExternalCounsel').on('keyup keypress', function(e) {
var keyCode = e.keyCode|| e.which;
if (keyCode === 13) { 
	e.preventDefault();
return false;
}

});

//Get All Litigation type 
$.ajax({
		type : "post",
		url : "./getAllExternalCounselJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#exte_coun_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				      // $( "#liti_type_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#exte_coun_name" ).val( ui.item.label );
				       exte_coun_status = 1;
						$("#exte_coun_name").attr("data-placement", "top");
						$("#exte_coun_name").attr("data-content", "External Counsel name already exists..!!");
						$('#exte_coun_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All Litigation type 


//to block the special characters and numeric values to enter
function blockSpecialChar(e){
	
    var k;
    document.all ? k = e.keyCode : k = e.which;
    //console.log("Key code "+k)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
    }

function validateForm() { 
			 var exte_coun_country_id = $("#exte_coun_country_id").val();
			 var exte_coun_state_id = $("#exte_coun_state_id").val();
             var exte_coun_city = $('#exte_coun_city').val();
             var exte_coun_law_firm = $('#exte_coun_law_firm').val();
             var exte_coun_name = $('#exte_coun_name').val();
             var exte_coun_mobile_no = $('#exte_coun_mobile_no').val();
             var exte_coun_email_id = $('#exte_coun_email_id').val();
             var exte_coun_area_of_expertise = $('#exte_coun_area_of_expertise').val();
    
 //Validation for External Counsel Name      
            	if (exte_coun_country_id == 0) {
                 	 
               		$( "#exte_coun_country_id" ).attr( "data-placement", "top" );
               		$( "#exte_coun_country_id" ).attr( "data-content", "Please select country Name." );
               		$('#exte_coun_country_id').popover('show');
        			
                      return false;
                  }
            
                 else
                 {
                 	$('#exte_coun_country_id').popover('destroy');
                }
               	if (exte_coun_state_id == 0) {
                 	 
               		$( "#exte_coun_state_id" ).attr( "data-placement", "top" );
               		$( "#exte_coun_state_id" ).attr( "data-content", "Please select state." );
               		$('#exte_coun_state_id').popover('show');
        			
                      return false;
                  }
            
                 else
                 {
                 	$('#exte_coun_state_id').popover('destroy');
                }
               	if (exte_coun_city == 0) {
                 	 
               		$( "#exte_coun_city" ).attr( "data-placement", "top" );
               		$( "#exte_coun_city" ).attr( "data-content", "Please select city." );
               		$('#exte_coun_city').popover('show');
        			
                      return false;
                  }
            
                 else
                 {
                 	$('#exte_coun_city').popover('destroy');
                }
               	
               	if(exte_coun_law_firm == 0 ) {
               	 
              		$( "#exte_coun_law_firm" ).attr( "data-placement", "top" );
              		$( "#exte_coun_law_firm" ).attr( "data-content", "Please select Law Firm." );
              		$('#exte_coun_law_firm').popover('show');
    				
                     return false;
                 
              	   }

                 else{
                	 $('#exte_coun_law_firm').popover('destroy');
                 		}

            	
             	if (exte_coun_name == null || exte_coun_name == "") {
            	 
             		$( "#exte_coun_name" ).attr( "data-placement", "top" );
             		$( "#exte_coun_name" ).attr( "data-content", "Please enter External Counsel Name." );
             		$('#exte_coun_name').popover('show');
				
                    return false;
                }
             	
             
             	if (!(isNaN(exte_coun_name))) {
                 $( "#exte_coun_name" ).attr( "data-placement", "top" );
                 $( "#exte_coun_name" ).attr( "data-content", "Please enter only text." );
                 $('#exte_coun_name').popover('show');
                 return false;
             }
             else
             {
             	$('#exte_coun_name').popover('destroy');
             }
             
             if (!(exte_coun_name.length>2)) {
                   $( "#exte_coun_name" ).attr( "data-placement", "top" );
                   $( "#exte_coun_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#exte_coun_name').popover('show');
                   return false;
               }
               else
               {
               	$('#exte_coun_name').popover('destroy');
              }
             if(exte_coun_status==1){
	        	 $( "#exte_coun_name" ).attr( "data-placement", "top" );
		         $( "#exte_coun_name" ).attr( "data-content", " External Counsil Name already exists..!!" );
		         $('#exte_coun_name').popover('show');
		         
		         return false;
	         }
            
         	else
            {
            	$('#exte_coun_name').popover('destroy');
            }
            
  //Validation for Law Firm 
             
             /*if (exte_coun_law_firm == 0 ) {
            	 
          		$( "#exte_coun_law_firm" ).attr( "data-placement", "top" );
          		$( "#exte_coun_law_firm" ).attr( "data-content", "Please enter Law Firm." );
          		$('#exte_coun_law_firm').popover('show');
				
                 return false;
             
          	   }

             else{
            	 $('#exte_coun_law_firm').popover('destroy');
             		}*/
             	
 
 // Validation for Contact Number
          
         /* if (exte_coun_mobile_no == null || exte_coun_mobile_no == "") {
         	 
        		$( "#exte_coun_mobile_no" ).attr( "data-placement", "top" );
        		$( "#exte_coun_mobile_no" ).attr( "data-content", "Please enter Contact Number." );
        		$('#exte_coun_mobile_no').popover('show');
				
               return false;
           
        	   }else{
        		   $('#exte_coun_mobile_no').popover('destroy');
        	   }*/
          
          if (isNaN(exte_coun_mobile_no)) {
          	
         	 $("#exte_coun_mobile_no" ).attr( "data-placement", "top" );
              $("#exte_coun_mobile_no" ).attr( "data-content", "Mobile No should be numeric" );
              $('#exte_coun_mobile_no').popover('show');
              return false;
          }   
         else
         {
         	$('#exte_coun_mobile_no').popover('destroy');
         }
        
          if (!(exte_coun_mobile_no.length < 15)) {
              $( "#exte_coun_mobile_no" ).attr( "data-placement", "top" );
              $( "#exte_coun_mobile_no" ).attr( "data-content", "Please enter max 15 digits." );
              $('#exte_coun_mobile_no').popover('show');
              return false;
          }
          else
          {
          	$('#exte_coun_mobile_no').popover('destroy');
         }
          
         /* if (exte_coun_email_id === null || exte_coun_email_id === "") {
              $("#exte_coun_email_id").attr("data-placement", "top");
              $("#exte_coun_email_id").attr("data-content", "Email must be filled out");
              $('#exte_coun_email_id').popover('show');

              return false;
          }else{
              $('#exte_coun_email_id').popover('destroy');
          }*/
          /// validate email format
          
          if(exte_coun_email_id.length != 0){
        	  var atpos = exte_coun_email_id.indexOf("@");
              var dotpos = exte_coun_email_id.lastIndexOf(".");
          if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= exte_coun_email_id.length)
          {
              $("#exte_coun_email_id").attr("data-placement", "top");
              $("#exte_coun_email_id").attr("data-content", "Please enter valid e-mail address");
              $('#exte_coun_email_id').popover('show');
              // alert("Not a valid e-mail address");
              return false;
          }else{
              $('#exte_coun_email_id').popover('destroy');
          } 
         
          }else{
              $('#exte_coun_email_id').popover('destroy');
          } 

//Validation for Area of expertise Name      
          
       	if (exte_coun_area_of_expertise == 0) {
      	 
       		$( "#exte_coun_area_of_expertise" ).attr( "data-placement", "top" );
       		$( "#exte_coun_area_of_expertise" ).attr( "data-content", "Please enter Area Of Expertise Name." );
       		$('#exte_coun_area_of_expertise').popover('show');
			
              return false;
          }
    
         else
         {
         	$('#exte_coun_area_of_expertise').popover('destroy');
        }
}

