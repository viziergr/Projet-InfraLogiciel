package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.timefusion.jfxcalendar.JSON.JsonUtils;

public class TeamsEntity implements JsonEntity {

  public static final String TEAMS_ENTITY_NAME = "teams";
  public static final int TEAMS_ENTITY_POSITION = 2;

  private int id;
  private String name;
  private String description;

  public TeamsEntity() {}

  public TeamsEntity(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("id", id);
    jsonObject.addProperty("name", name);
    jsonObject.addProperty("description", description);
    return jsonObject;
  }

  public void addTeamEntity() {
    JsonUtils.addEntityArray(
      JsonUtils.JSON_FILENAME,
      TEAMS_ENTITY_NAME,
      this.toJsonObject()
    );
  }

  public void updateTeamEntity() {
    JsonUtils.updateEntityArray(
      JsonUtils.JSON_FILENAME,
      TEAMS_ENTITY_NAME,
      this.getId(),
      this.toJsonObject()
    );
  }

  public void deleteTeamEntity() {
    JsonUtils.deleteEntityArray(
      JsonUtils.JSON_FILENAME,
      TEAMS_ENTITY_NAME,
      this.getId()
    );
  }

  @Override
  public String toString() {
    return (
      "TeamsEntity{" +
      "id=" +
      id +
      ", name='" +
      name +
      '\'' +
      ", description='" +
      description +
      '\'' +
      '}'
    );
  }

  @Override
  public String toJson() {
    return new Gson().toJson(this);
  }
}
