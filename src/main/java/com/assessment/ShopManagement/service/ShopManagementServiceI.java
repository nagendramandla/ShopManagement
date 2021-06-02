package com.assessment.ShopManagement.service;

import java.util.List;

import com.assessment.ShopManagement.entity.ShopDetails;

public interface ShopManagementServiceI {

	void addShopDetails(ShopDetails shopDetails);

	List<ShopDetails> listOfShops();

	List<ShopDetails> searchShopsNearBy(String shopNearAddress);
	
	

}
