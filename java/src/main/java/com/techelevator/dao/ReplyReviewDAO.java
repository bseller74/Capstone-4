package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.ReplyReview;
//interface for replies to reviews
public interface ReplyReviewDAO {

	public List<ReplyReview> getAllReplys();

	public List<ReplyReview> getReplysByUserId(Long userId);

	public List<ReplyReview> getReplysByReviewId(Long reviewId);

	public void addReplyReview(ReplyReview replyReview);

	public void deleteReplyReview(ReplyReview replyReview);

}
