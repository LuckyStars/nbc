<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>功能列表</title>
	<script type="text/javascript" src="${prc}/common/agent.js"></script>
	<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="con_conent fixed">

	<h1 class="title"><span class="title">教师报名</span></h1>

	<div class="table_box fixed">
	
    	<dl class="warp">
        	<dt>
        		<img src="${prc}/function/function-teachersignup/image/tu3.jpg" 
        			onclick="location.href='${prc}/teachersignup/comListPubed_act.action'" style="cursor:pointer"/>
        	</dt>
    		<dd><a href="${prc}/teachersignup/comListPubed_act.action">我要报名</a></dd>
		</dl>
		
		<dl class="warp">
        	<dt>
        		<img src="${prc}/function/function-teachersignup/image/tu3.jpg" 
        			onclick="location.href='${prc}/teachersignup/commonFinList_act.action'" style="cursor:pointer"/>
        	</dt>
    		<dd><a href="${prc}/teachersignup/commonFinList_act.action">已经结束的报名</a></dd>
		</dl>
		
        <c:if test="${sessionScope.tsCurUser.isAdmin}">
		<dl class="warp">
			<dt><img src="${prc}/function/function-teachersignup/image/tu4.jpg" 
				onclick="location.href='${prc}/teachersignup/adminList_act.action';" style="cursor:pointer"/>
			</dt>
		<dd><a href = "${prc}/teachersignup/adminList_act.action">报名管理</a></dd>
		</dl>
		</c:if>
		 
        <c:if test="${sessionScope.tsCurUser.isAdmin}">
		<dl class="warp">
			<dt>
				<img src="${prc}/function/function-teachersignup/image/tu5.jpg" 
				onclick="location.href='${prc}/teachersignup/masterList_act.action?month=1'"
				style="cursor:pointer" />
			</dt>
			<dd><a href = "${prc}/teachersignup/masterList_act.action?month=1">报名查看及统计</a></dd>
        </dl>
        </c:if>
        
        <c:if test="${sessionScope.tsCurUser.isAdmin}">
        <dl class="warp">
			<dt>
				<img src="${prc}/function/function-teachersignup/image/tu5.jpg" 
				onclick="location.href='${prc}/teachersignup/list_userPri.action'"
				style="cursor:pointer" />
			</dt>
			<dd><a href = "${prc}/teachersignup/list_userPri.action">权限管理</a></dd>
        </dl>
        </c:if>
        
	</div>

</div>
</body>
</html>