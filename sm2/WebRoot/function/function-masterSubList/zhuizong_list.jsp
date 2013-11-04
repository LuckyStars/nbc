﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/function-masterSubList/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" href="${prc}/function/function-masterSubList/css/gzt.css" />

<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>

<style>
table th {
	background: url(${prc}/function/function-masterSubList/img/table-bg.jpg)
		repeat-x;
	height: 38px;
	line-height: 38px;
}

table td {
	height: 34px;
	line-height: 34px;
	text-align: center;
}
</style>
<script type="text/javascript">
	$(function() {
		$("table tr:odd").css("border", "1px solid #EDEFFF")
		$("table tr:even").css("border", "1px solid #EDEFFF")
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
	});
</script>
</head>
<body>
		<div class="right">
			<h1>
				当前位置：首页 - <span style="color: #002F7F">工作计划跟踪 - 学期工作计划动态</span>
			</h1>
			<form action="${prc}/scMaster2/list_master.action" method="post">
			<input type="hidden" name="moduleId" value="${moduleId}" />
			<input type="hidden" name="search.typeId" value="${search.typeId}" />
			<div class="right-input">
				<p>
					<label>部门:</label>
					<select name="search.departId" ></select>
				</p>
				<p>
					<label>发布者:</label>
					<input type="text" name="search.createrName" />
				</p>
				<p>
					<label>发布时间:</label>
					
					<input type="text" name="search.start" id="startDate"
					readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})"
					/>
					
					<input type="text" name="search.end" id="endDate"
					readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})"
					/>
				</p>
				<a href="#">查询</a>
			</div>
			</form>
			<table width="100%" border="0">
				
				<tr>
					<th width="20%" scope="col">名称</th>
					<th width="10%" scope="col">部门</th>
					<th width="10%" scope="col">发布者</th>
					<th width="10%" scope="col">发布时间</th>
					<th width="50%" scope="col">进度</th>
				</tr>
				<c:forEach items="${pm.datas}" var="sub">
					<tr>
						<td class="other">
						<c:out value="${sub.title}" escapeXml="true"></c:out>
						</td>
						<td>财务部</td>
						<td>${sub.createrName}</td>
						<td><fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
							<div class="pross">
								<img src="${prc}/function/function-masterSubList/img/pro-bg1.jpg"
									class="pross-img" style="width:${sub.progress}%;" />
							</div>
							<p class="pross-p">${sub.progress}%</p> <img
							src="${prc}/function/function-masterSubList/img/qi.png" class="qi" />
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td class="other">蓝天工程工作计划</td>
					<td>财务部</td>
					<td>刘莉莉</td>
					<td>今天</td>
					<td>
						<div class="pross">
							<img src="${prc}/function/function-masterSubList/img/pro-bg1.jpg"
								class="pross-img" style="width:50%;" />
						</div>
						<p class="pross-p">90%</p> <img
						src="${prc}/function/function-masterSubList/img/qi.png" class="qi" />
					</td>
				</tr>
			</table>
		</div>
		
		<%@ include file="pager.jsp" %>
</body>