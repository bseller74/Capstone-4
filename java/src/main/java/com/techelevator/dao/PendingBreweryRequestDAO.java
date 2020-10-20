package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.PendingBreweryRequest;

//interface for pending brewery request
public interface PendingBreweryRequestDAO {
	
	public List<PendingBreweryRequest> getAllRequest();
	
	public void addRequest(PendingBreweryRequest request);
	
	public void updateRequest(PendingBreweryRequest request, long id);
	
	
	
	

}
