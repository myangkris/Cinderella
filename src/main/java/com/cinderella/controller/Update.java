package com.cinderella.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.service.account.UserAccount;

/**
 * Servlet implementation class Update
 */
@WebServlet("/updateProfile")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject input = RpcHelper.readJSONObject(request);
			String userName = input.getString("userName");
			String password = input.getString("password");
			String emailAddress = input.getString("email");
			String phoneNumber = input.getString("phoneNumber");
			UserBuilder builder = new UserBuilder();

			builder.setPassword(password);
			if (phoneNumber != null) {
				builder.setPhoneNumber(phoneNumber);
			}
			
			builder.setEmail(emailAddress);
			User user = builder.build();
		
			JSONObject obj = new JSONObject();
			UserAccount account = new UserAccount(userName);
			if (account.updateProfile(user)) {
				obj.put("status", "OK");
			} else {
				response.setStatus(401);
				obj.put("status", "Update fail");
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
