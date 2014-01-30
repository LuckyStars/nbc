package com.nbcedu.function.weixin.tests;

import java.util.HashMap;
import java.util.Map;

public class NewThisTest {
	public static Map<String,TClass> map = new HashMap<String, TClass>();
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while(true){
					TClass c = map.get("aaa");
					if(c!=null){System.out.println(c.getName());break;}
					else{System.out.println("null");}
					try {
						Thread.currentThread().sleep(100L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				new TClass("aaaaaa");
			}
		}).start();
	}
	

}
class TClass{
	
	private String name;
	
	TClass(String name){
		NewThisTest.map.put("aaa", this);
		System.out.println("sleep start");
		try {
			Thread.currentThread().sleep(2L*1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.name = name;
		System.out.println("sleep done");
	}
	
	public String getName(){
		return this.name;
	}
	
}
