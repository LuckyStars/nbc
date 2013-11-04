<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请示·报批性工作</title>
<link rel="stylesheet" href="${prc}/function/css/index.css" type="text/css"></link>
<link rel="stylesheet" href="${prc}/function/css/gzt.css" type="text/css"></link>
<script type="text/javascript" src="${prc}/function/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${prc}/function/function-masterSubList/js/schoolMaster.js"></script>
<style>
        table th {
	background:url(${prc}/function/img/table-bg.jpg) repeat-x;
    height:38px;
	line-height:38px;
}
        table td {
	height:34px;
	line-height:34px;
	text-align:center;
}
        table td img{
            cursor:pointer;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("table tr:odd").css("background", "#fff");
            $("table tr:even").css("background", "#EDEFFE");
        });
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
<div class="main" style="width: 990px;">
  <div class="right1">
    <h1>当前位置：首页 - <span style="color:#0374C2">请示·报批性工作</span></h1>
    <div class="right-input">
        <p><label>部门:</label><select></select></p>
        <p><label>发布者:</label><select></select></p>
        <p><label>发布时间:</label><select></select></p>
        <a href="#">查询</a>
    </div>
       <table width="100%" border="0">
      <tr>
        <th width="5%" scope="col">序号</th>
        <th width="30%" scope="col">名称</th>
        <th width="10%" scope="col">部门</th>
        <th width="10%" scope="col">发布者</th>
        <th width="20%" scope="col">发布时间</th>
          <th width="20%" scope="col">操作</th>
      </tr>
      <c:forEach items="${pm.datas }" var="subject" varStatus="i"> 
      <tr>
        <td align="center">${i.index+1 }</td>
        <td class="lan" align="center"><span class="words"><a href="${prc}/scMaster2/detail_master.action?id=${subject.id }" target="_blank">${subject.title }</a></span></td>
        <td align="center">${subject.departmentName }</td>
        <td align="center">${subject.createrName}</td>
        <td align="center"><fmt:formatDate value="${subject.createTime}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
        <td align="center">
<!--        	<span style="margin-right:20px ;"><img src="${prc}/function/img/but.jpg" /></span>-->
        	<c:if test="${subject.flag eq '1'}">
				<a href="#" onclick="javascript:stick('${subject.id }',0);" id="${subject.id }"><img src="${prc}/function/img/blue1.png"/></a>
			</c:if>
			<c:if test="${subject.flag ne '1'}">
				<a href="#" onclick="javascript:stick('${subject.id }',1);" id="${subject.id }"><img src="${prc}/function/img/blue0.png"/></a>
			</c:if>
        </td>
      </tr>
        </c:forEach>
    </table>
  </div>
</div>
</body>
</html>