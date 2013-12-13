package com.nbcedu.function.schoolmaster2.core.pager;


import java.util.List;

@SuppressWarnings("unchecked")
public class PagerModel {
	private List datas;//当前页显示的数据列表
	private int total;//分页显示的所有数据总数
	private int totalPageNo;//总的页面数
	private int pagesize;//每页显示数
	private int pageIndex ;//不用pg标签 当前页
	
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
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	
}
