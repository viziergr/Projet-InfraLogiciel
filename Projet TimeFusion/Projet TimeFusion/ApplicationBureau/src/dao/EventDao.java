package dao;

import model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventDao {

    private static EventDao instance;
    private List<Event> events;

    private EventDao() {
        events = new ArrayList<>(); // Initialize the events list in the constructor
    }

    public static EventDao getInstance() {
        if (instance == null) {
            instance = new EventDao(); // Create the instance if it doesn't exist
        }
        return instance;
    }

    public List<Event> getAllEvents() {
        // Code to retrieve all events
        return events;
    }

    public Optional<Event> getEventById(Long id) {
        // Code to retrieve an event by its ID
        return Optional.empty();
    }

    public void saveEvent(Event event) {
        // Code to save an event to the database
        events.add(event);
    }

    public void updateEvent(Event event) {
        // Code to update an event
    }

    public void deleteEvent(Long id) {
        // Code to delete an event
    }
}
