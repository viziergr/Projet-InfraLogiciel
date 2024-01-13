package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.timefusion.jfxcalendar.JSON.JsonUtils;

public class ParticipantsEntity implements JsonEntity {

  private int id;
  private String firstName;
  private String lastName;
  private String email;

  public static final String PARTICIPANTS_ENTITY_NAME = "participants";

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

  public void addParticipantEntity() {
    JsonUtils.addEntityArray(
      JsonUtils.JSON_FILENAME,
      PARTICIPANTS_ENTITY_NAME,
      this.toJsonObject()
    );
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
    return "ParticipantsEntity{" + "id=" + id + '}';
  }

  public static void main(String[] args) {
    ParticipantsEntity participantsEntity = new ParticipantsEntity(
      1,
      "John",
      "Doe",
      "John@doe.com"
    );
    participantsEntity.addParticipantEntity();
  }
}
