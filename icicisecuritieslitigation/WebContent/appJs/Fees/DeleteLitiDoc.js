/*
 * Author: Tejashri Zurunge
 * Date: 10/03/2016
 * Updated By:
 * Updated Date:
 * 
 * */

$(document).ready(function(){

});

function deleteLitiFeesDoc(fdoc_id){
	var fdoc_id = fdoc_id;
	items = {};
	items["fdoc_id"] = fdoc_id;
	var jsonString = JSON.stringify(items);
	
		if(fdoc_id > 0){
			 bootbox.confirm("Are you sure you want to delete?", function(result) { 
				 if(result==true){
					 $.ajax({
				    		type : "post",
				    		url : "./deleteLitiFeesDoc",
				    		contentType : "text/html",
				    		dataType : "html",
				    		data : jsonString,
				    		cache : false,
				    		success : function(deleteCount) {
				    			if(deleteCount==1){ 
				    				$("#dialogBox .modal-header").css("background-color", "#097a09");
				    				$('#dialogBox .modal-title').html("<span class='glyphicon glyphicon-ok'></span>  &nbsp;Success");
				    				$('#dialogBox .modal-body').html("<p><strong>Document deleted successfully!</strong></p>");
				    				$("#dialogBox").modal('show');
				    				$("#doc_attached_" + fdoc_id).remove();
				    			}
				    			
				    		}
					 });
				 }
			 });
		}
}
