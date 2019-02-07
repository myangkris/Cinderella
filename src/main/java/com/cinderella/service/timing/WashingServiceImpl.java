package com.cinderella.service.timing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.timing.WashingMessageProducer;
import com.cinderella.dto.WashingInfo;

@Service
public class WashingServiceImpl implements WashingService {

    @Autowired
    private WashingMessageProducer washingMessageProducer;
    
    @Override
    public void process(WashingInfo washingInfo) {
        washingMessageProducer.produce(washingInfo);
    }

}
