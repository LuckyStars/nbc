/*
 * @Title: ExporterBizImpl.java
 * @Package com.nbcedu.function.documentflow.biz.impl
 * @Description: ExporterBiz实现类。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-2 下午04:13:31
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-2                          
 */
package com.nbcedu.function.documentflow.biz.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.nbcedu.function.documentflow.biz.ExporterBiz;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.StatisticsVO;

/** 
 * <p>ExporterBiz实现类</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-2 下午04:13:31
 */
public class ExporterBizImpl implements ExporterBiz {
	
	/**
	 * Excel文件对象
	 */
	private WritableWorkbook book;
	/**
	 * 工作表对象
	 */
	private WritableSheet sheet;

	/* 
	 * @see com.nbcedu.function.documentflow.biz.ExporterBiz#export(java.util.List, java.lang.String)
	 */
	@Override
	public String export(List<StatisticsVO> statisticsVoList, String userId, String directory) {
		String filePath = generateFilePath(userId, directory);
//		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		System.out.println(filePath);
		File f = new File(filePath);
		if(!f.getParentFile().exists()){
			f.getParentFile().mkdirs();
		}
		
		try {
			book = Workbook.createWorkbook(f);
			//创建工作表
			sheet = book.createSheet("统计结果", 0);
			//创建表头单元格内容
			sheet.addCell(new Label(0, 0, "人员"));
			sheet.addCell(new Label(1, 0, "所属部门"));
			sheet.addCell(new Label(2, 0, "已处理公文量"));
			sheet.addCell(new Label(3, 0, "占全部已处理公文（人次）比例"));
			sheet.addCell(new Label(4, 0, "过期公文量"));
			sheet.addCell(new Label(5, 0, "占全部过期公文（人次）比例"));
			//迭代统计数据填充单元格
			for (int i = 0; i < statisticsVoList.size(); i++) {
				sheet.addCell(new Label(0, i + 1, statisticsVoList.get(i).getUserName()));
				sheet.addCell(new Label(1, i + 1, statisticsVoList.get(i).getDepartment()));
				sheet.addCell(new Label(2, i + 1, String.valueOf(statisticsVoList.get(i).getHandledCount())));
				sheet.addCell(new Label(3, i + 1, statisticsVoList.get(i).getHandlePercent()));
				sheet.addCell(new Label(4, i + 1, String.valueOf(statisticsVoList.get(i).getUnhandledCount())));
				sheet.addCell(new Label(5, i + 1, statisticsVoList.get(i).getUnhandledPercent()));
			}
			
			book.write();
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return filePath;
	}

	/** 
	 * 生成文件路径
	 * 
	 * @param userId 操作人的唯一标识
	 * @param directory 生成报告的目录
	 * @return 文件路径字符串
	 */ 
	private String generateFilePath(String userId, String directory) {
		StringBuilder sb = new StringBuilder(directory + "\\");
//		StringBuilder sb = new StringBuilder("");
		//文件名中加入时间字符串，例如：20110902170314
		sb.append(StringUtil.getDateTimeString(new Date()).replaceAll("[\\s,\\-,:]", ""));
		sb.append("_");
		sb.append(userId);
		sb.append(".xls");
		return sb.toString();
	}
}
