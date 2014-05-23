<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>index</title>
		<meta charset="utf-8">
	   	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap.css" rel="stylesheet" media="screen"/>
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap-theme.css" rel="stylesheet" media="screen"/>
	   	<link href="${prc }/function/function-weixin/signin.css" rel="stylesheet" media="screen"/>
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="${prc }/function/function-weixin/bs3/css/charisma-app.css" rel="stylesheet">
	   	<script src="${prc }/function/function-weixin/bs3/js/jquery.min.js"></script>
	   	<script type="text/javascript" src="${prc }/function/function-weixin/bs3/js/bootstrap.js" ></script>
	   	<script type="text/javascript">
	   		var time = 3;
	   		$(function(){
		   		$("#time").text(time);
		   		setInterval(function(){
		   			time = time -1;
		   			if(time == 0){
		   				window.location.href = "${prc}/weixin/login_weixin.action?type=list&username=${param.username}&password=${param.password}";
		   				return false;
			   		}
		   			$("#time").text(time);
			   	},1000);
		   	});
	   	</script>
	</head>
	<body>
		欢迎来到微信（校长工作台）。 <span id="time"></span>之后跳转。
	</body>
</html>