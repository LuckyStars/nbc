<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>index</title>
	   	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	<meta charset="utf-8">
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap.css" rel="stylesheet" media="screen">
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap-theme.css" rel="stylesheet" media="screen">
	   	<link href="${prc }/function/function-weixin/basic.css" rel="stylesheet" media="screen">
	   	<script src="${prc }/function/function-weixin/bs3/js/jquery.min.js"></script>
	   	<script type="text/javascript" src="${prc }/function/function-weixin/bs3/js/bootstrap.js" ></script>

	   	<script type="text/javascript">
	   		var showNav = function(){
	   			var _pageCon = $("#page_container");
	   			var _nav = $("#navga");

	   			_nav.animate({
	   				left:_nav.css("left")=="0px"?"-200px":"0px"
	   			},"fast");

	   			return;
	   			if(_pageCon.css("left")=="0px"){
	   				_pageCon.css("width",_pageCon.css("width"));
	   				_pageCon.css("position","fixed");
	   				_pageCon.animate({
	   					left:"200px"
	   				},"fast");
	   				_pageCon.css("width","100%");
	   				_pageCon.css("position","absolute");
	   			}else{
	   				_pageCon.css("width","100%");
	   				_pageCon.css("position","absolute");
	   				_pageCon.animate({
	   					left:"0px"
	   				},"fast");
	   			}
	   		};
		function apendSub(stepId){
			
		}
	   	</script>
	   	<style type="text/css">

	   		@keyframes fadeOut {
			  	0% {opacity: 1;}
			  	100% {opacity: 0;}
			}

			.fadeOut {
			  	animation-name: fadeOut;
			}
	   		@keyframes fadeIn {
			  	0% {opacity: 0;}
			  	100% {opacity: 1;}
			}

			.fadeIn {
			  animation-name: fadeIn;
			}
		   	@keyframes slideInLeft {
			  0% {
			    opacity: 0;
			    transform: translateX(-2000px);
			  }

			  100% {
			    transform: translateX(0);
			  }
			}

			.slideInLeft {
			  	animation-name: slideInLeft;
			}

			@keyframes slideOutLeft {
			  0% {
			    transform: translateX(0);
			  }

			  100% {
			    opacity: 0;
			    transform: translateX(-2000px);
			  }
			}

			.slideOutLeft {
			  	animation-name: slideOutLeft;
			}
	   	</style>
	</head>
	<body >
		<div id="page_container" style="left:0px;" >
		<div class="jumbotron">
		  	<div class="container">
		  		<h1 onclick="showNav();">${subject.title }</h1>
		  		<div>${subject.content }</div>
		  	</div>
		</div>
		<div class="container" >
			<div class="row" >
				<!-- Tab panes -->
				<div class="tab-content">
				  	<div class="tab-pane fade in active" id="home">
				  		大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁大练钢铁
				  	</div>
				  	<div class="tab-pane fade" id="profile">
				  	唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑唱红打黑</div>
				  	<div class="tab-pane fade" id="messages">
				  		造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底
				  	</div>
				  	<div class="tab-pane fade" id="settings">
				  		坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?
				  	</div>
				</div>
				
			</div>

		</div>
		</div>
		<div id="navga" style="left:-200px;position:fixed;top:0px;width:200px;height:100%;background-color: white;border-right:1px solid #DDDDDD;" class="fadeOut">
			<c:forEach items="${steps }" var="step" varStatus="i">
				<div class="panel-group" id="accordion">
				  <div class="panel panel-default" id="${step.id}">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="appendSub('${step.id}')">
				          ${step.name }
				        </a>
				      </h4>
				    </div>
				    <div id="${step.id}" class="panel-collapse collapse">
				      <div class="panel-body">
				        <ul class="nav nav-pills nav-stacked">
							<li class="active"><a href="#">中文连接1</a></li>
							<li><a href="#">中文连接2</a></li>
							<li><a href="#">中文连接3</a></li>
							<li><a href="#">中文连接4</a></li>
							<li><a href="#">中文连接5</a></li>
						</ul>
				      </div>
				    </div>
				  </div>
				</div>
			</c:forEach>
		</div>
	</body>
</html>