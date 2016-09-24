package com.quxin.freshfun.service.userlogin;

import com.quxin.freshfun.model.login.UserLoginPOJO;

public interface UserLoginService {
	public Integer login(UserLoginPOJO userLogin) ;

	public Integer addUserLogin(UserLoginPOJO userLogin);
}
