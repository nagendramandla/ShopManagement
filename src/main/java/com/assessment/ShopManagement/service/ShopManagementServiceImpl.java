package com.assessment.ShopManagement.service;

import static com.assessment.ShopManagement.utils.StudentManagementUtils.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.ShopManagement.GeoEntities.GeocodeResponse;
import com.assessment.ShopManagement.GeoEntities.Location;
import com.assessment.ShopManagement.dao.ShopManagementDaoI;
import com.assessment.ShopManagement.entity.ShopDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ShopManagementServiceImpl implements ShopManagementServiceI {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopManagementServiceImpl.class);

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	ShopManagementDaoI shopManagementDao;

	RestTemplate restTemplate = new RestTemplate();
	
	@Override
	public void addShopDetails(ShopDetails shopDetails) {

		String forObject = restTemplate.getForObject(GEO_CODE_URI.replaceAll(STR_SHOP_ADDRESS, shopDetails.getShopAddress())
				.replaceAll(STR_YOUR_API_KEY, API_KEY), String.class);
		GeocodeResponse geoCodeResponse = null;
		
		try {
			geoCodeResponse = mapper.readValue(forObject, GeocodeResponse.class);
			Location location = geoCodeResponse.getResults().get(0).getGeometry().getLocation();
			shopManagementDao.addShopDetails(shopDetails, new BigDecimal(location.getLattitude()), new BigDecimal(location.getLongitude()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOGGER.info("Response from GeoCoding is ::::: "+geoCodeResponse.toString());
	}

	@Override
	public List<ShopDetails> listOfShops() {
		return shopManagementDao.listOfShops();
	}

	@Override
	public List<ShopDetails> searchShopsNearBy(String shopNearAddress) {
		
		String forObject = restTemplate.getForObject(GEO_CODE_URI.replaceAll(STR_SHOP_ADDRESS, shopNearAddress)
				.replaceAll(STR_YOUR_API_KEY, API_KEY), String.class);
		GeocodeResponse geoCodeResponse = null;
		List<ShopDetails> nearByShops = null;
		
		try {
			geoCodeResponse = mapper.readValue(forObject, GeocodeResponse.class);
			Location location = geoCodeResponse.getResults().get(0).getGeometry().getLocation();
			nearByShops = shopManagementDao.searchShopsNearBy(new BigDecimal(location.getLattitude()), new BigDecimal(location.getLongitude()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nearByShops;
	}

}
