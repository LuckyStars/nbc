package com.nbcedu.function.schoolmaster2.action;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import com.nbcedu.function.schoolmaster2.constants.Constants;
import com.nbcedu.function.schoolmaster2.core.action.BaseAction;
import com.nbcedu.function.schoolmaster2.core.util.FileUtil;
import com.nbcedu.function.schoolmaster2.core.util.jsons.JSONException;
import com.nbcedu.function.schoolmaster2.core.util.jsons.JSONObject;
import com.nbcedu.function.schoolmaster2.core.util.struts2.Struts2Utils;

public class SWFUploadAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String filePath;
	private File file;

	public String upload() throws JSONException{
		JSONObject jo = new JSONObject();
		if (file == null) {
			jo.put("error", 1);
			jo.put("message", "文件【"+fileName+"】内容为空,不能上传！");
			Struts2Utils.renderJson(jo.toString());
			return null;
		} else if(file.length() > (5 * 1024 * 1024 * 1024)){//限制上传大小 5GB
			jo.put("error", 1);
			jo.put("message", "文件【"+fileName+"】大小超过5GB,不能上传！");
			Struts2Utils.renderJson(jo.toString());
			return null;
		}
		String upload_path = this.getRequest().getSession().getServletContext().getRealPath("/") + Constants.COMMON_UPLOAD;
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTime().getTime();
		String path = upload_path + "\\" + Long.toString(time);
		try {
			FileUtil.saveFile(path+"\\"+fileName,file);
			jo.put("error", 0);
			jo.put("message", "文件【"+fileName+"】上传成功！");
			jo.put("path", path+"\\"+fileName);
			Struts2Utils.renderJson(jo.toString());
			return null;
		} catch (IOException e) {
			jo.put("error", 1);
			jo.put("message", "文件上传发生错误！");
			Struts2Utils.renderJson(jo.toString());
			return null;
		}
		
	}
	
	/////////////////////
	///getters&setters///
	/////////////////////
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
}
