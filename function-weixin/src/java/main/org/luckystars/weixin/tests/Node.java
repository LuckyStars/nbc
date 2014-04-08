package org.luckystars.weixin.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Node {
	public static void main(String[] args) throws Exception {
		
		new Thread(new NodeTest("TEST1|")).start();
		new Thread(new NodeTest("TEST2|")).start();
		new Thread(new NodeTest("TEST3|")).start();
		new Thread(new NodeTest("TEST4|")).start();
		new Thread(new NodeTest("TEST5|")).start();
		new Thread(new NodeTest("TEST6|")).start();
		new Thread(new NodeTest("TEST7|")).start();
		new Thread(new NodeTest("TEST8|")).start();
		new Thread(new NodeTest("TEST9|")).start();
		new Thread(new NodeTest("TEST0|")).start();
		
		
	}
	
	static class NodeTest implements Runnable{
		private String id;
		NodeTest(String id){this.id = id;}
		@Override
		public void run() {
			for (int i = 0; i < Integer.MAX_VALUE-10000; i++) {
				try {
					HttpURLConnection con = (HttpURLConnection) new URL("http://127.0.0.1:8888/simple.html").openConnection();
					con.setDoInput(true);
					con.setDoOutput(true);
					con.connect();
					con.getOutputStream().write(1);
					con.getOutputStream().flush();
					con.getOutputStream().close();
					System.out.println(id + i + ":"+con.getResponseCode());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
