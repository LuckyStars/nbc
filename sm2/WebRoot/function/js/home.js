
var refreshTongji = function(){
	$.post(ctxPath + '/scMaster2/findTongjifenxi_maIndex.action', function(data) {
		$("#tongji_list").html("");
		if(data.length<=0){
			return;
		}
		
		for(var i = 0;i<data.length;i++){
			var cur = data[i];
			var li = $("<li><a href='" + 
					ctxPath + cur.url + 
					"' target='fm_right'>" +
					cur.title + "</a></li>");
			li.appendTo($("#tongji_list"));
		}
		
	});
	
};

var refLinshi = function (){
	/*
	 * linshi_list
	 * */
	$.post(ctxPath + '/scMaster2/findLinshi_maIndex.action', function(data) {
		console.log(data);
		if(data.length<=0){
			return;
		}
		$("#linshi_list").html("");
		for(var i = 0;i<data.length;i++){
			
			var curData = data[i];
			var li = $("<li><a href='" + 
					ctxPath + curData.url + 
					"' target='fm_right'>" +
					curData.title + "</a></li>");
			
			li.appendTo($("#linshi_list"));
		}
	});
};

$(function(){
	refreshTongji();
	refLinshi();
});