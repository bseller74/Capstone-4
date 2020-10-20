package com.techelevator.dao;

import com.techelevator.model.BrewerRequest;
import com.techelevator.model.User;
import com.techelevator.model.UserRoleChange;
import java.util.List;
//interface for user
public interface UserDAO {

	List<User> findAll();

	User getUserById(Long userId);

	User findByUsername(String username);

	int findIdByUsername(String username);

	boolean create(String username, String password, String role);
	
	public void updateUserRole(UserRoleChange user);
	
	public void newBrewer(long id, BrewerRequest user);
	
	public List<BrewerRequest> getAllRequests();
	
	public void deleteUserById(long id);
	
}
