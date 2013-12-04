package com.nbcedu.function.schoolmaster2.tags;

import static org.apache.taglibs.standard.tag.common.core.Util.escapeXml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 转换显示的表情
 * @author xuechong
 */
@SuppressWarnings("serial")
public class EmotionDisplayTag extends TagSupport {
	
	private String content;
	private static Map<String, String> emos =  new HashMap<String, String>(){{
		put("[傲慢]","傲慢.png");	
		put("[呲牙]","呲牙.png");	
		put("[大笑]","大笑.png");	
		put("[流汗]","流汗.png");	 
		put("[惊讶]","惊讶.png");	
		put("[可爱]","可爱.png");	
		put("[大哭]","大哭.png");	
		put("[酷酷]","酷酷.png");	
		put("[困了]","困了.png");	
		put("[难过]","难过.png");	
		put("[亲亲]","亲亲.png");	
		put("[挑逗]","挑逗.png");	
		put("[微笑]","微笑.png");	
		put("[问题]","问题.png");	
		put("[再见]","再见.png");	
	}};	
	
	@Override
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().write(
					replaceEmos(this.content,
					this.pageContext.getServletContext().getContextPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	
	public static String replaceEmos(String input,String ctx){
		if(input==null||input.trim().length() <=0){
			return "";
		}
		input = escapeXml(input);
		StringBuilder result = new StringBuilder(input.length());
		int next = 0;
		char[] origin = input.trim().toCharArray();
		for (int i = 0; i < origin.length; i++) {
			if(i<next){
				continue;
			}
			if(origin[i]=='[' && (i+3) <=origin.length){
				String mat = 
					new String(new char[]{origin[i],origin[i+1],origin[i+2],origin[i+3]});
				
				if(emos.containsKey(mat)){
					appendImgTag(result,ctx, mat);
					next += 4;
					continue;
				}
			}
			next++;
			result.append(origin[i]);
		}
		return result.toString();
	}


	private static void appendImgTag(StringBuilder result,String ctx,String mat) {
		result.append("<img src='");
		result.append(ctx);
		result.append("/function/emotion/images/");
		result.append(emos.get(mat));
		result.append("' />");
	}

	///////////////////////
	////getters&setters///
	///////////////////////
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
