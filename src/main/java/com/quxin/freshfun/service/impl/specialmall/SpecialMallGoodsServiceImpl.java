package com.quxin.freshfun.service.impl.specialmall;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.SpecialMallGoodsMapper;
import com.quxin.freshfun.model.specialmall.SpecialMallGoodsPOJO;
import com.quxin.freshfun.service.specialmall.SpecialMallGoodsService;
@Service
public class SpecialMallGoodsServiceImpl implements SpecialMallGoodsService {

	@Autowired
	private SpecialMallGoodsMapper smgMapper;
	
	@Override
	public void removeGoods(Map<String,Object> map) {
		// TODO Auto-generated method stub
		smgMapper.delete(map);
	}

	@Override
	public void addGoods(SpecialMallGoodsPOJO smg) {
		// TODO Auto-generated method stub
		smgMapper.insertSelective(smg);
	}

	@Override
	public List<SpecialMallGoodsPOJO> findMallGoods(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return smgMapper.selectByMap(map);
	}

}
