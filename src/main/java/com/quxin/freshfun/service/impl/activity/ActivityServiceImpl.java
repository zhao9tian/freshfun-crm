package com.quxin.freshfun.service.impl.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.ActivityMapper;
import com.quxin.freshfun.model.activity.ActivityPOJO;
import com.quxin.freshfun.service.activity.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {
	@Autowired
	private ActivityMapper mapper;
	
	@Override
	public int addActivity(ActivityPOJO activitypojo) {
		return mapper.addActivity(activitypojo);
	}
	
    /**
     * 删除 
     */

	@Override
	public void deleteActivity(ActivityPOJO activitypojo) {
		mapper.updateActivity(activitypojo);
	}
	
    /**
     * 修改
     */

	@Override
	public void updateActivity(ActivityPOJO activitypojo) {
		mapper.updateActivity(activitypojo);
	}

	
    /**
     * 根据ID查找
     */
	@Override
	public ActivityPOJO findById(int id) {
		ActivityPOJO  acticity =  mapper.findById(id);
		return acticity;
	}
	
    /**
     *  查找所有
     */

	@Override
	public List<ActivityPOJO> findAll() {
		List<ActivityPOJO> list = mapper.findAll();
		return list;
	}

}
