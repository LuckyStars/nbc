<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${prc}/function/css/gzt.css" rel="stylesheet"/>
    <link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
    $("table tr:odd").css("background", "#fff");
    $("table tr:even").css("background", "#EDEFFE");
});
</script>
</head>
	<body>
		<div class="right1">
		  	<form action="${prc}/scMaster2/listMasterStatistics_data.action">
			<input type="hidden" name="matcher" value="${data.matcher}"/>
		    <div class="right-input">		      
		        <p class="timer"><label>统计时间:</label>
		         <input class="Wdate"  name="start" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>" type="text" 
		          onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')}'})" id="d4311"/>
		         至：
			       <input class="Wdate" name="end" value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" 
			       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}'})" id="d4312" /></p>
		       <a id="select1" href="javascript:document.forms[0].submit();">查询</a>
		    </div>
		   <table width="100%" border="0">
		      <tr>
		        <th width="5%"  scope="col">序号</th>
		        <th width="35%" scope="col">标题</th>
		        <th width="10%" scope="col">发布人</th>
		        <th width="10%" scope="col">发布时间</th>
		        <th width="35%" scope="col">统计时间段</th>
		      </tr>
			     <c:forEach items="${pm.datas}" varStatus="status" var="data">
			      <tr>
			        <td class="lan" >${status.index+1}</td>
					 <td style=" text-align: left;">
			        	<a href="${prc}/scMaster2/toMasterChart_data.action?id=${data.id}&start=${data.startDate}&end=${data.endDate}&matcher=${data.matcher}">
							<c:out value="${data.title}"/>
						</a>
					</td>
			     
			        <td><c:out value="${data.name}"/></td>
			        <td><fmt:formatDate value="${data.createDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
			        <td>
			        	<fmt:formatDate value="${data.startDate}" pattern="yyyy-MM-dd"></fmt:formatDate>至
			        	<fmt:formatDate value="${data.endDate}" pattern="yyyy-MM-dd"></fmt:formatDate>的分析
			        </td>
			      </tr>
			    </c:forEach>
   			</table>
   			 <c:if test="${empty pm.datas}"><%--无数据 --%>
			    <div style="margin-top: 40px;text-align: center;">
			    	<img src="${prc }/function/img/no_data.png" alt="暂无上报数据" />
			    </div>
		    </c:if>
      </form>
   </div>
  
    <div  style="text-align:center;font-size:15px;margin-top:20px;">
   		<c:if test="${pm.total>0}">
        <pg:pager url="${prc}/scMaster2/listMasterStatistics_data.action"
			items="${pm.totalPageNo}" maxPageItems="${pm.total}" maxIndexPages="3" export="currentPageNumber=pageNumber">
<!--			<pg:param name="start" value="${start}"/>-->
<!--			<pg:param name="end" value="${end}"/>-->
			<pg:param name="matcher" value="${matcher}"/>
			总计${pm.total}条
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
		</c:if>
    </div>
</body>
</html>