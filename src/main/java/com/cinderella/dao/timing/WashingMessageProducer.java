package com.cinderella.dao.timing;

import com.cinderella.dto.WashingInfo;

public interface WashingMessageProducer {
    void produce(WashingInfo washingInfo);
}
