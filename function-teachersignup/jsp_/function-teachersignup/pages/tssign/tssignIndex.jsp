<%@ page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@ include file="../common.jsp"%>
<%
java.util.Date d = new java.util.Date();
java.text.SimpleDateFormat dformat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
String datetime = dformat.format(d); 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教师报名</title>
<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc }/function/function-teachersignup/js/tssign/tssign.js"></script>
<script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.8.0.min.js"></script>
</head>
<body onload="showTime()">
<div class="con_conent fixed">
     <h1 class="title"><span class="title">教师报名</span><span class="text">${sign.userName }，欢迎您...</span><span class="back">返回上一页</span></h1>
        <div class="table_box fixed">
            <div class="prompt">
                <p class="tit1"><fmt:formatDate value="${act.openDate}" pattern="yyyy" />
        					 - <fmt:formatDate value="${act.endDate}" pattern="yyyy" />学年　上学期教师报名</p>
                <p class="tit2">报名时间：<fmt:formatDate value="${act.openDate}" pattern="yyyy年MM月dd日 HH:mm" />
        					 - <fmt:formatDate value="${act.endDate}" pattern="yyyy年MM月dd日 HH:mm" /></p>
                <div class="left">
					<c:if test="${datetime<act.openDate}">
                    <p class="top">想知道我们都为您提供了哪些项目吗？点击这里哦！<img src="/schoolapp/function/function-teachersignup/image/but3.jpg" /></p>
                    </c:if>   
                    <c:if test="${datetime>=act.openDate}">
                    <p class="bottom">想知道您已经选了哪些项目吗？点击这里哦！<img src="/schoolapp/function/function-teachersignup/image/but4.jpg" /></p>
                    </c:if>
                </div>
                <div class="right">
                	 <img src="/schoolapp/function/function-teachersignup/image/pic1.jpg" />
                  <c:if test="${openDate>0 }">
	        		  <a id="nostar"  style="color: green;" ></a>
	        		</c:if>
	       		
                </div>
            </div>
              <div class="prompt">
               		 <p class="tit1"><fmt:formatDate value="${act.openDate}" pattern="yyyy" />
        					 - <fmt:formatDate value="${act.endDate}" pattern="yyyy" />学年　上学期教师报名</p>
                	<p class="tit2">报名时间：<fmt:formatDate value="${act.openDate}" pattern="yyyy年MM月dd日 HH:mm" />
        					 - <fmt:formatDate value="${act.endDate}" pattern="yyyy年MM月dd日 HH:mm" /></p>
             	 <div class="left">
             	 	<c:if test="${datetime>=act.openDate}">
                   		 <p class="top">想知道我们都为您提供了哪些项目吗？点击这里哦！<img src="/schoolapp/function/function-teachersignup/image/but5.jpg"/></p>
                    </c:if>
                    <c:if test="${datetime>=act.openDate}">
               	     	<p class="bottom">想知道您已经选了哪些项目吗？点击这里哦！<img src="/schoolapp/function/function-teachersignup/image/but6.jpg" /></p>
              		</c:if>
               	</div>
                <div class="right">
                   <img src="/schoolapp/function/function-teachersignup/image/pic2.jpg" />
                    <c:if  test="${endTime>0&&openDate==0 }">
	     			<a id="star"  style="color: green;" ></a>
	       		 </c:if>
                </div>
            </div>

            </div>

            
    </div>

</div>
</body>
</html>