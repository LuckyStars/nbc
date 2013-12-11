package com.nbcedu.function.schoolmaster2.tags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * TYPE显示标签
 * @author xuechong
 */
@SuppressWarnings("serial")
public class ShowTypeTag extends TagSupport{
	
	static Map<String, String> types = new HashMap<String, String>(){{
		put("caiwu","财务工作");
		put("dangtuan","党团工作");
		put("deyu","德育工作");
		put("houqinweisheng","后勤卫生工作");
		put("jiaoyujiaoyan","教育教研工作");
		put("xinxihua","信息化工作");
	}};
	
	private String id;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().write(getName(id));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	public static String getName(String id){
		return types.containsKey(id)?types.get(id):"";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
