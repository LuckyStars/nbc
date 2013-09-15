package com.nbcedu.function.documentflow.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;


public class DateConvertor extends StrutsTypeConverter{

	private static final SimpleDateFormat sdf = 
		new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
        String str = values[0];  
        if(StringUtil.isBlank(str)){
        	return null;
        }
        Date date = null;
        try {
			date =sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public String convertToString(Map context, Object o) {
		if(o==null){
			return "";
		}else{
			Date date = (Date)o;
			return sdf.format(date);
		}
	}
	
}
