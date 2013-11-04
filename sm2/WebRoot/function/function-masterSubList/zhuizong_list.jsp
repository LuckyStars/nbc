<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/function-masterSubList/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" href="${prc}/function/function-masterSubList/css/gzt.css" />
    <style>
        table th {
	background:url(${prc}/function/function-masterSubList/img/table-bg.jpg) repeat-x;
    height:38px;
	line-height:38px;
}
        table td {
	height:34px;
	line-height:34px;
	text-align:center;
}
    </style>
    <script type="text/javascript">
        $(function () {
            $("table tr:odd").css("border", "1px solid #EDEFFF")
            $("table tr:even").css("border", "1px solid #EDEFFF")
        })
</script>
<script type="text/javascript">
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
</head>
<body>
<div class="header">
  <h1 class="logo">
  <img src="${prc}/function/function-masterSubList/images/logo.png" width="314" height="51" alt="校长工作台" /></h1>
</div>
<div class="main">
  <div class="left">
    <h1 class="nav-title">应用菜单导航</h1>
    <ul class="nav-list">
      <li><a href="#" class="li-blue" id="nav1">
      <img src="${prc}/function/function-masterSubList/images/nav_01.png" width="16" height="16" />个人办公<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav2">
      <img src="${prc}/function/function-masterSubList/images/nav_02.png" width="16" height="16" />公文处理<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue li-red" id="nav3">
      <img src="${prc}/function/function-masterSubList/images/nav_03.png" width="16" height="16" />紧急重要事件处理<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav4">
      <img src="${prc}/function/function-masterSubList/images/nav_04.png" width="16" height="16" />学校临时事项处理<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav5">
      <img src="${prc}/function/function-masterSubList/images/nav_05.png" width="16" height="16" />工作计划跟踪<span class="cols-open"></span></a>
        <ul class="nav-list-inner" style="display:block;">
          <li><a class="current" href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav6"><img src="images/nav_06.png" width="16" height="16" />请示报批性工作汇总<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav7">
      <img src="${prc}/function/function-masterSubList/images/nav_07.png" width="16" height="16" />总结·汇报性工作汇总<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav8">
      <img src="${prc}/function/function-masterSubList/images/nav_08.png" width="16" height="16" />统计分析<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
      <li><a href="#" class="li-blue" id="nav9">
      <img src="${prc}/function/function-masterSubList/images/nav_09.png" width="16" height="16" />个人信息设置<span class="cols"></span></a>
        <ul class="nav-list-inner">
          <li><a href="#">·本学期工作动态</a></li>
          <li><a href="#">·教育教研工作</a></li>
          <li><a href="#">·德育工作</a></li>
          <li><a href="#">·后勤·卫生工作</a></li>
          <li><a href="#">·信息化工作</a></li>
          <li><a href="#">·财务工作</a></li>
          <li><a href="#">·党团工作</a></li>
        </ul>
      </li>
    </ul>
  </div>
  <div class="right">
    <h1>当前位置：首页 - <span style="color:#002F7F">工作计划跟踪 - 学期工作计划动态</span></h1>
    <div class="right-input">
        <p><label>部门:</label><select></select></p>
        <p><label>发布者:</label><select></select></p>
        <p><label>发布时间:</label><select></select></p>
        <a href="#">查询</a>
    </div>
       <table width="100%" border="0">
      <tr>
        <th width="20%" scope="col">名称</th>
        <th width="10%" scope="col">部门</th>
        <th width="10%" scope="col">发布者</th>
        <th width="10%" scope="col">发布时间</th>
          <th width="50%" scope="col">进度</th>
      </tr>
         <tr>
             <td class="other">蓝天工程工作计划</td>
             <td>财务部</td>
             <td>刘莉莉</td>
             <td>今天</td>
             <td>
                <div class="pross">
                    <img src="${prc}/function/function-masterSubList/img/pro-bg1.jpg" class="pross-img" />
                </div> 
                 <p class="pross-p">90%</p>
                 <img src="${prc}/function/function-masterSubList/img/qi.png"  class="qi"/>
             </td>
         </tr>
            <tr>
             <td class="other">阳光公益社为晨光脑瘫中心举办画展</td>
             <td>后勤部</td>
             <td>王菲</td>
             <td>昨天</td>
             <td>
                <div class="pross">
                    <img src="${prc}/function/function-masterSubList/img/pro-bg2.png" class="pross-img1" />
                </div> 
                 <p class="pross-p1">60%</p>
                 <img src="${prc}/function/function-masterSubList/img/qi.png"  class="qi"/>
             </td>
         </tr>
           <tr>
             <td class="other">眼科医院建设动态</td>
             <td>总经办</td>
             <td>刘莉莉</td>
             <td>三天前</td>
             <td>
                <div class="pross">
                    <img src="${prc}/function/function-masterSubList/img/pro-bg3.png" class="pross-img2" />
                </div> 
                 <p class="pross-p2">30%</p>
                 <img src="${prc}/function/function-masterSubList/img/qi1.png"  class="qi"/>
             </td>
         </tr>
    </table>
  </div>
</div>

