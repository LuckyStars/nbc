<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp"%>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title></title>
<link href="${prc}/function/function-week/css/index.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${prc}/function/js/easyui/themes/icon.css" />

<script src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${prc}/function/js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${prc}/function/js/datePicker/WdatePicker.js"></script>

<script type="text/javascript">
$('#managers').combotree({  
 	 url: 'findAllManager_user.action',
 	 required: true  
 	}); 
</script>
</head>
<body>
	<div class="right">
		<h1>
			当前位置：首页 - <span style="color: #dd0000">本周工作汇总</span>
		</h1>
		<div class="content">
			<h3>
				<form action="">
				发布时间： 
				<input class="Wdate" 
				name="search.updateDate" 
				value="<fmt:formatDate value='${search.updateDate}' pattern='yyyy-MM-dd'/>" 
				type="text" 
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'<fmt:formatDate value='${weekStart}' pattern='yyyy-MM-dd'/>'})"/>
				负责人： 
				
				<select style="width:120px;" id="managers" class="easyui-combotree" data-options="url:'findAllManager_user.action'" multiple="true" cascadeCheck="false" style="width:200px;"></select>
				事件状态： 
				<select name="search.status" >
					<option value="" >全部</option>
					<option value="new" >新增</option>
					<option value="updated" >更新</option>
				</select>
				事件类型： 
				<select>
					<option>全部</option>
				</select>
				
				<a class="button" id="select1">查询</a>
				</form>
			</h3>

			<h2>汪忱本周工作汇总</h2>
			<div class="workspace">
				<div class="water">
				
				<c:forEach items="${view}" var="entry" >
				<div class="workspaceleft con1">
					<div class="list-top"></div>
					<div class="listright"></div>
					<div class="listes">
						<ul class="workspacelist">
							<p class="listtitle">汪忱的本周工作${entry.value.name }</p>
							<p class="news">
								<span>本周新增</span>
							</p>
							<li>
								<span class="gary">汇总事项</span>
								<span class="gary">所属类型</span>
							</li>
							<c:forEach items="${entry.value.subs}" var="sub">
								<c:if test="${sub.status=='new' }">
								<li>
									<span class="blue">
									<a href="${prc}/scMaster2/detail_master.action?id=${sub.subId}">
									<c:out value="${sub.title}" escapeXml="true"/>
									</a>
									</span>
									<span class="gary">${sub.typeName }</span>
								</li>
								</c:if>
							</c:forEach>
							<p class="news">
								<span>本周更新</span>
							</p>
							<li>
								<span class="gary">汇总事项</span><span class="gary">所属类型</span>
							</li>
							<c:forEach items="${entry.value.subs}" var="sub">
								<c:if test="${sub.status=='updated' }">
								<li>
									<span class="blue">
									<a href="${prc}/scMaster2/detail_master.action?id=${sub.subId}">
									<c:out value="${sub.title}" escapeXml="true"/>
									</a>
									</span>
									<span class="gary">${sub.typeName }</span>
								</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</div>
				</c:forEach>
				
				
					<div class="workspaceleft con1">
						<div class="list-top"></div>
						<div class="listright"></div>
						<div class="listes">
							<ul class="workspacelist">
								<p class="listtitle">汪忱的本周工作</p>
								<p class="news">
									<span>本周新增</span>
								</p>
								<li><span class="gary">汇总事项</span><span class="gary">所属类型</span>
								</li>
								<li><span class="blue">事项一</span><span class="gary">总结报告</span>
								</li>
								<p class="news">
									<span>本周更新</span>
								</p>
								<li><span class="gary">汇总事项</span><span class="gary">所属类型</span>
								</li>
								<li><span class="blue">事项一</span><span class="gary">总结报告</span>
								</li>
							</ul>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<!--弹出层-->
	<!-- <div class="bg"></div>
      <div class="add2">
       <div class="add-top2">
           <p>负责人</p>
           <img src="images/erro.jpg"  class="close" style="cursor:pointer;"/>
       </div>
       <div class="add-down">
           <div id="choice">
               <div>
                   <h3>
                       <img src="images/img.gif" /></h3>
                   <h2>教务处</h2>
                   <p><input type="checkbox" />付航</p>
                   <p><input type="checkbox" />金强</p>
                   <p><input type="checkbox" />郭红</p>
                   <p><input type="checkbox" />李静</p>
               </div>
           </div>
           <a href="#" class="return2" style="margin-left:100px;">确定</a>
           <a href="#" class="return2">返回</a>
       </div>
          </div>-->
</body>
</html>
<script>
    $(function () {
        $("#choice img").each(function () {
            $(this).click(function () {
                if ($("#choice p").css("display") == "block") {
                    $("#choice p").css("display", "none");
                    $("img[src=${prc}/function/function-week/images/img1.gif]").attr("src", "${prc}/function/function-week/images/img.gif");
                } else {
                    $("#choice p").css("display", "block");
                    $("img[src=${prc}/function/function-week/images/img.gif]").attr("src", "${prc}/function/function-week/images/img1.gif");
                }
            });

        });
    });
</script>
<script>

    if (!Array.prototype.indexOf) {
        Array.prototype.indexOf = function (searchElement /*, fromIndex */) {
            if (this == null) {
                throw new TypeError();
            }
            var t = Object(this);
            var len = t.length >>> 0;
            if (len === 0) {
                return -1;
            }
            var n = 0;
            if (arguments.length > 1) {
                n = Number(arguments[1]);
                if (n != n) { // shortcut for verifying if it's NaN
                    n = 0;
                } else if (n != 0 && n != Infinity && n != -Infinity) {
                    n = (n > 0 || -1) * Math.floor(Math.abs(n));
                }
            }
            if (n >= len) {
                return -1;
            }
            var k = n >= 0 ? n : Math.max(len - Math.abs(n), 0);
            for (; k < len; k++) {
                if (k in t && t[k] === searchElement) {
                    return k;
                }
            }
            return -1;
        };
    }

    $.fn.waterfall = function () {
        var containerWidth = this.width();
        var $childrens = this.children();
        var boxWidth = $childrens.first().outerWidth();
        var count = Math.floor(containerWidth / boxWidth);
        var heightArr = [];
        for (var i = 0; i < count; i++) {
            heightArr[i] = 0;
        }
        for (var i = 0, j = $childrens.length; i < j; i++) {
            var minHeight = Math.min.apply(Math, heightArr);
            var index = heightArr.indexOf(minHeight);
            var $child = $childrens.eq(i);
            $child.css({ left: index * boxWidth, top: minHeight })
            heightArr[index] += $child.height();
        }
        this.height(Math.max.apply(Math, heightArr));
        var self = this;
    };
    
    
    $(".water").waterfall();
    </script>
