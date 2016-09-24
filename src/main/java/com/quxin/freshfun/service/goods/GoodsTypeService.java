package com.quxin.freshfun.service.goods;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.goods.GoodsTypePOJO;

public interface GoodsTypeService {
	public int addGoodsType(GoodsTypePOJO goodsType);
	public int updateGoodsType(GoodsTypePOJO goodsType);
    public GoodsTypePOJO  findById(int id);    
	public List<GoodsTypePOJO> findAll();
	public boolean deleteGoodsType(int id);
	public List<GoodsTypePOJO> findByPage(Map<String,Object> map);
	/**
	 * 逻辑删除
	 * @param goodstype
	 * @return
	 */
	public int removeGoodsType(GoodsTypePOJO goodstype);
	
	public List<GoodsTypePOJO> findAllType();
}


