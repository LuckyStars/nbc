<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>待处理文件</title>
<link href="../function/css/index.css" rel="stylesheet" type="text/css" />
<link href="../function/css/gzt.css" rel="stylesheet"/>
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
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
    $("table tr:odd").css("background", "#fff");
    $("table tr:even").css("background", "#EDEFFE");

    var x = 10;     
    var y = 20;   
    $("a.tooltip").mouseover(function(e){   
        this.myTitle = this.title;   
        this.title = ""; 
        var tooltip = "<p id='tooltip' style='position:absolute;background:#FDFDBF;border:1px solid #A2B1C3;width:300px;word-break:break-all; line-height:20px;padding:2px 5px;'><\/p>"; //创建 div 元素
	
        $("body").append(tooltip);  //把它追加到文档中   
        $("#tooltip").text(this.myTitle);
        $("#tooltip")   
            .css({   
                "top": (e.pageY + y-50) + "px",   
                "left": (e.pageX  + x) + "px"  
            }).show("fast");      //设置x坐标和y坐标，并且显示   
    }).mouseout(function(){        
        this.title = this.myTitle;   
        $("#tooltip").remove();   //移除    
    });
});
function changepage(page){
	$("#listUnhandledDocumentTask").append("<input type='hidden' name='pagerUtils.pageIndex' id='pagerUtils.pageIndex'></input>");
	document.getElementById("pagerUtils.pageIndex").value=page;
	document.forms[0].submit();
}
</script>
</head>
	<body>
		<div class="right1">
		  <h1>当前位置：<a href="javascript:parent.location.href='${prc}/scMaster2/index_index.action';">首页</a> - <span style="color:#002F7C">待处理文件</span></h1>
		  <s:form action="listUnhandledDocumentTask" method="post" namespace="/masterDocumentFlow" onsubmit="return checkDate();">
		    <div class="right-input">
		        <p><label>文件名称:</label>
					<input type="text" name="queryConditionVo.documentName" onkeyup="value=value.replace(/[%_]/g,'')"  value="${queryConditionVo.documentName }"/>
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
    <c:if test="${empty documentTaskVoList}"><%--无数据 --%>
		    <div style="margin-top: 40px;text-align: center;">
		    	<img src="${prc }/function/img/no_data.png" alt="暂无上报数据" />
		    </div>
    </c:if>
    	<div style="text-align:center;font-size:15px;margin-top:20px;">
			总计<s:property value="pagerUtils.totalResult"/>条
			<s:if test="pagerUtils.pageIndex != 1">
				<s:a cssClass="page" href="javascript:changepage(%{pagerUtils.pageIndex - 1})">&lt;&lt;上一页</s:a>
			</s:if>
			<font color="red"><s:property  value="%{pagerUtils.pageIndex}" /></font>
			<s:if test="pagerUtils.pageIndex lt pagerUtils.pageNumbers.length">
				<s:a cssClass="page" href="%{next}">下一页&gt;&gt;</s:a>
			</s:if>
		</div>
    </div>
</body>
</html>
	