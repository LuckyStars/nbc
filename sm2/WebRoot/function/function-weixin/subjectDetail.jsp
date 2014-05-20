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
	   	<link href="${prc}/function/function-weixin/lazyimg/bs3/css/pic.css" rel="stylesheet" media="screen">
	   	<script src="${prc }/function/function-weixin/bs3/js/jquery.min.js"></script>
	   	<script type="text/javascript" src="${prc }/function/function-weixin/bs3/js/bootstrap.js" ></script>
	   	<script type="text/javascript">
	  	 	var pageSize = 1; var _progressId;
			window.onscroll=function(){
				//网页可见区域高
				var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
				//网页被卷去的高
				var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
				var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
				if((b+372)>=c){
					loadData(pageSize);
				}
			};

			//下拉加载
			function loadData(pageS){
				$.get('${prc}/scMaster2/findDiss_weixin.action?pageSize='+pageS+"&progressId="+_progressId, function(data) {
					if(data!=''){
						var datastr =  eval(data);
						var _html="";
						$.each(datastr,function(i,n){
							_html+='评论人:'+n.userName+"&nbsp;&nbsp;评论内容"+n.content;
						});
						pageSize++;
					}
				});
			};
	   	
			//获取步骤
			function appendSub(stepId,subjectId,target){
				if(typeof(stepId)!= "undefined" && stepId!=""){
					$.ajax({
						url:'${prc}/scMaster2/findProgress_weixin.action?&stepId='+stepId,
						type:'post',
						dataType:'json',
						success:function(data){
							if(data){
								var _html = "";
								$.each(data,function(i,n){
									_html+='<li class="active"><a href="javascript:;" onclick=\'findDisByProId("'+subjectId+'","'+stepId+'","'+n.id+'","'+n.content+'")\'>'+n.name+'</a></li>';
								});
								if(_html !=""){
									$("#"+stepId).html(_html);
								}
								$(target)[0].onclick = function(){};
							}
						}
					});
				}
			}

			
	   		//获取评论
	   		function findDisByProId(subjectId,stepsId,proId,content){
	   			$("#home").html(content);
	   			_progressId = "";
	   			if(typeof(proId)!= "undefined" && proId!=""){
	   				_progressId = proId;
	   				$.ajax({
	   					url:'${prc}/scMaster2/findDiss_weixin.action?progressId='+proId,
		   				type:'post',
						dataType:'json',
						success:function(data){
		   					if(data){
								var _html="";
								$.each(data,function(i,n){
									_html+='评论人:'+n.userName+"&nbsp;&nbsp;评论内容"+n.content;
								});
								if(_html!=""){
									$("#profile").html(_html);
								}else{
									$("#profile").html("");
								}
							}
						}
		   			});
				}
	   		}

		   	//效果
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
				  	
				  	</div>
				  	<div class="" id="profile"></div>
				  	<!-- 
				  	<div class="tab-pane fade" id="messages">
				  		造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底造反到底
				  	</div>
				  	<div class="tab-pane fade" id="settings">
				  		坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?坑爹呢?
				  	</div> -->
				</div>
				
			</div>

		</div>
		</div>
		<div id="navga" style="left:-200px;position:fixed;top:0px;width:200px;height:100%;background-color: white;border-right:1px solid #DDDDDD; 
" class="fadeOut">
			<div id="parent">
			<c:forEach items="${steps }" var="step" varStatus="i">
				<div class="panel-group" id="accordion">
				  <div class="panel panel-default">
				    <div class="panel-heading">
				      <h4 class="panel-title" onclick="appendSub('${step.id}','${subject.id}',this)">
				        <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion" href="#contentTitle${step.id}">
				          ${step.name}
				        </a>
				      </h4>
				    </div>
				    <div class="panel-collapse" id="contentTitle">
				      <div class="panel-body" id="contentTitle${step.id}">
				        <ul class="nav nav-pills nav-stacked" id="${step.id}" >
						</ul>
				      </div>
				    </div>
				  </div>
				</div>
			</c:forEach>
		</div>
			<img onclick="showNav();" src="${prc}/function/function-weixin/img/2.png"/>
		</div>
		
	</body>
</html>