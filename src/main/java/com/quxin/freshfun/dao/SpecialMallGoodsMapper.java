package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quxin.freshfun.model.specialmall.SpecialMallGoodsPOJO;

public interface SpecialMallGoodsMapper {

	void delete(Map<String,Object> map);
	
	void insertSelective(SpecialMallGoodsPOJO smg);
	
	List<SpecialMallGoodsPOJO> selectByMap(Map<String,Object> map);
	
	List<SpecialMallGoodsPOJO> selectByGoodsId(@Param("goodsId")Integer goodsId);
	
}
