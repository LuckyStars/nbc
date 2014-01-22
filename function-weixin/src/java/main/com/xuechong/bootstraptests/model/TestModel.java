package com.xuechong.bootstraptests.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mvctests")
public class TestModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column  
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer id;
	@Column(name="name",insertable=true,unique=false,updatable=true,nullable=true)
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
