package com.quxin.freshfun.service.specialmall;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.specialmall.SpecialMallGoodsPOJO;

public interface SpecialMallGoodsService {
	public void removeGoods(Map<String,Object> map);
	public void addGoods(SpecialMallGoodsPOJO smg);
	
	public List<SpecialMallGoodsPOJO> findMallGoods(Map<String,Object> map);
}
