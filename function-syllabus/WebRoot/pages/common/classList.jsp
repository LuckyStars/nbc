<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span2 " >
	<h3 class="visible-desktop visible-tablet">&nbsp;</h3>
	<div class="accordion" id="accordion2">
		<c:if test="${not empty grades}">
		<c:forEach items="${grades}" var="grade" varStatus="i">
			<div class="accordion-group">
				<div class="accordion-heading ">
					<a class="accordion-toggle btn btn-info dropdown-toggle" data-toggle="collapse" data-parent="#accordion2" 
					href="#collapse${i.index }" >
					${grade.gradeName }
					</a>
				</div>
				<div id="collapse${i.index }" class="accordion-body collapse">
					<div class="accordion-inner">
						<ul class="nav">
							<c:if test="${not empty grade.classes}">
							<c:forEach items="${grade.classes}" var="clazz">
								<li>
									<a href="${prc}${jumpUrl}${clazz.id}">
									${clazz.name }
									</a><br/>
								</li>
							</c:forEach>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</c:forEach>
		</c:if>
	</div>
	
	<div class="alert alert-info" id="alertInfo">
		<a class="close" data-dismiss="alert" href="#">&times;</a>
		<a class="btn btn-info btn-mini" href="${prc }${refreshUrl}">更新缓存</a>
		*如果您看到的班级列表和实际情况不相符,
		请更新缓存
	</div>
	
</div>
