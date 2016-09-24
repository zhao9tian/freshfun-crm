package com.quxin.freshfun.service.impl.goods;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.GoodsTypeMapper;
import com.quxin.freshfun.model.goods.GoodsTypePOJO;
import com.quxin.freshfun.service.goods.GoodsTypeService;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	@Autowired
	private GoodsTypeMapper mapper;

	@Override
	public int addGoodsType(GoodsTypePOJO goodsType) {
		return mapper.insertSelective(goodsType);
	}

	@Override
	public int updateGoodsType(GoodsTypePOJO goodsType) {
		return mapper.updateByPrimaryKeySelective(goodsType);
	}

	@Override
	public GoodsTypePOJO findById(int id) {
		GoodsTypePOJO  goodsType = mapper.findById(id); 
		return goodsType;
	}

	@Override
	public List<GoodsTypePOJO> findAll() {
		List<GoodsTypePOJO> findAllList = mapper.findAll();
		return findAllList;
	}


	@Override
	public boolean deleteGoodsType(int id) {
		return mapper.deleteGoodsType(id);
	}

	@Override
	public List<GoodsTypePOJO> findByPage(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return mapper.findByPage(map);
	}

	@Override
	public int removeGoodsType(GoodsTypePOJO goodstype) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(goodstype);
	}

	@Override
	public List<GoodsTypePOJO> findAllType() {
		// TODO Auto-generated method stub
		return mapper.findAll();
	}

}
