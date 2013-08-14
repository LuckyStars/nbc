/**
 * 取后面时间
 */
var openDate=${act.openDate};//距开始时间
var endTime=${act.endTime};//距结束时间
function getTimes(reduceTime) { 
	var timeStr="";
	if (reduceTime>0) {
		var day=parseInt(reduceTime/(24*60*60*1000));   
		var hour=parseInt((reduceTime/(60*60*1000)-day*24));   
		var min=parseInt(((reduceTime/(60*1000))-day*24*60-hour*60));   
		var s=parseInt((reduceTime/1000-day*24*60*60-hour*60*60-min*60));
		if(day>0){
			timeStr=day+"天";
		}
		if (hour>0) {
			timeStr+=hour+"小时";
		}
		if (min>0) {
			timeStr+=min+"分钟";
		}
		if (s>0) {
			timeStr+=s+"秒";
		}
	}
	return timeStr;
}
function showTime() {
	if(endTime>0){
		$("#star").html("报名正在继续,离结束时间还有"+getTimes(endTime));
		endTime=endTime-1000;
		if(endTime<=0){
			window.location.reload(); 
		}	
	}
	if(openDate>0){
		$("#noStar").html("距离报名开始时间还有"+getTimes(openDate));
		openDate=openDate-1000;
		if(openDate<0){
			window.location.reload(); 
			}
	}
	window.setTimeout(showTime,1000);
}

/**
 * 时间比较
 */
function duibi(a, b) {
    var arr = a.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = b.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes >= lktimes) {
        return false;
    }
    else
        return true;

}