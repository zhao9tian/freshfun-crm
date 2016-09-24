package com.quxin.freshfun.model;

public class ImgUtilsPOJO {
	private String imgView;
	
	private String imgPath;

	public String getImgView() {
		return imgView;
	}

	public void setImgView(String imgView) {
		this.imgView = imgView;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public ImgUtilsPOJO(String imgView, String imgPath) {
		super();
		this.imgView = imgView;
		this.imgPath = imgPath;
	}
	
	public ImgUtilsPOJO() {
	}
}
