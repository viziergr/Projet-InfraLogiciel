package com.timefusion.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an event in the application.
 */
public class Event {

  private Long id;
  private String title;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private String location;
  private String description;
  private Boolean isPrivate;
  private User creator;

  /**
   * Constructor for Event
   * @param id
   * @param title
   * @param startTime
   * @param endTime
   * @param location
   * @param description
   * @param isPrivate
   * @param creator
   */
  public Event(
    Long id,
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String location,
    String description,
    Boolean isPrivate,
    User creator
  ) {
    this.id = id;
    this.title = title;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
    this.description = description;
    this.isPrivate = isPrivate;
    this.creator = creator;
  }

  /**
   * Constructor for Event
   * @param title
   * @param startTime
   * @param endTime
   * @param description
   * @param isPrivate
   * @param creator
   */
  public Event(
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String location,
    String description,
    Boolean isPrivate,
    User creator
  ) {
    this(
      null,
      title,
      startTime,
      endTime,
      location,
      description,
      isPrivate,
      creator
    );
  }

  /**
   * Constructor for Event
   * @param title
   * @param startTime
   * @param endTime
   * @param description
   * @param isPrivate
   * @param creator
   */
  public Event(
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String description,
    Boolean isPrivate,
    User creator
  ) {
    this(
      null,
      title,
      startTime,
      endTime,
      null,
      description,
      isPrivate,
      creator
    );
  }

  /**
   * Constructor for Event
   * @param title
   * @param startTime
   * @param endTime
   * @param isPrivate
   * @param creator
   */
  public Event(
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    Boolean isPrivate,
    User creator
  ) {
    this(null, title, startTime, endTime, null, null, isPrivate, creator);
  }

  /**
   * Constructor for Event
   * @param title
   * @param startTime
   * @param endTime
   * @param location
   * @param isPrivate
   * @param creator
   */
  public Event(
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    User creator,
    String location,
    Boolean isPrivate
  ) {
    this(null, title, startTime, endTime, location, null, isPrivate, creator);
  }

  /**
   * Returns the ID of the event.
   *
   * @return the ID of the event
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Sets the ID of the event.
   *
   * @param id the ID of the event
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Returns the title of the event.
   *
   * @return the title of the event
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title of the event.
   *
   * @param title the title of the event
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the start time of the event.
   *
   * @return the start time of the event
   */
  public LocalDateTime getStartTime() {
    return this.startTime;
  }

  /**
   * Sets the start time of the event.
   *
   * @param startTime the start time of the event
   */
  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  /**
   * Returns the end time of the event.
   *
   * @return the end time of the event
   */
  public LocalDateTime getEndTime() {
    return this.endTime;
  }

  /**
   * Sets the end time of the event.
   *
   * @param endTime the end time of the event
   */
  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  /**
   * Returns the location of the event.
   *
   * @return the location of the event
   */
  public String getLocation() {
    return this.location;
  }

  /**
   * Sets the location of the event.
   *
   * @param location the location of the event
   */
  public void setLocation(String location) {
    this.location = location;
  }

  /**
   * Returns the description of the event.
   *
   * @return the description of the event
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Sets the description of the event.
   *
   * @param description the description of the event
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns whether the event is private or not.
   *
   * @return whether the event is private or not
   */
  public Boolean getIsPersonal() {
    return this.isPrivate;
  }

  /**
   * Sets whether the event is private or not.
   *
   * @param isPersonal whether the event is private or not
   */
  public void setIsPersonal(Boolean isPersonal) {
    this.isPrivate = isPersonal;
  }

  /**
   * Returns the creator of the event.
   *
   * @return the creator of the event
   */
  public User getCreator() {
    return this.creator;
  }

  /**
   * Sets the creator of the event.
   *
   * @param creator the creator of the event
   */
  public void setCreator(User creator) {
    this.creator = creator;
  }

  /**
   * Returns a string representation of the event.
   *
   * @return a string representation of the event
   */
  @Override
  public String toString() {
    return (
      "Event{" +
      "id=" +
      id +
      ", title='" +
      title +
      ", startTime='" +
      startTime +
      ", endTime='" +
      endTime +
      ", location='" +
      location +
      ", description='" +
      description +
      ", isPrivate='" +
      isPrivate +
      ", creator='" +
      creator +
      "}"
    );
  }

  /**
   * Returns whether the event is equal to another object.
   *
   * @param o the object to compare to
   * @return whether the event is equal to another object
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return (
      Objects.equals(id, event.id) &&
      Objects.equals(title, event.title) &&
      Objects.equals(startTime, event.startTime) &&
      Objects.equals(endTime, event.endTime) &&
      Objects.equals(location, event.location) &&
      Objects.equals(description, event.description) &&
      Objects.equals(isPrivate, event.isPrivate) &&
      Objects.equals(creator, event.creator)
    );
  }
}
