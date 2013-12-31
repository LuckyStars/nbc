<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/jquery.json-2.4.min.js"></script>
<style type="">
.warp span {
	background: url("${prc}/function/images/new1.png") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    color: #FFFFFF;
    font-size: 12px;
    font-style: normal;
    height: 14px;
    line-height: 14px;
    margin-left: 92px;
    position: absolute;
    text-align: center;
    width: 17px;
    display:none;
}
</style>
</head>
<body>
	<div class="con_conent fixed">
		<div class="table_box fixed">
			<c:forEach items="${modules}" var="module">
				<c:if test="${module.flag==0}">
					<dl class="warp">
						<a href="${prc}/scMaster2/find_subject.action?subjectVo.moduleId=${module.id}">
							<dt>
								<span id="${module.id}"></span>
								<img src="${prc}/function/img/${module.icon}"></img>
							</dt>
							<dd><b>${module.name}</b></dd> </a>
					</dl>
				</c:if>
				<c:if test="${module.flag==1}">
					<pri:showWhenManager>
						<dl class="warp">
							<a href="${prc}/scMaster2/find_subject.action?subjectVo.moduleId=${module.id}">
								<dt>
									<span id="${module.id}"></span>
									<img src="${prc}/function/img/${module.icon}" />
								</dt>
								<dd><b>${module.name}</b></dd> </a>
						</dl>
					</pri:showWhenManager>
				</c:if>
				<c:if test="${module.flag==2}">
					<pri:hideWhenManager>
						<dl class="warp">
							<a href="${prc}/scMaster2/findB_subject.action?subjectVo.moduleId=${module.id}">
								<dt>
									<span id="${module.id}b"></span>
									<img src="${prc}/function/img/${module.icon}" />
								</dt>
								<dd><b>${module.name}</b></dd> </a>
						</dl>
					</pri:hideWhenManager>
				</c:if>
				<c:if test="${module.flag==3}">
						<dl class="warp">
							<a href="${prc}/scMaster2/find_subject.action?subjectVo.moduleId=${module.id}">
								<dt>	
									<span id="${module.id}"></span>
									<img src="${prc}/function/img/${module.icon}" />
								</dt>
								<dd><b>${module.name}</b></dd> </a>
						</dl>
				</c:if>
			</c:forEach>	
			<dl class="warp">
				<a href="${prc}/scMaster2/teacherList_invatition.action">
					<dt>
						<img src="${prc}/function/img/tu9.jpg" />
					</dt>
					<dd><b>邀请查看</b></dd> </a>
			</dl>	
			<dl class="warp" >
				<a href="${prc}/scMaster2/findAllTrans_subject.action">
					<dt>
						<span id="zhuanfa"></span>
						<img src="${prc}/function/img/zhuanfa.png" style="height:97px;"/>
					</dt>
					<dd><b>查看转发事项</dd></b> </a>
			</dl>
			<dl class="warp">
				<a href="${prc}/scMaster2/listStatistics_data.action?matcher=bookSite">
					<dt>
						<img src="${prc}/function/img/tu5.jpg" />
					</dt>
					<dd><b>场馆预定</dd></b></a>
			</dl>
		</div>
	</div>
</body>
<script type="text/javascript" src="${prc}/function/js/teacherIndex.js"></script>
</html>