package com.techelevator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.techelevator.dao.EventsDAO;
import com.techelevator.model.Events;

@CrossOrigin
@RestController
public class EventsController {

	@Autowired
	private EventsDAO dao;

	// pulls events by database id
	@RequestMapping(path = "/breweryEvents/{id}", method = RequestMethod.GET)
	public List<Events> eventsByBreweryId(@PathVariable long id) {
		return dao.getEventsByBreweryId(id);
	}

	// deletes events by database id
	@RequestMapping(path = "/deleteEvent/{id}", method = RequestMethod.DELETE)
	public void deleteEvent(@PathVariable Long id) {
		dao.deleteEvent(id);
	}

	// controller that creates events
	@RequestMapping(path = "/newEvent", method = RequestMethod.POST)
	public void createEvent(@RequestBody Events event) {
		dao.createEvent(event);
	}

	// controller that edits events
	@RequestMapping(path = "/updateEvent", method = RequestMethod.PUT)
	public void updateEvent(@RequestBody Events event) {
		dao.updateEvent(event);
	}
	// controller that pulls all events
	@RequestMapping(path = "/events", method = RequestMethod.GET)
	public List<Events> getAllEvents() {
		List<Events> allEvents = dao.getAllEvents();
		return allEvents;
	}
	// controller that pull events by database id
	@RequestMapping(path = "/breweryEvents/event/{id}", method = RequestMethod.GET)
	public Events getEventByEventId(@PathVariable Long id) {
		return dao.getEventByEventId(id);
	}

}
