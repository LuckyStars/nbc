package org.luckystars.weixin.transfer.pushmsg.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

public class Test {
	
	static final String brokerUrl = "vm://localhost";
	
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		Function startCallBack1 = new Function() {
			@Override
			void execute() {
				
				setUpConsumer();
			}
		};
		
		
		Thread serverThread = new Thread(new StartServer(startCallBack1));
		serverThread.start();
	}
	
	
	
	
	private static void setUpConsumer(){
		ActiveMQConnectionFactory fac = new ActiveMQConnectionFactory(brokerUrl);
		Connection conn = null;
		try {
			conn = fac.createConnection("user1","pwd1");
			conn.start();
			Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue("testqueue");
			
			MessageConsumer consumer = session.createConsumer(destination);
			

			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					System.out.print("\n\nonmessage:");
					try {
						System.out.println(message.getJMSType());
						System.out.println(message.toString() + "\n");
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
			
			MessageProducer producer = session.createProducer(null);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			for (int i = 0; i < 10; i++) {
				TextMessage msg = session.createTextMessage();
				msg.setText("NO:" + i);
				msg.setJMSCorrelationID(UUID.randomUUID().toString());
				
				producer.send(destination,msg);
			}
			
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public static abstract class Function{
		abstract void execute();
	}
	
	
	
	public static class StartServer implements Runnable{
		private Function callBack;
		StartServer(Function startCallBack){
			this.callBack = startCallBack;
		}
		@Override
		public void run() {
			BrokerService broker = new BrokerService();
			broker.setBrokerName("testBroker");
			broker.setDataDirectory("mqdata/");
		
			List<AuthenticationUser> users = new ArrayList<AuthenticationUser>(){{
				add(new AuthenticationUser("user1", "pwd1", "consumers"));
				add(new AuthenticationUser("user2", "pwd2", "consumers"));
				add(new AuthenticationUser("user3", "pwd3", "consumers"));
			}};

			SimpleAuthenticationPlugin auth = new SimpleAuthenticationPlugin(users);
			
			broker.setPlugins(new BrokerPlugin[]{auth});
			
			
			try {
				broker.addConnector(brokerUrl);
				broker.addConnector("stomp://localhost:61616");
				broker.start();

				this.callBack.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
