package com.quxin.freshfun.service.impl.userlogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.UserLoginMapper;
import com.quxin.freshfun.model.login.UserLoginPOJO;
import com.quxin.freshfun.service.userlogin.UserLoginService;

@Service
public class UserloginImpl implements UserLoginService{

	@Autowired
	private UserLoginMapper userLoginMapper;
	
	@Override
	public Integer login(UserLoginPOJO userLogin) {
		return userLoginMapper.login(userLogin);
	}

	@Override
	public Integer addUserLogin(UserLoginPOJO userLogin) {
		return null;
	}

}
