<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<div  style="text-align:center;font-size:15px;margin-top:20px;">
	<c:if test="${pm.total>0}">
        <pg:pager url="${prc}/scMaster2/list_master.action"
			items="${pm.totalPageNo}" maxPageItems="${pm.totalPageNo}" maxIndexPages="3" export="currentPageNumber=pageNumber">
			<pg:param name="moduleId" value="${moduleId}"/>
			<pg:param name="search.typeId" value="${search.typeId}"/>
			<pg:param name="search.departId" value="${search.departId}"/>
			<pg:param name="search.createrName" value="${search.createrName}"/>
			<pg:param name="search.departId" value="${search.departId}"/>
			<pg:param name="search.start" value="${search.start}"/>
			<pg:param name="search.end" value="${search.end}"/>

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