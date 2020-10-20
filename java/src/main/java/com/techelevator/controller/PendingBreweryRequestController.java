package com.techelevator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.dao.PendingBreweryRequestDAO;
import com.techelevator.model.PendingBreweryRequest;

@CrossOrigin
@RestController
public class PendingBreweryRequestController {

	@Autowired
	private PendingBreweryRequestDAO dao;
	
	// controller for listing all brewery requests
	@RequestMapping(path = "/getAllPendingBreweryRequest", method = RequestMethod.GET)
	public List<PendingBreweryRequest> getAllRequests() {
		return dao.getAllRequest();
	}
	// controller to change brewery request
	@RequestMapping(path = "/updateBreweryRequest", method = RequestMethod.PUT)
	public void updateRequest(@RequestBody PendingBreweryRequest request) {
		dao.updateRequest(request, request.getId());
	}
	// controller to add a brewery request 
	@RequestMapping(path = "/addBreweryRequest", method = RequestMethod.POST)
	public void addRequest(@RequestBody PendingBreweryRequest request) {
		dao.addRequest(request);
	}
}
