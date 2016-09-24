package com.quxin.freshfun.model.specialtheme;

public class SpecialThemePOJO {
	private Integer id;

    private String themeDes;

    private String themeImg;

    private String themeInfoImg;

    private String themeContent;

    private String themeInfoContent;

    private Long gmtCreate;

    private Long gmtModified;

    private Byte isDeleted;

    private String reservedField;
    
    private String shareUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeDes() {
        return themeDes;
    }

    public void setThemeDes(String themeDes) {
        this.themeDes = themeDes == null ? null : themeDes.trim();
    }

    public String getThemeImg() {
        return themeImg;
    }

    public void setThemeImg(String themeImg) {
        this.themeImg = themeImg == null ? null : themeImg.trim();
    }

    public String getThemeInfoImg() {
        return themeInfoImg;
    }

    public void setThemeInfoImg(String themeInfoImg) {
        this.themeInfoImg = themeInfoImg == null ? null : themeInfoImg.trim();
    }

    public String getThemeContent() {
        return themeContent;
    }

    public void setThemeContent(String themeContent) {
        this.themeContent = themeContent == null ? null : themeContent.trim();
    }

    public String getThemeInfoContent() {
        return themeInfoContent;
    }

    public void setThemeInfoContent(String themeInfoContent) {
        this.themeInfoContent = themeInfoContent == null ? null : themeInfoContent.trim();
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

	@Override
	public String toString() {
		return "SpecialThemePOJO [id=" + id + ", themeDes=" + themeDes
				+ ", themeImg=" + themeImg + ", themeInfoImg=" + themeInfoImg
				+ ", themeContent=" + themeContent + ", themeInfoContent="
				+ themeInfoContent + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + ", isDeleted=" + isDeleted
				+ ", reservedField=" + reservedField + "]";
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
    
    
    
}