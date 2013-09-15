package com.nbcedu.function.documentflow.model;

/**
 * 每条数据仅存储一个用户-角色对应信息
 * 
 * @since 2012-11-15
 */
public class DoucmentUserRole implements java.io.Serializable {
	private static final long serialVersionUID = 5253646005351538138L;
	
	private String id;//主键
	private String pid; //pid
	private String personName; //用户姓名
	private String roleName; // 只存储一条角色!!!!
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
}