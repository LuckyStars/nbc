package com.nbcedu.function.syllabus.utils.cache;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.nbcedu.function.syllabus.vo.ClassVO;
import com.nbcedu.function.syllabus.vo.GradeVO;
import com.nbcedu.function.syllabus.vo.SortedGrade;
import com.nbcedu.integration.uc.client.facade.BaseClient;
import com.nbcedu.integration.uc.client.vo.NbcUcClass;
import com.nbcedu.integration.uc.client.vo.NbcUcEdusys;
import com.nbcedu.integration.uc.client.vo.NbcUcGrade;

/**
 * 年级班级信息的缓存
 * @author xuechong
 */
public class CacheManager {
	
	private volatile static Map<String, ClassVO> CLASSES=null;
	private volatile static Map<String, GradeVO> GRADES=null;
	private volatile static List<SortedGrade> SORTED_GRADES=null;
	private static BaseClient baseClient =null;
	private static final Logger logger = Logger.getLogger(CacheManager.class);
	
	public static Map<String, ClassVO> getAllClasses(){
		if(CLASSES==null||CLASSES.isEmpty()){
			refresh();
		}
		return CLASSES;
	}
	
	public static Map<String, GradeVO> getAllGrades(){
		if(GRADES==null||GRADES.isEmpty()){
			refresh();
		}
		return GRADES;
	}
	
	public static List<SortedGrade> getSortedGrades(){
		if(SORTED_GRADES==null||SORTED_GRADES.isEmpty()){
			refresh();
		}
		return SORTED_GRADES;
	}
	
	/**
	 * 更新
	 * @author xuechong
	 */
	public synchronized static void refresh(){
		
		try {
			CLASSES = new HashMap<String, ClassVO>();
			GRADES = new HashMap<String, GradeVO>(12);
			if(baseClient==null){
				baseClient= new BaseClient();
			}
			
			if(logger.isInfoEnabled()){
				logger.info("更新班级年级缓存");
			}
			
			List<NbcUcEdusys> eduList = baseClient.queryEdusysList(1, null);
			for (NbcUcEdusys edusys : eduList) {
				List<NbcUcGrade> gradeList = baseClient.queryGrades(edusys.getId());
				if(gradeList==null||gradeList.isEmpty()){
					continue;
				}
				for (NbcUcGrade grade : gradeList) {
					
					List<NbcUcClass> clazzList = 
						baseClient.queryClassList(edusys.getId(), 
								Integer.valueOf(grade.getGradeNum()), 0, Integer.MAX_VALUE);
					
					Map<String, ClassVO> tempClassMap = null;
					
					if(clazzList!=null&&!clazzList.isEmpty()){
						tempClassMap = new HashMap<String, ClassVO>(clazzList.size());
						for (NbcUcClass ucClass : clazzList) {
							ClassVO clazz = 
								new ClassVO(ucClass.getId(), ucClass.getClassName(), 
										grade.getId(),ucClass.getClassNum());
							tempClassMap.put(clazz.getId(), clazz);
						}
						CLASSES.putAll(tempClassMap);
					}
					
					GradeVO gradeVO = new GradeVO(grade.getId(),grade.getGradeName(), 
							tempClassMap!=null?tempClassMap:new HashMap<String, ClassVO>(),
									Integer.valueOf(grade.getGradeNum()));
					
					GRADES.put(grade.getId(), gradeVO);
				}
			}
			sortGrades();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新班级年级缓存时出错",e);
		}
		
	}

	private static void sortGrades() {
		
		SORTED_GRADES = new ArrayList<SortedGrade>(GRADES.size());
		TreeSet<GradeVO> gradeVoSet = new TreeSet<GradeVO>(new Comparator<GradeVO>() {
			@Override
			public int compare(GradeVO g1, GradeVO g2) {
				return g1.getGradeNum()>g2.getGradeNum()?1:
						g1.getGradeNum()==g2.getGradeNum()?0:-1;
			}
		});
		gradeVoSet.addAll(GRADES.values());
		
		for (GradeVO gradeVO : gradeVoSet) {
			SortedGrade grade = new SortedGrade();
			grade.setGradeName(gradeVO.getGradeNum() + "年级");
			TreeSet<ClassVO> classSet = new TreeSet<ClassVO>(new Comparator<ClassVO>() {
				@Override
				public int compare(ClassVO clazz1, ClassVO clazz2) {
					return clazz1.getClassNum()>clazz2.getClassNum()?1:
							clazz1.getClassNum()==clazz2.getClassNum()?0:-1;
				}
			});
			classSet.addAll(gradeVO.getAllClasses().values());
			grade.setClasses(new ArrayList<ClassVO>(classSet));
			SORTED_GRADES.add(grade);
		}
		
	}
}
