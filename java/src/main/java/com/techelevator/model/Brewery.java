package com.techelevator.model;
//model for brewery
public class Brewery {

	private long id;
	private String name;
	private String address;
	private String description;
	private long brewerId;
	private String brewery_url;
	private Long phone;
	private String hours;
	private boolean active;
	private String image;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getBrewerId() {
		return brewerId;
	}
	public void setBrewerId(long brewerId) {
		this.brewerId = brewerId;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Brewery [id=" + id + ", name=" + name + ", address=" + address + ", description=" + description
				+ ", brewerId=" + brewerId + ", brewery_url=" + brewery_url + ", phone=" + phone + ", hours=" + hours
				+ ", active=" + active + ", image=" + image + "]";
	}

	
}
