<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sns" uri="/sns-tags" %> 
<%@ include file="/function/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>列表</title>
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/css/gzt.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="${prc}/function/css/index.css" type="text/css"></link>
<script type="text/javascript">var APP_PATH = "${prc}";</script>
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${appContext.skinPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${appContext.skinPath}/js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript">
        $(function () {
            $("table tr:odd").css("background", "#f0f8fc");
            $("table tr:even").css("background", "#d5e0ee");
            $("table tr").mouseover(function () {
                $(this).css("color","#f00");
            });
            $("table tr").mouseleave(function () {
                $(this).css("color", "#000");
            });
            $(".cx1").click(function(){
            	$.post("getByIdWorkbench.action",{module : "${param.module}" },function(data){
            		if(data != ''){
            			$("#addsubjectDiv").html(data);
            			$(".bg").css("display", "block");
                        $(".add").css("display", "block");
            		}
          	  });
               
            });
            $("#edit").click(function(){
            	$.post("getByIdWorkbench.action",function(data){
            		if(data != ''){
            			$("#addsubjectDiv").html(data);
            			$(".bg").css("display", "block");
                        $(".add").css("display", "block");
            		}
          	  });
            });
            
            $(".add-top img").click(function () {
                $(".bg").css("display", "none");
                $("#add").css("display", "none");
            });
        });
       
        //查询
        function matteredit(obj){
        	$.post("getByIdWorkbench.action?id="+obj,function(data){
        		if(data != ''){
        			$("#addsubjectDiv").html(data);
        			$(".bg").css("display", "block");
                    $(".add").css("display", "block");
        		}
      	  });
        }
      
        //异步新建提交
    	function matterSubmit(){
    		if(!doValid()){return;};
    		isSubjectExist();
    	}	
    	function on_delete(subjectId){
    		if(confirm("确定要删除吗?")){
    			$.post("deleteWorkbench.action", { id: subjectId},
    				function(data){
    					var dateObj=$.parseJSON(data);
    					if(0==dateObj.result){
    						document.location.href="showSubjectWorkbench.action?module=${param.module}";
    					}else{
    						alert("删除失败");
    					}
    			});
    		}
    	}
    	
    	//验证主题重名
    	function isSubjectExist(){
    		var url ="isExistWorkbench.action";
    		var subjectTile = $("input[name='subject.title']").val();
    		$.post(url,{subjectTile:subjectTile},function(data){
    				if(data.result == 0){
    						doSave();
    				}else{
    					alert(data.msg);
    				};
    			},"json");
    	}
    	
    	function doSave(){
        	var users = $("#cc").combotree('getValues');
    		$("#saveForm").ajaxSubmit({
    			url:"addSubjectWorkbench.action",
    			data: {executeUsers:$("#cc").combotree('getText'),executeUsersId:users.toString()},
    			success:function(data){
    				var dateObj=$.parseJSON(data);
    				alert((0==dateObj.result)?"成功!":"失败!");
    				document.location.href="showSubjectWorkbench.action?module=${param.module}";
    			}
    		});
    	}
    function matterQuery(){
    	$("#form").submit();
    }
</script>
</head>
<body>
<form action="searchSubjectWorkbench.action" id="form" method="post">
<div class="con_conent fixed">
     <h1 class="title"><span class="title">当前位置：</span><span class="text">首页　-　<a href="${appContext.appPath}/scMaster2/teacherInput_index.action">校长工作台</a>　-　</span><span class="back">临时事项</span></h1>
        <div class="table_box fixed">
            <div class="nav">
                <span>提交日期:</span>
                <select id=""></select>
                <span>事项标题:</span>
                <input type="text" id="" name="subject.title" />
                <a class="cx" href="javascript:matterQuery();">查询</a>
                <a class="cx1" href="javascript:void(0);">增加</a>
            </div>
        <table width="92%" border="0">
            <tr>
                <th width="5%" scope="col">序号</th>
                <th width="30%" scope="col">标题</th>
                <th width="20%" scope="col">创建日期</th>
                <th width="10%" scope="col">创建人</th>
                <th width="15%" scope="col">所属部门</th>
                <th width="20%" scope="col">操作</th>
            </tr>
            <c:forEach items="${page.datas}" var="sub" varStatus="i">
            <tr>
                <td align="center">${i.index+1 }</td>
                <td align="center">${sub.title }</td>
                <td align="center">${sub.createTime }</td>
                <td align="center">${sub.createrName}</td>
                <td align="center">${sub.departmentName }</td>
                <td align="center">
                <span class="space"><a href="reportDetailedWorkbench.action?id=${sub.id }">查看</a></span>
                <span class="space" id="cx2"><a href="javascript:matteredit('${sub.id }')" id="edit">编辑</a>
                </span><span class="space""><a href="javascript:void(0);" onclick="on_delete('${sub.id}')">删除</a></span></td>
            </tr>
            </c:forEach>
        </table>
         <c:if test="${page.count > 0}">
    		<jsp:include page="/pages/sjoa/page.jsp" flush="true">
				<jsp:param name="urlAction" value="showSubjectWorkbench.action" />
				<jsp:param name="page" value="page" />
			</jsp:include>
		</c:if> 
    </div>
</div>
           <!--弹出层-->
    <div class="bg"></div>
      <div id="addsubjectDiv" >
     
       </div>
     </form>
     <script>
      function doValid(){
		 if($("#addSubjectInput").val()==''){
			 alert("请添加主题");
				return false;
		 }
    	  var text = $("#textContent").val();
    	  if(text.length==0){
    		  alert("请填写简介！");
    		  return false;
    	  }
    	  return true;
      }
 
   </script>
</body>
</html>