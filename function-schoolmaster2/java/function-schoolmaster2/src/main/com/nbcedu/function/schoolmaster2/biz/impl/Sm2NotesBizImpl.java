package com.nbcedu.function.schoolmaster2.biz.impl;

import java.util.List;

import com.nbcedu.function.schoolmaster2.biz.Sm2NotesBiz;
import com.nbcedu.function.schoolmaster2.core.biz.impl.BaseBizImpl;
import com.nbcedu.function.schoolmaster2.dao.Sm2NotesDao;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Notes;

public class Sm2NotesBizImpl extends BaseBizImpl<Sm2Notes> implements Sm2NotesBiz{
	
	private Sm2NotesDao notesDao;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Sm2Notes> findNoteBySubId(String subjectId) {
		return this.notesDao.
		createQuery("FROM Sm2Notes t WHERE t.parentId = ?",subjectId).list();
	}

	////////////////////////
	////getters&setters/////
	////////////////////////
	public void setNotesDao(Sm2NotesDao notesDao) {
		super.setDao(notesDao);
		this.notesDao = notesDao;
	}
	
}
