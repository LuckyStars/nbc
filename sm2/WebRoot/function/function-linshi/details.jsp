<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title>临时事项详细</title>
	
	<link href="${prc}/function/function-linshi/css/index.css" rel="stylesheet" type="text/css" />
	<link href="${prc}/function/function-linshi/css/jqui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />
	<link href="${prc}/function/function-linshi/css/gzt.css" rel="stylesheet" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/jqui.js"></script>
	<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/kindeditor-min.js" ></script>
	<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
	
	<style>
        .ui-widget-content {
        width:230px; height:10px; background:#EA605E
        }
        .ui-slider .ui-slider-handle {
        width:15px; height:15px; top:-4px;
        }
        .ui-widget-header {
        background:#D2DFE8
        }
        .content h2 p {
         margin-left:100px;
        }
        .tabs li{
        	padding:0 0 0 15px;
        }
        .tabs li img{
        	float:right;position:relative; padding-left: 5px;
        	width:16px;
        	height:14px;
        }
    </style>
    
	<script type="text/javascript">
	KindEditor.ready(function(K) {
		var contentOptions = {
			resizeType : 1,
			width: 416,
			height : 320,
			pasteType:1,
			filterMode:true,
		//	uploadJson : '${prc}/cadreManual/cyclesAction!uploadImage.action',
			allowFileManager : false,
			items : ['preview','print', 'cut', 'copy', 'paste', 'selectall',
			         '|', 'justifyleft', 'justifycenter','justifyright', 'justifyfull', 
			         'insertorderedlist','insertunorderedlist', '|', 'formatblock', 'fontname',
					'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', '|',  'table', 'hr'],
			filterMode : true,
			urlType : "",
			afterChange : function() {
				var self = this;
				self.sync();
			}
		};
		K.create('textarea[name="progress.content"]', contentOptions);
 	 });
	 $(function () {
         $(".resources li").click(function () {
             $(".resources .cur").removeClass("cur");
             var index = $(this).index();
             if (index == 0) {
                 $(".resources").attr("class", "resources doc");
             } else if (index == 1) {
                 $(".resources").attr("class", "resources pic");
             } else if (index == 2) {
                 $(".resources").attr("class", "resources video");
             }
             $(".resource-lists").hide().eq(index).show();
             $(this).addClass("cur");
         });
         $(".ico2").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds").show();
         });
         $(".shou").click(function () {//赞
             //$("body").css("overflow", "hidden");
             //$(".bg").show();
             //$(".adds1").show();
        	 window.frames["postFrame"].showZan();
         });
         
         $(".ico1").click(function () {
             //$("body").css("overflow", "hidden");
             //$(".bg").show();
             //$(".adds2").show();
             window.frames["postFrame"].showRead();
         });
         
         $(".ico7").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds4").show();
             $('#trans').tree({
 				checkbox: true,
 				url: 'tree_user.action',
 				onClick:function(node){
 					$(this).tree('toggle', node.target);
 				},
 				onContextMenu: function(e, node){
 				}
 			});
              
         });
         $(".ico8").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds3").show();
         });
         $(".ico5").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds5").show();
         });
         $(".ico4").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
	         
             $(".adds6").show();
         });
         $(".addtabs").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds7").show();
         });
         $(".close").click(function () {
             $("body").css("overflow", "auto");
             $(".bg").hide();
             $(".adds").hide();
             $(".adds1").hide();
             $(".adds2").hide();
             $(".adds3").hide();
             $(".adds4").hide();
             $(".adds5").hide();
             $(".adds6").hide();
             $(".adds7").hide();
         });
       
         //步骤操作
         $("#stepSave").click(function(){
        	 var name = $.trim($("input[name='step.name']").val());
        	 if(name.length>0){
	            $.post("isExistStep_master.action",{name:name},function(data1){
	            	if(data1==0){
			         	var formParams = $("#stepForm").serialize();
			    		$.post("addStep_master.action", formParams, function(data) {
			      				$(".tabs-wp ul").append('<li id="'+data+'" class="blocksTab cur"><a href="javascript:changeTab('+data+');">'+name+'</a>'+
					      				'<img src="${prc}/function/function-linshi/img/errotab.png" /></li>');
			      				$("input[name='step.name']").val("");
				   				 $(".bg").hide();
				   				 $(".adds7").hide();
			      				changeTab(data);
			     			});
	            	}else{
						alert("存在相同步骤！");
	            	}
		          });
	          }else{
					alert("请填写步骤名称！");
	          }
         	});
      	$(".tabs li img").click(function(){
				var id = $(this).attr("name");
				$.post("deleteStep_master.action",{id:id},function(data){
						if(data==0){
							$("#"+id).remove();
						}else{
							alert("删除失败！");
						}
					});
          	});
      	//步骤结束
      	//工作进展
      	 $("#progressSave").click(function(){
        	 var name = $.trim($("input[name='progress.name']").val());
        	 if(name.length>0){
	            $.post("isExist_progress.action",{name:name},function(data1){
	            	if(data1==0){
			         	var formParams = $("#progressForm").serialize();
			    		$.post("add_progress.action", formParams, function(data) {
			      				$("input[name='progress.name']").val("");
			      				$("textarea[name='progress.content']").val("");
				   				 $(".bg").hide();
				   				 $(".adds6").hide();
			      				changeTab(jQuery("#select1  option:selected").val());
			     			});
	            	}else{
						alert("存在相同工作进展！");
	            	}
		          });
	          }else{
					alert("请填写工作进展名称！");
	          }
         	});
      	//转发
    	 $("#transSave").click(function(){
 			var nodes = $('#trans').tree('getChecked');
			var s = '';
			var n = '';
			for(var i=0; i<nodes.length; i++){
				var b = $('#tt2').tree('isLeaf', nodes[i].target);
				if(b){
					if (s != '') s += ',';
					s += nodes[i].id;
					n += nodes[i].text;
				}
			}
        	 if(s.length>0){
	         	var content = $("textarea[name='trans.content']").val();
	    		$.post("add_trans.action",{transUids:s,transNames:n,content:content,subjectId: $("input[name='subjectId']").val()}, function(data) {
	      				$("textarea[name='trans.content']").val("");
		   				 $(".bg").hide();
		   				 $(".adds4").hide();
		   				 alert("发送成功！");
	     			});
        	 }else{ alert("请选择转发人！");}
         	});
	  });
	  function select(select){
      	if (select) {  
        	$(".tree-checkbox.tree-checkbox0").removeClass("tree-checkbox0").addClass("tree-checkbox1");
      	} else {  
      		$(".tree-checkbox.tree-checkbox0").removeClass("tree-checkbox1").addClass("tree-checkbox0");
      	}  
       }
	</script>
	<script>
	</script>
</head>
<body>
	<input type="hidden" name="subjectId" value="${subject.id}"/>
	<div class="con_conent fixed">
		<h1 class="title">
			<span class="title">当前位置：</span>
			<span class="text">首页 - 校长工作台 - </span>
			<span class="back">临时事项</span>
		</h1>
		<div class="table_box fixed">
			<div class="content">
				<h2>
					<span>${subject.title }</span>
					<div style="float: left; margin-top: 15px; margin-left: 20px;">
						<p style="">
							<label for="amount"></label> 
							<input disabled="disabled" type="text" id="amount" style="border: 0; color: #EA605E; font-weight: bold; background-color: #f0f8fc;" />
						</p>
						<div id="slider-range-max"></div>
					</div>
					<%-- --%>
					<pri:showWhenManager>
					<img id="flagImg" src="${prc}/function/function-linshi/img/qi2.png" width="23" height="30" />
					<img src="${prc}/function/function-linshi/img/qi3.png" width="23" height="30" />
					</pri:showWhenManager>
					<script type="text/javascript">
						
					
					</script>
				</h2>

				<h3>
					发布日期： <span><fmt:formatDate value="${subject.lastUpdateTime }" pattern="yyyy年MM月dd日" /></span>
					关联重心工作： <span> </span>
					执行者：<span><c:forEach items="${subject.excuteUsers }" var="user">${user.userName}</c:forEach></span>
				</h3>
				<div class="articles">
					<p>${subject.content }</p>
				</div>
				<div class="buttons">
					<img src="${prc}/function/function-linshi/images/ico8.png" class="ico7"/>
	  				<img src="${prc}/function/function-linshi/images/ico3.png"  class="ico4"/>
					<img src="${prc}/function/function-linshi/images/add.png"  class="addtabs"/>
				</div>
				<div class="tabs-wp">
					<ul class="tabs">
						<c:forEach items="${steps }" var="step" varStatus="i">
							<li id="${step.id}" class="blocksTab <c:if test="${i.index==0 }">cur</c:if>" >
								<a href="javascript:changeTab('${step.id}');">${step.name }</a>
								<img name="${step.id}" src="${prc}/function/function-linshi/img/errotab.png" />
							</li>
						</c:forEach>
					</ul>
				</div>
				
				<c:forEach items="${steps }" var="step" varStatus="i">
					<c:if test="${i.index==0 }">
					<iframe id="postFrame" name="postFrame" style="border:0px;width:780px;height:900px; hidden;" scrolling="no"
					 src="${prc}/scMaster2/showStep_master.action?id=${step.id}" >
					
					</iframe>
					</c:if>
				</c:forEach>
				
			</div>
		</div>
	</div>
	<!--弹出层 遮盖-->
	<div class="bg"></div>
	<!--弹出层 转发-->
	<div class="adds4">
		<div class="add-tops4">
			<p>转发</p>
			<img src="${prc}/function/function-linshi/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-downs4">
			<div class="down-left">
				<p class="check"><a href="#" onclick="select('true');">全选</a> | <a href="#" onclick="select('false');">取消全选</a></p>
				<p class="teacher">
					<span>史家小学教师</span>
				</p>
				<div style="width:200px;height:350px;overflow:auto;;margin-left:10px;">
					<ul id="trans" class="easyui-tree" animate="true" />
				</div>
				<div style="clear: both"></div>
			</div>
			<div class="down-cen">
				<img src="${prc}/function/function-linshi/images/cen.jpg" />
			</div>
			<div class="down-right">
				<div class="right-p">
					<p>
						<input type="checkbox" checked="checked" />
						<span>李丽霞</span>
					</p>
					<div style="clear: both"></div>
				</div>
				<div class="right-up">
					<p>填写转发内容</p>
					<textarea name="trans.content"></textarea>
					<a href="#" id="transSave">发送</a>
				</div>
			</div>
			<div style="clear: both"></div>
		</div>
		<div style="clear: both"></div>
	</div>
	<!--弹出层 转发-->
	
	
	<!--弹出层3-->
	<div class="adds3">
	<div class="add-tops3">
	 	<p>转移</p>
	 	<img src="${prc}/function/function-linshi/img/erro.jpg"  class="close" style="cursor:pointer;"/>
	</div>
	<div class="add-downs3">
	    <p><input type="radio" /><span>步骤二：xxxxxxxxxxxx</span></p>
	    <p><input type="radio" /><span>步骤二：xxxxxxxxxxxx</span></p>
	</div>
	</div>
    <!--弹出层5-->
    <div class="adds5">
	<div class="add-tops5">
    	<p>批示</p>
    	<img src="${prc}/function/function-linshi/img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
	  	<div class="add-downs5">
      		<p>附件上传：</p>
	      	<textarea></textarea>
	      	<div class="chico">
	          	<a href="#">选择文件 </a>
	           	<a href="#">开始上传 </a>
	           	<a href="#">中断上传 </a>
	      	</div>
		</div>
	</div>
	<!--弹出层6-->
	<div class="adds6">
		<form action="" id="progressForm">
			<div class="add-tops6">
			    <p>增加工作进展</p>
			    <img src="${prc}/function/function-linshi/img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
			  	<div class="add-downs6">
		      		<div>
		          		<p>所属步骤：</p>
			          	<select name="progress.stepId">
							<c:forEach items="${steps }" var="step" >
								<option value="${step.id }">${step.name}</option>
					 		</c:forEach>
			          	</select>
		      		</div>
			      	<div>
			          	<p>工作进展：</p>
			          	<input type="text" name="progress.name"/>
			      	</div>
		      		<div>
		          		<p>具体工作内容：</p>
		          		<textarea name="progress.content" ></textarea>
		      		</div>
		      		<div class="sure">
		          		<a id="progressSave" href="#">确定 </a>
		           		<a href="#" class="close">关闭 </a>
		      		</div>
			</div>
		</form>
	</div>
	<!--弹出层7-->
    <form action="addStep_master.action" id="stepForm">
    <input type="hidden" name="step.subjectId" value="${subject.id}" />
    <div class="adds7">
  		<div class="add-tops7">
	    	<p>编辑</p>
	    	<img src="${prc}/function/function-linshi/img/erro.jpg"  class="close" style="cursor:pointer;"/>
	    </div>
	  	<div class="add-downs7">
			<div class="chen">
	          <p>步骤名称：</p>
	          <input type="text" name="step.name"/>
<!--	          <img src="${prc}/function/function-linshi/images/jia.jpg" />-->
<!--	          <img src="${prc}/function/function-linshi/images/jian.jpg"  class="jian"/>-->
	      	</div>
	      	<div class="sure">
	          <a id="stepSave" href="#">确定 </a>
	          <a href="#" class="close">关闭 </a>
	      	</div>
		</div>
	</div>
	</form>
<script>
	$(function () {
	    $("#slider-range-max").slider({
	        range: "max",
	        min: 5,
	        max: 100,
	        disabled: true ,
	        value: 90,
	        step: 5,
	        slide: function (event, ui) {
	            $("#amount").val(ui.value+"%");
	        }
	    });
	    $("#amount").val($("#slider-range-max").slider("value")+"%");
	});
	
	function changeTab(stepId){
		$("li").removeClass("cur");
		$("#" + stepId).addClass("cur");
		$("#postFrame").attr('src','${prc}/scMaster2/showStep_master.action?id=' + stepId);
	}
</script>
</body>
</html>