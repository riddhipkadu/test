$(document).ready(function(){
	  
    $('#arc_name').click(function(){
        $('#arc_name').popover('destroy');
    });

}); 

$("button[name='delete_Arc_name']").click(function(){
	var arc_id = $(this).val();
	items = {};
	items["arc_id"] = arc_id;
	var jsonString = JSON.stringify(items);
	
		if(arc_id>0){
					 $.ajax({
							type : "post",
							url : "./checkARCnameDependancy",
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
							
							    if(value['sarf_arc_name'] == 1 || value['mgr_arc_name'] == 1){
							    		
							    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
							    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
										$('#dialogBox .modal-body').html("<p><strong>This ARC is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
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
				    		url : "./deleteARCname",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>ARC Name deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				        			//window.location.reload(true);
				    				$('table#example tr#row_'+arc_id).remove();
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
//Is exist method for ARC Name
var arc_status;
$("#arc_name").keyup(function(){ 
	//alert(arc_name);
	  var arc_name = jQuery.trim($("#arc_name").val());
	  var arc_id = 0;

		if(arc_name !='' && arc_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["arc_id"] = arc_id;
				items["arc_name"] = arc_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isARCNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			arc_status = 0;
			$('#arc_name').popover('destroy');
		}
		else{
			arc_status = 1;
			$("#arc_name").attr("data-placement", "top");
			$("#arc_name").attr("data-content", "ARC Name is already exists..!!");
			$('#arc_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});
//Get All ARC Name
  $.ajax({
	type : "post",
	url : "./getAllArcManagerJson",
	dataType : "html",
	cache : false,
	success : function(data) {
		var list = $.parseJSON(data);
		 $( "#arc_name" ).autocomplete({
			    minLength: 0,
			    source: list,
			    focus: function( event, ui ) {
			      
			          return false;
			    },
			    select: function( event, ui ) {
			       $( "#arc_name" ).val( ui.item.label );
			       lawf_status = 1;
					$("#arc_name").attr("data-placement", "top");
					$("#arc_name").attr("data-content", "ARC Name is already exists..!!!!!");
					$('#arc_name').popover('show');
			       return false;
			    } 
			 });
	}
});
//Is exist method for Email
var email_status;
$("#arc_email_id").keyup(function(){ 
	  var arc_email_id = jQuery.trim($("#arc_email_id").val());
	  var arc_id = 0;

		if(arc_email_id !='' && arc_email_id!=null ){ // && !typeof emp_id === 'undefined'
			    items = {};
				items["arc_id"] = arc_id;
				items["arc_email_id"] = arc_email_id;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isARCNameEmailExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			email_status = 0;
			$('#arc_email_id').popover('destroy');
		}
		else{
			email_status = 1;
			$("#arc_email_id").attr("data-placement", "top");
			$("#arc_email_id").attr("data-content", "Email Id is already exists..!!");
			$('#arc_email_id').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});

function validateForm() { 
    //alert("!!!!");
    var arc_name = $('#arc_name').val();
    var arc_contact_person = $('#arc_contact_person').val();
    var arc_contact_no = $('#arc_contact_no').val();
    var arc_email_id=$('#arc_email_id').val();

//Validation for ARC Name      
    
    	if (arc_name == null || arc_name == "") {
   	 
    		$( "#arc_name" ).attr( "data-placement", "top" );
    		$( "#arc_name" ).attr( "data-content", "Please enter ARC Name Name." );
    		$('#arc_name').popover('show');
	
           return false;
       }
    	
    if(arc_status==1){
   	 $( "#arc_name" ).attr( "data-placement", "top" );
        $( "#arc_name" ).attr( "data-content", " ARC Name is already exists..!!" );
        $('#arc_name').popover('show');
        
        return false;
    }
   
	else
   {
   	$('#arc_name').popover('destroy');
   }
   
//Validation for Contact Person Name
    
    if (arc_contact_person == null || arc_contact_person == "") {
   	 
 		$( "#arc_contact_person" ).attr( "data-placement", "top" );
 		$( "#arc_contact_person" ).attr( "data-content", "Please enter Contact Person." );
 		$('#arc_contact_person').popover('show');
		
        return false;
    
 	   }
 
 	if (!(isNaN(arc_contact_person))) {
     $( "#arc_contact_person" ).attr( "data-placement", "top" );
     $( "#arc_contact_person" ).attr( "data-content", "Please enter only text." );
     $('#arc_contact_person').popover('show');
     return false;
 }
 else
 {
 	$('#arc_contact_person').popover('destroy');
 }
 
 if (!(arc_contact_person.length>2)) {
       $( "#arc_contact_person" ).attr( "data-placement", "top" );
       $( "#arc_contact_person" ).attr( "data-content", "Please enter at least three characters." );
       $('#arc_contact_person').popover('show');
       return false;
   }
   else
   {
   	$('#arc_contact_person').popover('destroy');
  }	

 //Validation for Contact Number
 if (arc_contact_no == 0)  {
   	 
		$( "#arc_contact_no" ).attr( "data-placement", "top" );
		$( "#arc_contact_no" ).attr( "data-content", "Please Enter Contact Number." );
		$('#arc_contact_no').popover('show');
	
      return false;
  }
	 else
   {
   	$('#arc_contact_no').popover('destroy');
   }
 
 if (arc_contact_no.match( /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/))
	{
     
 } else {
 	$( "#arc_contact_no" ).attr( "data-placement", "top" );
      $( "#arc_contact_no" ).attr( "data-content", "Phone number contain only 10 digit." );
      $('#arc_contact_no').popover('show');
      return false;
 }
 
 if (arc_email_id == 0)  {
 	 
		$( "#arc_email_id" ).attr( "data-placement", "top" );
		$( "#arc_email_id" ).attr( "data-content", "Please Enter Email." );
		$('#arc_email_id').popover('show');
	
      return false;
  }
	 else
   {
   	$('#arc_email_id').popover('destroy');
   }
 
 if (arc_email_id.match( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/))
	{
     
 } else {
 	$( "#arc_email_id" ).attr( "data-placement", "top" );
      $( "#arc_email_id" ).attr( "data-content", "Not a valid e-mail address." );
      $('#arc_email_id').popover('show');
      return false;
  }
 if(email_status==1){
   	 $( "#arc_email_id" ).attr( "data-placement", "top" );
        $( "#arc_email_id" ).attr( "data-content", " Email Id is already exists..!!" );
        $('#arc_email_id').popover('show');
        
        return false;
    }
   
	else
   {
   	$('#arc_email_id').popover('destroy');
   }

} 
 
 /*if (lawf_mobile_no == null || lawf_mobile_no == "") {
	 
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
*/
    	
