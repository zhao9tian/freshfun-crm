package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.goodslimit.GoodsLimitPOJO;


/**
 * 限时购dao层
 * @author TuZl
 *
 * @time 2016年8月29日下午4:31:15
 */
public interface GoodsLimitMapper {

	/**
	 * 返回商品列表
	 * @return
	 */
	List<GoodsLimitPOJO> findAll(Map<String, Object> map);
	
	/**
	 * 返回复合查询条件的数据总数
	 * @param map
	 * @return
	 */
	Integer total(Map<String, Object> map);
	
	/**
	 * 保存商品信息
	 * @param goods
	 * @return 返回商品Id
	 */
	Integer save(GoodsLimitPOJO gl);
	
	/**
	 * 修改商品信息
	 * @param goods
	 */
	void update(GoodsLimitPOJO gl);
	
	/**
	 * 获取商品信息
	 * @param goodsId 
	 * @return
	 */
	GoodsLimitPOJO selectGoodsLimitById(Integer goodsId);

	/**
	 * 修改商品状态为下架
	 * @param id
	 */
	void removeGoodsLimit(Integer id);

	/**
	 * 获取商品信息
	 * @param goodsId 
	 * @return
	 */
	GoodsLimitPOJO getGoodsById(Integer goodsId);

	/**
	 * 校验名称不重复
	 * @param map
	 * @return
	 */
	Integer getTotalGoodsName(Map<String, Object> map);

	
}