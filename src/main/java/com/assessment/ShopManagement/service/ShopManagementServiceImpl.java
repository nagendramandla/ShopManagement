package com.assessment.ShopManagement.service;

import static com.assessment.ShopManagement.utils.StudentManagementUtils.API_KEY;
import static com.assessment.ShopManagement.utils.StudentManagementUtils.GEO_CODE_URI;
import static com.assessment.ShopManagement.utils.StudentManagementUtils.STR_SHOP_ADDRESS;
import static com.assessment.ShopManagement.utils.StudentManagementUtils.STR_YOUR_API_KEY;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

	@Override
	public void sendEmail(MultipartFile[] files) {
		Properties props = getMailProperties();
	    Session session = sessionAuthentication(props);
	    System.out.println("Authentication Success");
	    try {
	        // Create a default MimeMessage object.
	        MimeMessage message = new MimeMessage(session);
	        InternetAddress[] myToList = InternetAddress.parse("mandla.nagendra@gmail.com");
	        InternetAddress[] myBccList = InternetAddress.parse("mandla.nagendra@gmail.com");
	        InternetAddress[] myCcList = InternetAddress.parse("mandla.nagendra@gmail.com");
	        // Set From: header field of the header.
	        message.setFrom(new InternetAddress("mandla.nagendra@gmail.com"));
	
	        // Set To: header field of the header.
	        message.setRecipients(Message.RecipientType.TO,myToList);
	        message.setRecipients(Message.RecipientType.BCC, myBccList);
	        message.setRecipients(Message.RecipientType.CC, myCcList);
	
	        // Set Subject: header field
	        message.setSubject("Test");
	
	        // Create the message part
	        BodyPart messageBodyPart = new MimeBodyPart();
	
	        // Now set the actual message
	        messageBodyPart.setContent("Messge Body", "text/html");
	
	        // Create a multipar message
	        Multipart multipart = new MimeMultipart();
	
	        // Set text message part
	        multipart.addBodyPart(messageBodyPart);
	
	        addMultiPartAttachment(files, multipart);
	
	        // Send the complete message parts
	        message.setContent(multipart);
	        //Transport.send(message, message.getAllRecipients());
	        Transport.send(message);
	     } catch (MessagingException e) {
	         e.printStackTrace();
	    }
	}

	private Session sessionAuthentication(Properties props) {
		return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mandla.nagendra@gmail.com", "XXXXXX"); // Change the password here.
            }
        });
	}
	
	private Properties getMailProperties() {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
	    //props.put("mail.from", "mandla.nagendra@gmail.com");
	    //props.put("mail.smtp.starttls.enable", "true");
	    props.setProperty("mail.debug", "true");
	    props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.prot", "465");

        return props;
	}
	
	/**
	 * @param files
	 * @param multipart
	 * @throws MessagingException
	 */
	private void addMultiPartAttachment(MultipartFile[] files, Multipart multipart) throws MessagingException {
		if(files != null && files.length > 0){
		    for (MultipartFile filePath : files) {
		        MimeBodyPart attachPart = new MimeBodyPart();
		        try {
		        	System.out.println("Attaching file : "+ filePath.getOriginalFilename());
		            filePath.transferTo(new File(filePath.getOriginalFilename()).getAbsoluteFile());
		            attachPart.attachFile(filePath.getOriginalFilename());
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		        multipart.addBodyPart(attachPart);
		    }
		}
	}

}
