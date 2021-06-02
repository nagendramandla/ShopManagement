package com.assessment.ShopManagement.dao;

import java.math.BigDecimal;
import java.util.List;

import com.assessment.ShopManagement.entity.ShopDetails;

public interface ShopManagementDaoI {

	void addShopDetails(ShopDetails shopDetails, BigDecimal bigDecimal, BigDecimal bigDecimal2);

	List<ShopDetails> listOfShops();

	List<ShopDetails> searchShopsNearBy(BigDecimal bigDecimal, BigDecimal bigDecimal2);

	

}
