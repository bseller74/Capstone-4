package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.BreweryNews;

@Component
public class JDBCBreweryNewsDAO implements BreweryNewsDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// Communicates with database to get all news
	@Override
	public List<BreweryNews> getAllNews() {
		List<BreweryNews> allNews = new ArrayList<>();
		String sqlInsert = "SELECT * from brewery_news " + 
				"JOIN breweries ON brewery_news.breweryId = breweries.id";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInsert);
		while(results.next()) {
			BreweryNews news = mapRowToBreweryNews(results);
			allNews.add(news);
		}
		return allNews;
	}
	// Communicates with database to get all news by specific brewery id
	@Override
	public List<BreweryNews> getNewsByBreweryId(Long breweryId) {
		List<BreweryNews> newsList = new ArrayList<>();
		String sqlInsert = "Select * from brewery_news WHERE breweryId = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlInsert, breweryId); 
		
		while(results.next()) {
			BreweryNews news = mapRowToBreweryNews(results);
			
			newsList.add(news);
		}
		return newsList;
	}
	// deletes news by news id
	@Override
	public void deleteNews(Long newsId) {
		String sqlInsert = "DELETE FROM brewery_news WHERE newsid = ?";
		jdbcTemplate.update(sqlInsert, newsId);
		
	}
	// edits news in database
	@Override
	public void updateNews(BreweryNews news) {
		String sqlInsert = "UPDATE brewery_news SET newstitle = ?, body = ?, newsimageurl =? where newsid = ?";
		jdbcTemplate.update(sqlInsert, news.getNewstitle(), news.getBody(), news.getNewsImageUrl(), news.getNewsId());
		
	}
	// creates news in database
	@Override
	public void addNews(BreweryNews news) {
		String sql = "INSERT INTO brewery_news (breweryId, newsTitle, body, newsImageurl) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, news.getBreweryId(), news.getNewstitle(), news.getBody(), news.getNewsImageUrl());
	}
		
	@Override
	public BreweryNews getNewsByNewsId(Long newsId) {
		BreweryNews news = new BreweryNews();
		String sqlInsert = "SELECT * from brewery_news where newsid = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlInsert, newsId);
		while(result.next()) {
			news = mapRowToBreweryNews(result);
		}
		return news;
	}
	
	// maps data to object by using database column name in table brewery_news
	public BreweryNews mapRowToBreweryNews(SqlRowSet results) {
		BreweryNews news = new BreweryNews();
		news.setNewsId(results.getLong("newsid"));
		news.setBreweryId(results.getLong("breweryId"));
		news.setNewstitle(results.getString("newsTitle"));
		news.setBody(results.getString("body"));
		news.setNewsImageUrl(results.getString("newsImageUrl"));
		try {
			news.setBreweryName(results.getString("name"));
		} catch (Exception e){
			
		}
		return news;
	}

}
