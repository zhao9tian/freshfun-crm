package com.quxin.freshfun.service.impl.goods;

import com.quxin.freshfun.dao.GoodsDetailMapper;
import com.quxin.freshfun.model.goods.GoodsDetail;
import com.quxin.freshfun.service.goods.GoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by TuZl on 2016/9/22.
 */
@Service("goodsDetailService")
public class GoodsDetailServiceImpl implements GoodsDetailService {

    @Autowired
    GoodsDetailMapper goodsDetailMapper ;
    @Override
    public Integer addGoodsDetail(GoodsDetail goodsDetailPOJO) {
        return goodsDetailMapper.insertGoodsDetail(goodsDetailPOJO);
    }

    @Override
    public void modifyGoodsDetail(GoodsDetail goodsDetailPOJO) {
       goodsDetailMapper.updateGoodsDetail(goodsDetailPOJO);
    }

    @Override
    public GoodsDetail queryGoodsDetailByGoodsId(Integer goodsId) {
        return goodsDetailMapper.selectGoodsDetailByGoodsId(goodsId);
    }
}
