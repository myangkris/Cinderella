package com.cinderella.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class RpcHelper {
	// Writes a JSONArray to http response.
	public static void writeJsonArray(HttpServletResponse response, JSONArray array) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin","*");
		PrintWriter out = response.getWriter();
		out.print(array);
		out.close();
	}

    // Writes a JSONObject to http response.
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException {		
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();	
		out.print(obj);
		out.close();
	}
	
	// Parses a JSONObject from http request.
	// the parameter comes from the body of http request which is also a JSON format
	public static JSONObject readJSONObject(HttpServletRequest request) {
		StringBuilder sBuilder = new StringBuilder();
	   	try (BufferedReader reader = request.getReader()) {
	   		String line = null;
	   		while((line = reader.readLine()) != null) {
	   			sBuilder.append(line);
	   		}
	   		return new JSONObject(sBuilder.toString());
	   		 
	   	} catch (Exception e) {
	   		e.printStackTrace();
	   	}
	   	 
	   	return new JSONObject();
	}

}























