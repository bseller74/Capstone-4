package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.Review;
//interface for reviews
public interface ReviewDAO {

	public void addReview(Review review);
	
	public void deleteReview(Review review);
	
	public List<Review> getReviewsByBeer(Long BeerId);
	
	public List<Review> getReviewsByBrewery(Long BreweryId);
	
	public List<Review> getReviewsByUserId(Long id);
	
	
}
