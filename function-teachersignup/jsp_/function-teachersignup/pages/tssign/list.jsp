<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报名预览</title>
<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("table tr:odd").css("background", "#f0f8fc");
            $("table tr:even").css("background", "#d5e0ee");
            $(".menu li").click(function () {
                $(".arr").attr("class", "");
                $(this).attr("class", "arr");
            });
            $(".menu li.mag_btn").click(function () {
                $(this).attr("class", "mag_btn");
            });
            var x = $(document).height();
            $(".shade").css({ "width": $(document).width(), "height": $(document).height() });
        });
</script>
</head>
<body>
<div class="con_conent fixed">
     <h1 class="title"><span class="title">教师报名</span><span class="text">金强老师，欢迎您...</span><span class="back">返回上一页</span></h1>
        <div class="table_box fixed">
            <div class="box">
            <p class="join"><fmt:formatDate value="${act.openDate}" pattern="yyyy" />
        					 - <fmt:formatDate value="${act.endDate}" pattern="yyyy" />　下学期 XXX报名一览</p>
   
    <table width="448px" border="0" class="table-xi">
            <tr>
                <th width="30%" scope="col">序号</th>
                <th width="70%" scope="col">报名类型</th>
            </tr>
            <c:forEach var="Subject" items="${Subject}" varStatus="status"> 
            <tr>
                <td>${ status.index + 1}</td>
                <td>${ Subject.name}</td>
            </tr>
            </c:forEach>
        </table >
       	文件上传： <input type="file" name="filename"/>
                <a href="history.go(-1)" class="btn btnright">返回</a>
                </div>
             </div>
</div>
</body>
</html>