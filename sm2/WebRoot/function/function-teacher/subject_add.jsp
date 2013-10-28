<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
 $(function () {
            $(".add-top img").click(function () {
                $(".bg").css("display", "none");
                $(".add").css("display", "none");
            });
            $(".return").click(function () {
                $(".bg").css("display", "none");
                $(".add").css("display", "none");
            });
            $('#cc').combotree({  
           	 url: 'tree_user.action',
           	 required: true  
           	});  
            
        });
 </script>
 <form id="saveForm" method="post">
	<div class="bg"></div>
      <div class="add" style="height: 450px;">
       <div class="add-top">
           <p>添加事项</p>
           <img src="${prc}/sm2/function/img/erro.jpg"  class="close" style="cursor:pointer;"/>
       </div>
       <div class="add-down">
           <div id="choice">
               <div class="nav11">
               <c:if test="${not empty types}">
               		<p> 类型：<select name="subject.moduleId">
               		<c:forEach items="${types}" var="type">
               			<option value="${type.id}">${type.name}</option>
                   </c:forEach>
                   </select></p>
				</c:if>
                <p>事项名称：
                	<input type="text" id="addSubjectInput" class="input" name="subject.title" value="${subject.title}"/></p>
 					<c:if test="${not empty types1}">
	                  <p>关联重心工作：<select>
	                   <c:forEach items="${types1}" var="obj">
	                   	<option value=""></option>
	                   	<option value="${obj.id }">${obj.name}</option>
	                   	</c:forEach>
	                   </select>
	             	  </p>
                   </c:if>
					<p>　执行者：<select id="cc" class="easyui-combotree" data-options="url:'tree_user.action'" multiple="true" cascadeCheck="false" style="width:200px;"></select></p>
                   <div class="more1">
                       <span>事件详情：</span>
                       <textarea id="textContent"  class="big" name="subject.content" >${subject.content}</textarea>
                   </div>
               </div>
           </div>
           <a href="javascript:matterSubmit();" class="return" style="margin-left:100px;">提交</a>
           <a href="#" class="return">返回</a>
       </div>
          </div>
</form>