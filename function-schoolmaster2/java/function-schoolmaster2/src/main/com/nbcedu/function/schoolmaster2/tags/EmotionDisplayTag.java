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
	private Map<String, String> emos =  new HashMap<String, String>(){{
		put("[01]","01.png");	
		put("[02]","02.png");	
		put("[03]","03.png");	
		put("[04]","04.png");	 
		put("[05]","05.png");	
		put("[06]","06.png");	
		put("[07]","07.png");	
		put("[08]","08.png");	
		put("[09]","09.png");	
		put("[0A]","0A.png");	
		put("[0B]","0B.png");	
		put("[0C]","0C.png");	
		put("[0D]","0D.png");	
		put("[0E]","0E.png");	
		put("[0F]","0F.png");	
	}};	
	
	
	@Override
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().write(replaceEmos());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	
	private String replaceEmos(){
		if(this.content==null||this.content.trim().length() <=0){
			return "";
		}
		this.content = escapeXml(this.content);
		StringBuilder result = new StringBuilder(content.length());
		int next = 0;
		char[] origin = content.trim().toCharArray();
		String ctx = this.pageContext.getServletContext().getContextPath();
		for (int i = 0; i < origin.length; i++) {
			if(i<next){
				continue;
			}
			if(origin[i]=='[' && (i+3) <=origin.length){
				String mat = new String(new char[]{origin[i],origin[i+1],origin[i+2],origin[i+3]});
				if(emos.containsKey(mat)){
					result.append("<img src='");
					result.append(ctx);
					result.append("/function/emotion/images/");
					result.append(emos.get(mat));
					result.append("' />");
					next += 4;
					continue;
				}
			}
			next++;
			result.append(origin[i]);
		}
		return result.toString();
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
