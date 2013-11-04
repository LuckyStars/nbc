<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${prc}/function/function-masterSubList/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${prc}/function/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
        $(function () {
            $("table tr:odd").css("background", "#f0f8fc");
            $("table tr:even").css("background", "#d5e0ee");
            $("table tr").mouseover(function () {
                $(this).css("color","#f00");
            });
            $("table tr").mouseleave(function () {
                $(this).css("color", "#000");
            });
            $(".cx1").click(function(){
                $(".bg").css("display", "block");
                $(".add").css("display", "block");
            });
            $(".add-top img").click(function () {
                $(".bg").css("display", "none");
                $(".add").css("display", "none");
            });
        });
</script>
</head>
<body>
<div class="con_conent fixed">
  <h1 class="title"><span class="title">当前位置：</span><span class="text">首页　-　校长工作台　-　</span><span class="back">临时事项</span></h1>
  <div class="table_box fixed">
    <div class="nav"> <span>提交日期:</span>
      <select>
      </select>
      <span>事项标题:</span>
      <input type="text" />
      <a class="cx" href="#">查询</a> <a class="cx1" href="#">增加</a> </div>
    <table width="100%" border="0">
      <tr>
        <th width="10%" scope="col">序号</th>
        <th width="11%" scope="col">补卡人姓名</th>
        <th width="18%" scope="col">补卡申请日期</th>
        <th width="17%" scope="col">补卡状态</th>
        <th width="18%" scope="col">补卡类型</th>
        <th width="26%" scope="col">操作</th>
      </tr>
      <tr>
        <td>1</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td><span class="space">发布</span><span class="space">查看</span><span class="space">附件</span></td>
      </tr>
      <tr>
        <td>2</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td><span class="space">编辑</span><span class="space">删除</span></td>
      </tr>
      <tr>
        <td>3</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>4</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>5</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>6</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>7</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>8</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>9</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
      <tr>
        <td>10</td>
        <td>李强</td>
        <td>2013-6-20</td>
        <td>已领取</td>
        <td>学生卡</td>
        <td></td>
      </tr>
    </table>
  </div>
</div>
<!--弹出层-->
<div class="bg"></div>
<div class="add">
  <div class="add-top">
    <p>选学生</p>
    <img src="${prc}/function/function-masterSubList/img/erro.jpg"  class="close" style="cursor:pointer;"/> </div>
  <div class="add-down">
    <div id="choice">
      <div class="nav1"> <span>事项名称：</span>
        <input type="text"  class="input"/>
        <span>关联重心工作：</span>
        <select>
        </select>
        <div class="more"> <span>事件详情：</span>
          <input type="text"  class="big"/>
        </div>
      </div>
    </div>
    <a href="#" class="return" style="margin-left:100px;">提交</a> <a href="#" class="return">返回</a> </div>
</div>
</body>
</html>
