package com.assessment.ShopManagement.controller;

import static org.junit.Assert.assertEquals;
import static com.assessment.ShopManagement.utils.StudentManagementUtils.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.assessment.ShopManagement.entity.ShopDetails;
import com.assessment.ShopManagement.service.ShopManagementServiceI;

public class ShopManagementControllerTest {
	
	@Spy
	ShopManagementServiceI shopManagementService;
	
	@InjectMocks
	ShopManagementController shopManagementController;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddShopDetails() {
		ShopDetails shopDetails = new ShopDetails();
		shopDetails.setShopAddress("KalyanNagar, Bangalore");
		shopDetails.setShopCategory("Mall");
		
		assertEquals(shopManagementController.addShopDetails(shopDetails), "Shop details added successfully");
	}
	
	@Test
	public void testAddShopDetailsForInValidCategory() {
		ShopDetails shopDetails = new ShopDetails();
		shopDetails.setShopAddress("KalyanNagar, Bangalore");
		shopDetails.setShopCategory("MallIn");
		
		assertEquals(shopManagementController.addShopDetails(shopDetails), "Shop category should be one from "+SHOP_CATEGORY);
	}

}
