﻿var allNums = [
	               {
	            	   url:"/masterDocumentFlow/sumUnHandledDocumentTask.action",
	            	   numId:"documentNum",
	            	   name:"公文"
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

var refreshZhongxin= function(){
	var zhongxinTypes = new Array('caiwu','dangtuan','deyu','houqinweisheng','jiaoyujiaoyan','xinxihua');
	
	$.post(ctxPath + '/scMaster2/findZhongxin_maIndex.action', function(data) {
		if(data.length<=0){
			return;
		}
		for(var i = 0;i< zhongxinTypes.length;i++){
			var ty = zhongxinTypes[i];
			var flag = $("#" + ty + "_flag");
			if(data[ty]>0){
				flag.show();
			}else{
				flag.hide();
			}
		}
	});
	
};


var refreshTongji = function(){
	/*
				<a href="" >
					<span class="tongjidiv pointer" style="background-color: #d48bde;" >
						<img class="tongjidivImg" src="${prc}/function/images/tongji/dianjiao.png" />
						<span class="tongjidivText">电教服务统计</span>
					</span>
				</a>
	 
	 */
	$.post(ctxPath + '/scMaster2/findTongjifenxi_maIndex.action', function(data) {
		
		if(data.length<=0){
			return;
		}
		
		for(var i = 0;i<data.length;i++){
			var cur = data[i];
			var par = $("<a href='" + cur.url + "' ></a>");
			var sp = $("<span class='tongjidiv pointer' style='background-color: " 
					+ cur.color +";' >"
					+ "</span>");
			var img = $("<img class='tongjidivImg' src='${prc}/function/images/tongji/"+cur.icon+".png' />");
			var tit = $("<span class='tongjidivText'>" + cur.title+ "</span>");
			tit.appendTo(sp);
			img.appendTo(sp);
			sp.appendTo(par);
			par.appendTo($("#tongjib"));
		}
		
	});
	
	
};

function refreshAll (){
//	for ( var i = 0; i < allNums.length; i++) {
//		var curNum = allNums[i];
//		refreshNumber(curNum.url,curNum.numId);
//	}
	refreshDongtai();
	refLinshi();
	refreshZhongxin();
	refreshTongji();
}

$(function(){
	refreshAll();
});

setInterval(refreshAll,1000*60*2);