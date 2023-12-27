package com.timefusion.model;

import java.util.Objects;

public class Calendar {

  private Long id;
  private String name;
  private Long userId;
  private Long teamId;
  private Long eventId;

  public Calendar(
    Long id,
    String name,
    Long userId,
    Long teamId,
    Long eventId
  ) {
    this.id = id;
    this.name = name;
    this.userId = userId;
    this.teamId = teamId;
    this.eventId = eventId;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getUserId() {
    return this.userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getTeamId() {
    return this.teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public Long getEventId() {
    return this.eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  @Override
  public String toString() {
    return (
      "Calendar{" +
      "id=" +
      id +
      ", name='" +
      name +
      ", userId=" +
      userId +
      ", teamId=" +
      teamId +
      ", eventId=" +
      eventId +
      "}"
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Calendar calendar = (Calendar) o;
    return (
      Objects.equals(id, calendar.id) &&
      Objects.equals(name, calendar.name) &&
      Objects.equals(userId, calendar.userId) &&
      Objects.equals(teamId, calendar.teamId) &&
      Objects.equals(eventId, calendar.eventId)
    );
  }
}
