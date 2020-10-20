package com.techelevator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.dao.BreweryNewsDAO;
import com.techelevator.model.BreweryNews;

@RestController
@CrossOrigin
public class BreweryNewsController {

	@Autowired
	public BreweryNewsDAO dao;
	
	// controller to list all news
	@RequestMapping(path = "/allNews", method = RequestMethod.GET)
	public List<BreweryNews> getAllNews() {
		return dao.getAllNews();
	}
	// controller to list news by database id
	@RequestMapping(path = "/news/{id}", method = RequestMethod.GET)
	public List<BreweryNews> newsByBreweryID(@PathVariable Long id){
		return dao.getNewsByBreweryId(id);
	}
	// controller to delete news by database id
	@RequestMapping(path = "/news/{id}", method = RequestMethod.DELETE)
	public void deleteNews(@PathVariable Long id){
		dao.deleteNews(id);
	}
	
	@RequestMapping(path = "/addNews", method = RequestMethod.POST)
	public void addNews(@RequestBody BreweryNews news) {
		dao.addNews(news);
	}
	
	@RequestMapping(path = "/updateNews", method = RequestMethod.PUT)
	public void updateNews(@RequestBody BreweryNews news) {
		dao.updateNews(news);
	}
	
	@RequestMapping(path = "/getNewsById/{newsId}", method = RequestMethod.GET)
	public BreweryNews getNewsByNewsId(@PathVariable long newsId) {
		return dao.getNewsByNewsId(newsId);
	}
	
	
	
	
}
