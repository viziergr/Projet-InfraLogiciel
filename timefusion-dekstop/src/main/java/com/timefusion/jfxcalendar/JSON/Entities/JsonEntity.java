package com.timefusion.jfxcalendar.JSON.Entities;

import com.google.gson.JsonObject;

public interface JsonEntity {
  /**
   * Converts the entity to a JSON string.
   *
   * @return The JSON string representation of the entity.
   */
  String toJson();

  /**
   * Converts the entity to a JsonObject.
   *
   * @return The JsonObject representation of the entity.
   */
  JsonObject toJsonObject();

  /**
   * Returns a string representation of the entity.
   *
   * @return The string representation of the entity.
   */
  String toString();
}
