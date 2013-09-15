package doctrans;


import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import doctrans.form.MainForm;
import doctrans.form.StatusLogger;


public class Processor implements Runnable{
	
	private final MainForm form;
	
	private StatusLogger logger ;
	private String schoolAppHome;
	private ConvertDocument convertor;
	
	public Processor(MainForm form){
		this.form = form;
		this.schoolAppHome = form.getSelectedPath();
		this.logger = form.getLogger();
		this.convertor = new ConvertDocument(this.getConfigPath(), form.getLogger());
	}
	
	/**
	 * the main process method 
	 */
	@Override
	public void run() {
		execute();
		form.notifyProcessDone();
	}
	
	
	@SuppressWarnings("static-access")
	private void execute(){
		for (String originPath : this.allOriginFilePath()) {
			try {
				String swfPath = this.findSwfPath(originPath);
				if(!new File(swfPath).exists()){
					this.convertor.docToSwf(originPath, swfPath);
					Thread.currentThread().sleep(3000);
				}else{
					this.logger.replace("文件已存在" + swfPath);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				this.logger.replace("error when parse file:" + originPath + 
						"and the Exception is" + e.toString());
			}
			
		}
		
	}
	
	private Collection<String> allOriginFilePath(){
		List<String> result = new LinkedList<String>();
		for (File file : this.getFile(this.originFileFolderPath())) {
			result.add(file.getPath());
		}
		return result;
	}
	
	private Collection<File> getFile(String path){
		Collection<File> result = new LinkedList<File>();
		File parent = new File(path);
		File[] children = parent.listFiles();
		if (children!=null&&children.length>0) {
			for (File file : children) {
				if (file.isFile()) {
					result.add(file);
				} else {
					result.addAll(getFile(file.getPath()));
				}
			}
		}
		return result;
	}
	
	
	private String originFileFolderPath(){
		return this.schoolAppHome + 
			File.separator + "WEB-INF" + 
			File.separator+ "attachments";
	}
	
	private String findSwfPath(String filePath){
		filePath = filePath.replaceAll("\\\\WEB-INF", "");
		filePath = filePath.substring(0,filePath.lastIndexOf("."));
		filePath =  filePath + ".swf";
		return  filePath;
	}
	
	private String getConfigPath(){
		return this.schoolAppHome + 
			File.separator + "WEB-INF" + 
			File.separator + "classes" + 
			File.separator + "functionsupportconfig.properties";
	}
}
