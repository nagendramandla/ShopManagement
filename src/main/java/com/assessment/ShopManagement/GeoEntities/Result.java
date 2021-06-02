package com.assessment.ShopManagement.GeoEntities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@JsonProperty("formatted_address")
	private String formattedAddress;
	
	private Geometry geometry;
	
	private List<String> types;

	@JsonProperty("address_components")
	private List<AddressComponent> addressComponents;

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public List<AddressComponent> getAddressComponents() {
		return addressComponents;
	}

	public void setAddressComponents(List<AddressComponent> addressComponents) {
		this.addressComponents = addressComponents;
	}

	@Override
	public String toString() {
		return "Result [formattedAddress=" + formattedAddress + ", geometry=" + geometry + ", types=" + types
				+ ", addressComponents=" + addressComponents + "]";
	}
	
}
