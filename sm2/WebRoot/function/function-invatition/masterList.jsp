<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>邀请查看 校长列表</title>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
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
table td a img{ 
display: block;
float: left;
margin: 5px 0 0 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("table tr:odd").css("border", "1px solid #EDEFFF");
		$("table tr:even").css("border", "1px solid #EDEFFF");
	});
</script>
<script type="text/javascript">
	$(function() {
		window.parent.selectTab('tab_yaoqingchakan');
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
		$("#search").click(function () {
        	window.location.href="${prc}/scMaster2/masterList_invatition.action?searchDate="+$.trim($("#searchDate").val())+"&searchTitle="+$.trim($("#searchTitle").val())+"&searchUser="+$.trim($("#searchUser").val());
        });
        $(".download").click(function () {
            $(".bg").css("display", "block");
            $(".add-load").css("display", "block");
            var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		$.ajax({
    			url:"${prc}/scMaster2/detail_invatition.action",
    			type:'post',
    			data:{"tsm2Invatition.id":id,"t":Math.random()},
    			dataType:'json',
    			success:function(data){
					var coursewares = eval('('+data.resources+')');
					var cwsHtml = "<tr><th width=\"13%\" scope=\"col\">序号</th><th width=\"66%\" scope=\"col\">附件名称</th><th width=\"21%\" scope=\"col\">操作</th></tr>";
					count=coursewares.length;
					var num=0;
					for(var i=0;i<count;i++){
						num = i+1;
						cwsHtml = cwsHtml +"<tr><td>"+num+"</td><td><span>"+coursewares[i].fileName+"</span></td><td>"+"<a class=\"downfile\" href=\"${prc}/scMaster2/download_invatition.action?tsm2Invatition.id="+coursewares[i].id+"\">下载</a></td></tr>";
					}
					$("#downFiles").html(cwsHtml);
    			},
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				alert("出错了!");
    				window.location.href="${prc}/listSections.action?courseId="+courseId;
    			}
        	});
        });
	});
</script>
</head>
<body>
<div class="main" style="width: 990px;">
		<div class="right1">
    		<input type="hidden" name="moduleId" value="${moduleId}" />
			<input type="hidden" name="search.typeId" value="${search.typeId}" />
			<div class="right-input">
				<p>
					<label>提交日期:</label>
					<input type="text" name="searchDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${searchDate}" id="searchDate" style="width:180px;"/>
				</p>
				<p>
					<label>创建人:</label>
					<s:select list="persons" name="searchUser" listKey="uid" listValue="name" headerKey="" headerValue="" id="searchUser" cssStyle="width:90px;"></s:select>
				</p>
				<p> 
					<label>标题:</label>
					<input type="text" name="searchTitle" value="${searchTitle}" id="searchTitle"/>
				</p>
					<a href="javascript:void(0);" id="search">查询</a>
			</div>
			
			
			<table width="100%" border="0">
				<tr>
					<th width="30%" scope="col">邀请内容</th>
					<th width="20%" scope="col">邀请人</th>
					<th width="20%" scope="col">提交时间</th>
					<th width="30%" scope="col">操作</th>
				</tr>
				
				<c:forEach items="${pm.datas }" var="subject">
					<tr id="${subject.id}" >
						<td class="other">
							<a href="${prc}/scMaster2/masterShow_invatition.action?tsm2Invatition.id=${subject.id}">
								<c:out value="${subject.title}" escapeXml="true"></c:out>
							</a>
						</td>
						<td>${subject.createrName}</td>
						<td>
							<fmt:formatDate value="${subject.createTime}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<span class="space"><a href="${prc}/scMaster2/masterShow_invatition.action?tsm2Invatition.id=${subject.id}">查看</a></span>
							<c:if test="${subject.flag ==0}"><span class="space download"><a href="#">附件</a></span></c:if>
						</td>
					</tr>
				</c:forEach>
				<%--
				
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
				
				
				
				 --%>
			</table>
			
			<c:if test="${empty pm.datas}"><%--无数据 --%>
		    <div style="margin-top: 40px;text-align: center;">
		    	<img src="${prc }/function/img/no_data.png" alt="暂无上报数据" />
		    </div>
		    </c:if>
		</div>
		<div  style="text-align:center;font-size:15px;margin-top:20px;">
			<c:if test="${pm.total>0}">
	        <pg:pager url="${prc}/scMaster2/masterList_invatition.action"
				items="${pm.totalPageNo}" maxPageItems="${pm.totalPageNo}" maxIndexPages="3" export="currentPageNumber=pageNumber">
				<pg:param name="searchDate" value="${searchDate}"/>
				<pg:param name="searchTitle" value="${searchTitle}"/>
				<pg:param name="searchUser" value="${searchUser}"/>
	
				总计${pm.total}条
				<pg:first>
					<a href="${pageUrl}">首页</a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl}" >上一页</a> 
				</pg:prev>
				<pg:pages>
					<c:choose>
						<c:when test="${currentPageNumber eq pageNumber}">
							<font color="red">${pageNumber}</font>
						</c:when>
						<c:otherwise>
							<a href="${pageUrl}">${pageNumber }</a>
						</c:otherwise>
					</c:choose>
				</pg:pages>
				<pg:next>
					<a href="${pageUrl}" >下一页</a> 
				</pg:next>
				<pg:last>
					<a href="${pageUrl}">尾页</a>
				</pg:last>
			</pg:pager>
			</c:if>
			</div>
	</div>
	
	<!--弹出层1-->
	<div class="bg"></div>
	<div class="add-load">
		<div class="add-loadtop1">
			<p>附件列表</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-loaddown">
			<table width="430px" style="border: 1px solid #d7d7d7; margin-top: 10px;" id="downFiles">
			</table>
		</div>
	</div>
</body>
</html>