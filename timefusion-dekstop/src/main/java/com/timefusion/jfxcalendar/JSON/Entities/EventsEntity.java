package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.timefusion.jfxcalendar.JSON.JsonUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventsEntity {

  private int id;
  private EventNature nature;
  private boolean isOnline;
  private String title;
  private String description;
  private String location;
  private LocalDateTime startTime;
  private LocalDateTime endTime;
  private ParticipantsEntity[] participants;

  private static int nextNegativeId = -1;
  public static boolean isOffline = true;

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
    LocalDateTime startTime,
    LocalDateTime endTime,
    ParticipantsEntity[] participants
  ) {
    if (isOffline) {
      resetNextNegativeId();
    }
    this.id = !isOffline ? id : generateNextOfflineId();
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

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public static int generateNextOfflineId() {
    return nextNegativeId--;
  }

  public static void resetNextNegativeId() {
    nextNegativeId = getLastOfflineId();
  }

  public static int getLastOfflineId() {
    JsonElement eventsJsonElement = JsonUtils.readJsonPart(EVENTS_ENTITY_NAME);

    if (eventsJsonElement.isJsonArray()) {
      JsonArray eventsJsonArray = eventsJsonElement.getAsJsonArray();
      int lastOfflineId = -1;
      for (JsonElement eventElement : eventsJsonArray) {
        JsonObject eventObject = eventElement.getAsJsonObject();
        int eventId = eventObject.get("id").getAsInt();
        if (eventId < 0 && eventId < lastOfflineId) {
          lastOfflineId = eventId;
        }
      }
      nextNegativeId = lastOfflineId;
      return lastOfflineId;
    }

    return -1;
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
            parseLocalDateTime(eventObject.get("start_time").getAsString()),
            parseLocalDateTime(eventObject.get("end_time").getAsString()),
            ParticipantsEntity.jsonArrayToParticipantsArray(
              eventObject.get("participants").getAsJsonArray()
            )
          );
        }
      }
    }

    return null;
  }

  private static LocalDateTime parseLocalDateTime(String dateTimeString) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm"
    );
    return LocalDateTime.parse(dateTimeString, formatter);
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

    // Format start and end times using DateTimeFormatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss"
    );
    String formattedStartTime = startTime.format(formatter);
    String formattedEndTime = endTime.format(formatter);

    jsonObject.addProperty("start_time", formattedStartTime);
    jsonObject.addProperty("end_time", formattedEndTime);

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

  public static void main(String[] args) {
    EventsEntity event = new EventsEntity(
      1,
      EventNature.ADDED,
      true,
      "Test Event",
      "Test Description",
      "Test Location",
      LocalDateTime.now(),
      LocalDateTime.now(),
      new ParticipantsEntity[] { new ParticipantsEntity() }
    );
    event.addEventEntity();
  }
}
