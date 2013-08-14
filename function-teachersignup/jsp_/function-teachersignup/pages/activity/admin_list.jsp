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
	     <h1 class="title"><span class="title">教师报名</span><span class="back">返回上一页</span></h1>
	        <div class="table_box fixed">
	            <div class="nav">
	                <span>增加日期</span>
	                <select></select>
	                <span>状态</span>
	                <select></select>
	                <a class="cx" href="#">查询</a>
	                 <a class="cx1" href="#">增加报名信息</a>
	            </div>
	        <table width="100%" border="0">
	            <tr>
	                <th width="26%" scope="col">名称</th>
	                <th width="14%" scope="col">状态</th>
	                <th width="18%" scope="col">增加日期</th>
	                <th width="42%" scope="col">操作</th>
	            </tr>
	            
	            <c:if test="${not empty pm.datas}">
	            	<c:forEach var="act" items="${pm.datas}">
	            		<tr>
			                <td>${act.name }</td>
			                <td><as:showStatus statusId="${act.status}"/></td>
			                <td><fmt:formatDate value="${act.createDate}" pattern="yyyy-MM-dd"/></td>
			                <td><span class="space1">查看管理</span><span class="space1">取消发布</span><span class="space1">手工结束</span><span class="space1">追加获奖情况</span></td>
			            </tr>
	            	</c:forEach>
	            </c:if>
	            
	            <tr>
	                <td>1</td>
	                <td>XXX报名</td>
	                <td>已发布</td>
	                <td>2013-02-17</td>
	                <td><span class="space1">查看管理</span><span class="space1">取消发布</span><span class="space1">手工结束</span><span class="space1">追加获奖情况</span></td>
	            </tr>
	        </table>
	        <div class="page_nav" id="pagingBars">
				<c:if test="${pm.total > 10}">
					<pg:pager url="adminList_act.action" items="${pm.total}" maxPageItems="10" export="currentPageNumber=pageNumber">
						  <pg:next><span class="page_nav_next"><a href="${pageUrl}">下一页</a></span></pg:next>
								<ul>
									<pg:pages>	
										<c:choose>
											<c:when test="${currentPageNumber eq pageNumber}"> <li class="page_nav_current">${pageNumber}</li> </c:when>
											<c:otherwise> <li><a href="${pageUrl}" class="first">${pageNumber}</a></li></c:otherwise>
										</c:choose>	
									</pg:pages>
								</ul>
							<pg:prev> <span class="page_nav_prev"><a href="${pageUrl}">上一页</a></span></pg:prev>
					</pg:pager>
				</c:if>	
   			 </div>
	    </div>
	</div>
</body>
</html>
