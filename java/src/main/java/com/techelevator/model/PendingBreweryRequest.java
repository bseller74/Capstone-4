package com.techelevator.model;
//model for pending brewery request
public class PendingBreweryRequest {
	
	private long id;
	private String brewerUsername;
	private int userId;
	private String name;
	private String address;
	private String description;
	private String image;
	private String brewery_url;
	private Long phone;
	private String hours;
	private boolean processed;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrewerUsername() {
		return brewerUsername;
	}
	public void setBrewerUsername(String brewerUsername) {
		this.brewerUsername = brewerUsername;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrewery_url() {
		return brewery_url;
	}
	public void setBrewery_url(String brewery_url) {
		this.brewery_url = brewery_url;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	} 

}
