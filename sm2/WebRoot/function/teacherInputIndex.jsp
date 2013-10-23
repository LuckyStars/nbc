<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/css/index.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<c:set var="ctxPath" value="http://172.16.30.183:9080/sns_sjoa"></c:set>
	<div class="con_conent fixed">
		<div class="table_box fixed">
			<dl class="warp">
				<a href="${ctxPath }/showSubjectWorkbench.action?module=ndzx&userId=${sns_init}">
					<dt>
						<img src="${prc}/function/img/tu4.jpg" />
					</dt>
					<dd>年度重心工作</dd> </a>
			</dl>
			<dl class="warp">
				<a href="${ctxPath }/showSubjectWorkbench.action?module=lssx&userId=${sns_init}">
					<dt>
						<img src="${prc}/function/img/tu5.jpg" />
					</dt>
					<dd>临时事项</dd> </a>
			</dl>
			<dl class="warp">
				<a href="${ctxPath }/showSubjectWorkbench.action?module=xxdt_3&userId=${sns_init}">
					<dt>
						<img src="${prc}/function/img/tu6.jpg" />
					</dt>
					<dd>紧急重要事件</dd> </a>
			</dl>
			<dl class="warp">
				<a href="${ctxPath }/showSubjectWorkbench.action?module=xxdt_2&userId=${sns_init}">
					<dt>
						<img src="${prc}/function/img/tu7.jpg" />
					</dt>
					<dd>请示报批事项</dd> </a>
			</dl>
			<dl class="warp">
				<a href="${ctxPath }/showSubjectWorkbench.action?module=xxdt_1&userId=${sns_init}">
					<dt>
						<img src="${prc}/function/img/tu8.jpg" />
					</dt>
					<dd>让校长关注的事项</dd>
				</a>
			</dl>
		</div>
	</div>
</body>
</html>