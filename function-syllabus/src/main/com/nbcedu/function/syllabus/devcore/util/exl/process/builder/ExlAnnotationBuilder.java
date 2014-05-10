package com.nbcedu.function.syllabus.devcore.util.exl.process.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import com.nbcedu.function.syllabus.devcore.util.exl.annotations.ExlData;
import com.nbcedu.function.syllabus.devcore.util.exl.annotations.ExlModel;
import com.nbcedu.function.syllabus.devcore.util.exl.mapping.BookDataMapping;
import com.nbcedu.function.syllabus.devcore.util.exl.mapping.SheetContent;


public class ExlAnnotationBuilder {
	
	/**
	 * build book 
	 * @param head
	 * @param conditions
	 * @param dataList
	 * @param viewType
	 * @return
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	public static Workbook buildAnnoWorkBook(String head, List<String> conditions,
			List dataList, Integer viewType) {
		if(viewType==null){
			viewType = 0;
		}
		Workbook book = new HSSFWorkbook();
		book = ExlBuilder.buildHead(head,book);
		book = ExlBuilder.buildConditions(conditions, book,0);
		BookDataMapping bookData = new BookDataMapping();
		bookData.setTitles(encapeTitles(dataList.get(0),viewType));
		bookData.setDatas(encapeValue(dataList, viewType));
		ExlBuilder.buildDatas(book, bookData,0);
		return book;
	}
	public static Workbook buildAnnoWorkBook(SheetContent content,Workbook book,int sheetIndex) {
		Integer viewType = content.getViewType();
		if(viewType==null){
			viewType = 0;
		}
		book = ExlBuilder.buildHead(content.getHead(),book);
		book = ExlBuilder.buildConditions(content.getConditions(), book,sheetIndex);
		BookDataMapping bookData = new BookDataMapping();
		bookData.setTitles(encapeTitles(content.getDataList().get(0),viewType));
		bookData.setDatas(encapeValue(content.getDataList(), viewType));
		ExlBuilder.buildDatas(book, bookData,sheetIndex);
		return book;
	}
	
	/**
	 * encape the titles
	 * @param data
	 * @param viewType
	 * @return
	 * @author xuechong
	 */
	private static List<String> encapeTitles(Object data,Integer viewType){
		List<String> result = null;
		Field[] fiedls = data.getClass().getDeclaredFields();
		Map<Integer, String> titleMap = new TreeMap<Integer, String>();
		ExlData dataAnno;
		
		for (Field field : fiedls) {
			dataAnno = field.getAnnotation(ExlData.class);
			if(dataAnno!=null){
				if(StringUtils.isNotBlank(dataAnno.title()[viewType])){
					titleMap.put(dataAnno.sortId(), dataAnno.title()[viewType]);
				}
			}
		}
		
		result = new ArrayList<String>(titleMap.values());
		return result;
	}
	/**
	 * put the data into the List<List<String>> 
	 * @param datas
	 * @param viewType
	 * @return
	 * @author xuechong
	 */
	@SuppressWarnings("unchecked")
	private static List<List<String>> encapeValue(List datas,Integer viewType){
		List<List<String>> result = new ArrayList<List<String>>();
		
		Map<Integer, String> dataMap = new TreeMap<Integer, String>();
		for (Object data : datas){
			if (!validateModel(data)) {
				throw new IllegalArgumentException("导出EXL时不支持的数据类型",
						new Throwable("no annotation " + ExlModel.class.getName() + " find on object"));
			}
			dataMap.clear();
			Field[] fiedls = data.getClass().getDeclaredFields();
			ExlData dataAnno;
			for (Field field : fiedls) {
				dataAnno = field.getAnnotation(ExlData.class);
				field.setAccessible(true);
				if(dataAnno!=null){
					if(StringUtils.isNotBlank(dataAnno.title()[viewType])){//when title is empty means no to display in exl
						try {
							dataMap.put(dataAnno.sortId(), 
									field.get(data)!=null?field.get(data).toString():"");
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
			}
			if(dataMap.size()>0){//if has values
				result.add(new ArrayList<String>(dataMap.values()));
			}
		}
		
		return result;
	}
	
	/**
	 * validate if the model can be translate to exl<br>
	 * (wether the object has the <b>ExlModel</b> annotataion on its head)
	 * @param o
	 * @return
	 * @author xuechong
	 */
	private static boolean validateModel(Object o){
		return o.getClass().getAnnotation(ExlModel.class)!=null;
	}
	
	
	
}
