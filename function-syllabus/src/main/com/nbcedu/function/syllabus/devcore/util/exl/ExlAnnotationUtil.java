package com.nbcedu.function.syllabus.devcore.util.exl;

import java.io.OutputStream;
import java.util.List;

import com.nbcedu.function.syllabus.devcore.util.exl.mapping.SheetContent;
import com.nbcedu.function.syllabus.devcore.util.exl.process.AnnotationResultProcessor;

public class ExlAnnotationUtil {
	
	@SuppressWarnings("unchecked")
	public static void export(List dataList){
		AnnotationResultProcessor.process(null, null, dataList, null);
	}
	@SuppressWarnings("unchecked")
	public static void export(List dataList,Integer viewType){
		AnnotationResultProcessor.process(null, null, dataList, viewType);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List dataList){
		AnnotationResultProcessor.process(head, null, dataList, null);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List dataList,Integer viewType){
		AnnotationResultProcessor.process(head, null, dataList, viewType);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List<String> conditions,List dataList){
		AnnotationResultProcessor.process(head, conditions, dataList, null);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List<String> conditions,List dataList,Integer viewType){
		AnnotationResultProcessor.process(head, conditions, dataList, viewType);
	}
	
	public static void export(String fileName,SheetContent[] sheetContents){
		AnnotationResultProcessor.process(fileName, sheetContents);
	}

	////////////////////////////////////////////
	/////to export with given output stream/////
	////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	public static void export(List dataList,OutputStream out){
		AnnotationResultProcessor.process(null, null, dataList, null,out);
	}
	@SuppressWarnings("unchecked")
	public static void export(List dataList,Integer viewType,OutputStream out){
		AnnotationResultProcessor.process(null, null, dataList, viewType,out);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List dataList,OutputStream out){
		AnnotationResultProcessor.process(head, null, dataList, null,out);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List dataList,Integer viewType,OutputStream out){
		AnnotationResultProcessor.process(head, null, dataList, viewType,out);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List<String> conditions,List dataList,OutputStream out){
		AnnotationResultProcessor.process(head, conditions, dataList, null,out);
	}
	
	@SuppressWarnings("unchecked")
	public static void export(String head,List<String> conditions,List dataList,Integer viewType,OutputStream out){
		AnnotationResultProcessor.process(head, conditions, dataList, viewType,out);
	}
	/**
	 * to export exl with many sheets<br>
	 * every SheetContent in sheetContents Array represents one sheet in the exported exl
	 * @param fileName
	 * @param sheetContents
	 * @author xuechong
	 */
	public static void export(String fileName,SheetContent[] sheetContents,OutputStream out){
		AnnotationResultProcessor.process(fileName,out, sheetContents);
	}
}
