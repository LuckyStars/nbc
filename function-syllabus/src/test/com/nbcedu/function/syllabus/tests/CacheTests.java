package com.nbcedu.function.syllabus.tests;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

import org.junit.Test;

import com.nbcedu.function.syllabus.utils.cache.CacheManager;
import com.nbcedu.function.syllabus.vo.ClassVO;
import com.nbcedu.function.syllabus.vo.GradeVO;

public class CacheTests {
	@Test
	public void test(){
		Map<String, ClassVO> clazzMap = CacheManager.getAllClasses();
		iterateMap(clazzMap);
		System.out.printf("班级总数:%d\n",clazzMap.size());
		Map<String, GradeVO> gradeVo = CacheManager.getAllGrades();
		iterateMap(gradeVo);
		System.out.printf("年级总数:%d\n",gradeVo.size());
		CacheManager.refresh();
	}
	
	private<T extends Serializable> void iterateMap(Map<String, T> map){
		for (String key : map.keySet()) {
			System.out.print(key + "||||");
			System.out.println(map.get(key) + "|||" + getName(map.get(key)));
		}
	}
	
	private String getName(Object o){
		String result = "";
		Method m = null;
		try {
			m = o.getClass().getMethod("getName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			result = (String) m.invoke(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
