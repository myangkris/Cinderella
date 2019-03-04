package com.cinderella.controller;

import java.io.IOException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.cinderella.service.account.Account;
import com.cinderella.service.account.UserAccount;
import com.cinderella.service.machine.WashMachineService;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends AbstractAutowiredHttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
	private WashMachineService washMachineService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String jwt = request.getHeader("Authorization");
		String scope = "";
		try {
//			Jws<Claims> claims = Jwts.parser()
//			  .setSigningKey("this is my custom Secret key for authnetication".getBytes("UTF-8"))
//			  .parseClaimsJws(jwt);
			scope = (String)Jwts.parser().setSigningKey(
				    "this is my custom Secret key for authnetication".getBytes("UTF-8")).parseClaimsJws(jwt).getBody().get("scope");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("token can not read");
			System.out.println(jwt);
			e.printStackTrace();
		}
		
		try {
//			HttpSession session = request.getSession(false);
			JSONObject obj = new JSONObject();
			
			if (scope.equals("this is my custom Secret key for authnetication")) {
				
//				String userId = session.getAttribute("user_id").toString();
				obj.put("status", "OK");
			} else {
				response.setStatus(403);
				obj.put("status", "Invalid Token");
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			JSONObject input = RpcHelper.readJSONObject(request);
			String userName = input.getString("username");
			String password = input.getString("password");
			
			JSONObject obj = new JSONObject();
			if (Account.logIn(userName, password)) {
//				HttpSession session = request.getSession(); // this session ID will attach to response
//				session.setAttribute("userName", userName);
//				session.setMaxInactiveInterval(600);
				@SuppressWarnings("deprecation")
				String jwt = Jwts.builder()
						  .setSubject("users/TzMUocMF4p")
						  .claim("name", userName)
						  .claim("scope", "this is my custom Secret key for authnetication")
						  .signWith(
						    SignatureAlgorithm.HS256,
						    "this is my custom Secret key for authnetication".getBytes("UTF-8")
						  )
						  .compact();
				System.out.println(jwt);
				UserAccount account = new UserAccount(userName);
				JSONArray array = account.checkMachineList();
				JSONObject user = account.getProfile();
				obj.put("status", "OK").put("user_info" , user).put("machine_list" , array).put("Authorization", jwt);
System.out.println("OBJECT: " + obj);
			} else {
				response.setStatus(401);
				obj.put("status", "User Doesn't Exist");
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}



