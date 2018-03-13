package com.fise.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fise.iot.common.Constant;
import com.fise.iot.common.annotation.ControllerLog;
import com.fise.iot.model.AuthUser;
import com.fise.iot.service.UserService;

@Controller
@RequestMapping("/register/")
public class RegisterController extends BaseController {

	@Autowired
	private UserService userService;


	@ControllerLog("查询用户名是否存在")
	@RequestMapping("checkUserName")
	@ResponseBody
	public boolean queryPage(AuthUser user) {
		int count = userService.queryCount(user);
		if(count > 0)return false;
		return true;
	}


	@ControllerLog("注册用户")
	@RequestMapping("register")
	public String add(AuthUser user) {
		user.setUseable(Constant.USER_ABLE);
		user.setRoleid(Constant.USER_ROLE);
		userService.saveUser(user);
		return "common/success";
	}


}
