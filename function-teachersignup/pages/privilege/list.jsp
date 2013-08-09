<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>权限管理</title>
<link href="${prc }/function/function-teachersignup/easyui/themes/metro/easyui.css" rel="stylesheet" type="text/css" />
<link href="${prc }/function/function-teachersignup/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc }/function/function-teachersignup/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${prc }/function/function-teachersignup/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div class="con_conent fixed">
	     <h1 class="title">
			<span class="title">权限管理</span>
			<span class="back"><a href="#">返回上一页</a></span>
		 </h1>
	        <div class="table_box fixed">
	            <div class="box">
	            <p class="join">管理员列表</p>
	   
				<table width="448px" border="0" class="table-xi">
					<tr>
						<th width="50%" scope="col">姓名</th>
						<th width="50%" scope="col">操作</th>
					</tr>
					<c:if test="${not empty pm.datas}">
					<c:forEach var="userPri" items="${pm.datas}">
					<tr>
						<td>${userPri.userName }</td>
						<td>
							<a href="javascript:deletePri('${userPri.id }');">删除</a>
						</td>
					</tr>
					</c:forEach>
					</c:if>
				</table>
				<a href="#" id="popAdd" class="return" style="margin-left:124px;">新增</a>
				<a href="#" class="return">返回</a>
				</div>
			</div>
	</div>
    <!--弹出层-->
    <div id="pop" style="display: none;">
	<div class="bg"  ></div>
    <div class="add1" style="height:140px;" >
       	<div class="add-top1" >
           	<p>补卡类型维护</p>
           	<img src="${prc }/function/function-teachersignup/image/erro.jpg"  class="close" style="cursor:pointer;"/>
       	</div>
       	<div class="add-down">
			<div class="under">
              	<b>请选择:</b><input type="text" id="userTree" name="userId" style="width: 150px;"/>
               	<div style="color: red;" id="errMsg"></div>
               	<a href="javascript:comfirmSub();" class="return" style="margin-left:30px;">确定</a>
           		<a href="#" class="return" onclick="$('#pop').hide();">取消</a>
          	</div>
    	</div>
	</div>
	</div>
	<!--弹出层 END-->
	<form action="${prc}/teachersignup/add_userPri.action" name="userPriForm" >
		<input type="hidden" name="userUids" />
	</form>
	
<script type="text/javascript">
   	$(function () {
       	$(".btnright").click(function () {
           	$(".bg").css("display", "block");
           	$(".add1").css("display", "block");
       	});
       	$(".add-top1 img").click(function () {
       		$("#pop").hide();
       	});
       	
       	$("#popAdd").click(function(){
			
			
			$("#userTree").combotree({
				 url:'${prc}/teachersignup/tree_userPri.action',
			     multiple:true,
			     cascadeCheck:true,
			     onLoadSuccess:function(){
			    		var node = $('#userTree').combotree('tree').tree('getSelected');
						if (node){
							$('#userTree').combotree('tree').tree('collapseAll', node.target);
						} else {
							$('#userTree').combotree('tree').tree('collapseAll');
						}
				 }
			});

			$("#pop").show();
			
       	});
       	
   	});

   	function deletePri(id){
		if(confirm('确定要删除吗?')){
			location.href="${prc}/teachersignup/remove_userPri.action?id=" + id;
		}
   	}

   	function comfirmSub(){
   		var nodes = $('#userTree').combotree('tree').tree('getChecked');
   		var noChecked = true;
   		if(nodes){
   	   		var idStr = "";
			for(var i = 0;i<nodes.length;i++){
				if(nodes[i].id.indexOf('|')<0){
					idStr = idStr + nodes[i].id + ",";
					noChecked = false;
	   	   	   	}
			}
			if(noChecked){
				alert("请选择人员!");
				return;
			}else{
				$("input[name='userUids']").val(idStr);
				document.userPriForm.submit();
				return ;
			}
   	   	}
   	   	alert("请选择人员!");   	   	
   	}
</script>

</body>
</html>
