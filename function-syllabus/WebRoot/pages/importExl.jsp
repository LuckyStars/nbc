<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	   	<title>按班级查询课程</title>
	   	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	<!-- Bootstrap -->
	   	<link href="${med }/basic/bootstrap.css" rel="stylesheet" media="screen">
	   	<link href="${med }/basic/bootstrap-responsive.css" rel="stylesheet" media="screen">
  	</head>
  	<body>
	
	<%--引入导航条 --%>
	<c:set var="curnav" scope="page" value="2"></c:set>
  	<%@ include file="/pages/common/navList.jsp" %>
  	<%--引入导航条 --%>
  	
	<div class="container-fluid">
		<div class="row-fluid">
		<div class="span1"></div>
			<%--这里是班级列表 --%>
			<c:set var="jumpUrl" scope="page" value="/course/import/"></c:set>
			<c:set var="refreshUrl" scope="page" value="/course/cache/refresh"></c:set>
			<%@include file="common/classList.jsp" %>
			<%--这里是班级列表 END--%>
			
			<%--这里是导入表单 --%>
			<div class="span8">
				<h3 class="muted text-center">${className }</h3>
				<form action="${prc}/upload/import/" enctype="multipart/form-data"  method="post">
					<input type="hidden" name="classId" value="${classId }"></input>
				<div class="control-group well">
					<div class="offset1" >
						<i class="icon-folder-open" > </i> 
						<input class="" id="fileInput" type="file" name="file">
						<input type="submit" class="btn btn-success" value="上传"/>
						<div class="btn-group">
							<a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
								Excel模板
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="${prc}/模板.xls">导入数据模板</a>
								</li>
								<li>
									<a href="${prc}/course/download/coursename">标准课程名称</a>
								</li>
							</ul>
						</div>
					</div>
					
				</div>
				</form>
				<c:if test="${isExistBackContent eq true}">
					<div class="alert alert-error" >
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						<h4>${backContent}</h4>
					</div>
				</c:if>
				<c:if test="${isExist eq true}">
					<div class="alert alert-error" >
						<a class="close" data-dismiss="alert" href="#">&times;</a>
						<h4>已有数据!</h4>
						${className}已有数据,如果再次导入将会覆盖原有内容
					</div>
				</c:if>
			</div>
			<%--这里是导入表单 end --%>
			<div class="span1">
			</div>
		</div>
	</div>
	
	<%--引入页脚 --%>
	<%@include file="/pages/common/footer.jsp" %>
	<%--引入页脚 --%>
	<script>
		$('#fileInput').popover({
			content:'上传格式只限xls,最大10m',
			placement:'top',
			trigger:'hover'
		});
	</script>
	
  	</body>
</html>