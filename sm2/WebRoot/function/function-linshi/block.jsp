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
	<link href="${prc}/function/function-linshi/css/gzt.css" rel="stylesheet" />
	<script type="text/javascript" src="${prc}/function/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${prc}/function/js/jqui.js"></script>
	
	<script type="text/javascript" src="${prc}/plugins/json/jquery.json-2.4.min.js"></script>
	<script type="text/javascript" src="${prc}/plugins/swfupload/swfupload.js"></script>
	<script type="text/javascript" src="${prc}/plugins/swfobject/swfobject.js"></script>
	<script type="text/javascript" src="${prc}/plugins/ueditor/editor_all_min.js"></script>
	<script type="text/javascript" >var APP_PATH = '${prc}';</script>
	<script type="text/javascript" src="${prc}/plugins/ueditor/editor_config_sns.js"></script>
	<script type="text/javascript" src="${prc}/plugins/fck/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${prc}/plugins/fck/ckfinder/ckfinder.js"></script>
	<script type="" src="${prc}/function/agent.js"></script>
	
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
    </style>
    
	<script type="text/javascript">
	
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
         $(".ico1").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds2").show();
         });
         $(".ico7").click(function () {
             $("body").css("overflow", "hidden");
             $(".bg").show();
             $(".adds4").show();
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
     });
	</script>
	<script>
	</script>
</head>
<body style="height: 100%;">
	<div class="con_conent fixed">
		<div class="table_box fixed">
		<c:forEach items="${postViews}" var="post">
			<div class="content">
				
				<div class="mids">
					<a class="h4">
						<span><a href="javascript:switchHide('article_${post.id}');">·${post.title}</a></span>
						<img src="${prc}/function/function-linshi/images/up.png" width="13" height="13" class="mids-img" />
						<img src="${prc}/function/function-linshi/images/ico3.png" />
						<img src="${prc}/function/function-linshi/images/ico4.png" />
						<img src="${prc}/function/function-linshi/images/ico5.png" />
						<img src="${prc}/function/function-linshi/images/ico6.png" />
						<img src="${prc}/function/function-linshi/images/ico7.png" />
						<img src="${prc}/function/function-linshi/images/ico8.png" />
					</a>
				</div>
				
				<div class="article" id="article_${post.id}" style="height: 212px;">
				
					<p class="p">
					<c:out value="${post.content}" escapeXml="true">
					</c:out>
					</p>
					<c:forEach items="${post.commentList }" var="comment">
					<dl class="new1">
						<dt>
							<img src="${prc}/function/function-linshi/img/tu.jpg" />
						</dt>
						<dd>
							<p>
								<span class="blue"><sns:user id="${comment.creater}" />：</span>${comment.content }<br />
							</p>
							<span class="gray">(
							<fmt:formatDate value="${comment.createTime }" 
							pattern="yyyy-MM-dd HH:mm:ss" />)</span>
						</dd>
					</dl>
					</c:forEach>
					<p class="pack1">查看所有批示</p>
				</div>
				
				<div class="box-top">
						<ul>
							<li class="two">
								<a href="javascript:switchHide('comment_${post.id }');">评论</a>
								<img src="${prc}/function/function-linshi/images/up1.jpg" style="margin-top: 10px; display: block; float: left;" />
							</li>
							<img src="${prc}/function/function-linshi/images/pen.png" style="display: block; float: right; margin: 2px 20px 0 0" />
						</ul>
					</div>
				<div class="box" id="comment_${post.id }" >
					<form name="addCommentform_${post.id }" action="${prc }/addCommWorkbench.action?type=2" >
					
					<input type="hidden" name="subjectId" value="${block.subject.id }"/> 
					<input type="hidden" name="postId" value="${post.id }" /> 
					
					<div class="conshen box-down" >
						<input type="text" name="comment.content" class="erro" /> 
						<a href="javascript:document.addCommentform_${post.id }.submit();" class="btn">发表</a>
						<c:forEach items="${posts.commList }" var="comm">
						<dl class="new">
							<dt>
								<img src="${prc}/function/function-linshi/img/tu.jpg" />
							</dt>
							<dd>
								<p>
									<span class="blue"><sns:user id="${comm.creater}" />：</span>${comm.content}<br />
								</p>
								<span class="gray">(<fmt:formatDate value="${comm.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />)</span>
							</dd>
						</dl>
						</c:forEach>
						<p class="pack">查看所有评论</p>
					</div>
					</form>
				</div>
				
			</div>
			</c:forEach>
		</div>
	</div>
	<!--弹出层-->
	<div class="bg"></div>
	<!--弹出层1-->
	<div class="bg"></div>
	<div class="adds1" id="divZan">
		<div class="add-tops1">
			<p>赞</p>
			<img src="${prc}/function/function-linshi/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-downs1">
			<c:forEach items="${suppRecords}" var="sup">
				<dl class="comments">
					<dt>
						<%--<img src="${prc}/img/tu.jpg" /> --%>
					</dt>
					<dd>
						<p>
							<span class="blue"><sns:user id="${sup.userId}" /></span>
							<img src="${prc}/function/function-linshi/img/tu3.jpg" /><br />
						</p>
						<span class="gray">(<fmt:formatDate 
						value="${record.firstDate }" 
						pattern="yyyy-MM-dd HH:mm:dd" />)
						</span>
					</dd>
				</dl>
			</c:forEach>
			
			<dl class="comments">
				<dt>
					<img src="${prc}/function/function-linshi/img/tu.jpg" />
				</dt>
				<dd>
					<p>
						<span class="blue">沫儿</span>
						<img src="${prc}/function/function-linshi/img/tu3.jpg" /><br />
					</p>
					<span class="gray">(3天前)</span>
				</dd>
			</dl>
		</div>
	</div>
	<!--弹出层2-->
	<div class="bg"></div>
	<div class="adds2" id="divReads">
		<div class="add-tops2">
			<p>阅读</p>
			<img src="${prc}/function/function-linshi/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-downs2">
			<c:forEach items="${readRecords}" var="sup">
				<dl class="comments">
					<dt>
						<%--<img src="${prc}/img/tu.jpg" /> --%>
					</dt>
					<dd>
						<span class="grays"><sns:user id="${sup.userId}" /></span>
						<span class="grays">
							<fmt:formatDate 
							value="${record.firstDate }" 
							pattern="yyyy-MM-dd HH:mm:dd" />
						</span>
					</dd>
				</dl>
			</c:forEach>
			<dl class="comments">
				<dt>
					<img src="${prc}/function/function-linshi/images/tu.jpg" />
				</dt>
				<dd>
					<span class="grays">金强</span>
					<span class="grays">2013-09-10</span>
					<span class="grays">2013-09-13</span>
				</dd>
			</dl>
		</div>
	</div>
	<!--弹出层4-->
	<div class="bg"></div>
	<div class="adds4">
		<div class="add-tops4">
			<p>转发</p>
			<img src="${prc}/function/function-linshi/img/erro.jpg" class="close" style="cursor: pointer;" />
		</div>
		<div class="add-downs4">
			<div class="down-left">
				<p class="check">全选 | 取消全选</p>
				<p class="teacher">
					<input type="checkbox" /><span>史家小学教师</span>
				</p>
				<ul>
					<li>
						<p class="bigs">
							<input type="checkbox" /><span>办公室</span>
						</p>
						<p class="smalls">
							<input type="checkbox" /><span>金强</span>
						</p>
						<p class="smalls">
							<input type="checkbox" /><span>汪忱</span>
						</p>
					</li>
					<li>
						<p class="bigs">
							<input type="checkbox" />
							<span>教务处</span>
						</p>
						<p class="smalls">
							<input type="checkbox" />
							<span>李丽霞</span>
						</p>
					</li>
					<li>
						<p class="bigs">
							<input type="checkbox" />
							<span>教务处</span>
						</p>
						<p class="smalls">
							<input type="checkbox" />
							<span>陈凤伟</span>
						</p>
					</li>
				</ul>
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
					<textarea></textarea>
					<a href="#">发送</a>
				</div>
			</div>
			<div style="clear: both"></div>
		</div>
		<div style="clear: both"></div>
	</div>
	<!--弹出层3-->
	<div class="bg"></div>
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
    <div class="bg"></div>
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
	<div class="bg"></div>
	<div class="adds6">
		<div class="add-tops6">
		    <p>增加工作进展</p>
		    <img src="${prc}/function/function-linshi/img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
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
	<!--弹出层7-->
    <div class="bg"></div>
    <div class="adds7">
  		<div class="add-tops7">
	    	<p>编辑</p>
	    	<img src="${prc}/function/function-linshi/img/erro.jpg"  class="close" style="cursor:pointer;"/>
	    </div>
	  	<div class="add-downs7">
			<div class="chen">
	          <p>步骤名称1：</p>
	          <input type="text" />
	          <img src="${prc}/function/function-linshi/images/jia.jpg" />
	          <img src="${prc}/function/function-linshi/images/jian.jpg"  class="jian"/>
	      	</div>
	      	<div class="chen">
	          	<p>步骤名称2：</p>
	          	<input type="text" />
	          	<img src="${prc}/function/function-linshi/images/jia.jpg" />
	      	</div>
	      	
	      	<div class="sure">
	          <a href="#">确定 </a>
	           <a href="#">关闭 </a>
	      	</div>
		</div>
	</div>
<script>

	$(function () {
	    $("#slider-range-max").slider({
	        range: "max",
	        min: 5,
	        max: 100,
	        value: 5,
	        step: 5,
	        slide: function (event, ui) {
	            $("#amount").val(ui.value+"%");
	        }
	    });
	    $("#amount").val($("#slider-range-max").slider("value")+"%");
	    sethash();
	});
	
	function showZan(){
		$("body").css("overflow", "hidden");
        $("#divZan").show();
	}
	function showRead(){
		$("#divReads").show();
	}
	
	function switchHide(id){
		if($("#" + id).is(":hidden")){
			$("#" + id).slideDown();
		}else{
			$("#" + id).slideUp();
		}
	}
	
</script>
</body>
</html>