package com.assessment.ShopManagement.GeoEntities;

import java.io.Serializable;
import java.util.List;

public class GeocodeResponse implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	private List<Result> results;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Result> getResults() {
		return results;
	}
	public void setResults(List<Result> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return "GeocodeResponse [status=" + status + ", results=" + results + "]";
	}
    
}
