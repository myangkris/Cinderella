package com.cinderella.controller;

public class StringToInt {
	public static int toNumber(String input) {
		int sum = 0;
		for(int i = 0 ; i < input.length(); i ++) {
			sum = sum * 10 + input.charAt(i) - '0';
		}
		return sum;
	}
}
