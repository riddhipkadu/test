$(document).ready(function(){
	
});

var filecount = 1;
	function addFileInput() {
	   // if(filecount <=5){
		$('#filesContainer')
		.append(
				"<div class='form-group' id='file"+filecount+"'>"
				+"<label class='col-sm-5 control-label'>Upload Document :</label>"
				+"<div class='col-sm-6'><input type='file' name='amend_documents' class='file-loading'/>"
				+"</div>"
				+"<div class='col-sm-1' style='text-align: right;'>"
				+"<i class='glyphicon glyphicon-remove-circle' onclick='deleteRow("+filecount+");' style='color: red;cursor:pointer;' title='Delete'></i>"
				+"</div></div>");
		filecount++;
	    //}
	}

	function deleteRow(filecount) {
		$("#file" + filecount).remove();
	}