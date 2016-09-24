package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.specialtheme.SpecialThemePOJO;

/**
 * 主题Dao层接口
 * @author TuZl
 *
 * @time 2016年8月26日上午10:52:41
 */
public interface SpecialThemeMapper {
	
	/**
	 * 查询全部专题信息
	 * @return
	 */
	List<SpecialThemePOJO> findAll(Map<String,Object> map);
	/**
	 * 插入专题信息
	 * @param specialTheme
	 * @return
	 */
	Integer insert(SpecialThemePOJO specialTheme);
	/**
	 * 更新专题信息
	 * @param specialTheme
	 */
	void update(SpecialThemePOJO specialTheme);
	/**
	 * 删除专题信息
	 * @param id
	 */
	void removeSpecialTheme(Integer id);
	/**
	 * 根据id查询专题信息
	 * @param id
	 * @return
	 */
	SpecialThemePOJO findById(Integer id);
	/**
	 * 根据条件查询专题信息
	 * @param map
	 * @return
	 */
	List<SpecialThemePOJO> find(Map<String, Object> map);
	
}
