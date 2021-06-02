package com.assessment.ShopManagement.dao;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.assessment.ShopManagement.entity.ShopDetails;
import com.assessment.ShopManagement.service.ShopManagementServiceImpl;

@Repository
public class ShopManagementDaoImpl implements ShopManagementDaoI {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopManagementServiceImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addShopDetails(ShopDetails shopDetails, BigDecimal lattiude, BigDecimal longitude) {

		jdbcTemplate.update(
				"INSERT INTO SHOP_DETAILS (SHOP_NAME, SHOP_CATEGORY, SHOP_OWNER_NAME, SHOP_ADDRESS, SHOP_LATTITUDE, SHOP_LONGITUDE) VALUES "
						+ "(?, ?, ?, ?, ?, ?)",
				new Object[] { shopDetails.getShopName(), shopDetails.getShopCategory(), shopDetails.getShopOwnerName(),
						shopDetails.getShopAddress(), lattiude, longitude });

	}

	@Override
	public List<ShopDetails> listOfShops() {
		
		List<ShopDetails> shopList = jdbcTemplate.query("SELECT SHOP_NAME, SHOP_CATEGORY, SHOP_OWNER_NAME, SHOP_ADDRESS FROM SHOP_DETAILS", 
				(rowSet, rowNum) -> {
					ShopDetails shopDetails = new ShopDetails();
					shopDetails.setShopName(rowSet.getString("SHOP_NAME"));
					shopDetails.setShopCategory(rowSet.getString("SHOP_CATEGORY"));
					shopDetails.setShopAddress(rowSet.getString("SHOP_ADDRESS"));
					shopDetails.setShopOwnerName(rowSet.getString("SHOP_OWNER_NAME"));

					return shopDetails;
				});
		LOGGER.info("List of Shops are: " + shopList.toString());
		
		return shopList;
	}

	@Override
	public List<ShopDetails> searchShopsNearBy(BigDecimal lattitude, BigDecimal longitude) {

		List<ShopDetails> shopList = jdbcTemplate.query(
				"SELECT SHOP_NAME, SHOP_CATEGORY, SHOP_OWNER_NAME, SHOP_ADDRESS FROM SHOP_DETAILS WHERE (6371 * acos(cos(radians(?)) * cos(radians(SHOP_LATTITUDE)) * cos(radians(SHOP_LONGITUDE) - radians(?)) + sin(radians(?)) *  sin(radians(SHOP_LATTITUDE)))) < 10",
				new Object[] { lattitude, longitude, lattitude }, (rowSet, rowNum) -> {
					ShopDetails shopDetails = new ShopDetails();
					shopDetails.setShopName(rowSet.getString("SHOP_NAME"));
					shopDetails.setShopCategory(rowSet.getString("SHOP_CATEGORY"));
					shopDetails.setShopAddress(rowSet.getString("SHOP_ADDRESS"));
					shopDetails.setShopOwnerName(rowSet.getString("SHOP_OWNER_NAME"));

					return shopDetails;
				});

		LOGGER.info("List of Shops are on search address is: " + shopList.toString());
		return shopList;
	}
}
