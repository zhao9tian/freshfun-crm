package com.quxin.freshfun.service.goods;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.goods.GoodsDetail;
import com.quxin.freshfun.model.goods.GoodsPOJO;

/**
 * 商品service
 * @author TuZl
 * @time 2016年8月22日下午1:28:50
 */
public interface GoodsService {
	
	/**
	 * 根据商品id获取商品主要信息
	 * @param goodsId
	 * @return	返回商品主要信息MySQL
	 */
	GoodsPOJO getGoodsById(Integer goodsId);
	
	/**
	 * 根据商品id获取商品详细信息
	 * @param goodsId
	 * @return	返回商品详细信息MongoDB
	 */
	GoodsDetail getGoodsByGoodsId(Integer goodsId);
	
	/**
	 * 返回商品MySQL列表信息
	 * @param map
	 * @return
	 */
	List<GoodsPOJO> findMysqlList(Map<String, Object> map);
	
	
	Integer count(Map<String, Object> map);

	/**
	 * 商品上架
	 * @param goods 商品对象
	 * @param gm 商品mongoDB 
	 * @param typeIds 商品类型id
	 * @return  新增的商品id
	 */
	Integer addGoods(GoodsPOJO goods , GoodsDetail gm, List<Integer> typeIds);
	
	/**
	 * 修改商品信息
	 * @param goods
	 * @param gm
	 * @param typeIds
	 */
	void updateGoods(GoodsPOJO goods , GoodsDetail gm, List<Integer> typeIds);
	
	/**
	 * 下架商品
	 * @param goodIds 货物id
	 */
	void removeGoods(List<Integer> goodIds);
	
	/**
	 * 单个商品下架
	 * @param id
	 */
	void updateGoodsDown(Integer id);
	
	/**
	 * 获取商品类型
	 * @param goodsId
	 * @return
	 */
	List<Integer> getTypeGoodsIds(Integer goodsId);
	
	/**
	 * 名称不能相同
	 * @param goodsName
	 * @return
	 */
	Integer validateGoodsName(String goodsName , String id);
	
	/**
	 * 根据专题id查询专题商品信息
	 * @param map
	 * @return
	 */
	List<GoodsPOJO> findSpecialGoods(Map<String,Object> map);
	
	/**
	 * 根据专题id查询专题商品信息
	 * @param map
	 * @return
	 */
	List<GoodsPOJO> findSpecialMallGoods(Map<String,Object> map);
	/**
	 * 根据分类id查询分类商品信息
	 * @param map
	 * @return
	 */
	List<GoodsPOJO> findTypeGoods(Map<String,Object> map);
	/**
	 * 根据活动id查询活动商品信息
	 * @param map
	 * @return
	 */
	List<GoodsPOJO> findActivityGoods(Map<String,Object> map);
	/**
	 * 根据代理商户ID查询代理的商品信息
	 * @param map
	 * @return
	 */
	List<GoodsPOJO> findProxyGoods (Map<String,Object> map);
	/**
	 * 查询上月销量
	 * goodsId
	 * @return
	 */
	Integer getLastSales(Integer goodsId);
	/**
	 * 根据订单id查询总销量
	 * @param goodsId
	 * @return
	 */
	Integer getAllSales(Integer goodsId);

	
	/**
	 * 单个商品上架
	 * @param id
	 */
	void updateGoodsUp(Integer id);
	
	/**
	 * 新增 精选图片
	 * @param map
	 * @return
	 */
	Integer addGoodsSelection(Map<String,Object> map);
}
