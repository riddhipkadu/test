$(document).ready(function(){
	  
    $('#mgr_name').click(function(){
        $('#mgr_name').popover('destroy');
    });

}); 

$("button[name='delete_Arc_Manager']").click(function(){
	
	var mgr_arc_id = $(this).val();
	items = {};
	items["mgr_arc_id"] = mgr_arc_id;
	var jsonString = JSON.stringify(items);
	
		if(mgr_arc_id>0){
					 $.ajax({
							type : "post",
							url : "./checkArcManagerDependancy",
							contentType : "application/json",
							dataType : "html",
							data : jsonString,
							cache : false,
							success : function(res) {
								if(res == 1 ){
							    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
							    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
										$('#dialogBox .modal-body').html("<p><strong>This ARC Manager is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
				    				    $("#dialogBox").modal('show');	
									}else{
										bootbox.confirm("Are you sure you want to delete?", function(result) { 
							if(result==true){
							 $.ajax({
						    		type : "post",
						    		url : "./deleteArcManager",
						    		contentType : "text/html",
						    		dataType : "html",
						    		data : jsonString,
						    		cache : false,
						    		success : function(deleteCount) {
						    			if(deleteCount==1){ 
						    				$("#dialogBox .modal-header").css("background-color", "#097a09");
						    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
						    				$('#dialogBox .modal-body').html("<p><strong>ARC Manager deleted successfully!</strong></p>");
						    				$("#dialogBox").modal('show');
						        			//window.location.reload(true);
						    				$('table#example tr#row_'+mgr_arc_id).remove();
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

var mgr_arc_status;
     $("#mgr_name").keyup(function(){ 
	  var mgr_name = jQuery.trim($("#mgr_name").val());
	  var mgr_arc_id = 0;

		if(mgr_name !='' && mgr_name !=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["mgr_arc_id"] = mgr_arc_id;
				items["mgr_name"] = mgr_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isArcManagerExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		if(result==0||result=='0'){
			mgr_arc_status = 0;
			$('#mgr_name').popover('destroy');
		}
		else{
			mgr_arc_status = 1;
			$("#mgr_name").attr("data-placement", "top");
			$("#mgr_name").attr("data-content", "ARC Manager is already exists..!!");
			$('#mgr_name').popover('show');
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
		 $( "#mgr_name" ).autocomplete({
			    minLength: 0,
			    source: list,
			    focus: function( event, ui ) {
			      
			          return false;
			    },
			    select: function( event, ui ) {
			       $( "#mgr_name" ).val( ui.item.label );
			       lawf_status = 1;
					$("#mgr_name").attr("data-placement", "top");
					$("#mgr_name").attr("data-content", "ARC Manager is already exists..!!");
					$('#mgr_name').popover('show');
			       return false;
			    } 
			 });
	}
});

//Is exist method for Email
var email_status;
$("#mgr_arc_email_id").keyup(function(){ 
	  var mgr_arc_email_id = jQuery.trim($("#mgr_arc_email_id").val());
	  var arc_id = 0;

		if(mgr_arc_email_id !='' && mgr_arc_email_id!=null ){ // && !typeof emp_id === 'undefined'
			    items = {};
				items["arc_id"] = arc_id;
				items["mgr_arc_email_id"] = mgr_arc_email_id;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isARCManagerEmailExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			email_status = 0;
			$('#mgr_arc_email_id').popover('destroy');
		}
		else{
			email_status = 1;
			$("#mgr_arc_email_id").attr("data-placement", "top");
			$("#mgr_arc_email_id").attr("data-content", "Email Id is already exists..!!");
			$('#mgr_arc_email_id').popover('show');
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
    var mgr_arc_name = $('#mgr_arc_name').val();
    var mgr_name = $('#mgr_name').val();
    var mgr_arc_contact_no = $('#mgr_arc_contact_no').val();
    var mgr_arc_email_id=$('#mgr_arc_email_id').val();


    //Validation for Contact Person Name
    
    if (mgr_arc_name == 0) {
   	 
 		$( "#mgr_arc_name" ).attr( "data-placement", "Right" );
 		$( "#mgr_arc_name" ).attr( "data-content", "Please enter ARC Name." );
 		$('#mgr_arc_name').popover('show');
		
        return false;
      }
 else
 {
 	$('#mgr_arc_name').popover('destroy');
 }
    
//Validation for ARC Manager       
    
    	if (mgr_name == null || mgr_name == "") {
   	 
    		$( "#mgr_name" ).attr( "data-placement", "top" );
    		$( "#mgr_name" ).attr( "data-content", "Please enter ARC Manager." );
    		$('#mgr_name').popover('show');
	
           return false;
       }
    	
    if(mgr_arc_status==1){
   	 $( "#mgr_name" ).attr( "data-placement", "top" );
        $( "#mgr_name" ).attr( "data-content", " ARC Manager is already exists..!!" );
        $('#mgr_name').popover('show');
        
        return false;
    }
   
	else
   {
   	$('#mgr_name').popover('destroy');
   }
    
 //Validation for Contact Number
 if (mgr_arc_contact_no == 0)  {
   	 
		$( "#mgr_arc_contact_no" ).attr( "data-placement", "top" );
		$( "#mgr_arc_contact_no" ).attr( "data-content", "Please Enter Contact Number." );
		$('#mgr_arc_contact_no').popover('show');
	
      return false;
  }
	 else
   {
   	$('#mgr_arc_contact_no').popover('destroy');
   }
 
 if (mgr_arc_contact_no.match( /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/))
	{
     
 } else {
 	$( "#mgr_arc_contact_no" ).attr( "data-placement", "top" );
      $( "#mgr_arc_contact_no" ).attr( "data-content", "Phone number contain only 10 digit." );
      $('#mgr_arc_contact_no').popover('show');
      return false;
 }
 
 if (mgr_arc_email_id == 0)  {
 	 
		$( "#mgr_arc_email_id" ).attr( "data-placement", "top" );
		$( "#mgr_arc_email_id" ).attr( "data-content", "Please Enter Email." );
		$('#mgr_arc_email_id').popover('show');
	
      return false;
  }
	 else
   {
   	$('#mgr_arc_email_id').popover('destroy');
   }
 
 if (mgr_arc_email_id.match( /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/))
	{
     
 } else {
 	$( "#mgr_arc_email_id" ).attr( "data-placement", "top" );
      $( "#mgr_arc_email_id" ).attr( "data-content", "Not a valid e-mail address." );
      $('#mgr_arc_email_id').popover('show');
      return false;
  }
 
 if(email_status==1){
   	 $( "#mgr_arc_email_id" ).attr( "data-placement", "top" );
        $( "#mgr_arc_email_id" ).attr( "data-content", " Email Id is already exists..!!" );
        $('#mgr_arc_email_id').popover('show');
        
        return false;
    }
   
	else
   {
   	$('#mgr_arc_email_id').popover('destroy');
   }
} 
 
 /*if (mgr_arc_contact_no == null || mgr_arc_contact_no == "") {
	 
		$( "#mgr_arc_contact_no" ).attr( "data-placement", "top" );
		$( "#mgr_arc_contact_no" ).attr( "data-content", "Please enter Contact Number." );
		$('#mgr_arc_contact_no').popover('show');
		
      return false;
  
	   }else{
		   $('#mgr_arc_contact_no').popover('destroy');
	   }
 
 if (isNaN(mgr_arc_contact_no)) {
 	
	 $("#mgr_arc_contact_no" ).attr( "data-placement", "top" );
     $("#mgr_arc_contact_no" ).attr( "data-content", "Mobile No should be numeric" );
     $('#mgr_arc_contact_no').popover('show');
     return false;
 }   
else
{
	$('#mgr_arc_contact_no').popover('destroy');
}

 if (!(mgr_arc_contact_no.length==10)) {
     $( "#mgr_arc_contact_no" ).attr( "data-placement", "top" );
     $( "#mgr_arc_contact_no" ).attr( "data-content", "Please enter ten digits." );
     $('#mgr_arc_contact_no').popover('show');
     return false;
 }
 else
 {
 	$('#mgr_arc_contact_no').popover('destroy');
}
        
 if (mgr_arc_email_id.length != 0 ) {
 var atpos = mgr_arc_email_id.indexOf("@");
 var dotpos = mgr_arc_email_id.lastIndexOf(".");
 if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= mgr_arc_email_id.length)
 {
     $("#mgr_arc_email_id").attr("data-placement", "top");
     $("#mgr_arc_email_id").attr("data-content", "Not a valid e-mail address");
     $('#mgr_arc_email_id').popover('show');
     // alert("Not a valid e-mail address");
     return false;
 } else
 {
     $('#mgr_arc_email_id').popover('destroy');
 } 
}*/

	
