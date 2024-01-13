package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.timefusion.jfxcalendar.JSON.JsonUtils;
import java.time.LocalDateTime;

public class InformationEntity implements JsonEntity {

  public static final String INFORMATION_ENTITY_NAME = "information";
  public static final int INFORMATION_ENTITY_POSITION = 0;
  private String lastUpdated;
  private String lastSynced;

  public InformationEntity() {}

  public InformationEntity(String lastUpdated, String lastSynced) {
    this.lastUpdated = lastUpdated;
    this.lastSynced = lastSynced;
  }

  public String getLast_updated() {
    return lastUpdated;
  }

  public void setLast_updated() {
    this.lastUpdated = LocalDateTime.now().toString();
  }

  public String getLast_synced() {
    return lastSynced;
  }

  public void setLast_synced() {
    this.lastSynced = LocalDateTime.now().toString();
  }

  public void getInformation() {
    System.out.println("Last updated: " + lastUpdated);
    System.out.println("Last synced: " + lastSynced);
  }

  @Override
  public String toString() {
    return (
      "InformationEntity{" +
      "last_updated=" +
      lastUpdated +
      ", last_synced=" +
      lastSynced +
      '}'
    );
  }

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("last_updated", lastUpdated);
    jsonObject.addProperty("last_synced", lastSynced);
    return jsonObject;
  }

  public void addInformationEntity() {
    JsonElement existingInformation = JsonUtils.readJsonPart(
      INFORMATION_ENTITY_NAME
    );

    if (
      existingInformation != null &&
      existingInformation.getAsJsonObject().entrySet().isEmpty()
    ) {
      JsonUtils.addEntityElement(
        JsonUtils.JSON_FILENAME,
        INFORMATION_ENTITY_NAME,
        this.toJsonObject(),
        INFORMATION_ENTITY_POSITION
      );
      System.out.println("Information entity added successfully.");
    } else {
      System.out.println(
        "Information entity is not empty. Cannot add a new entity."
      );
    }
  }

  public void updateInformationEntity() {
    JsonUtils.updateEntityElement(
      JsonUtils.JSON_FILENAME,
      INFORMATION_ENTITY_NAME,
      this.toJsonObject()
    );
  }

  public void deleteInformationEntity() {
    JsonUtils.deleteEntityElement(
      JsonUtils.JSON_FILENAME,
      INFORMATION_ENTITY_NAME,
      INFORMATION_ENTITY_POSITION
    );
  }

  @Override
  public String toJson() {
    return new Gson().toJson(this);
  }

  public static void main(String[] args) {
    InformationEntity information = new InformationEntity();
    information.setLast_updated();
    information.setLast_synced();
    information.updateInformationEntity();
    // information.deleteInformationEntity();
  }
}
