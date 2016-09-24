package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.specialmall.SpecialMallPOJO;

public interface SpecialMallMapper {
	/**
	 * 更新
	 * @param sm
	 */
	void updateByPrimaryKeySelective(SpecialMallPOJO sm);
	/**
	 * 新增
	 * @param sm
	 */
	void insertSelective(SpecialMallPOJO sm);
	/**
	 * 查询全部
	 * @return
	 */
	List<SpecialMallPOJO> findAll(Map<String,Object> map);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	SpecialMallPOJO selectByPrimaryKey(Integer id);
	/**
	 * 查询总数据
	 * @return
	 */
	List<SpecialMallPOJO> findCount();
}
