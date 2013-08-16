<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
<title>补卡列表</title>
<link href="${prc}/function/function-cardManage/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/function-cardManage/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="${prc}/common/lib/nbc-ui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${prc}/common/lib/nbc-ui/themes/icon.css"/>
<script type="text/javascript" src="${prc}/common/lib/nbc-ui/jquery.easyui.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("table tr:odd").css("background", "#f0f8fc");
        $("table tr:even").css("background", "#d5e0ee");
        $(".menu li").click(function () {
            $(".arr").attr("class", "");
            $(this).attr("class", "arr");
        });
        $(".menu li.mag_btn").click(function () {
            $(this).attr("class", "mag_btn");
        });
        var x = $(document).height();
        $(".shade").css({ "width": $(document).width(), "height": $(document).height() });
    });
    function deleteApply(id){
    	if(confirm('是否确认删除该项？'))
    		window.location.href='../cardManage/delete_apply.action?applyId='+id;
    }
</script>
</head>
<body>
<div class="con_conent fixed">
     <h1 class="title"><span class="title">补卡管理</span><span class="back"><a href="../cardManage/home.action">返回上一页</a></span></h1>
        <div class="table_box fixed">
            <div class="nav">
            <form action="../cardManage/list_apply.action" method="get">
                <span>申请日期</span>
                <select name="cmApply.month"> 
                	<option <c:if test="${'' == cmApply.month}">selected</c:if> value="">全 部</option>
                	<option <c:if test="${1 == cmApply.month}">selected</c:if> value="1">近一月</option>
                	<option <c:if test="${3 == cmApply.month}">selected</c:if> value="3">近一季</option>
                	<option <c:if test="${6 == cmApply.month}">selected</c:if> value="6">近半年</option>
                	<option <c:if test="${12 == cmApply.month}">selected</c:if> value="12">近一年</option>
                </select>
                <span>补卡人姓名</span>
                <input type="text" name="cmApply.cardUserName" value="${cmApply.cardUserName}"/>
                <span>补卡状态</span>
                <select>
                	<c:forEach items="${cmApply.cardStatus}" begin="0" end="3" step="1" var="status">
                		<option value="">${status.name}</option>
                	</c:forEach>
                </select>
                <span>补卡类型</span>
                <select id="cc" name="cmApply.cardTypeId" >
                	<option value=""> </option>
					<c:forEach items="${cardTypelist}" var="c">
						<c:choose>
							<c:when test="${c.id == cmApply.cardTypeId}">
								<option value="${c.id}" selected>${c.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${c.id}">${c.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
                <a class="cx" href="javascript:document.forms[0].submit();">查询</a>
                </form>
            </div>
        <table width="100%" border="0">
            <tr>
                <th width="10%" scope="col">序号</th>
                <th width="11%" scope="col">补卡人姓名</th>
                <th width="18%" scope="col">补卡申请日期</th>
                <th width="17%" scope="col">补卡状态</th>
                <th width="18%" scope="col">补卡类型</th>
                <th width="26%" scope="col">操作</th>
            </tr>
            <c:forEach items="${pm.datas}" var="a" varStatus="vs">
            <tr>
               <td>${vs.count}</td>
               <td>${a.cardUserName }</td>
               <td>${a.createDate }</td>
<!--               <as:showStatus statusId="${a.cardTypeId}"></as:showStatus>-->
               <td>${a.status}</td>
               <td>${a.cardType.name}</td>
               <td>
               <c:choose>
	               <c:when test="${a.status==0}">
	               		<a href="javascript:changeApply(1)"><span class="space">申请</span></a>
	               		<a href="../cardManage/toUpdate_apply.action?id=${a.id}"><span class="space">修改</span></a>
	               		<a href="javascript:deleteApply('${a.id}');"><span class="space">删除</span></a>
	               </c:when>
	                 <c:when test="${a.status==1}">
	               		<a href="javascript:changeApply(0)"><span class="space">取消申请</span></a>
	               </c:when>
	                <c:when test="${a.status==3}">
	               		<a href="javascript:changeApply(4)"><span class="space">领取确认</span></a>
	               </c:when>
	               <c:otherwise>&nbsp;</c:otherwise>
               </c:choose>
               </td>
            </tr>
            </c:forEach>
        </table>
        <div class="page">
        <pg:pager url="../cardManage/list_apply.action"
			items="${pm.total}" maxPageItems="${pm.pageSize}" maxIndexPages="3" export="currentPageNumber=pageNumber">
			<pg:param name="cmApply.cardUserName" value="${cmApply.cardUserName}"/>
			<pg:param name="cmApply.month" value="${cmApply.month}"/>
			<pg:param name="cmApply.cardTypeId" value="${cmApply.cardTypeId}"/>
			总计${pm.total}条
			<pg:first>
				<a href="${pageUrl}">首页</a>
			</pg:first>
			<pg:prev>
				<a href="${pageUrl}" >上一页</a> 
			</pg:prev>
			<pg:pages>
				<c:choose>
					<c:when test="${currentPageNumber eq pageNumber}">
						<font color="red">${pageNumber}</font>
					</c:when>
					<c:otherwise>
						<a href="${pageUrl}">${pageNumber }</a>
					</c:otherwise>
				</c:choose>
			</pg:pages>
			<pg:next>
				<a href="${pageUrl}" >下一页</a> 
			</pg:next>
			<pg:last>
				<a href="${pageUrl}">尾页</a>
			</pg:last>
		</pg:pager>
    </div>
 </div>
</div>
</body>
</html>
