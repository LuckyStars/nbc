<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${prc}/function/css/gzt.css" />
<style type="text/css">
table th {
	background: url(${prc}/function/img/table-bg.jpg) repeat-x;
	height: 38px;
	line-height: 38px;
}

table td {
	height: 34px;
	line-height: 34px;
	text-align: left;
	padding-left: 50px;
}
</style>

<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("table tr:even").css("background", "#fff");
		$("table tr:odd").css("background", "#EDEFFE");
	});
</script>

<script type="text/javascript">
	$(function() {
		$(".nav-list-inner a").click(function() {
			$(".current").removeClass("current");
			$(this).addClass("current");
		});
		$(".li-blue").on("click", ".cols", function(e) {
			$(".nav-list-inner").hide();
			$(".li-blue .cols-open").attr("class", "cols");
			$(this).parent().next().show();
			$(this).attr("class", "cols-open");
			e.stopPropagation();
		});
		$(".li-blue").on("click", ".cols-open", function(e) {
			$(this).parent().next().hide();
			$(this).attr("class", "cols");
			e.stopPropagation();
		});
		$(".li-blue").on("click", function() {
			$(this).find("span").trigger("click");
		});
		$(".box-top li").click(function() {
			$(".conshen").hide().eq($(this).index()).show();
		});
		$(".addtabs").click(function() {
			$("#add-tab").show();
		});
		$("#btn-addfujian").click(function() {
			$("#add-fujian").show();
		});
		$("#aaa").click(function() {
			$("#bbb").css({
				"top" : $(this).offset().top + 50,
				"left" : $(this).offset().left + 50
			}).show();
		});
		$(".button").click(function() {
			$(".bg").show();
			$(".add2").show();
		});
		$(".close").click(function() {
			$(".bg").hide();
			$(".add2").hide();
		});
		$(".work").click(function() {
			$(".one").show();
			$(".two").hide();
			$(".three").hide();
		});
		$(".work1").click(function() {
			$(".one").hide();
			$(".two").show();
			$(".three").hide();
		});
		$(".work2").click(function() {
			$(".one").hide();
			$(".three").show();
			$(".two").hide();
		});
	});
</script>
</head>
<body>
	<div class="right">
		<h1>
			当前位置：首页 - <span style="color: #0374C2">您的待办事宜</span>
		</h1>
		<div class="right-input1">
			<p class="work">
				<span class="ico">总结汇报</span><%-- <span class="ico-1">1</span>--%>
			</p>
			<p class="work1">
				<span class="ico1">请示报批</span><%-- <span class="ico-1">1</span>--%>
			</p>
			<p class="work2">
				<span class="ico2">紧急重要</span>
			</p>
			<%--
				<select>
					<option>全部</option>
					<option>总结汇报</option>
					<option>请示报批</option>
					<option>紧急重要</option>
					<option>临时事项</option>
				</select>
			 
			 
			<p>
				<label>名称:</label><select></select>
			</p>
			<p>
				<label>时间:</label><select></select>
			</p>
			<a href="#">查询</a>--%>
		</div>
		<div class="one">
			<p class="tit1">
				<span>总结汇报<%--<a href="#">5</a>条--%></span>
			</p>
			<table width="100%" border="0">
				<c:forEach items="${subList }" var="sub">
					<c:if test="${sub.moduleId=='zongjiehuibao' }">
					<tr>
						<td width="25%"><span class="lan1">${sub.title }</span>
						</td>
						<td width="25%">${sub.createrName }</td>
						<td width="10%">
							<fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd HH:mm" />
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<div class="two">
			<p class="tit2">
				<span>请示报批<%--<a href="#">5</a>条--%></span>
			</p>
			<table width="100%" border="0">
				<c:forEach items="${subList }" var="sub">
					<c:if test="${sub.moduleId=='qingshibaopi' }">
					<tr>
						<td width="25%"><span class="lan1">${sub.title }</span>
						</td>
						<td width="25%">${sub.createrName }</td>
						<td width="10%">
							<fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd HH:mm" />
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<div class="three">
			<p class="tit3">
				<span>紧急重要<%--<a href="#">5</a>条--%></span>
			</p>
			<table width="100%" border="0">
				<c:forEach items="${subList }" var="sub">
					<c:if test="${sub.moduleId=='jinjizhongyao' }">
					<tr>
						<td width="25%"><span class="lan1">${sub.title }</span>
						</td>
						<td width="25%">${sub.createrName }</td>
						<td width="10%">
							<fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd HH:mm" />
						</td>
					</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>