<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>临时事项详细</title>
<link href="${prc}/function/function-linshi/css/index.css" rel="stylesheet"
	type="text/css" />
<link href="${prc}/function/function-linshi/css/gzt.css" rel="stylesheet" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
	.qi {
		margin:25px 0 0 95px;cursor:pointer;
		}
		
	.titH1{
		background:url(${prc}/function/images/right_title.png);
		width:983px;
		height:30px;
		line-height:30px;
		margin:4px auto;
		font-size:12px;
		color:#6d6c6c;
		font-weight:normal;
		text-indent:26px;
	}
	.tabs-wp {
	height:32px;
	line-height:32px;
	border-bottom:1px solid #85bbe9;
	width:900px;
	margin:0 auto;
	padding:0;
	}
	.tabs-wp .addtabs {
		height:32px;
		line-height:32px;
		float:right;
		cursor:pointer;
	}
	.tabs-wp .addtabs img {
		margin-top:10px;
		float:right;
	}
	.tabs {
		height:32px;
		line-height:32px;
		border-bottom:1px solid #85bbe9;
		width:860px;
		margin:0 auto;
		padding:0;
		float:left;
	}
	.tabs li {
		border:1px solid #85bbe9;
		border-bottom:none;
		height:32px;
		line-height:32px;
		float:left;
		list-style:none;
		padding:0 20px;
		font-size:16px;
		color:#368fe8;
		margin-left:10px;
	}
	.tabs li.cur {
		position:relative;
		height:30px;
		line-height:30px;
		top:-1px;
		border:1px solid #85bbe9;
		border-top:3px solid #0070d2;
		border-bottom-color:#fff;
		color:#0a5eb2;
	}
	.box {
	width:900px;
	height:auto !important;
	height:328px;
	min-height:328px;
	border:2px solid #d9d9d9;
	margin:0 auto;
	}
	.box-top {
		width:900px;
		height:27px;
		background:url(${prc}/function/img/box-bg.jpg) repeat-x;
	}
	.box-top li {
		float:left;
		line-height:27px;
		padding:0 33px 0 33px;
	    font-size:14px;
	}
	.box-top li a {
		color:#fff; float:left;
	}
	</style>
<script type="text/javascript">
$(function(){
    $(".nav-list-inner a").click(function(){
		$(".current").removeClass("current");
		$(this).addClass("current");
	});
	$(".li-blue").on("click",".cols",function(e){
		$(".nav-list-inner").hide();
		$(".li-blue .cols-open").attr("class","cols");
		$(this).parent().next().show();		
		$(this).attr("class","cols-open");
		e.stopPropagation();
	});
	$(".li-blue").on("click",".cols-open",function(e){		
		$(this).parent().next().hide();
		$(this).attr("class","cols");
		e.stopPropagation();
	});
	$(".li-blue").on("click",function(){
		$(this).find("span").trigger("click");
	});
	$(".box-top li").click(function(){
		$(".conshen").hide().eq($(this).index()).show();						
	});
	$(".addtabs").click(function(){
		$("#add-tab").show();
	});
	$("#btn-addfujian").click(function(){
		$("#add-fujian").show();
	});
	$("#aaa").click(function(){
		$("#bbb").css({"top":$(this).offset().top+50,"left":$(this).offset().left+50}).show();
	});
	$(".resources li").click(function () {
	    $(".resources .cur").removeClass("cur");
	    var index = $(this).index();
	    if (index == 0) {
	        $(".resources").attr("class", "resources doc");
	    } else if (index == 1) {
	        $(".resources").attr("class", "resources pic");
	    } else if (index == 2) {
	        $(".resources").attr("class", "resources video");
	    }
	    $(".resource-lists").hide().eq(index).show();
	    $(this).addClass("cur");
	});
	$(".ico2").click(function () {
        $("body").css("overflow","hidden")
	    $(".bg").show();
	    $(".add").show();
	});
	$(".shou").click(function () {
	    $("body").css("overflow", "hidden")
	    $(".bg").show();
	    $(".add1").show();
	});
	$(".ico1").click(function () {
	    $("body").css("overflow", "hidden")
	    $(".bg").show();
	    $(".add2").show();
	});
	$(".icos4").click(function () {
	    $(".bg").show();
	    $(".add3").show();
	});
	$(".icos5").click(function () {
	    $(".bg").show();
	    $(".add4").show();
	});
	$(".close").click(function () {
	    $("body").css("overflow", "auto")
	    $(".bg").hide();
	    $(".add").hide();
	    $(".add1").hide();
	    $(".add2").hide();
	    $(".add3").hide();
	    $(".add4").hide();
	});
});
</script>
</head>
<body>
	<div class="right">
		<h1 class="titH1">
			当前位置：首页 - <span style="color: #dd0000">${subject.title }</span>
		</h1>
		<div class="content">
			<h2>
				${subject.title }
				<img id="aaa" src="${prc}/function/images/tb1.png" width="80"
					height="80" /> 
					<img src="${prc}/function/images/qi2.png" class="qi" />
			</h2>
			<h3>
				发布日期：<input value="<fmt:formatDate value="${subject.lastTime }" pattern="yyyy年MM月dd日" />"> 
				关联重心工作:<input value="${subjectExtand.associateName}"> 
				执行者： <span>${subjectExtand.executeUsers }</span>
			</h3>
			<div class="articles">
				<p>${subject.content }</p>
			</div>
			<div class="tabs-wp">
				<ul class="tabs">
					<li class="cur">组织筹备</li>
					<li>执行阶段</li>
				</ul>
			</div>
			<div class="mids">
				<a class="h4"><span>·六一儿童节</span> <img
					src="${prc}/function/images/up.png" width="13" height="13"
					class="mids-img" />
					<img src="${prc}/function/images/ico3.jpg" class="icos3" />
					<img src="${prc}/function/images/ico4.jpg" class="icos4" />
					<img src="${prc}/function/images/ico5.jpg" class="icos5" />
				</a>
				<div class="conls">
					<a>
						<img src="${prc}/function/images/shou.png" width="13" height="13"
						class="shou" />(20)</a><span> | </span><a>
						<img src="${prc}/function/images/ico1.png" width="13" height="13" class="ico1" />（${subReadCount}）
					</a>
					<span> | </span>
					<a>
					<img src="${prc}/function/images/ico2.png" width="13" height="13"
						class="ico2" />（5620）</a>
				</div>
			</div>
			<div class="article" style="height: 287px;">
				<p class="p">2011年6月1日上午，在“六一”国际儿童节这天，东城区人民政府区长牛青山、东城区人民政府办公室主任袁秀江、东城区教委主任冯洪荣等领导来到府学胡同小学，亲切看望慰问学校师生，向同学们致以节日的美好祝福。
					来宾们在府学小导游的带领下参观了府学石学府、奥运博物馆、府学展室、府学园林等，聆听了同学们精彩细致的讲解。同学们表演了快板等节目，表达了对党和祖国的感恩和祝福。牛区长与孩子们亲切交谈，饶有兴趣地与孩子们一起感受府学600余年的历史文化，把节日的礼物送给孩子们，勉励孩子们快乐学习，健康成长，长大报效祖国。</p>
				<dl class="new1">
					<dt>
						<img src="${prc}/function/img/tu.jpg" />
					</dt>
					<dd>
						<p>
							<span class="blue">金校长：</span>好好学习天天向上<br />
						</p>
						<span class="gray">(2013-4-20 10:00)</span>
					</dd>
				</dl>
				<dl class="new1">
					<dt>
						<img src="${prc}/function/img/tu.jpg" />
					</dt>
					<dd>
						<p>
							<span class="blue">金校长：</span>好好学习天天向上<br />
						</p>
						<span class="gray">(2013-4-20 10:00)</span>
					</dd>
				</dl>
				<dl class="new1">
					<dt>
						<img src="${prc}/function/img/tu.jpg" />
					</dt>
					<dd>
						<p>
							<span class="blue">金校长：</span>好好学习天天向上<br />
						</p>
						<span class="gray">(2013-4-20 10:00)</span>
					</dd>
				</dl>
				<p class="pack1">查看所有批示</p>
			</div>
			<div class="box">
				<div class="box-top">
					<ul>
						<li class="two">
						<a href="javascript:;">评论</a>
						<img src="${prc}/function/images/up1.jpg"
							style="margin-top: 10px; display: block; float: left;" />
						</li>
					</ul>
				</div>
				<div class="conshen box-down">
					<dl class="new">
						<dt>
							<img src="${prc}/function/img/tu.jpg" />
						</dt>
						<dd>
							<p>
								<span class="blue">金校长：</span>好好学习天天向上<br />
							</p>
							<span class="gray">(32分钟前)</span>
						</dd>
					</dl>
					<dl class="new">
						<dt>
							<img src="${prc}/function/img/tu.jpg" />
						</dt>
						<dd>
							<p>
								<span class="blue">金校长：</span>好好学习天天向上<br />
							</p>
							<span class="gray">(32分钟前)</span>
						</dd>
					</dl>
					<dl class="new">
						<dt>
							<img src="${prc}/function/img/tu.jpg" />
						</dt>
						<dd>
							<p>
								<span class="blue">金校长：</span>好好学习天天向上<br />
							</p>
							<span class="gray">(32分钟前)</span>
						</dd>
					</dl>
					<dl class="new">
						<dt>
							<img src="${prc}/function/img/tu.jpg" />
						</dt>
						<dd>
							<p>
								<span class="blue">金校长：</span>好好学习天天向上<br />
							</p>
							<span class="gray">(32分钟前)</span>
						</dd>
					</dl>
					<dl class="new none">
						<dt>
							<img src="${prc}/function/img/tu.jpg" />
						</dt>
						<dd>
							<p>
								<span class="blue">金校长：</span>好好学习天天向上<br />
							</p>
							<span class="gray">(32分钟前)</span>
						</dd>
					</dl>
					<p class="pack">查看所有评论</p>
				</div>
			</div>
		</div>
	</div>
	<!--弹出层1-->
	<div class="bg"></div>
	<div class="add1">
		<div class="add-top1">
			<p>赞</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-down1">
			<dl class="comments">
				<dt>
					<img src="${prc}/function/img/tu.jpg" />
				</dt>
				<dd>
					<p>
						<span class="blue">沫儿</span><img src="${prc}/function/img/tu3.jpg" /><br />
					</p>
					<span class="gray">(3天前)</span>
				</dd>
			</dl>
			<!--<a href="#" class="return" style="margin-left:100px;">提交</a> <a href="#" class="return">返回</a> </div>-->
		</div>
	</div>
	<!--弹出层2-->
	<div class="bg"></div>
	<div class="add2">
		<div class="add-top2">
			<p>阅读</p>
			<img src="img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-down2">
			<dl class="comments">
				<dt>
					<img src="${prc}/function/images/tu.jpg" />
				</dt>
				<dd>
					<span class="grays">金强</span> <span class="grays">2013-09-10</span>
					<span class="grays">2013-09-13</span>
				</dd>
			</dl>
		</div>
	</div>
	<!--弹出层3-->
	<div class="bg"></div>
	<div class="add3">
		<div class="add-top3">
			<p>批示</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-down3">
			<textarea>请写批示</textarea>
			<a href="#">发表 </a>
		</div>
	</div>
	<!--弹出层4-->
	<div class="bg"></div>
	<div class="add4">
		<div class="add-top4">
			<p>转发</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-down4">
			<div class="down-left">
				<p class="check">全选 | 取消全选</p>
				<p class="teacher">
					<input type="checkbox" /><span>史家小学教师</span>
				</p>
				<ul>
					<li>
						<p class="big">
							<input type="checkbox" /><span>办公室</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>金强</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>汪忱</span>
						</p></li>
					<li>
						<p class="big">
							<input type="checkbox" /><span>教务处</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>李丽霞</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>李雪</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>张光北</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>王东</span>
						</p></li>
					<li>
						<p class="big">
							<input type="checkbox" /><span>教务处</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>陈凤伟</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>陈凤伟</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>陈凤伟</span>
						</p>
						<p class="small">
							<input type="checkbox" /><span>陈凤伟</span>
						</p></li>
				</ul>
				<div style="clear: both"></div>
			</div>
			<div class="down-cen">
				<img src="${prc}/function/images/cen.jpg" />
			</div>
			<div class="down-right">
				<div class="right-p">
					<p>
						<input type="checkbox" checked="checked" /><span>李丽霞</span>
					</p>
					<p>
						<input type="checkbox" checked="checked" /><span>李丽霞</span>
					</p>
					<p>
						<input type="checkbox" checked="checked" /><span>李丽霞</span>
					</p>
					<p>
						<input type="checkbox" checked="checked" /><span>李丽霞</span>
					</p>
					<div style="clear: both"></div>
				</div>
				<div class="right-up">
					<p>填写转发内容</p>
					<textarea></textarea>
					<a href="#">发送</a>
				</div>
			</div>
			<div style="clear: both"></div>
		</div>
		<div style="clear: both"></div>
	</div>
	
</body>
</html>
