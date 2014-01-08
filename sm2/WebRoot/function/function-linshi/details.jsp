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
	<script type="text/javascript" src="${prc}/function/function-masterSubList/js/schoolMaster.js"></script>
    
    <link rel="stylesheet" type="text/css" 
    <%-- 这里不要换1.10.3的样式...会有问题(╯‵□′)╯︵┻━┻ --%>
    href="${prc}/function/js/jqueryui/jquery-ui-themes-1.10.1/themes/base/minified/jquery-ui.min.css"/>
	
	<link href="${prc}/function/js/stickynote/stickynote.css" rel="stylesheet" />
	<link href="${prc}/function/js/tabs/css/tabs.css" rel="stylesheet" />
	
	<pri:showWhenMaster>
    <script type="text/javascript" src="${prc}/function/js/jqueryui/js/jquery-ui-1.10.3.custom.min.js"></script>
    </pri:showWhenMaster>
	<script type="text/javascript" src="${prc}/function/js/stickynote/uuid.core.js" ></script>
	<script type="text/javascript" src="${prc}/function/js/stickynote/stickynote.js" ></script>
	<script type="text/javascript" src="${prc}/function/js/stickynote/masternote.js" ></script>
    <script type="text/javascript" src="${prc }/function/js/tabs/js/tabs.js"></script>
    <style type="text/css">
    	.img {
    	float:right;height:20px;margin: 2px;
    	}
    </style>
	<script type="text/javascript"> 
	$(function(){
		initNotes('${subject.id}');
	});
	var content;
	KindEditor.ready(function(K) {
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
	content = K.create('textarea[name="progress.content"]', contentOptions);
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
         $(".tabs_edit").click(function () {
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
      	$(".tabs_remove").click(function(){
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
			      				content.html("");
				   				$(".adds6").hide();
				   				alert("保存成功！");
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
			
		</style>
		<div class="table_box fixed">
			<div class="content">
				<div class="detail_titles" style="text-align: center;">
				
					<pri:showWhenMaster>
						<div>
						<img style="margin-top: 0px;position: absolute;left: 20px;"
						src='${prc}/function/images/percent/${subject.progress }.png' 
						width='60' height='60'/>
						</div>
					</pri:showWhenMaster>
					<span style="margin-bottom: 20px;">${subject.title }</span>
					<img src="${prc }/function/function-linshi/img/back.jpg" alt="返回" onclick="history.back();"
						style="float:right;cursor: pointer;height:20px;margin-right: 15px;"/>
				</div>
					<pri:showWhenManager>
						<div style="font-size:12px; position: absolute;left:5px;">
						<div id="slider_pro" style="width:200px;"></div>
						<span id="slider_num" ></span>
					</div>
					</pri:showWhenManager>
				<h3 style="font-family: 微软雅黑;font-size:14px;text-align: center;padding-left: 70px;width: 690px;">
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
						id="stknote"
						  onclick="newNote('${subject.id}');" /><%--便签 --%>
						  
						<c:if test="${master==true}">
							<img id="${subject.id}" onclick="javascript:stick('${subject.id }',3,'qi');"
							style="float:right;height:20px;margin: 2px;"
							 src="${prc}/function/img/qi1.png" />
						</c:if>
						<c:if test="${master==false}">
							<img id="${subject.id}" onclick="javascript:stick('${subject.id }',1,'qi');"
							style="float:right;height:20px;margin: 2px;"
							src="${prc}/function/img/qi3.png" />
						</c:if>
					</pri:showWhenMaster>
					<pri:showWhenManager>
						<img style="float:right;cursor: pointer;height:20px;margin: 2px;" title="转发" 
						src="${prc}/function/function-linshi/images/fenxiang.jpg" class="ico7 cpoint" /><%--转发 --%>
					</pri:showWhenManager>
					<pri:showWhenManager> 
						<c:if test="${checkUser==true}">
							<img id="flagImg" src="${prc}/function/function-linshi/img/qi2.png" 
								style="float:right;height:20px;margin: 2px;"
								title="<c:forEach items='${subject.checkUsers}' 
								var='user' ><c:if test='${user.flag==1}'>${user.userName};</c:if></c:forEach>"/>
						</c:if>
					</pri:showWhenManager>
				</h3>
				<div class="articles">
					<p style="text-align: left;">${subject.content }</p>
				</div>
				
				<div >
				
				 <script type="text/javascript">
				 var initTabs = function(){

						showIcoOnHover();
						initWidths();
						initMoves();
						initHovers();
				
						$(".tab_text").each(function(){//点击字的事件
							$(this).click(function(){
								$(this).parent().removeClass("tabs_hover");
								$(".tabs_tab").removeClass("tabs_selected");
								$(this).parent().addClass("tabs_selected");
								var stepId = $(this).parent().attr('stepId');
								changeTab(stepId);
							});
						});
					};		

					$(function(){
						
						initTabs();

					});
				 </script>
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
				<div class="tabs_warp" style="text-align: left;">
				<div class="tabs_arrow tabs_arrow_left"></div>
				<div class="tabs_arrow tabs_arrow_right"></div>
				<div id="tabs_container" class="tabs_container" >
					<ul class="tabs_tab_parent" id="tabs_tab_parent" >
						<c:forEach items="${steps }" var="step" varStatus="i">
						<li stepId="${step.id }" class="tabs_tab <c:if test="${i.index==0 }">tabs_selected</c:if>">
							<span class="tabs_icon_container">
								<pri:showWhenManager>
								<div class="tabs_icon tabs_new_children" onclick="popAddProg('${step.id}');" ></div>
								</pri:showWhenManager>
								<c:if test="${sessionScope.sm2_init==step.createrId}">
								<div class="tabs_icon tabs_edit" id="${step.id}" ></div>
								<div class="tabs_icon tabs_remove"  name="${step.id}"></div>
								</c:if>
							</span>
							<div class="tabs_icon"><!--用于往下挤高度= =|| --></div>
							<div class="tab_text" title="${step.name }">	
								${step.name }
							</div>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
				
				<pri:showWhenManager>
				<img title="增加步骤" 
				style="height: 20px;
					margin-top: 5px;
					float: right;
					left: 50px;
					top: 10px;
					position:relative;
					cursor: pointer;"
				class="ico4 cpoint addtabs"
				 src="${prc}/function/function-linshi/images/prog_add.png" /><%--增加步骤 --%>
			</pri:showWhenManager>
			<div style="z-index:-999999;clear: both;width:790px;border-top: 1px solid #d5d5d5;height: 10px;margin-top:-1px;">
			</div>
			
			<c:forEach items="${steps }" var="step" varStatus="i">
				<c:if test="${i.index==0 }">
				<iframe id="postFrame" name="postFrame" 
				
				frameborder="0" allowtransparency="true" scrolling="no"
				style="border:none;width:780px; height:600px;"
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