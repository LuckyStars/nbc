package com.nbcedu.function.cardmanage.core.util.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * 为带有文件上传的表单提供统一的处理接口，jsp中可以调用
 * 
 * @author qinyuan
 * @since 2011-5-11
 */
public class CommonsFileUploadUtil {
	private static final Logger log = Logger.getLogger(CommonsFileUploadUtil.class);

	/**
	 * 文件下载功能
	 * 
	 * 
	 * @param filePath
	 *            要下载的文件物理路径，必须值
	 * @param fileName
	 *            要下载的文件名称，包含后缀，eg ： 下载文件.txt ，可为空
	 * @param response
	 */
	public static void downloadFile(String filePath, String fileName,
			HttpServletResponse response) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				fileNotFound(response);
				return;
			}

			if (fileName == null || fileName.trim().length() == 0) {
				fileName = FilenameUtils.getName(filePath);
			}

			InputStream is = new FileInputStream(file);
			// 对下载的文件名称进行编码，避免出现中文乱码问题
			response.setHeader("Content-disposition", "attachment; filename="
					+ URLEncoder.encode(fileName, "UTF-8"));
			int len = -1;
			OutputStream out = response.getOutputStream();
			while ((len = is.read()) != -1) {
				out.write(len);
			}

			is.close();
			out.flush();
			out.close();
		} catch (FileNotFoundException notFound) {
			fileNotFound(response);
			return;
		} catch (IOException ioe) {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param response
	 */
	private static void fileNotFound(HttpServletResponse response) {
		try {
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().write("您所请求的文件不存在！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理带有文件上传功能的表单，返回处理UpFileFormEntity对象
	 * 
	 * @param request
	 *            对应处理HttpServletRequest实例
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static UpFileFormEntity getUploadFile(HttpServletRequest request) {
		if (request == null)
			return null;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024L * 1024L * 50L);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			log.error("文件上传发生错误" + e1.getMessage());

			return null;
		}

		Map<String, String> paraMap = new HashMap<String, String>();
		List<FileItem> fileItems = new ArrayList<FileItem>();

		Iterator it = items.iterator();

		while (it.hasNext()) {
			FileItem fileItem = (FileItem) it.next();
			if (fileItem.isFormField()) {
				String value = fileItem.getString();
				if (value != null && value.length() > 0) {
					//value = value; //iso2GBK(value);
				}

				if (paraMap.containsKey(fileItem.getFieldName())) {
					paraMap.put(fileItem.getFieldName(),
							paraMap.get(fileItem.getFieldName()) + "^_^"
									+ value);
				} else {
					paraMap.put(fileItem.getFieldName(), value);
				}

				continue;
			}

			if (fileItem.getName() != null && fileItem.getSize() != 0) {
				fileItems.add(fileItem);
			}
		}

		return new UpFileFormEntity(paraMap, fileItems);
	}

	/**
	 * 检查当前文件保存目录是否存在，若不存在，直接创建
	 * 
	 * @param file
	 */
	public static void checkFilePath(File file) {
		if (file == null)
			return;

		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
	}

	/**
	 * 编码转换，NBC用不到！！
	 * 
	 * @param isoStr
	 * @return
	 */
	@Deprecated
	private static String iso2GBK(String isoStr) {
		try {
			return new String(isoStr.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return isoStr;
	}
}