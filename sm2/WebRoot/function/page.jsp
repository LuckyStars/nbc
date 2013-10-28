<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="prc" value="${pageContext.request.contextPath}"/>

<div class="page"  style="text-align:center;font-size:15px;margin-top:20px;">
<%--分页标签 --%>
 <pg:pager url="${prc}/scMaster2/listMasterStatistics_data.action"
			items="${pm.totalPageNo}" maxPageItems="${pm.totalPageNo}" maxIndexPages="3" export="currentPageNumber=pageNumber">
<%
Enumeration enu = request.getParameterNames();
%>
<%
while(enu.hasMoreElements()){
   String paramName = (String)enu.nextElement();
   
   if(!paramName.equals("page")&&!paramName.equals("urlAction")&&paramName.indexOf("page.")<0){
	   %>
	   <pg:param name='<%=paramName%>' value='<%=request.getParameter(paramName)%>'/>
	   <%
   }
}
%>
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
</div>
