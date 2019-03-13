package com.cinderella.service.timing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cinderella.dao.timing.WashingMessageProducer;
import com.cinderella.dto.WashingInfo;
import com.cinderella.entity.Machine;
import com.cinderella.service.machine.WashMachineService;
import com.cinderella.service.user.UserService;

@Service
public class WashingServiceImpl implements WashingService {

    @Autowired
    private WashingMessageProducer washingMessageProducer;
    
    @Autowired
    private WashMachineService washMachineService;
    
    @Autowired
    private UserService userService;
    
    
    @Override
    @Transactional
    public void process(WashingInfo washingInfo) {
        washingMessageProducer.produce(washingInfo);
        System.out.println("Update machine");
        washMachineService.updateWashMachineStatus(washingInfo, Machine.STATUS_WASHING);
        userService.updateBalanceById(Integer.parseInt(washingInfo.getUserId()), -2.00);
    }
}
