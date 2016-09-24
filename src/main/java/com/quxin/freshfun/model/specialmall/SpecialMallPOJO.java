package com.quxin.freshfun.model.specialmall;

public class SpecialMallPOJO {
    private Integer id;

    private Long gmtCreate;

    private Long gmtModified;

    private String mallImg;

    private String mallInfoImg;

    private String mallContent;

    private String mallInfoContent;

    private String mallDes;

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

    public String getMallImg() {
        return mallImg;
    }

    public void setMallImg(String mallImg) {
        this.mallImg = mallImg == null ? null : mallImg.trim();
    }

    public String getMallInfoImg() {
        return mallInfoImg;
    }

    public void setMallInfoImg(String mallInfoImg) {
        this.mallInfoImg = mallInfoImg == null ? null : mallInfoImg.trim();
    }

    public String getMallContent() {
        return mallContent;
    }

    public void setMallContent(String mallContent) {
        this.mallContent = mallContent == null ? null : mallContent.trim();
    }

    public String getMallInfoContent() {
        return mallInfoContent;
    }

    public void setMallInfoContent(String mallInfoContent) {
        this.mallInfoContent = mallInfoContent == null ? null : mallInfoContent.trim();
    }

    public String getMallDes() {
        return mallDes;
    }

    public void setMallDes(String mallDes) {
        this.mallDes = mallDes == null ? null : mallDes.trim();
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
}