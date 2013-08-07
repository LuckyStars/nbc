package com.nbcedu.function.cardmanage.core.util;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;



/**
 * Struts2工具类. 实现获取Request/Response/Session与绕过jsp/freemaker直接输出文本的简化函数.
 * 
 * @author calvin
 */
public class Struts2Util {

	// -- header 常量定义 --//
	private static final String HEADER_ENCODING = "encoding";
	private static final String HEADER_NOCACHE = "no-cache";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final boolean DEFAULT_NOCACHE = true;
	
	/**
	 * 脱离servlet容器，得到request	
	 * @author dhy
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);		
	}
	
	/**
	 * 脱离servlet容器，得到response	
	 * @author dhy
	 * @return HttpServletRequest
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);		
	}
	

	// -- 绕过jsp/freemaker直接输出文本的函数 --//
	/**
	 * 直接输出内容的简便函数. eg. render("text/plain", "hello", "encoding:GBK"); render("text/plain", "hello",
	 * "no-cache:false"); render("text/plain", "hello", "encoding:GBK", "no-cache:false");
	 * 
	 * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
	 */
	public static void render(final String contentType, final String content, final String... headers) {
		HttpServletResponse response = initResponseHeader(contentType, headers);
		try {
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出文本.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderText(final String text, final String... headers) {
		render(ServletUtil.TEXT_TYPE, text, headers);
	}

	/**
	 * 直接输出HTML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderHtml(final String html, final String... headers) {
		render(ServletUtil.HTML_TYPE, html, headers);
	}

	/**
	 * 直接输出XML.
	 * 
	 * @see #render(String, String, String...)
	 */
	public static void renderXml(final String xml, final String... headers) {
		render(ServletUtil.XML_TYPE, xml, headers);
	}

	/**
	 * 直接输出JSON.
	 * 
	 * @param jsonString json字符串.
	 * @see #render(String, String, String...)
	 */
	public static void renderJson(final String jsonString, final String... headers) {
		render(ServletUtil.JSON_TYPE, jsonString, headers);
	}

	/**
	 * 分析并设置contentType与headers.
	 */
	private static HttpServletResponse initResponseHeader(final String contentType, final String... headers) {
		// 分析headers参数
		String encoding = DEFAULT_ENCODING;
		boolean noCache = DEFAULT_NOCACHE;
		for (String header : headers) {
			String headerName = StringUtil.substringBefore(header, ":");
			String headerValue = StringUtil.substringAfter(header, ":");

			if (StringUtil.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
				encoding = headerValue;
			} else if (StringUtil.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
				noCache = Boolean.parseBoolean(headerValue);
			} else {
				throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();

		// 设置headers参数
		String fullContentType = contentType + ";charset=" + encoding;
		response.setContentType(fullContentType);
		if (noCache) {
			ServletUtil.setDisableCacheHeader(response);
		}

		return response;
	}
	
	
	/**
	 * reSubmit:(避免重复提交)
	 * @author Wang QingQiang
	 * Create at:May 27, 2011 1:47:14 PM
	 * @return
	 */
	public static boolean reSubmit() {
		String sv = (String) ServletActionContext.getRequest().getParameter("sv");
		String obj = (String) ServletActionContext.getRequest().getSession().getAttribute("sv");
        if (sv != null && sv.equals(obj)) {
        	ServletActionContext.getRequest().removeAttribute("sv");
            return true;
        } else {
            return false;
        }
	}

	
	
}
