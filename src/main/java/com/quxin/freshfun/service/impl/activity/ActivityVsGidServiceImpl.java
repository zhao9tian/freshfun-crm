package com.quxin.freshfun.service.impl.activity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.ActivityVsGidMapper;
import com.quxin.freshfun.model.activity.ActivityVsGidPOJO;
import com.quxin.freshfun.service.activity.ActivityVsGidService;
@Service
public class ActivityVsGidServiceImpl implements ActivityVsGidService {
	@Autowired
	private ActivityVsGidMapper mapper;

	@Override
	public void delete(Map<String,Object> map) {

		mapper.delete(map);
	}

	@Override
	public void insertSelective(ActivityVsGidPOJO avg) {
		mapper.insertSelective(avg);

	}

	@Override
	public List<ActivityVsGidPOJO> findActicityGoods(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return mapper.findActicityGoods(map);
	}

}
