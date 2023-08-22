package com.example.basic.util;

import jakarta.servlet.http.HttpServletRequest;

public class MasterMethod {
	public static String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		return authorization.substring(7);
	}
}
