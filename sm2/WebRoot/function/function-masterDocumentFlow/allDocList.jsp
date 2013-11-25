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
	height:35px;
	line-height:35px;
	text-align:center;
	border-bottom:1px dotted #ccc;
}
#aaa {border-top:1px dashed #cccccc;height: 1px;overflow:hidden;}
</style>
<script type="text/javascript" src="../function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
function changepage(page){
	window.location.href="/schoolapp/masterDocumentFlow/listAllDocumentTask.action?pagerUtils.pageIndex="+page;
}
</script>
</head>
	<body>
		<div class="right1">
		  <h1>当前位置：首页 - <span style="color:#002F7C">公文处理</span></h1>
	<div style="margin:0 40px 0 40px;">
       <table width="100%" border="0">
      <s:iterator value="documentTaskVoList" var="taskVo" status="st">
      <tr>
        <td style="text-align:left;">
        	<a style="color:red;" href="viewHandledDocument.action?docId=<s:property value='#taskVo.id'/>&taskType=<s:property value='#taskVo.type'/>">
			.<s:property value="#taskVo.longTitle"/></a>
		</td>
        <td>.<s:property value="#taskVo.documentSourceDisplayName"/></td>
        <td>.<s:property value="#taskVo.publishTime"/></td>
      </tr>
      </s:iterator>
    </table>
    </div>
    	<c:if test="${pagerUtils.totalResult>10}">
		<div style="text-align:center;font-size:15px;margin-top:20px;">
			 <pg:pager url="../masterDocumentFlow/listAllDocumentTask.action"
    			items="${pagerUtils.totalResult}" maxPageItems="${pagerUtils.pageSize}" maxIndexPages="5" export="currentPageNumber=pageNumber">
    			总计${pagerUtils.totalResult}条
    			<pg:first>
    				<a href="${pageUrl}">首页</a>
    			</pg:first>
    			<pg:prev>
    				<a href="${pageUrl}" >上一页</a> 
    			</pg:prev>
    			<pg:pages>
    				<c:choose>
    					<c:when test="${currentPageNumber eq pageNumber}">
    						<font color="red">${pageNumber}</font>
    					</c:when>
    					<c:otherwise>
    						<a href="${pageUrl}">${pageNumber }</a>
    					</c:otherwise>
    				</c:choose>
    			</pg:pages>
    			<pg:next>
    				<a href="${pageUrl}" >下一页</a> 
    			</pg:next>
    			<pg:last>
    				<a href="${pageUrl}">尾页</a>
    			</pg:last>
    		</pg:pager>
		</div>
		</c:if>
    </div>
</body>
</html>
	