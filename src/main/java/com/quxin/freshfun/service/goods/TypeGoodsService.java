package com.quxin.freshfun.service.goods;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.goods.TypeGoodsPOJO;

public interface TypeGoodsService {
	
	void delete(Map<String,Object> map);
	
	void insertSelective(TypeGoodsPOJO tg);
	
	List<TypeGoodsPOJO> findTpyeGoods(Map<String,Object> map);
}
