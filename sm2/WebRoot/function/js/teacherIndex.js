
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