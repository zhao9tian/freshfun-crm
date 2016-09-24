package com.quxin.freshfun.model.goodslimit;

import java.text.DecimalFormat;

/**
 * 限时购商品详情--商品详情一致
 * @author TuZl
 * @time 2016年8月29日下午6:06:11
 */
public class GoodsLimitPOJO {
    private Integer id;

    private Long gmtCreate;

    private Long gmtModified;

    private Long startTime;

    private Long endTime;

    private Integer marketPrice;

    private Integer shopPrice;

    private String goodsName;

    private String goodsDes;

    private String goodsImg;

    private Integer goodsStorage;

    private Integer isOnSale;

    private Integer storeId;

    private Integer merchantProxyId;

    private Integer limitCount;
    
    

    public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

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

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Integer shopPrice) {
        this.shopPrice = shopPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDes() {
        return goodsDes;
    }

    public void setGoodsDes(String goodsDes) {
        this.goodsDes = goodsDes == null ? null : goodsDes.trim();
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

    public Integer getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(Integer goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public Integer getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getMerchantProxyId() {
        return merchantProxyId;
    }

    public void setMerchantProxyId(Integer merchantProxyId) {
        this.merchantProxyId = merchantProxyId;
    }

    
    
    /******************************************************************************/
    private String shopPriceString;
    
	private String marketPriceString;
	
	
	public String getShopPriceString() {
		if(shopPrice!=null){
			DecimalFormat df = new DecimalFormat("#0.00");
			shopPriceString = df.format(((double) shopPrice)/100);
		}
		return shopPriceString;
	}

	public void setShopPriceString(String shopPriceString) {
		this.shopPriceString = shopPriceString;
	}

	public String getMarketPriceString() {
		if(marketPrice !=null){
			DecimalFormat df = new DecimalFormat("#0.00");
			marketPriceString = df.format(((double) marketPrice)/100);
		}
		return marketPriceString;
	}

	public void setMarketPriceString(String marketPriceString) {
		this.marketPriceString = marketPriceString;
	}
    
}