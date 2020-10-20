package com.techelevator.model;
//model for changing user role
public class UserRoleChange {
	
	private long userId;
	private String newRole;
	

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getNewRole() {
		return newRole;
	}
	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}
	
	

}
