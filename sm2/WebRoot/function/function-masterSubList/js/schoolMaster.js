 /**
  * 插绿旗 
  * @param id
  * @param flag
  * @return
  */
function stick(id,flag,banner){
	var suff=".png";
    	$.post("stick_subject.action", { subjectId: id, flag: flag },
		   function(data){
 		   		if(data=="success"){
 		   			var flag1;
 	 		   		if(flag==0){
 	 		   			flag1=1;
 	 		   		}else{
						flag1=0;
 	 		   		}
 	 		   		var a = '<a href="#" onclick="javascript:stick(\''+id+'\','+flag1+',\''+banner+'\');" id="'+id+'">';
 	 		   		var img1 = '<img src="../function/img/'+banner+flag+suff+'" class="qi"/></a>';
 	 		   		var img = a+img1;
					$("#"+id).replaceWith(img);
 		   		}
			}
    	);
    }