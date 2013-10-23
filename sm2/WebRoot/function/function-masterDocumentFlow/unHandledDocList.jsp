<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>待处理文件</title>
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
    $("table tr:odd").css("background", "#fff");
    $("table tr:even").css("background", "#EDEFFE");
});
</script>
</head>
	<body>
		<div class="right1">
		  <h1>当前位置：首页 - <span style="color:#002F7C">待处理文件</span></h1>
		  <s:form action="listUnhandledDocumentTask" method="post" namespace="/masterDocumentFlow" onsubmit="return checkDate();">
		    <div class="right-input">
		        <p><label>文件名称:</label>
					<input type="text" name="queryConditionVo.documentName" onkeyup="value=value.replace(/[%_]/g,'')"  />
				</p>
		        <p class="timer"><label>发布时间:</label>
		        <input type="text" name="queryConditionVo.starting" value="${queryConditionVo.starting }" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')}'})" id="d4311"/>
		        <img src="../function/img/time.jpg"  class="time"/><label>至</label>
		        <input type="text"  name="queryConditionVo.ending" value="${queryConditionVo.ending }" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}'})" id="d4312" />
		        <img src="../function/img/time.jpg"  class="times"/></p>
		        <p><label>发布单位:</label>
		        <select name="queryConditionVo.documentSourceId" >
					<option value="-1">--请选择--</option>
    					<c:forEach items="${documentSourceVoList}" var="c">
    						<c:choose>
    							<c:when test="${c.id == queryConditionVo.documentSourceId}">
    								<option value="${c.id}" selected="selected">${c.displayName}</option>
    							</c:when>
    							<c:otherwise>
    								<option value="${c.id}">${c.displayName}</option>
    							</c:otherwise>
    						</c:choose>
    					</c:forEach>	        
		        </select></p>
		        <a href="javascript:document.forms[0].submit();">查询</a>
		    </div>
		  </s:form>
       <table width="100%" border="0">
      <tr>
        <th width="5%" scope="col">序号</th>
        <th width="35%" scope="col">文件名称</th>
        <th width="10%" scope="col">发文单位</th>
        <th width="10%" scope="col">发布者</th>
        <th width="10%" scope="col">发布时间</th>
        <th width="10%" scope="col">状态</th>
        <th width="10%" scope="col">过期时间</th>
        <th width="10%" scope="col">操作</th>
      </tr>
      <s:iterator value="documentTaskVoList" var="taskVo" status="st">
      <tr>
        <td><s:property value='#st.index+1'/> </td>
        <td class="lan">
        	<a href="viewUnhandledDocument.action?docId=<s:property value='#taskVo.id'/>&taskType=<s:property value='#taskVo.type'/>">
			<s:property value="#taskVo.documentTitle"/></a>
		</td>
        <td><s:property value="#taskVo.documentSourceDisplayName"/></td>
        <td><s:property value="#taskVo.authorName"/></td>
        <td><s:property value="#taskVo.publishTime"/></td>
        <td><s:property value="#taskVo.status"/></td>
        <td><s:property value="#taskVo.expireTime"/></td>
        <td><span class="find"><a href="viewUnhandledDocument.action?docId=<s:property value='#taskVo.id'/>&taskType=<s:property value='#taskVo.type'/>">查阅处理</a></span></td>
      </tr>
      </s:iterator>
    </table>
    	<div style="text-align:center;font-size:15px;margin-top:20px;">
			 <pg:pager url="../masterDocumentFlow/listUnhandledDocumentTask.action"
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
    </div>
</body>
</html>
	