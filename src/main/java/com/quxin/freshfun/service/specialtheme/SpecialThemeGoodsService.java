package com.quxin.freshfun.service.specialtheme;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.specialtheme.SpecialThemeGoodsPOJO;

public interface SpecialThemeGoodsService {
	/**
	 * 添加
	 * @param stg
	 */
	public void addSpecialGoods(SpecialThemeGoodsPOJO stg);
	/**
	 * 删除
	 * @param map
	 */
	public void removeSpecialGoods(Map<String,Object> map);
	
	public List<SpecialThemeGoodsPOJO> findSpecialGoods(Map<String,Object> map);
}
