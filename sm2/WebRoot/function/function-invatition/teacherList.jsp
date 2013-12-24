<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib prefix="invStatus" uri="InvStatus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>邀请查看 教师列表</title>
	
<link href="${prc}/function/function-invatition/teacherList/css/index.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />
<link type="text/css" href="${prc}/function/swfupload/css/default.css" rel="stylesheet"/>
<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<!--<script type="text/javascript" src="${prc}/function/js/jqui.js"></script>-->
<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/kindeditor-min.js" ></script>
<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/lang/zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/swfupload.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/fileprogress.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/handlers.js"></script>
<script type="text/javascript" src="${prc}/function/swfupload/js/swfupload.queue.js"></script>
<script type="text/javascript">
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
			button_image_url: "${prc}/function/swfupload/images/button_2.jpg",
			button_width: "60",
			button_height: "23",
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
    $(function () {
        $("table tr:odd").css("background", "#f0f8fc");
        $("table tr:even").css("background", "#d5e0ee");
    	delWare = function(id){
    		delCount=delCount+1;
    		delFilePaths.push(id);
    		$("#"+id).remove();
    	};

    	//增加与修改
        $(".cx1,.modify").click(function () {
        	var id= $(this).attr("id");
    	    if(!swfu){
    			swfu = new SWFUpload(settings);
    	    }
            var id= $(this).attr("id");
            $('#master').combotree({  
              	 url: 'findAllMaster_user.action',
              	 multiple:true
            }); 
            if(id=="add"){
            	addUpdate="add";
            	vid="";
            	post_url=prc+"/scMaster2/add_invatition.action";
                $(".bg").css("display", "block");
                $(".add1").css("display", "block");
                $(".plan").css("display", "block");
                $(".password").css("display", "block");
                $(".plans").css("display", "none");
            }else{
            	var obj = $(this).parents("tr");
        		vid = obj.attr("id");
            	addUpdate="modify";
            	post_url=prc+"/scMaster2/modify_invatition.action";
            	$.ajax({
    				url:prc+"/scMaster2/detail_invatition.action",
    				type:'post',
    				data:{"tsm2Invatition.id":vid,"t":Math.random()},
    				dataType:'json',
    				success:function(data){
	    		        $("#t_name").val(data.name);
    		        	editorCourseContent.html(data.content);
						$("input[name='a2']").each(function(){
							if(data.flag ==$(this).val()){
								$(this).attr("checked","checked");
							}
						});
				      	$('#master').combotree('setValues',data.users);
					if("0"==data.flag){
						var coursewares = eval('('+data.resources+')');
						var cwsHtml = "";
						count=coursewares.length;
						for(var i=0;i<count;i++){
							cwsHtml = cwsHtml +"<div id=\""+coursewares[i].id+"\">" +coursewares[i].fileName +"<a class=\"progressCancel\" href=\"javascript:void(0);\" onclick=\"delWare('"+coursewares[i].id+"')\" style=\"visibility: visible;float:right;margin:2px;\">×</a></div>";
						}
						$("#wareFile").html(cwsHtml);
		                $(".plan").css("display", "block");
		                $(".password").css("display", "block");
		                $(".plans").css("display", "none");
		                $("#showWareFile").css("display", "block");
					}else{
						 $("#t_link").val(data.link);
			             $(".plan").css("display", "none");
			             $(".password").css("display", "none");
			             $(".plans").css("display", "block");
					}
	                $(".bg").css("display", "block");
	                $(".add1").css("display", "block");
    				},
    				error:function(XMLHttpRequest, textStatus, errorThrown){
    					alert("出错了!");
    					window.location.href=prc+"/scMaster2/teacherList_invatition.action";
    				}
            	});
            }
        });
        
        $(".add-top1 img,#return").click(function () {
        	window.location.href=prc+"/scMaster2/teacherList_invatition.action";
        });
        $("#search").click(function () {
        	window.location.href=prc+"/scMaster2/teacherList_invatition.action?searchDate="+$.trim($("#searchDate").val())+"&searchTitle="+$.trim($("#searchTitle").val())+"&searchUser="+$.trim($("#searchUser").val());
        });
        $(".push").click(function () {
            var s = $(this).attr("id");
        	var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		window.location.href=prc+"/scMaster2/push_invatition.action?tsm2Invatition.id="+id+"&tsm2Invatition.status="+s+"&searchDate="+$.trim($("#searchDate").val())+"&searchTitle="+$.trim($("#searchTitle").val())+"&searchUser="+$.trim($("#searchUser").val());
        });
        $(".del").click(function () {
        	var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		if(confirm("确定要删除吗?"))
    		window.location.href=prc+"/scMaster2/del_invatition.action?tsm2Invatition.id="+id+"&searchDate="+$.trim($("#searchDate").val())+"&searchTitle="+$.trim($("#searchTitle").val())+"&searchUser="+$.trim($("#searchUser").val());
        });
        $(".download").click(function () {
            $(".bg").css("display", "block");
            $(".add-load").css("display", "block");
            var obj = $(this).parents("tr");
    		var id = obj.attr("id");
    		$.ajax({
    			url:prc+"/scMaster2/detail_invatition.action",
    			type:'post',
    			data:{"tsm2Invatition.id":id,"t":Math.random()},
    			dataType:'json',
    			success:function(data){
					var coursewares = eval('('+data.resources+')');
					var cwsHtml = "<tr><th width=\"13%\" scope=\"col\">序号</th><th width=\"66%\" scope=\"col\">附件名称</th><th width=\"21%\" scope=\"col\">操作</th></tr>";
					count=coursewares.length;
					var num=0;
					for(var i=0;i<count;i++){
						num = i+1;
						cwsHtml = cwsHtml +"<tr><td>"+num+"</td><td><span>"+coursewares[i].fileName+"</span></td><td>"+"<a class=\"downfile\" href=\""+prc + "/scMaster2/download_invatition.action?tsm2Invatition.id="+coursewares[i].id+"\">下载</a></td></tr>";
					}
					$("#downFiles").html(cwsHtml);
    			},
    			error:function(XMLHttpRequest, textStatus, errorThrown){
    				alert("出错了!");
    				window.location.href=prc+"/listSections.action?courseId="+courseId;
    			}
        	});
        });
        
        $(".add-loadtop1 img").click(function () {
            $(".bg").css("display", "none");
            $(".add-load").css("display", "none");
        });
        
        $(".lian").click(function () {
            $(".plans").css("display", "block");
            $(".plan").css("display", "none");
            $(".password").css("display", "none");
            $("#showWareFile").css("display", "none");
            
        });
        
        $(".wen").click(function () {
            $(".plan").css("display", "block");
            $(".password").css("display", "block");
            $(".plans").css("display", "none");
            if(addUpdate=="modify"){
           	 $("#showWareFile").css("display", "block");
           }
        });
        $("#btnUpload1").click(function () {
            var _name = $.trim($("#t_name").val());
            var _user = $("#master").combotree('getValues').toString();
            editorCourseContent.sync();
            var _content = $.trim($("#t_content").val());
            var _div = $("input[name='a2'][type='radio']:checked").val();
            var _link=$.trim($("#t_link").val());
            filePaths=new Array();
            if(_name ==""){
                alert("标题名称不能为空！");
                return false;
            }
            if(_user ==""){
                alert("邀请人不能为空！");
                return false;
            }
            if((_div=="0"&&swfu.getStats().files_queued == 0&&addUpdate=="add")||(_div=="0"&&delCount==count&&swfu.getStats().files_queued == 0&&addUpdate=="modify")){
                alert("附件不能为空！");
                return false;
            }
            if(_div=="1"&&_link == ""){
                alert("连接不能为空！");
                return false;
            }
			uploadData = {
					"tsm2Invatition.id":vid,
					"tsm2Invatition.title":_name,
					"searchUser":_user,
					"tsm2Invatition.content":_content,
					"tsm2Invatition.flag":_div,
					"tsm2Invatition.link":_link,
					"delResourses":delFilePaths
			};
			if((_div=="0"&&addUpdate=="add")||(_div=="0"&&addUpdate=="modify"&&swfu.getStats().files_queued > 0)){
				swfu.startUpload();
			}else{
				uploadData.t = Math.random();
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
        });
    });
</script>

<script type="text/javascript">
	KindEditor.ready(function(K) {
	editorCourseContent = K.create('textarea[name="detailContent"]', {
		resizeType : 0,
		allowFileManager : false,
		allowImageUpload : true,
		uploadJson : '${prc}/scMaster2/upload.action',
		items : [
				'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
				'insertunorderedlist', 'link', '|', 'insertfile', 'image']
		});
	});
</script>
</head>
<body>
	<div class="con_conent fixed">
		<h1 class="title">
			<span class="title">当前位置：</span>
			<span class="text"><a href="${prc}/scMaster2/teacherInput_index.action">首页</a> - </span>
			<span class="back"> 给校长发出的邀请</span>
		</h1>
		<div class="table_box fixed">
			<div class="nav">
				<span>提交日期:</span>
				<input type="text" name="searchDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${searchDate}" id="searchDate" style="width:110px;"/>
<!--				<span>邀请人:</span>-->
<!--				<s:select list="persons" name="searchUser" listKey="uid" listValue="name" headerKey="" headerValue="" id="searchUser" cssStyle="width:80px;height:29px;"></s:select>-->
				<span>标题:</span> 
				<input type="text" name="searchTitle" value="${searchTitle}" id="searchTitle"/>
				<a class="cx" href="javascript:void(0);" id="search">查询</a>
				<a class="cx1" href="javascript:void(0);" id="add">增加</a>
			</div>
			<table width="100%" border="0">
				<tr>
					<th width="25%" scope="col">标题</th>
					<th width="25%" scope="col">提交时间</th>
					<th width="25%" scope="col">状态</th>
					<th width="25%" scope="col">操作</th>
				</tr>
				<c:forEach items="${pm.datas }" var="subject">
					<tr id="${subject.id}">
						<td>
							<c:out value="${subject.title}" escapeXml="true"></c:out>
						</td>
						<td>
							<fmt:formatDate value="${subject.createTime}" pattern="yyyy-MM-dd" />
						</td>
						<td><invStatus:showStatus statusId="${subject.status}" /></td>
						<td><c:if test="${subject.status ==0}"><span class="space push" id="1"><a href="javascript:void(0);" >发布</a></span>
								<span class="space modify"><a href="javascript:void(0);">编辑</a></span>
								<span class="space del"><a href="javascript:void(0);">删除</a></span>
							</c:if>
							<c:if test="${subject.status ==1}"><span class="space">
								<a href="${prc}/scMaster2/teacherShow_invatition.action?tsm2Invatition.id=${subject.id}">查看</a></span>
								<span class="space push" id="0"><a href="javascript:void(0); " >取消发布</a></span>
							</c:if>
							<c:if test="${subject.flag ==0}"><span class="space download">
								<a href="javascript:void(0);">附件</a></span>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div  style="text-align:center;font-size:15px;margin-top:20px;">
   		<c:if test="${pm.total>0}">
        <pg:pager url="${prc}/scMaster2/teacherList_invatition.action"
			items="${pm.totalPageNo}" maxPageItems="${pm.totalPageNo}" maxIndexPages="3" export="currentPageNumber=pageNumber">
			<pg:param name="searchDate" value="${searchDate}"/>
			<pg:param name="searchTitle" value="${searchTitle}"/>
			<pg:param name="searchUser" value="${searchUser}"/>

			总计${pm.total}条
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
		</c:if>
    </div>
	</div>
	<!--新增-->
	<div class="bg"></div>
	
	<div class="add1">
		<div class="add-top1">
			<p>新增/编辑</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-down">
			<p class="tit">
				<span>标题名称：</span>
				<input id="t_name" type="text" value="" />
			</p>
			 <p class="tit">
				<span>邀&nbsp;&nbsp;请&nbsp;&nbsp;人：</span>
				<select id="master"  style="width:218px;height:27px;"></select>
<!--				<s:select list="persons" listKey="uid" listValue="name" id="t_user" cssStyle="width:218px;height:29px;"></s:select>-->
			</p>
			<div class="tit1">
				<p>事件详情：</p>
				<textarea id="t_content" name="detailContent" style="width:500px;height: 150px;"></textarea>
			</div>
			<p class="tit2">
				附件/链接：
				<span>
					<a href="javascript:void(0);" class="wen">
						<input type="radio" name="a2" value="0" checked="checked"/>附件
					</a>
				</span>
				<span>
					<a href="javascript:void(0);" class="lian">
						<input type="radio" name="a2" value="1"/>链接
					</a>
				</span>
			</p>
			<div id="showWareFile" style="display:none">
			<p class="titword">
				<span>已传文件：</span>
			</p><div id="wareFile" style="float:left;width:502px; height:80px;overflow:auto; border:1px solid #ccc;margin-bottom:10px;"></div></div>
			<div class="tit3 plan">
			    <div class="password" id="divMovieContainer" style="disapply:block;margin-left:12px;display:inline"><span id="spanButtonPlaceHolder" ></span></div>

				<div style="margin-left:15px; width:480px;height:80px;maroverflow:auto;overflow-x:hidden;display:inline;float:left" id="fsUploadProgress" class="fieldset flash">
                </div>
			</div>
			<div class="tit3 plans">
				<p style="width:60px;height:20px;margin-left:15px;">链&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接：</p>
				<textarea id="t_link"></textarea>
			</div>
			
			<a href="javascript:void(0);" class="return" id="btnUpload1" style="margin-left: 200px;">提交</a> 
			<a href="javascript:void(0);" class="return" id="return">返回</a>
		</div>
	</div>
	<!--新增 END-->
	<!--弹出层1-->
	<div class="add-load">
		<div class="add-loadtop1">
			<p>附件列表</p>
			<img src="${prc}/function/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-loaddown">
			<table width="430px" style="border: 1px solid #d7d7d7; margin-top: 10px;" id="downFiles">
			</table>
		</div>
	</div>
</body>
</html>