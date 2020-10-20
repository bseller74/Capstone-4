package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BeerDAO;
import com.techelevator.model.Beer;

@RestController
@CrossOrigin
public class BeerController {

	@Autowired
	BeerDAO dao;
	// controller to pull top 5 beers
	@RequestMapping(path = "/topbeers", method = RequestMethod.GET)
	public List<Beer> getTopFiveBeers(){
		List<Beer> topFiveBeers = dao.topFiveBeers();
		return topFiveBeers;
	}
	// controller to pull all beers
	@RequestMapping(path = "/beer", method = RequestMethod.GET)
	public List<Beer> getAllBeer() {
		return dao.getAllBeer();
	}
	// controller to pull a beer by its database id
	@RequestMapping(path = "/beer/{id}", method = RequestMethod.GET)
	public Beer getBeerById(@PathVariable Long id) {
		dao.updateRating(id);
		return dao.getBeerById(id);
	}
	// controller to get brewery beers
	@RequestMapping(path = "/brewery/{id}/beers", method = RequestMethod.GET)
	public List<Beer> beerByBrewery(@PathVariable Long id) {
		return dao.BeerByBrewery(id);
	}
	// controller to delete beers
	@RequestMapping(path = "/beer/{id}", method = RequestMethod.DELETE)
	public String deleteBeer(@PathVariable Long id) {
		dao.deleteBeer(id);
		return "Beer Deleted";

	}
	// controller to add new beers
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/addBeer", method = RequestMethod.POST)
	public void saveBeer(@Valid @RequestBody Beer beer) {
		dao.saveBeer(beer);
	}
	// controller to edit beers
	@RequestMapping(path = "/beer/{id}", method = RequestMethod.PUT)
	public void updateBeer(@PathVariable long id, @RequestBody Beer beer) {
		dao.updateBeer(beer, id);
	}

}
