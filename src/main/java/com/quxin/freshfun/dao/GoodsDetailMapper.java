package com.quxin.freshfun.dao;

import com.quxin.freshfun.model.goods.GoodsDetail;

/**
 * Created by TuZl on 2016/9/22.
 */
public interface GoodsDetailMapper {
    /**
     * 插入数据库
     * @param goodsDetail
     * @return
     */
    Integer insertGoodsDetail(GoodsDetail goodsDetail);

    /**
     * 编辑商品详情信息
     * @param goodsDetail
     */
    void updateGoodsDetail(GoodsDetail goodsDetail);

    /**
     * 根据商品Id查询商品详情信息
     * @param goodsId
     * @return
     */
    GoodsDetail selectGoodsDetailByGoodsId(Integer goodsId);
}
