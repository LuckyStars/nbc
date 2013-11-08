 /**
  * 插绿旗 
  * @param id
  * @param flag
  * @return
  */
function stick(id,flag,banner){
    	$.post("stick_subject.action", { subjectId: id, flag: flag },
		   function(data){
 		   		if(data=="success"){
 		   			var flag1;
 	 		   		if(flag==0){
 	 		   			flag1=1;
 	 		   		}else{
						flag1=0;
 	 		   		}
 	 		   		var img = '<a href="#" onclick="javascript:stick(\''+id+'\','+flag1+');" id="'+id+'"><img src="../function/img/'+banner+' id="'+id+'"/>';
					$("#"+id).replaceWith(img);
 		   		}
			}
    	);
    }