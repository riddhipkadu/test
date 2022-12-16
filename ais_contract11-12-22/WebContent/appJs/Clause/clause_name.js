/*
 * Author: Akash jadhav
 * Date: 10/12/2022
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	  
    $('#cls_name').click(function(){
        $('#cls_name').popover('destroy');
    });

});

$("button[name='delete_cls_name']").click(function(){
	
	var cls_id = $(this).val();
	items = {};
	items["cls_id"] = cls_id;
	var jsonString = JSON.stringify(items);
	
		if(cls_id > 0){

			$.ajax({
					type : "post",
					url : "./checkDependancyClausee",
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
								if(value['cls_type'] == 1 || value['exec_type'] == 1){
					    		$("#dialogBox .modal-header").css("background-color", "#e26d1c");
					    		$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-remove-sign'></span>  &nbsp;Error");
								$('#dialogBox .modal-body').html("<p><strong>This Clause is in use. Kindly make sure it is either unassigned or contact administrator..!!</strong></p>");
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
				    		url : "./deleteClause",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Clause Type deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+cls_id).remove();
				    				document.getElementById('count').innerHTML = ($('#example >tbody >tr').length);
				        			//window.location.reload(true);
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
var cls_type_status;
$("#cls_name").keyup(function(){ 
	  var cls_name = jQuery.trim($("#cls_name").val());
	  var cls_id = 0;

		if(cls_name !='' && cls_name!=null ){ // && !typeof emp_id === 'undefined'
			
			    items = {};
				items["cls_id"] = cls_id;
				items["cls_name"] = cls_name;

				var jsonString = JSON.stringify(items);
				$.ajax({
					type : "post",
					url : "./isContractTypeNameExist",
					contentType : "application/json",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(result) {
		//alert(result+" resulatt");
		if(result==0||result=='0'){
			cls_type_status = 0;
			$('#cls_name').popover('destroy');
		}
		else{
			cls_type_status = 1;
			$("#cls_name").attr("data-placement", "top");
			$("#cls_name").attr("data-content", "Clause Name is already exists..!!");
			$('#cls_name').popover('show');
			return false;
		}

	},
	error : function(xhr, ajaxOptions, thrownError) {
		$('#errors').html("There is error:" + thrownError);
	}
});
		}
});


$('#addClause').on('keyup keypress', function(e) {
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
   // console.log("Key code "+k)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
    }

//Get All Clause 
function allClause (){
$.ajax({
		type : "post",
		url : "./getAllClauseJson",
		dataType : "html",
		cache : false,
		success : function(data) {
			alert('function called')
			var list = $.parseJSON(data);
			console.log("list:"+list);
			 $( "#cls_name" ).autocomplete({
				    minLength: 0,
				    source: list,
				    focus: function( event, ui ) {
				      // $( "#liti_type_name" ).val( ui.item.label );
				          return false;
				    },
				    select: function( event, ui ) {
				       $( "#cls_name" ).val( ui.item.label );
				       cont_type_status = 1;
						$("#cls_name").attr("data-placement", "top");
						$("#cls_name").attr("data-content", "Clause name is already exists..!!");
						$('#cls_name').popover('show');
				       return false;
				    } 
				 });
		}
	});
	}
	$scope.allClause();
//ENd Get All Litigation type 

function validateForm() { 
            
             var cls_name = $('#cls_name').val();
             
             	if (cls_name == null || cls_name == "") {
            	 
             		$( "#cls_name" ).attr( "data-placement", "top" );
             		$( "#cls_name" ).attr( "data-content", "Please enter Clause name." );
             		$('#cls_name').popover('show');
				
                    return false;
                }
             	if(cls_type_status==1){
		        	 $( "#cls_name" ).attr( "data-placement", "top" );
			         $( "#cls_name" ).attr( "data-content", " Clause Name is already exists..!!" );
			         $('#cls_name').popover('show');
			         
			         return false;
		         }
               
            	else
               {
               	$('#cls_name').popover('destroy');
               }
            
            	if (!(isNaN(cls_name))) {
                $( "#cls_name" ).attr( "data-placement", "top" );
                $( "#cls_name" ).attr( "data-content", "Please enter only text." );
                $('#cls_name').popover('show');
                return false;
            }
            else
            {
            	$('#cls_name').popover('destroy');
            }
            
            if (!(cls_name.length>2)) {
                  $( "#cls_name" ).attr( "data-placement", "top" );
                  $( "#cls_name" ).attr( "data-content", "Please enter at least three characters." );
                  $('#cls_name').popover('show');
                  return false;
              }
              else
              {
              	$('#cls_name').popover('destroy');
             }
        
}
     