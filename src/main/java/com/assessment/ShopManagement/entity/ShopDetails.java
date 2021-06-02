package com.assessment.ShopManagement.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Shop Details", description = "Enter shop details")
public class ShopDetails implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1234152L;
	
	private String shopName;
	@ApiModelProperty(value="Shop Category")
	private String shopCategory;
	private String shopAddress;
	private String shopOwnerName;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(String shopCategory) {
		this.shopCategory = shopCategory;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopOwnerName() {
		return shopOwnerName;
	}
	public void setShopOwnerName(String shopOwnerName) {
		this.shopOwnerName = shopOwnerName;
	}
	
	@Override
	public String toString() {
		return "ShopDetails [shopName=" + shopName + ", shopCategory=" + shopCategory + ", shopAddress=" + shopAddress
				+ ", shopOwnerName=" + shopOwnerName + "]";
	}
	
}
