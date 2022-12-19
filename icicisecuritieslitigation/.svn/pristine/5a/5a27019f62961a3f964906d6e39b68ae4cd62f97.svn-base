/*
 * Author: Tejashri Zurunge
 * Date: 20/09/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){
	//$("#sfco_entity_id").on('change', getAllLocationForOrganization);
	//$("#sfco_unit_id").on('change', getAllDepartmentsByLocation);
	  
    $('#sfco_name').click(function(){
        $('#sfco_name').popover('destroy');
    });

    $("#sfco_type_id").multiselect({
		buttonWidth: '445px',
	    enableFiltering: true,
	    filterBehavior: 'text',
	    enableCaseInsensitiveFiltering: true,
	    maxHeight : 150,
	});
}); 

var termFileCount = 1;
function addContractFileInput(){
	$("#fileContiner_Contract").append('<div class="col-md-6" id="termFile'+termFileCount+'">'
			+'<div class="form-group">'
	        +'<label class="col-sm-4 control-label">document:</label>'
	        +'<div class="col-sm-2">'
	        +'<input type="file" name="sfco_doc" class="file-loading" />'
	        +'</div>'
	        +'<div class="col-sm-4" style="text-align: right; cursor: pointer;">'
	        +'<i class="glyphicon glyphicon-remove-circle"	onclick="deleteTermRow('+termFileCount+');" style="color: red;" title="Delete"></i>'
	        +'</div>'
	        +'</div>'
	        +'</div>'
			);
	termFileCount++;
}
function deleteTermRow(filecount) {
	$("#termFile" + filecount).remove();
}

$("button[name='delete_sfco']").click(function(){
	
	var sfco_id = $(this).val();
	//console.log("area_expe_id "+area_expe_id);
	items = {};
	items["sfco_id"] = sfco_id;
	var jsonString = JSON.stringify(items);
	
		if(sfco_id > 0){
				 							
						bootbox.confirm("Are you sure you want to delete?", function(result) { 
							 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteStandardFormContracts",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Standard Form Contracts deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$('table#example tr#row_'+sfco_id).remove();
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
		
});
	//<-----------------------------------------code for delete document starts here------------------------------------------------>

$(document).on("click","button[name='delete_sfco_document']",function(){
	var sfco_doc_id = $(this).val();

	//console.log("liti_id "+liti_id);
	items = {};
	items["sfco_doc_id"] = sfco_doc_id;
	var jsonString = JSON.stringify(items);

	if(sfco_doc_id>0){
		bootbox.confirm("Are you sure you want to delete?", function(result) { 
			if(result==true){
				$.ajax({
					type : "post",
					url : "./deleteSfcoDocument",
					contentType : "text/html",
					dataType : "html",
					data : jsonString,
					cache : false,
					success : function(deleteCount) {
						if(deleteCount=="Success"){ 
							$("#dialogBox .modal-header").css("background-color", "#097a09");
							$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
							$('#dialogBox .modal-body').html("<p><strong>Standard form contract Document deleted successfully!</strong></p>");
							$("#dialogBox").modal('show');
							//window.location.reload(true);
							//getAllLitigationDocuments();
							$('table#example tr#row_'+sfco_doc_id).remove();
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

});

//<-----------------------------------------Code for delete document ends here------------------------------------------->

function getAllLocationForOrganization() { 
	var user_orga_id = $("#sfco_entity_id").val();
	if (user_orga_id > 0) {
		
		$.ajax({
			type : "post",
			url : "./getLocationByOrgaUserId",
			//contentType : "application/json",
			dataType : "json",
			data : {orga_id :user_orga_id },
			cache : false,
			success : function(locationlist) {
				data ="";
				data +='<option value = 0>--Select--</option>';

				$.each(locationlist,function(key,value){
					data += "<option value="+value['loca_id']+">"+value['loca_name']+"</option>";
				});
				$("#sfco_unit_id").html(data);	
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$('#errors').html("There is error:" + thrownError);
			}
		});
} else {
$('#sfco_unit_id').html('<option value="0">--Select--</option>');
$('#sfco_function_id').html('<option value="0">--Select--</option>');
}
}

	function getAllDepartmentsByLocation() {
		//alert("In function");
		var user_orga_id = $("#sfco_entity_id").val();
		var user_loca_id = $("#sfco_unit_id").val();
		if (user_loca_id > 0) {
			
			$.ajax({
				type : "post",
				url : "./getDepartmentsByLocaUserId",
				//contentType : "application/json",
				dataType : "json",
				data : {loca_id : user_loca_id, orga_id: user_orga_id},
				cache : false,
				success : function(departmentlist) {
					var data ="";
					data +="<option value= 0>--Select--</option>";
					
					$.each(departmentlist,function(key,value){
						//console.log("In loop");
						data += "<option value="+value['dept_id']+">"+value['dept_name']+"</option>";
					});
					$("#sfco_function_id").html(data);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
}
else {
	$('#sfco_function_id').html('<option value="0">--Select--</option>');
}
}

function blockSpecialChar(e){
	
    var k;
    document.all ? k = e.keyCode : k = e.which;
    console.log("Key code "+k)
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k <= 48 && k >= 57));
    }


function validateForm() { 
            
			//var sfco_entity_id = $('#sfco_entity_id').val();
			//var sfco_unit_id = $('#sfco_unit_id').val();
			//var sfco_function_id = $('#sfco_function_id').val();
    		var sfco_name = $('#sfco_name').val();
            var sfco_type_id = $('#sfco_type_id').val();
            var sfco_abbreviation = $('#sfco_abbreviation').val();
             
             	if (sfco_name == null || sfco_name == "") {
            	 
             		$( "#sfco_name" ).attr( "data-placement", "top" );
             		$( "#sfco_name" ).attr( "data-content", "Please enter Standard Form Contract." );
             		$('#sfco_name').popover('show');
				
                    return false;
                }
             	
             	if (!(isNaN(sfco_name))) {
                 $( "#sfco_name" ).attr( "data-placement", "top" );
                 $( "#sfco_name" ).attr( "data-content", "Please enter only text." );
                 $('#sfco_name').popover('show');
                 return false;
             }
             else
             {
             	$('#sfco_name').popover('destroy');
             }
             
             if (!(sfco_name.length>2)) {
                   $( "#sfco_name" ).attr( "data-placement", "top" );
                   $( "#sfco_name" ).attr( "data-content", "Please enter at least three characters." );
                   $('#sfco_name').popover('show');
                   return false;
               }
               else
               {
               	$('#sfco_name').popover('destroy');
              }
             if (sfco_type_id == 0 ) {
               	 
          		$( "#sfco_type_id" ).attr( "data-placement", "top" );
          		$( "#sfco_type_id" ).attr( "data-content", "Please select SFC type." );
          		$('#sfco_type_id').popover('show');
     	
                 return false;
             } else {
              	$('#sfco_type_id').popover('destroy');
             }
             
             if (sfco_abbreviation == null || sfco_abbreviation == "") {
            	 
          		$( "#sfco_abbreviation" ).attr( "data-placement", "top" );
          		$( "#sfco_abbreviation" ).attr( "data-content", "Please enter abbreviation." );
          		$('#sfco_abbreviation').popover('show');
				
                 return false;
             } else {
               	$('#sfco_abbreviation').popover('destroy');
              }
             
             //if(area_expe_name)
		}
