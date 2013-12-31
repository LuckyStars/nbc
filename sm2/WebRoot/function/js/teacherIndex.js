
function refreshNumber(url){
	$.get(url, function(data) {
		  $.each(data, function(i,item){
			  $("#"+item.moduleId).show();
			 $("#"+item.moduleId).text(item.num);
		});
		
	});
}

function refreshAll (){
	refreshNumber("findStatusCount_subject.action");
}

$(function(){
	refreshAll();
});

setInterval(refreshAll,1000*60*2);