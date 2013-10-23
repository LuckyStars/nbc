<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<script type="text/javascript">
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
  <h1 class="title"><span class="title">当前位置：</span><span class="text">首页　-　请假代课　-　</span><span class="back">统计分析</span></h1>
 <form action="${prc}/scMaster2/add_data.action">
 <input type="hidden" name="data.matcher" value="bookSite" id="matcher"/>
  <div class="table_box fixed">
    <div class="nav"> <span>统计时间:</span>
      <input class="Wdate"  name="data.startDate" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" id="begin"/>
      <span>至:</span>
      <input class="Wdate" name="data.endDate" value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly"  id="end" />
      <a href="${prc}/scMaster2/listStatistics_data.action?data.matcher=bookSite" style="float:left; margin-top:15px; margin-left:20px; font-size:14px;">查看全部列表</a> </div>
	<div style="height:500px;width:700px" >
          <iframe id="chart" name="chart"  style="height:500px;width:700px;overflow:hidden;" frameborder="0" src="${prc}/scMaster2/chart_data.action?matcher=bookSite"></iframe>
    </div>
      <div class="fen">
          <p><span>分析：</span><input type="text" id="title" class="top" readonly="readonly" value="${data.title }"/></p>
          <textarea class="down" readonly="readonly">${data.content}</textarea>
      </div>
      <a href="${prc}/scMaster2/listMasterStatistics_data.action" class="have">返回</a>
  </div>
  </form>
</div>

</body>
</html>
