package com.nbcedu.function.teachersignup.core.util.properties;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author       作者 黎巍 测试通过
 * @version      版本 0.01
 * @filename     文件名 PropertiesUtil.java
 * @date         创建日期 Feb 15, 2011
 * @description  描述 读取配置文件的单例类
 */
public class PropertiesUtil {

	//私有化构造方法
	private PropertiesUtil() {
	}

	/**
	 * 描述:类级内部类（static），作用相当于其外部类的static成分，它的对象与外部类对象间不存在依赖关系，因此可直接创建。而对象级内部类的实例，是绑定在外部对象实例中的。
	 */
	public static class SingletonHolder {
		public static PropertiesUtil instance = new PropertiesUtil();
	}

	public static PropertiesUtil getInstance(String filePath) {
		PropertiesUtil pu = SingletonHolder.instance;
		pu.filePath = filePath;
		//调用读取配置文件的方法  
		readConfig(filePath);
		return pu;
	}

	private static Properties properties;

	//配置文件路径
	private String filePath;

	/**
	 * 方法名称:readConfig
	 * 作者:黎巍
	 * 创建日期:Feb 15, 2011
	 * 方法描述:读取配置文件，把配置文件中的内容读出来设置到属性上  
	 * @param config void
	 */
	private static void readConfig(String filePath) {
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = PropertiesUtil.class.getResourceAsStream(filePath);
			p.load(in);
			properties = p;
		} catch (IOException e) {
			System.out.println("装载配置文件出错了，具体堆栈信息如下：");
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		readConfig(filePath);
	}

	/**
	 * 以下为属性文件的操作类
	 */
	public String getParameter(String key) {
		return (String) properties.get(key);
	}

	public void setParameter(String key, String value) {
		properties.put(key, value);
	}
	
	public void delPropertie(String key) {
		properties.remove(key);
	}

	/**
	 * 方法名称:savePropertieToLocaltion
	 * 作者:黎巍
	 * 创建日期:Feb 16, 2011
	 * 方法描述:物理修改配置文件 注意物理地址为当前util类的相对目录 
	 * @paramvoid
	 */
	public void savePropertieToLocaltion() {
       String url = PropertiesUtil.class.getResource("/").toString();
       String path = url.substring(6,url.length());
       path = path.replaceAll("%20", " ");
		try {
			FileOutputStream out = new FileOutputStream(path+getFilePath());
			properties.store(out, null);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("没有提供的属性文件！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("存取属性文件有误，请检查属性文件！");
			e.printStackTrace();
		}
	}
}
