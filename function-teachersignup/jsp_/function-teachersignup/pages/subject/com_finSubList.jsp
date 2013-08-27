<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>已结束报名一览</title>
	<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc}/common/agent.js"></script>
	<script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.7.1.min.js"></script>
</head>
<body>
	<div class="con_conent fixed">
    	<h1 class="title">
    		<span class="title">教师报名</span>
    		<span class="back">
    			<a href="${prc}/teachersignup/commonFinList_act.action">返回上一页</a>
    		</span>
   		</h1>
       	<div class="table_box fixed">
            <div class="box">
	            
	            <p class="join">${asvo.actName }报名一览</p>
	            <br/>
	            <c:if test="${not empty asvo.actComment }">
	            <strong style="font-size: 14px;">报名简介：</strong>
	            <div id="coomments" style="width:450px;overflow:hidden;word-warp:break-word;word-break:break-all;" >
	            <c:out value="${asvo.actComment}" escapeXml="true"></c:out>
	            </div>
	            <br/>
	            </c:if>
	            <c:if test="${not empty asvo.attaName}">
	            <strong style="font-size: 14px;">附件：</strong>
	            <a href="${prc}/teachersignup/downAtta_act.action?id=${asvo.actId}"><c:out value="${asvo.attaName}" escapeXml="true"></c:out></a>
	            <br/>
	            </c:if>
	            <br/>
	            
	    		<table width="448px" border="0" class="table-xi">
		            <tr>
		                <th width="50%" scope="col">报名类型</th>
		                <th width="50%" scope="col">获奖情况</th>
		            </tr>
		            <c:if test="${not empty signList}">
		            	<c:forEach items="${signList}" var="sign">
		            		<tr>
				                <td><c:out value="${sign.signSubject}" escapeXml="true"></c:out></td>
				                <td><c:out value="${sign.rewardName}" escapeXml="true"></c:out></td>
				            </tr>
		            	</c:forEach>
		            </c:if>
	        	</table>
	            <a href="${prc}/teachersignup/commonFinList_act.action" class="btn btnright">返回</a>
       		</div>	
    	</div>
	</div>
    <%--弹出层--%>
    <div id="popDiv" style="display: none;">
	<div class="bg"></div>
		<div class="add1">
		<div class="add-top1">
			<p>补卡类型维护</p>
			<img src="${prc}/function/function-teachersignup/image/erro.jpg" onclick="closePop();" class="close" style="cursor:pointer;"/>
		</div>
		<div class="add-down">
			<div class="under">
				<p class="type">请选择您想要报名的类型：</p>
				<p><input type="checkbox" /><span>全选</span></p>
				<div class="check">
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				<p><input type="checkbox" /><span>小学高级职称</span></p>
				</div>
				<a href="#" class="return" style="margin-left:30px;">确定</a>
				<a href="#" class="return">取消</a>
			</div>
		</div>
	</div>
	</div>
	<%--弹出层--%>
<script>
	function closePop(){
		$("#popDiv").fadeOut(200);
	}
	function openPop(){
		$("#popDiv").fadeIn(200);
	}
</script>
</body>
</html>