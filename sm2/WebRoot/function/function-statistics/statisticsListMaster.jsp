<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <link href="${prc}/function/css/gzt.css" rel="stylesheet"/>
    <link href="${prc}/function/function-statistics/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
</head>
<body>
     <div class="right1">
     	<form action="${prc}/scMaster2/listMasterStatistics_data.action">
     		<input type="hidden" name="matcher" value="${data.matcher}"/>
	    	<h1>当前位置：<a href="javascript:parent.location.href='${prc}/scMaster2/index_index.action';">首页</a> - ${name } - <span style="color:#dd0000">统计分析</span></h1>
		    <div class="content">
		      <h3>统计时间：
		          <input class="Wdate"  name="start" value="<fmt:formatDate value='${start}' pattern='yyyy-MM-dd'/>" type="text" 
		          onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')}'})" id="d4311"/>
		         至：
			       <input class="Wdate" name="end" value="<fmt:formatDate value='${end}' pattern='yyyy-MM-dd'/>" type="text" readonly="readonly" 
			       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}'})" id="d4312" />
		          <a class="button" id="select1" href="javascript:document.forms[0].submit();">查询</a>
		      </h3>
		    </div>
	         <ul class="list">
	         	<c:forEach items="${pm.datas}" varStatus="status" var="data">
	         		<c:choose>
						<c:when test="${status.index%2==0 }">
							<li class="bg">
								<a
								href="${prc}/scMaster2/toMasterChart_data.action?id=${data.id}&start=${data.startDate}&end=${data.endDate}&matcher=${data.matcher}"
								>${status.index+1}　
									<fmt:formatDate value="${data.startDate}" pattern="yyyy年MM月dd日"/>-
									<fmt:formatDate value="${data.endDate}"  pattern="yyyy年MM月dd日"/>
									<span>的统计分析</span>
								</a>
								<a title="查看"
								href="${prc}/scMaster2/toMasterChart_data.action?id=${data.id}&start=${data.startDate}&end=${data.endDate}&matcher=${data.matcher}" style="float:right;">
									<img src="${prc}/function/function-statistics/images/ico.png" />
								</a>
							</li>
		            	</c:when>	
			            <c:otherwise>
			            	<li>
								<a
								href="${prc}/scMaster2/toMasterChart_data.action?id=${data.id}&start=${data.startDate}&end=${data.endDate}&matcher=${data.matcher}"
								>${status.index+1}　
									<fmt:formatDate value="${data.startDate}" pattern="yyyy年MM月dd日"/>-
									<fmt:formatDate value="${data.endDate}"  pattern="yyyy年MM月dd日"/>
									<span>的统计分析</span>
								</a>
								
								<a title="查看"
							 	href="${prc}/scMaster2/toMasterChart_data.action?id=${data.id}&start=${data.startDate}&end=${data.endDate}&matcher=${data.matcher}" style="float:right;">
									<img src="${prc}/function/function-statistics/images/ico.png" />
								</a>
							</li>
			            </c:otherwise>	
		           	</c:choose>
             	</c:forEach>
         	</ul>
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