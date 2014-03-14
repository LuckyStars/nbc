﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../function/css/index.css" rel="stylesheet" type="text/css" />
<link href="../function/css/gzt.css" rel="stylesheet"/>
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${por}/common/agent.js"></script>
<script type="text/javascript">
function commit(){
	var date = $(".Wdate");
	for(var i=0;i<date.length;i++){
		if(date[i].value.length<=0){
			alert("统计时间不能为空！");
			return;
		}
	}
	if($.trim($("#title").val()).length<=0){
		alert("标题不能为空！");
		return ;
	}if($.trim($(".down").val()).length<=0){
		alert("分析不能为空！");
		return ;
	}
	document.forms[0].submit();
}
function aaa(){
	var begin = $("#begin").val();
	var end = $("#end").val();
	var matcher =  $("#matcher").val();
	$("#s").attr("href","${prc}/scMaster2/chart_data.action?matcher="+matcher+"&start="+begin+"&end="+end);
	$("#s").click();
	sethash();
}
$(function() {
	$(" .tabs1 li").click(function(){
		 $(this).attr("class","cur").siblings(".cur").removeClass("cur");
		}
	);
});
</script>
</head>
<body>
<div class="con_conent fixed">
  <h1 class="title"><span class="title">当前位置：</span><span class="text"><a href="${prc}/scMaster2/teacherInput_index.action">首页</a>　-　${name }　-　</span><span class="back">统计分析</span></h1>
 <form action="${prc}/scMaster2/add_data.action">
 <input type="hidden" name="matcher" value="${matcher}" id="matcher"/>
  <div class="table_box fixed" style="background: #f0f8fc;">
    <div class="nav"> <span>统计时间:</span>
      <input class="Wdate"  name="data.startDate" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'end\')}'})" id="begin"/>
      <span>至:</span>
      <input class="Wdate" name="data.endDate" value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'begin\')}'})" id="end" />
      <a class="cx" id="s" target="chart" href="#" onclick="aaa();">统计</a><a href="${prc}/scMaster2/listStatistics_data.action?matcher=${matcher}" class="cx" >查看全部</a> </div>
	<div >
          <iframe id="chart" name="chart"  style="height:500px;width:800px;overflow:hidden;scrolling:no;" frameborder="0" src="${prc}/scMaster2/chart_data.action?matcher=${matcher}&start=<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>&end=<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>"></iframe>
    </div>
      <div class="fen">
          <p style="margin-top:20px;">
          	<span>标题：</span>
          	<input type="text" id="title" name="data.title" class="top" maxlength="30" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>至<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>"/><span>的统计分析</span>
          </p>
          <p style="display:block;"><span style="float:left;margin-top:20px;">分析：</span><textarea name="data.content" class="down"></textarea>
         </p>
      </div>
      <a href="javascript:commit();" class="have" style=" margin-top: 216px;">生成</a>
  </div>
  </form>
</div>

</body>
</html>
