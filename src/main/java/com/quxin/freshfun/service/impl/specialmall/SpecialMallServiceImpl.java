package com.quxin.freshfun.service.impl.specialmall;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.SpecialMallMapper;
import com.quxin.freshfun.model.specialmall.SpecialMallPOJO;
import com.quxin.freshfun.service.specialmall.SpecialMallService;
@Service
public class SpecialMallServiceImpl implements SpecialMallService {
	@Autowired
	private SpecialMallMapper specialMallMapper;
	
	@Override
	public void modifySpecialMall(SpecialMallPOJO sm) {
		// TODO Auto-generated method stub
		specialMallMapper.updateByPrimaryKeySelective(sm);
	}

	@Override
	public void addSpecialMall(SpecialMallPOJO sm) {
		// TODO Auto-generated method stub
		specialMallMapper.insertSelective(sm);
	}

	@Override
	public List<SpecialMallPOJO> findAll(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return specialMallMapper.findAll(map);
	}

	@Override
	public SpecialMallPOJO findById(Integer id) {
		// TODO Auto-generated method stub
		return specialMallMapper.selectByPrimaryKey(id);
	}

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		int result = 0;
		if(specialMallMapper.findCount()!=null)
			result = specialMallMapper.findCount().size();
		return result;
	}

	@Override
	public List<SpecialMallPOJO> findAllMall() {
		// TODO Auto-generated method stub
		return specialMallMapper.findCount();
	}
	
	

}
