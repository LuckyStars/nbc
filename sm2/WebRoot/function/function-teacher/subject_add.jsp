<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="${prc}/sm2/function/css/index.css" type="text/css"></link>
<script type="text/javascript">
 $(function () {
       $(".add-top img").click(function () {
           $(".bg").css("display", "none");
           $(".add").css("display", "none");
       });
       $('#cc').combotree({  
      		url: 'tree_user.action',
     		multiple:true
      	 //required: true  
      	});  
       $('#master').combotree({  
         	 url: 'findAllMaster_user.action',
         	 multiple:true
         //	 required: true  
       });  
   });
 </script>
 <form id="saveForm" method="post">
 	<input type="hidden" name="subject.moduleId" value="${moduleId }"/>
      <div class="add" style="height: 450px;">
       <div class="add-top">
           <p>添加事项</p>
           <img src="${prc}/sm2/function/img/erro.jpg"  class="close" style="cursor:pointer;"/>
       </div>
       <div class="add-down">
           <div id="choice">
               <div class="nav11">
               <c:if test="${not empty types}">
               		<p style="margin:5px 5px 5px 25px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型：
	               		<select name="subject.typeId"  onchange="search(this);">
		               		<c:forEach items="${types}" var="type">
		               			<option value="${type.id}">${type.name}</option>
		                   </c:forEach>
	                   </select>
                   </p>
				</c:if>
                <p style="margin:5px 5px 5px 25px; ">事项标题：<input type="text" id="addSubjectInput" class="input" name="subject.title" maxlength="20"/></p>
 					<c:if test="${not empty subjects}">
	                  <p>关联重心工作：<select name="subject.parentId" id="parent">
	                   <c:forEach items="${subjects}" var="obj">
	                   	<option value="${obj.id }">${obj.title}</option>
	                   	</c:forEach>
	                   </select>
	             	  </p>
                   </c:if>
					<p style="margin:5px 5px 5px 25px; ">　执行者：<select id="cc" class="easyui-combotree" cascadeCheck="false" style="width:200px;"></select></p>
                    <p style="margin:5px 5px 5px 25px; ">　审批者：<select id="master" class="easyui-combotree" cascadeCheck="false" style="width:200px;"></select></p>
                   <div class="more1">
                       <span>事件详情：</span>
                       <textarea id="textContent"  class="big" name="subject.content" ></textarea>
                   </div>
               </div>
           </div>
           <a href="javascript:matterSubmit();" class="return" style="margin-left:100px;">提交</a>
           <a href="javascript:back();" class="return">返回</a>
       </div>
          </div>
</form>