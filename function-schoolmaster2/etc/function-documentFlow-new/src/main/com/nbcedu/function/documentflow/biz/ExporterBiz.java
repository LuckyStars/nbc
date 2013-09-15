/*
 * @Title: ExporterBiz.java
 * @Package com.nbcedu.function.documentflow.biz
 * @Description: Excel操作的业务逻辑接口，该接口包含了导出Excel的所有业务方法。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-2 下午04:09:45
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-2                          
 */
package com.nbcedu.function.documentflow.biz;

import java.util.List;

import com.nbcedu.function.documentflow.vo.StatisticsVO;

/** 
 * <p>Excel操作的业务逻辑接口</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-2 下午04:09:45
 */
public interface ExporterBiz {
	/** 
	 * 将统计信息导出Excel
	 * 
	 * @param statisticsVoList 统计信息列表
	 * @param userId 操作人唯一标识
	 * @param directory 输出Excel文件的目录
	 * @return 导出Excel的文件名
	 */ 
	String export(List<StatisticsVO> statisticsVoList, String userId, String directory);
}
