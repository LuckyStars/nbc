<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib prefix="invStatus" uri="InvStatus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>邀请查看 教师列表</title>
	
	<link href="${prc}/function/function-invatition/teacherList/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/jqui.js"></script>
	<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	var prc ="${pageContext.request.contextPath}";
    $(function () {
        $("table tr:odd").css("background", "#f0f8fc");
        $("table tr:even").css("background", "#d5e0ee");
        $(".add-loadtop1 img").click(function () {
            $(".bg").css("display", "none");
            $(".add-load").css("display", "none");
        });
        $("#search").click(function () {
        	window.location.href=prc+"/scMaster2/masterList_invatition.action?searchDate="+$.trim($("#searchDate").val())+"&searchTitle="+$.trim($("#searchTitle").val())+"&searchUser="+$.trim($("#searchUser").val());
        });
        $(".download").click(function () {
            $(".bg").css("display", "block");
            $(".add-load").css("display", "block");
            var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		$.ajax({
    			url:prc+"/scMaster2/detail_invatition.action",
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
						cwsHtml = cwsHtml +"<tr><td>"+num+"</td><td><span>"+coursewares[i].fileName+"</span></td><td>"+"<a class=\"downfile\" href=\""+prc + "/scMaster2/download_invatition.action?tsm2Invatition.id="+coursewares[i].id+"\">下载</a></td></tr>";
					}
					$("#downFiles").html(cwsHtml);
    			},
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				alert("出错了!");
    				window.location.href=prc+"/listSections.action?courseId="+courseId;
    			}
        	});
        });
    });
    
</script>
</head>
<body>
	<div class="con_conent fixed">
		<h1 class="title">
			<span class="title">当前位置：</span>
			<span class="text">首页 - </span>
			<span class="back">我收到的邀请</span>
		</h1>
		<div class="table_box fixed">
			<div class="nav">
				<span>提交日期:</span>
				<input type="text" name="searchDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${searchDate}" id="searchDate" style="width:180px;"/>
				<span>创建人:</span>
				<s:select list="users" name="searchUser" listKey="createrId" listValue="createrName" headerKey="" headerValue="" id="searchUser" cssStyle="width:90px;height:29px;"></s:select>
				<span>标题:</span> 
				<input type="text" name="searchTitle" value="${searchTitle}" id="searchTitle"/>
				<a class="cx" href="javascript:void(0);" id="search">查询</a>
			</div>
			<table width="100%" border="0">
				<tr>
					<th width="25%" scope="col">标题</th>
					<th width="25%" scope="col">提交时间</th>
					<th width="25%" scope="col">创建人</th>
					<th width="25%" scope="col">操作</th>
				</tr>
				<c:forEach items="${pm.datas }" var="subject">
					<tr id="${subject.id}">
						<td>
							<c:out value="${subject.title}" escapeXml="true"></c:out>
						</td>
						<td>
							<fmt:formatDate value="${subject.createTime}" pattern="yyyy-MM-dd" />
						</td>
						<td>${subject.createrName}</td>
						<td>
							<span class="space"><a href="${prc}/scMaster2/masterShow_invatition.action?tsm2Invatition.id=${subject.id}">查看</a></span>
							<c:if test="${subject.flag ==0}"><span class="space download"><a href="#">附件</a></span></c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
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