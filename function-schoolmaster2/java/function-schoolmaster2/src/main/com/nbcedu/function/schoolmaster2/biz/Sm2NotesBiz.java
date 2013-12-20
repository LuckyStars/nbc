package com.nbcedu.function.schoolmaster2.biz;

import java.util.List;

import com.nbcedu.function.schoolmaster2.core.biz.BaseBiz;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Notes;

public interface Sm2NotesBiz extends BaseBiz<Sm2Notes>{
	
	public List<Sm2Notes> findNoteBySubId(String subjectId);
	
	public List<Sm2Notes> findNoteBySubUser(String subjectId,String uid);
}
