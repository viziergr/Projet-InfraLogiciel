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
  private User creator;

  // Constructeurs, getters, setters

  public Event(
    Long id,
    String title,
    LocalDateTime startTime,
    LocalDateTime endTime,
    String location,
    String description,
    Boolean isPersonal,
    User creator
  ) {
    this.id = id;
    this.title = title;
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
    this.description = description;
    this.isPersonal = isPersonal;
    this.creator = creator;
  }

  // autres constructeurs, getters et setters...

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getStartTime() {
    return this.startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return this.endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getIsPersonal() {
    return this.isPersonal;
  }

  public void setIsPersonal(Boolean isPersonal) {
    this.isPersonal = isPersonal;
  }

  public User getCreator() {
    return this.creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

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
      ", isPersonal='" +
      isPersonal +
      ", creator='" +
      creator +
      "}"
    );
  }
}
