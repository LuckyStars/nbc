package com.nbcedu.function.cardmanage.core.util;

import java.util.List;

/**
 * 分页模型Bean
 */
@SuppressWarnings("unchecked")
public class PagerModel {
	private int currentPage = 1; // 当前页码
	private int pageSize = 10; // 页面大小
	
	private int recordCount; // 总记录数
	private List recordList; // 记录列表

	private int totalPage; // 总页数
	private int startPageIndex; // 开始页码
	private int endPageIndex; // 结束页码
	
	/**
	 * 获取setFirstResult值
	 * 
	 * @param currentPage 当前页码
	 * @param pageSize 页面大小
	 * @return firstResult
	 */
	public static int getFirstResult(int currentPage, int pageSize) {
		return (currentPage - 1) * pageSize;
	}

	public PagerModel() {
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public List getRecordList() {
		return recordList;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setRecordCount(int recordCount) {
		this.totalPage = (recordCount + pageSize - 1) / pageSize;

		if (totalPage <= 10) {
			this.startPageIndex = 1;
			this.endPageIndex = totalPage;
		} else {
			this.startPageIndex = currentPage - 5 - 1;
			this.endPageIndex = currentPage + 5;

			if (this.startPageIndex < 1) {
				this.startPageIndex = 1;
				this.endPageIndex = 10;
			} else if (this.endPageIndex > totalPage) {
				this.endPageIndex = totalPage;
				this.startPageIndex = totalPage - 10 + 1;
			}
		}
		
		this.recordCount = recordCount;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}

	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}
}
