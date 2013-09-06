//package com.nbcedu.function.schoolmaster2.data.util;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//ivate static int COLORNUM = 12;
//	
//	private static boolean dataConetentIsNull(MasterDataInfo mdi) {
//		if(mdi == null) {
//			return true;
//		}
//		String datacontent =mdi.getDatacontent();
//		boolean b = (datacontent==null ||"".equals(datacontent));
//		return b;
//	}
//	private static boolean textConetentIsNull(MasterDataInfo mdi) {
//		if(mdi == null) {
//			return true;
//		}
//		String datacontent =mdi.getTextcontent();
//		boolean b = (datacontent==null ||"".equals(datacontent));
//		return b;
//	}
//	
//	public static String get_Area2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph ");
//		if(detailFlag){
//			xml.append(" caption='" + mdi.getCaption());
//			xml.append("' subcaption='" + mdi.getSubcaption() + "'");
//			xml.append(" outCnvBaseFontSize='12'");
//			xml.append(" subcaption='12'");
//			xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "'");
//			xml.append(" numberPrefix = '" + mdi.getNumberprefix() + "'");
//		}
//		xml.append(" xAxisName='" + mdi.getXaxisname());
//		xml.append("' yAxisName='" + mdi.getYaxisname());
//		xml.append("' decimalPrecision='0'>");
//		Iterator it=jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//		while(it.hasNext()){      
//          Map.Entry entry = (Map.Entry)it.next();
//          String key = entry.getKey().toString();
//          String value = entry.getValue().toString();
//          String temp = "<set name='" + key + "' value='" + value + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Area2D%26apos;);'/>";
//          xml.append(temp);
//          }
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_Bar2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph  ");
//		if(detailFlag){
//			xml.append(" caption='" + mdi.getCaption() +"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append(" subcaption='" + mdi.getSubcaption() + "'");
//			xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "'");
//			xml.append(" numberPrefix = '" + mdi.getNumberprefix() + "'");
//		}
//			xml.append("xAxisName='"+ mdi.getXaxisname() +"' yAxisName='" + mdi.getYaxisname() + "' decimalPrecision='0' formatNumberScale='0' chartRightMargin='30'>");
//		Iterator it=jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//		int color  = 0;
//		while(it.hasNext()){      
//          Map.Entry entry = (Map.Entry)it.next();
//          String key = entry.getKey().toString();
//          String value = entry.getValue().toString();
//          String temp = "<set name='" + key + "' value='" + value + "' color='"+ getFusionColor(color++)+"' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Bar2D%26apos;);'/>";
//          xml.append(temp);
//		}
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_Column2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph  ");
//		if(detailFlag){
//			xml.append(" caption='" + mdi.getCaption() +"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append(" subcaption='" + mdi.getSubcaption() + "'");
//			xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "'");
//			xml.append(" numberPrefix = '" + mdi.getNumberprefix() + "'");
//		}
//			xml.append("xAxisName='"+ mdi.getXaxisname() +"' yAxisName='" + mdi.getYaxisname() + "' decimalPrecision='0' formatNumberScale='0'>");
//		Iterator it=jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//		int color  = 0;
//		while(it.hasNext()){      
//          Map.Entry entry = (Map.Entry)it.next();
//          String key = entry.getKey().toString();
//          String value = entry.getValue().toString();
//          String temp = "<set name='" + key + "' value='" + value + "' color='"+ getFusionColor(color++)+"' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Column2D%26apos;);'/>";
//          xml.append(temp);
//		}
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_Col2DLineDY_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		//临时
//		StringBuffer xml = new StringBuffer("<graph  ");
//		if(detailFlag){
//			xml.append(" caption='" + mdi.getCaption() + "' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append(" subcaption='" + mdi.getSubcaption() + "'");
//			xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "'");
//			xml.append(" numberPrefix = '" + mdi.getNumberprefix() + "' ");
//		}
//		xml.append(" PYAxisName='"+mdi.getYaxisname() +"' SYAxisName='" + mdi.getXaxisname() +"'  showvalues='0'  numDivLines='4' formatNumberScale='0' decimalPrecision='0' anchorSides='10' anchorRadius='3' anchorBorderColor='009900'>");
//		xml.append("<categories>");
//		Object[] categoryValue = jsonStringToCategory(mdi.getDatacontent());
//		for (int i = 0; i < categoryValue.length; i++) {
//			String temp = "<category name='" + categoryValue[i] + "'/>";
//			xml.append(temp);
//		}
//		xml.append("</categories>");
//		Map<String,Object[]> mapValue = jsonStringToSet(mdi.getDatacontent());
//		Iterator it=mapValue.entrySet().iterator();
//		int color  = 0;
//		while(it.hasNext()) {
//			Map.Entry<String,Object[]> entry = (Map.Entry<String,Object[]>)it.next();
//			String key = entry.getKey().toString();
//			Object[] values = entry.getValue();
//			String temp = "<dataset seriesName='" + key + "' color='"+ getFusionColor(color++)+"' showValues='0' ";
//			xml.append(temp);
//			if(key!= null &&key.equals(AVERAGE)) {
//				xml.append("parentYAxis='S' ");
//			}
//			xml.append(">");
//			for (int i = 0; i < values.length; i++) {
//				String temp1 = "<set value='" + values[i] + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;MSColumn2DLineDY%26apos;);'/>";
//				xml.append(temp1);
//			}
//			xml.append("</dataset>");
//		}
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//		
//	}
//	public static String get_Col3DLineDY_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		//临时
//		StringBuffer xml = new StringBuffer("<graph  ");
//		if(detailFlag) {
//			xml.append("caption='"  + mdi.getCaption()+"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append(" subcaption='" + mdi.getSubcaption() + "'");
//			xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "'");
//			xml.append(" numberPrefix = '" + mdi.getNumberprefix() + "' ");
//		}
//			xml.append("PYAxisName='" + mdi.getYaxisname() +"' SYAxisName='" + mdi.getYaxisnameofline()+"' showvalues='0'  numDivLines='4' formatNumberScale='0' decimalPrecision='0' anchorSides='10' anchorRadius='3' anchorBorderColor='009900'>");
//		xml.append("<categories>");
//		Object[] categoryValue = jsonStringToCategory(mdi.getDatacontent());
//		for (int i = 0; i < categoryValue.length; i++) {
//			String temp = "<category name='" + categoryValue[i] + "'/>";
//			xml.append(temp);
//		}
//		xml.append("</categories>");
//		Map<String,Object[]> mapValue = jsonStringToSet(mdi.getDatacontent());
//		Iterator it=mapValue.entrySet().iterator();
//		int color  = 0;
//		while(it.hasNext()) {
//			Map.Entry<String,Object[]> entry = (Map.Entry<String,Object[]>)it.next();
//			String key = entry.getKey().toString();
//			//临时
//			Object[] values = entry.getValue();
//			String temp = "<dataset seriesName='" + key + "' color='"+getFusionColor(color++)+"' showValues='0' ";
//			xml.append(temp);
//			if(key!= null &&key.equals(AVERAGE)) {
//				xml.append("parentYAxis='S' ");
//			}
//			xml.append(">");
//			for (int i = 0; i < values.length; i++) {
//				String temp1 = "<set value='" + values[i] + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;MSColumn3DLineDY%26apos;);'/>";
//				xml.append(temp1);
//			}
//			xml.append("</dataset>");
//		}
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//		
//	}
//
//	public static String get_Doughnut2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if (!dataConetentIsNull(mdi)) {
//			StringBuffer xml = new StringBuffer("<graph  "); 
//			if(detailFlag){
//				xml.append("caption='" + mdi.getCaption() + "' subcaption='"+mdi.getSubcaption()+"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//				xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//				xml.append(" numberPrefix='" +mdi.getNumberprefix() + "' ");
//			}
//			xml.append("showNames='1' decimalPrecision='0'>");
//			Iterator it = jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//			while (it.hasNext()) {
//				Map.Entry entry = (Map.Entry) it.next();
//				String key = entry.getKey().toString();
//				String value = entry.getValue().toString();
//				String temp = "<set name='" + key + "' value='" + value + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Doughnut2D%26apos;);'/>";
//				xml.append(temp);
//			}
//			xml.append("</graph>");
//			return xml.toString();
//		}
//		return null;
//	}
//	public static String get_Line2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph  ");
//		if(detailFlag){
//			xml.append("caption='" + mdi.getCaption() + "' subcaption='"+mdi.getSubcaption()+"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append(" numberPrefix='" +mdi.getNumberprefix() + "' ");
//			xml.append(" numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//		}
//		xml.append("yAxisName='" + mdi.getYaxisname() +"'");
//		xml.append("xAxisName='" + mdi.getXaxisname() +"'");
//		xml.append(" decimalPrecision='0' formatNumberScale='0'  showNames='1' showValues='0'  showAlternateHGridColor='1' AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' >");
//		Iterator it=jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//		while(it.hasNext()){      
//          Map.Entry entry = (Map.Entry)it.next();
//          String key = entry.getKey().toString();
//          String value = entry.getValue().toString();
//          String temp = "<set name='" + key + "' value='" + value + "' hoverText='"+ key + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Line%26apos;);'/>";
//          xml.append(temp);
//          }
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//	}
//	
//	public static String get_MSArea2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph ");
//		if(detailFlag){
//			xml.append("caption='"+mdi.getCaption()+"' subcaption='" + mdi.getSubcaption() +"' outCnvBaseFontSize = '12' baseFontSize ='12' ");
//			xml.append("numberPrefix='"+mdi.getNumberprefix()+"' ");
//			xml.append("numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//		}
//			xml.append("yAxisName='" + mdi.getYaxisname() +"'");
//			xml.append("divlinecolor='F47E00' numdivlines='4' showAreaBorder='1' areaBorderColor='000000'  showNames='1' numVDivLines='29' vDivLineAlpha='30' formatNumberScale='1' rotateNames='0'  decimalPrecision='0'>");
//		xml.append("<categories>");
//		Object[] categoryValue = jsonStringToCategory(mdi.getDatacontent());
//		for (int i = 0; i < categoryValue.length; i++) {
//			String temp = "<category name='" + categoryValue[i] + "'/>";
//			xml.append(temp);
//		}
//		xml.append("</categories>");
//		Map<String,Object[]> mapValue = jsonStringToSet(mdi.getDatacontent());
//		Iterator it=mapValue.entrySet().iterator();
//		int color  = 1;
//		while(it.hasNext()) {
//			Map.Entry<String,Object[]> entry = (Map.Entry<String,Object[]>)it.next();
//			String key = entry.getKey().toString();
//			//临时
//			Object[] values = entry.getValue();
//			//临时
//			String temp = "<dataset seriesname='" + key + "' color='"+getFusionColor(color++)+"' showValues='0' areaAlpha='50' showAreaBorder='1' areaBorderThickness='2' areaBorderColor='"+getFusionColor(color)+"'>";
//			xml.append(temp);
//			
//			for (int i = 0; i < values.length; i++) {
//				String temp1 = "<set value='" + values[i] + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;MSArea2D%26apos;);'/>";
//				xml.append(temp1);
//			}
//			xml.append("</dataset>");
//		}
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_MSBar2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		//临时
//		StringBuffer xml = new StringBuffer("<graph  ");
//		if(detailFlag){
//			xml.append("caption='" + mdi.getCaption()+"'subcaption='" + mdi.getSubcaption() +"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append("numberPrefix='"+mdi.getNumberprefix()+"' ");
//			xml.append("numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//		}
//			xml.append("yaxisname='"+mdi.getYaxisname()+"' hovercapbg='FFFFFF' divLineColor='999999' divLineAlpha='80' numdivlines='5' decimalPrecision='0' >");
//		xml.append("<categories>");
//		Object[] categoryValue = jsonStringToCategory(mdi.getDatacontent());
//		for (int i = 0; i < categoryValue.length; i++) {
//			String temp = "<category name='" + categoryValue[i] + "'/>";
//			xml.append(temp);
//		}
//		xml.append("</categories>");
//		Map<String,Object[]> mapValue = jsonStringToSet(mdi.getDatacontent());
//		Iterator it=mapValue.entrySet().iterator();
//		int color  = 0;
//		while(it.hasNext()) {
//			Map.Entry<String,Object[]> entry = (Map.Entry<String,Object[]>)it.next();
//			String key = entry.getKey().toString();
//			
//			Object[] values = entry.getValue();
//			
//			String temp = "<dataset seriesname='" + key + "' color='"+getFusionColor(color++)+"'>";
//			xml.append(temp);
//			for (int i = 0; i < values.length; i++) {
//				String temp1 = "<set value='" + values[i] + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;MSBar2D%26apos;);'/>";
//				xml.append(temp1);
//			}
//			xml.append("</dataset>");
//		}
//		xml.append("</graph>");
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_Pie2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph ");
//		if(detailFlag){
//			xml.append("caption='" + mdi.getCaption()+"'subcaption='" + mdi.getSubcaption() +"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append("numberPrefix='"+mdi.getNumberprefix()+"' ");
//			xml.append("numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//		}
//		xml.append("showNames='1'  decimalPrecision='0' anchorBorderColor='009900'>");
//		Iterator it=jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//		while(it.hasNext()){      
//          Map.Entry entry = (Map.Entry)it.next();
//          String key = entry.getKey().toString();
//          String value = entry.getValue().toString();
//          String temp = "<set name='" + key + "' value='" + value + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Pie2D%26apos;);'/>";
//          xml.append(temp);
//          }
//		xml.append("</graph>");
//
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_Pie3D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//		StringBuffer xml = new StringBuffer("<graph ");
//		if(detailFlag){
//			xml.append("caption='" + mdi.getCaption()+"'subcaption='" + mdi.getSubcaption() +"' outCnvBaseFontSize = '12' baseFontSize ='12'");
//			xml.append("numberPrefix='"+mdi.getNumberprefix()+"' ");
//			xml.append("numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//		}
//		xml.append("showNames='1'  decimalPrecision='0'  >");
//		Iterator it=jsonStringToValueMap(mdi.getDatacontent()).entrySet().iterator();
//		while(it.hasNext()){      
//          Map.Entry entry = (Map.Entry)it.next();
//          String key = entry.getKey().toString();
//          String value = entry.getValue().toString();
//          String temp = "<set name='" + key + "' value='" + value + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;Pie3D%26apos;);'/>";
//          xml.append(temp);
//          }
//		xml.append("</graph>");
//
//		return xml.toString();
//		}
//		return null;
//	}
//	public static String get_MSLine_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//			StringBuffer xml = new StringBuffer("<graph ");
//			if(detailFlag) {
//				xml.append("caption = '" + mdi.getCaption() + "' outCnvBaseFontSize = '12' baseFontSize ='12'");
//				xml.append("subcaption = '" + mdi.getSubcaption() + "' ");
//				xml.append("numberPrefix='"+mdi.getNumberprefix()+"' ");
//				xml.append("numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//			}
//			xml.append("yAxisName='" + mdi.getYaxisname() +"' ");
//			xml.append("hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='3' numVdivlines='0'>");
//			xml.append("<categories>");
//			Object[] categoryValue = jsonStringToCategory(mdi.getDatacontent());
//			for (int i = 0; i < categoryValue.length; i++) {
//				String temp = "<category name='" + categoryValue[i] + "'/>";
//				xml.append(temp);
//			}
//			xml.append("</categories>");
//			Map<String,Object[]> mapValue = jsonStringToSet(mdi.getDatacontent());
//			Iterator it=mapValue.entrySet().iterator();
//			int color  = 0;
//			while(it.hasNext()) {
//				Map.Entry<String,Object[]> entry = (Map.Entry<String,Object[]>)it.next();
//				String key = entry.getKey().toString();
//				Object[] values = entry.getValue();
//				String temp = "<dataset seriesname='" + key + "' color='"+getFusionColor(color)+"' anchorBorderColor='" +getFusionColor(color)+"' anchorBgColor='" +getFusionColor(color++)+"'>";
//				xml.append(temp);
//				for (int i = 0; i < values.length; i++) {
//					String temp1 = "<set value='" + values[i] + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;MSLine%26apos;);'/>";
//					xml.append(temp1);
//				}
//				xml.append("</dataset>");
//			}
//			xml.append("</graph>");
//			return xml.toString();
//			}
//			return null;
//	}
//	public static String get_StCol2D_XML(MasterDataInfo mdi,boolean detailFlag) {
//		mdi = nullToEmpty(mdi);
//		if(!dataConetentIsNull(mdi)){
//			StringBuffer xml = new StringBuffer("<graph ");
//			if(detailFlag) {
//				xml.append("caption = '" + mdi.getCaption() + "' outCnvBaseFontSize = '12' baseFontSize ='12'");
//				xml.append("subcaption = '" + mdi.getSubcaption() + "' ");
//				xml.append("numberPrefix='"+mdi.getNumberprefix()+"' ");
//				xml.append("numberSuffix = '" + mdi.getNumbersuffix() + "' ");
//			}
//			xml.append("yAxisName='" + mdi.getYaxisname() +"' ");
//			xml.append("xAxisName='" + mdi.getXaxisname() +"' ");
//			xml.append("hovercapbg='FFECAA' hovercapborder='F47E00' decimalPrecision= '0' showvalues='0' numdivlines='3' numVdivlines='0' formatNumberScale='0'>");
//			xml.append("<categories>");
//			Object[] categoryValue = jsonStringToCategory(mdi.getDatacontent());
//			for (int i = 0; i < categoryValue.length; i++) {
//				String temp = "<category name='" + categoryValue[i] + "'/>";
//				xml.append(temp);
//			}
//			xml.append("</categories>");
//			Map<String,Object[]> mapValue = jsonStringToSet(mdi.getDatacontent());
//			Iterator it=mapValue.entrySet().iterator();
//			int color  = 0;
//			while(it.hasNext()) {
//				Map.Entry<String,Object[]> entry = (Map.Entry<String,Object[]>)it.next();
//				String key = entry.getKey().toString();
//				Object[] values = entry.getValue();
//				String temp = "<dataset seriesname='" + key + "' color='"+getFusionColor(color++)+"' showValues='1'>";
//				xml.append(temp);
//				for (int i = 0; i < values.length; i++) {
//					String temp1 = "<set value='" + values[i] + "' link ='JavaScript:detailWindow(%26apos;"+mdi.getApplicationidentifier()+"%26apos;,%26apos;StackedColumn2D%26apos;);'/>";
//					xml.append(temp1);
//				}
//				xml.append("</dataset>");
//			}
//			xml.append("</graph>");
//			return xml.toString();
//			}
//			return null;
//	}
//	/**
//	 * 校长工作台中非表报栏目的内容数据的解析
//	 * JSON数据格式举例：{"title1":["image1","hyper1","content1","date1"],"title2":["image2","hyper2","content2","date2"],"title3":["image3","hyper3","content3","date3"]}
//	 */
//	public static Map<String,List<String>> getDataColumn(String jsonString) {
//		
//		Map<String,List<String>> newsMap = new LinkedHashMap<String,List<String>>();
//		JSONObject jsonObject = JSONObject.fromObject(jsonString);
//		if(jsonObject == null || jsonObject.isEmpty()) {
//			return null;
//		}
//		Iterator keySet = jsonObject.keys();
//		while (keySet.hasNext()) {
//			String key = (String)keySet.next();
//			String value = jsonObject.getString(key);
//			JSONArray jsonArray = JSONArray.fromObject(value);
//			Object[] stringArray = jsonArray.toArray();
//			List<String> strings = new ArrayList<String>();
//			for(int i = 0;i<stringArray.length;i++) {
//				if(stringArray[i] == null ||stringArray[i].equals(""))
//				{
//					strings.add("");
//					continue;
//				}
//				strings.add((String)stringArray[i]);
//			}
//			newsMap.put(key, strings);
//		}
//		return newsMap;
//		
//	}
//	/**
//	 * 一维数据的获取：{"key3":"100","key2":"200","key1":"300"}
//	 */
//	public static Map<String,String> jsonStringToValueMap(String jsonString) {
//		Map<String,String> valueMap = new LinkedHashMap<String,String>();
//		JSONObject json = JSONObject.fromObject(jsonString);
//		if(json == null ||json.isEmpty()) {
//			return null;
//		}
//		Iterator keys = json.keys();
//		while (keys.hasNext()) {
//			String key = (String) keys.next();
//			String value1 = json.get(key).toString();
//			valueMap.put(key, value1);
//		}
//		return valueMap;
//	}
//	/**
//	 * 二维数据中category中数据的获取：{"set":{"producta":["1","2","3","4"],"productb":["5","6","7","8"]},"categories":["test1","test2","test3","test4"]}
//	 * @param jsonString
//	 * @return
//	 */
//	public static Object[] jsonStringToCategory(String jsonString) {
//		JSONObject json = JSONObject.fromObject(jsonString);
//		if(json == null ||json.isEmpty()) {
//			return null;
//		}
//		String categoriesvalue = json.getString("categories");
//		JSONArray messageArray = JSONArray.fromObject(categoriesvalue);
//		Object[] os =  messageArray.toArray();
//		return os;
//		}
//	/**
//	 * 二维数据中set数据的获取：{"set":{"producta":["1","2","3","4"],"productb":["5","6","7","8"]},"categories":["test1","test2","test3","test4"]}
//	 * @param jsonString
//	 * @return
//	 */
//	public static Map<String,Object[]> jsonStringToSet(String jsonString) {
//		Map<String,Object[]> valueMap = new LinkedHashMap<String,Object[]>();
//		JSONObject json = JSONObject.fromObject(jsonString);
//		if(json == null ||json.isEmpty()) {
//			return null;
//		}
//		String datavalue = json.getString("set");
//		JSONObject jsonSet = JSONObject.fromObject(datavalue);
//		Iterator keys = jsonSet.keys();
//		while (keys.hasNext()) {
//			String key = (String) keys.next();
//			String value1 = jsonSet.get(key).toString();
//			JSONArray messageArraySet = JSONArray.fromObject(value1);
//			Object[] os = (Object[]) messageArraySet.toArray();
//			valueMap.put(key, os);
//		}
//		return valueMap;
//	}
//	public static Map<String,Object[]> getTestContent(MasterDataInfo mdi) {
//		if(textConetentIsNull(mdi)) {
//			return null;
//		}
//		Map<String, Object[] > map = new LinkedHashMap<String,Object[]>();
//		String textContent = mdi.getTextcontent();
//		boolean testContent = (textContent!=null &&!"".equals(textContent));
//		if(testContent) {
//		JSONObject jsonObject = JSONObject.fromObject(textContent);
//		Iterator keys = jsonObject.keys();
//		while(keys.hasNext()) {
//			String key = keys.next().toString();
//			String value = jsonObject.get(key).toString();
//			JSONArray arraySet = JSONArray.fromObject(value);
//			Object[] os = (Object[]) arraySet.toArray();
//			map.put(key, os);
//		}
//		}
//		return map;
//	}
//	/**
//	 * 图表显示颜色的选取
//	 */
//	public static String getFusionColor(int i) {
//		int j = i%COLORNUM;
//		String color = MasterParamConstant.colorMap.get(j);
//		if(color == null) {
//			return "FFFFFF";
//		}
//		return color;
//		
//	}
//	public static String nullToEmpty(String string) {
//		if(string== null ||"".equals(string)) {
//			return "";
//		}
//		return string.trim();
//	}
//	public static MasterDataInfo nullToEmpty(MasterDataInfo mdi) {
//		mdi.setCaption(nullToEmpty(mdi.getCaption()));
//		mdi.setSubcaption(nullToEmpty(mdi.getSubcaption()));
//		mdi.setXaxisname(nullToEmpty(mdi.getXaxisname()));
//		mdi.setYaxisname(nullToEmpty(mdi.getYaxisname()));
//		mdi.setYaxisnameofline(nullToEmpty(mdi.getYaxisnameofline()));
//		mdi.setNumberprefix(nullToEmpty(mdi.getNumberprefix()));
//		mdi.setNumbersuffix(nullToEmpty(mdi.getNumbersuffix()));
//		return mdi;
//	}
//}
