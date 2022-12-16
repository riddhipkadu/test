$(document).ready(function(){

    $('#apaid_conversion_rate').change(function(){
        var ladv_conversion_rate = $(this).val();

        if(ladv_conversion_rate !=''){
        var ladv_conversion_rate = $(this).val();
        $('#apaid_converted_amt').val($(this).val()*$("#apaid_fees_amount").val())
    	}else{
    		$(this).val(0.0);
    	}
    	});
    $('#apaid_fees_amount').keyup(function(){
        $('#apaid_converted_amt').val($("#apaid_conversion_rate").val()*$("#apaid_fees_amount").val())
    });
    
    $("#apaid_amt_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
    
    $("#apaid_amt_date_div").datepicker({
		 
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

var advocate_paid_fees_status;
$('#apaid_fees_amount').on('keyup', function(){
	
	  var apaid_fees_amount 	= jQuery.trim($("#apaid_fees_amount").val());
	  var apaid_advocate_fees_id = $("#apaid_advocate_fees_id").val();

		if(apaid_fees_amount !='' && apaid_fees_amount!= null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["apaid_fees_amount"] 			= apaid_fees_amount;
				items["apaid_advocate_fees_id"] 	= apaid_advocate_fees_id;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isAdvocateAmountGreaterThanAgreedFees",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			advocate_paid_fees_status = 0;
			$('#apaid_fees_amount').popover('destroy');
		}
		else{
			advocate_paid_fees_status = 1;
			$("#apaid_fees_amount").attr("data-placement", "top");
			$("#apaid_fees_amount").attr("data-content", "Amount is greater than agreed fees..!!");
			$('#apaid_fees_amount').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});

/****************************code for online converted currency rate starts here ****************************************************/

$("#apaid_currency_code").on("change", getConversionRate);
$("#apaid_converted_currency").on("change", getConversionRate);
$("#apaid_amt_date").on("change", getConversionRate);

function getConversionRate(){
	
	var amount_involved = $('#apaid_fees_amount').val();
	var converted_amt_currency = $("#apaid_converted_currency").val();
	var involved_amt_currency = $('#apaid_currency_code').val();
	var amount_date = $('#apaid_amt_date').val();
	var conversion_rate = $('#apaid_conversion_rate').val();
	
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
								  $("#apaid_conversion_rate").val(rate);
								  //var conversion_rate = 
								  $("#apaid_converted_amt").val(rate*amount_involved);
								  if (rate == 0) {
							         	 
							      		$( "#apaid_conversion_rate" ).attr( "data-placement", "bottom" );
							      		$( "#apaid_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#apaid_conversion_rate').popover('show');
							    	
							             return false;
							         }
							     	 else
							          {
							          	$('#apaid_conversion_rate').popover('destroy');
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
	var apaid_invoice_no = $('#apaid_invoice_no').val();
	var apaid_invoice_amt = $('#apaid_invoice_amt').val();
	var apaid_invoice_amt_currency = $('#apaid_invoice_amt_currency').val();
	var apaid_invoice_date = $('#apaid_invoice_date').val();
	 
	var apaid_fees_amount = $('#apaid_fees_amount').val(); 	
    var apaid_currency_code = $('#apaid_currency_code').val();
    var apaid_conversion_rate = $('#apaid_conversion_rate').val();
    var apaid_converted_currency = $('#apaid_converted_currency').val();
    
    if (apaid_invoice_no == 0) {
     	 
		$( "#apaid_invoice_no" ).attr( "data-placement", "top" );
		$( "#apaid_invoice_no" ).attr( "data-content", "Please enter invoice number." );
		$('#apaid_invoice_no').popover('show');
		
       return false;
   }

  else
  {
  	$('#apaid_invoice_no').popover('destroy');
 }
    if (apaid_invoice_amt == 0) {
     	 
		$( "#apaid_invoice_amt" ).attr( "data-placement", "top" );
		$( "#apaid_invoice_amt" ).attr( "data-content", "Please enter invoice amount." );
		$('#apaid_invoice_amt').popover('show');
		
       return false;
   }

  else
  {
  	$('#apaid_invoice_amt').popover('destroy');
 }

    if (apaid_invoice_amt_currency == "NA") {
     	 
   		$( "#apaid_invoice_amt_currency" ).attr( "data-placement", "top" );
   		$( "#apaid_invoice_amt_currency" ).attr( "data-content", "Please select currency." );
   		$('#apaid_invoice_amt_currency').popover('show');
		
          return false;
      }

     else
     {
     	$('#apaid_invoice_amt_currency').popover('destroy');
    }
    if (apaid_invoice_date == 0) {
     	 
		$( "#apaid_invoice_date" ).attr( "data-placement", "top" );
		$( "#apaid_invoice_date" ).attr( "data-content", "Please select invoice date" );
		$('#apaid_invoice_date').popover('show');
		
       return false;
   }
  else
  {
  	$('#apaid_invoice_date').popover('destroy');
 }
    if (advocate_paid_fees_status == 1) {
   	 
		$( "#apaid_fees_amount" ).attr( "data-placement", "top" );
		$( "#apaid_fees_amount" ).attr( "data-content", "Amount is greater than agreed fees..!!" );
		$('#apaid_fees_amount').popover('show');
			
       return false;
   
	   }else{
		   $('#apaid_fees_amount').popover('destroy');
	   }  
    
    if (apaid_fees_amount == null || apaid_fees_amount == 0) {
    	 
		$( "#apaid_fees_amount" ).attr( "data-placement", "top" );
		$( "#apaid_fees_amount" ).attr( "data-content", "Please enter fees amount." );
		$('#apaid_fees_amount').popover('show');
			
       return false;
   
	   }else{
		   $('#apaid_fees_amount').popover('destroy');
	   }  
    if (isNaN(apaid_fees_amount)) {
      	
    	 $("#apaid_fees_amount" ).attr( "data-placement", "top" );
         $("#apaid_fees_amount" ).attr( "data-content", "Fees amount should be numeric" );
         $('#apaid_fees_amount').popover('show');
         return false;
     }   
    else
    {
    	$('#lcou_counsel_fees_amount').popover('destroy');
    }
    if (apaid_currency_code == "NA" ) {
      	 
  		$( "#apaid_currency_code" ).attr( "data-placement", "top" );
  		$( "#apaid_currency_code" ).attr( "data-content", "Please select currency." );
  		$('#apaid_currency_code').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#apaid_currency_code').popover('destroy');
      }
    if (isNaN(apaid_conversion_rate)) {
      	 
  		$( "#apaid_conversion_rate" ).attr( "data-placement", "top" );
  		$( "#apaid_conversion_rate" ).attr( "data-content", "Please enter numeric rate." );
  		$('#apaid_conversion_rate').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#apaid_conversion_rate').popover('destroy');
      }
    if(apaid_conversion_rate > 0 ){
    if (apaid_converted_currency == "NA") {
      	 
  		$( "#apaid_converted_currency" ).attr( "data-placement", "top" );
  		$( "#apaid_converted_currency" ).attr( "data-content", "Please select converted currency." );
  		$('#apaid_converted_currency').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#apaid_converted_currency').popover('destroy');
      }
    }
    else
    {
    	$('#apaid_converted_currency').popover('destroy');
    }

	
	
}