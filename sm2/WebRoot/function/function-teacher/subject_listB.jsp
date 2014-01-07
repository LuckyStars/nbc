<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="con_conent fixed">
		<div class="table_box fixed">
			<c:forEach items="${list}" var="subject">
				<dl class="warp">
					<a href="${prc}/scMaster2/detail_master.action?id=${subject.id}";">
						<dt>
							<img src="${prc}/function/img/${fn:substring(subject.id, 7, 8) }.png" />
						</dt>
						<dd>${subject.title}</dd> </a>
				</dl>
			</c:forEach>		
		</div>
	</div>
</body>
</html>