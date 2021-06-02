package com.assessment.ShopManagement.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.assessment.ShopManagement.entity.ShopDetails;

public class ShopManagementDaoImplTest {
	
	@Mock
	JdbcTemplate jdbcTemplate;
	
	@InjectMocks
	ShopManagementDaoImpl shopManagementDao;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddShopDetails() {
		
		when(jdbcTemplate.update(any(), any(), any())).thenReturn(1);
		
		shopManagementDao.addShopDetails(new ShopDetails(), new BigDecimal("12.112"), new BigDecimal("33.32222"));
	}
	
	@Test
	public void testListOfShops() {
		
		List<ShopDetails> inputShopDetails = getShopDetails();
		when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(inputShopDetails);
		List<ShopDetails> ouptShopDetails = shopManagementDao.listOfShops();
		assertEquals(ouptShopDetails.size(),inputShopDetails.size());
		
	}
	
	private List<ShopDetails> getShopDetails() {
		
		List<ShopDetails> shopDetailsList = new ArrayList();
		
		ShopDetails shopDetails = new ShopDetails();
		shopDetails.setShopAddress("KalyanNagar, Bangalore");
		shopDetails.setShopCategory("Mall");
		
		shopDetailsList.add(shopDetails);
		
		return shopDetailsList;
	}

}
