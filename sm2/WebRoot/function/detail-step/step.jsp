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
	<script type="text/javascript" src="${prc}/function/emotion/emotion.js"></script>
	<link rel="stylesheet" href="${prc }/function/emotion/emotion.css" type="text/css"/>
    <script>
    	var ctx = '${prc}';
    	function resizeParent(){
    		var height = $(document).height();
    		console.log(height);
    		parent.resizeFrame(height+200);
    	}
        $(function () {
        	  $("table tr:odd").css("background", "#f0f8fc");
        	    $("table tr:even").css("background", "#d5e0ee");
        	    $("table tr").css("height","27px");
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
            $(".answ").click(function () {
                $(".fa").show();
            });
            $(".fa textarea").blur(function () {
                $(".fa").hide();
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
            $(".resources li").click(function () {
                $(".resources .cur").removeClass("cur");
              // $(".resource-lists").empty();
                var index = $(this).index();
                if (index == 0) {
                    $(".resources").attr("class", "resources doc");
                    findAllResource($("#progId").val(),index);
                } else if (index == 1) {
                   	$(".resources").attr("class", "resources pic");
                   	$.post("findPic_resource.action",{progId:$("#progId").val(),type:index},function(data){
                   		if(data != ''){
                       		$(".resource-lists").empty();
                   			$(".resource-lists").html(data);
                   		}
                 	  	});
                } else if (index == 2) {
                    $(".resources").attr("class", "resources video");
                    findAllResource($("#progId").val(),index);
                }
              
               // $(".resource-lists").hide().eq(index).show();
                $(this).addClass("cur");
            });
            $(".ico2").click(function () {
                
                $("body").css("overflow", "hidden");
                $("#progId").val(this.id);
                $(".bg").show();
                $(".adds").show();
                findAllResource(this.id,0);
                resizeParent();
            });
            function findAllResource(progId,type){
            	$.post("findAll_resource.action",{progId : progId ,type:type},function(data){
            		if(data != ''){
            			$(".resource-lists").empty();
            			$(".resource-lists").html(data);
            		}
          	  	});
            }
      
            //资源 end
            $(".ico7").click(function () {
                $("body").css("overflow", "hidden");
                $(".bg").show();
                $(".adds4").show();
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
                $(".adds1").hide();
                $(".adds2").hide();
                $(".adds3").hide();
                $(".adds4").hide();
                $(".adds5").hide();
                $(".adds6").hide();
                $(".adds7").hide();
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
      	  $(".resources .cur").removeClass("cur");
      	  $(".resources").attr("class", "resources doc");
      	  $(".resources .doc").addClass("cur");
      	  $(".bg").hide();
      	  $(".adds").hide();

       }
    </script>
    
    <script type="text/javascript">
    	
    	function hideDiv(eleId){
    		$("#" + eleId).fadeOut();
    	}
    
    	function switchArticle(id){
    		$("#art_" + id).is(":hidden")?
    		function(){
    			$("#art_" + id).slideDown('fast',resizeParent);
    		}():
    		function(){
    			$("#art_" + id).slideUp('fast',resizeParent);
    		}();
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
    				$(divId).slideDown('fast',resizeParent)
    				:$(divId).slideUp('fast',resizeParent);
    	}
    </script>
</head>
<body style="background-color: #f0f8fc;">
	<c:forEach items="${proList }" var="prog">
		
   	<div class="mids">
   		<a class="h4">
   			<span>·${prog.name }</span>
   			<img src="${prc }/function/detail-step/images/up.png"
   				onclick="switchArticle('${prog.id}');"
   			 width="13" height="13" class="mids-img"/>
   			
   			<img src="${prc }/function/detail-step/images/ico4.png" alt="转移步骤" onclick="showStepTrans('${prog.id}');" class="ico8"/><%--转移步骤 --%>
   			<c:if test="${sessionScope.sm2_init==prog.createrId}">
   				<img src="${prc }/function/detail-step/images/ico5.png" class="prog" id="${prog.id}"/><%--删除进展 --%>
   			</c:if>
   			<%--<img src="${prc }/function/detail-step/images/ico6.png" class="ico5"/>上传附件 --%>
   			
   			
   			
   			<img src="${prc }/function/detail-step/images/zan.png" alt="赞"
   			<c:if test="${prog.zand > 0}">
   			 style="display: none;" 
   			</c:if>
   			id="clickZan_${prog.id}"
   			onclick="zan('${prog.id}');" /><%--点赞狂魔 --%>
   			
   			<img  src="${prc }/function/detail-step/images/zancancel.png" alt="取消赞" 
   			<c:if test="${prog.zand <= 0}">
   			 style="display: none;" 
   			</c:if>
   			id="cancelZan_${prog.id}"
   			onclick="cancelZan('${prog.id}');" />
   			
   			<a href="javascript:showDiscusContent('${prog.id}');">查看评论</a>
   			
   		</a>
        <div class="conls"> 
        	<a>
        		<img src="${prc }/function/detail-step/images/shou.png" 
        		onclick="showZans('${prog.id}');" alt="赞"
        		width="13" height="13" class="shou"/>(<span id="zan_${prog.id }">${prog.zanCount }</span>)<%--赞 --%>
        	</a>
        	<span> | </span>
        	<a>
        		<img src="${prc }/function/detail-step/images/ico1.png" 
        		onclick="showReads('${prog.id}')" alt="阅读"
        		width="13" height="13" class="ico1"/>（${prog.readCount}）<%--阅读 --%>
        	</a>
       		<span> | </span>
        	<a>
        		<img src="${prc }/function/detail-step/images/ico2.png" id="${prog.id}" 
        		width="13" height="13" class="ico2"/>（0）
        	</a>
        	
        </div>
	</div>
	
    <div class="article" style="height:inherit;" id="art_${prog.id }">
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
        <div class="conshen">
        	<pri:showWhenMaster>
        	<form action="${prc}/scMaster2/add_comment.action" method="post"
        	id="comm_form_${prog.id }"
        	 >
	          	<input type="text" id="comm__content_${prog.id }" name="comm.content" class="erro"/>
	          	<input type="hidden" name="stepId" value="${id }"/>
	          	<input type="hidden" name="comm.progressId" value="${prog.id }"/>
	          	<a href="javascript:subCommForm('${prog.id }');" class="btn">发表</a>
        	</form>
        	</pri:showWhenMaster>
        </div>
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
       
        <p class="pack1">查看所有批示</p>
	</div>
	
	<!-- 评论  -->
    <div class="box" >
        <div class="conshen box-down" id="disc_content_${prog.id }" style="display: none;" >
        	
        	<form action="${prc}/scMaster2/add_disc.action" method="post"
        	id="disc_form_${prog.id }"
        	 >
        	 	<div class="emo_comment">
			        <div class="emo_form">
			            <div class="face_tool" id="emodiv_${prog.id }">
			               
			            </div>
			            <p><span class="emotion" id="emospan_${prog.id }"></span></p>
			            <textarea class="emo_content" name="disscus.content" id="disc__content_${prog.id}" cols="" rows=""></textarea>
			        </div>
			    </div>
			    <script type="text/javascript">
			    	var emo_${prog.id} = new EmoFace(
			    			'disc__content_${prog.id}',
			    			'emodiv_${prog.id }',
			    			'emospan_${prog.id }'
			    			);
			    	emo_${prog.id}.Create();
			    </script>
	          	<input type="hidden" name="stepId" value="${id }"/>
	          	<input type="hidden" name="disscus.progressId" value="${prog.id }"/>
	          	<a href="javascript:subDiscForm('${prog.id }');" class="btn">发表</a>
        	</form>
        	
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
          	
          	<p class="pack"><a >查看所有评论</a></p>
		</div>
	</div>
	<!-- 评论 END -->
	</c:forEach>
      
      <!--弹出层 资源-->
<div class="bg"></div>
<div class="adds" id="add-tab">
  <div class="add-tops">
    <p>资源</p>
    <img src="${prc }/function/detail-step/img/erro.jpg"  onclick="resourceClose();" style="cursor:pointer;"/> </div>
    <input type="hidden" id="progId" />
 	<div class="add-downs">
<!--    	<p>全部 | 本步骤 | 本进展</p>-->
        <div class="resources doc" >
        	<h5>
          		<ul>
            		<li class="cur" >文档</li>
		            <li>图片</li>
		            <li>视频</li>
          		</ul>
         	 	<a href="#" class="mores">更多&gt;&gt;</a>
         	</h5>
	        <div class="resource-lists" style="height:380px;"></div>
      </div>
    <a href="#" class="return" style="margin-left:100px;" id="upload">提交</a> <a href="#" class="return" onclick="resourceClose()">返回</a> </div>
</div>
<!-- 弹出层 资源 END -->

    <!--弹出层 赞-->
	<div class="adds1" id="divZan" style="position: absolute;">
  		<div class="add-tops1">
	   		<p>赞</p>
	    	<img src="${prc}/function/detail-step/img/erro.jpg" 
	    	onclick="hideDiv('divZan');"
	    	class="close" style="cursor:pointer;"/>
    	</div>
  		<div class="add-downs1" id="zanContentsDiv">
      		<dl class="comments">
            	<dd>
              		<p>
              			<span class="blue">沫儿</span><br />
              		</p>
              		<span class="gray">(3天前)</span>
              	</dd>
          	</dl>
      		<dl class="comments">
            <dt><img src="img/tu.jpg" /></dt>
            <dd>
              <p><span class="blue">沫儿</span><img src="img/tu3.jpg" /><br />
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
    		<img src="${prc }/function/detail-step/img/erro.jpg"  class="close" style="cursor:pointer;"/>
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
 

    <!--弹出层  转发-->
    <div class="adds4">
  <div class="add-tops4">
    <p>转发</p>
    <img src="img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
  <div class="add-downs4">
      <div class="down-left">
          <p class="check">全选 | 取消全选 </p>
          <p class="teacher"><input type="checkbox" /><span>史家小学教师</span></p>
          <ul>
              <li>
                  <p class="bigs"><input type="checkbox" /><span>办公室</span></p>
                  <p class="smalls"><input type="checkbox" /><span>金强</span></p>
                  <p class="smalls"><input type="checkbox" /><span>汪忱</span></p>
              </li>
          </ul>
          <div style="clear:both"></div>
      </div>
       <div class="down-cen"><img src="images/cen.jpg" /></div>
       <div class="down-right">
           <div class="right-p">
               <p><input type="checkbox"  checked="checked"/><span>李丽霞</span></p>
               <p><input type="checkbox" checked="checked"/><span>李丽霞</span></p>
               <p><input type="checkbox" checked="checked"/><span>李丽霞</span></p>
               <p><input type="checkbox" checked="checked"/><span>李丽霞</span></p>
               <div style="clear:both"></div>
           </div>
           <div class="right-up">
               <p>填写转发内容</p>
               <textarea></textarea>
               <a href="#">发送</a>
           </div>
       </div>
      <div style="clear:both"></div>
</div>
        <div style="clear:both"></div>
</div>

<!--弹出层  转发END-->
	<!--弹出层 转移步骤-->
	<div class="adds3" id="step_trans_div" >
  		<div class="add-tops3">
    		<p>转移</p>
    		<img src="${prc }/function/img/erro.jpg"  class="close" style="cursor:pointer;"/>
    	</div>
    	<form action="${prc}/scMaster2/changeStep_progress.action" method="post" onsubmit="return confirm('确认转移步骤吗?');" >
    		<input type="hidden" id="trans_prog_id" name="id" />
    		<input type="hidden" name="originStepId" value="${id }"/>
	  		<div class="add-downs3" id="step_radios">
		       	<p><input type="radio" name="stepId" /><span>步骤二：xxxxxxxxxxxx</span></p>
			</div>
			<input type="submit" value="提交"/>
		</form>
	</div>
 	<!--弹出层 转移步骤END-->
 
 
 
          <!--弹出层附件上传-->
    <div class="adds5">
  <div class="add-tops5">
    <p>批示</p>
    <img src="img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
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

 <!--弹出层附件上传END-->

              <!--弹出层增加工作进展-->
    <div class="adds6">
  <div class="add-tops6">
    <p>增加工作进展</p>
    <img src="img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
  <div class="add-downs6">
      <div>
          <p>所属步骤：</p>
          <select>

          </select>
      </div>
      <div>
          <p>工作进展：</p>
          <input type="text" />
      </div>
      <div>
          <p>具体工作内容：</p>
          <textarea></textarea>
      </div>
      <div class="sure">
          <a href="#">确定 </a>
           <a href="#">关闭 </a>
      </div>
      
</div>
</div>
<!--弹出层增加工作进展 END-->
                  <!--弹出层编辑-->
    <div class="adds7">
  <div class="add-tops7">
    <p>编辑</p>
    <img src="img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
  <div class="add-downs7">
      <div class="chen">
          <p>步骤名称1：</p>
          <input type="text" />
          <img src="images/jia.jpg" />
          <img src="images/jian.jpg"  class="jian"/>
      </div>
      <div class="chen">
          <p>步骤名称2：</p>
          <input type="text" />
          <img src="images/jia.jpg" />
      </div>
      <div class="chen">
          <p>步骤名称3：</p>
          <input type="text" />
          <img src="images/jia.jpg" />
      </div>
      <div class="chen">
          <p>步骤名称4：</p>
          <input type="text" />
          <img src="images/jia.jpg" />
      </div>
      <div class="chen">
          <p>步骤名称5：</p>
          <input type="text" />
          <img src="images/jia.jpg" />
      </div>
      <div class="sure">
          <a href="#">确定 </a>
           <a href="#">关闭 </a>
      </div>
      
</div>
</div>
<script type="text/javascript">

	$(function(){
		resizeParent();
	});
</script>
<!--弹出层编辑END-->

</body>
</html>