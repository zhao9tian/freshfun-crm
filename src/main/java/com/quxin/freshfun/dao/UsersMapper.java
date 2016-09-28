package com.quxin.freshfun.dao;

import com.quxin.freshfun.model.user.UsersPOJO;

import java.util.List;

public interface UsersMapper {

	/**
	 * 根据手机号查询用户id
	 * @param phone
	 * @return
	 */
	Long selectUserIdByPhone(String phone);

	/**
	 * 根据userId查询用户
	 * @param userId
	 * @return
	 */
	List<UsersPOJO> selectUserById(Long userId);
}
