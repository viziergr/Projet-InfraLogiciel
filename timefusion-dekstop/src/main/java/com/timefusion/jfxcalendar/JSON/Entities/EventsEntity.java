package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.timefusion.jfxcalendar.JSON.JsonUtils;

public class EventsEntity {

  private int id;
  private EventNature nature;
  private boolean isOnline;
  private String title;
  private String description;
  private String location;
  private String startTime;
  private String endTime;
  private ParticipantsEntity[] participants;

  public static final String EVENTS_ENTITY_NAME = "events";
  public static final int EVENTS_ENTITY_POSITION = 4;

  public EventsEntity() {}

  public EventsEntity(
    int id,
    EventNature nature,
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

  public EventNature getNature() {
    return nature;
  }

  public void setNature(EventNature nature) {
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

  public static boolean isJsonEventsEntityEmpty() {
    JsonElement eventsJsonElement = JsonUtils.readJsonPart(EVENTS_ENTITY_NAME);

    if (eventsJsonElement.isJsonArray()) {
      JsonArray teamJsonArray = eventsJsonElement.getAsJsonArray();
      return teamJsonArray.size() == 0;
    } else {
      return true;
    }
  }

  public static EventsEntity getEventEntityById(int id) {
    JsonElement eventsJsonElement = JsonUtils.readJsonPart(EVENTS_ENTITY_NAME);

    if (eventsJsonElement.isJsonArray()) {
      JsonArray eventsJsonArray = eventsJsonElement.getAsJsonArray();
      for (JsonElement eventElement : eventsJsonArray) {
        JsonObject eventObject = eventElement.getAsJsonObject();
        int eventId = eventObject.get("id").getAsInt();
        if (eventId == id) {
          return new EventsEntity(
            eventId,
            EventNature.valueOf(
              eventObject.get("nature").getAsString().toUpperCase()
            ),
            eventObject.get("is_online").getAsBoolean(),
            eventObject.get("title").getAsString(),
            eventObject.get("description").getAsString(),
            eventObject.get("location").getAsString(),
            eventObject.get("start_time").getAsString(),
            eventObject.get("end_time").getAsString(),
            ParticipantsEntity.jsonArrayToParticipantsArray(
              eventObject.get("participants").getAsJsonArray()
            )
          );
        }
      }
    }

    return null;
  }

  public static JsonArray getAllEventEntities() {
    JsonElement eventsJsonElement = JsonUtils.readJsonPart(EVENTS_ENTITY_NAME);

    if (eventsJsonElement.isJsonArray()) {
      return eventsJsonElement.getAsJsonArray();
    }

    return null;
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

  public static void deleteEventEntity(int eventId) {
    JsonUtils.deleteEntityArray(
      JsonUtils.JSON_FILENAME,
      EVENTS_ENTITY_NAME,
      eventId
    );
  }

  public static void deleteAllEventEntities() {
    JsonArray eventsJsonArray = getAllEventEntities();
    for (JsonElement eventElement : eventsJsonArray) {
      JsonObject eventObject = eventElement.getAsJsonObject();
      int eventId = eventObject.get("id").getAsInt();
      EventsEntity.deleteEventEntity(eventId);
    }
  }

  private JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", id);
    jsonObject.addProperty("nature", nature.toString());
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
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("Event ID: ").append(id).append("\n");
    stringBuilder.append("Nature: ").append(nature).append("\n");
    stringBuilder.append("Is Online: ").append(isOnline).append("\n");
    stringBuilder.append("Title: ").append(title).append("\n");
    stringBuilder.append("Description: ").append(description).append("\n");
    stringBuilder.append("Location: ").append(location).append("\n");
    stringBuilder.append("Start Time: ").append(startTime).append("\n");
    stringBuilder.append("End Time: ").append(endTime).append("\n");
    stringBuilder.append("Participants: \n");
    for (ParticipantsEntity participant : participants) {
      stringBuilder.append(participant.toString()).append("\n");
    }

    return stringBuilder.toString();
  }
}