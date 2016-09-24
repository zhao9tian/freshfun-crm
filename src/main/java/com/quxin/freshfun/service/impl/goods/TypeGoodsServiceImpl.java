package com.quxin.freshfun.service.impl.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.TypeGoodsMapper;
import com.quxin.freshfun.model.goods.TypeGoodsPOJO;
import com.quxin.freshfun.service.goods.TypeGoodsService;
@Service
public class TypeGoodsServiceImpl implements TypeGoodsService {

	@Autowired
	private TypeGoodsMapper typeGoodsMapper;
	
	@Override
	public void delete(Map<String,Object> map) {
		// TODO Auto-generated method stub
		typeGoodsMapper.delete(map);
	}

	@Override
	public void insertSelective(TypeGoodsPOJO tg) {
		// TODO Auto-generated method stub
		typeGoodsMapper.insertSelective(tg);
	}

	@Override
	public List<TypeGoodsPOJO> findTpyeGoods(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return typeGoodsMapper.findTypeGoods(map);
	}

}
