package com.fise.iot.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fise.iot.common.pojo.ParamData;
import com.fise.iot.model.AuthUser;

/**
 * 用户工具
 * 
 * @author czh
 * @2016年11月11日
 */
public class UserUtil {
	
	public static String getCurrentUserName() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		// 读取session中的用户
		AuthUser user = (AuthUser) request.getAttribute("loginUser");
		String username = "匿名操作";
		ParamData params = new ParamData();
		if (null != user) {
			username = user.getUsername();
		}else{
			if(params.containsKey("username")){
				username = params.getString("username");
			}
		}
		return username;
	}
}
