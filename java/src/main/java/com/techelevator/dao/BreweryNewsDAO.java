package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.BreweryNews;
//interface for News
public interface BreweryNewsDAO {

	public List<BreweryNews> getAllNews();
	
	public List<BreweryNews> getNewsByBreweryId(Long breweryId);
	
	public void deleteNews(Long newsId);
	
	public void updateNews(BreweryNews news);
	
	public void addNews(BreweryNews news);
	
	public BreweryNews getNewsByNewsId(Long newsId);
}
