package model;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String description;
    private Boolean isPersonal;

    // Constructeurs, getters, setters

    public Event() {
    }

    // autres constructeurs, getters et setters...
}
