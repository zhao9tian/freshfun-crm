package com.quxin.freshfun.model.withdraw;

/**
 * 提现出参
 * Created by qucheng on 2016/9/29.
 */
public class WithdrawOutput {
    private String id ;
    /**用户id*/
    private Long userId;
    /*提现金额*/
    private String withDrawPrice;
    /**提现方式*/
    private String withDrawType;
    /**提现账户*/
    private String paymentAccount;
    /**申请状态*/
    private String state;
    /**退款申请时间*/
    private String createDate;
    /**处理时间*/
    private String updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getWithDrawType() {
        return withDrawType;
    }

    public void setWithDrawType(String withDrawType) {
        this.withDrawType = withDrawType;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }


    public String getWithDrawPrice() {
        return withDrawPrice;
    }

    public void setWithDrawPrice(String withDrawPrice) {
        this.withDrawPrice = withDrawPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "WithdrawOutput{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", withDrawPrice='" + withDrawPrice + '\'' +
                ", withDrawType='" + withDrawType + '\'' +
                ", paymentAccount='" + paymentAccount + '\'' +
                ", state='" + state + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }
}
