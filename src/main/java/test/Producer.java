package test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Producer {
    private static final String USERNAME = "cinderella";
    private static final String PASSWORD = "cinderella";
    private static final String ADDRESS = "tcp://localhost";
    private static final String PORT = "61616";

    public void produce() {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                USERNAME,
                PASSWORD,
                ADDRESS + ":" + PORT);
        try {
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("first");
			MessageProducer producer = session.createProducer(destination);
			//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			for (int i = 0; i < 10; i++) {
			    MapMessage msg = session.createMapMessage();
			    msg.setStringProperty("userId", "User_" + i);
			    msg.setStringProperty("machineId", "Machine_" + i);
			    msg.setLongProperty("time", 40 * 60 * 1000);
			    msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 20000);
			    producer.send(msg);
			    Thread.sleep(1000);
			}

			if (connection != null) {
			    connection.close();
			}
		} catch (JMSException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		new Producer().produce();
	}
}
