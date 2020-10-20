package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Favorite;

@Component
public class JDBCFavoriteDAO implements FavoriteDAO {

	@Autowired
	JdbcTemplate jdbc;
	
	// add favorites by user id and brewery id
	@Override
	public void addFavorite(Long user_id, Long brewery_id) {
		String sql = "INSERT INTO brewery_favorites (user_id, brewery_id) VALUES (?, ?)";
		jdbc.update(sql, user_id, brewery_id);
	}
	// deletes favorites by user id and brewery id
	@Override
	public void deleteFavorite(Long user_id, Long brewery_id) {
		String sql = "DELETE FROM brewery_favorites WHERE user_id = ? AND brewery_id = ?";
		jdbc.update(sql, user_id, brewery_id);
	}
	// maps data to object by using database column name in table brewery_favorites
	@Override
	public List<Favorite> getFavorites(Long user_id) {
		List<Favorite> list = new ArrayList<Favorite>();
		String sql = "SELECT * FROM brewery_favorites WHERE user_id = ?";
		SqlRowSet result = jdbc.queryForRowSet(sql, user_id);
		while (result.next()) {
			Favorite favorite = new Favorite();
			favorite.setUser_id(result.getLong("user_id"));
			favorite.setBrewery_id(result.getLong("brewery_id"));
			list.add(favorite);
		}
		return list;
	}

		
}
