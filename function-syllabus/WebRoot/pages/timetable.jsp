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
	<c:set var="curnav" scope="page" value="1"></c:set>
  	<%@ include file="/pages/common/navList.jsp" %>
  	<%--引入导航条 --%>
  	
	<div class="container-fluid">
		<div class="row-fluid">
		<div class="span1"></div>
			<%--这里是班级列表 --%>
			<c:set var="jumpUrl" scope="page" value="/lesson/class/"></c:set>
			<c:set var="refreshUrl" scope="page" value="/lesson/cache/refresh"></c:set>
			<%@include file="common/classList.jsp" %>
			<%--这里是班级列表 END--%>
			
			<%--这里是课程表 --%>
			<div class="span8">
				<h3 class="muted text-center">${className }</h3>
				<table class="table table-hover table-condensed table-bordered table-striped"> 
					<thead >
						<c:forEach var="headRow" items="${allLessons}"  varStatus="i">
							<c:if test="${i.index==0}">
								<tr>
									<c:forEach var="head" items="${headRow}" >
										<th>
											${head.courseName}
										</th>
									</c:forEach>
								</tr>
							</c:if>
						</c:forEach>
					</thead>
					
					<tbody>
						<c:forEach var="row" items="${allLessons}"  varStatus="i">
							<c:if test="${i.index>0}">
							<tr 
							<c:if test="${i.index%2==0}"> class="info"</c:if>
							>
								<c:forEach var="cell" items="${row}" >
									<td data-tea="${cell.teacherName }">
										${cell.courseName}
									</td>
								</c:forEach>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
				
				<%--这里任课教师信息相关 --%>
				<div class="alert alert-info" id="teaDiv" style="display:none;">
				  <strong id="teaName"></strong>
				</div>
				
				<div class="alert alert-info hidden-desktop" id="teaDiv" >
				<a class="close" data-dismiss="alert" href="#">&times;</a>
				  *点击课程可以查看任课教师
				</div>
				<%--这里任课教师信息相关 end--%>
				
			</div>
			<%--这里是课程表 end --%>
			<div class="span1">
			</div>
		</div>
	</div>
	
	<%--引入页脚 --%>
	<%@include file="/pages/common/footer.jsp" %>
	
	<script>
	<%-- 单元格的hover事件,需要提示任课教师信息的单元格请在td中加入data-tea属性,不需提示的单元格不用修改  --%>
		$("td").hover(
		  function () {
			var teaName = $(this).attr('data-tea');
			if($.trim(teaName)!=''){
				$("#teaName").html("任课教师:" + teaName);
				$('#teaDiv').fadeIn();
			}
		  },
		  function () {
			$('#teaDiv').hide();
		  }
		);
	</script>
	
  	</body>
</html>