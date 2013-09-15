/*
 * @Title: StringUtil.java
 * @Package com.nbcedu.function.survey.util
 * @Description: 字符串工具类，该类包括了关于字符串操作的常用方法
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-3-24 下午04:02:12
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-3-24                          
 */
package com.nbcedu.function.documentflow.utils;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
 * <p>字符串工具类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-3-24 下午04:02:12
 */
public class StringUtil {
	
	/**
	 * 代表换行标签的常量
	 */
	private static final String BR = "<br/>";
	
	/**
	 * 分割字符串
	 * 
	 * @param str String 原始字符串
	 * @param splitsign String 分隔符
	 * @return String[] 分割后的字符串数组
	 */
	public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null) {
			return null;
		}
		List<String> al = new ArrayList<String>();
		while ((index = str.indexOf(splitsign)) != -1) {
			al.add(str.substring(0, index));
			str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return (String[]) al.toArray(new String[0]);
	}

	/**
	 * 替换字符串
	 * 
	 * @param from String 原始字符串
	 * @param to String 目标字符串
	 * @param source String 母字符串
	 * @return String 替换后的字符串
	 */
	public static String replace(String from, String to, String source) {
		if (source == null || from == null || to == null) {
			return null;
		}
		StringBuffer bf = new StringBuffer("");
		int index = -1;
		while ((index = source.indexOf(from)) != -1) {
			bf.append(source.substring(0, index) + to);
			source = source.substring(index + from.length());
			index = source.indexOf(from);
		}
		bf.append(source);
		return bf.toString();
	}

	/**
	 * 替换字符串，能能够在HTML页面上直接显示(替换双引号和小于号)
	 * 
	 * @param str String 原始字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlencode(String str) {
		if (str == null) {
			return null;
		}

		return replace("\"", "&quot;", replace("<", "&lt;", str));
	}

	/**
	 * 替换字符串，将被编码的转换成原始码（替换成双引号和小于号）
	 * 
	 * @param str String
	 * @return String
	 */
	public static String htmldecode(String str) {
		if (str == null) {
			return null;
		}

		return replace("&quot;", "\"", replace("&lt;", "<", str));
	}

	/**
	 * 在页面上直接显示文本内容，替换小于号，空格，回车，TAB
	 * 
	 * @param str String 原始字符串
	 * @return String 替换后的字符串
	 */
	public static String htmlshow(String str) {
		if (str == null) {
			return null;
		}

		str = replace("<", "&lt;", str);
		str = replace(" ", "&nbsp;", str);
		str = replace("\r\n", BR, str);
		str = replace("\n", BR, str);
		str = replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;", str);
		return str;
	}

	/**
	 * 返回指定字节长度的字符串
	 * 
	 * @param str String 字符串
	 * @param length int 指定长度
	 * @return String 返回的字符串
	 */
	public static String toLength(String str, int length) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		try {
			if (str.getBytes("GBK").length <= length) {
				return str;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= 3;
		while (length > 0) {
			c = str.charAt(index);
			if (c < 128) {
				length--;
			} else {
				length--;
				length--;
			}
			buff.append(c);
			index++;
		}
		buff.append("...");
		return buff.toString();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str 传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断输入的字符串是否符合Email样式.
	 * 
	 * @param str 传入的字符串
	 * @return 是Email样式返回true,否则返回false
	 */
	public static boolean isEmail(String str) {
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(str).matches();
	}
	
	/** 
	 * 判断字符串是否符合手机号码样式
	 * 
	 * @param str 要判断的字符串
	 * @return 如果符合手机号样式返回true，否则返回false
	 */ 
	public static boolean isMobile(String str) {
		return Pattern.matches("^(13[0-9]|15[0|3|6|7|8|9]|18[8|9])[0-9]{8}$", str);
	}

	/**
	 * 判断输入的字符串是否为纯汉字
	 * 
	 * @param str 传入的字符串
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否为空白,包括null和""
	 * 
	 * @param str 要判断的String对象
	 * @return 如果为null或空字符串返回true
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断是否为质数
	 * 
	 * @param x 要判断的整数
	 * @return 如果为质数，返回true
	 */
	public static boolean isPrime(int x) {
		if (x <= 7) {
			if (x == 2 || x == 3 || x == 5 || x == 7) {
				return true;
			}
		}
		int c = 7;
		if (x % 2 == 0) {
			return false;
		}
		if (x % 3 == 0) {
			return false;
		}
		if (x % 5 == 0) {
			return false;
		}
		int end = (int) Math.sqrt(x);
		while (c <= end) {
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 6;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 6;
		}
		return true;
	}

	/**
	 * 全角字符转半角字符
	 * 
	 * @param qjStr 要进行转换的全角字符串
	 * @return String 转换后的半角字符串
	 */
	public static String chageToBJ(String qjStr) {
		char[] chr = qjStr.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chr.length; i++) {
			chr[i] = (char) ((int) chr[i] - 65248);
			sb.append(chr[i]);
		}
		return sb.toString();
	}
	
	/** 
	 * 返回指定Date对象转换成的时间字符串，格式为
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date 指定要转换的Date对象
	 * @return 格式化的时间字符串
	 */ 
	public static String getDateTimeString(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.format(date);
	}
	
	/** 
	 * 将指定字符串转换成日期格式yyyy-MM-dd
	 * 
	 * @param dateStr 要进行转换的字符串
	 * @return 符合指定格式的Date对象
	 * @throws ParseException 如果无法解析指定字符串的开始处
	 */ 
	public static Date convertStringToDate(String dateStr) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.parse(dateStr);
	}
	
	/** 
	 * 将指定字符串转换成日期格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param datetimeStr 要进行转换的字符串
	 * @return 符合指定格式的Date对象
	 * @throws ParseException 如果无法解析指定字符串的开始处
	 */ 
	public static Date convertStringToDateTime(String datetimeStr) throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return f.parse(datetimeStr);
	}
	
	/** 
	 * 返回指定Date对象转换成的日期字符串，格式为
	 * yyyy-MM-dd
	 * 
	 * @param date 指定要转换的Date对象
	 * @return 格式化的日期字符串
	 */ 
	public static String getDateString(Date date) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		return f.format(date);
	}
	
	/** 
	 * 返回两个日期之间的天数之差
	 * 
	 * @param d1 第一个日期
	 * @param d2 第二个日期
	 * @return 返回两个日期之间的天数
	 */ 
	public static long getDaysInterval(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) / 86400000;
	}
	
	/** 
	 * 将指定字符串转换成UTF-8编码
	 * 
	 * @param str 要转换编码的字符串
	 * @return 转换后的字符串
	 */ 
	public static String getUTF8String(String str) {
		String s = null;
		try {
			s = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/** 
	 * 获取中文字符串的拼音全拼，英文字符不变
	 * 
	 * @param cnStr 中文字符串
	 * @param caseType 拼音大小写标识，0：小写；1：大写
	 * @return 对应中文的拼音字符串
	 */ 
	public static String getPinYin(String cnStr, int caseType) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		char[] chars = cnStr.toCharArray();
		StringBuilder sb = new StringBuilder();
		
		if (caseType == 0) {
			format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		} else {
			format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		}
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        
        for (int i = 0; i < chars.length; i++) {
        	if (chars[i] > 128) { 
                try { 
                	sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], format)[0]); 
                } catch (BadHanyuPinyinOutputFormatCombination e) { 
                	e.printStackTrace(); 
                } 
	        } else { 
	                sb.append(chars[i]); 
	        } 
        }
		return sb.toString();
	}
	
	/** 
	 * 返回两个数的相除的百分比字符串
	 * 
	 * @param d1 被除数
	 * @param d2 除数
	 * @return 百分比字符串
	 */ 
	public static String getPercent(double d1, double d2) {
		NumberFormat numberFormat  =  NumberFormat.getPercentInstance();
		numberFormat.setMinimumFractionDigits(2);
		return numberFormat.format(d1 / d2);
	}
	
	/** 
	 * 随机生成表示颜色的字符串
	 * 
	 * @return 随机生成的颜色字符串
	 */ 
	public static String getRandomColor() {
		//颜色代码位数
        int colorLength = 6;
        
        //颜色代码数组
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        StringBuffer sb = new StringBuffer("#");
        Random random = new Random();
        for (int i = 0; i < colorLength; i++) {
        	sb.append(codeSequence[random.nextInt(16)]);
        }
        return sb.toString();
	}
	
	/** 
	 * 截取字符串，如果字符串长度小于等于定义的值则不截取
	 * 如果大于定义的长度则按照定义的长度截取最后附加省略号
	 * 
	 * @param str 要截取的字符串
	 * @param length 定义的字符串长度
	 * @return 截取后的字符串
	 */ 
	public static String split(String str, int length) {
		return str.length() > length ? str.substring(0, length) + "..." : str;
	}
}
