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
</head>
<body>
     <div class="right1">
     	<form action="${prc}/scMaster2/listMasterStatistics_data.action">
	    	<h1>当前位置：首页 - 请假代课 - <span style="color:#dd0000">统计分析</span></h1>
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
		    <div style="height:500px;width:900px" >
          		<iframe id="chart" name="chart"  style="height:500px;width:900px;overflow:hidden;" frameborder="0" src="${prc}/scMaster2/chart_data.action?matcher=${data.matcher}"></iframe>
    		</div>
    		<div class="fen">
          		<p><span>分析：</span><input type="text" id="title" class="top" readonly="readonly" value="${data.title }"/></p>
          		<textarea class="down" readonly="readonly">${data.content}</textarea>
     		</div>
         </form>
      </div>
  
</body>
</html>