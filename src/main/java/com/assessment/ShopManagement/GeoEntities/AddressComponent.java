package com.assessment.ShopManagement.GeoEntities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AddressComponent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonAlias(value = "long_name")
	private String longName;
	
	@JsonAlias(value = "short_name")
	private String shortName;
	
	private List<String> types;

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "AddressComponent [longName=" + longName + ", shortName=" + shortName + ", types=" + types + "]";
	}
	
}
