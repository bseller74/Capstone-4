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
import com.techelevator.dao.ReplyReviewDAO;
import com.techelevator.model.ReplyReview;

@CrossOrigin
@RestController
public class ReplyReviewController {

	@Autowired
	ReplyReviewDAO dao;

	// controller to list all replies
	@RequestMapping(path = "/replies", method = RequestMethod.GET)
	public List<ReplyReview> getAllReplies() {
		return dao.getAllReplys();
	}

	// controller to delete reply
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "/replies", method = RequestMethod.DELETE)
	public String deleteReplyReview(@RequestBody ReplyReview replyReview) {
		dao.deleteReplyReview(replyReview);
		return "Reply Deleted";
	}
	// controller to add a new reply		
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/replies", method = RequestMethod.POST)
	public void addReplyReview(@Valid @RequestBody ReplyReview replyReview) {
		dao.addReplyReview(replyReview);
	}
	// pulls all replies by user database id
	@RequestMapping(path = "/user/{userId}/replies", method = RequestMethod.GET)
	public List<ReplyReview> getReplysByUserId(@PathVariable Long userId) {
		return dao.getReplysByUserId(userId);
	}
	// pulls all replies by review's database id
	@RequestMapping(path = "/review/{reviewId}/replies", method = RequestMethod.GET)
	public List<ReplyReview> getReplysByReviewId(@PathVariable Long reviewId) {
		return dao.getReplysByReviewId(reviewId);
	}

}
