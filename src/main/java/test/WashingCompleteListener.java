package test;

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
			System.out.println(String.format("%s - %s - %s", userId, machineId, time));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
