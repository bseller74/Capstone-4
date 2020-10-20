package com.techelevator.dao;

import com.techelevator.model.BrewerRequest;

//interface for Brewer Request
public interface BrewerRequestDAO {

	public void switchProcessedStatus(BrewerRequest request);
	
	public void updateBrewerIdForBrewery(BrewerRequest request);
	
	
	
	
}
