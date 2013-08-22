package com.nbcedu.function.teachersignup.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class Utils {
	public static final class Dates{
		private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		public static String fullDateStr(Date date){
			return sdf.format(date);
		}
		
	}
	
	public static final class StreamUtil{
		public static void copy(InputStream in,OutputStream out) throws IOException{
			byte[] buffer = new byte[1024];
			while(true){
				int bytesRead = in.read(buffer);
				if(bytesRead==-1){
					break;
				}
				out.write(buffer,0,bytesRead);
			}
		}
	}
}
