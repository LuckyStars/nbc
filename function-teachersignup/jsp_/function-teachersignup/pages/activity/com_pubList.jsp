<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
	<link href="${prc}/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${prc}/function/function-teachersignup/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${prc}/common/agent.js"></script>
	<script type="text/javascript" src="${prc}/function/function-teachersignup/js/jquery.countdown.js"></script>
	<style type="text/css">
	    .cntSeparator {
	        font-size: 45px;
	        margin: 5px 7px;
	        color: #000;
	    }
	    .desc div {
	        font-family: Arial;
	        width: 70px;
	        font-size: 13px;
	        font-weight: bold;
	        color: #000;
	    }
		.mLeft{
			margin-left:30px;
			float:left;
			width:300px;
		}
		.mRight{
			margin-right:30px;
			float:right;
			width:300px;
		}
		.top{
			border:1px solid #ccc;
		}
		.pubed{
			background-color:#FFCCCC
		}
		.pubed .cntSeparator{
			color: #F00;
		}
		.opened .cntSeparator{
			color: #09F;
		}
		.opened {
			background-color:#CCFFFF
		}
		.actTitle{
			text-align:center;
			color:#666666;
			margin:5px;
		}
		.countDownParent{
			text-align:center;
			margin:0 auto; 
			margin-top:20px;
			width:685px;
		}
		.oppcontens{
			font-size:16px;
			font-weight:bold;
			margin: 10px;
		}
		.popPar{
			text-align:center;
		}
	</style>
</head>
<body>
	<div class="con_conent fixed">
	     <h1 class="title">
	     	<span class="title">教师报名</span>
	     	<span class="back">
	     		<a href="${prc}/function/function-teachersignup/pages/index.jsp">返回上一页</a>
	     	</span>
	     </h1>
	        
	        <div class="table_box fixed">
				
				<c:if test="${not empty waitList }">
				<h1 class="actTitle">即将开始的报名</h1>
					<c:forEach items="${waitList}" var = "act">
						<script type="text/javascript">
						$(function(){
							$('#count${act.id}').countdown({
								startTime: "${act.countDownTime}",
								stepTime: 1,
								digitImages: 6,
								digitWidth: 53,
								digitHeight: 77,
								timerEnd: function(){ location.href='${prc}/teachersignup/comListPubed_act.action'; },
								image: "${prc}/function/function-teachersignup/image/digits2_red.png"
							});
						});
						</script>
						<div class="prompt pubed" >
			                <p class="tit1"><c:out value="${act.name}" escapeXml="true"></c:out></p>
			                <p class="tit2">报名时间：${act.openDate }-${act.endDate }</p>
							<div >
			                   <div class="countDownParent" id="count${act.id }"></div>
			                </div>
							<div style="margin-top:25px;">
			                    <div class="top mLeft" >
									想知道我们都为您提供了哪些项目吗？点击这里哦
									<span><a href="javascript:showPop('${act.id }');" class="joinins">查看项目</a></span>
								</div>
			                    <div class="top mRight" >
									现在报名还没有开放哦！
									<span><a class="joinined">未开放</a></span>
								</div>
			                </div>
			            </div>
					</c:forEach>
				</c:if>
				
				<c:if test="${not empty openList }">
				<h1 class="actTitle">正在进行的报名</h1>
					<c:forEach items="${openList}" var = "act">
						<script type="text/javascript">
						window.onload = function(){
							$('#count${act.id}').countdown({
								startTime: "${act.countDownTime}",
								stepTime: 1,
								digitImages: 6,
								digitWidth: 53,
								digitHeight: 77,
								timerEnd: function(){ location.href='${prc}/teachersignup/comListPubed_act.action'; },
								image: "${prc}/function/function-teachersignup/image/digits2_blue.png"
							});
						};
						</script>
						<div class="prompt opened" >
			                <p class="tit1"><c:out value="${act.name}" escapeXml="true"></c:out></p>
			                <p class="tit2">报名时间： ${act.openDate } - ${act.endDate }</p>
							<div >
			                   <div class="countDownParent" id="count${act.id }"></div>
			                </div>
							<div style="margin-top:25px;">
			                    <div class="top mLeft" >
									想知道我们都为您提供了哪些项目吗？点击这里哦
									<span><a href="javascript:showPop('${act.id }');" class="joinins">查看项目</a></span>
								</div>
			                    <div class="top mRight" >
									想知道您已经选了哪些项目吗？点击这里哦！
									<span><a href="${prc}/teachersignup/subList_sign.action?actId=${act.id}" class="joinin">我的报名</a></span>
								</div>
			                </div>
			            </div>
					</c:forEach>
				</c:if>
	    </div>
	
	</div>
	<%--弹出层 --%>
	
	<script type="text/javascript">
		function closePop(){
			$("#popDiv").fadeOut(100);
		}

		function showPop(actId){
			
			$("#popContent").html("");
			
			var result = $.ajax({
				url:"${prc}/teachersignup/subjectsByAct_act.action?id=" + actId,
				async:false
			}).responseText;
			result = JSON.parse(result);
			for(var i=0;i<result.subs.length;i++){
				$("<p class='oppcontens' >" + result.subs[i] + "</p>").appendTo($("#popContent"));
			}
			
			$("#popDiv").fadeIn(100);
		}
	</script>
	<div id="popDiv" style="display: none;" >
		<div class="bg"></div>
	    	<div class="add2" >
	      	<div class="add-top2">
	           	<p>报名项目</p>
	           	<img src="${prc}/function/function-teachersignup/image/erro.jpg" onclick="closePop();" class="close" style="cursor:pointer;"/>
	    	</div>
	    	<div id="popContent" class="popPar">
	       			
       		</div>
		</div>
	</div>
	<%--弹出层 --%>
</body>
</html>