<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<title></title>
	
	<script type="text/javascript">
		var ctxPath = '${prc}';
	</script>
	<link href="${prc}/function/function-linshi/css/index.css" rel="stylesheet" type="text/css" />
	<%-- 
	<link href="${prc}/function/function-linshi/css/jqui.css" rel="stylesheet" type="text/css" />
	--%>
	<link href="${prc}/function/function-linshi/css/style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />
	<link href="${prc}/function/function-linshi/css/gzt.css" rel="stylesheet" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/kindeditor-min.js" ></script>
	<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${prc}/function/function-linshi/js/imgmag.js" ></script>
    
    <link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.1/themes/base/jquery-ui.css"/>
	<link href="${prc}/function/js/stickynote/stickynote.css" rel="stylesheet" />
	
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript" src="${prc}/function/js/stickynote/uuid.core.js" ></script>
	<script type="text/javascript" src="${prc}/function/js/stickynote/stickynote.js" ></script>
	<script type="text/javascript" src="${prc}/function/js/stickynote/masternote.js" ></script>
    
	<script type="text/javascript">
	$(function(){
		initNotes('${subject.id}');
	});
	content = KindEditor.ready(function(K) {
		var contentOptions = {
			resizeType : 1,
			width: 416,
			height : 220,
			pasteType:1,
			filterMode:true,
			uploadJson : '${prc}/scMaster2/upload.action',
			allowFileManager : false,
			items : ['preview','print', 'cut', 'copy', 'paste', 'selectall',
			         '|', 'justifyleft', 'justifycenter','justifyright', 'justifyfull', 
			         'insertorderedlist','insertunorderedlist', '|', 'formatblock', 'fontname',
					'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight',
					'removeformat', '|',  'table', 'hr','|','image', 'multiimage'],
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
         $(".shou").click(function () {//赞
        	 window.frames["postFrame"].showZan();
         });
         
         $(".ico1").click(function () {
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
             $(".bg").show();
             $(".adds5").show();
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
         $(".stepClose").click(function () {
             $("body").css("overflow", "auto");
             $(".bg").hide();
         	$(".updateStep").hide();
         });
         $(".addtabs").click(function () {
             $(".bg").show();
             $(".adds7").show();
         });
         $(".small").click(function () {
             $(".bg").show();
             $.post("findById_step.action",{id:this.id},function(data){
                $("#stepId").val(data.id);
                $("#stepName").val(data.name);
             	$(".updateStep").show();
             });
         });
         $(".jia").click(function(){
			$("#add_prog_parent").append(' <div  style=" display: block; position: relative; height: 30px; width: 350px;">'+
					'<p>步骤名称：</p><input type="text" name="stepName"  maxlength="20"  />'+
					'<img src="${prc}/function/function-linshi/images/jia.jpg"  onclick="jian(this)" style="right:40px"/></div>');
         });

      	$("#stepUpdate").click(function(){
      		 var name = $.trim($("#stepName").val());
      		 if(name.length>0){
 	            $.post("isExistStep_step.action",{name:name,id:$("#stepId").val()},function(data1){
 	            	if(data1==0){
 			         	var formParams = $("#stepFormUpdate").serialize();
 			    		$.post("update_step.action", formParams, function(data) {
		      				location.reload();
 			     		});
 	            	}else{
 						alert("存在相同步骤！");
 	            	}
 		          });
 	          }else{
 					alert("请填写步骤名称！");
 	          }
         });
      	$(".delete_step_ico").click(function(){
			var id = $(this).attr("name");
		 	$.post("isExist_progress.action",{stepId:id},function(data1){
				if(data1==0){
					if(confirm("确定删除此步骤吗?")){
						$.post("delete_step.action",{id:id},function(data){
							if(data==0){
								location.reload();
							}else{
								alert("删除失败！");
							}
						});
					}
				}else{
					alert("请先删除工作进展。");
				}
		 	});
       	});
      	//步骤结束
      	//工作进展
      	 $("#progressSave").click(function(){
        	 var name = $.trim($("input[name='progress.name']").val());
        	 if(name.length>0){
	            $.post("isExist_progress.action",{name:name,stepId:$("#prog_step_id").val()},function(data1){
	            	if(data1==0){
			         	var formParams = $("#progressForm").serialize();
			    		$.post("add_progress.action", formParams, function(data) {
			      				$("input[name='progress.name']").val("");
			      				conten.html("");
				   				 $(".adds6").hide();
				   				location.reload();
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
				$(".tree-checkbox.tree-checkbox1").removeClass("tree-checkbox1").addClass("tree-checkbox0");
			}  
     	}
	  
		function popAddProg(stepId){
		  $("#prog_step_id").val(stepId);
		  $(".bg").show();
		  $(".adds6").show();
	  	}
	</script>
	<script>
		function resizeFrame(height){
			$("#postFrame").css("height",height);
		}
		function jian(i){
			$(i).parent().remove();
		}
		function stepSave(){
			var names="";
			var name = document.getElementsByName("stepName");
        	for(var i=0;i<name.length;i++){
	        	var n = $.trim(name[i].value);
	        	if(n.length>0 ){
	        		if(i==(name.length-1)){
		        		names += n;
		        	}else{
						names += n+",";
		        	}
	        	}	
	     	}
	        if(names.length>0){
        		$.post("isExistStep_step.action",{name:names,subjectId: $("input[name='subjectId']").val()},function(data1){
     			  if(data1==0){
   					$.post("add_step.action", {subjectId: $("input[name='subjectId']").val(),name:names}, function(data) {
   	      				if(data==0){location.reload();}else{alert("增加出现错误！");}
   	     			});
     			  }else{alert("存在相同步骤！");}
     		  	});
			 }else{alert("请填写步骤名称！");}
	   }
	</script>
</head>
<body style="text-align: center;">
	<div id="master_notes"></div>
	<input type="hidden" name="subjectId" value="${subject.id}"/>
	<div class="con_conent fixed" style="background-color: #FFF;">
		<%--<h1 class="title">
			<span class="title">当前位置：</span>
			<span class="text">首页 - 校长工作台 - </span>
		</h1> --%>
		<style type="text/css">
		
			.detail_titles{
				margin: 0;
				padding: 0;
				display: block;
				color: #0273c2;
				font-weight: bold;
				font-family: Arial, Helvetica, sans-serif;
				font-size:22px;
			}
			
			.tab_titile{
				font-family: 微软雅黑;
				font-size:16px;
			}
			
			.tabs-wp {
				height: 32px;
				line-height: 38px;
				border-bottom: 1px solid #ccc;
				width: 100%;
				padding: 0;
			}
			
			.tabs_content_title{
				width:70px;
				overflow:hidden;
				text-overflow:ellipsis;
				white-space:nowrap;
			}
			
		</style>
		<div class="table_box fixed">
			<div class="content">
			
				<div class="detail_titles" style="text-align: center;">
					<span style="margin-bottom: 20px;">${subject.title }</span>
					<pri:showWhenMaster>
						<img style="margin-top: 0px;"
						src='${prc}/function/images/percent/${subject.progress }.png' 
						width='60' height='60'/>
					</pri:showWhenMaster>
					
					<pri:hideWhenMaster>
					<div style="float: right; margin-top: 15px; margin-right: 20px;margin-bottom:20px;font-size:12px;">
						<div id="slider_pro" style="width:200px;"></div>
					</div>
					</pri:hideWhenMaster>
					
					<pri:showWhenManager> 
					<c:if test="${checkUser==true}">
						<img id="flagImg" src="${prc}/function/function-linshi/img/qi2.png" width="23" height="30" 
							title="<c:forEach items='${subject.checkUsers}' 
							var='user' ><c:if test='${user.flag==1}'>${user.userName};</c:if></c:forEach>"/>
						<%--<img src="${prc}/function/function-linshi/img/qi3.png" width="23" height="30" />--%>
					</c:if>
					</pri:showWhenManager>
					
					<pri:showWhenMaster>
						<c:if test="${master==true}">
							<img id="flagImg" src="${prc}/function/function-linshi/img/qi2.png" width="23" height="30"/>
						</c:if>
						<c:if test="${master!=true}">
							<img src="${prc}/function/function-linshi/img/qi2.png" width="23" height="30" />
						</c:if>
					</pri:showWhenMaster>
				</div>

	
				<h3 style="font-family: 微软雅黑;font-size:16px;text-align: center;">
					发布日期： <span><fmt:formatDate value="${subject.lastUpdateTime }" pattern="yyyy年MM月dd日" /></span>
					&nbsp;&nbsp;&nbsp;关联重心工作： <span><typ:show id="${subject.typeId}"/></span>
					&nbsp;&nbsp;&nbsp;执行者：<span>
					<c:forEach items="${subject.excuteUsers }" var="user" varStatus="i">
					${user.userName}<c:if test="${not i.last}">、</c:if>
					</c:forEach>
					</span>
					
					<pri:showWhenMaster>
					
					<img style="float:right;cursor: pointer;height:20px;margin: 2px;" title="转发" 
					src="${prc}/function/function-linshi/images/fenxiang.jpg" class="ico7 cpoint" /><%--转发 --%>
					
					<img style="float:right;cursor: pointer;height: 20px;margin: 2px;" title="随笔记" 
					src="${prc}/function/js/stickynote/createNote.png"
					  onclick="newNote('${subject.id}');" /><%--便签 --%>
					
					</pri:showWhenMaster>
				</h3>
				<div class="articles">
					<p>${subject.content }</p>
				</div>
				<div class="tabs-wp">
					<div id="spec-list">
					
					<c:if test="${not empty steps and fn:length(steps)>6 }"><%--少于6个内容不需显示移动的箭头 --%>
					<a href="javascript:;" class="spec-control1" id="spec-forward"></a>
					<a href="javascript:;" class="spec-control2" id="spec-backward"></a>
					</c:if>
       			 	<div class="spec-items" style="position: absolute; width: 680px; height:38px; overflow: hidden; left:35px;">
				
					<ul class="tabs" style="position: absolute;  top: 0px; width:4000px;">
						<c:forEach items="${steps }" var="step" varStatus="i">
							
							<li style="margin:0px;"
							 id="${step.id}" class="blocksTab <c:if test="${i.index==0 }">cur</c:if>"
							onmouseover="$('#step_ops_${step.id}').show();"
							onmouseout="$('#step_ops_${step.id}').hide();"
							 >
								<div class="tabs_content_title">
								<a title="${step.name }" class="tab_titile"
								href="javascript:changeTab('${step.id}');"
								>${step.name }</a>
								</div>
								
								<span id="step_ops_${step.id}" style="display: none;">
									
									<c:if test="${sessionScope.sm2_init==step.createrId}">
									<img title="删除步骤" name="${step.id}"
									class="delete_step_ico small1" 
									src="${prc}/function/function-linshi/images/icon1.png"  /><%--删除 --%>
									
									<img title="编辑步骤" id="${step.id}" 
									 src="${prc}/function/function-linshi/images/icon2.png" 
									class="small"/><%--编辑步骤 --%>
									</c:if>
									
									<pri:showWhenManager>
									<img title="增加工作进展"
									 onclick="popAddProg('${step.id}');"
									src="${prc}/function/function-linshi/images/icon3.png" 
									class="small2 ico4"/><%--增加工作进展 --%>
									</pri:showWhenManager>
								</span>
							</li>
						</c:forEach>
					</ul>
					
					</div>
					
				</div>
					<a class="addtabs">
				
						<img title="增加步骤" 
						style="height: 20px;margin-top: 5px;"
						class="ico4 cpoint"
						 src="${prc}/function/function-linshi/images/prog_add.png" /><%--增加步骤 --%>
					</a>
				<c:forEach items="${steps }" var="step" varStatus="i">
					<c:if test="${i.index==0 }">
					<iframe id="postFrame" name="postFrame" style="border:0px;width:780px; height:600px;" scrolling="no"
					 src="${prc}/scMaster2/showStep_master.action?id=${step.id}&subjectId=${subject.id}" > 
					
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
			<img src="${prc}/function/img/close.png" 
			 class="close" style="cursor: pointer;height:18px;" />
		</div>
		<div class="add-downs4">
			<div class="down-left">
				<p class="check"><a href="#" onclick="select('true');">全选</a> | <a href="#" onclick="select('false');">取消全选</a></p>
				<p class="teacher">
					<span style="color: #006FD1;">史家小学教师</span>
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
	
	
    <!--弹出层5-->
    <div class="adds5">
	<div class="add-tops5">
    	<p>批示</p>
    	<img src="${prc}/function/img/close.png" 
    	class="close" style="cursor:pointer;height: 18px;"/>
    </div>
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
			<input type="hidden" name="subjectId" value="${subject.id}" />
			<div class="add-tops6">
			    <p>增加工作进展</p>
			    <img src="${prc}/function/img/close.png"
			      class="close" style="cursor:pointer;height: 18px;"/> </div>
			  	<div class="add-downs6">
		          	<input name="progress.stepId" type="hidden" id="prog_step_id" value="${step.id}" />
			      	<div>
			          	<p>工作进展：</p>
			          	<input type="text" name="progress.name" maxlength="20"/>
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
	<%--弹出层7步骤 增加--%>
    <form action="addStep_master.action" id="stepForm">
    	<div class="adds7">
	  		<div class="add-tops7">
		    	<p>增加</p>
		    	<img src="${prc}/function/img/close.png"
		    	 class="close" style="cursor:pointer;height:18px;"/>
		    </div>
		  	<div class="add-downs7">
				<div class="chen" id="add_prog_parent" style="overflow-y:scroll;height:200px;width:405px;">
					<div style=" display: block; position: relative; height: 30px; width:350px; ">
			          <p>步骤名称：</p>
			          <input type="text" name="stepName" maxlength="20"/>
			          <img src="${prc}/function/function-linshi/images/jian.jpg"  class="jia" style="right:40px"/>
			      	</div>
		      	</div>
		      	<div class="sure">
		          <a href="javascript:stepSave();">确定 </a>
		          <a href="#" class="close">关闭 </a>
		      	</div>
			</div>
		</div>
	</form>
	<%--弹出层7 增加步骤 END--%>
	<form action="update_step.action" id="stepFormUpdate">
		<input type="hidden" name="step.subjectId" value="${subject.id}" />
    	<input type="hidden" name="step.id" id="stepId" />
    	<div class="updateStep">
	  		<div class="add-tops7">
		    	<p>编辑</p>
		    	<img src="${prc}/function/img/close.png"
		    	 class="stepClose" style="cursor:pointer;height: 18px;"/>
		    </div>
		  	<div class="add-downs7">
				<div class="chen">
		          <p>步骤名称：</p>
		          <input type="text" name="step.name" id="stepName" maxlength="20"/>
		      	</div>
		      	<div class="sure">
		          <a id="stepUpdate" href="#">确定 </a>
		          <a href="#" class="stepClose">关闭 </a>
		      	</div>
			</div>
		</div>
	</form>
<script>
	<pri:hideWhenMaster><%-- 校长不显示进度条 --%>
 	$(function(){
		$("#slider_pro").slider({
			value:${subject.progress},
			onSlideEnd : function(newVal){
				var origin = ${subject.progress};
				var sl = $("#slider_pro");
				if(newVal<origin){
					alert("不能小于原先进度");
					sl.slider('setValue',${subject.progress});
				}
				if(newVal>origin){
					if(confirm('确定修改进度吗?')){
						var newPercent = $("#slider_pro").slider('getValue');
						location.href="${prc}/scMaster2/changeProgress_master.action?id=${subject.id}&subject.progress=" 
							+ newPercent;
					}else{
						sl.slider('setValue',${subject.progress});
					}
				}
			},
			onChange:function(newVal,oldVal){
				$("#amount").val(newVal + "%");
				$(".slider-handle").text(newVal + "%");
				$("#red").css({"margin-right":(100-newVal)+"%"});
			},
			step:5
		});
		$(".slider-handle").text(${subject.progress}+ "%");
		var l = 100-${subject.progress};
		var div = "<div style='margin-right:"+l+"%;background-color: #FF0000;height:6px;' id='red'></div>";
		$(".slider-inner").append(div);
		//$("#amount").val($("#slider_pro").slider('getValue') + "%"); 
		
	}); 
 	</pri:hideWhenMaster>
	
	function changeTab(stepId){
		$("#prog_step_id").val(stepId);
		$("li").removeClass("cur");
		$("#" + stepId).addClass("cur");
		$("#postFrame").attr('src','${prc}/scMaster2/showStep_master.action?id=' + stepId);
	}
</script>

<c:if test="${not empty steps and fn:length(steps)>6 }"><%--少于6个内容不需显示移动的箭头 --%>
<script type="text/javascript">
$("#spec-backward").imgmag({ show: 5, number: 1});
</script>
</c:if>
</body>
</html>