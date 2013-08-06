package com.nbcedu.function.cardmanage.core.util.exl.mapping;

import java.util.List;

/**
 * the content of each sheet
 * @author xuechong
 *
 */
public class SheetContent {
	/**the title of the sheet**/
	private final String head;
	/**the search conditions **/
	private final List<String> conditions;
	/**the data content**/
	@SuppressWarnings("unchecked")
	private final List dataList;
	private final Integer viewType;
	
	@SuppressWarnings("unchecked")
	public SheetContent(String head, List<String> conditions, List dataList,
			Integer viewType) {
		super();
		this.head = head;
		this.conditions = conditions;
		this.dataList = dataList;
		this.viewType = viewType;
	}
	public String getHead() {
		return head;
	}
	public List<String> getConditions() {
		return conditions;
	}
	@SuppressWarnings("unchecked")
	public List getDataList() {
		return dataList;
	}
	public Integer getViewType() {
		return viewType;
	}
	
}
