package com.quxin.freshfun.model.goods;

public class GoodsRelationPOJO {
    private Integer id;

    private Integer goodsId;

    private String bannerIds;

    private String themeIds;

    private String typeIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getBannerIds() {
        return bannerIds;
    }

    public void setBannerIds(String bannerIds) {
        this.bannerIds = bannerIds == null ? null : bannerIds.trim();
    }

    public String getThemeIds() {
        return themeIds;
    }

    public void setThemeIds(String themeIds) {
        this.themeIds = themeIds == null ? null : themeIds.trim();
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds == null ? null : typeIds.trim();
    }
}