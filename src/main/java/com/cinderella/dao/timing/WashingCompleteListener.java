package com.cinderella.dao.timing;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class WashingCompleteListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			MapMessage msg = (MapMessage)message;
			String userId = msg.getStringProperty("userId");
			String machineId = msg.getStringProperty("machineId");
			Long time = msg.getLongProperty("time");
			System.out.println(String.format("%s - %s - %s - %s", userId, machineId, time, this));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
