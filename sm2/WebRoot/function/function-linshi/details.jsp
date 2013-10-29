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
<body>
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
					
					<img src="${prc}/function/function-linshi/img/qi2.png" width="23" height="30" />
					<img src="${prc}/function/function-linshi/img/qi3.png" width="23" height="30" />
					
					
				</h2>

				<h3>
					发布日期： <span><fmt:formatDate value="${subject.lastUpdateTime }" pattern="yyyy年MM月dd日" /></span>
					关联重心工作： <span> </span>
					执行者：<span> </span>
				</h3>
				<div class="articles">
					<p>${subject.content }</p>
				</div>
				<div class="buttons">
					<a class="button" id="btn-addfujian">增加附件</a>
				</div>
				<div class="tabs-wp">
					<ul class="tabs">
						<c:forEach items="${steps }" var="step" varStatus="i">
							<li  id="${step.id}" 
							class="blocksTab 
								<c:if test="${i.index==0 }">
								cur
								</c:if>"
							>
							<a href="javascript:changeTab('${step.id}');">${step.name }</a>
						</li>
						</c:forEach>
					</ul>
					<div class="mids">
					<div class="conls">
							<a>
								<img src="${prc}/function/function-linshi/images/shou.png" width="13" height="13"class="shou" />
								(999)
							</a>
							<span> | </span>
							<a>
								<img src="${prc}/function/function-linshi/images/ico1.png" width="13" height="13" class="ico1" />
								（999）
							</a>
							<span> | </span>
							<a>
								<img src="${prc}/function/function-linshi/images/ico2.png" width="13" height="13" class="ico2" />
							</a>
						</div>
					</div>
				</div>
				
				<c:forEach items="${steps }" var="block" varStatus="i">
					<c:if test="${i.index==0 }">
					<iframe id="postFrame" name="postFrame" style="border:0px;width:870px;height:900px; hidden;" scrolling="no"
					 src="${prc}/scMaster2/showStep_master.action?id=${step.id}" >
					
					
					</iframe>
					</c:if>
				</c:forEach>
				
			</div>
		</div>
	</div>
	<!--弹出层 转发-->
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
	<!--弹出层 转发-->
	
	
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
	        disabled: true ,
	        value: ,
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