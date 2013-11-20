<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${prc }/function/detail-step/css/pic.css" type="text/css"/>
<style type="text/css">
 div.file-panel {
    -moz-user-select: none;
    background: none repeat scroll 0 0 rgba(0, 0, 0, 0.5);
    height: 0;
    left: 0;
    overflow: hidden;
    top: 0;
    width: 100%;
    z-index: 300;
}
div.file-panel span {
    background: url("/sm2/function/detail-step/img/picOper.png") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    cursor: pointer;
    display: inline;
    float: right;
    height: 24px;
    margin: 5px 1px 1px;
    //overflow: hidden;
    text-indent: -9999px;
    width: 24px;
    background-position: -48px -24px;//取图片
}
</style>
<script type="text/javascript" src="/sm2/function/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="/sm2/function/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="/sm2/function/swfupload/js/handlers.js"></script>
<script type="text/javascript" src="/sm2/function/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript" src="/sm2/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/sm2/function/js/jquery.lazyload.js"></script>
<!--<script type="text/javascript" src="/sm2/function/detail-step/js/upload.js"></script>-->
<script type="text/javascript">
$(function () {
	$("img").lazyload(); 
	$("table tr").css("height","27px");
	$(".juti").bind({
		  mouseenter:function(){
						$(this).children(".file-panel").slideDown("slow",function(){
							$(this).css("height","30px");	
						 });
					},  
		  mouseleave:function(){
						 $(this).children(".file-panel").slideUp("slow",function(){
							$(this).css("height","0px");	
			 			});
			 		}  
		});
	$(".cancel").click(function(){
		$.ajax({
    		url:prc+"/scMaster2/delete_resource.action",
    		type:'post',
    		data:{id:this.id},
    		dataType:'json',
    		success:function(data){
    			$.post("findPic_resource.action",{progId : $("#progId").val() ,type:1},function(data){
            		if(data != ''){
            			$(".resource-lists").empty();
            			$(".resource-lists").html(data);
            		}
          	  	});
    		},
    		error:function(XMLHttpRequest, textStatus, errorThrown){
    			alert("删除出错!");
    		}
    	});
		});

	var prc ="${pageContext.request.contextPath}"; 
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
		flash_url : "/sm2/function/swfupload/js/swfupload.swf",
		upload_url: prc+"/scMaster2/resourceUpload.action",	
		file_post_name: "filedata",   
		file_size_limit : "20 MB",
		file_types : "*.jpg;*.gif;*.jpeg;*.png;*.bmp;*.ico",
		file_types_description : "",
		file_upload_limit : 20,
		file_queue_limit : 100,
		custom_settings : {
			progressTarget : "fsUploadProgress"
		},
		debug: false,
		// Button settings
		button_image_url: "/sm2/function/swfupload/images/TestImageNoText_65x29.png",
		button_width: "60",
		button_height: "24",
		button_placeholder_id: "spanButtonPlaceHolder",
		button_text: '<span class="cx1">浏览文件：</span>',
		//button_image_url : "swfupload/xpbutton.png",    // 按钮图标  
		button_text_top_padding: 2,
		
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
	   if(error==0){
			filePaths.push(obj.path);
			var currentTime = new Date();
			var progress = new FileProgress(file, this.customSettings.progressTarget);
		    progress.setComplete();
			//Calculate upload time
			var cTime = (Math.ceil(currentTime-iTime)/1000);
			var zmin = 0;
			var zsec = 0;
			zmin = Math.floor(cTime/60);
			if (zmin < 10) {
			  zmin = "0" + zmin; 
			}
			zsec = Math.ceil(cTime % 60);
			if (zsec < 10) {
			  zsec = "0" + zsec; 
			}
			//Show how long the upload took
			progress.setStatus("上传完成，用时:<b><font color=red> " + zmin + "分:" + zsec + '秒</font></b>');
			progress.toggleCancel(false);
		}else{
			alert(obj.message);
		}
}
	function queueComplete(numFilesUploaded) {
		if(numFilesUploaded==0){
			alert("上传文件失败！");
		}else{
			alert(filePaths.toString());
	    	$.ajax({
	    		url:prc+"/scMaster2/add_resource.action",
	    		type:'post',
	    		data:{resourses:filePaths.toString(),progId:$("#progId").val(),type:1},
	    		dataType:'json',
	    		success:function(data){
	    			//window.location.href=prc+"/scMaster2/teacherList_invatition.action";
	    		},
	    		error:function(XMLHttpRequest, textStatus, errorThrown){
	    			alert("出错了!");
	    			//window.location.href=prc+"/scMaster2/teacherList_invatition.action";
	    		}
	    	});
		}
	}
	$(".return").click(function(){
		upload.startUpload();
	});
	
});
</script>
</head>
<body>
	 <form id="saveForm" method="post">
	 	<input type="hidden" value="${progId }" id="progId"></input>
            <c:forEach items="${pm.datas}" var="resource" varStatus="i">
               <div class="juti" style="border:1px solid #A4B3EE"> 
               		<img src="${resource.filePath }" width="102" height="140" />
               		<div class="file-panel" style="height: 0px; overflow: hidden;">
               			<span class="cancel" id="${resource.id}">删除</span>
<!--               			<span class="rotateRight">向右旋转</span>-->
<!--               			<span class="rotateLeft">向左旋转</span>-->
               		</div>
               </div>
            </c:forEach>
<!--        <div class="flash" style="margin-left:15px; width:537px;height:80px;maroverflow:auto;overflow-x:hidden;display:inline;float:left;border: 1px solid #A4B3EE" id="fsUploadProgress"></div>  -->
        <span id="spanButtonPlaceHolder"></span>
	</form>
</body>
</html>