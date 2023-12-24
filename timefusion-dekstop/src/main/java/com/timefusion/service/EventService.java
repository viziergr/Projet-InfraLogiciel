package com.timefusion.service;

import com.timefusion.dao.EventDao;
import com.timefusion.model.Event;
import java.util.List;
import java.util.Optional;

public class EventService {

  private EventDao eventDao;

  public EventService(EventDao eventDao) {
    this.eventDao = eventDao;
  }

  public List<Event> getAllEvents() {
    return eventDao.getAllEvents();
  }

  public Optional<Event> getEventById(Long id) {
    return eventDao.getEventById(id);
  }

  public void saveEvent(Event event) {
    eventDao.saveEvent(event);
  }

  public void updateEvent(Event event) {
    eventDao.updateEvent(event);
  }

  public void deleteEvent(Long id) {
    eventDao.deleteEvent(id);
  }
}
