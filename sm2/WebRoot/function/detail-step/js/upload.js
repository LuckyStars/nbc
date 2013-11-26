var prc ="${pageContext.request.contextPath}"; 
$(function () {
	
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
		file_types : "*.*",
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