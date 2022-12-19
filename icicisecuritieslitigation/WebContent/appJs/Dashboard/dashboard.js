/**
 * Author : Tejashri Zurunge
 * Created At : 18-04-2018
 *  Purpose : Separate internal & embeded JS Scripts separatly
 *  ************************************
 */
var StatisticsValues = '';
var StatisticsEntity = '';
var StatisticsLocation = '';
function ExportStatistics(){
	if(StatisticsEntity ==''){
		StatisticsEntity = 'Sun Pharma Advance Recharge Center';
	}
	if(StatisticsLocation==''){
		
		StatisticsLocation = 'All';
	}
	var SrNo = 1;
	var ExcelOutput = '<div class="row"><div class="table-responsive"><table id="StatisticsData" class="table table-bordered table-hover table-striped" style="text-align:center;" align="center">';
	ExcelOutput += '<tr><th>Sr No.</th><th>Entity</th><th>Unit</th><th>Function</th><th>Complied</th><th>Delayed</th><th>Non-Complied</th><th>Posing</th></tr>';
	for(var key in StatisticsValues){
		var totalCount = StatisticsValues[key][0] + StatisticsValues[key][1] + StatisticsValues[key][2] + StatisticsValues[key][3]
		if(totalCount == "0"){
			totalCount = 1;
		}	
		
		ExcelOutput += '<tr>';
		ExcelOutput += '<td>'+(SrNo++)+'</td><td>'+StatisticsEntity+'</td><td>'+StatisticsLocation+'</td><td>'+key+'</td><td>'+((StatisticsValues[key][0] / totalCount)*100).toFixed(2)+'% </td><td>'+((StatisticsValues[key][1] / totalCount)*100).toFixed(2)+'%</td><td>'+((StatisticsValues[key][2] / totalCount)*100).toFixed(2)+'% </td><td>'+((StatisticsValues[key][3] / totalCount)*100).toFixed(2)+'%</td>';
		ExcelOutput += '</tr>';
	}
	ExcelOutput += '</table>';
	ExcelOutput += '<table class="table table-striped table-bordered"><tr><td><center><button id="ExportStatisticsTable" name="ExportTable" class="btn btn-success"> <i class="fa fa-file-excel-o" aria-hidden="true"></i> Export Statistic  </button></center></td></tr>';
	ExcelOutput += '</table></div></div>';
	bootbox.dialog({'title':'Statistics','message':ExcelOutput});
	$("#ExportStatisticsTable").on('click',function(){
		var Curr_Date = new Date().toJSON().slice(0,10);
		$("#StatisticsData").table2excel({		
			name: "Statistics",
			filename: "Statistics_Report_"+Curr_Date
		});
	});
}


function getInternanalCompliaceGraph(){
	$("#internalCompliancePieChart").hide();
	$("#internalCompLoader").show();


	$.ajax({
		type : "POST",
		url : "./getInternamCompGraph",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			$("#internalCompLoader").hide();
			$("#internalCompliancePieChart").show();
			var data = response;
			data = data.IntCompData;
			var key = Object.keys(data);
			var intCompData= [];
			for(var j=0; j < 4; j++){
				var str = [];
				for(var i=0; i < Object.keys(data).length; i++){
					str.push(data[key[i]][j]); 
				}
				intCompData.push(str);
			}
			var compliedTask = intCompData[0];
			var posingTask =  intCompData[1];
			var nonCompliedTask = intCompData[2];
			var delayedTask = intCompData[3];
			var background = {
					type: 'linearGradient',
					x0: 0,
					y0: 0,
					x1: 0,
					y1: 1,
					colorStops: [{offset: 0, color: '#d2e6c9'},
					             {offset: 1, color: 'white'}]
			};



			$('#internalCompliace').jqChart({
				orientation: 'horizontal',
				width: 450,
				height: 400,
				border: {strokeStyle: '#6ba851'},
				background: background,
				animation: {duration: 1},
				shadows: {
					enabled: true
				},
				series: [
				         {
				        	 type: 'pie',
				        	 fillStyles: ['#00CC33', '#FFFF00', '#FF0000 ', '#BFBFBF'],
				        	 labels: {
				        		 stringFormat: '%.1f%%',
				        		 valueType: 'percentage',
				        		 font: '15px sans-serif',
				        		 fillStyle: 'white'
				        	 },
				        	 explodedRadius: 10,
				        	 explodedSlices: [4],
				        	 data: [['Complied', compliedTask], ['Posing', posingTask], ['Non-Complied', nonCompliedTask], ['Delayed', delayedTask]]
				         }
				         ]
			});
		}
	});

}

function getInternanalCompliaceGraphImpact(){
	$("#internalCompliancePieChart").hide();
	$("#internalCompLoader").show();
	items = {};
	items['taskImpact'] = $("#GraphByImpact").val();
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "POST",
		url : "./getIntCompChartGraphByImpact",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : jsonString,
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			$("#internalCompLoader").hide();
			$("#internalCompliancePieChart").show();
			var data = response;
			data = data.IntCompData;
			var key = Object.keys(data);
			var intCompData= [];
			for(var j=0; j < 4; j++){
				var str = [];
				for(var i=0; i < Object.keys(data).length; i++){
					str.push(data[key[i]][j]); 
				}
				intCompData.push(str);
			}
			var compliedTask = intCompData[0];
			var posingTask =  intCompData[1];
			var nonCompliedTask = intCompData[2];
			var delayedTask = intCompData[3];
			var background = {
					type: 'linearGradient',
					x0: 0,
					y0: 0,
					x1: 0,
					y1: 1,
					colorStops: [{offset: 0, color: '#d2e6c9'},
					             {offset: 1, color: 'white'}]
			};



			$('#internalCompliace').jqChart({
				orientation: 'horizontal',
				width: 450,
				height: 400,
				border: {strokeStyle: '#6ba851'},
				background: background,
				animation: {duration: 1},
				shadows: {
					enabled: true
				},
				series: [
				         {
				        	 type: 'pie',
				        	 fillStyles: ['#00CC33', '#FFFF00', '#FF0000 ', '#BFBFBF'],
				        	 labels: {
				        		 stringFormat: '%.1f%%',
				        		 valueType: 'percentage',
				        		 font: '15px sans-serif',
				        		 fillStyle: 'white'
				        	 },
				        	 explodedRadius: 10,
				        	 explodedSlices: [4],
				        	 data: [['Complied', compliedTask], ['Posing', posingTask], ['Non-Complied', nonCompliedTask], ['Delayed', delayedTask]]
				         }
				         ]
			});
		}
	});

}



function getEntityGraph() {
	$("#entityChart").hide();
	var dataArray = {};
	if ($("#orgList1").val() == 0) {
		$( "#orgList1" ).attr( "data-placement", "top" );
    	$( "#orgList1" ).attr( "data-content", "Select Entity" );
     	$('#orgList1').popover('show');
		$("#entityChart").show();
	} else {
		$("#entLoader").show();
		$('#orgList1').popover('destroy');
		dataArray['org'] = $("#orgList1").val();
		$.ajax({
			type : "POST",
			url : "./getEntityGraph",
			data : JSON.stringify(dataArray),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				$("#entLoader").hide();
				$("#entityChart").show();
				
				var data = response;
				data = data.locGraph;
				var keys = Object.keys(data);
				var locData = [];
				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					locData.push(str);
				}
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				$('#entityLevel').jqChart({
					orientation: 'horizontal',
				     width: 1150,
				     height: 300,
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : locData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : locData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : locData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : locData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
			}
		});

	}
}

function getLocationGraph() {
	$("#locGraph").hide();
	var dataArray = {};
	if ($("#orgList2").val() == 0) {
		$( "#orgList2" ).attr( "data-placement", "top" );
    	$( "#orgList2" ).attr( "data-content", "Select Entity" );
     	$('#orgList2').popover('show');
     	$("#locGraph").show();
	} else {
		$('#orgList2').popover('destroy');
		$("#locLoader").show();
		dataArray['org'] = $("#orgList2").val();
		$.ajax({
			type : "POST",
			url : "./getLocationGraph",
			data : JSON.stringify(dataArray),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				$("#locLoader").hide();
				$("#locGraph").show();
				
				var data = response;
				data = data.locGraph;
				var keys = Object.keys(data);
				var locData = [];

				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					locData.push(str);
				}
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				$('#LocationBarChart').jqChart({
					orientation: 'horizontal',
					 width: 1150,
				     height: 300,
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : locData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : locData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : locData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : locData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
			}
		});
	}
}

function getDepartmentGraph() {
	$("#deptGraph").hide();
	
	var dataArray = {};

	if ($("#orgList3").val() === 0 || $("#locList2").val() === "0") {
		$( "#orgList3" ).attr( "data-placement", "top" );
    	$( "#orgList3" ).attr( "data-content", "Select Entity For Unit" );
     	$('#orgList3').popover('show');
     	$("#deptGraph").show();
    	
	} else {
		$('#orgList3').popover('destroy');
		$("#deptLoader").show();
		dataArray['org'] = $("#orgList3").val();
		dataArray['loc'] = $("#locList2").val();
		
		StatisticsEntity = $("#orgList3 option:selected").text();
		StatisticsLocation = $("#locList2 option:selected").text();
		
		$.ajax({
			type : "POST",
			url : "./getDepartmentGraph",
			data : JSON.stringify(dataArray),
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				$("#deptLoader").hide();
				$("#deptGraph").show();
				var data = response;
				data = data.deptGraph;
				
				var keys = Object.keys(data);
				var locData = [];

				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					locData.push(str);
				}
				
				StatisticsValues = data;
				
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				$('#DepartmentBarChart').jqChart({
					orientation: 'horizontal',
					 width: 1150,
				     height: 300,
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : locData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : locData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : locData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : locData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
			}
		});
	}
}
//For Type of Task Graph
//
//function getTaskTypeGraph(){
//	var dataArray = {};
//	if ($("#typeOfTask").val() === 0) {
//		$( "#typeOfTask" ).attr( "data-placement", "top" );
//    	$( "#typeOfTask" ).attr( "data-content", "Please Select Type" );
//     	$('#typeOfTask').popover('show');
//		
//	} else {
//		$('#typeOfTask').popover('destroy');
//		dataArray['typeOfTask'] = $("#typeOfTask").val();
//		$.ajax({
//			type : "POST",
//			url : "./getTaskTypeGraph",
//			data : JSON.stringify(dataArray),
//			dataType : "json",
//			contentType : "application/json; charset=utf-8",
//			success : function(response) {
//				var data = response;
//				data = data.typeGraph;
//				var keys = Object.keys(data);
//				var typeData = [];
//
//				for (var j = 0; j < 4; j++) {
//					var str = [];
//					for (var i = 0; i < Object.keys(data).length; i++) {
//						str.push(data[keys[i]][j]);
//					}
//					typeData.push(str);
//				}
//				var background = {
//						type : 'linearGradient',
//						x0 : 0,
//						y0 : 0,
//						x1 : 0,
//						y1 : 1,
//						colorStops : [ {
//							offset : 0,
//							color : '#d2e6c9'
//						}, {
//							offset : 1,
//							color : 'white'
//						} ]
//				};
//				$('#TaskTypeLevel').jqChart({
//					orientation: 'horizontal',
//					 width: 1150,
//				     height: 300,
//					legend : {
//						customItems : [ {
//							text : {
//								text : 'Complied'
//							},
//							marker : {
//								fillStyle : '#00CC33',
//								type : 'circle'
//							}
//						}, {
//							text : {
//								text : 'Posing'
//							},
//							marker : {
//								fillStyle : '#FFFF00',
//								type : 'circle'
//							}
//						}, {
//							text : {
//								text : 'Non-Complied'
//							},
//							marker : {
//								fillStyle : '#FF0000',
//								type : 'circle'
//							}
//						}, {
//							text : {
//								text : 'Delayed'
//							},
//							marker : {
//								fillStyle : '#BFBFBF',
//								type : 'circle'
//							}
//						} ]
//					},
//					border : {
//						strokeStyle : '#6ba851'
//					},
//					background : background,
//					animation : {
//						duration : 1
//					},
//					shadows : {
//						enabled : true
//					},
//					axes : [ {
//						type : 'category',
//						location : 'bottom',
//						categories : keys
//					}, {
//						type : 'linearGradient',
//						location : 'left',
//						minimum : 0,
//						maximum : 100,
//						interval : 10,
//						labels : {
//							stringFormat : '%.1f%%'
//						}
//					} ],
//					series : [ {
//						type : 'stacked100Column',
//						title : 'Complied',
//						fillStyles : [ '#00CC33' ],
//						data : typeData[0],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					}, {
//						type : 'stacked100Column',
//						title : 'Posing',
//						fillStyles : [ '#FFFF00' ],
//						data : typeData[1],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					}, {
//						type : 'stacked100Column',
//						title : 'Non-Complied',
//						fillStyles : [ '#FF0000' ],
//						data : typeData[2],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					}, {
//						type : 'stacked100Column',
//						title : 'Delayed',
//						fillStyles : [ '#BFBFBF' ],
//						data : typeData[3],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					} ]
//				});
//			}
//		});
//	}
//	$("#typeOfTask").val(0);
//}



//load pie Chart Graph
function getPieChart(){
	
	$("#overAllPieChart").hide();
	
	
	$.ajax({
		type : "POST",
		url : "./getOverallLitigationStatusGraph",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			$("#pieLoader").hide();
			$("#overAllPieChart").show();
	var data = response;
	data = data.OrgData;
	var key = Object.keys(data);
	var ogrData= [];
	  for(var j=0; j < 5; j++){
		  var str = [];
		  for(var i=0; i < Object.keys(data).length; i++){
			  		str.push(data[key[i]][j]); 
		  }
		  ogrData.push(str);
	  }
	var pendingliti = ogrData[0];
	var againstliti =  ogrData[1];
	var infavourliti = ogrData[2];
	var settleliti = ogrData[3];
	var withdrawliti = ogrData[4];
    var background = {
      type: 'linearGradient',
      x0: 0,
      y0: 0,
      x1: 0,
      y1: 1,
      colorStops: [{offset: 0, color: '#d2e6c9'},
          {offset: 1, color: 'white'}]
  };
  
	

  $('#jqChart1').jqChart({
	  orientation: 'horizontal',
	     width: 500,
	     height: 300,
      border: {strokeStyle: '#6ba851'},
      background: background,
      animation: {duration: 1},
      shadows: {
          enabled: true
      },
      series: [
          {
              type: 'pie',
              fillStyles: ['#00CC33', '#FFFF00', '#FF0000 ', '#BFBFBF', '#ffc1cc'],
              labels: {
                  stringFormat: '%.1f%%',
                  valueType: 'percentage',
                  font: '15px sans-serif',
                  fillStyle: 'white'
              },
              explodedRadius: 10,
              explodedSlices: [5],
              data: [['Pending', pendingliti], ['In Favour', infavourliti], ['Against', againstliti], ['Settled', settleliti], ['Withdrawn', withdrawliti] ]
          }
          
         /* var pendingliti = ogrData[0];
      	var againstliti =  ogrData[1];
      	var infavourliti = ogrData[2];
      	var settleliti = ogrData[3];
      	var withdrawliti = ogrData[4];*/
          
      ]
  });
		}
	});
}   

function getDetailDataLitigationByStatus(liti_litigation_result){
	//alert(liti_litigation_result);
	var liti_litigation_result = liti_litigation_result;
	
	items = {};
	items["liti_litigation_result"] = liti_litigation_result;
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./getAllLitigationDataByStatusPieChart",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			var data_body = "";
			var res = $.parseJSON(response);
			var flag = 0;
			if(res.length != 0){
				data_body += '<table class="table table-striped table-bordered" width="100%" cellspacing="0"><thead><tr style="background:#a72f14;color:#fff;">'
					+ '<th>Litigation Id </th>'
					+ '<th>By/Against</th>     '
					+ '<th>Case Category</th>     '
					+ '<th>Case Title</th>     '
					+ '<th>Case Reference No.</th>     '
					+ ' </tr></thead>'
					+ '<tbody>';
				//alert("response body length :"+res.length);
				$.each(res, function(key,value){
					
					data_body += 
							 '<tr>'
							+ '<td><a href="litigationDetails?liti_id='+value['liti_id']+'" style="color:brown;">'+value['liti_client_id']+'</a></td>'
							+ '<td>'+value['by_against']+'</td>     '
							+ '<td>'+value['liti_type_id']+'</td>     '
							+ '<td>'+value['case_title']+'</td>     '
							+ '<td>'+value['case_ref_no']+'</td>     '
							+ ' </tr>';
					
				});
				data_body += '</tbody></table>';
					
					$("#dialogBox .modal-header").css("background-color", "#dca732");
		    		$('#dialogBox .modal-title').html("&nbsp;Litigation List");
					$('#dialogBox .modal-body').html(data_body);
				    $("#dialogBox").modal('show');	
			}
		}
	});
}


function getAllLitigationByCaseCategory(caseCategory, LitigationStatus){
	
	//alert(caseCategory + " and "+LitigationStatus)
		items = {};
		
		items['caseCategory'] = caseCategory;
		items['LitigationStatus'] = LitigationStatus;
		var jsonString = JSON.stringify(items);
		
		$.ajax({
			type : "post",
			url : "./getAllLitigationByCaseCategory",
			contentType : "application/json",
			dataType : "html",
			data : jsonString,
			cache : false,
			success : function(response) {
				var data_body = "";
				var res = $.parseJSON(response);
				var flag = 0;
				if(res.length != 0){
					data_body += '<table class="table table-striped table-bordered" width="100%" cellspacing="0"><thead><tr style="background:#a72f14;color:#fff;">'
						+ '<th>Litigation Id </th>'
						+ '<th>By/Against</th>     '
						+ '<th>Case Category</th>     '
						+ '<th>Case Title</th>     '
						+ '<th>Case Reference No.</th>     '
						+ ' </tr></thead>'
						+ '<tbody>';
					//alert("response body length :"+res.length);
					$.each(res, function(key,value){
						
						data_body += 
								 '<tr>'
								+ '<td><a href="litigationDetails?liti_id='+value['liti_id']+'" style="color:brown;">'+value['liti_client_id']+'</a></td>'
								+ '<td>'+value['by_against']+'</td>     '
								+ '<td>'+value['liti_type_id']+'</td>     '
								+ '<td>'+value['case_title']+'</td>     '
								+ '<td>'+value['case_ref_no']+'</td>     '
								+ ' </tr>';
						
					});
					data_body += '</tbody></table>';
						
						$("#dialogBox .modal-header").css("background-color", "#dca732");
			    		$('#dialogBox .modal-title').html("&nbsp;Litigation List");
						$('#dialogBox .modal-body').html(data_body);
					    $("#dialogBox").modal('show');	
				}
			}
		});
}



//Load location level Graph
 	function getLocationLevelgraph(){
 		$("#locGraph").hide();
		$("#locLoader").show();
		$.ajax({
			type : "POST",
			url : "./getLocationLevelGraph",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				
				$("#locLoader").hide();
				$("#locGraph").show();
				
				var data = response;
				data = data.locData;
				var keys = Object.keys(data);
				var locData = [];

				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					locData.push(str);
				}
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				$('#LocationBarChart').jqChart({
					orientation: 'horizontal',
				     width: 1150,
				     height: 300,
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : locData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : locData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : locData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : locData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
			}
		});
	}

 	//Load Department level Graph
	function getDepartmentLevelgraph(){
		$("#deptGraph").hide();
		$("#deptLoader").show();
		$.ajax({
			type : "POST",
			url : "./getDepartmentLevelGraph",
			dataType : "json",
			contentType : "application/json; charset=utf-8",
			success : function(response) {
				
				$("#deptLoader").hide();
				$("#deptGraph").show();
				
				var data = response.deptData; 
				var keys = Object.keys(data);
				var locData = [];

				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					locData.push(str);
				}
				
				StatisticsValues = data;
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				$('#DepartmentBarChart').jqChart({
					 orientation: 'horizontal',
				     width: 1150,
				     height: 300,
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : locData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : locData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : locData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : locData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
				$('#DepartmentBarChart').resizable();
			}
		});
	}

	//Load Type Of Task
//	function getTypeOfTaskGraph(){
//		 $("#taskDiv").show();
//		$.ajax({
//			type : "POST",
//			url : "./getTaskTypelevelGraph",
//			dataType : "json",
//			contentType : "application/json; charset=utf-8",
//			success : function(response) {
//				var data = response;
//				data = data.taskTypeData;
//				var keys = Object.keys(data);
//				var typeData = [];
//
//				for (var j = 0; j < 4; j++) {
//					var str = [];
//					for (var i = 0; i < Object.keys(data).length; i++) {
//						str.push(data[keys[i]][j]);
//					}
//					typeData.push(str);
//				}
//				var background = {
//						type : 'linearGradient',
//						x0 : 0,
//						y0 : 0,
//						x1 : 0,
//						y1 : 1,
//						colorStops : [ {
//							offset : 0,
//							color : '#d2e6c9'
//						}, {
//							offset : 1,
//							color : 'white'
//						} ]
//				};
//				$('#TaskTypeLevel').jqChart({
//					orientation: 'horizontal',
//				     width: 1150,
//				     height: 300,
//					legend : {
//						customItems : [ {
//							text : {
//								text : 'Complied'
//							},
//							marker : {
//								fillStyle : '#00CC33',
//								type : 'circle'
//							}
//						}, {
//							text : {
//								text : 'Posing'
//							},
//							marker : {
//								fillStyle : '#FFFF00',
//								type : 'circle'
//							}
//						}, {
//							text : {
//								text : 'Non-Complied'
//							},
//							marker : {
//								fillStyle : '#FF0000',
//								type : 'circle'
//							}
//						}, {
//							text : {
//								text : 'Delayed'
//							},
//							marker : {
//								fillStyle : '#BFBFBF',
//								type : 'circle'
//							}
//						} ]
//					},
//					border : {
//						strokeStyle : '#6ba851'
//					},
//					background : background,
//					animation : {
//						duration : 1
//					},
//					shadows : {
//						enabled : true
//					},
//					axes : [ {
//						type : 'category',
//						location : 'bottom',
//						categories : keys
//					}, {
//						type : 'linearGradient',
//						location : 'left',
//						minimum : 0,
//						maximum : 100,
//						interval : 10,
//						labels : {
//							stringFormat : '%.1f%%'
//						}
//					} ],
//					series : [ {
//						type : 'stacked100Column',
//						title : 'Complied',
//						fillStyles : [ '#00CC33' ],
//						data : typeData[0],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					}, {
//						type : 'stacked100Column',
//						title : 'Posing',
//						fillStyles : [ '#FFFF00' ],
//						data : typeData[1],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					}, {
//						type : 'stacked100Column',
//						title : 'Non-Complied',
//						fillStyles : [ '#FF0000' ],
//						data : typeData[2],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					}, {
//						type : 'stacked100Column',
//						title : 'Delayed',
//						fillStyles : [ '#BFBFBF' ],
//						data : typeData[3],
//						cursor : 'pointer',
//						labels : {
//							stringFormat : '%.2f%%',
//							valueType : 'percentage',
//							font : '12px sans-serif'
//						}
//					} ]
//				});
//			}
//		});
//	}
	
//Load Entity Level Chart
	
function getEntityChart(){
	$("#entityChart").hide();
	$("#entLoader").show();
	$.ajax({
		type : "POST",
		url : "./getOverallGraphForEntity",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			
			$("#entLoader").hide();
			$("#entityChart").show();
			
				var data = response;
				data = data.EntityData;
				var keys = Object.keys(data);				
				var EntData = [];

				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					EntData.push(str);
				}	
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				
				$('#entityLevel').jqChart({
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : EntData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : EntData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : EntData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : EntData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
				
			}
		});
}

//Get Pie Chart By Impacts
function getpieChartByImpact(){
	$("#pieLoader").show();
	$("#overAllPieChart").hide();
	items = {};
	items['taskImpact'] = $("#GraphByImpact").val();
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "POST",
		url : "./getPiechartGraphByImpact",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : jsonString,
		cache : false,
		success : function(response) {
			console.log(response);
			$("#pieLoader").hide();
			$("#overAllPieChart").show();
	var data = response;
	data = data.OrgData;
	var key = Object.keys(data);
	var ogrData= [];
	  for(var j=0; j < 4; j++){
		  var str = [];
		  for(var i=0; i < Object.keys(data).length; i++){
			  		str.push(data[key[i]][j]); 
		  }
		  ogrData.push(str);
	  }
	var compliedTask = ogrData[0];
	var posingTask =  ogrData[1];
	var nonCompliedTask = ogrData[2];
	var delayedTask = ogrData[3];
  var background = {
      type: 'linearGradient',
      x0: 0,
      y0: 0,
      x1: 0,
      y1: 1,
      colorStops: [{offset: 0, color: '#d2e6c9'},
          {offset: 1, color: 'white'}]
  };
  
	

  $('#jqChart1').jqChart({
	  orientation: 'horizontal',
	  	width: 450,
	    height: 400,
      border: {strokeStyle: '#6ba851'},
      background: background,
      animation: {duration: 1},
      shadows: {
          enabled: true
      },
      series: [
          {
              type: 'pie',
              fillStyles: ['#00CC33', '#FFFF00', '#FF0000 ', '#BFBFBF'],
              labels: {
                  stringFormat: '%.1f%%',
                  valueType: 'percentage',
                  font: '15px sans-serif',
                  fillStyle: 'white'
              },
              explodedRadius: 10,
              explodedSlices: [4],
              data: [['Complied', compliedTask], ['Posing', posingTask], ['Non-Complied', nonCompliedTask], ['Delayed', delayedTask]]
          }
      ]
  });
		},
		error : function(xhr, ajaxOptions, thrownError) {
			bootbox.dialog({
				title : "Error !",
				message : "Something went wrong while fetching \"OverAll Compliance Status\""
			});
		}
	});
}

//Get Entity chart by Imapct
function getEntityChartByImpact(){
	$("#entLoader").show();
	$("#entityChart").hide();
	
	items = {};
	items['taskImpact'] = $("#GraphByImpact").val();
	items['orgId'] = "0";
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "POST",
		url : "./getEntityGraphByImpact",
		dataType : "json",
		data:jsonString,
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			
			$("#entLoader").hide();
			$("#entityChart").show();
			
				var data = response;
				data = data.EntityData;
				var keys = Object.keys(data);
				var EntData = [];

				for (var j = 0; j < 4; j++) {
					var str = [];
					for (var i = 0; i < Object.keys(data).length; i++) {
						str.push(data[keys[i]][j]);
					}
					EntData.push(str);
				}

				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				$('#entityLevel').jqChart({
					orientation: 'horizontal',
				     width: 1150,
				     height: 300,
					legend : {
						customItems : [ {
							text : {
								text : 'Complied'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Posing'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Non-Complied'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Delayed'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						} ]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
						type : 'category',
						location : 'bottom',
						categories : keys
					}, {
						type : 'linearGradient',
						location : 'left',
						minimum : 0,
						maximum : 100,
						interval : 10,
						labels : {
							stringFormat : '%.1f%%'
						}
					} ],
					series : [ {
						type : 'stacked100Column',
						title : 'Complied',
						fillStyles : [ '#00CC33' ],
						data : EntData[0],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Posing',
						fillStyles : [ '#FFFF00' ],
						data : EntData[1],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Non-Complied',
						fillStyles : [ '#FF0000' ],
						data : EntData[2],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Column',
						title : 'Delayed',
						fillStyles : [ '#BFBFBF' ],
						data : EntData[3],
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					} ]
				});
			}
		});
}

//Get Location Chart by Impact
function getLocationChartByImpact(){
	$("#locGraph").hide();
	$("#locLoader").show();
	
	items = {};
	items['taskImpact'] = $("#GraphByImpact").val();
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "POST",
		url : "./getLocationGraphByImpact",
		dataType : "json",
		data:jsonString,
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			
			$("#locLoader").hide();
			$("#locGraph").show();
			
			var data = response;
			data = data.locData;
			var keys = Object.keys(data);
			var locData = [];

			for (var j = 0; j < 4; j++) {
				var str = [];
				for (var i = 0; i < Object.keys(data).length; i++) {
					str.push(data[keys[i]][j]);
				}
				locData.push(str);
			}
			var background = {
				type : 'linearGradient',
				x0 : 0,
				y0 : 0,
				x1 : 0,
				y1 : 1,
				colorStops : [ {
					offset : 0,
					color : '#d2e6c9'
				}, {
					offset : 1,
					color : 'white'
				} ]
			};
			$('#LocationBarChart').jqChart({
				orientation: 'horizontal',
			     width: 1150,
			     height: 300,
				legend : {
					customItems : [ {
						text : {
							text : 'Complied'
						},
						marker : {
							fillStyle : '#00CC33',
							type : 'circle'
						}
					}, {
						text : {
							text : 'Posing'
						},
						marker : {
							fillStyle : '#FFFF00',
							type : 'circle'
						}
					}, {
						text : {
							text : 'Non-Complied'
						},
						marker : {
							fillStyle : '#FF0000',
							type : 'circle'
						}
					}, {
						text : {
							text : 'Delayed'
						},
						marker : {
							fillStyle : '#BFBFBF',
							type : 'circle'
						}
					} ]
				},
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				animation : {
					duration : 1
				},
				shadows : {
					enabled : true
				},
				axes : [ {
					type : 'category',
					location : 'bottom',
					categories : keys
				}, {
					type : 'linearGradient',
					location : 'left',
					minimum : 0,
					maximum : 100,
					interval : 10,
					labels : {
						stringFormat : '%.1f%%'
					}
				} ],
				series : [ {
					type : 'stacked100Column',
					title : 'Complied',
					fillStyles : [ '#00CC33' ],
					data : locData[0],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				}, {
					type : 'stacked100Column',
					title : 'Posing',
					fillStyles : [ '#FFFF00' ],
					data : locData[1],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				}, {
					type : 'stacked100Column',
					title : 'Non-Complied',
					fillStyles : [ '#FF0000' ],
					data : locData[2],
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				}, {
					type : 'stacked100Column',
					title : 'Delayed',
					fillStyles : [ '#BFBFBF' ],
					data : locData[3],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				} ]
			});
		}
	});
}

function getDepartmentChartByImpact(){
	$("#deptGraph").hide();
	$("#deptLoader").show();
	
	items = {};
	items['taskImpact'] = $("#GraphByImpact").val();
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "POST",
		url : "./getDepartmentGraphByImpact",
		dataType : "json",
		data : jsonString,
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			
			$("#deptLoader").hide();
			$("#deptGraph").show();
			
			var data = response;
			data = data.deptData;
			var keys = Object.keys(data);
			var locData = [];

			for (var j = 0; j < 4; j++) {
				var str = [];
				for (var i = 0; i < Object.keys(data).length; i++) {
					str.push(data[keys[i]][j]);
				}
				locData.push(str);
			}
			
			StatisticsValues = data;
			var background = {
				type : 'linearGradient',
				x0 : 0,
				y0 : 0,
				x1 : 0,
				y1 : 1,
				colorStops : [ {
					offset : 0,
					color : '#d2e6c9'
				}, {
					offset : 1,
					color : 'white'
				} ]
			};
			$('#DepartmentBarChart').jqChart({
				orientation: 'horizontal',
			     width: 1150,
			     height: 300,
				legend : {
					customItems : [ {
						text : {
							text : 'Complied'
						},
						marker : {
							fillStyle : '#00CC33',
							type : 'circle'
						}
					}, {
						text : {
							text : 'Posing'
						},
						marker : {
							fillStyle : '#FFFF00',
							type : 'circle'
						}
					}, {
						text : {
							text : 'Non-Complied'
						},
						marker : {
							fillStyle : '#FF0000',
							type : 'circle'
						}
					}, {
						text : {
							text : 'Delayed'
						},
						marker : {
							fillStyle : '#BFBFBF',
							type : 'circle'
						}
					} ]
				},
				border : {
					strokeStyle : '#6ba851'
				},
				background : background,
				animation : {
					duration : 1
				},
				shadows : {
					enabled : true
				},
				axes : [ {
					type : 'category',
					location : 'bottom',
					categories : keys
				}, {
					type : 'linearGradient',
					location : 'left',
					minimum : 0,
					maximum : 100,
					interval : 10,
					labels : {
						stringFormat : '%.1f%%'
					}
				} ],
				series : [ {
					type : 'stacked100Column',
					title : 'Complied',
					fillStyles : [ '#00CC33' ],
					data : locData[0],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				}, {
					type : 'stacked100Column',
					title : 'Posing',
					fillStyles : [ '#FFFF00' ],
					data : locData[1],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				}, {
					type : 'stacked100Column',
					title : 'Non-Complied',
					fillStyles : [ '#FF0000' ],
					data : locData[2],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				}, {
					type : 'stacked100Column',
					title : 'Delayed',
					fillStyles : [ '#BFBFBF' ],
					data : locData[3],
					cursor : 'pointer',
					labels : {
						stringFormat : '%.2f%%',
						valueType : 'percentage',
						font : '12px sans-serif'
					}
				} ]
			});
		}
	});
}

function getProductFilters(){	
	$.ajax({
		type : "POST",
		url : "./getProductGraphFilters",
		contentType : "application/json",
		dataType : "html",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			var response = jQuery.parseJSON(response);
			var FilterOutput = "<option value='0'>-- Select --</option>";
				for(key in response){
					FilterOutput += "<option value='"+key+"'>"+response[key]+"</option>";
				}
				$("#productList").html(FilterOutput);
			}
	});
}
//Product graph

function getCaseCategoryGraph(){

	$("#productsGraph").hide();
	$("#productsLoader").show();

	
	$.ajax({
		type : "POST",
		url : "./getCaseCategoryGraph",
		contentType : "application/json",
		dataType : "html",
		//data : jsonString,
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			$("#productsLoader").hide();
			$("#productsGraph").show();
			
			var ProductGraphJson = jQuery.parseJSON(response);
			var keys = Object.keys(ProductGraphJson);
			var Pending = [];
			var In_favour = [];
			var Settled = [];
			var Against = [];
			var WithDrawn = [];
		
			

			
				for(key in ProductGraphJson){
					for(innerKey in ProductGraphJson[key]){
						if(innerKey == "Pending")
							Pending.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "In favour")
							In_favour.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "Settled")
							Settled.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "Against")
							Against.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "WithDrawn")
							WithDrawn.push(ProductGraphJson[key][innerKey]);
					
					}
				} 
				
				var background = {
					type : 'linearGradient',
					x0 : 0,
					y0 : 0,
					x1 : 0,
					y1 : 1,
					colorStops : [ {
						offset : 0,
						color : '#d2e6c9'
					}, {
						offset : 1,
						color : 'white'
					} ]
				};
				
				$('#ProductBarChart').jqChart({
					legend : {
						customItems : [ {
							text : {
								text : 'Pending'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'In favour'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Against'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						},
						{
							text : {
								text : 'Settled'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						},
						{
							text : {
								text : 'WithDrawn'
							},
							marker : {
								fillStyle : '#ffc1cc',
								type : 'circle'
							}
						}]
					},
					border : {
						strokeStyle : '#6ba851'
					},
					background : background,
					animation : {
						duration : 1
					},
					shadows : {
						enabled : true
					},
					axes : [ {
				    	   type: 'category',
				    	   location: 'left',
				    	   categories: keys
				       },
				       {
				    	   type: 'linear',
				    	   location: 'bottom',
				    	   extendRangeToOrigin: true,
				    	   labels: {
				    		   stringFormat: '%d%%'
				    	   }
				       } ],
					series : [ {
						type : 'stacked100Bar',
						title : 'Pending',
						fillStyles : [ '#00CC33' ],
						data : Pending,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Bar',
						title : 'In favour',
						fillStyles : [ '#FFFF00' ],
						data : In_favour,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Bar',
						title : 'Settled',
						fillStyles : [ '#BFBFBF' ],
						data : Settled,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Bar',
						title : 'Against',
						fillStyles : [ '#FF0000' ],
						data : Against,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					},
					{
						type : 'stacked100Bar',
						title : 'WithDrawn',
						fillStyles : [ '#ffc1cc' ],
						data : WithDrawn,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}
					
					]
				});	
			}
		});
}
