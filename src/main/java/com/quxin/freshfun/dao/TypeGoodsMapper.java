package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quxin.freshfun.model.goods.TypeGoodsPOJO;

public interface TypeGoodsMapper {
	
	void delete(Map<String,Object> map);
	
	void insertSelective(TypeGoodsPOJO tg);
	
	List<TypeGoodsPOJO> findTypeGoods(Map<String,Object> map);
	
	List<TypeGoodsPOJO> findTypeGoodsByGoodsId(@Param("goodsId")Integer goodsId);
}
