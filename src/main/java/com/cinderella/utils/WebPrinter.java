package com.cinderella.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


public class WebPrinter {
	public static void printJSONArray(HttpServletResponse response, JSONArray array) {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");//*可以变成ip，访问控制, 也可以在ec2中设定
		try {
			PrintWriter pw = response.getWriter();
			pw.print(array);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject readJSONObject(HttpServletRequest request) {
		try {
			BufferedReader br = request.getReader();
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new JSONObject();
	}
	
	public static void printJSONObject(HttpServletResponse response, JSONObject object) {
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");//*可以变成ip，访问控制, 也可以在ec2中设定
		try {
			PrintWriter pw = response.getWriter();
			pw.print(object);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
