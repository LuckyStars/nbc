<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>总结~汇报~关注性工作</title>
<link rel="stylesheet" href="${prc}/function/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${prc}/function/css/gzt.css" type="text/css"></link>
<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${prc}/function/function-masterSubList/js/schoolMaster.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<style>
table th {
	background: url(${prc}/function/img/table-bg.jpg) repeat-x;
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
		$("table tr:odd").css("background", "#fff");
		$("table tr:even").css("background", "#EDEFFE");
	});
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
		window.parent.selectTab('tab_zongjiehuibao');
	});
</script>
</head>
<body>
	<div class="main" style="width: 990px;">
		<div class="right1">
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
		</div>
			<table width="100%" border="0">
				<tr>
					<th width="5%" scope="col">序号</th>
					<th width="30%" scope="col">名称</th>
					<th width="10%" scope="col">部门</th>
					<th width="10%" scope="col">发布者</th>
					<th width="15%" scope="col">发布时间</th>
					<th width="25%" scope="col">操作</th>
				</tr>
				<c:forEach items="${pm.datas }" var="subject" varStatus="i">
					<tr>
						<td align="center">${i.index+1 }</td>
						<td class="lan" align="center">
							<span class="words">
								<a href="${prc}/scMaster2/detail_master.action?id=${subject.id }"
									>${subject.title }</a>
							</span>
						</td>
						<td align="center">${subject.departmentName }</td>
						<td align="center">${subject.createrName}</td>
						<td align="center"><fmt:formatDate
								value="${subject.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate>
						</td>
						<td align="center">
							<!--        	<span class="praise">赞</span>--> <!--        	<span class="konw">我知道了</span>-->
							<c:if test="${subject.flag eq '1'}">
								<a href="#" onclick="javascript:stick('${subject.id }',3,'blue');" id="${subject.id }">
									<img src="${prc}/function/img/blue1.png" />
								</a>
							</c:if> 
							<c:if test="${subject.flag ne '1'}">
								<a href="#" onclick="javascript:stick('${subject.id }',1,'blue');" id="${subject.id }">
									<img src="${prc}/function/img/blue3.png" />
								</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${empty pm.datas}"><%--无数据 --%>
		    <div style="margin-top: 40px;text-align: center;">
		    	<img src="${prc }/function/img/no_data.png" alt="暂无上报数据" />
		    </div>
		    </c:if>
			<%@ include file="pager.jsp"%>
		</div>
</body>
</html>