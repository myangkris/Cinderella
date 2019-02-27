package com.cinderella.service.timing;

import com.cinderella.dto.WashingInfo;

public interface WashingService {
    /**
     * Processes the washing info, including producing the time 
     * @param washingInfo
     */
    void process(WashingInfo washingInfo);
}
