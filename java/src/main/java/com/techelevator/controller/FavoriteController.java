package com.techelevator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.dao.FavoriteDAO;
import com.techelevator.model.Favorite;

@RestController
@CrossOrigin
public class FavoriteController {
	
	@Autowired
	FavoriteDAO dao;
	// controller for adding a favorite brewery 
	@RequestMapping(path="/favorite/{user_id}/{brewery_id}", method=RequestMethod.POST)
	public void AddFavorite(@PathVariable Long user_id, @PathVariable Long brewery_id) {
		dao.addFavorite(user_id, brewery_id);
	}
	// controller for deleting a favorite brewery 
	@RequestMapping(path="/favorite/{user_id}/{brewery_id}", method=RequestMethod.DELETE)
	public void DeleteFavorite(@PathVariable Long user_id, @PathVariable Long brewery_id) {
		dao.deleteFavorite(user_id, brewery_id);
	}
	// controller for pulling all favorited breweries
	@RequestMapping(path="/favorite/{user_id}", method=RequestMethod.GET)
	public List<Favorite> getFavorites(@PathVariable Long user_id) {
		return dao.getFavorites(user_id);
	}
}
