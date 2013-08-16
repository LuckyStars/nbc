<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
<title>补卡申请</title>
<link href="${prc}/function/function-cardManage/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/function-cardManage/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="${prc}/common/lib/nbc-ui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${prc}/common/lib/nbc-ui/themes/icon.css"/>
<script type="text/javascript" src="${prc}/common/lib/nbc-ui/jquery.easyui.min.js"></script>
<script>
    $(function () {
        $("#divselect cite").click(function () {
            if ($("#divselect ul").css("display") == "none") {
                $("#divselect ul").slideDown();
            } else {
                $("#divselect ul").slideUp();
            }
            return false;
        });

        $("#divselect li").each(function () {
            $(this).click(function () {
                $("#divselect cite").html($(this).text());
                $("#inputselect").val($(this).text());
            });

        });
       $("#cc").click(function () { 
           	var val = $('#cc').combobox('getValue');
			alert(val); 
		//	if()
            $(".class").css("display", "none");
            $(".cx2").css("display", "none");
            $("#cardUserName").val();
      
        //	$("#cardUserName").val("");
          //  $(".class").css("display", "block");
            //$(".cx2").css("display", "block");
        });
        $(".cx2").click(function () {
            $("#classdiv").css("display", "block");
            var classID = $("input[name='cmApply.cardClassId']")[0].value; 
            if(classID==""){
				alert("请先选择班级！");
				return;
            }else{
	        	$('#tt2').tree({
	    			checkbox: false,
	    			url:'../cardManage/studentTree_tree.action?classID='+classID,
	    			onClick:function(node){
	    				$(this).tree('toggle', node.target);
	    				$("#cardUserName").val(node.text);
	    				//alert('you click '+node.text);
	    			},
	    			onContextMenu: function(e, node){
	    				e.preventDefault();
	    				$('#tt2').tree('select', node.target);
	    				$('#mm').menu('show', {
	    					left: e.pageX,
	    					top: e.pageY
	    				});
	    			},
	    			onDblClick:function(node){
	    				$("#classdiv").css("display", "none");
	        		}
	    		});
    		}
        });
        $(document).click(function () {
            $("#divselect ul").hide();
        });
	
    });

    $(function () {
        $("#choice img").each(function () {
            $(this).click(function () {
                if ($("#choice p").css("display") == "block") {
                    $("#choice p").css("display","none");
                    $("img[src=img/img1.gif]").attr("src", "img/img.gif");
                } else {
                    $("#choice p").css("display", "block");
                    $("img[src=img/img.gif]").attr("src", "img/img1.gif");
                }
            });
        });
    });
    function sure(){
    	$("#classdiv").css("display", "none");
    }
    function back(){
    	$("#classdiv").css("display", "none");
    }
</script>
</head>
<body>
<div class="con_conent fixed">
     <h1 class="title"><span class="title">补卡管理</span><span class="back"><a href="../cardManage/home.action">返回上一页</a></span></h1>
      	<form action="../cardManage/apply_apply.action" method="post">
        <div class="table_box fixed">
          <div class="type2">
              <span>补卡类型</span>
              <div id="divselect">
<!--                  <cite>请选择分类</cite>-->
<!--                  <ul>-->
<!--                      <li class="teacher"><a href="javascript:;" selectid="0">教师补卡</a></li>-->
<!--                      <li class="student"><a href="javascript:;" selectid="1" name="cmApply.cardType.id">学生补卡</a></li>-->
<!--                  </ul>-->
				<select id="cc" class="easyui-combobox" name="cmApply.cardTypeId" style="width:200px;" required="true">
					<c:forEach items="${cardTypelist}" var="c">
						<c:out value="${c.type}"></c:out>  
						<c:if test="${c.type==1}">
							<option value="${c.id}" selected>${c.name}</option>
						</c:if>
						<c:if test="${c.type!=1}">
						<option value="${c.id}">${c.name}</option>
						</c:if>
					</c:forEach>
				</select>
              </div>
          </div>
          <div class="class">
              <span>班　　级</span>
              <span style=" margin-left: 25px;">
              	<input class="easyui-combotree" name="cmApply.cardClassId" multiple="false" cascadeCheck="true" url="${prc}/cardManage/classTree_tree.action" />
              </span>
          </div>
            <div class="name">
              <span>补卡人姓名</span>
                <div>
		            <input id="cardUserName" class="easyui-validatebox" type="text" name="cmApply.cardUserName" required="true" readonly="readonly"></input>
		            <a class="cx2" >选学生</a>
	        	</div>
          	</div>
            <div class="reason">
              <span>补卡原因</span>
              <input type="text"/>
          </div>
              <a href="javascript:document.forms[0].submit();" class="return1" style="margin-left:260px;">申请</a>
           <a href="#" class="return1">保存</a>
    </div>
    </form>
     <!--弹出层-->
     <div id="classdiv" style="display: none;">
    	<div class="bg"></div>
      	<div class="add2">
	      	<div class="add-top2">
	           <p>选学生</p>
	           <img src="${prc}/function/function-cardManage/image/erro.jpg" onclick="back();" class="close" style="cursor:pointer;"/>
	        </div>
	       	<div class="add-down">
	           <div id="choice">
<!--                   <h3><img src="${prc}/function/function-cardManage/image/img.gif" /></h3>-->
                   <ul id="tt2"></ul>
	           </div>
<!--	           <a href="javascript:sure();" class="return2" style="margin-left:100px;">确定</a>-->
<!--	           <a href="javascript:back();" class="return2">返回</a>-->
	       </div>
       </div>
    </div>
</div>
  
</body>
</html>

