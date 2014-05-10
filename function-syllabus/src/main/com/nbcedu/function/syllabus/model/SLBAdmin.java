package com.nbcedu.function.syllabus.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="t_slb_admin")
@SuppressWarnings("serial")
public class SLBAdmin implements Serializable{
	
	@Id
	@Column(unique=true,length=32,nullable=false)
	@GeneratedValue(generator = "nbc-uuid")
    @GenericGenerator(name = "nbc-uuid",strategy="uuid")
	private String id;
	@Column(unique=false,length=32,nullable=false,name="pid")
	private String pid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}
