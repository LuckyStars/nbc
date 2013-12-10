<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${prc}/function/css/gzt.css" rel="stylesheet"/>
    <link href="../function/css/gzt.css" rel="stylesheet"/>
    <link href="${prc}/function/function-statistics/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function resizeFrame(height){
			$("#chart").css('height',height);
		}
	</script>
	<style type="text/css">
    			
    			.chars_{
    				margin-left:100px;
    			}
    			
    			.chart_title{
    				font-size:18px;
    				color:#0A5FB0;
    				font-weight: bold;
    				margin-bottom: 5px;
    			}
    			
    			.chart_content{
    				text-align:left;
    				border: 1px solid #C0C0C0;
    				font-size: 14px;
    				height: 300px;
    				overflow: scroll;
    				width:730px; 
    				word-warp:break-word;
    				word-break:break-all;
    				overflow-x:hidden;
    			}
    			
			</style>
</head>
<body>
     <div class="right1" style="text-align: center;">
     	<form action="${prc}/scMaster2/listMasterStatistics_data.action">
	    	<h1>当前位置：<a href="javascript:parent.location.href='${prc}/scMaster2/index_index.action';">首页</a> - ${name } - <span style="color:#dd0000">统计分析</span></h1>
		    <div class="content">
		      <h3>统计时间：
		          <input class="Wdate"  name="start" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>" type="text" 
		          onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')}'})" id="d4311"/>
		         至：
			       <input class="Wdate" name="end" value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" 
			       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}'})" id="d4312" />
		          <a class="button" id="select1" href="javascript:document.forms[0].submit();">查询</a>
		          <a class="button" href="${prc}/scMaster2/listMasterStatistics_data.action?matcher=${data.matcher}">返回</a>
		      </h3>
		    </div>
		    <div  >
          		<iframe id="chart" name="chart"  style="height:500px;width:800px;overflow:hidden;" frameborder="0" src="${prc}/scMaster2/chart_data.action?matcher=${data.matcher}"></iframe>
    		</div>
    		<div style="clear:both;height:50px;"></div>
    		
    		
    		<div class="fen" style="text-align: left;">
    			<div class="chars_ chart_title">
    			分析：${data.title }
    			</div>
    			<div class="chars_ chart_content">
    			<c:out value="${data.content}" escapeXml="true"></c:out>
    			</div>
     		</div>
         </form>
      </div>
  
</body>
</html>