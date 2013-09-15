package doctrans.form;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StatusLogger {
	
	private MainForm form;
	private static final String SUF = "当前状态:";
	private File logFile = new File("/documentTrans.log");
	
	StatusLogger(MainForm form){
		this.form = form;
	}
	
	public void replace(String str){
		this.form.getStatusLabel().setText(SUF + str);
		try {
			this.writeLog(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void append(String str){
		this.form.getStatusLabel().setText(this.form.getStatusLabel().getText() + str);
		try {
			this.writeLog(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * clear all
	 */
	public void clear(){
		this.form.getStatusLabel().setText("");
	}
	
	private void writeLog(String content) throws IOException{
		FileWriter writer =null;
		try {
			writer = new FileWriter(this.logFile,true);
			writer.append(content + "\r\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){writer.close();}
		}
	}
}
