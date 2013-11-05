var allNums = [
	               {
	            	   url:"/masterDocumentFlow/sumUnHandledDocumentTask.action?userId=" + curUid,
	            	   numId:"documentNum",
	            	   name:"公文"
	               },
	               {
	            	   url:"/countWorkbench.action?module=xxdt_1&status=new?userId=" + curUid,
	            	   numId:"zongjie",
	            	   name:"总结汇报关注性工作"
	               },
	               {
	            	   url:"/countWorkbench.action?module=xxdt_2&status=new?userId=" + curUid,
	            	   numId:"qingshi",
	            	   name:"请示报批工作"
	               },
	               {
	            	   url:"/countWorkbench.action?module=xxdt_3&status=new?userId=" + curUid,
	            	   numId:"jinji",
	            	   name:"紧急重要事件处理"
	               }
               ];

var allNews = [
               		{
               			url:"/countWorkbench.action?module=ndzx&status=updated?userId=" + curUid,
               			newsId:"hexin",
               			name:"我关注的核心工作"
               		},
               		{
               			url:"/countWorkbench.action?module=ndzx_1&status=new?userId=" + curUid,
               			newsId:"deyugongzuo",
               			name:"德育工作"
               		},
               		{
               			url:"/countWorkbench.action?module=ndzx_2&status=new?userId=" + curUid,
               			newsId:"deyujiaoyan",
               			name:"教育教研工作"
               		},
               		{
               			url:"/countWorkbench.action?module=ndzx_3&status=new?userId=" + curUid,
               			newsId:"xinxihua",
               			name:"信息化工作"
               		},
               		{
               			url:"/countWorkbench.action?module=ndzx_4&status=new?userId=" + curUid,
               			newsId:"houqinweisheng",
               			name:"后勤卫生工作"
               				
               		},
               		{
               			url:"/countWorkbench.action?module=ndzx_5&status=new?userId=" + curUid,
               			newsId:"dangtuan",
               			name:"党团工作"
               		},
               		{
               			url:"/countWorkbench.action?module=ndzx_6&status=new?userId=" + curUid,
               			newsId:"caiwu",
               			name:"财务工作"
               		}
               ];


function refreshNumber(url,numId){
	url = ctxPath + url;
	$.post(url, function(data) {
		if(data>0){
			$('#' + numId).show();
			$('#' + numId).html(data);
		}else{
			$('#' + numId).hide();
		}
	});
}

function refreshNews(url,newsId){
	url = ctxPath + url;
	$.post(url, function(data) {
		if(data>0){
			$('#' + newsId).show();
		}else{
			$('#' + newsId).hide();
		}
	});
}

var refLinshi = function (){
	
	$.post(ctxPath + '/scMaster2/findLinshi_maIndex.action', function(data) {
		if(data.length<=0){
			return;
		}
		$("#linshiDiv").html("");
		var curData = $.parseJSON(data);
		for(var i = 0;i<data.length;i++){
			
			var content = $("<div class='linshi'></div>");
			var aTag = $("<a class='pointer' href='" + curData[i].url + "' target=_blank></a>");
			$("<img src='" + ctxPath +"/function/images/percent/" 
					+ curData[i].progress+
					".png' width='80' height='80'>").appendTo(aTag);
			$("<span style='color:#e1517e;'>"+ curData[i].title +"</span>").appendTo(aTag);
			aTag.appendTo(content);
			
			var flag = $("<a class='flag" + curData[i].statu + "'></a>");
			flag.appendTo(content);
			content.appendTo($("#linshiDiv"));
		}
	});
};

var daibanCount = 0;
function refreshDaiban(){
	daibanCount = 0;
	var contents = [
		                {
					 	   url:"/countWorkbench.action?module=xxdt_1&status=new?userId=" + curUid,
					 	   name:"总结汇报关注性工作"
					    },
					    {
					 	   url:"/countWorkbench.action?module=xxdt_2&status=new?userId=" + curUid,
					 	   name:"请示报批工作"
					    },
					    {
					 	   url:"/countWorkbench.action?module=xxdt_3&status=new?userId=" + curUid,
					 	   name:"紧急重要事件处理"
					    }
			       ];
	
	for ( var int = 0; int < contents.length; int++) {
		var con = contents[int];
		$.post((ctxPath + con.url), function(data) {
			if(data>0){
				daibanCount = daibanCount + parseInt(data);
			}
			if(daibanCount>0){
				$("#daibanshiyi").show();
				$("#daibanshiyi").html(daibanCount);
			}else{
				$("#daibanshiyi").hide();
			}
		});
	}
	
	
}

function refreshAll (){
	for ( var i = 0; i < allNums.length; i++) {
		var curNum = allNums[i];
		refreshNumber(curNum.url,curNum.numId);
	}
	for ( var i = 0; i < allNews.length; i++) {
		var curNews = allNews[i];
		refreshNews(curNews.url,curNews.newsId);
	}
	refreshDaiban();
	refLinshi();
}

$(function(){
	refreshAll();
});

setInterval(refreshAll,1000*60*2);