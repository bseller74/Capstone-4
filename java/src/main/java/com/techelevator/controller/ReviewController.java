package com.techelevator.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.dao.ReviewDAO;
import com.techelevator.model.Review;

@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
public class ReviewController {

	@Autowired
	ReviewDAO dao;

	// controller to add a new review
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/new-review", method = RequestMethod.POST)
	public void addReview(@Valid @RequestBody Review review) {
		dao.addReview(review);
	}

	// controller to delete a review
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/delete-review", method = RequestMethod.DELETE)
	public void deleteReview(@Valid @RequestBody Review review) {
		dao.deleteReview(review);
	}

	// controller to list reviews for specific beers
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/beer/{id}/reviews", method = RequestMethod.GET)
	public List<Review> reviewByBeer(@PathVariable Long id) {
		return dao.getReviewsByBeer(id);
	}

	// controller to list reviews for specific reviews
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/brewery/{id}/reviews", method = RequestMethod.GET)
	public List<Review> reviewByBrewery(@PathVariable Long id) {
		return dao.getReviewsByBrewery(id);
	}

	// controller to list reviews for specific users
	@PreAuthorize("permitAll()")
	@RequestMapping(path = "/user/{id}/reviews", method = RequestMethod.GET)
	public List<Review> getReviewsByUserId(@PathVariable Long id) {
		return dao.getReviewsByUserId(id);
	}
}
