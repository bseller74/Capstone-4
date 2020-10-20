package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.Events;
//interface for events
public interface EventsDAO {

	public List<Events> getAllEvents();

	public void createEvent(Events event);

	public List<Events> getEventsByBreweryId(Long breweryId);
	
	public Events getEventByEventId(Long id);

	public void updateEvent(Events event);

	public void deleteEvent(Long id);

}
