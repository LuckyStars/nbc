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
	var settings = {
			flash_url : "${prc}/function/swfupload/js/swfupload.swf",
			upload_url: "${prc}/scMaster2/swfUpload.action",	
			file_post_name: "file",   
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
			button_image_url: "${prc}/function/swfupload/images/TestImageNoText_65x29.png",
			button_width: "60",
			button_height: "24",
			button_placeholder_id: "spanButtonPlaceHolder",
			button_text: '<span class="">浏览文件：</span>',
			button_text_style: ".theFont { font-size: 12;text-align:center;color:#ffffff;}",
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
	function uploadStart(file) {
		try {
		   /* I don't want to do any file validation or anything, I'll just update the UI and
		   return true to indicate that the upload should start.
		   It's important to update the UI here because in Linux no uploadProgress events are called. The best
		   we can do is say we are uploading.
		   */
		   //Capture start time
		   var currentTime = new Date();
		   iTime = currentTime;
		   //Set Timeleft to estimating
		   Timeleft = "计算中...";
		    
		   var progress = new FileProgress(file, this.customSettings.progressTarget);
		   progress.setStatus("Uploading...");
		   progress.toggleCancel(true, this);
		   var params = {fileName:file.name,"t":Math.random()};
		   this.setPostParams(params);
		}
		catch (ex) {}

		return true;
		}
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
			uploadData.t = Math.random();
			uploadData.resourses = filePaths;
	    	$.ajax({
	    		url:post_url,
	    		type:'post',
	    		data:$.param(uploadData, true),
	    		dataType:'json',
	    		success:function(data){
	    			window.location.href=prc+"/scMaster2/teacherList_invatition.action";
	    		},
	    		error:function(XMLHttpRequest, textStatus, errorThrown){
	    			alert("出错了!");
	    			window.location.href=prc+"/scMaster2/teacherList_invatition.action";
	    		}
	    	});
		}
	}