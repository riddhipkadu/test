$(document).ready(function(){
	
    $('#cpaid_conversion_rate').change(function(){
        var lcou_conversion_rate = $(this).val();

        if(lcou_conversion_rate !=''){
        var lcou_conversion_rate = $(this).val();
        $('#cpaid_converted_amt').val($(this).val()*$("#cpaid_fees_amount").val())
    	}else{
    		$(this).val(0.0);
    	}
    	});
    $('#cpaid_fees_amount').keyup(function(){
        $('#cpaid_converted_amt').val($("#cpaid_conversion_rate").val() * $("#cpaid_fees_amount").val())
    });
   
    $("#cpaid_amt_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker("update",new Date());
    
    $("#cpaid_amt_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"

	}).datepicker({ autoclose: true}).datepicker('setEndDate', new Date()).focus();
    
   
    /*$("#cpaid_amt_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
		endDate: new Date(),
	}).datepicker("update",new Date());*/
    
    
});

var counsel_paid_fees_status;
$('#cpaid_fees_amount').on('keyup', function(){ 
	  var cpaid_fees_amount 	= jQuery.trim($("#cpaid_fees_amount").val());
	  var cpaid_counsel_fees_id = $("#cpaid_counsel_fees_id").val();

		if(cpaid_fees_amount !='' && cpaid_fees_amount!= null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["cpaid_fees_amount"] 		= cpaid_fees_amount;
				items["cpaid_counsel_fees_id"] 	= cpaid_counsel_fees_id;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isAmountGreaterThanAgreedFees",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			counsel_paid_fees_status = 0;
			$('#cpaid_fees_amount').popover('destroy');
		}
		else{
			counsel_paid_fees_status = 1;
			$("#cpaid_fees_amount").attr("data-placement", "top");
			$("#cpaid_fees_amount").attr("data-content", "Amount is greater than agreed fees..!!");
			$('#cpaid_fees_amount').popover('show');
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

$("#cpaid_currency_code").on("change", getConversionRate);
$("#cpaid_converted_currency").on("change", getConversionRate);
$("#cpaid_amt_date").on("change", getConversionRate);

function getConversionRate(){
	
	var amount_involved = $('#cpaid_fees_amount').val();
	var converted_amt_currency = $("#cpaid_converted_currency").val();
	var involved_amt_currency = $('#cpaid_currency_code').val();
	var amount_date = $('#cpaid_amt_date').val();
	var conversion_rate = $('#cpaid_conversion_rate').val();
	
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
								  $("#cpaid_conversion_rate").val(rate);
								  //var conversion_rate = 
								  $("#cpaid_converted_amt").val(rate*amount_involved);
								  if (rate == 0) {
							         	 
							      		$( "#cpaid_conversion_rate" ).attr( "data-placement", "bottom" );
							      		$( "#cpaid_conversion_rate" ).attr( "data-content", "Conversion rate is not avaliable." );
							      		$('#cpaid_conversion_rate').popover('show');
							    	
							             return false;
							         }
							     	 else
							          {
							          	$('#cpaid_conversion_rate').popover('destroy');
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
	var cpaid_invoice_no = $('#cpaid_invoice_no').val();
	var cpaid_invoice_amt = $('#cpaid_invoice_amt').val();
	var cpaid_invoice_amt_currency = $('#cpaid_invoice_amt_currency').val();
	var cpaid_invoice_date = $('#cpaid_invoice_date').val();
	 
	var cpaid_fees_amount = $('#cpaid_fees_amount').val(); 	
    var cpaid_currency_code = $('#cpaid_currency_code').val();
    var cpaid_conversion_rate = $('#cpaid_conversion_rate').val();
    var cpaid_converted_currency = $('#cpaid_converted_currency').val();
    
    if (cpaid_invoice_no == 0) {
     	 
		$( "#cpaid_invoice_no" ).attr( "data-placement", "top" );
		$( "#cpaid_invoice_no" ).attr( "data-content", "Please enter invoice number." );
		$('#cpaid_invoice_no').popover('show');
		
       return false;
   }

  else
  {
  	$('#cpaid_invoice_no').popover('destroy');
 }
    if (cpaid_invoice_amt == 0) {
     	 
		$( "#cpaid_invoice_amt" ).attr( "data-placement", "top" );
		$( "#cpaid_invoice_amt" ).attr( "data-content", "Please enter invoice amount." );
		$('#cpaid_invoice_amt').popover('show');
		
       return false;
   }

  else
  {
  	$('#cpaid_invoice_amt').popover('destroy');
 }

    if (cpaid_invoice_amt_currency == "NA") {
     	 
   		$( "#cpaid_invoice_amt_currency" ).attr( "data-placement", "top" );
   		$( "#cpaid_invoice_amt_currency" ).attr( "data-content", "Please select currency." );
   		$('#cpaid_invoice_amt_currency').popover('show');
          return false;
      }
     else
     {
     	$('#cpaid_invoice_amt_currency').popover('destroy');
    }
    if (cpaid_invoice_date == 0) {
     	 
		$( "#cpaid_invoice_date" ).attr( "data-placement", "top" );
		$( "#cpaid_invoice_date" ).attr( "data-content", "Please select invoice date" );
		$('#cpaid_invoice_date').popover('show');
		
       return false;
   }
  else
  {
  	$('#cpaid_invoice_date').popover('destroy');
  }
    
    if (counsel_paid_fees_status == 1) {
		$( "#cpaid_fees_amount" ).attr( "data-placement", "top" );
		$( "#cpaid_fees_amount" ).attr( "data-content", "Amount is greater than agreed fees..!!" );
		$('#cpaid_fees_amount').popover('show');
			
       return false;
   
	   }else{
		   $('#cpaid_fees_amount').popover('destroy');
	   } 
    
    if (cpaid_fees_amount == null || cpaid_fees_amount == 0) {
		$( "#cpaid_fees_amount" ).attr( "data-placement", "top" );
		$( "#cpaid_fees_amount" ).attr( "data-content", "Please enter fees amount." );
		$('#cpaid_fees_amount').popover('show');
			
       return false;
   
	   }else{
		   $('#cpaid_fees_amount').popover('destroy');
	   }  
    if (isNaN(cpaid_fees_amount)) {
      	
    	 $("#cpaid_fees_amount" ).attr( "data-placement", "top" );
         $("#cpaid_fees_amount" ).attr( "data-content", "Fees amount should be numeric" );
         $('#cpaid_fees_amount').popover('show');
         return false;
     }   
    else
    {
    	$('#cpaid_fees_amount').popover('destroy');
    }
    
    if (cpaid_currency_code == "NA" ) {
      	 
  		$( "#cpaid_currency_code" ).attr( "data-placement", "top" );
  		$( "#cpaid_currency_code" ).attr( "data-content", "Please select currency." );
  		$('#cpaid_currency_code').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#cpaid_currency_code').popover('destroy');
      }
    if (isNaN(cpaid_conversion_rate)) {
      	 
  		$( "#cpaid_conversion_rate" ).attr( "data-placement", "top" );
  		$( "#cpaid_conversion_rate" ).attr( "data-content", "Please enter numeric rate." );
  		$('#cpaid_conversion_rate').popover('show');
	
         return false;
     }
 	 else
      {
      	$('#cpaid_conversion_rate').popover('destroy');
      }
    if(cpaid_conversion_rate > 0 ){
    if (cpaid_converted_currency == "NA") {
      	 
  		$( "#cpaid_converted_currency" ).attr( "data-placement", "top" );
  		$( "#cpaid_converted_currency" ).attr( "data-content", "Please select converted currency." );
  		$('#cpaid_converted_currency').popover('show');
         return false;
     }
 	 else
      {
      	$('#cpaid_converted_currency').popover('destroy');
      }
    }
    else
    {
    	$('#cpaid_converted_currency').popover('destroy');
    }
}