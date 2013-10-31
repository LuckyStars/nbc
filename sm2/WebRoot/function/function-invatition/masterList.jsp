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
	<script type="text/javascript">
	var prc ="${pageContext.request.contextPath}";
    $(function () {
        $("table tr:odd").css("background", "#f0f8fc");
        $("table tr:even").css("background", "#d5e0ee");
        $(".push").click(function () {
        	var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		window.location.href=prc+"/scMaster2/push_invatition.action?tsm2Invatition.id="+id;
        });
        $(".del").click(function () {
        	var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		window.location.href=prc+"/scMaster2/del_invatition.action?tsm2Invatition.id="+id;
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
				<select> </select> 
				<span>事项标题:</span> 
				<input type="text" />
				<a class="cx" href="#">查询</a>
			</div>
			<table width="100%" border="0">
				<tr>
					<th width="25%" scope="col">标题</th>
					<th width="25%" scope="col">发布时间</th>
					<th width="25%" scope="col">发布人</th>
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