package doctrans;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import doctrans.form.StatusLogger;



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
	private StatusLogger logger = null;
	
	private static Map<String, String> fileMap = new HashMap<String, String>();

	
	public ConvertDocument(String propertiePath,StatusLogger logger){
		this.logger = logger;
		try{
			Properties props = new Properties();
			props.load(new FileInputStream(new File(propertiePath)));
			flashPaperDir=props.getProperty("flashPaperDir");
		}catch (Exception e) {
			
			flashPaperDir = System.getProperty("user.dir");
			logger.replace("在配置文件中没有找到flashPaperDir,使用默认路径:" + flashPaperDir);
		}
		for (File file : new File(flashPaperDir+File.separator+FLASH_PAPER2_2).listFiles()) {
			fileMap.put(file.getName(), file.getAbsolutePath());
		}
		flashPrinterPath = fileMap.get(FLASH_PRINTER_EXE);
		installPath = fileMap.get(INSTALL_BAT);
		unInstallPath = fileMap.get(UNINSTALL_BAT);
	}
	

	public boolean docToSwf(String originPath,String destPath)
			throws Exception {
		String destFolder = destPath.substring(0,destPath.lastIndexOf("\\"));
		
		logger.replace("源路径" + originPath);
		logger.replace("转换后路径" + destPath);
		
		new File(destFolder).mkdirs();
		String command = flashPrinterPath + SPACE + originPath + SPACE + "-o"
				+ SPACE + destPath;
		Process process = runtime.exec(command);
		process.waitFor();
		process.destroy();
		
		File outFile = new File(destPath);
		if(outFile.exists()){
			logger.replace("转换成功" + originPath);
		}else{
			logger.replace("转换失败" + originPath);
		}
		
		return true;
	}
	

	public void install() throws Exception {
		String cmd = CMD_C_START + installPath;
		Process process = runtime.exec(cmd,null,new File(flashPaperDir));
		process.waitFor();
		process.destroy();
	}

	public void uninstall() throws Exception {
		String cmd = CMD_C_START + unInstallPath;
		Process process = runtime.exec(cmd,null,new File(flashPaperDir));
		process.waitFor();
		process.destroy();
	}

}
