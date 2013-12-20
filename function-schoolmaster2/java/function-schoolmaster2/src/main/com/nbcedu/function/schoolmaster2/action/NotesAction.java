package com.nbcedu.function.schoolmaster2.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.reflect.TypeToken;
import com.nbcedu.function.schoolmaster2.biz.Sm2NotesBiz;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;
import com.nbcedu.function.schoolmaster2.data.model.Sm2Notes;
import com.nbcedu.function.schoolmaster2.utils.Utils;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class NotesAction  extends BaseAction implements ModelDriven<Sm2Notes>{
	
	private static final Logger logger = Logger.getLogger(NotesAction.class);
	
	private Sm2NotesBiz notesBiz;

	private Sm2Notes note;
	
	/***
	 * 获取相关
	 * @author xuechong
	 */
	public void getNotesBySubjectId(){
		List<Sm2Notes> results = 
			this.notesBiz.findNoteBySubUser(this.id, Utils.curUserUid());
		
		Struts2Utils.renderJson(
				Utils.gson.toJson(
						results, 
						new TypeToken<List<Sm2Notes>>(){}.getType()));
	}
	
	/**
	 * 保存或更新 
	 * @author xuechong
	 */
	public void update(){
		this.note.setUserName(Utils.curUserName());
		this.note.setUserUid(Utils.curUserUid());
		Boolean result = this.notesBiz.addOrUpdate(note)!=null;
		Struts2Utils.renderText(result.toString());
	}
	
	/**
	 * 删除
	 * @author xuechong
	 */
	public void remove(){
		Boolean result = Boolean.TRUE;
		try {
			this.notesBiz.removeById(id);
		} catch (Exception e) {
			logger.error("删除便条时出现错误", e);
			result = Boolean.FALSE;
		}
		Struts2Utils.renderText(result.toString());
	}
	
	////////////////////////////
	//////getter&setters//////
	///////////////////////////
	public void setNotesBiz(Sm2NotesBiz notesBiz) {
		this.notesBiz = notesBiz;
	}
	@Override
	public Sm2Notes getModel() {
		return this.note;
	}
	
}
