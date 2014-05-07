package com.nbcedu.function.syllabus.utils;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ServletUtils {

	private static final Logger logger = Logger.getLogger(ServletUtils.class);
	public static OutputStream getExlOutStream(HttpServletRequest request,
			HttpServletResponse response,String fileName) {
		OutputStream out = null;
		try {
			String agent = (String) request.getHeader("USER-AGENT");
			if (agent != null && agent.indexOf("MSIE") == -1) {
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ new String(fileName.getBytes("utf-8"),
										"ISO8859_1"));
			} else {
				// IE
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ URLEncoder.encode(fileName, "UTF8"));
			}
			response.setContentType("application/vnd.ms-excel;charset=uft-8");
			out = response.getOutputStream();
		} catch (Exception e) {
			logger.error("下载文件获取输出流时失败,文件名为" + fileName, e);
		}
		return out;
	}
}
