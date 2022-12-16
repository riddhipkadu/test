<!--footer start-->
<div class="footer">
	<div class="container">
		<p>&copy; Lexcare Global Consultants Private Limited .V 2.0 All Rights Reserved.</p>
	</div>
</div>
<!--footer end-->

<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>

<!-- other js files -->

<!-- Custom Chart Js -->
<script src="charts/jquery-ui-1.10.4.min.js"></script>
<script src="charts/jquery.jqChart.min.js"></script>
<script src="charts/jquery.jqRangeSlider.min.js"></script>
<script src="charts/jquery.mousewheel.js"></script>

<!-- Full Calendar  -->
<script src="css/plugins/fullcalendar/moment.min.js" type="text/javascript"></script>
<script src="css/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>

<!--Bootstrap DatePicker -->
<script	src="js/bootstrap-datepicker.js"></script>

<!-- AmCharts -->
<!-- <script src="charts/amcharts.js"></script>
<script src="charts/light.js"></script>
<script src="charts/serial.js"></script>
 -->
<!-- Date Js -->
<script src="js/date.js"></script>

<!-- Export HTML Table  -->
<script src="tableExport/tableExport.js"></script>
<script src="tableExport/jquery.base64.js"></script>
<script src="tableExport/html2canvas.js"></script>
<script src="tableExport/sprintf.js"></script>
<script src="tableExport/jspdf.min.js"></script>
<script src="tableExport/base64.js"></script>
<script src="tableExport/jspdf.debug.js"></script>
<script src="tableExport/html2pdf.js"></script>
<script src="tableExport/FileSaver.js"></script>
<script src="tableExport/FileSaver.min.js"></script>
<script src="tableExport/base64.js"></script>
<script src="tableExport/jquery.table2excel.js"></script>
<script src="tableExport/jspdf.js"></script>
<script src="tableExport/jspdf.plugin.autoble.js"></script>
<script src="tableExport/table2csv.js"></script>


<!-- Jspdf Plugins -->
<!-- <script src="js/jspdf.debug.js"></script> -->
<script src="js/jspdf.plugin.autotable.js"></script>
<script src="tableExport/plugins/acroform.js"></script>
<script src="tableExport/plugins/addhtml.js"></script>
<script src="tableExport/plugins/addimage.js"></script>
<script src="tableExport/plugins/annotations.js"></script>
<script src="tableExport/plugins/autoprint.js"></script>
<script src="tableExport/plugins/canvas.js"></script>
<script src="tableExport/plugins/cell.js"></script>
<script src="tableExport/plugins/context2d.js"></script>
<script src="tableExport/plugins/from_html.js"></script>
<script src="tableExport/plugins/javascript.js"></script>
<script src="tableExport/plugins/outline.js"></script>
<script src="tableExport/plugins/png_support.js"></script>
<script src="tableExport/plugins/prevent_scale_to_fit.js"></script>
<script src="tableExport/plugins/split_text_to_size.js"></script>
<script src="tableExport/plugins/standard_fonts_metrics.js"></script>
<script src="tableExport/plugins/svg.js"></script>
<script src="tableExport/plugins/total_pages.js"></script>


<!-- Reveal JS -->
<script src="js/jquery.reveal.js"></script>

<!-- Dojo js -->
<script src="dojo/dojo.js"></script>
<!--table initialise script-->
<script src="js/currency/money.min.js"></script>
<script src="js/bootstrap-multiselect.js"></script>
<script>

	 $(function(){
		$("#tillcurrentdate").datepicker({
			 	autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
				endDate:"now",
				todayHighlight:"true",
				showOnFocus:"true",
				defaultViewDate:"today"
			
		
		}).datepicker();  
		
		$("#tillcurrentdate1").datepicker({
			 
		 	autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			endDate:"now",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		
	
	}).datepicker();  
		 
	 });
	 
	 $(function() {
			
			$("#legalduedatepicker").datepicker({
				autoclose : true,
				format : "dd-mm-yyyy",
				viewMode : "auto",
				minViewMode : "auto",
					todayHighlight:"true",
					showOnFocus:"true",
					defaultViewDate:"today"
			}).datepicker();
			
			
		}); 
	 
	 $(function() {
		
		$("#datepicker1").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
				todayHighlight:"true",
				showOnFocus:"true",
				defaultViewDate:"today"
		}).datepicker();
		
		
	}); 
	 
	 
	$(function() {
		$("#datepicker2").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	$(function() {
		$("#datepicker3").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	$(function() {
		$("#datepicker4").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	$(function() {
		$("#datepicker5").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	$(function() {
		$("#datepicker6").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker("update",new Date());
		;
	});
	
	$(function() {
		$("#datepicker7").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker("update",new Date());
		;
	});
	
	$(function() {
		$("#datepicker8").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker("update",new Date());
		;
	});
	
	$(function() {
		$("#datepicker9").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker("update",new Date());
		;
	});
	
	$(function() {
		$("#datepicker10").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker("update",new Date());
		;
	});
	
	$(function() {
		$("#datepicker11").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker("update",new Date());
		;
	});
	
	//For First Alert
	$(function() {
		$("#firstAlert").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	
	//For Second Alert
	$(function() {
		$("#secondAlert").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	
	//For Thired Alert
	$(function() {
		$("#thiredAlert").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	
	//First Alert For Multi. Configuration
	$(function() {
		$("#firstAlertForMultiConfig").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
	
	//Second Alert For Multi. Configuration
	$(function() {
		$("#secondAlertForMultiConfig").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
	});
	
	//Thired Alert For Multi. Configuration
	$(function() {
		$("#thiredAlertForMultiConfig").datepicker({
			autoclose : true,
			format : "dd-mm-yyyy",
			viewMode : "auto",
			minViewMode : "auto",
			todayHighlight:"true",
			showOnFocus:"true",
			defaultViewDate:"today"
		}).datepicker();
		;
	});
</script>
<!--end-->
<script>
$("#legal_updates").click(function(e) {
	e.preventDefault();
	$("#updates").slideToggle("transition:all 1s ease");
	// $("#slide").hide();
});
</script>

<script>
	$("#app_icon").click(function(e) {
		e.preventDefault();
		$("#slide").slideToggle("transition:all 1s ease");
		$("#profile").hide();
	});

	$('.page_container').click(function() {
			$("#slide").hide();
			$("#profile").hide();
			$("#updates").hide();
	});
</script>
<script>
	$("#profile_icon").click(function(e) {
		e.preventDefault();
		$("#profile").slideToggle("transition:all 1s ease");
		$("#slide").hide();
	});

	/* $('.page_container').click(function() {
		if (!$(this.target).is('#profile')) {
			$("#profile").hide();
		}
	}); */
</script>

</body>
</html>