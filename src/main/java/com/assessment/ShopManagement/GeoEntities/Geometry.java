package com.assessment.ShopManagement.GeoEntities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geometry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Bounds bounds;
	
	@JsonProperty("location_type")
	private String locationType;
	
	private Location location;
    
    @JsonProperty("viewport")
    private Bounds viewPort;

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Bounds getViewPort() {
		return viewPort;
	}

	public void setViewPort(Bounds viewPort) {
		this.viewPort = viewPort;
	}

	@Override
	public String toString() {
		return "Geometry [bounds=" + bounds + ", locationType=" + locationType + ", location=" + location
				+ ", viewPort=" + viewPort + "]";
	}
    
}
