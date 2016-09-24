package com.quxin.freshfun.dao;

import java.util.List;
import java.util.Map;

import com.quxin.freshfun.model.proxy.ProxyInfoPOJO;

public interface ProxyInfoMapper {
	/**
	 * 根据商品ID查询代理信息
	 * @param goodsId
	 * @return
	 */
	ProxyInfoPOJO findByGoodsId(Integer goodsId);
	
    /**
     * 保存代理信息
     * @param proxyInfo
     * @return
     */
	Integer save(ProxyInfoPOJO proxyInfo);
	/**
	 * 查询所有代理信息
	 * @param map
	 * @return
	 */
	List<ProxyInfoPOJO>  findAll(Map<String, Object> map); 
	
	
	
	
}
