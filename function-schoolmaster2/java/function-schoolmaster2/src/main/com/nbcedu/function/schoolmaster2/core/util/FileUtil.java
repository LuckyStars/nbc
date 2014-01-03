package com.nbcedu.function.schoolmaster2.core.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.log4j.Logger;

/**
 * 
 * <p>
 * 文件上传
 * </p>
 * @author 黎青春 Create at:2012-4-6 下午03:51:37
 */
public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	static final int BUFFERSIZE = 4*1024;

	public static String getExtension(String fileName) {
		String extension = "";
		if (fileName != null && !"".equals(fileName)) {
			extension = fileName.substring(fileName.lastIndexOf("."));
		}
		return extension;
	}

	public static void saveFile(String fileName, File file) throws IOException {
		createPath(fileName);
		BufferedInputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
			outputStream = new FileOutputStream(fileName);
			copy(inputStream, outputStream);
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
			if (inputStream != null)
				inputStream.close();
		}
	}

	public static void createPath(String fileName) {
		int pathpos = fileName.lastIndexOf(File.separatorChar);
		String path = "";
		if (pathpos > 0) {
			path = fileName.substring(0, pathpos);
			File file = new File(path);
			if ((!(file.exists())) && (!(file.mkdirs()))) {
				return;
			}

			file = new File(fileName);
			if (file.exists())
				file.delete();
		}
	}

	public static void safeCopyAndClose(InputStream in, OutputStream out){
		try {
			if(in!=null&&out!=null){
				byte[] buffer = new byte[1024];
				while (true) {
					int bytesRead = in.read(buffer);
					if (bytesRead == -1) {
						break;
					}
					out.write(buffer, 0, bytesRead);
				}
			}
		} catch (IOException e) {
			logger.error(e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
	}
	public static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		while (true) {
			int bytesRead = in.read(buffer);
			if (bytesRead == -1) {
				break;
			}
			out.write(buffer, 0, bytesRead);
		}
	}
	/**
	 * NIO文件拷贝
	 * @param inFile
	 * @param outFile
	 * @throws IOException
	 * @author xuechong
	 */
	public static void copyWithNIO(FileInputStream inFile,
			FileOutputStream outFile) throws IOException {
		FileChannel inChannel = inFile.getChannel();
		FileChannel outChannel = outFile.getChannel();
		for (ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024); 
			inChannel.read(buffer) != -1;
			buffer.clear()) {
			
			buffer.flip();
			while (buffer.hasRemaining())
				outChannel.write(buffer);
		}
		inChannel.close();
		outChannel.close();
	}
}
