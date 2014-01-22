package com.xuechong.bootstraptests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.xuechong.bootstraptests.model.TestModel;

public class TestService {
	@Autowired(required=true)
	private JdbcTemplate jdbcTemplate;
	
	public void addSomeDBtrans(){
		int name = (int)(Math.random()*999999);
		TestModel t = new TestModel();
		t.setName(String.valueOf(name));
		System.out.println("hibernate name is:" + name);
		
		name = (int)(Math.random()*999999);
		String sql ="insert into mvctests(name) values('{value}');".replace("{value}", name+"");
		this.jdbcTemplate.execute(sql.toString());
		System.out.println("jdbc name is:" + name);
		
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
