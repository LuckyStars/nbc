package com.nbcedu.function.cardmanage.core.util.number;

import java.math.BigDecimal;

/** 
 * <p>描述 提供高精度的运算支持，所以函数以double为参数类型，兼容int与float</p>
 * 
 * @author Li Wei
 */ 
public final class NumberUtils {
	/**
	 * 方法描述:精确的加法运算  
	 */
	public static float add(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).floatValue();
	}

	/** 
	 * <p>精确的减法运算</p>
	 */ 
	public static float subtract(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.subtract(b2).floatValue();
	}

	/**
	 * 提供精确的乘法运算.
	 */
	public static float multiply(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).floatValue();
	}

	/**
	 * 提供（相对）精确的除法运算.
	 */
	public static float divide(float v1, float v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2).floatValue();
	}
	
	/**
	 * 方法描述:精确的加法运算  
	 * @param scale 运算结果小数后精确的位数
	 */
	public static float add(float v1, float v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
		.floatValue();
	}

	/** 
	 * <p>精确的减法运算</p>
	 * @param scale 运算结果小数后精确的位数
	 */ 
	public static float subtract(float v1, float v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Float.toString(v1));
		BigDecimal b2 = new BigDecimal(Float.toString(v2));
		return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
		.floatValue();
	}

	/**
	 * 提供精确的乘法运算.
	 * @param scale 运算结果小数后精确的位数
	 */
	public static float multiply(float v1, float v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.floatValue();
	}

	/**
	 * 提供（相对）精确的除法运算.
	 * 由scale参数指定精度，以后的数字四舍五入.
	 * 
	 * @param scale 表示表示需要精确到小数点以后几位
	 */
	public static float divide(float v1, float v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理.
	 * 
	 * @param scale 小数点后保留几位
	 */
	public static float round(float v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}


}
