package com.timefusion.jfxcalendar.JSON.Entities;

public enum EventNature {
  ADDED("Added"),
  UPDATED("Updated"),
  DELETED("Deleted"),
  UNCHANGED("Unchanged");

  private final String displayName;

  EventNature(String displayName) {
    this.displayName = displayName;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
