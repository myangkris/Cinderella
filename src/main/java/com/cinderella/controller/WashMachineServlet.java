package com.cinderella.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinderella.entity.WashMachine;
import com.cinderella.service.machine.WashMachineService;
import com.cinderella.utils.WebPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class WashMachineServlet
 */
@Component
@WebServlet("/allWashMachines")
public class WashMachineServlet extends AbstractAutowiredHttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private WashMachineService washMachineService;
	
	@Autowired
	private ObjectMapper objectMapper;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WashMachineServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("Servlet called");
	    response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Credential", "true");
	    List<WashMachine> machines = washMachineService.listAllMachines();
		JSONArray array = new JSONArray();
		for (WashMachine machine : machines) {
            String jsonString = objectMapper.writeValueAsString(machine);
            JSONObject object = new JSONObject(jsonString);
            array.put(object);
        }
		WebPrinter.printJSONArray(response, array);
		//RpcHelper.writeJsonObject(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
