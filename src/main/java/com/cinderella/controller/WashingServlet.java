package com.cinderella.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinderella.dao.DBConnection;
import com.cinderella.dto.WashingInfo;
import com.cinderella.dto.WashingInfo.WashingInfoBuilder;
import com.cinderella.entity.User;
import com.cinderella.service.timing.WashingService;

/**
 * Servlet implementation class WashingController
 */
@Component
@WebServlet("/washing")
public class WashingServlet extends AbstractAutowiredHttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private WashingService washingService;
    
    @Autowired
    private DBConnection connection;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public WashingServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 System.out.println("Washing called !!!");
        /*String userId = request.getParameter("userId");
        String machineId = request.getParameter("machineId");
 System.out.println(userId);
 System.out.println(machineId);
 System.out.println(request.getParameter("washingDuration"));
        long washingDuration = Long.parseLong(request.getParameter("washingDuration"));*/
 
        JSONObject input = RpcHelper.readJSONObject(request);
        String userId = input.getInt("userId") + "";
        String machineId = input.getInt("machineId") + "";
        long washingDuration = input.getInt("washingDuration");
        
        User user = connection.findUserByUserId(Integer.parseInt(userId));
        
        WashingInfoBuilder builder = new WashingInfoBuilder();
        builder.withUserId(userId)
               .withUsername(user.getName())
               .withMachineId(machineId)
               .withWashingDuration(washingDuration);
        WashingInfo washingInfo = builder.build();
        
        washingService.process(washingInfo);
    }

    // TODO remove this method in master
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }
}
