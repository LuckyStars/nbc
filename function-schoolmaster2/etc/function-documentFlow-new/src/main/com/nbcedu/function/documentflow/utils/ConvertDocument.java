package com.nbcedu.function.documentflow.utils;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nbcedu.function.functionsupport.util.PropertiesUtil;


/**
 * 
 * <p></p>
 * @author 黎青春
 * Create at:2012-3-20 下午01:31:25
 */
public class ConvertDocument {
	private static final String UNINSTALL_BAT = "uninstall.bat";
	private static final String INSTALL_BAT = "install.bat";
	private static final String CMD_C_START = "cmd /c start ";
	private static final String FLASH_PRINTER_EXE = "FlashPrinter.exe";
	private static final String FLASH_PAPER2_2 = "FlashPaper2.2";
	private static final String SPACE = " ";
	private static String flashPaperDir;
	private static String flashPrinterPath;
	private static String installPath;
	private static String unInstallPath;
	private static Runtime runtime = Runtime.getRuntime();
	private static final Logger logger = Logger.getLogger(ConvertDocument.class);
	
	private static Map<String, String> fileMap = new HashMap<String, String>();
	private static ConvertDocument instance;
	
	static{
		try{
			flashPaperDir=PropertiesUtil.findLocalPropertieValue("flashPaperDir");
		}catch (Exception e) {
			
			flashPaperDir = System.getProperty("user.dir");
			logger.error("在配置文件中没有找到flashPaperDir,使用默认路径:" + flashPaperDir);
		}
		for (File file : new File(flashPaperDir+File.separator+FLASH_PAPER2_2).listFiles()) {
			fileMap.put(file.getName(), file.getAbsolutePath());
		}
		flashPrinterPath = fileMap.get(FLASH_PRINTER_EXE);
		installPath = fileMap.get(INSTALL_BAT);
		unInstallPath = fileMap.get(UNINSTALL_BAT);
	}
	
	public static ConvertDocument getInstance() throws Exception{
		if(null == instance){
			synchronized (ConvertDocument.class) {
				instance = new ConvertDocument();
			}
		}
		return instance;
	}

	public boolean docToSwf(String originPath,String destPath)
			throws Exception {
		String destFolder = destPath.substring(0,destPath.lastIndexOf("\\"));
		logger.info(originPath);
		logger.info(destPath);
		
		new File(destFolder).mkdirs();
		String command = flashPrinterPath + SPACE + originPath + SPACE + "-o"
				+ SPACE + destPath;
		Process process = runtime.exec(command);
		process.waitFor();
		process.destroy();
		
		File outFile = new File(destPath);
		if(outFile.exists()){
			logger.info("docToSwf success.........");
		}
		return true;
	}
	

	public static void install() throws Exception {
		String cmd = CMD_C_START + installPath;
		Process process = runtime.exec(cmd,null,new File(flashPaperDir));
		process.waitFor();
		process.destroy();
	}

	public static void uninstall() throws Exception {
		String cmd = CMD_C_START + unInstallPath;
		Process process = runtime.exec(cmd,null,new File(flashPaperDir));
		process.waitFor();
		process.destroy();
	}

}
