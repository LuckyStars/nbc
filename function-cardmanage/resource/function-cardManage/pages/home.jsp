<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${prc}/function/function-cardManage/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/function-cardManage/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
$(function(){

	
});


</script>
</head>
<body>
<div class="con_conent fixed">
     <h1 class="title"><span class="title"></span><span class="text">欢迎你</span><span class="back">返回上一页</span></h1>
        <div class="table_box fixed">
        	<a href="../cardManage/toApply_apply.action">
	            <dl class="warp">
	                <dt><img src="${prc}/function/function-cardManage/image/tu.jpg" /></dt>
	                <dd>补卡申请</dd>
	            </dl>
	        </a>
	        <a href="../cardManage/list_apply.action">
             <dl class="warp">
                <dt><img src="${prc}/function/function-cardManage/image/tu1.jpg" /></dt>
                <dd>补卡申请记录</dd>
            </dl>
            </a>
              <a href="../cardManage/manageList_apply.action">
             <dl class="warp">
                <dt><img src="${prc}/function/function-cardManage/image/tu2.jpg" /></dt>
                <dd>补卡信息管理</dd>
            </dl>
            </a>
    </div>
</div>
</body>
</html>
