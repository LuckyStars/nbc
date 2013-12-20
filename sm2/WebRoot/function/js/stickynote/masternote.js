/**容器**/
var parentDiv = "#master_notes";

var initNotes = function(subjectId){
	$.post(ctxPath + '/scMaster2/getNotesBySubjectId_note.action?id='+subjectId,function(data){
		if(data && data.length>0){
			for(var i = 0;i < data.length;i++){
				var option = data[i];
				option.zIndex = i*10 + 100;
				option.saveOnCreate = false;
				createNote(option);
			}
		}
	});	
};

var newNote = function(){
	var option = new Object();
	option.saveOnCreate = true;
	option.top = '200px';
	option.left = '200px';
	createNote(option);
}

var createNote = function(options){

	if(!options.id){
		options.id = UUID.generate();
	}
	options.containment = parentDiv;

	options.beforeClose = function(notesData){
		var result = false;
		$.ajax({
			url:ctxPath + '/scMaster2/remove_note.action',
			async:false,
			data : {id:notesData.id}
		}).done(function(msg){if(msg=='true'){result=true;}});
		return result;
	};

	options.save = function(notesData){
		var result = false;
		$.ajax({
			url:ctxPath + '/scMaster2/update_note.action',
			async:false,
			data : notesData
		}).done(function(msg){if(msg=='true'){result=true;}});
		return result;
	};

	$("<div id='" + options.id +"' ></div>").appendTo($(parentDiv));
	$("#" + options.id).StickyNotes(options);
};

$(function(){
	initNotes();
});
