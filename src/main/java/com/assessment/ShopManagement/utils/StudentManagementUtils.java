package com.assessment.ShopManagement.utils;

import java.util.Arrays;
import java.util.List;

public interface StudentManagementUtils {

	List<String> SHOP_CATEGORY = Arrays.asList(new String[] {"General store", "Mall", "Supermarket", "Medical store"});
	
	String GEO_CODE_URI = "https://maps.googleapis.com/maps/api/geocode/json?address=shopAddress&key=YOUR_API_KEY";
	
	String STR_YOUR_API_KEY = "YOUR_API_KEY";
	
	String API_KEY = "AIzaSyD_zhjLlVSrB860i4hFUiQTkLQiH7Y8JA0";
	
	String STR_SHOP_ADDRESS = "shopAddress";
	
}
