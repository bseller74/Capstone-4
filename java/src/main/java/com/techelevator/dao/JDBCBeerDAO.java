package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Beer;

@Component
public class JDBCBeerDAO implements BeerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JDBCBeerDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
// Communicates with database to get all beers
	@Override
	public List<Beer> getAllBeer() {

		List<Beer> allBeer = new ArrayList<>();
		String sqlSelectAllBeer = "SELECT * FROM beers";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectAllBeer);

		while (result.next()) {
			allBeer.add(mapRowToBeer(result));
		}

		return allBeer;

	}
	// Communicates with database to get all beers from specific brewery
	@Override
	public List<Beer> BeerByBrewery(Long id) {
		List<Beer> brewBeers = new ArrayList<>();
		String sqlBrewBeer = "SELECT * FROM beers WHERE brewery_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlBrewBeer, id);

		while (result.next()) {
			brewBeers.add(mapRowToBeer(result));
		}
		return brewBeers;
	}
	// Communicates with database to get beer from specific beer database id
	@Override
	public Beer getBeerById(Long id) {

		Beer beer = new Beer();
		String sqlSelectBeerByName = "SELECT * FROM beers where beer_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectBeerByName, id);

		if (result.next()) {
			beer = mapRowToBeer(result);
		}

		return beer;
	}
	// deletes beer from specific beer database id
	@Override
	public void deleteBeer(Long id) {
		
		jdbcTemplate.update(
				"DELETE FROM reply_review WHERE review_id IN (SELECT id FROM review WHERE beerid = ?)",
				id);
		jdbcTemplate.update("DELETE FROM review WHERE beerid = ?", id);
		jdbcTemplate.update("DELETE FROM beers WHERE beer_id = ?", id);
		
	}
	// creates new beer in the database
	@Override
	public void saveBeer(Beer beer) {
		jdbcTemplate.update(
				"INSERT INTO beers (beer_name, brewery_id, beer_type, description, picture, abv, ibu, available) VALUES (?,?,?,?,?,?,?,?)",
				beer.getName(), beer.getBreweryId(), beer.getType(), beer.getDescription(), beer.getImgUrl(),
				beer.getAbv(), beer.getIbu(), beer.isCurrent());

	}
	// changes existing beer in the database
	@Override
	public void updateBeer(Beer beer, long id) {

		String sql = "UPDATE beers SET beer_name = ?, beer_type = ?, description = ?, picture = ?, abv = ?, ibu = ?, available = ? WHERE beer_id = ?";

		jdbcTemplate.update(sql, beer.getName(), beer.getType(), beer.getDescription(), beer.getImgUrl(), beer.getAbv(),
				beer.getIbu(), beer.isCurrent(), id);

	}
	// changes existing  rating of beer in the database
	@Override
	public void updateRating(Long id) {
		String sql = "SELECT AVG(rating) FROM review WHERE beerId = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		Double rounded = 0.0;
		while (result.next()) {
			Double average = result.getDouble("avg");
			rounded = DoubleRounder.round(average, 1);
		}
		String update = "UPDATE beers SET rating = ? WHERE beer_id = ?";
		jdbcTemplate.update(update, rounded, id);

	}
	// Communicates with database to get top rated beers from specific beer rating in database
	@Override
	public List<Beer> topFiveBeers() {
		List<Beer> topFiveList = new ArrayList<Beer>();
		String sqlInsert = "SELECT * FROM beers where rating IS NOT NULL AND rating > 2.0 ORDER BY rating DESC LIMIT 5";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlInsert);

		while (result.next()) {
			topFiveList.add(mapRowToBeer(result));
		}

		return topFiveList;
	}
	// maps data to java object by using database column name in table beers
	private Beer mapRowToBeer(SqlRowSet row) {

		Beer beer = new Beer();

		beer.setId(row.getLong("beer_id"));
		beer.setName(row.getString("beer_name"));
		beer.setAbv(row.getDouble("abv"));
		beer.setIbu(row.getDouble("ibu"));
		beer.setBreweryId(row.getLong("brewery_id"));
		beer.setType(row.getString("beer_type"));
		beer.setDescription(row.getString("description"));
		beer.setRating(row.getDouble("rating"));
		beer.setCurrent(row.getBoolean("available"));
		beer.setImgUrl(row.getString("picture"));

		return beer;

	}

}
