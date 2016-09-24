package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.quxin.freshfun.model.specialtheme.SpecialThemeGoodsPOJO;

public interface SpecialThemeGoodsMapper {
	void deleteByMap(Map<String,Object> map);
	
	void insert(SpecialThemeGoodsPOJO stg);
	
	List<SpecialThemeGoodsPOJO> selectByMap(Map<String,Object> map);
	
	List<SpecialThemeGoodsPOJO> selectByGoodsId(@Param("goodsId")Integer goodsId);
}
