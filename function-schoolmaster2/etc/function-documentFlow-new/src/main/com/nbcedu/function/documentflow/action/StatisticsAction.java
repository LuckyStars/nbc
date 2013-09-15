/*
 * @Title: StatisticsAction.java
 * @Package com.nbcedu.function.documentflow.action
 * @Description: StatisticsVO的请求处理控制器。
 * @author Wang Zhuoxuan wangzhuoxuan@nbcedu.com
 * @date 2011-9-10 下午06:35:21
 * @version V1.0
 *
 * Modification History:  
 * Date         Author      Version     Description  
 * -------------------------------------------------------------- 
 * 2011-9-10                          
 */
package com.nbcedu.function.documentflow.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.nbcedu.common.utils.PagerUtils;
import com.nbcedu.core.privilege.model.User;
import com.nbcedu.function.documentflow.biz.DocumentTaskBiz;
import com.nbcedu.function.documentflow.biz.ExporterBiz;
import com.nbcedu.function.documentflow.utils.StringUtil;
import com.nbcedu.function.documentflow.vo.QueryConditionVO;
import com.nbcedu.function.documentflow.vo.StatisticsVO;
import com.opensymphony.xwork2.ActionContext;

/** 
 * <p>StatisticsVO的请求处理控制器</p>
 * 
 * @author Wang Zhuoxuan
 * Create at:2011-9-10 下午06:35:21
 */
public class StatisticsAction {

	/**
	 * 统计文件名称
	 */
	private String fileName;
	/**
	 * 统计文件路径
	 */
	private String filePath;
	/**
	 * PagerUtils对象
	 */
	private PagerUtils pagerUtils = new PagerUtils();
	/**
	 * QueryConditionVO对象
	 */
	private QueryConditionVO queryConditionVo = new QueryConditionVO();
	/**
	 * 统计信息列表
	 */
	private List<StatisticsVO> statisticsVoList;
	/**
	 * DocumentTaskBiz对象
	 */
	private DocumentTaskBiz documentTaskBiz;
	/**
	 * ExporterBiz对象
	 */
	private ExporterBiz exporterBiz;
	
	/** 
	 * 显示统计信息
	 * 
	 * @return 跳转到统计信息页面
	 */ 
	public String display() {
		pagerUtils.setPageSize(10);
		statisticsVoList = documentTaskBiz.findDocumentTasksStat(queryConditionVo, pagerUtils);
		return "statistics";
	}
	
	/** 
	 * 导出统计信息
	 * 
	 * @return 返回stream类型的result试图
	 * @throws UnsupportedEncodingException 如果URLEncoder编码出现异常
	 */ 
	public String export() throws UnsupportedEncodingException {
		String userId = ((User) ActionContext.getContext().getSession().get("documentFlow_init")).getPid();
		String starting = ServletActionContext.getRequest().getParameter("starting");
		String ending = ServletActionContext.getRequest().getParameter("ending");
		
		queryConditionVo.setStarting(starting);
		queryConditionVo.setEnding(ending);
		
		statisticsVoList = documentTaskBiz.findDocumentTasksStat(queryConditionVo, null);
		filePath = exporterBiz.export(statisticsVoList, userId, 
				ServletActionContext.getServletContext().getRealPath("/WEB-INF/reports"));
		fileName = URLEncoder.encode("documentFlow_" + StringUtil.getDateString(new Date()) + ".xls", "UTF-8");
		return "export";
	}
	

	/** 
	 * 读取文件输入流
	 * 
	 * @return InputStream对象
	 * @throws Exception 如果读入输入流出错
	 */ 
	public InputStream getDownloadFile() throws Exception {
        return new FileInputStream(filePath);  
    }
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the pagerUtils
	 */
	public PagerUtils getPagerUtils() {
		return pagerUtils;
	}

	/**
	 * @param pagerUtils the pagerUtils to set
	 */
	public void setPagerUtils(PagerUtils pagerUtils) {
		this.pagerUtils = pagerUtils;
	}

	/**
	 * @return the queryConditionVo
	 */
	public QueryConditionVO getQueryConditionVo() {
		return queryConditionVo;
	}

	/**
	 * @param queryConditionVo the queryConditionVo to set
	 */
	public void setQueryConditionVo(QueryConditionVO queryConditionVo) {
		this.queryConditionVo = queryConditionVo;
	}

	/**
	 * @return the statisticsVoList
	 */
	public List<StatisticsVO> getStatisticsVoList() {
		return statisticsVoList;
	}

	/**
	 * @param statisticsVoList the statisticsVoList to set
	 */
	public void setStatisticsVoList(List<StatisticsVO> statisticsVoList) {
		this.statisticsVoList = statisticsVoList;
	}

	/**
	 * @param documentTaskBiz the documentTaskBiz to set
	 */
	public void setDocumentTaskBiz(DocumentTaskBiz documentTaskBiz) {
		this.documentTaskBiz = documentTaskBiz;
	}

	/**
	 * @param exporterBiz the exporterBiz to set
	 */
	public void setExporterBiz(ExporterBiz exporterBiz) {
		this.exporterBiz = exporterBiz;
	}
}
