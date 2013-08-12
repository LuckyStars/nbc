<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>新建报名事件</title>
		<link href="${prc }/function/function-teachersignup/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
		<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${prc }/function/function-teachersignup/easyui/jquery.easyui.min.js"></script>
		
		<link type="text/css" href="${prc}/function/function-teachersignup/swfupload/css/default.css" rel="stylesheet"/>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/swfupload.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/fileprogress.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/handlers.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/swfupload.queue.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/datePicker/WdatePicker.js"></script>
		
		<script type="text/javascript">
		var swfu;
		window.onload = function() {
			var settings = {
				flash_url : "${prc}/function/function-teachersignup/swfupload/js/swfupload.swf",
				upload_url: "${prc}/teachersignup/add_act.action",	
				file_post_name: "atta",   
				file_size_limit : "10 MB",
				file_types : "*.*",
				file_types_description : "",
				file_upload_limit : 1,
				file_queue_limit : 1,
				custom_settings : {
					progressTarget : "fsUploadProgress"
				},
				debug: false,
				// Button settings
				button_image_url: "${prc}/function/function-teachersignup/swfupload/images/TestImageNoText_65x29.png",
				button_width: "65",
				button_height: "27",
				button_placeholder_id: "spanButtonPlaceHolder",
				button_text: '<span class="">浏览文件</span>',
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
			swfu = new SWFUpload(settings);
		};
		function uploadSuccess(){
			alert('上传成功');
			window.location.href='';
		}
		function uploadError(){
			alert('上传失败');
			window.location.href='';
			}
		function submitForm(){//提交表单
			if($.trim($("#actName").val())==''){
				$("#actName").focus();
				return false;
			}
		}
		
	</script>
	</head>
	<body>
	<div class="con_conent fixed">
		<h1 class="title">
			<span class="title">教师报名>新增报名</span>
			<span class="back">返回上一页</span>
		</h1>
	    <div class="table_box fixed">
	    	<form name="mainFrom" action="${prc }/teachersignup/add_act.action" method="post" >
			<p class="apply">
				报名名称：<span>
						<input id="actName" class="easyui-validatebox" data-options="required:true" type="text" />
						</span>
			</p>
	       	<div class="data">
	       		<p class="begin">报名起始日期：
	       		<span>
	       			<input name="openDate" class="Wdate" type="text" id="openDate"
					onfocus="WdatePicker({onpicked:function(){endDate.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\');}',isShowClear:false})"
					readonly="readonly"
					 />
	       		</span>
	       		</p>
	       		<p class="end">报名结束日期：
	       			<span>
	       				<input name="endDate" class="Wdate" type="text" id="endDate"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'openDate\');}',isShowClear:false})"
						readonly="readonly"
						 />
	    			</span>
	    		</p>
	    	</div>
	    	
	        <div style="clear:both;"></div>
	        <%-- 报名类型 --%>
	        
	        <div class="type-1"><span class="tit3">报名类型：</span>
	           	<div style="width:280px;overflow: hidden;">
	       	<table id="subjectDL" class="easyui-datagrid" style="width:280px;height:auto"
				data-options="
					singleSelect: true,
					toolbar: '#tbSubject',
					onClickRow: onClickRowSubject
				">
				<thead>
					<tr>
						<th data-options="field:'subjectName',width:240,
							editor:{
								type:'validatebox',
								options:{required:true}
							}">报名类型
						</th>
					</tr>
				</thead>
			</table>
		
			<div id="tbSubject" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" 
					data-options="iconCls:'icon-add',plain:true" onclick="appendSubject()">新增</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" 
					data-options="iconCls:'icon-remove',plain:true" onclick="removeSubjectRow()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" 
					data-options="iconCls:'icon-save',plain:true" onclick="acceptSubject()">确定</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" 
					data-options="iconCls:'icon-save',plain:true" onclick="subjectValues()">show</a>
			</div>
			</div>
			</div>
			<script type="text/javascript">
				var editIndexSubject = undefined;
				
				function endEditingSubject(){
					if (editIndexSubject == undefined){return true;}
					if ($('#subjectDL').datagrid('validateRow', editIndexSubject)){
						
						$('#subjectDL').datagrid('endEdit', editIndexSubject);
						var curValue = $('#subjectDL').datagrid('getRows')[editIndexSubject]['subjectName'];
						
						if($.trim(curValue)==''){
							$('#subjectDL').datagrid('beginEdit',editIndexSubject);
							return false;
						}
						
						var allRows = $('#subjectDL').datagrid('getRows');
						for(var i =0;i<allRows.length;i++){
							if (i!=editIndexSubject && curValue== allRows[i]['subjectName']){
								$.messager.alert('名称重复','报名类型不能重复');
								$('#subjectDL').datagrid('beginEdit',editIndexSubject);
								return  false;
							}
						}
						return true;
					} else {
						return false;
					}
				}
				
				function onClickRowSubject(index){
					if (editIndexSubject != index){
						if (endEditingSubject()){
							$('#subjectDL').datagrid('selectRow', index)
									.datagrid('beginEdit', index);
							editIndexSubject = index;
						} else {
							$('#subjectDL').datagrid('selectRow', editIndexSubject);
						}
					}
				}
				
				function appendSubject(){
					if (endEditingSubject()){
						$('#subjectDL').datagrid('appendRow',{status:'P'});
						editIndexSubject = $('#subjectDL').datagrid('getRows').length-1;
						$('#subjectDL').datagrid('selectRow', editIndexSubject)
								.datagrid('beginEdit', editIndexSubject);
					}
				}
				
				function removeSubjectRow(){
					if (editIndexSubject == undefined){return;}
					$('#subjectDL').datagrid('cancelEdit', editIndexSubject)
							.datagrid('deleteRow', editIndexSubject);
					editIndexSubject = undefined;
				}
				
				function acceptSubject(){
					if (endEditingSubject()){
						$('#subjectDL').datagrid('acceptChanges');
					}
				}
				
				function subjectValues(){
					var allRows = $('#subjectDL').datagrid('getRows');
					var result = '';
					for(var i =0;i<allRows.length;i++){
						result = result + "," + allRows[i]['subjecName'];
					}
					alert(result);
					return result;
				}
				
				
			</script>
		    
	       	<%-- 报名类型 --%>
	        
	        <%-- 奖项类型 --%>
	        <div class="type-1 type-2"><span class="tit3">奖项类型：</span>
	           	<div style="width:280px;overflow: hidden;">
			       	<table id="rewardDL" class="easyui-datagrid" style="width:280px;height:auto"
						data-options="
							singleSelect: true,
							toolbar: '#tbReward',
							onClickRow: onClickRowReward
						">
						<thead>
							<tr>
								<th data-options="field:'rewardName',width:240,
									editor:{
										type:'validatebox',
										options:{required:true}
									}">奖项类型
								</th>
							</tr>
						</thead>
					</table>
				
					<div id="tbReward" style="height:auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" 
							data-options="iconCls:'icon-add',plain:true" onclick="appendReward()">新增</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" 
							data-options="iconCls:'icon-remove',plain:true" onclick="removeRewardRow()">删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" 
							data-options="iconCls:'icon-save',plain:true" onclick="acceptReward()">确定</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" 
							data-options="iconCls:'icon-save',plain:true" onclick="rewardValues()">show</a>
					</div>
	           	</div>
	       	</div>
	       	
	       	<script type="text/javascript">
				var editIndexReward = undefined;
				
				function endEditingReward(){
					if (editIndexReward == undefined){return true;}
					if ($('#rewardDL').datagrid('validateRow', editIndexReward)){
						
						$('#rewardDL').datagrid('endEdit', editIndexReward);
						var curValue = $('#rewardDL').datagrid('getRows')[editIndexReward]['rewardName'];
						
						if($.trim(curValue)==''){
							$('#rewardDL').datagrid('beginEdit',editIndexReward);
							return false;
						}
						
						var allRows = $('#rewardDL').datagrid('getRows');
						for(var i =0;i<allRows.length;i++){
							if (i!=editIndexReward && curValue== allRows[i]['rewardName']){
								$.messager.alert('名称重复','奖项类型不能重复');
								$('#rewardDL').datagrid('beginEdit',editIndexReward);
								return  false;
							}
						}
						return true;
					} else {
						return false;
					}
				}
				
				function onClickRowReward(index){
					if (editIndexReward != index){
						if (endEditingReward()){
							$('#rewardDL').datagrid('selectRow', index)
									.datagrid('beginEdit', index);
							editIndexSubject = index;
						} else {
							$('#rewardDL').datagrid('selectRow', editIndexReward);
						}
					}
				}
				
				function appendReward(){
					if (endEditingReward()){
						$('#rewardDL').datagrid('appendRow',{status:'P'});
						editIndexReward = $('#rewardDL').datagrid('getRows').length-1;
						$('#rewardDL').datagrid('selectRow', editIndexReward)
								.datagrid('beginEdit', editIndexReward);
					}
				}
				
				function removeRewardRow(){
					if (editIndexReward == undefined){return;}
					$('#rewardDL').datagrid('cancelEdit', editIndexReward)
							.datagrid('deleteRow', editIndexReward);
					editIndexReward = undefined;
				}
				
				function acceptReward(){
					if (endEditingReward()){
						$('#rewardDL').datagrid('acceptChanges');
					}
				}
				
				function rewardValues(){
					var allRows = $('#rewardDL').datagrid('getRows');
					var result = '';
					for(var i =0;i<allRows.length;i++){
						result = result + "," + allRows[i]['rewardName'];
					}
					alert(result);
					return result;
				}
				
			</script>
	       	<%-- 奖项类型 --%>
	       	
	        <div class="upload" >
	           上传附件：<div  class="fieldset flash" style="height: 80px;width:400px;" id="fsUploadProgress"></div>
	           	<div id="divMovieContainer" >
					<span id="spanButtonPlaceHolder"></span>
				</div>
	        </div>
			<div style="clear:both;"></div>
	        <div class="upload">
	           报名简介：<span class="rel"><textarea class="rel"></textarea></span> 
	        </div>
	        
	           
	        <a href="#" class="return1" style="margin-left:170px;">确定</a>
	       	<a href="#" class="return1">取消</a>
	       	
	       	</form>
		</div>
	</div>
	
	<script>
	    $(function () {
	        $(".yes").click(function () {
	            $(".type-2").show();
	        });
	        
	        $(".no").click(function () {
	            $(".type-2").hide();
	        });
	    });

	    function newType(){
            $('#dlgType').dialog('open').dialog('setTitle','报名类型');
            $('#fm').form('clear');
        }

        function saveType(){
			if($.trim($("#subjectName").val()=='')){
				$("#subjectName").focus();
				return;
			}
        }
	</script>
	
	</body>
</html>
