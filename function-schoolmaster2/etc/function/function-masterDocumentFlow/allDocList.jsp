<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公文处理</title>
<link href="../function/css/index.css" rel="stylesheet" type="text/css" />
<link href="../function/css/gzt.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="../function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../function/js/easyui/themes/icon.css" />
<style>
table th {
	background:url(../function/img/table-bg.jpg) repeat-x;
    height:38px;
	line-height:38px;
}
table td {
	height:34px;
	line-height:34px;
	text-align:center;
}
</style>
<script type="text/javascript" src="../function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../plugins/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
    $(".change_tab tr:odd").css("background", "#fff");
    $(".change_tab tr:even").css("background", "#EDEFFE");
});
function changepage(page){
	window.location.href="/schoolapp/masterDocumentFlow/listAllDocumentTask.action?pagerUtils.pageIndex="+page;
}
</script>
</head>
	<body>
		<div class="right1">
		  <h1>当前位置：首页 - <span style="color:#002F7C">公文处理</span></h1>
       <table width="100%" border="0">
      <s:iterator value="documentTaskVoList" var="taskVo" status="st">
      <tr>
        <td class="lan">
        	<a href="viewUnhandledDocument.action?docId=<s:property value='#taskVo.id'/>&taskType=<s:property value='#taskVo.type'/>">
			<s:property value="#taskVo.longTitle"/></a>
		</td>
        <td><s:property value="#taskVo.documentSourceDisplayName"/></td>
        <td><s:property value="#taskVo.publishTime"/></td>
      </tr>
      </s:iterator>
    </table>
  	<div class="bgbottom_box fixed">
		<div class="search_num">
			<span class="fl">主题数：<s:property value="pagerUtils.totalResult"/></span>
			<span class="fr">
				<s:if test="pagerUtils.pageIndex != 1">
					<s:a cssClass="page" href="%{pre}">&lt;&lt;上一页</s:a>
				</s:if>
				
				<s:if test="pagerUtils.pageIndex lt pagerUtils.pageNumbers.length">
					<s:a cssClass="page" href="%{next}">下一页&gt;&gt;</s:a>
				</s:if>
				<s:select name="pagerUtils.pageIndex" onchange="changepage(this.value)" list="pagerUtils.pageNumbers" value="%{pagerUtils.pageIndex}"/>
			</span>
		</div>
	</div>
    </div>
</body>
</html>
	