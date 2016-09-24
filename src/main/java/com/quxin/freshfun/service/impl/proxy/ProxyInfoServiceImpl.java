package com.quxin.freshfun.service.impl.proxy;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.ProxyInfoMapper;
import com.quxin.freshfun.model.proxy.ProxyInfoPOJO;
import com.quxin.freshfun.service.proxy.ProxyInfoService;

@Service
public class ProxyInfoServiceImpl implements ProxyInfoService {
	
	@Autowired
	private ProxyInfoMapper mapper;


	@Override
	public ProxyInfoPOJO findByGoodsId(Integer goodsId) {
		ProxyInfoPOJO proxyInfo = mapper.findByGoodsId(goodsId);
		return proxyInfo;
	}

	@Override
	public Integer save(ProxyInfoPOJO proxyInfo) {
		return mapper.save(proxyInfo);
	}

	@Override
	public List<ProxyInfoPOJO> findAll(Map<String, Object> map) {
		List<ProxyInfoPOJO> lsit = mapper.findAll(map);
		return lsit;
	}

}
