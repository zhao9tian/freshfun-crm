package com.quxin.freshfun.dao;

import com.quxin.freshfun.model.user.UsersPOJO;

public interface UsersMapper {
	UsersPOJO selectByUserId(Long userId);
}
