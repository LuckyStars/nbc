<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/jquery.form.js"></script>
<script type="text/javascript" src="${prc}/function/js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="http://127.0.0.1:8080/schoolapp/common/agent.js"></script>
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
            $(".add-top img").click(function () {
                $(".bg").css("display", "none");
                $("#add").css("display", "none");
            });
        });
       
        //查询
        function matteredit(obj,m){
        	$.post("toUpdate_subject.action?id="+obj+"&moduleId="+m,function(data){
        		if(data != ''){
        			$("#addsubjectDiv").html(data);
        			$(".bg").css("display", "block");
                    $(".add").css("display", "block");
        		}
      	  });
        }
      
    	function on_delete(subjectId){
        	$.post("isExist_step.action",{sujectId:subjectId},
				function(data){
					if(data==0){
		        		if(confirm("确定要删除吗?")){
		        			$.post("delete_subject.action", { id: subjectId},
		        				function(data){
		        					if(0==data){
		        						document.forms[0].submit();
		        					}else{
		        						alert("删除失败");
		        					}
		        			});
		        		}
					}else{
						alert("主题下存在步骤，请先删除步骤！");
					}
        		});
    
    	}
    function matterQuery(){
    	$("#form").submit();
    }
    function look(id){
    	document.location.href="${prc}/scMaster2/detail_master.action?id="+id;
    	sethash();
    }
</script>
</head>
<body>
<form action="${prc}/scMaster2/findAllTrans_subject.action" id="form" method="post">
<input type="hidden" name="subjectVo.moduleId" value="${subjectVo.moduleId}">
<input type="hidden" name="subjectVo.moduleName" value="${subjectVo.moduleName}">
<div class="con_conent fixed">
     <h1 class="title"><span class="title">当前位置：</span><span class="text"><a href="${prc}/scMaster2/teacherInput_index.action">首页</a>　-　</span><span class="back">转发事项</span></h1>
        <div class="table_box fixed">
            <div class="nav">
                <span>事项标题:</span>
                <input type="text"  name="subjectVo.title" value="${ subjectVo.title}"/>
                <a class="cx" href="javascript:matterQuery();">查询</a>
            </div>
        <table width="100%" border="0">
            <tr>
                <th width="5%" scope="col">序号</th>
                <th width="35%" scope="col">标题</th>
                <th width="15%" scope="col">创建日期</th>
                <th width="10%" scope="col">创建人</th>
                <th width="15%" scope="col">所属部门</th>
                <th width="20%" scope="col">操作</th>
            </tr>
            <c:forEach items="${pm.datas}" var="sub" varStatus="i">
            <tr>
                <td align="center" <c:if test="${sub.status==0}">style="font-weight: bold;"</c:if>>${i.index+1 }</td>
                <td align="center" <c:if test="${sub.status==0}">style="font-weight: bold;"</c:if>>
                	<a href="javascript:look('${sub.id }');">${sub.title }</a></td>
                <td align="center" <c:if test="${sub.status==0}">style="font-weight: bold;"</c:if>><fmt:formatDate value="${sub.createTime}" pattern="yyyy-MM-dd"/></td>
                <td align="center" <c:if test="${sub.status==0}">style="font-weight: bold;"</c:if>> ${sub.createrName}</td>
                <td align="center" <c:if test="${sub.status==0}">style="font-weight: bold;"</c:if>>${sub.departmentName}</td>
                <td align="center">
                <span class="space"><a href="javascript:look('${sub.id }');">查看</a></span>
                </td>
            </tr>
            </c:forEach>
        </table>
    	<c:if test="${pm.total>0}">
    		   <div  style="text-align:center;font-size:15px;margin-top:20px;">
        		<pg:pager url="${prc}/scMaster2/findAllTrans_subject.action"
					items="${pm.totalPageNo}" maxPageItems="${pm.pagesize}" maxIndexPages="3" export="currentPageNumber=pageNumber">
				<pg:param name="subjectVo.moduleId" value="${subjectVo.moduleId}"/>
				<pg:param name="subjectVo.moduleName" value="${subjectVo.moduleName}"/>
				<pg:param name="subjectVo.title" value="${subjectVo.title}"/>
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
			</div>
		</c:if> 
		</div>
    </div>
    </form>
</body>
</html>