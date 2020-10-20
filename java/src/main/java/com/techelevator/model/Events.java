package com.techelevator.model;
//model for brewery events
public class Events {

	private long event_id;
	private long brewery_id;
	private String event_title;
	private String event_date;
	private String description;
	private String picture;
	private String brewery_name;
	
	
	
	public String getBrewery_name() {
		return brewery_name;
	}
	public void setBrewery_name(String brewery_name) {
		this.brewery_name = brewery_name;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}
	
	
	public long getBrewery_id() {
		return brewery_id;
	}
	public void setBrewery_id(long brewery_id) {
		this.brewery_id = brewery_id;
	}
	public String getEvent_date() {
		return event_date;
	}
	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
