package com.nbcedu.function.syllabus.utils;

public class Utils {
	/**
	 * 阿拉伯数字转换
	 * @param k
	 * @return
	 */
	public static String numToCharacers(int k) {
		switch (k) {
		case 1:
			return  "一";
		case 2:
			return  "二";
		case 3:
			return  "三";
		case 4:
			return  "四";
		case 5:
			return  "五";
		case 6:
			return  "六";
		case 7:
			return  "七";
		case 8:
			return  "八";
		case 9:
			return  "九";
		case 10:
			return  "十";
		default:
			return  "";
		}
	}
	public static int characersToNum(char k) {
		switch (k) {
		case '一':
			return  1;
		case '二':
			return  2;
		case '三':
			return  3;
		case '四':
			return  4;
		case '五':
			return  5;
		case '六':
			return  6;
		case '七':
			return  7;
		case '八':
			return  8;
		case '九':
			return  9;
		case '十':
			return  10;
		default:
			return  0;
		}
	}
	/**
	 * 截取后缀(小写)
	 * @param str
	 * @return
	 */
	public static String subFileSuffix(String str){
		int beginIndex = str.indexOf(".");
		return str.substring(beginIndex+1, str.length()).toLowerCase();
	}
}
