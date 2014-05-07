package com.nbcedu.function.syllabus.devcore.util.exl.testss;

import com.nbcedu.function.syllabus.devcore.util.exl.annotations.ExlData;
import com.nbcedu.function.syllabus.devcore.util.exl.annotations.ExlModel;



@ExlModel(viewTypes={0,1,2,3})
public class PersonModel {
	
	@ExlData(sortId=1,title={"姓名","姓名","",""})
	private String name;
	@ExlData(sortId=2,title={"年龄","age","年龄",""})
	private Integer age;
	@ExlData(sortId=3,title={"familyName","familyName","familyName","familyName"})
	private String familyName;
	@ExlData(sortId=4,title={"IDCODE","IDCODE","IDCODE","IDCODE"})
	private String idCode;
	
	public PersonModel() {
		super();
	}
	public PersonModel(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getIdCode() {
		return idCode;
	}
	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}
	
}
