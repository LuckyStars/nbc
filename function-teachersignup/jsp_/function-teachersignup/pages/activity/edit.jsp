<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>编辑报名事件</title>
		<link href="${prc }/function/function-teachersignup/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${prc }/function/function-teachersignup/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/datePicker/WdatePicker.js"></script>
		<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${prc}/common/agent.js"></script>
		<link type="text/css" href="${prc}/function/function-teachersignup/swfupload/css/default.css" rel="stylesheet"/>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/swfupload.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/fileprogress.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/handlers.js"></script>
		<script type="text/javascript" src="${prc}/function/function-teachersignup/swfupload/js/swfupload.queue.js"></script>
		
		<script type="text/javascript">

		var swfu;
		window.onload = function() {
			var settings = {
				flash_url : "${prc}/function/function-teachersignup/swfupload/js/swfupload.swf",
				upload_url: "${prc }/teachersignup/add_act.action",	
				file_post_name: "atta",   
				file_size_limit : "10 MB",
				file_types : "*.*",
				file_types_description : "支持文件格式",
				file_upload_limit : 1,
				file_queue_limit : 0,
				custom_settings : {
					progressTarget : "fsUploadProgress",
					cancelButtonId : "divMovieContainer"
				},
				debug: false,
				// Button settings
				button_image_url: "${prc}/function/function-teachersignup/swfupload/images/button_2.jpg",
				button_width: "64",
				button_height: "23",
				button_placeholder_id: "spanButtonPlaceHolder",
				button_text: '<span class="theFont">浏览</span>',
				button_text_style: ".theFont { font-size: 12;text-align:center;color:#ffffff;}",
				button_text_top_padding: 2,
				// The event handler functions are defined in handlers.js
			    file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : upFail,
				upload_success_handler : upSuc,
				queue_complete_handler : queueComplete,
				use_query_string : true
			};
			swfu = new SWFUpload(settings);
		};

		function upSuc(){
			$.messager.alert('上传成功','上传成功!');
			location.href = "${prc}/teachersignup/adminList_act.action";
		}

		function upFail(){
			$.messager.alert('上传失败','上传失败!');
			location.href = "${prc}/teachersignup/adminList_act.action";
		}
		
		function submitForm(){//提交表单
			if($.trim($("#actName").val())==''){
				$("#actName").focus();
				return false;
			}
			if($.trim($("#actName").val()).length>20){
				$.messager.alert('报名名称','报名名称不能超过20个字符!');
			}

			if($("input[name='act.openDate']").val()==''||$("input[name='act.endDate']").val()==''){
				$.messager.alert('报名时间','请选择报名时间!');
				$("input[name='act.openDate']").focus();
				return false;
			}
			if(endEditingSubject()){
				$("input[name='subjectName']").val(subjectValues());
			}else{
				return false;
			}
			if($("input[name='subjectName']").val()==''){
				$.messager.alert('报名类型','请至少填写一种报名类型!');
				return false;
			}
			if(endEditingReward()){
				$("input[name='rewardName']").val(rewardValues());
			}else{
				return false;
			}

			if($("textarea[name='act.comment']").val().length>200){
				$.messager.alert('报名简介','报名简介不能超过200个字!');
				return false;
			}
			alert(swfu.getFile(0)==null);
			if(swfu.getFile(0)==null){
				var postForm = $("<form action='${prc}/teachersignup/add_act.action' method='post'></form>");
				postForm.append("<input type='hidden' name='act.name' value='" + encodeURI($("input[name='act.name']").val()) + "' />");
				postForm.append("<input type='hidden' name='act.openDate' value='" + encodeURI($("input[name='act.openDate']").val()) + "' />");
				postForm.append("<input type='hidden' name='act.endDate' value='" + encodeURI($("input[name='act.endDate']").val()) + "' />");
				postForm.append("<input type='hidden' name='subjectName' value='" + encodeURI($("input[name='subjectName']").val()) + "' />");
				postForm.append("<input type='hidden' name='rewardName' value='" + encodeURI($("input[name='rewardName']").val()) + "' />");
				postForm.append("<input type='hidden' name='act.comment' value='" + encodeURI($("textarea[name='act.comment']").val()) + "' />");
				postForm.append("<input type='hidden' name='act.id' value='${act.id}' />");
				postForm.appendTo(document.body).submit();
				
				return false;
			}
			swfu.addPostParam("act.name", encodeURI($("#actName").val()));
			swfu.addPostParam("act.openDate", encodeURI($("input[name='act.name']").val()));
			swfu.addPostParam("act.endDate", encodeURI($("input[name='act.endDate']").val()));
			swfu.addPostParam("subjectName", encodeURI($("input[name='subjectName']").val()));
			swfu.addPostParam("rewardName", encodeURI($("input[name='rewardName']").val()));
			swfu.addPostParam("act.comment", encodeURI($("textarea[name='act.comment']").val()));
			swfu.addPostParam("act.id", '${act.id}');
			swfu.startUpload();
			return false;
		}
		
		</script>
	</head>
	<body>
	<div class="con_conent fixed">
		<h1 class="title">
			<span class="title">教师报名</span>
			<span class="back"><a href="${prc }/teachersignup/adminList_act.action">返回上一页</a></span>
		</h1>
	    <div class="table_box fixed">
			<p class="apply">
				报名名称：
				<span>
					<input id="actName" name="act.name" value="${act.name}"
					 class="easyui-validatebox" data-options="required:true" type="text" />
				</span>
			</p>
	       	<div class="data">
	       		<p class="begin">报名起始日期：
	       		<span>
	       			<input name="act.openDate" class="Wdate" type="text" id="openDate"
	       			value="<fmt:formatDate value="${act.openDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onfocus="WdatePicker({onpicked:function(){endDate.focus();},dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\');}',isShowClear:false})"
					readonly="readonly"
					 />
	       		</span>
	       		</p>
	       		<p class="end">报名结束日期：
	       			<span>
	       				<input name="act.endDate" class="Wdate" type="text" id="endDate"
	       				value="<fmt:formatDate value="${act.endDate }" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
					<c:if test="${not empty act.subjects}">
					,data:[
						<c:forEach items="${act.subjects}" var="sub" varStatus="i">
							{'subjectName':'${sub.name}'},
						</c:forEach>
						]
					</c:if>"
				>
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
							$.messager.alert('名称为空','报名类型不能为空');
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
						$('#subjectDL').datagrid('beginEdit',editIndexSubject);
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
						result = result + "," + allRows[i]['subjectName'];
					}
					return result;
				}
				
				
			</script>
		    
	       	<%-- 报名类型 END --%>
	        
	        <%-- 奖项类型 --%>
	        <div class="type-1 type-2"><span class="tit3">奖项类型：</span>
	           	<div style="width:280px;overflow: hidden;">
			       	<table id="rewardDL" class="easyui-datagrid" style="width:280px;height:auto"
						data-options="
							singleSelect: true,
							toolbar: '#tbReward',
							onClickRow: onClickRowReward
							<c:if test="${not empty act.rewards}">
							,data:[
								<c:forEach items="${act.rewards}" var="rew" varStatus="i">
									{'rewardName':'${rew.name}'},
								</c:forEach>
								]
							</c:if>"
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
							$.messager.alert('名称为空','奖项类型不能为空');
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
						$('#rewardDL').datagrid('beginEdit',editIndexReward);
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
					return result;
				}
				
			</script>
	       	<%-- 奖项类型 --%>
	       	
	       	<%-- 附件 --%>
		
		        <div class="upload">
		   			<div class="plug_in borders fl" style="position:absolue;z-index:0;" >
	                    <div >
	                    	<div style="margin:0 auto;overflow-y:auto;overflow-x:hidden;">
					       		<div class="fieldset flash" id="fsUploadProgress"></div>
								<div id="divMovieContainer"></div>
					       	</div>
	                    </div>
						<span id="spanButtonPlaceHolder" class="alet_btn"></span>
						<p class="gray f12 mt5" style="margin-top:10px;color: #336699;" id="divStatus">支持的文件最大容量： 10MB(只可上传一个文件) </p>
					</div>
	           	</div>
	        </div>
	       	
	        <%-- 附件 --%>
			<div style="clear:both;"></div>
			
	      	<div class="type-1 type-2" ><span class="tit3"> 报名简介：<br/>(200字以内)</span>
	            <span class="rel">
	            <textarea  name="act.comment" class="rel" cols="60" rows="8">${act.comment}</textarea></span> 
	        </div>
	        
	        <input type="hidden" name="subjectName"/>
	       	<input type="hidden" name="rewardName"/>
	       	
	        <a href="javascript:submitForm();" onclick="" class="return1" style="margin-left:170px;">确定</a>
	       	<a href="#" class="return1">取消</a>
	       	
	</div>
	</body>
</html>
