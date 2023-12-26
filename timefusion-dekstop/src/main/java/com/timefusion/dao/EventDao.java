package com.timefusion.dao;

import com.timefusion.model.Event;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The EventDao class is responsible for performing database operations related to the Event entity.
 * It extends the GenericDao class and provides specific implementations for inserting, updating, and deleting Event records.
 */
public class EventDao extends GenericDao<Event> {

  private static final String TABLE_NAME = "Event";
  private final Map<String, Class<?>> schema = new HashMap<>();

  public EventDao() {
    super(TABLE_NAME);
    defineSchema();
  }

  /**
   * Defines the schema for the EventDao class.
   * This method is responsible for defining the structure of the database table for events.
   * It sets the column names and their corresponding data types.
   */
  @Override
  protected void defineSchema() {
    try {
      schema.put("id", Integer.class);
      schema.put("title", String.class);
      schema.put("start_time", java.sql.Timestamp.class);
      schema.put("end_time", java.sql.Timestamp.class);
      schema.put("location", String.class);
      schema.put("description", String.class);
      schema.put("is_personal", Boolean.class);
      schema.put("creator_id", Integer.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves the value of a specific column from the given Event object.
   *
   * @param columnName the name of the column to retrieve the value from
   * @param event the Event object from which to retrieve the value
   * @return the value of the specified column in the Event object, or null if an exception occurs
   */
  private Object getColumnValue(String columnName, Event event) {
    try {
      return Event.class.getDeclaredMethod(
          "get" +
          columnName.substring(0, 1).toUpperCase() +
          columnName.substring(1)
        )
        .invoke(event);
    } catch (Exception e) {
      e.printStackTrace(); // Handle the exception appropriately
      return null;
    }
  }

  /**
   * Inserts a record into the database.
   *
   * @param event the Event object representing the record to be inserted
   * @return the number of rows affected by the insert operation
   * @throws SQLException if an error occurs while inserting the record
   * @throws IllegalArgumentException if the provided object is not of type Event or does not adhere to the expected schema
   */
  @Override
  public int insertRecord(Event event) throws SQLException {
    if (!this.validateSchema(event)) {
      throw new IllegalArgumentException(
        "Event object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, event));
    }

    return super.databaseUtil.insertRecord(TABLE_NAME, columnValues);
  }

  /**
   * Validates the schema of an event.
   *
   * @param event the event to validate
   * @return true if the schema is valid, false otherwise
   */
  @Override
  protected boolean validateSchema(Event event) {
    for (Map.Entry<String, Class<?>> entry : schema.entrySet()) {
      String columnName = entry.getKey();
      Class<?> expectedType = entry.getValue();
      Object columnValue = getColumnValue(columnName, event);

      if (columnValue == null || !expectedType.isInstance(columnValue)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Updates a record in the database.
   *
   * @param updatedEvent the Event object representing the record to be updated
   * @return the number of rows affected by the update operation
   * @throws SQLException if an error occurs while updating the record
   * @throws IllegalArgumentException if the provided object is not of type Event or does not adhere to the expected schema
   */
  @Override
  protected int updateRecordById(Event updatedEvent) throws SQLException {
    if (!this.validateSchema(updatedEvent)) {
      throw new IllegalArgumentException(
        "Updated Event object does not adhere to the expected schema."
      );
    }

    Map<String, Object> columnValues = new HashMap<>();
    for (String columnName : schema.keySet()) {
      columnValues.put(columnName, getColumnValue(columnName, updatedEvent));
    }

    long recordId = updatedEvent.getId();

    return super.databaseUtil.updateRecordById(
      TABLE_NAME,
      "id",
      recordId,
      columnValues
    );
  }

  /**
   * Deletes a record from the database.
   *
   * @param eventToDelete the Event object representing the record to be deleted
   * @return the number of rows affected by the delete operation
   * @throws SQLException if an error occurs while deleting the record
   * @throws IllegalArgumentException if the provided object is not of type Event or does not adhere to the expected schema
   */
  @Override
  protected int deleteRecordById(Event eventToDelete) throws SQLException {
    long recordId = eventToDelete.getId();

    return super.databaseUtil.deleteRecordById(TABLE_NAME, "id", recordId);
  }
}
