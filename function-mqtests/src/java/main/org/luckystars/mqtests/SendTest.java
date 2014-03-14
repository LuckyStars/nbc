package org.luckystars.mqtests;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SendTest {

	static final String url = "tcp://172.16.101.24:61616";
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory1 = new ActiveMQConnectionFactory(url);
		Connection connection1 = factory1.createConnection();
		Session session1 = connection1.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Topic topic1 = session1.createTopic("TestTopic");
		MessageConsumer consumer = session1.createConsumer(topic1);
		// Listen for arriving messages
		MessageListener listener = new MessageListener() {
			public void onMessage(Message msg) {
				/* do something */
				TextMessage txt = (TextMessage)msg;
				try {
					System.out.println(txt.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
				System.out.println(msg.toString());
			}
		};
		consumer.setMessageListener(listener);
		connection1.start();

		// Create the reusable JMS objects
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					try {
						do1();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.currentThread().sleep(1000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		
		
	}
	static void do1() throws Exception{
		ConnectionFactory factory = new ActiveMQConnectionFactory(url);
		Connection connection = factory.createConnection();
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);

		Topic topic = session.createTopic("TestTopic");
		MessageProducer producer = session.createProducer(topic);

		// Create and send the message
		TextMessage msg = session.createTextMessage();
		msg.setText("Hello JMS World");
		producer.send(msg);
	}
}
