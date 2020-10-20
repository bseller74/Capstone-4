package com.techelevator.model;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//model for reviews
public class Review {

	public Long id;
	@NotBlank
	public String title;
	@NotBlank
	public String overall;
	public String color;
	public String taste;
	public String head;
	public String smell;
	public Long beerId;
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	public int rating;
	public Long userId;
	public String reviewImgUrl;
	public Date reviewDate;
	public Long breweryId;
	
	
	public Long getBreweryId() {
		return breweryId;
	}
	public void setBreweryId(Long breweryId) {
		this.breweryId = breweryId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverall() {
		return overall;
	}
	public void setOverall(String overall) {
		this.overall = overall;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getSmell() {
		return smell;
	}
	public void setSmell(String smell) {
		this.smell = smell;
	}
	public Long getBeerId() {
		return beerId;
	}
	public void setBeerId(Long beerId) {
		this.beerId = beerId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getReviewImgUrl() {
		return reviewImgUrl;
	}
	public void setReviewImgUrl(String reviewImgUrl) {
		this.reviewImgUrl = reviewImgUrl;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	
	
}
