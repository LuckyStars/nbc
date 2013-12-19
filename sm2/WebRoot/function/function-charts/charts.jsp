<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>图标</title>
	<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
	<link href="${prc}/function/css/gzt.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${prc}/function/Charts/FusionCharts.js" ></script>
	<script type="text/javascript">
		
		function renderChart(chartType){
			var chart = new FusionCharts("${prc}/function/Charts/FCF_" 
					+ chartType + ".swf", "ChartId", "790","470");
			chart.setDataXML("${xmlContent}");   
			chart.render("chartdiv");
		}
		
		
		function changeTab(chartType){
			clearCur();
			$("#" + chartType ).addClass('cur');
			renderChart(chartType);
		}
		
		var clearCur = function(){$("li[class='cur']").removeClass('cur');};
		function resizeParent(){
    		var height = $(document).height();
    		parent.resizeFrame(height+10);
    	}
		$(function(){
			renderChart('${dataType}');
			$("#${dataType}").attr('class','cur');
			resizeParent();
		});
		
	</script>
	
	
</head>
<body>
<!--	<div class="con_conent fixed">-->
<!--		<div class="table_box fixed">-->
	    	<div>
	        	<ul class="tabs1" style="width:790px;background:${color}" >
	          		<li id="Pie2D"><a href="javascript:changeTab('Pie2D');">饼图</a></li>
	          		<li id="Line"><a href="javascript:changeTab('Line');">折线图</a></li>
	          		<li id="Bar2D"><a href="javascript:changeTab('Bar2D');">柱状图(横)</a></li>
	          		<li id="Column3D"><a href="javascript:changeTab('Column3D');">柱状图(竖)</a></li>
	          		<li id="Funnel"><a href="javascript:changeTab('Funnel');">漏斗图</a></li>
	          		<li id="Doughnut2D"><a href="javascript:changeTab('Doughnut2D');">空心饼图</a></li>
	        	</ul>
	        </div>
          	<div id="chartdiv" style="height: 450px;margin: 0px;padding: 0px;"></div>
<!--	  	</div>-->
<!--	</div>-->
</body>
</html>
	