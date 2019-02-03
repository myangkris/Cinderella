package com.cinderella.service.timing;

import org.apache.activemq.ScheduledMessage;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import java.util.Random;

import javax.annotation.Resource;
import javax.jms.*;


public class Producer {
    @Resource
    private JmsTemplate jmsTemplate;

    public void produce() {
        Destination destination = jmsTemplate.getDefaultDestination();
		for (int i = 0; i < 3; i++) {
		    jmsTemplate.send(destination, new MessageCreator() {
		        public Message createMessage(Session session) throws JMSException {
		          MapMessage msg = session.createMapMessage();
		          int num = new Random().nextInt(1000);
		          msg.setStringProperty("machineId", "Machine_ID-" + num);
		          msg.setLongProperty("time", System.currentTimeMillis());
		          msg.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 20000L);
		          msg.setStringProperty("userId", "User_" + num);
		          
		          System.out.println(String.format("%s - %s - %s", 
		                  msg.getStringProperty("userId"), 
		                  msg.getStringProperty("machineId"), 
		                  msg.getLongProperty("time")));
		          return msg;
		        }
		    });
		    
		    try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
		}
    }
    
    // For test. TODO: remove this method in production
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"beans.xml"});  
        BeanFactory factory = (BeanFactory) context;  
        Producer producer = (Producer) factory.getBean("producer"); 
        producer.produce();
        System.out.println("produced");
	}
}
