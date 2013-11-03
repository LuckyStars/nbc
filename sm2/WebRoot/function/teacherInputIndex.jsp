<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:set var="ctxPath" value="http://localhost/sm2"></c:set>
	<div class="con_conent fixed">
		<div class="table_box fixed">
			<c:forEach items="${modules}" var="module">
				<c:if test="${module.flag==0}">
					<dl class="warp">
						<a href="${prc}/scMaster2/find_subject.action?subjectVo.moduleId=${module.id}&subjectVo.moduleName=${module.name}">
							<dt>
								<img src="${prc}/function/img/tu4.jpg" />
							</dt>
							<dd>${module.name}</dd> </a>
					</dl>
				</c:if>
				<c:if test="${module.flag==1}">
					<pri:showWhenManager>
						<dl class="warp">
							<a href="${prc}/scMaster2/find_subject.action?subjectVo.moduleId=${module.id}&subjectVo.moduleName=${module.name}">
								<dt>
									<img src="${prc}/function/img/tu4.jpg" />
								</dt>
								<dd>${module.name}</dd> </a>
						</dl>
					</pri:showWhenManager>
				</c:if>
			</c:forEach>		
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
			
			 <dl class="warp">
			 <a href="${ctxPath }/showSubjectWorkbench.action?module=xxdt_1&userId=${sns_init}">
     		 <dt><img src="${prc}/function/img/tu9.jpg" /></dt>
	      	<dd>邀请您参与的工作</dd>
	     </a>
	    </dl>
	     
	      <dl class="warp">
	       <a href="${ctxPath }/findAllModule.action?module.parentId=tjfx">
		      <dt><img src="${prc}/function/img/tu10.jpg" /></dt>
		      <dd>统计分析B部分管理</dd>
	      	</a>
	    </dl>
	      <dl class="warp">
	      <dt><img src="${prc}/function/img/tu11.jpg" /></dt>
	      <dd>统计分析B部分</dd>
    </dl>
			
		</div>
	</div>
</body>
</html>