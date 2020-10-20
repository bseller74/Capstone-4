package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BreweryDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.BrewerRequest;
import com.techelevator.model.Brewery;
import com.techelevator.model.User;

@RestController
@CrossOrigin
public class BreweryController {

	@Autowired
	public BreweryDAO dao;
	@Autowired
	public UserDAO userDao;

	// controller to list all breweries
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/breweries", method = RequestMethod.GET)
	public List<Brewery> getAllBreweries() {
		return dao.getAllBrewerys();
	}

	// controller to get breweries by database id
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/brewery/{id}", method = RequestMethod.GET)
	public Brewery getBrewery(@PathVariable long id) {
		return dao.getBreweryById(id);
	}

	// controller to get brewery by the brewer's database id
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/brewery/brewer/{id}", method = RequestMethod.GET)
	public Brewery getBreweryByBrewer(@PathVariable long id) {
		return dao.getBreweryByBrewer(id);
	}

	// allows administrator to create brewery
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/addBrewery", method = RequestMethod.POST)
	public void saveBrewery(@Valid @RequestBody Brewery brewery) {
		dao.saveBrewery(brewery);
	}

	// controller to edit brewery by breweries database id
	@RequestMapping(path = "/brewery/{id}", method = RequestMethod.PUT)
	public void updateBrewery(@PathVariable long id, @RequestBody Brewery brewery) {
		dao.updateBrewery(brewery, id);
	}

	// controller to let user request to be a brewer
	@RequestMapping(path = "/brewerRequest/{id}", method = RequestMethod.POST)
	public void sendBrewerRequest(@PathVariable long id, @RequestBody BrewerRequest user) {
		userDao.newBrewer(id, user);
	}

	// controller to list all requests
	@RequestMapping(path = "/brewerRequest", method = RequestMethod.GET)
	public List<BrewerRequest> getBrewerRequest() {
		return userDao.getAllRequests();
	}
	// controller to pull brewery by name
	@RequestMapping(path = "/getBreweryByName/{name}", method = RequestMethod.GET)
	public Brewery getBreweryByName(@PathVariable String name) {
		return dao.getBreweryByName(name);
	}
	// controller to pull brewery by database id
	@RequestMapping(path = "/brewery/{id}", method = RequestMethod.DELETE)
	public void deleteBrewery(@PathVariable long id) {
		dao.deleteBrewery(id);
	}

}
