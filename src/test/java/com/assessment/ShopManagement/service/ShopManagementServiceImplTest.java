package com.assessment.ShopManagement.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.assessment.ShopManagement.GeoEntities.GeocodeResponse;
import com.assessment.ShopManagement.GeoEntities.Geometry;
import com.assessment.ShopManagement.GeoEntities.Location;
import com.assessment.ShopManagement.GeoEntities.Result;
import com.assessment.ShopManagement.dao.ShopManagementDaoI;
import com.assessment.ShopManagement.entity.ShopDetails;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShopManagementServiceImplTest {

	@Spy
	ShopManagementDaoI shopManagementDao;

	@Mock
	ObjectMapper objectMapper;

	@InjectMocks
	ShopManagementServiceImpl shopManagementService;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addShopDetails() throws JsonParseException, JsonMappingException, IOException {

		when(objectMapper.readValue(any(String.class), (Class<Object>) any(Object.class))).thenReturn(createGeocodeResponseObj());

		shopManagementService.addShopDetails(createShopDetailsObj());

	}

	@Test
	public void testListOfShops() {
		shopManagementService.listOfShops();
	}

	@Test
	public void testSearchShopsNearBy() throws JsonParseException, JsonMappingException, IOException {
		
		GeocodeResponse geocodeResponse = createGeocodeResponseObj();
		when(objectMapper.readValue(any(String.class), (Class<Object>) any(Object.class))).thenReturn(geocodeResponse);
		
		Location location = geocodeResponse.getResults().get(0).getGeometry().getLocation();
		
		shopManagementDao.searchShopsNearBy(new BigDecimal(location.getLattitude()), new BigDecimal(location.getLongitude()));

	}

	private ShopDetails createShopDetailsObj() {
		ShopDetails shopDetails = new ShopDetails();
		shopDetails.setShopAddress("KalyanNagar, Bangalore");
		shopDetails.setShopCategory("Mall");
		return shopDetails;
	}

	private GeocodeResponse createGeocodeResponseObj() {
		GeocodeResponse geoResponse = new GeocodeResponse();

		List<Result> results = new ArrayList();

		Result result = new Result();

		Geometry geometry = new Geometry();

		Location location = new Location();

		location.setLattitude("13.000999989");
		location.setLongitude("22.927972");

		geometry.setLocation(location);

		result.setGeometry(geometry);

		results.add(result);
		geoResponse.setResults(results);
		return geoResponse;
	}

}
