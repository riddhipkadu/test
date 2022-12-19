/*
 * Author: Tejashri Zurunge
 * Date: 01/10/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	
	$("#advo_law_firm").multiselect({
		buttonWidth: '230px',
        enableFiltering: true,
        filterBehavior: 'text',
        enableCaseInsensitiveFiltering: true,
        maxHeight: 250
	});
	
	$("#advo_area_of_expertise").multiselect({
		buttonWidth: '230px',
        enableFiltering: true,
        filterBehavior: 'text',
        enableCaseInsensitiveFiltering: true,
        maxHeight: 120
	});
	
    $('#advo_name').click(function(){
        $('#advo_name').popover('destroy');
    });
    
   // $("#advo_country_id").on('change',getStateByCountry);
   // $("#advo_state_id").on('change', getCityByState);
    
});
    $(document).on("click","button[name='delete_advocate']",function(){
    	var $tr = $(this).closest('tr');
    	var advo_id = $(this).val();
    	//console.log("exte_coun_id "+exte_coun_id);
    	items = {};
    	items["advo_id"] = advo_id;
    	var jsonString = JSON.stringify(items);
    	
    		if(advo_id > 0){
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
						}if(flag == 0){
							
							bootbox.confirm("Are you sure you want to delete?", function(result) { 
    				 if(result==true){
    					 $.ajax({
    				    		type : "post",
    				    		url : "./deleteAdvocate",
    				    		contentType : "text/html",
    				    		dataType : "html",
    				    		data : jsonString,
    				    		cache : false,
    				    		success : function(deleteCount) {
    				    			if(deleteCount==1){ 
    				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
    				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span> &nbsp;Success");
    				    				$('#dialogBox .modal-body').html("<p><strong>Advocate deleted successfully!</strong></p>");
    				    				$("#dialogBox").modal('show');
    				    				$('table#example tr#row_'+advo_id).remove();
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
				$("#advo_state_id").html(data);
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
				$("#advo_city_id").html(data);
			}
		});
	}
}
//Search box
$("#search_advo_country_id").change(function(){
	if($(this).val()>0){
		$.ajax({
			type : "POST",
			url  : "./getJoinedStateByCountryId",
			data : {country_id : $(this).val()},
			dataType : "json",
			success : function(res){
				var data = "<option value=0>--Select--</option>";
				
				$.each(res,function(key,value){
					data += "<option value="+value['state_id']+">"+value['state_name']+"</option>";
				});
				$("#search_advo_state_id").html(data);
			}
		});
	}
});
$("#search_advo_state_id").change(function(){
	if($(this).val()>0){
		$.ajax({
			type : "POST",
			url  : "./getJoinedCityByStateId",
			data : {state_id : $(this).val()},
			dataType : "json",
			success : function(res){
				var data = "<option value=0>--Select--</option>";
				
				$.each(res,function(key,value){
					data += "<option value="+value['city_id']+">"+value['city_name']+"</option>";
				});
				$("#search_advo_city_id").html(data);
			}
		});
	}
});

//Ajax call for advocate name on change of Law firm 
$("#advo_law_firm").change(function(){ 
	var law_firm_id = $("#advo_law_firm").val();
	if(law_firm_id>0){
		$.ajax({
			type : "POST",
			url  : "./getAdvocateByLawFirmId",
			data : {law_firm_id:law_firm_id},
			success : function(res){
				var data = "";
				var result = jQuery.parseJSON(res);
				data += "<option value='0'>--Select--</option>";
				if(result.length !=0){
				$.each(result,function(key,value){ 
					data +="<option value="+value['advocate_id']+">"+value['advocate_name']+"</option>";
				   });
				
				}
			    $("#search_advocate_id").html(data);
			}
	    });//End ajax
	}//End If
	
});
//search box End 
$("#search_advocate").click(function(){
	
	var advo_law_firm = $("#advo_law_firm").val();
	var advo_country = $("#search_advo_country_id").val();
	var advo_state = $("#search_advo_state_id").val();
	var advo_city = $("#search_advo_city_id").val();
	var advo_id   = $("#search_advocate_id").val();
	var advo_area_of_expertise = $("#advo_area_of_expertise").val();
	
		if(advo_country > 0 ){
			$("#search_advocate").hide();
			$("#loader").show();
			items = {};
			items["advo_law_firm"]          = advo_law_firm;
			items["advo_country"]           = advo_country;
			items["advo_state"]             = advo_state;
			items["advo_city"]              = advo_city;
			items["advo_id"]                = advo_id;
			items["advo_area_of_expertise"] = advo_area_of_expertise;

			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchAdvocate",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(result) { 
					$("#search_advocate").show();
					$("#loader").hide();
					var data = "";
					var i=1;
					document.getElementById('count').innerHTML = result.length;
					 if(result.length != 0){ 
					$.each(result,function(key,value){
						
						var advo_id = value['advo_id'];
						data += '<tr id = "row_'+advo_id+'">';
						data +="<td><center><a href='#'>"+i+"</center></a></td>";
						data +="<td>"+value['advo_name']+" </td>";
						data +="<td>"+value['advo_law_firm']+" </td>";
						data +="<td>"+value['advo_mob_no']+" </td>";
						data +="<td>"+value['advo_email_id']+" </td>";
						data +="<td>"+value['advo_area_expe_name']+ "</td>";
						data += '<td><a href="editAdvocate?advo_id='+advo_id+'">';
						data += '<button type="button" onclick="" name="submit" value="submit" class="btn btn-primary">';
						data += '<i class="fa fa-pencil-square-o"></i>&nbsp;Edit ';
						data += '</button></a>&nbsp';
						data += '<button type="button" value='+advo_id+' name="delete_advocate" class="btn btn-danger">';
						data +=	'<i class="fa fa-trash"></i>&nbsp;Delete </button>';
						data +="</td></tr>";
						i++;
						
					});
					}else{
						data += "<tr ><td colspan=7 style='text-align:center'>No result found..!!</td></tr> " ;
					}
					$("#searchResult").html(data); 
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#search_advo_country_id").attr("data-placement", "top");
			$("#search_advo_country_id").attr("data-content", "Please select coutry.");
			$('#search_advo_country_id').popover('show');
			return false;
		}
});


var advo_status;
$("#advo_name").keyup(function(){ 
	  var advo_name = jQuery.trim($("#advo_name").val());
	  var advo_id = 0;

		if(advo_name !='' && advo_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["advo_name"] = advo_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isAdvocateNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
					if(result==0||result=='0'){
						advo_status = 0;
						$('#advo_name').popover('destroy');
					}
					else{
						advo_status = 1;
						$("#advo_name").attr("data-placement", "top");
						$("#advo_name").attr("data-content", "Advocate Name already exists..!!");
						$('#advo_name').popover('show');
						return false;
					}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addAdvocate').on('keyup keypress', function(e) {
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

//Get All advo_name 
$.ajax({
		type : "post",
		url : "./getAllAdvocateNameJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			var list = $.parseJSON(data);
			 $( "#advo_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				          return false;
				    },
				    select: function( event, ui ) {
				        $( "#advo_name" ).val( ui.item.label );
				        advo_status = 1;
						$("#advo_name").attr("data-placement", "top");
						$("#advo_name").attr("data-content", "Advocate Name is already exists..!!");
						$('#advo_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
//ENd Get All advo_name


function validateForm() { 
			 var advo_country_id = $('#advo_country_id').val();
			 var advo_state_id = $('#advo_state_id').val();
			 var advo_city_id = $('#advo_city_id').val();
			 var advo_law_firm = $('#advo_law_firm').val();
             var advo_name = $('#advo_name').val();
             var advo_mobile_no = $('#advo_mobile_no').val();
             var advo_email_id = $('#advo_email_id').val();
             var advo_area_of_expertise = $('#advo_area_of_expertise').val();
    
 //Validation for External Counsel Name      
             if (advo_country_id == 0) {
             	 
            		$( "#advo_country_id" ).attr( "data-placement", "top" );
            		$( "#advo_country_id" ).attr( "data-content", "Please select country Name." );
            		$('#advo_country_id').popover('show');
     			
                   return false;
               }
         
              else
              {
              	$('#advo_country_id').popover('destroy');
             }
            	if (advo_state_id == 0) {
              	 
            		$( "#advo_state_id" ).attr( "data-placement", "top" );
            		$( "#advo_state_id" ).attr( "data-content", "Please select state." );
            		$('#advo_state_id').popover('show');
     			
                   return false;
               }
         
              else
              {
              	$('#advo_state_id').popover('destroy');
             }
            	if (advo_city_id == 0) {
              	 
            		$( "#advo_city_id" ).attr( "data-placement", "top" );
            		$( "#advo_city_id" ).attr( "data-content", "Please select city." );
            		$('#advo_city_id').popover('show');
     			
                   return false;
               }
         
              else
              {
              	$('#advo_city_id').popover('destroy');
             }
            	
            	if(advo_law_firm == 0 ) {
            	 
           		$( "#advo_law_firm" ).attr( "data-placement", "top" );
           		$( "#advo_law_firm" ).attr( "data-content", "Please select Law Firm." );
           		$('#advo_law_firm').popover('show');
 				
                  return false;
              
           	   }

              else{
             	 $('#advo_law_firm').popover('destroy');
              		}


             	if (advo_name == null || advo_name == "") {
            	 
             		$( "#advo_name" ).attr( "data-placement", "top" );
             		$( "#advo_name" ).attr( "data-content", "Please enter Advocate Name." );
             		$('#advo_name').popover('show');
				
                    return false;
                }
             	if(advo_status == 1){
					$("#advo_name").attr("data-placement", "top");
					$("#advo_name").attr("data-content", "Advocate Name already exists..!!");
					$('#advo_name').popover('show');
					return false;
             	}else{
             		$('#advo_name').popover('destroy');
             	}
             	
             
             	if (!(isNaN(advo_name))) {
                 $( "#advo_name" ).attr( "data-placement", "top" );
                 $( "#advo_name" ).attr( "data-content", "Please enter only text." );
                 $('#advo_name').popover('show');
                 return false;
             }
             else
             {
             	$('#advo_name').popover('destroy');
             }
             
             if (!(advo_name.length>2)) {
                   $( "#advo_name" ).attr( "data-placement", "top" );
                   $( "#advo_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#advo_name').popover('show');
                   return false;
               }
               else
               {
               	$('#advo_name').popover('destroy');
              }
             /*if(advo_status==1){
	        	 $( "#advo_name" ).attr( "data-placement", "top" );
		         $( "#advo_name" ).attr( "data-content", " Advocate Name already exists..!!" );
		         $('#advo_name').popover('show');
		         
		         return false;
	         }
            
         	else
            {
            	$('#advo_name').popover('destroy');
            }*/
            
   // Validation for Contact Number
          
         /* if (advo_mobile_no == null || advo_mobile_no == "") {
         	 
        		$( "#advo_mobile_no" ).attr( "data-placement", "top" );
        		$( "#advo_mobile_no" ).attr( "data-content", "Please enter Contact Number." );
        		$('#advo_mobile_no').popover('show');
				
               return false;
           
        	   }else{
        		   $('#advo_mobile_no').popover('destroy');
        	   }*/
          
          if (isNaN(advo_mobile_no)) {
          	
         	 $("#advo_mobile_no" ).attr( "data-placement", "top" );
              $("#advo_mobile_no" ).attr( "data-content", "Mobile No should be numeric" );
              $('#advo_mobile_no').popover('show');
              return false;
          }   
         else
         {
         	$('#advo_mobile_no').popover('destroy');
         }
        
          if (advo_mobile_no.length > 15) {
              $( "#advo_mobile_no" ).attr( "data-placement", "top" );
              $( "#advo_mobile_no" ).attr( "data-content", "Please enter max 15 digits." );
              $('#advo_mobile_no').popover('show');
              return false;
          }
          else
          {
          	$('#advo_mobile_no').popover('destroy');
         }
          
         /* if (advo_email_id === null || advo_email_id === "") {
              $("#advo_email_id").attr("data-placement", "top");
              $("#advo_email_id").attr("data-content", "Email must be filled out");
              $('#advo_email_id').popover('show');

              return false;
          }else{
              $('#advo_email_id').popover('destroy');
          }*/
          
          /// validate email format
          if (advo_email_id.length != 0 ) {
          var atpos = advo_email_id.indexOf("@");
          var dotpos = advo_email_id.lastIndexOf(".");
          if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= advo_email_id.length)
          {
              $("#advo_email_id").attr("data-placement", "top");
              $("#advo_email_id").attr("data-content", "Please enter valid e-mail address");
              $('#advo_email_id').popover('show');
              // alert("Not a valid e-mail address");
              return false;
          } else{
              $('#advo_email_id').popover('destroy');
          }
          }else{
              $('#advo_email_id').popover('destroy');
          }

//Validation for Area of expertise Name      
          
       	if (advo_area_of_expertise == 0) {
      	 
       		$( "#advo_area_of_expertise" ).attr( "data-placement", "top" );
       		$( "#advo_area_of_expertise" ).attr( "data-content", "Please enter Area Of Expertise Name." );
       		$('#advo_area_of_expertise').popover('show');
			
              return false;
          }
    
         else
         {
         	$('#advo_area_of_expertise').popover('destroy');
        }
       

       	
}