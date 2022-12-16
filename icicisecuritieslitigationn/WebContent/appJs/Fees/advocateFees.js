/*
 * Author: Tejashri Zurunge
 * Date: 10/03/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	var lcou_type_of_fees = $("#ladv_type_of_fees").val();
	if(lcou_type_of_fees == 'appearance'){
		$("#effe_non_effe").show();
	}else{
		$("#effe_non_effe").hide();
	}
	  
    $('#ladv_advocate_name').click(function(){
        $('#ladv_advocate_name').popover('destroy');
    });
    $('#ladv_conversion_rate').change(function(){
    	 var ladv_conversion_rate = $('#ladv_conversion_rate').val();

         if(ladv_conversion_rate !=''){
         var ladv_conversion_rate = $('#ladv_conversion_rate').val();
        $('#ladv_converted_amt').val($(this).val()*$("#ladv_advocate_fees_amount").val())
         }else{
        		$('#ladv_conversion_rate').val(0.0);	 
         }
         });
    $('#ladv_advocate_fees_amount').keyup(function(){
        $('#ladv_converted_amt').val($("#ladv_conversion_rate").val()*$("#ladv_advocate_fees_amount").val())
    });
    
    $("#ladv_amount_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
    
    $("#ladv_amount_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"

	}).datepicker({ autoclose: true}).datepicker('setEndDate', new Date()).focus();
    
});

$("#effe_non_effe").hide();
$("#ladv_type_of_fees").change(function(){ 
	var lcou_type_of_fees = $("#ladv_type_of_fees").val();
	if(lcou_type_of_fees == 'appearance'){
		$("#effe_non_effe").show();
	}else{
		$("#effe_non_effe").hide();
	}
});

$("#ladv_advocate_law_firm_id").change(function(){ 
	var law_firm_id = $("#ladv_advocate_law_firm_id").val();
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
			    $("#ladv_advocate_id").html(data);
			}
	    });//End ajax
	}//End If
	
});

/****************************code for online converted currency rate starts here ****************************************************/

$("#ladv_currency").on("change", getConversionRate);
$("#ladv_converted_currency").on("change", getConversionRate);
$("#ladv_amount_date").on("change", getConversionRate);

function getConversionRate(){
	
	var amount_involved = $('#ladv_advocate_fees_amount').val();
	var converted_amt_currency = $("#ladv_converted_currency").val();
	var involved_amt_currency = $('#ladv_currency').val();
	var amount_date = $('#ladv_amount_date').val();
	var conversion_rate = $('#ladv_conversion_rate').val();
	
	if(involved_amt_currency != "NA" && converted_amt_currency != "NA" ){
			items = {};
			items["converted_amt_currency"]           = converted_amt_currency;
			items["involved_amt_currency"]            = involved_amt_currency;
			items["amount_date"] 		 			  = amount_date;
			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./getConversionRate",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(result) {
					 if(result.length != 0){ 
							 var rate = result;
							 //alert("£1 =" + rate)
								  $("#ladv_conversion_rate").val(rate);
								  //var conversion_rate = 
								  $("#ladv_converted_amt").val(rate*amount_involved);
								  if (rate == 0) {
							         	 
							      		$( "#ladv_conversion_rate" ).attr( "data-placement", "bottom" );
							      		$( "#ladv_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#ladv_conversion_rate').popover('show');
							    	
							             return false;
							         }
							     	 else
							          {
							          	$('#ladv_conversion_rate').popover('destroy');
							          }
					 }
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
	}
		
}
/****************************code for online converted currency rate ends here ****************************************************/

    function validateForm(){
    	 var ladv_type_of_fees = $('#ladv_type_of_fees').val();
    	 var ladv_effective_non_effective = $('#ladv_effective_non_effective').val();
         var ladv_advocate_fees_amount = $('#ladv_advocate_fees_amount').val(); 
         var ladv_currency = $('#ladv_currency').val();
         var ladv_conversion_rate = $('#ladv_conversion_rate').val();
         var ladv_converted_currency = $('#ladv_converted_currency').val();
         var ladv_advocate_law_firm_id = $('#ladv_advocate_law_firm_id').val();
         var ladv_advocate_id = $('#ladv_advocate_id').val();

         if (ladv_advocate_law_firm_id == "0") {
          	 
     		$( "#ladv_advocate_law_firm_id" ).attr( "data-placement", "top" );
     		$( "#ladv_advocate_law_firm_id" ).attr( "data-content", "Please select law firm." );
     		$('#ladv_advocate_law_firm_id').popover('show');
			
            return false;
        }
  
       else
       {
       	$('#ladv_advocate_law_firm_id').popover('destroy');
      }
  
         if (ladv_advocate_id == "0") {
          	 
     		$( "#ladv_advocate_id" ).attr( "data-placement", "top" );
     		$( "#ladv_advocate_id" ).attr( "data-content", "Please select advocate name." );
     		$('#ladv_advocate_id').popover('show');
			
            return false;
        }
  
       else
       {
       	$('#ladv_advocate_id').popover('destroy');
      }
  
         
         if (ladv_type_of_fees == "NA") {
          	 
        		$( "#ladv_type_of_fees" ).attr( "data-placement", "top" );
        		$( "#ladv_type_of_fees" ).attr( "data-content", "Please select Type of Fees." );
        		$('#ladv_type_of_fees').popover('show');
 			
               return false;
           }
     
          else
          {
          	$('#ladv_type_of_fees').popover('destroy');
         }
        if(ladv_type_of_fees == 'appearance'){
         if (ladv_effective_non_effective == "NA") {
          	 
     		$( "#ladv_effective_non_effective" ).attr( "data-placement", "top" );
     		$( "#ladv_effective_non_effective" ).attr( "data-content", "Please select effective or non effective field." );
     		$('#ladv_effective_non_effective').popover('show');
			
            return false;
        }
        }
       else
       {
       	$('#ladv_effective_non_effective').popover('destroy');
      }
         if (ladv_advocate_fees_amount ==0) {
         	 
     		$( "#ladv_advocate_fees_amount" ).attr( "data-placement", "top" );
     		$( "#ladv_advocate_fees_amount" ).attr( "data-content", "Please enter fees amount." );
     		$('#ladv_advocate_fees_amount').popover('show');
				
            return false;
        
     	   }else{
     		   $('#ladv_advocate_fees_amount').popover('destroy');
     	   }  
         if (isNaN(ladv_advocate_fees_amount)) {
           	
         	 $("#ladv_advocate_fees_amount" ).attr( "data-placement", "top" );
              $("#ladv_advocate_fees_amount" ).attr( "data-content", "Fees amount should be numeric" );
              $('#ladv_advocate_fees_amount').popover('show');
              return false;
          }   
         else
         {
         	$('#ladv_advocate_fees_amount').popover('destroy');
         }
         if (ladv_currency == "NA" ) {
           	 
        		$( "#ladv_currency" ).attr( "data-placement", "top" );
        		$( "#ladv_currency" ).attr( "data-content", "Please select currency." );
        		$('#ladv_currency').popover('show');
      	
               return false;
           }
       	 else
            {
            	$('#ladv_currency').popover('destroy');
            }
          if (isNaN(ladv_conversion_rate)) {
            	 
        		$( "#ladv_conversion_rate" ).attr( "data-placement", "top" );
        		$( "#ladv_conversion_rate" ).attr( "data-content", "Please enter numeric rate." );
        		$('#ladv_conversion_rate').popover('show');
      	
               return false;
           }
       	 else
            {
            	$('#ladv_conversion_rate').popover('destroy');
            }
          if(ladv_conversion_rate > 0 ){
          if (ladv_converted_currency == "NA") {
            	 
        		$( "#ladv_converted_currency" ).attr( "data-placement", "top" );
        		$( "#ladv_converted_currency" ).attr( "data-content", "Please select converted currency." );
        		$('#ladv_converted_currency').popover('show');
      	
               return false;
           }
       	 else
            {
            	$('#ladv_converted_currency').popover('destroy');
            }
          }
          else
          {
          	$('#ladv_converted_currency').popover('destroy');
          }

     
    
    
    }
    
    