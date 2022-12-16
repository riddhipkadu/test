$(document).ready(function(){
	
	//set from query date as today	
	$("#from_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	//set to query date as today
	$("#to_date_div").datepicker({
		autoclose : true,
		format : "dd-mm-yyyy",
	}).datepicker();
	
// Start Date Picker for Report 
	
	$("#from_date_div").datepicker({
 
 	autoclose : true,
	format : "dd-mm-yyyy",
	viewMode : "auto",
	minViewMode : "auto",
	endDate:"now",
	todayHighlight:"true",
	showOnFocus:"true",
	defaultViewDate:"today"

}).datepicker().on('changeDate', function(e){

$('#to_date_div').datepicker({ autoclose: true}).datepicker('setStartDate', e.date).focus();
});

	$("#to_date_div").datepicker({
		 
	 	autoclose : true,
		format : "dd-mm-yyyy",
		viewMode : "auto",
		minViewMode : "auto",
		endDate:"now",
		todayHighlight:"true",
		showOnFocus:"true",
		defaultViewDate:"today"

	}).datepicker().on('changeDate', function(e){
		
		$('#from_date_div').datepicker({ autoclose: true}).datepicker('setEndDate', e.date).focus();
	});
	
	//End Date Picker 
	
});

$(document).on("click","button[name='activityLogs']",function(){
	
	var log_activity_id = $(this).val();
	var log_related_to = $('#row_'+log_activity_id).text();
	
	if(log_activity_id > 0) {
		$("#activityLogs_"+log_activity_id).hide();
		$("#loader").show();
		//$("$loader_"+log_activity_id).hide();
	var data = {};
	
	data["log_activity_id"]    = log_activity_id;
	data["log_related_to"]	   = log_related_to;
	
	var jsonString = JSON.stringify(data);
	
	$.ajax({
		type : "POST",
		url  : "./getActivityLogs",
		data : jsonString,
		contentType : "application/json",
		dataType : "json",
		success : function(result){
			var data = "";
			if(result.length != 0){
			var i = 1;
			$.each(result,function(key,value){
				data += "<tr>";
				data += "<td>"+i+" </td>";
				data +="<td>"+value['log_related_to']+" </td>";
				if(value['log_related_name'] != ""){
					data +="<td>"+value['log_related_name']+" </td>";
					}else{
						data +="<td> NA </td>";	
					}
				data +="<td>"+value['log_activity']+" </td>";
				data +="<td>"+value['log_subactivity_id']+" </td>";
				data +="<td>"+value['log_subactivity']+" </td>";
				if(value['log_assigned_to'] != ""){
				data +="<td>"+value['log_assigned_to']+" </td>";
				}else{
				data +="<td> NA </td>";	
				}
				data +="<td>"+value['log_created_at']+" </td>";
				data += "</tr>"
			i++;	
			});
		   }else{
			   data += "<tr><td colspan=8 style='text-align:center'>No result found.</td></tr>";
			   
		   }
			$("#loader").hide();
			$("#activityLogs_"+log_activity_id).show();
			$("#log_model_activity_logs").modal('show');
			$("#logResultActivity").html(data);
		}
	});	
  }
	
});


$("#btnSearch").click(function(){
	
	var log_related_to = $("#log_related_to").val();
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();

	if(log_related_to != "NA" ){
		
		$("#btnSearch").hide();
		$("#loader").show();

		items = {};
			items["log_related_to"]          = log_related_to;
			items["from_date"] 		 		 = from_date;
			items["to_date"] 		 		 = to_date;
			
			var jsonString = JSON.stringify(items);
 
			$.ajax({
				type : "post",
				url : "./searchLogs",
				contentType : "application/json",
				dataType : "json",
				data : jsonString,
				cache : false,
				success : function(result) { 
					$("#btnSearch").show();
					$("#loader").hide();
					
					var data = "";
					var i=1;
					 if(result.length != 0){ 
					$.each(result,function(key,value){
						
						var log_id = value['log_id'];
						data += "<tr>";
						data +="<td>"+i+"</td>";
						data +="<td id='row_"+value['log_activity_id']+"'>"+value['log_related_to']+" </td>";
						if(value['log_related_name'] != ""){
						data +="<td>"+value['log_related_name']+" </td>";
						}else{
							data +="<td> NA </td>";	
						}
						data +="<td>"+value['log_activity']+" </td>";
						data +="<td>"+value['log_subactivity']+" </td>";
						if(value['log_assigned_to'] != ""){
							data +="<td>"+value['log_assigned_to']+" </td>";
						}else{
							data +="<td> NA </td>";	
						}
						data +="<td>"+value['log_created_at']+" </td>";
						data +="<td id='loader_'"+value['log_activity_id']+">";
						data += "<button type='submit' id='activityLogs_"+value['log_activity_id']+"' name='activityLogs' value="+value['log_activity_id']+" class='btn btn-primary' style='margin :2px'><i class='fa fa-history'></i>&nbsp;Logs</button>";
						data +="</td>";
						data +="</tr>";
						i++;
						
					});				
					}else{
						data += "<tr><td colspan=8 style='text-align:center'>No result found..!!</td></tr> " ;
					}
					$("#searchResult").html(data); 
					
				},error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else{
			$("#log_related_to").attr("data-placement", "top");
			$("#log_related_to").attr("data-content", "Please select related to field..!!");
			$('#log_related_to').popover('show');
			return false;
		}
});
