<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="/sm2/function/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="/sm2/function/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="/sm2/function/swfupload/js/handlers.js"></script>
<script type="text/javascript" src="/sm2/function/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/sm2/function/js/jquery-1.8.3.min.js"></script>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager" %>
<!--<script type="text/javascript" src="/sm2/function/detail-step/js/upload.js"></script>-->
<script type="text/javascript">
var prc ="${pageContext.request.contextPath}"; 
$(function () {
	  $("table tr").css("height","27px");
	var swfu;
	var filePaths=new Array();
	var delFilePaths=new Array();
	var uploadData={};
	var addUpdate="";
	var vid="";
	var post_url="";
	var delCount=0;
	var count=0;
	 var upload;
	 
	var settings = {
		flash_url : prc+"/function/swfupload/js/swfupload.swf",
		upload_url: "/sm2/scMaster2/resourceUpload.action",	
		file_post_name: "filedata",   
		file_size_limit : "20 MB",
		file_types : "*.*",
		file_types_description : "",
		file_upload_limit : 20,
		file_queue_limit : 100,
		post_params: {  
            "JSESSIONID": "<%=request.getSession().getId()%>"
           },  
		custom_settings : {
			progressTarget : "fsUploadProgress"
		},
		debug: true,
		// Button settings
		button_image_url: prc+"/function/swfupload/images/button_2.jpg",
		button_width: "60",
		button_height: "24",
		button_placeholder_id: "spanButtonPlaceHolder",
		button_text: '<span class="b">浏览文件</span>',
		button_text_style: '.b{ font-size: 12;text-align:center;}',
		button_text_top_padding: 3,
				
		// The event handler functions are defined in handlers.js
	    file_queued_handler : fileQueued,
		file_queue_error_handler : fileQueueError,
		upload_start_handler : uploadStart,
		upload_progress_handler : uploadProgress,
		upload_error_handler : uploadError,
		upload_success_handler : uploadSuccess,
		queue_complete_handler : queueComplete,
		use_query_string : true
	};
	upload = new SWFUpload(settings);
	
	function uploadSuccess(file, serverData){
	   var str=$.parseJSON(JSON.stringify(serverData));
	   var obj=$.parseJSON(str);
	   var error=obj.error;
	   alert(error);
	   if(error==0){
			filePaths.push(obj.path);
			//var currentTime = new Date();
			//var progress = new FileProgress(file, this.customSettings.progressTarget);
		  //  progress.setComplete();
			//Calculate upload time
			//var cTime = (Math.ceil(currentTime-iTime)/1000);
		//	var zmin = 0;
		//	var zsec = 0;
		//	zmin = Math.floor(cTime/60);
		//	if (zmin < 10) {
		//	  zmin = "0" + zmin; 
		//	}
		//	zsec = Math.ceil(cTime % 60);
		//	if (zsec < 10) {
		//	  zsec = "0" + zsec; 
		//	}
			//Show how long the upload took
	//		progress.setStatus("上传完成，用时:<b><font color=red> " + zmin + "分:" + zsec + '秒</font></b>');
	//		progress.toggleCancel(false);
		}else{
			alert(obj.message);
		}
}
function queueComplete(numFilesUploaded) {
	if(numFilesUploaded==0){
		alert("上传文件失败！");
	}else{
		alert($("#progId").val()+"pppp");
    	$.ajax({
    		url:prc+"/scMaster2/add_resource.action",
    		type:'post',
    		data:{resourses:filePaths.toString(),progId:$("#progId").val()},
    		dataType:'json',
    		success:function(data){
    			findAll();
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown){
    			alert("出错了!");
    		}
    	});
	}
}
$("#upload").click(function(){
	upload.startUpload();
});


});
function findAll(){
	alert($("#progId").val());
	$.post("findAll_resource.action",{progId : $("#progId").val() ,type:$("#type").val()},function(data){
		if(data != ''){
			$(".resource-lists").empty();
			$(".resource-lists").html(data);
		}
	  	});
}
function deleteR(id){
	$.ajax({
		url:prc+"/scMaster2/delete_resource.action",
		type:'post',
		data:{id:id},
		dataType:'json',
		success:function(data){
			findAll();
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert("删除出错!");
		}
	});
	}
</script>
</head>
<body>
<!--	<div style="float:right;"><span id="spanButtonPlaceHolder" ></span></div>${progId}-->
	 <div class="liuyan">
   		  	<a href="#">
   		  		<img src="../function/detail-step/img/liulan.png" width="64" height="23" />
   		  		<img src="../function/detail-step/img/tijiao.png" width="64" height="23" />
   		  	</a>
     </div>
     <div class="biaoge">
	 <form id="saveForm" method="post">
	 	<input type="hidden" value="${progId}" id="progId"></input>
	 	<input type="hidden" value="${type }" id="type"></input>
         	<table width="540px" border="0" class="table-xi">
         	 <tr>
                <th width="30%" scope="col" style="color:#004E7F; font-size:14px; font-weight:bold">名称</th>
                <th width="30%" scope="col" style="color:#004E7F; font-size:14px; font-weight:bold">时间</th>
                <th width="40%" scope="col" style="color:#004E7F; font-size:14px; font-weight:bold">操作</th>
            </tr>
            <c:forEach items="${pm.datas}" var="resource" varStatus="i">
	           <tr>
<!--	                <td align="center">${i.index+1 }</td>-->
	                <td align="center">${resource.fileName }</td>
	                <td align="center"><fmt:formatDate value="${resource.createTime}" pattern="yyyy-MM-dd"/></td>
	                <td align="center">
	                	<span class="space"><a href="${resource.filePath}">下载</a></span>
						<span class="space"><a href="javascript:deleteR('${resource.id}')">删除</a></span>
	                </td>
	           </tr>
            </c:forEach>
        </table>  
       <c:if test="${pagerUtils.totalResult>0}">
		<div style="text-align:center;font-size:15px;margin-top:20px;">
			 <pg:pager url="findAll_resource.action"
    			items="${pagerUtils.totalResult}" maxPageItems="${pagerUtils.pageSize}" maxIndexPages="5" export="currentPageNumber=pageNumber">
    			<pg:param name="progId" value="${progId }"/>
    			<pg:param name="type" value="${type }"/>
    			总计${pagerUtils.totalResult}条
    			<pg:first>
    				<a href="${pageUrl}">首页</a>
    			</pg:first>
    			<pg:prev>
    				<a href="${pageUrl}" >上一页</a> 
    			</pg:prev>
    			<pg:pages>
    				<c:choose>
    					<c:when test="${currentPageNumber eq pageNumber}">
    						<font color="red">${pageNumber}</font>
    					</c:when>
    					<c:otherwise>
    						<a href="${pageUrl}">${pageNumber }</a>
    					</c:otherwise>
    				</c:choose>
    			</pg:pages>
    			<pg:next>
    				<a href="${pageUrl}" >下一页</a> 
    			</pg:next>
    			<pg:last>
    				<a href="${pageUrl}">尾页</a>
    			</pg:last>
    		</pg:pager>
		</div>
		</c:if>
<!--        <div class="flash" style="margin-left:15px; width:537px;height:80px;maroverflow:auto;overflow-x:hidden;display:inline;float:left;border: 1px solid #A4B3EE" id="fsUploadProgress"></div>  -->
      
	</form>
	</div>
</body>
</html>