<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title></title>
<link href="${prc}/function/function-week/css/index.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />

<script src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${prc}/function/js/masonry.pkgd.js"></script>

<script type="text/javascript">
$('#managers').combotree({  
 	 url: 'findAllManager_user.action',
 	 required: true  
 	}); 
 	$(function(){
 		window.parent.selectTab('tab_week');
 	});
</script>
<style type="text/css">
.tit_boder{
			background: #DCE3FF;
			width: 980px;
			height: 30px;
			line-height: 30px;
			margin: 4px auto;
			font-size: 12px;
			border: 1px solid #A4B3EE;
		}

</style>
</head>
<body>
	<div class="right">
	
		<div class="content">
			<h3 class="tit_boder" style="height:36px;" >
				<form action="${prc}/scMaster2/search_week.action" method="post" name="searchForm">
				发布时间： 
				<input class="Wdate" 
				name="search.updateDate" 
				value="<fmt:formatDate value='${search.updateDate}' pattern='yyyy-MM-dd'/>" 
				type="text" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'<fmt:formatDate value='${weekStart}' pattern='yyyy-MM-dd'/>',maxDate:'今天'})"/>
				负责人：
				<select name="search.publisher" 
				style="width:120px;" id="managers" 
				class="easyui-combotree" 
				data-options="url:'findAllManager_user.action'" multiple="true" 
				cascadeCheck="false" ></select>
				事件状态： 
				<select name="search.status" >
					<option value="-1" >全部</option>
					<option value="0" >新增</option>
					<option value="2" >更新</option>
				</select>
				事件类型： 
				<select name="search.subType"
					style="width:120px;"
					class="easyui-combotree" 
					data-options="url:'${prc}/function/function-week/types.json'" multiple="true" 
					cascadeCheck="false" >
				</select>
				
				<a class="button" id="select1" href="javascript:document.searchForm.submit();">查询</a>
				</form>
			</h3>

			
			<h2>${personTitle}</h2>
			
			
			<c:if test="${empty view}"><%--无数据 --%>
		    <div style="margin-top: 40px;text-align: center;">
		    	<img src="${prc }/function/img/no_data.png" alt="暂无上报数据" />
		    </div>
		    </c:if>
			
			<div class="workspace">
				<div class="masonry js-masonry"
					data-masonry-options='{ "columnWidth": 60, "gutter": 10 }'
				>
				
				<c:forEach items="${view}" var="entry" >
				<div class="item con1">
					<div class="listes">
						<ul class="workspacelist">
							<p class="listtitle">${entry.value.name }</p>
							<p class="news">
								<span>本周新增</span>
							</p>
							<li>
								<span class="gary">汇总事项</span>
								<span class="gary">所属类型</span>
							</li>
							<c:forEach items="${entry.value.subs}" var="sub">
								<c:if test="${sub.status=='new' }">
								<li>
									<span class="blue">
									<a href="${prc}/scMaster2/detail_master.action?id=${sub.subId}">
									<c:out value="${sub.subTitle}" escapeXml="true"/>
									</a>
									</span>
									<span class="gary">${sub.typeName }</span>
								</li>
								</c:if>
							</c:forEach>
							<p class="news">
								<span>本周更新</span>
							</p>
							<li>
								<span class="gary">汇总事项</span><span class="gary">所属类型</span>
							</li>
							<c:forEach items="${entry.value.subs}" var="sub">
								<c:if test="${sub.status=='updated' }">
								<li>
									<span class="blue">
									<a href="${prc}/scMaster2/detail_master.action?id=${sub.subId}">
									<c:out value="${sub.subTitle}" escapeXml="true"/>
									</a>
									</span>
									<span class="gary">${sub.typeName }</span>
								</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
				</c:forEach>
				
					<%--
					
					<div class="item con1">
						<div class="listes">
							<ul class="workspacelist">
								<p class="listtitle">汪忱的本周工作</p>
								<p class="news">
									<span>本周新增</span>
								</p>
								<li><span class="gary">汇总事项</span><span class="gary">所属类型</span>
								</li>
								<li><span class="blue">事项一</span><span class="gary">总结报告</span>
								</li>
								<p class="news">
									<span>本周更新</span>
								</p>
								<li><span class="gary">汇总事项</span><span class="gary">所属类型</span>
								</li>
								<li><span class="blue">事项一</span><span class="gary">总结报告</span>
								</li>
							</ul>
						</div>
					</div>--%>



				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
