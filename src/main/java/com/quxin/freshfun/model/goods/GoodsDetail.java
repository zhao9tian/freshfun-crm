package com.quxin.freshfun.model.goods;



public class GoodsDetail {
	private String id;
	private Integer goodsId;
	private String des;
	
	private Integer goodsUnit;
	private String goodsBrand;
	private String goodsSize;
	private String goodsPlace;
	private String storageMethod;
	private Long goodsBirthdate ;
	private Long goodsOutdate ;
	private String goodsDelivery;
	private String goodsService;
	
	//详情图片
	private String standardImgPath;
	private String detailImgPath;
	private String carouselImgPath;
	

	public String getDetailImgPath() {
		return detailImgPath;
	}

	public void setDetailImgPath(String detailImgPath) {
		this.detailImgPath = detailImgPath;
	}

	public String getStandardImgPath() {
		return standardImgPath;
	}

	public void setStandardImgPath(String standardImgPath) {
		this.standardImgPath = standardImgPath;
	}

	public String getCarouselImgPath() {
		return carouselImgPath;
	}

	public void setCarouselImgPath(String carouselImgPath) {
		this.carouselImgPath = carouselImgPath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}


	public Integer getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(Integer goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public String getStorageMethod() {
		return storageMethod;
	}

	public void setStorageMethod(String storageMethod) {
		this.storageMethod = storageMethod;
	}

	
	public Long getGoodsBirthdate() {
		return goodsBirthdate;
	}

	public void setGoodsBirthdate(Long goodsBirthdate) {
		this.goodsBirthdate = goodsBirthdate;
	}

	public Long getGoodsOutdate() {
		return goodsOutdate;
	}

	public void setGoodsOutdate(Long goodsOutdate) {
		this.goodsOutdate = goodsOutdate;
	}

		public String getGoodsDelivery() {
		return goodsDelivery;
	}

	public void setGoodsDelivery(String goodsDelivery) {
		this.goodsDelivery = goodsDelivery;
	}

	public String getGoodsService() {
		return goodsService;
	}

	public void setGoodsService(String goodsService) {
		this.goodsService = goodsService;
	}

	

	public GoodsDetail() {
	}
	public GoodsDetail(Integer goodsId, String des, String actualImg,
			Integer goodsUnit, String goodsBrand, String goodsSize,
			String goodsPlace, String storageMethod, String goodsDelivery,
			String goodsService) {
		super();
		this.goodsId = goodsId;
		this.des = des;
		this.goodsUnit = goodsUnit;
		this.goodsBrand = goodsBrand;
		this.goodsSize = goodsSize;
		this.goodsPlace = goodsPlace;
		this.storageMethod = storageMethod;
		this.goodsDelivery = goodsDelivery;
		this.goodsService = goodsService;
	}

	@Override
	public String toString() {
		return "GoodsDetail [id=" + id + ", goodsId=" + goodsId + ", des=" + des + ", goodsUnit=" + goodsUnit
				+ ", goodsBrand=" + goodsBrand + ", goodsSize=" + goodsSize + ", goodsPlace=" + goodsPlace
				+ ", storageMethod=" + storageMethod + ", goodsBirthdate=" + goodsBirthdate + ", goodsOutdate="
				+ goodsOutdate + ", goodsDelivery=" + goodsDelivery + ", goodsService=" + goodsService
				+ ", standardImgPath=" + standardImgPath + ", detailImgPath=" + detailImgPath + ", carouselImgPath="
				+ carouselImgPath + "]";
	}
	
}
