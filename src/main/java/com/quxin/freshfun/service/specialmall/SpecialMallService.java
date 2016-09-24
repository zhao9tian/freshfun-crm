package com.quxin.freshfun.service.specialmall;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.specialmall.SpecialMallPOJO;

public interface SpecialMallService {
	
	public void modifySpecialMall(SpecialMallPOJO sm);
	
	public void addSpecialMall(SpecialMallPOJO sm);
	
	public List<SpecialMallPOJO> findAll(Map<String,Object> map);
	
	public SpecialMallPOJO findById(Integer id);
	
	public int findCount();
	
	public List<SpecialMallPOJO> findAllMall();
}
