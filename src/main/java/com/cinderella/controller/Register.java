package com.cinderella.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.cinderella.entity.User;
import com.cinderella.entity.User.UserBuilder;
import com.cinderella.service.account.Account;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject input = RpcHelper.readJSONObject(request);
			
			String username = input.getString("username");
			String password = input.getString("password");
			String email = input.getString("email");
			String phoneNumber = input.getString("phoneNumber");
			UserBuilder builder = new UserBuilder();
			//Date date = new Date();
			builder.setUsername(username);
			builder.setPassword(password);
			builder.setBalance(100);
			builder.setPhoneNumber(phoneNumber);
			builder.setBonusPoints(0);
			builder.setEmail(email);
			/*builder.setUserId(date.getSeconds()*60*24*31*12*300 + 
					date.getMinutes()*24*31*12*300 + 
					date.getHours()*31*12*300 + 
					date.getDate()*12*300 + 
					date.getMonth()*300 + 
					date.getYear());*/
			User user = builder.build();
			

			
			JSONObject obj = new JSONObject();
			if (Account.register(user)) {
//				HttpSession session = request.getSession(); // this session ID will attach to response
//				session.setAttribute("userName", userName);
//				session.setMaxInactiveInterval(600);
				obj.put("status", "OK");
			} else {
				response.setStatus(401);
				obj.put("status", "User Name has been registered");
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
