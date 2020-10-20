package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.PendingBreweryRequest;

@Component
public class JDBCPendingBreweryRequestDAO implements PendingBreweryRequestDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Communicates with database to get all brewery requests
	@Override
	public List<PendingBreweryRequest> getAllRequest() {
		List<PendingBreweryRequest> output = new ArrayList<PendingBreweryRequest>();
		String sql = "SELECT * FROM pending_brewery_request";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			output.add(mapRowToRequest(results));
		}
		return output;
	}
	// creates new brewery request in database
	@Override
	public void addRequest(PendingBreweryRequest request) {
		String sql = "INSERT INTO pending_brewery_request (user_id , name, address, description, image, brewery_url, phone, hours)"
				+ "VALUES (?,?,?,?,?,?,?,?)"; 
		jdbcTemplate.update(sql, request.getUserId(), request.getName(),request.getAddress(),request.getDescription(),
				request.getImage(),request.getBrewery_url(),request.getPhone(), request.getHours());
	}
	// edits new brewery request in database
	@Override
	public void updateRequest(PendingBreweryRequest request, long id) {
		String sqlInsert = "UPDATE pending_brewery_request set processed = ? WHERE id = ?";
		jdbcTemplate.update(sqlInsert, request.isProcessed(), request.getId());
		
	}
	// maps data to object by using database column name in table pending_brewery_request
	private PendingBreweryRequest mapRowToRequest(SqlRowSet results) {
		PendingBreweryRequest request = new PendingBreweryRequest();
		request.setId(results.getLong("id"));
		request.setUserId(results.getInt("user_id"));
		request.setName(results.getString("name"));
		request.setAddress(results.getString("address"));
		request.setDescription(results.getString("description"));
		request.setImage(results.getString("image"));
		request.setBrewery_url(results.getString("brewery_url"));
		request.setPhone(results.getLong("phone"));
		request.setHours(results.getString("hours"));
		request.setProcessed(results.getBoolean("processed"));

		return request;
	}


}
