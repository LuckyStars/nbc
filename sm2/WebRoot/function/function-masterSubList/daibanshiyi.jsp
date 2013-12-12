<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>tab_daibanshiyi</title>
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
.pages{
padding:0 15px; position:relative; right:0px; height:40px; width:300px; margin:0 auto;
}
.pages a{ display:block; padding:0px 10px; background:#fcfcfc; border:1px solid #ccc; text-align:center; line-height:20px; float:left; height:20px;  margin-left:15px; margin-top:20px;cursor:pointer; }
.pages a select{ width:45px;}
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
		<c:if test="${not empty moduleId }">
		$(".wtab").hide();
		$("#tab_${moduleId}").show();
		</c:if>
		window.parent.selectTab('tab_daibanshiyi');
	});
	function tabClick(moduleId){
		location.href="${prc}/scMaster2/daiBanPage_master.action?moduleId=" + moduleId;	
	}
</script>

</head>
<body>
	<div class="right">
		<div class="right-input1">
			<p class="work"  onclick="tabClick('zongjiehuibao');">
				<span class="ico">总结汇报</span><%-- <span class="ico-1">1</span>--%>
			</p>
			<p class="work1"  onclick="tabClick('qingshibaopi');">
				<span class="ico1">请示报批</span><%-- <span class="ico-1">1</span>--%>
			</p>
			<p class="work2" onclick="tabClick('jinjizhongyao');">
				<span class="ico2">紧急重要</span>
			</p>
			<p class="work3" onclick="tabClick('linshishixiang');">
				<span class="ico2">临时事项</span>
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
		<div class="one wtab" id="tab_zongjiehuibao" >
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
		<div class="two wtab" id="tab_qingshibaopi">
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
		<div class="three wtab" id="tab_jinjizhongyao">
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
		
		<div class="four wtab" id="tab_linshishixiang">
			<p class="tit4">
				<span>临时事项<%--<a href="#">5</a>条--%></span>
			</p>
			<table width="100%" border="0">
				<c:forEach items="${subList }" var="sub">
					<c:if test="${sub.moduleId=='linshishixiang' }">
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
		
		<c:if test="${not empty moduleId and not empty subList}"><%--如果指定模块才需要显示分页 --%>
		<div>
			<div class="pages">
				<c:if test="${pager.offset>=1}">
				<a href="${prc}/scMaster2/daiBanPage_master.action?moduleId=${moduleId }&pager.offset=${pager.offset-1}"
				>上一页</a>
				</c:if>
				<c:if test="${pager.offset<pm.totalPageNo}">
				<a href="${prc}/scMaster2/daiBanPage_master.action?moduleId=${moduleId }&pager.offset=${pager.offset+1}"
				>下一页</a>	
				</c:if>
				<a style="cursor: default;">共${pm.totalPageNo}页&nbsp;
					<select
						onchange="function(){
							location.href='${prc}/scMaster2/daiBanPage_master.action?moduleId=${moduleId }&pager.offset=' + this.value;
						}();"
					>
						<c:forEach var="i" begin="1" end="${pm.totalPageNo}">
						<option>${i}</option>
						</c:forEach>
					</select>
				</a>
			</div>
		</div>
		</c:if>
		
	</div>
</body>
</html>