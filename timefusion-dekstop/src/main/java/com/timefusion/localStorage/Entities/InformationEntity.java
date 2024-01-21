package com.timefusion.localStorage.Entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.timefusion.localStorage.JsonUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InformationEntity implements JsonEntity {

  public static final String INFORMATION_ENTITY_NAME = "information";
  public static final int INFORMATION_ENTITY_POSITION = 0;
  private String lastUpdated;
  private String lastSynced;
  private boolean offlineState;

  public InformationEntity() {}

  public InformationEntity(
    String lastUpdated,
    String lastSynced,
    Boolean offlineState
  ) {
    this.lastUpdated = lastUpdated;
    this.lastSynced = lastSynced;
    this.offlineState = offlineState;
  }

  public InformationEntity(String lastUpdated, String lastSynced) {
    this(lastUpdated, lastSynced, getWasOffline());
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdatedNow() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd-MM-yyyy HH:mm:ss"
    );
    this.lastUpdated = LocalDateTime.now().format(formatter).toString();
  }

  public String getLastSynced() {
    return lastSynced;
  }

  public void setLastSyncedNow() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "dd-MM-yyyy HH:mm:ss"
    );
    this.lastSynced = LocalDateTime.now().format(formatter).toString();
  }

  public static boolean getWasOffline() {
    JsonElement informationJsonElement = JsonUtils.readJsonPart(
      INFORMATION_ENTITY_NAME
    );
    if (informationJsonElement.isJsonObject()) {
      JsonObject informationObject = informationJsonElement.getAsJsonObject();
      return informationObject.get("offline_state").getAsBoolean();
    }
    return true; // Default value because when infromation is empty it means that the user deconnected or has never been connected and he need to be online to connect
  }

  public boolean getWasOfflineState() {
    return offlineState;
  }

  public void setWasOffline(boolean offlineState) {
    this.offlineState = offlineState;
  }

  public static boolean isJsonInformationEntityEmpty() {
    JsonElement informationJsonElement = JsonUtils.readJsonPart(
      INFORMATION_ENTITY_NAME
    );

    if (informationJsonElement.isJsonObject()) {
      JsonObject informationObject = informationJsonElement.getAsJsonObject();
      return informationObject.entrySet().isEmpty();
    }

    return true;
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
    }
  }

  public void updateInformationEntity() {
    JsonUtils.updateEntityElement(
      JsonUtils.JSON_FILENAME,
      INFORMATION_ENTITY_NAME,
      this.toJsonObject()
    );
  }

  public static void deleteInformationEntity() {
    JsonUtils.deleteEntityElement(
      JsonUtils.JSON_FILENAME,
      INFORMATION_ENTITY_NAME,
      INFORMATION_ENTITY_POSITION
    );
  }

  public static InformationEntity getInfoEntityFromJson() {
    JsonElement infoJsonElement = JsonUtils.readJsonPart(
      INFORMATION_ENTITY_NAME
    );

    if (infoJsonElement.isJsonObject()) {
      JsonObject jsonObject = infoJsonElement.getAsJsonObject();
      if (!jsonObject.entrySet().isEmpty()) {
        return new InformationEntity(
          jsonObject.get("last_updated").getAsString(),
          jsonObject.get("last_synced").getAsString(),
          jsonObject.get("offline_state").getAsBoolean()
        );
      }
    }

    return new InformationEntity();
  }

  public static InformationEntity getInformationEntity() {
    JsonObject jsonObject = JsonUtils
      .readJsonPart(INFORMATION_ENTITY_NAME)
      .getAsJsonObject();
    return new InformationEntity(
      jsonObject.get("last_updated").getAsString(),
      jsonObject.get("last_synced").getAsString(),
      jsonObject.get("offline_state").getAsBoolean()
    );
  }

  @Override
  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("last_updated", lastUpdated);
    jsonObject.addProperty("last_synced", lastSynced);
    jsonObject.addProperty("offline_state", offlineState);
    return jsonObject;
  }

  @Override
  public String toString() {
    return (
      "InformationEntity{" +
      "last_updated=" +
      lastUpdated +
      ", last_synced=" +
      lastSynced +
      ", offline_state=" +
      offlineState +
      '}'
    );
  }

  @Override
  public String toJson() {
    return new Gson().toJson(this);
  }

  public static void main(String[] args) {
    InformationEntity information = new InformationEntity();
    information.setLastUpdatedNow();
    information.setLastSyncedNow();
    // information.updateInformationEntity();
    // information.deleteInformationEntity();
    // information.deleteInformationEntity();
    // System.out.println(InformationEntity.getInfoEntityFromJson().toString());
    // System.out.println(InformationEntity.isJsonInformationEntityEmpty());
    System.out.println(getInformationEntity());
  }
}
