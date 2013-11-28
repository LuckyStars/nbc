<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" href="${prc}/function/function-masterSubList/css/gzt.css" />
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${prc}/function/function-masterSubList/js/schoolMaster.js"></script>
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
		$("table tr:odd").css("border", "1px solid #EDEFFF");
		$("table tr:even").css("border", "1px solid #EDEFFF");
		$("td a img").addClass("qi");
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
<div class="main" style="width: 990px;">
		<div class="right1">
			<h1>
				当前位置：<a href="javascript:parent.location.href='${prc}/scMaster2/index_index.action';">首页</a> - <span style="color: #002F7F">工作计划跟踪 -</span>
			</h1>
			<form action="${prc}/scMaster2/list_master.action" method="post">
    		<input type="hidden" name="moduleId" value="${moduleId}" />
			<input type="hidden" name="search.typeId" value="${search.typeId}" />
			<div class="right-input">
				<p>
					<label>部门:</label>
					<select name="search.departId" >
						<option></option>
						<c:forEach items="${departments}" var="department" >
							<option id="${department.id}" <c:if test="${search.departId==department.id}">selected</c:if>>${department.name}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>发布者:</label>
					<input type="text" name="search.createrName" value="${search.createrName }" />
				</p>
				<p>
					<label>发布时间:</label>
					<input type="text" name="search.start" id="startDate"
					readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})" 
					value="<fmt:formatDate value='${search.start}' pattern='yyyy-MM-dd'></fmt:formatDate>"/>
					
					<input type="text" name="search.end" id="endDate"
					readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDate\')}'})" 
					value="<fmt:formatDate value='${search.end}' pattern='yyyy-MM-dd'></fmt:formatDate>"/>
				</p>
					<a href="javascript:document.forms[0].submit();">查询</a>
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
							<a href="${prc}/scMaster2/detail_master.action?id=${sub.id }" target="_blank">
								<c:out value="${sub.title}" escapeXml="true"></c:out>
							</a>
						</td>
						<td>${sub.departmentName}</td>
						<td>${sub.createrName}</td>
						<td><fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd"/></td>
						<td>
							<div class="pross">
								<img src="${prc}/function/images/percentLine/${sub.progress}.png"
									class="pross-img"/>
							</div>
							<c:if test="${sub.flag eq '1'}">
								<a href="#" onclick="javascript:stick('${sub.id }',3,'qi');" id="${sub.id }">
									<img src="${prc}/function/img/qi1.png" />
								</a>
							</c:if> 
							<c:if test="${sub.flag ne '1'}">
								<a href="#" onclick="javascript:stick('${sub.id }',1,'qi');" id="${sub.id }">
									<img src="${prc}/function/img/qi3.png" />
								</a>
							</c:if>
<!--								<img src="${prc}/fu nction/function-masterSubList/img/qi.png" class="qi" />-->
						</td>
					</tr>
				</c:forEach>
				
			</table>
		</div>
		<%@ include file="pager.jsp" %>
	</div>
</body>
</html>