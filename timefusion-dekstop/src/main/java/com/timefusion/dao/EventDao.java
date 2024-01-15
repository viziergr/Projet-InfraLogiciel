package com.timefusion.dao;

import com.timefusion.model.Event;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The EventDao class is responsible for performing database operations related to the Event entity.
 * It extends the GenericDao class and provides specific implementations for inserting, updating, and deleting Event records.
 */
public class EventDao extends GenericDao<Event> {

  private static final String TABLE_NAME = "Event";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public EventDao() throws SQLException {
    super(TABLE_NAME);
    defineSchema();
  }

  private Object getColumnValue(String columnName, Event event) {
    switch (columnName) {
      case "id":
        return event.getId();
      case "title":
        return event.getTitle();
      case "start_time":
        return event.getStartTime();
      case "end_time":
        return event.getEndTime();
      case "location":
        return event.getLocation();
      case "description":
        return event.getDescription();
      case "is_personal":
        return event.getIsPersonal();
      default:
        return null;
    }
  }

  private Event mapResultSetToUser(List<Map<String, Object>> result) {
    if (result.isEmpty()) {
      return null;
    } else if (result.size() > 1) {
      throw new IllegalArgumentException("More than event user found");
    }
    Event event = new Event(
      (int) result.get(0).get("id"),
      (String) result.get(0).get("title"),
      ((java.sql.Timestamp) result.get(0).get("start_time")).toLocalDateTime(),
      ((java.sql.Timestamp) result.get(0).get("end_time")).toLocalDateTime(),
      (String) result.get(0).get("location"),
      (String) result.get(0).get("description"),
      (boolean) result.get(0).get("is_personal")
    );
    return event;
  }

  private Map<String, Object> mapEventToColumnValues(Event event) {
    Map<String, Object> columnValues = new HashMap<>();

    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, event));
    }

    return columnValues;
  }

  public int insertEventRecord(Event event) throws SQLException {
    return super.databaseUtil.insertRecord(
      tableName,
      this.mapEventToColumnValues(event)
    );
  }

  public int updateEventRecord(Event event) throws SQLException {
    Map<String, Object> columnValues = mapEventToColumnValues(event);
    columnValues.remove("id");
    return super.databaseUtil.updateRecordById(
      tableName,
      "id",
      event.getId(),
      columnValues
    );
  }

  public int deleteEventRecord(Event event) throws SQLException {
    return super.databaseUtil.deleteRecordById(tableName, "id", event.getId());
  }

  public List<Event> retrieveEventsRecords(Map<String, Object> criteriaMap)
    throws SQLException {
    return this.mapEventToColumnValues(
        super.databaseUtil.retrieveRecords(tableName, criteriaMap)
      );
  }

  private List<Event> mapEventToColumnValues(List<Map<String, Object>> result) {
    List<Event> events = new ArrayList<>();

    for (Map<String, Object> row : result) {
      Event event = new Event(
        (int) row.get("id"),
        (String) row.get("title"),
        ((java.sql.Timestamp) row.get("start_time")).toLocalDateTime(),
        ((java.sql.Timestamp) row.get("end_time")).toLocalDateTime(),
        (String) row.get("location"),
        (String) row.get("description"),
        (boolean) row.get("is_personal")
      );
      events.add(event);
    }

    return events;
  }

  @Override
  protected void defineSchema() {
    try {
      schema.put("id", int.class);
      schema.put("title", String.class);
      schema.put("start_time", LocalDateTime.class);
      schema.put("end_time", LocalDateTime.class);
      schema.put("location", String.class);
      schema.put("description", String.class);
      schema.put("is_personal", boolean.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected int insertRecord(Event event) throws SQLException {
    return this.insertEventRecord(event);
  }

  @Override
  protected int updateRecordByEntity(Event event) throws SQLException {
    return this.updateEventRecord(event);
  }

  @Override
  protected List<Event> retrieveRecords(
    String tablename,
    Map<String, Object> criteriaMap
  ) throws SQLException {
    return this.retrieveEventsRecords(criteriaMap);
  }
}
