package com.nbcedu.function.cardmanage.core.util.strings;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nbcedu.function.cardmanage.core.exception.ActionException;


/**
 * 示例：
 * ---------------------------------------------------------
 *	try {
 *		CheckText.checkFormat(val, "C", 200, 0, 1, "活动名称");
 *		CheckText.checkFormat(val, "D", 64, 0, 1, "活动结束时间");
 *		CheckText.checkFormat(val, "N", 8, 0, 1, "赠送积分数");
 *	} catch (ActionException e) {
 *		this.addActionMessage(e.getMessage());
 *	}
 * ---------------------------------------------------------
 * 
 * 校验
 * @param type
 *            字段类型 "C" 为字符串，"N" 为数字,"D"为日期类型
 * @param length
 *            (最大长度) 整型数字 0表示不限制最大长度
 * @param precision
 *            (精度) 整型数字 表示保留小数位的长度,0表示是整数
 * @param mode
 *            (定长标志) 整型数字 0（定长不允许空）/1（不定长不可空）/2（不定长允许空）/3（定长允许空）
 * @param describe
 *            (表单描述) 比如：电话号码，用户姓名等等,必须写
 * @author qinyuan
 */
public class CheckText {
	private CheckText() {
	}

	public static void checkFormat(String text, String type, int length, int precision, int mode, String describe)
			throws ActionException {
		if ((type == null) || ("".equals(type))) {
			throw new ActionException("字段类型为空");
		}

		if ((!"C".equals(type)) && (!"N".equals(type)) && (!"D".equals(type))) {
			throw new ActionException("无法识别字段类型");
		}

		if (length < 0) {
			throw new ActionException("非法长度");
		}

		if (precision < 0) {
			throw new ActionException("非法精度");
		}

		if ((mode < 0) || (mode > 3)) {
			throw new ActionException("非法校验模式");
		}

		if ((describe == null) || ("".equals(describe))) {
			throw new ActionException("字段名称为空");
		}

		if ("C".equals(type)) {
			if (mode == 0) {
				if ((text == null) || ("".equals(text))) {
					throw new ActionException(describe + "字段长度为空");
				}
				if ((length != 0) && (text.getBytes().length != length))
					throw new ActionException(describe + "字段长度不为" + length);
			} else if (mode == 1) {
				if ((text == null) || ("".equals(text)))
					throw new ActionException(describe + "字段为空");
				if ((length != 0) && (text.getBytes().length > length))
					throw new ActionException(describe + "字段长度大于" + length);
			} else if (mode == 2) {
				if ((text != null) && (length != 0) && (text.getBytes().length > length))
					throw new ActionException(describe + "字段长度大于" + length);
			} else if ((mode == 3) && (text != null) && (!"".equals(text)) && (text.getBytes().length != length) && (length != 0)) {
				throw new ActionException(describe + "字段长度不为" + length);
			}
		} else if ("D".equals(type)) {
			if (mode == 0) {
				if ((text == null) || ("".equals(text))) {
					throw new ActionException(describe + "字段长度为空");
				}
				if ((length != 0) && (text.getBytes().length != length)) {
					throw new ActionException(describe + "字段长度不为" + length);
				}
				if (!isDate(text))
					throw new ActionException(describe + "字段不能格式化为时间类型");
			} else if (mode == 1) {
				if ((text == null) || ("".equals(text))) {
					throw new ActionException(describe + "字段长度为空");
				}
				if ((length != 0) && (text.getBytes().length > length)) {
					throw new ActionException(describe + "字段长度大于" + length);
				}
				if (!isDate(text))
					throw new ActionException(describe + "字段不能格式化为时间类型");
			} else if (mode == 2) {
				if ((text != null) && (!"".equals(text))) {
					if ((length != 0) && (text.getBytes().length > length)) {
						throw new ActionException(describe + "字段长度大于" + length);
					}
					if (!isDate(text))
						throw new ActionException(describe + "字段不能格式化为时间类型");
				}
			} else if ((mode == 3) && (text != null) && (!"".equals(text))) {
				if ((length != 0) && (text.getBytes().length != length)) {
					throw new ActionException(describe + "字段长度不为" + length);
				}
				if (!isDate(text)) {
					throw new ActionException(describe + "字段不能格式化为时间类型");
				}
			}
		} else if ("N".equals(type)) {
			if (mode == 0) {
				if ((text == null) || ("".equals(text))) {
					throw new ActionException(describe + "字段为空");
				}
				if ((length != 0) && (text.getBytes().length != length)) {
					throw new ActionException(describe + "字段长度不为" + length);
				}
				if (precision == 0) {
					if (!isNumber(text))
						throw new ActionException(describe + "字段不能转换成整数");
				} else {
					String[] ts = text.split("\\.");
					if (ts.length == 1) {
						if (!isNumber(ts[0]))
							throw new ActionException(describe + "字段不能转换成数字");
					} else {
						if (ts.length != 2) {
							throw new ActionException(describe + "字段不能转换成数字");
						}
						if (!isNumber(ts[0])) {
							throw new ActionException(describe + "字段不能转换成数字");
						}
						if ((ts[1] != null) && (!"".equals(ts[1]))) {
							if (!isNumber(ts[1])) {
								throw new ActionException(describe + "字段不能转换成数字");
							}
							if (ts[1].getBytes().length > precision)
								throw new ActionException(describe + "字段精度大于" + precision);
						}
					}
				}
			} else if (mode == 1) {
				if ((text == null) || ("".equals(text))) {
					throw new ActionException(describe + "字段长度为空");
				}
				if ((length != 0) && (text.getBytes().length > length)) {
					throw new ActionException(describe + "字段长度大于" + length);
				}
				if (precision == 0) {
					if (!isNumber(text))
						throw new ActionException(describe + "字段不能转换成整数");
				} else {
					String[] ts = text.split("\\.");
					if (ts.length == 1) {
						if (!isNumber(ts[0]))
							throw new ActionException(describe + "字段不能转换成数字");
					} else {
						if (ts.length != 2) {
							throw new ActionException(describe + "字段不能转换成数字");
						}
						if (!isNumber(ts[0])) {
							throw new ActionException(describe + "字段不能转换成数字");
						}
						if ((ts[1] != null) && (!"".equals(ts[1]))) {
							if (!isNumber(ts[1])) {
								throw new ActionException(describe + "字段不能转换成数字");
							}
							if (ts[1].getBytes().length > precision)
								throw new ActionException(describe + "字段精度大于" + precision);
						}
					}
				}
			} else if (mode == 2) {
				if ((text != null) && (!"".equals(text))) {
					if ((length != 0) && (text.getBytes().length > length)) {
						throw new ActionException(describe + "字段长度大于" + length);
					}
					if (precision == 0) {
						if (!isNumber(text))
							throw new ActionException(describe + "字段不能转换成整数");
					} else {
						String[] ts = text.split("\\.");
						if (ts.length == 1) {
							if (!isNumber(ts[0]))
								throw new ActionException(describe + "字段不能转换成数字");
						} else {
							if (ts.length != 2) {
								throw new ActionException(describe + "字段不能转换成数字");
							}
							if (!isNumber(ts[0])) {
								throw new ActionException(describe + "字段不能转换成数字");
							}
							if ((ts[1] != null) && (!"".equals(ts[1]))) {
								if (!isNumber(ts[1])) {
									throw new ActionException(describe + "字段不能转换成数字");
								}
								if (ts[1].getBytes().length > precision)
									throw new ActionException(describe + "字段精度大于" + precision);
							}
						}
					}
				}
			} else if ((mode == 3) && (text != null) && (!"".equals(text))) {
				if ((length != 0) && (text.getBytes().length != length)) {
					throw new ActionException(describe + "字段长度不为" + length);
				}
				if (precision == 0) {
					if (!isNumber(text))
						throw new ActionException(describe + "字段不能转换成整数");
				} else {
					String[] ts = text.split("\\.");
					if (ts.length == 1) {
						if (!isNumber(ts[0]))
							throw new ActionException(describe + "字段不能转换成数字");
					} else {
						if (ts.length != 2) {
							throw new ActionException(describe + "字段不能转换成数字");
						}
						if (!isNumber(ts[0])) {
							throw new ActionException(describe + "字段不能转换成数字");
						}
						if ((ts[1] != null) && (!"".equals(ts[1]))) {
							if (!isNumber(ts[1])) {
								throw new ActionException(describe + "字段不能转换成数字");
							}
							if (ts[1].getBytes().length > precision)
								throw new ActionException(describe + "字段精度大于" + precision);
						}
					}
				}
			}
		}
	}

	// 数字
	private static boolean isNumber(String str) {
		if ((str == null) || ("".equals(str.trim()))) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str.trim());

		return isNum.matches();
	}

	// 日期
	private static boolean isDate(String str) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			df.parse(str);

			String[] ts = str.split("-");
			if (ts.length != 3) {
				return false;
			}
			if ((ts[0].length() != 4) || (ts[1].getBytes().length > 2) || (ts[2].getBytes().length > 2)) {
				return false;
			}

			int year = Integer.parseInt(ts[0]);
			int month = Integer.parseInt(ts[1]);
			int date = Integer.parseInt(ts[2]);
			int maxdate = 0;
			boolean isrn = false;

			if ((month > 12) || (month <= 0)) {
				return false;
			}

			if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
				isrn = true;
			}

			if (month == 2) {
				if (isrn)
					maxdate = 29;
				else
					maxdate = 28;
			} else if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10)
					|| (month == 12))
				maxdate = 31;
			else {
				maxdate = 30;
			}

			return (date <= maxdate) && (date > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

}