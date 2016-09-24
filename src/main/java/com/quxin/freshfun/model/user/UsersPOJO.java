package com.quxin.freshfun.model.user;

public class UsersPOJO {
    private Integer id;

    private Long userId;

    private Long gmtCreate;

    private Long gmtModified;

    private String userName;

    private String userHeadUrl;

    private String mobilePhone;

    private String deviceId;

    private String wxId;

    private String wzId;

    private String loginMethod;

    private Byte isReceived;

    private Integer parentId;

    private Long regTime;

    private Byte userCredit;

    private Integer userMoney;

    private Integer frozenMoney;

    private Byte userIdentify;

    private Byte incomeIdentify;

    private Byte userEnter;

    private Integer userInfoId;

    private Byte isDeleted;

    private Integer userAddress;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl == null ? null : userHeadUrl.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId == null ? null : wxId.trim();
    }

    public String getWzId() {
        return wzId;
    }

    public void setWzId(String wzId) {
        this.wzId = wzId == null ? null : wzId.trim();
    }

    public String getLoginMethod() {
        return loginMethod;
    }

    public void setLoginMethod(String loginMethod) {
        this.loginMethod = loginMethod == null ? null : loginMethod.trim();
    }

    public Byte getIsReceived() {
        return isReceived;
    }

    public void setIsReceived(Byte isReceived) {
        this.isReceived = isReceived;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Long getRegTime() {
        return regTime;
    }

    public void setRegTime(Long regTime) {
        this.regTime = regTime;
    }

    public Byte getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(Byte userCredit) {
        this.userCredit = userCredit;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(Integer frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public Byte getUserIdentify() {
        return userIdentify;
    }

    public void setUserIdentify(Byte userIdentify) {
        this.userIdentify = userIdentify;
    }

    public Byte getIncomeIdentify() {
        return incomeIdentify;
    }

    public void setIncomeIdentify(Byte incomeIdentify) {
        this.incomeIdentify = incomeIdentify;
    }

    public Byte getUserEnter() {
        return userEnter;
    }

    public void setUserEnter(Byte userEnter) {
        this.userEnter = userEnter;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Integer userAddress) {
        this.userAddress = userAddress;
    }
}