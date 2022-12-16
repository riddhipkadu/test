/*
 * Author: Tejashri Zurunge
 * Date: 10/03/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){

	var lcou_type_of_fees = $("#lcou_type_of_fees").val();
	if(lcou_type_of_fees == 'appearance'){
		$("#effe_non_effe").show();
	}else{
		$("#lcou_effective_non_effective").val("NA");
		$("#effe_non_effe").hide();
	}
	  
    $('#lcou_counsel_name').click(function(){
        $('#lcou_counsel_name').popover('destroy');
    });
    $('#lcou_conversion_rate').change(function(){
        var lcou_conversion_rate = $('#lcou_conversion_rate').val();

        if(lcou_conversion_rate !=''){
        var lcou_conversion_rate = $('#lcou_conversion_rate').val();
        $('#lcou_converted_amt').val($(this).val()*$("#lcou_counsel_fees_amount").val())
    	}else{
    		$('#lcou_conversion_rate').val(0.0);
    	}
    	});
    $('#lcou_counsel_fees_amount').keyup(function(){
        $('#lcou_converted_amt').val($("#lcou_conversion_rate").val()*$("#lcou_counsel_fees_amount").val())
    });
    $("#noti_amount_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		//endDate : new Date(),
	}).datepicker("update",new Date());
    
    $("#noti_amount_div").datepicker({
		 
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
$("#lcou_type_of_fees").change(function(){ 
	var lcou_type_of_fees = $("#lcou_type_of_fees").val();
	if(lcou_type_of_fees == 'appearance'){
		$("#effe_non_effe").show();
	}else{
		$("#effe_non_effe").val("NA");
		$("#effe_non_effe").hide();
	}
});

$("#lcou_law_firm_id").change(function(){ 
	var law_firm_id = $("#lcou_law_firm_id").val();
	if(law_firm_id>0){
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
			    $("#lcou_counsel_id").html(data);
			}
	    });//End ajax
	}//End If
	
});

/****************************code for online converted currency rate starts here ****************************************************/

$("#lcou_currency").on("change", getConversionRate);
$("#lcou_converted_currency").on("change", getConversionRate);
$("#lcou_amount_date").on("change", getConversionRate);

function getConversionRate(){
	
	var amount_involved = $('#lcou_counsel_fees_amount').val();
	var converted_amt_currency = $("#lcou_converted_currency").val();
	var involved_amt_currency = $('#lcou_currency').val();
	var amount_date = $('#lcou_amount_date').val();
	var conversion_rate = $('#lcou_conversion_rate').val();
	
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
								  $("#lcou_conversion_rate").val(rate);
								  //var conversion_rate = 
								  $("#lcou_converted_amt").val(rate*amount_involved);
								  if (rate == 0) {
							         	 
							      		$( "#lcou_conversion_rate" ).attr( "data-placement", "bottom" );
							      		$( "#lcou_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#lcou_conversion_rate').popover('show');
							    	
							             return false;
							         }
							     	 else
							          {
							          	$('#lcou_conversion_rate').popover('destroy');
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
    	 var lcou_type_of_fees = $('#lcou_type_of_fees').val();
    	 var lcou_effective_non_effective = $('#lcou_effective_non_effective').val();
         var lcou_counsel_fees_amount = $('#lcou_counsel_fees_amount').val(); 	
         var lcou_currency = $('#lcou_currency').val();
         var lcou_conversion_rate = $('#lcou_conversion_rate').val();
         var lcou_converted_currency = $('#lcou_converted_currency').val();
         var lcou_law_firm_id = $('#lcou_law_firm_id').val();
         var lcou_counsel_id = $('#lcou_counsel_id').val();
  
         if (lcou_law_firm_id == "0") {
          	 
     		$( "#lcou_law_firm_id" ).attr( "data-placement", "top" );
     		$( "#lcou_law_firm_id" ).attr( "data-content", "Please select law firm." );
     		$('#lcou_law_firm_id').popover('show');
			
            return false;
        }
  
       else
       {
       	$('#lcou_law_firm_id').popover('destroy');
      }
         if (lcou_counsel_id == "0") {
          	 
     		$( "#lcou_counsel_id" ).attr( "data-placement", "top" );
     		$( "#lcou_counsel_id" ).attr( "data-content", "Please select counsel name." );
     		$('#lcou_counsel_id').popover('show');
			
            return false;
        }
  
       else
       {
       	$('#lcou_counsel_id').popover('destroy');
      }
  
         if (lcou_type_of_fees == "NA") {
          	 
        		$( "#lcou_type_of_fees" ).attr( "data-placement", "top" );
        		$( "#lcou_type_of_fees" ).attr( "data-content", "Please select Type of Fees." );
        		$('#lcou_type_of_fees').popover('show');
 			
               return false;
           }
     
          else
          {
          	$('#lcou_type_of_fees').popover('destroy');
         }
        if(lcou_type_of_fees == 'appearance'){
         if (lcou_effective_non_effective == "NA") {
          	 
     		$( "#lcou_effective_non_effective" ).attr( "data-placement", "top" );
     		$( "#lcou_effective_non_effective" ).attr( "data-content", "Please select effective or non effective field." );
     		$('#lcou_effective_non_effective').popover('show');
			
            return false;
        }
        }
       else
       {
       	$('#lcou_effective_non_effective').popover('destroy');
      }
         if (lcou_counsel_fees_amount == 0) {
         	 
     		$( "#lcou_counsel_fees_amount" ).attr( "data-placement", "top" );
     		$( "#lcou_counsel_fees_amount" ).attr( "data-content", "Please enter fees amount." );
     		$('#lcou_counsel_fees_amount').popover('show');
				
            return false;
        
     	   }else{
     		   $('#lcou_counsel_fees_amount').popover('destroy');
     	   }  
         if (isNaN(lcou_counsel_fees_amount)) {
           	
         	 $("#lcou_counsel_fees_amount" ).attr( "data-placement", "top" );
              $("#lcou_counsel_fees_amount" ).attr( "data-content", "Fees amount should be numeric" );
              $('#lcou_counsel_fees_amount').popover('show');
              return false;
          }   
         else
         {
         	$('#lcou_counsel_fees_amount').popover('destroy');
         }
         if (lcou_currency == "NA" ) {
           	 
       		$( "#lcou_currency" ).attr( "data-placement", "top" );
       		$( "#lcou_currency" ).attr( "data-content", "Please select currency." );
       		$('#lcou_currency').popover('show');
     	
              return false;
          }
      	 else
           {
           	$('#lcou_currency').popover('destroy');
           }
         if (isNaN(lcou_conversion_rate)) {
           	 
       		$( "#lcou_conversion_rate" ).attr( "data-placement", "top" );
       		$( "#lcou_conversion_rate" ).attr( "data-content", "Please enter numeric rate." );
       		$('#lcou_conversion_rate').popover('show');
     	
              return false;
          }
      	 else
           {
           	$('#lcou_conversion_rate').popover('destroy');
           }
         if(lcou_conversion_rate > 0 ){
         if (lcou_converted_currency == "NA") {
           	 
       		$( "#lcou_converted_currency" ).attr( "data-placement", "top" );
       		$( "#lcou_converted_currency" ).attr( "data-content", "Please select converted currency." );
       		$('#lcou_converted_currency').popover('show');
     	
              return false;
          }
      	 else
           {
           	$('#lcou_converted_currency').popover('destroy');
           }
         }
         else
         {
         	$('#lcou_converted_currency').popover('destroy');
         }

    
    
    }
    
    