package com.techelevator.model;
//model for brewer request
public class BrewerRequest {

	private long id;
	private int userId;
	private String username;
	private int breweryId;
	private boolean processed;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getBreweryId() {
		return breweryId;
	}
	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}
	public boolean isProcessed() {

		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
	
	
}
