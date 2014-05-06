package com.nbcedu.function.syllabus.devcore.util.exl.testss;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nbcedu.function.syllabus.devcore.util.exl.process.builder.ExlAnnotationBuilder;


public class AnnoBuilderTests {
	@Test
	public void encapeValueTest() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		Method m = ExlAnnotationBuilder.class.getDeclaredMethod("encapeValue",new Class[]{List.class,Integer.class});
		m.setAccessible(true);
		
		List<PersonModel> pList = new ArrayList<PersonModel>();
		PersonModel p1 = new PersonModel("p1",1);
		pList.add(p1);
		PersonModel p2 = new PersonModel("p2",2);
		pList.add(p2);
		PersonModel p3 = new PersonModel("p3",3);
		pList.add(p3);
		List<List<String>> resultList = (List<List<String>>) m.invoke(null, pList,1);
		for (List<String> list : resultList) {
			System.out.println(list);
		}
	}

	
	
}
