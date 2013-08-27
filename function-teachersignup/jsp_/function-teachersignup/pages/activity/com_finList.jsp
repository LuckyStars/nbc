<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>普通教师 已结束的报名</title>
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
	        <table width="100%" border="0">
	            <tr>
	                <th width="40%" scope="col">名称</th>
	                <th width="20%" scope="col">开始时间</th>
	                <th width="20%" scope="col">结束时间</th>
	                <th width="20%" scope="col">操作</th>
	            </tr>
	            
	            <c:if test="${not empty pm.datas}">
	            	<c:forEach var="act" items="${pm.datas}">
	            		<tr>
			                <td align="center">
			                <div style="width:220px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;text-align: center;">
							${act.name }
							</div>
			                </td>
			                <td><fmt:formatDate value="${act.openDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			                <td><fmt:formatDate value="${act.endDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			                <td>
			                	<span class="space1">
			                		<a href="${prc}/teachersignup/comFinSubList_sign.action?actId=${act.id}">
			                		我的报名
			                		</a>
			                	</span>
			                </td>
			            </tr>
	            	</c:forEach>
	            </c:if>
	            
	        </table>
	        <div class="page_nav" id="pagingBars">
					<pg:pager url="commonFinList_act.action?" items="${pm.total}" maxPageItems="10" 
						export="currentPageNumber=pageNumber">
						  <pg:next><span class="page_nav_next"><a href="${pageUrl}">下一页</a></span></pg:next>
								<ul>
									<pg:pages>	
										<c:choose>
											<c:when test="${currentPageNumber eq pageNumber}">
											<li class="page_nav_current">${pageNumber}</li>
											</c:when>
											<c:otherwise> 
											<li><a href="${pageUrl}" class="first">${pageNumber}</a></li>
											</c:otherwise>
										</c:choose>	
									</pg:pages>
								</ul>
							<pg:prev><span class="page_nav_prev"><a href="${pageUrl}">上一页</a></span></pg:prev>
					</pg:pager>
   			 </div>
	    </div>
	</div>
</body>
</html>
