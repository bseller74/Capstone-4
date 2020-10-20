package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.Beer;

// interface for beer
public interface BeerDAO {

	public List<Beer> getAllBeer();

	public Beer getBeerById(Long id);

	public List<Beer> BeerByBrewery(Long id);

	public void deleteBeer(Long id);

	public void saveBeer(Beer beer);

	public void updateBeer(Beer beer, long id);
	
	public void updateRating(Long id);
	
	public List<Beer> topFiveBeers();

}
