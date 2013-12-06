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
           	// required: true  
           	});  
            $('#master').combotree({  
             	 url: 'findAllMaster_user.action',
             //	 required: true  
            }); 
            var a = new Array();
            <c:forEach items="${subject.excuteUsers}" var="type">
   				a.push('${type.userId }');
      		 </c:forEach>
      	    
            $('#cc').combotree('setValues', a);
            var b = new Array();
            <c:forEach items="${subject.checkUsers}" var="type">
   				b.push('${type.userUid }');
      		 </c:forEach>
      	    $('#master').combotree('setValues', b);
            
        });
 </script>
 <form id="saveForm" method="post">
 	<input type="hidden" name="subject.id" value="${id}"/>
 		<input type="hidden" name="subject.moduleId" value="${moduleId }"/>
	<div class="bg"></div>
      <div class="add" style="height: 450px;">
       <div class="add-top">
           <p>编辑事项</p>
           <img src="${prc}/sm2/function/img/erro.jpg"  class="close" style="cursor:pointer;"/>
       </div>
       <div class="add-down">
           <div id="choice">
               <div class="nav11">
               <c:if test="${not empty types}">
               		<p style="margin:5px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型：<select name="subject.typeId">
               		<c:forEach items="${types}" var="type">
               			<option value="${type.id}">${type.name}</option>
                   </c:forEach>
                   </select></p>
				</c:if>
                <p style="margin:5px;">事项名称：
                	<input type="text" id="addSubjectInput" class="input" name="subject.title" value="${subject.title}"/></p>
 					<c:if test="${not empty subjects}">
	                  <p>关联重心工作：<select name="subject.parentId">
	                   <c:forEach items="${subjects}" var="obj">
	                   	<option value="${obj.id }">${obj.title}</option>
	                   	</c:forEach>
	                   </select>
	             	  </p>
                   </c:if>
					<p style="margin:5px;">执行者：<select id="cc" class="easyui-combotree" data-options="url:'tree_user.action'" multiple="true" cascadeCheck="false" style="width:200px;"></select></p>
                    <p style="margin:5px;">审批者：<select id="master" class="easyui-combotree" data-options="url:'findAllMaster_user.action'" multiple="true" cascadeCheck="false" style="width:200px;"></select></p>
                   <div class="more1">
                       <span>事件详情：</span>
                       <textarea id="textContent"  class="big" name="subject.content" >${subject.content}</textarea>
                   </div>
               </div>
           </div>
           <a href="javascript:updateSubmit();" class="return" style="margin-left:100px;">提交</a>
           <a href="#" class="return">返回</a>
       </div>
          </div>
</form>