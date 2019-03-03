package com.cinderella.service.remiding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinderella.dao.mysql.MySQLConnection;
import com.cinderella.entity.User;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

@Service
public class JiguangWashCompleteRemindingService implements WashCompleteRemindingService {
    /**
     * App configurations on Jiguang push
     * https://www.jiguang.cn/dev/#/app/b8c3ed289440f0ffd0d9eae3/info
     */
    
    private static final String APP_KEY = "b8c3ed289440f0ffd0d9eae3";
    private static final String MASTER_SECRET = "563838d2737edff7fc43a8a9";
    
    @Autowired
    private MySQLConnection connection;
    
    @Override
    public void pushReminder(String userId) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
        
        User user = connection.findUserByUserId(Integer.parseInt(userId));
        
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = PushPayload.alertAll(user.getName() + ", your laundry is done!");

        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            System.out.println("Connection error, should retry later\n" + e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            System.out.println("Should review the error, and fix the request\n" + e);
            System.out.println("HTTP Status: " + e.getStatus());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());
        }
    }

}
