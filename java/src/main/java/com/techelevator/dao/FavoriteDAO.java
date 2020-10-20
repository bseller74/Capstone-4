package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.Favorite;
//interface for favorite brewery
public interface FavoriteDAO {

	public void addFavorite(Long user_id, Long brewery_id);
	
	public void deleteFavorite(Long user_id, Long brewery_id);
	
	public List<Favorite> getFavorites(Long user_id);
	
}
