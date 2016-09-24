package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.activity.ActivityVsGidPOJO;

public interface ActivityVsGidMapper {
    void delete(Map<String,Object> map);
	
	void insertSelective(ActivityVsGidPOJO avg);
	
	List<ActivityVsGidPOJO> findActicityGoods(Map<String,Object> map);

}
