package com.nbcedu.function.syllabus.dao;

import java.util.List;

import com.nbcedu.function.syllabus.devcore.dao.HibernateBaseDao;
import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.vo.LessonVo;

public interface SLBLessonDao  extends HibernateBaseDao<SLBLesson>{

	
	@SuppressWarnings("unchecked")
	public List findBy(LessonVo t);
}
