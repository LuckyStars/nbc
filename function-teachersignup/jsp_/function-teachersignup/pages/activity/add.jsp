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
				报名名称：<span><input type="text"  width="120px"/></span>
			</p>
	       	<div class="data">
	       		<p class="begin">报名起始日期：
	       		<span>
	       			<input type="text"  width="120px" height="20px"/>
	       			<img src="${prc }/function/function-teachersignup/image/data.jpg" />
	       		</span>
	       		</p>
	       		<p class="end">报名结束日期：
	       			<span>
	       				<input type="text"  width="120px" height="20px"/>
	       				<img src="${prc }/function/function-teachersignup/image/data.jpg" />
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
					return result;
				}
				
			</script>
		    
	       	<%-- 报名类型 --%>
	       	
	        <p class="awards">是否追加获奖情况：
	        	<span><input type="radio" class="yes"/>是</span>
	        	<span><input type="radio"  class="no"/>否</span>
	        </p>
	        
	        <div class="type-1 type-2"><span class="tit3">奖项类型：</span>
	           	<div class="select1">
	              	<p>
	              		<span class="best">特等奖</span>
	              		<span class="dele">修改</span>
	              		<span class="dele">删除</span>
	              	</p>
	               	<p>
	               		<span class="best">一等奖</span>
	               		<span class="dele">修改</span>
	               		<span class="dele">删除</span>
	               	</p>
	               	<p>
	               		<span class="best">二等奖</span>
	               		<span class="dele">修改</span>
	               		<span class="dele">删除</span>
	               	</p>
	                <p>
	                	<span class="best">三等奖</span>
	                	<span class="dele">修改</span>
	                	<span class="dele">删除</span>
	                </p>
	               	<p>
	               		<span class="best"><input type="text" /></span>
	               		<span class="dele">修改</span>
	               		<span class="dele">删除</span>
					</p>
	           	</div>
	            <a class="cx4" href="#">增加奖项类型</a>
	       	</div>
	        <div class="upload">
	           上传附件：<input type="file" name="atta" /> 
	        </div>
			<div style="clear:both;"></div>
	        <div class="upload">
	           报名简介：<span class="rel"><input type="text" /></span> 
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
