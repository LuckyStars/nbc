<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <link href="${prc }/function/detail-step/css/index.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${prc }/function/detail-step/css/gzt.css" type="text/css"/>
    <script type="text/javascript" src="${prc}/function/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/kindeditor-min.js" ></script>
	<script type="text/javascript" src="${prc}/function/kindeditor-4.1.5/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${prc}/function/emotion/jQuery.jEmotion.js"></script>
    <script  type="text/javascript">
    	var ctx = '${prc}';
    	var resizeParent = function(){
    		var height = $(document).height();
    		parent.resizeFrame(height);
    	};
        $(function () {
            $(".img").each(function () {
                $(this).click(function () {
                    if ($(".conshen").css("display") == "block") {
                        $(".conshen").slideUp();
                        $(this).attr("src", "${prc }/function/detail-step/images/down.jpg");
                    } else {
                        $(".conshen").slideDown();
                        $(this).attr("src", "${prc }/function/detail-step/images/up1.jpg");
                    }
                });
            });
            $(".lian").click(function () {
                $(".curs").removeClass("cur");
                $(this).addClass("cur");
                $(".article").show();
                $(".fu").hide();
            });
            $(".curs").click(function () {
                $(this).addClass("cur");
                $(".lian").removeClass("cur");
                $(".article").hide();
                $(".fu").show();
            });
            //资源begin
            
            $(".resource li").click(function () {
                $(".resource .cur").removeClass("cur");
                var index = $(this).index();
                if (index == 0) {
                    $(".resource").attr("class", "resource doc");
                    findAllResource($("#progId").val(),index);
                } else if (index == 1) {
                   	$(".resource").attr("class", "resource pic");
                   	$.post("findPic_resource.action",{progId:$("#progId").val(),type:index},function(data){
                   		if(data != ''){
                   			openList(data);
                   		}
                 	  	});
                } else if (index == 2) {
                    $(".resource").attr("class", "resource video");
                    findAllResource($("#progId").val(),index);
                }
                $(this).addClass("cur");
            });
      
            //资源 end
            $(".ico4").click(function () {
                $("body").css("overflow", "hidden");
                $(".bg").show();
                $(".adds6").show();
            });
            $(".close").click(function () {
                $("body").css("overflow", "auto");
                $(".bg").hide();
                $(".adds1").hide();
                $(".adds2").hide();
                $(".adds3").hide();
                $(".adds5").hide();
                $(".adds6").hide();
            });

            //删除工作进展
            $(".prog").click(function () {
            	if(confirm("确定要删除吗?")){
		    		$.post("delete_progress.action", {id:this.id}, function(data) {
			    		if(data==0){
				    		alert("删除成功！");
		      				location.reload();
			    		}
		     		});
                }
            });
        });
        function resourceClose(){
      	  $(".resource .cur").removeClass("cur");
      	  $(".resource").attr("class", "resource doc");
      	  $(".resource.doc li:first-child ").addClass("cur");
      	  $(".bg").hide();
      	  $(".adds").hide();
       }
   function updatePro(){
	  var id=$('#progressId').val();
	  var name=$('input[name="progress.name"]').val();
	   $.post("isExist_progress.action", {id:id,name:name}, function(data) {
			if(data==0){
				$("#upprogressForm").submit();
			}else{
				alert("存在同名工作进展！");
			}
	   });
   }
  var contentU ;
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
		contentU = K.create('textarea[name="progress.content"]', contentOptions);
	 });
   function showPro(id){
	$.post("find_progress.action",{id:id},function(data){
		if(data){
			$('input[name="progress.id"]').val(id);
			contentU.html(data.content);
			$('input[name="progress.name"]').val(data.name);
			$(".bg").show();
			$(".adds6").show();
		}else{alert("工作进展不存在");}

	});
	}
   function resource(i){
        $("body").css("overflow", "hidden");
        $("#progId").val(i.id);
        $(".bg").show();
        $(".adds").show();
        findAllResource(i.id,0);
        resizeParent();
    }
    function findAllResource(progId,type){
    	$.post("findAll_resource.action",{progId : progId ,type:type},function(data){
    		if(data != ''){
    			openList(data);
    			//location.href="findAll_resource.action?progId="+progId+"&typestr="+type;
    		}
  	   });
    }
    function openList(data){
    	document.getElementById("resource-lists").innerHTML = "";
    	$(".resource-lists").append('<div class="hengxian"><hr width="565" style=" FILTER: progid:DXImageTransform.Microsoft.Shadow(color:#987cb9,direction:145,strength:15"/></div>');
    	$(".resource-lists").append(data);
   }
    
    //资源 end
	
    	function hideDiv(eleId){
    		$("#" + eleId).fadeOut();
    	}
    
    	function switchArticle(id){
    		
    		var imgPath = "${prc }/function/detail-step/images/";
    		$("#art_" + id).is(":hidden")?
    		function(){
    			imgPath = imgPath + "up.png"; 
    			$("#art_" + id).slideDown('fast',resizeParent);
    		}():
    		function(){
    			imgPath = imgPath + "down.png"; 
    			$("#art_" + id).slideUp('fast',resizeParent);
    		}();
    		$("#prog_img_"+id).attr('src',imgPath);
    	}
    	
    	function zan(progId){
    		$("#clickZan_" +progId).hide(); 
    		$.ajax({
    			url:"${prc}/scMaster2/add_zan.action",
    			data:{id:progId}
    		}).always(function(resp,status){
    			if(resp!='true'||status!='success'){
    				alert("点赞失败请稍后重试");
    				$("#clickZan_" +progId).fadeIn();
    			}else{
    				var zanCount = parseInt($("#zan_"+progId).html());
    				$("#zan_"+progId).html(isNaN(zanCount)?1:(zanCount+1));
    				$("#cancelZan_" +progId).fadeIn();
    			}
    		});
    	}
    	function cancelZan(progId){
    		$("#cancelZan_" +progId).hide();
    		$.ajax({
    			url:"${prc}/scMaster2/cancel_zan.action",
    			data:{id:progId}
    		}).always(function(resp,status){
    			if(resp!='suc'||status!='success'){
    				alert("取消赞失败请稍后重试");
    				$("#cancelZan_" +progId).fadeIn();
    			}else{
    				var zanCount = parseInt($("#zan_"+progId).html());
    				$("#zan_"+progId).html(isNaN(zanCount)?0:(zanCount-1));
    				$("#clickZan_" +progId).fadeIn();
    			}
    		});
    	}
    	
    	function showZans(progId){
    		//zanContentsDiv
    		$("#zanContentsDiv").html("");
    		$.post("${prc}/scMaster2/showZans_zan.action?id="+progId,function(data){
    			if(data.length<=0){
    				return;
    			}
    			for(var i = 0;i<data.length;i++){
	    			$('<dl class="comments"><dd><p><span class="blue">'
	 					+ data[i].name +
	   					'</span><br /></p><span class="gray">('
						+ data[i].date + 
						')</span></dd></dl>').appendTo($("#zanContentsDiv"));
    			}
    		});
    		$("#divZan").show();
    	}
    	
    	function showStepTrans(progId){
    		$("#step_radios").html("");
    		$("#trans_prog_id").val(progId);
    		$.post("${prc}/scMaster2/stepList_step.action?id="+progId,function(data){
    			if(data.length<=0){
    				return;
    			}
    			for(var i = 0;i<data.length;i++){
    				var checked_ = '';
    				if(data[i].id=='${id}'){
    					checked_ = 'checked="checked"';
    				}
	    			$('<p><input type="radio" name="stepId" value="'
	    					+ data[i].id + 
	    					'"'+checked_+' /><span>' 
	    					+ data[i].name +
	    					'</span></p>').appendTo($("#step_radios"));
    			}
    		});
    		$(".bg").show();
    		$("#step_trans_div").show();
    	}
    	
    	function subDiscForm(progId){
    		if($.trim($("#disc__content_"+progId).val())==""){
    			alert("请填写内容");
    			return;
    		}
    		$("#disc_form_"+progId).submit();
    	}
    	
    	function subCommForm(progId){
    		if($.trim($("#comm__content_"+progId).val())==""){
    			alert("请填写内容");
    			return;
    		}
    		$("#comm_form_"+progId).submit();
    	}
    	
    	function showReads(progId){
    		$("#reads_contents").html("");
    		$.post("${prc}/scMaster2/showReads_reads.action?id="+progId,function(data){
    			if(data.length<=0){
    				return;
    			}
    			for(var i = 0;i<data.length;i++){
	    			$('<dl class="comments"><dd><p><span '+ 
	    					function (isTrans){return isTrans=='true'?'':'class="blue"';}(data[i].trans)+'>'
	 					+ data[i].name +'</span><br /></p><span class="gray">('
						+ data[i].time + ')</span></dd></dl>').appendTo($("#reads_contents"));
    			}
    		});
    		
    		$("#reads_div").show();
    	}
    	
    	function showDiscusContent (progId){
    		var divId = "#disc_content_"+progId;
    		$(divId).is(":hidden")?
    				function(){
    					$(divId).slideDown('fast',resizeParent);
    				}()
    				:$(divId).slideUp('fast',resizeParent);
    	}
    	
    	var showAllComment = function (progId){
    		$.post('${prc}/scMaster2/allComm_master.action?id=' + progId,function(data){
    			if(data && data.length>0){
    				var contentDiv = $("#comment_content");
    				contentDiv.html("");
    				for(var i =0;i<data.length;i++){
    					var msg = data[i];
    					$(
    						"<dl class='new'><dd><p><span class='blue'>"
    						+ msg.user 
    						+ "：</span>"
    						+ msg.content
    						+ "<br /></p><span class='gray float'>("
    						+ msg.time
    						+ ")</span></dd><div style='clear:both;'></div></dl>"
    					).appendTo(contentDiv);
    				}
    			}
    		});
    		
    		resizeParent();
    	};
    	
    	var showAllDiscuss = function(progId){
    		$.post('${prc}/scMaster2/allDiss_master.action?id=' + progId,function(data){
    			if(data && data.length>0){
    				var contentDiv =$("#diss_content");
    				contentDiv.html('');
    				for(var i =0;i<data.length;i++){
    					var msg = data[i];
    					$(
    						"<dl class='new'><dd><p><span class='blue'>"
    						+ msg.user 
    						+ "：</span>"
    						+ msg.content
    						+ "<br /></p><span class='gray float'>("
    						+ msg.time
    						+ ")</span></dd><div style='clear:both;'></div></dl>"
    					).appendTo(contentDiv);
    				}
    			}
    		});
    		
    		resizeParent();
    	};
    </script>
    <style>
    	.prog_parent{
    		border:1px  dashed #9FBAD6;
    		margin-left:5px;
    		margin-bottom: 10px;
    	}
    	.article{
    		border:0px;
    	}
    	.box{
    		border:0px;
    	}
    	
    	.conshen{
    		border:0px;
    	}
    	
    </style>
</head>
<body style="">
	<input type="hidden" value="${id }" name="step_Id"/>
	   <!--弹出层 资源-->
	<div class="bg"></div>
	<div class="adds" id="add-tab" style="height: auto !important;position: fixed;left: 50%;z-index: 50;margin-left: -298px; display:none;">
	    <input type="hidden" id="progId" />
	 	<div class="wendangs">
	        <div class="resource doc" >
	        	<h5>
	          		<ul>
	            		<li class="cur" >文档</li>
			            <li>图片</li>
			            <li>视频</li>
	          		</ul>
	          		<a href="#" class="more"><img src="${prc}/function/detail-step/img/curr.png" width="24" height="24" onclick="resourceClose();"/></a>
	         	</h5>
		        <div class="resource-lists" id="resource-lists"> </div>
			 </div>
		</div>
	</div>
<!-- 弹出层 资源 END -->
	<c:forEach items="${proList }" var="prog" varStatus="progStatus">
	<div class="prog_parent" >
	   	<div class="mids">
	   		<a class="h4">
	   			<span onclick="switchArticle('${prog.id}');" >${prog.name }</span>
	   			<img id="prog_img_${prog.id}" 
	   				<c:choose >
						<c:when test="${progStatus.first}">
						src="${prc }/function/detail-step/images/up.png"
						</c:when>
						<c:otherwise>
						src="${prc }/function/detail-step/images/down.png"
						</c:otherwise>
					</c:choose>
				onclick="switchArticle('${prog.id}');"
	   			 width="13" height="13" class="mids-img"/>
	   			 
	   			<pri:hideWhenMaster>
	   			<img src="${prc }/function/detail-step/images/ico4.png" title="转移步骤" onclick="showStepTrans('${prog.id}');" class="ico8"/><%--转移步骤 --%>
	   			<c:if test="${sessionScope.sm2_init==prog.createrId}">
	   				<img src="${prc }/function/detail-step/images/ico5.png" title="删除" class="prog" id="${prog.id}"/><%--删除进展 --%>
	   				<img src="${prc }/function/function-linshi/images/proUpdate.png" title="修改" onclick="showPro('${prog.id}');"/>
	   			</c:if>
	   			<%--<img src="${prc }/function/detail-step/images/ico6.png" class="ico5"/>上传附件 --%>
	   			</pri:hideWhenMaster>
	   			
	   			
	   			<img
	   			style="height: 20px;
	   			<c:if test="${prog.zand > 0}">
	   			 display: none;
	   			</c:if>
	   			"
	   			 src="${prc }/function/detail-step/images/zan2.png" title="赞"
	   			id="clickZan_${prog.id}"
	   			onclick="zan('${prog.id}');" /><%--点赞狂魔 --%>
	   			
	   			<img 
	   			style="height: 20px;
	   			<c:if test="${prog.zand <= 0}">
	   			 display: none;
	   			</c:if>
	   			"
	   			 src="${prc }/function/detail-step/images/zancancel2.png" title="取消赞" 
	   			id="cancelZan_${prog.id}"
	   			onclick="cancelZan('${prog.id}');" />
	   			
	   			<img src="${prc }/function/detail-step/images/icon4.png" 
	   			title="查看评论" onclick="javascript:showDiscusContent('${prog.id}');"/>
	   			
	   		</a>
	        <div class="conls"> 
	        	<a>
	        		<img src="${prc }/function/detail-step/images/shou.png" 
	        		onclick="showZans('${prog.id}');" title="赞"
	        		width="13" height="13" class="shou"/>(<span id="zan_${prog.id }">${prog.zanCount }</span>)<%--赞 --%>
	        	</a>
	        	<span> | </span>
	        	<a>
	        		<img src="${prc }/function/detail-step/images/ico1.png" 
	        		onclick="showReads('${prog.id}')" title="阅读"
	        		width="13" height="13" class="ico1"/>（${prog.readCount}）<%--阅读 --%>
	        	</a>
	       		<span> | </span>
	        	<a>
	        		<img src="${prc }/function/detail-step/images/ico2.png" id="${prog.id}" 
	        		title="查看资源" width="13" height="13" class="ico2" onclick="resource(this)"/>
	        	</a>
	        </div>
		</div>
	
	<!-- 评论  -->
	<div id="disc_content_${prog.id }" style="display: none; ">
    <div class="box" style="min-height: 0px;" >
        <div class="conshen box-down" 
        	name="disc_content_${prog.id }"
           >
        	<pri:hideWhenMaster>
        	<form action="${prc}/scMaster2/add_disc.action" method="post"
        	id="disc_form_${prog.id }"
        	 >
        	 	<div >
        	 			<div >
			            <textarea
			            style="width:700px;margin-left:10px; margin-bottom:10px;display: block;border: 1px solid #ccc;"
			             class="error" name="disscus.content" id="disc__content_${prog.id}"
			             ></textarea>
			             </div>
	          			<a href="javascript:subDiscForm('${prog.id }');" class="btn">发表</a>
			            <span class="emotion" id="emospan_${prog.id }" 
			            style="cursor:pointer; float:right;display:block;margin:0px 40px 0 0;">
			            	<img src="${prc}/function/emotion/images/xiao.png" title="表情"/>
			            </span>
			    </div>
			    <script type="text/javascript">
			    $(function(){
				    $('#disc__content_${prog.id}').jEmotion({
				    	input:'disc__content_${prog.id}',
						handler: 'emospan_${prog.id }', //表情按钮
						imagePath: '${prc}/function/emotion/images/', //图片路径
						converts: 'div' //载入页面时候这个选择器中的元素会被替换表情图片
					});
			    });
			    </script>
	          	<input type="hidden" name="stepId" value="${id }"/>
	          	<input type="hidden" name="disscus.progressId" value="${prog.id }"/>
        	</form>
        	</pri:hideWhenMaster>
        	
        	<div style="clear: both;height: 5px;"></div>
        	
        	<div id="diss_content"
        	style="background-color:#F5F5F5;width: 705px;margin-left: 10px;"
        	>
        	<c:forEach items="${disMap}" var="disEntry">
        		<c:if test="${disEntry.key==prog.id }">
        			<c:forEach items="${disEntry.value }" var="dis">
        				<dl class="new">
			            	<dd>
			              		<p>
			              			<span class="blue">${dis.userName }：</span>
			              			<emo:show content="${dis.content }"/>
			              			<br />
			              		</p>
			              		<span class="gray float">(<fmt:formatDate value="${dis.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>)</span>
			              	</dd>
			             	<div style="clear:both;"></div>
			          	</dl>
        			</c:forEach>
        		</c:if>
        	</c:forEach>
          	</div>
          	
          	<p class="pack1"><a style="color: #0A5FB2;" href="javascript:showAllDiscuss('${prog.id}');">查看所有评论</a></p>
		</div>
	</div>
	</div>
	<!-- 评论 END -->
	
    <div class="article" 
    style="height:inherit;<c:if test="${not  progStatus.first}">display:none;</c:if>" 
    id="art_${prog.id }"
    >
        <textarea style="width:700px;height:200px;" id="content_${prog.id}"></textarea>
        <script type="text/javascript">
	        KindEditor.ready(function(K) {
	        	var options = {items : []};
	        	var editor${prog.id} = K.create('#content_${prog.id}',options);
	       		editor${prog.id}.html('${prog.content}');
	        	editor${prog.id}.readonly(true);
	        });
        </script>
    
    	<!-- 增加批示 -->    
        <div class="conshen" style="border:0px;">
        	<pri:showWhenMaster>
        	<form action="${prc}/scMaster2/add_comment.action" method="post"
        	id="comm_form_${prog.id }"
        	 >
	          	<input type="text" id="comm__content_${prog.id }" name="comm.content"
	          	 class="erro" style="width:700px;margin:0px; margin-bottom:10px;"/>
	          	<input type="hidden" name="stepId" value="${id }"/>
	          	<input type="hidden" name="comm.progressId" value="${prog.id }"/>
	          	<a href="javascript:subCommForm('${prog.id }');" class="btn">发表</a>
        	</form>
        	</pri:showWhenMaster>
        </div>
        <div style="clear: both;height: 5px;"></div>
        <div id="comment_content" style=" background-color: #F5F5F5;width: 705px;">
        <!-- 增加批示 END  -->   
        <c:forEach items="${comMap}" var="comEntry">
       		<c:if test="${comEntry.key==prog.id }">
       			<c:forEach items="${comEntry.value }" var="com">
       				<dl class="new">
		            	<dd>
		              		<p>
		              			<span class="blue">${com.userName }：</span>
		              			<c:out value="${com.content }" escapeXml="true"></c:out><br />
		              		</p>
		              		<span class="gray float">(<fmt:formatDate value="${com.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>)</span>
		              	</dd>
		             	<div style="clear:both;"></div>
		          	</dl>
       			</c:forEach>
       		</c:if>
       	</c:forEach>
		</div>
        <p class="pack1" onclick="showAllComment('${prog.id}');">查看所有批示</p>
	</div>
	
	</div>
	</c:forEach>
    <!--弹出层 赞-->
	<div class="adds1" id="divZan" style="position: absolute;">
  		<div class="add-tops1">
	   		<p>赞</p>
	    	<img
	    	 src="${prc}/function/img/close.png" 
	    	onclick="hideDiv('divZan');"
	    	class="close" style="cursor:pointer;height:18px;"/>
    	</div>
  		<div class="add-downs1" id="zanContentsDiv">
      		<dl class="comments">
            <dt><img src="${prc}/function/img/tu.jpg" /></dt>
            <dd>
              <p><span class="blue">沫儿</span><img src="${prc}/function/img/tu3.jpg" /><br />
              </p>
              <span class="gray">(3天前)</span> </dd>
          	</dl>
		</div>
	</div>
	<!--弹出层 赞 END-->


    <!--弹出层 阅读-->
	<div class="adds2" id="reads_div">
  		<div class="add-tops2">
    		<p>阅读</p>
    		<img src="${prc}/function/img/close.png" 
    		 class="close" style="cursor:pointer;height:18px;"/>
    	</div>
    	
  		<div class="add-downs2" id="reads_contents">
   			<dl class="comments">
            	<dt><img src="${prc }/function/detail-step/images/tu.jpg" /></dt>
            	<dd>
              		<span class="grays">金强</span>
	              	<span class="grays">2013-09-10</span>
	                <span class="grays">2013-09-13</span>
            	</dd>
          	</dl>
		</div>
		
	</div>
 	<!--弹出层 阅读 END -->
 
	<!--弹出层 转移步骤-->
	<div class="adds3" id="step_trans_div" >
  		<div class="add-tops3">
    		<p>转移</p>
    		<img src="${prc}/function/img/close.png"
    		  class="close" style="cursor:pointer;height:18px"/>
    	</div>
    	<form action="${prc}/scMaster2/changeStep_progress.action" method="post" 
    	onsubmit="return confirm('确认转移步骤吗?');" name="step_trans_form">
    		<input type="hidden" id="trans_prog_id" name="id" />
    		<input type="hidden" name="originStepId" value="${id }"/>
	  		<div class="add-downs3" id="step_radios">
		       	<p><input type="radio" name="stepId" /><span>步骤二：xxxxxxxxxxxx</span></p>
			</div>
			<div style="text-align: center;">
			
			<a href="javascript:document.step_trans_form.submit();" class="btn" >提交</a>
			</div>
		</form>
	</div>
 	<!--弹出层 转移步骤END-->
 <div class="adds6">
		<form action="update_progress.action" id="upprogressForm">
			<input name="progress.id" type="hidden" id="progressId" />
			<div class="add-tops6">
			    <p>编辑工作进展</p>
			    <img src="${prc}/function/img/close.png"
			      class="close" style="cursor:pointer;height: 18px;"/> </div>
		  	<div class="add-downs6">
		      	<div>
		          	<p>工作进展：</p>
		          	<input type="text" name="progress.name" maxlength="20"/>
		      	</div>
	      		<div>
	          		<p>具体工作内容：</p>
	          		<textarea name="progress.content"></textarea>
	      		</div>
	      		<div class="sure">
	          		<a id="progressSave" href="javascript:updatePro();">确定 </a>
	           		<a href="#" class="close">关闭 </a>
	      		</div>
			</div>
		</form>
	</div>

<script type="text/javascript">

	$(function(){
		resizeParent();
	});
</script>

</body>
</html>