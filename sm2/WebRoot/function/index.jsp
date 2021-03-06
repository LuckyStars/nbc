<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校长工作台</title>
	<link rel="stylesheet" href="${prc}/function/css/masterindex.css" />
	<link rel="stylesheet" href="${prc}/function/js/weather/JQweather.css" />
	<script type="text/javascript"
	src="${prc}/function/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
	src="${prc}/function/js/masterIndex.js"></script>
	<script type="text/javascript"
	src="${prc}/function/js/weather/JQweatherplugin.js"></script>
	<script type="text/javascript">var ctxPath = '${prc}';</script>
	<style type="text/css">
	.flagupdated{
		position: absolute;
		display: inline-block;
		background: url(${prc}/function/images/text.png);
		width: 14px;
		height: 14px;
		top: 10px;
		right: 10px;
	}
	.flagnew{
		position: absolute;
		display: inline-block;
		background: url(${prc}/function/images/new.png);
		width: 16px;
		height: 16px;
		top: 10px;
		right: 10px;
	}
	.flagattention{
		position: absolute;
		display: inline-block;
		background: url(${prc}/function/img/lvqi.png);
		width: 30px;
		height: 11px;
		top: 10px;
		right: 10px;
	}
	.box_logo_right{
	height:20px;
	width:auto;
	color:#fff;
	margin:43px 10px 0 0 ;
	line-height:20px;
	font-size:12px;
	}
	.box_logo_right a{
	color:#fff;
	text-decoration:none;
	}
	
	</style>
</head>
<body>
		<div class="header">
		<h1 class="logo" style="float:left;">
			<img src="${prc}/function/images/logo.png" width="314" height="51"
				alt="校长工作台" />
		</h1>
		<div class="box_logo_right" style="float:right;">
			<a class="la" style="float:left;" 
				href="#">
				<span style="
				background:url(${prc}/function/css/icons_white.png) no-repeat;
				width:14px;height:14px;
				background-position:-170px 0px;
				padding-right:8px;
				"
				>&nbsp;</span>教师门户</a>
		&nbsp;&nbsp;
		<a class="tc" href="${logoutService}">
		<span style="
				background:url(${prc}/function/css/icons_white.png) no-repeat;
				width:14px;height:14px;
				background-position:-122px -73px;
				padding-right:8px;
				"
				>&nbsp;</span>退出登录</a></div>
	</div>
	<div class="main">
		<div class="module module1" style="background-image: url('${prc}/function/img/person_bg.png');">
			<h1>个人信息</h1>
			<div class="content userinfo">
				<img class="photo"
				style="width:120px;height:120px;"
				 src="${photoPath}" onerror="$(this).attr('src','${prc}/function/img/zanwu.jpg')" /> <%--一期 --%>
				<%--<img class="photo" src="${prc}/scMaster2/headPhoto_index.action" />二期 --%>
				<p class="name">
					欢迎您! ${userName}校长
				</p>
				<p class="name" >所在学校：史家小学</p>
				<p class="name">&nbsp;</p>
				
				<div style="margin-left:10px;"  class="index_avatar_parent" >
					<a href="#" class="index_avatar_btn">
						<span class="index_avatar_curUser">
							&nbsp;
						</span>
						更换头像
					</a>
				</div>
				<div style="clear: both;height:30px;"></div>
				<div id="index_weather"></div>
			</div>
		</div>
		
		<div class="module module1" style="margin-left: 10px;">
			<h1>个人办公</h1>
			<div class="content" style="padding: 0 1px;">
				<div class="office">
					<a class="pointer" href="#"> 
						<img src="${prc}/function/images/richeng.jpg" /> 
						<span>我的日程安排</span>
					</a>
				</div>
				<div class="office" style="width: 126px; height: 150px;">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=masterDocumentFlow/listUnhandledDocumentTask.action">
						<img src="${prc}/function/images/daichuli.jpg"/>
						<span>待处理文件</span>
						<i id="documentNum" style="display:none;"></i>
					</a>
				</div>

				<div class="office" style="width: 125px; height: 150px;">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/daiban_master.action">
						<img src="${prc}/function/images/daiban.jpg"/>
						<span>待办事宜</span>
						<%-- <i id="daibanshiyi" >6</i>--%>
					</a>
				</div>

				<div class="office">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/tongxun.jpg" />
						<span>我的通讯录</span>
					</a>
				</div>

				<div class="office mail" style="width: 126px; height: 148px;">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/search_week.action">
						<img src="${prc}/function/images/mail.jpg" />
						<span>本周工作汇总</span>
					</a>
				</div>

				<div class="office" style="width: 125px; height: 148px;">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/masterList_invatition.action">
						<img src="${prc}/function/images/yaoqing.jpg" />
						<span>邀请查看及消息</span>
					</a>
				</div>
			</div>
		</div>

		<div class="module module1" style="margin-left: 10px;">
			<h1>年度重心工作跟踪</h1>
			<div class="content">
				<table width="354" height="272" border="0" cellpadding="1"
					cellspacing="1" class="genzhong" style="margin: 12px auto;">
					<tr>
						<td colspan="2">
							<div style="width: 200px; margin: 0 auto;">
							<a class="pointer ts" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.flag=1"> 
								<img src="${prc}/function/images/icon1.png" />
								<span>我关注的核心工作</span>
								<img style="display: none;" src="${prc}/function/images/new.png" id="hexin" />
							</a>
							</div>
						</td>
					</tr>
					<tr>
						<td width="177">
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=deyu">
								<img src="${prc}/function/images/icon2.png" />
								<span>德育工作</span>
								<img src="${prc}/function/images/new.png" id="deyu_flag" />
							</a>
						</td>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=jiaoyujiaoyan">
							<img src="${prc}/function/images/icon3.png" />
							<span>教育教研工作</span>
							<img src="${prc}/function/images/new.png" id="jiaoyujiaoyan_flag" />
						</a>
						</td>
					</tr>
					<tr>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=xinxi">
								<img src="${prc}/function/images/icon4.png" />
								<span>信息化工作</span>
								<img src="${prc}/function/images/new.png" id="xinxihua_flag" />
							</a>
						</td>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=houqin">
								<img src="${prc}/function/images/icon5.png" />
								<span>后勤卫生工作</span>
								<img src="${prc}/function/images/new.png" id="houqinweisheng_flag" />
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=bangongshi">
								<img src="${prc}/function/images/icon6.png" />
								<span>办公室</span>
								<img src="${prc}/function/images/new.png" id="dangtuan_flag" />
							</a>
						</td>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=caiwu">
								<img src="${prc}/function/images/icon7.png" />
								<span>财务工作</span>
								<img src="${prc}/function/images/new.png" id="caiwu_flag" />
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="module module1">
			<h1>学校动态</h1>
			<div class="content">
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=zongjiehuibao">
						<img src="${prc}/function/images/pic1.png" width="179" height="140" />
						<span style="color: #0273c2;">总结·汇报·关注性工作</span>
					</a>
					<i id="zongjie" style="display: none;" >6</i>
				</div>
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=masterDocumentFlow/listAllDocumentTask.action">
						<img src="${prc}/function/images/pic2.png" width="179" height="140" />
						<span style="color: #447c0e;">公文处理</span>
					</a>
				</div>
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=qingshibaopi">
						<img src="${prc}/function/images/pic3.png" width="179" height="140" />
						<span style="color: #ff7200;">请示·报批性工作</span>
					</a>
					<i id="qingshi" style="display: none;" >6</i>
				</div>
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=jinjizhongyao">
						<img src="${prc}/function/images/pic4.png" width="179" height="140" />
						<span style="color: #9910ac;">紧急重要事件处理</span>
					</a>
					<i id="jinji" style="display: none;" >6</i>
				</div>
			</div>
		</div>
		<div class="module module1" id="linshi_parent" style="margin-left: 10px;">
			<h1>学校临时事项跟踪</h1>
			<div class="content" id="linshiDiv">
				<%--
				<div class="linshi">
					<a class="pointer" href="#">
					<img src="${prc}/function/images/tb1.png" width="80" height="80" />
					<span style="color: #e1517e;">东兴杯课程比赛</span>
					</a>
					<a class="qi"></a>
				</div>
				--%>
			</div>
		</div>
		
		<style>
		
			.tongjiPar{
			width: 388px;
			margin: 0px;
			padding: 2px;
			}
			
			
			.tongjidiv{
				width: 188px;
				height: 72px;
				cursor: pointer;
				text-align: center;
				vertical-align: middle;
				position: relative;
				display: inline-block;
				padding: 0px;
				float:left;
				border:1px solid #FFFFFF;
			}
			
			.tongjidivText{
				text-align:left;
				float: right;
				color: #fff;
				font-size: 14px;
				margin: 25px 30px 0px 5px;
				width:85px;
				overflow:hidden;
				text-overflow:ellipsis;
				white-space:nowrap;
			}
			
			.tongjidivImg{
				margin: 15px 0px 20px 15px;
			}
		</style>
		
		<div class="module module1" style="margin-left: 10px;">
			<h1>统计分析</h1>
			<div class="content tongjiPar">
			
					<a href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=bookSite">
					<span class="tongjidiv pointer" style="background-color: #d15b47;">
						<img class="tongjidivImg" src="${prc}/function/images/tongji/changguan.png" />
						<span class="tongjidivText">场馆预订统计</span>
					</span>
					</a>
					
					<a href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=substitute">
					<span class="tongjidiv pointer" style="background-color: #ffb752;">
						<img class="tongjidivImg" src="${prc}/function/images/tongji/qingjia.png" />
						<span class="tongjidivText">请假代课统计</span>
					</span>
					</a>
					
					<a href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=repair">
					<span class="tongjidiv pointer" style="background-color: #87b87f;">
						<img class="tongjidivImg" src="${prc}/function/images/tongji/baoxiu.png" />
						<span class="tongjidivText">在线报修统计</span>
					</span>
					</a>
					
					<a href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=logistics" >
					<span class="tongjidiv pointer" style="background-color: #d48bde;" >
						<img class="tongjidivImg" src="${prc}/function/images/tongji/dianjiao.png" />
						<span class="tongjidivText">电教服务统计</span>
					</span>
					</a>
					
					<span id="tongjib">
					
					</span>
				</div>
				
				
				<%--
				<table width="382" height="301" class="tongji" cellpadding="1"
					cellspacing="1" style="margin: 0 auto;">
					<tr>
						<td width="190" height="72" bgcolor="#d15b47">
							<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=bookSite">
								<img src="${prc}/function/images/png1.png" />
								<span>场馆预订统计</span>
								<d class="new"></d>
							</a>
						</td>
						<td bgcolor="#ffb752">
							<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=substitute">
								<img src="${prc}/function/images/png2.png" />
								<span>请假代课统计</span>
								<d class="text"></d>
							</a>
						</td>
					</tr>
					<tr>
						<td bgcolor="#87b87f">
							<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=repair">
								<img src="${prc}/function/images/png3.png" />
								<span>在线报修统计</span>
							</a>
						</td>
						<td bgcolor="#d48bde">
							<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/listMasterStatistics_data.action?matcher=logistics">
								<img src="${prc}/function/images/png4.png" />
								<span>电教服务统计</span>
							</a>
						</td>
					</tr>
					
					<tr>
						<td bgcolor="#f79263">
							<a class="pointer" href="#">
								<img src="${prc}/function/images/tongji/tj04.png" />
								<span>学生参赛情况</span>
							</a>
						</td>
						<td bgcolor="#f79263">
							<a class="pointer" href="#">
							<img src="${prc}/function/images/tongji/tj03.png" />
							<span>财务收支状况</span>
						</a>
						</td>
					</tr>
					<tr>
						<td bgcolor="#846fe0">
							<a class="pointer" href="#">
							<img src="${prc}/function/images/tongji/tj02.png" />
							<span>教师报销统计</span>
						</a>
						</td>
						<td bgcolor="#f76382">
							<a class="pointer" href="#">
								<img src="${prc}/function/images/tongji/tj01.png" />
								<span>经费执行情况</span>
							</a>
						</td>
					</tr>
				</table> --%>
				
				
		</div>
	</div>
	<script type="text/javascript">
		var id_callback = function(){
			$("#index_weather").simpleWeather({cityId:id,dataUrl:'${prc}/weather.service?jsoncallback=?'});
		};
	</script>
	<script type="text/javascript" src="http://61.4.185.48:81/g/"></script>
	<script type="text/javascript" >
		if(typeof(id)=='undefined'){
			(function(){
				var img = $("<img src='${prc}/function/js/weather/noconnection.png' style='margin-left:20px;'></img>");
				img.appendTo($("#index_weather"));
			})();	
		}
	</script>
</body>
</html>
