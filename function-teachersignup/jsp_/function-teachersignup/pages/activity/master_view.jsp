<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教师报名管理列表</title>
	<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
	<link href="${prc }/function/function-teachersignup/css/pagination.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${prc}/common/agent.js"></script>
    <script type="text/javascript">
    
        $(function () {
            $("table tr:odd").css("background", "#f0f8fc");
            $("table tr:even").css("background", "#d5e0ee");
            $(".menu li").click(function () {
                $(".arr").attr("class", "");
                $(this).attr("class", "arr");
            });
            $(".menu li.mag_btn").click(function () {
                $(this).attr("class", "mag_btn");
            });
            var x = $(document).height();
            $(".shade").css({ "width": $(document).width(), "height": $(document).height() });
        });

</script>
</head>
<body>
	<div class="con_conent fixed">
	     <h1 class="title">
	     	<span class="title">教师报名</span>
	     	<span class="back">
	     		<a href="${prc}/function/function-teachersignup/pages/index.jsp">返回上一页</a>
     		</span>
     	</h1>
	        <div class="table_box fixed">
	            <div class="nav">
	            	<form action="${prc }/teachersignup/masterList_act.action">
	                <span>增加日期</span>
	                <select name="month">
	                	<option value="0" >全部</option>
	                	<option value="1" 
	                		<c:if test="${month==1}">selected="selected"</c:if>
	                	>近一个月</option>
	                	<option value="3" 
	                		<c:if test="${month==3}">selected="selected"</c:if>
	                	>近一季度</option>
	                	
	                	<option value="6" 
	                		<c:if test="${month==6}">selected="selected"</c:if>
	                	>近半年</option>
	                	
	                	<option value="12" 
	                		<c:if test="${month==12}">selected="selected"</c:if>
	                	>近一年</option>
	                	
	                </select>
	                <span>状态</span>
	                <select name="actStatu" >
	                	<option value="5">全部</option>
	                	<option value="0" 
	                		<c:if test="${actStatu==0}">selected="selected"</c:if>
	                	>未发布</option>
	                	
	                	<option value="1" 
	                		<c:if test="${actStatu==1}">selected="selected"</c:if>
	                	>已发布</option>
	                	
	                	<option value="2" 
	                		<c:if test="${actStatu==2}">selected="selected"</c:if>
	                	>已暂停</option>
	                	
	                	<option value="3" 
	                		<c:if test="${actStatu==3}">selected="selected"</c:if>
	                	>已结束</option>
	                	
	                </select>
	                <input type="submit" value="查询" class="cx"/>
	            	</form>
	            </div>
	        <table width="100%" border="0">
	            <tr>
	                <th width="25%" scope="col">名称</th>
	                <th width="25%" scope="col">状态</th>
	                <th width="25%" scope="col">增加日期</th>
	                <th width="25%" scope="col">操作</th>
	            </tr>
	            
	            <c:if test="${not empty pm.datas}">
	            	<c:forEach var="act" items="${pm.datas}">
	            		<tr>
			                <td align="center">
			                <div style="width:220px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;text-align: center;">
							${act.name }
							</div>
			                </td>
			                <td><as:showStatus statusId="${act.status}"/></td>
			                <td><fmt:formatDate value="${act.createDate}" pattern="yyyy-MM-dd"/></td>
			                <td>
			                	<%--
			                	/**未发布*/
								EDITING(0x0,"未发布"),
								/**已发布*/
								PUBLISHED(0x1,"已发布"),
								/**暂停*/
								PAUSED(0x2,"暂停"),
								/**结束*/
								FINISHED(0x3,"结束");
			                	 --%>
		                		<span class="space1">
			                		<a href="${prc}/teachersignup/masterList_sign.action?actId=${act.id}">
			                		报名情况
			                		</a>
		                		</span>
		                		<span class="space1">
			                		<a href="${prc}/teachersignup/adminExl_sign.action?actId=${act.id}">
			                		下载报名结果
			                		</a>
		                		</span>
			                </td>
			            </tr>
	            	</c:forEach>
	            </c:if>
	        </table>
	        <c:if test="${pm.total > 10}">
	        <div class="page_nav" id="pagingBars">
					<pg:pager url="masterList_act.action" items="${pm.total}" maxPageItems="10" export="currentPageNumber=pageNumber">
						<pg:param name="month" value="${month}"/>
						<pg:param name="actStatu" value="${actStatu}"/>
						<pg:next><span class="page_nav_next"><a href="${pageUrl}">下一页</a></span></pg:next>
						<ul>
							<pg:pages>	
								<c:choose>
									<c:when test="${currentPageNumber eq pageNumber}"> <li class="page_nav_current">${pageNumber}</li> </c:when>
									<c:otherwise> <li><a href="${pageUrl}" class="first">${pageNumber}</a></li></c:otherwise>
								</c:choose>	
							</pg:pages>
						</ul>
						<pg:prev><span class="page_nav_prev"><a href="${pageUrl}">上一页</a></span></pg:prev>
					</pg:pager>
   			 </div>
   			 </c:if>
	    </div>
	</div>
</body>
</html>
