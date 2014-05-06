package com.nbcedu.function.syllabus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nbcedu.function.syllabus.dao.SLBLessonDao;
import com.nbcedu.function.syllabus.devcore.dao.impl.ExtendsHibernateBaseDaoImpl;
import com.nbcedu.function.syllabus.model.SLBLesson;
import com.nbcedu.function.syllabus.vo.LessonVo;

@Repository("slbLessonDao")
public class SLBLessonDaoImpl extends ExtendsHibernateBaseDaoImpl<SLBLesson> implements SLBLessonDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SLBLesson> findBy(LessonVo lessonVo) {
		String hql = "FROM SLBLesson l WHERE l.courseId=:couId AND l.classId=:claId";
		Query q = getSession().createQuery(hql);
		q.setString("couId", lessonVo.getCourseId());
		q.setString("claId", lessonVo.getClassId());
		return q.list();
	}
	
}
