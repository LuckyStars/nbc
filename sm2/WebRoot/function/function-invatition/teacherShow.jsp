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
<script type="text/javascript" src="${por}/common/agent.js"></script>
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
        	}
            $(".reply").click(function () {
            	var commentId= $(this).attr("id");
            	var _content=$.trim($(this).parent().prev().val());
                if(_content ==""){
                    alert("回复内容不能为空！");
                    return false;
                }
        		$.ajax({
        			url:prc+"/scMaster2/reply_invatition.action",
        			type:'post',
        			data:{"tsm2Invatition.id":commentId,fileName:_content,"t":Math.random()},
        			dataType:'json',
        			success:function(data){
        				window.location.href="${prc}/scMaster2/teacherShow_invatition.action?tsm2Invatition.id=${tsm2Invatition.id}";
        			},
        			error:function(XMLHttpRequest, textStatus, errorThrown){
        				alert("出错了!");
        				window.location.href="${prc}/scMaster2/teacherShow_invatition.action?tsm2Invatition.id=${tsm2Invatition.id}";
        			}
            	});
            });
            $(".answ").click(function () {
            	$(this).parent().next().show();
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
<body>
  <div class="con_conent fixed">
  <h1 class="title"><span class="title">当前位置：</span><span class="text"><a href="${prc}/scMaster2/teacherInput_index.action">首页</a>　-　</span><span class="back">给校长发出的邀请</span></h1>
  <div class="table_box fixed">
    <div class="content">
      <h2>${tsm2Invatition.title}</h2>
        <div class="on">
      <h3><span>发布日期：</span><a class="ongray"><s:date name="tsm2Invatition.createTime" format="yyyy年MM月dd日"/></a><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邀请人：</span><a class="ongray">${invatitionVo.name}</a>
      </h3>
        <div id="star" style="width: 184px; height: 24px;float:right">
        <img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" /><img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" /><img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" /><img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" /><img src="${prc}/function/function-invatition/masterShow/images/star-off-big.png" />
    </div>
            </div>
      <div class="articles">${tsm2Invatition.content}</div>
      <div class="tabs-wp">
        <ul class="tabs">
          <s:if test="tsm2Invatition.flag==0">
          	<li class="curs cur">附件</li>
          </s:if><s:else>
            <li class="lian cur">链接</li>
          </s:else>
        </ul> </div>
      <div class="article" style="height:20px;">
        <p><a href="${tsm2Invatition.link}" target="_blank">链接地址点击查看</a></p>
      </div>
         <ul class="fu">
         <s:iterator value="tsm2Resources">
            <li>${fileName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="downfile" href="${prc}/scMaster2/download_invatition.action?tsm2Invatition.id=${id}">下载</a></li>
         </s:iterator>
        </ul>
     
      <div class="box">
        <div class="box-top">
          <ul>
            <li class="two"><a href="javascript:void(0);">校长评论</a><img src="${prc}/function/function-invatition/masterShow/images/up1.jpg"  id="img" style="margin-top:10px; display:block; float:left; cursor:pointer;"/></li>
          </ul>
        </div>
        <div class="conshen box-down">
        <s:if test="tsm2MasterComments.size()>0">
          <s:iterator value="tsm2MasterComments">
          <dl class="new">
            <dt><img src="${prc}/function/function-invatition/masterShow/img/tu.jpg" /></dt>
            <dd>
              <p><span class="blue">${createrName}：</span>${content}<br />
              </p>
              <span class="gray float"><s:date name="createtime" format="yyyy年MM月dd日HH时mm分ss秒"/></span><span class="answ"><a href="javascript:void(0);">回复</a></span> </dd>
               <dd class="fa">
                   <textarea name="dasdda"></textarea>
                   <p><a href="javascript:void(0);" class="reply" id="${id}">发布</a></p>
              </dd>
              
             <s:iterator value="replys">
             <div class="clears"><p><img src="${prc}/function/function-invatition/masterShow/img/tu.jpg" /></p></div>
              <dd class="spaceing">
              <p><span class="blue">${createrName}：</span>${content}<br />
              </p>
              <span class="gray float"><s:date name="createtime" format="yyyy年MM月dd日HH时mm分ss秒"/></span><span class="answ"></span> </dd>
             	  <div style="clear:both;"></div>
          	 </s:iterator>
          	 <div style="clear:both;"></div>
          </dl>
          </s:iterator>
		</s:if>
		<s:else> &nbsp;&nbsp;&nbsp;没有评论</s:else>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
sethash();
</script>
</body>
</html>