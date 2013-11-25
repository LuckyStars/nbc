var allNums = [
	               {
	            	   url:"/masterDocumentFlow/sumUnHandledDocumentTask.action",
	            	   numId:"documentNum",
	            	   name:"公文"
	               }
               ];

var allNews = [
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


function refreshDongtai(){
	var modules = ['qingshi','zongjie','jinji'];
	$.getJSON(ctxPath + '/scMaster2/findDongtai_maIndex.action',function(data){
		for ( var i = 0; i < modules.length; i++) {
			if(data[modules[i]]>0){
				$("#" + modules[i]).show();
				$("#" + modules[i]).html(data[modules[i]]);
			}else{
				$("#" + modules[i]).hide();
			}
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
		var curData = data;
		for(var i = 0;i<data.length;i++){
			
			var content = $("<div class='linshi'></div>");
			var aTag = $("<a class='pointer' href='" +ctxPath+ curData[i].url + "' target=_blank></a>");
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
					 	   url:"/countWorkbench.action?module=xxdt_1&status=new",
					 	   name:"总结汇报关注性工作"
					    },
					    {
					 	   url:"/countWorkbench.action?module=xxdt_2&status=new",
					 	   name:"请示报批工作"
					    },
					    {
					 	   url:"/countWorkbench.action?module=xxdt_3&status=new",
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
	refreshDongtai();
	refLinshi();
	refreshDaiban();
}

$(function(){
	refreshAll();
});

setInterval(refreshAll,1000*60*2);