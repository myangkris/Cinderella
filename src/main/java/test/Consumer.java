package test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Scope
@Component
public class Consumer {
    private static final String USERNAME = "cinderella";
    private static final String PASSWORD = "cinderella";
    private static final String ADDRESS = "tcp://localhost";
    private static final String PORT = "61616";
    
    private ConnectionFactory connectionFactory;
    
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageConsumer consumer;
    
    public Consumer() {
    	connectionFactory = new ActiveMQConnectionFactory(
                USERNAME,
                PASSWORD,
                ADDRESS + ":" + PORT);
    	try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("first");
			consumer = session.createConsumer(destination);
			System.out.println("Consumer initialized...");
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
    
    public void receiver() {
		try {
			System.out.println("receive() called...");
			consumer.setMessageListener(new WashingCompleteListener());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

    public static void main (String args[]) throws Exception {
        Consumer consumer = new Consumer();
        consumer.receiver();
    }
}
