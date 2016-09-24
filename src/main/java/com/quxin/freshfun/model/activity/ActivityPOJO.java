package com.quxin.freshfun.model.activity;

import java.text.ParseException;

import com.quxin.freshfun.utils.DateUtils;

public class ActivityPOJO {
    private Integer id;

    private Long gmtCreate;

    private Long gmtModified;

    private String activityDes;

    private String activityImg;

    private String activityInfoImg;

    private String activityContent;

    private String activityInfoContent;

    private Byte isDeleted;

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

    public String getActivityDes() {
        return activityDes;
    }

    public void setActivityDes(String activityDes) {
        this.activityDes = activityDes == null ? null : activityDes.trim();
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg == null ? null : activityImg.trim();
    }

    public String getActivityInfoImg() {
        return activityInfoImg;
    }

    public void setActivityInfoImg(String activityInfoImg) {
        this.activityInfoImg = activityInfoImg == null ? null : activityInfoImg.trim();
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent == null ? null : activityContent.trim();
    }

    public String getActivityInfoContent() {
        return activityInfoContent;
    }

    public void setActivityInfoContent(String activityInfoContent) {
        this.activityInfoContent = activityInfoContent == null ? null : activityInfoContent.trim();
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
    /**
	 * 用于首页列表显示
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
}