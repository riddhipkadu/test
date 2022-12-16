/**
 * Author : Tejashri Zurunge
 * Created At : 18-04-2018
 *  Purpose : Separate internal & embeded JS Scripts separatly
 *  ************************************
 */


//load pie Chart Graph for status wise
function getPieChart(){
	$("#overAllPieChart").hide();
	$("#pieLoader").show();
	
	$.ajax({
		type : "POST",
		url : "./getOverallPreContractStatusGraph",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			$("#pieLoader").hide();
			$("#overAllPieChart").show();
	var data = response;
	data = data.OrgData;
	var key = Object.keys(data);
	var ogrData= [];
	  for(var j=0; j < 7; j++){
		  var str = [];
		  for(var i=0; i < Object.keys(data).length; i++){
			  		str.push(data[key[i]][j]); 
		  }
		  ogrData.push(str);
	  }
	  
	var SentforReview = ogrData[0];
	var Finalized =  ogrData[1];
	var ClosedExecuted = ogrData[2];
	var SentToPOCforNegotiation = ogrData[3];
	var Others = ogrData[4];
	var Pending = ogrData[5];
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
              fillStyles: ['#00CC33', '#FFFF00', '#FF0000 ', '#BFBFBF', '#f9a6a6', '#2742EE'],
              labels: {
                  stringFormat: '%.1f%%',
                  valueType: 'percentage',
                  font: '15px sans-serif',
                  fillStyle: 'white'
              },
              explodedRadius: 10,
              explodedSlices: [6],
              data: [['Sent for Review', SentforReview], ['Finalized', Finalized], ['Closed/Executed', ClosedExecuted], ['SentToPOCforNegotiation', SentToPOCforNegotiation], ['Others', Others], ['Pending', Pending] ]
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



 

//Export exportcontracttypestatus image Formate
/* function exportOver() {
	alert("hiiiiiiiiii");
		var config = {
				fileName : 'overAllPieChart.png',
				type : 'image/png' // 'image/png' or 'image/jpeg'
		}
		$("#jqChart1").jqChart('exportToImage', config);
	};
	
	//Export overall graph in image Formate
	$scope.exportOverAllPieChart = function() {
		alert('Function called')
		var config = {
				fileName : 'overAllPieChart.png',
				type : 'image/png' // 'image/png' or 'image/jpeg'
		}
		$("#jqChart1").jqChart('exportToImage', config);
	};*/

function getDetailDataPreContractByStatus(status){
	//alert(status);
	var cont_status = status;
	
	items = {};
	items["cont_status"] = cont_status;
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./getAllPreContractDataByStatusPieChart",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			var data_body = "";
			var res = $.parseJSON(response);
			var flag = 0;
			if(res.length != 0){
				data_body += '<table class="table table-striped table-bordered" width="100%" cellspacing="0"><thead><tr style="background:#054eff;color:#fff;">'
					+ '<th>Contract Id </th>'
					+ '<th>Organogram Details </th>    '
					+ '<th>Start Date</th>     '
					+ '<th>End Date</th>     '
					+ '<th>Responsible Person</th>     '
					+ '<th>Party Name</th>     '
					+ ' </tr></thead>'
					+ '<tbody>';
				//alert("response body length :"+res.length);
				$.each(res, function(key,value){
					
					data_body += 
							 '<tr>'
						    
							+ '<td><a href="updateTab?cont_id='+value['cont_id']+'" style="color:blue;">'+value['cont_id']+'</a></td>'
							+ '<td>'+value['orga_name']+","+value['loca_name']+","+value['dept_name']+'</td> '
							+ '<td>'+value['cont_start_date']+'</td>  '
							+ '<td>'+value['cont_end_date']+'</td> '
							+ '<td>'+value['cont_responsible_person']+'</td> '
						
		                    + '<td>';
					                    var c = 1;
					                  $.each(value['parties'],function(key,value){
					  data_body +=    c+')'+value['party_name']+'<br>';
						              c++;
					                  });
					
					+'</td>';
				+ ' </tr>';
					
				});
				data_body += '</tbody></table>';
					
					$("#dialogBox .modal-header").css("background-color", "#dca732");
		    		$('#dialogBox .modal-title').html("&nbsp;Contract List");
					$('#dialogBox .modal-body').html(data_body);
				    $("#dialogBox").modal('show');	
			}
		}
	});
}


function getAllPreContractByCaseCategory(caseCategory, PreContractStatus){
	
	//alert(caseCategory + " and "+LitigationStatus)
		items = {};
		
		items['caseCategory'] = caseCategory;
		items['PreContractStatus'] = PreContractStatus;
		var jsonString = JSON.stringify(items);
		
		$.ajax({
			type : "post",
			url : "./getAllPreContractByCaseCategory",
			contentType : "application/json",
			dataType : "html",
			data : jsonString,
			cache : false,
			success : function(response) {
				var data_body = "";
				var res = $.parseJSON(response);
				var flag = 0;
				if(res.length != 0){
					data_body += '<table class="table table-striped table-bordered" width="100%" cellspacing="0"><thead><tr style="background:#054eff;color:#fff;">'
						+ '<th>Contract Id </th>'
						+ '<th>Organisation Details </th>  '
						+ '<th>Start Date</th>  '
						+ '<th>End Date</th> '
						+ '<th>Responsible Person</th> '
						+ '<th>Party Name</th> '
						+ '<th>Status</th>'
						+ ' </tr></thead>'
						+ '<tbody>';
					//alert("response body length :"+res.length);
					$.each(res, function(key,value){
						
						data_body += 
								 '<tr>'
							+ '<td><a href="updateTab?cont_id='+value['cont_id']+'" style="color:blue;">'+value['cont_id']+'</a></td>'
							+ '<td>'+value['orga_name']+","+value['loca_name']+","+value['dept_name']+'</td> '
							+ '<td>'+value['cont_start_date']+'</td>  '
							+ '<td>'+value['cont_end_date']+'</td> '
							+ '<td>'+value['cont_responsible_person']+'</td> ';
							
					
						
							 data_body +=  '<td>';
					                    var c = 1;
					                  $.each(value['parties'],function(key,value){
					  data_body +=    c+')'+value['party_name']+'<br>';
						              c++;
					                  });
					
					+'</td>'
					if(value['cont_status'] != 'Others'){
						
						data_body +='<td>'+ value['cont_status']+'</td> ';
								}	
								else{
					    data_body += '<td>'+"Others - "+value['other_status']+'</td>';
								};
					
								+ ' </tr>';
						
					});
					data_body += '</tbody></table>';
						
						$("#dialogBox .modal-header").css("background-color", "#dca732");
			    		$('#dialogBox .modal-title').html("&nbsp; Contract List");
						$('#dialogBox .modal-body').html(data_body);
					    $("#dialogBox").modal('show');	
				}
			}
		});
}

//case category wise bar chart

function getCaseCategoryGraph(){

	$("#productsGraph").hide();
	$("#productsLoader").show();
	/*items = {};
	items['fromDate'] = $("#from_date").val();
	items['toDate'] = $("#to_date").val();
	items['taskImpact'] = $("#GraphByImpact").val();
	items['productProtocolNo'] = $("#productList").val();
	
	var jsonString = JSON.stringify(items);*/
	
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
			var Sent_for_Review  = [];
			var Finalized = [];
			var ClosedExecuted = [];
			var SentToPOCforNegotiation = [];
			var Others = [];
			var Pending = [];
			/*var ReOpened = [];*/
			
			/*int  = 0;
			int In_favour = 0;
			int Settled = 0;
			int Against = 0;
			int Withdrawn = 0;
			int totalCount = 0;*/
			
				for(key in ProductGraphJson){
					for(innerKey in ProductGraphJson[key]){
						if(innerKey == "Sent for Review")
							Sent_for_Review.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "Finalized")
							Finalized.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "Closed/Executed")
							ClosedExecuted.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "SentToPOCforNegotiation")
							SentToPOCforNegotiation.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "Others")
							Others.push(ProductGraphJson[key][innerKey]);
						if(innerKey == "Pending")
							Pending.push(ProductGraphJson[key][innerKey]);
						/*if(innerKey == "ReOpened")
							ReOpened.push(ProductGraphJson[key][innerKey]);*/
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
								text : 'Sent for Review'
							},
							marker : {
								fillStyle : '#00CC33',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Finalized'
							},
							marker : {
								fillStyle : '#FFFF00',
								type : 'circle'
							}
						}, {
							text : {
								text : 'Closed/Executed'
							},
							marker : {
								fillStyle : '#FF0000',
								type : 'circle'
							}
						},
						{
							text : {
								text : 'SentToPOCforNegotiation'
							},
							marker : {
								fillStyle : '#BFBFBF',
								type : 'circle'
							}
						},
						{
							text : {
								text : 'Others'
							},
							marker : {
								fillStyle : '#f3aaaa',
								type : 'circle'
							}
						},
						{
							text : {
								text : 'Pending'
							},
							marker : {
								fillStyle : '#2742EE',
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
						title : 'Sent for Review',
						fillStyles : [ '#00CC33' ],
						data : Sent_for_Review,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Bar',
						title : 'Finalized',
						fillStyles : [ '#FFFF00' ],
						data : Finalized,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Bar',
						title : 'Closed/Executed',
						fillStyles : [ '#FF0000' ],
						data : ClosedExecuted,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}, {
						type : 'stacked100Bar',
						title : 'SentToPOCforNegotiation',
						fillStyles : [ '#BFBFBF' ],
						data : SentToPOCforNegotiation,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					},
					{
						type : 'stacked100Bar',
						title : 'Others',
						fillStyles : [ '#f3aaaa' ],
						data : Others,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					},{
						type : 'stacked100Bar',
						title : 'Pending',
						fillStyles : [ '#2742EE' ],
						data : Pending,
						cursor : 'pointer',
						labels : {
							stringFormat : '%.2f%%',
							valueType : 'percentage',
							font : '12px sans-serif'
						}
					}]
				});	
			}
		});
}


//load Entity wise pie Chart Graph - Akash jadhav
function getPieChart1(){
	$("#overAllPieChart1").hide();
	$("#pieLoader").show();
	
	$.ajax({
		type : "POST",
		url : "./getOverallEntityGraph",
		dataType : "json",
		contentType : "application/json; charset=utf-8",
		success : function(response) {
			
			$("#pieLoader").hide();
			$("#overAllPieChart1").show();
			
	var data = response;
	data = data.OrgData;
	var key = Object.keys(data);
	var ogrData= [];
	  for(var j=0; j < 11; j++){
		  var str = [];
		  for(var i=0; i < Object.keys(data).length; i++){
			  		str.push(data[key[i]][j]); 
		  }
		  ogrData.push(str);
	  }
	  
	var MahycoGrow = ogrData[0];
	var MahycoPrivateLimited =  ogrData[1];
	var MaharashtraHybridSeedsCompanyPrivateLimited = ogrData[2];
	var GrowIndigoPrivateLimited = ogrData[3];
	var SevenStarFruitsPrivateLimited = ogrData[4];
	var LeadbeterSeedsPrivateLimited = ogrData[5];
	var MahycoInternationalPteLimited = ogrData[6];
	var MahycoBangladeshPrivateLimited = ogrData[7];
	var MahycoKenyaPrivateLimited = ogrData[8];
	var MahycoGrowSAPtyLTD = ogrData[9];
	
	
    var background = {
      type: 'linearGradient',
      x0: 0,
      y0: 0,
      x1: 0,
      y1: 1,
      colorStops: [{offset: 0, color: '#d2e6c9'},
          {offset: 1, color: 'white'}]
  };
  
	

  $('#jqChart2').jqChart({
	  orientation: 'horizontal',
	     width: 650,
	     height:400,
      border: {strokeStyle: '#6ba851'},
      background: background,
      animation: {duration: 1},
      shadows: {
          enabled: true
      },
      series: [
          {
              type: 'pie',
              fillStyles: ['#00CC33', '#FFFF00', '#FF0000 ', '#BFBFBF', '#f9a6a6', '#2742EE','#00ffff','#ff8000','#80ff00','#0080ff'],
              labels: {
                  stringFormat: '%.1f%%',
                  valueType: 'percentage',
                  font: '15px sans-serif',
                  fillStyle: 'white'
              },
              explodedRadius: 15,
              explodedSlices: [10],
              data: [['Mahyco Grow', MahycoGrow], ['Mahyco Private Limited', MahycoPrivateLimited], ['Maharashtra Hybrid Seeds Company Private Limited', MaharashtraHybridSeedsCompanyPrivateLimited], ['Grow Indigo Private Limited', GrowIndigoPrivateLimited], ['Seven Star Fruits Private Limited', SevenStarFruitsPrivateLimited], ['Leadbeter Seeds Private Limited', LeadbeterSeedsPrivateLimited],['Mahyco International Pte Limited', MahycoInternationalPteLimited],['Mahyco Bangladesh Private Limited', MahycoBangladeshPrivateLimited],['Mahyco Kenya Private Limited', MahycoKenyaPrivateLimited],['Mahyco Grow SA Pty LTD', MahycoGrowSAPtyLTD] ]
          }
          
        
          
      ]
  });
		}
	});
}


function getDetailsDataEntity(status){
	//alert(status);
	var cont_status = status;
	
	items = {};
	items["cont_status"] = cont_status;
	
	var jsonString = JSON.stringify(items);
	
	$.ajax({
		type : "post",
		url : "./getAllEntityDataByPieChart",
		contentType : "application/json",
		dataType : "html",
		data : jsonString,
		cache : false,
		success : function(response) {
			var data_body = "";
			var res = $.parseJSON(response);
			var flag = 0;
			if(res.length != 0){
				data_body += '<table class="table table-striped table-bordered" width="100%" cellspacing="0"><thead><tr style="background:#054eff;color:#fff;">'
					+ '<th>Executed Contract Id </th>'
					+ '<th>Organisation Details </th>    '
					+ '<th>Start Date</th>     '
					+ '<th>End Date</th>     '
					+ '<th>Responsible Person</th>     '
					+ '<th>Party Name</th>     '
					+ ' </tr></thead>'
					+ '<tbody>';
				//alert("response body length :"+res.length);
				$.each(res, function(key,value){
					
					data_body += 
							 '<tr>'
						    
							+ '<td><a href="executedContractUpdates?exec_id='+value['cont_id']+'" style="color:blue;">'+value['cont_id']+'</a></td>'
							+ '<td>'+value['orga_name']+","+value['loca_name']+","+value['dept_name']+'</td> '
							+ '<td>'+value['cont_start_date']+'</td>  '
							+ '<td>'+value['cont_end_date']+'</td> '
							+ '<td>'+value['cont_responsible_person']+'</td> '
						
		                    + '<td>';
					                    var c = 1;
					                  $.each(value['parties'],function(key,value){
					  data_body +=    c+')'+value['party_name']+'<br>';
						              c++;
					                  });
					
					+'</td>';
				+ ' </tr>';
					
				});
				data_body += '</tbody></table>';
					
					$("#dialogBox .modal-header").css("background-color", "#dca732");
		    		$('#dialogBox .modal-title').html("&nbsp;Contract List");
					$('#dialogBox .modal-body').html(data_body);
				    $("#dialogBox").modal('show');	
			}
		}
	});
}