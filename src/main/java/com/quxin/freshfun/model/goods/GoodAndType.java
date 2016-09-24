package com.quxin.freshfun.model.goods;

public class GoodAndType {
	private Integer goodId;
	private Integer typeId;
	public Integer getGoodId() {
		return goodId;
	}
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public GoodAndType(Integer goodId, Integer typeId) {
		super();
		this.goodId = goodId;
		this.typeId = typeId;
	}
	
	public GoodAndType() {
	}
}
