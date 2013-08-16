package com.nbcedu.function.cardmanage.core.pager;


import java.util.List;

@SuppressWarnings("unchecked")
public class PagerModel {
	private List datas;//当前页显示的数据列表
	private int total;//分页显示的所有数据总数
	private int totalPageNo;//总的页面数
	private int pageSize;
	
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalPageNo
	 */
	public int getTotalPageNo() {
		return totalPageNo;
	}
	/**
	 * @param totalPageNo the totalPageNo to set
	 */
	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}
	
	
}
