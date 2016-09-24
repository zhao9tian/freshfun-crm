package com.quxin.freshfun.service.impl.specialtheme;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.SpecialThemeGoodsMapper;
import com.quxin.freshfun.model.specialtheme.SpecialThemeGoodsPOJO;
import com.quxin.freshfun.service.specialtheme.SpecialThemeGoodsService;

@Service
public class SpecialThemeGoodsServiceImpl implements SpecialThemeGoodsService {

	@Autowired
	private SpecialThemeGoodsMapper stgMapper;
	
	@Override
	public void addSpecialGoods(SpecialThemeGoodsPOJO stg) {
		// TODO Auto-generated method stub
		stgMapper.insert(stg);
	}

	@Override
	public void removeSpecialGoods(Map<String,Object> map) {
		// TODO Auto-generated method stub
		stgMapper.deleteByMap(map);
	}

	@Override
	public List<SpecialThemeGoodsPOJO> findSpecialGoods(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return stgMapper.selectByMap(map);
	}

}
