<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>统计列表</title>
<link href="../function/css/index.css" rel="stylesheet" type="text/css" />
<link href="../function/css/gzt.css" rel="stylesheet"/>
<script type="text/javascript" src="../function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
</head>
<body>
<div class="con_conent fixed">
  <h1 class="title"><span class="title">当前位置：</span><span class="text"><a href="${prc}/scMaster2/teacherInput_index.action">首页</a>　-　请假代课　-　</span><span class="back">统计分析</span></h1>
  <form action="${prc}/scMaster2/listStatistics_data.action">
  <input type="hidden" name="matcher" value="${matcher}"/>
  <div class="table_box fixed">
    <div class="nav"> <span>统计时间:</span>
      <input class="Wdate"  name="start" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')}'})" id="d4311"/>
      <span>至</span>
       <input class="Wdate" name="end" value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}'})" id="d4312" />
      <a class="cx" href="javascript:document.forms[0].submit();">查询</a><a href="${prc}/scMaster2/toChart_data.action?matcher=${matcher}" style="float:left; margin-top:15px; margin-left:20px; font-size:14px;">去统计</a></div>
      <div class="list">
      	<c:forEach items="${pm.datas}" varStatus="status" var="data">
      		<c:choose>
				<c:when test="${status.index%2==0 }">
					<p class="color">
	          			<span class="list-sp">${status.index+1}<span style="margin-left:10px;">
			          		<fmt:formatDate value="${data.startDate}" pattern="yyyy年MM月dd日"/>-
							<fmt:formatDate value="${data.endDate}"  pattern="yyyy年MM月dd日"/>
			          	</span></span>
			          	<span class="list-ri">的统计分析</span>
			          	<a href="${prc}/scMaster2/remove_data.action?id=${data.id}"><img src="${prc}/function/img/erro1.png" /></a>
          			</p>
				</c:when>
				<c:otherwise>
					<p>
			          	<span class="list-sp">${status.index+1}<span style="margin-left:10px;">
			          		<fmt:formatDate value="${data.startDate}" pattern="yyyy年MM月dd日"/>-
							<fmt:formatDate value="${data.endDate}"  pattern="yyyy年MM月dd日"/>
			          	</span></span>
			          	<span class="list-ri">的统计分析</span>
			          	<a href="${prc}/scMaster2/remove_data.action?id=${data.id}"><img src="${prc}/function/img/erro1.png" /></a>
			          </p>
				</c:otherwise>	
      		</c:choose>
      	</c:forEach>
      </div>
  </div>
  </form>
   <div  style="text-align:center;font-size:15px;margin-top:20px;">
   		<c:if test="${pm.total>0}">
        <pg:pager url="${prc}/scMaster2/listStatistics_data.action"
			items="${pm.totalPageNo}" maxPageItems="${pm.totalPageNo}" maxIndexPages="3" export="currentPageNumber=pageNumber">
			<pg:param name="start" value="${start}"/>
			<pg:param name="end" value="${end}"/>
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
</div>
</body>
</html>
