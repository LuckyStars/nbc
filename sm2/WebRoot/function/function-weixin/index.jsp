<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	   	<script src="${prc }/function/function-weixin/bs3/js/jquery.min.js"></script>
	   	<script type="text/javascript" src="${prc }/function/function-weixin/bs3/js/bootstrap.js" ></script>
	   	<script type="text/javascript">
	  
	   	</script>
	</head>
	<body >
		<div class="container">
	      <form class="form-signin" role="form" action="${prc}/weixin/login_weixin.action?type=welcome" method="post" >
	      	<input type="hidden" name="openId" value='<%=request.getParameter("openId")%>'>
	        <h2 class="form-signin-heading text-center">请登录</h2>
	        <input type="text" class="form-control" placeholder="用户名" name="username" required>
	        <input type="password" class="form-control" placeholder="密 码" name="password"  required>
	        	        <button class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>
	      </form>
		</div>
	</body>
</html>