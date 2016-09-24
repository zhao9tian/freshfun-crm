package com.quxin.freshfun.service.goods;

import com.quxin.freshfun.model.goods.GoodsDetail;

/**
 * Created by TuZl on 2016/9/22.
 */
public interface GoodsDetailService {

    /**
     * 保存商品详情
     * @param goodsDetailPOJO
     * @return
     */
    Integer addGoodsDetail(GoodsDetail goodsDetailPOJO);

    /**
     * 修改商品详情
     * @param goodsDetailPOJO
     */
    void modifyGoodsDetail(GoodsDetail goodsDetailPOJO);

    /**
     * 根据商品Id查询商品详情信息
     * @param goodsId
     * @return
     */
    GoodsDetail queryGoodsDetailByGoodsId(Integer goodsId);

}
