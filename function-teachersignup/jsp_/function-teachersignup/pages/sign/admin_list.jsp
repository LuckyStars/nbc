<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>报名获奖情况(鹳狸猿)</title>
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
        	<form action="${prc}/teachersignup/adminList_sign.action" method="post">
	            <div class="nav">
	                <input type="hidden" name="actId" value="${actId}" />
	                <span>教师姓名</span>
	                <input name="userName" type="text" value="${userName}" />
	                
	                <span>报名类型</span>
	                <select name="subId">
	                	<option value=" ">全部</option>
	                	<c:if test="${not empty subjSet}">
	                	<c:forEach items="${subjSet}" var="sub">
	                		<option value="${sub.id }"
	                			<c:if test="${subId eq sub.id}">selected="selected"</c:if>
	                		>${sub.name }</option>
	                	</c:forEach>
	                	</c:if>
	                </select>
	                <span>奖项类型</span>
	                
	                <select name="rewId">
	                	<option value=" ">全部</option>
	                	<c:if test="${not empty rewdSet}">
	                	<c:forEach items="${rewdSet}" var="rew">
	                		<option value="${rew.id }"
	                			<c:if test="${rewId eq rew.id}">selected="selected"</c:if>
	                		>${rew.name }</option>
	                	</c:forEach>
	                	</c:if>
	                </select>
	                
	                <input type="submit" class="cx"  value="查询"/>
	                <a class="cx" href="${prc}/teacherSignup/adminExl_sign.action">下载/导出</a>
	            </div>
            </form>
		<table width="100%" border="0">
			<tr>
				<th width="20%" scope="col">教师姓名</th>
				<th width="30%" scope="col">报名类型</th>
				<th width="30%" scope="col">获奖情况</th>
				<th width="20%" scope="col">追加奖项</th>
			</tr>
			<c:if test="${not empty pm.datas}">
				<c:forEach items="${pm.datas}" var="sign">
					<tr>
					    <td><c:out value="${sign.teaName }" escapeXml="true"></c:out></td>
					    <td><c:out value="${sign.signSubject }" escapeXml="true"></c:out></td>
					    <td>
					    	<select  class="rewSelect" id="rew${sign.signId }" disabled="disabled">
					         	<option>暂无获奖情况</option>
							    <c:if test="${not empty rewdSet}">
				                	<c:forEach items="${rewdSet}" var="rew">
				                		<option value="${rew.id }"
				                			<c:if test="${sign.rewId eq rew.id}">selected="selected"</c:if>
				                		>${rew.name }</option>
				                	</c:forEach>
							    </c:if>
					        	<option>特等奖</option>
					        	<option>一等奖</option>
					        </select>
					    </td>
					    <td>
					  	<c:choose>
					  		<c:when test="${not empty rewdSet}">
					  			<a href="javascript:changeRew('${sign.signId}');" id="edit${sign.signId }">追加获奖</a>
					  			<a href="javascript:confirmRew('${sign.signId}');" id="confirm${sign.signId }" style="display: none;">确认</a>
					  		</c:when>
					  		<c:otherwise>暂无奖项</c:otherwise>
					  	</c:choose>  
					   </td>
					</tr>
					
				</c:forEach>
			</c:if>
            
        </table>
        <div class="page_nav" id="pagingBars">
			<pg:pager url="adminList_sign.action?actId=${actId}&userName=${userName}&subId=${subId}&rewId=${rewId}" items="${pm.total}" maxPageItems="10" export="currentPageNumber=pageNumber">
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
		</div>
    </div>
	<script type="text/javascript">
		var rewEditFin = true;
		function changeRew(id){
			if(rewEditFin){
				var x =$("select[class='rewSelect']");
				x.each(function(){$(this).attr('disabled','disabled');});
				$("select[id='rew" + id + "']").removeAttr('disabled');
				$("#edit" + id).hide();
				$("#confirm"+id).show();
				rewEditFin = false;
			}else{
				alert('请先确认获奖!');	
			}
		}

		function confirmRew(id){
			var rewId = $("select[id='rew" + id + "']").val();
			location.href="${prc}/teachersignup/addRew_sign.action?actId=${actId}&rewId=" + rewId + "&signId=" + id;
		}
	</script>
</div>
</body>
</html>
