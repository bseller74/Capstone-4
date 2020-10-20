package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.ReplyReview;

@Component
public class JDBCReplyReviewDAO implements ReplyReviewDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Communicates with database to get all replies
	@Override
	public List<ReplyReview> getAllReplys() {
		List<ReplyReview> output = new ArrayList<ReplyReview>();
		String sql = "SELECT * FROM reply_review";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			output.add(mapRowToReply(results));
		}
		return output;
	}

	// Communicates with database to get all replies by specific user
	@Override
	public List<ReplyReview> getReplysByUserId(Long userId) {
		List<ReplyReview> output = new ArrayList<ReplyReview>();
		String sql = "SELECT * FROM reply_review WHERE user_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			output.add(mapRowToReply(results));
		}
		return output;
	}

	// Communicates with database to get all replies by specific review
	@Override
	public List<ReplyReview> getReplysByReviewId(Long reviewId) {
		List<ReplyReview> output = new ArrayList<ReplyReview>();
		String sql = "SELECT * FROM reply_review JOIN users ON reply_review.user_id = users.user_id WHERE review_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, reviewId);
		while (results.next()) {
			output.add(mapRowToReply(results));
		}
		return output;
	}

	// creates new reply in database
	@Override
	public void addReplyReview(ReplyReview replyReview) {
		String sql = "INSERT INTO reply_review (user_id, review_id, title, reply) VALUES (?,?,?,?);";
		jdbcTemplate.update(sql, replyReview.getUserId(), replyReview.getReviewId(), replyReview.getTitle(),
				replyReview.getReply());
	}

	// deletes reply in database
	@Override
	public void deleteReplyReview(ReplyReview replyReview) {
		String sql = "DELETE FROM reply_review WHERE id = ?";
		jdbcTemplate.update(sql, replyReview.getId());
	}

	// maps data to object by using database column name in table reply_review
	private ReplyReview mapRowToReply(SqlRowSet results) {
		ReplyReview request = new ReplyReview();
		request.setId(results.getLong("id"));
		request.setUserId(results.getLong("user_id"));
		request.setReviewId(results.getLong("review_id"));
		request.setTitle(results.getString("title"));
		request.setReply(results.getString("reply"));
		try {
			request.setUsername(results.getString("username"));
		} catch (Exception e) {

		}
		return request;
	}

}
