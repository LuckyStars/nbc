<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<%@ taglib prefix="invStatus" uri="InvStatus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${prc}/function/function-invatition/masterShow/css/index.css" rel="stylesheet" type="text/css" />
<link href="${prc}/function/function-invatition/masterShow/css/gzt.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.9.1.min.js"></script>
<script>
		var prc ="${pageContext.request.contextPath}";
		var id="${tsm2Invatition.id}";
		var score="${tsm2Invatition.score}";
        $(function () {
    	    var flag="${tsm2Invatition.flag}";
    	    if(flag=="0"){
    	        $(".article").hide();
    	        $(".fu").show();
    	    }else{
    	        $(".article").show();
    	        $(".fu").hide();
    	    }
    	    if(score > -1){
        	    var imgs = $(this).find('img');
                for(var j=0;j<=score;j++){
                    imgs.eq(j).attr('src', "${prc}/function/function-invatition/masterShow/images/star-on-big.png");
                }
        	}else{
        	    ; (function ($) {
        	        $.fn.star = function (options) {
        	            var setings = {
        	                both: '',
        	                on: '',
        	                half: ''
        	            };
        	            $.extend(setings, options)
        	            var imgs = $(this).find('img'), tags = true;
        	            var index1=0;
        	            imgs.each(function (index) {
        	                $(this).mousemove(function (e) {
        	                    tags = true;
        	                    imgs.attr('src', setings.both);
        	                	index1 = index;
        	                    for (var i = 0; i < index; i++) {
        	                        imgs.eq(i).attr('src', setings.on);
        	                    }
        	                    var x = e.screenX - $(this).parent().offset().left;
        	                    if (x / 24 < Number(index) + 0.5) {
        	                    } else {
        	                        imgs.eq(index).attr('src', setings.on);
        	                    }
        	                    if (x <= 0) {
        	                        imgs.eq(0).attr('src', setings.both);
        	                    }
        	                });
        	            });
        	            $(this).click(function () {
        	                tags = false;
        	                $(this).find('img').unbind('mousemove');
        	                $(this).unbind('click');
        	        		$.ajax({
        	        			url:prc+"/scMaster2/modifyScore_invatition.action",
        	        			type:'post',
        	        			data:{"tsm2Invatition.id":id,score:index1,"t":Math.random()},
        	        			dataType:'json',
        	        			success:function(data){
        	        			},
        	        			error:function(XMLHttpRequest, textStatus, errorThrown){
        	        				alert("出错了!");
        	        				window.location.href="${prc}/scMaster2/masterShow_invatition.action?tsm2Invatition.id=${tsm2Invatition.id}";
        	        			}
        	            	});
        	            });
        	            $(this).mouseleave(function () {
        	                if (tags) {
        	                    imgs.attr('src', setings.both);
        	                }
        	            });
        	        };
        	    }(jQuery));
        	    $('#star').star({ both: '${prc}/function/function-invatition/masterShow/images/star-off-big.png', on: '${prc}/function/function-invatition/masterShow/images/star-on-big.png' });
            }
            $("#comment").click(function () {
                var _content = $.trim($("#content").val());
                if(_content ==""){
                    alert("评论内容不能为空！");
                    return false;
                }
        		$.ajax({
        			url:prc+"/scMaster2/comment_invatition.action",
        			type:'post',
        			data:{"tsm2Invatition.id":id,fileName:_content,"t":Math.random()},
        			dataType:'json',
        			success:function(data){
        				window.location.href="${prc}/scMaster2/masterShow_invatition.action?tsm2Invatition.id=${tsm2Invatition.id}";
        			},
        			error:function(XMLHttpRequest, textStatus, errorThrown){
        				alert("出错了!");
        				window.location.href="${prc}/scMaster2/masterShow_invatition.action?tsm2Invatition.id=${tsm2Invatition.id}";
        			}
            	});
            });
            $("#img").each(function () {
                $(this).click(function () {
                    if ($(".conshen").css("display") == "block") {
                        $(".conshen").css("display", "none");
                        $(this).attr("src", "${prc}/function/function-invatition/masterShow/images/down.jpg");
                    } else {
                        $(".conshen").css("display", "block");
                        $(this).attr("src", "${prc}/function/function-invatition/masterShow/images/up1.jpg");
                    }
                });
            });
        });
        
    </script>
</head>
<body style="background:#F9F9F9;"> 
       <div class="con_conent fixed" style="width:99%;background:#F9F9F9;">
  <div class="table_box fixed">
    <div class="content" style="margin-left:10px;">
      <h2>${tsm2Invatition.title}</h2>
      <div class="on" style="width:99%">
	      <h3><span>发布日期：</span>
      		<a class="ongray"><s:date name="tsm2Invatition.createTime" format="yyyy年MM月dd日"/></a>
      		<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邀请人：</span><a class="ongray">${tsm2Invatition.createrName}</a>
	      </h3>
	      <div id="star" style="width: 25%; height: 24px;float:right;margin-right:15px">
	        <img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" />
	        <img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" />
	        <img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" />
	        <img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" />
	        <img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" />
	        <img src="${prc }/function/function-linshi/img/back.png" alt="返回" onclick="history.back();"
						style="height:20px;margin-left:1px;float:right;"/>
	    	</div>
       </div>
      
      <div class="articles" style="width:95%;">${tsm2Invatition.content}</div>
      <div class="tabs-wp" style="width:99%">
        <ul class="tabs">
          <s:if test="tsm2Invatition.flag==0">
          	<li class="curs cur">附件</li>
          </s:if><s:else>
            <li class="lian cur">链接</li>
          </s:else>
        </ul> </div>
      <div class="article" style="height:20px;width:97%">
        <p><a href="${tsm2Invatition.link}" target="_blank">链接地址点击查看</a></p>
      </div>
         <ul class="fu">
         <s:iterator value="tsm2Resources">
            <li>${fileName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="downfile" href="${prc}/scMaster2/download_invatition.action?tsm2Invatition.id=${id}">下载</a></li>
         </s:iterator>
        </ul>
     
      <div class="box" style="width:99%;">
        <div class="box-top" style="width:100%;">
          <ul>
            <li class="two"><a href="javascript:void(0);">校长评论</a><img src="${prc}/function/function-invatition/masterShow/images/up1.jpg"  id="img" style="margin-top:10px; display:block; float:left; cursor:pointer;"/></li>
          </ul>
        </div>
        <div class="conshen box-down">
          <input type="text"  class="erro" id="content" style="width:99%"/>
          <a href="javascript:void(0);" class="btn" id="comment">发表</a>
          <s:iterator value="tsm2MasterComments">
          <dl class="new">
            <dt></dt>
            <dd>
              <p><span class="blue">${createrName}：</span>${content}<br />
              </p>
             <span class="gray float"><s:date name="createtime" format="yyyy年MM月dd日HH时mm分ss秒"/></span></dd>
             <s:iterator value="replys">
             <div class="clears"><p><img src="${prc}/function/function-invatition/masterShow/img/tu.jpg" /></p></div>
              <dd class="spaceing">
              <p><span class="blue">${createrName}：</span>${content}<br />
              </p>
              <span class="gray float"><s:date name="createtime" format="yyyy年MM月dd日HH时mm分ss秒"/></span><span class="answ"></span> </dd>
             	  <div style="clear:both;"></div>
          	 </s:iterator>
          </dl>
          </s:iterator>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>