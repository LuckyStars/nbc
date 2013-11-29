<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查阅处理</title>
<link href="../function/css/index.css" rel="stylesheet" type="text/css" />
<link href="../function/css/gzt.css" rel="stylesheet"/>
<link href="../function/css/teacherTree.css" rel="stylesheet"/>
<link rel="stylesheet" type="text/css" href="../function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../function/js/easyui/themes/icon.css" />
<script type="text/javascript" src="../function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(function(){
	$("#viewgroup").click(function(){
		$(".online_friend").css({top:$(this).offset().top-($(".online_friend").height()/2),left:$(this).offset().left-$(".online_friend").width()});
	    $(".online_friend").toggle();
	    
		$(".online_friend .online_btn a:first").click(function(){
			var nodes = $('.userlist').tree('getChecked');
			var s1 = '';
			var bool = true;
			for(var i=0; i<nodes.length; i++){
				var b = $('.userlist').tree('isLeaf', nodes[i].target);
				if(b){
					var nodeId = nodes[i].id;
					if(nodeId.indexOf("u|") == -1){
						bool = false;
						break;
					}
					var newNodeId = nodeId.substring(nodeId.indexOf("u|") + 2);
					
				    s1 += '<div><input type="hidden" name="forwardings" value="'+ newNodeId +'" /><span>'+nodes[i].text+'</span><a class="name_del" href="javascript:void(0);">&nbsp;&nbsp;&nbsp;&nbsp;</a></div>';
				}
			}
			
			if(!bool){
				alert("只能选择人员");
				return false;
			}
			$("#smslist").html(s1);
			$(".online_friend").hide();
		});
		$(".online_friend .online_btn a:last").click(function(){
			$(".online_friend").hide();
		});
	});
		$("#dd").dialog({
			title:"查看路径",
			closed:true,
			modal: true,
			top:100
		});
		$("#viewall").click(function(){
			$("#dd").dialog("open");
		});
		
		$.post('displayTree.action',{pids:$("#pids").val()},function(data){
			$('.userlist').tree({
				checkbox: true,
				data: data,
				onLoadSuccess:function(){
				   $('.userlist').tree('collapseAll');
			    }
			});
		},"json");
		
		$("#search").keyup(function(){
			try {
				$(".tree-title").parent("div").hide();
				
				var nodes = $(".tree-title:contains('"+this.value+"')").parent("div");
				nodes.show();
				for(var i=0; i<nodes.length; i++){
				  var node = nodes[i];
				  var prentUL = $(node).parent("li").parent("ul");
				  if(prentUL){
				    prentUL.show();
				    prentUL.prev("div").show();
				  }
				}
				
			}catch(e){ }
			
			var searchVal = $.trim($("#search").val());
			if (searchVal!="") {
				$('.userlist').tree('expandAll');
			}else{
				$('.userlist').tree('collapseAll');
			}
		});
		$(".name_del").live("click",function(){
			$(this).parent().remove();
		});
});
function sumitFun(){
	$("#handlingDocument_handlingDocument").attr("disabled", "disabled").val("提交中...").css("color", "#808080");
	var ctn = $("textarea[name='commentVo.content']").val();
	if(ctn.length > 200){
		alert("处理意见不能超过200字符!");
		$("#handlingDocument_handlingDocument").removeAttr("disabled").val("已阅").css("color", "#000000");
		return false;
	}
	return true;
}
function preView(attId){
	checkName();
	var docId = '${documentVo.id}';
	var response = $.ajax({
		async:false,
		url:"${prc}/masterDocumentFlow/alreadyReadDocument.action",
		data:"documentVo.id="+docId
	}).responseText;
	if(response!='suc'){
		//something wrong
	}
	var divContent = $.ajax({
		async:false,
		url:"${prc}/masterDocumentFlow/preViewAttachment.action",
		data:"aid="+attId
	}).responseText;
	$("#popDiv").html(divContent);
	$("#popDiv").skygqbox({title:"在线预览"});
}

function checkName(){
	var name = '${sessionScope.documentFlow_init.name}' + '√';
	$("#curUserName").html(name);
}
</script>
</head>
<body>
  <div class="right1">
    <h1>当前位置：<a href="javascript:parent.location.href='${prc}/scMaster2/index_index.action';">首页</a> - <span style="color:#E00001">待处理文件 - 查阅处理</span></h1>
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
			</p>
	
          <p><span class="box-time">附件：</span>
          <s:if test="documentVo.attachments.size != 0">
			<s:iterator value="documentVo.attachments" var="attachment">
	          <span class="box-sp" style="background:url(../function/img/ico4.jpg) no-repeat left; padding-left:20px;">
	          	<s:property value="#attachment.fileName" /></span>
	            <a href="javascript:preView('<s:property value='#attachment.id'/>');">预览</a>
	          <a href="${por}/documentFlow/downloadAttachment.action?aid=<s:property value='#attachment.id'/>" style="margin-left:10px; text-decoration:underline;">下载</a></p>
          	</s:iterator>
		  </s:if>
		 <s:else>无</s:else>
      </div>
       <div class="articles">
       	<s:property value="documentVo.content" escape="false"/>
      </div>
	<s:form method="post" namespace="/masterDocumentFlow" onsubmit="return sumitFun();" action="handlingDocument" >
	<s:hidden name="documentVo.id" value="%{documentVo.id}"/>
	<s:hidden name="taskType" value="%{#request.taskType}" />
	<s:hidden id="pids" value="%{#request.pids}" />
      <div class="text-box">
          <div class="text-left">
              <p>处理意见：</p>
              <textarea name="commentVo.content" style="overflow-x:hidden;overflow-y:auto; resize:none;" ></textarea>
          </div>
          <s:if test="('DOCUMENTFLOW_TRANSFERER' in #session.roleList) && #request.taskType == 0">
          <div class="text-right">
              <p>转发至：</p>
              <textarea></textarea>
          </div>
          <a href="#" id="viewgroup">选人</a>
          </s:if>
      </div>
      </s:form>
      <div class="bun">
          <a href="handlingDocument">已阅</a>
          <a href="javascript:window.location.href='listUnhandledDocumentTask.action';">返回</a>
      </div>
  </div>
    <div class="online_friend">
	  <div class="friend_box">
	    <h1 class="online_tit">选择用户</h1>
	    <div class="search" style="height: 22px;">&nbsp;&nbsp;搜索用户&nbsp;&nbsp;
	    	<input type="text" id="search" value="" style="height:18px;width:150px;"/>
	    </div>
	    <div class="userlist"></div>
	    <div class="searchlist"></div>
	  </div>
	  <div class="online_btn"><a href="#">确定</a><a href="#">返回</a></div>
	</div>
	<div id="dd" style="padding: 5px; width: 480px; height: 320px;"></div>
	<div id="popDiv" style="width: 600px; height: 400px;"></div>
</body>
</html>