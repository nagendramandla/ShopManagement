package com.assessment.ShopManagement.controller;

import static com.assessment.ShopManagement.utils.StudentManagementUtils.SHOP_CATEGORY;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.assessment.ShopManagement.config.ShopManagementConfig;
import com.assessment.ShopManagement.entity.ShopDetails;
import com.assessment.ShopManagement.service.ShopManagementServiceI;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping(value = "/shop")
@RestController
@Api(value = "Shop Management API's", description = "Shop Management API's")
public class ShopManagementController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopManagementController.class);
	
	@Autowired
	ShopManagementServiceI shopManagementService;
	
	@Autowired
	ShopManagementConfig shopManagementConfig;
	
	@PostMapping(value="/addShop")
	@ApiOperation(value = "Add shop details here")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
	public String addShopDetails(@RequestBody ShopDetails shopDetails) {
		
		LOGGER.info("Entered into ShopManagementController class addShopDetails method with::: "+shopDetails.toString());

		if(shopDetails!=null && SHOP_CATEGORY.contains(shopDetails.getShopCategory())) {
			shopManagementService.addShopDetails(shopDetails);
			return "Shop details added successfully";
			
		} else {
			return "Shop category should be one from "+SHOP_CATEGORY;
		}
	}
	
	@GetMapping(value = "/shops")
	@ApiOperation(value = "List of all shops")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
	public List<ShopDetails> listOfShops() {
		return shopManagementService.listOfShops();
	}
	
	@GetMapping(value = "/searchNearByShops/search/{shopNearAddress}")
	@ApiOperation(value = "API is used to search near by shops")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
	public List<ShopDetails> searchShopsNearBy(@PathVariable("shopNearAddress") String shopNearAddress) {
		return shopManagementService.searchShopsNearBy(shopNearAddress);
	}

	@ApiParam(name = "files", value = "Select the file to Upload", required = false, allowMultiple=true)  
	@RequestMapping(value="sendEmail", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public void sendEmail(@RequestParam String shopName, @RequestPart MultipartFile[] files) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("shopDetails files is : "+files);
		System.out.println("shopDetails files is : "+files.length);
		//ShopDetails shopDetails2 = new ObjectMapper().readValue(shopDetails, ShopDetails.class);
		System.out.println("location from config: "+shopManagementConfig.getLocation());
		System.out.println("shopDetails Object is : "+shopName);
		Arrays.asList(files).stream().forEach(file -> 
         	{
        	System.out.println(file.getOriginalFilename());
        	});
		
		shopManagementService.sendEmail(files);
	}
}
