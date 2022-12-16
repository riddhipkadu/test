$(document).ready(function(){
	$("#orga_id").on('change', getAllLocationForOrganizationLiti);
	$("#loca_id").on('change', getAllDepartmentsByLocationLiti);
	$("#info").hide();
	function getAllLocationForOrganizationLiti() { 
		var user_orga_id = $("#orga_id").val();
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
					$("#loca_id").html(data);	
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		} else {
			$('#loca_id').html('<option value="0">--Select--</option>');
			$('#dept_id').html('<option value="0">--Select--</option>');
		}
	}

	function getAllDepartmentsByLocationLiti() {
		var user_orga_id = $("#orga_id").val();
		var user_loca_id = $("#loca_id").val();
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
					$("#dept_id").html(data);
				},
				error : function(xhr, ajaxOptions, thrownError) {
					$('#errors').html("There is error:" + thrownError);
				}
			});
		}
		else {
			$('#dept_id').html('<option value="0">--Select--</option>');
		}
	}
	
	//Search litigation
	$("#liti_calendar").click(function(){
		
		var orga_id = $("#orga_id").val();
		var loca_id = $("#loca_id").val();
		var dept_id = $("#dept_id").val();
		var liti_assigned_to = $("#liti_assigned_to").val();
		if(orga_id!=0){
			$("#info").hide();
			$("#loader_table").show();
			$("#liti_calendar").hide();
			var data = {};
			data['orga_id'] = orga_id;
			data['loca_id'] = loca_id;
			data['dept_id'] = dept_id;
			data['liti_assigned_to'] = liti_assigned_to;
			var jsonString = JSON.stringify(data);
			$.ajax({
				type : "POST",
				url  : "./searchLitigationForCalendar",
				data : jsonString,
				contentType : "application/json",
				dataType : "json",
				success  : function(result){
					
					$("#loader_table").hide();
					var eventsAdd = new Array();
					if(result.length!=0){
					$.each(result,function(key,value){
						var HearingDate = new Date(value["liti_next_hearing_date"].replace(/ /g,'T'));
						event = new Object();
						event.title = "Litigation Id[" + value["liti_client_id"] + "]";
						event.start = HearingDate; 
						event.allDay = true;
						event.url = './litigationDetails?liti_id='+ value["liti_id"];
						
						eventsAdd.push(event);
						
					});
					
					}else{
						$("#info").show();
					}
					$("#loader_table").hide();
					$("#liti_calendar").show();
					$('#calendar').fullCalendar( 'removeEvents');
					$('#calendar').fullCalendar('addEventSource',eventsAdd);
				}
				
			});
			
		}else{
			$("#orga_id").attr("data-placement", "top");
			$("#orga_id").attr("data-content", "Please select entity.");
			$('#orga_id').popover('show');
		}
	});
});	