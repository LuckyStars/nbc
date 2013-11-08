<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>校长工作台</title>
	<link rel="stylesheet" href="${prc}/function/css/masterindex.css" />
	<script type="text/javascript"
	src="${prc}/function/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript"
	src="${prc}/function/js/masterIndex.js"></script>
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
	height:30px;
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
		<div class="box_logo_right" style="float:right;"><span style="float:left;">欢迎您，${userName}！</span>
					
			<a class="la"  style="float:left; background:url(${prc}/function/images/ico__1.png) no-repeat;padding-left:20px;" href="#">教师门户</a>
		&nbsp;&nbsp;
		<a class="tc" href="${logoutService}"><img class="png" src="${prc}/function/images/bg__1.png"
			title="退出"  style="float:left; margin-top: 3px;" /> 退出登录</a></div>
	</div>
	<div class="main">
		<div class="module module1">
			<h1>个人信息</h1>
			<div class="content userinfo">
				<img class="photo" src="${photoPath}" />
				<p class="name">
					${userName}&nbsp;<img src="${prc}/function/images/name-bg.png" />
				</p>
				<p class="">身 份：校长</p>
				<p class="">所在学校：史家小学</p>
				<p class="state">
					<img src="${prc}/function/images/xin.png" />&nbsp;${userPhrase }
				</p>
				<p class="write">
					<textarea></textarea>
				</p>
			</div>
		</div>
		
		<div class="module module1" style="margin-left: 24px;">
			<h1>个人办公</h1>
			<div class="content" style="padding: 0 1px;">
				<div class="office">
					<a class="pointer" href="#"> 
						<img src="${prc}/function/images/richeng.jpg" /> 
						<span>我的日程安排</span>
					</a>
				</div>
				<div class="office" style="width: 126px; height: 150px;">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=${prc}/masterDocumentFlow/listUnhandledDocumentTask.action">
						<img src="${prc}/function/images/daichuli.jpg"/>
						<span>待处理文件</span>
						<i id="documentNum" style="display:none;"></i>
					</a>
				</div>

				<div class="office" style="width: 125px; height: 150px;">
					<a class="pointer" href="${prc}/scMaster2/daiban_master.action">
						<img src="${prc}/function/images/daiban.jpg"/>
						<span>待办事宜</span>
						<i id="daibanshiyi" >6</i>
					</a>
				</div>

				<div class="office">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/tongxun.jpg" />
						<span>我的通讯录</span>
					</a>
				</div>

				<div class="office mail" style="width: 126px; height: 148px;">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/mail.jpg" />
						<span>电子邮件</span>
						<i>6</i>
					</a>
				</div>

				<div class="office" style="width: 125px; height: 148px;">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=/scMaster2/masterList_invatition.action">
						<img src="${prc}/function/images/yaoqing.jpg" />
						<span>邀请查看及消息</span>
					</a>
				</div>
			</div>
		</div>

		<div class="module module1" style="margin-left: 24px;">
			<h1>年度重心工作跟踪</h1>
			<div class="content">
				<table width="354" height="272" border="0" cellpadding="1"
					cellspacing="1" class="genzhong" style="margin: 12px auto;">
					<tr>
						<td colspan="2">
							<div style="width: 200px; margin: 0 auto;">
							<a class="pointer ts" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=nianduzhongxin"> 
								<img src="${prc}/function/images/icon1.png" />
								<span>我关注的核心工作</span>
								<img src="${prc}/function/images/new.png" id="hexin" />
							</a>
							</div>
						</td>
					</tr>
					<tr>
						<td width="177">
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=deyu-ndzx">
								<img src="${prc}/function/images/icon2.png" />
								<span>德育工作</span>
								<img src="${prc}/function/images/new.png" id="deyugongzuo" />
							</a>
						</td>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=jiaoyujiaoyan-ndzx">
							<img src="${prc}/function/images/icon3.png" />
							<span>教育教研工作</span>
							<img src="${prc}/function/images/new.png" id="deyujiaoyan" />
						</a>
						</td>
					</tr>
					<tr>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=xinxihua-ndzx">
								<img src="${prc}/function/images/icon4.png" />
								<span>信息化工作</span>
								<img src="${prc}/function/images/new.png" id="xinxihua" />
							</a>
						</td>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=houqin-ndzx">
								<img src="${prc}/function/images/icon5.png" />
								<span>后勤·卫生工作</span>
								<img src="${prc}/function/images/new.png" id="houqinweisheng" />
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=dangtuan-ndzx">
								<img src="${prc}/function/images/icon6.png" />
								<span>党团工作</span>
								<img src="${prc}/function/images/new.png" id="dangtuan" />
							</a>
						</td>
						<td>
							<a class="pointer" href="${prc}/scMaster2/login_index.action?moduleId=nianduzhongxin&search.typeId=caiwu">
								<img src="${prc}/function/images/icon7.png" />
								<span>财务工作</span>
								<img src="${prc}/function/images/new.png" id="caiwu" />
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="module module2">
			<h1>学校动态</h1>
			<div class="content">
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=zongjiehuibao">
						<img src="${prc}/function/images/pic1.png" width="179" height="140" />
						<span style="color: #0273c2;">总结·汇报·关注性工作</span>
					</a>
					<i id="zongjie">6</i>
				</div>
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=${prc}/masterDocumentFlow/listAllDocumentTask.action">
						<img src="${prc}/function/images/pic2.png" width="179" height="140" />
						<span style="color: #447c0e;">公文处理</span>
					</a>
				</div>
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=qingshibaopi">
						<img src="${prc}/function/images/pic3.png" width="179" height="140" />
						<span style="color: #ff7200;">请示·报批性工作</span>
					</a>
					<i id="qingshi">6</i>
				</div>
				<div class="tongtai">
					<a class="pointer" href="${prc}/scMaster2/home_index.action?rightURL=scMaster2/list_master.action?moduleId=jinjizhongyao">
						<img src="${prc}/function/images/pic4.png" width="179" height="140" />
						<span style="color: #9910ac;">紧急重要事件处理</span>
					</a>
					<i id="jinji">6</i>
				</div>
			</div>
		</div>
		<div class="module module2" style="margin-left: 24px;">
			<h1>学校临时事项跟踪</h1>
			<div class="content" id="linshiDiv">
				<!--<div class="linshi">
					<a class="pointer" href="#">
					<img src="${prc}/function/images/tb1.png" width="80" height="80" />
					<span style="color: #e1517e;">东兴杯课程比赛</span>
					</a>
					<a class="qi"></a>
				</div>
				<div class="linshi">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/tb2.png" width="80" height="80" />
						<span style="color: #359127;">迎接香港同盟校</span>
					</a>
					<a class="new"></a>
				</div>
				<div class="linshi">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/tb3.png" width="80" height="80" />
						<span style="color: #f36d2f;">优秀校长论坛</span>
					</a>
					<a class="text"></a>
				</div>
				<div class="linshi">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/tb4.png" width="80" height="80" />
						<span style="color: #447c0e;">东兴杯课程比赛</span>
					</a>
				</div>
				<div class="linshi">
					<a class="pointer" href="#">
						<img src="${prc}/function/images/tb5.png" width="80" height="80" />
						<span style="color: #b25ebd;">迎接香港同盟</span>
					</a>
				</div>-->
			</div>
		</div>
		<div class="module module2" style="margin-left: 24px;">
			<h1>统计分析</h1>
			<div class="content">
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
								<img src="${prc}/function/images/png5.png" />
								<span>学生参赛情况</span>
							</a>
						</td>
						<td bgcolor="#f79263">
							<a class="pointer" href="#">
							<img src="${prc}/function/images/png6.png" />
							<span>财务收支状况</span>
						</a>
						</td>
					</tr>
					<tr>
						<td bgcolor="#846fe0">
							<a class="pointer" href="#">
							<img src="${prc}/function/images/png7.png" />
							<span>教师报销统计</span>
						</a>
						</td>
						<td bgcolor="#f76382">
							<a class="pointer" href="#">
								<img src="${prc}/function/images/png8.png" />
								<span>经费执行情况</span>
							</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
