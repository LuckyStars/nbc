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
	           	<div class="select1">
	           		<table title="报名类型" id="dg" class="easyui-datagrid" style="width:300px;height:100px"
				            toolbar="#toolbar" pagination="false"
				            rownumbers="false" fitColumns="true" singleSelect="true">
				        <thead>
				        </thead>
				        
				        <tbody>
				        	<tr><td><input name="subjectName" type="hidden" />类型啊</td></tr>
				        </tbody>
				    </table>
				    
				    <div id="dlg" class="easyui-dialog" style="width:240px;height:160px;padding:10px 20px"
				            closed="true" buttons="#dlg-buttons">
				            <div class="fitem">
				                <label>类型名称:</label>
				                <input name="subjectName" class="easyui-validatebox" required="true">
				            </div>
				    </div>
				    
				    <div id="toolbar">
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
				    </div>
				    <div id="dlg-buttons">
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">确定</a>
				        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
				    </div>
	              	<p>
		              	<span class="best">小学高级职称</span>
		              	<span class="dele">修改</span>
		              	<span class="dele">删除</span>
	              	</p>
	               	<p>
	               		<span class="best">小学高级职称</span>
	               		<span class="dele">修改</span>
	               		<span class="dele">删除</span>
	               	</p>
	               	<p>
	               		<span class="best">小学高级职称</span>
	               		<span class="dele">修改</span>
	               		<span class="dele">删除</span>
	               	</p>
	               	<p>
	               		<span class="best">
	               			<input type="text" />
	               		</span>
	               		<span class="dele">修改</span>
	               		<span class="dele">删除</span>
	               	</p>
	               	
	           	</div>
	        	<a class="cx4" href="#">增加报名信息</a>
	       	</div>
		    
	       	
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

	    function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','New User');
            $('#fm').form('clear');
        }
	</script>
	
	</body>
</html>
