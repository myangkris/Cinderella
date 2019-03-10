package com.cinderella.dao.timing;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Autowired;

import com.cinderella.dto.WashingInfo.WashingInfoBuilder;
import com.cinderella.entity.User;
import com.cinderella.entity.Machine;
import com.cinderella.service.machine.WashMachineService;
import com.cinderella.service.remiding.WashCompleteRemindingService;

public class WashingCompleteListener implements MessageListener {
    @Autowired
    private WashCompleteRemindingService washCompleteRemindingService;
    
    @Autowired
    private WashMachineService washMachineService;
    
	@Override
	public void onMessage(Message message) {
		try {
			MapMessage msg = (MapMessage)message;
			String userId = msg.getStringProperty("userId");
			String machineId = msg.getStringProperty("machineId");
			Long time = msg.getLongProperty("time");
			System.out.println(String.format("%s - %s - %s - %s", userId, machineId, time, this));
			washCompleteRemindingService.pushReminder(userId);
			
			// Update status
			WashingInfoBuilder builder = new WashingInfoBuilder();
			builder.withUserId(String.valueOf(User.DUMMY_USER_ID))
			       .withMachineId(machineId)
			       .withWashingDuration(time);
			
			washMachineService.updateWashMachineStatus(builder.build(), Machine.STATUS_AVAILABLE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
