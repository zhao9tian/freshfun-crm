package com.quxin.freshfun.model.goods;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import com.quxin.freshfun.utils.DateUtils;

public class GoodsPOJO {
    private Integer id;

    /**
     * unix是按秒计算
     */
    private Long gmtCreate ;

    private Long gmtModified ;

    private String goodsName;

    private String goodsDes;

    private String goodsImg;

    /**
     * 数据库应删属性
     */
    private String goodsType = "0";

    private Integer shopPrice;

    private Integer marketPrice;

    private Integer storeId;

    private Integer goodsWeight;

    private Integer goodsStorage;

    private Byte warnning;

    private Byte isOnSale;

    private Byte isOnAgent;

    private Byte isNew;

    private Byte isHot;

    private Byte isPromote;

    private String reservedField;
    
    private Long merchantProxyId;
    
    private String shareUrl;


    public Long getMerchantProxyId() {
        return merchantProxyId;
    }

    public void setMerchantProxyId(Long merchantProxyId) {
        this.merchantProxyId = merchantProxyId;
    }

    private List<Integer> goodsTypeIds;
    
    

	public List<Integer> getGoodsTypeIds() {
		return goodsTypeIds;
	}

	public void setGoodsTypeIds(List<Integer> goodsTypeIds) {
		this.goodsTypeIds = goodsTypeIds;
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
		this.goodsImg = goodsImg;
	}

	public Integer getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Integer shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Integer marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Integer goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Integer getGoodsStorage() {
        return goodsStorage;
    }

    public void setGoodsStorage(Integer goodsStorage) {
        this.goodsStorage = goodsStorage;
    }

    public Byte getWarnning() {
        return warnning;
    }

    public void setWarnning(Byte warnning) {
        this.warnning = warnning;
    }

    public Byte getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(Byte isOnSale) {
        this.isOnSale = isOnSale;
    }

    public Byte getIsNew() {
        return isNew;
    }

    public void setIsNew(Byte isNew) {
        this.isNew = isNew;
    }

    public Byte getIsHot() {
        return isHot;
    }

    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    public Byte getIsPromote() {
        return isPromote;
    }

    public void setIsPromote(Byte isPromote) {
        this.isPromote = isPromote;
    }

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField == null ? null : reservedField.trim();
    }

    public Byte getIsOnAgent() {
        return isOnAgent;
    }

    public void setIsOnAgent(Byte isOnAgent) {
        this.isOnAgent = isOnAgent;
    }
    
	@Override
	public String toString() {
		return "GoodsPOJO [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", goodsName="
				+ goodsName + ", goodsDes=" + goodsDes + ", goodsImg=" + goodsImg + ", goodsType=" + goodsType
				+ ", shopPrice=" + shopPrice + ", marketPrice=" + marketPrice + ", storeId=" + storeId
				+ ", goodsWeight=" + goodsWeight + ", goodsStorage=" + goodsStorage + ", warnning=" + warnning
				+ ", isOnSale=" + isOnSale + ", isNew=" + isNew + ", isHot=" + isHot + ", isPromote=" + isPromote
				+ ", reservedField=" + reservedField + ", merchantProxyId=" + merchantProxyId + ", goodsTypeIds="
				+ goodsTypeIds + ", shopPriceString=" + shopPriceString + ", marketPriceString=" + marketPriceString
				+ ", gmtCreateView=" + gmtCreateView + ", gmtModifiedView=" + gmtModifiedView + ", sales=" + sales
				+ ", SalesMoney=" + SalesMoney + "]";
	}

	/*************************************************************/
	//用于页面展示价格显示成double 两位小数
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

	/**
	 * 用于首页列表显示,时间转成string显示在页面
	 */
	private String gmtCreateView;
	
	private String gmtModifiedView;

	public String getGmtCreateView() {
		try {
			gmtCreateView = DateUtils.longToString(gmtCreate, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return gmtCreateView;
	}

	public String getGmtModifiedView() {
		try {
			gmtModifiedView = DateUtils.longToString(gmtModified, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return gmtModifiedView;
	}


	/**
     * 上月销量
     */
    private Integer sales;
    
    
    public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}
	
	private Double SalesMoney;

	public Double getSalesMoney() {
		return SalesMoney;
	}
	public void setSalesMoney(Double salesMoney) {
		SalesMoney = salesMoney;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	
}