package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.timefusion.jfxcalendar.JSON.JsonUtils;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ParticipantsEntity implements JsonEntity {

  private int id;
  private String firstName;
  private String lastName;
  private String email;

  public static final String PARTICIPANTS_ENTITY_NAME = "participants";

  private static final Gson gson = new GsonBuilder()
    .setPrettyPrinting()
    .create();

  public ParticipantsEntity() {
    // Default constructor
  }

  public ParticipantsEntity(
    int id,
    String firstName,
    String lastName,
    String email
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void addParticipantEntity(int eventId) {
    addParticipantEntityArray(
      JsonUtils.JSON_FILENAME,
      PARTICIPANTS_ENTITY_NAME,
      this.toJsonObject(),
      eventId
    );
  }

  public void addParticipantEntityArray(
    String filePath,
    String entityName,
    JsonObject newEntity,
    int eventId
  ) {
    try (FileReader fileReader = new FileReader(filePath)) {
      JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();

      for (JsonElement jsonElement : jsonArray) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject.has("events")) {
          JsonArray eventsArray = jsonObject.getAsJsonArray("events");

          for (JsonElement eventElement : eventsArray) {
            JsonObject eventObject = eventElement.getAsJsonObject();

            if (
              eventObject.has("id") &&
              eventObject.get("id").getAsInt() == eventId
            ) {
              JsonArray participantsArray = eventObject.getAsJsonArray(
                "participants"
              );

              if (participantsArray != null) {
                participantsArray.add(newEntity);
                System.out.println("Participant added to the event.");
              }
              break;
            }
          }
        }
      }

      try (FileWriter fileWriter = new FileWriter(filePath)) {
        gson.toJson(jsonArray, fileWriter);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static JsonArray participantsArrayToJsonArray(
    ParticipantsEntity[] participants
  ) {
    JsonArray jsonArray = new JsonArray();
    for (ParticipantsEntity participant : participants) {
      jsonArray.add(participant.toJsonObject());
    }
    return jsonArray;
  }

  public static ParticipantsEntity[] jsonArrayToParticipantsArray(
    JsonArray jsonArray
  ) {
    ParticipantsEntity[] participants = new ParticipantsEntity[jsonArray.size()];
    for (int i = 0; i < jsonArray.size(); i++) {
      JsonObject participantJson = jsonArray.get(i).getAsJsonObject();
      ParticipantsEntity participant = new ParticipantsEntity();

      if (participantJson.has("id")) {
        participant.setId(participantJson.get("id").getAsInt());
      }

      if (participantJson.has("first_name")) {
        participant.setFirstName(
          participantJson.get("first_name").getAsString()
        );
      }

      if (participantJson.has("last_name")) {
        participant.setLastName(participantJson.get("last_name").getAsString());
      }

      if (participantJson.has("email")) {
        participant.setEmail(participantJson.get("email").getAsString());
      }

      participants[i] = participant;
    }
    return participants;
  }

  public static ParticipantsEntity[] getParticipantsArray(int eventId) {
    try (FileReader fileReader = new FileReader(JsonUtils.JSON_FILENAME)) {
      JsonArray jsonArray = JsonParser.parseReader(fileReader).getAsJsonArray();
      ParticipantsEntity[] participants = null;

      for (JsonElement jsonElement : jsonArray) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject.has("events")) {
          JsonArray eventsArray = jsonObject.getAsJsonArray("events");

          for (JsonElement eventElement : eventsArray) {
            JsonObject eventObject = eventElement.getAsJsonObject();

            if (
              eventObject.has("id") &&
              eventObject.get("id").getAsInt() == eventId
            ) {
              JsonArray participantsArray = eventObject.getAsJsonArray(
                "participants"
              );

              if (participantsArray != null) {
                participants = jsonArrayToParticipantsArray(participantsArray);
                break;
              }
            }
          }
        }
      }
      return participants;
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + JsonUtils.JSON_FILENAME);
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    }
    return null;
  }

  @Override
  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", id);
    jsonObject.addProperty("first_name", firstName);
    jsonObject.addProperty("last_name", lastName);
    jsonObject.addProperty("email", email);
    return jsonObject;
  }

  @Override
  public String toJson() {
    return new Gson().toJson(this);
  }

  @Override
  public String toString() {
    return (
      "ParticipantsEntity{" +
      "id=" +
      id +
      ", firstName=\"" +
      firstName +
      "\", lastName=\"" +
      lastName +
      "\", email=\"" +
      email +
      "\"}"
    );
  }

  public static void main(String[] args) {
    ParticipantsEntity[] participants = ParticipantsEntity.getParticipantsArray(
      12
    );

    if (participants != null) {
      for (ParticipantsEntity participant : participants) {
        System.out.println(participant);
      }
    } else {
      System.out.println("No participants found for the specified event ID.");
    }
  }
}
