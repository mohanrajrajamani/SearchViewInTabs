package com.example.admin.searchviewintabs.helper;

public class ProductList {

	String title, subTitle;

	public ProductList(String title, String subTitle) {
		this.subTitle = subTitle;
		this.title = title;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

}
