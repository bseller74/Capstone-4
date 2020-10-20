package com.techelevator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.techelevator.model.BrewerRequest;

@Component
public class JDBCBrewerRequestDAO implements BrewerRequestDAO {


@Autowired
private JdbcTemplate jdbcTemplate;

	// Changes processed status in database from the database id
	@Override
	public void switchProcessedStatus(BrewerRequest request) {
		String sql = "UPDATE brewer_request SET processed = ? WHERE id = ?";
		jdbcTemplate.update(sql, request.isProcessed(), request.getId() );
	}

	// set brewer id to their own brewery in the database
	@Override
	public void updateBrewerIdForBrewery(BrewerRequest request) {
		String sql = "Update breweries SET brewer_id = ? WHERE id = ?";
		jdbcTemplate.update(sql, request.getUserId(), request.getBreweryId() );
		
	}


	
	


}
