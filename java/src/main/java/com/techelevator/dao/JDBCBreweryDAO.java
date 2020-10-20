package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Brewery;

@Component
public class JDBCBreweryDAO implements BreweryDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Communicates with database to get all breweries
	@Override
	public List<Brewery> getAllBrewerys() {
		List<Brewery> output = new ArrayList<Brewery>();
		String sql = "SELECT * FROM breweries";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			output.add(mapRowToBrewery(results));
		}
		return output;
	}

	// Communicates with database to get brewery by id
	@Override
	public Brewery getBreweryById(long id) {
		String sql = "SELECT * FROM breweries WHERE id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if (results.next()) {
			return mapRowToBrewery(results);
		} else {
			throw new RuntimeException("No brewery found");
		}
	}

	// Communicates with database to get brewery by brewer id
	@Override
	public Brewery getBreweryByBrewer(Long id) {
		String sql = "SELECT * FROM breweries WHERE brewer_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if (results.next()) {
			return mapRowToBrewery(results);
		} else {
			throw new RuntimeException("No Brewery Found");
		}
	}

	// add new brewery to the database
	@Override
	public void saveBrewery(Brewery brewery) {

		jdbcTemplate.update(
				"INSERT INTO breweries (name, address, description, image, brewery_url, phone, hours, active) VALUES (?,?,?,?,?,?,?,?)",
				brewery.getName(), brewery.getAddress(), brewery.getDescription(), brewery.getImage(),
				brewery.getBrewery_url(), brewery.getPhone(), brewery.getHours(), brewery.isActive());
	}
	// edits existing brewery in the database by brewery id
	@Override
	public void updateBrewery(Brewery brewery, long id) {

		String sqlInsert = "UPDATE breweries SET name = ?, address = ?, description = ?, image = ?, brewery_url = ?, phone = ?, hours = ?, active = ?"
				+ " WHERE id = ?";

		jdbcTemplate.update(sqlInsert, brewery.getName(), brewery.getAddress(), brewery.getDescription(),
				brewery.getImage(), brewery.getBrewery_url(), brewery.getPhone(), brewery.getHours(),
				brewery.isActive(), id);

	}
	// Communicates with database to get brewery by name
	@Override
	public Brewery getBreweryByName(String name) {
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM breweries WHERE name = ?", name);
		if (results.next()) {
			return mapRowToBrewery(results);
		} else {
			throw new RuntimeException("No brewery found");
		}
	}
	// deletes brewery in database by its id
	@Override
	public void deleteBrewery(long id) {
		jdbcTemplate.update(
				"DELETE FROM reply_review WHERE review_id IN (SELECT id FROM review WHERE beerid IN (SELECT beer_id FROM beers WHERE brewery_id = ?))",
				id);
		jdbcTemplate.update("DELETE FROM review WHERE beerid IN (SELECT beer_id FROM beers WHERE brewery_id = ?)", id);
		jdbcTemplate.update("DELETE FROM beers WHERE brewery_id = ?", id);
		jdbcTemplate.update("DELETE FROM brewer_request WHERE breweryid = ?", id);
		jdbcTemplate.update("DELETE FROM brewery_favorites WHERE brewery_id = ?", id);
		jdbcTemplate.update("DELETE FROM brewery_news WHERE breweryid = ?", id);
		jdbcTemplate.update("DELETE FROM events WHERE brewery_id = ?", id);
		jdbcTemplate.update("DELETE FROM breweries WHERE id = ?", id);

	}

	// maps SQL query to brewery object
	private Brewery mapRowToBrewery(SqlRowSet results) {
		Brewery brewery = new Brewery();
		brewery.setId(results.getLong("id"));
		brewery.setName(results.getString("name"));
		brewery.setAddress(results.getString("address"));
		brewery.setDescription(results.getString("description"));
		brewery.setBrewerId(results.getLong("brewer_id"));
		brewery.setBrewery_url(results.getString("brewery_url"));
		brewery.setPhone(results.getLong("phone"));
		brewery.setHours(results.getString("hours"));
		brewery.setActive(results.getBoolean("active"));
		brewery.setImage(results.getString("image"));
		return brewery;
	}

}
