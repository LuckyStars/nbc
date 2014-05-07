package com.nbcedu.function.syllabus.devcore.util.exl.testss;

import java.util.ArrayList;
import java.util.List;

import com.nbcedu.function.syllabus.devcore.util.exl.process.AnnotationResultProcessor;


public class ExportTests {
	@org.junit.Test
	public void ex(){
		List conditionList = new ArrayList<String>();
		conditionList.add("conditons1 : 1231");
		conditionList.add("conditons2 : 1342351");
		AnnotationResultProcessor.process("asdasd",conditionList,null,null);
	}
}
