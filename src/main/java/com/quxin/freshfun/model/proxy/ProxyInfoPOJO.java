package com.quxin.freshfun.model.proxy;

public class ProxyInfoPOJO {
    private Integer id;

    private Long gmtCreate;

    private Long gmtModified;

    private Integer goodsId;

    private Integer merchantProxyId;

    private Integer merchantProxyMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getMerchantProxyId() {
        return merchantProxyId;
    }

    public void setMerchantProxyId(Integer merchantProxyId) {
        this.merchantProxyId = merchantProxyId;
    }

    public Integer getMerchantProxyMoney() {
        return merchantProxyMoney;
    }

    public void setMerchantProxyMoney(Integer merchantProxyMoney) {
        this.merchantProxyMoney = merchantProxyMoney;
    }
}