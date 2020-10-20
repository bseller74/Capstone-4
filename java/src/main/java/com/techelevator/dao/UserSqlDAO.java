package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.techelevator.model.BrewerRequest;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.User;
import com.techelevator.model.UserRoleChange;

@Service
public class UserSqlDAO implements UserDAO {
	RegisterUserDTO registeredUser = new RegisterUserDTO();

	private JdbcTemplate jdbcTemplate;

	public UserSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Communicates with database to get user by username
	@Override
	public int findIdByUsername(String username) {
		return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
	}

	// Communicates with database to get username by user id
	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId " + userId + " was not found.");
		}
	}

	// Communicates with database to get all users
	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		String sql = "select * from users";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			User user = mapRowToUser(results);
			users.add(user);
		}

		return users;
	}

	// Communicates with database to get user by username
	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		for (User user : this.findAll()) {
			if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
				return user;
			}
		}
		throw new UsernameNotFoundException("User " + username + " was not found.");
	}

	// add new user to database
	@Override
	public boolean create(String username, String password, String role) {
		boolean userCreated = false;

		// create user
		String insertUser = "insert into users (username,password_hash,role) values(?,?,?)";
		String password_hash = new BCryptPasswordEncoder().encode(password);
		String ssRole = "ROLE_" + role.toUpperCase();

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		String id_column = "user_id";
		userCreated = jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(insertUser, new String[] { id_column });
			ps.setString(1, username);
			ps.setString(2, password_hash);
			ps.setString(3, ssRole);
			return ps;
		}, keyHolder) == 1;
		int newUserId = (int) keyHolder.getKeys().get(id_column);
		return userCreated;
	}

	// changes role for each user in database by user id
	@Override
	public void updateUserRole(UserRoleChange user) {
		String sqlInsert = "UPDATE users SET role = ?  WHERE user_id = ?";
		jdbcTemplate.update(sqlInsert, user.getNewRole(), user.getUserId());

	}

	// creates new brewer request for users to become brewers
	@Override
	public void newBrewer(long breweryId, BrewerRequest user) {
		String sqlInsert = "INSERT INTO brewer_request (user_id, username, breweryId, processed) VALUES (?, ?, ?, false)";
		jdbcTemplate.update(sqlInsert, user.getUserId(), user.getUsername(), breweryId);

	}

	// Communicates with database to list all brewer requests in database
	@Override
	public List<BrewerRequest> getAllRequests() {
		List<BrewerRequest> output = new ArrayList<>();
		String sql = "SELECT * FROM brewer_request";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			BrewerRequest request = mapRowToBrewerRequest(results);
			output.add(request);
		}
		return output;
	}

	// deletes user by user id
	@Override
	public void deleteUserById(long id) {
		jdbcTemplate.update("DELETE FROM reply_review WHERE user_id = ?", id);
		jdbcTemplate.update("DELETE FROM review WHERE userid = ?", id);
		jdbcTemplate.update("DELETE FROM pending_brewery_request WHERE user_id = ?", id);
		jdbcTemplate.update("UPDATE breweries SET brewer_id = null WHERE brewer_id = ?;", id);
		jdbcTemplate.update("DELETE FROM brewer_request WHERE user_id = ?", id);
		jdbcTemplate.update("DELETE FROM brewery_favorites WHERE user_id = ?", id);
		jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
	}

	// maps data to object by using database column name in table users
	private User mapRowToUser(SqlRowSet rs) {
		User user = new User();
		user.setId(rs.getLong("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password_hash"));
		user.setAuthorities(rs.getString("role"));
		user.setActivated(true);
		return user;
	}

	// maps data to object by using database column name in table brewer_request
	private BrewerRequest mapRowToBrewerRequest(SqlRowSet results) {
		BrewerRequest request = new BrewerRequest();
		request.setId(results.getLong("id"));
		request.setUserId(results.getInt("user_id"));
		request.setUsername(results.getString("username"));
		request.setBreweryId(results.getInt("breweryId"));
		request.setProcessed(results.getBoolean("processed"));
		return request;
	}

}
