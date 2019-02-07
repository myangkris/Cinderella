package com.cinderella.controller.timing;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinderella.dto.WashingInfo;
import com.cinderella.dto.WashingInfo.WashingInfoBuilder;
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
        String userId = request.getParameter("userId");
        String machineId = request.getParameter("machineId");
        long washingDuration = Long.parseLong(request.getParameter("washingDuration"));
        
        WashingInfoBuilder builder = new WashingInfoBuilder();
        builder.withUserId(userId)
               .withMachineId(machineId)
               .withWashingDuration(washingDuration);
        WashingInfo washingInfo = builder.build();
        
        System.out.println(washingService);
        washingService.process(washingInfo);
    }

    // TODO remove this method in master
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(req, resp);
    }
}
