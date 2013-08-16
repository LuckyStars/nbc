<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
<title>补卡管理</title>
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

$(function(){
	var lastIndex;
	$('#tt').datagrid({
		toolbar:[{
			text:'append',
			iconCls:'icon-add',
			handler:function(){
				$('#tt').datagrid('endEdit', lastIndex);
				$('#tt').datagrid('appendRow',{
					productid:'',
					unitcost:''
				});
				lastIndex = $('#tt').datagrid('getRows').length-1;
				$('#tt').datagrid('selectRow', lastIndex);
				$('#tt').datagrid('beginEdit', lastIndex);
			}
		},'-',{
			text:'delete',
			iconCls:'icon-remove',
			handler:function(){
				var row = $('#tt').datagrid('getSelected');
				if (row){
					var index = $('#tt').datagrid('getRowIndex', row);
					$('#tt').datagrid('deleteRow', index);
				}
			}
		},'-',{
			text:'accept',
			iconCls:'icon-save',
			handler:function(){
				$('#tt').datagrid('acceptChanges');
			}
		},'-',{
			text:'reject',
			iconCls:'icon-undo',
			handler:function(){
				$('#tt').datagrid('rejectChanges');
			}
		},'-',{
			text:'GetChanges',
			iconCls:'icon-search',
			handler:function(){
				var rows = $('#tt').datagrid('getChanges');
				alert('changed rows: ' + rows.length + ' lines');
			}
		}],
		onBeforeLoad:function(){
			$(this).datagrid('rejectChanges');
		},
		onClickRow:function(rowIndex){
			if (lastIndex != rowIndex){
				$('#tt').datagrid('endEdit', lastIndex);
				$('#tt').datagrid('beginEdit', rowIndex);
			}
			lastIndex = rowIndex;
		}
	});
});
</script>
</head>
<body>
<div class="con_conent fixed">
     <h1 class="title"><span class="title">补卡管理</span><span class="back"><a href="../cardManage/home.action">返回上一页</a></span></h1>
        <div class="table_box fixed">
            <div class="nav1">
            <form action="../cardManage/list_apply.action" method="post">
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
                <span>补卡类型</span>
                <select></select>
                <span>班级</span>
                <select></select>
                <span>补卡状态</span>
                <select></select>
                <span>申请人姓名</span>
                <input type="text" name="cmApply.applyUserName" value="${cmApply.applyUserName}"/>
                <a class="cx" href="javascript:document.forms[0].submit();">查询</a>
                <a class="cx1" href="#">补卡类型维护</a>
                <a class="cx1" href="#">导出数据</a>
            </form>
            </div>
        <table width="100%" border="0">
            <tr>
                <th width="7%" scope="col">序号</th>
                <th width="11%" scope="col">补卡人姓名</th>
                <th width="13%" scope="col">班级</th>
                <th width="12%" scope="col">申请人</th>
                <th width="18%" scope="col">申请日期</th>
                <th width="15%" scope="col">补卡状态</th>
                <th width="26%" scope="col">操作</th>
            </tr>
         <c:forEach items="${pm.datas}" var="a" varStatus="vs">
            <tr>
               <td>${vs.count}</td>
               <td>${a.cardUserName}</td>
               <td>${a.cardClassName}</td>
               <td>${a.applyUserName}</td>
               <td>${a.createDate }</td>
<!--               <as:showStatus statusId="${a.cardTypeId}"></as:showStatus>-->
               <td>${a.status}</td>
               <td>
               <c:choose>
	           
	                 <c:when test="${a.status==1}">
	               		<a href="javascript:changeApply(0)"><span class="space">办理</span></a>
	               </c:when>
	                <c:when test="${a.status==2}">
	               		<a href="javascript:changeApply(3)"><span class="space">通知领取</span></a>
	               </c:when>
	                <c:when test="${a.status==3}">
	               		<a href="#"><span class="space">提醒领取</span></a>
	               </c:when>
	               <c:otherwise>&nbsp;</c:otherwise>
               </c:choose>
               </td>
            </tr>
            </c:forEach>
        </table>
        <div class="page">
        <pg:pager url="../cardManage/manageList_apply.action"
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
  <!--弹出层-->
    <div class="bg"></div>
      <div class="add" style="width:540px;height:380px;"> 
<!--       <div class="add-top">-->
<!--           <p>补卡类型维护</p>-->
<!--           <img src="img/erro.jpg"  class="close" style="cursor:pointer;"/>-->
<!--       </div>-->
<!--       <div class="add-down">-->
<!--           <p class="save"><span>补卡类型</span><input type="text" /></p>-->
<!--           <div class="team">-->
           <table width="100%" border="0" id="tt" title="Editable DataGrid" iconCls="icon-edit" singleSelect="true" 
           url="../function/function-cardManage/pages/datagrid_data2.json" 	rownumbers="true" pagination="true">
           <thead> 
            <tr>
                <th width="30%" scope="col"  style=" background:#D3DDE7" field="unitcost">补卡类型</th>
                <th width="40%" scope="col"  style=" background:#D3DDE7" field="productid">操作</th>
            </tr>
            </thead>
<!--            <tr>-->
<!--                <td>1</td>-->
<!--                <td>学生卡</td>-->
<!--                <td><span class="space">修改</span><span class="space">删除</span></td>-->
<!--            </tr>-->
<!--            <tr>-->
<!--                <td>2</td>-->
<!--                <td>学生卡</td>-->
<!--                <td><span class="space">修改</span><span class="space">删除</span></td>-->
<!--            </tr>-->
<!--           <tr>-->
<!--                <td>3</td>-->
<!--                <td>学生卡</td>-->
<!--                <td><span class="space">修改</span><span class="space">删除</span></td>-->
<!--            </tr>-->
        </table>
<!--           </div>-->
<!--           <a href="#" class="return" style="margin-left:100px;">确定</a>-->
<!--           <a href="#" class="return">返回</a>-->
       </div>
<!--    </div>  -->
</body>
</html>
