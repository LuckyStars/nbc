package org.luckystars.mqtests;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendStomp {
	
	public static void main(String[] args) {
		try {
			Socket soc = new Socket("127.0.0.1", 61616);
			//Socket soc = new Socket("172.16.101.18", 61222);
			String msg = "MESSAGE\n" + 
				"destination: /TestTopic/TestTopic\n" + 
				"message-id: xuechong1234556899\n" + 
				"\n" + 
				"hello queue a\n" + 
				"\n" + 
				"\u000000\n";
			
			soc.getOutputStream().write(msg.getBytes());
			soc.getOutputStream().flush();
			soc.close();
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
