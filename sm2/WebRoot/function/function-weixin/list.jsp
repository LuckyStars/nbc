<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/function/common.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>index</title>
		<meta charset="utf-8">
	   	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap.css" rel="stylesheet" media="screen"/>
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap-theme.css" rel="stylesheet" media="screen"/>
	   	<link href="${prc }/function/function-weixin/signin.css" rel="stylesheet" media="screen"/>
	   	<link href="${prc }/function/function-weixin/bs3/css/bootstrap-responsive.min.css" rel="stylesheet">
		<link href="${prc }/function/function-weixin/bs3/css/charisma-app.css" rel="stylesheet">
	   	<script src="${prc }/function/function-weixin/bs3/js/jquery.min.js"></script>
	   	<script type="text/javascript" src="${prc }/function/function-weixin/bs3/js/bootstrap.js" ></script>
	   	<script type="text/javascript">
	   	var pageSize = 1;
	   			window.onscroll=function(){
	   				//网页可见区域高
	   				var a = document.documentElement.scrollTop==0? document.body.clientHeight : document.documentElement.clientHeight;
	   				//网页被卷去的高
	   				var b = document.documentElement.scrollTop==0? document.body.scrollTop : document.documentElement.scrollTop;
	   				var c = document.documentElement.scrollTop==0? document.body.scrollHeight : document.documentElement.scrollHeight;
	   				if((b+372)>=c){
	   					loadData(pageSize);
	   				}
	   			};
	   			function loadData(pageS){
	   				$.get('${prc}/scMaster2/findLinshi_weixin.action?pageSize='+pageS, function(data) {
	   					if(data!=''){
	   						var datastr =  eval(data);
	   						var s = $(".dashboard-list");
	   						for(var i=0;i<datastr.length;i++){
	   							s.append("<li onclick=\"look(\'"+datastr[i].id+"\');\"><img class='dashboard-avatar' alt='Usman' src='${prc}//function/images/percent2/" + datastr[i].progress+".png'>"+
	   									"<strong>标题:</strong> "+datastr[i].title+"<br><strong>日期:</strong> "+datastr[i].createTime+"<br>"+
										"<strong>创建人:</strong> <span class='label label-success'>"+datastr[i].createrName+"</span>");
	   						}
	   						pageSize++;
	   					}
	   					
	   				});
	   			};
	   			loadData(pageSize);
	   			function look(id){
	   				alert(11);
			    	document.location.href="${prc}/scMaster2/detail_weixin.action?id="+id;
			    }
	   	</script>
	</head>
	<body>
		<div class="box " height="400px">
			<div class="box-header well" data-original-title>
				<div class="box-icon">
				</div>
			</div>
			<div class="box-content">
				<div class="box-content">
					<ul class="dashboard-list">
						
					</ul>
				</div>
			</div>
		</div>
	</body>
</html>