package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.JsonObject;
import com.timefusion.jfxcalendar.JSON.JsonUtils;

public class EventsEntity {

  private int id;
  private String nature;
  private boolean isOnline;
  private String title;
  private String description;
  private String location;
  private String startTime;
  private String endTime;
  private ParticipantsEntity[] participants;

  public static final String EVENTS_ENTITY_NAME = "events";
  public static final int EVENTS_ENTITY_POSITION = 4;

  public EventsEntity() {
    // Default constructor
  }

  public EventsEntity(
    int id,
    String nature,
    boolean isOnline,
    String title,
    String description,
    String location,
    String startTime,
    String endTime,
    ParticipantsEntity[] participants
  ) {
    this.id = id;
    this.nature = nature;
    this.isOnline = isOnline;
    this.title = title;
    this.description = description;
    this.location = location;
    this.startTime = startTime;
    this.endTime = endTime;
    this.participants = participants;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNature() {
    return nature;
  }

  public void setNature(String nature) {
    this.nature = nature;
  }

  public boolean isIs_online() {
    return isOnline;
  }

  public void setIs_online(boolean isOnline) {
    this.isOnline = isOnline;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDesciption(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public ParticipantsEntity[] getParticipants() {
    return participants;
  }

  public void setParticipants(ParticipantsEntity[] participants) {
    this.participants = participants;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public void addEventEntity() {
    JsonUtils.addEntityArray(
      JsonUtils.JSON_FILENAME,
      EVENTS_ENTITY_NAME,
      this.toJsonObject()
    );
  }

  public void updateEventEntity() {
    JsonUtils.updateEntityArray(
      JsonUtils.JSON_FILENAME,
      EVENTS_ENTITY_NAME,
      this.getId(),
      this.toJsonObject()
    );
  }

  public void deleteEventEntity() {
    JsonUtils.deleteEntityArray(
      JsonUtils.JSON_FILENAME,
      EVENTS_ENTITY_NAME,
      this.getId()
    );
  }

  private JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", id);
    jsonObject.addProperty("nature", nature);
    jsonObject.addProperty("is_online", isOnline);
    jsonObject.addProperty("title", title);
    jsonObject.addProperty("description", description);
    jsonObject.addProperty("location", location);
    jsonObject.addProperty("start_time", startTime);
    jsonObject.addProperty("end_time", endTime);
    jsonObject.add(
      "participants",
      ParticipantsEntity.participantsArrayToJsonArray(participants)
    );
    return jsonObject;
  }

  @Override
  public String toString() {
    return "EventEntity{" + "id=" + id + '}';
  }

  public static void main(String[] args) {
    EventsEntity event = new EventsEntity();
    event.setId(2);
    event.setNature("nature");
    event.setIs_online(true);
    event.setTitle("title");
    event.setDesciption("description");
    event.setLocation("location");
    event.setStartTime("start_time");
    event.setEndTime("end_time");
    ParticipantsEntity participant1 = new ParticipantsEntity(
      1,
      "caca1",
      "last_name",
      "email"
    );
    ParticipantsEntity participant2 = new ParticipantsEntity(
      2,
      "cacae",
      "last_name",
      "email"
    );
    event.setParticipants(
      new ParticipantsEntity[] { participant1, participant2 }
    );
    event.deleteEventEntity();
  }
}
