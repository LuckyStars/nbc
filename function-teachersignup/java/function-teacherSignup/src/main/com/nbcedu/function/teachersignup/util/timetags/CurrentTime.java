package com.nbcedu.function.teachersignup.util.timetags;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class CurrentTime extends TagSupport{

	private String format;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().write(new SimpleDateFormat(format).format(new Date()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	//////////////////
	////get set //////
	/////////////////
	public void setFormat(String format) {
		this.format = format;
	}
	
}
