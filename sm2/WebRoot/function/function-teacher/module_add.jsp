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
           	 url: 'treeWorkbench.action',
           	 required: true  
           	});  
            
        });
 </script>
 <form id="saveForm" method="post">
<input name="module.id" type="hidden" value="${module.id }" />
<input name="module.parentId" type="hidden" value="tjfx" />
	<div class="bg"></div>
      <div class="add" style="height: 450px;">
       <div class="add-top">
           <p>添加事项</p>
           <img src="${appContext.skinPath}/img/erro.jpg"  class="close" style="cursor:pointer;"/>
       </div>
       <div class="add-down">
           <div id="choice">
               <div class="nav11">
                   <p>事项名称：<input type="text" id="addSubjectInput" class="input" name="module.name" value="${module.name }"/></p>
                  
				<p>　执行者：<select id="cc" class="easyui-combotree" data-options="url:'treeWorkbench.action'" multiple="true" cascadeCheck="false" style="width:200px;"></select></p>
                   <div class="more1">
                       <span>事件详情：</span>
                       <textarea id="textContent"  class="big" name="module.discription" >${module.discription }</textarea>
                   </div>
               </div>
           </div>
           <a href="javascript:matterSubmit();" class="return" style="margin-left:100px;">提交</a>
           <a href="#" class="return">返回</a>
       </div>
          </div>
</form>