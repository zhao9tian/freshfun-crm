package com.quxin.freshfun.controller.login;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quxin.freshfun.model.login.UserLoginPOJO;
import com.quxin.freshfun.service.userlogin.UserLoginService;

@Controller("/userLogin")
public class UserLogin {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@RequestMapping("/login")
	@ResponseBody
	public Integer login(UserLoginPOJO userLogin){
		Integer id = userLoginService.login(userLogin);
		return id;
	}
	
	@RequestMapping("/register")
	public Integer register(UserLoginPOJO userLogin){
		userLogin.setPassword(DigestUtils.md5Hex(userLogin.getPassword()));
		Integer id = userLoginService.addUserLogin(userLogin);
		return id;
	}
	
}
