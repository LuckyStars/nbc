<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/common.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	   	<title>按教师查询课程</title>
	   	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	<!-- Bootstrap -->
	   	<link href="${med }/basic/bootstrap.css" rel="stylesheet" media="screen">
	   	<link href="${med }/basic/bootstrap-responsive.css" rel="stylesheet" media="screen">
		<script type="text/javascript">
		function search(){
			var teacher = document.getElementById("searchTeahcer").value;
			document.forms[0].action="${prc}/lesson/teacher/"+teacher;
			document.forms[0].submit();
		}
		</script>
  	</head>
  	<body>
	
	<%--引入导航条 --%>
	<c:set var="curnav" scope="page" value="1"></c:set>
  	<%@ include file="/pages/common/navList.jsp" %>
  	<%--引入导航条 --%>
	<div class="container-fluid">
		<div class="row-fluid">
			<%--这里是查询输入框 --%>
			<div class="span2 " >
				<h3 class="visible-desktop visible-tablet">&nbsp;</h3>
				<form class="form-search" action =${prc}/lesson/teacher/>
				  <div class="input-append">
					<input type="text" class="span8 search-query" id="searchTeahcer" data-provide="typeahead"
					data-source=${teacherNameArray } autocomplete="off">
					<button type="button" class="btn btn-info" id="subSearch" onclick="search()">查询</button>
				  </div>
				 </form>
			</div>
			<%--这里是查询输入框 end --%>
			<div class="span1"></div>
			<%--这里是课程表 --%>
			<div class="span8">
				<h3 class="muted text-center">${teacherName }老师的课程</h3>
				<table class="table table-hover table-condensed table-bordered">
					<thead >
						<c:forEach var="headRow" items="${allLessons}" varStatus="i">
							<c:if test="${i.index==0}">
							<tr>
								<c:forEach var="head" items="${headRow}" >
									<th>
										${head}
									</th>
								</c:forEach>
							</tr>
							</c:if>
						</c:forEach>
					</thead>
					<tbody>
						<c:forEach var="row" items="${allLessons}" varStatus="i" >
							<c:if test="${i.index>0}">
							<tr>
								<c:forEach var="cell" items="${row}" >
									<th>
										${cell}
									</th>
								</c:forEach>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<%--这里是课程表 end--%>
			
			<div class="span1">
			</div>
		</div>
	</div>
	
	<%--引入页脚 --%>
	<%@include file="/pages/common/footer.jsp" %>
	
	<script type="text/javascript">
		$('#searchTeahcer').popover({
		content:'如果不输入内容可以查询自己的课程',
		placement:'top',
		trigger:'focus'});
	</script>
  	</body>
</html>