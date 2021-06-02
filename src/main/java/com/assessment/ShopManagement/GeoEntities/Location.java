package com.assessment.ShopManagement.GeoEntities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("lat")
	private String lattitude;
	
	@JsonProperty("lng")
	private String longitude;

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [lattitude=" + lattitude + ", longitude=" + longitude + "]";
	}
	
}
