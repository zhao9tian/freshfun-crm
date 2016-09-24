package com.quxin.freshfun.service.activity;

import java.util.List;

import com.quxin.freshfun.model.activity.ActivityPOJO;

public interface ActivityService {
	/**
	 * 增加
	 * @param activitypojo
	 * @return
	 */
	int addActivity(ActivityPOJO activitypojo);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	void deleteActivity(ActivityPOJO activitypojo );
	/**
	 * 修改
	 * @param activitypojo
	 * @return
	 */
	void updateActivity(ActivityPOJO activitypojo);
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	ActivityPOJO findById(int id);
	/**
	 * 查找全部
	 * @return
	 */
	List<ActivityPOJO> findAll();  

}
