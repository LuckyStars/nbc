package com.nbcedu.function.schoolmaster2.tags;

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
		put("[01]","111");	
		put("[02]","222");	
		put("[03]","333");	
		put("[04]","444");	 
		put("[05]","555");	
		put("[06]","666");	
		put("[07]","777");	
		put("[08]","8888");	
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
		StringBuilder result = new StringBuilder();
		int next = 0;
		char[] origin = content.trim().toCharArray();
		for (int i = 0; i < origin.length; i++) {
			if(i<next){
				continue;
			}
			if(origin[i]=='[' && (i+3) <=origin.length){
				String mat = new String(new char[]{origin[i],origin[i+1],origin[i+2],origin[i+3]});
				if(emos.containsKey(mat)){
					result.append(emos.get(mat));
					next += 4;
					continue;
				}
			}
			next++;
			result.append(origin[i]);
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		String origin = "asdsdaasd[0]asdasdasd[x]fgg[01]ggggg[02][aaaa]gfgggg[";
		EmotionDisplayTag tag = new EmotionDisplayTag();
		tag.setContent(origin);
		System.out.println(tag.replaceEmos());
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
