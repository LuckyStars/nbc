<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查阅处理</title>
<link href="../function/css/index.css" rel="stylesheet" type="text/css" />
<link href="../function/css/gzt.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="../function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../function/js/easyui/themes/icon.css" />
<script type="text/javascript" src="../function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
</head>
<body>
  <div class="right1">
    <h1>当前位置：首页 - <span style="color:#E00001">待处理文件 - 查阅处理</span></h1>
    <p class="fonts"><s:property value="documentVo.title"/></p>
      <div class="right-box">
          <p><span class="box-time">发布时间：</span><span class="box-sp"><s:property value="documentVo.publishTime"/></span></p>
          <p><span class="box-time">截止时间：</span><span class="box-sp"><s:property value="documentVo.expireTime"/></span></p>
          <p><span class="box-time">流转状态：</span><span class="box-sp"><s:property value="documentVo.status"/></span></p>
           <p><span class="box-time">紧张程度：</span><span class="box-sp"><s:property value="documentVo.notifyProfileName"/></span></p>
          <p style="margin-left:110px;"><span class="box-time">发文单位：</span><span class="box-sp"><s:property value="documentVo.documentSourceName"/></span></p>
          <p style="margin-left:50px;"><span class="box-time">发布者：</span><span class="box-sp"><s:property value="documentVo.authorName"/></span></p>
          <p><span class="box-time">流转路径：</span>
          			<c:forEach items="${documentVo.aLLDocNames}" var="personName">
							<c:choose>
								<c:when  test="${sessionScope.documentFlow_init.name eq personName}">
									<div id="curUserName" style="display:inline;" >${personName}</div>
								</c:when>
								<c:otherwise>
									 <span class="box-sp">
									${personName}
									</span>
								</c:otherwise>
							</c:choose>
						&nbsp;&nbsp;
						</c:forEach>
<!--        <a href="#" style="color:#02275B; margin-left:10px; text-decoration:underline;">(查看全部)</a>-->
			</p><br />
		 <s:if test="documentVo.attachments.size != 0">
	          <p><span class="box-time">附件：</span>
	          	<s:iterator value="documentVo.attachments" var="attachment">
	          		<a href="downloadAttachment.action?aid=<s:property value='#attachment.id'/>" style="margin-left:10px; text-decoration:underline;">
	          	  		<span class="box-sp" style="background:url(../function/img/ico4.jpg) no-repeat left; padding-left:20px;">
		          			<s:property value="#attachment.fileName" />
		          		</span>
		          	</a>
		        </s:iterator>  	
	          </p>
         </s:if>
      </div>
       <div class="articles">
       	<s:property value="documentVo.content" escape="false"/>
      </div>
      <div class="bun">
          <a href="javascript:history.back();">返回</a>
      </div>
  </div>
</body>
</html>