package com.fise.iot.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 */
public class CookieUtil {
	public static void set(String key, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void longset(String key, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(30 * 24 * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
    
	//获取用户的登录信息SESSION_ID
	public static String get(String key, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(key)) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	public static void delete(String key, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] arr_cookie = request.getCookies();
		if (arr_cookie != null && arr_cookie.length > 0) {
			for (Cookie cookie : arr_cookie) {
				if (cookie.getName().equals(key)) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
	}
}
