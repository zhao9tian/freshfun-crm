package com.quxin.freshfun.dao;

import org.apache.ibatis.annotations.Param;

import com.quxin.freshfun.model.goods.GoodsRelationPOJO;
/**
 * @author 付士豪
 */
public interface GoodsReationMapper {
	/**
	 * 新增
	 * @param relation
	 */
	void insertSelective(GoodsRelationPOJO relation);
	void delGoodsRelation(@Param("goodsId")Integer goodsId);
	GoodsRelationPOJO selectGoodsRelationByGoodsId(@Param("goodsId")Integer goodsId);
}
