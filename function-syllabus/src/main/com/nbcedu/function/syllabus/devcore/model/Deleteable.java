package com.nbcedu.function.syllabus.devcore.model;

import java.io.Serializable;

/**
 * 可逻辑删除的接口<br>
 * 值为REMOVED时为"已经被删除"<br>
 * 值为ENABLE时为"未被删除"
 * @author xuechong
 */
public interface Deleteable extends Serializable{
	/**已被删除的状态*/
	public final int REMOVED = 0;
	/**可以使用的状态的状态*/
	public final int ENABLED = 1;
	public Integer getDeleteFlag();
	public void setDeleteFlag(Integer deleteFlag);
}
