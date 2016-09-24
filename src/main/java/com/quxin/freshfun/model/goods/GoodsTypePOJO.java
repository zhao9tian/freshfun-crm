package com.quxin.freshfun.model.goods;

public class GoodsTypePOJO {
    private Integer id;

    private Long gmtCreate;

    private Long gmtModified;

    private String goodsType;

    private String goodsTypeImg;
    
    private String goodsInfoDes;

    private String goodsInfoImg;

    private Byte isDeleted;

    private String reservedField;

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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    public String getGoodsTypeImg() {
        return goodsTypeImg;
    }

    public void setGoodsTypeImg(String goodsTypeImg) {
        this.goodsTypeImg = goodsTypeImg == null ? null : goodsTypeImg.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField == null ? null : reservedField.trim();
    }

	public String getGoodsInfoDes() {
		return goodsInfoDes;
	}

	public void setGoodsInfoDes(String goodsInfoDes) {
		this.goodsInfoDes = goodsInfoDes;
	}

	public String getGoodsInfoImg() {
		return goodsInfoImg;
	}

	public void setGoodsInfoImg(String goodsInfoImg) {
		this.goodsInfoImg = goodsInfoImg;
	}
}