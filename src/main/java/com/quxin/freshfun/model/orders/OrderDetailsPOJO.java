package com.quxin.freshfun.model.orders;

import com.quxin.freshfun.model.goods.GoodsPOJO;
import com.quxin.freshfun.model.user.UsersPOJO;

public class OrderDetailsPOJO {
    private Integer id;

    private Long userId;

    private Long orderId;

    private String orderDetailsId;

    private Integer goodsId;

    private Integer actualPrice;

    private String deliveryNum;

    private Integer addressId;

    private Integer commentId;

    private Long payTime;

    private Integer paymentMethod;

    private Integer count;

    private Integer payStatus;

    private Integer orderStatus;

    private Integer refundStatus;

    private Integer commentStatus;

    private Integer payPlateform;

    private Integer isDeleted;

    private Long createDate;

    private Long updateDate;

    private String deliveryName;
    
    private Integer isLimit;
    
    private GoodsPOJO goods;
    
    private UsersPOJO user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId == null ? null : orderDetailsId.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(String deliveryNum) {
        this.deliveryNum = deliveryNum == null ? null : deliveryNum.trim();
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Integer getPayPlateform() {
        return payPlateform;
    }

    public void setPayPlateform(Integer payPlateform) {
        this.payPlateform = payPlateform;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName == null ? null : deliveryName.trim();
    }

	@Override
	public String toString() {
		return "OrderDetailsPOJO [id=" + id + ", userId=" + userId + ", orderId=" + orderId + ", orderDetailsId="
				+ orderDetailsId + ", goodsId=" + goodsId + ", actualPrice=" + actualPrice + ", deliveryNum="
				+ deliveryNum + ", addressId=" + addressId + ", commentId=" + commentId + ", payTime=" + payTime
				+ ", paymentMethod=" + paymentMethod + ", count=" + count + ", payStatus=" + payStatus
				+ ", orderStatus=" + orderStatus + ", refundStatus=" + refundStatus + ", commentStatus=" + commentStatus
				+ ", payPlateform=" + payPlateform + ", isDeleted=" + isDeleted + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", deliveryName=" + deliveryName + "]";
	}

	public GoodsPOJO getGoods() {
		return goods;
	}

	public void setGoods(GoodsPOJO goods) {
		this.goods = goods;
	}

	public Integer getIsLimit() {
		return isLimit;
	}

	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}


	public UsersPOJO getUser() {
		return user;
	}

	public void setUser(UsersPOJO user) {
		this.user = user;
	}
    
    /****************************************订单发货时间************************************************/
    private Long deliveryTime;

	public Long getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Long deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
    
    
}