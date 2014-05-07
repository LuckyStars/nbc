<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 这里是页头可以在单独文件引入 end--%> 
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			 <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar collapsed">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span><span class="icon-bar"></span>
			 </a>
			 <a href="#" class="brand">课程表</a>
			<div class="nav-collapse navbar-responsive-collapse collapse">
				<ul class="nav">
					
					<li class="dropdown <c:if test="${curnav==1}">active</c:if>">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						 查看课程<strong class="caret"></strong>
						</a>
						<ul class="dropdown-menu well">
							<li>
								<a href="${prc}/lesson/teacher/">按教师查询</a>
							</li>
							<li>
								<a href="${prc}/lesson/class/">按班级查询</a>
							</li>
						</ul>
					</li>
					<li class="<c:if test="${curnav==2}">active</c:if>">
						<a href="${prc}/course/import" >数据录入</a>
					</li>
					<%--
					<li>
						<a href="#">编辑课程</a>
					</li>
					<li>
						<a href="#">权限管理</a>
					</li> --%>
				</ul>
				<ul class="nav pull-right">
					<li class="divider-vertical">
					</li>
					<li class="dropdown">
						 <a data-toggle="dropdown" class="dropdown-toggle" href="#">
						 操作<strong class="caret"></strong>
						 </a>
						<ul class="dropdown-menu well">
							<li>
								<a href="#">退出</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>   
	</div>
</div>	
<br/>
<br/>
<%-- 这里是页头可以在单独文件引入 end--%>