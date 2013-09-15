/*
 * @Title: AttachmentAction.java
 * @Package com.nbcedu.function.documentflow.action
 * @Description: 附件的请求处理控制器。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-13 下午01:33:57
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-13                          
 */
package com.nbcedu.function.documentflow.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.nbcedu.common.json.JSONTool;
import com.nbcedu.function.documentflow.biz.AttachmentBiz;
import com.nbcedu.function.documentflow.model.Attachment;
import com.nbcedu.function.documentflow.utils.ConvertDocument;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.AttachmentVO;
import com.nbcedu.function.functionsupport.util.PropertiesUtil;

/** 
 * <p>附件的请求处理控制器</p>
 * @author Wang Zhuoxuan
 * Create at:2011-9-13 下午01:33:57
 */
public class AttachmentAction {
	
	private String fileName;
	private String filePath;
	private File attachment;
	private String attachmentFileName;
	private String attachmentContentType;
	private AttachmentVO attachmentVo;
	private AttachmentBiz attachmentBiz;
	private static final Logger logger = Logger.getLogger(AttachmentAction.class);

	/** 
	 * 上传附件
	 */ 
	public void upload() {
		attachmentVo = new AttachmentVO();
		String userId = ServletActionContext.getRequest().getParameter("userId");
		try {
			String userDir = "\\" + userId;
			String timestampDir = "\\" + StringUtil.getDateTimeString(new Date()).replaceAll("[\\s,\\-,:]", "");
			String filePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/attachments") + userDir + timestampDir;
			
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
				
			FileUtils.copyFile(attachment, new File(dir, attachmentFileName));
			
			attachmentVo.setFileName(attachmentFileName);
			attachmentVo.setRealPath(filePath + "\\" + attachmentFileName);
			
			String attachmentId = attachmentBiz.addAttachment(attachmentVo);
			
			Map<String, String> jsonData = new HashMap<String, String>();
			jsonData.put("id", attachmentId);
			jsonData.put("text", attachmentFileName);
			
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			JSONTool.writeJSONData(jsonData, ServletActionContext.getResponse());
			
			this.conver(attachmentVo.getRealPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换文档为swf
	 * @param path
	 * @return
	 * @throws Exception
	 * @author xuechong
	 */
	private boolean conver(String path) throws Exception{
		String swfpath = findSwfPath(path);
		String extensionName=path.substring(path.lastIndexOf(".")+1, path.length());
		String converFormat = PropertiesUtil.findLocalPropertieValue("converFormat");
		String [] converFormatList = converFormat.split(",");
		boolean convertible = Boolean.FALSE;
		if(converFormatList!=null && converFormatList.length>0){
			for (int i = 0; i < converFormatList.length; i++) {
				if(extensionName.equalsIgnoreCase(converFormatList[i])){
					convertible = true;
					break ;
				}
			}
		}
		if(convertible){
			if(ConvertDocument.getInstance().docToSwf(path,swfpath)){
				logger.info("文档转换---成功---");
				return true ;
			}else{
				logger.error("文档转换---失败---");
				return false ;
			}
		}else{
			logger.info("文档转换---没有找到匹配的类型--");
			return false ;
		}
	}
	
	/** 
	 * 删除附件
	 */ 
	public void remove() {
		String aid = ServletActionContext.getRequest().getParameter("aid");
		Attachment file = attachmentBiz.findAttachment(aid);
		try {
			FileUtils.forceDelete(new File(file.getRealPath()));
			//判断文件夹是否为空,如果目录为空则删除目录
			File userDirectory = new File(file.getRealPath().replaceFirst("\\" + file.getFileName(), ""));
			if (userDirectory.list().length == 0) {
				FileUtils.deleteDirectory(userDirectory);
			}
			attachmentBiz.removeAttachment(aid);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("删除附件错误", e);
		}
	}
	
	/** 
	 * 下载附件
	 * @return 跳转的结果名称
	 * @throws UnsupportedEncodingException 如果文件名称编码不支持
	 */ 
	public String download() throws UnsupportedEncodingException {
		String aid = ServletActionContext.getRequest().getParameter("aid");
		Attachment att = attachmentBiz.findAttachment(aid);
		filePath = att.getRealPath();
		fileName = URLEncoder.encode(att.getFileName(), "UTF-8");
		this.attachment = new File(att.getRealPath());
		return "download";
	}
	/**
	 * 预览
	 * @since 2013 - 5 -10
	 * @author xuechong
	 */
	public void preView(){
		StringBuilder result = new StringBuilder();
		
		String aid = ServletActionContext.getRequest().getParameter("aid");
		Attachment att = attachmentBiz.findAttachment(aid);
		filePath = att.getRealPath();
		String fileName = simpleFileName(filePath);
		
		String swfPath = filePath.substring(//swf的相对路径
					filePath.indexOf("WEB-INF")+8,
					filePath.lastIndexOf(".")) + ".swf";
		swfPath = hashSwfName(fileName, swfPath);
		
		String swfFilePath = findSwfPath(att.getRealPath());
		this.attachment = new File(swfFilePath);
		if(attachment.exists()){
			
			result.append("<EMBED id=\"embedDOC\" src=\"");
			result.append(ServletActionContext.getRequest().getContextPath()+"/");
			result.append(swfPath.replaceAll("\\\\", "/"));
			result.append("\" quality=high bgcolor=#FFFFFF style=\"width: 100%;height: 100%;\"");				
			result.append(" NAME=\"myMovieName\"  TYPE=\"application/x-shockwave-flash\"");	
			result.append(" PLUGINSPAGE=\"http://www.macromedia.com/go/getflashplayer\" wmode=\"transparent\" />");
		
		}else{
			result.append("该文件不支持在线预览,请下载查看:");
			result.append("<a href=\"");
			result.append(ServletActionContext.getRequest().getContextPath());
			result.append("/documentFlow/downloadAttachment.action?aid=");
			result.append(aid);
			result.append("\">");
			result.append(att.getFileName());
			result.append("</a>");
		}
		PrintWriter out = null;
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			out = ServletActionContext.getResponse().getWriter();
			out.write(result.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{if(out!=null){out.close();}}
	}
	
	/**
	 * 获取swf文件
	 * 流格式的flash显示会有问题所以弃用了此方法
	 * @author xuechong
	 * @throws UnsupportedEncodingException 
	 */
	@Deprecated
	public String swf() throws UnsupportedEncodingException{
		String aid = ServletActionContext.getRequest().getParameter("aid");
		Attachment att = attachmentBiz.findAttachment(aid);
		filePath = att.getRealPath();
		filePath = findSwfPath(filePath);
		fileName = URLEncoder.encode(att.getFileName().
					substring(0,att.getFileName().lastIndexOf(".")) + ".swf", "UTF-8");
		this.attachment = new File(filePath);
		return "downloadswf";
	}
	private String findSwfPath(String filePath){
		String fileName = simpleFileName(filePath);
		filePath = filePath.replaceAll("\\\\WEB-INF", "");
		filePath = filePath.substring(0,filePath.lastIndexOf(".")>0?filePath.lastIndexOf("."):filePath.length());
		filePath =  filePath + ".swf";
		filePath = hashSwfName(fileName, filePath);
		return  filePath;
	}
	private String simpleFileName(String path){
		String fileName = new File(path).getName();
		return  fileName.substring(0,fileName.lastIndexOf(".")>0?fileName.lastIndexOf("."):fileName.length());
	}
	private String hashSwfName(String fileName,String originpath){
		return originpath.replace(fileName + ".swf", Math.abs(fileName.hashCode()) + ".swf");
	}
	/** 
	 * 读取文件输入流
	 * @return InputStream对象
	 * @throws Exception 如果读入文件出错
	 */ 
	public InputStream getDownloadAttachment() throws Exception {
        return new FileInputStream(filePath);  
    }
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
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	public AttachmentVO getAttachmentVo() {
		return attachmentVo;
	}
	public void setAttachmentVo(AttachmentVO attachmentVo) {
		this.attachmentVo = attachmentVo;
	}
	public void setAttachmentBiz(AttachmentBiz attachmentBiz) {
		this.attachmentBiz = attachmentBiz;
	}
}
