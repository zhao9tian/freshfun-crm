package com.quxin.freshfun.dao;

import java.util.List;

import com.quxin.freshfun.model.activity.ActivityPOJO;

public interface ActivityMapper {
	/**
	 * 
	 * @param activitypojo
	 * @return
	 */
	public int addActivity(ActivityPOJO activitypojo);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteActity(int id );
	/**
	 * 
	 * @param id
	 * @return
	 */
    public ActivityPOJO findById(int id);
    /**
     * 
     * @return
     */
    public List<ActivityPOJO> findAll();  
    /**
     * 
     * @param id
     * @return
     */
    public void  updateActivity(ActivityPOJO activitypojo); 
    
}
