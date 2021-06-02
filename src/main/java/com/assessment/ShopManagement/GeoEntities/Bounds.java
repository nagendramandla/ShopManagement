package com.assessment.ShopManagement.GeoEntities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bounds implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("northeast")
	private Location northEast;
	
	@JsonProperty("southwest")
	private Location southWest;

	public Location getNorthEast() {
		return northEast;
	}

	public void setNorthEast(Location northEast) {
		this.northEast = northEast;
	}

	public Location getSouthWest() {
		return southWest;
	}

	public void setSouthWest(Location southWest) {
		this.southWest = southWest;
	}

	@Override
	public String toString() {
		return "Bounds [northEast=" + northEast + ", southWest=" + southWest + "]";
	}

}
