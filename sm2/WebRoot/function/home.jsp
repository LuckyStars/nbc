<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校长工作台</title>
<script type="text/javascript">var ctxPath = '${prc}';</script>
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/home.js"></script>
<link href="${prc}/function/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${prc}/function/css/gzt.css" />
    <style>
.linshi	{background: url("${prc}/function/images/li_blue.png") repeat scroll 0 0 rgba(0, 0, 0, 0);
    color: #0273C2;
    display: inline-block;
    font-size: 12px;
    font-weight: bold;
    height: 28px;
    line-height: 28px;
    width: 190px;
    }
    </style>
	
    <script type="text/javascript">
    function open1(t){
    	 $(".nav-list-inner").hide();
         $(".li-blue .cols-open").attr("class", "cols");
         $(t).parent().next().show();
         $(t).attr("class", "cols-open");
    }
    $(function () {
        $(".nav-list-inner a").click(function () {
            $(".current").removeClass("current");
            $(this).addClass("current");
        });
        $(".li-blue").on("click", ".cols", function (e) {
            $(".nav-list-inner").hide();
            $(".li-blue .cols-open").attr("class", "cols");
            $(this).parent().next().show();
            $(this).attr("class", "cols-open");
            e.stopPropagation();
        });
        $("#cols").on("click",".cols", function (e) {
            $(".nav-list-inner").hide();
            $(".li-blue .cols-open").attr("class", "cols");
            $(this).parent().next().show();
            $(this).attr("class", "cols-open");
            e.stopPropagation();
        });
        $("#cols").on("click", ".cols-open", function (e) {
            $(this).parent().next().hide();
            $(this).attr("class", "cols");
            e.stopPropagation();
        });
        $(".li-blue").on("click", ".cols-open", function (e) {
            $(this).parent().next().hide();
            $(this).attr("class", "cols");
            e.stopPropagation();
        });
        $(".li-blue").on("click", function () {
            $(this).find("span").trigger("click");
        });
        $(".box-top li").click(function () {
            $(".conshen").hide().eq($(this).index()).show();
        });
        $(".addtabs").click(function () {
            $("#add-tab").show();
        });
        $("#btn-addfujian").click(function () {
            $("#add-fujian").show();
        });
        $("#aaa").click(function () {
            $("#bbb").css({ "top": $(this).offset().top + 50, "left": $(this).offset().left + 50 }).show();
        });
        $(".button").click(function () {
            $(".bg").show();
            $(".add2").show();
        });
        $(".close").click(function () {
            $(".bg").hide();
            $(".add2").hide();
        });
        
    });
    
	</script>
<script>

		function selectTab(id){
			var a = $("#"+id);
			a.addClass('current');
			a.parent().parent().parent().children('a').children('span').click();
		}
		
		function resizeFrame(height){
			$("#content_frame").css("height",height);
		}
</script>
<style type="text/css">
	.box_logo_right{
	height: 20px;
	width: auto;
	color: #fff;
	margin: 43px 10px 0 0;
	line-height: 20px;
	font-size: 12px;
	float: right;
	}
	.box_logo_right a {
		color:#FFFFFF;
	}
	.la{
	}
	.tc{
	}
</style>
</head>
<body>
<div class="header">

	<h1 class="logo" style="float:left;">
  		<img src="${prc}/function/images/logo.png" width="314" height="51" alt="校长工作台" />
	</h1>
	<div class="box_logo_right" style="float:right;">
		<a class="la" style="float:left; padding-left: 20px;" 
			href="#">
			<span style="
			background:url(${prc}/function/css/icons_white.png) no-repeat;
			width:14px;height:14px;
			background-position:-170px 0px;"
			>&nbsp;&nbsp;&nbsp;&nbsp;</span>教师门户</a>
		&nbsp;&nbsp;
		<a class="tc" href="${logoutService}">
		<span style="
				background:url(${prc}/function/css/icons_white.png) no-repeat;
				width:14px;height:14px;
				background-position:-122px -73px;"
				
				>&nbsp;&nbsp;&nbsp;&nbsp;</span>退出登录</a>
	</div>			
</div>
<div class="main">
  	<div class="left">
    <h1 class="nav-title">
    	应用菜单导航&nbsp;
    	<a href="${prc}/scMaster2/index_index.action"
    	title="回首页"
    	style="background-image: url('${prc}/function/css/icons_white.png');
    	background-repeat: no-repeat;background-position: -0px -22px;
    	width:14px;height:14px;">&nbsp;&nbsp;&nbsp;</a>
    </h1>
    <ul class="nav-list">
    <%--个人办公 --%>
      	<li>
	      	<a href="#" class="li-blue" id="nav1">
		      	<img src="${prc}/function/images/nav_01.png" width="16" height="16" />
		      	个人办公<span class="cols"></span>
	      	</a>
        	<ul class="nav-list-inner">
          		<li>
          			<a href="${prc}/masterDocumentFlow/listUnhandledDocumentTask.action" 
          			target="fm_right">我的待处理公文</a>
          		</li>
			      <%--<li><a href="#">·我的日程安排</a></li>
			          <li><a href="#">·我的通讯录</a></li>
			          <li><a href="#">·我要看的学校动态</a></li>--%>
          		<li>
          			<a href="${prc}/scMaster2/daiban_master.action"
          			target="fm_right" >待办事宜</a>
       			</li>
				<li>
	          		<a href="${prc}/scMaster2/masterList_invatition.action" 
	          		target="fm_right">我收到的邀请</a>
	          	</li>
	          
	          	<li>
		          	<a href="${prc}/scMaster2/search_week.action"
	          		target="fm_right">本周工作汇总</a>
	          	</li>
<%--          <li><a href="#">·我的邮件</a></li>--%>
        	</ul>
		</li>
      	<%--个人办公 END --%>
      
      	<%--工作计划追踪 --%>
		<li>
			<a href="#" class="li-blue" id="nav5">
	      		<img src="${prc}/function/images/nav_05.png" width="16" height="16" />
	      		工作计划跟踪<span class="cols" ></span>
      		</a>
        	<ul class="nav-list-inner" style="display: none;">
          		<li>
		          	<a style="font-weight: bold;"
		           		href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.flag=1" 
		           		target="fm_right">我关注的核心工作
		           		<img style="height: 11px;width:11px;display: inline;padding: 0px;margin: 0px;float: none;"
		           		src="${prc}/function/img/zhongxin_flag.png"/>
	           		</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=jiaoyujiaoyan-ndzx" 
					target="fm_right">教育教研工作</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=deyu" 
					target="fm_right">德育工作</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=houqin" 
					target="fm_right">后勤·卫生工作</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=xinxi" 
					target="fm_right">信息化工作</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=caiwu"
					 target="fm_right">财务工作</a>
				 </li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=dangtuan" 
					target="fm_right">党团工作</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/list_master.action?moduleId=nianduzhongxin&search.typeId=bangonshi" 
					target="fm_right">办公室工作</a>
				</li>
        	</ul>
      	</li>
      	<%--工作计划追踪 END--%>
      
      	<%--学校动态 --%>
		<li>
			<a href="#" class="li-blue" id="nav2">
				<img src="${prc}/function/images/nav_02.png" width="16" height="16" />
				学校动态<span class="cols"></span>
			</a>
		 	<ul class="nav-list-inner">
		   		<li>
		   			<a href="${prc}/masterDocumentFlow/listAllDocumentTask.action" 
		   			target="fm_right" >公文处理</a>
		   		</li>
		    	<li>
		    		<a href="${prc}/scMaster2/list_master.action?moduleId=zongjiehuibao"
		    		target="fm_right" >总结·汇报·关注性工作</a>
		    	</li>
		    	<li>
		    		<a href="${prc}/scMaster2/list_master.action?moduleId=qingshibaopi"
		    		target="fm_right" >请示·报批性工作</a>
		    	</li>
		    	<li>
		    		<a href="${prc}/scMaster2/list_master.action?moduleId=jinjizhongyao"
		    		target="fm_right">紧急重要事件处理</a>
		    	</li>
		  	</ul>
		</li>
        <%--学校动态 END--%>
      
		<%-- 临时事项  --%>
		<li style="background: url(../function/images/li_blue.png) repeat-x;background-size:100% 28px;">
			<a href="${prc}/scMaster2/list_master.action?moduleId=linshishixiang"  class="linshi" id="nav4" target="fm_right" style="display:inline;background:aliceblue;" >
				<img src="${prc}/function/images/nav_04.png" width="16" height="16" />
				学校临时事项处理
			</a>
			<span id="cols"><span class="cols" style="position:absolute;left:164px" ></span></span>
		  	<ul class="nav-list-inner" id="linshi_list">
		  	
		  	</ul>
		</li>
		<%-- 临时事项  END--%>
		
		<%--统计分析 --%>
		<li>
			<a href="#" class="li-blue" id="nav8">
			<img src="${prc}/function/images/nav_08.png" width="16" height="16" />统计分析<span class="cols"></span></a>
			<ul class="nav-list-inner">
		  		<li>
		  			<a href="${prc}/scMaster2/listMasterStatistics_data.action?matcher=bookSite" 
		  			target="fm_right" >场馆预定统计</a>
		  		</li>
				<li>
					<a href="${prc}/scMaster2/listMasterStatistics_data.action?matcher=substitute" 
					target="fm_right">请假代课统计</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/listMasterStatistics_data.action?matcher=repair" 
					target="fm_right">在线保修统计</a>
				</li>
				<li>
					<a href="${prc}/scMaster2/listMasterStatistics_data.action?matcher=logistics" 
					target="fm_right">电教服务统计</a>
				</li>
				<span id="tongji_list" >
				
				</span>
	 		</ul>
		</li>
		<li>
			<a href="#" class="li-blue" id="nav9">
      		<img src="${prc}/function/images/nav_09.png" width="16" height="16" />个人信息设置<span class="cols"></span></a>
      		<ul class="nav-list-inner">
          		<li><a target="fm_right" href="${prc}/function/building.html">修改密码及头像</a></li>
        	</ul>
		</li>
	</ul>
		<%--统计分析 --%>
		
		<%--
		<li><a href="#" class="li-blue" id="nav3">
		<img src="${prc}/function/images/nav_03.png" width="16" height="16" />紧急重要事件处理<span class="cols"></span></a>
		  <ul class="nav-list-inner">
		    <li><a href="#">·公众方面</a></li>
		    <li><a href="#">·家长方面</a></li>
		    <li><a href="#">·教学方面</a></li>
		    <li><a href="#">·后勤方面</a></li>
		    <li><a href="#">·卫生工作</a></li>
		  </ul>
		</li> 
      
      <li><a href="#" class="li-blue" id="nav6">
      <img src="${prc}/function/images/nav_06.png" width="16" height="16" />请示报批性工作汇总<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="${prc}/scMaster2/list_master.action?moduleId=qingshibaopi" target="fm_right">·财务方面请示</a></li>
          <li><a href="${prc}/scMaster2/list_master.action?moduleId=qingshibaopi" target="fm_right">·人事方面请示</a></li>
          <li><a href="${prc}/scMaster2/list_master.action?moduleId=qingshibaopi" target="fm_right">·事务方面请示</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav7">
      <img src="${prc}/function/images/nav_07.png" width="16" height="16" />总结·汇报性工作汇总<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="${prc}/scMaster2/list_master.action?moduleId=zongjiehuibao" target="fm_right">·年度工作总结</a></li>
          <li><a href="${prc}/scMaster2/list_master.action?moduleId=zongjiehuibao" target="fm_right">·教学质量总结</a></li>
          <li><a href="${prc}/scMaster2/list_master.action?moduleId=zongjiehuibao" target="fm_right">·学校宣传总结</a></li>
        </ul>
      </li>--%>
      
	</div>
	<iframe id="content_frame" class="right" id="fm_right" name="fm_right" scrolling="yes" height="100%" width="100%" frameborder="0" src="${prc}/${rightURL}"></iframe>
</div>
</body>
</html>
