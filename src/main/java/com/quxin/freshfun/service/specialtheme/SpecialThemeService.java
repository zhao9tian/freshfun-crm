package com.quxin.freshfun.service.specialtheme;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.specialtheme.SpecialThemePOJO;

public interface SpecialThemeService {
	/**
	 * 查询全部专题信息
	 * @return
	 */
	List<SpecialThemePOJO> findAll(Map<String,Object> map);
	/**
	 * 保存专题信息
	 * @param specialTheme
	 * @return
	 */
	Integer save(SpecialThemePOJO specialTheme);
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
	SpecialThemePOJO find(Integer id);
	/**
	 * 根据条件查询专题信息
	 * @param map
	 * @return
	 */
	List<SpecialThemePOJO> find(Map<String, Object> map);
	
}
