package com.nbcedu.function.schoolmaster2.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;

public class ResourceUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	/** 文件对象 */ 
	private File filedata;       
	/** 文件名 */ 
	private String filedataFileName;       
	/** 文件内容类型 */ 
	private String filedataContentType;  

	// 定义允许上传的文件扩展名
	Map<String, String> extMap = new HashMap<String, String>(){{
		put("image", "gif,jpg,jpeg,png,bmp,ico");
		put("flash", "swf,flv");
		put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
	}};
	public void upload(){
		String savePath = (getRequest().getSession().getServletContext().getRealPath("/") + Constants.COMMON_UPLOAD + "/");
		String saveUrl = getRequest().getContextPath() + "/" + Constants.COMMON_UPLOAD + "/";
		
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory() &&	uploadDir.mkdirs()) {
			Struts2Utils.renderJson(getError("上传目录不存在。"));
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			Struts2Utils.renderJson(getError("上传目录没有写权限。"));
		}
		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		// 获取内存中当前文件输入流
		InputStream in = null;
			try {
				fos = new FileOutputStream(savePath + "/" + filedataFileName);
				in = new FileInputStream(filedata);
				
				int num = 0;
				while ((num = in.read(buffer)) > 0) {
					fos.write(buffer, 0, num);
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
				
				Struts2Utils.renderJson(getError("上传出错。"));
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					Struts2Utils.renderJson(getError("上传出错。"));
				}
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					Struts2Utils.renderJson(getError("上传出错。"));
				}
			}
			JSONObject jo = new JSONObject();
			jo.put("error", 0);
			jo.put("message", "文件【"+filedataFileName+"】上传成功！");
			jo.put("path", saveUrl+filedataFileName);
			
		Struts2Utils.renderText(jo.toJSONString());
	}
	
	/**
	 * 出错Json
	 * @param message
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}


}
